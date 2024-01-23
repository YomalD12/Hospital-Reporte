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
public class Especialidades {
    protected String codigo, nombre;
    public Lista medicos;

    public Especialidades(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
        medicos = new Lista();
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Lista getMedicos() {
        return medicos;
    }
    
    public void insertarMedico(Medicos m){
        medicos.insertarNodo(m);
    }
    
    public void modificarMedico(Medicos m, Medicos mNuevo){
        medicos.modificarNodo(m, mNuevo);
    }
    
    public void eliminarMedico(String cod){
        Medicos m = buscarMedicos(cod);
        medicos.eliminarNodo(m);
    }
    
    public Medicos buscarMedicos(String ced){
         Nodo aux = medicos.primero;
        while (aux != null) {
            if (((Medicos) (aux.info)).getCédula().equals(ced)) {
                return ((Medicos)(aux.info));
            }
            aux = aux.siguiente;
        }
        return null;
    }
   
    
    public Medicos buscarMedicoApellidos(String apellido){
         Nodo aux = medicos.primero;
        while (aux != null) {
            if (((Medicos) (aux.info)).getApellido().equals(apellido)) {
                return ((Medicos)(aux.info));
            }
            aux = aux.siguiente;
        }
        return null;
    }
    
    
    

    
    
    
}
