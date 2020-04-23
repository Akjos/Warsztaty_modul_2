package com.coderslab.controllers;

import com.coderslab.DAO.UserDAO;
import com.coderslab.DAO.UsersGroupsDAO;
import com.coderslab.databaseModel.UsersGroup;
import com.coderslab.utils.PrintInConsoleUtil;
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
        UsersGroup group = getElement();
        group.setId(id);
        dao.update(group);
    }

    @Override
    protected void delete() {
        System.out.println("Enter id group to delete");
        int id = ScannerManager.getNumber();
        System.out.println("Deleting this group will delete this users and all their solutions");
        PrintInConsoleUtil.showUsers(new UserDAO().findAllByGroupId(id));
        if(confirmDelete()) {
            dao.delete(id);
        }
    }

    @Override
    protected void showAll() {
        UsersGroup[] groups = dao.findAll();
        System.out.println("All User group:");
        PrintInConsoleUtil.showUsersGroups(groups);
    }

    private UsersGroup getElement() {
        UsersGroup group = new UsersGroup();
        System.out.println("Enter name");
        group.setName(ScannerManager.getString());
        return group;
    }
}
