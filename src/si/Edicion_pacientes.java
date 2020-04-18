package si;
import Controladores.Controlador_edicion_paciente;
import Controladores.Controladorexistencias;
import Controladores.Controladorventa;
import java.awt.Color;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import ticket.ticketcortedecaja;

public class Edicion_pacientes extends javax.swing.JFrame{

  

 public Edicion_pacientes() {
        initComponents();
         Controladorventa.noduplicar_edicionpaciente=true;
        this.setLocationRelativeTo(null); // CENTRAR FORMULARIO
        Controlador_edicion_paciente.metodosalabriredicion_paciente();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Corte_btncancelar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel79 = new javax.swing.JLabel();
        user_edad_edicion = new javax.swing.JTextField();
        user_sexo_edicion = new javax.swing.JComboBox();
        jLabel78 = new javax.swing.JLabel();
        jLabel77 = new javax.swing.JLabel();
        calendar_fecha_nacimiento_edicion = new com.toedter.calendar.JDateChooser();
        guardarcambios_paciente = new javax.swing.JButton();
        user_nombre_edicion1 = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(206, 231, 235));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "EXISTENCIAS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 36))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(255, 0, 0));
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(206, 231, 235));
        jPanel2.setLayout(null);

        Corte_btncancelar.setBackground(new java.awt.Color(255, 255, 255));
        Corte_btncancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Corte_btncancelar.setForeground(new java.awt.Color(255, 255, 255));
        Corte_btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/salir-flecha-derecha (1).png"))); // NOI18N
        Corte_btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Corte_btncancelarActionPerformed(evt);
            }
        });
        jPanel2.add(Corte_btncancelar);
        Corte_btncancelar.setBounds(580, 10, 60, 60);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(135, 193, 193));
        jLabel3.setText("Ediciòn de datos del cliente");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(10, 0, 340, 70);

        jLabel74.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(135, 193, 193));
        jLabel74.setText("Nombre:");
        jPanel2.add(jLabel74);
        jLabel74.setBounds(10, 90, 77, 24);

        jLabel79.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(135, 193, 193));
        jLabel79.setText("Edad:");
        jPanel2.add(jLabel79);
        jLabel79.setBounds(10, 170, 66, 32);

        user_edad_edicion.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_edad_edicionActionPerformed(evt);
            }
        });
        jPanel2.add(user_edad_edicion);
        user_edad_edicion.setBounds(110, 170, 140, 40);

        user_sexo_edicion.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Femenino" }));
        jPanel2.add(user_sexo_edicion);
        user_sexo_edicion.setBounds(460, 160, 100, 50);

        jLabel78.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(135, 193, 193));
        jLabel78.setText("Sexo:");
        jPanel2.add(jLabel78);
        jLabel78.setBounds(350, 170, 52, 24);

        jLabel77.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(113, 193, 193));
        jLabel77.setText("Fecha de nacimiento:");
        jPanel2.add(jLabel77);
        jLabel77.setBounds(10, 260, 189, 24);

        calendar_fecha_nacimiento_edicion.setBackground(new java.awt.Color(206, 231, 235));
        calendar_fecha_nacimiento_edicion.setForeground(new java.awt.Color(0, 96, 255));
        calendar_fecha_nacimiento_edicion.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jPanel2.add(calendar_fecha_nacimiento_edicion);
        calendar_fecha_nacimiento_edicion.setBounds(230, 260, 270, 29);

        guardarcambios_paciente.setBackground(new java.awt.Color(206, 231, 235));
        guardarcambios_paciente.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        guardarcambios_paciente.setForeground(new java.awt.Color(255, 0, 0));
        guardarcambios_paciente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/casilla-de-verificacion (1).png"))); // NOI18N
        guardarcambios_paciente.setText("GUARDAR CAMBIOS");
        guardarcambios_paciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                guardarcambios_pacienteActionPerformed(evt);
            }
        });
        jPanel2.add(guardarcambios_paciente);
        guardarcambios_paciente.setBounds(180, 410, 270, 46);

        user_nombre_edicion1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_nombre_edicion1ActionPerformed(evt);
            }
        });
        jPanel2.add(user_nombre_edicion1);
        user_nombre_edicion1.setBounds(110, 80, 460, 40);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 50, 650, 480);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("Datos del cliente");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Corte_btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Corte_btncancelarActionPerformed
       Controlador_edicion_paciente.paciente_no_actualizado();
        this.dispose();
        
    }//GEN-LAST:event_Corte_btncancelarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
          Controladorventa.noduplicar_edicionpaciente=false;
    }//GEN-LAST:event_formWindowClosed

    private void user_edad_edicionActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_edad_edicionActionPerformed
    
    }//GEN-LAST:event_user_edad_edicionActionPerformed

    private void guardarcambios_pacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_guardarcambios_pacienteActionPerformed
           Controlador_edicion_paciente.confirmar_cambios();
            this.dispose();
    }//GEN-LAST:event_guardarcambios_pacienteActionPerformed

    private void user_nombre_edicion1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_nombre_edicion1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_nombre_edicion1ActionPerformed

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
            java.util.logging.Logger.getLogger(Edicion_pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Edicion_pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Edicion_pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Edicion_pacientes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Edicion_pacientes().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Corte_btncancelar;
    public static com.toedter.calendar.JDateChooser calendar_fecha_nacimiento_edicion;
    public static javax.swing.JButton guardarcambios_paciente;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JTextField user_edad_edicion;
    public static javax.swing.JTextField user_nombre_edicion1;
    public static javax.swing.JComboBox user_sexo_edicion;
    // End of variables declaration//GEN-END:variables
}
