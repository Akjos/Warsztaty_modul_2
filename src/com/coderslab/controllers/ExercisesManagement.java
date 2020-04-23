package com.coderslab.controllers;

import com.coderslab.DAO.ExercisesDAO;
import com.coderslab.DAO.SolutionsDAO;
import com.coderslab.databaseModel.Exercise;
import com.coderslab.utils.PrintInConsoleUtil;
import com.coderslab.utils.ScannerManager;

public class ExercisesManagement extends Manager{
    private ExercisesDAO dao;

    public ExercisesManagement() {
        this.dao = new ExercisesDAO();
        this.name = "\nExercises Menu\n";
    }

    @Override
    protected void add() {
        dao.create(getElement());
    }

    @Override
    protected void edit() {
        System.out.println("Enter id exercise");
        int id = ScannerManager.getNumber();
        Exercise exercise = getElement();
        exercise.setId(id);
        dao.update(exercise);
    }

    @Override
    protected void delete() {
        System.out.println("Enter id exercise to delete");
        int id = ScannerManager.getNumber();
        System.out.println("Deleting this exercise will delete this solutions:");
        PrintInConsoleUtil.showSolutions(new SolutionsDAO().findAllByExerciseId(id));
        if(confirmDelete()) {
            dao.delete(id);
        }
    }

    @Override
    protected void showAll() {
        Exercise[] exercises = dao.findAll();
        System.out.println("All exercises:");
        PrintInConsoleUtil.showExercises(exercises);
    }


    private Exercise getElement() {
        Exercise exercise = new Exercise();
        System.out.println("Enter title");
        exercise.setTitle(ScannerManager.getString());
        System.out.println("Enter description");
        exercise.setDescription(ScannerManager.getString());
        return exercise;
    }


}
