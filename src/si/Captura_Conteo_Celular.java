package si;
import Controladores.Controlador_Captura_ConteoCeular;
import Controladores.Controlador_Estudios_Coproparasitoscopico;
import Controladores.Controlador_Report_pdf_ConteoCelular;
import Controladores.Controlador_Report_pdf_Coproparasitos;
import Controladores.Controlador_capturar_resultados;
import Controladores.Controladorventa;

public class Captura_Conteo_Celular extends javax.swing.JFrame {
public static String producto="";
   
    public Captura_Conteo_Celular() {
        initComponents();
        Controladorventa.noduplicarcultivos = true;
         id_venta.setText(String.valueOf(Controlador_capturar_resultados.id_a_actualizar_resultados));         
        this.setLocationRelativeTo(null); // CENTRAR FORMULARIO  
    }    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel81 = new javax.swing.JLabel();
        txtConteo2 = new javax.swing.JTextField();
        jLabel83 = new javax.swing.JLabel();
        GuardarDatosCultivos = new javax.swing.JButton();
        txtConteo1 = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableCoproparasitos = new javax.swing.JTable();
        gastos_btn_back = new javax.swing.JButton();
        jLabel82 = new javax.swing.JLabel();
        id_venta = new javax.swing.JLabel();
        genetrar_Pdf = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(135, 193, 193));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Captura Conteo Celular", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 32))); // NOI18N
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(135, 193, 193));
        jPanel2.setLayout(null);

        jLabel81.setBackground(new java.awt.Color(0, 0, 0));
        jLabel81.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel81.setText("Valor (%):");
        jPanel2.add(jLabel81);
        jLabel81.setBounds(10, 130, 190, 21);

        txtConteo2.setBackground(new java.awt.Color(135, 193, 193));
        txtConteo2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtConteo2.setToolTipText("");
        txtConteo2.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel2.add(txtConteo2);
        txtConteo2.setBounds(10, 160, 180, 40);

        jLabel83.setBackground(new java.awt.Color(0, 0, 0));
        jLabel83.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jLabel83.setText("Celula:");
        jPanel2.add(jLabel83);
        jLabel83.setBounds(10, 30, 260, 21);

        GuardarDatosCultivos.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        GuardarDatosCultivos.setText("Guardar");
        GuardarDatosCultivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                GuardarDatosCultivosActionPerformed(evt);
            }
        });
        jPanel2.add(GuardarDatosCultivos);
        GuardarDatosCultivos.setBounds(20, 240, 170, 50);

        txtConteo1.setBackground(new java.awt.Color(135, 193, 193));
        txtConteo1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        txtConteo1.setToolTipText("");
        txtConteo1.setBorder(new javax.swing.border.LineBorder(new java.awt.Color(255, 255, 255), 1, true));
        jPanel2.add(txtConteo1);
        txtConteo1.setBounds(10, 70, 180, 40);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 50, 240, 310);

        jTableCoproparasitos = new javax.swing.JTable(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        jTableCoproparasitos.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTableCoproparasitos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableCoproparasitos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTableCoproparasitos);

        jPanel1.add(jScrollPane1);
        jScrollPane1.setBounds(260, 110, 440, 190);

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
        jPanel1.add(gastos_btn_back);
        gastos_btn_back.setBounds(590, 40, 150, 50);

        jLabel82.setBackground(new java.awt.Color(0, 0, 0));
        jLabel82.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel82.setText("Venta:");
        jPanel1.add(jLabel82);
        jLabel82.setBounds(260, 60, 58, 24);

        id_venta.setBackground(new java.awt.Color(0, 0, 0));
        id_venta.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        id_venta.setForeground(new java.awt.Color(102, 102, 102));
        id_venta.setText("idVenta:");
        jPanel1.add(id_venta);
        id_venta.setBounds(340, 60, 74, 24);

        genetrar_Pdf.setBackground(new java.awt.Color(255, 255, 255));
        genetrar_Pdf.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        genetrar_Pdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/pdf.png"))); // NOI18N
        genetrar_Pdf.setText("Generar PDF");
        genetrar_Pdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genetrar_PdfActionPerformed(evt);
            }
        });
        jPanel1.add(genetrar_Pdf);
        genetrar_Pdf.setBounds(530, 320, 230, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 781, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 379, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel1.getAccessibleContext().setAccessibleName("Captura Coproparasitos");

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        Controladorventa.noduplicarcultivos=false;
    }//GEN-LAST:event_formWindowClosed

    private void gastos_btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gastos_btn_backActionPerformed
        dispose();
    }//GEN-LAST:event_gastos_btn_backActionPerformed

    private void GuardarDatosCultivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_GuardarDatosCultivosActionPerformed
        Controlador_Captura_ConteoCeular.insertResultadosDeConteoCelular2022();
        
    }//GEN-LAST:event_GuardarDatosCultivosActionPerformed

    private void genetrar_PdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genetrar_PdfActionPerformed
        // GENERANDO PDF DE CLIENTES
       
    Controlador_Report_pdf_ConteoCelular cc = new Controlador_Report_pdf_ConteoCelular();
     cc.Generacion_PDF_ConteoCelular();

          

    }//GEN-LAST:event_genetrar_PdfActionPerformed

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
            java.util.logging.Logger.getLogger(Captura_Conteo_Celular.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Captura_Conteo_Celular.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Captura_Conteo_Celular.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Captura_Conteo_Celular.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
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
                new Captura_Conteo_Celular().setVisible(true);
            }
        });
    }
 


    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton GuardarDatosCultivos;
    private javax.swing.JButton gastos_btn_back;
    public static javax.swing.JButton genetrar_Pdf;
    public static javax.swing.JLabel id_venta;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTableCoproparasitos;
    public static javax.swing.JTextField txtConteo1;
    public static javax.swing.JTextField txtConteo2;
    // End of variables declaration//GEN-END:variables
}
