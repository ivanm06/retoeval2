package me.ivanmart.plaiaundi.Model;

public class ArticuloReserva {
    private int id;
    private int cantidad;

    public ArticuloReserva(int id, int cantidad) {
        this.id = id;
        this.cantidad = cantidad;
    }

    public int getId() {
        return id;
    }

    public int getCantidad() {
        return cantidad;
    }
}
