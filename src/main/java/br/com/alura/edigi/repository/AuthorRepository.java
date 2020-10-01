package br.com.alura.edigi.repository;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Timestamp;

import br.com.alura.edigi.model.Author;

public class AuthorRepository {
   
    private Connection connection;

    public AuthorRepository(Connection connection) {
        this.connection = connection;
    }

    public boolean hasAuthor(Author author){
        var sql = """
                SELECT EXISTS(SELECT email FROM author WHERE email = ?)
        """;
        try (var query = connection.prepareStatement(sql)){
            query.setString(1, author.getEmail());

            var response = query.executeQuery();
            response.next();

            return response.getBoolean(1);
        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }

    public boolean save(Author author){
        var sql = """
                INSERT INTO author(name, email) 
                VALUES (?, ?) 
        """;

        try (var statement = connection.prepareStatement(sql)){
            statement.setString(1, author.getName());
            statement.setString(2, author.getEmail());

            return !statement.execute();

        } catch (SQLException exception) {
            exception.printStackTrace();
            return false;
        }
    }
}