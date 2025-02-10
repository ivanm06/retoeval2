package me.ivanmart.plaiaundi.Menus;

import me.ivanmart.plaiaundi.Database.AuthRepo;
import me.ivanmart.plaiaundi.Enums.Privilegio;
import me.ivanmart.plaiaundi.Enums.Sexo;
import me.ivanmart.plaiaundi.Model.Usuario;
import me.ivanmart.plaiaundi.Utils.MenuUtil;
import me.ivanmart.plaiaundi.Utils.Password;


public class AuthMenu {
    private static Usuario usuario = new Usuario();

    public void start(){
        System.out.println("""
                    +-------------------+
                    |  Accede a la APP  |
                    | 1. Iniciar Sesión |
                    | 2. Crear cuenta   |
                    +-------------------+
                    """);

        int c = MenuUtil.getInt();
        while (c != 1 && c != 2) c = MenuUtil.getInt("Inserta una opción válida.");

        if (c == 1) login();
        else register();
    }

    public void login(){

        System.out.println("""
                    +----------------------------+
                    |  Quieres seguir adelante?  |
                    | 1. Seguir                  |
                    | 2. Vorlver atras           |
                    +----------------------------+
                    """);
        int c = MenuUtil.getInt();
        while (c != 1 && c != 2) c = MenuUtil.getInt("Inserta una opción válida.");
        if (c != 1) start();

        //Insertar DNI
        String dni = MenuUtil.getString("Inserta tu DNI.");
        while (!MenuUtil.checkDNI(dni)) dni = MenuUtil.getString("[Info] Inserta un DNI valido.");

        String pass = Password.read("Inserta tu contraseña:");
        if (!AuthRepo.checkPassword(dni, pass)){ // Verificar contraseña.
            System.out.println("[Error] Credenciales inválidas.");
            start();
        }else{
            System.out.println("[Info] Sesión iniciada.");
            usuario = AuthRepo.getUsuario(dni);
            System.out.printf("[Info] Bienvenido %s %s %s.%n", usuario.getNombre(), usuario.getApellido1(), usuario.getApellido2());
        }
    }

    public void register() {

        System.out.println("""
                    +----------------------------+
                    |  Quieres seguir adelante?  |
                    | 1. Seguir                  |
                    | 2. Vorlver atras           |
                    +----------------------------+
                    """);
        int c = MenuUtil.getInt();
        while (c != 1 && c != 2) c = MenuUtil.getInt("Inserta una opción válida.");
        if (c != 1) start();

        //Insertar DNI
        String dni = MenuUtil.getString("Inserta tu DNI.");
        while (!MenuUtil.checkDNI(dni)) dni = MenuUtil.getString("Inserta un DNI valido.");

        // Pedir informacion sobre el usuario
        String nombre = MenuUtil.getString("Inserta tu nombre.");
        String apellido1 = MenuUtil.getString("Inserta tu primer apellido.");
        String apellido2 = MenuUtil.getString("Inserta tu segundo apellido.");
        Sexo sexo = MenuUtil.getEnum(Sexo.class, "Inserta tu sexo. (H/M)");
        String pass = Password.read("Inserta tu contraseña:");
        while (!Password.validate(pass)) pass = Password.read("[Error] La contraseña debe de tener al menos 8 caracteres, entre los cuales: una mayúscula, una minúscula y un número.");

        // Registrar usuario
        boolean registro = AuthRepo.registrar(new Usuario(dni, nombre, apellido1, apellido2, sexo, Privilegio.CLIENTE), pass); // Agregar el usuario
        if (registro) System.out.println("[Info] Registrado correctamente.");
        else System.out.println("[Error] No se ha podido registrar la cuenta.");
        start();
    }

    public static Usuario getUsuario(){
        return usuario;
    }
}
