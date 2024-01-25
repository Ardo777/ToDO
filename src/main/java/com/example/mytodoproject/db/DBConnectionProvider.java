package com.example.mytodoproject.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public  class DBConnectionProvider {

    private static final String DB_URL = "jdbc:mysql://localhost:3306/myToDo";
    private static final String USERNAME="root";
    private static final String PASSWORD="root";
    private static DBConnectionProvider dbConnectionProvider = null;
    private Connection connection;

    private DBConnectionProvider() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    public  static DBConnectionProvider getInstance() {
        if (dbConnectionProvider == null) {
            dbConnectionProvider = new DBConnectionProvider();
        }
        return dbConnectionProvider;
    }

    public Connection getConnection() {
        try {
            if (connection == null || connection.isClosed()) {
                connection = DriverManager.getConnection(DB_URL, USERNAME, PASSWORD);
            }
        } catch (SQLException e) {
           e.printStackTrace();
        }
        return connection;
    }

}
