package com.coderslab.utils;

import java.sql.*;

public class DBUtil {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/?useSSL=false&characterEncoding=utf8&allowPublicKeyRetrieval=true";
    private static final String DB_NAME = "programing_school";
    private static final String DB_USER = "root";
    private static final String DB_PASS = "coderslab";

    private static Connection getConnectionToLocalHost() throws SQLException {
        return DriverManager.getConnection(DB_URL, DB_USER, DB_PASS);
    }

    public static Connection getConnection() throws SQLException {
        Connection connection = getConnectionToLocalHost();
        connection.setCatalog(DB_NAME);
        return connection;
    }



    public static void executeStatement(String sql) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static void executeStatementInDatabase(String sql) {
        try (Connection connection = getConnectionToLocalHost();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

