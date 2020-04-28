package br.com.alura.edigi.repository;

import java.util.ArrayList;
import java.util.List;

import br.com.alura.edigi.model.Author;

public class AuthorRepository {
   
    private static List<Author> authors = new ArrayList<>();

    public boolean hasAuthor(Author author){
        return AuthorRepository.authors.contains(author);
    }

    public void save(Author author){
        if(hasAuthor(author)) throw new IllegalArgumentException("Já existe autor cadastrado com esse email");

        AuthorRepository.authors.add(author);
    } 
}