package si;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import javax.swing.JOptionPane;
import java.sql.*;
import java.util.logging.Level;
import java.sql.Connection;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import java.io.*;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;

public class SI_Inicio extends javax.swing.JFrame {
      int timer; //variable conteo de los intentos de acceso
    /**
     * Creates new form SI_Inicio  ============ placeholrd --->> https://www.youtube.com/watch?v=oYs4mPvfNzU   new diseños -- https://www.youtube.com/watch?v=XAowXcmQ-kA
     */
    public SI_Inicio() {
        initComponents();
        fechadehoy=fecha();
        setIconImage(getIconImage());  //La variable que le manda la imagen (DataMax) al proyecto 
        this.setLocationRelativeTo(null);
    }
    
         //  ICONO AL EJECUTAR EL PROYECTO
                 public Image getIconImage(){
                     Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Reportes/logo5.png"));
                     return retValue;
                 }
             // FIN DEL ICONO

    public void RetornaValor(){ /* UN AVEZ K SE INGRESAN LOS DATOS RETORNA LOS VALORES DE LOS PLACEHOLD */
   text_user.setText("Ingresa Usuario");
   text_user.setFont(new Font("Tahoma",Font.BOLD, 17));
   text_user.setForeground(new Color(236, 240, 241));
                 pass_user.setText("**********");
                 pass_user.setFont(new Font("Tahoma",Font.BOLD, 17));
                 pass_user.setForeground(new Color(236, 240, 241));
               /*               promater.setText("");
                              promater.setFont(new Font("Tahoma",Font.BOLD, 17));
                              promater.setForeground(new Color(236, 240, 241)); */
         }
    
    
      String user,estadoinactivo="Inactivo", pass;
      int resultopen, aperturahecha, resultadoclose, cierrehecho;
      int id_usuario=0;
      String fechadehoy;
    String []datos = new String[4];

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        usuario = new javax.swing.JButton();
        admin = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        text_user = new javax.swing.JTextField();
        pass_user = new javax.swing.JPasswordField();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        pass_user1 = new javax.swing.JPasswordField();
        iduser = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        usuario.setBackground(new java.awt.Color(0, 111, 153));
        usuario.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        usuario.setForeground(new java.awt.Color(255, 255, 255));
        usuario.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/jefe.png"))); // NOI18N
        usuario.setText("Usuario");
        usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                usuarioActionPerformed(evt);
            }
        });
        usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                usuarioKeyPressed(evt);
            }
        });
        getContentPane().add(usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 410, 200, 50));

        admin.setBackground(new java.awt.Color(0, 102, 153));
        admin.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        admin.setForeground(new java.awt.Color(255, 255, 255));
        admin.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/gerente (1).png"))); // NOI18N
        admin.setText("Administrador");
        admin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                adminActionPerformed(evt);
            }
        });
        getContentPane().add(admin, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 410, 200, 50));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Usuario:");
        getContentPane().add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 310, -1, -1));

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Contraseña:");
        getContentPane().add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 350, -1, -1));

        jButton1.setBackground(new java.awt.Color(0, 51, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/salir-flecha-derecha (1).png"))); // NOI18N
        jButton1.setText("Salir");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 10, -1, 50));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 370, 200, 10));

        jSeparator3.setCursor(new java.awt.Cursor(java.awt.Cursor.CROSSHAIR_CURSOR));
        getContentPane().add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 330, 200, 10));

        text_user.setBackground(new java.awt.Color(0, 0, 51));
        text_user.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        text_user.setForeground(new java.awt.Color(255, 255, 255));
        text_user.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        text_user.setText("Ingresa Usuario");
        text_user.setBorder(null);
        text_user.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        text_user.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                text_userFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                text_userFocusLost(evt);
            }
        });
        text_user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                text_userMouseClicked(evt);
            }
        });
        text_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                text_userActionPerformed(evt);
            }
        });
        text_user.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                text_userKeyPressed(evt);
            }
        });
        getContentPane().add(text_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 310, 200, -1));

        pass_user.setBackground(new java.awt.Color(0, 0, 51));
        pass_user.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        pass_user.setForeground(new java.awt.Color(255, 255, 255));
        pass_user.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pass_user.setText("**********");
        pass_user.setBorder(null);
        pass_user.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pass_userFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pass_userFocusLost(evt);
            }
        });
        pass_user.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pass_userMouseClicked(evt);
            }
        });
        getContentPane().add(pass_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, 200, -1));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 370, 200, 10));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/User.png"))); // NOI18N
        jLabel5.setText("U");
        getContentPane().add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 20, 260, -1));

        jLabel4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/imagenes/F22.jpg"))); // NOI18N
        jLabel4.setText("jLabel3");
        getContentPane().add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 480, 490));

        pass_user1.setBackground(new java.awt.Color(0, 0, 51));
        pass_user1.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        pass_user1.setForeground(new java.awt.Color(255, 255, 255));
        pass_user1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pass_user1.setText("**********");
        pass_user1.setBorder(null);
        pass_user1.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pass_user1FocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pass_user1FocusLost(evt);
            }
        });
        pass_user1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                pass_user1MouseClicked(evt);
            }
        });
        getContentPane().add(pass_user1, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 350, 200, -1));

        iduser.setBackground(new java.awt.Color(0, 0, 51));
        iduser.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        iduser.setForeground(new java.awt.Color(255, 255, 255));
        iduser.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        iduser.setText("Ingresa Usuario");
        iduser.setBorder(null);
        iduser.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        iduser.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                iduserFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                iduserFocusLost(evt);
            }
        });
        iduser.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                iduserMouseClicked(evt);
            }
        });
        iduser.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                iduserActionPerformed(evt);
            }
        });
        iduser.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                iduserKeyPressed(evt);
            }
        });
        getContentPane().add(iduser, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 380, 200, -1));

        pack();
    }// </editor-fold>//GEN-END:initComponents
