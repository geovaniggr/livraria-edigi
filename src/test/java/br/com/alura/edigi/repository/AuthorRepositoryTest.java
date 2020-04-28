package br.com.alura.edigi.repository;

import br.com.alura.edigi.model.Author;

public class AuthorRepositoryTest {
    public static void main(String[] args) {

        AuthorRepository authorRepository = new AuthorRepository();

        System.out.println("Testando o salvamento de um autor com mensagem de sucesso");

        try {
            Author author = new Author("Geovani", "geovani@alura.com.br");
            authorRepository.save(author);
            System.out.println("Autor Cadastrado com Sucesso!");
            System.out.println(author);

        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
        }

        try {
            System.out.println("\nTestando se o autor repetido não é cadastrado");
            authorRepository.save(new Author("Granieri", "geovani@alura.com.br"));
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage());
        }
    }
}