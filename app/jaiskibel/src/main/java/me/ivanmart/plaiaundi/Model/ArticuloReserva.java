package me.ivanmart.plaiaundi.Model;

import me.ivanmart.plaiaundi.Enums.Talla;

public class ArticuloReserva extends Articulo {
    private int cantidad;

    public ArticuloReserva(int id, int cantidad) {
        super(id);
        this.cantidad = cantidad;
    }

    public ArticuloReserva(int id, String nombre, String descripcion, int precio, Talla talla, int cantidad) {
        super(id, nombre, descripcion, precio, talla);
        this.cantidad = cantidad;
    }

    public int getCantidad() {
        return cantidad;
    }
    public void addCantidad(int cantidad){
        this.cantidad += cantidad;
    }

    public String[] getDataArray(int extra){
        String[] dataArray = new String[]{String.valueOf(super.getId()), super.getNombre(), super.getDescripcion(), String.valueOf(super.getTalla()), String.valueOf(super.getPrecio()), String.valueOf(this.cantidad)};
        String[] extended = new String[dataArray.length + extra];
        System.arraycopy(dataArray, 0, extended, 0, dataArray.length);
        return extended;
    }

    @Override
    public String toString() {
        return "%d | %s | %s | %s | %d | %d ".formatted(super.getId(), super.getNombre(), super.getDescripcion(), super.getTalla(), super.getPrecio(), this.cantidad);
    }
}
