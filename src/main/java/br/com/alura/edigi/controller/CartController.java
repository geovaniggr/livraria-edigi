package br.com.alura.edigi.controller;

import br.com.alura.edigi.model.Book;
import br.com.alura.edigi.model.Cart;
import br.com.alura.edigi.repository.BookRepository;

public class CartController {

    private BookRepository bookRepository;
    private Cart cart = new Cart();

    public CartController(BookRepository bookRepository){
        this.bookRepository = bookRepository;
    }

    public void add(Book book){

        if( bookRepository.findByTitle(book.getTitle()).isEmpty())
            throw new IllegalArgumentException("O livro desejado não está em nosso catalogo");

        cart.addItem(book);
    }

    public String checkout(){
        return this.cart.checkout();
    }

}