package si;
import Controladores.Controlador_Estudios_Cultivos;
import Controladores.Controlador_capturar_resultados;
import Controladores.Controladorventa;

public class Captura_Resultados_Cultivos extends javax.swing.JFrame {
public static String producto="";
    public Captura_Resultados_Cultivos() {
        initComponents();
        Controladorventa.noduplicarcultivos = true;
         id_venta.setText(String.valueOf(Controlador_capturar_resultados.id_a_actualizar_resultados));
         nombre_estudio.setText(this.producto);
        Modelos.Modelo_Cultivos.LlenarTabladeResultadosInterpretaciones(jTableInterpretaciones); // Se inicializa la tablla con los datos precargados
        this.setLocationRelativeTo(null); // CENTRAR FORMULARIO  
    }

   /* Captura_Resultados_Cultivos(int parseInt, String toString) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    } */

    Captura_Resultados_Cultivos(String producto) {
       this.producto=producto;
    }
             
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableInterpretaciones = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        txtdescripcionq = new javax.swing.JTextField();
        gastos_btn_back = new javax.swing.JButton();
        ComboResultados = new javax.swing.JComboBox<>();
        GuardarDatosCultivos = new javax.swing.JButton();
        jLabel80 = new javax.swing.JLabel();
        id_venta = new javax.swing.JLabel();
        nombre_estudio = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(135, 193, 193));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Capturar resultados de cultivos", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 36))); // NOI18N
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(135, 193, 193));
        jPanel2.setLayout(null);

        jTableInterpretaciones = new javax.swing.JTable(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        jTableInterpretaciones.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTableInterpretaciones.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableInterpretaciones.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTableInterpretaciones);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(0, 180, 660, 210);

        jLabel2.setBackground(new java.awt.Color(135, 193, 193));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel2.setText("Antimicrobiano:");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(20, 0, 220, 29);

        jLabel3.setBackground(new java.awt.Color(135, 193, 193));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel3.setText("Interpretación:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(20, 70, 160, 29);

        txtdescripcionq.setBackground(new java.awt.Color(135, 193, 193));
        txtdescripcionq.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtdescripcionq.setToolTipText("");
        txtdescripcionq.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel2.add(txtdescripcionq);
        txtdescripcionq.setBounds(20, 30, 460, 40);

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
        gastos_btn_back.setBounds(500, 20, 150, 50);

        ComboResultados.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        ComboResultados.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Susceptible", "Resistente", "Intermedio" }));
        jPanel2.add(ComboResultados);
        ComboResultados.setBounds(20, 110, 250, 40);

        GuardarDatosCultivos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        GuardarDatosCultivos.setText("Guardar");
        GuardarDatosCultivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarDatosCultivosActionPerformed(evt);
            }
        });
        jPanel2.add(GuardarDatosCultivos);
        GuardarDatosCultivos.setBounds(290, 80, 120, 40);

        jLabel80.setBackground(new java.awt.Color(0, 0, 0));
        jLabel80.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel80.setText("Venta:");
        jPanel2.add(jLabel80);
        jLabel80.setBounds(460, 90, 58, 24);

        id_venta.setBackground(new java.awt.Color(0, 0, 0));
        id_venta.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        id_venta.setForeground(new java.awt.Color(102, 102, 102));
        id_venta.setText("idVenta:");
        jPanel2.add(id_venta);
        id_venta.setBounds(540, 90, 74, 24);

        nombre_estudio.setBackground(new java.awt.Color(0, 0, 0));
        nombre_estudio.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        nombre_estudio.setText("Estudio");
        jPanel2.add(nombre_estudio);
        nombre_estudio.setBounds(390, 130, 250, 30);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 50, 670, 400);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 741, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 497, Short.MAX_VALUE)
                .addContainerGap())
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
        Controlador_Estudios_Cultivos.insertResultadosCultivos();
    }//GEN-LAST:event_GuardarDatosCultivosActionPerformed

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
            java.util.logging.Logger.getLogger(Captura_Resultados_Cultivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Captura_Resultados_Cultivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Captura_Resultados_Cultivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Captura_Resultados_Cultivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Captura_Resultados_Cultivos().setVisible(true);
            }
        });
    }
 


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JComboBox<String> ComboResultados;
    public static javax.swing.JButton GuardarDatosCultivos;
    private javax.swing.JButton gastos_btn_back;
    public static javax.swing.JLabel id_venta;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTableInterpretaciones;
    public static javax.swing.JLabel nombre_estudio;
    public static javax.swing.JTextField txtdescripcionq;
    // End of variables declaration//GEN-END:variables
}
