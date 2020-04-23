package com.coderslab.utils;

import com.coderslab.DAO.ExercisesDAO;
import com.coderslab.DAO.SolutionsDAO;
import com.coderslab.DAO.UserDAO;
import com.coderslab.DAO.UsersGroupsDAO;
import com.coderslab.databaseModel.Exercise;
import com.coderslab.databaseModel.Solution;
import com.coderslab.databaseModel.User;
import com.coderslab.databaseModel.UsersGroup;

public class InitDatabase {

    public void initDatabase() {
        dropDatabase();
        createDatabase();
        createTables();
        fillDatabase();

    }

    private void dropDatabase() {
        String sql = "drop database IF EXISTS programing_school;";

        DBUtil.executeStatementInDatabase(sql);
    }


    private void createDatabase() {
        String sql = "create database programing_school CHARACTER SET utf8mb4 COLLATE utf8mb4_unicode_ci;";

        DBUtil.executeStatementInDatabase(sql);
    }

    private void createTables() {
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
                "foreign key (user_group_id) references users_groups(id) ON DELETE CASCADE);";

        String sqlSolutions = "create table solutions ( " +
                "id int primary key auto_increment, " +
                "created datetime, updated datetime, " +
                "description text, exercises_id int(11), " +
                "user_id int(11), " +
                "foreign key (exercises_id) references exercises(id) ON DELETE CASCADE, " +
                "foreign key (user_id) references users(id) ON DELETE CASCADE );";

        DBUtil.executeStatement(sqlUsersGroups);
        DBUtil.executeStatement(sqlExercises);
        DBUtil.executeStatement(sqlUsers);
        DBUtil.executeStatement(sqlSolutions);

    }

    private void fillDatabase() {
        fillUsersGroups();
        fillUsers();
        fillExercises();
        fillSolutions();
    }

    private void fillSolutions() {
        SolutionsDAO solutionsDAO = new SolutionsDAO();
        solutionsDAO.created(new Solution(1, 1));
        solutionsDAO.created(new Solution(1, 1));
        solutionsDAO.created(new Solution(2, 1));
        solutionsDAO.created(new Solution(3, 1));
        solutionsDAO.created(new Solution(1, 2));
        solutionsDAO.created(new Solution(3, 2));
        solutionsDAO.created(new Solution(1, 3));
        solutionsDAO.created(new Solution(2, 3));
        solutionsDAO.created(new Solution(1, 4));
    }

    private void fillExercises() {
        ExercisesDAO exercisesDAO = new ExercisesDAO();
        exercisesDAO.create(new Exercise("zadanie 1", "to jest jakiś opis"));
        exercisesDAO.create(new Exercise("zadanie 2", "to jest jakiś opis"));
        exercisesDAO.create(new Exercise("zadanie 3", "to jest jakiś opis"));
        exercisesDAO.create(new Exercise("zadanie 4", "to jest jakiś opis"));
    }

    private void fillUsers() {
        UserDAO userDAO = new UserDAO();
        userDAO.create(new User("Jan", "Jan@gmail.com", "password0", 1));
        userDAO.create(new User("Kazik", "Kazik@gmail.com", "password1", 1));
        userDAO.create(new User("Radek", "Radek@gmail.com", "password2", 1));
        userDAO.create(new User("Michał", "Michał@gmail.com", "password3", 2));
        userDAO.create(new User("Daniel", "Daniel@gmail.com", "password4", 2));
        userDAO.create(new User("Oskar", "Oskar@gmail.com", "password5", 3));
        userDAO.create(new User("Asia", "Asia@gmail.com", "password6", 3));
        userDAO.create(new User("Agnieszka", "Agnieszka@gmail.com", "password7", 3));
    }

    private void fillUsersGroups() {
        UsersGroupsDAO usersGroupsDAO = new UsersGroupsDAO();
        usersGroupsDAO.create(new UsersGroup("Group A"));
        usersGroupsDAO.create(new UsersGroup("Group B"));
        usersGroupsDAO.create(new UsersGroup("Group C"));
    }


}
