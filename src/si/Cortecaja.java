/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si;

import Controladores.Controladorcortedecaja;
import Controladores.Controladorventa;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 *
 * @author Alexis
 */
public class Cortecaja extends javax.swing.JFrame implements Runnable{
   String  usuarioname=SI_Inicio.text_user.getText();
Thread hilo; 
   String hora,minutos,segundos;
     Calendar fecha_actual = new GregorianCalendar();
  
    public Cortecaja() {
        initComponents();
               Controladorventa.noduplicarcorte=true;
         hilo=new Thread(this);
     hilo.start();
        this.setLocationRelativeTo(null); // CENTRAR FORMULARIO
        Fecha.setText(Controladorventa.fecha());
        Controladorcortedecaja.metodosalabrircortedecaja();
        user.setText(usuarioname);
    }
    public void hora(){
        Calendar calendario=new GregorianCalendar();
        Date horaactual=new Date();
        calendario.setTime(horaactual);
    hora=calendario.get(Calendar.HOUR)>9?""+calendario.get(Calendar.HOUR):"0"+calendario.get(Calendar.HOUR);
    minutos=calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
    segundos= calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);        
     }
    public void run() {
      Thread ct = Thread.currentThread();
      while(ct==hilo){
          hora();
          Reloj.setText(hora+":"+minutos+":"+segundos+" ");
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
        user = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        Fecha = new javax.swing.JLabel();
        Reloj = new javax.swing.JLabel();
        user1 = new javax.swing.JLabel();
        pagosmadetoday = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        Gastosfromtoday = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        Ventasfortoday1 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Corte_btncancelar = new javax.swing.JButton();
        monto = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        totaldescuentos = new javax.swing.JLabel();
        numerosdescuentos = new javax.swing.JLabel();
        aperturacantidad = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(135, 193, 193));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CORTE DE CAJA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 36))); // NOI18N
        jPanel1.setForeground(new java.awt.Color(206, 231, 235));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jPanel1.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 0, 180, 50));

        jPanel3.setBackground(new java.awt.Color(135, 193, 193));
        jPanel3.setForeground(new java.awt.Color(206, 231, 235));
        jPanel3.setLayout(null);

        Fecha.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Fecha.setForeground(new java.awt.Color(255, 255, 255));
        Fecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Fecha.setText("DD/MM/YYYY");
        jPanel3.add(Fecha);
        Fecha.setBounds(10, 10, 240, 60);

        Reloj.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Reloj.setForeground(new java.awt.Color(255, 255, 255));
        Reloj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reloj.setText("00:00:00");
        jPanel3.add(Reloj);
        Reloj.setBounds(340, 10, 240, 60);

        user1.setBackground(new java.awt.Color(206, 231, 235));
        user1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        user1.setForeground(new java.awt.Color(135, 193, 193));
        jPanel3.add(user1);
        user1.setBounds(240, 0, 190, 60);

        pagosmadetoday.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        pagosmadetoday.setForeground(new java.awt.Color(255, 255, 255));
        pagosmadetoday.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pagosmadetoday.setText("00.00");
        jPanel3.add(pagosmadetoday);
        pagosmadetoday.setBounds(10, 300, 180, 29);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Pagos");
        jPanel3.add(jLabel4);
        jLabel4.setBounds(10, 260, 170, 29);

        Gastosfromtoday.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        Gastosfromtoday.setForeground(new java.awt.Color(255, 255, 255));
        Gastosfromtoday.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Gastosfromtoday.setText("00.00");
        jPanel3.add(Gastosfromtoday);
        Gastosfromtoday.setBounds(10, 210, 180, 29);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Gastos");
        jPanel3.add(jLabel7);
        jLabel7.setBounds(10, 170, 170, 29);

        Ventasfortoday1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        Ventasfortoday1.setForeground(new java.awt.Color(255, 255, 255));
        Ventasfortoday1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Ventasfortoday1.setText("00.00");
        jPanel3.add(Ventasfortoday1);
        Ventasfortoday1.setBounds(10, 120, 180, 29);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Ventas");
        jPanel3.add(jLabel6);
        jLabel6.setBounds(10, 80, 180, 29);

        Corte_btncancelar.setBackground(new java.awt.Color(255, 255, 255));
        Corte_btncancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Corte_btncancelar.setForeground(new java.awt.Color(255, 0, 0));
        Corte_btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/salir-flecha-derecha (1).png"))); // NOI18N
        Corte_btncancelar.setText("Cancelar");
        Corte_btncancelar.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(135, 193, 193)));
        Corte_btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Corte_btncancelarActionPerformed(evt);
            }
        });
        jPanel3.add(Corte_btncancelar);
        Corte_btncancelar.setBounds(10, 370, 250, 60);

        monto.setBackground(new java.awt.Color(135, 193, 193));
        monto.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        monto.setForeground(new java.awt.Color(255, 255, 255));
        monto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        monto.setText("00.00");
        monto.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(135, 193, 193)));
        monto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                montoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                montoFocusLost(evt);
            }
        });
        monto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                montoKeyReleased(evt);
            }
        });
        jPanel3.add(monto);
        monto.setBounds(340, 370, 230, 60);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("   Monto entregado:");
        jPanel3.add(jLabel5);
        jLabel5.setBounds(330, 340, 240, 29);

        totaldescuentos.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        totaldescuentos.setForeground(new java.awt.Color(255, 255, 255));
        totaldescuentos.setText("Total de descuentos");
        jPanel3.add(totaldescuentos);
        totaldescuentos.setBounds(440, 210, 200, 29);

        numerosdescuentos.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        numerosdescuentos.setForeground(new java.awt.Color(255, 255, 255));
        numerosdescuentos.setText("Numero");
        jPanel3.add(numerosdescuentos);
        numerosdescuentos.setBounds(340, 210, 100, 29);

        aperturacantidad.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        aperturacantidad.setForeground(new java.awt.Color(255, 255, 255));
        aperturacantidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        aperturacantidad.setText("00.00");
        jPanel3.add(aperturacantidad);
        aperturacantidad.setBounds(420, 110, 180, 40);

        jLabel8.setBackground(new java.awt.Color(135, 193, 193));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("La apertura de caja fue:");
        jPanel3.add(jLabel8);
        jLabel8.setBounds(340, 80, 240, 29);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Descuentos:");
        jPanel3.add(jLabel9);
        jLabel9.setBounds(340, 170, 180, 29);

        jPanel1.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 50, 630, 450));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Corte_btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Corte_btncancelarActionPerformed
          dispose();  
    }//GEN-LAST:event_Corte_btncancelarActionPerformed

    private void montoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_montoFocusGained
  Controladorcortedecaja.montoFocusGained();
    }//GEN-LAST:event_montoFocusGained

    private void montoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_montoFocusLost
  Controladorcortedecaja.montoFocusLost();
    }//GEN-LAST:event_montoFocusLost

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
          Controladorventa.noduplicarcorte=false;
    }//GEN-LAST:event_formWindowClosed

    private void montoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_montoKeyReleased
  char tecla = evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
                  Controladorcortedecaja.botoncorte();
        }
    }//GEN-LAST:event_montoKeyReleased

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
            java.util.logging.Logger.getLogger(Cortecaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cortecaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cortecaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cortecaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cortecaja().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Corte_btncancelar;
    private javax.swing.JLabel Fecha;
    public static javax.swing.JLabel Gastosfromtoday;
    public static javax.swing.JLabel Reloj;
    public static javax.swing.JLabel Ventasfortoday1;
    public static javax.swing.JLabel aperturacantidad;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    public static javax.swing.JTextField monto;
    public static javax.swing.JLabel numerosdescuentos;
    public static javax.swing.JLabel pagosmadetoday;
    public static javax.swing.JLabel totaldescuentos;
    private javax.swing.JLabel user;
    private javax.swing.JLabel user1;
    // End of variables declaration//GEN-END:variables
}
