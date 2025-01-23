package me.ivanmart.plaiaundi.Database;


import me.ivanmart.plaiaundi.Model.Establecimiento;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class EstablecimientoRepo {

    public static ArrayList<Establecimiento> getEstablecimientos(){
        ArrayList<Establecimiento> establecimientos = new ArrayList<>();
        try{
            String query = "select id, nombre from Establecimiento";
            Statement statement = DBConnector.con.createStatement();
            statement.execute(query);
            ResultSet rs = statement.getResultSet();
            while (rs.next()) establecimientos.add(new Establecimiento(rs.getInt("id"), rs.getString("nombre")));
            return establecimientos;
        }catch (SQLException e){
            System.out.printf("[Error] Ha habido un problema con la base de datos: %s%n", e.getMessage());
        }
        return establecimientos;
    }
}
