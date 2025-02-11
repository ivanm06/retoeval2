package me.ivanmart.plaiaundi.Model;

import java.util.ArrayList;

public class Cesta {
    private static final ArrayList<ArticuloReserva> cesta = new ArrayList<>();
    private static Establecimiento establecimiento;
    private static Fecha fecha; // Fecha en la que la reserva se llevará a cabo.

    // Métodos

    /**
     * Añade artículos a la cesta, si la cesta ya contiene ese artículo, le suma la cantidad extra.
     * */
    public static void addToCesta(ArticuloReserva art){
        boolean contains = cesta.contains(art);
        if (contains) cesta.get(cesta.indexOf(art)).addCantidad(art.getCantidad());
        else cesta.add(art);
    }


    // Getters & Setters

    public static ArrayList<ArticuloReserva> getCesta(){ return cesta; }

    public static Fecha getFecha(){ return Cesta.fecha; }

    public static Establecimiento getEstablecimiento() { return establecimiento; }

    public static void setEstablecimiento(Establecimiento e) { establecimiento = e; }

    public static void setFecha(Fecha fecha) { Cesta.fecha = fecha; }
}
