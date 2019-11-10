package si;

import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
//import net.sf.jasperreports.engine.JasperReport;  //import net.sf.jasperreports.view.JasperViewer;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;

public class Reportes extends javax.swing.JFrame {
    
 ResultSet rs; 
 private final String logotipo = "/Reportes/logo1.jpeg";
                 /**       Creates new form Reportes      */
    public Reportes() {
        initComponents();
                setIconImage(getIconImage());  //La variable que le manda la imagen (DataMax) al proyecto 
    this.setLocationRelativeTo(null); // CENTRAR FORMULARIO
       
    }
    String fechadesde="",fechahasta="", fechahoy="";
    
     //  ICONO AL EJECUTAR EL PROYECTO
                 public Image getIconImage(){
                     Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Reportes/logo5.png"));
                     return retValue;
                 }
             // FIN DEL ICONO

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jComboBox1 = new javax.swing.JComboBox<>();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jPanel10 = new javax.swing.JPanel();
        jButton1 = new javax.swing.JButton();
        fechadehoy = new com.toedter.calendar.JDateChooser();
        jPanel1 = new javax.swing.JPanel();
        B_cancelar = new javax.swing.JButton();
        jPanel9 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jButton2 = new javax.swing.JButton();
        fechahastacalendario = new com.toedter.calendar.JDateChooser();
        fechadesdecalendario = new com.toedter.calendar.JDateChooser();

        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 51, 51));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(0, 204, 0));
        jPanel2.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Reportes Detalles de ventas");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(240, 0, 270, 50);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 730, 50));

        jPanel10.setBackground(new java.awt.Color(0, 51, 102));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Reportes por día ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel10.setFocusCycleRoot(true);
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/pdf.png"))); // NOI18N
        jButton1.setText("Generar Reporte");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 240, 74));

        fechadehoy.setBackground(new java.awt.Color(0, 153, 204));
        fechadehoy.setForeground(new java.awt.Color(0, 96, 255));
        fechadehoy.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jPanel10.add(fechadehoy, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 240, 40));

        getContentPane().add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 280, 210));
        jPanel10.getAccessibleContext().setAccessibleName("   Reportes X día");

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        B_cancelar.setBackground(new java.awt.Color(0, 51, 102));
        B_cancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        B_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/salir-flecha-derecha (1).png"))); // NOI18N
        B_cancelar.setText("cancelar");
        B_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_cancelarActionPerformed(evt);
            }
        });

        jPanel9.setBackground(new java.awt.Color(0, 51, 102));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Reportes Rango de Fechas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText(" A");
        jPanel9.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 30, 60, 40));

        jButton2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/pdf.png"))); // NOI18N
        jButton2.setText("Generar Reporte");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        jPanel9.add(jButton2, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 100, 260, 74));

        fechahastacalendario.setBackground(new java.awt.Color(0, 153, 204));
        fechahastacalendario.setForeground(new java.awt.Color(0, 96, 255));
        fechahastacalendario.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jPanel9.add(fechahastacalendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 30, 180, 40));

        fechadesdecalendario.setBackground(new java.awt.Color(0, 153, 204));
        fechadesdecalendario.setForeground(new java.awt.Color(0, 96, 255));
        fechadesdecalendario.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jPanel9.add(fechadesdecalendario, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 180, 40));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap(305, Short.MAX_VALUE)
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 415, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(251, 251, 251)
                .addComponent(B_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 206, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel9, javax.swing.GroupLayout.PREFERRED_SIZE, 208, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(B_cancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 54, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(16, Short.MAX_VALUE))
        );

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 730, 300));

        pack();
    }// </editor-fold>//GEN-END:initComponents
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
   // generate report x dia seleccionado 
          int dialogButton = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Desea Generar Reporte De las Ventas ?", "REPORTE GENRAL DE PROVEEDORES ",dialogButton);
        if(result == 0){
            try {
                 llenarfechadehoy();
         Map parametro = new HashMap(); /* parameter1 <<-- ESTE PARAMETRO VIENE DESDE EL REPORTE SOLO SE ESTA LLAMANDO */
         parametro.put("parameter1",fechahoy); 
         parametro.put("logo", this.getClass().getResourceAsStream(logotipo)); 
                JasperReport reporte = null;
                String path = "src/Reportes/Reporte_detalleVentas.jasper";
                

              // reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                 reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Reporte_detalleVentas.jasper")); /*ASI MANDO A LLAMAR LOS REPORTES CON .jar */
                /* ========================= LLENADO DEL REPORTE  ======================  */
                //  path --> LA RUTA DEL REPORTE
                //     --> LOS PARAMETROS K SE LE PUEDE ENVIAR ALA REPORTE IN THIS CASE ES NULL y la concion-->(ca) B.D
                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, ca);

                /* ========================= CREAR LA VISTA DEL REPORTE  ======================  */
                JasperViewer vista = new JasperViewer(jprint, false);

                /* ============= UN CIERRE LA VISTA DEL REPORTE CUANDO SE PRESIONE LA X de cerrar ============  */
                vista.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                /* ==================== MOSTRAR CMO VISIBLE ESTE REPORTE  ======================  */
                vista.setVisible(true);
                vista.setTitle("REPORTE DETALLES DE VENTAS");
            } catch (JRException ex) {
                Logger.getLogger(menu_principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }//GEN-LAST:event_jButton1ActionPerformed

     public String llenarfechadesde(){ // Ordena la fecha del componente Jcalendar  que esta de la sig. manera:  dia /mes / aÃ±o, lo cual para la base de datos no es la manera correcta de ingresarlo, sino asÃ­: aÃ±o/mes/dia
        
       int año= fechadesdecalendario.getCalendar().get(Calendar.YEAR);
       int mes= fechadesdecalendario.getCalendar().get(Calendar.MONTH)+1;
       int dia= fechadesdecalendario.getCalendar().get(Calendar.DAY_OF_MONTH);
       fechadesde= año+"-"+mes+"-"+dia;
        return fechadesde;
    }
    
     public String llenarfechahasta(){ // Ordena la fecha del componente Jcalendar  que esta de la sig. manera:  dia /mes / aÃ±o, lo cual para la base de datos no es la manera correcta de ingresarlo, sino asÃ­: aÃ±o/mes/dia
        
       int año= fechahastacalendario.getCalendar().get(Calendar.YEAR);
       int mes= fechahastacalendario.getCalendar().get(Calendar.MONTH)+1;
       int dia= fechahastacalendario.getCalendar().get(Calendar.DAY_OF_MONTH);
       fechahasta= año+"-"+mes+"-"+dia;
        return fechahasta;
    }
      
     public String llenarfechadehoy(){ 
        
       int año= fechadehoy.getCalendar().get(Calendar.YEAR);
       int mes= fechadehoy.getCalendar().get(Calendar.MONTH)+1;
       int dia= fechadehoy.getCalendar().get(Calendar.DAY_OF_MONTH);
       fechahoy= año+"/"+mes+"/"+dia;
        return fechahoy;
    }
     
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // GENERAR REPORTES POR FECHAS SELECCIONADAS
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Desea Generar Reporte De una Fecha Determinda?", "REPORTE GENRAL DE LA VENTAS ",dialogButton);
        if(result == 0){
            try {
                llenarfechadesde();
                llenarfechahasta();
                           Map parametro = new HashMap();
                            parametro.put("parameter2", fechadesde); 
                            parametro.put("parameter3",fechahasta ); 
                            parametro.put("logo", this.getClass().getResourceAsStream(logotipo)); 
                JasperReport reporte = null;
                String path = "src/Reportes/VentasXFechas.jasper";

              // reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                 reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/VentasXFechas.jasper")); /*ASI MANDO A LLAMAR LOS REPORTES CON .jar */
                /* ========================= LLENADO DEL REPORTE  ======================  */
                //  path --> LA RUTA DEL REPORTE
                //     --> LOS PARAMETROS K SE LE PUEDE ENVIAR ALA REPORTE IN THIS CASE ES NULL y la concion-->(ca) B.D
                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, ca);

                /* ========================= CREAR LA VISTA DEL REPORTE  ======================  */
                JasperViewer vista = new JasperViewer(jprint, false);

                /* ============= UN CIERRE LA VISTA DEL REPORTE CUANDO SE PRESIONE LA X de cerrar ============  */
                vista.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                /* ==================== MOSTRAR CMO VISIBLE ESTE REPORTE  ======================  */
                vista.setVisible(true);
                vista.setTitle("REPORTE DETALLES DE VENTAS");
            } catch (JRException ex) {
                Logger.getLogger(menu_principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }        
    }//GEN-LAST:event_jButton2ActionPerformed

    private void B_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_cancelarActionPerformed
        // BOTON DE CANCELAR LA INSERCION DE NUEVO USUARIO
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Desea cancelar", "                Aviso",dialogButton);
        if(result == 0){
            dispose();   }
    }//GEN-LAST:event_B_cancelarActionPerformed

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
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reportes.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reportes().setVisible(true);
            }
        });
    }
    
    SI cc= new SI();
 Connection ca= cc.conexion();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_cancelar;
    private com.toedter.calendar.JDateChooser fechadehoy;
    private com.toedter.calendar.JDateChooser fechadesdecalendario;
    private com.toedter.calendar.JDateChooser fechahastacalendario;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JComboBox<String> jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel9;
    // End of variables declaration//GEN-END:variables
}
