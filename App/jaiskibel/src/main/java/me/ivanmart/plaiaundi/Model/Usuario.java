package me.ivanmart.plaiaundi.Model;

import me.ivanmart.plaiaundi.Enums.Sexo;
import me.ivanmart.plaiaundi.Enums.Privilegio;

public class Usuario {
    private String dni;
    private String nombre;
    private String apellido1;
    private String apellido2;
    private Sexo sexo;
    private Privilegio privilegio;

    public Usuario(String dni, String nombre, String apellido1, String apellido2, Sexo sexo, Privilegio privilegio) {
        this.dni = dni;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.sexo = sexo;
        this.privilegio = privilegio;
    }

    public Usuario(){}

    public Privilegio getPrivilegio() {
        return privilegio;
    }

    public String getDNI() {
        return dni;
    }

    public String getNombre() {
        return nombre;
    }

    public String getApellido1() {
        return apellido1;
    }

    public String getApellido2() {
        return apellido2;
    }

    public Sexo getSexo() {
        return sexo;
    }

    public String[] getDataArray(){
        return new String[]{String.valueOf(this.getDNI()), this.getNombre(), this.getApellido1(), this.getApellido2(), this.getSexo().toString(), this.getPrivilegio().toString()};
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "dni='" + dni + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido1='" + apellido1 + '\'' +
                ", apellido2='" + apellido2 + '\'' +
                ", sexo=" + sexo +
                ", privilegio=" + privilegio +
                '}';
    }
}
