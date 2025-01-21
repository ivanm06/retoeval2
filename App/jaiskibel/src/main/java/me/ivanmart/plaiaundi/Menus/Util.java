package me.ivanmart.plaiaundi.Menus;

import java.util.Scanner;

public class Util {
    public static final Scanner sc = new Scanner(System.in);

    public static boolean checkDNI(String dni){
        if (dni.length() != 9) return false;
        return dni.matches("/\\d{8}[A-Z]/gm");
    }
}
