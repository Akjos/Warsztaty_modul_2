package com.coderslab;


import com.coderslab.DAO.ExercisesDAO;
import com.coderslab.DAO.SolutionsDAO;
import com.coderslab.DAO.UserDAO;
import com.coderslab.DAO.UsersGroupsDAO;
import com.coderslab.Utils.DBUtil;
import com.coderslab.databaseModel.Exercises;
import com.coderslab.databaseModel.Solutions;
import com.coderslab.databaseModel.User;
import com.coderslab.databaseModel.UsersGroups;

public class Main {
    public static void main(String[] args) {

        initDatabase();

    }

    private static void initDatabase() {
        DBUtil.dropDatabase();
        DBUtil.createDatabase();
        DBUtil.createTables();


        UsersGroupsDAO usersGroupsDAO = new UsersGroupsDAO();
        usersGroupsDAO.created(new UsersGroups("Group A"));
        usersGroupsDAO.created(new UsersGroups("Group B"));
        usersGroupsDAO.created(new UsersGroups("Group C"));
        usersGroupsDAO.created(new UsersGroups("Group D"));

        UserDAO userDAO = new UserDAO();
        userDAO.create(new User("Jan", "jan@gmail.com", "pssword", 1));
        userDAO.create(new User("tam", "tam@gmail.com", "pssword", 1));
        userDAO.create(new User("man", "man@gmail.com", "pssword", 2));
        userDAO.create(new User("can", "can@gmail.com", "pssword", 3));
        userDAO.create(new User("lan", "lan@gmail.com", "pssword", 3));

        ExercisesDAO exercisesDAO = new ExercisesDAO();
        exercisesDAO.created(new Exercises("zadanie 1", "to jest jakiś opis"));
        exercisesDAO.created(new Exercises("zadanie 2", "to jest jakiś opis"));
        exercisesDAO.created(new Exercises("zadanie 3", "to jest jakiś opis"));
        exercisesDAO.created(new Exercises("zadanie 4", "to jest jakiś opis"));

        SolutionsDAO solutionsDAO = new SolutionsDAO();
        solutionsDAO.created(new Solutions("to jest jakiś opis1", 1,1));
        solutionsDAO.created(new Solutions("to jest jakiś opis2", 1,2));
        solutionsDAO.created(new Solutions("to jest jakiś opis3", 3,1));
        solutionsDAO.created(new Solutions("to jest jakiś opis4", 3,1));
    }


}
