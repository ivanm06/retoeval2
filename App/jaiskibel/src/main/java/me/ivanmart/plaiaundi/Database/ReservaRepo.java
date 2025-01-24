package me.ivanmart.plaiaundi.Database;

import me.ivanmart.plaiaundi.Enums.Privilegio;
import me.ivanmart.plaiaundi.Enums.Sexo;
import me.ivanmart.plaiaundi.Enums.Talla;
import me.ivanmart.plaiaundi.Menus.AuthMenu;
import me.ivanmart.plaiaundi.Menus.Util;
import me.ivanmart.plaiaundi.Model.Articulo;
import me.ivanmart.plaiaundi.Model.ArticuloReserva;
import me.ivanmart.plaiaundi.Model.Reserva;
import me.ivanmart.plaiaundi.Model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReservaRepo {

    public static boolean reservar(ArrayList<ArticuloReserva> articulos){
        Usuario user = AuthMenu.getUsuario(); // guardar el ususario en una variable
        int idReserva = 1;

        try{
            String query = "insert into Reserva (DNIUsuario, fechaInicio, fechaFin) values (?, CURRENT_TIMESTAMP, CURRENT_TIMESTAMP + INTERVAL ? DAY);";
            PreparedStatement statement = DBConnector.con.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
            statement.setString(1, user.getDNI());
            statement.setInt(2, 30);
            statement.execute();

            ResultSet generatedKeys = statement.getGeneratedKeys();
            if (generatedKeys.next()) idReserva = generatedKeys.getInt(1);
        }catch (SQLException e){
            System.out.printf("[Error] Ha habido un problema con la base de datos: %s%n", e.getMessage());
            return false;
        }

        try{
            String query2 = "insert into articuloReservado values";

            boolean first = true;
            for (ArticuloReserva a : articulos) { // recorrer la lista de articulos
                query2 += (first ? "" : ",") +" (%d, %d, %d)".formatted(idReserva, a.getId(), a.getCantidad()); // añadir articulos reservados en la base de datos
                if (first) first = false;
            }
            Statement statement = DBConnector.con.createStatement();
            statement.execute(query2);
            return true;
        }catch (SQLException e){
            System.out.printf("[Error] Ha habido un problema con la base de datos: %s%n", e.getMessage());
            return false;
        }
    }

    public static ArrayList<Reserva> getReservas(){
        ArrayList<Reserva> reservas = new ArrayList<>();
        String query = "select * from Reserva";
        try{
            Statement statement = DBConnector.con.createStatement();
            statement.execute(query);
            ResultSet set = statement.getResultSet();
            while (set.next()) reservas.add( new Reserva(
                    set.getInt("id"),
                    set.getTimestamp("fechaInicio"),
                    set.getTimestamp("fechaFin")
            ));
            return reservas;
        }catch (SQLException e){
            System.out.println("[Error] " + e.getMessage());
        }
        return reservas;
    }

    public static ArrayList<ArticuloReserva> getArticulosFromReserva(int idReserva){
        String query = "select * from articuloReservado ar join Articulo a on a.id = ar.idArticulo join Reserva r on r.id = ar.idReserva join Establecimiento e on e.id = r.idEstablecimiento where r.id = ?";
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
                        Util.toEnum(Talla.class, set.getString("talla"), Talla.M),
                        set.getInt("cantidad")
                );
                System.out.println(ar);
                articulos.add(ar);
            }
            return articulos;
        }catch (SQLException e){
            System.out.println("[Error] " + e.getMessage());
        }
        return articulos;
    }
}