/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si;
import Controladores.Controladorventa;
import Controladores.Controlador_Report_pdf;
import Controladores.Controlador_Report_pdf_paquetes;
import Controladores.Controlador_capturar_resultados;
import Modelos.Modelo_capturar_resultados;
import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
/**
 *
 * @author Alexis
 */
public class Capturar_resultados extends javax.swing.JFrame {
public static boolean seagregoexterno=false;
 static SI cc= new SI();
    /**
     * Creates new form Entradaproductos
     */
public Capturar_resultados(int id_venta_a_capturar_resultados){
    Controlador_capturar_resultados.id_a_actualizar_resultados=id_venta_a_capturar_resultados; //Aqui se guarda el id de la venta de a cual se van a actualizar los datos
    Modelo_capturar_resultados.id_paciente(Controlador_capturar_resultados.id_a_actualizar_resultados);
} 
    public Capturar_resultados() {
        initComponents();
        Controladorventa.noduplicar_capturaresultados=true;
        Controlador_capturar_resultados.metodos_al_iniciar_entradasproductos0(Controlador_capturar_resultados.id_a_actualizar_resultados);//Aqui se envia al controlador el id para traer todos los datos para capturar los resultados
        id_venta.setText(String.valueOf(Controlador_capturar_resultados.id_a_actualizar_resultados));
        id_paciente.setText(String.valueOf(Controlador_capturar_resultados.id_paciente));
        Controlador_capturar_resultados.se_puede_habilitar_el_boton(Controlador_capturar_resultados.id_a_actualizar_resultados);
         this.setLocationRelativeTo(null); // CENTRAR FORMULARIO  
         envio_email.setEnabled(false);
    }
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        agregar_articulo = new javax.swing.JPanel();
        producto_sobrante3 = new javax.swing.JPanel();
        jPanel33 = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Jtable_ProductosEntradas = new javax.swing.JTable();
        fecha_hora_ingreso = new javax.swing.JLabel();
        id_venta = new javax.swing.JLabel();
        paciente = new javax.swing.JLabel();
        edad = new javax.swing.JLabel();
        sexo = new javax.swing.JLabel();
        fecha_nacimiento = new javax.swing.JLabel();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jLabel84 = new javax.swing.JLabel();
        jLabel85 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        genetrar_Pdf = new javax.swing.JButton();
        gastos_btn_back = new javax.swing.JButton();
        envio_email = new javax.swing.JButton();
        jLabel86 = new javax.swing.JLabel();
        id_paciente = new javax.swing.JLabel();
        medico = new javax.swing.JLabel();
        jLabel83 = new javax.swing.JLabel();
        jLabel87 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        agregar_articulo.setBackground(new java.awt.Color(0, 51, 102));
        agregar_articulo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        agregar_articulo.setDoubleBuffered(false);
        agregar_articulo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        producto_sobrante3.setBackground(new java.awt.Color(135, 193, 193));
        producto_sobrante3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        producto_sobrante3.setDoubleBuffered(false);
        producto_sobrante3.setLayout(null);

