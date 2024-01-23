/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Clases.CitasMedicas;
import Clases.Clinica;
import Clases.Especialidades;
import Clases.Medicos;
import Clases.Nodo;
import Clases.Pacientes;
import java.awt.event.KeyEvent;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author
 */
public class Interfaz_Reporte_Individual extends javax.swing.JInternalFrame {

    /**
     * Creates new form Interfaz_Reporte_Individual
     */
    Clinica cl;

    public Interfaz_Reporte_Individual() {
        initComponents();
        cl = new Clinica();
        model = new DefaultTableModel();
        txtApellido.setEnabled(false);
        txtNombre.setEnabled(false);
        columnas();
    }

    DefaultTableModel model;

    public Interfaz_Reporte_Individual(Clinica c) {
        initComponents();
        cl = new Clinica();
        cl = c;
        model = new DefaultTableModel();
        txtApellido.setEnabled(false);
        txtNombre.setEnabled(false);
        columnas();
    }

    private void columnas() {
        model = new DefaultTableModel();
        model.addColumn("Especialidad");
        model.addColumn("Doctor");
        model.addColumn("Fecha");
        model.addColumn("Hora");
        model.addColumn("Estado");
        jtbReportes.setModel(model);
    }

    public void citasMedicas() {
        columnas();
        // recorrer lista de especialidad
        Nodo aux1 = cl.espcLista.primero; // de la primera especialidad recorrer la lista de medicos
        while (aux1 != null) {
            Nodo aux2 = ((Especialidades) (aux1.info)).medicos.primero; // de la lista medicos buscar al medico que atendio a ese paciente
            while (aux2 != null) {
                Pacientes p = cl.buscarPaciente(txtCedulaPaciente.getText());
                Nodo aux3 = p.citasMedicas.primero;// recorrer la lista de citas que ha tenido el paciente
                while (aux3 != null) {
                    if (((Medicos) (aux2.info)).getCédula().equals(((CitasMedicas) (aux3.info)).getCedMedico())) {
                        Object[] fila = {((Especialidades) (aux1.info)).getNombre(), ((Medicos) (aux2.info)).getNombre() + " " + ((Medicos) (aux2.info)).getApellido(), ((CitasMedicas) (aux3.info)).getFecha(), ((CitasMedicas) (aux3.info)).getHora(), ((CitasMedicas) (aux3.info)).getEstado()};
                        model.addRow(fila);
                    }
                    aux3 = aux3.siguiente;
                }
                aux2 = aux2.siguiente;
            }
            aux1 = aux1.siguiente;
        }
        jtbReportes.setModel(model);
    }

    public void citasMedicasSolicitadas() {
        columnas();
        // recorrer lista de especialidad
        Nodo aux1 = cl.espcLista.primero; // de la primera especialidad recorrer la lista de medicos
        while (aux1 != null) {
            Nodo aux2 = ((Especialidades) (aux1.info)).medicos.primero; // de la lista medicos buscar al medico que atendio a ese paciente
            while (aux2 != null) {
                Pacientes p = cl.buscarPaciente(txtCedulaPaciente.getText());
                Nodo aux3 = p.citasMedicas.primero;// recorrer la lista de citas que ha tenido el paciente
                while (aux3 != null) {
                    if (((Medicos) (aux2.info)).cédula.equals(((CitasMedicas) (aux3.info)).getCedMedico()) && ((CitasMedicas) (aux3.info)).getEstado().equals("Solicitada")) {
                        Object[] fila = {((Especialidades) (aux1.info)).getNombre(), ((Medicos) (aux2.info)).getNombre() + " " + ((Medicos) (aux2.info)).getApellido(), ((CitasMedicas) (aux3.info)).getFecha(), ((CitasMedicas) (aux3.info)).getHora(), ((CitasMedicas) (aux3.info)).getEstado()};
                        model.addRow(fila);
                    }
                    aux3 = aux3.siguiente;
                }
                aux2 = aux2.siguiente;
            }
            aux1 = aux1.siguiente;
        }
        jtbReportes.setModel(model);
    }

