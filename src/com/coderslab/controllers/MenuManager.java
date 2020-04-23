package com.coderslab.controllers;

import com.coderslab.DAO.UserDAO;
import com.coderslab.databaseModel.User;
import com.coderslab.utils.ScannerManager;

public class MenuManager {
    private static String ADMINISTRATION_MENU = "Select:\n" +
            "'1' - User Manager\n" +
            "'2' - Exercise Manager\n" +
            "'3' - Group Manager\n" +
            "'4' - Assign Exercise to User\n" +
            "'5' - Quit";

    public void start(String[] users) {
        if(users.length == 0) {
            startAdministration();
        } else {
            startUser(users[0]);
        }
        ScannerManager.closeScanner();
    }

    private void startUser(String userEmail) {
        User user = new UserDAO().getUserByEmail(userEmail);
        if(user != null) {
            new ManagerForUser(user).start();
        } else {
            System.out.println("Wrong email address!");
        }
    }

    private void startAdministration() {
        boolean flag = true;

        while (flag) {
            switch (ScannerManager.getMenu(ADMINISTRATION_MENU)) {
                case 1:
                    new UsersManagement().start();
                    break;
                case 2:
                    new ExercisesManagement().start();
                    break;
                case 3:
                    new GroupManagement().start();
                    break;
                case 4:
                    new AssignExerciseToUserManagement().start();
                    break;
                case 5:
                    flag = false;
                    break;
            }
        }
    }
}
