package com.coderslab.controllers;

import com.coderslab.utils.ScannerManager;

public abstract class Manager {

    private static String MENU = "Select: \n" +
            "'1' - Add\n" +
            "'2' - Edit\n" +
            "'3' - Delete\n" +
            "'4' - Back\n";

    protected String name = "";

    public void start() {

        boolean flag = true;

        while (flag) {
            showAll();
            System.out.println(name);
            switch (ScannerManager.getMenu(MENU)) {
                case 1:
                    add();
                    break;
                case 2:
                    edit();
                    break;
                case 3:
                    delete();
                    break;
                case 4:
                    flag = false;
                    break;
            }

        }
    }

    protected boolean confirmDelete() {
        System.out.println("Do you want delete this element [y/n]");
        while (true) {
            String temp = ScannerManager.getString();
            if (temp.equals("y")) {
                return true;
            } else if (temp.equals("n")) {
                return false;
            }
        }
    }

    abstract void add();

    abstract void edit();

    abstract void delete();


    abstract void showAll();
}
