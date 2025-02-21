package me.ivanmart.plaiaundi.Model;

import me.ivanmart.plaiaundi.Enums.Modalidad;
import me.ivanmart.plaiaundi.Enums.Nivel;
import me.ivanmart.plaiaundi.Enums.Talla;
import me.ivanmart.plaiaundi.Enums.TipoArticulo;

public class Ski extends Articulo{
    // Atributos
    private final Modalidad modalidad;
    private final Nivel nivel;

    // Constructor
    public Ski(int id, String nombre, String descripcion, int precio, Talla talla, Modalidad modalidad, Nivel nivel, TipoArticulo tipo) {
        super(id, nombre, descripcion, precio, talla, tipo);
        this.nivel = nivel;
        this.modalidad = modalidad;
    }

    // Metodos

    @Override
    public String[] getDataArray(int extra){
        String[] dataArray = new String[]{String.valueOf(this.id), this.nombre, this.descripcion, String.valueOf(this.talla), String.valueOf(this.modalidad), String.valueOf(this.nivel), String.valueOf(this.precio)};
        String[] extended = new String[dataArray.length + extra];
        System.arraycopy(dataArray, 0, extended, 0, dataArray.length);
        return extended;
    }

    @Override
    public String toString() {
        return "%s | %s | %s".formatted(super.toString(), modalidad, nivel);
    }
}
