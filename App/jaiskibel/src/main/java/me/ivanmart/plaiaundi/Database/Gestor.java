package me.ivanmart.plaiaundi.Database;

import me.ivanmart.plaiaundi.Enums.Privilegio;
import me.ivanmart.plaiaundi.Enums.Sexo;
import me.ivanmart.plaiaundi.Menus.Util;
import me.ivanmart.plaiaundi.Model.Usuario;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Gestor {

    public static boolean checkPassword(String dni, String password){
        try{
            String query = "select contrasena from Usuario where dni = ?";
            PreparedStatement statement = DBConnector.con.prepareStatement(query);
            statement.setString(1, dni);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            if (rs.next()){
                return Password.check(password, rs.getString("contrasena"));
            }else return false;
        }catch (SQLException e){
            System.out.printf("[Error] Ha habido un problema con la base de datos: %s%n", e.getMessage());
        }
        return false;
    }

    public static Usuario getUsuario(String dni){
        System.out.println("3");
        try{
            String query = "select * from Usuario where dni = ?";
            PreparedStatement statement = DBConnector.con.prepareStatement(query);
            statement.setString(1, dni);
            statement.execute();
            System.out.println("4");
            ResultSet rs = statement.getResultSet();
            System.out.println("5");
            if (rs.next()){
                System.out.println("6");
                return new Usuario(dni, rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), Util.toEnum(Sexo.class, rs.getString("sexo")), Util.toEnum(Privilegio.class, rs.getString("privilegio")));
            }else return new Usuario();
        }catch (SQLException e){
            System.out.printf("[Error] Ha habido un problema con la base de datos: %s%n", e.getMessage());
        }
        return new Usuario();
    }

    public static boolean registrar(Usuario user, String pass){
        try{
            String query = "insert into Usuario values (?,?,?,?,?,?,?)";
            PreparedStatement statement = DBConnector.con.prepareStatement(query);
            statement.setString(1, user.getDNI());
            statement.setString(2, user.getNombre());
            statement.setString(3, user.getApellido1());
            statement.setString(4, user.getApellido2());
            statement.setString(5, user.getSexo().toString());
            statement.setString(6, Password.hash(pass));
            statement.setString(7, user.getPrivilegio().toString());
            statement.execute();
            return true;
        }catch (SQLException e){
            System.out.printf("[Error] Ha habido un problema con la base de datos: %s%n", e.getMessage());
        }
        return false;
    }
}
