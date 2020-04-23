package com.coderslab.utils;

import java.util.Scanner;

public class ScannerManager {
    private static Scanner scanner = new Scanner(System.in);

    public static int getMenu(String menu) {
        System.out.println(menu);
        return getNumber();
    }

    public static int getNumber() {
        int number;
        while (!scanner.hasNextInt()) {
            System.out.print("Select proper number\n");
            scanner.next();
        }
        number = scanner.nextInt();
        scanner.nextLine();
        return number;
    }

    public static void closeScanner() {
        scanner.close();
    }

    public static String getString() {
        return scanner.nextLine();
    }
}
