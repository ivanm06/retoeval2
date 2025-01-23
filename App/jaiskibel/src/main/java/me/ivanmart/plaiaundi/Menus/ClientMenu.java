package me.ivanmart.plaiaundi.Menus;

import me.ivanmart.plaiaundi.Database.ArticuloRepo;
import me.ivanmart.plaiaundi.Database.ClientRepo;
import me.ivanmart.plaiaundi.Model.Articulo;
import me.ivanmart.plaiaundi.Model.Establecimiento;

import java.util.ArrayList;
import java.util.Arrays;

public class ClientMenu {
    private ArrayList<Articulo> cesta = new ArrayList<>();

    public void start(){
        ArrayList<Establecimiento> establecimientos = ClientRepo.getEstablecimientos();
        mostrarMenuEstablecimiento(establecimientos);

        int c = Util.getInt();
        while (c <=0 || c > establecimientos.size()) c = Util.getInt("Valor inválido.");
        System.out.println("""
                +-------------------------------+
                | Qué tipo de artículo quieres? |
                | 1. Ski                        |
                | 2. Snowboard                  |
                | 3. Accesorios                 |
                +-------------------------------+
                """);
        int c2 = Util.getInt();
        while (c2 <=0 || c2 > 3) c2 = Util.getInt("Valor inválido.");

        ArrayList<Articulo> articulos = new ArrayList<>();
        switch (c2){
            case 1:
                System.out.println("Setting");
                articulos = ArticuloRepo.getSkis(establecimientos.get(c).getId());
                System.out.println("Setted");
                System.out.println(articulos.size());
                break;
        }
        for (Articulo a : articulos){
            System.out.println("um");
            System.out.println(a);
        }

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
