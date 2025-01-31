package me.ivanmart.plaiaundi;

import me.ivanmart.plaiaundi.Database.DBConnector;
import me.ivanmart.plaiaundi.Database.ReservaRepo;
import me.ivanmart.plaiaundi.Enums.Privilegio;
import me.ivanmart.plaiaundi.Menus.AdminMenu;
import me.ivanmart.plaiaundi.Menus.AuthMenu;
import me.ivanmart.plaiaundi.Menus.ClientMenu;
import me.ivanmart.plaiaundi.Utils.MenuUtil;
import me.ivanmart.plaiaundi.Model.Cesta;
import java.sql.SQLException;

public class Main {
    private static final AuthMenu auth = new AuthMenu();


    public static void main(String[] args) {
        // Conectarse a la Base de Datos.
        boolean conectado = conectarDB(0);
        if (!conectado) return;

        // Mostrar Menú Auth.
        auth.start();

        switch (AuthMenu.getUsuario().getPrivilegio()){
            case Privilegio.ADMIN: // En caso de que el usuario sea Administrador.
                AdminMenu amenu = new AdminMenu();
                amenu.start();
                break;
            case Privilegio.CLIENTE:// En caso de que el ususario sea Cliente.
                ClientMenu cmenu = new ClientMenu();
                cmenu.start();
                break;
            default:
                System.out.println("[Error] Ha habido un problema a la hora de iniciar sesion.");
                break;
        }

        // Una vez finalizado el programa.
        if (!Cesta.getCesta().isEmpty()){
            int dias = MenuUtil.getInt("Por cuantos dias quieres reservar? [1-30]");
            while (dias < 0 || dias > 30) dias = MenuUtil.getInt("[Error] Selecciona un valor dentro del rango.");

            boolean res = ReservaRepo.reservar(Cesta.getCesta(), dias);
            if (res) System.out.println("[Info] Se han reservado los artículos con éxito.");
            else System.out.println("[Error] Error al reservar los artículos.");
        }

        // Cerrar conexión a la Base de Datos.
        try{
            DBConnector.close();
        } catch (SQLException e) {
            System.out.printf("[Error] Error al cerrar la conexión con la base de datos. %s%n", e.getMessage());
        }
    }

    private static boolean conectarDB(int intento){
        if (intento >= 3){
            System.out.println("[Error] Se ha llegado al número máximo de intentos, cerrando programa.");
            return false;
        }
        try{
            DBConnector.start();
        }catch (SQLException e){
            System.out.printf("[Error] error al conectar con la BD, Intentando de nuevo. %s%n", e.getMessage());
            return conectarDB(intento + 1);
        }
        return true;
    }
}