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
public class Lista {

    public Nodo primero;
    public Nodo ultimo;
    public int nElementos;
    

    public Lista() {
        primero = null;
        ultimo = null;
        nElementos=0;
    }
    

    public void insertarNodo(Object info) {
        Nodo nodo = new Nodo();
        nodo.info = info;
        if (primero == null) {
            primero = nodo;
            primero.siguiente = null;
            ultimo = primero;
        } else {
            ultimo.siguiente = nodo;
            nodo.siguiente = null;
            ultimo = nodo;
        }
        nElementos++;
    }


    public Nodo buscarNodo(Object info) {
        Nodo actual = new Nodo();
        actual = primero;
        while (actual != null) {
            if (actual.info.equals(info)) {
                return actual;
            }
            actual = actual.siguiente;
        }
        return null;
    }

    public Nodo modificarNodo(Object info, Object nuevaInfo) {
        Nodo nodo = buscarNodo(info);
        if (nodo != null) {
            nodo.info = nuevaInfo;
            return nodo;
        }
        return null;
    }
    
    public void eliminarNodo(Object info){
        Nodo actual = new Nodo();
        Nodo anterior = new Nodo();
        actual = primero;
        anterior = null;
        while(actual != null){
            if (actual.info == info) {
                if (actual == primero) {
                    primero = primero.siguiente;
                }else{
                    anterior.siguiente = actual.siguiente;
                }
            }
            anterior = actual;
            actual = actual.siguiente;
        }
        nElementos--;
    }

}
