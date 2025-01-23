package me.ivanmart.plaiaundi.Database;

import me.ivanmart.plaiaundi.Menus.AuthMenu;
import me.ivanmart.plaiaundi.Model.ArticuloReserva;
import me.ivanmart.plaiaundi.Model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class ReservaRepo {

    public static boolean reservar(ArrayList<ArticuloReserva> articulos){
        Usuario user = AuthMenu.getUsuario();
        int idReserva = 1;

        try{
            String query = "insert into Reserva (DNIUsuario, fechaFin) values (?, CURRENT_TIMESTAMP + INTERVAL ? DAY);";
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
            String query2 = "insert into ArticuloReservado values";

            boolean first = true;
            for (ArticuloReserva a : articulos) {
                query2 += (first ? "" : ",") +" (%d, %d, %d)".formatted(idReserva, a.getId(), a.getCantidad());
                if (first) first = false;
            }
            System.out.println(query2);
            Statement statement = DBConnector.con.createStatement();
            statement.execute(query2);
            return true;
        }catch (SQLException e){
            System.out.printf("[Error] Ha habido un problema con la base de datos: %s%n", e.getMessage());
            return false;

        }

    }
}