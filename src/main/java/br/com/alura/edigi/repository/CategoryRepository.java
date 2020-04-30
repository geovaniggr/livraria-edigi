package br.com.alura.edigi.repository;

import java.util.HashSet;
import java.util.Set;

import br.com.alura.edigi.model.Category;

public class CategoryRepository {

    private static Set<Category> categories = new HashSet<>();

    public void save(Category category){
        if( !(CategoryRepository.categories.add(category)) ){
            throw new IllegalArgumentException("Já existe uma categoria cadastrada com esse nome");
        }

    }


}