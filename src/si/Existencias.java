package si;
import Controladores.Controladorexistencias;
import Controladores.Controladorventa;
import java.awt.event.KeyEvent;


public class Existencias extends javax.swing.JFrame{

  

 public Existencias() {
        initComponents();
         Controladorventa.noduplicarexistencias=true;
        this.setLocationRelativeTo(null); // CENTRAR FORMULARIO
        Controladorexistencias.metodosalabrirexistencias();
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Corte_btncancelar = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        cambiocombobox = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        subtotal = new javax.swing.JLabel();
        descuentolabel = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        labeldescuento = new javax.swing.JLabel();
        descuentocombo = new javax.swing.JLabel();
        monto = new javax.swing.JTextField();
        descuentolabel1 = new javax.swing.JLabel();
        reimprimirventa = new javax.swing.JCheckBox();
        jPanel28 = new javax.swing.JPanel();
        Existencias = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "EXISTENCIAS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 36))); // NOI18N
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setLayout(null);

        Corte_btncancelar.setBackground(new java.awt.Color(0, 51, 102));
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

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Puedes buscar desde aquí tu producto");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(0, 0, 340, 29);

        jPanel10.setBackground(new java.awt.Color(0, 51, 102));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Venta   ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cambiocombobox.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        cambiocombobox.setForeground(new java.awt.Color(255, 0, 0));
        cambiocombobox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cambiocombobox.setText("00.00");
        jPanel10.add(cambiocombobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, 140, 28));

        jLabel31.setFont(new java.awt.Font("Trebuchet MS", 1, 22)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Cambio:");
        jPanel10.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 140, -1));

        jLabel30.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Subtotal");
        jLabel30.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel10.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 150, -1));

        subtotal.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        subtotal.setForeground(new java.awt.Color(255, 255, 255));
        subtotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subtotal.setText("00.00");
        jPanel10.add(subtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 140, 28));

        descuentolabel.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        descuentolabel.setForeground(new java.awt.Color(255, 255, 255));
        descuentolabel.setText("Pago");
        descuentolabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel10.add(descuentolabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 190, -1));

        total.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        total.setForeground(new java.awt.Color(255, 255, 255));
        total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total.setText("00.00");
        jPanel10.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 150, 50));

        labeldescuento.setFont(new java.awt.Font("Trebuchet MS", 1, 22)); // NOI18N
        labeldescuento.setForeground(new java.awt.Color(255, 255, 255));
        labeldescuento.setText("Descuento:");
        jPanel10.add(labeldescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 170, -1));

        descuentocombo.setFont(new java.awt.Font("Arial Black", 1, 28)); // NOI18N
        descuentocombo.setForeground(new java.awt.Color(255, 0, 51));
        descuentocombo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descuentocombo.setText("00.00");
        jPanel10.add(descuentocombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 140, -1));

        monto.setBackground(new java.awt.Color(0, 148, 204));
        monto.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        monto.setForeground(new java.awt.Color(255, 255, 255));
        monto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        monto.setText("00.00");
        monto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
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
        jPanel10.add(monto, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 150, -1));

        descuentolabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        descuentolabel1.setForeground(new java.awt.Color(255, 255, 255));
        descuentolabel1.setText("Total");
        descuentolabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel10.add(descuentolabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 190, -1));

        reimprimirventa.setBackground(new java.awt.Color(0, 51, 102));
        reimprimirventa.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        reimprimirventa.setForeground(new java.awt.Color(255, 255, 255));
        reimprimirventa.setText("Reimprimirventa");
        jPanel10.add(reimprimirventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 240, -1));

        jPanel2.add(jPanel10);
        jPanel10.setBounds(400, 150, 430, 268);

        jPanel28.setBackground(new java.awt.Color(0, 51, 102));
        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ventanas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Existencias.setBackground(new java.awt.Color(255, 255, 255));
        Existencias.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Existencias.setForeground(new java.awt.Color(204, 0, 0));
        Existencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/actualizar.png"))); // NOI18N
        Existencias.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Existencias.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Existencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExistenciasActionPerformed(evt);
            }
        });
        jPanel28.add(Existencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 30, 90, 70));

        jPanel2.add(jPanel28);
        jPanel28.setBounds(0, 80, 130, 100);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 50, 1000, 480);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 1030, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 17, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Corte_btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Corte_btncancelarActionPerformed
        this.dispose();
    }//GEN-LAST:event_Corte_btncancelarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
          Controladorventa.noduplicarexistencias=false;
    }//GEN-LAST:event_formWindowClosed

    private void montoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_montoFocusGained
        Controladorventa.montoFocusGained();
    }//GEN-LAST:event_montoFocusGained

    private void montoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_montoFocusLost
        Controladorventa.montoFocusLost();
    }//GEN-LAST:event_montoFocusLost

    private void montoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_montoKeyReleased
        char tecla = evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            Controladorventa.montoKeyRealeased();

        }
    }//GEN-LAST:event_montoKeyReleased

    private void ExistenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExistenciasActionPerformed
        if(Controladorventa.noduplicarexistencias==false){new Existencias().setVisible(true);}
    }//GEN-LAST:event_ExistenciasActionPerformed

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
            java.util.logging.Logger.getLogger(Existencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Existencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Existencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Existencias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Existencias().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Corte_btncancelar;
    private javax.swing.JButton Existencias;
    public static javax.swing.JLabel cambiocombobox;
    public static javax.swing.JLabel descuentocombo;
    public static javax.swing.JLabel descuentolabel;
    private javax.swing.JLabel descuentolabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel28;
    public static javax.swing.JLabel labeldescuento;
    public static javax.swing.JTextField monto;
    public static javax.swing.JCheckBox reimprimirventa;
    public static javax.swing.JLabel subtotal;
    public static javax.swing.JLabel total;
    // End of variables declaration//GEN-END:variables
}
