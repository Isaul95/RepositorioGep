
package si;

import java.awt.Color;
import java.awt.Font;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class Productos_crud extends javax.swing.JFrame {
public static   SI cc = new SI();
       public static Connection ca= cc.conexion();
    int id_mio;
    public Productos_crud() {
        initComponents();
        this.setLocationRelativeTo(null); // CENTRAR FORMULARIO  
     mostrartablaarticulos();
     agregar.setEnabled(false);
     update.setEnabled(false);
    }
    
        
  void mostrartablaarticulos(){
        tabla_agregar.setVisible(true);
              DefaultTableModel modelo = new DefaultTableModel();
    
    modelo.addColumn("Id_producto");
    modelo.addColumn("Nombre del producto");
   // modelo.addColumn("Tipo de producto");
     modelo.addColumn("Precio");
     //modelo.addColumn("Cantidad");
//     modelo.addColumn("Fecha de caducidad");
  //  modelo.addColumn("Id del proveedor");
   // modelo.addColumn("fecha y hora de registro");
    tabla_agregar.setModel(modelo);
    String []datos = new String[3];    
    try {
            Statement st = ca.createStatement();
            ResultSet rs= st.executeQuery("select id_producto, nombre_producto, precio from  productos");
            while(rs.next()){
            datos[0]=rs.getString(1);
            datos[1]=rs.getString(2);
            datos[2]=rs.getString(3);
            
        
            modelo.addRow(datos);
            }
           tabla_agregar.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(Productos_crud.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    public boolean TodoValidoProducts(){ // Creacion del metodo de las validaciones de los campos de texto ---->>> PRODUCTOSSS
    if(!namep.getForeground().equals(new Color(236, 240, 0xf1)) || !preciop.getForeground().equals(new Color(236, 240, 0xf1)) ){  
           return true; 
        }    
        return false;
    }  

   void limpiardatosarticulos(){
           namep.setText("");
           preciop.setText("");           
   }
   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        agregar_articulo = new javax.swing.JPanel();
        agregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_agregar = new javax.swing.JTable();
        update = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        namep = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        preciop = new javax.swing.JTextField();
        jSeparator18 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        agregar_articulo.setBackground(new java.awt.Color(0, 51, 102));
        agregar_articulo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        agregar_articulo.setDoubleBuffered(false);
        agregar_articulo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        agregar.setBackground(new java.awt.Color(0, 148, 204));
        agregar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        agregar.setForeground(new java.awt.Color(255, 255, 255));
        agregar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/Add1.png"))); // NOI18N
        agregar.setText("Agregar");
        agregar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        agregar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        agregar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });
        agregar_articulo.add(agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 200, 95, 97));

        tabla_agregar.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        tabla_agregar.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla_agregar.setSelectionBackground(new java.awt.Color(255, 255, 255));
        tabla_agregar.setSelectionForeground(new java.awt.Color(0, 135, 204));
        tabla_agregar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_agregarMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tabla_agregar);

        agregar_articulo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 310, 490, 200));

        update.setBackground(new java.awt.Color(0, 148, 204));
        update.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        update.setForeground(new java.awt.Color(255, 255, 255));
        update.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/actualizar.png"))); // NOI18N
        update.setText("Actualizar");
        update.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        update.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        update.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        update.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                updateActionPerformed(evt);
            }
        });
        agregar_articulo.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 200, 100, 97));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jLabel5.setText("Agregar / Modificare Productos");
        jLabel5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(53, 53, 53)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 413, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(824, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(25, 25, 25))
        );

        agregar_articulo.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 50));

        jPanel11.setBackground(new java.awt.Color(0, 51, 102));
        jPanel11.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   DATOS GENERALES DEL PRODUCTO   ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Tahoma", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel11.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("  Nombre del producto:");
        jPanel11.add(jLabel1);
        jLabel1.setBounds(10, 31, 210, 30);

        namep.setBackground(new java.awt.Color(0, 51, 102));
        namep.setFont(new java.awt.Font("Arial", 2, 17)); // NOI18N
        namep.setForeground(new java.awt.Color(255, 255, 255));
        namep.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        namep.setText("Piezas");
        namep.setBorder(null);
        namep.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                namepFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                namepFocusLost(evt);
            }
        });
        namep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                namepKeyTyped(evt);
            }
        });
        jPanel11.add(namep);
        namep.setBounds(230, 30, 200, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("  Precio:");
        jPanel11.add(jLabel4);
        jLabel4.setBounds(10, 80, 80, 30);

        preciop.setBackground(new java.awt.Color(0, 51, 102));
        preciop.setFont(new java.awt.Font("Arial", 2, 17)); // NOI18N
        preciop.setForeground(new java.awt.Color(255, 255, 255));
        preciop.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        preciop.setText("00.00");
        preciop.setBorder(null);
        preciop.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                preciopFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                preciopFocusLost(evt);
            }
        });
        preciop.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                preciopKeyTyped(evt);
            }
        });
        jPanel11.add(preciop);
        preciop.setBounds(230, 80, 200, 30);
        jPanel11.add(jSeparator18);
        jSeparator18.setBounds(230, 110, 200, 20);
        jPanel11.add(jSeparator19);
        jSeparator19.setBounds(230, 60, 200, 10);

        agregar_articulo.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 470, 130));

        jButton1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/salir-flecha-derecha (1).png"))); // NOI18N
        jButton1.setText("Regresar");
        jButton1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        agregar_articulo.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(340, 220, 150, 50));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(agregar_articulo, javax.swing.GroupLayout.PREFERRED_SIZE, 514, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(agregar_articulo, javax.swing.GroupLayout.DEFAULT_SIZE, 525, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        //codigo para agregar un nuevo articulo        
        if(namep.getText().isEmpty()||preciop.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Debe de llenar todos los campos de texto antes de guardar un nuevo articulo","                    AVISO",JOptionPane.INFORMATION_MESSAGE);
        }       
        else{            
            try{               
                String sql = "INSERT INTO productos(nombre_producto,tipo_producto,precio,id_proveedor)  VALUES (?,?,?,?)";
                PreparedStatement pst = ca.prepareCall(sql);
                pst.setString(1,namep.getText());
                pst.setString(2,"pollo");
                pst.setString(3,preciop.getText());
                //pst.setString(4,cantp.getText());
                pst.setString(4, "1");               

                int a=pst.executeUpdate();
                if(a>0){
                    JOptionPane.showMessageDialog(null,"Datos guardados correctamente");
                   limpiardatosarticulos();
                   mostrartablaarticulos();                 
                }
            } catch(SQLException e)  {
                JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
            }
        }
    }//GEN-LAST:event_agregarActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        //codigo para actualizar  TABLA DE PRODUCTOS
        if(namep.getText().isEmpty()||preciop.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Llene todos los campos de texto antes de guardar cambios","                              AVISO",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            try{
                int fila =tabla_agregar.getSelectedRow();

                PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET precio='"+preciop.getText()+"',nombre_producto='"+namep.getText()+"'WHERE id_producto='"+tabla_agregar.getValueAt(fila,0).toString()+"'");
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Datos modificados correctamente");
                mostrartablaarticulos();
                update.setEnabled(false);
               limpiardatosarticulos();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
            }
        }
    }//GEN-LAST:event_updateActionPerformed

    private void namepFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_namepFocusGained
        // ************++ NOMBRE DE PRODUCTO  **************
        if(namep.getText().trim().equals("Piezas")){
            namep.setText("");
            namep.setForeground(new  Color (34,167,240));
        }
    }//GEN-LAST:event_namepFocusGained

    private void namepFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_namepFocusLost
        if(namep.getText().trim().equals("")){
            namep.setText("Piezas");
        }
    }//GEN-LAST:event_namepFocusLost

    private void namepKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_namepKeyTyped
        // ************** NOMBRE DEL PRODUCTO  **************  //("^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24}[0-9*]{4}[\\s])+$");
        String cadena = namep.getText();
        Pattern pat = Pattern.compile ("^[A-Za-z0-9\\s]+$");
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {            
            namep.setFont(new Font("Tahoma",Font.BOLD, 17)); // CUANDO ES CORRECTI CAMBIA DE COLOR Y TAMAÑO ES EL MISMO
            namep.setForeground(new Color(236, 240, 241));
        } else if (namep.getText().isEmpty()) {
            namep.setFont(new Font("Arial",Font.ITALIC, 17));    namep.setForeground(new  Color (255,0,0));
        } else{
            namep.setFont(new Font("Arial",Font.ITALIC, 17));
            namep.setForeground(Color.red);
        }    // LLAMADO DEL METODO DE LAS VALIDACIONES DE LOS CAMPOS DE TEXTOS HABILITAR/DEHABILITAR EL BOTON
        if(TodoValidoProducts()){
            agregar.setEnabled(false); // BOTON ADD ACTIVO
        }else{
            agregar.setEnabled(true); // BOTON ADD OCULTO
        }
    }//GEN-LAST:event_namepKeyTyped

    private void preciopFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_preciopFocusGained
        // ************++ PRECIO DEL PRODUCTO  **************
        if(preciop.getText().trim().equals("00.00")){
            preciop.setText("");
            preciop.setForeground(new  Color (34,167,240));
        }
    }//GEN-LAST:event_preciopFocusGained

    private void preciopFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_preciopFocusLost
        if(preciop.getText().trim().equals("")){
            preciop.setText("00.00");
        }
    }//GEN-LAST:event_preciopFocusLost

    private void preciopKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_preciopKeyTyped
        // ******************** PRECIO DEL PRODUCTO RESTRICCION   ********************************
        // Extraer precio;   /($[0-9,]+(.[0-9]{2})?)/       ^[0-9]+([,][0-9]*)?$
        String cadena = preciop.getText();
        Pattern pat = Pattern.compile("^[0-9]+([.][0-9])?$");
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {           
            preciop.setFont(new Font("Tahoma",Font.BOLD, 17)); // CUANDO ES CORRECTI CAMBIA DE COLOR Y TAMAÑO ES EL MISMO
            preciop.setForeground(new Color(236, 240, 241));
        } else if (preciop.getText().isEmpty()) {
            preciop.setFont(new Font("Arial",Font.ITALIC, 17));    preciop.setForeground(new  Color (255,0,0));
        } else {
            preciop.setFont(new Font("Arial",Font.ITALIC, 17));
            preciop.setForeground(Color.red);
        }
        // LLAMADO DEL METODO DE LAS VALIDACIONES DE LOS CAMPOS DE TEXTOS HABILITAR/DEHABILITAR EL BOTON
        if(TodoValidoProducts()){
            agregar.setEnabled(false); // BOTON ADD ACTIVO
        }else{
            agregar.setEnabled(true); // BOTON ADD OCULTO
        }
    }//GEN-LAST:event_preciopKeyTyped

    private void tabla_agregarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_agregarMouseClicked
         int fila =tabla_agregar.getSelectedRow();
      if(fila>=0){
             update.setEnabled(true);
                namep.setText(tabla_agregar.getValueAt(fila,1).toString());
                preciop.setText(tabla_agregar.getValueAt(fila,2).toString());
         
      }
      else
          JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila primero","Aviso",JOptionPane.INFORMATION_MESSAGE);                                 
         
    /*     
         if(fila>=0){
             update.setEnabled(true);
                namep.setText(tabla_agregar.getValueAt(fila,0).toString());
                preciop.setText(tabla_agregar.getValueAt(fila,1).toString());           
      }
      else
          JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila primero","Aviso",JOptionPane.INFORMATION_MESSAGE);
*/         
    }//GEN-LAST:event_tabla_agregarMouseClicked

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
            java.util.logging.Logger.getLogger(Productos_crud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Productos_crud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Productos_crud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Productos_crud.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Productos_crud().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton agregar;
    public static javax.swing.JPanel agregar_articulo;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JTextField namep;
    private javax.swing.JTextField preciop;
    private javax.swing.JTable tabla_agregar;
    private javax.swing.JButton update;
    // End of variables declaration//GEN-END:variables
}
