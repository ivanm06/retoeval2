package me.ivanmart.plaiaundi.Model;

import java.sql.Timestamp;

public class Reserva {
    // Atributos
    private int id;
    private Timestamp fechaInicio;
    private Timestamp fechaFin;
    private int idEstablecimiento;
    private String establecimiento;
    private int articulos;
    private int precio;

    // Constructores

    public Reserva(int id, Timestamp fechaInicio, Timestamp fechaFin) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public Reserva(int id, Timestamp fechaInicio, Timestamp fechaFin, int idEstablecimiento, String establecimiento, int articulos, int precio) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.idEstablecimiento = idEstablecimiento;
        this.establecimiento = establecimiento;
        this.articulos = articulos;
        this.precio = precio;
    }

    // Metodos

    public String[] getDataArray() {
        return new String[]{
                String.valueOf(this.id),
                String.valueOf(this.fechaInicio),
                String.valueOf(this.fechaFin),
                this.establecimiento,
                String.valueOf(this.articulos),
                String.valueOf(this.precio)
        };
    }
}
