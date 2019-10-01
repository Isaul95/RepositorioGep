
package si;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;


public class Pantalla_CorteCaja extends javax.swing.JFrame {

    Calendar fecha_actual = new GregorianCalendar();
    public Pantalla_CorteCaja() {
        initComponents();
        this.setLocationRelativeTo(null); // CENTRAR FORMULARIO
        Corte_Fecha.setCalendar(fecha_actual);
    }

    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableGastos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        Corte_Fecha = new com.toedter.calendar.JDateChooser();
        Corte_btncancelar = new javax.swing.JButton();
        Corte_btnImprimirticket = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        montoaentregar = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CORTE DE CAJA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 36))); // NOI18N
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setLayout(null);

        jTableGastos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id_corte", "Monto", "Fecha", "Hora", "Usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableGastos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTableGastos);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(20, 240, 610, 230);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Fecha:");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(10, 150, 90, 29);

        Corte_Fecha.setDateFormatString("yyy/MM/dd");
        Corte_Fecha.setEnabled(false);
        Corte_Fecha.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(Corte_Fecha);
        Corte_Fecha.setBounds(10, 180, 210, 40);

        Corte_btncancelar.setBackground(new java.awt.Color(242, 38, 19));
        Corte_btncancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Corte_btncancelar.setText("Cancelar");
        Corte_btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Corte_btncancelarActionPerformed(evt);
            }
        });
        jPanel2.add(Corte_btncancelar);
        Corte_btncancelar.setBounds(330, 10, 250, 60);

        Corte_btnImprimirticket.setBackground(new java.awt.Color(0, 148, 204));
        Corte_btnImprimirticket.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Corte_btnImprimirticket.setForeground(new java.awt.Color(255, 255, 255));
        Corte_btnImprimirticket.setText("Realizar Corte");
        Corte_btnImprimirticket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Corte_btnImprimirticketActionPerformed(evt);
            }
        });
        jPanel2.add(Corte_btnImprimirticket);
        Corte_btnImprimirticket.setBounds(70, 10, 250, 60);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Monto a Entregar:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(10, 80, 160, 29);

        montoaentregar.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        montoaentregar.setEnabled(false);
        jPanel2.add(montoaentregar);
        montoaentregar.setBounds(10, 110, 210, 40);

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

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Corte_btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Corte_btncancelarActionPerformed
        // BOTON DE CANCELAR LA INSERCION DE NUEVO USUARIO
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Regresar a pagina anterior?","                    Aviso",dialogButton);
        if(result == 0){
            dispose();   }
    }//GEN-LAST:event_Corte_btncancelarActionPerformed

    private void Corte_btnImprimirticketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Corte_btnImprimirticketActionPerformed
        // ABRE NUEVA VENTANA PARA Registro de Gastos
        new Apertura().setVisible(true);
    }//GEN-LAST:event_Corte_btnImprimirticketActionPerformed

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
            java.util.logging.Logger.getLogger(Pantalla_CorteCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pantalla_CorteCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pantalla_CorteCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pantalla_CorteCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pantalla_CorteCaja().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public com.toedter.calendar.JDateChooser Corte_Fecha;
    private javax.swing.JButton Corte_btnImprimirticket;
    private javax.swing.JButton Corte_btncancelar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTableGastos;
    public javax.swing.JTextField montoaentregar;
    // End of variables declaration//GEN-END:variables
}
