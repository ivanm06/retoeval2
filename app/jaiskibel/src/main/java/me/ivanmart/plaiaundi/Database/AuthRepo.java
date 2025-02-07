package me.ivanmart.plaiaundi.Database;

import me.ivanmart.plaiaundi.Enums.Privilegio;
import me.ivanmart.plaiaundi.Enums.Sexo;
import me.ivanmart.plaiaundi.Utils.MenuUtil;
import me.ivanmart.plaiaundi.Model.Usuario;
import me.ivanmart.plaiaundi.Utils.Password;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

public class AuthRepo {

    public static boolean checkPassword(String dni, String password){
        try{
            String query = "select contrasena from Usuario where dni = ?";
            PreparedStatement statement = DBConnector.con.prepareStatement(query);
            statement.setString(1, dni);
            statement.execute();
            ResultSet rs = statement.getResultSet();

            if (rs.next()) return Password.check(password, rs.getString("contrasena"));
            else return false;
        }catch (SQLException e){
            System.out.printf("[Error] Ha habido un problema con la base de datos: %s%n", e.getMessage());
        }
        return false;
    }

    public static Usuario getUsuario(String dni){
        try{
            String query = "select * from Usuario where dni = ?";
            PreparedStatement statement = DBConnector.con.prepareStatement(query);
            statement.setString(1, dni);
            statement.execute();
            ResultSet rs = statement.getResultSet();
            if (rs.next()){
                Sexo sexo = MenuUtil.toEnum(Sexo.class, rs.getString("sexo"), Sexo.M); // Guardar sexo ususario
                Privilegio privilegio = MenuUtil.toEnum(Privilegio.class, rs.getString("privilegio"), Privilegio.CLIENTE);// Guardar privilegio del usuario
                return new Usuario(dni, rs.getString("nombre"), rs.getString("apellido1"), rs.getString("apellido2"), sexo, privilegio);
            }else return new Usuario();
        }catch (SQLException e){
            System.out.printf("[Error] Ha habido un problema con la base de datos: %s%n", e.getMessage());
        }
        return new Usuario();
    }

    public static boolean registrar(Usuario user, String pass){ // Guardar el usuario en la base de datos
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

    public static ArrayList<Usuario> getUsuarios(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        String query = "select dni, nombre, apellido1, apellido2, sexo, privilegio from Usuario";
        try{
            Statement statement = DBConnector.con.createStatement();
            statement.execute(query);
            ResultSet set = statement.getResultSet();
            while (set.next()) usuarios.add( new Usuario(
                set.getString("dni"),
                    set.getString("nombre"),
                    set.getString("apellido1"),
                    set.getString("apellido2"),
                    MenuUtil.toEnum(Sexo.class, set.getString("sexo"), Sexo.M),
                    MenuUtil.toEnum(Privilegio.class, set.getString("privilegio"), Privilegio.CLIENTE)

            ));
            return usuarios;
        }catch (SQLException e){
            System.out.println("[Error] " + e.getMessage());
        }
        return usuarios;
    }

    public static void eliminarUsuario(String dni) {
        String query = "DELETE FROM Usuario WHERE dni = ?";
        try (PreparedStatement statement = DBConnector.con.prepareStatement(query)) {
            // Establecer el parámetro del DNI
            statement.setString(1, dni);

            // Ejecutar la consulta
            int filasAfectadas = statement.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Usuario con DNI " + dni + " ha sido eliminado correctamente.");
            } else {
                if(!dni.equals("0")){
                    System.out.println("[Info] No se encontró ningún usuario con el DNI " + dni);
                }
            }
        } catch (SQLException e) {
            System.out.printf("[Error] Ha habido un problema con la base de datos: %s%n", e.getMessage());
        }
    }

}
