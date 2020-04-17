package com.coderslab;


import com.coderslab.controllers.AssignExerciseToUserManagement;
import com.coderslab.controllers.ExercisesManagement;
import com.coderslab.controllers.GroupManagement;
import com.coderslab.controllers.UsersManagement;
import com.coderslab.utils.InitDatabase;
import com.coderslab.utils.ScannerManager;

public class Main {
    public static void main(String[] args) {
//        new InitDatabase().initDatabase();
        startApp();

    }

    private static void startApp() {

        String menu_name = "Select:\n" +
                "'1' - User Manager\n" +
                "'2' - Exercise Manager\n" +
                "'3' - Group Manager\n" +
                "'4' - Assign Exercise to User\n" +
                "'5' - Quit";

        boolean flag = true;

        while (flag) {
            switch (ScannerManager.getMenu(menu_name)) {
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
        ScannerManager.closeScanner();
    }
}
