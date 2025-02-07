package me.ivanmart.plaiaundi.Database;

import me.ivanmart.plaiaundi.Enums.Talla;
import me.ivanmart.plaiaundi.Enums.TipoArticulo;
import me.ivanmart.plaiaundi.Menus.AuthMenu;
import me.ivanmart.plaiaundi.Utils.MenuUtil;
import me.ivanmart.plaiaundi.Model.*;

import java.sql.*;
import java.util.ArrayList;

public class ReservaRepo {

    public static boolean reservar(ArrayList<ArticuloReserva> articulos, int dias){
        Usuario user = AuthMenu.getUsuario();
        int idReserva = 1;

        // Crea la reserva en la base de datos.
        try{
            String query = "insert into Reserva (DNIUsuario, fechaInicio, fechaFin, idEstablecimiento) values (?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL ? DAY, ?);";
            PreparedStatement statement = DBConnector.con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getDNI());
            statement.setInt(2, dias);
            statement.setInt(3, Cesta.getEstablecimiento().getId());
            statement.execute();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) idReserva = generatedKeys.getInt(1);
        }catch (SQLException e){
            System.out.printf("[Error] Ha habido un problema con la base de datos: %s%n", e.getMessage());
            return false;
        }

        // Le asigna los articulos a la reserva creada anteriormente.
        try{
            String query = "insert into articuloReservado values";

            boolean first = true;
            for (ArticuloReserva a : articulos) { // recorrer la lista de articulos
                query += (first ? "" : ",") +" (%d, %d, %d)".formatted(idReserva, a.getId(), a.getCantidad()); // añadir articulos reservados en la base de datos
                if (first) first = false;
            }
            Statement statement = DBConnector.con.createStatement();
            statement.execute(query);
            return true;
        }catch (SQLException e){
            System.out.printf("[Error] Ha habido un problema con la base de datos: %s%n", e.getMessage());
            return false;
        }
    }

    /**
     * Devuelve los {@link Articulo} que pertenecen a una reserva.
     * @return {@link ArrayList}<{@link ArticuloReserva}>
     * */
    public static ArrayList<ArticuloReserva> getArticulosFromReserva(int idReserva){
        String query = "select a.id, a.nombre, a.descripcion, a.precio, a.talla, ar.cantidad, case when sn.idArticulo is not null then 'snowboard' when sk.idArticulo is not null then 'ski' else 'accesorio' end as tipo from articuloReservado ar join Articulo a on a.id = ar.idArticulo join Reserva r on r.id = ar.idReserva join Establecimiento e on e.id = r.idEstablecimiento left join Ski sk using(idArticulo) left join Snowboard sn using(idArticulo) where r.id = ?";
        ArrayList<ArticuloReserva> articulos = new ArrayList<>();

        try{
            PreparedStatement statement = DBConnector.con.prepareStatement(query);
            statement.setInt(1, idReserva);
            statement.execute();
            ResultSet set = statement.getResultSet();
            while (set.next()){
                ArticuloReserva ar = new ArticuloReserva(
                        set.getInt("id"),
                        set.getString("nombre"),
                        set.getString("descripcion"),
                        set.getInt("precio"),
                        MenuUtil.toEnum(Talla.class, set.getString("talla"), Talla.M),
                        set.getInt("cantidad"),
                        MenuUtil.toEnum(TipoArticulo.class, set.getString("tipo"), TipoArticulo.ACCESORIO)
                );
                articulos.add(ar);
            }
            return articulos;
        }catch (SQLException e){
            System.out.println("[Error] " + e.getMessage());
        }
        return articulos;
    }


    /**
     * Devuelve las reservas de un cliente
     *
     * @param dni Dni del usuario
     * @return {@link ArrayList}<{@link Reserva}>
     * */

    public static ArrayList<Reserva> getReservasDeCliente(String dni){
        String query = "select r.id, r.fechaInicio, r.fechaFin, e.id as idEstablecimiento, e.nombre as establecimiento, sum(ar.cantidad) as articulos, (sum(a.precio) * timestampdiff(DAY, r.fechaInicio, r.fechaFin)) as precio from Reserva r join Establecimiento e on e.id = r.idEstablecimiento join articuloReservado ar on r.id = ar.idReserva join Articulo a on ar.idArticulo = a.id where r.dniUsuario = ? group by r.id order by precio desc;";
        ArrayList<Reserva> reservas = new ArrayList<>();
        try{
            PreparedStatement statement = DBConnector.con.prepareStatement(query);
            statement.setString(1, dni);
            statement.execute();
            ResultSet set = statement.getResultSet();
            while (set.next()) reservas.add(new Reserva(
                    set.getInt("id"),
                    set.getTimestamp("fechaInicio"),
                    set.getTimestamp("fechaFin"),
                    set.getInt("idEstablecimiento"),
                    set.getString("establecimiento"),
                    set.getInt("articulos"),
                    set.getInt("precio")
            ));
        }catch (SQLException e){
            System.out.printf("[Error] %s%n", e.getMessage());
        }
        return reservas;
    }

    //Eliminar un reserva

    public static void anularReserva(int reservaID) {
        String query = "DELETE FROM Reserva WHERE id = ?;";
        try {
            PreparedStatement statement = DBConnector.con.prepareStatement(query);
            statement.setString(1, String.valueOf(reservaID));
            statement.execute();
        } catch (SQLException e) {
            System.out.printf("[Error] Ha habido un problema con la base de datos: %s%n", e.getMessage());
        }
        System.out.println("Reserva con ID '" + reservaID + "' ha sido anulada correctamente.");


    }

}