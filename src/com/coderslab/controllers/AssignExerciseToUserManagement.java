package com.coderslab.controllers;

import com.coderslab.DAO.ExercisesDAO;
import com.coderslab.DAO.SolutionsDAO;
import com.coderslab.DAO.UserDAO;
import com.coderslab.databaseModel.Solution;
import com.coderslab.utils.PrintInConsoleUtil;
import com.coderslab.utils.ScannerManager;

public class AssignExerciseToUserManagement {

    private SolutionsDAO dao = new SolutionsDAO();

    private String MENU = "Select:\n" +
            "'1' - Add\n" +
            "'2' - View\n" +
            "'3' - Back";

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
        PrintInConsoleUtil.showUsers(new UserDAO().findAll());
        System.out.println("Select id User");
        int userId = ScannerManager.getNumber();
        Solution[] allUserSolutions = dao.findAllByUserId(userId);
        PrintInConsoleUtil.showSolutions(allUserSolutions);
    }


    private void add() {
        PrintInConsoleUtil.showUsers(new UserDAO().findAll());
        System.out.println("Select id User");
        int userId = ScannerManager.getNumber();
        PrintInConsoleUtil.showExercises(new ExercisesDAO().findAll());
        System.out.println("Select id Exercise");
        int exerciseId = ScannerManager.getNumber();
        Solution solution = new Solution(exerciseId, userId);
        dao.created(solution);
    }

}
