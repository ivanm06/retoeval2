package me.ivanmart.plaiaundi.Database;

import me.ivanmart.plaiaundi.Enums.Modalidad;
import me.ivanmart.plaiaundi.Enums.Nivel;
import me.ivanmart.plaiaundi.Enums.Talla;
import me.ivanmart.plaiaundi.Enums.TipoArticulo;
import me.ivanmart.plaiaundi.Model.*;
import me.ivanmart.plaiaundi.Utils.MenuUtil;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;

public class ArticuloRepo {

    public static ArrayList<Articulo> getSkis(int idEstablecimiento){
        ArrayList<Articulo> articulos = new ArrayList<>(); // Crear lista de los artículos (skis)
        String query = "select * from Articulo a join articuloEstablecimiento ae on ae.idArticulo = a.id join Ski S on a.id = S.idArticulo where ae.idEstablecimiento = ?";

        try{
            PreparedStatement statement = DBConnector.con.prepareStatement(query);
            statement.setInt(1, idEstablecimiento);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) articulos.add(new Ski(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getInt("precio"),
                    MenuUtil.toEnum(Talla.class, rs.getString("talla"), Talla.M),
                    MenuUtil.toEnum(Modalidad.class, rs.getString("modalidad"), Modalidad.PISTA),
                    MenuUtil.toEnum(Nivel.class, rs.getString("nivel"), Nivel.PRINCIPIANTE),
                    TipoArticulo.SKI
            ));
            return articulos;
        }catch (SQLException e){
            System.out.printf("[Error] Ha habido un problema con la base de datos: %s%n", e.getMessage());
        }
        return articulos;
    }

    public static ArrayList<Articulo> getSnowboards(int idEstablecimiento){
        ArrayList<Articulo> articulos = new ArrayList<>(); // Crear lista de los artículos (Snowboards)
        String query = "select * from Articulo a join articuloEstablecimiento ae on ae.idArticulo = a.id join Snowboard S on a.id = S.idArticulo where ae.idEstablecimiento = ?";

        try{
            PreparedStatement statement = DBConnector.con.prepareStatement(query);
            statement.setInt(1, idEstablecimiento);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) articulos.add(new Snowboard(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getInt("precio"),
                    MenuUtil.toEnum(Talla.class, rs.getString("talla"), Talla.M),
                    MenuUtil.toEnum(Modalidad.class, rs.getString("modalidad"), Modalidad.PISTA),
                    TipoArticulo.SNOWBOARD
            ));
            return articulos;
        }catch (SQLException e){
            System.out.printf("[Error] Ha habido un problema con la base de datos: %s%n", e.getMessage());
        }
        return articulos;
    }

    public static ArrayList<Articulo> getAccesorios(int idEstablecimiento){
        ArrayList<Articulo> articulos = new ArrayList<>(); // Crear lista de los artículos (accesorios)
        String query = "select * from Articulo a join articuloEstablecimiento ae on ae.idArticulo = a.id join Accesorios A on a.id = A.idArticulo where ae.idEstablecimiento = ?";

        try{
            PreparedStatement statement = DBConnector.con.prepareStatement(query);
            statement.setInt(1, idEstablecimiento);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) articulos.add(new Accesorio(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getString("descripcion"),
                    rs.getInt("precio"),
                    MenuUtil.toEnum(Talla.class, rs.getString("talla"), Talla.M),
                    rs.getString("tipo"),
                    TipoArticulo.ACCESORIO
            ));
            return articulos;
        }catch (SQLException e){
            System.out.printf("[Error] Ha habido un problema con la base de datos: %s%n", e.getMessage());
        }
        return articulos;
    }

    public static HashMap<Integer, Integer> getStock(int idEstablecimiento, Fecha fechaReserva){
        String query = "select ae.idArticulo, ae.cantidad - coalesce(ventas.vendido, 0) as stock from articuloEstablecimiento ae " +
                "left join ( " +
                "SELECT ae.idArticulo, sum(ar.cantidad) as vendido from articuloEstablecimiento ae " +
                "left join articuloReservado ar using(idArticulo) " +
                "left join Reserva r on ar.idReserva = r.id " +
                "WHERE ae.idEstablecimiento = 1 and " +
                "((? between r.fechaInicio and r.fechaFin) /* Check si hay una reserva parcial dentro de la fecha a reservar. */ " +
                "or (? between r.fechaInicio and r.fechaFin) /* Check si hay una reserva parcial dentro de la fecha a reservar. */ " +
                "or (r.fechaInicio between ? and ?)) /* Check si hay una reserva total (dentro) dentro de la fecha a reservar.*/ " +
                "group by ar.idArticulo " +
                ") as ventas " +
                "using(idArticulo) where ae.idEstablecimiento = ?;";
        
        HashMap<Integer, Integer> map = new HashMap<>();

        try{
            PreparedStatement statement = DBConnector.con.prepareStatement(query);
            statement.setTimestamp(1, fechaReserva.getInicio());
            statement.setTimestamp(2, fechaReserva.getFin());
            statement.setTimestamp(3, fechaReserva.getInicio());
            statement.setTimestamp(4, fechaReserva.getFin());
            statement.setInt(5, idEstablecimiento);
            statement.execute();
            ResultSet set = statement.getResultSet();
            while (set.next()) map.put(set.getInt("idArticulo"), set.getInt("stock"));
            return map;
        }catch (SQLException e){
            System.out.println("[Error] " + e.getMessage());
        }
        return map;
    }
}
