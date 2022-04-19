package ru.obvilion.home.db;

public class DatabaseSettings {
    public final String host, database, user, password;
    public final int port;

    public DatabaseSettings(String host, int port, String database, String user, String password) {
        this.host = host;
        this.port = port;
        this.database = database;
        this.user = user;
        this.password = password;
    }

    public String getURL() {
        return "jdbc:postgresql://" + host + ":" + port + "/" + database;
    }
}
