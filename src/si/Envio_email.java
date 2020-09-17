
package si;

import Controladores.Controlador_Report_pdf;
import Controladores.Controladorventa;
import Modelos.Modelo_capturar_resultados;
import static si.Capturar_resultados.id_venta;
public class Envio_email extends javax.swing.JFrame { 
 
    public Envio_email() {
        initComponents();
        this.setLocationRelativeTo(null); // CENTRAR FORMULARIO 
    }
       
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        para = new javax.swing.JTextField();
        asunto = new javax.swing.JTextField();
        emailsend = new javax.swing.JButton();
        File = new javax.swing.JLabel();
        send_message = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        regresar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(135, 193, 193));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Envio email", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 36))); // NOI18N
        jPanel1.setToolTipText("APERTURA DE CAJA");
        jPanel1.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel1.setText("Para :");
        jPanel1.add(jLabel1);
        jLabel1.setBounds(30, 80, 60, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel2.setText("Asunto :");
        jPanel1.add(jLabel2);
        jLabel2.setBounds(30, 120, 80, 30);

        para.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        para.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                paraKeyPressed(evt);
            }
        });
        jPanel1.add(para);
        para.setBounds(120, 80, 460, 30);

        asunto.setFont(new java.awt.Font("Arial", 0, 14)); // NOI18N
        asunto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        asunto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                asuntoActionPerformed(evt);
            }
        });
        asunto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                asuntoKeyPressed(evt);
            }
        });
        jPanel1.add(asunto);
        asunto.setBounds(120, 120, 460, 30);

        emailsend.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        emailsend.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/enviar.png"))); // NOI18N
        emailsend.setText("Enviar");
        emailsend.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        emailsend.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        emailsend.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                emailsendActionPerformed(evt);
            }
        });
        jPanel1.add(emailsend);
        emailsend.setBounds(130, 200, 171, 63);

        File.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        jPanel1.add(File);
        File.setBounds(170, 160, 410, 30);

        send_message.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        send_message.setForeground(new java.awt.Color(204, 0, 0));
        send_message.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        send_message.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jPanel1.add(send_message);
        send_message.setBounds(60, 270, 510, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel4.setText("Archivo :");
        jPanel1.add(jLabel4);
        jLabel4.setBounds(30, 160, 110, 30);

        regresar.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        regresar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/salir-flecha-derecha (1).png"))); // NOI18N
        regresar.setText("Regresar");
        regresar.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        regresar.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        regresar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresarActionPerformed(evt);
            }
        });
        jPanel1.add(regresar);
        regresar.setBounds(360, 200, 171, 63);

        jLabel3.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel3.setText("Dos correos separados por punto y coma (;)   EJEMP: (xx@gmail.com;xx@hotmail.com)");
        jPanel1.add(jLabel3);
        jLabel3.setBounds(100, 50, 490, 20);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 596, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 312, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void asuntoKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_asuntoKeyPressed
        
    }//GEN-LAST:event_asuntoKeyPressed

    private void asuntoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_asuntoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_asuntoActionPerformed

    private void paraKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_paraKeyPressed
        
    }//GEN-LAST:event_paraKeyPressed

    private void emailsendActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_emailsendActionPerformed
        Controlador_Report_pdf enviar = new Controlador_Report_pdf();
        if(Modelo_capturar_resultados.verificar_estudio_con_longitud_mayor(Integer.valueOf(id_venta.getText()))>=1){
            //ENVIO PDF LARGO
     enviar.btn_Envio_email(Envio_email.para.getText(),Envio_email.asunto.getText(),Capturar_resultados.id_paciente.getText()+"_"+Capturar_resultados.paciente.getText()+"_02"+".pdf",Envio_email.send_message.getText());  
            //ENVIO PDF CORTO
   if(Modelo_capturar_resultados.verificar_estudio_con_longitud_menor(Integer.valueOf(id_venta.getText()))>=1){
  enviar.btn_Envio_email(Envio_email.para.getText(),Envio_email.asunto.getText(),Capturar_resultados.id_paciente.getText()+"_"+Capturar_resultados.paciente.getText()+"_01"+".pdf",Envio_email.send_message.getText());   
   
   }
        }else{//ENVIO PDF CON LONGITUDES DE ANALISIS MENORES A 68    
  enviar.btn_Envio_email(Envio_email.para.getText(),Envio_email.asunto.getText(),Envio_email.File.getText(),Envio_email.send_message.getText());  
        }     
    }//GEN-LAST:event_emailsendActionPerformed

    private void regresarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresarActionPerformed
        dispose();
    }//GEN-LAST:event_regresarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
Controladorventa.noduplicar_envio_email=false;        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(Envio_email.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Envio_email.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Envio_email.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Envio_email.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Envio_email().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JLabel File;
    public static javax.swing.JTextField asunto;
    public static javax.swing.JButton emailsend;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    public static javax.swing.JPanel jPanel1;
    public static javax.swing.JTextField para;
    public static javax.swing.JButton regresar;
    public static javax.swing.JLabel send_message;
    // End of variables declaration//GEN-END:variables
}
