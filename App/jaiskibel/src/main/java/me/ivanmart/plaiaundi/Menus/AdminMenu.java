package me.ivanmart.plaiaundi.Menus;

import me.ivanmart.plaiaundi.Database.EstadisticasRepo;
import me.ivanmart.plaiaundi.Database.ReservaRepo;
import me.ivanmart.plaiaundi.Model.ArticuloReserva;

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
        int c;
        do {
            showMenu();
            c = Util.getInt();
            while (c < 0 || c > 5) c = Util.getInt("Valor inválido.");
            ArrayList<String[]> valores2;
            String[] titulos2;
            switch (c){
                case 2:
                    valores2 = EstadisticasRepo.getArticulosMasPedidos();
                    titulos2 = new String[]{"ID", "Nombre", "Cantidad"};
                    break;
                case 3:
                    valores2 = EstadisticasRepo.getClientesHabituales();
                    titulos2 = new String[]{"DNI", "Nombre", "Reservas"};
                    break;
                case 4:
                    valores2 = new ArrayList<>();
                    valores2.add(new String[]{String.valueOf(EstadisticasRepo.getNumeroClientes())});
                    titulos2 = new String[]{"Cantidad"};
                    break;
                case 5:
                    valores2 = EstadisticasRepo.getReservasPorDinero();
                    titulos2 = new String[]{"DNI Cliente", "ID Reserva", "Fecha Inicio", "Fecha Fin", "ID Establecimiento", "Articulos", "Total"};
                    break;
                case 0:
                    start();
                    return;
                default:
                    valores2 = EstadisticasRepo.getReservas();
                    titulos2 = new String[]{"ID", "Fecha Inicio", "Fecha Fin"};
                    break;
            }

            Util.generateTable(titulos2, valores2);

            if (c == 1){
                int r = Util.getInt("Inserta el id de la reserva a ver. (0 para volver atrás)");
                while (r < 0) r = Util.getInt("Valor inválido.");
                if (r == 0){
                    continuar();
                }
                ArrayList<ArticuloReserva> articulos = ReservaRepo.getArticulosFromReserva(r);
                String[] titulosArticulos = new String[]{"ID", "Nombre", "Descripcion", "Talla", "Precio", "Cantidad"};
                ArrayList<String[]> valoresArticulo = new ArrayList<>();
                for(ArticuloReserva a : articulos) valoresArticulo.add(a.getDataArray(0));
                Util.generateTable(titulosArticulos, valoresArticulo);
            }
        }while (true);
    }

    private void showMenu(){
        System.out.println("""
            +--------------------------------+
            |           Estadisticas         |
            | 1. Mostrar todas las reservas  |
            | 2. Artículos más pedidos       |
            | 3. Clientes habituales         |
            | 4. Número de clientes          |
            | 5. Dinero ingresado            |
            | 0. Volver                      |
            +--------------------------------+
            """);
    }
}
