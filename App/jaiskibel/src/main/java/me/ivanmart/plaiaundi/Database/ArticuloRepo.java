package me.ivanmart.plaiaundi.Database;

import me.ivanmart.plaiaundi.Model.Articulo;
import me.ivanmart.plaiaundi.Model.Ski;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class ArticuloRepo {

    public static ArrayList<Articulo> getSkis(int idEstablecimiento){
        ArrayList<Articulo> articulos = new ArrayList<>();
        try{
            String query = "select a.id, a.nombre from Articulo a join articuloEstablecimiento ae on ae.idArticulo = a.id where ae.idEstablecimiento = ?";
            PreparedStatement statement = DBConnector.con.prepareStatement(query);
            statement.setInt(1, idEstablecimiento);
            System.out.println(idEstablecimiento);
            System.out.println(query.replace('?', (char) idEstablecimiento));
            statement.execute();
            ResultSet rs = statement.getResultSet();
            while (rs.next()) articulos.add(new Ski(rs.getInt("id"), rs.getString("nombre")));
            return articulos;
        }catch (SQLException e){
            System.out.printf("[Error] Ha habido un problema con la base de datos: %s%n", e.getMessage());
        }
        return articulos;
    }
}
