package me.ivanmart.plaiaundi.Model;

import me.ivanmart.plaiaundi.Enums.Talla;

public class Articulo {

    //Atributos
    private int id;
    private String nombre;
    private String descripcion;
    private int precio;
    private Talla talla;

    //Builder
    public Articulo(int id, String nombre, String descripcion, int precio, Talla talla) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.talla = talla;
    }

    //Getters && Setters
    public int getId() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public Talla getTalla() {
        return talla;
    }

    public String[] getDataArray(int extra){
        String[] dataArray = new String[]{String.valueOf(this.getId()), this.getNombre(), this.getDescripcion(), this.getTalla().toString(), String.valueOf(this.getPrecio())};
        String[] extended = new String[dataArray.length + extra];
        System.arraycopy(dataArray, 0, extended, 0, dataArray.length);
        return extended;
    }

    //Metodos
    @Override
    public String toString() {
        return "%d | %s | %s | %s | %d".formatted(this.id, this.nombre, this.descripcion, this.talla, this.precio);
    }
}
