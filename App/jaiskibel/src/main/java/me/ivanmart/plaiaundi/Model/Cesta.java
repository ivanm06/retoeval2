package me.ivanmart.plaiaundi.Model;

import java.util.ArrayList;

public class Cesta {
    private static final ArrayList<ArticuloReserva> cesta = new ArrayList<>();
    private Establecimiento establecimiento;

    public static void addToCesta(ArticuloReserva art){
        boolean contains = cesta.contains(art);
        if (contains) cesta.get(cesta.indexOf(art)).addCantidad(art.getCantidad());
        else cesta.add(art);
    }

    public static ArrayList<ArticuloReserva> getCesta(){
        return cesta;
    }
}
