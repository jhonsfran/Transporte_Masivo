/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import DAO.Conexion;
import DAO.DAOEmpleadoImpl;
import Mapeo.Empleado;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JOptionPane;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

/**
 *
 * @author Univalle_F
 */
public class CrudEmpleado extends javax.swing.JFrame{

    /**
     * Creates new form CrudEmpleado
     */
    public CrudEmpleado() {
        initComponents();
    }
    
    public boolean validaCampos(String empleado_id, String empleado_nombre, String empleado_telefono, String empleado_sueldo){
        
        boolean valida = false;
        
        if (empleado_id.equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor dígite la identificación del empleado");
        } else if (empleado_nombre.equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor dígite el nombre del empleado");
        } else if (empleado_telefono.equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor dígite el teléfono del empleado");
        } else if (empleado_sueldo.equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor dígite el sueldo del empleado");
        } else if (!(empleado_sueldo).matches("[0-9]*")) {
            JOptionPane.showMessageDialog(null, "El dato sueldo debe ser un número");
        } else {
            valida = true;
        }
        
        return valida;
    }
    
    public void setCampos() {

        empleado_id.setText("");
        empleado_nombre.setText("");
        empleado_telefono.setText("");
        empleado_sueldo.setText("");

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        crudEmpleado = new javax.swing.JPanel();
        empleado_id = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        empleado_nombre = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        empleado_telefono = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        empleado_sueldo = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        empleado_rol = new javax.swing.JComboBox();
        CrearEmpleado = new javax.swing.JButton();
        consultarEmpleado = new javax.swing.JButton();
        eliminarEmpleado = new javax.swing.JButton();
        ListarEmpleados = new javax.swing.JButton();
        salir = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        empleado_id.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empleado_idActionPerformed(evt);
            }
        });

        jLabel1.setText("Identificacion");

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel2.setText("EMPLEADO");

        empleado_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empleado_nombreActionPerformed(evt);
            }
        });

        jLabel3.setText("Nombre");

        empleado_telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empleado_telefonoActionPerformed(evt);
            }
        });

        jLabel4.setText("Telefono");

        empleado_sueldo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                empleado_sueldoActionPerformed(evt);
            }
        });

        jLabel5.setText("Sueldo");

        jLabel6.setText("Tipo Empleado");

        empleado_rol.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Conductor", "Auxiliar Servicio", "Administrador", "Director Operat.", "Gerente" }));

        CrearEmpleado.setText("Guardar");
        CrearEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CrearEmpleadoActionPerformed(evt);
            }
        });

        consultarEmpleado.setText("Consultar");
        consultarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarEmpleadoActionPerformed(evt);
            }
        });

        eliminarEmpleado.setText("Eliminar");
        eliminarEmpleado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarEmpleadoActionPerformed(evt);
            }
        });

        ListarEmpleados.setText("Listar");
        ListarEmpleados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ListarEmpleadosActionPerformed(evt);
            }
        });

        salir.setText("Salir");
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout crudEmpleadoLayout = new javax.swing.GroupLayout(crudEmpleado);
        crudEmpleado.setLayout(crudEmpleadoLayout);
        crudEmpleadoLayout.setHorizontalGroup(
            crudEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crudEmpleadoLayout.createSequentialGroup()
                .addGroup(crudEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(crudEmpleadoLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(crudEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 219, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(crudEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(jLabel1)
                                .addComponent(empleado_id)
                                .addComponent(jLabel3)
                                .addComponent(empleado_nombre)
                                .addComponent(jLabel4)
                                .addComponent(jLabel5)
                                .addComponent(jLabel6)
                                .addComponent(empleado_rol, 0, 219, Short.MAX_VALUE)
                                .addComponent(empleado_sueldo)
                                .addComponent(empleado_telefono))
                            .addGroup(crudEmpleadoLayout.createSequentialGroup()
                                .addGroup(crudEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(eliminarEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(CrearEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, 92, Short.MAX_VALUE))
                                .addGap(31, 31, 31)
                                .addGroup(crudEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(consultarEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, 95, Short.MAX_VALUE)
                                    .addComponent(ListarEmpleados, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))))
                    .addGroup(crudEmpleadoLayout.createSequentialGroup()
                        .addGap(93, 93, 93)
                        .addComponent(salir)))
                .addContainerGap(20, Short.MAX_VALUE))
        );
        crudEmpleadoLayout.setVerticalGroup(
            crudEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(crudEmpleadoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(29, 29, 29)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(empleado_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(empleado_nombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(empleado_telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel5)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(empleado_sueldo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel6)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(empleado_rol, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(31, 31, 31)
                .addGroup(crudEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ListarEmpleados)
                    .addComponent(eliminarEmpleado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(crudEmpleadoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(CrearEmpleado)
                    .addComponent(consultarEmpleado))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                .addComponent(salir)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crudEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(crudEmpleado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void empleado_idActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empleado_idActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empleado_idActionPerformed

    private void empleado_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empleado_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empleado_nombreActionPerformed

    private void empleado_telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empleado_telefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empleado_telefonoActionPerformed

    private void empleado_sueldoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_empleado_sueldoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_empleado_sueldoActionPerformed

    private void CrearEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CrearEmpleadoActionPerformed
        // TODO add your handling code here:
        DAOEmpleadoImpl mi_empleado = new DAOEmpleadoImpl();
        int tipo=0;
        String placa_bus = "";
        String estacion = "0";
        
        if(validaCampos(empleado_id.getText(), empleado_nombre.getText(), empleado_telefono.getText(), empleado_sueldo.getText())){
            
            switch (empleado_rol.getSelectedItem().toString()) {
                case "Conductor":
                    tipo = 1;
                    placa_bus = JOptionPane.showInputDialog("Introduce la placa del bus asignado al conductor: ");
                    break;
                case "Auxiliar Servicio":
                    tipo = 2;
                    estacion = JOptionPane.showInputDialog("Introduce el id de la estación donde trabaja el empleado: ");
                    break;
                case "Administrador":
                    tipo = 3;
                    break;
                case "Director Operat.":
                    tipo = 4;
                    break;
                case "Gerente":
                    tipo = 5;
                    break;
                default:
                    break;
            }
        
            try {
                
                String estado = mi_empleado.registrar(empleado_id.getText(), empleado_nombre.getText(), empleado_telefono.getText(), empleado_sueldo.getText(), tipo, placa_bus, Integer.parseInt(estacion));
                JOptionPane.showMessageDialog(null, estado);
                
                if (estado.equalsIgnoreCase("Insersion exitosa")) {
                    setVisible(false);
                    setCampos();
                    setVisible(true);
                }

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, ex);
            }
            
        }
      
    }//GEN-LAST:event_CrearEmpleadoActionPerformed

    private void consultarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_consultarEmpleadoActionPerformed
        // TODO add your handling code here:
        // TODO add your handling code here:
        DAOEmpleadoImpl mi_empleado = new DAOEmpleadoImpl();
        String id_empleado_old = empleado_id.getText();

        if (empleado_id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor dígite la identificación del empleado");
        }else {
            try {
                if(!mi_empleado.existe(id_empleado_old)){
                    JOptionPane.showMessageDialog(null, "El empleado que busca no existe");
                }else{
                    
                    CrearEmpleado.setEnabled(false);
                    eliminarEmpleado.setEnabled(false);
                    Empleado empleado = mi_empleado.buscar(id_empleado_old);
                    
                    empleado_id.setText(empleado.getEmpleadoId());
                    empleado_nombre.setText(empleado.getEmpleadoNombre());
                    empleado_telefono.setText(empleado.getEmpleadoTelefono());
                    empleado_sueldo.setText(empleado.getEmpleadoSueldo());
                    
                    String tipo_empleado = "";
                    
                    switch (empleado.getEmpleadoRol()) {
                        case 1:
                            tipo_empleado = "Conductor";
                            break;
                        case 2:
                            tipo_empleado = "Auxiliar Servicio";
                            break;
                        case 3:
                            tipo_empleado = "Administrador";
                            break;
                        case 4:
                            tipo_empleado = "Director Operat.";
                            break;
                        case 5:
                            tipo_empleado = "Gerente";
                            break;
                        default:
                            break;
                    }
                    
                    this.empleado_rol.setSelectedItem(tipo_empleado);
                    this.setVisible(false);

                    JButton actualizar = new JButton("Actualizar");//creo un boton
                    //actualizar.setBounds(new Rectangle(50, 50, 100, 75));
                    //actualizar.setSize(80, 29);
                    actualizar.reshape(140, 10, 100, 27);// x,y,largo,ancho
                    this.crudEmpleado.add(actualizar);
                    this.setVisible(true);

                    actualizar.addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent evt) {
                            
                            int tipo = 0;
                            String placa_bus = "";
                            String estacion = "0";
                            
                            switch (empleado_rol.getSelectedItem().toString()) {
                                
                                case "Conductor":
                                    tipo = 1;
                                    placa_bus = JOptionPane.showInputDialog("Introduce la placa del bus asignado al conductor: ");
                                    break;
                                case "Auxiliar Servicio":
                                    tipo = 2;
                                    estacion = JOptionPane.showInputDialog("Introduce el id de la estación donde trabaja el empleado: ");
                                    break;
                                case "Administrador":
                                    tipo = 3;
                                    break;
                                case "Director Operat.":
                                    tipo = 4;
                                    break;
                                case "Gerente":
                                    tipo = 5;
                                    break;
                                default:
                                    break;
                            }

                            try {
                                
                                //actualizar.setEnabled(false);

                                if(validaCampos(empleado_id.getText(), empleado_nombre.getText(), empleado_telefono.getText(), empleado_sueldo.getText())){
                                    
                                    String estado = mi_empleado.modificar(id_empleado_old, empleado_id.getText(), empleado_nombre.getText(),
                                            empleado_telefono.getText(), empleado_sueldo.getText(), tipo, placa_bus, Integer.parseInt(estacion));

                                    JOptionPane.showMessageDialog(null, estado);
                                    
                                    
                                    if (estado.equalsIgnoreCase("actualización exitosa")) {
                                        //System.exit(0);
                                        crudEmpleado.remove(actualizar);
                                        setVisible(false);
                                        setCampos();

                                        CrearEmpleado.setEnabled(true);
                                        eliminarEmpleado.setEnabled(true);

                                        setVisible(true);

                                    }
                                }
                                                      
                                
                            } catch (Exception ex) {
                                Logger.getLogger(CrudEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                            }

                        }
                    });
                }       
            } catch (Exception ex) {
                Logger.getLogger(CrudEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_consultarEmpleadoActionPerformed

    private void eliminarEmpleadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarEmpleadoActionPerformed
        // TODO add your handling code here:
        DAOEmpleadoImpl mi_empleado = new DAOEmpleadoImpl();

        if (empleado_id.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor digite la identificacion del empleado");
        }else{
            try {
                
                String estado = mi_empleado.eliminar(empleado_id.getText());
                JOptionPane.showMessageDialog(null, estado);
                
                if (estado.equalsIgnoreCase("eliminación exitosa")) {
                    setVisible(false);
                    setCampos();
                    setVisible(true);
                }

            } catch (Exception ex) {
                //Logger.getLogger(CrudEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                JOptionPane.showMessageDialog(null, ex);
            }

        }
    }//GEN-LAST:event_eliminarEmpleadoActionPerformed

    private void ListarEmpleadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ListarEmpleadosActionPerformed
        // TODO add your handling code here:
        
        Conexion conect = new Conexion();
        conect.conectar();
        String path = null;
        
        try {
            path = new java.io.File(".").getCanonicalPath();
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, ex);
        }


        try {

            //System.out.println(path + "\\src\\Reportes\\Empleados.jasper");

            JasperReport reporte = JasperCompileManager.compileReport(path + "\\src\\Reportes\\Empleados.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, conect.getConexion());

            JasperViewer jasperViewer = new JasperViewer(print);
            jasperViewer.setVisible(true);

            conect.cerrar();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }//GEN-LAST:event_ListarEmpleadosActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        // TODO add your handling code here:
        this.setVisible(false);
    }//GEN-LAST:event_salirActionPerformed

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
            java.util.logging.Logger.getLogger(CrudEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrudEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrudEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrudEmpleado.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CrudEmpleado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton CrearEmpleado;
    private javax.swing.JButton ListarEmpleados;
    private javax.swing.JButton consultarEmpleado;
    private javax.swing.JPanel crudEmpleado;
    private javax.swing.JButton eliminarEmpleado;
    private javax.swing.JTextField empleado_id;
    private javax.swing.JTextField empleado_nombre;
    private javax.swing.JComboBox empleado_rol;
    private javax.swing.JTextField empleado_sueldo;
    private javax.swing.JTextField empleado_telefono;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton salir;
    // End of variables declaration//GEN-END:variables
}
