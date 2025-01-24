package me.ivanmart.plaiaundi.Model;

import me.ivanmart.plaiaundi.Enums.Talla;

public class Accesorio extends Articulo{
    private String tipo;

    public Accesorio(int id, String nombre, String descripcion, int precio, Talla talla, String tipo) {
        super(id, nombre, descripcion, precio, talla);
        this.tipo = tipo;
    }

    @Override
    public String[] getDataArray(int extra){
        String[] dataArray = new String[]{String.valueOf(this.getId()), this.getNombre(), this.getDescripcion(), this.getTalla().toString(), this.tipo, String.valueOf(this.getPrecio())};
        String[] extended = new String[dataArray.length + extra];
        System.arraycopy(dataArray, 0, extended, 0, dataArray.length);
        return extended;
    }

    @Override
    public String toString() {
        return super.toString() + "";
    }
}
