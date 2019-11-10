package si;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import si.Gastos;
//import static si.menu_principal.searchforproducts;
import ticket.TikectGasto;
//import static si.menu_principal.venta; // DANDO ACCESOO ALA INTERFAZ PRINCIPAL


public class Pantalla_Gastos extends javax.swing.JFrame {  
    
                 
              // menu_principal Objeto = menu_principal();
               //Objeto.
     String[] piezas = {"Pechuga", "Muslo","Pierna","Ala","Huacal","Cadera","Cabeza", "Molleja", "Patas"};
                       
       
                Calendar fecha_actual = new GregorianCalendar();
                String fechahoy="", buscap = "";
                int tt;
                Statement sent;  
                Gastos gastos;
                float cantidad;
                float  piezasxunpollo=14, piezasdepollopares=2, piezasdepollosinpares=1, resultadodepiezaspares,resultadodepiezasinpares;
               // String id_usuario; 
                TikectGasto tikectGastos;
                  float cantidadpolloenDB, pollosdivididos, addpiezas;
                String  usuarioname=SI_Inicio.text_user.getText();
               int  id_usuario=Integer.parseInt(SI_Inicio.iduser.getText()), id_proveedor;
                  String tipo, total, pollocrudo;
              //  String usuarioname=SI_Inicio.text_user.getText(); //variable para obtener el nombre del usuario o administrador que ingreso al sistema
    //private Object rs;
                ResultSet rs;
    public Pantalla_Gastos() {
        initComponents();
        //menu_principal.autocompletar();
       // Actualizar();
//       autocompletar();
        this.setLocationRelativeTo(null); // CENTRAR FORMULARIO
         //AutoCompleteDecorator.decorate(menu_principal.searchforproducts);
        jDateChooserFecha.setCalendar(fecha_actual);
        //txtpiezas.setEnabled(false);
       // txtpiezas.setText("0");
    }
        
     public boolean validarFormulario(String gastos) { // VALIDACION DE TXT MONTO
        boolean next = false;
        Pattern patGastos = Pattern.compile("^[0-9]+([.])?([0-9]+)?$");
        Matcher matGastos = patGastos.matcher(gastos);

        if (matGastos.matches()) {
            next = true;

        } else {
            JOptionPane.showMessageDialog(null, "Solo Numeros");
            txtmonto.setBackground(Color.red);
        }
        return next;
    }
     
      public boolean validarFormulariopiezas(String gastos) { // VALIDACION DE TXT cantidad
        boolean next = false;
        Pattern patGastos = Pattern.compile("^[0-9]+([.])?([0-9]+)?$");
        Matcher matGastos = patGastos.matcher(gastos);

        if (matGastos.matches()) {
            next = true;

        } else {
            JOptionPane.showMessageDialog(null, "Solo Numeros");
            txtpiezas.setBackground(Color.red);
        }
        return next;
    }
     
                               
             public boolean validarFormulariotexto(String gastos) { // VALIDACION DE TXTDESCRIPCION
        boolean next = false;      //"^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24}[\\s]*)+$"
        Pattern patGastos = Pattern.compile("^[A-Za-z\\s]+$");// ^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24}[\\s]*)+$
        Matcher matGastos = patGastos.matcher(gastos);

        if (matGastos.matches()) {
            next = true;

        } else {
            JOptionPane.showMessageDialog(null, "Solo letras");
            txtdescripcion.setBackground(Color.red);
        }
        return next;
    }             
             
             public static String fecha(){ /* SE DECARA LA FECHA DEL SISTEMA */
        Date fecha=new Date();
        SimpleDateFormat formatoFecha= new SimpleDateFormat("YYYY/MM/dd");
        return formatoFecha.format(fecha);
    }
             
             
             
