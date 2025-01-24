package me.ivanmart.plaiaundi.Menus;

import me.ivanmart.plaiaundi.Database.ArticuloRepo;
import me.ivanmart.plaiaundi.Database.EstablecimientoRepo;
import me.ivanmart.plaiaundi.Model.Articulo;
import me.ivanmart.plaiaundi.Model.ArticuloReserva;
import me.ivanmart.plaiaundi.Model.Cesta;
import me.ivanmart.plaiaundi.Model.Establecimiento;

import java.util.ArrayList;
import java.util.HashMap;

public class ClientMenu {

    public void start(){
        ArrayList<Establecimiento> establecimientos = EstablecimientoRepo.getEstablecimientos();  // Istanciar el arryList de los Establecimiento
        mostrarMenuEstablecimiento(establecimientos);   // Mostrar el menu 

        int c = Util.getInt(); // Guardar selección
        while (c <= 0 || c > establecimientos.size()) c = Util.getInt("Valor inválido.");
        Establecimiento establecimiento = establecimientos.get(c-1);
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
        int c = Util.getInt();
        while (c < 0 || c > 3) c = Util.getInt("Valor inválido."); // validar la seleción
        ArrayList<Articulo> articulos = new ArrayList<>(); // Crear el array de los articulos
        String[] titulos = new String[]{};
        switch (c){ //Case que guarda los titulos dependiendo del articulo seleccionado
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
        if (articulos.isEmpty()){ // Si no hay artículos en la lista
            System.out.println("[Info] No hay artículos de este tipo.");
            return false;
        }
        int articulosFS = 0;

        ArrayList<String[]> valores = new ArrayList<>(); // Crear una lista para los valores
        for (Articulo a : articulos){
            String[] dataArray = a.getDataArray(1);
            int stock = establecimiento.getStock(a.getId());
            if (stock < 1){
                dataArray[dataArray.length-1] = "Fuera de Stock";
                articulosFS++;
            }else dataArray[dataArray.length-1] = "%d en stock".formatted(stock);
            valores.add(dataArray);
        }

        System.out.println(Util.generateTable(titulos, valores));

        if (articulos.size() == articulosFS){
            System.out.println("No hay articulos en stock en este momento.");
            return false;
        }

        int a = Util.getInt("Selecciona el id del articulo a reservar. (0 para ir atrás.)");

        // el id del artículo no puede ser negativo y tiene que estár en ese establecimiento
        while (a < 0 || (!establecimiento.containsArticulo(a) && a != 0)) a = Util.getInt("Artículo inválido");
        if (a == 0) return false;
        int stock = establecimiento.getStock(a);
        if (stock <1){
            System.out.println("No hay stock de ese artículo.");
            return false;
        }

        int cantidad = Util.getInt("Cuantos quieres?");

        // el valor de la cantidad no puede ser negativo ni mayor al stock del articulo
        while (cantidad < 0 || cantidad > stock) cantidad = Util.getInt("Valor inválido");
        if (cantidad == 0) return false;
        System.out.printf("%d articulos añadidos a la cesta.%n", cantidad);
        establecimiento.setPending(a, cantidad);
        Cesta.addToCesta(new ArticuloReserva(a, cantidad, establecimiento));
        menuReserva(establecimiento);
        return false;
    }

    private void mostrarMenuEstablecimiento(ArrayList<Establecimiento> establecimientos){
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
