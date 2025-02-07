package me.ivanmart.plaiaundi.Utils;

import org.mindrot.jbcrypt.BCrypt;

import java.io.BufferedReader;
import java.io.InputStreamReader;

public class Password {

    /**
     * Hashea la contraseña usando la dependencia Bcrypt.
     *
     * @param password Contraseña en texto plano.
     * @return {@link String} hash
     */
    public static String hash(String password) {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Verifica si un hash pertenece a una contraseña usando la dependencia Bcrypt.
     *
     * @param password Contraseña en texto plano
     * @param hash     Hash
     * @return {@code boolean}
     */
    public static boolean check(String password, String hash) {
        return BCrypt.checkpw(password, hash);
    }


    /**
     * Lee la contraseña insertada por el usuario y oculta su input.
     *
     * @param text Texto a mostrar al usuario.
     * @return {@link String} Contraseña insertada por el usuario.
     */
    public static String read(String text) {
        String password;
        System.out.println(isCMD());
        if (isCMD()){
            // Iniciar Ocultar texto en un hilo nuevo.
            Eraser et = new Eraser(text);
            Thread mask = new Thread(et);
            mask.start();
            // Recibir contraseña del usuario
            password = MenuUtil.getString(text);
            // Dejar de Ocultar.
            et.stopMasking();
        } else {
            password = MenuUtil.getString(text);
        }
        return password;
    }

    /**
     * Valida una contraseña. La contraseña debe tener: 8 carácteres, entre los cuales: al menos una mayúscula, una minúscula y un número.
     *
     * @param pass Contraseña del usuario.
     * @return {@code boolean} true si es válida, false si no lo es.
     **/
    public static boolean validate(String pass) {
        return pass.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$");
    }

    /**
     * Verifica si se está usando el CMD de windows. (El Eraser solo funciona en el cmd)
     *
     * @return {@code boolean}
     * */
    private static boolean isCMD(){
        try {
            // Get current process ID (PID)
            long pid = ProcessHandle.current().pid();

            // Get parent process ID (PPID)
            Process process = Runtime.getRuntime().exec("wmic process where ProcessId=" + pid + " get ParentProcessId");
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            reader.readLine(); // Skip header
            String parentPid = reader.readLine().trim(); // Read the parent PID

            // Get parent process name
            Process parentProcess = Runtime.getRuntime().exec("wmic process where ProcessId=" + parentPid + " get Name");
            BufferedReader parentReader = new BufferedReader(new InputStreamReader(parentProcess.getInputStream()));
            parentReader.readLine(); // Skip header
            String parentName = parentReader.readLine().trim(); // Read parent process name
            System.out.println(parentName);
            boolean is = parentName.equalsIgnoreCase("cmd.exe");
        } catch (Exception e) {
            return false;
        }
        return true;
    }

}

class Eraser implements Runnable {
    private boolean stop;

    /**
     * @param prompt mensaje mostrado al usuario.
     */
    public Eraser(String prompt) {
        System.out.print(prompt + "  ");
    }

    /**
     * Empezar a ocultar...
     */
    public void run() {
        stop = true;
        while (stop) {
            System.out.print("\010*"); // Mostrar asterisco reemplazando el anterior caracter por este.
            try {
                Thread.sleep(1);
            } catch (InterruptedException _) {
            }
        }
    }

    /**
     * Dejar de ocultar el texto.
     */
    public void stopMasking() {
        this.stop = false;
    }
}
