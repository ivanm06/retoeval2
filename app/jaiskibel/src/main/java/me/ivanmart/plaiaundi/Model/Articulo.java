package me.ivanmart.plaiaundi.Model;

import me.ivanmart.plaiaundi.Enums.Talla;
import me.ivanmart.plaiaundi.Enums.TipoArticulo;

public abstract class Articulo {
    // Atributos
    protected final int id;
    protected String nombre;
    protected String descripcion;
    protected int precio;
    protected Talla talla;
    protected TipoArticulo tipo;

    // Constructor
    public Articulo(int id, String nombre, String descripcion, int precio, Talla talla, TipoArticulo tipo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.talla = talla;
        this.tipo = tipo;
    }

    // Metodos

    /**
     * Devuelve un array de {@link String} con la información del objeto
     *
     * @param extra Cantidad de items extra que se quieran añadir a la lista.
     * @return {@link String}[]
     */
    public abstract String[] getDataArray(int extra);

    @Override
    public String toString() {
        return "%d | %s | %s | %s | %d".formatted(this.id, this.nombre, this.descripcion, this.talla, this.precio);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Articulo that)) return false;
        return id == that.id;
    }

    // Getters&Setters

    public int getId() { return id; }
    public String getNombre() { return nombre; }
    public String getDescripcion() { return descripcion; }
    public int getPrecio() { return precio; }
    public Talla getTalla() { return talla; }
    public TipoArticulo getTipo() { return tipo; }
}
