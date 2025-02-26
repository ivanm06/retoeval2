package me.ivanmart.plaiaundi.Utils;

import me.ivanmart.plaiaundi.Model.Fecha;

import java.io.Console;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class MenuUtil {
    public static final Scanner sc = new Scanner(System.in);

    /**
     * Calcula la letra de un número de DNI.
     *
     * @param dni int de 0 a 99999999
     * @return {@code char}
     */
    private static char calcularLetraDNI(int dni) {
        String letras = "TRWAGMYFPDXBNJZSQVHLCKE";
        int resto = dni % 23;
        return letras.charAt(resto);
    }

    /**
     * Comprueba si un DNI es válido o no usando regex y calcula si la letra insertada
     * es realmente la que debería.
     *
     * @param dni String de longitud 9 (8 números y 1 caracter)
     * @return {@code boolean} - true si es válido.
     */
    private static boolean checkDNI(String dni) {
        if (!dni.matches("\\d{8}[A-Z]")) return false;
        return dni.charAt(8) == calcularLetraDNI(Integer.parseInt(dni.substring(0, 8))); // Validar que se esté insertando la letra correcta
    }

    public static String getDNI(String txt) {
        String dni = getString(txt);
        while (!checkDNI(dni)) dni = getDNI("[Error] inserta un dni válido.");
        return dni;
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
        return getString();
    }

    /**
     * Le pide al usuario un {@link String} y se salta el problema de tener que
     * limpiar el {@code buffer} cada vez que se pide un dato nuevo.
     *
     * @return {@link String}
     */
    public static String getString() {
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
        return getInt();
    }

    /**
     * Le pide al usuario un {@code int}
     *
     * @return {@code int}
     */
    public static int getInt() {
        int val;
        try {
            val = sc.nextInt();
        } catch (Exception _) {
            // Vaciar el buffer.
            sc.nextLine();
            return getInt("[Error] Inserta número positivo.");
        }
        return val;
    }

    /**
     * Le pide al usuario un condicional (true/false)
     *
     * @param cmdTrue Texto identificado como {@code true}
     * @return {@code boolean}- true si el texto coincide, false si no coincide.
     */
    public static boolean getBoolean(String cmdTrue, String txt) {
        String cmd = getString(txt).toLowerCase();
        return cmd.equals(cmdTrue);
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
        if (en == null) return getEnum(enumClass, "[Error] Valor inválido. ");
        return en;
    }

    /**
     * Le pide al usuario un timestamp con el siguiente formato: (YYYY-MM-DD) (año-mes-dia).
     * Si el timestamp no es válida (ya tenga un formato incorrecto o la fecha no exista) se le
     * pedirá otro timestamp al usuario.
     *
     * @return {@link Timestamp}
     */
    public static Timestamp getTimestamp(String txt) {
        String date = getString(txt);
        try {
            // Parseamos la fecha.
            DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate ld = LocalDate.parse(date, dateFormatter);
            // Si la fecha es anterior a la fecha de hoy, preguntar otra.
            if (ld.isBefore(LocalDate.now())) return getTimestamp("[Error] La fecha tiene que ser posterior a hoy.");
            // Comparamos los dias de la fecha para confirmar que la fecha existe y el parser no la ha modificado.
            int day = Integer.parseInt(date.split("-")[2]);
            if (ld.getDayOfMonth() == day) return Timestamp.valueOf(ld + " 00:00:00");
            // Preguntar por otra fecha si la introducida no es válida.
            return getTimestamp("[Error] Introduce una fecha válida.");
        } catch (Exception e) {
            return getTimestamp("[Error] La fecha tiene que tener el siguiente formato: (YYYY-MM-DD)");
        }
    }

    /**
     * Le pide al usuario dos fechas "{@link Timestamp}" y devuelve un objeto {@link Fecha}.
     *
     * @return {@link Fecha} - Rango de Fecha (fechaInicio - fechaFin)
     */
    public static Fecha getFecha(String txt1, String txt2) {
        Timestamp t1 = getTimestamp(txt1);
        Timestamp t2 = getTimestamp(txt2);
        while (t1.after(t2)) t2 = getTimestamp("[Error] La segunda fecha debe ser mayor a la primera.");
        return new Fecha(t1, t2);
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
        for (int i = 0; i < titles.length; i++)
            header += " %s%s |".formatted(titles[i], " ".repeat(max[i] - titles[i].length()));
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
        System.out.printf("%n%s", inner);
    }
}
