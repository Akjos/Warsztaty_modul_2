package com.coderslab.controllers;

import com.coderslab.DAO.SolutionsDAO;
import com.coderslab.databaseModel.Solution;
import com.coderslab.databaseModel.User;
import com.coderslab.utils.PrintInConsoleUtil;
import com.coderslab.utils.ScannerManager;

public class ManagerForUser {
    private static String MENU = "Select:\n" +
            "'1' - Add solution to exercise\n" +
            "'2' - View done exercise\n" +
            "'3' - Quit";

    private User user;
    private SolutionsDAO dao;

    public ManagerForUser(User user) {
        this.user = user;
        dao = new SolutionsDAO();
    }

    public void start() {

        boolean flag = true;

        while (flag) {
            switch (ScannerManager.getMenu(MENU)) {  /*Wiem że tu powtażam kod z AssignExerciseToUserManager ale to dlaego że powinno się tu pojawić więcej opcji*/
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

    private void add() {
        Solution[] solutionToDo = getSolutionToDo();
        if (solutionToDo.length == 0) {
            System.out.println("You don't have exercise to do");
        } else {
            System.out.println("Exercise:");
            showSolutions(solutionToDo);
            while (true) {
                System.out.println("Select exercise by id or select '-1' to quit:");
                int id = ScannerManager.getNumber();
                if (id != -1) {
                    Solution solution = getSolution(solutionToDo, id);
                    if (solution != null) {
                        System.out.println("Set Your Solution:");
                        solution.setDescription(ScannerManager.getString());
                        dao.update(solution);
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    }

    private Solution getSolution(Solution[] solutionToDo, int id) {
        for (Solution tempSol : solutionToDo) {
            if (id == tempSol.getId()) {
                return tempSol;
            }
        }
        System.out.println("You don't have exercise to do by this id");
        return null;
    }

    private void view() {
        showSolutions(dao.findAllByUserId(user.getId()));
    }

    private Solution[] getSolutionToDo() {
        return dao.findAllUndoneByUserId(user.getId());
    }

    private void showSolutions(Solution[] solutions) {
        PrintInConsoleUtil.showSolutions(solutions);
    }


}
