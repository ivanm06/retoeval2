package me.ivanmart.plaiaundi.Model;

public class Reserva {
    // Atributos
    private int id;
    private Fecha fecha;
    private String establecimiento;
    private int articulos;
    private int precio;

    // Constructores
    public Reserva(int id, Fecha fecha, String establecimiento, int articulos, int precio) {
        this.id = id;
        this.fecha = fecha;
        this.establecimiento = establecimiento;
        this.articulos = articulos;
        this.precio = precio;
    }

    // Metodos
    public String[] getDataArray() {
        return new String[]{
                String.valueOf(this.id),
                String.valueOf(this.fecha.getInicio()),
                String.valueOf(this.fecha.getFin()),
                this.establecimiento,
                String.valueOf(this.articulos),
                String.valueOf(this.precio)
        };
    }
}
