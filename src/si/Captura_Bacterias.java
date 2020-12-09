package si;
import Controladores.Controlador_Estudios_Cultivos;
import Controladores.Controlador_capturar_resultados;
import Controladores.Controladorventa;

public class Captura_Bacterias extends javax.swing.JFrame {
public static String producto="";
   //  private String bacteria;
    // private String porcentaje;
  Captura_Bacterias(String producto) {
       this.producto=producto;
    }
    public Captura_Bacterias() {
        initComponents();
        Controladorventa.noduplicarcultivos = true;
         id_venta.setText(String.valueOf(Controlador_capturar_resultados.id_a_actualizar_resultados));
         nombre_estudio.setText(this.producto);         
        Modelos.Modelo_Cultivos.LlenarTabladeBacterias(jTablebacterias); // Se inicializa la tablla con los datos precargados
        this.setLocationRelativeTo(null); // CENTRAR FORMULARIO  
    }    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        GuardarDatosCultivos = new javax.swing.JButton();
        jLabel80 = new javax.swing.JLabel();
        id_venta = new javax.swing.JLabel();
        nombre_estudio = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        txtporcentaje = new javax.swing.JTextField();
        txtbacteria = new javax.swing.JTextField();
        jLabel82 = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        gastos_btn_back = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTablebacterias = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(135, 193, 193));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Capturar bacterias", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 32))); // NOI18N
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(135, 193, 193));
        jPanel2.setLayout(null);

        GuardarDatosCultivos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        GuardarDatosCultivos.setText("Guardar");
        GuardarDatosCultivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarDatosCultivosActionPerformed(evt);
            }
        });
        jPanel2.add(GuardarDatosCultivos);
        GuardarDatosCultivos.setBounds(290, 110, 170, 50);

        jLabel80.setBackground(new java.awt.Color(0, 0, 0));
        jLabel80.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel80.setText("Estudio:");
        jPanel2.add(jLabel80);
        jLabel80.setBounds(530, 10, 110, 24);

        id_venta.setBackground(new java.awt.Color(0, 0, 0));
        id_venta.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        id_venta.setForeground(new java.awt.Color(102, 102, 102));
        id_venta.setText("idVenta:");
        jPanel2.add(id_venta);
        id_venta.setBounds(400, 10, 74, 24);

        nombre_estudio.setBackground(new java.awt.Color(0, 0, 0));
        nombre_estudio.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        nombre_estudio.setText("Estudio");
        jPanel2.add(nombre_estudio);
        nombre_estudio.setBounds(420, 50, 220, 30);

        jLabel81.setBackground(new java.awt.Color(0, 0, 0));
        jLabel81.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel81.setText("Crecimiento:");
        jPanel2.add(jLabel81);
        jLabel81.setBounds(10, 90, 190, 24);

        txtporcentaje.setBackground(new java.awt.Color(135, 193, 193));
        txtporcentaje.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtporcentaje.setToolTipText("");
        txtporcentaje.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel2.add(txtporcentaje);
        txtporcentaje.setBounds(10, 120, 260, 40);

        txtbacteria.setBackground(new java.awt.Color(135, 193, 193));
        txtbacteria.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtbacteria.setToolTipText("");
        txtbacteria.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel2.add(txtbacteria);
        txtbacteria.setBounds(10, 40, 260, 40);

        jLabel82.setBackground(new java.awt.Color(0, 0, 0));
        jLabel82.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel82.setText("Venta:");
        jPanel2.add(jLabel82);
        jLabel82.setBounds(320, 10, 58, 24);

        jLabel83.setBackground(new java.awt.Color(0, 0, 0));
        jLabel83.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel83.setText("Microorganismo en desarrollo:");
        jPanel2.add(jLabel83);
        jLabel83.setBounds(10, 10, 260, 21);

        gastos_btn_back.setBackground(new java.awt.Color(255, 255, 255));
        gastos_btn_back.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        gastos_btn_back.setForeground(new java.awt.Color(255, 0, 0));
        gastos_btn_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/salir-flecha-derecha (1).png"))); // NOI18N
        gastos_btn_back.setText("Regresar");
        gastos_btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gastos_btn_backActionPerformed(evt);
            }
        });
        jPanel2.add(gastos_btn_back);
        gastos_btn_back.setBounds(480, 110, 150, 50);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 50, 650, 170);

        jTablebacterias = new javax.swing.JTable(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        jTablebacterias.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTablebacterias.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTablebacterias.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTablebacterias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTablebacteriasMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTablebacterias);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(40, 230, 560, 170);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 669, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        Controladorventa.noduplicarcultivos=false;
    }//GEN-LAST:event_formWindowClosed

    private void gastos_btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gastos_btn_backActionPerformed
        dispose();
    }//GEN-LAST:event_gastos_btn_backActionPerformed

    private void GuardarDatosCultivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarDatosCultivosActionPerformed
        Controlador_Estudios_Cultivos.insertBacterias();
        
    }//GEN-LAST:event_GuardarDatosCultivosActionPerformed

    private void jTablebacteriasMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTablebacteriasMousePressed

        int fila =jTablebacterias.getSelectedRow(); 
           int col =jTablebacterias.getSelectedColumn(); 
        if(fila>=0 || col==1){
        Captura_Resultados_Cultivos a =new Captura_Resultados_Cultivos(jTablebacterias.getValueAt(fila, 0).toString(), jTablebacterias.getValueAt(fila, 1).toString());
//Captura_Bacterias a = new Captura_Bacterias(jTableMuestraCultivos.getValueAt(fila,0).toString());        
        new Captura_Resultados_Cultivos().setVisible(true);
        //new Captura_Bacterias().setVisible(true);
        }
        
    }//GEN-LAST:event_jTablebacteriasMousePressed

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
            java.util.logging.Logger.getLogger(Captura_Bacterias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Captura_Bacterias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Captura_Bacterias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Captura_Bacterias.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Captura_Bacterias().setVisible(true);
            }
        });
    }
 


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton GuardarDatosCultivos;
    private javax.swing.JButton gastos_btn_back;
    public static javax.swing.JLabel id_venta;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTablebacterias;
    public static javax.swing.JLabel nombre_estudio;
    public static javax.swing.JTextField txtbacteria;
    public static javax.swing.JTextField txtporcentaje;
    // End of variables declaration//GEN-END:variables
}
