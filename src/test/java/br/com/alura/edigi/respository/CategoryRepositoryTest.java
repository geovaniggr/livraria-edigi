package br.com.alura.edigi.respository;

import br.com.alura.edigi.model.Category;
import br.com.alura.edigi.repository.CategoryRepository;

public class CategoryRepositoryTest {

    public static void main(String[] args) {
        CategoryRepository categoryRepository = new CategoryRepository();
        
        try {
            System.out.println("Cadastro correto de uma categoria retornando a mensagem de suecesso");
            Category java = new Category("Java");
            categoryRepository.save(java);
            System.out.println("Categoria cadastrada com sucesso");
            System.out.println(java);

        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage() + "\n");
        }

        System.out.println();

        try {
            System.out.println("Testando a exceção de categoria repetida");
            categoryRepository.save(new Category("Java"));
        } catch (IllegalArgumentException error) {
            System.out.println(error.getMessage() + "\n");
        }
    }
}