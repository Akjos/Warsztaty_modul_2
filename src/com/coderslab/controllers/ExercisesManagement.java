package com.coderslab.controllers;

import com.coderslab.DAO.ExercisesDAO;
import com.coderslab.databaseModel.Exercises;
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
        Exercises exercise = getElement();
        exercise.setId(id);
        dao.update(exercise);
    }

    @Override
    protected void delete() {
        System.out.println("Enter id exercise to delete");
        int id = ScannerManager.getNumber();
        System.out.println("Do you want delete this exercise [y/n]");
        while (true) {
            String temp = ScannerManager.getString();
            if (temp.equals("y")) {
                dao.delete(id);
                break;
            } else if (temp.equals("n")) {
                break;
            }
        }
    }

    @Override
    protected void showAll() {
        Exercises[] exercise = dao.findAll();
        System.out.println("All exercises:");
        for (Exercises exercises : exercise) {
            System.out.println(exercises);
        }
    }

    private Exercises getElement() {
        Exercises exercise = new Exercises();
        System.out.println("Enter title");
        exercise.setTitle(ScannerManager.getString());
        System.out.println("Enter description");
        exercise.setDescription(ScannerManager.getString());
        return exercise;
    }


}
