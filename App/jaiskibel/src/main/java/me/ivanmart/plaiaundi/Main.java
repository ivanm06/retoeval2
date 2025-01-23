package me.ivanmart.plaiaundi;

import me.ivanmart.plaiaundi.Database.DBConnector;
import me.ivanmart.plaiaundi.Enums.Privilegio;
import me.ivanmart.plaiaundi.Menus.AdminMenu;
import me.ivanmart.plaiaundi.Menus.AuthMenu;
import me.ivanmart.plaiaundi.Menus.ClientMenu;

import java.sql.SQLException;

public class Main {
    public static AuthMenu auth = new AuthMenu();

    public static void main(String[] args) {
        try{
            DBConnector.start();
        }catch (SQLException e){
            System.out.println("[Error] error al conectar con la BD. " + e.getMessage());
        }
        auth.start();

        switch (AuthMenu.getUsuario().getPrivilegio()){
            case Privilegio.ADMIN:
                AdminMenu amenu = new AdminMenu();
                amenu.start();
                break;
            case Privilegio.CLIENTE:
                ClientMenu cmenu = new ClientMenu();
                cmenu.start();
                break;
            default:
                System.out.println("Ha habido un problema a la hora de iniciar sesion.");
                break;
        }

        try{
            DBConnector.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}