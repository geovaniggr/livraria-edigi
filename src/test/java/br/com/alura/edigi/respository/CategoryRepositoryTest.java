package br.com.alura.edigi.respository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.alura.edigi.configurations.ConnectionFactory;
import br.com.alura.edigi.model.Category;
import br.com.alura.edigi.repository.CategoryRepository;

public class CategoryRepositoryTest {

    private CategoryRepository repository;

    private Connection connection;

    public CategoryRepositoryTest() throws SQLException {
        connection = ConnectionFactory.getConnection();
        repository = new CategoryRepository(connection);
    }

    @BeforeEach
    void cleanup() throws SQLException{
        connection.prepareStatement("truncate category cascade").execute();
    }

    @AfterEach
    void close() throws SQLException {
        connection.close();
    }

    @Test
    @DisplayName("Categoria não cadastrada deve ser inserida com sucesso")
    public void addCategory() {
        var category = new Category("Java");
        assertTrue(repository.save(category));
    }

    @Test
    @DisplayName("Categoria repetida deve lançar exceção ")
    public void addRepeatedCategory() {
        var category = new Category("Java");
        repository.save(category);
        assertFalse(repository.save(category));
    }

    @Test
    @DisplayName("Busca por categoria existente deve retornar verdadeiro")
    public void searchCategory(){
        var category = new Category("Java");
        repository.save(category);

        assertTrue(repository.hasCategory(category));
    }

    @Test
    @DisplayName("Busca por categoria inexistente deve retornar falso")
    public void searchForNonexistentCategory(){
        var category = new Category("NodeJS");
        assertFalse(repository.hasCategory(category));
    }
}
