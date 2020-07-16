package br.com.alura.edigi.models;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.alura.edigi.model.Category;

public class CategoryTest {

    @Test
    @DisplayName("Categoria com nome nulo ou vazio não deve ser criado")
    public void nullNameForCategory() {
        assertAll(
            () -> assertThrows(IllegalArgumentException.class, () -> new Category(null), "Categoria com nome nulo foi criado"),
            () -> assertThrows(IllegalArgumentException.class, () -> new Category(""), "Categoria com nome vazia foi criado")
        );
    }
}