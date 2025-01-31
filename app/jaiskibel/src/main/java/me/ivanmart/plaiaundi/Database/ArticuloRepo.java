package me.ivanmart.plaiaundi.Database;

import me.ivanmart.plaiaundi.Enums.Modalidad;
import me.ivanmart.plaiaundi.Enums.Nivel;
import me.ivanmart.plaiaundi.Enums.Talla;
import me.ivanmart.plaiaundi.Utils.MenuUtil;
import me.ivanmart.plaiaundi.Model.Accesorio;
import me.ivanmart.plaiaundi.Model.Articulo;
import me.ivanmart.plaiaundi.Model.Ski;
import me.ivanmart.plaiaundi.Model.Snowboard;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
                    MenuUtil.toEnum(Nivel.class, rs.getString("nivel"), Nivel.PRINCIPIANTE)
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
                    MenuUtil.toEnum(Modalidad.class, rs.getString("modalidad"), Modalidad.PISTA)
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
                    rs.getString("tipo")
            ));
            return articulos;
        }catch (SQLException e){
            System.out.printf("[Error] Ha habido un problema con la base de datos: %s%n", e.getMessage());
        }
        return articulos;
    }

    public static HashMap<Integer, Integer> getStock(int idEstablecimiento){
        String query = "select ae.idArticulo, ae.cantidad - sum(a.cantidad) as stock  from articuloEstablecimiento ae join articuloReservado a using(idArticulo) where idEstablecimiento = ? group by idArticulo";
        HashMap<Integer, Integer> map = new HashMap<>();

        try{
            PreparedStatement statement = DBConnector.con.prepareStatement(query);
            statement.setInt(1, idEstablecimiento);
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
