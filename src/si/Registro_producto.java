
package si;

import Controladores.Controlador_registro_producto;
import Controladores.Controladorventa;
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
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
//import static si.menu_principal.proem;

// import static si.SI_Inicio.text_user;

public class Registro_producto extends javax.swing.JFrame {
  
 //Nombre de algunas variables  //TCC = CAMPO DE TEXTO PARA CONFIRMAR CONTRASEÑA   //CC  = LABEL DE CONFIRMAR CONTRASEÑA
    int resultadoprimerusuario;
    String estadoactivo="Activo";
     // boolean primerusuario; //Obtiene el id del proveedor
           // String datos_proveedores_o_usuarios[]= new String[3];
   Statement sent;  
  ResultSet rs;
    private Object Controladro_registro_producto;
      
    public Registro_producto() {
        initComponents(); 
        Controladorventa.noduplicar_nuevo_producto=true;
        Controlador_registro_producto.metodos_al_abrir_registro_productos();
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
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        estudio = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        precio = new javax.swing.JTextField();
        B_registro = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        tipodecategoria = new javax.swing.JComboBox<String>();
        valor_referencia = new javax.swing.JTextField();
        unidades = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        reimprimirventa = new javax.swing.JCheckBox();
        nueva_categoria = new javax.swing.JTextField();
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

        jLabel2.setBackground(new java.awt.Color(0, 0, 0));
        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(0, 0, 0));
        jLabel2.setText("referencia:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, -1, 23));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Estudio:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 80, 23));

        estudio.setBackground(new java.awt.Color(135, 193, 193));
        estudio.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        estudio.setForeground(new java.awt.Color(0, 0, 0));
        estudio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        estudio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.add(estudio, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 500, -1));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Precio:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 280, 100, 23));

        precio.setBackground(new java.awt.Color(135, 193, 193));
        precio.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        precio.setForeground(new java.awt.Color(0, 0, 0));
        precio.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        precio.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.add(precio, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 280, 120, -1));

        B_registro.setBackground(new java.awt.Color(255, 255, 255));
        B_registro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        B_registro.setForeground(new java.awt.Color(204, 0, 0));
        B_registro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/casilla-de-verificacion (1).png"))); // NOI18N
        B_registro.setText("Registrar");
        B_registro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_registroActionPerformed(evt);
            }
        });
        jPanel1.add(B_registro, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 320, 140, 40));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Unidades:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, 23));

        jLabel8.setBackground(new java.awt.Color(0, 0, 0));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(0, 0, 0));
        jLabel8.setText("Categoria:");
        jPanel1.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 80, 100, 23));

        tipodecategoria.setBackground(new java.awt.Color(135, 193, 193));
        tipodecategoria.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        tipodecategoria.setForeground(new java.awt.Color(0, 0, 0));
        jPanel1.add(tipodecategoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 80, 500, -1));

        valor_referencia.setBackground(new java.awt.Color(135, 193, 193));
        valor_referencia.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        valor_referencia.setForeground(new java.awt.Color(0, 0, 0));
        valor_referencia.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        valor_referencia.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.add(valor_referencia, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 500, -1));

        unidades.setBackground(new java.awt.Color(135, 193, 193));
        unidades.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        unidades.setForeground(new java.awt.Color(0, 0, 0));
        unidades.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        unidades.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.add(unidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 180, 500, -1));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Valor de");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, 100, 23));

        reimprimirventa.setBackground(new java.awt.Color(135, 193, 193));
        reimprimirventa.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        reimprimirventa.setText("Nueva categoria");
        reimprimirventa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.add(reimprimirventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 170, -1));

        nueva_categoria.setBackground(new java.awt.Color(135, 193, 193));
        nueva_categoria.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        nueva_categoria.setForeground(new java.awt.Color(0, 0, 0));
        nueva_categoria.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        nueva_categoria.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.add(nueva_categoria, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 130, 470, -1));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 670, 370));

        jPanel2.setBackground(new java.awt.Color(135, 193, 193));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.setLayout(null);

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Registro Nuevo Usuario");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(210, 10, 230, 30);

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
        B_cancelar.setBounds(600, 0, 60, 40);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 670, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void B_registroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_registroActionPerformed
       Controlador_registro_producto.registrar_nuevo_producto();
    }//GEN-LAST:event_B_registroActionPerformed
    private void B_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_cancelarActionPerformed
               this.dispose();   
    }//GEN-LAST:event_B_cancelarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        Controladorventa.noduplicar_nuevo_producto=false;
    }//GEN-LAST:event_formWindowClosed

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
            java.util.logging.Logger.getLogger(Registro_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro_producto.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro_producto().setVisible(true);
            }
        });
    }
    
    SI cc= new SI();
 Connection ca= cc.conexion();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_cancelar;
    public static javax.swing.JButton B_registro;
    public static javax.swing.JTextField estudio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JTextField nueva_categoria;
    public static javax.swing.JTextField precio;
    public static javax.swing.JCheckBox reimprimirventa;
    public static javax.swing.JComboBox<String> tipodecategoria;
    public static javax.swing.JTextField unidades;
    public static javax.swing.JTextField valor_referencia;
    // End of variables declaration//GEN-END:variables

}
