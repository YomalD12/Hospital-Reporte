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
public class Medicos extends Persona{
    protected String codEspecialidad;

    public Medicos(String cédula, String apellido, String nombre, char género, String codEspecialidad) {
        super(cédula, apellido, nombre, género);
        this.codEspecialidad = codEspecialidad;
    }

    public String getCodEspecialidad() {
        return codEspecialidad;
    }

    public void setCodEspecialidad(String codEspecialidad) {
        this.codEspecialidad = codEspecialidad;
    }
    
    
}
