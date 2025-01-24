package me.ivanmart.plaiaundi.Model;

import java.sql.Timestamp;

public class Reserva {
    private int id;
    private Timestamp fechaInicio;
    private Timestamp fechaFin;

    public Reserva(int id, Timestamp fechaInicio, Timestamp fechaFin) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public String[] getDataArray(){
        return new String[]{String.valueOf(this.id), String.valueOf(this.fechaInicio), String.valueOf(this.fechaFin)};
    }
}
