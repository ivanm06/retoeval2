package me.ivanmart.plaiaundi.Menus;

import me.ivanmart.plaiaundi.Database.AuthRepo;
import me.ivanmart.plaiaundi.Database.ReservaRepo;
import me.ivanmart.plaiaundi.Model.Articulo;
import me.ivanmart.plaiaundi.Model.ArticuloReserva;
import me.ivanmart.plaiaundi.Model.Reserva;
import me.ivanmart.plaiaundi.Model.Usuario;

import java.util.ArrayList;

public class AdminMenu {

    public void start(){
        System.out.println("""
                +------------------------------+
                | Selecciona como vas a seguir |
                | 1. Cliente                   |
                | 2. Administrador             |
                +------------------------------+
                """);
        int c;
        do {
            c = Util.getInt();
            switch (c){
                case 1:
                    ClientMenu menu = new ClientMenu();
                    menu.start();
                    break;
                case 2:
                    continuar();
                    break;
                default:
                    System.out.println("[Info] Selecciona un valor válido.");
                    break;
            }
        }while (c!=1 && c != 2);
    }

    private void continuar(){
        System.out.println("""
                +-------------------------------+
                |   Opciones de Administrador   |
                | 1. Añadir Establecimiento     |
                | 2. Añadir Articulo            |
                | 3. Añadir articulo en almacen |
                | 4. Mostrar Clientes           |
                | 5. Mostrar Reservas           |
                | 0. Volver                     |
                +-------------------------------+
                """);
        int c;
        do {
            c = Util.getInt();
            switch (c){
                case 1:
                case 2:
                case 3:
                case 4:
                    String[] titulos = new String[]{"DNI", "Nombre", "Apellido1", "Apellido2", "Sexo", "Privilegio"};
                    ArrayList<String[]> valores = new ArrayList<>();
                    ArrayList<Usuario> usuarios = AuthRepo.getUsuarios();
                    for (Usuario u : usuarios) valores.add(u.getDataArray());
                    System.out.println(Util.generateTable(titulos, valores));
                    break;
                case 5:
                    String[] titulos2 = new String[]{"ID", "fechaInicio", "fechaFin"};
                    ArrayList<String[]> valores2 = new ArrayList<>();
                    ArrayList<Reserva> reservas = ReservaRepo.getReservas();
                    for (Reserva r : reservas) valores2.add(r.getDataArray());
                    System.out.println(Util.generateTable(titulos2, valores2));

                    int r = Util.getInt("Inserta el id de la reserva a ver. (0 para volver atrás)");
                    while (r < 0) r = Util.getInt("Valor inválido.");
                    if (r == 0){
                        continuar();
                        break;
                    }
                    ArrayList<ArticuloReserva> articulos = ReservaRepo.getArticulosFromReserva(r);
                    String[] titulosArticulos = new String[]{"ID", "Nombre", "Descripcion", "Talla", "Precio", "Reservados", "Stock"};
                    ArrayList<String[]> valoresArticulo = new ArrayList<>();
                    for(ArticuloReserva a : articulos) valoresArticulo.add(a.getDataArray(0));
                    System.out.println(Util.generateTable(titulosArticulos, valoresArticulo));
                    break;
                case 0:
                    start();
                    break;
            }
        }while (c < 0 || c > 5);

        /*
        Añadir establecimientos
        Añadir Articulos
        Añadir articulos en establecimientos
        ver clientes
        ver reservas -> filtrar producto/establecimiento/cantidadreservas/cantidadclientes/cantidaddinero
        volver.
        */

        // TODO
    }
}
