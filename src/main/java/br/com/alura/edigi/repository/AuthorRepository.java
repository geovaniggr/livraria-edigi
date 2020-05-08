package br.com.alura.edigi.repository;

import java.util.HashSet;
import java.util.Set;

import br.com.alura.edigi.model.Author;

public class AuthorRepository {
   
    private static Set<Author> authors = new HashSet<>();

    public boolean hasAuthor(Author author) {
        return AuthorRepository.authors.contains(author);
    }

    public void save(Author author){
        if( !(AuthorRepository.authors.add(author)) ) {
            throw new IllegalArgumentException("Já existe autor cadastrado com esse email!");
        }
    } 
}