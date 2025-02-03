package me.ivanmart.plaiaundi.Menus;

import me.ivanmart.plaiaundi.Database.ReservaRepo;
import me.ivanmart.plaiaundi.Model.ArticuloReserva;
import me.ivanmart.plaiaundi.Model.Cesta;
import me.ivanmart.plaiaundi.Utils.MenuUtil;

import java.util.ArrayList;

public class ReservaMenu {
    public void start(){
        if (Cesta.getCesta().isEmpty()){
            System.out.println("[Info] Gracias por interesarte por nosotros, hasta la proxima!");
            return;
        }

        // Pedirle al usuario los dias de la reserva.
        int dias = MenuUtil.getInt("Por cuantos dias quieres reservar? [1-30]");
        while (dias < 0 || dias > 30) dias = MenuUtil.getInt("[Error] Selecciona un valor dentro del rango.");

        // Mostrar tabla de reserva.
        mostrarArticulos(dias);

        boolean reservar = MenuUtil.getBoolean("si", "Quieres Reservar los articulos seleccionados? (si/no)");
        // Reservar artículos
        if (reservar) reservar(dias);
    }

    private void mostrarArticulos(int dias){
        String[] titulos = new String[]{"ID", "Nombre", "Descripcion", "Talla", "Precio/Dia", "Tipo", "Cantidad"};
        ArrayList<String[]> valores = new ArrayList<>();

        int precioTotal = 0;
        for (ArticuloReserva articulo : Cesta.getCesta()){
            String[] valor = articulo.getDataArray(1);
            valor[valor.length-1] = String.valueOf(articulo.getTipo());
            valor[valor.length-2] = String.valueOf(articulo.getCantidad());
            valores.add(valor);
            precioTotal += articulo.getCantidad() * articulo.getPrecio();
        }

        MenuUtil.generateTable(titulos, valores);
        System.out.printf("| Precio Total: %s |%n", precioTotal * dias);
        System.out.printf("+---------------%s-+%n", "-".repeat(String.valueOf(precioTotal*dias).length()));
    }

    private void reservar(int dias){
        boolean res = ReservaRepo.reservar(Cesta.getCesta(), dias);

        if (res) System.out.println("[Info] Se han reservado los artículos con éxito.");
        else System.out.println("[Error] Error al reservar los artículos.");
    }
}
