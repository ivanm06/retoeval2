package me.ivanmart.plaiaundi.Model;

import java.util.HashMap;

public class Establecimiento {
    private String nombre;
    private int id;
    private HashMap <Integer, Integer> stock = new HashMap<>();

    public Establecimiento(int id, String nombre) {
        this.nombre = nombre;
        this.id = id;
    }

    public String getNombre() {
        return this.nombre;
    }

    public int getId() {
        return this.id;
    }

    public int getStock(int idArticulo){
        Integer cantidad = stock.get(idArticulo);
        if (cantidad == null) return -1;
        else return cantidad;
    }

    public boolean containsArticulo(int idArticulo){
        return stock.containsKey(idArticulo);
    }

    public void setStock(HashMap<Integer, Integer> stock){
        this.stock = stock;
    }
    public void setPending(int idArticulo, int cantidad){
        stock.replace(idArticulo, stock.get(idArticulo) - cantidad);
    }
}