public static String fecha(){ /* SE DECARA LA FECHA DEL SISTEMA */
        java.util.Date fecha=new java.util.Date();
        SimpleDateFormat formatoFecha= new SimpleDateFormat("YYYY/MM/dd");
        return formatoFecha.format(fecha);
    }
    public void yaseabriosistema(){
             //metodo agregado 11 septiembre 2018
             try {
             Statement sent = ca.createStatement();   
              ResultSet rs = sent.executeQuery("select * from apertura where fecha='"+fechadehoy+"' ");
            while(rs.next()){        
            resultopen=Integer.parseInt(rs.getString(1)); //Obtiene el id de la venta
            }
            if(resultopen!=0){ //si el id resultante de la consulta es diferente de 0 quiere decir que ya hay por lo menos una venta en el sistema
            aperturahecha=1; //entonces el valor de "primerventa" se convertirá en 1, indicando que ya hay por lo menos una venta
            }
        } 
             catch (SQLException ex) {
            Logger.getLogger(menu_principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    public void yacerrosistema(){
             //metodo agregado 11 septiembre 2018
             try {
             Statement sent = ca.createStatement();   
              ResultSet rs = sent.executeQuery("select * from cortes where fecha='"+fechadehoy+"' ");
            while(rs.next()){        
            resultadoclose=Integer.parseInt(rs.getString(1)); //Obtiene el id de la venta
            }
            if(resultadoclose!=0){ //si el id resultante de la consulta es diferente de 0 quiere decir que ya hay por lo menos una venta en el sistema
            cierrehecho=1; //entonces el valor de "primerventa" se convertirá en 1, indicando que ya hay por lo menos una venta
            }
        } 
             catch (SQLException ex) {
            Logger.getLogger(menu_principal.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    private void usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_usuarioActionPerformed
         
        // BOTON DE INGRESO PARA LOS USUARIOS
     //if (text_user.getText().isEmpty() || pass_user.getText().isEmpty()) { // si los campos estan vacias
     if (text_user.getText().equals("Ingresa Usuario") || pass_user.getText().equals("********")) { // 
          JOptionPane.showMessageDialog(null, "Por favor Inserte su Usuario y Contraseña  \nPara Ingresar", "ALERTA", JOptionPane.WARNING_MESSAGE);
        } else { 
        user=text_user.getText();
      pass=pass_user.getText(); //se guardan los datos del usuario
        
    try {
            Statement st = ca.createStatement();
            ResultSet rs= st.executeQuery( "SELECT * FROM user WHERE nombre_usuario='"+text_user.getText()+"' or contraseña='"+pass_user.getText()+"'");
            while(rs.next()){ //ciclo para leer los datos en la variable rs
            datos[0]=rs.getString("nombre_usuario"); 
            datos[1]=rs.getString("contraseña");
            datos[2]=rs.getString("estado_activo_inactivo");
            id_usuario=rs.getInt("id_usuario");
            iduser.setText(String.valueOf(id_usuario));
            }
                if(datos[2].equals("Inactivo")){
                JOptionPane.showMessageDialog(null,"Usuario Inactivo, por favor comunicate con tu administrador para recuperar tu usuario","               Lo sentimos",JOptionPane.WARNING_MESSAGE);
            }
            
            else{
                    yacerrosistema();
                     if(resultadoclose>0){
                  JOptionPane.showMessageDialog(null,"Ya se hizo corte de caja, por lo tanto no se puede abrir hasta el día de mañana"," Espera un momento",JOptionPane.INFORMATION_MESSAGE); //Msg de bienvenida                                                                     
           
             }
                     
              else if(user.equals(datos[0])&&pass.equals(datos[1])){ //comparacion entre lo escrito por el usuario y lo almacenado en la base de datos
                JOptionPane.showMessageDialog(null,"Bienvenido a nuestro Sistema: \nUsuario:" +user," Acceso Concedido",JOptionPane.INFORMATION_MESSAGE); //Msg de bienvenida                                                                     
             yaseabriosistema();
             
             if(aperturahecha==0){//Si el valor de apertua es mayo a 0, no se abrirá la ventana de apertura
                 
                 this.setVisible(false);  
                                     // HERE 
                              new Apertura().setVisible(true);
                               this.setIconImage(null);
             }
            
             else{
                  //new menu_principal().setVisible(true);
             this.setVisible(false);  
                                     // HERE 
                              menu_principal m= new menu_principal();
                               this.setIconImage(null);
             }
            
                }else{
                    JOptionPane.showMessageDialog(null,"Usuario o Contraseña incorrecto \n Intentelo Nuevamente \n Resta " + (3 - timer) + " Intentos","Error",JOptionPane.WARNING_MESSAGE); //Msg de error
                     text_user.setText("");
                     pass_user.setText("");
                 timer = timer + 1;
                }
                 if(timer == 3){
                       JOptionPane.showMessageDialog(null,"Excedido el número de intentos \n Intentelo mas tarde ","Error de Ingreso",JOptionPane.ERROR_MESSAGE); //Msg de error
                       //Procediendo a bloquear usuario
                        try{
           PreparedStatement ps = ca.prepareStatement ("UPDATE user SET estado_activo_inactivo='"+estadoinactivo+"'WHERE id_usuario='"+id_usuario+"'");
                ps.executeUpdate();
 JOptionPane.showMessageDialog(null,"Tu usuario ha sido bloqueado, por favor comunicate con tu administrador para recuperar tu usuario","Lo sentimos",JOptionPane.WARNING_MESSAGE);
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage());
        } 
                       //Procediendo a bloquear usuario
                       System.exit(0);  //Y ya una vez bloqueado el usuario, el programa se cerrara automaticamente
                     }
            }
                 
        } catch (SQLException ex) {
            Logger.getLogger(SI_Inicio.class.getName()).log(Level.SEVERE, null, ex);
        }
           catch (NullPointerException  NPE){
               JOptionPane.showMessageDialog(null, "Tranquilo(a), no has escrito correctamente el nombre de usuario ni la contraseña \nINTENTE INICIAR SESION COMO ADMIN O REGISTRARSE","Tomalo con calma",JOptionPane.WARNING_MESSAGE);
  
               text_user.setText("");
                    pass_user.setText("");
               timer=0;
           }
     }
    }//GEN-LAST:event_usuarioActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
     int dialogButton = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Desea salir de Inicio?", "Aviso",dialogButton);
           if(result == 0){
               System.exit(0);   }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void usuarioKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_usuarioKeyPressed
     // JOptionPane.showMessageDialog(null,"Hola");    //este evento funciona para cuando tu le das clic al boton "usuario"
    }//GEN-LAST:event_usuarioKeyPressed

    private void adminActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_adminActionPerformed
        // INGRESO DE LOS ADMIN AL SISTEMA
       // if (text_user.getText().isEmpty() || pass_user.getText().isEmpty()) {
       if (text_user.getText().equals("Ingresa Usuario") || pass_user.getText().equals("********")) { // 
            JOptionPane.showMessageDialog(null, "Por favor Inserte su Usuario y Contraseña de ADMINISTRADOR \nPara Ingresar", "ALERTA", JOptionPane.WARNING_MESSAGE);
        } else {

            user=text_user.getText();
            pass=pass_user.getText(); //se guardan los datos del admi
 
            String []datos = new String[2];
            try {
                Statement st = ca.createStatement();
                ResultSet rs= st.executeQuery("select * from admin");
                //ResultSet rs= st.executeQuery( "SELECT * FROM user WHERE nombre_usuario='"+text_user.getText()+"' and contraseña='"+pass_user.getText()+"'");
                while(rs.next()){ //ciclo para leer los datos en la variable rs
                    datos[0]=rs.getString("nombre_usuario");
                    datos[1]=rs.getString("contraseña");
                }
                if(user.equals(datos[0])&&pass.equals(datos[1])){ //comparacion entre lo escrito por el usuario y lo almacenado en la base de datos
                    JOptionPane.showMessageDialog(null,"Bienvenido Administrador:\n" +user," Acceso Concedido",JOptionPane.INFORMATION_MESSAGE); //Msg de bienvenida
                    new menu_principal().setVisible(true);
                    this.setVisible(false);
                             this.setIconImage(null);
                }
                else if(timer == 3){
                    JOptionPane.showMessageDialog(null,"Excedido el número de intentos \n Intentelo mas tarde ","Error de Ingreso",JOptionPane.ERROR_MESSAGE); //Msg de error
                    //JOptionPane.showMessageDialog(null, "Excedido el número de intentos \n Intentelo mas tarde" ,JOptionPane.ERROR_MESSAGE);
                    System.exit(0);
                }
                else{
                    JOptionPane.showMessageDialog(null,"Usuario o Contraseña incorrecto \n Intentelo Nuevamente \n Resta " + (3 - timer) + " Intentos","Error",JOptionPane.WARNING_MESSAGE); //Msg de error
                    text_user.setText("");
                    pass_user.setText("");
                    timer = timer + 1;
                }
            } catch (SQLException ex) {
                Logger.getLogger(SI_Inicio.class.getName()).log(Level.SEVERE, null, ex);
            }        }
    }//GEN-LAST:event_adminActionPerformed

    private void text_userMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_text_userMouseClicked
    }//GEN-LAST:event_text_userMouseClicked
    private void text_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_text_userActionPerformed
    }//GEN-LAST:event_text_userActionPerformed
    private void pass_userMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pass_userMouseClicked
    }//GEN-LAST:event_pass_userMouseClicked
    private void text_userKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_text_userKeyPressed
    }//GEN-LAST:event_text_userKeyPressed
    private void text_userFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_text_userFocusGained
        if(text_user.getText().trim().equals("Ingresa Usuario")){
            text_user.setText("");
            
        }
           text_user.setForeground(Color.blue);
    }//GEN-LAST:event_text_userFocusGained

    private void text_userFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_text_userFocusLost
        if(text_user.getText().trim().equals("")){
            text_user.setText("Ingresa Usuario");
        }
           text_user.setForeground(new Color(236, 240, 241));
    }//GEN-LAST:event_text_userFocusLost

    private void pass_userFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pass_userFocusGained
        if(pass_user.getText().trim().equals("**********")){
            pass_user.setText("");
        }
           pass_user.setForeground(Color.blue);
    }//GEN-LAST:event_pass_userFocusGained

    private void pass_userFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pass_userFocusLost
        if(pass_user.getText().trim().equals("")){
            pass_user.setText("********** ");
        }
           pass_user.setForeground(new Color(236, 240, 241));
    }//GEN-LAST:event_pass_userFocusLost

    private void pass_user1FocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pass_user1FocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_pass_user1FocusGained

    private void pass_user1FocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pass_user1FocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_pass_user1FocusLost

    private void pass_user1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_pass_user1MouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_pass_user1MouseClicked

    private void iduserFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_iduserFocusGained
        // TODO add your handling code here:
    }//GEN-LAST:event_iduserFocusGained

    private void iduserFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_iduserFocusLost
        // TODO add your handling code here:
    }//GEN-LAST:event_iduserFocusLost

    private void iduserMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_iduserMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_iduserMouseClicked

    private void iduserActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_iduserActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_iduserActionPerformed

    private void iduserKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_iduserKeyPressed
        // TODO add your handling code here:
    }//GEN-LAST:event_iduserKeyPressed

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
            java.util.logging.Logger.getLogger(SI_Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(SI_Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(SI_Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(SI_Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new SI_Inicio().setVisible(true);
            }
        });
    }
SI cc= new SI();
 Connection ca= cc.conexion();
  
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton admin;
    public static javax.swing.JTextField iduser;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    public static javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JPasswordField pass_user;
    private javax.swing.JPasswordField pass_user1;
    public static javax.swing.JTextField text_user;
    public static javax.swing.JButton usuario;
    // End of variables declaration//GEN-END:variables
}
