package me.ivanmart.plaiaundi.Menus;

import java.io.Console;
import java.util.Scanner;

public class Util {
    public static final Scanner sc = new Scanner(System.in);

    public static boolean checkDNI(String dni){
        if (dni.length() != 9) return false;
        return dni.matches("\\d{8}[A-Z]"); // TODO: Calcular si letra pertenece a número
    }

    public static String getString(String txt){
        System.out.println(txt);
        String str = sc.nextLine();
        if (str.isBlank() || str.equals("\n")) return sc.nextLine();
        return str;
    }

    public static <T extends Enum<T>> T toEnum(Class<T> enumClass, String str) {
        T tipo;
        try {
            tipo = Enum.valueOf(enumClass, str);
        } catch (Exception e) {
            System.out.print("[Error] Valor inválido. ");
            return toEnum(enumClass, str);
        }
        return tipo;
    }

    public static <T extends Enum<T>> T getEnum(Class<T> enumClass, String txt) {
        String str = getString(txt).toUpperCase();
        return toEnum(enumClass, str);
    }

    public static String getPass(String txt){
        Console console = System.console();
        char[] pass = console.readPassword("Enter your password: ");
        String str = "";
        for (char c : pass) str += c;
        return str;
    }
}
