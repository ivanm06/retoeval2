package me.ivanmart.plaiaundi.Model;

import me.ivanmart.plaiaundi.Enums.Talla;
import me.ivanmart.plaiaundi.Enums.TipoArticulo;

public class Articulo {
    // Atributos
    private final int id;
    private String nombre;
    private String descripcion;
    private int precio;
    private Talla talla;
    private TipoArticulo tipo;

    // Constructor
    public Articulo(int id, String nombre, String descripcion, int precio, Talla talla, TipoArticulo tipo) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.talla = talla;
        this.tipo = tipo;
    }

    public Articulo(int id) {
        this.id = id;
    }

    // Getters && Setters
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

    public TipoArticulo getTipo() {
        return tipo;
    }

    // Metodos

    /**
     * Devuelve un array de {@link String} con la información del objeto
     *
     * @param extra Cantidad de items extra que se quieran añadir a la lista.
     * @return {@link String}[]
     */
    public String[] getDataArray(int extra) {
        String[] dataArray = new String[]{String.valueOf(this.getId()), this.getNombre(), this.getDescripcion(), this.getTalla().toString(), String.valueOf(this.getPrecio())};
        String[] extended = new String[dataArray.length + extra];
        System.arraycopy(dataArray, 0, extended, 0, dataArray.length);
        return extended;
    }

    @Override
    public String toString() {
        return "%d | %s | %s | %s | %d".formatted(this.id, this.nombre, this.descripcion, this.talla, this.precio);
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof Articulo that)) return false;
        return id == that.id;
    }
}
