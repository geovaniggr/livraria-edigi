package br.com.alura.edigi.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import br.com.alura.edigi.model.Author;
import br.com.alura.edigi.model.Book;
import br.com.alura.edigi.model.Category;

public class BookRepository {
   private Connection connection;

   public BookRepository(Connection connection){
       this.connection = connection;
   }

   public boolean save(Book book){
       var sql = """
                INSERT INTO book (title, isbn, synopsis, table_of_contents, number_of_pages, price, edition, author_email, category_name) \
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? )
           """;

       try (var queryStatement = connection.prepareStatement(sql)){

           queryStatement.setString(1, book.getTitle());
           queryStatement.setString(2, book.getIsbn());
           queryStatement.setString(3, book.getSynopsis());
           queryStatement.setString(4, book.getTableOfContents());
           queryStatement.setInt(5, book.getNumberOfPages());
           queryStatement.setBigDecimal(6, book.getPrice());
           queryStatement.setInt(7, book.getEdition());
           queryStatement.setString(8, book.getAuthor().getEmail());
           queryStatement.setString(9, book.getCategory().getName());

           var response = queryStatement.execute();
            return !response;
       } catch (SQLException exception) {
           throw new IllegalArgumentException("Houve um erro!. Já existe livro cadastrado com esse ISBN ou titulo");
       }
   }

   public Optional<List<Book>> findByTitle(String title) {
       var sql = """
                SELECT \
                    b.*,  \
                    a.name as author__name, a.created_at as author__createdat, a.email as author__email, \
                    c.name as category__name, c.created_at as category__createdat \
                FROM book b \
                LEFT JOIN author a ON b.author_email = a.email \
                LEFT JOIN category c ON b.category_name = c.name \
                WHERE b.title LIKE ?  \
        """;

       var resultList = new ArrayList<Book>();

       try (var query = connection.prepareStatement(sql)){
           query.setString(1, "%%%s%%".formatted(title));

           var result = query.executeQuery();

           while(result.next()){

               var book = new Book(
                       result.getString("title"),
                       result.getString("synopsis"),
                       result.getString("table_of_contents"),
                       result.getString("isbn"),
                       result.getBigDecimal("price"),
                       result.getInt("number_of_pages"),
                       new Author(result.getString("author__name"), result.getString("author__email"), result.getObject("author__createdat", LocalDateTime.class)),
                       new Category(result.getString("category__name"), result.getObject("category__createdat", LocalDateTime.class))
               );
               book.setEdition(result.getInt("edition"));
               resultList.add(book);
           }
       } catch (SQLException exception) {
           throw new RuntimeException("Houve um erro: " + exception.getCause());
       }

       return resultList.isEmpty()
               ? Optional.ofNullable(null)
               : Optional.ofNullable(resultList);
   }

   public boolean existsByTitle(String title){
       var sql = """
            SELECT EXISTS(SELECT title FROM book WHERE title = ? )
               """;

       try( var query = connection.prepareStatement(sql)){
           query.setString(1, title);
           var response = query.executeQuery();
           response.next();

           return response.getBoolean(1);
       } catch (SQLException exception) {
            throw new RuntimeException("Houve um problema com o servidor!");
       }
   }
}