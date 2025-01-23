package me.ivanmart.plaiaundi.Model;

public class Establecimiento {
    private String nombre;
    private int id;

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
}
