package me.ivanmart.plaiaundi.Menus;

import java.io.Console;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Util {
    public static final Scanner sc = new Scanner(System.in);

    /**
     * Calcula la letra de un número de DNI.
     *
     * @param dni int de 0 a 99999999
     * @return {@code char}
     */
    public static char calcularLetraDNI(int dni) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int resto = dni % 23;
        return letras.charAt(resto);
    }

    /**
     * Comprueba si un DNI es válido o no usando regex y calcula si la letra insertada
     * es realmente la que debería.
     *
     * @param dni String de longitud 9 (8 números y 1 caracter)
     * @return {@code boolean}
     */
    public static boolean checkDNI(String dni) {
        if (!dni.matches("\\d{8}[A-Z]")) return false;
        return dni.charAt(8) == calcularLetraDNI(Integer.parseInt(dni.substring(0, 8))); // Validar que se esté insertando la letra correcta
    }

    /**
     * Le pide al usuario un {@link String} y se salta el problema de tener que
     * limpiar el {@code buffer} cada vez que se pide un dato nuevo.
     *
     * @param txt texto que se mostrará a la hora de pedirle el {@link String} al usuario
     * @return {@link String}
     */
    public static String getString(String txt) {
        System.out.println(txt);
        String str = sc.nextLine();
        if (str.isBlank() || str.equals("\n")) return sc.nextLine();
        return str;
    }

    /**
     * Le pide al usuario un {@code int}
     *
     * @param txt texto que se mostrará a la hora de pedirle el {@code int} al usuario
     * @return {@code int}
     */
    public static int getInt(String txt) {
        System.out.println(txt);
        return sc.nextInt();
    }

    /**
     * Le pide al usuario un {@code int}
     *
     * @return {@code int}
     */
    public static int getInt() {
        return sc.nextInt();
    }

    /**
     * Convierte un {@link java.lang.String} a un {@link java.lang.Enum}
     *
     * @return {@code null} si el valor es inválido y Enum<T> si es vlaído.
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
     * Convierte un {@link java.lang.String} a un {@link java.lang.Enum}
     *
     * @param <T> valor por defecto si no es posible convertir.
     * @return Enum<T></T>
     */
    public static <T extends Enum<T>> T toEnum(Class<T> enumClass, String str, T def) { // Funcion para pasar string a Enum
        T en = toEnum(enumClass, str);
        if (en == null) return def;
        return en;
    }

    /**
     * Le pide al usuario un {@link String} y lo convierte a {@link Enum} automaticamente
     *
     * @return {@link Enum}
     */
    public static <T extends Enum<T>> T getEnum(Class<T> enumClass, String txt) {
        String str = getString(txt);
        T en = toEnum(enumClass, str);
        while (en == null) {
            System.out.print("[Error] Valor inválido. ");
            en = toEnum(enumClass, str);
        }
        return en;
    }

    /**
     * Le pide al usuario una contraseña y la convierte a {@link String}
     *
     * @return {@link String}
     */
    public static String getPass(String txt) { // Recoger la contraseña del ususario.
        Console console = System.console();
        char[] pass = console.readPassword(txt);
        String str = "";
        for (char c : pass) str += c;
        return str;
    }

    /**
     * Genera una tabla en formato ascii y la printea en la consola.<br/>
     * Recibe:<br/>
     *
     * @param titles {@code String[]} titles: array de strings que se usarán para formar el encabezado de la tabla.<br/>
     * @param vals   {@code ArrayList<String[]>}: arraylist de arrays de strings que usará para añadir los datos en la tabla.<br/>
     * @implNote Por ejemplo:<br/>
     * String[] titles = {"Nombre", "Apellido", "Edad"};<br/>
     * ArrayList<String[]> values = {{"Ivan", "Martin", "18"}, {"Juan", "Perez", "34"}};<br/>
     * <br/>
     * Esto mostrará en pantalla lo siguiente:<br/>
     * +--------------------------+<br/>
     * | Nombre | Apellido | Edad |<br/>
     * +--------------------------+<br/>
     * | Ivan ㅤㅤ| Martin ㅤ| 18ㅤ|<br/>
     * | Juanㅤㅤ| Perez ㅤ | 34ㅤ|<br/>
     * +--------------------------+<br/>
     */

    public static void generateTable(String[] titles, ArrayList<String[]> vals) {
        // Calcula el espacio máximo que ocupará cada celda.
        int[] max = new int[titles.length];
        for (int i = 0; i < titles.length; i++) max[i] = titles[i].length();
        for (String[] row : vals) for (int i = 0; i < titles.length; i++) max[i] = Math.max(max[i], row[i].length());

        // Genera las barras horizontales de la tabla según los máximos.
        String bar = "+%s+%n".formatted("-".repeat(Arrays.stream(max).sum() + 3 * max.length - 1));
        String inner = bar;
        String header = "|";

        // Genera los títulos.
        for (int i = 0; i < titles.length; i++) header += " %s%s |".formatted(titles[i], " ".repeat(max[i] - titles[i].length()));
        inner += header + "\n" + bar;

        // Genera los datos.
        for (String[] row : vals) {
            String strRow = "|";
            for (int i = 0; i < titles.length; i++) {
                strRow += " %s%s |".formatted(row[i], " ".repeat(max[i] - row[i].length()));
            }
            inner += strRow + "\n";
        }
        inner += bar;

        // Printea la tabla en la terminal.
        System.out.println(inner);
    }
}
