package br.com.alura.edigi.respository;

import br.com.alura.edigi.configurations.ConnectionFactory;
import br.com.alura.edigi.model.Category;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.alura.edigi.model.Author;
import br.com.alura.edigi.repository.AuthorRepository;

import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class AuthorRepositoryTest {

    private AuthorRepository repository;
    private Connection connection;

    public AuthorRepositoryTest() throws SQLException  {
        connection = ConnectionFactory.getConnection();
        repository = new AuthorRepository(connection);
    }

    @BeforeEach
    void cleanup() throws SQLException{
        connection.prepareStatement("truncate author cascade").execute();
    }

    @AfterEach
    void close() throws SQLException {
        connection.close();
    }

    @Test
    @DisplayName("Autor não existente deve ser cadastrado")
    public void addAuthorToDatabase() {
        var author = new Author("Geovani", "geovani@alura.com.br");
        repository.save(author);
    }

    @Test
    @DisplayName("Autor com email já cadastrado deve lançar exceção")
    public void addAlreadyExistsAuthor(){
        var author = new Author("Geovani", "geovani@alura.com.br");
        repository.save(author);
        assertThrows(IllegalArgumentException.class, () -> repository.save(author));
    }

    @Test
    @DisplayName("Busca por autor existente deve retornar verdadeiro")
    public void searchForExistentAuthor(){
        var author = new Author("Geovani", "geovani@alura.com.br");
        repository.save(author);

        assertTrue(repository.hasAuthor(author));
    }

    @Test
    @DisplayName("Busca por autor não existente deve retornar false")
    public void searchForNonexistentAuthor(){
        var author = new Author("Geovani", "geovani@alura.com.br");
        assertFalse(repository.hasAuthor(author));
    }
}