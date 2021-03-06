
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vista;

import DAO.Conexion;
import DAO.DAOEmpleadoImpl;
import DAO.DAOTurnoImpl;
import Mapeo.Turno;
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
public class CrudTurno extends javax.swing.JFrame {

    /**
     * Creates new form Turno
     */
    public CrudTurno() {
        initComponents();

        String hora_final = "";
        
        for(int hora = 0; hora <= 23; hora++){

            for(int minutos = 0; minutos <= 59; minutos=minutos+10){
                
                if(hora<=9 && minutos <= 9){
                    hora_final = "0" + hora + ":0" + minutos + "";
                }else if(hora<=9){
                    hora_final = "0" + hora + ":" + minutos + "";
                }else if(minutos<=9){
                    hora_final = "" + hora + ":0" + minutos + "";
                }else{
                    hora_final = "" + hora + ":" + minutos + "";
                }
                
                this.hora_inicio.addItem(hora_final);
                this.hora_fin.addItem(hora_final);
            }
            
        }

    }
    
    public boolean validaCampos(){
        
        boolean mensaje = false;
        
        String[] hora_inicio_digitada =  hora_inicio.getSelectedItem().toString().split(":");
        String[] hora_fin_digitada =  hora_fin.getSelectedItem().toString().split(":");
    
        if ((dia_inicio.getSelectedItem().toString().equals(dia_fin.getSelectedItem().toString()))
                && (hora_inicio.getSelectedItem().toString().equals(hora_fin.getSelectedItem().toString()))) {

            JOptionPane.showMessageDialog(null, "El día de inicio y fin y la hora de inicio y fin no pueden ser iguales");

        } else if ((dia_inicio.getSelectedItem().toString().equals(dia_fin.getSelectedItem().toString()))
                && (((Integer.parseInt(hora_inicio_digitada[0]) == Integer.parseInt(hora_fin_digitada[0]))
                && (Integer.parseInt(hora_inicio_digitada[1]) > Integer.parseInt(hora_fin_digitada[1])))
                || ((Integer.parseInt(hora_inicio_digitada[0]) > Integer.parseInt(hora_fin_digitada[0]))))) {

            JOptionPane.showMessageDialog(null, "La hora de inicio no puede ser mayor que la hora de fin con un turno en el mismo día");

        }else{
            mensaje = true;
        }
        
        return mensaje;
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">                          
    private void initComponents() {

        CrudTurno = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        dia_inicio = new javax.swing.JComboBox();
        dia_fin = new javax.swing.JComboBox();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        turno_id = new javax.swing.JTextField();
        hora_fin = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        hora_inicio = new javax.swing.JComboBox();
        guardar = new javax.swing.JButton();
        consultar = new javax.swing.JButton();
        eliminar = new javax.swing.JButton();
        listar = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Tahoma", 0, 24)); // NOI18N
        jLabel1.setText("Turno");

        jLabel2.setText("Día Inicio");

        dia_inicio.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" }));

        dia_fin.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Lunes", "Martes", "Miercoles", "Jueves", "Viernes", "Sabado", "Domingo" }));

        jLabel3.setText("Día Fin");

        jLabel4.setText("Id turno");

        jLabel5.setText("Hora fin");

        jLabel6.setText("Hora inicio");

        hora_inicio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                hora_inicioActionPerformed(evt);
            }
        });

        guardar.setText("Guardar");
        guardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarActionPerformed(evt);
            }
        });

        consultar.setText("Consultar");
        consultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                consultarActionPerformed(evt);
            }
        });

        eliminar.setText("Eliminar");
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });

        listar.setText("Listar");
        listar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout CrudTurnoLayout = new javax.swing.GroupLayout(CrudTurno);
        CrudTurno.setLayout(CrudTurnoLayout);
        CrudTurnoLayout.setHorizontalGroup(
            CrudTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrudTurnoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(CrudTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1)
                    .addGroup(CrudTurnoLayout.createSequentialGroup()
                        .addGroup(CrudTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel6)
                            .addComponent(hora_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, CrudTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                .addComponent(guardar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(eliminar, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 77, Short.MAX_VALUE)))
                        .addGap(33, 33, 33)
                        .addGroup(CrudTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addComponent(hora_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(CrudTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                .addComponent(listar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(consultar, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                    .addGroup(CrudTurnoLayout.createSequentialGroup()
                        .addGroup(CrudTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addGroup(javax.swing.GroupLayout.Alignment.LEADING, CrudTurnoLayout.createSequentialGroup()
                                .addComponent(jLabel4)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(turno_id))
                            .addGroup(CrudTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                .addComponent(jLabel2)
                                .addComponent(dia_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(33, 33, 33)
                        .addGroup(CrudTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel3)
                            .addComponent(dia_fin, javax.swing.GroupLayout.PREFERRED_SIZE, 87, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        CrudTurnoLayout.setVerticalGroup(
            CrudTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(CrudTurnoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(21, 21, 21)
                .addGroup(CrudTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4)
                    .addComponent(turno_id, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(CrudTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(CrudTurnoLayout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia_fin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CrudTurnoLayout.createSequentialGroup()
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(dia_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(18, 18, 18)
                .addGroup(CrudTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(CrudTurnoLayout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hora_fin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(CrudTurnoLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(hora_inicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(CrudTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(guardar)
                    .addComponent(consultar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(CrudTurnoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(eliminar)
                    .addComponent(listar))
                .addGap(23, 23, 23))
        );

        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CrudTurno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(83, 83, 83)
                .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 77, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(CrudTurno, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addContainerGap(28, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>                        

    private void guardarActionPerformed(java.awt.event.ActionEvent evt) {                                        
        // TODO add your handling code here:
        
        DAOTurnoImpl mi_turno = new DAOTurnoImpl();

        if (validaCampos()) {

            try {

                String estado_insersion = mi_turno.registrar(dia_inicio.getSelectedItem().toString(),
                        dia_fin.getSelectedItem().toString(),hora_inicio.getSelectedItem().toString(), hora_fin.getSelectedItem().toString());
                JOptionPane.showMessageDialog(null, estado_insersion);

                if (!estado_insersion.equalsIgnoreCase("")) {
                    turno_id.setText("");
                }

            } catch (Exception ex) {
                Logger.getLogger(CrudEmpleado.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
    }                                       

    private void hora_inicioActionPerformed(java.awt.event.ActionEvent evt) {                                            
        // TODO add your handling code here:
    }                                           

    private void listarActionPerformed(java.awt.event.ActionEvent evt) {                                       
        // TODO add your handling code here:
        
        Conexion conect = new Conexion();
        conect.conectar();
        String path = null;

        try {
            path = new java.io.File(".").getCanonicalPath();
        } catch (IOException ex) {
            Logger.getLogger(CrudEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {

            //System.out.println(path + "\\src\\Vista\\Empleados.jasper");
            JasperReport reporte = JasperCompileManager.compileReport(path + "\\src\\Reportes\\Turnos.jrxml");
            JasperPrint print = JasperFillManager.fillReport(reporte, null, conect.getConexion());

            JasperViewer jasperViewer = new JasperViewer(print);
            jasperViewer.setVisible(true);

            conect.cerrar();

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }                                      

    private void consultarActionPerformed(java.awt.event.ActionEvent evt) {                                          
        // TODO add your handling code here:
        
        DAOTurnoImpl mi_turno = new DAOTurnoImpl();
        String id_turno = turno_id.getText();

        if (id_turno.equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor dígite el id del turno");
        } else {
            try {
                if (!mi_turno.existe(Integer.parseInt(id_turno))) {
                    JOptionPane.showMessageDialog(null, "El el turno que busca no existe");
                } else {

                    guardar.setEnabled(false);
                    eliminar.setEnabled(false);
                    Turno mi_turnito = mi_turno.buscar(Integer.parseInt(id_turno));

                    turno_id.setText(""+mi_turnito.getTurnoId()+"");
                    dia_inicio.setSelectedItem(mi_turnito.getTurnoDiaInicio());
                    dia_fin.setSelectedItem(mi_turnito.getTurnoDiaFin());
                    hora_inicio.setSelectedItem(mi_turnito.getTurnoHoraInicio());
                    hora_fin.setSelectedItem(mi_turnito.getTurnoHoraFin());
                    
                    this.setVisible(false);

                    JButton actualizar = new JButton("Actualizar");//creo un boton
                    //actualizar.setBounds(new Rectangle(50, 50, 100, 75));
                    //actualizar.setSize(80, 29);
                    actualizar.reshape(110, 10, 100, 27);// x,y,largo,ancho
                    this.CrudTurno.add(actualizar);
                    turno_id.setEnabled(false);
                    this.setVisible(true);

                    actualizar.addActionListener(new java.awt.event.ActionListener() {
                        @Override
                        public void actionPerformed(java.awt.event.ActionEvent evt) {

                            try {
                                
                                if (validaCampos()) {

                                    String estado = mi_turno.modificar(Integer.parseInt(id_turno), dia_inicio.getSelectedItem().toString(),
                        dia_fin.getSelectedItem().toString(),hora_inicio.getSelectedItem().toString(), hora_fin.getSelectedItem().toString());

                                    JOptionPane.showMessageDialog(null, estado);

                                    if (estado.equalsIgnoreCase("actualización exitosa")) {
                                        //System.exit(0);
                                        CrudTurno.remove(actualizar);
                                        setVisible(false);
                                        turno_id.setText("");

                                        guardar.setEnabled(true);
                                        eliminar.setEnabled(true);
                                        turno_id.setEnabled(true);

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
                JOptionPane.showMessageDialog(null, ex);
            }
        }
    }                                         

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:
        this.setVisible(false);
    }                                        

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {                                         
        // TODO add your handling code here:

        DAOTurnoImpl mi_turno = new DAOTurnoImpl();
        String id_turno = turno_id.getText();

        if (id_turno.equals("")) {
            JOptionPane.showMessageDialog(null, "Por favor digite el id del turno");
        } else {

            try {

                    String estado = mi_turno.eliminar(Integer.parseInt(id_turno));
                    JOptionPane.showMessageDialog(null, estado);

                    if (estado.equalsIgnoreCase("eliminación exitosa")) {
                        turno_id.setText("");
                    }

            } catch (Exception ex) {
                Logger.getLogger(CrudUsuario.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }                                        

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
            java.util.logging.Logger.getLogger(CrudTurno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CrudTurno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CrudTurno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CrudTurno.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                
                new CrudTurno().setVisible(true);

            }
        });
    }

    // Variables declaration - do not modify                     
    private javax.swing.JPanel CrudTurno;
    private javax.swing.JButton consultar;
    private javax.swing.JComboBox dia_fin;
    private javax.swing.JComboBox dia_inicio;
    private javax.swing.JButton eliminar;
    private javax.swing.JButton guardar;
    private javax.swing.JComboBox hora_fin;
    private javax.swing.JComboBox hora_inicio;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JButton listar;
    private javax.swing.JTextField turno_id;
    // End of variables declaration                   
}