              public void mostrartodoslosproductosenexistenciasporbusqueda(String textobusqueda){
            jTableGastos.setVisible(true);    //hace visible la tabla de proveedores 
              DefaultTableModel modelo = new DefaultTableModel(); // Se crea un objeto para agregar los nombres de las columnas a la tabla
   
           // add al modelo las 5 columnas con los nombrs TABLA
        modelo.addColumn("Tipo"); 
           modelo.addColumn("Cantidad");       
        modelo.addColumn("Fecha");
        modelo.addColumn("Total");
      
              
     jTableGastos.setModel(modelo);  // Ya una vez asignado todos los nombres se le envia el objeto a la tabla proveedores
    String []datos = new String[4];     //Un arreglo con la cantidad de nombres en las columnas
    try {
      
             sent = ca.createStatement();   
                               //      rs = sent.executeQuery("select * from descripcion_de_venta where id_venta= '"+id_de_la_venta_incrementable+"'");
                       if(textobusqueda.equals("")){
                          rs= sent.executeQuery("SELECT * FROM `egreso` order by fecha desc"); // se ejecuta la sentencia dentro del parentesis
 //  SELECT `idegreso`,`cantidad`,`tipo`,`fecha`,`total`,`nombre` FROM `egreso` INNER JOIN user WHERE egreso.`usuario` = user.id_usuario and fecha = curdate()";   
                       }
                       else{
                   
                           rs= sent.executeQuery("SELECT * FROM egreso where tipo LIKE '%" +textobusqueda+"%' or total LIKE '%" +textobusqueda+"%'  or fecha LIKE '%" +textobusqueda+"%'  or cantidad LIKE '%" +textobusqueda+"%' order by fecha desc" ); // se ejecuta la sentencia dentro del parentesis
           
                       }
             while(rs.next()){        
            datos[0]=rs.getString(3);
            datos[1]=rs.getString(2);
            datos[2]=rs.getString(4);
            datos[3]=rs.getString(5);
            modelo.addRow(datos); //se asigna el arreglo  entero a todo el objeto llamado modelo  
            }
           jTableGastos.setModel(modelo); // Se vuelve a enviar nuevamente el objeto modelo a la tabla
        } catch (SQLException ex) {
            Logger.getLogger(menu_principal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo mostrar ningun dato porque tu consulta está mal");
        } 
    }

             
    
     
      public void LlenarTabla(JTable tablaD){ // recibe como parametro 
         Object[] columna = new Object[6];  //crear un obj con el nombre de colunna
            Connection ca= cc.conexion(); // CONEXION DB 
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  tablaD.setModel(modeloT);  // add modelo ala tabla 
        
        modeloT.addColumn("Idegreso");    // add al modelo las 5 columnas con los nombrs TABLA
        modeloT.addColumn("Tipo"); 
        modeloT.addColumn("Cantidad");       
        modeloT.addColumn("Fecha");
        modeloT.addColumn("Total");
       modeloT.addColumn("nombre");               
     try {
         String sSQL = "SELECT `idegreso`,`cantidad`,`tipo`,`fecha`,`total`,`nombre` FROM `egreso` INNER JOIN user WHERE egreso.`usuario` = user.id_usuario order by fecha desc";
     PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                columna[0] = rs.getString("idegreso");  /* === LA DB == */
                columna[1] = rs.getString("tipo");
                columna[2] = rs.getString("cantidad");
                columna[3] = rs.getString("fecha");
                columna[4] = rs.getString("total");
                columna[5] = rs.getString("nombre");                
                modeloT.addRow(columna);
            }
        }
        ps.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Advertencia", JOptionPane.PLAIN_MESSAGE);    
    }
}
     
     public void limpiar(){     /*====  VACIAR CAMPOS */
            txtdescripcion.setText(null);
            txtmonto.setText(null);

           // vistaGastos.jDateChooserFecha.setDate(null);
         }         
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableGastos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        txtmonto = new javax.swing.JTextField();
        txtdescripcion = new javax.swing.JTextField();
        jDateChooserFecha = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        btnRegistrarGasto = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtpiezas = new javax.swing.JTextField();
        busquedagastos = new javax.swing.JTextField();
        gastos_btn_back = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GASTOS", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 36))); // NOI18N
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setLayout(null);

        jTableGastos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTableGastos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTableGastos);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(10, 240, 650, 230);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cantidad/Piezas:");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(490, 10, 150, 29);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Descripción del Gasto:");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(10, 10, 220, 29);

        txtmonto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(txtmonto);
        txtmonto.setBounds(10, 110, 210, 40);
        jPanel2.add(txtdescripcion);
        txtdescripcion.setBounds(10, 40, 480, 40);

        jDateChooserFecha.setDateFormatString("yyy/MM/dd");
        jDateChooserFecha.setEnabled(false);
        jDateChooserFecha.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jDateChooserFecha);
        jDateChooserFecha.setBounds(250, 110, 210, 40);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Monto del Gasto:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(10, 80, 160, 29);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Busqueda de Gastos");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(10, 150, 250, 40);

        btnRegistrarGasto.setBackground(new java.awt.Color(0, 51, 102));
        btnRegistrarGasto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRegistrarGasto.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarGasto.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/combustible (1).png"))); // NOI18N
        btnRegistrarGasto.setText("Registrar gastos");
        btnRegistrarGasto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarGastoActionPerformed(evt);
            }
        });
        jPanel2.add(btnRegistrarGasto);
        btnRegistrarGasto.setBounds(460, 180, 190, 50);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Fecha:");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(250, 80, 90, 29);

        txtpiezas.setBackground(new java.awt.Color(0, 148, 204));
        txtpiezas.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        txtpiezas.setForeground(new java.awt.Color(255, 255, 255));
        txtpiezas.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        txtpiezas.setText("00.00");
        txtpiezas.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        txtpiezas.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtpiezasFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtpiezasFocusLost(evt);
            }
        });
        txtpiezas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtpiezasActionPerformed(evt);
            }
        });
        jPanel2.add(txtpiezas);
        txtpiezas.setBounds(500, 40, 150, 40);

        busquedagastos.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedagastosKeyReleased(evt);
            }
        });
        jPanel2.add(busquedagastos);
        busquedagastos.setBounds(10, 200, 220, 30);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(0, 50, 690, 480);

        gastos_btn_back.setBackground(new java.awt.Color(255, 255, 255));
        gastos_btn_back.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        gastos_btn_back.setForeground(new java.awt.Color(0, 0, 0));
        gastos_btn_back.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/salir-flecha-derecha (1).png"))); // NOI18N
        gastos_btn_back.setText("Regresar");
        gastos_btn_back.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                gastos_btn_backActionPerformed(evt);
            }
        });
        jPanel1.add(gastos_btn_back);
        gastos_btn_back.setBounds(530, 0, 150, 40);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 685, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
