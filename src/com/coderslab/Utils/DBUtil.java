package com.coderslab.Utils;

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

    public static void dropDatabase() {
        String sql = "drop database IF EXISTS programing_school;";

        executeStatementInDatabase(sql);
    }



    public static void createDatabase() {
        String sql = "create database programing_school CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;";

        executeStatementInDatabase(sql);
    }

    public static void createTables() {
        String sqlUsersGroups = "create table users_groups ( " +
                "id int primary key auto_increment, " +
                "name varchar(255) );";

        String sqlExercises = "create table exercises ( " +
                "id int auto_increment primary key , " +
                "title varchar(255), " +
                "description text );";

        String sqlUsers = "create table users ( " +
                "id int(11) auto_increment primary key , " +
                "username varchar(255), email varchar(255), " +
                "password varchar(245), user_group_id int(11), " +
                "foreign key (user_group_id) references users_groups(id) );";

        String sqlSolutions = "create table solutions ( " +
                "id int primary key auto_increment, " +
                "created datetime, updated datetime, " +
                "description text, exercises_id int(11), " +
                "user_id int(11), " +
                "foreign key (exercises_id) references exercises(id), " +
                "foreign key (user_id) references users(id) );";

        executeStatement(sqlUsersGroups);
        executeStatement(sqlExercises);
        executeStatement(sqlUsers);
        executeStatement(sqlSolutions);
    }

    private static void executeStatement(String sql) {
        try (Connection connection = getConnection();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private static void executeStatementInDatabase(String sql) {
        try (Connection connection = getConnectionToLocalHost();
             PreparedStatement statement = connection.prepareStatement(sql)) {
            statement.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

}

