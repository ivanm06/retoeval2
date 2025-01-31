package me.ivanmart.plaiaundi.Menus;

import me.ivanmart.plaiaundi.Database.ArticuloRepo;
import me.ivanmart.plaiaundi.Database.EstablecimientoRepo;
import me.ivanmart.plaiaundi.Model.Articulo;
import me.ivanmart.plaiaundi.Model.ArticuloReserva;
import me.ivanmart.plaiaundi.Model.Cesta;
import me.ivanmart.plaiaundi.Model.Establecimiento;
import me.ivanmart.plaiaundi.Utils.MenuUtil;

import java.util.ArrayList;
import java.util.HashMap;

public class ClientMenu {

    public void start(){
        // Recuperar lista de establecimientos de la base de datos.
        ArrayList<Establecimiento> establecimientos = EstablecimientoRepo.getEstablecimientos();
        mostrarMenuEstablecimiento(establecimientos);

        int c = MenuUtil.getInt();
        while (c <= 0 || c > establecimientos.size()) c = MenuUtil.getInt("[Error] Selecciona un establecimiento válido.");
        Establecimiento establecimiento = establecimientos.get(c-1);
        Cesta.setEstablecimiento(establecimiento);

        // Mapa de articulos en stock en el establecimiento.
        HashMap<Integer, Integer> stock = ArticuloRepo.getStock(establecimiento.getId());
        establecimiento.setStock(stock);

        boolean finalizar = menuReserva(establecimiento);
        while (!finalizar) finalizar = menuReserva(establecimiento);
    }

    private boolean menuReserva(Establecimiento establecimiento){
        System.out.println("""
                +-------------------------------+
                | Qué tipo de artículo quieres? |
                | 1. Ski                        |
                | 2. Snowboard                  |
                | 3. Accesorios                 |
                | 0. Finalizar                  |
                +-------------------------------+
                """);

        int c = MenuUtil.getInt();
        while (c < 0 || c > 3) c = MenuUtil.getInt("[Error] Selecciona un artículo válido.");

        // Inicializar listas.
        ArrayList<Articulo> articulos = new ArrayList<>();
        String[] titulos = new String[]{};

        // Generar títulos en base al tipo de articulo seleccionado.
        switch (c){
            case 1:
                articulos = ArticuloRepo.getSkis(establecimiento.getId());
                titulos = new String[]{"ID", "Nombre", "Descripcion", "Talla", "Modalidad", "Nivel", "Precio/Dia", "Stock"};
                break;
            case 2:
                articulos = ArticuloRepo.getSnowboards(establecimiento.getId());
                titulos = new String[]{"ID", "Nombre", "Descripcion", "Talla", "Modalidad", "Precio/Dia", "Stock"};
                break;
            case 3:
                articulos = ArticuloRepo.getAccesorios(establecimiento.getId());
                titulos = new String[]{"ID", "Nombre", "Descripcion", "Talla", "Tipo", "Precio/Dia", "Stock"};
                break;
            case 0:
                return true;
        }

        if (articulos.isEmpty()){
            System.out.println("[Info] No hay artículos de este tipo.");
            return false;
        }

        int articulosFS = 0; // Cantidad de artículos fuera de stock.

        // Generar tabla de valores.
        ArrayList<String[]> valores = new ArrayList<>();
        for (Articulo a : articulos){
            String[] dataArray = a.getDataArray(1);
            int stock = establecimiento.getStock(a.getId());
            if (stock < 1){
                dataArray[dataArray.length-1] = "Fuera de Stock";
                articulosFS++;
            }else dataArray[dataArray.length-1] = stock + " en stock";
            valores.add(dataArray);
        }
        MenuUtil.generateTable(titulos, valores);

        // Si todos los artículos están fuera de stock, salir.
        if (articulos.size() == articulosFS){
            System.out.println("[Info] No hay articulos en stock en este momento.");
            return false;
        }

        int a = MenuUtil.getInt("Selecciona el id del articulo a reservar. (0 para ir atrás.)");

        // El id del artículo no puede ser negativo y tiene que estár en ese establecimiento
        while (a < 0 || (!establecimiento.containsArticulo(a) && a != 0)) a = MenuUtil.getInt("[Error] Selecciona un artículo válido.");
        if (a == 0) return false;

        int stock = establecimiento.getStock(a);
        if (stock < 1){
            System.out.println("[Info] No hay stock de ese artículo.");
            return false;
        }

        int cantidad = MenuUtil.getInt("Cuantos quieres?");
        while (cantidad <= 0) cantidad = MenuUtil.getInt("[Error] La cantidad tiene que ser un número entero positivo.");

        // El valor de la cantidad no puede ser negativo ni mayor al stock del articulo
        while (cantidad < 0 || cantidad > stock) cantidad = MenuUtil.getInt("[Error] No hay suficientes artículos en stock.");
        if (cantidad == 0) return false;

        System.out.printf("[Info] %d articulos añadidos a la cesta.%n", cantidad);
        establecimiento.setPending(a, cantidad);
        Cesta.addToCesta(new ArticuloReserva(a, cantidad));
        return false;
    }

    private void mostrarMenuEstablecimiento(ArrayList<Establecimiento> establecimientos){
        if (establecimientos.isEmpty()){
            System.out.println("[Info] No hay establecimientos disponibles.");
            return;
        }
        String base = "+-----------------------------------------+\n";
        String inner = base + "| En qué establecimiento deseas reservar? |\n";
        for (int i = 0; i < establecimientos.size(); i++){ // Mostrar los establecimientos enumerados y sus nombres
            String nombre = establecimientos.get(i).getNombre();
            inner += "| " + (i+1) + ". " + nombre + " ".repeat(base.length() - nombre.length() - 8) + " |\n";
        }
        inner += base;
        System.out.println(inner);
    }
}
