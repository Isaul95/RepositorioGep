package si;
import Controladores.Controlador_Report_Pdf_ReferenciaMayor;
import Controladores.Controlador_Report_pdf_cultivos;
import Controladores.Controlador_Report_pdf_paquetes;
import Controladores.Controlador_capturar_resultados;
import Controladores.Controladorventa;
import Modelos.Modelo_capturar_resultados;

public class Muestra_de_Cultivos extends javax.swing.JFrame { 
    public Muestra_de_Cultivos() {
        initComponents();
        id_venta.setText(String.valueOf(Controlador_capturar_resultados.id_a_actualizar_resultados));
Controladorventa.noduplicargastos=true;
Modelos.Modelo_Cultivos.LlenarTablaCultivos(jTableMuestraCultivos); // Se manda a llamr el llenado del tabla desde el modelo de los cultivos
        this.setLocationRelativeTo(null); // CENTRAR FORMULARIO  
    }
             
    @SuppressWarnings("unchecked") 
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableMuestraCultivos = new javax.swing.JTable();
        jLabel2 = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        id_venta = new javax.swing.JLabel();
        gastos_btn_back = new javax.swing.JButton();
        genetrar_Pdf = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(135, 193, 193));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CULTIVOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 24))); // NOI18N
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(135, 193, 193));
        jPanel2.setLayout(null);

        jTableMuestraCultivos = new javax.swing.JTable(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        jTableMuestraCultivos.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jTableMuestraCultivos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableMuestraCultivos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jTableMuestraCultivos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                jTableMuestraCultivosMousePressed(evt);
            }
        });
        jScrollPane1.setViewportView(jTableMuestraCultivos);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(0, 40, 560, 180);

        jLabel2.setBackground(new java.awt.Color(135, 193, 193));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel2.setText("Lista de estudios:");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(290, 10, 180, 29);

        jLabel80.setBackground(new java.awt.Color(0, 0, 0));
        jLabel80.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel80.setText("Venta:");
        jPanel2.add(jLabel80);
        jLabel80.setBounds(40, 10, 58, 24);

        id_venta.setBackground(new java.awt.Color(0, 0, 0));
        id_venta.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        id_venta.setForeground(new java.awt.Color(102, 102, 102));
        id_venta.setText("idVenta:");
        jPanel2.add(id_venta);
        id_venta.setBounds(120, 10, 74, 24);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 40, 570, 230);

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
        gastos_btn_back.setBounds(400, 270, 150, 50);

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
        genetrar_Pdf.setBounds(50, 270, 155, 60);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 577, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 339, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        Controladorventa.noduplicargastos=false;
    }//GEN-LAST:event_formWindowClosed

    private void gastos_btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gastos_btn_backActionPerformed
        dispose();
    }//GEN-LAST:event_gastos_btn_backActionPerformed

    private void jTableMuestraCultivosMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTableMuestraCultivosMousePressed
        int fila =jTableMuestraCultivos.getSelectedRow(); 
           int col =jTableMuestraCultivos.getSelectedColumn(); 
        if(fila>=0 || col==1){
        Captura_Bacterias a = new Captura_Bacterias(jTableMuestraCultivos.getValueAt(fila,0).toString());                
        new Captura_Bacterias().setVisible(true);
        }
    }//GEN-LAST:event_jTableMuestraCultivosMousePressed

    private void genetrar_PdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genetrar_PdfActionPerformed
        // GENERANDO PDF DE CLIENTES               
          if(Modelo_capturar_resultados.activarVistaParaCapturadeCultivosxx(Integer.valueOf(id_venta.getText()))>=1){
              //Controlador_Report_pdf_paquetes pac = new Controlador_Report_pdf_paquetes();
            //pac.Generacion_PDF_client_paquetes("_01");
            
            Controlador_Report_pdf_cultivos rc = new Controlador_Report_pdf_cultivos();
              rc.Generacion_PDF_cultivos();
              
              //JOptionPane.showMessageDialog(null, "Toy enyrando en el valor de resultadi igual a => +");
              
              
               if (Modelo_capturar_resultados.verificar_estudio_con_longitud_mayor(Integer.valueOf(Capturar_resultados.id_venta.getText())) >= 1) {
            //AQUÍ VA TU NUEVO PDF
            
//    PDF LARGO
//JOptionPane.showMessageDialog(null, "Se genera pdf de largos porque hay cultivos y largos");
            Controlador_Report_Pdf_ReferenciaMayor ref = new Controlador_Report_Pdf_ReferenciaMayor(); 
            ref.Generacion_PDF_client_referenciaMayor("_02");
//    PDF CORTO
            if (Modelo_capturar_resultados.verificar_estudio_con_longitud_menor(Integer.valueOf(Capturar_resultados.id_venta.getText())) >= 1) {
                Controlador_Report_pdf_paquetes pac = new Controlador_Report_pdf_paquetes();
                pac.Generacion_PDF_client_paquetes("_01"); // llamando el reporte de paketes
               // JOptionPane.showMessageDialog(null, "Se genera pdf de cortos porque  hubo cultivos y hay estudio largo");
            }                    // activarVistaParaCapturadeCultivos
        }        
                     
               else if(Modelo_capturar_resultados.verificar_estudio_con_longitud_menor(Integer.valueOf(id_venta.getText()))>=1){//PDF CON LONGITUDES DE ANALISIS MENORES A 68
            Controlador_Report_pdf_paquetes pac = new Controlador_Report_pdf_paquetes();
            pac.Generacion_PDF_client_paquetes("_01"); // llamando el reporte de paketes
            //JOptionPane.showMessageDialog(null, "Toy en el pdf corto...");                     
        } 
                            
            }
           
       
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
            java.util.logging.Logger.getLogger(Muestra_de_Cultivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Muestra_de_Cultivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Muestra_de_Cultivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Muestra_de_Cultivos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Muestra_de_Cultivos().setVisible(true);
            }
        });
    }
 


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton gastos_btn_back;
    public static javax.swing.JButton genetrar_Pdf;
    public static javax.swing.JLabel id_venta;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JTable jTableMuestraCultivos;
    // End of variables declaration//GEN-END:variables
}
