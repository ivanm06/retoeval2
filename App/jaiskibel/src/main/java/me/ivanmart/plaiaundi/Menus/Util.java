package me.ivanmart.plaiaundi.Menus;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Util {
    public static final Scanner sc = new Scanner(System.in);

    /**
        Comprueba si un DNI es válido o no usando regex.


    */
    public static boolean checkDNI(String dni){ // Validar Dni
        if (dni.length() != 9) return false;
        return dni.matches("\\d{8}[A-Z]"); // TODO: Calcular si letra pertenece a número
    }

    /**
        Le pide al usuario un {@link String} y se salta el problema de tener que
        limpiar el {@code buffer} cada vez que se pide un dato nuevo.
    */
    public static String getString(String txt){
        System.out.println(txt);
        String str = sc.nextLine();
        if (str.isBlank() || str.equals("\n")) return sc.nextLine();
        return str;
    }

    /** Le pide al usuario un {@code int} */
    public static int getInt(String txt){
        System.out.println(txt);
        return sc.nextInt();
    }
    public static int getInt(){
        return sc.nextInt();
    }

    /**
        Convierte un {@link java.lang.String} a un {@link java.lang.Enum}
        @return {@code null} si el valor es inválido y Enum<T> si es vlaído.
    */
    public static <T extends Enum<T>> T toEnum(Class<T> enumClass, String str) { // Funcion para pasar string a Enum
        T tipo;
        try {
            tipo = Enum.valueOf(enumClass, str.toUpperCase());
        } catch (Exception e) {
            return null;
        }
        return tipo;
    }
    /**
         Convierte un {@link java.lang.String} a un {@link java.lang.Enum}
         @return Enum<T></T>
         @param <T> valor por defecto si no es posible convertir.
     */
    public static <T extends Enum<T>> T toEnum(Class<T> enumClass, String str, T def) { // Funcion para pasar string a Enum
        T en = toEnum(enumClass, str);
        if (en == null) return def;
        return en;
    }

    /** Le pide al usuario un {@link String} y lo convierte a {@link Enum} automaticamente */
    public static <T extends Enum<T>> T getEnum(Class<T> enumClass, String txt) {
        String str = getString(txt);
        T en = toEnum(enumClass, str);
        while (en == null){
            System.out.print("[Error] Valor inválido. ");
            en = toEnum(enumClass, str);
        }
        return en;
    }

    /** Le pide al usuario una contraseña y la convierte a {@link String} */
    public static String getPass(String txt){ //Recoger la contraseña del ususario
        Console console = System.console();
        char[] pass = console.readPassword(txt);
        String str = "";
        for (char c : pass) str += c;
        return str;
    }

    /**
        Genera una tabla en formato ascii y la printea en la consola.<br/>
        Recibe:<br/>
            - {@code String[]} titles: array de strings que se usarán para formar el encabezado de la tabla.<br/>
            - {@code ArrayList<String[]>}: arraylist de arrays de strings que usará para añadir los datos en la tabla.<br/>

            Por ejemplo:<br/>
                String[] titles = {"Nombre", "Apellido", "Edad"};<br/>
                ArrayList<String[]> values = {{"Ivan", "Martin", "18"}, {"Juan", "Perez", "34"}};<br/>

            Esto mostrará en pantalla lo siguiente:<br/>
            +---------------------------+<br/>
            | Nombre | Apellido  | Edad |<br/>
            +---------------------------+<br/>
            | Ivan   | Martin    | 18   |<br/>
            | Juan   | Perez     | 34   |<br/>
            +---------------------------+<br/>
     */

    public static void generateTable(String[] titles, ArrayList<String[]> vals){ // Generar una tabala
        int[] max = new int[titles.length];
        for (int i = 0; i < titles.length; i++) max[i] = titles[i].length();
        for (String[] row : vals){
            for (int i = 0; i < titles.length; i++) max[i] = Math.max(max[i], row[i].length());
        }
        String bar = "+%s+%n".formatted("-".repeat(Arrays.stream(max).sum() + 3*max.length -1));
        StringBuilder inner = new StringBuilder(bar);
        String header = "|";


        for(int i = 0; i < titles.length; i++) header += " %s%s |".formatted(titles[i], " ".repeat(max[i] - titles[i].length()));
        inner.append(header).append("\n").append(bar);
        for (String[] row : vals){
            String strRow = "|";
            for (int i = 0; i < titles.length; i++){
                strRow += " %s%s |".formatted(row[i], " ".repeat(max[i] - row[i].length()));
            }
            inner.append(strRow).append("\n");
        }
        inner.append(bar);

        System.out.println(inner);
    }
}
