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
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import si.Gastos;
import static si.menu_principal.searchforproducts;
import ticket.TikectGasto;
import static si.menu_principal.venta; // DANDO ACCESOO ALA INTERFAZ PRINCIPAL


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
       autocompletar();
        this.setLocationRelativeTo(null); // CENTRAR FORMULARIO
         AutoCompleteDecorator.decorate(menu_principal.searchforproducts);
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

             
    
     
      public void LlenarTabla(JTable tablaD){ // recibe como parametro 
         Object[] columna = new Object[6];  //crear un obj con el nombre de colunna
            Connection ca= cc.conexion(); // CONEXION DB 
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  tablaD.setModel(modeloT);  // add modelo ala tabla 
        
        modeloT.addColumn("Idegreso");    // add al modelo las 5 columnas con los nombrs TABLA
        modeloT.addColumn("Cantidad");
        modeloT.addColumn("Tipo");        
        modeloT.addColumn("Fecha");
        modeloT.addColumn("Total");
       modeloT.addColumn("nombre");               
         /* SELECT `idegreso`, `tipo`, `total`, `fecha`, turno FROM `egreso` \n" + "  INNER JOIN empleado\n" + "WHERE egreso.`empleado_idempleado` = empleado.idempleado";     */    
        try {
         String sSQL = "SELECT `idegreso`,`cantidad`,`tipo`,`fecha`,`total`,`nombre` FROM `egreso` INNER JOIN user WHERE egreso.`usuario` = user.id_usuario";
 // SELECT `idegreso`, `tipo`, `fecha`, `total`, nombre FROM `egreso` INNER JOIN user WHERE egreso.`id_usuario` = user.id_usuario
         
  // String sSQL = "SELECT * FROM egreso\n" + "WHERE fecha = '"+llenarfechadehoy()+"'";
         
         //   "SELECT *FROM egreso\n" + "WHERE fecha = '2019-07-20'";            
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                columna[0] = rs.getString("idegreso");  /* === LA DB == */
                columna[1] = rs.getString("cantidad");
                columna[2] = rs.getString("tipo");
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
            txtpiezas.setText(null);
           // vistaGastos.jDateChooserFecha.setDate(null);
         }         
     
     
     
     
     
     boolean comprobarpollo(){ //1
         actualizar_pollocrudo();
     boolean resultado=false;
              
         
       
          
            
           for(int i=0; i<piezas.length; i++) {
  

            try{
                       sent  = (Statement)ca.createStatement();
                                           rs = sent.executeQuery("select * from productos  where nombre_producto='"+piezas[i]+"' and fecha= '"+fecha()+"'");
                                            while(rs.next()){
                                                      buscap =rs.getString("nombre_producto");
                                                      cantidadpolloenDB =rs.getInt("cantidad"); // piezas en la db
                                                      }
            
   if(buscap.equals(piezas[i])){ //Si el nombre del producto es diferente del estado vacio, en palabras más sencillas; si se encuentra el producto que se quiere agregar para que no se asigne nuevamente  
        try{// el id del usuario
                if(piezas[i].equals("Pechuga")||piezas[i].equals("Muslo")||
                   piezas[i].equals("Pierna")||
                   piezas[i].equals("Ala")||
                   piezas[i].equals("Patas")){
                    
               resultadodepiezaspares=cantidad*piezasdepollopares;
                   addpiezas=cantidadpolloenDB+resultadodepiezaspares;
               
               PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+addpiezas+"'WHERE nombre_producto='"+piezas[i]+"' and fecha= '"+fecha()+"'");
               int ty = ps.executeUpdate();
                
                 if(ty>0){
                     resultado = true;
            
                 }else{
                     resultado = false;
                 }
           }
           else if(piezas[i].equals("Huacal")||piezas[i].equals("Cadera")||
                   piezas[i].equals("Cabeza")||
                   piezas[i].equals("Molleja")){
            resultadodepiezasinpares=cantidad*piezasdepollosinpares;
          addpiezas=cantidadpolloenDB+resultadodepiezasinpares;
           PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+addpiezas+"'WHERE nombre_producto='"+piezas[i]+"' and fecha= '"+fecha()+"'");
               int ty = ps.executeUpdate();
                
                 if(ty>0){
                     resultado = true;
                 }else{
                     resultado = false;
                 }
        }
        }//fin del id del usuario
                 catch(Exception w){
                     JOptionPane.showMessageDialog(null, "Error" + w.getMessage());
                 }//fin del id del usuario
               }
 
         }catch (Exception f){
                    JOptionPane.showMessageDialog(null, "Error, nombre de producto no registrado" + f.getMessage());
                
         }
     
         
           }//2
        return resultado;
     } //1
     
     
     public void actualizar_pollocrudo(){ //1
    
      String buscap = "";     
  
            try{
                       sent  = (Statement)ca.createStatement();
                                           rs = sent.executeQuery("select * from productos  where nombre_producto='"+tipo+"' and fecha= '"+fecha()+"'");
                                            while(rs.next()){
                                                      buscap =rs.getString("nombre_producto");
                                                      cantidadpolloenDB =rs.getInt("cantidad"); // piezas en la db
                                                      }
                  
           if(buscap.equalsIgnoreCase(tipo)){
   try{// el id del usuario

              addpiezas=cantidadpolloenDB+cantidad;
            
               PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+addpiezas+"'WHERE nombre_producto='"+tipo+"' and fecha= '"+fecha()+"'");
               int ty = ps.executeUpdate();
                 if(ty>0){
                   
         JOptionPane.showMessageDialog(null, "SALIO DE ACTUALIZAR POLLO CRUDO0", "POLLO CRUDO",JOptionPane.INFORMATION_MESSAGE);
                 }else{
                   
                 }

        }//fin del id del usuario
                 catch(Exception w){
                     JOptionPane.showMessageDialog(null, "Error" + w.getMessage());
                 }//fin del id del usuario
            }        
         }catch (Exception f){
                    JOptionPane.showMessageDialog(null, "Error, nombre de producto no registrado" + f.getMessage());
         }
     } //1
     
     
     
      /*  ======   HACIENDO UNA CONSULTA DE LOS GASTOS A BUSCAR CON -- ((UNA)) -- FECHA DETERINADA =======A*/          
          public void LlenarTablaBusquedaFecha(JTable tablaD, String jDateXUnaFecha){ // recibe como parametro 
         Object[] columna = new Object[6];  //crear un obj con el nombre de colunna
            Connection ca= cc.conexion(); // CONEXION DB 
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  tablaD.setModel(modeloT);  // add modelo ala tabla 
        
        modeloT.addColumn("Idegreso");    // add al modelo las 5 columnas con los nombrs TABLA
        modeloT.addColumn("Cantidad");
        modeloT.addColumn("Tipo");        
        modeloT.addColumn("Fecha");
        modeloT.addColumn("Total");
       modeloT.addColumn("nombre");               
         /* SELECT `idegreso`, `tipo`, `total`, `fecha`, turno FROM `egreso` \n" + "  INNER JOIN empleado\n" + "WHERE egreso.`empleado_idempleado` = empleado.idempleado";     */    
        try {
         String sSQL = "SELECT `idegreso`,`cantidad`,`tipo`,`fecha`,`total`,`nombre` FROM `egreso` INNER JOIN user WHERE egreso.`usuario` = user.id_usuario AND fecha = '"+llenarfechadehoy()+"'";
         
  // String sSQL = "SELECT * FROM egreso\n" + "WHERE fecha = '"+llenarfechadehoy()+"'";
         
         //   "SELECT *FROM egreso\n" + "WHERE fecha = '2019-07-20'";            
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                columna[0] = rs.getString("idegreso");  /* === LA DB == */
                columna[1] = rs.getString("cantidad");
                columna[2] = rs.getString("tipo");
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
     
     public String llenarfechadehoy(){         
       int año= jDateXUnaFecha.getCalendar().get(Calendar.YEAR);
       int mes= jDateXUnaFecha.getCalendar().get(Calendar.MONTH)+1;
       int dia= jDateXUnaFecha.getCalendar().get(Calendar.DAY_OF_MONTH);
       fechahoy= año+"/"+mes+"/"+dia;
        return fechahoy;
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
        btnListar = new javax.swing.JButton();
        txtmonto = new javax.swing.JTextField();
        txtdescripcion = new javax.swing.JTextField();
        jDateChooserFecha = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableGastosFechaActual = new javax.swing.JTable();
        jDateXUnaFecha = new com.toedter.calendar.JDateChooser();
        B_cancelar = new javax.swing.JButton();
        btnRegistrarGasto = new javax.swing.JButton();
        btnImprimirticket = new javax.swing.JButton();
        jLabel5 = new javax.swing.JLabel();
        txtpiezas = new javax.swing.JTextField();
        buscargastos = new javax.swing.JButton();

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
                "Idegreso", "Cantidad", "Tipo", "Total", "Fecha", "Usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableGastos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTableGastos);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(10, 240, 650, 230);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Cantidad/Piezas:");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(250, 80, 150, 29);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Descripción del Gasto:");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(10, 10, 220, 29);

        btnListar.setBackground(new java.awt.Color(0, 148, 204));
        btnListar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnListar.setForeground(new java.awt.Color(255, 255, 255));
        btnListar.setText("Listar Gastos");
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });
        jPanel2.add(btnListar);
        btnListar.setBounds(430, 110, 210, 50);

        txtmonto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(txtmonto);
        txtmonto.setBounds(10, 110, 210, 40);
        jPanel2.add(txtdescripcion);
        txtdescripcion.setBounds(10, 40, 480, 40);

        jDateChooserFecha.setDateFormatString("yyy/MM/dd");
        jDateChooserFecha.setEnabled(false);
        jDateChooserFecha.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jDateChooserFecha);
        jDateChooserFecha.setBounds(10, 180, 210, 40);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Monto del Gasto:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(10, 80, 160, 29);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Busqueda de Gastos");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(870, 10, 250, 40);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Fecha:");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(790, 90, 80, 40);

        jTableGastosFechaActual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Idegreso", "Cantidad", "Tipo", "Total", "Fecha", "Usuario"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableGastosFechaActual);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(680, 150, 600, 170);

        jDateXUnaFecha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jDateXUnaFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateXUnaFechaPropertyChange(evt);
            }
        });
        jPanel2.add(jDateXUnaFecha);
        jDateXUnaFecha.setBounds(870, 90, 230, 40);

        B_cancelar.setBackground(new java.awt.Color(242, 38, 19));
        B_cancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        B_cancelar.setText("Regresar");
        B_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_cancelarActionPerformed(evt);
            }
        });
        jPanel2.add(B_cancelar);
        B_cancelar.setBounds(1080, 420, 200, 40);

        btnRegistrarGasto.setBackground(new java.awt.Color(0, 148, 204));
        btnRegistrarGasto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRegistrarGasto.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarGasto.setText("Registrar Gastos");
        btnRegistrarGasto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarGastoActionPerformed(evt);
            }
        });
        jPanel2.add(btnRegistrarGasto);
        btnRegistrarGasto.setBounds(430, 170, 210, 50);

        btnImprimirticket.setBackground(new java.awt.Color(0, 148, 204));
        btnImprimirticket.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnImprimirticket.setForeground(new java.awt.Color(255, 255, 255));
        btnImprimirticket.setText("Imprimir Tikect");
        btnImprimirticket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirticketActionPerformed(evt);
            }
        });
        jPanel2.add(btnImprimirticket);
        btnImprimirticket.setBounds(910, 340, 200, 50);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Fecha:");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(10, 150, 90, 29);

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
        txtpiezas.setBounds(240, 120, 150, 40);

        buscargastos.setBackground(new java.awt.Color(0, 148, 204));
        buscargastos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buscargastos.setForeground(new java.awt.Color(255, 255, 255));
        buscargastos.setText("Buscar");
        buscargastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscargastosActionPerformed(evt);
            }
        });
        jPanel2.add(buscargastos);
        buscargastos.setBounds(1120, 80, 150, 50);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 50, 1290, 480);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1327, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void B_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_cancelarActionPerformed
        // BOTON DE CANCELAR LA INSERCION DE NUEVO USUARIO
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Regresar a pagina anterior?","                    Aviso",dialogButton);
        if(result == 0){
            dispose();   }
    }//GEN-LAST:event_B_cancelarActionPerformed

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        // Listar Gastos
        LlenarTabla(jTableGastos);
        
    }//GEN-LAST:event_btnListarActionPerformed
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

