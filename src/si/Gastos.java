package si;
import Controladores.Controladorgastos;
import Controladores.Controladorventa;
import java.awt.Color;
import java.awt.event.KeyEvent;


public class Gastos extends javax.swing.JFrame { 
    public Gastos() {
        initComponents();
Controladorventa.noduplicargastos=true;
        this.setLocationRelativeTo(null); // CENTRAR FORMULARIO  
    }
             
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableGastos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        txtmonto = new javax.swing.JTextField();
        txtdescripcion = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        busquedagastos = new javax.swing.JTextField();
        gastos_btn_back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GASTOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 36))); // NOI18N
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setLayout(null);

        jTableGastos = new javax.swing.JTable(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        jTableGastos.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTableGastos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableGastos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTableGastos);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(10, 240, 650, 230);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Descripción del Gasto:");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(10, 10, 220, 29);

        txtmonto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtmonto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                txtmontoKeyReleased(evt);
            }
        });
        jPanel2.add(txtmonto);
        txtmonto.setBounds(10, 110, 210, 40);
        jPanel2.add(txtdescripcion);
        txtdescripcion.setBounds(10, 40, 480, 40);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Monto del Gasto:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(10, 80, 160, 29);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Busqueda de Gastos");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(10, 150, 250, 40);

        busquedagastos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedagastosKeyReleased(evt);
            }
        });
        jPanel2.add(busquedagastos);
        busquedagastos.setBounds(10, 200, 220, 30);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 50, 690, 480);

        gastos_btn_back.setBackground(new java.awt.Color(255, 255, 255));
        gastos_btn_back.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        gastos_btn_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/salir-flecha-derecha (1).png"))); // NOI18N
        gastos_btn_back.setText("Regresar");
        gastos_btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gastos_btn_backActionPerformed(evt);
            }
        });
        jPanel1.add(gastos_btn_back);
        gastos_btn_back.setBounds(530, 0, 150, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        Controladorventa.noduplicargastos=false;
    }//GEN-LAST:event_formWindowClosed

    private void gastos_btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gastos_btn_backActionPerformed
        dispose();
    }//GEN-LAST:event_gastos_btn_backActionPerformed

    private void busquedagastosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedagastosKeyReleased
        Controladorgastos.busquedadegastos();
    }//GEN-LAST:event_busquedagastosKeyReleased

    private void txtmontoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txtmontoKeyReleased
        char tecla = evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            Controladorgastos.insercionengastos();
        }
    }//GEN-LAST:event_txtmontoKeyReleased

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
            java.util.logging.Logger.getLogger(Gastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Gastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Gastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Gastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Gastos().setVisible(true);
            }
        });
    }
 


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTextField busquedagastos;
    private javax.swing.JButton gastos_btn_back;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTableGastos;
    public static javax.swing.JTextField txtdescripcion;
    public static javax.swing.JTextField txtmonto;
    // End of variables declaration//GEN-END:variables
}
