/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Interfaces;

import Clases.CitasMedicas;
import Clases.Clinica;
import Clases.Especialidades;
import Clases.Lista;
import Clases.Medicos;
import Clases.Nodo;
import Clases.Pacientes;
import java.awt.event.KeyEvent;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JOptionPane;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author
 */
public class Interfaz_Citas_Medicas extends javax.swing.JInternalFrame {

    Clinica cl;
    DefaultTableModel model;

    /**
     * Creates new form Interfaz_Citas_Medicas
     */
    public Interfaz_Citas_Medicas() {
        initComponents();
        cl = new Clinica();
        model = new DefaultTableModel();
        txtApellido.setEnabled(false);
        txtNombre.setEnabled(false);
        jdcFecha.getDateEditor().setEnabled(false);
        cargarDatosEspecialidades();
        cargarHorasCombo();
        columnas();
        cargar();
    }

    
    public Interfaz_Citas_Medicas(Clinica c) {
        initComponents();
        cl = new Clinica();
        cl = c;
        model = new DefaultTableModel();
        txtApellido.setEnabled(false);
        txtNombre.setEnabled(false);
        jdcFecha.getDateEditor().setEnabled(false);
        cargarDatosEspecialidades();
        cargarHorasCombo();
        columnas();
        cargar();
    }

    private void columnas() {
        model.addColumn("Cédula Pac.");
        model.addColumn("Nombre Pac.");
        model.addColumn("Apellido Pac.");
        model.addColumn("Fecha");
        model.addColumn("Especialidad");
        model.addColumn("Doctor");
        model.addColumn("Hora");
        model.addColumn("Estado");
        jtbCitasMedicas.setModel(model);
    }

    private void cargarDatosMedicosEspecialidad(String codEspecialidad) {
        DefaultComboBoxModel mode = new DefaultComboBoxModel();
        mode.addElement("Seleccione...");
        Especialidades e = cl.buscarEspecialidad(codEspecialidad);
        if (e != null) {
            Nodo actual = e.medicos.primero;
            while (actual != null) {
                mode.addElement(((Medicos) actual.info).apellido);
                actual = actual.siguiente;
            }
            jcbMedico.setModel(mode);
        } else {
            JOptionPane.showMessageDialog(null, "No existen medicos en esa especialidad");
        }

    }

    private void cargarDatosEspecialidades() {
        Nodo actual = cl.espcLista.primero;
        DefaultComboBoxModel mode = new DefaultComboBoxModel();
        mode.addElement("Seleccione...");
        while (actual != null) {
            Especialidades c = new Especialidades(((Especialidades) (actual.info)).getCodigo(), ((Especialidades) (actual.info)).getNombre());
            mode.addElement(c.getCodigo());
            actual = actual.siguiente;
        }
        jcbEspecialidad.setModel(mode);
    }

    public void cargarHorasCombo() {
        DefaultComboBoxModel mode = new DefaultComboBoxModel();
        mode.addElement("Seleccione...");
        mode.addElement("09:00");
        mode.addElement("10:00");
        mode.addElement("11:00");
        mode.addElement("12:00");
        mode.addElement("13:00");
        mode.addElement("14:00");
        jcbHora.setModel(mode);
    }

    public void cargarHoras() {
        if (jcbEspecialidad.getSelectedItem().equals("Seleccione...")) {

        } else {
            DefaultComboBoxModel mode = (DefaultComboBoxModel) jcbHora.getModel();
            Lista l = horasDisponible();
            if (l != null) {
                Nodo actual = l.primero;
                while (actual != null) {
                    for (int i = 0; i < mode.getSize(); i++) {
                        if (actual.info.equals(mode.getElementAt(i))) {
                            mode.removeElementAt(i);
                        }
                    }
                    actual = actual.siguiente;
                }
                jcbHora.setModel(mode);

            } else {
                cargarHorasCombo();
            }
        }

    }

