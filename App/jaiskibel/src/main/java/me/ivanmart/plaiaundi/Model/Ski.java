package me.ivanmart.plaiaundi.Model;

public class Ski extends Articulo{
    public Ski(int id, String nombre) {
        super(id, nombre);
    }

    @Override
    public String toString() {
        return super.getId() + " " + super.getNombre();
    }
}
