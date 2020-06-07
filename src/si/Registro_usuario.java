
package si;

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

public class Registro_usuario extends javax.swing.JFrame {
  
 //Nombre de algunas variables  //TCC = CAMPO DE TEXTO PARA CONFIRMAR CONTRASEÑA   //CC  = LABEL DE CONFIRMAR CONTRASEÑA
    int resultadoprimerusuario;
    String estadoactivo="Y";
     // boolean primerusuario; //Obtiene el id del proveedor
           // String datos_proveedores_o_usuarios[]= new String[3];
   Statement sent;  
  ResultSet rs;
      
    public Registro_usuario() {
        initComponents(); 
        Controladorventa.noduplicar_registro_usuario=true;
      setIconImage(getIconImage());  //La variable que le manda la imagen (DataMax) al proyecto 
        this.setLocationRelativeTo(null); // CENTRAR FORMULARIO
    }
      //  ICONO AL EJECUTAR EL PROYECTO
                 public Image getIconImage(){
                     Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Reportes/logoAlk.jpg"));
                     return retValue;
                 }
             // FIN DEL ICONO
    
    public void RetornarValoresRegistro(){ /* UN AVEZ K SE INGRESAN LOS DATOS RETORNA LOS VALORES DE LOS PLACEHOLD */
  user_nombre .setText("");
      user_nombre.setBackground(new Color(135,193,193));
           user_usuario .setText("");
               user_usuario.setBackground(new Color(135,193,193));
                    user_contra1.setText("");
                         user_contra1.setBackground(new Color(135,193,193));     
                          user_contra2.setText("");
                         user_contra2.setBackground(new Color(135,193,193));    
    }
 /*************** METODO de salida DONDE LE PREGUNTA AL USUARIO SI DESEA REALIZAR OTRA INSERCION DE USUARIO *******************/
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        user_nombre = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        user_usuario = new javax.swing.JTextField();
        B_registro = new javax.swing.JButton();
        user_contra1 = new javax.swing.JPasswordField();
        jLabel4 = new javax.swing.JLabel();
        user_contra2 = new javax.swing.JPasswordField();
        user_type = new javax.swing.JComboBox();
        jLabel5 = new javax.swing.JLabel();
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
        jLabel2.setText("Tipo de usuario:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 210, -1, 23));

        jLabel1.setBackground(new java.awt.Color(0, 0, 0));
        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(0, 0, 0));
        jLabel1.setText("Nombre:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 80, 23));

        user_nombre.setBackground(new java.awt.Color(135, 193, 193));
        user_nombre.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        user_nombre.setForeground(new java.awt.Color(0, 0, 0));
        user_nombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        user_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.add(user_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 500, -1));

        jLabel7.setBackground(new java.awt.Color(0, 0, 0));
        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(0, 0, 0));
        jLabel7.setText("Usuario:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 80, 23));

        user_usuario.setBackground(new java.awt.Color(135, 193, 193));
        user_usuario.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        user_usuario.setForeground(new java.awt.Color(0, 0, 0));
        user_usuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        user_usuario.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.add(user_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 500, -1));

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
        jPanel1.add(B_registro, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 230, 140, 40));

        user_contra1.setBackground(new java.awt.Color(135, 193, 193));
        user_contra1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        user_contra1.setForeground(new java.awt.Color(0, 0, 0));
        user_contra1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        user_contra1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.add(user_contra1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 500, -1));

        jLabel4.setBackground(new java.awt.Color(0, 0, 0));
        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(0, 0, 0));
        jLabel4.setText("Contraseña:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 23));

        user_contra2.setBackground(new java.awt.Color(135, 193, 193));
        user_contra2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        user_contra2.setForeground(new java.awt.Color(0, 0, 0));
        user_contra2.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        user_contra2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        jPanel1.add(user_contra2, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 160, 450, -1));

        user_type.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        user_type.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Usuario" }));
        user_type.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(135, 193, 193)));
        jPanel1.add(user_type, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 210, 190, -1));

        jLabel5.setBackground(new java.awt.Color(0, 0, 0));
        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(0, 0, 0));
        jLabel5.setText("Confirme contraseña");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 160, -1, 23));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 670, 280));

        jPanel2.setBackground(new java.awt.Color(135, 193, 193));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.setLayout(null);

        jLabel3.setBackground(new java.awt.Color(0, 0, 0));
        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Registrar nuevo usuario");
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
     // *************** --->> ADD NUEVO USUARIO  ***********************
                if(user_nombre .getText().equals("") || user_usuario .getText().equals("")||user_contra1.getText().equals("")){
            JOptionPane.showMessageDialog(null,"Antes de añadir un nuevo usuario, \n Favor de llenar todos los campos","                       Aviso",JOptionPane.WARNING_MESSAGE);
        } 
                else if(!user_contra1.getText().equals(user_contra2.getText())){
                    JOptionPane.showMessageDialog(null,"Las contraseñas no coinciden","Advertencia",JOptionPane.ERROR_MESSAGE);
                }
        else{
                                          try {
             sent = ca.createStatement(); 
             rs = sent.executeQuery("select * from user where  nombre='"+user_nombre.getText()+"'");
            while(rs.next()){        
            resultadoprimerusuario=Integer.parseInt(rs.getString(1)); //Obtiene el id del proveedor
            }
              if(resultadoprimerusuario!=0){ //si el id resultante de la consulta es diferente de 0 quiere decir que ya hay por lo menos una venta en el sistema
          JOptionPane.showMessageDialog(null,"Usuario ya registrado");
              }
              else {int id_permiso;
                  try{
                String sql = "INSERT INTO user( nombre, username, password, habilitado, id_permiso)  VALUES (?,?,?,?,?)";
                PreparedStatement pst = ca.prepareCall(sql);
                pst.setString(1,user_nombre.getText());
                pst.setString(2,user_usuario.getText());
                pst.setString(3,user_contra1.getText());
                pst.setString(4,estadoactivo);
                if(Registro_usuario.user_type.getSelectedItem().toString().equalsIgnoreCase("Administrador")){id_permiso=1;}
                else{id_permiso=2;}
                 pst.setInt(5,id_permiso);

                int a=pst.executeUpdate();
                if(a>0){
                    JOptionPane.showMessageDialog(null,"Usuario registrado","¡Exito al guardar!",JOptionPane.INFORMATION_MESSAGE);
                   RetornarValoresRegistro(); 
                }
            } catch(SQLException e)  {
                JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
            }
                  
              }
        }
                    catch (SQLException ex) {
            Logger.getLogger(Registro_usuario.class.getName()).log(Level.SEVERE, null, ex);
       }
            //FIN DE LA COMPROBACIÓN DEL PRIMER PROVEEDOR
                          }              
                
    }//GEN-LAST:event_B_registroActionPerformed
    private void B_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_cancelarActionPerformed
               this.dispose();   
    }//GEN-LAST:event_B_cancelarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
         Controladorventa.noduplicar_registro_usuario=false;
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
            java.util.logging.Logger.getLogger(Registro_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro_usuario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro_usuario().setVisible(true);
            }
        });
    }
    
    SI cc= new SI();
 Connection ca= cc.conexion();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_cancelar;
    public static javax.swing.JButton B_registro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPasswordField user_contra1;
    private javax.swing.JPasswordField user_contra2;
    private javax.swing.JTextField user_nombre;
    public static javax.swing.JComboBox user_type;
    private javax.swing.JTextField user_usuario;
    // End of variables declaration//GEN-END:variables

}
