package br.com.alura.edigi.respository;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.alura.edigi.model.Author;
import br.com.alura.edigi.repository.AuthorRepository;

public class AuthorRepositoryTest {

    private AuthorRepository authorRepository = new AuthorRepository();

    @Test
    @DisplayName("Autor não existente deve ser cadastrado")
    public void addAuthorToDatabase() {
        Author author = new Author("Geovani", "geovani@alura.com.br");
        this.authorRepository.save(author);
    }

    @Test
    @DisplayName("Autor com email já cadastrado deve lançar exceção")
    public void addAlreadyExistsAuthor(){
        assertThrows(IllegalArgumentException.class, () -> this.authorRepository.save(new Author("Granieri", "geovani@alura.com.br")));
    }
}