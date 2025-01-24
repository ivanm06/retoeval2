package me.ivanmart.plaiaundi.Database;

import org.mindrot.jbcrypt.BCrypt;

public class Password { // Validar la contraseña y pasarla a hash

    public static String hash(String password){
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }

    public static boolean check(String password, String hash){
        return BCrypt.checkpw(password, hash);
    }
}
