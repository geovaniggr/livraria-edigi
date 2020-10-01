package br.com.alura.edigi.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import br.com.alura.edigi.model.Category;

public class CategoryRepository {

    private Connection connection;
    public CategoryRepository(Connection connection){
        this.connection = connection;
    }

    public boolean hasCategory(Category category){
        var sql = """
                SELECT EXISTS(SELECT name FROM category WHERE name = ?)
                """;
        try ( var query = connection.prepareStatement(sql)){
            query.setString(1, category.getName());

            var response = query.executeQuery();
            response.next();

            return response.getBoolean(1);
        } catch (SQLException exception) {
            throw new RuntimeException("Houve um erro com o servidor.");
        }

    }

    public boolean save(Category category){
        var sql = """
                INSERT INTO category(name) 
                VALUES (?) 
        """;

        try (var statement = connection.prepareStatement(sql)){

            statement.setString(1, category.getName());

            return !statement.execute();

        } catch (SQLException exception) {
            return false;
        }
    }
}