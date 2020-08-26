package br.com.alura.edigi.models;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.alura.edigi.model.Author;

public class AuthorTest {

    @Test
    @DisplayName("Autor com dados inválidos não deve ser criado")
    public void nullNameForAuthor() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> new Author(null, "geovani@alura.com.br"), "Autor com nome nulo foi criado"),
            () -> assertThrows( IllegalArgumentException.class, () -> new Author("", "geovani@alura.com.br"), "Autor com nome vazio foi criado"),
            () -> assertThrows( IllegalArgumentException.class, () -> new Author("Geovani", null), "Autor com email nulo foi criado"),
            () -> assertThrows( IllegalArgumentException.class, () -> new Author("Geovani", ""), "Autor com email vazio foi criado")
        );
    }
    
    @Test()
    @DisplayName("Autor com email invalido não deve ser criado")
    public void invalidEmailForAuthor(){
        assertThrows( IllegalArgumentException.class, () -> new Author("Geovani", "geovanigranieri"));
    }

    @Test()
    @DisplayName("Autor com dados validos deve ser criado sem falso-negativo")
    public void validInputForAuthor(){
        new Author("Geovani", "geovani.geovani@gmail.com");
    }
}