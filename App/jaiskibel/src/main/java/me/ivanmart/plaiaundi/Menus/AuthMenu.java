package me.ivanmart.plaiaundi.Menus;

import me.ivanmart.plaiaundi.Database.AuthRepo;
import me.ivanmart.plaiaundi.Enums.Privilegio;
import me.ivanmart.plaiaundi.Enums.Sexo;
import me.ivanmart.plaiaundi.Model.Usuario;


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

        int c = Util.getInt();

        while (c != 1 && c != 2){ // validar opción
            System.out.println("Inserta una opción válida.");
            c = Util.getInt();
        }

        if (c == 1) login();
        else register();
    }

    public void login(){
        String dni = Util.getString("Inserta tu DNI.");
        while (!Util.checkDNI(dni)){ // verificar DNI
            dni = Util.getString("Inserta un DNI valido.");
        }
        String pass = Util.getString("Inserta tu contraseña.");
        if (!AuthRepo.checkPassword(dni, pass)){ // verificar contraseña
            System.out.println("Credenciales inválidas.");
            start();
        }else{
            System.out.println("[Info] Sesión iniciada.");
            usuario = AuthRepo.getUsuario(dni);
        }
    }

    public void register() {
        String dni = Util.getString("Inserta tu DNI.");
        while (!Util.checkDNI(dni)){ // verificar DNI
            dni = Util.getString("Inserta un DNI valido.");
        }
        // Pedir informacion sobre el usuario
        String nombre = Util.getString("Inserta tu nombre.");
        String apellido1 = Util.getString("Inserta tu primer apellido.");
        String apellido2 = Util.getString("Inserta tu segundo apellido.");
        Sexo sexo = Util.getEnum(Sexo.class, "Inserta tu sexo.");
        String pass = Util.getPass("Inserta tu contraseña.");

        boolean registro = AuthRepo.registrar(new Usuario(dni, nombre, apellido1, apellido2, sexo, Privilegio.CLIENTE), pass); // Agregar el usuario
        if (registro) System.out.println("[Info] Registrado correctamente.");
        else System.out.println("[Error] No se ha podido registrar la cuenta.");
        start();
    }

    public static Usuario getUsuario(){
        return usuario;
    }
}
