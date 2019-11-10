
package si;

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
import static si.menu_principal.tabla_usuariosnuevo;

// import static si.SI_Inicio.text_user;

public class Registro extends javax.swing.JFrame {
  
 //Nombre de algunas variables  //TCC = CAMPO DE TEXTO PARA CONFIRMAR CONTRASEÑA   //CC  = LABEL DE CONFIRMAR CONTRASEÑA
    int resultadoprimerusuario;
    String estadoactivo="Activo";
     // boolean primerusuario; //Obtiene el id del proveedor
           // String datos_proveedores_o_usuarios[]= new String[3];
   Statement sent;  
  ResultSet rs;
    
    public void TablaDatosUsuarios(){  /********* METODOS DE LA TABLA DE LOS USUARIOS  *********/
       tabla_usuariosnuevo.setVisible(true);
              DefaultTableModel modelo = new DefaultTableModel();
    
    modelo.addColumn("Id Usuario");           modelo.addColumn("Nombre");
    modelo.addColumn("Usuario");              modelo.addColumn("Apellido Paterno");
    modelo.addColumn("Apellido Materno");     modelo.addColumn("ContraseÃ±a");
    modelo.addColumn("Email");                modelo.addColumn("RFC");
    modelo.addColumn("Telefono");             modelo.addColumn("Estado");
    modelo.addColumn("Fecha y Hora de registro");
    
     tabla_usuariosnuevo.setModel(modelo);
    String []datos = new String[11];    
    try {
            Statement st = ca.createStatement();
            ResultSet rs= st.executeQuery("select * from  user");
            while(rs.next()){
            datos[0]=rs.getString(1);        datos[1]=rs.getString(2);
            datos[2]=rs.getString(3);        datos[3]=rs.getString(4);
            datos[4]=rs.getString(5);        datos[5]=rs.getString(6);
            datos[6]=rs.getString(7);        datos[7]=rs.getString(8);
            datos[8]=rs.getString(9);        datos[9]=rs.getString(10);
            datos[10]=rs.getString(11);
        
            modelo.addRow(datos);
            }
           tabla_usuariosnuevo.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(menu_principal.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }  // FIN DE LA TABLA DE DATOS DE LOS USUSARIOS ******************
   
   public boolean TodoValido(){ // Creacion del metodo de las validaciones de los campos de texto
    if(!user_nombre.getForeground().equals(new Color(236, 240, 0xf1)) || !user_usuario.getForeground().equals(new Color(236, 240, 0xf1)) || !user_contra.getForeground().equals(new Color(236, 240, 0xf1)) || !user_apepaterno.getForeground().equals(new Color(236, 240, 0xf1)) ||
            !user_apematerno.getForeground().equals(new Color(236, 240, 0xf1)) || !user_email.getForeground().equals(new Color(236, 240, 0xf1)) || !user_rfc.getForeground().equals(new Color(236, 240, 0xf1)) || !user_telefono.getForeground().equals(new Color(236, 240, 0xf1))){  
           return true; 
        }    
        return false;
    }
   
    public Registro() {
        initComponents(); 
      setIconImage(getIconImage());  //La variable que le manda la imagen (DataMax) al proyecto 
        this.setLocationRelativeTo(null); // CENTRAR FORMULARIO
        Ocultos();
    }
      //  ICONO AL EJECUTAR EL PROYECTO
                 public Image getIconImage(){
                     Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Reportes/logo5.png"));
                     return retValue;
                 }
             // FIN DEL ICONO
    
    public void Ocultos(){  /* ETIKETAS CON ICONOS DE CORECTO E INCORRECTO INICIALIZADOS COMO OCULTOS */
        jLabel32.setVisible(false);  // ETIQUETAS DE DATOS CORRECTOS 
    jLabel33.setVisible(false);        jLabel64.setVisible(false);
    jLabel36.setVisible(false);        jLabel43.setVisible(false);     jLabel66.setVisible(false);  
    jLabel35.setVisible(false);        jLabel42.setVisible(false);     jLabel65.setVisible(false); 
    jLabel34.setVisible(false);        jLabel39.setVisible(false);     jLabel63.setVisible(false); 
    jLabel41.setVisible(false);        jLabel47.setVisible(false);     jLabel70.setVisible(false); 
    jLabel40.setVisible(false);        jLabel46.setVisible(false);     jLabel69.setVisible(false); 
    jLabel38.setVisible(false);        jLabel45.setVisible(false);     jLabel68.setVisible(false); 
    jLabel37.setVisible(false);        jLabel44.setVisible(false);     jLabel67.setVisible(false); 
     B_registro.setEnabled(false);
    }
    
    void VaciarDatosRegistro(){
        user_nombre.setText("");          user_usuario.setText("");
        user_contra.setText("");          user_apepaterno.setText("");
        user_apematerno.setText("");      user_email.setText("");
        user_rfc.setText("");             user_telefono.setText("");
    }
    
    public void RetornarValoresRegistro(){ /* UN AVEZ K SE INGRESAN LOS DATOS RETORNA LOS VALORES DE LOS PLACEHOLD */
  user_nombre .setText("Ejemplo:  Isaul");
      user_nombre.setFont(new Font("Arial",Font.ITALIC, 15));
      user_nombre.setForeground(Color.red);
           user_usuario .setText("Ejemplo:  isaul45");
              user_usuario.setFont(new Font("Arial",Font.ITALIC, 15));
               user_usuario.setForeground(Color.red);
                    user_contra.setText("Ejemplo:  Isa12345");
                       user_contra.setFont(new Font("Arial",Font.ITALIC, 15));
                       user_contra.setForeground(Color.red);
      user_apepaterno.setText("Ejemplo:  Hernandez");
          user_apepaterno.setFont(new Font("Arial",Font.ITALIC, 15));
          user_apepaterno.setForeground(Color.red);
                 user_apematerno.setText("Ejemplo:  Ramirez");
                      user_apematerno.setFont(new Font("Arial",Font.ITALIC, 15));
                      user_apematerno.setForeground(Color.red);
                               user_email.setText("Ejem:  You@hotmail.com");
                                   user_email.setFont(new Font("Arial",Font.ITALIC, 15));
                                   user_email.setForeground(Color.red);
      user_rfc.setText("Ejemplo:  HERDNA24");
           user_rfc.setFont(new Font("Arial",Font.ITALIC, 15));
           user_rfc.setForeground(Color.red);
                   user_telefono.setText("Ejemplo:  7331170055");
                       user_telefono.setFont(new Font("Arial",Font.ITALIC, 15));
                       user_telefono.setForeground(Color.red);                              
    }
 /*************** METODO de salida DONDE LE PREGUNTA AL USUARIO SI DESEA REALIZAR OTRA INSERCION DE USUARIO *******************/
    public void Salir(){ 
    int dialogButton = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Desea registrar otro usuario?", "          Aviso",dialogButton);
           if(result == 1){
               dispose();   }                                                                 }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        user_telefono = new javax.swing.JTextField();
        user_nombre = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        user_rfc = new javax.swing.JTextField();
        user_email = new javax.swing.JTextField();
        user_apematerno = new javax.swing.JTextField();
        user_apepaterno = new javax.swing.JTextField();
        user_usuario = new javax.swing.JTextField();
        user_contra = new javax.swing.JTextField();
        B_registro = new javax.swing.JButton();
        B_cancelar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jSeparator7 = new javax.swing.JSeparator();
        jSeparator8 = new javax.swing.JSeparator();
        jLabel32 = new javax.swing.JLabel();
        jLabel33 = new javax.swing.JLabel();
        jLabel64 = new javax.swing.JLabel();
        jLabel34 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel63 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel35 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));
        jPanel1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Contraseña:");
        jPanel1.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, -1, 23));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Nombre:");
        jPanel1.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 80, 23));

        user_telefono.setBackground(new java.awt.Color(0, 51, 102));
        user_telefono.setFont(new java.awt.Font("Arial", 2, 17)); // NOI18N
        user_telefono.setForeground(new java.awt.Color(255, 255, 255));
        user_telefono.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        user_telefono.setText("Ejemplo:  733-117-0055");
        user_telefono.setBorder(null);
        user_telefono.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                user_telefonoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                user_telefonoFocusLost(evt);
            }
        });
        user_telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_telefonoActionPerformed(evt);
            }
        });
        user_telefono.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                user_telefonoKeyTyped(evt);
            }
        });
        jPanel1.add(user_telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 310, 193, -1));

        user_nombre.setBackground(new java.awt.Color(0, 51, 102));
        user_nombre.setFont(new java.awt.Font("Arial", 2, 17)); // NOI18N
        user_nombre.setForeground(new java.awt.Color(255, 255, 255));
        user_nombre.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        user_nombre.setText("Ejemplo:  Isaul");
        user_nombre.setBorder(null);
        user_nombre.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                user_nombreFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                user_nombreFocusLost(evt);
            }
        });
        user_nombre.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                user_nombreKeyTyped(evt);
            }
        });
        jPanel1.add(user_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 30, 193, -1));

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Email:");
        jPanel1.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 70, 23));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Apellido Mat.:");
        jPanel1.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, -1, 23));

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Apellido Pat.:");
        jPanel1.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 150, -1, 23));

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Usuario:");
        jPanel1.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 70, 80, 23));

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("RFC:");
        jPanel1.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 270, 70, 23));

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Telefono:");
        jPanel1.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 310, 90, 23));

        user_rfc.setBackground(new java.awt.Color(0, 51, 102));
        user_rfc.setFont(new java.awt.Font("Arial", 2, 17)); // NOI18N
        user_rfc.setForeground(new java.awt.Color(255, 255, 255));
        user_rfc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        user_rfc.setText("Ejem:  CUPU800825569");
        user_rfc.setBorder(null);
        user_rfc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                user_rfcFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                user_rfcFocusLost(evt);
            }
        });
        user_rfc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                user_rfcKeyTyped(evt);
            }
        });
        jPanel1.add(user_rfc, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 193, -1));

        user_email.setBackground(new java.awt.Color(0, 51, 102));
        user_email.setFont(new java.awt.Font("Arial", 2, 17)); // NOI18N
        user_email.setForeground(new java.awt.Color(255, 255, 255));
        user_email.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        user_email.setText("Ejem:  You@hotmail.com");
        user_email.setBorder(null);
        user_email.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                user_emailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                user_emailFocusLost(evt);
            }
        });
        user_email.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                user_emailKeyTyped(evt);
            }
        });
        jPanel1.add(user_email, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 230, 193, -1));

        user_apematerno.setBackground(new java.awt.Color(0, 51, 102));
        user_apematerno.setFont(new java.awt.Font("Arial", 2, 17)); // NOI18N
        user_apematerno.setForeground(new java.awt.Color(255, 255, 255));
        user_apematerno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        user_apematerno.setText("Ejemplo:  Ramirez");
        user_apematerno.setBorder(null);
        user_apematerno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                user_apematernoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                user_apematernoFocusLost(evt);
            }
        });
        user_apematerno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                user_apematernoKeyTyped(evt);
            }
        });
        jPanel1.add(user_apematerno, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 193, -1));

        user_apepaterno.setBackground(new java.awt.Color(0, 51, 102));
        user_apepaterno.setFont(new java.awt.Font("Arial", 2, 17)); // NOI18N
        user_apepaterno.setForeground(new java.awt.Color(255, 255, 255));
        user_apepaterno.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        user_apepaterno.setText("Ejemplo:  Hernandez");
        user_apepaterno.setBorder(null);
        user_apepaterno.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                user_apepaternoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                user_apepaternoFocusLost(evt);
            }
        });
        user_apepaterno.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                user_apepaternoKeyTyped(evt);
            }
        });
        jPanel1.add(user_apepaterno, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 150, 193, -1));

        user_usuario.setBackground(new java.awt.Color(0, 51, 102));
        user_usuario.setFont(new java.awt.Font("Arial", 2, 17)); // NOI18N
        user_usuario.setForeground(new java.awt.Color(255, 255, 255));
        user_usuario.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        user_usuario.setText("Ejemplo:  isaul45");
        user_usuario.setBorder(null);
        user_usuario.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                user_usuarioFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                user_usuarioFocusLost(evt);
            }
        });
        user_usuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_usuarioActionPerformed(evt);
            }
        });
        user_usuario.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                user_usuarioKeyTyped(evt);
            }
        });
        jPanel1.add(user_usuario, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 193, -1));

        user_contra.setBackground(new java.awt.Color(0, 51, 102));
        user_contra.setFont(new java.awt.Font("Arial", 2, 17)); // NOI18N
        user_contra.setForeground(new java.awt.Color(255, 255, 255));
        user_contra.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        user_contra.setText("Ejemplo:  Isa12345");
        user_contra.setBorder(null);
        user_contra.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                user_contraFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                user_contraFocusLost(evt);
            }
        });
        user_contra.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                user_contraKeyTyped(evt);
            }
        });
        jPanel1.add(user_contra, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 110, 193, -1));

        B_registro.setBackground(new java.awt.Color(0, 51, 102));
        B_registro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        B_registro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/casilla-de-verificacion (1).png"))); // NOI18N
        B_registro.setText("Registrar");
        B_registro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_registroActionPerformed(evt);
            }
        });
        jPanel1.add(B_registro, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 360, 140, 40));

        B_cancelar.setBackground(new java.awt.Color(0, 51, 102));
        B_cancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        B_cancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/salir-flecha-derecha (1).png"))); // NOI18N
        B_cancelar.setText("cancelar");
        B_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_cancelarActionPerformed(evt);
            }
        });
        jPanel1.add(B_cancelar, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 360, 130, 40));
        jPanel1.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 330, 190, 10));
        jPanel1.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 50, 190, 10));
        jPanel1.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 190, 10));
        jPanel1.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 130, 190, 10));
        jPanel1.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 190, 10));
        jPanel1.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 210, 190, 10));
        jPanel1.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 250, 190, 20));
        jPanel1.add(jSeparator8, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 290, 190, 10));

        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/correcto.gif"))); // NOI18N
        jPanel1.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 10, 50, 40));

        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/borrado.gif"))); // NOI18N
        jPanel1.add(jLabel33, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 20, 30, 40));

        jLabel64.setFont(new java.awt.Font("Arial Black", 3, 16)); // NOI18N
        jLabel64.setForeground(new java.awt.Color(255, 51, 51));
        jLabel64.setText(" VACIO");
        jPanel1.add(jLabel64, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 20, 80, 40));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/correcto.gif"))); // NOI18N
        jPanel1.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 290, 50, 40));

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/borrado.gif"))); // NOI18N
        jPanel1.add(jLabel39, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 300, 30, 40));

        jLabel63.setFont(new java.awt.Font("Arial Black", 3, 16)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 51, 51));
        jLabel63.setText(" VACIO");
        jPanel1.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 290, 70, 40));

        jLabel65.setFont(new java.awt.Font("Arial Black", 3, 16)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 51, 51));
        jLabel65.setText(" VACIO");
        jPanel1.add(jLabel65, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 50, -1, 40));

        jLabel66.setFont(new java.awt.Font("Arial Black", 3, 16)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 51, 51));
        jLabel66.setText(" VACIO");
        jPanel1.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 90, 80, 40));

        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/correcto.gif"))); // NOI18N
        jPanel1.add(jLabel35, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 50, 50, 40));

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/correcto.gif"))); // NOI18N
        jPanel1.add(jLabel36, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 90, 50, 40));

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/correcto.gif"))); // NOI18N
        jPanel1.add(jLabel37, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 130, 50, 40));

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/correcto.gif"))); // NOI18N
        jPanel1.add(jLabel38, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 170, 50, 40));

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/correcto.gif"))); // NOI18N
        jPanel1.add(jLabel40, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 210, 50, 40));

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/correcto.gif"))); // NOI18N
        jPanel1.add(jLabel41, new org.netbeans.lib.awtextra.AbsoluteConstraints(360, 250, 50, 40));

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/borrado.gif"))); // NOI18N
        jPanel1.add(jLabel42, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 60, 30, 40));

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/borrado.gif"))); // NOI18N
        jPanel1.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 100, 30, 40));

        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/borrado.gif"))); // NOI18N
        jPanel1.add(jLabel44, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 140, 30, 40));

        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/borrado.gif"))); // NOI18N
        jPanel1.add(jLabel45, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 180, 30, 40));

        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/borrado.gif"))); // NOI18N
        jPanel1.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 220, 30, 40));

        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/borrado.gif"))); // NOI18N
        jPanel1.add(jLabel47, new org.netbeans.lib.awtextra.AbsoluteConstraints(370, 260, 30, 40));

        jLabel67.setFont(new java.awt.Font("Arial Black", 3, 16)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 51, 51));
        jLabel67.setText(" VACIO");
        jPanel1.add(jLabel67, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 130, 70, 40));

        jLabel68.setFont(new java.awt.Font("Arial Black", 3, 16)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 51, 51));
        jLabel68.setText(" VACIO");
        jPanel1.add(jLabel68, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 170, 70, 40));

        jLabel69.setFont(new java.awt.Font("Arial Black", 3, 16)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 51, 51));
        jLabel69.setText(" VACIO");
        jPanel1.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 210, 70, 40));

        jLabel70.setFont(new java.awt.Font("Arial Black", 3, 16)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 51, 51));
        jLabel70.setText(" VACIO");
        jPanel1.add(jLabel70, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 250, 70, 40));

        getContentPane().add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 50, 420, 440));

        jPanel2.setBackground(new java.awt.Color(0, 204, 0));
        jPanel2.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jPanel2.setLayout(null);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel3.setText("Registro Nuevo Usuario");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(100, 10, 230, 30);

        getContentPane().add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 420, 50));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void B_registroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_registroActionPerformed
     // *************** --->> ADD NUEVO USUARIO  ***********************
                if(user_nombre .getText().equals("Ejemplo:  Isaul") || user_usuario .getText().equals("Ejemplo:  isaul45")||user_contra.getText().equals("Ejemplo:  Isa12345")||user_apepaterno .getText().equals("Ejemplo:  Hernandez")||user_apematerno .getText().equals("Ejemplo:  Ramirez")||user_email .getText().equals("Ejem:  You@hotmail.com")||user_rfc .getText().equals("Ejemplo:  HERDNA24") ||user_telefono .getText().equals("Ejemplo:  7331170055")){
            JOptionPane.showMessageDialog(null,"Antes de añadir un nuevo usuario, \n Favor de llenar todos los campos","                       Aviso",JOptionPane.WARNING_MESSAGE);
        } 
        else{
                                          try {
             sent = ca.createStatement(); 
             rs = sent.executeQuery("select * from user where  nombre='"+user_nombre.getText()+"' and  apellido_paterno='"+user_apepaterno.getText()+"'and  apellido_materno='"+user_apematerno.getText()+"'");
            while(rs.next()){        
            resultadoprimerusuario=Integer.parseInt(rs.getString(1)); //Obtiene el id del proveedor
            }
              if(resultadoprimerusuario!=0){ //si el id resultante de la consulta es diferente de 0 quiere decir que ya hay por lo menos una venta en el sistema
          JOptionPane.showMessageDialog(null,"Usuario ya registrado");
              }
              else {
                  try{
                String sql = "INSERT INTO user( nombre, nombre_usuario, apellido_paterno, apellido_materno, contraseña, email, RFC, Telefono, estado_activo_inactivo)  VALUES (?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = ca.prepareCall(sql);
                pst.setString(1,user_nombre.getText());
                pst.setString(2,user_usuario.getText());
                pst.setString(3,user_apepaterno.getText());
                pst.setString(4,user_apematerno.getText());
                pst.setString(5,user_contra.getText());
                pst.setString(6,user_email.getText());
                pst.setString(7,user_rfc.getText());
                pst.setString(8,user_telefono.getText());
                pst.setString(9,estadoactivo);

                int a=pst.executeUpdate();
                if(a>0){
                    JOptionPane.showMessageDialog(null,"Datos guardados correctamente");
                   VaciarDatosRegistro();
                      TablaDatosUsuarios();
                   RetornarValoresRegistro(); 
                   Ocultos();
                   Salir();
                }
            } catch(SQLException e)  {
                JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
            }
                  
              }
        }
                    catch (SQLException ex) {
            Logger.getLogger(menu_principal.class.getName()).log(Level.SEVERE, null, ex);
       }
            //FIN DE LA COMPROBACIÓN DEL PRIMER PROVEEDOR
                          }              
                
    }//GEN-LAST:event_B_registroActionPerformed
    private void B_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_cancelarActionPerformed
        // BOTON DE CANCELAR LA INSERCION DE NUEVO USUARIO
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Desea cancelar el registro?","                    Aviso",dialogButton);
           if(result == 0){
               dispose();   }
    }//GEN-LAST:event_B_cancelarActionPerformed
    private void user_nombreFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_user_nombreFocusGained
        // ------------------- *************  PLACEHOLD DEL NOMBR DE USUARIO  ************* -------------------
        if(user_nombre.getText().trim().equals("Ejemplo:  Isaul")){
            user_nombre.setText("");
            user_nombre.setForeground(new  Color (34,167,240)); // COLOR DE FONDO CUANDO SE INGRESAN LOS DATOS
        }
    }//GEN-LAST:event_user_nombreFocusGained
    private void user_nombreFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_user_nombreFocusLost
        //  ************************   NOMBRE DE USUARIO *************************
        if(user_nombre.getText().trim().equals("")){
            user_nombre.setText("Ejemplo:  Isaul");
        }
        /*   user_nombre.setForeground(new Color(236, 240, 241));
               if("Ejemplo:  Isaul".equals(user_nombre.getText()) ){
              user_nombre.setFont(new Font("Arial",Font.ITALIC, 15));
              user_nombre.setForeground(Color.red);  
           } else{  user_nombre.setFont(new Font("Tahoma",Font.BOLD, 15));  }    */
    }//GEN-LAST:event_user_nombreFocusLost

    private void user_usuarioFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_user_usuarioFocusGained
                            // *********   *********  *********   USUARIO   **********   *********   *********
        if(user_usuario.getText().trim().equals("Ejemplo:  isaul45")){
            user_usuario.setText("");
            user_usuario.setForeground(new  Color (34,167,240));
        }
    }//GEN-LAST:event_user_usuarioFocusGained
    private void user_usuarioFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_user_usuarioFocusLost
        if(user_usuario.getText().trim().equals("")){
            user_usuario.setText("Ejemplo:  isaul45");
        }
    }//GEN-LAST:event_user_usuarioFocusLost

    private void user_contraFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_user_contraFocusGained
                     //   **************** CONTRASEÑA ****************
        if(user_contra.getText().trim().equals("Ejemplo:  Isa12345")){
            user_contra.setText("");
            user_contra.setForeground(new  Color (34,167,240));
        }
    }//GEN-LAST:event_user_contraFocusGained
    private void user_contraFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_user_contraFocusLost
        if(user_contra.getText().trim().equals("")){
            user_contra.setText("Ejemplo:  Isa12345");
        }
    }//GEN-LAST:event_user_contraFocusLost
    private void user_apepaternoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_user_apepaternoFocusGained
        if(user_apepaterno.getText().trim().equals("Ejemplo:  Hernandez")){
            user_apepaterno.setText("");
            user_apepaterno.setForeground(new  Color (34,167,240));
        }           
    }//GEN-LAST:event_user_apepaternoFocusGained
    private void user_apepaternoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_user_apepaternoFocusLost
        if(user_apepaterno.getText().trim().equals("")){
            user_apepaterno.setText("Ejemplo:  Hernandez");
        }
    }//GEN-LAST:event_user_apepaternoFocusLost
    private void user_apematernoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_user_apematernoFocusGained
        if(user_apematerno.getText().trim().equals("Ejemplo:  Ramirez")){
            user_apematerno.setText("");
            user_apematerno.setForeground(new  Color (34,167,240));
        }
    }//GEN-LAST:event_user_apematernoFocusGained
    private void user_apematernoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_user_apematernoFocusLost
         if(user_apematerno.getText().trim().equals("")){
            user_apematerno.setText("Ejemplo:  Ramirez");
        }
    }//GEN-LAST:event_user_apematernoFocusLost
    private void user_emailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_user_emailFocusGained
        if(user_email.getText().trim().equals("Ejem:  You@hotmail.com")){
            user_email.setText("");
             user_email.setForeground(new  Color (34,167,240));
        }           
    }//GEN-LAST:event_user_emailFocusGained
    private void user_emailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_user_emailFocusLost
        if(user_email.getText().trim().equals("")){
            user_email.setText("Ejem:  You@hotmail.com");
        }
    }//GEN-LAST:event_user_emailFocusLost
    private void user_rfcFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_user_rfcFocusGained
        if(user_rfc.getText().trim().equals("Ejem:  CUPU800825569")){
            user_rfc.setText("");
             user_rfc.setForeground(new  Color (34,167,240));
        }
    }//GEN-LAST:event_user_rfcFocusGained
    private void user_rfcFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_user_rfcFocusLost
        if(user_rfc.getText().trim().equals("")){
            user_rfc.setText("Ejem:  CUPU800825569");
        }
    }//GEN-LAST:event_user_rfcFocusLost
    private void user_telefonoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_user_telefonoFocusGained
        if(user_telefono.getText().trim().equals("Ejemplo:  733-117-0055")){
            user_telefono.setText("");
             user_telefono.setForeground(new  Color (34,167,240));
        }
    }//GEN-LAST:event_user_telefonoFocusGained
    private void user_telefonoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_user_telefonoFocusLost
        if(user_telefono.getText().trim().equals("")){
            user_telefono.setText("Ejemplo:  733-117-0055");
        }
       /*    user_telefono.setForeground(new Color(236, 240, 241));
           if("Ejemplo:  7331170055".equals(user_telefono.getText()) ){
              user_telefono.setFont(new Font("Arial",Font.ITALIC, 15));
              user_telefono.setForeground(Color.red);
           } else{
               user_telefono.setFont(new Font("Tahoma",Font.BOLD, 15));          }     */
    }//GEN-LAST:event_user_telefonoFocusLost

    private void user_telefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_telefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_telefonoActionPerformed

    private void user_nombreKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_user_nombreKeyTyped
     //   *****************  RESTRICCION DEL NOMBRE DEL USUARIO   *****************  <<----------------------
        String cadena = user_nombre.getText();
        Pattern pat = Pattern.compile("^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24}[\\s]*)+$");
        Matcher mat = pat.matcher(cadena);
      if (mat.matches()) {
           jLabel32.setVisible(true);/* -->> ACTIVO CORRECTO */ 
             jLabel33.setVisible(false);/* -->>NO ACTIVO INCORRECTO */
               jLabel64.setVisible(false);         
                  user_nombre.setFont(new Font("Tahoma",Font.BOLD, 17)); // CUANDO ES CORRECTI CAMBIA DE COLOR Y TAMAÑO ES EL MISMO       
                    user_nombre.setForeground(new Color(236, 240, 241));
         }  //else if ("Ejemplo:  Isaul".equals(user_nombre.getText())) {        // vaciooo 
      else if (user_nombre.getText().isEmpty()) {        
          jLabel32.setVisible(false);  
               jLabel33.setVisible(false);
                 jLabel64.setVisible(true);  // ETIKETA DE VACIOO EN ACCION
           
            }else {
               jLabel33.setVisible(true);
                 jLabel32.setVisible(false);  jLabel64.setVisible(false);
                    user_nombre.setFont(new Font("Arial",Font.ITALIC, 17));
                       user_nombre.setForeground(Color.red);  
      }
        // LLAMADO DEL METODO DE LAS VALIDACIONES DE LOS CAMPOS DE TEXTOS HABILITAR/DEHABILITAR EL BOTON
             if(TodoValido()){ 
               B_registro.setEnabled(false); // BOTON ADD ACTIVO
            }else{
               B_registro.setEnabled(true); // BOTON ADD OCULTO
        }  
         
    }//GEN-LAST:event_user_nombreKeyTyped

    private void user_apepaternoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_user_apepaternoKeyTyped
                   // ***************  VALIDAR APELLIDO PATERNO DEL USUARIO  ******************
       String cadena = user_apepaterno.getText();
        Pattern pat = Pattern.compile("^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24})+$");
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {
            jLabel37.setVisible(true);/* -->> CORRECTO */ 
             jLabel44.setVisible(false);/* -->>  INCORRECTO */
               jLabel67.setVisible(false);   /* -->>  VACIOO  */
                  user_apepaterno.setFont(new Font("Tahoma",Font.BOLD, 17)); // CUANDO ES CORRECTI CAMBIA DE COLOR Y TAMAÑO ES EL MISMO       
                    user_apepaterno.setForeground(new Color(236, 240, 241)); 
            
        } else if (user_apepaterno.getText().isEmpty()) {      // vaciooo 
            jLabel37.setVisible(false);  
               jLabel44.setVisible(false);
                 jLabel67.setVisible(true);  // ETIKETA DE VACIOO EN ACCION

        } else {
            jLabel44.setVisible(true);
                 jLabel32.setVisible(false);  jLabel67.setVisible(false);
                    user_apepaterno.setFont(new Font("Arial",Font.ITALIC, 17));
                       user_apepaterno.setForeground(Color.red);  
        }
        // LLAMADO DEL METODO DE LAS VALIDACIONES DE LOS CAMPOS DE TEXTOS HABILITAR/DEHABILITAR EL BOTON
             if(TodoValido()){ 
               B_registro.setEnabled(false); // BOTON ADD ACTIVO
            }else{
               B_registro.setEnabled(true); // BOTON ADD OCULTO
        }
    }//GEN-LAST:event_user_apepaternoKeyTyped

    private void user_apematernoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_user_apematernoKeyTyped
         // ***************  VALIDAR APELLIDO MATERNO DEL USUARIO  ******************
        String cadena = user_apematerno.getText();
        Pattern pat = Pattern.compile("^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24})+$");
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {
             jLabel38.setVisible(true);
             jLabel45.setVisible(false);
               jLabel68.setVisible(false); 
                  user_apematerno.setFont(new Font("Tahoma",Font.BOLD, 17)); 
                    user_apematerno.setForeground(new Color(236, 240, 241));
        } else if (user_apematerno.getText().isEmpty()) {    
             jLabel38.setVisible(false);  
               jLabel45.setVisible(false);
                 jLabel68.setVisible(true);
        } else {
             jLabel45.setVisible(true);
                 jLabel38.setVisible(false);  jLabel68.setVisible(false);
                    user_apematerno.setFont(new Font("Arial",Font.ITALIC, 17));
                       user_apematerno.setForeground(Color.red); 
        }
        // LLAMADO DEL METODO DE LAS VALIDACIONES DE LOS CAMPOS DE TEXTOS HABILITAR/DEHABILITAR EL BOTON
            if(TodoValido()){ 
               B_registro.setEnabled(false); // BOTON ADD ACTIVO
            }else{
               B_registro.setEnabled(true); // BOTON ADD OCULTO
        }
    }//GEN-LAST:event_user_apematernoKeyTyped

    private void user_emailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_user_emailKeyTyped
          // ***************  VALIDAR APELLIDO CORREO ELECTORNICO DEL USUARIO  ******************
       String cadena = user_email.getText();
        Pattern pat = Pattern.compile("^[_A-zA-Z0-9-\\+]+(\\.[_a-za-z0-9-]+)*@" + "[a-za-z0-9-]+(\\.[a-za-z0-9]+)*(\\.[a-za-z]{2,})$");
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {
             jLabel40.setVisible(true);/* -->> CORRECTO */ 
             jLabel46.setVisible(false);/* -->>  INCORRECTO */
               jLabel69.setVisible(false);   /* -->>  VACIOO  */
                  user_email.setFont(new Font("Tahoma",Font.BOLD, 15)); 
                    user_email.setForeground(new Color(236, 240, 241)); 
        } else if (user_email.getText().isEmpty()) {      // vaciooo 
               jLabel40.setVisible(false);  
               jLabel46.setVisible(false);
                 jLabel69.setVisible(true);
        } else {
                jLabel46.setVisible(true);
                 jLabel40.setVisible(false);  jLabel69.setVisible(false);
                    user_email.setFont(new Font("Arial",Font.ITALIC, 15));
                       user_email.setForeground(Color.red); 
        }
        // LLAMADO DEL METODO DE LAS VALIDACIONES DE LOS CAMPOS DE TEXTOS HABILITAR/DEHABILITAR EL BOTON
           if(TodoValido()){ 
               B_registro.setEnabled(false); // BOTON ADD ACTIVO
            }else{
               B_registro.setEnabled(true); // BOTON ADD OCULTO
        } 
    }//GEN-LAST:event_user_emailKeyTyped

    private void user_rfcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_user_rfcKeyTyped
          // ***************  VALIDAR  RFC DEL USUARIO  ******************
