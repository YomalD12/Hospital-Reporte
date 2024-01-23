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
public class Clinica {

    public Lista espcLista;
    public Lista pacLista;

    public Clinica() {
        this.espcLista = new Lista();
        this.pacLista = new Lista();
    }

    public void insertarEspecialidad(Especialidades e) {
        espcLista.insertarNodo(e);
    }

    public void insertarPaciente(Pacientes p) {
        pacLista.insertarNodo(p);
    }

    public void modificarEspecialidad(Especialidades e, Especialidades eNueva) {
        espcLista.modificarNodo(e, eNueva);
    }

    public void modificarPaciente(Pacientes p, Pacientes pNueva) {
        pacLista.modificarNodo(p, pNueva);
    }

    public void eliminarEspecialidad(String cod) {
        Especialidades e = buscarEspecialidad(cod);
        espcLista.eliminarNodo(e);
    }

    public void eliminarPaciente(String cod) {
        Pacientes p = buscarPaciente(cod);
        pacLista.eliminarNodo(p);
    }
    
    public Pacientes buscarPaciente(String cod){
        Nodo aux = pacLista.primero;
        while (aux != null) {
            if (((Pacientes) (aux.info)).getCédula().equals(cod)) {
                return ((Pacientes)(aux.info));
            }
            aux = aux.siguiente;
        }
        return null;
    }
    
    public Especialidades buscarEspecialidad(String cod){
        Nodo aux = espcLista.primero;
        while (aux != null) {
            if (((Especialidades) (aux.info)).getCodigo().equals(cod)) {
                return ((Especialidades)(aux.info));
            }
            aux = aux.siguiente;
        }
        return null;
    }
    
    
    public Especialidades buscarEspecialidadNombre(String nombre){
        Nodo aux = espcLista.primero;
        while (aux != null) {
            if (((Especialidades) (aux.info)).getNombre().equals(nombre)) {
                return ((Especialidades)(aux.info));
            }
            aux = aux.siguiente;
        }
        return null;
    }
    
    
}
