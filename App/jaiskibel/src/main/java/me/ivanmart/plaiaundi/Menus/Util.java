package me.ivanmart.plaiaundi.Menus;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Util {
    public static final Scanner sc = new Scanner(System.in);

    public static boolean checkDNI(String dni){ // Validar Dni
        if (dni.length() != 9) return false;
        return dni.matches("\\d{8}[A-Z]"); // TODO: Calcular si letra pertenece a número
    }

    public static String getString(String txt){
        System.out.println(txt);
        String str = sc.nextLine();
        if (str.isBlank() || str.equals("\n")) return sc.nextLine();
        return str;
    }
    // +--------------------------------------+
    public static int getInt(String txt){
        System.out.println(txt);
        return sc.nextInt();
    }
    public static int getInt(){
        return sc.nextInt();
    }
    // +--------------------------------------+

    public static <T extends Enum<T>> T toEnum(Class<T> enumClass, String str) { // Funcion para pasar string a Enum
        T tipo;
        try {
            tipo = Enum.valueOf(enumClass, str.toUpperCase());
        } catch (Exception e) {
            return null;
        }
        return tipo;
    }
    public static <T extends Enum<T>> T toEnum(Class<T> enumClass, String str, T def) { // Funcion para pasar string a Enum
        T en = toEnum(enumClass, str);
        if (en == null) return def;
        return en;
    }

    public static <T extends Enum<T>> T getEnum(Class<T> enumClass, String txt) {
        String str = getString(txt);
        T en = toEnum(enumClass, str);
        while (en == null){
            System.out.print("[Error] Valor inválido. ");
            en = toEnum(enumClass, str);
        }
        return en;
    }

    public static String getPass(String txt){ //Recoger la contraseña del ususario
        Console console = System.console();
        char[] pass = console.readPassword("Enter your password: ");
        String str = "";
        for (char c : pass) str += c;
        return str;
    }

    public static String generateTable(String[] titles, ArrayList<String[]> vals){ // Generar una tabala
        int[] max = new int[titles.length];
        for (int i = 0; i < titles.length; i++) max[i] = titles[i].length();
        for (String[] row : vals){
            for (int i = 0; i < titles.length; i++) max[i] = Math.max(max[i], row[i].length());
        }
        String bar = "+%s+%n".formatted("-".repeat(Arrays.stream(max).sum() + 2*max.length + 7));
        String inner = bar;
        String header = "|";


        for(int i = 0; i < titles.length; i++) header += " %s%s |".formatted(titles[i], " ".repeat(max[i] - titles[i].length()));
        inner += header + "\n" + bar;
        for (String[] row : vals){
            String strRow = "|";
            for (int i = 0; i < titles.length; i++){
                strRow += " %s%s |".formatted(row[i], " ".repeat(max[i] - row[i].length()));
            }
            inner += strRow + "\n";
        }
        inner += bar;
        return inner;
    }
}
