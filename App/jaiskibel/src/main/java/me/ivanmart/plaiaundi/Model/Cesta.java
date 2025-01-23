package me.ivanmart.plaiaundi.Model;

import java.util.ArrayList;

public class Cesta {
    private static final ArrayList<ArticuloReserva> cesta = new ArrayList<>();

    public static void addToCesta(ArticuloReserva art){
        cesta.add(art);
    }

    public static ArrayList<ArticuloReserva> getCesta(){
        return cesta;
    }
}
