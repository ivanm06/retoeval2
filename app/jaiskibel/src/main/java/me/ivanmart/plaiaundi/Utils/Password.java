package me.ivanmart.plaiaundi.Utils;

import org.mindrot.jbcrypt.BCrypt;
import java.io.BufferedReader;
import java.io.IOException;
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
        boolean prod = true;
        if (prod) return MenuUtil.getString(text); // En intellij, no funciona el Eraser.

        String password = "";
        Eraser td = new Eraser(text + " ");
        Thread t = new Thread(td);
        t.start();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            password = br.readLine();
            td.maskEnd();
        } catch (IOException ioe) {}
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

}

class Eraser implements Runnable {
    private boolean end;
    public Eraser(String prompt) {
        System.out.print(prompt);
    }
    public void run() {
        end = true;
        while (end) {
            System.out.print("\010*");
            try {
                Thread.currentThread().sleep(1);
            } catch (InterruptedException ie) {
                ie.printStackTrace();
            }
        }
    }

    public void maskEnd() {
        this.end = false;

    }
}