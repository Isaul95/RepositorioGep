
package si;
import Controladores.Controladorapertura;
import static Controladores.Controladorgastos.pass;
import static Controladores.Controladorgastos.pass2;
import static Controladores.Controladorgastos.pass3;
import Controladores.Controladorventa;
import static Controladores.Controladorventa.validarFormulariotexto_paciente;
import Modelos.Modelo_venta_a_credito;
import Modelos.Modeloventa;
import java.awt.Color;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;

public class Agregar_paciente extends javax.swing.JFrame{
 Thread hilo; 
 String hora, minutos, segundos,tipo_de_venta;
    public Agregar_paciente() {
         initComponents();
    
        user.setText(SI_Inicio.text_user.getText());
        this.setLocationRelativeTo(null); // CENTRAR FORMULARIO

    } 
    public Agregar_paciente(String venta) {
        this.tipo_de_venta=venta;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        user = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        user_nombre = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        medico = new javax.swing.JTextField();
        user_edad = new javax.swing.JTextField();
        jLabel81 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        user_sexo = new javax.swing.JComboBox();
        calendar_fecha_nacimiento = new com.toedter.calendar.JDateChooser();
        jLabel79 = new javax.swing.JLabel();
        agregar_paciente = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(135, 193, 193));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Datos del paciente", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 36))); // NOI18N
        jPanel1.setToolTipText("Datos del paciente");
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(135, 193, 193));
        jPanel2.setForeground(new java.awt.Color(206, 231, 235));
        jPanel2.setLayout(null);

        user.setBackground(new java.awt.Color(0, 0, 0));
        user.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(user);
        user.setBounds(420, -10, 190, 30);

        jLabel82.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel82.setText("Nombre:");
        jPanel2.add(jLabel82);
        jLabel82.setBounds(20, 60, 97, 32);

        user_nombre.setBackground(new java.awt.Color(135, 193, 193));
        user_nombre.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        user_nombre.setForeground(new java.awt.Color(0, 0, 0));
        user_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        user_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_nombreActionPerformed(evt);
            }
        });
        jPanel2.add(user_nombre);
        user_nombre.setBounds(150, 70, 520, 26);

        jLabel83.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel83.setText("Medico:");
        jPanel2.add(jLabel83);
        jLabel83.setBounds(20, 110, 91, 32);

        medico.setBackground(new java.awt.Color(135, 193, 193));
        medico.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        medico.setForeground(new java.awt.Color(0, 0, 0));
        medico.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel2.add(medico);
        medico.setBounds(150, 120, 520, 26);

        user_edad.setBackground(new java.awt.Color(135, 193, 193));
        user_edad.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        user_edad.setForeground(new java.awt.Color(0, 0, 0));
        user_edad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        user_edad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_edadActionPerformed(evt);
            }
        });
        jPanel2.add(user_edad);
        user_edad.setBounds(150, 170, 90, 26);

        jLabel81.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel81.setText("Edad:");
        jPanel2.add(jLabel81);
        jLabel81.setBounds(20, 160, 66, 32);

        jLabel78.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel78.setText("Sexo:");
        jPanel2.add(jLabel78);
        jLabel78.setBounds(20, 210, 64, 32);

        user_sexo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        user_sexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Femenino" }));
        user_sexo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(135, 193, 193)));
        jPanel2.add(user_sexo);
        user_sexo.setBounds(150, 220, 160, 25);

        calendar_fecha_nacimiento.setBackground(new java.awt.Color(135, 193, 193));
        calendar_fecha_nacimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(135, 193, 193)));
        calendar_fecha_nacimiento.setForeground(new java.awt.Color(255, 255, 255));
        calendar_fecha_nacimiento.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jPanel2.add(calendar_fecha_nacimiento);
        calendar_fecha_nacimiento.setBounds(210, 270, 270, 31);

        jLabel79.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel79.setText("Fecha de nac.");
        jPanel2.add(jLabel79);
        jLabel79.setBounds(20, 270, 159, 32);

        agregar_paciente.setBackground(new java.awt.Color(255, 255, 255));
        agregar_paciente.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        agregar_paciente.setForeground(new java.awt.Color(0, 0, 0));
        agregar_paciente.setText("Agregar paciente");
        agregar_paciente.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        agregar_paciente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar_pacienteActionPerformed(evt);
            }
        });
        jPanel2.add(agregar_paciente);
        agregar_paciente.setBounds(430, 340, 240, 45);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 50, 680, 380);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 696, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        jPanel1.getAccessibleContext().setAccessibleName("CAJA");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void user_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_nombreActionPerformed

    private void user_edadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_edadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_edadActionPerformed

    private void agregar_pacienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar_pacienteActionPerformed
                 try{
                       pass = Controladorventa.validarFormulario_paciente(user_edad.getText());
                   pass2 = validarFormulariotexto_paciente(user_nombre.getText());
                   pass3 = validarFormulariotexto_paciente(medico.getText());
                     String fecha_paciente= Controladorventa.fecha_de_nacimiento_del_paciente();
                   if (pass && pass2 &&pass3&&!fecha_paciente.equalsIgnoreCase("")) {
                      
                        
                      if(nucleo.ventacredito.isSelected()){
                          Modelo_venta_a_credito.completar_venta_a_credito();
                          this.setVisible(false);
                       new nucleo().setVisible(true);
                      }else{
                             Modeloventa.llenar_datos_del_paciente_tras_completar_la_venta();
                       Modeloventa.limpiardatosdeventa();
                          this.setVisible(false);
                       new nucleo().setVisible(true);
                      }
                      
                    
                   } else{
                    JOptionPane.showMessageDialog(null,"Complete todos los datos del paciente", "ERROR", JOptionPane.ERROR_MESSAGE);
                 }
                 }catch(NullPointerException NPE){
                      JOptionPane.showMessageDialog(null,"El campo fecha no puede quedar vacio", "ERROR", JOptionPane.ERROR_MESSAGE);
                 }
    }//GEN-LAST:event_agregar_pacienteActionPerformed


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
            java.util.logging.Logger.getLogger(Agregar_paciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Agregar_paciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Agregar_paciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Agregar_paciente.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Agregar_paciente().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar_paciente;
    public static com.toedter.calendar.JDateChooser calendar_fecha_nacimiento;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JTextField medico;
    private javax.swing.JLabel user;
    public static javax.swing.JTextField user_edad;
    public static javax.swing.JTextField user_nombre;
    public static javax.swing.JComboBox user_sexo;
    // End of variables declaration//GEN-END:variables
}
