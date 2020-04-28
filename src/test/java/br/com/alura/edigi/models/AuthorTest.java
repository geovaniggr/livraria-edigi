package br.com.alura.edigi.models;

import br.com.alura.edigi.model.Author;

public class AuthorTest {

    public static void main(String[] args) {

        try {
            System.out.println("Testando exceção de nome vazio");
            new Author("", "");

        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage() + "\n");
        }

        try {
            System.out.println("Testando exceção de email vazio");
            new Author("Geovani", "");
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage() + "\n");
        }

        try {
            System.out.println("Testando exceção de email com formato invalido");
            new Author("Geovani", "geovanigranieri");
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage() + "\n");
        }

        try {
            System.out.println("Testando se a RegEx não da um falso negativo");
            Author author4 = new Author("Geovani", "geovani.geovani@gmail.com");
            System.out.println(author4);
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
        }
    }
}