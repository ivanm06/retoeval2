package me.ivanmart.plaiaundi;

import me.ivanmart.plaiaundi.Enums.TipoCuenta;
import me.ivanmart.plaiaundi.Menus.AdminMenu;
import me.ivanmart.plaiaundi.Menus.AuthMenu;
import me.ivanmart.plaiaundi.Menus.ClientMenu;

public class Main {
    public static AuthMenu auth = new AuthMenu();

    public static void main(String[] args) {
        auth.start();

        switch (auth.getTipo()){
            case TipoCuenta.ADMIN:
                AdminMenu amenu = new AdminMenu();
                amenu.start();
                break;
            case TipoCuenta.CLIENTE:
                ClientMenu cmenu = new ClientMenu();
                cmenu.start();
                break;
            default:
                System.out.println("Ha habido un problema a la hora de iniciar sesion.");
                break;
        }
    }
}