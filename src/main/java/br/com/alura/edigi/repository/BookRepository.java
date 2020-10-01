package br.com.alura.edigi.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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
                INSERT INTO book (title, isbn, synopsis, table_of_contents, number_of_pages, price, edition, author_id, category_id) \
                VALUES (?, ?, ?, ?, ?, ?, ?, ?, ? )
           """;

       try (var queryStatement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

           queryStatement.setString(1, book.getTitle());
           queryStatement.setString(2, book.getIsbn());
           queryStatement.setString(3, book.getSynopsis());
           queryStatement.setString(4, book.getTableOfContents());
           queryStatement.setInt(5, book.getNumberOfPages());
           queryStatement.setBigDecimal(6, book.getPrice());
           queryStatement.setInt(7, book.getEdition());
           queryStatement.setLong(8, book.getAuthor().getId());
           queryStatement.setLong(9, book.getCategory().getId());

           var response = queryStatement.execute();

           var returnedKeys = queryStatement.getGeneratedKeys();

           while(returnedKeys.next()){
               book.setId(returnedKeys.getLong("id"));
           }

            return !response;
       } catch (SQLException exception) {
           exception.printStackTrace();

           return false;
       }
   }

   public Optional<List<Book>> findByTitle(String title) {
       var sql = """
                SELECT \
                    b.*,  \
                    a.id as author__id, a.name as author__name, a.created_at as author__createdat, a.email as author__email, \
                    c.id as category__id, c.name as category__name, c.created_at as category__createdat \
                FROM book b \
                LEFT JOIN author a ON b.author_id = a.id \
                LEFT JOIN category c ON b.category_id = c.id \
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
                       new Author(result.getLong("author__id")  , result.getString("author__name"), result.getString("author__email"), result.getTimestamp("author__createdat").toLocalDateTime()),
                       new Category( result.getLong("category__id"), result.getString("category__name"), result.getTimestamp("category__createdat").toLocalDateTime())
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