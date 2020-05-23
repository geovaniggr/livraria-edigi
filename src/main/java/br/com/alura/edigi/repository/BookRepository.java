package br.com.alura.edigi.repository;

import java.util.HashSet;
import java.util.Set;

import br.com.alura.edigi.model.Book;

public class BookRepository {
   private static Set<Book> books = new HashSet<>();

   public void save(Book book){
       if( !(BookRepository.books.add(book)))
           throw new IllegalArgumentException("Já existe livro cadastrado com esse título");
   }

}