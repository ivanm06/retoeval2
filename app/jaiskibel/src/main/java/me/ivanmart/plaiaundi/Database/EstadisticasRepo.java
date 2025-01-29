package me.ivanmart.plaiaundi.Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EstadisticasRepo {
    public static ArrayList<String[]> getReservasPorDinero(){
        ArrayList<String[]> reservas = new ArrayList<>();
        String query = "select r.*, count(a.id) articulos, sum(a.precio) total from Reserva r join articuloReservado ar on r.id = ar.idReserva join Articulo a on a.id = ar.idArticulo group by r.id order by total desc;";
        try{
            Statement statement = DBConnector.con.createStatement();
            statement.execute(query);
            ResultSet set = statement.getResultSet();
            while (set.next()) reservas.add(new String[]{
                    set.getString("dniUsuario"),
                    set.getString("id"),
                    set.getString("fechaInicio"),
                    set.getString("fechaFin"),
                    set.getString("idEstablecimiento"),
                    set.getString("articulos"),
                    set.getString("total")
            });
            return reservas;
        }catch (SQLException e){
            System.out.println("[Error] " + e.getMessage());
        }
        return reservas;
    }

    public static ArrayList<String[]> getReservas(){
        ArrayList<String[]> reservas = new ArrayList<>();
        String query = "select * from Reserva";
        try{
            Statement statement = DBConnector.con.createStatement();
            statement.execute(query);
            ResultSet set = statement.getResultSet();
            while (set.next()) reservas.add(new String[]{
                    set.getString("id"),
                    set.getString("fechaInicio"),
                    set.getString("fechaFin")
            });
            return reservas;
        }catch (SQLException e){
            System.out.println("[Error] " + e.getMessage());
        }
        return reservas;
    }
    public static ArrayList<String[]> getArticulosMasPedidos(){
        ArrayList<String[]> reservas = new ArrayList<>();
        String query = "select a.id, a.nombre, sum(ar.cantidad) as cantidad from Articulo a join articuloReservado ar on a.id = ar.idArticulo group by a.id order by cantidad desc;";
        try{
            Statement statement = DBConnector.con.createStatement();
            statement.execute(query);
            ResultSet set = statement.getResultSet();
            while (set.next()) reservas.add(new String[]{
                    set.getString("id"),
                    set.getString("nombre"),
                    set.getString("cantidad")
            });
            return reservas;
        }catch (SQLException e){
            System.out.println("[Error] " + e.getMessage());
        }
        return reservas;
    }
    public static ArrayList<String[]> getClientesHabituales(){
        ArrayList<String[]> reservas = new ArrayList<>();
        String query = "select u.dni, concat(u.nombre, ' ',  u.apellido1, ' ', u.apellido2) as nombre, count(r.id) as reservas from Usuario u join Reserva r on u.dni = r.dniUsuario group by u.dni order by reservas desc;";
        try{
            Statement statement = DBConnector.con.createStatement();
            statement.execute(query);
            ResultSet set = statement.getResultSet();
            while (set.next()) reservas.add(new String[]{
                    set.getString("dni"),
                    set.getString("nombre"),
                    set.getString("reservas")
            });
            return reservas;
        }catch (SQLException e){
            System.out.println("[Error] " + e.getMessage());
        }
        return reservas;
    }
    public static int getNumeroClientes(){
        String query = "select count(distinct u.dni) as total from Usuario u join Reserva r on u.dni = r.dniUsuario;";
        try{
            Statement statement = DBConnector.con.createStatement();
            statement.execute(query);
            ResultSet set = statement.getResultSet();
            if(set.next()) return set.getInt("total");
            return 0;
        }catch (SQLException e){
            System.out.println("[Error] " + e.getMessage());
        }
        return 0;
    }


}
