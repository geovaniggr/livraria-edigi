package br.com.alura.edigi.models;

import br.com.alura.edigi.model.Category;

public class CategoryTest {

    public static void main(String[] args) {
        
        try {
            System.out.println("Testando a exceção de nome nulo para a categoria");
            new Category(null);
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage() + "\n");
        }

        try {
            System.out.println("Testando a exceção de nome vazio para a categoria");
            new Category("");
        } catch (Exception e) {
            System.out.println(e.getMessage() + "\n");
        }
    }
}