    public Lista horasDisponible() {
        Lista horasDisponibles = new Lista();
        SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
        String fecha = "";
        if (jdcFecha.getDate() != null) {
            fecha = df.format(jdcFecha.getDate());
        } else {
            fecha = "01/dic/2018";
        }
        Especialidades e = cl.buscarEspecialidad(jcbEspecialidad.getSelectedItem().toString());
        Medicos m = null;
        if (e != null) {
            m = e.buscarMedicoApellidos(jcbMedico.getSelectedItem().toString());
        }
        //RECORRER LOS PACIENTES
        Nodo actual = cl.pacLista.primero;
        while (actual != null) {
            Pacientes p = cl.buscarPaciente(((Pacientes) actual.info).getCédula());
            Nodo aux = p.citasMedicas.primero;
            while (aux != null) {
                if (((CitasMedicas) aux.info).getFecha().equals(fecha)) {
                    if (((CitasMedicas) aux.info).getCedMedico().equals(m.getCédula())) {
                        horasDisponibles.insertarNodo(((CitasMedicas) aux.info).getHora());
                    }
                }
                aux = aux.siguiente;
            }
            actual = actual.siguiente;
        }
        return horasDisponibles;
    }

    public void limpiar() {
        txtCedulaPaciente.setText("");
        txtNombre.setText("");
        txtApellido.setText("");
        jdcFecha.setDate(null);
        jcbEspecialidad.setSelectedIndex(0);
        //jcbMedico.setSelectedIndex(0);
        jcbHora.setSelectedIndex(0);
        jcbEstado.setSelectedIndex(0);
        jtbCitasMedicas.clearSelection();

    }

    private void registrar() {
        Pacientes p = cl.buscarPaciente(txtCedulaPaciente.getText());
        Especialidades e = cl.buscarEspecialidad(jcbEspecialidad.getSelectedItem().toString());
        Medicos m = e.buscarMedicoApellidos(jcbMedico.getSelectedItem().toString());
        SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
        String fecSalida = df.format(jdcFecha.getDate());
        CitasMedicas cm = new CitasMedicas(m.getCédula(), jcbHora.getSelectedItem().toString(), jcbEstado.getSelectedItem().toString(), fecSalida);
        p.insertarCita(cm);
        Object[] fila = {p.getCédula(), p.getNombre(), p.getApellido(), cm.getFecha(), e.getNombre(), m.getApellido(), cm.getHora(), cm.getEstado()};
        model.addRow(fila);
        jtbCitasMedicas.setModel(model);
    }

