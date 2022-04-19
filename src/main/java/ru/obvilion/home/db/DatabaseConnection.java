package ru.obvilion.home.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private DatabaseSettings settings;
    private Connection connection;

    public DatabaseConnection(DatabaseSettings settings) {
        this.settings = settings;
    }

    public void connect() throws SQLException {
        connection = DriverManager.getConnection(settings.getURL(), settings.user, settings.password);
    }

    public Connection getConnection() {
        return connection;
    }
}
