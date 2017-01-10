/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import DAO.DAOEmpleadoImpl;
import DAO.DAOUsuarioImpl;
import Mapeo.Empleado;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author PORTATIL
 */
public class PQRS extends javax.swing.JFrame {
    
    private String empleado_logeado;
    
    public String getUserLogeado() {
        return empleado_logeado;
    }

    public void setUserLogeado(String id_empleado) {
        this.empleado_logeado = id_empleado;
    }
    
    

    /**
     * Creates new form PQRS
     */
    public PQRS() {
        initComponents();
        
        DAOUsuarioImpl new_user = new DAOUsuarioImpl();
        List<String> motivo_pqrs = new ArrayList<String>();

        try {
            motivo_pqrs = new_user.motivoPQRS();//traigo todos los administradores que hay en el sistema

            if (motivo_pqrs.isEmpty()) {
                motivo_pqrs.add("No existen motivos en la BD");//seteo la posicion cero por si no hay datos
                btRegistrarPqrs.setEnabled(false);
            }

            Iterator iter = motivo_pqrs.iterator();
            while (iter.hasNext()) {
                String tmp = iter.next().toString();
                this.cbPqrs_motivo.addItem(tmp);
            }

        } catch (Exception ex) {
            //Logger.getLogger(CrudEstacion.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, ex);
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
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        cbPqrs_motivo = new javax.swing.JComboBox<String>();
        jScrollPane1 = new javax.swing.JScrollPane();
        taPqrs_descrip = new javax.swing.JTextArea();
        jLabel4 = new javax.swing.JLabel();
        btRegistrarPqrs = new javax.swing.JButton();
        btSalirPqrs = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        tfUsuario_id = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Tahoma", 2, 24)); // NOI18N
        jLabel2.setText("Ingresa PQRS");

        jLabel3.setText("Motivo");

        cbPqrs_motivo.addInputMethodListener(new java.awt.event.InputMethodListener() {
            public void caretPositionChanged(java.awt.event.InputMethodEvent evt) {
            }
            public void inputMethodTextChanged(java.awt.event.InputMethodEvent evt) {
                cbPqrs_motivoInputMethodTextChanged(evt);
            }
        });

        taPqrs_descrip.setColumns(20);
        taPqrs_descrip.setRows(5);
        jScrollPane1.setViewportView(taPqrs_descrip);

        jLabel4.setText("Descripción de su solicitud");

        btRegistrarPqrs.setText("Registrar");
        btRegistrarPqrs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btRegistrarPqrsActionPerformed(evt);
            }
        });

        btSalirPqrs.setText("Salir");
        btSalirPqrs.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btSalirPqrsActionPerformed(evt);
            }
        });

        jLabel5.setText("Id Usuario: ");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(81, 81, 81)
                        .addComponent(btRegistrarPqrs)
                        .addGap(32, 32, 32)
                        .addComponent(btSalirPqrs)
                        .addGap(127, 127, 127))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(cbPqrs_motivo, 0, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jScrollPane1))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel4)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(tfUsuario_id, javax.swing.GroupLayout.PREFERRED_SIZE, 141, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jLabel2)
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(tfUsuario_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 11, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(cbPqrs_motivo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btRegistrarPqrs)
                    .addComponent(btSalirPqrs))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btRegistrarPqrsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btRegistrarPqrsActionPerformed
        // TODO add your handling code here:
        
        DAOUsuarioImpl new_user = new DAOUsuarioImpl();
        DAOEmpleadoImpl mi_empleado = new DAOEmpleadoImpl();
       
        
        if(cbPqrs_motivo.getSelectedItem().toString().equals("")){
            JOptionPane.showMessageDialog(null, "Debe elegir un motivo");
        }else if(taPqrs_descrip.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Debe llenar el campo descripción");        
        }else if(tfUsuario_id.getText().equals("")){
            JOptionPane.showMessageDialog(null, "Debe digitar el id del usuario");        
        }else if(tfUsuario_id.getText().equals("0")){
            JOptionPane.showMessageDialog(null, "Sólo los usuarios con tarjeta personalizada pueden ingresar a PQRS");        
        }else{
            
            try {
                
                
                
                if (!new_user.existe(tfUsuario_id.getText())) {
                    JOptionPane.showMessageDialog(null, "El usuario no existe");
                }else if (!mi_empleado.existe(this.getUserLogeado())) {
                    JOptionPane.showMessageDialog(null, "El usuario logeado no existe");
                }else{
                    
                    Empleado empleaducho = mi_empleado.buscar(this.getUserLogeado());
                    
                    //JOptionPane.showMessageDialog(null, this.getUserLogeado());
                    //JOptionPane.showMessageDialog(null, empleaducho.getAuxServCliente().getEstacion().getEstacionId());
                    
                    if (empleaducho.getEmpleadoRol() != 2) {
                        JOptionPane.showMessageDialog(null, "Las PQRS sólo pueden ser registradas por un Auxiliar del servicion al cliente");
                    } else{
                        int motivo = cbPqrs_motivo.getSelectedIndex() + 1;
                        String estado = new_user.registrarPQRS(tfUsuario_id.getText(), motivo , taPqrs_descrip.getText(), empleaducho.getAuxServCliente().getEstacion().getEstacionId());
                        JOptionPane.showMessageDialog(null, estado);

                        if(estado.equalsIgnoreCase("Insersión exitosa")){
                            taPqrs_descrip.setText("");
                            tfUsuario_id.setText("");
                        }
                    }
                }
            } catch (Exception ex) {
                System.out.println(ex);
            }
        }
    }//GEN-LAST:event_btRegistrarPqrsActionPerformed

    private void btSalirPqrsActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btSalirPqrsActionPerformed
        // TODO add your handling code here:
        
        setVisible(false);
    }//GEN-LAST:event_btSalirPqrsActionPerformed

    private void cbPqrs_motivoInputMethodTextChanged(java.awt.event.InputMethodEvent evt) {//GEN-FIRST:event_cbPqrs_motivoInputMethodTextChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_cbPqrs_motivoInputMethodTextChanged

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
            java.util.logging.Logger.getLogger(PQRS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(PQRS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(PQRS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(PQRS.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new PQRS().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btRegistrarPqrs;
    private javax.swing.JButton btSalirPqrs;
    private javax.swing.JComboBox<String> cbPqrs_motivo;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea taPqrs_descrip;
    private javax.swing.JTextField tfUsuario_id;
    // End of variables declaration//GEN-END:variables
}
