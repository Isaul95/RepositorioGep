
package si;

import Controladores.Controlador_registro_paquete;
import static Controladores.Controlador_registro_paquete.concatenartodoslosidpaquetedelatabla;
import Controladores.Controlador_registro_producto;
import Controladores.Controladorventa;
import Modelos.Modelo_registro_paquete;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.imageio.ImageIO;
import javax.swing.JCheckBox;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
//import static si.menu_principal.proem;

// import static si.SI_Inicio.text_user;

public class Registro_paquete extends javax.swing.JFrame {
    private Object Controladro_registro_producto;
      
    public Registro_paquete() {
        initComponents(); 
        Controladorventa.noduplicar_nuevo_paquete=true;
        Controlador_registro_paquete.metodos_al_abrir_registro_paquetes();
      setIconImage(getIconImage());  //La variable que le manda la imagen (DataMax) al proyecto 
        this.setLocationRelativeTo(null); // CENTRAR FORMULARIO
    }
      //  ICONO AL EJECUTAR EL PROYECTO
                 public Image getIconImage(){
                     Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Reportes/logoAlk.jpg"));
                     return retValue;
                 }
             // FIN DEL ICONO
 
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel8 = new javax.swing.JLabel();
        paquetes = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        Tablepaquetes = new javax.swing.JTable();
        paquetes_sinproducto = new javax.swing.JComboBox<>();
        jLabel9 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        Tablepaquetessinanalisis = new javax.swing.JTable();
        agregartodos1 = new javax.swing.JButton();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        B_cancelar = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(135, 193, 193));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Paquetes existentes:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 290, 190, 23));

        paquetes.setBackground(new java.awt.Color(135, 193, 193));
        paquetes.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        paquetes.setForeground(new java.awt.Color(0, 0, 0));
        paquetes.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                paquetesItemStateChanged(evt);
            }
        });
        jPanel1.add(paquetes, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 320, 500, -1));

        Tablepaquetes = new javax.swing.JTable(){
            public boolean isCellEditable(int filas, int columnas){
                if(columnas!=0){
                    return true;
                }else{
                    return false;
                }
            }
        };
        Tablepaquetes.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane1.setViewportView(Tablepaquetes);

        jPanel1.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 370, 1290, 210));

        paquetes_sinproducto.setBackground(new java.awt.Color(135, 193, 193));
        paquetes_sinproducto.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        paquetes_sinproducto.setForeground(new java.awt.Color(0, 0, 0));
        paquetes_sinproducto.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                paquetes_sinproductoItemStateChanged(evt);
            }
        });
        jPanel1.add(paquetes_sinproducto, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 500, -1));

        jLabel9.setBackground(new java.awt.Color(0, 0, 0));
        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(0, 0, 0));
        jLabel9.setText("Paquetes sin análisis");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 0, 190, 23));

        Tablepaquetessinanalisis = new javax.swing.JTable(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        Tablepaquetessinanalisis.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(Tablepaquetessinanalisis);

        jPanel1.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 1290, 210));

        agregartodos1.setBackground(new java.awt.Color(255, 255, 255));
        agregartodos1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        agregartodos1.setForeground(new java.awt.Color(204, 0, 0));
        agregartodos1.setText("Agregar todos");
        agregartodos1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregartodos1ActionPerformed(evt);
            }
        });
        jPanel1.add(agregartodos1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 20, -1, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 40, 1300, 610));

        jPanel2.setBackground(new java.awt.Color(135, 193, 193));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.setLayout(null);

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Agregar productos a paquetes");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(510, 0, 310, 30);

        B_cancelar.setBackground(new java.awt.Color(255, 255, 255));
        B_cancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        B_cancelar.setForeground(new java.awt.Color(204, 0, 0));
        B_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/salir-flecha-derecha (1).png"))); // NOI18N
        B_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_cancelarActionPerformed(evt);
            }
        });
        jPanel2.add(B_cancelar);
        B_cancelar.setBounds(1240, 0, 60, 40);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1300, 40));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void B_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_cancelarActionPerformed
               this.dispose();   
    }//GEN-LAST:event_B_cancelarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        Controladorventa.noduplicar_nuevo_paquete=false;
    }//GEN-LAST:event_formWindowClosed

    private void paquetesItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_paquetesItemStateChanged
      Controlador_registro_paquete.llenartabladeproductospertenecientesapaquete(paquetes.getSelectedItem().toString().substring(8));
      System.out.println(paquetes.getSelectedItem().toString().substring(8));
    }//GEN-LAST:event_paquetesItemStateChanged

    private void paquetes_sinproductoItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_paquetes_sinproductoItemStateChanged
        // TODO add your handling code here:
    }//GEN-LAST:event_paquetes_sinproductoItemStateChanged

    private void agregartodos1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregartodos1ActionPerformed
   try{
        Modelo_registro_paquete.agregar_los_id_al_paquete(concatenartodoslosidpaquetedelatabla());    
    }catch(StringIndexOutOfBoundsException NO){
    JOptionPane.showMessageDialog(null, "No hay análisis que agregar","Por favor verifique",JOptionPane.ERROR_MESSAGE);
     }
    }//GEN-LAST:event_agregartodos1ActionPerformed
   
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
            java.util.logging.Logger.getLogger(Registro_paquete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro_paquete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro_paquete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro_paquete.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
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
                new Registro_paquete().setVisible(true);
            }
        });
    }
    
    SI cc= new SI();
 Connection ca= cc.conexion();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_cancelar;
    public static javax.swing.JTable Tablepaquetes;
    public static javax.swing.JTable Tablepaquetessinanalisis;
    private javax.swing.JButton agregartodos1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JComboBox<String> paquetes;
    public static javax.swing.JComboBox<String> paquetes_sinproducto;
    // End of variables declaration//GEN-END:variables

}
