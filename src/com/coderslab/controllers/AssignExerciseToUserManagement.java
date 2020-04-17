package com.coderslab.controllers;

import com.coderslab.DAO.ExercisesDAO;
import com.coderslab.DAO.SolutionsDAO;
import com.coderslab.DAO.UserDAO;
import com.coderslab.databaseModel.Exercises;
import com.coderslab.databaseModel.Solutions;
import com.coderslab.databaseModel.User;
import com.coderslab.utils.ScannerManager;

public class AssignExerciseToUserManagement {

    private SolutionsDAO dao = new SolutionsDAO();

    private String MENU = "Select:\n" +
            "'1' - Add\n" +
            "'2' - View\n" +
            "'3' - Quit";

    private String NAME = "Assign Exercise to User";

    public void start() {

        boolean flag = true;

        while (flag) {
            System.out.println(NAME);
            switch (ScannerManager.getMenu(MENU)) {
                case 1:
                    add();
                    break;
                case 2:
                    view();
                    break;
                case 3:
                    flag = false;
                    break;
            }

        }
    }

    private void view() {
        showUsers();
        System.out.println("Select id User");
        int userId = ScannerManager.getNumber();
        Solutions[] allUserSolutions = dao.findAllByUserId(userId);
        showSolutions(allUserSolutions);
    }

    private void showSolutions(Solutions[] solutions) {
        for (Solutions solution: solutions) {
            System.out.println(solution);
        }
    }

    private void add() {
        showUsers();
        System.out.println("Select id User");
        int userId = ScannerManager.getNumber();
        showExercises();
        System.out.println("Select id Exercise");
        int exerciseId = ScannerManager.getNumber();
        Solutions solution = new Solutions(exerciseId, userId);
        dao.created(solution);
    }

    private void showExercises() {
        Exercises[] exercise = new ExercisesDAO().findAll();
        for (Exercises exercises : exercise) {
            System.out.println(exercises);
        }
    }

    protected void showUsers() {
        User[] users = new UserDAO().findAll();
        for (User user : users) {
            System.out.println(user);
        }
    }
}
