package me.ivanmart.plaiaundi.Utils;

import org.mindrot.jbcrypt.BCrypt;

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
        // Iniciar Ocultar texto en un hilo nuevo.
        Eraser et = new Eraser(text);
        Thread mask = new Thread(et);
        mask.start();
        // Recibir contraseña del usuario
        String password = MenuUtil.getString();
        // Dejar de Ocultar.
        et.stopMasking();

        // validar contraseña
        if (!validate(password)) {
            return read("[Info] La contraseña debe de tener al menos 8 carácteres, entre los cuales una mayuscula, una minuscula y un número.");
        }

        return password;
    }

    /**
     * Valida una contraseña. La contraseña debe tener: 8 carácteres, entre los cuales: al menos una mayúscula, una minúscula y un número.
     *
     * @param pass Contraseña del usuario.
     * @return {@code boolean} true si es válida, false si no lo es.
     **/
    private static boolean validate(String pass) {
        return pass.matches("^(?=.*[a-z])(?=.*[A-Z])(?=.*\\d)[A-Za-z\\d]{8,}$");
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
