package com.coderslab.controllers;

import com.coderslab.DAO.SolutionsDAO;
import com.coderslab.DAO.UserDAO;
import com.coderslab.databaseModel.User;
import com.coderslab.utils.PrintInConsoleUtil;
import com.coderslab.utils.ScannerManager;

public class UsersManagement extends Manager {
    private UserDAO dao;

    public UsersManagement() {
        this.dao = new UserDAO();
        this.name = "\nUser Menu\n";
    }

    @Override
    protected void add() {
        dao.create(getElement());
    }

    @Override
    protected void edit() {
        System.out.println("Enter id user");
        int id = ScannerManager.getNumber();
        User user = getElement();
        user.setId(id);
        dao.update(user);
    }

    @Override
    protected void delete() {
        System.out.println("Enter id user to delete");
        int id = ScannerManager.getNumber();
        System.out.println("Deleting this user will delete this solutions:");
        PrintInConsoleUtil.showSolutions(new SolutionsDAO().findAllByUserId(id));
        if(confirmDelete()) {
            dao.delete(id);
        }
    }

    @Override
    protected void showAll() {
        User[] users = dao.findAll();
        System.out.println("All users:");
        PrintInConsoleUtil.showUsers(users);
    }

    private User getElement() {
        User user = new User();
        System.out.println("Enter name");
        user.setUsername(ScannerManager.getString());
        System.out.println("Enter email");
        user.setEmail(ScannerManager.getString());
        System.out.println("Enter password");
        user.setNewPassword(ScannerManager.getString());
        System.out.println("Enter group id");
        user.setUserGroupId(ScannerManager.getNumber());
        return user;
    }


}