        jPanel33.setBackground(new java.awt.Color(135, 193, 193));
        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Captura de resultados", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Serif", 1, 36))); // NOI18N
        jPanel33.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Jtable_ProductosEntradas = new javax.swing.JTable(){
            public boolean isCellEditable(int filas, int columnas){
                if(columnas==1){
                    return true;
                }else{
                    return false;
                }
            }
        };
        Jtable_ProductosEntradas.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        Jtable_ProductosEntradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        Jtable_ProductosEntradas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Jtable_ProductosEntradasMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(Jtable_ProductosEntradas);

        jPanel33.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 320, 770, 200));

        fecha_hora_ingreso.setBackground(new java.awt.Color(0, 0, 0));
        fecha_hora_ingreso.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        fecha_hora_ingreso.setForeground(new java.awt.Color(102, 102, 102));
        fecha_hora_ingreso.setText("dbFecha_ingreso:");
        jPanel33.add(fecha_hora_ingreso, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 280, 230, -1));

        id_venta.setBackground(new java.awt.Color(0, 0, 0));
        id_venta.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        id_venta.setForeground(new java.awt.Color(102, 102, 102));
        id_venta.setText("idVenta:");
        jPanel33.add(id_venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 170, -1));

        paciente.setBackground(new java.awt.Color(0, 0, 0));
        paciente.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        paciente.setForeground(new java.awt.Color(102, 102, 102));
        paciente.setText("dbPaciente:");
        jPanel33.add(paciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 630, -1));

        edad.setBackground(new java.awt.Color(0, 0, 0));
        edad.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        edad.setForeground(new java.awt.Color(102, 102, 102));
        edad.setText("dbEdad:");
        jPanel33.add(edad, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 160, 180, -1));

        sexo.setBackground(new java.awt.Color(0, 0, 0));
        sexo.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        sexo.setForeground(new java.awt.Color(102, 102, 102));
        sexo.setText("dbSexo:");
        jPanel33.add(sexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 200, 270, -1));

        fecha_nacimiento.setBackground(new java.awt.Color(0, 0, 0));
        fecha_nacimiento.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        fecha_nacimiento.setForeground(new java.awt.Color(102, 102, 102));
        fecha_nacimiento.setText("dbFecha_nacimiento:");
        jPanel33.add(fecha_nacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, 260, -1));

        jLabel80.setBackground(new java.awt.Color(0, 0, 0));
        jLabel80.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel80.setText("Venta:");
        jPanel33.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 50, 80, -1));

        jLabel81.setBackground(new java.awt.Color(0, 0, 0));
        jLabel81.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel81.setText("Paciente:");
        jPanel33.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 80, 90, -1));

        jLabel82.setBackground(new java.awt.Color(0, 0, 0));
        jLabel82.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel82.setText("Edad:");
        jPanel33.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 160, 60, -1));

        jLabel84.setBackground(new java.awt.Color(0, 0, 0));
        jLabel84.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel84.setText("Sexo:");
        jPanel33.add(jLabel84, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 200, 60, -1));

        jLabel85.setBackground(new java.awt.Color(0, 0, 0));
        jLabel85.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel85.setText("Fecha de nacimiento:");
        jPanel33.add(jLabel85, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 240, 230, -1));

        jLabel75.setBackground(new java.awt.Color(0, 0, 0));
        jLabel75.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel75.setText("Fecha de ingreso:");
        jPanel33.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 280, 230, -1));

        genetrar_Pdf.setBackground(new java.awt.Color(255, 255, 255));
        genetrar_Pdf.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        genetrar_Pdf.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/pdf.png"))); // NOI18N
        genetrar_Pdf.setText("Generar PDF");
        genetrar_Pdf.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                genetrar_PdfActionPerformed(evt);
            }
        });
        jPanel33.add(genetrar_Pdf, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 530, 170, 60));

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
        jPanel33.add(gastos_btn_back, new org.netbeans.lib.awtextra.AbsoluteConstraints(660, 30, 140, 40));

        envio_email.setBackground(new java.awt.Color(255, 255, 255));
        envio_email.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        envio_email.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/email.png"))); // NOI18N
        envio_email.setText("Enviar Email");
        envio_email.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                envio_emailActionPerformed(evt);
            }
        });
        jPanel33.add(envio_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 530, 170, 60));

        jLabel86.setBackground(new java.awt.Color(0, 0, 0));
        jLabel86.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel86.setText("No. paciente");
        jPanel33.add(jLabel86, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 120, -1));

        id_paciente.setBackground(new java.awt.Color(0, 0, 0));
        id_paciente.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        id_paciente.setForeground(new java.awt.Color(102, 102, 102));
        id_paciente.setText("idVenta:");
        jPanel33.add(id_paciente, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 50, 160, -1));

        medico.setBackground(new java.awt.Color(0, 0, 0));
        medico.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        medico.setForeground(new java.awt.Color(102, 102, 102));
        medico.setText("dbPaciente:");
        jPanel33.add(medico, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 630, -1));

        jLabel83.setBackground(new java.awt.Color(0, 0, 0));
        jLabel83.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jLabel83.setText("Medico:");
        jPanel33.add(jLabel83, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 120, 80, -1));

        jLabel87.setBackground(new java.awt.Color(255, 153, 153));
        jLabel87.setFont(new java.awt.Font("Dialog", 1, 21)); // NOI18N
        jLabel87.setForeground(new java.awt.Color(0, 0, 0));
        jLabel87.setText("Ruta de archivos: C:/reportes");
        jLabel87.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel87.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel87MouseClicked(evt);
            }
        });
        jPanel33.add(jLabel87, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 550, 300, -1));

        producto_sobrante3.add(jPanel33);
        jPanel33.setBounds(10, 10, 810, 600);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(producto_sobrante3, javax.swing.GroupLayout.PREFERRED_SIZE, 834, javax.swing.GroupLayout.PREFERRED_SIZE))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(420, Short.MAX_VALUE)
                    .addComponent(agregar_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(420, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(14, Short.MAX_VALUE)
                .addComponent(producto_sobrante3, javax.swing.GroupLayout.PREFERRED_SIZE, 620, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(agregar_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void gastos_btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gastos_btn_backActionPerformed
       dispose();
    }//GEN-LAST:event_gastos_btn_backActionPerformed

    private void genetrar_PdfActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_genetrar_PdfActionPerformed
        // GENERANDO PDF DE LCIENTES
        if(Modelo_capturar_resultados.HAY_UN_PAQUETE_EN_LA_VENTA(Controlador_capturar_resultados.id_a_actualizar_resultados)){
            //JOptionPane.showMessageDialog(null,"HAY UN PAQUETE EN LA VENTA");
            Controlador_Report_pdf_paquetes pac = new Controlador_Report_pdf_paquetes();
            pac.Generacion_PDF_client_paquetes(); // llamando el reporte de paketes
        }else{
             Controlador_Report_pdf n = new Controlador_Report_pdf();
      n.Generacion_PDF_client(); // llamando el metodo con el obj  reporte  normal
        }
    }//GEN-LAST:event_genetrar_PdfActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        Controladorventa.noduplicar_capturaresultados=false;
    }//GEN-LAST:event_formWindowClosed

    private void envio_emailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_envio_emailActionPerformed
        if(Controladorventa.noduplicar_envio_email==false){
        new Envio_email().setVisible(true);
        Envio_email.File.setText(Capturar_resultados.id_paciente.getText()+"_"+Capturar_resultados.paciente.getText()+".pdf");        
        }
    }//GEN-LAST:event_envio_emailActionPerformed

    private void jLabel87MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel87MouseClicked
        try {
            Desktop.getDesktop().open(new File("C:\\reportes"));
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null,"La carpeta no existe.","Error, verifique",JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_jLabel87MouseClicked

    private void Jtable_ProductosEntradasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jtable_ProductosEntradasMouseClicked
        //Capturar el valor de referencia
         int fila =Jtable_ProductosEntradas.getSelectedRow();
           int col =Jtable_ProductosEntradas.getSelectedColumn(); 
         if(fila>=0 && col==0){
              if(Controladorventa.noduplicar_capturar_valor_de_referencia==false){
                  Capturar_referencia a   =new Capturar_referencia(Jtable_ProductosEntradas.getValueAt(fila, 0).toString());
             new Capturar_referencia().setVisible(true);
              }
              else{
                  System.out.println("Ya se abrio la pestaña"+fila+" col"+col);
                          
              }
         }
         else{
                 System.out.println("no es la primer columna"+fila+" col"+col);
      }
      
    }//GEN-LAST:event_Jtable_ProductosEntradasMouseClicked

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
            java.util.logging.Logger.getLogger(Capturar_resultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Capturar_resultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Capturar_resultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Capturar_resultados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Capturar_resultados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JTable Jtable_ProductosEntradas;
    public static javax.swing.JPanel agregar_articulo;
    public static javax.swing.JLabel edad;
    public static javax.swing.JButton envio_email;
    public static javax.swing.JLabel fecha_hora_ingreso;
    public static javax.swing.JLabel fecha_nacimiento;
    private javax.swing.JButton gastos_btn_back;
    public static javax.swing.JButton genetrar_Pdf;
    public static javax.swing.JLabel id_paciente;
    public static javax.swing.JLabel id_venta;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel84;
    private javax.swing.JLabel jLabel85;
    private javax.swing.JLabel jLabel86;
    private javax.swing.JLabel jLabel87;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JLabel medico;
    public static javax.swing.JLabel paciente;
    public static javax.swing.JPanel producto_sobrante3;
    public static javax.swing.JLabel sexo;
    // End of variables declaration//GEN-END:variables
}
