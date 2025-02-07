package me.ivanmart.plaiaundi.Menus;

import me.ivanmart.plaiaundi.Database.AuthRepo;
import me.ivanmart.plaiaundi.Database.EstadisticasRepo;
import me.ivanmart.plaiaundi.Database.ReservaRepo;
import me.ivanmart.plaiaundi.Model.ArticuloReserva;
import me.ivanmart.plaiaundi.Model.Usuario;
import me.ivanmart.plaiaundi.Utils.MenuUtil;

import java.util.ArrayList;

import static me.ivanmart.plaiaundi.Database.AuthRepo.eliminarUsuario;
import static me.ivanmart.plaiaundi.Database.ReservaRepo.anularReserva;

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
        while (c != 1 && c != 2) c = MenuUtil.getInt("[Error] Selecciona un valor válido.");

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
        while (c < 0 || c > 8) c = MenuUtil.getInt("[Error] Valor inválido.");

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
            case 6:
                valores2 = new ArrayList<>();
                ArrayList<Usuario> usuarios = AuthRepo.getUsuarios();
                for(Usuario u : usuarios) valores2.add(u.getDataArray());
                titulos2 = new String[]{"DNI", "Nombre", "Primer Apellido", "Segundo Apellido", "Sexo", "Privilegio"};
                break;
            case 7:
                // Generar tabala
                valores2 = new ArrayList<>();
                ArrayList<Usuario> usuariosElim = AuthRepo.getUsuarios();
                for (Usuario u : usuariosElim) {
                    valores2.add(u.getDataArray());
                }
                titulos2 = new String[]{"DNI", "Nombre", "Primer Apellido", "Segundo Apellido", "Sexo", "Privilegio"};

                // Mostrar la tabla solo cuando se entra en esta sección
                MenuUtil.generateTable(titulos2, valores2);

                // Solicitar el DNI del usuario a eliminar
                String dni = obtenerDNIParaEliminar();

                // Eliminar usuario si el DNI es válido y no es "0"
                if (!dni.equals("0")) {
                    eliminarUsuario(dni);  // Realiza la eliminación y no vuelve a mostrar la tabla
                }
                break;
            case 8:
                // Mostrar tabla de reservas
                valores2 = EstadisticasRepo.getReservas();
                titulos2 = new String[]{"ID", "Fecha Inicio", "Fecha Fin"};
                MenuUtil.generateTable(titulos2, valores2);

                // Solicitar el ID de la reserva a anular
                int reservaId = MenuUtil.getInt("Introduce el ID de la reserva a anular. (0 para volver atrás)");
                while (reservaId < 0) {
                    reservaId = MenuUtil.getInt("[Error] Introduce un ID válido.");
                }

                // Si el usuario elige 0 volver atrás
                if (reservaId == 0) {
                   showMenu();
                } else {
                    anularReserva(reservaId);  // Realiza la anulación de la reserva
                }
                break;
            case 0:
                start();
                return;
        }

        MenuUtil.generateTable(titulos2, valores2);
        if (c==5) mostrarIngresosTotales();
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
            | 6. Mostrar todos los clientes  |
            | 7. Eliminar usuario            |
            | 8. Anular Reserva              |
            | 0. Volver                      |
            +--------------------------------+
            """);
    }
    private void menuReservas(){
        int r = MenuUtil.getInt("Inserta el id de la reserva a ver. (0 para volver atrás)");
        while (r < 0) r = MenuUtil.getInt("[Error] inserta un id válido.");
        if (r == 0) return;

        ArrayList<ArticuloReserva> articulos = ReservaRepo.getArticulosFromReserva(r);

        String[] titulosArticulos = new String[]{"ID", "Nombre", "Descripcion", "Talla", "Precio", "Cantidad"};
        ArrayList<String[]> valoresArticulo = new ArrayList<>();

        for(ArticuloReserva a : articulos) valoresArticulo.add(a.getDataArray(0));
        MenuUtil.generateTable(titulosArticulos, valoresArticulo);
    }

    private void mostrarIngresosTotales(){
        int total = EstadisticasRepo.getIngresosTotales();
        System.out.println("\033[F\r" + "| Ingresos totales: " + total + "€€" + " |");
        System.out.println("+" + "-".repeat(21 + (total+"").length()) + "+");
    }

    //Obtener DNI
    private String obtenerDNIParaEliminar() {
        String dni;
        do {
            dni = MenuUtil.getString("Introduce el DNI del Usuario a eliminar. (0 para volver atrás)");
            if (dni.equals("0")) {
                showMenu();  // Si el usuario quiere volver atrás, se regresa al menú principal
                return dni;
            }
            if (dni.isEmpty() /* || !MenuUtil.checkDNI(dni) ---> si se quiere verificar que sea real*/ ) {
                System.out.println("[Info] Inserta un DNI válido.");
            }
        } while (dni.isEmpty() /* || !MenuUtil.checkDNI(dni) ---> si se quiere verificar que sea real*/ );
        return dni;
    }


}