/*
    public void autocompletar(){ //metodo sin retorno para obtener la lista de campos de la tabla productos la cual obtiene cada uno de los nombres para poder hacer algunas coincidencias al momento que el usuario estÃ¡ escribiendo
          ArrayList<String> lista = new ArrayList<String>();
      
       menu_principal.searchforproducts.removeAllItems(); //Ã‰sta linea es importante ya que cada vez que se llama este metodo se eliminan los item que previamente se cargaron en la llamada anterior, ESTO PARA QUE NO SE VUELVAN AGREGAR LOS MISMOS ITEMS, MÃ�S DE 1 VEZ
        try{
            sent  =(Statement)ca.createStatement();
           rs = sent.executeQuery("select nombre_producto from productos ");
            while(rs.next()){
              menu_principal.searchforproducts.addItem(rs.getString("nombre_producto"));
            }
            for(int a=0; a<lista.size(); a++){
          menu_principal.searchforproducts.addItem(lista.get(a)); //Este ciclo lo que hace es ordenarlos de manera de lista descendente
        }
        }catch (Exception e){
            
        }
    }
    */
public void obtener_id_del_proveedor(String name){
    String nombredelaempresa=name;
        try{
            sent  =(Statement)ca.createStatement();
           rs = sent.executeQuery("select * from proveedores where nombre_de_la_empresa= '"+nombredelaempresa+"' ");
            while(rs.next()){
               id_proveedor=rs.getInt("id_proveedor");
            }

        }catch (Exception e){
            
        }
}



    private void btnRegistrarGastoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarGastoActionPerformed
        // ABRE NUEVA VENTANA PARA Registro de Gastos
          if (txtdescripcion.getText().isEmpty() || txtmonto.getText().isEmpty() /* || txtpiezas.getText().isEmpty()*/) {
                    JOptionPane.showMessageDialog(null, "No dejar campos en blanco");
                } else {             
                    boolean pass = validarFormulario(txtmonto.getText());
                    boolean pass2 = validarFormulariotexto(txtdescripcion.getText());
                    boolean pass3 = validarFormulariopiezas(txtpiezas.getText());

                    if (pass && pass2 && pass3) {
                        
    float totalmonto = Integer.parseInt(txtmonto.getText()); //puse otro de tipo float xq total no me reconoce como string a float
                        cantidad = Float.parseFloat(txtpiezas.getText());
                          tipo = txtdescripcion.getText();                                                                                                                                    
                         String fecha = fecha(); 
                       pollocrudo= txtdescripcion.getText();                        
                        gastos = new Gastos(cantidad, tipo, totalmonto, id_usuario, fecha);
                       if (gastos.Gastosinsert()) { //  aki me insertar en una de las dos tablas mas no en las dos

                         JOptionPane.showMessageDialog(null, "Gastos Registrados con Exito...");
                            limpiar();
                            JOptionPane.showMessageDialog(null, "Generando Ticket de Gastos...");
                           // txtpiezas.setText("0");
                            LlenarTabla(jTableGastos); // LLENANDO LA TABLA AL INSERTAR CORRECTAMEBTE
                            tikectGastos = new TikectGasto();
                            tikectGastos.TikectGasto(cantidad ,tipo, String.valueOf(totalmonto));

                       }/*0*/ else { /*4*/
                            JOptionPane.showMessageDialog(null, "error", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }/*4*/
    
                    }//
                } //
 
    }//GEN-LAST:event_btnRegistrarGastoActionPerformed

    private void txtpiezasFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpiezasFocusGained
        // *********************   CAJA DE TEXTO DE PAGOO *********
        if(txtpiezas.getText().trim().equals("00.00")){
            txtpiezas.setText("");
            //user_usuario.setForeground(Color.red);
        }
        txtpiezas.setForeground(Color.blue);
    }//GEN-LAST:event_txtpiezasFocusGained

    private void txtpiezasFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtpiezasFocusLost
        // *********************   CAJA DE TEXTO DE PAGOO *********
        if(txtpiezas.getText().trim().equals("")){
            txtpiezas.setText("00.00");
        }
        txtpiezas.setForeground(new Color(236, 240, 241));
    }//GEN-LAST:event_txtpiezasFocusLost

    private void txtpiezasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtpiezasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtpiezasActionPerformed

    private void busquedagastosKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedagastosKeyReleased
        String textobusqueda = busquedagastos.getText();
        mostrartodoslosproductosenexistenciasporbusqueda(textobusqueda);
    }//GEN-LAST:event_busquedagastosKeyReleased

    private void gastos_btn_backActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_gastos_btn_backActionPerformed
        // BOTON DE CANCELAR LA INSERCION DE NUEVO USUARIO
        dispose();
    }//GEN-LAST:event_gastos_btn_backActionPerformed

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
            java.util.logging.Logger.getLogger(Pantalla_Gastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pantalla_Gastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pantalla_Gastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pantalla_Gastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pantalla_Gastos().setVisible(true);
            }
        });
    }
    
    
    
    
    SI cc= new SI();
 Connection ca= cc.conexion();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public javax.swing.JButton btnRegistrarGasto;
    private javax.swing.JTextField busquedagastos;
    private javax.swing.JButton gastos_btn_back;
    public com.toedter.calendar.JDateChooser jDateChooserFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable jTableGastos;
    public javax.swing.JTextField txtdescripcion;
    public javax.swing.JTextField txtmonto;
    public static javax.swing.JTextField txtpiezas;
    // End of variables declaration//GEN-END:variables

    
}