    public void citasMedicasCobradas() {
        columnas();
        // recorrer lista de especialidad
        Nodo aux1 = cl.espcLista.primero; // de la primera especialidad recorrer la lista de medicos
        while (aux1 != null) {
            Nodo aux2 = ((Especialidades) (aux1.info)).medicos.primero; // de la lista medicos buscar al medico que atendio a ese paciente
            while (aux2 != null) {
                Pacientes p = cl.buscarPaciente(txtCedulaPaciente.getText());
                Nodo aux3 = p.citasMedicas.primero;// recorrer la lista de citas que ha tenido el paciente
                while (aux3 != null) {
                    if (((Medicos) (aux2.info)).cédula.equals(((CitasMedicas) (aux3.info)).getCedMedico()) && ((CitasMedicas) (aux3.info)).getEstado().equals("Cobrada")) {
                        Object[] fila = {((Especialidades) (aux1.info)).getNombre(), ((Medicos) (aux2.info)).getNombre() + " " + ((Medicos) (aux2.info)).getApellido(), ((CitasMedicas) (aux3.info)).getFecha(), ((CitasMedicas) (aux3.info)).getHora(), ((CitasMedicas) (aux3.info)).getEstado()};
                        model.addRow(fila);
                    }
                    aux3 = aux3.siguiente;
                }
                aux2 = aux2.siguiente;
            }
            aux1 = aux1.siguiente;
        }
        jtbReportes.setModel(model);
    }

    public void citasMedicasPerdidas() {
        columnas();
        // recorrer lista de especialidad
        Nodo aux1 = cl.espcLista.primero; // de la primera especialidad recorrer la lista de medicos
        while (aux1 != null) {
            Nodo aux2 = ((Especialidades) (aux1.info)).medicos.primero; // de la lista medicos buscar al medico que atendio a ese paciente
            while (aux2 != null) {
                Pacientes p = cl.buscarPaciente(txtCedulaPaciente.getText());
                Nodo aux3 = p.citasMedicas.primero;// recorrer la lista de citas que ha tenido el paciente
                while (aux3 != null) {
                    if (((Medicos) (aux2.info)).cédula.equals(((CitasMedicas) (aux3.info)).getCedMedico()) && ((CitasMedicas) (aux3.info)).getEstado().equals("Perdida")) {
                        Object[] fila = {((Especialidades) (aux1.info)).getNombre(), ((Medicos) (aux2.info)).getNombre() + " " + ((Medicos) (aux2.info)).getApellido(), ((CitasMedicas) (aux3.info)).getFecha(), ((CitasMedicas) (aux3.info)).getHora(), ((CitasMedicas) (aux3.info)).getEstado()};
                        model.addRow(fila);
                    }
                    aux3 = aux3.siguiente;
                }
                aux2 = aux2.siguiente;
            }
            aux1 = aux1.siguiente;
        }
        jtbReportes.setModel(model);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtCedulaPaciente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbReportes = new javax.swing.JTable();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximizable(true);

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jButton1.setText("Ver Citas Médicas");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setText("Citas Solicitadas");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setText("Citas Cobradas");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setText("Citas Perdidas");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jButton1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton4)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel2.setText("Cédula Paciente:");

        txtCedulaPaciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtCedulaPacienteActionPerformed(evt);
            }
        });
        txtCedulaPaciente.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txtCedulaPacienteKeyPressed(evt);
            }
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtCedulaPacienteKeyReleased(evt);
            }
        });

        jLabel3.setText("Nombre:");

        jLabel4.setText("Apellido:");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2)
                    .addComponent(jLabel3)
                    .addComponent(jLabel4))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(txtCedulaPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtApellido, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(txtNombre))
                .addContainerGap(153, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtCedulaPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jtbReportes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtbReportes);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 151, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Reporte por Persona");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jLabel1)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txtCedulaPacienteKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaPacienteKeyReleased
        // TODO add your handling code here
    }//GEN-LAST:event_txtCedulaPacienteKeyReleased

    private void txtCedulaPacienteKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtCedulaPacienteKeyPressed
        // TODO add your handling code here:
        if (evt.getKeyCode() == KeyEvent.VK_ENTER) {
            
            Pacientes p = cl.buscarPaciente(txtCedulaPaciente.getText());
            if (p != null) {
                txtNombre.setText(p.getApellido());
                txtApellido.setText(p.getNombre());

            } else {
                JOptionPane.showMessageDialog(null, "Paciente no Registrado");
                txtCedulaPaciente.requestFocus();
            }
        }
    }//GEN-LAST:event_txtCedulaPacienteKeyPressed

    private void txtCedulaPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaPacienteActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
        citasMedicas();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
        citasMedicasSolicitadas();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // TODO add your handling code here:
        citasMedicasCobradas();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // TODO add your handling code here:
        citasMedicasPerdidas();
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Reporte_Individual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Reporte_Individual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Reporte_Individual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Reporte_Individual.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz_Reporte_Individual().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jtbReportes;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCedulaPaciente;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
