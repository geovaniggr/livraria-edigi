package br.com.alura.edigi.respository;

import java.math.BigDecimal;

import br.com.alura.edigi.model.Author;
import br.com.alura.edigi.model.Book;
import br.com.alura.edigi.model.Category;
import br.com.alura.edigi.repository.BookRepository;

public class BookRepositoryTest {

    public static void main(String[] args) {
        Author author = new Author("Geovani", "geovani@alura.com.br");
        Category category = new Category("Programação");
        Book book = new Book("Java8", "Um livro de Java", "1. O que é Java", "978-12-32334-12-1", new BigDecimal(50), 50,
                author, category);
        BookRepository bookRepository = new BookRepository();

        try {
            System.out.println("Testando o salvamento de um livro");
            bookRepository.save(book);
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage() + "\n");
        }

        try {
            System.out.println("Testando o salvamento de um livro repetido");
            bookRepository.save(book);
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage() + "\n");
        }
    }
}