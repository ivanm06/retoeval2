package me.ivanmart.plaiaundi.Menus;

import me.ivanmart.plaiaundi.Enums.TipoCuenta;

public class AuthMenu {
    private TipoCuenta tipo;

    public void start(){
        System.out.println("""
                    +-------------------+
                    |  Accede a la APP  |
                    | 1. Iniciar Sesión |
                    | 2. Crear cuenta   |
                    +-------------------+
                    """);

        int c = Util.sc.nextInt();

        while (c != 1 && c != 2){
            System.out.println("Inserta una opción válida");
            c = Util.sc.nextInt();
        }

        if (c == 1) login();
        else register();
    }

    public void login(){
        System.out.println("Inserta tu DNI");
        String dni = Util.sc.nextLine();
        while (!Util.checkDNI(dni)){
            System.out.println("Inserta un DNI valido");
            dni = Util.sc.nextLine();
        }

        String pass = Util.sc.nextLine();
        boolean correcto = false;
        while (!correcto){

        }

    }

    public void register() {
    }


        public TipoCuenta getTipo(){
        return this.tipo;
    }
}
