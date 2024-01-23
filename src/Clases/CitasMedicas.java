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
public class CitasMedicas {
    protected String cedMedico, hora, estado;
    protected String fecha;

    public CitasMedicas(String cedMedico, String hora, String estado, String fecha) {
        this.cedMedico = cedMedico;
        this.hora = hora;
        this.estado = estado;
        this.fecha = fecha;
    }
    
    

    public String getCedMedico() {
        return cedMedico;
    }

    public void setCedMedico(String cedMedico) {
        this.cedMedico = cedMedico;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    } 
}
