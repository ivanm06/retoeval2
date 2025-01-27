package me.ivanmart.plaiaundi.Model;

import me.ivanmart.plaiaundi.Enums.Talla;

public class ArticuloReserva {
    private int id;
    private String nombre;
    private String descripcion;
    private int precio;
    private Talla talla;
    private int cantidad;
    private Establecimiento establecimiento;

    public ArticuloReserva(int id, int cantidad, Establecimiento establecimiento) {
        this.id = id;
        this.cantidad = cantidad;
        this.establecimiento = establecimiento;
    }

    public ArticuloReserva(int id, String nombre, String descripcion, int precio, Talla talla, int cantidad) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.precio = precio;
        this.talla = talla;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void addCantidad(int cantidad){
        this.cantidad += cantidad;
    }

    public Establecimiento getEstablecimiento() {
        return establecimiento;
    }

    public String[] getDataArray(int extra){
        String[] dataArray = new String[]{String.valueOf(this.id), this.nombre, this.descripcion, String.valueOf(this.talla), String.valueOf(this.precio), String.valueOf(this.cantidad)};
        String[] extended = new String[dataArray.length + extra];
        System.arraycopy(dataArray, 0, extended, 0, dataArray.length);
        return extended;
    }

    @Override
    public boolean equals(Object o) {
        if (!(o instanceof ArticuloReserva that)) return false;
        return id == that.id;
    }

    @Override
    public String toString() {
        return "ArticuloReserva{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", precio=" + precio +
                ", talla=" + talla +
                ", cantidad=" + cantidad +
                ", establecimiento=" + establecimiento +
                '}';
    }
}
