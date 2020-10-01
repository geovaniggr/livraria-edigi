package br.com.alura.edigi.configurations;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import javax.sql.DataSource;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Connection;
import java.sql.SQLException;

public class ConnectionFactory {

    private static DataSource dataSource;

    private ConnectionFactory(){}

    public static Connection getConnection() throws SQLException {
        if(dataSource == null){
            setupDatabaseConfigurations();
        }

        return dataSource.getConnection();
    }

    private static void setupDatabaseConfigurations(){
        var path = Paths.get("", "src/main/resources/database.properties").toAbsolutePath().toString();
        var config = new HikariConfig(path);

        dataSource =  new HikariDataSource(config);
    }
}
