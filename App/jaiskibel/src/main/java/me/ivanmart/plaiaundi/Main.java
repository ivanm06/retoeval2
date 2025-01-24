package me.ivanmart.plaiaundi;

import me.ivanmart.plaiaundi.Database.DBConnector;
import me.ivanmart.plaiaundi.Database.ReservaRepo;
import me.ivanmart.plaiaundi.Enums.Privilegio;
import me.ivanmart.plaiaundi.Menus.AdminMenu;
import me.ivanmart.plaiaundi.Menus.AuthMenu;
import me.ivanmart.plaiaundi.Menus.ClientMenu;
import me.ivanmart.plaiaundi.Model.Cesta;

import java.sql.SQLException;

public class Main {
    public static AuthMenu auth = new AuthMenu();

    public static void main(String[] args) {
        try{
            DBConnector.start(); // Iniciar conexión con la base de datos
        }catch (SQLException e){
            System.out.println("[Error] error al conectar con la BD. " + e.getMessage());
        }
        auth.start();

        switch (AuthMenu.getUsuario().getPrivilegio()){ // seleccionar privilegio
            case Privilegio.ADMIN: // En caso de que el privilegio del ususario sea Administrador
                AdminMenu amenu = new AdminMenu();
                amenu.start();
                break;
            case Privilegio.CLIENTE:// En caso de que el privilegio del ususario sea Cliente
                ClientMenu cmenu = new ClientMenu();
                cmenu.start();
                break;
            default:
                System.out.println("Ha habido un problema a la hora de iniciar sesion.");
                break;
        }

        if (!Cesta.getCesta().isEmpty()){ // Verificar que hayan articulos en el array "Cesta"
            boolean res = ReservaRepo.reservar(Cesta.getCesta());
            if (res){
                System.out.println("Se han reservado los artículos con éxito.");
            }else{
                System.out.println("Error");
            }
        }

        try{
            DBConnector.close(); // Cerar conexión con la base de datos
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}