    private void eliminar() {
        int n = jtbCitasMedicas.getSelectedRow();
        if (n != -1) {
            Pacientes p = cl.buscarPaciente(txtCedulaPaciente.getText());
            Especialidades e = cl.buscarEspecialidadNombre(jtbCitasMedicas.getValueAt(n, 4).toString());
            Medicos m = e.buscarMedicoApellidos(jtbCitasMedicas.getValueAt(n, 5).toString());
            CitasMedicas cm = p.buscarCita(m.getCédula(), jtbCitasMedicas.getValueAt(n, 3).toString(), jtbCitasMedicas.getValueAt(n, 6).toString(), jtbCitasMedicas.getValueAt(n, 7).toString());
            p.eliminarCita(cm);
            model.removeRow(n);
            jtbCitasMedicas.setModel(model);
            limpiar();
        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una cita para cancelar");
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtCedulaPaciente = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        txtApellido = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jcbEspecialidad = new javax.swing.JComboBox();
        jLabel6 = new javax.swing.JLabel();
        jcbMedico = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        jcbHora = new javax.swing.JComboBox();
        jLabel8 = new javax.swing.JLabel();
        jcbEstado = new javax.swing.JComboBox();
        jLabel9 = new javax.swing.JLabel();
        jdcFecha = new com.toedter.calendar.JDateChooser();
        jPanel3 = new javax.swing.JPanel();
        btnGuardar = new javax.swing.JButton();
        CancelarCita = new javax.swing.JButton();
        btnModificar = new javax.swing.JButton();
        btnCancelar = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jtbCitasMedicas = new javax.swing.JTable();

        setClosable(true);
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMaximizable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jLabel1.setFont(new java.awt.Font("Verdana", 1, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Citas Médicas");

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

        jLabel5.setText("Especialidad:");

        jcbEspecialidad.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione..." }));
        jcbEspecialidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbEspecialidadActionPerformed(evt);
            }
        });

        jLabel6.setText("Médico:");

        jcbMedico.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione..." }));
        jcbMedico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jcbMedicoActionPerformed(evt);
            }
        });

        jLabel7.setText("Hora:");

        jcbHora.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione...", "09:00", "10:00", "11:00", "12:00", "13:00", "14:00" }));

        jLabel8.setText("Estado:");

        jcbEstado.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Seleccione...", "Solicitada", "Cobrada", "Perdida" }));

        jLabel9.setText("Fecha:");

        jdcFecha.setDateFormatString("dd/MMM/yyyy");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel4)
                            .addComponent(jLabel9)
                            .addComponent(jLabel6)
                            .addComponent(jLabel7)
                            .addComponent(jLabel8))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jcbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, 96, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbMedico, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtCedulaPaciente, javax.swing.GroupLayout.PREFERRED_SIZE, 101, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtApellido, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 130, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 161, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, 140, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jcbHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel9)
                    .addComponent(jdcFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(jcbEspecialidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(jcbMedico, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel7)
                    .addComponent(jcbHora, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel8)
                    .addComponent(jcbEstado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        btnGuardar.setText("Guardar");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        CancelarCita.setText("Cancelar Cita");
        CancelarCita.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CancelarCitaActionPerformed(evt);
            }
        });

        btnModificar.setText("Modificar");
        btnModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnModificarActionPerformed(evt);
            }
        });

        btnCancelar.setText("Cancelar");
        btnCancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCancelarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(CancelarCita))
                    .addComponent(btnModificar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btnGuardar, javax.swing.GroupLayout.PREFERRED_SIZE, 97, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(btnCancelar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnGuardar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnModificar)
                .addGap(10, 10, 10)
                .addComponent(CancelarCita)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnCancelar)
                .addContainerGap())
        );

        jtbCitasMedicas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(jtbCitasMedicas);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 638, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 161, Short.MAX_VALUE)
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
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
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

    private void jcbEspecialidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbEspecialidadActionPerformed
        // TODO add your handling code here:
        if (jcbEspecialidad.getSelectedItem().equals("Seleccione...")) {
            jcbMedico.setSelectedItem("Seleccione...");
        } else {
            cargarDatosMedicosEspecialidad(jcbEspecialidad.getSelectedItem().toString());
            cargarHorasCombo();
        }

    }//GEN-LAST:event_jcbEspecialidadActionPerformed

    private void jcbMedicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jcbMedicoActionPerformed
        // TODO add your handling code here:
        cargarHoras();
        //cargarHorasCombo();
    }//GEN-LAST:event_jcbMedicoActionPerformed

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        // TODO add your handling code here:
        registrar();
        cargarHorasCombo();
        limpiar();
    }//GEN-LAST:event_btnGuardarActionPerformed

    private void btnCancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCancelarActionPerformed
        // TODO add your handling code here:
        activarCombos();
        limpiar();
    }//GEN-LAST:event_btnCancelarActionPerformed

    private void CancelarCitaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CancelarCitaActionPerformed
        // TODO add your handling code here:
        eliminar();
        activarCombos();
    }//GEN-LAST:event_CancelarCitaActionPerformed

    private void btnModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnModificarActionPerformed
        // TODO add your handling code here:
        modificar();
        jtbCitasMedicas.clearSelection();
        limpiar();

    }//GEN-LAST:event_btnModificarActionPerformed

    private void txtCedulaPacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtCedulaPacienteActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtCedulaPacienteActionPerformed

    public void modificar() {
        int n = jtbCitasMedicas.getSelectedRow();
        if (n != -1) {
            Pacientes p = cl.buscarPaciente(jtbCitasMedicas.getValueAt(n, 0).toString());
            Especialidades e = cl.buscarEspecialidadNombre(jtbCitasMedicas.getValueAt(n, 4).toString());
            Medicos m = e.buscarMedicoApellidos(jtbCitasMedicas.getValueAt(n, 5).toString());
            
            CitasMedicas cm = p.buscarCita(m.getCédula(), jtbCitasMedicas.getValueAt(n, 3).toString(), jtbCitasMedicas.getValueAt(n, 6).toString(), jtbCitasMedicas.getValueAt(n, 7).toString());
            SimpleDateFormat df = new SimpleDateFormat("dd/MMM/yyyy");
            String fecSalida = df.format(jdcFecha.getDate());
            CitasMedicas cmNueva = new CitasMedicas(m.getCédula(), jtbCitasMedicas.getValueAt(n, 6).toString(), jcbEstado.getSelectedItem().toString(), fecSalida);
            p.modificarCita(cm, cmNueva);
            model.setValueAt(p.getCédula(), n, 0);
            model.setValueAt(p.getNombre(), n, 1);
            model.setValueAt(p.getApellido(), n, 2);
            model.setValueAt(cmNueva.getFecha(), n, 3);
            model.setValueAt(e.getNombre(), n, 4);
            model.setValueAt(m.getApellido(), n, 5);
            model.setValueAt(cm.getHora(), n, 6);
            model.setValueAt(cmNueva.getEstado(), n, 7);
            jtbCitasMedicas.clearSelection();

        } else {
            JOptionPane.showMessageDialog(null, "Seleccione una cita");
        }
    }

    public void cargar() {
        jtbCitasMedicas.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                int n = jtbCitasMedicas.getSelectedRow();
                if (n != -1) {
                    Pacientes p = cl.buscarPaciente(jtbCitasMedicas.getValueAt(n, 0).toString());
                    txtCedulaPaciente.setText(p.getCédula());
                    txtNombre.setText(p.getNombre());
                    txtApellido.setText(p.getApellido());
                    Date s;
                    try {
                        s = new SimpleDateFormat("dd/MMM/yyyy").parse(jtbCitasMedicas.getValueAt(n, 3).toString());
                        jdcFecha.setDate(s);
                    } catch (ParseException ex) {
                        System.out.println(ex.getMessage());
                    }
                    //Especialidades es = cl.buscarEspecialidadNombre(jtbCitasMedicas.getValueAt(n, 4).toString().trim());
                    //jcbEspecialidad.setSelectedItem(es.getCodigo());
                    jcbEspecialidad.setEnabled(false);
                    //Medicos m = es.buscarMedicoApellidos(jtbCitasMedicas.getValueAt(n, 5).toString());
                    ///cargarDatosMedicosEspecialidad(es.getNombre());
                    //jcbMedico.setSelectedItem(m.getApellido());
                    jcbMedico.setEnabled(false);
                    jcbHora.setEnabled(false);
                    jcbEstado.setSelectedItem(jtbCitasMedicas.getValueAt(n, 7).toString());
                }
            }
        });
    }

    public void activarCombos() {
        jcbEspecialidad.setEnabled(true);
        jcbMedico.setEnabled(true);
        jcbHora.setEnabled(true);
    }

    /*public CitasMedicas buscarCita(){
     int n = 
     return;
        
     }*/
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
            java.util.logging.Logger.getLogger(Interfaz_Citas_Medicas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Citas_Medicas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Citas_Medicas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Interfaz_Citas_Medicas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Interfaz_Citas_Medicas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CancelarCita;
    private javax.swing.JButton btnCancelar;
    private javax.swing.JButton btnGuardar;
    private javax.swing.JButton btnModificar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JComboBox jcbEspecialidad;
    private javax.swing.JComboBox jcbEstado;
    private javax.swing.JComboBox jcbHora;
    private javax.swing.JComboBox jcbMedico;
    private com.toedter.calendar.JDateChooser jdcFecha;
    private javax.swing.JTable jtbCitasMedicas;
    private javax.swing.JTextField txtApellido;
    private javax.swing.JTextField txtCedulaPaciente;
    private javax.swing.JTextField txtNombre;
    // End of variables declaration//GEN-END:variables
}
