package com.coderslab;


import com.coderslab.controllers.MenuManager;
import com.coderslab.utils.InitDatabase;

public class Main {
    public static void main(String[] args) {
//        new InitDatabase().initDatabase(); /*inicjalizacja bazy danych z przykładowymi wartościami*/
        new MenuManager().start(args);

    }
}
