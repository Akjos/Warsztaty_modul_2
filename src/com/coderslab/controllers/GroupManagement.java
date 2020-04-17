package com.coderslab.controllers;

import com.coderslab.DAO.UsersGroupsDAO;
import com.coderslab.databaseModel.UsersGroups;
import com.coderslab.utils.ScannerManager;

public class GroupManagement extends Manager {
    UsersGroupsDAO dao;
    public GroupManagement() {
        this.dao = new UsersGroupsDAO();
        this.name = "\nGroup Manager\n";
    }

    @Override
    protected void add() {
        dao.create(getElement());
    }

    @Override
    protected void edit() {
        System.out.println("Enter id group");
        int id = ScannerManager.getNumber();
        UsersGroups group = getElement();
        group.setId(id);
        dao.update(group);
    }

    @Override
    protected void delete() {
        System.out.println("Enter id group to delete");
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
        UsersGroups[] groups = dao.findAll();
        System.out.println("All User group:");
        for (UsersGroups group : groups) {
            System.out.println(group);
        }
    }

    private UsersGroups getElement() {
        UsersGroups group = new UsersGroups();
        System.out.println("Enter name");
        group.setName(ScannerManager.getString());
        return group;
    }
}
