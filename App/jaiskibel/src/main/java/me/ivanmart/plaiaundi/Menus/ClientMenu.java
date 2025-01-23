package me.ivanmart.plaiaundi.Menus;

import me.ivanmart.plaiaundi.Database.ArticuloRepo;
import me.ivanmart.plaiaundi.Database.EstablecimientoRepo;
import me.ivanmart.plaiaundi.Main;
import me.ivanmart.plaiaundi.Model.Articulo;
import me.ivanmart.plaiaundi.Model.ArticuloReserva;
import me.ivanmart.plaiaundi.Model.Cesta;
import me.ivanmart.plaiaundi.Model.Establecimiento;

import java.util.ArrayList;

public class ClientMenu {
    public void start(){
        ArrayList<Establecimiento> establecimientos = EstablecimientoRepo.getEstablecimientos();
        mostrarMenuEstablecimiento(establecimientos);

        int c = Util.getInt();
        while (c <=0 || c > establecimientos.size()) c = Util.getInt("Valor inválido.");
        menuReserva(establecimientos.get(c));
    }

    private void menuReserva(Establecimiento establecimiento){
        System.out.println("""
                +-------------------------------+
                | Qué tipo de artículo quieres? |
                | 1. Ski                        |
                | 2. Snowboard                  |
                | 3. Accesorios                 |
                | 0. Finalizar                  |
                +-------------------------------+
                """);
        int c = Util.getInt();
        while (c < 0 || c > 3) c = Util.getInt("Valor inválido.");
        ArrayList<Articulo> articulos = new ArrayList<>();
        switch (c){
            case 1:
                articulos = ArticuloRepo.getSkis(establecimiento.getId());
                break;
                // TODO: añadir "snowboard" y "accesorios"

            case 0:

                return;
        }
        for (Articulo a : articulos) System.out.println(a);
        int a = Util.getInt("Selecciona Articulo a reservar.");
        // TODO: Check if el id "a" está en "articulos"º
        int cantidad = Util.getInt("Cuantos quieres?");
        while (cantidad <= 0) cantidad = Util.getInt("Valor inválido");

        Cesta.addToCesta(new ArticuloReserva(a, cantidad));
        menuReserva(establecimiento);
    }

    private void mostrarMenuEstablecimiento(ArrayList<Establecimiento> establecimientos){
        String base = "+-----------------------------------------+\n";
        String inner = base + "| En qué establecimiento deseas reservar? |\n";
        for (int i = 0; i < establecimientos.size(); i++){
            String nombre = establecimientos.get(i).getNombre();
            inner += "| " + (i+1) + ". " + nombre + " ".repeat(base.length() - nombre.length() - 8) + " |\n";
        }
        inner += base;
        System.out.println(inner);
    }
}
