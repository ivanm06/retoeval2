package me.ivanmart.plaiaundi.Model;

import java.sql.Timestamp;

public class Fecha {
    private Timestamp fechaInicio;
    private Timestamp fechaFin;

    public Fecha(Timestamp fechaInicio, Timestamp fechaFin) {
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
    }

    public int getDias(){
        if (fechaFin == null || fechaInicio == null) return 0;
        long timediff = fechaFin.getTime() - fechaInicio.getTime();
        return (int) (timediff / 1000 / 60 / 60 / 24);
    }

    public Timestamp getFin(){return this.fechaFin;}
    public Timestamp getInicio(){return this.fechaInicio;}
}
