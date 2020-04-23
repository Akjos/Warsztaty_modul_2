package com.coderslab.utils;

import com.coderslab.DAO.ExercisesDAO;
import com.coderslab.databaseModel.Exercise;
import com.coderslab.databaseModel.Solution;
import com.coderslab.databaseModel.User;
import com.coderslab.databaseModel.UsersGroup;

public class PrintInConsoleUtil {

    public static void showSolutions(Solution[] solutions) {
        for (Solution solution : solutions) {
            System.out.println(solution);
        }
    }

    public static void showExercises(Exercise[] exercises) {
        for (Exercise exercise : exercises) {
            System.out.println(exercise);
        }
    }
    public static void showUsers(User[] users) {
        for (User user : users) {
            System.out.println(user);
        }
    }
    public static void showUsersGroups(UsersGroup[] usersGroups) {
        for (UsersGroup usersGroup : usersGroups) {
            System.out.println(usersGroup);
        }
    }
}
