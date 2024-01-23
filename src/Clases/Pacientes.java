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
public class Pacientes extends Persona {

    public String direccion;
    public Lista citasMedicas;

    public Pacientes(String cédula, String nombre, String apellido, char género, String dir) {
        super(cédula, apellido, nombre, género);
        direccion = dir;
        citasMedicas = new Lista();
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void insertarCita(CitasMedicas cm) {
        citasMedicas.insertarNodo(cm);
    }

    public void modificarCita(CitasMedicas cm, CitasMedicas cmNueva) {
        citasMedicas.modificarNodo(cm, cmNueva);
    }

    public void eliminarCita(CitasMedicas cm) {
        citasMedicas.eliminarNodo(cm);
    }
    public CitasMedicas buscarCita(String codDoctor, String fecha, String hora, String estado){
        Nodo aux = citasMedicas.primero;
        while(aux!=null){
            if ( ((CitasMedicas)aux.info).getCedMedico().equals(codDoctor) && ((CitasMedicas)aux.info).getFecha().equals(fecha) && ((CitasMedicas)aux.info).getHora().equals(hora) && ((CitasMedicas)aux.info).getEstado().equals(estado)) {
                return (CitasMedicas)aux.info;
            }
        }
        return null;     
    }
}
