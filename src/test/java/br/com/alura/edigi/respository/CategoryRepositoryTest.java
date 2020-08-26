package br.com.alura.edigi.respository;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import br.com.alura.edigi.model.Category;
import br.com.alura.edigi.repository.CategoryRepository;

public class CategoryRepositoryTest {

    private final CategoryRepository categoryRepository = new CategoryRepository();

    @Test
    @DisplayName("Categoria não cadastrada deve ser inserida com sucesso")
    public void addAuthorToDatabase() {
        Category java = new Category("Java");
        categoryRepository.save(java);
        System.out.println("Categoria cadastrada com sucesso");
        System.out.println(java);
    }

    @Test
    @DisplayName("Autor com email já cadastrado deve lançar exceção")
    public void addAlreadyExistsAuthor() {
        assertThrows(IllegalArgumentException.class, () -> this.categoryRepository.save(new Category("Java")));
    }
}
