package me.ivanmart.plaiaundi.Model;

import me.ivanmart.plaiaundi.Enums.Modalidad;
import me.ivanmart.plaiaundi.Enums.Talla;
import me.ivanmart.plaiaundi.Enums.TipoArticulo;

public class Snowboard extends Articulo{

    // Atributos
    private final Modalidad modalidad;

    // Constructor
    public Snowboard(int id, String nombre, String descripcion, int precio, Talla talla, Modalidad modalidad, TipoArticulo tipo) {
        super(id, nombre, descripcion, precio, talla, tipo);
        this.modalidad = modalidad;
    }

    // Metodos
    @Override
    public String[] getDataArray(int extra){
        String[] dataArray = new String[]{String.valueOf(this.getId()), this.getNombre(), this.getDescripcion(), this.getTalla().toString(), this.modalidad.toString(), String.valueOf(this.getPrecio())};
        String[] extended = new String[dataArray.length + extra];
        System.arraycopy(dataArray, 0, extended, 0, dataArray.length);
        return extended;
    }

    @Override
    public String toString() {
        return super.toString() + " | " + modalidad;
    }
}
