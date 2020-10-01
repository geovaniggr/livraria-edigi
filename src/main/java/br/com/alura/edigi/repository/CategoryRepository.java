package br.com.alura.edigi.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
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

        try (var statement = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)){

            statement.setString(1, category.getName());

            var response = statement.execute();

            var createdKeys = statement.getGeneratedKeys();

            while(createdKeys.next()){
                category.setCreatedAt(createdKeys.getTimestamp("created_at").toLocalDateTime());
            }

            return !response;

        } catch (SQLException exception) {
            return false;
        }
    }
}