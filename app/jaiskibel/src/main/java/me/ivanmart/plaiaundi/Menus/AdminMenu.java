package me.ivanmart.plaiaundi.Menus;

import me.ivanmart.plaiaundi.Database.EstadisticasRepo;
import me.ivanmart.plaiaundi.Database.ReservaRepo;
import me.ivanmart.plaiaundi.Model.ArticuloReserva;
import me.ivanmart.plaiaundi.Utils.MenuUtil;

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

        int c = MenuUtil.getInt();
        while (c != 1 && c != 2) c = MenuUtil.getInt("[Info] Selecciona un valor válido.");

        if (c == 1){
            ClientMenu menu = new ClientMenu();
            menu.start();
        }else{ // c == 2
            continuar();
        }
    }

    private void continuar() {
        showMenu();
        int c = MenuUtil.getInt();
        while (c < 0 || c > 5) c = MenuUtil.getInt("Valor inválido.");

        ArrayList<String[]> valores2 = new ArrayList<>();
        String[] titulos2 = new String[]{};

        switch (c) {
            case 1:
                valores2 = EstadisticasRepo.getReservas();
                titulos2 = new String[]{"ID", "Fecha Inicio", "Fecha Fin"};
                break;
            case 2:
                valores2 = EstadisticasRepo.getArticulosMasPedidos();
                titulos2 = new String[]{"ID", "Nombre", "Cantidad"};
                break;
            case 3:
                valores2 = EstadisticasRepo.getClientesHabituales();
                titulos2 = new String[]{"DNI", "Nombre", "Reservas"};
                break;
            case 4:
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
        }

        MenuUtil.generateTable(titulos2, valores2);
        if (c == 1) menuReservas();
        continuar();
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
    private void menuReservas(){
        int r = MenuUtil.getInt("Inserta el id de la reserva a ver. (0 para volver atrás)");
        while (r < 0) r = MenuUtil.getInt("Valor inválido.");
        if (r == 0) return;

        ArrayList<ArticuloReserva> articulos = ReservaRepo.getArticulosFromReserva(r);

        String[] titulosArticulos = new String[]{"ID", "Nombre", "Descripcion", "Talla", "Precio", "Cantidad"};
        ArrayList<String[]> valoresArticulo = new ArrayList<>();

        for(ArticuloReserva a : articulos) valoresArticulo.add(a.getDataArray(0));
        MenuUtil.generateTable(titulosArticulos, valoresArticulo);
    }
}
