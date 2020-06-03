package br.com.alura.edigi.repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import br.com.alura.edigi.model.Book;

public class BookRepository {
   private static Set<Book> books = new HashSet<>();

   public void save(Book book){
       if( !(BookRepository.books.add(book)))
           throw new IllegalArgumentException("Já existe livro cadastrado com esse título");
   }

   public Optional<List<Book>> findByTitle(String title){
        List<Book> filteredBooks =  
                books.stream()
                .filter( book -> book.getTitle().toLowerCase().contains(title.toLowerCase()))
                .collect(Collectors.toList());
                            
        return filteredBooks.isEmpty()
            ? Optional.ofNullable(null) 
            : Optional.ofNullable(filteredBooks);
   }

}