package br.com.alura.edigi.controller;

import java.util.List;
import java.util.Optional;

import br.com.alura.edigi.model.Book;
import br.com.alura.edigi.repository.AuthorRepository;
import br.com.alura.edigi.repository.BookRepository;
import br.com.alura.edigi.repository.CategoryRepository;

public class BookController {
   
    private BookRepository bookRepository;
    private AuthorRepository authorRepository;
	private CategoryRepository categoryRepository;

    public BookController(BookRepository bookRepository, AuthorRepository authorRepository,  CategoryRepository categoryRepository){
        this.bookRepository = bookRepository;
        this.authorRepository = authorRepository;
        this.categoryRepository = categoryRepository;
    }

    public boolean store(Book book) {
        if(!categoryRepository.hasCategory(book.getCategory()))
            throw new IllegalArgumentException("Não existe categoria cadastrada com esse nome!");
       
        if(!authorRepository.hasAuthor(book.getAuthor()))
            return false;

        this.bookRepository.save(book);

        return true;
    }

    public List<Book> findByTitle(String title){
        
        if( title.isEmpty() || title == null || title.length() < 2)
            throw new IllegalArgumentException("A busca por livro deve conter pelo menos 2 caracteres");
        
        Optional<List<Book>> searchResult = bookRepository.findByTitle(title);

        if(searchResult.isEmpty())
            throw new IllegalArgumentException("Nenhum livro encontrado com essse título");

        return searchResult.get();
    }

}