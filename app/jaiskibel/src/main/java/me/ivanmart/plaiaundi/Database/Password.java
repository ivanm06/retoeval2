package me.ivanmart.plaiaundi.Database;

import org.mindrot.jbcrypt.BCrypt;

public class Password {

    /**
     * Hashea la contraseña usando la dependencia Bcrypt.
     * @return {@link String} hash
     * @param password Contraseña en texto plano.
     * */
    public static String hash(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    /**
     * Verifica si un hash pertenece a una contraseña usando la dependencia Bcrypt.
     * @return {@code boolean}
     * @param password Contraseña en texto plano
     * @param hash Hash
     * */
    public static boolean check(String password, String hash){
        return BCrypt.checkpw(password, hash);
    }
}