// RFC example: R.F.C CUPU800825569
        String cadena = user_rfc.getText();
        Pattern pat = Pattern.compile("^([A-ZÑ\\x26]{3,4}([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1]))([A-Z\\d]{3})?$");
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {           
             jLabel41.setVisible(true);
             jLabel47.setVisible(false);
               jLabel70.setVisible(false);
                  user_rfc.setFont(new Font("Tahoma",Font.BOLD, 17)); 
                    user_rfc.setForeground(new Color(236, 240, 241));
        } else if (user_rfc.getText().isEmpty()) {      // vaciooo 
              jLabel41.setVisible(false);  
               jLabel47.setVisible(false);
                 jLabel70.setVisible(true);
        } else {
               jLabel47.setVisible(true);
                 jLabel41.setVisible(false);  jLabel70.setVisible(false);
                    user_rfc.setFont(new Font("Arial",Font.ITALIC, 17));
                       user_rfc.setForeground(Color.red); 
        }
        // LLAMADO DEL METODO DE LAS VALIDACIONES DE LOS CAMPOS DE TEXTOS HABILITAR/DEHABILITAR EL BOTON
           if(TodoValido()){ 
               B_registro.setEnabled(false); // BOTON ADD ACTIVO
            }else{
               B_registro.setEnabled(true); // BOTON ADD OCULTO
        }   
    }//GEN-LAST:event_user_rfcKeyTyped

    private void user_telefonoKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_user_telefonoKeyTyped
        // ***************  VALIDAR  TELEFONO DEL USUARIO  ******************
    // Pattern pat = Pattern.compile("\\d{3}\\d{3}");  PERMITE UN TELEFONO SIN LADA SOLO 7 DIJISTOS 
    String cadena = user_telefono.getText();
        Pattern pat = Pattern.compile("\\d{3}-\\d{3}-\\d{3}");
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {
            jLabel34.setVisible(true);
             jLabel39.setVisible(false);
               jLabel63.setVisible(false);  
                  user_telefono.setFont(new Font("Tahoma",Font.BOLD, 17)); 
                    user_telefono.setForeground(new Color(236, 240, 241));
        } else if (user_telefono.getText().isEmpty()) {      // vaciooo 
                jLabel34.setVisible(false);  
               jLabel39.setVisible(false);
                 jLabel63.setVisible(true);
        } else {
                 jLabel39.setVisible(true);
                 jLabel34.setVisible(false);  jLabel63.setVisible(false);
                    user_telefono.setFont(new Font("Arial",Font.ITALIC, 17));
                       user_telefono.setForeground(Color.red); 
        }
        // LLAMADO DEL METODO DE LAS VALIDACIONES DE LOS CAMPOS DE TEXTOS HABILITAR/DEHABILITAR EL BOTON
         if(TodoValido()){ 
               B_registro.setEnabled(false); // BOTON ADD ACTIVO
            }else{
               B_registro.setEnabled(true); // BOTON ADD OCULTO
        }
    }//GEN-LAST:event_user_telefonoKeyTyped

    private void user_usuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_usuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_usuarioActionPerformed

    private void user_usuarioKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_user_usuarioKeyTyped
                // *******************  NOMBRE DE USUARIOOO   *******************
        String cadena = user_usuario.getText();
     Pattern pat = Pattern.compile("^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24}[^'\\s]*)+$"); 
        Matcher mat = pat.matcher(cadena);  
      if (mat.matches()) {
           jLabel35.setVisible(true);
             jLabel42.setVisible(false);
               jLabel65.setVisible(false);       
                  user_usuario.setFont(new Font("Tahoma",Font.BOLD, 17)); 
                    user_usuario.setForeground(new Color(236, 240, 241));
         } else if (user_usuario.getText().isEmpty()) {       
          jLabel35.setVisible(false);  
               jLabel42.setVisible(false);
                 jLabel65.setVisible(true); 
            }else {
               jLabel42.setVisible(true);
                 jLabel35.setVisible(false);  jLabel65.setVisible(false);
                    user_usuario.setFont(new Font("Arial",Font.ITALIC, 17));
                       user_usuario.setForeground(Color.red);  
      }
        // LLAMADO DEL METODO DE LAS VALIDACIONES DE LOS CAMPOS DE TEXTOS HABILITAR/DEHABILITAR EL BOTON
           if(TodoValido()){ 
               B_registro.setEnabled(false); // BOTON ADD ACTIVO
            }else{
               B_registro.setEnabled(true); // BOTON ADD OCULTO
        }  
    }//GEN-LAST:event_user_usuarioKeyTyped
    private void user_contraKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_user_contraKeyTyped
               // ********************** CONTRASEÑA  **********************
         String cadena = user_contra.getText();
                // Pattern pat = Pattern.compile("^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24}[\\s]*)+$");
            Pattern pat = Pattern.compile("^(?=\\w*\\d)(?=\\w*[A-Z])(?=\\w*[a-z])\\S{6,16}$");  
        Matcher mat = pat.matcher(cadena);
      if (mat.matches()) {
           jLabel36.setVisible(true);
             jLabel43.setVisible(false);
               jLabel66.setVisible(false);         
                  user_contra.setFont(new Font("Tahoma",Font.BOLD, 17)); // CUANDO ES CORRECTI CAMBIA DE COLOR Y TAMAÑO ES EL MISMO       
                    user_contra.setForeground(new Color(236, 240, 241));
         }  else if (user_contra.getText().isEmpty()) {       
          jLabel36.setVisible(false);  
               jLabel43.setVisible(false);
                 jLabel66.setVisible(true);  // ETIKETA DE VACIOO EN ACCION
            }else {
               jLabel43.setVisible(true);
                 jLabel36.setVisible(false);  jLabel66.setVisible(false);
                    user_contra.setFont(new Font("Arial",Font.ITALIC, 17));
                       user_contra.setForeground(Color.red);  
      }
        // LLAMADO DEL METODO DE LAS VALIDACIONES DE LOS CAMPOS DE TEXTOS HABILITAR/DEHABILITAR EL BOTON
          if(TodoValido()){ 
               B_registro.setEnabled(false); // BOTON ADD ACTIVO
            }else{
               B_registro.setEnabled(true); // BOTON ADD OCULTO
        }  
    }//GEN-LAST:event_user_contraKeyTyped

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
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Registro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Registro().setVisible(true);
            }
        });
    }
    
    SI cc= new SI();
 Connection ca= cc.conexion();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_cancelar;
    public static javax.swing.JButton B_registro;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel32;
    private javax.swing.JLabel jLabel33;
    private javax.swing.JLabel jLabel34;
    private javax.swing.JLabel jLabel35;
    private javax.swing.JLabel jLabel36;
    private javax.swing.JLabel jLabel37;
    private javax.swing.JLabel jLabel38;
    private javax.swing.JLabel jLabel39;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel40;
    private javax.swing.JLabel jLabel41;
    private javax.swing.JLabel jLabel42;
    private javax.swing.JLabel jLabel43;
    private javax.swing.JLabel jLabel44;
    private javax.swing.JLabel jLabel45;
    private javax.swing.JLabel jLabel46;
    private javax.swing.JLabel jLabel47;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel64;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator8;
    private javax.swing.JTextField user_apematerno;
    private javax.swing.JTextField user_apepaterno;
    private javax.swing.JTextField user_contra;
    private javax.swing.JTextField user_email;
    private javax.swing.JTextField user_nombre;
    private javax.swing.JTextField user_rfc;
    private javax.swing.JTextField user_telefono;
    private javax.swing.JTextField user_usuario;
    // End of variables declaration//GEN-END:variables

    
}
