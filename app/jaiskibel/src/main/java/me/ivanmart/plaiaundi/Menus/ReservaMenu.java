package me.ivanmart.plaiaundi.Menus;

import me.ivanmart.plaiaundi.Database.ReservaRepo;
import me.ivanmart.plaiaundi.Interfaces.Menu;
import me.ivanmart.plaiaundi.Model.ArticuloReserva;
import me.ivanmart.plaiaundi.Model.Cesta;
import me.ivanmart.plaiaundi.Model.Fecha;
import me.ivanmart.plaiaundi.Utils.MenuUtil;

import java.util.ArrayList;

public class ReservaMenu implements Menu {

    public void start(){
        if (Cesta.getCesta().isEmpty()){
            System.out.println("[Info] Gracias por interesarte por nosotros, hasta la proxima!");
            return;
        }

        // Pedirle al usuario los dias de la reserva.
        Fecha fecha = Cesta.getFecha();

        // Mostrar tabla de reserva.
        mostrarArticulos(fecha);

        boolean reservar = MenuUtil.getBoolean("si", "Quieres Reservar los articulos seleccionados? (si/no)");
        // Reservar artículos
        if (reservar) reservar(fecha);
        else Cesta.vaciar();
    }

    private void mostrarArticulos(Fecha fecha){
        String[] titulos = new String[]{"ID", "Nombre", "Descripcion", "Talla", "Precio/Dia", "Tipo", "Cantidad"};
        ArrayList<String[]> valores = new ArrayList<>();

        int precioTotal = 0;
        for (ArticuloReserva articulo : Cesta.getCesta()){
            String[] valor = articulo.getDataArray(1);
            valor[valor.length-1] = String.valueOf(articulo.getCantidad());
            valor[valor.length-2] = String.valueOf(articulo.getTipo());
            valores.add(valor);
            precioTotal += articulo.getCantidad() * articulo.getPrecio();
        }

        // Generar tabla resumen.
        MenuUtil.generateTable(titulos, valores);

        int dias = fecha.getDias()+1;

        // Calcular Precio Total.
        int total = precioTotal * dias;
        System.out.printf("| Dias: %s%s |%n", dias, " ".repeat(8 + String.valueOf(total).length() - String.valueOf(dias).length()));
        System.out.printf("| Precio Total: %s |%n", total);
        System.out.printf("+---------------%s-+%n", "-".repeat(String.valueOf(total).length()));
    }

    private void reservar(Fecha fecha){
        boolean res = ReservaRepo.reservar(Cesta.getCesta(), fecha);

        Cesta.vaciar();
        if (res) System.out.println("[Info] Se han reservado los artículos con éxito.Gracias por contar con nosotros!");
        else System.out.println("[Error] Error al reservar los artículos.");
    }
}
