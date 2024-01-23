/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Clases;

/**
 *
 * @author 
 */
public class Persona {

    public String cédula, nombre, apellido;
    char género;

    public Persona(String cédula, String nombre, String apellido, char género) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.cédula = cédula;
        this.género = género;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getCédula() {
        return cédula;
    }

    public void setCédula(String cédula) {
        this.cédula = cédula;
    }

    public char getGénero() {
        return género;
    }

    public void setGénero(char género) {
        this.género = género;
    }

}
