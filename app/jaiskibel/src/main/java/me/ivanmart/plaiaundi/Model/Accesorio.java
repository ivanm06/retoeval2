package me.ivanmart.plaiaundi.Model;

import me.ivanmart.plaiaundi.Enums.Talla;
import me.ivanmart.plaiaundi.Enums.TipoArticulo;

public class Accesorio extends Articulo{
    private final String tipo;

    public Accesorio(int id, String nombre, String descripcion, int precio, Talla talla, String tipo, TipoArticulo tipoArticulo) {
        super(id, nombre, descripcion, precio, talla, tipoArticulo);
        this.tipo = tipo;
    }

    @Override
    public String[] getDataArray(int extra){
        String[] dataArray = new String[]{String.valueOf(this.id), this.nombre, this.descripcion, String.valueOf(this.talla), this.tipo, String.valueOf(this.precio)};
        String[] extended = new String[dataArray.length + extra];
        System.arraycopy(dataArray, 0, extended, 0, dataArray.length);
        return extended;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
