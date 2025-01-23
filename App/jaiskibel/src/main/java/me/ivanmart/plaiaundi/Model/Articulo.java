package me.ivanmart.plaiaundi.Model;

public class Articulo {
    private int id;
    private String nombre;

    public Articulo(int id, String nombre) {
        this.id = id;
        this.nombre = nombre;
    }

    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }
}
