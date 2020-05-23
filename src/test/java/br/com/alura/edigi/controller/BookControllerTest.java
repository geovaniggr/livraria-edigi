package br.com.alura.edigi.controller;

import java.math.BigDecimal;

import br.com.alura.edigi.model.Author;
import br.com.alura.edigi.model.Book;
import br.com.alura.edigi.model.Category;
import br.com.alura.edigi.repository.AuthorRepository;
import br.com.alura.edigi.repository.BookRepository;
import br.com.alura.edigi.repository.CategoryRepository;

public class BookControllerTest {
    public static void main(String[] args) {
        BookRepository bookRepository = new BookRepository();
        AuthorRepository authorRepository = new AuthorRepository();
        CategoryRepository categoryRepository = new CategoryRepository();
        BookController bookController = new BookController(bookRepository, authorRepository, categoryRepository);
    
        Author author = new Author("Geovani", "geovani@alura.com.br");
        Category category = new Category("Programação");
    
        categoryRepository.save(category);
        authorRepository.save(author);
    
        Book book = new Book("Java8", "Um livro de Java", "1. O que é Java", "978-12-12134-12-1", new BigDecimal(50), 50, author, category);
        Book bookWithoutAuthor = new Book("Java8", "Um livro de Java", "1. O que é Java", "978-12-12314-12-1", new BigDecimal(50), 50, new Author("jose", "jose@alura.com"),category);
        Book bookWithoutCategory = new Book("Java8", "Um livro de Java", "1. O que é Java", "978-12-12334-12-1", new BigDecimal(50), 50, author, new Category("Não Cadastrado"));
        
        try {
            System.out.println("Testando a execção de livro inserido com categoria inexistente");
            bookController.store(bookWithoutCategory);
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage() + "\n");
        }
        
        try {
            System.out.println("Testando a execção de livro inserido com autor inexistente");
            bookController.store(bookWithoutAuthor);
        } catch (IllegalArgumentException error){
            System.out.println(error.getMessage() + "\n");
        }
        
        System.out.println("Testando o salvamento de um livro com dados corretos");
        System.out.println(bookController.store(book));
    }
    
}