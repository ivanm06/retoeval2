package me.ivanmart.plaiaundi.Model;

import me.ivanmart.plaiaundi.Enums.Modalidad;
import me.ivanmart.plaiaundi.Enums.Nivel;
import me.ivanmart.plaiaundi.Enums.Talla;

public class Ski extends Articulo{
    //Atributos
    private Modalidad modalidad;
    private Nivel nivel;

    //Builder
    public Ski(int id, String nombre, String descripcion, int precio, Talla talla, Modalidad modalidad, Nivel nivel) {
        super(id, nombre, descripcion, precio, talla);
        this.nivel = nivel;
        this.modalidad = modalidad;
    }

    // Metodods

    @Override
    public String[] getDataArray(int extra){
        String[] dataArray = new String[]{String.valueOf(this.getId()), this.getNombre(), this.getDescripcion(), this.getTalla().toString(), this.modalidad.toString(), this.nivel.toString(), String.valueOf(this.getPrecio())};
        String[] extended = new String[dataArray.length + extra];
        System.arraycopy(dataArray, 0, extended, 0, dataArray.length);
        return extended;
    }

    @Override
    public String toString() {
        return super.toString() + " ";
    }
}