public void registrar_pollo_crudo(String name, float precio, float cantidad, int id){
         String sql = null;
         float preciodelpollocrudo=precio/cantidad;
try {
          Statement sent = ca.createStatement(); 
          sql = "INSERT INTO productos (nombre_producto, tipo_producto, precio, cantidad, fecha, id_proveedor, fecha_de_caducidad)  VALUES (?,?,?,?,?,?,?)";
         PreparedStatement pst = ca.prepareCall(sql);
           // sql = "INSERT INTO egreso (tipo,fecha, total, user_id_usuario)  VALUES (?,?,?,?)";
           
           pst.setString(1, name);
           pst.setString(2, "Pollos");
           pst.setFloat(3, preciodelpollocrudo);
          pst.setFloat(4,cantidad);
          pst.setString(5, fecha());
          pst.setInt(6, id);
          pst.setString(7, "Sin fecha de caducidad");
                      
          tt = pst.executeUpdate();
            pst.close();
             
        } catch (SQLException ex) {
            System.err.print(ex);
           // return false;
        }
}

    private void btnRegistrarGastoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarGastoActionPerformed
        // ABRE NUEVA VENTANA PARA Registro de Gastos
          if (txtdescripcion.getText().isEmpty() || txtmonto.getText().isEmpty() /* || txtpiezas.getText().isEmpty()*/) {
                    JOptionPane.showMessageDialog(null, "No dejar campos en blanco");
                } else {             
                    boolean pass = validarFormulario(txtmonto.getText());
                    boolean pass2 = validarFormulariotexto(txtdescripcion.getText());
                  //  boolean pass3 = validarFormulariopiezas(txtpiezas.getText());

                    if (pass && pass2 /*&& pass3*/) {
                        
    float totalmonto = Integer.parseInt(txtmonto.getText()); //puse otro de tipo float xq total no me reconoce como string a float
                        cantidad = Float.parseFloat(txtpiezas.getText());
                          tipo = txtdescripcion.getText();                                                                                                                                    
                         String fecha = fecha(); 
                       pollocrudo= txtdescripcion.getText();                        
                         
 if("pollo crudo".equalsIgnoreCase(txtdescripcion.getText())){/*1*/
                    gastos = new Gastos(cantidad, tipo, totalmonto, id_usuario, fecha);
                         gastos.Gastosinsert();
                         limpiar();
                         LlenarTabla(jTableGastos);
                             JOptionPane.showMessageDialog(null, "Gastos Registrados con Exito... entrando desde gastos en productos");
   float totalpiezaspollo = (cantidad*piezasxunpollo);
 float precioxpieza = (totalmonto/totalpiezaspollo);   
     
  if(comprobarpollo()){
   JOptionPane.showMessageDialog(null," CANTIDAD ACTUALIZADA DE PRODUCTOS ");
  }
  else{
       obtener_id_del_proveedor(menu_principal.proveedorarticulo.getSelectedItem().toString());
    
      registrar_pollo_crudo(pollocrudo, totalmonto, cantidad, id_proveedor); //Esto registra 1 pollo o la cantidad de pollos que metas          
          for(int i=0; i<piezas.length; i++) {
 
         
          obtener_id_del_proveedor(menu_principal.proveedorarticulo.getSelectedItem().toString());
    
            String sql = null;
               try {
            
          Statement sent = ca.createStatement(); 
          sql = "INSERT INTO productos (nombre_producto, tipo_producto, precio, cantidad, fecha, id_proveedor, fecha_de_caducidad)  VALUES (?,?,?,?,?,?,?)";
         PreparedStatement pst = ca.prepareCall(sql);
           // sql = "INSERT INTO egreso (tipo,fecha, total, user_id_usuario)  VALUES (?,?,?,?)";
           
           pst.setString(1, piezas[i]);
           pst.setString(2, "Pollos");
           pst.setFloat(3, precioxpieza);
           //DE ACUERDO A LA PIEZA SON LAS CANTIDADES
           if(piezas[i].equals("Pechuga")||piezas[i].equals("Muslo")||
                   piezas[i].equals("Pierna")||
                   piezas[i].equals("Ala")||
                   piezas[i].equals("Patas")){
               resultadodepiezaspares=cantidad*piezasdepollopares;
               pst.setFloat(4,resultadodepiezaspares);
           }
           else if(piezas[i].equals("Huacal")||piezas[i].equals("Cadera")||
                   piezas[i].equals("Cabeza")||
                   piezas[i].equals("Molleja")){
            resultadodepiezasinpares=cantidad*piezasdepollosinpares;
               pst.setFloat(4,resultadodepiezasinpares);
        }
           
           pst.setString(5, fecha());
          pst.setInt(6, id_proveedor);
          pst.setString(7, "Sin fecha de caducidad");
                      
          tt = pst.executeUpdate();
            pst.close();
             
        } catch (SQLException ex) {
            System.err.print(ex);
           // return false;
        }
          
          }                                                
                   
                  // gastos = new Gastos(piezas, precioxpieza, pollosdivididos);
                  // gastos = new Gastos(cantidad, tipo, total, usuarioname, fecha);
                   if (tt>0 ) {/*2*/ /* && gastos.Gastosinsert() */
                       JOptionPane.showMessageDialog(null, "Productos Registrados con Exito...");
                       
                       limpiar();
                       autocompletar();
                   }  /*2*/  
                   
 }
        
        }/*1*/ else if(!"pollo crudo".equalsIgnoreCase(txtdescripcion.getText())){/*3*/
           // txtpiezas.setEnabled(false);  // ME ACTIVA EL TXT
            txtpiezas.setText("0");

                                gastos = new Gastos(cantidad, tipo, totalmonto, id_usuario, fecha);
                       if (gastos.Gastosinsert()) { //  aki me insertar en una de las dos tablas mas no en las dos
       
                            
                            
  //======================================== ============================================================================= /
                            
                            
                            
                            
                         JOptionPane.showMessageDialog(null, "Gastos Registrados con Exito...");
                            limpiar();
                            JOptionPane.showMessageDialog(null, "Generando Ticket de Gastos...");
                           // txtpiezas.setText("0");
                            LlenarTabla(jTableGastos); // LLENANDO LA TABLA AL INSERTAR CORRECTAMEBTE
                            tikectGastos = new TikectGasto();
                            tikectGastos.TikectGasto(cantidad ,tipo, total);

                       }/*0*/ else { /*4*/
                            JOptionPane.showMessageDialog(null, "error", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }/*4*/
        }/*3*/ 
                    }//
                } //
            
        
    }//GEN-LAST:event_btnRegistrarGastoActionPerformed

    private void btnImprimirticketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirticketActionPerformed
        // BOTON EN LA  BUSKEDA DE IMPRIMIR TICKET
         int fila;
                try {
                    fila = jTableGastosFechaActual.getSelectedRow();
                    if (fila == -1) {
                        JOptionPane.showMessageDialog(null, "No ha seleccionado ninguna fila.", "ERROR", JOptionPane.ERROR_MESSAGE);
                    } else {
                        int cantidad = (int) jTableGastosFechaActual.getValueAt(fila, 1);
                        String tipo = (String) jTableGastosFechaActual.getValueAt(fila, 2);
                        String total = (String) jTableGastosFechaActual.getValueAt(fila, 3);
                        JOptionPane.showMessageDialog(null, "Ticket Generado Exitosamente");
                        tikectGastos = new TikectGasto();
                        tikectGastos.TikectGasto(cantidad, tipo, total);
                    }
                } catch (Exception ex) {
                }  
        
    }//GEN-LAST:event_btnImprimirticketActionPerformed

    private void jDateXUnaFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateXUnaFechaPropertyChange
        //  ME HACE LA BUSKEDA AL PONER UNA FECJA EN EL CHOOSERDATE
         
            /* String fechahoy= jDateXUnaFecha.toString();
          
            LlenarTablaBusquedaFecha(jTableGastosFechaActual, llenarfechadehoy());  */

    }//GEN-LAST:event_jDateXUnaFechaPropertyChange

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

    private void buscargastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscargastosActionPerformed
        // BOTON PARA LA CONSULTA DE  GASTOS 
        String fechahoy= jDateXUnaFecha.toString();
          
            LlenarTablaBusquedaFecha(jTableGastosFechaActual, llenarfechadehoy());
    }//GEN-LAST:event_buscargastosActionPerformed

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
    private javax.swing.JButton B_cancelar;
    private javax.swing.JButton btnImprimirticket;
    private javax.swing.JButton btnListar;
    public javax.swing.JButton btnRegistrarGasto;
    private javax.swing.JButton buscargastos;
    public com.toedter.calendar.JDateChooser jDateChooserFecha;
    public com.toedter.calendar.JDateChooser jDateXUnaFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable jTableGastos;
    public javax.swing.JTable jTableGastosFechaActual;
    public javax.swing.JTextField txtdescripcion;
    public javax.swing.JTextField txtmonto;
    public static javax.swing.JTextField txtpiezas;
    // End of variables declaration//GEN-END:variables

    
}
