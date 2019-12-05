package si;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
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
import ticket.TicketVentaExterna;

public class ProductosExternos extends javax.swing.JFrame  implements Runnable{
   SI cc= new SI();
  menu_principal menuprin ;    
    float piezaendb=0;
    float totalapagar=0;
    float cantidadnumerica=0;
    int a=0;
      DecimalFormat solodosdecimales = new DecimalFormat("#.##");
            ArrayList nombres = new ArrayList();
             ArrayList piezas = new ArrayList();
        String productoexternoblanca="productoexternoblanca";
        String productoexternocentral="productoexternocentral";
        String productoexternozapata="productoexternozapata";
       TicketVentaExterna ticketVentasExternas;
    Thread hilo;
    String hora,minutos,segundos;
    Statement sent;  
  ResultSet rs;     
String  usuarioname=SI_Inicio.text_user.getText();
int  id_usuario=Integer.parseInt(SI_Inicio.iduser.getText());
    Calendar fecha_actual = new GregorianCalendar();
    public ProductosExternos() {
        initComponents();
        menu_principal.noduplicarexternos=true;
         hilo=new Thread(this);
     hilo.start();
        this.setLocationRelativeTo(null); // CENTRAR FORMULARIO
        Fecha.setText(fecha());
       // user.setText(usuarioname);        
    }
    
      public void LlenarTableDatosblanca(JTable tablaD){ // recibe como parametro 
         Object[] columna = new Object[2];  //crear un obj con el nombre de colunna
           
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  tablaD.setModel(modeloT);  // add modelo ala tabla         
         modeloT.addColumn("Producto");           
        modeloT.addColumn("Pieza");
     EtiquetaSucursal.setText("Productos de Blanca");
        try { Connection ca= cc.conexion();
   String sSQL = "SELECT nombre, pieza FROM productoexternoblanca";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {          
                 columna[0] = rs.getString(1);                
                columna[1] = (int) rs.getFloat(2);                              
                modeloT.addRow(columna);
            }
        }
    } catch (Exception e) { JOptionPane.showMessageDialog(null, "ERROR EN METODO: LlenarTableDatosblanca","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{cc.getClose();}
}
            
       public void LlenarTableDatosMercado(JTable tablaD){ // recibe como parametro 
         Object[] columna = new Object[2];  //crear un obj con el nombre de colunna
  
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  tablaD.setModel(modeloT);  // add modelo ala tabla         
         modeloT.addColumn("Producto");           
        modeloT.addColumn("Pieza");
       EtiquetaSucursal.setText("Productos de Mercado");
        try {          Connection ca= cc.conexion(); // CONEXION DB 
         String sSQL = "SELECT nombre, pieza FROM productoexternocentral";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                 columna[0] = rs.getString(1);                
                columna[1] = (int) rs.getFloat(2);
                modeloT.addRow(columna);
            }
        }
    } catch (Exception e) { JOptionPane.showMessageDialog(null, "ERROR EN METODO: LlenarTableDatosMercado","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{cc.getClose();}
}
    
        public void LlenarTableDatoszapata(JTable tablaD){ // recibe como parametro 
         Object[] columna = new Object[2];  //crear un obj con el nombre de colunna
           
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  tablaD.setModel(modeloT);  // add modelo ala tabla         
         modeloT.addColumn("Producto");           
        modeloT.addColumn("Pieza");
         EtiquetaSucursal.setText("Productos de Zapata");
        try { Connection ca= cc.conexion(); // CONEXION DB 
         String sSQL = "SELECT nombre, pieza FROM productoexternozapata";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                 columna[0] = rs.getString(1);                
                columna[1] = (int) rs.getFloat(2);
                modeloT.addRow(columna);
            }
        }
    } catch (Exception e) { JOptionPane.showMessageDialog(null, "ERROR EN METODO: LlenarTableDatoszapata","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{cc.getClose();}
}
                    
    public void hora(){
        Calendar calendario=new GregorianCalendar();
        Date horaactual=new Date();
        calendario.setTime(horaactual);
    hora=calendario.get(Calendar.HOUR)>9?""+calendario.get(Calendar.HOUR):"0"+calendario.get(Calendar.HOUR);
    minutos=calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
    segundos= calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);        
     }
    public void run() {
      Thread ct = Thread.currentThread();
      while(ct==hilo){
          hora();
          Reloj.setText(hora+":"+minutos+":"+segundos+" ");
      }
    }
    
    public static String fecha(){ /* SE DECARA LA FECHA DEL SISTEMA */
        Date fecha=new Date();
        SimpleDateFormat formatoFecha= new SimpleDateFormat("YYYY/MM/dd");
        return formatoFecha.format(fecha);
    }
    
    public boolean validarFormulario(String cantidaddelatabla) { // VALIDACION DE TXT MONTO
        boolean next = false;
        Pattern patGastos = Pattern.compile("^[0-9]+([.])?([0-9]+)?$");
        Matcher matGastos = patGastos.matcher(cantidaddelatabla);
        if (matGastos.matches()&&!cantidaddelatabla.equals("")&&!cantidaddelatabla.equals("0")) {
            next = true;               
        } else {
            JOptionPane.showMessageDialog(null, "No puedes escribir letras, dejar vacio el campo ni meter un 0", "Advertencia", JOptionPane.INFORMATION_MESSAGE);    
        }
        return next;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Corte_btncancelar = new javax.swing.JButton();
        Fecha = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        J_tableLlenados = new rojerusan.RSTableMetro();
        Reloj = new javax.swing.JLabel();
        combopieza = new javax.swing.JComboBox<>();
        combosucursal = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        EtiquetaSucursal = new javax.swing.JLabel();
        calculadora = new javax.swing.JPanel();
        borrar = new javax.swing.JButton();
        nueve = new javax.swing.JButton();
        ocho = new javax.swing.JButton();
        siete = new javax.swing.JButton();
        cuatro = new javax.swing.JButton();
        cinco = new javax.swing.JButton();
        seis = new javax.swing.JButton();
        tres = new javax.swing.JButton();
        dos = new javax.swing.JButton();
        uno = new javax.swing.JButton();
        listo = new javax.swing.JButton();
        cantidad = new javax.swing.JTextField();
        salir = new javax.swing.JButton();
        cero = new javax.swing.JButton();
        pago = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        user = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Producto externo", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 36))); // NOI18N
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setLayout(null);

        Corte_btncancelar.setBackground(new java.awt.Color(0, 51, 102));
        Corte_btncancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Corte_btncancelar.setForeground(new java.awt.Color(255, 255, 255));
        Corte_btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/salir-flecha-derecha (1).png"))); // NOI18N
        Corte_btncancelar.setText("Regresar");
        Corte_btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Corte_btncancelarActionPerformed(evt);
            }
        });
        jPanel2.add(Corte_btncancelar);
        Corte_btncancelar.setBounds(20, 410, 250, 60);

        Fecha.setFont(new java.awt.Font("Times New Roman", 1, 27)); // NOI18N
        Fecha.setForeground(new java.awt.Color(255, 255, 255));
        Fecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Fecha.setText("DD/MM/YYYY");
        jPanel2.add(Fecha);
        Fecha.setBounds(0, 0, 230, 60);

        J_tableLlenados.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        J_tableLlenados.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        J_tableLlenados.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        J_tableLlenados.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        J_tableLlenados.setFuenteFilas(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        J_tableLlenados.setFuenteFilasSelect(new java.awt.Font("Tahoma", 1, 15)); // NOI18N
        J_tableLlenados.setGrosorBordeFilas(0);
        J_tableLlenados.setGrosorBordeHead(0);
        J_tableLlenados.setRowHeight(25);
        J_tableLlenados.setShowHorizontalLines(false);
        J_tableLlenados.setShowVerticalLines(false);
        jScrollPane2.setViewportView(J_tableLlenados);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(420, 100, 200, 260);

        Reloj.setFont(new java.awt.Font("Times New Roman", 1, 27)); // NOI18N
        Reloj.setForeground(new java.awt.Color(255, 255, 255));
        Reloj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reloj.setText("00:00:00");
        jPanel2.add(Reloj);
        Reloj.setBounds(380, 10, 260, 60);

        combopieza.setBackground(new java.awt.Color(0, 51, 102));
        combopieza.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        combopieza.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Pechuga", "Muslo", "Pierna", "Ala", "Huacal", "Cadera", "Cabeza", "Molleja", "Patas" }));
        jPanel2.add(combopieza);
        combopieza.setBounds(30, 190, 180, 34);

        combosucursal.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        combosucursal.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Blanca", "Zapata", "Mercado" }));
        jPanel2.add(combosucursal);
        combosucursal.setBounds(30, 110, 180, 26);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("¿De parte de quién viene ésta producto?");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(30, 60, 360, 29);

        EtiquetaSucursal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        EtiquetaSucursal.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(EtiquetaSucursal);
        EtiquetaSucursal.setBounds(420, 60, 190, 40);

        calculadora.setBackground(new java.awt.Color(0, 51, 102));
        calculadora.setForeground(new java.awt.Color(102, 102, 255));
        calculadora.setLayout(null);

        borrar.setBackground(new java.awt.Color(0, 51, 102));
        borrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        borrar.setForeground(new java.awt.Color(255, 255, 255));
        borrar.setText("C");
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });
        calculadora.add(borrar);
        borrar.setBounds(120, 60, 50, 50);

        nueve.setBackground(new java.awt.Color(0, 51, 102));
        nueve.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        nueve.setForeground(new java.awt.Color(255, 255, 255));
        nueve.setText("9");
        nueve.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                nueveActionPerformed(evt);
            }
        });
        calculadora.add(nueve);
        nueve.setBounds(80, 64, 38, 50);

        ocho.setBackground(new java.awt.Color(0, 51, 102));
        ocho.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        ocho.setForeground(new java.awt.Color(255, 255, 255));
        ocho.setText("8");
        ocho.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ochoActionPerformed(evt);
            }
        });
        calculadora.add(ocho);
        ocho.setBounds(40, 64, 40, 50);

        siete.setBackground(new java.awt.Color(0, 51, 102));
        siete.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        siete.setForeground(new java.awt.Color(255, 255, 255));
        siete.setText("7");
        siete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sieteActionPerformed(evt);
            }
        });
        calculadora.add(siete);
        siete.setBounds(0, 64, 40, 50);

        cuatro.setBackground(new java.awt.Color(0, 51, 102));
        cuatro.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cuatro.setForeground(new java.awt.Color(255, 255, 255));
        cuatro.setText("4");
        cuatro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cuatroActionPerformed(evt);
            }
        });
        calculadora.add(cuatro);
        cuatro.setBounds(0, 114, 40, 60);

        cinco.setBackground(new java.awt.Color(0, 51, 102));
        cinco.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        cinco.setForeground(new java.awt.Color(255, 255, 255));
        cinco.setText("5");
        cinco.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cincoActionPerformed(evt);
            }
        });
        calculadora.add(cinco);
        cinco.setBounds(40, 114, 40, 60);

        seis.setBackground(new java.awt.Color(0, 51, 102));
        seis.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        seis.setForeground(new java.awt.Color(255, 255, 255));
        seis.setText("6");
        seis.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                seisActionPerformed(evt);
            }
        });
        calculadora.add(seis);
        seis.setBounds(80, 114, 40, 60);

        tres.setBackground(new java.awt.Color(0, 51, 102));
        tres.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        tres.setForeground(new java.awt.Color(255, 255, 255));
        tres.setText("3");
        tres.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tresActionPerformed(evt);
            }
        });
        calculadora.add(tres);
        tres.setBounds(80, 174, 40, 50);

        dos.setBackground(new java.awt.Color(0, 51, 102));
        dos.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        dos.setForeground(new java.awt.Color(255, 255, 255));
        dos.setText("2");
        dos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dosActionPerformed(evt);
            }
        });
        calculadora.add(dos);
        dos.setBounds(40, 174, 40, 50);

        uno.setBackground(new java.awt.Color(0, 51, 102));
        uno.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        uno.setForeground(new java.awt.Color(255, 255, 255));
        uno.setText("1");
        uno.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                unoActionPerformed(evt);
            }
        });
        calculadora.add(uno);
        uno.setBounds(0, 174, 40, 50);

        listo.setBackground(new java.awt.Color(0, 51, 105));
        listo.setFont(new java.awt.Font("Tahoma", 1, 12)); // NOI18N
        listo.setForeground(new java.awt.Color(255, 255, 255));
        listo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/casilla-de-verificacion (1).png"))); // NOI18N
        listo.setText("Agregar");
        listo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listoActionPerformed(evt);
            }
        });
        calculadora.add(listo);
        listo.setBounds(0, 224, 120, 40);

        cantidad.setBackground(new java.awt.Color(0, 0, 0));
        cantidad.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        cantidad.setForeground(new java.awt.Color(255, 255, 255));
        cantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cantidad.setBorder(null);
        cantidad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        calculadora.add(cantidad);
        cantidad.setBounds(0, 10, 160, 50);

        salir.setBackground(new java.awt.Color(0, 51, 102));
        salir.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        salir.setForeground(new java.awt.Color(255, 0, 0));
        salir.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/salir-flecha-derecha (1).png"))); // NOI18N
        salir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salirActionPerformed(evt);
            }
        });
        calculadora.add(salir);
        salir.setBounds(120, 110, 50, 60);

        cero.setBackground(new java.awt.Color(0, 51, 102));
        cero.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        cero.setForeground(new java.awt.Color(255, 255, 255));
        cero.setText("0");
        cero.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ceroActionPerformed(evt);
            }
        });
        calculadora.add(cero);
        cero.setBounds(120, 170, 50, 100);

        jPanel2.add(calculadora);
        calculadora.setBounds(230, 90, 180, 280);

        pago.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        pago.setForeground(new java.awt.Color(255, 255, 255));
        pago.setText("00.00");
        jPanel2.add(pago);
        pago.setBounds(550, 370, 70, 29);

        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Pieza");
        jPanel2.add(jLabel10);
        jLabel10.setBounds(30, 160, 190, 29);

        jButton1.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/casilla-de-verificacion (1).png"))); // NOI18N
        jButton1.setText("Pagar productos");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1);
        jButton1.setBounds(310, 410, 210, 60);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 21)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Total a pagar: $");
        jPanel2.add(jLabel9);
        jLabel9.setBounds(360, 370, 190, 29);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(20, 50, 650, 480);

        user.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jPanel1.add(user);
        user.setBounds(500, 0, 150, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 682, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Corte_btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Corte_btncancelarActionPerformed
            dispose();   
    }//GEN-LAST:event_Corte_btncancelarActionPerformed
public void piezasenbase(String pieza){    
      try{ Connection ca= cc.conexion(); // CONEXION DB //SOLO SE LLAMA A LA CANTIDAD PORQUE EN EL TICKET YA SE DEFINIRÁN LOS NOMBRES DE CADA ARTICULO
                      sent  = (Statement)ca.createStatement();
        combosucursal.getSelectedItem().toString();
         if(combosucursal.getSelectedItem().toString().equals("Blanca")){          
             rs = sent.executeQuery("select pieza from productoexternoblanca where nombre = '"+pieza+"' ");
        }else if(combosucursal.getSelectedItem().toString().equals("Zapata")){                       
            rs = sent.executeQuery("select pieza from productoexternozapata where nombre = '"+pieza+"' ");
        }else{                  
           rs = sent.executeQuery("select pieza from productoexternocentral where nombre = '"+pieza+"' ");
    }                                                                                            
                         while(rs.next()){
                             piezaendb=rs.getFloat(1);               
                         }
      }catch(Exception e){ 
          JOptionPane.showMessageDialog(null, "piezasenbase");
      }finally{cc.getClose();}
}
public void piezasenproductos(String pieza){
      try{ Connection ca= cc.conexion(); // CONEXION DB //SOLO SE LLAMA A LA CANTIDAD PORQUE EN EL TICKET YA SE DEFINIRÁN LOS NOMBRES DE CADA ARTICULO
                      sent  = (Statement)ca.createStatement();
                      rs = sent.executeQuery("select cantidad from productos where nombre_producto = '"+pieza+"' ");
                         while(rs.next()){
                             piezaendb=rs.getFloat(1);               
                         }
      }catch(Exception e){
          JOptionPane.showMessageDialog(null, "piezasenproductos");
      }finally{cc.getClose();}
}
public void totalapagarmetodo(){
      try{ Connection ca= cc.conexion(); // CONEXION DB //SOLO SE LLAMA A LA CANTIDAD PORQUE EN EL TICKET YA SE DEFINIRÁN LOS NOMBRES DE CADA ARTICULO
                      sent  = (Statement)ca.createStatement();
    combosucursal.getSelectedItem().toString();
         if(combosucursal.getSelectedItem().toString().equals("Blanca")){            
               rs = sent.executeQuery("select SUM(total) from productoexternoblanca WHERE nombre IN ('Pechuga', 'Muslo', 'Pierna', 'Ala', 'Huacal', 'Cadera')");                       
          }else if(combosucursal.getSelectedItem().toString().equals("Zapata")){            
               rs = sent.executeQuery("select SUM(total) from productoexternozapata WHERE nombre IN ('Pechuga', 'Muslo', 'Pierna', 'Ala', 'Huacal', 'Cadera')");
         }else{          
               rs = sent.executeQuery("select SUM(total) from productoexternocentral WHERE nombre IN ('Pechuga', 'Muslo', 'Pierna', 'Ala', 'Huacal', 'Cadera')");                        
    }                           
                         while(rs.next()){
                             totalapagar=rs.getFloat(1);             
                         }
      }catch(Exception e){              
          JOptionPane.showMessageDialog(null, "totalapagarmetodo");
      }finally{cc.getClose();}
}
public void nombresypiezas(){
        try{ Connection ca= cc.conexion(); // CONEXION DB //SOLO SE LLAMA A LA CANTIDAD PORQUE EN EL TICKET YA SE DEFINIRÁN LOS NOMBRES DE CADA ARTICULO
                      sent  = (Statement)ca.createStatement();                      
   if(combosucursal.getSelectedItem().toString().equals("Blanca")){                       
             rs = sent.executeQuery("select nombre, pieza from productoexternoblanca where pieza != 0");
   }else if(combosucursal.getSelectedItem().toString().equals("Zapata")){                       
             rs = sent.executeQuery("select nombre, pieza from productoexternozapata where pieza != 0");                       
   }else{                  
             rs = sent.executeQuery("select nombre, pieza from productoexternocentral where pieza != 0");                      
    }                      
                       while(rs.next()){
                             nombres.add(rs.getString(1));
                             piezas.add(rs.getFloat(2));
                         }
      }catch(Exception e){  
            JOptionPane.showMessageDialog(null, "totalapagarmetodo");
      }finally{cc.getClose();}
}    
    
    public void vaciandotablas(){
        try{ Connection ca= cc.conexion(); // CONEXION DB // el id del usuario para obtener el id del usuario y comprobar si hay o no algun registro
   String sql = "UPDATE `productoexternoblanca` SET `pieza`=0 ,`total`=0";  
            PreparedStatement pst = ca.prepareCall(sql);                          
            int a=pst.executeUpdate();
            if(a>0){   // UPDATE `productoexternoblanca` SET `pieza`=0;              
                         }             
      }catch(Exception w){
                     JOptionPane.showMessageDialog(null,"error en id usuario"+w);
      }//fin del id del usuario para comprobar si hay o no elementos ya guardados
        finally{cc.getClose();}
    }
    
    public void productospaExtras(){ // recibe como parametro                          
        try { Connection ca= cc.conexion(); // CONEXION DB 
            ArrayList columna1 = new ArrayList(); 
            ArrayList columna2 = new ArrayList();                     
               sent  = (Statement)ca.createStatement();          
         if(combosucursal.getSelectedItem().toString().equals("Blanca")){          
             rs = sent.executeQuery("SELECT nombre, pieza FROM productoexternoblanca where pieza != 0");
        }else if(combosucursal.getSelectedItem().toString().equals("Zapata")){                       
            rs = sent.executeQuery("SELECT nombre, pieza FROM productoexternozapata where pieza != 0");
        }else{                 
           rs = sent.executeQuery("SELECT nombre, pieza FROM productoexternocentral where pieza != 0");
    }                                       
            while (rs.next()) {                
                             columna1.add(rs.getString(1));
                             columna2.add(rs.getFloat(2));                             
            }                           
            String nombresuc = combosucursal.getSelectedItem().toString();            
       ticketVentasExternas = new TicketVentaExterna();
       ticketVentasExternas.TicketVentaExterna(nombresuc , Float.parseFloat(pago.getText().toString()), columna1, columna2);              
    } catch (Exception e) {
       JOptionPane.showMessageDialog(null, "ERROR EN METODO: productosvendidoseneldia","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{cc.getClose();}
}               
    
    public void insertarengastos(){
        try{ Connection ca= cc.conexion(); // CONEXION DB // el id del usuario para obtener el id del usuario y comprobar si hay o no algun registro
   String sql = "INSERT INTO  egreso(cantidad, tipo, fecha, total, usuario)  VALUES (?,?,?,?,?)";
                         PreparedStatement pst = ca.prepareCall(sql); 
                         pst.setInt(1,0);
                         pst.setString(2,combosucursal.getSelectedItem().toString());
                         pst.setString(3,fecha());
                         pst.setFloat(4,Float.parseFloat(pago.getText().toString()));
                         pst.setInt(5,id_usuario);
                         int a=pst.executeUpdate();
                         if(a>0){   // UPDATE `productoexternoblanca` SET `pieza`=0;
                  productospaExtras();     
                         }                                                 
      }catch(Exception w){
                     JOptionPane.showMessageDialog(null,"insertarengastos"+w);
      }//fin del id del usuario para comprobar si hay o no elementos ya guardados
        finally{cc.getClose();}
    }
    
    public void agregaraproductos(){
        nombresypiezas();
        for (int i = 0; i < nombres.size(); i++) {            
            piezasenproductos(nombres.get(i).toString());         
           piezaendb+=Float.parseFloat(piezas.get(i).toString());
                 try{ Connection ca= cc.conexion(); // CONEXION DB  //la insersion a la tabla ventas
                PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+piezaendb+"'WHERE nombre_producto= '"+nombres.get(i).toString()+"' ");  
                int a=ps.executeUpdate();  
                if(a>0){
                    pago.setText(""); 
                }
            }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
            }//fin de la insersion a la tabla ventas
                 finally{cc.getClose();}
        }
       nombres.clear();
    }
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
    insertarengastos();
    agregaraproductos();   // UPDATE `productoexternoblanca` SET `pieza`=0;
    vaciandotablas(); 
              LlenarTableDatosblanca(J_tableLlenados); 
              LlenarTableDatoszapata(J_tableLlenados);
              LlenarTableDatosMercado(J_tableLlenados);
                menu_principal.noduplicarexternos=false;
              this.setVisible(false);
              menu_principal pho = new menu_principal(true);
    }//GEN-LAST:event_jButton1ActionPerformed

    private void ceroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ceroActionPerformed
        String one="0";
        if(!cantidad.equals("")){

            cantidad.setText(cantidad.getText()+one);
        }
        else {
            cantidad.setText(one);
            cantidad=cantidad;
        }
    }//GEN-LAST:event_ceroActionPerformed

    private void salirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salirActionPerformed
        calculadora.setVisible(false);
    }//GEN-LAST:event_salirActionPerformed

    private void listoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listoActionPerformed
        boolean pass2 = validarFormulario(cantidad.getText());
        if(pass2){//ESTO VALIDA QUE EL TEXTO ESCRITO NO TENGA INCOHERENCIAS
            float cantidadnumerica=Float.parseFloat(cantidad.getText().toString());
            float total=0;
            switch(combopieza.getSelectedItem().toString()){
                case "Pechuga":
                piezasenbase(combopieza.getSelectedItem().toString());
                cantidadnumerica+=piezaendb;
                total=cantidadnumerica*33;
                try{ Connection ca= cc.conexion(); // CONEXION DB  //la insersion a la tabla ventas
                    if(combosucursal.getSelectedItem().toString().equals("Blanca")){
                        PreparedStatement ps = ca.prepareStatement ("UPDATE productoexternoblanca SET pieza='"+cantidadnumerica+"',total = '"+solodosdecimales.format(total)+"',fecha = '"+fecha()+"',tiendaexterna = '"+combosucursal.getSelectedItem().toString()+"'WHERE nombre= 'Pechuga' ");
                        a=ps.executeUpdate();
                        LlenarTableDatosblanca(J_tableLlenados);
                    }else if(combosucursal.getSelectedItem().toString().equals("Zapata")){
                        PreparedStatement ps = ca.prepareStatement ("UPDATE productoexternozapata SET pieza='"+cantidadnumerica+"',total = '"+solodosdecimales.format(total)+"',fecha = '"+fecha()+"',tiendaexterna = '"+combosucursal.getSelectedItem().toString()+"'WHERE nombre= 'Pechuga' ");
                        a=ps.executeUpdate();
                        LlenarTableDatoszapata(J_tableLlenados);
                    }else{
                        PreparedStatement ps = ca.prepareStatement ("UPDATE productoexternocentral SET pieza='"+cantidadnumerica+"',total = '"+solodosdecimales.format(total)+"',fecha = '"+fecha()+"',tiendaexterna = '"+combosucursal.getSelectedItem().toString()+"'WHERE nombre= 'Pechuga' ");
                        a=ps.executeUpdate();
                        LlenarTableDatosMercado(J_tableLlenados);
                    }
                    if(a>0){
                       cantidad.setText("");
                        totalapagarmetodo();
                        pago.setText(solodosdecimales.format(totalapagar));

                    }
                }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                    JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
                }//fin de la insersion a la tabla ventas
                finally{cc.getClose();}
                break;
                case "Muslo":
                piezasenbase(combopieza.getSelectedItem().toString());
                cantidadnumerica+=piezaendb;
                total=Float.parseFloat(String.valueOf(cantidadnumerica*7.50));
                try{  Connection ca= cc.conexion(); // CONEXION DB//la insersion a la tabla ventas
                    if(combosucursal.getSelectedItem().toString().equals("Blanca")){
                        PreparedStatement ps = ca.prepareStatement ("UPDATE productoexternoblanca SET pieza='"+cantidadnumerica+"',total = '"+solodosdecimales.format(total)+"',fecha = '"+fecha()+"',tiendaexterna = '"+combosucursal.getSelectedItem().toString()+"'WHERE nombre= 'Muslo' ");
                        a=ps.executeUpdate();
                        LlenarTableDatosblanca(J_tableLlenados);
                    }else if(combosucursal.getSelectedItem().toString().equals("Zapata")){
                        PreparedStatement ps = ca.prepareStatement ("UPDATE productoexternozapata SET pieza='"+cantidadnumerica+"',total = '"+solodosdecimales.format(total)+"',fecha = '"+fecha()+"',tiendaexterna = '"+combosucursal.getSelectedItem().toString()+"'WHERE nombre= 'Muslo' ");
                        a=ps.executeUpdate();
                        LlenarTableDatoszapata(J_tableLlenados);
                    }else{
                        PreparedStatement ps = ca.prepareStatement ("UPDATE productoexternocentral SET pieza='"+cantidadnumerica+"',total = '"+solodosdecimales.format(total)+"',fecha = '"+fecha()+"',tiendaexterna = '"+combosucursal.getSelectedItem().toString()+"'WHERE nombre= 'Muslo' ");
                        a=ps.executeUpdate();
                        LlenarTableDatosMercado(J_tableLlenados);
                    }
                    if(a>0){
                        cantidad.setText("");
                        totalapagarmetodo();
                        pago.setText(solodosdecimales.format(totalapagar));
                    }
                }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                    JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
                }//fin de la insersion a la tabla ventas
                finally{cc.getClose();}
                break;
                        case "Pierna":
                        piezasenbase(combopieza.getSelectedItem().toString());
                        cantidadnumerica+=piezaendb;
                        total=Float.parseFloat(String.valueOf(cantidadnumerica*7.50));
                        try{ Connection ca= cc.conexion(); // CONEXION DB //la insersion a la tabla ventas
                            if(combosucursal.getSelectedItem().toString().equals("Blanca")){
                                PreparedStatement ps = ca.prepareStatement ("UPDATE productoexternoblanca SET pieza='"+cantidadnumerica+"',total = '"+solodosdecimales.format(total)+"',fecha = '"+fecha()+"',tiendaexterna = '"+combosucursal.getSelectedItem().toString()+"'WHERE nombre= 'Pierna' ");
                                a=ps.executeUpdate();
                                LlenarTableDatosblanca(J_tableLlenados);
                            }else if(combosucursal.getSelectedItem().toString().equals("Zapata")){
                                PreparedStatement ps = ca.prepareStatement ("UPDATE productoexternozapata SET pieza='"+cantidadnumerica+"',total = '"+solodosdecimales.format(total)+"',fecha = '"+fecha()+"',tiendaexterna = '"+combosucursal.getSelectedItem().toString()+"'WHERE nombre= 'Pierna' ");
                                a=ps.executeUpdate();
                                LlenarTableDatoszapata(J_tableLlenados);
                            }else{
                                PreparedStatement ps = ca.prepareStatement ("UPDATE productoexternocentral SET pieza='"+cantidadnumerica+"',total = '"+solodosdecimales.format(total)+"',fecha = '"+fecha()+"',tiendaexterna = '"+combosucursal.getSelectedItem().toString()+"'WHERE nombre= 'Pierna' ");
                                a=ps.executeUpdate();
                                LlenarTableDatosMercado(J_tableLlenados);
                            }

                            if(a>0){
                                cantidad.setText("");
                                totalapagarmetodo();
                                pago.setText(solodosdecimales.format(totalapagar));
                            }
                        }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                            JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
                        }//fin de la insersion a la tabla ventas
                        finally{cc.getClose();}
                        break;
                case "Ala":
                piezasenbase(combopieza.getSelectedItem().toString());
                cantidadnumerica+=piezaendb;
                total=Float.parseFloat(String.valueOf(cantidadnumerica*5));
                try{ Connection ca= cc.conexion(); // CONEXION DB //la insersion a la tabla ventas
         if(combosucursal.getSelectedItem().toString().equals("Blanca")){

                        PreparedStatement ps = ca.prepareStatement ("UPDATE productoexternoblanca SET pieza='"+cantidadnumerica+"',total = '"+solodosdecimales.format(total)+"',fecha = '"+fecha()+"',tiendaexterna = '"+combosucursal.getSelectedItem().toString()+"'WHERE nombre= 'Ala' ");
                        a=ps.executeUpdate();
                        LlenarTableDatosblanca(J_tableLlenados);
                    }else if(combosucursal.getSelectedItem().toString().equals("Zapata")){

                        PreparedStatement ps = ca.prepareStatement ("UPDATE productoexternozapata SET pieza='"+cantidadnumerica+"',total = '"+solodosdecimales.format(total)+"',fecha = '"+fecha()+"',tiendaexterna = '"+combosucursal.getSelectedItem().toString()+"'WHERE nombre= 'Ala' ");
                        a=ps.executeUpdate();
                        LlenarTableDatoszapata(J_tableLlenados);
                    }else{
                        PreparedStatement ps = ca.prepareStatement ("UPDATE productoexternocentral SET pieza='"+cantidadnumerica+"',total = '"+solodosdecimales.format(total)+"',fecha = '"+fecha()+"',tiendaexterna = '"+combosucursal.getSelectedItem().toString()+"'WHERE nombre= 'Ala' ");
                        a=ps.executeUpdate();
                        LlenarTableDatosMercado(J_tableLlenados);
                    }
                    if(a>0){
                        cantidad.setText("");
                        totalapagarmetodo();
                        pago.setText(solodosdecimales.format(totalapagar));
                    }
                }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                    JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
                }//fin de la insersion a la tabla ventas
                finally{cc.getClose();}
                break;
                   case "Huacal":
                piezasenbase(combopieza.getSelectedItem().toString());
                cantidadnumerica+=piezaendb;
                total=Float.parseFloat(String.valueOf(cantidadnumerica*5));
                try{  Connection ca= cc.conexion(); // CONEXION DB//la insersion a la tabla ventas
         if(combosucursal.getSelectedItem().toString().equals("Blanca")){
                        PreparedStatement ps = ca.prepareStatement ("UPDATE productoexternoblanca SET pieza='"+cantidadnumerica+"',total = '"+solodosdecimales.format(total)+"',fecha = '"+fecha()+"',tiendaexterna = '"+combosucursal.getSelectedItem().toString()+"'WHERE nombre= 'Huacal' ");
                        a=ps.executeUpdate();
                        LlenarTableDatosblanca(J_tableLlenados);
                    }else if(combosucursal.getSelectedItem().toString().equals("Zapata")){

                        PreparedStatement ps = ca.prepareStatement ("UPDATE productoexternozapata SET pieza='"+cantidadnumerica+"',total = '"+solodosdecimales.format(total)+"',fecha = '"+fecha()+"',tiendaexterna = '"+combosucursal.getSelectedItem().toString()+"'WHERE nombre= 'Huacal' ");
                        a=ps.executeUpdate();
                        LlenarTableDatoszapata(J_tableLlenados);
                    }else{
                        PreparedStatement ps = ca.prepareStatement ("UPDATE productoexternocentral SET pieza='"+cantidadnumerica+"',total = '"+solodosdecimales.format(total)+"',fecha = '"+fecha()+"',tiendaexterna = '"+combosucursal.getSelectedItem().toString()+"'WHERE nombre= 'Huacal' ");
                        a=ps.executeUpdate();
                        LlenarTableDatosMercado(J_tableLlenados);
                    }
                    if(a>0){
                        cantidad.setText("");
                        totalapagarmetodo();
                        pago.setText(solodosdecimales.format(totalapagar));
                    }
                }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                    JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
                }//fin de la insersion a la tabla ventas
                finally{cc.getClose();}
                break;
                   case "Cadera":
                piezasenbase(combopieza.getSelectedItem().toString());
                cantidadnumerica+=piezaendb;
                total=Float.parseFloat(String.valueOf(cantidadnumerica*2));
                try{  Connection ca= cc.conexion(); // CONEXION DB//la insersion a la tabla ventas
         if(combosucursal.getSelectedItem().toString().equals("Blanca")){
                        PreparedStatement ps = ca.prepareStatement ("UPDATE productoexternoblanca SET pieza='"+cantidadnumerica+"',total = '"+solodosdecimales.format(total)+"',fecha = '"+fecha()+"',tiendaexterna = '"+combosucursal.getSelectedItem().toString()+"'WHERE nombre= 'Cadera' ");
                        a=ps.executeUpdate();
                        LlenarTableDatosblanca(J_tableLlenados);
                    }else if(combosucursal.getSelectedItem().toString().equals("Zapata")){
                        PreparedStatement ps = ca.prepareStatement ("UPDATE productoexternozapata SET pieza='"+cantidadnumerica+"',total = '"+solodosdecimales.format(total)+"',fecha = '"+fecha()+"',tiendaexterna = '"+combosucursal.getSelectedItem().toString()+"'WHERE nombre= 'Cadera' ");
                        a=ps.executeUpdate();
                        LlenarTableDatoszapata(J_tableLlenados);
                    }else{
                        PreparedStatement ps = ca.prepareStatement ("UPDATE productoexternocentral SET pieza='"+cantidadnumerica+"',total = '"+solodosdecimales.format(total)+"',fecha = '"+fecha()+"',tiendaexterna = '"+combosucursal.getSelectedItem().toString()+"'WHERE nombre= 'Cadera' ");
                        a=ps.executeUpdate();
                        LlenarTableDatosMercado(J_tableLlenados);
                    }
                    if(a>0){
                       cantidad.setText("");
                        totalapagarmetodo();
                        pago.setText(solodosdecimales.format(totalapagar));
                    }
                }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                    JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
                }//fin de la insersion a la tabla ventas
                finally{cc.getClose();}
                break;
                   default:
                piezasenbase(combopieza.getSelectedItem().toString());
                cantidadnumerica+=piezaendb;
                try{ Connection ca= cc.conexion(); // CONEXION DB //la insersion a la tabla ventas

                    if(combosucursal.getSelectedItem().toString().equals("Blanca")){

                        PreparedStatement ps = ca.prepareStatement ("UPDATE productoexternoblanca SET pieza='"+cantidadnumerica+"',fecha = '"+fecha()+"',tiendaexterna = '"+combosucursal.getSelectedItem().toString()+"'WHERE nombre= '"+combopieza.getSelectedItem().toString()+"' ");
                        a=ps.executeUpdate();
                        LlenarTableDatosblanca(J_tableLlenados);
                    }else if(combosucursal.getSelectedItem().toString().equals("Zapata")){

                        PreparedStatement ps = ca.prepareStatement ("UPDATE productoexternozapata SET pieza='"+cantidadnumerica+"',fecha = '"+fecha()+"',tiendaexterna = '"+combosucursal.getSelectedItem().toString()+"'WHERE nombre= '"+combopieza.getSelectedItem().toString()+"' ");
                        a=ps.executeUpdate();
                        LlenarTableDatoszapata(J_tableLlenados);
                    }else{

                        PreparedStatement ps = ca.prepareStatement ("UPDATE productoexternocentral SET pieza='"+cantidadnumerica+"',fecha = '"+fecha()+"',tiendaexterna = '"+combosucursal.getSelectedItem().toString()+"'WHERE nombre= '"+combopieza.getSelectedItem().toString()+"' ");
                        a=ps.executeUpdate();
                        LlenarTableDatosMercado(J_tableLlenados);
                    }
                    if(a>0){
                        cantidad.setText("");
                        totalapagarmetodo();
                        pago.setText(solodosdecimales.format(totalapagar));
                    }
                }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                    JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
                }//fin de la insersion a la tabla ventas
                finally{cc.getClose();}
                break;
            }
        }//ESTO VALIDA QUE EL TEXTO ESCRITO NO TENGA INCOHERENCIAS
    }//GEN-LAST:event_listoActionPerformed

    private void unoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_unoActionPerformed
        String one="1";
        if(!cantidad.equals("")){

            cantidad.setText(cantidad.getText()+one);
        }
        else {
            cantidad.setText(one);
            cantidad=cantidad;
        }
    }//GEN-LAST:event_unoActionPerformed

    private void dosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dosActionPerformed
        String two="2";
        if(!cantidad.equals("")){

            cantidad.setText(cantidad.getText()+two);
        }
        else {
            cantidad.setText(two);
            cantidad=cantidad;
        }
    }//GEN-LAST:event_dosActionPerformed

    private void tresActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tresActionPerformed
        String th="3";
        if(!cantidad.equals("")){

            cantidad.setText(cantidad.getText()+th);
        }
        else {
            cantidad.setText(th);
            cantidad=cantidad;
        }
    }//GEN-LAST:event_tresActionPerformed

    private void seisActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_seisActionPerformed
        String six="6";
        if(!cantidad.equals("")){

            cantidad.setText(cantidad.getText()+six);
        }
        else {
            cantidad.setText(six);
            cantidad=cantidad;
        }
    }//GEN-LAST:event_seisActionPerformed

    private void cincoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cincoActionPerformed
        String five="5";
        if(!cantidad.equals("")){

            cantidad.setText(cantidad.getText()+five);
        }
        else {
            cantidad.setText(five);
            cantidad=cantidad;
        }
    }//GEN-LAST:event_cincoActionPerformed

    private void cuatroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cuatroActionPerformed
        String fo="4";
        if(!cantidad.equals("")){

            cantidad.setText(cantidad.getText()+fo);
        }
        else {
            cantidad.setText(fo);
            cantidad=cantidad;
        }
    }//GEN-LAST:event_cuatroActionPerformed

    private void sieteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sieteActionPerformed
        String sevenr="7";
        if(!cantidad.equals("")){

            cantidad.setText(cantidad.getText()+sevenr);
        }
        else {
            cantidad.setText(sevenr);
            cantidad=cantidad;
        }
    }//GEN-LAST:event_sieteActionPerformed

    private void ochoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ochoActionPerformed
        String eight="8";
        if(!cantidad.equals("")){

            cantidad.setText(cantidad.getText()+eight);
        }
        else {
            cantidad.setText(eight);
            cantidad=cantidad;
        }
    }//GEN-LAST:event_ochoActionPerformed

    private void nueveActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_nueveActionPerformed
        String nine="9";
        if(!cantidad.equals("")){

            cantidad.setText(cantidad.getText()+nine);
        }
        else {
            cantidad.setText(nine);
            cantidad=cantidad;
        }
    }//GEN-LAST:event_nueveActionPerformed

    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
        cantidad.setText("");
    }//GEN-LAST:event_borrarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
      menu_principal.noduplicarexternos=false;
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
            java.util.logging.Logger.getLogger(ProductosExternos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ProductosExternos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ProductosExternos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ProductosExternos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ProductosExternos().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Corte_btncancelar;
    private javax.swing.JLabel EtiquetaSucursal;
    private javax.swing.JLabel Fecha;
    private rojerusan.RSTableMetro J_tableLlenados;
    private javax.swing.JLabel Reloj;
    private javax.swing.JButton borrar;
    private javax.swing.JPanel calculadora;
    public static javax.swing.JTextField cantidad;
    private javax.swing.JButton cero;
    private javax.swing.JButton cinco;
    private javax.swing.JComboBox<String> combopieza;
    private javax.swing.JComboBox<String> combosucursal;
    private javax.swing.JButton cuatro;
    private javax.swing.JButton dos;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JButton listo;
    private javax.swing.JButton nueve;
    private javax.swing.JButton ocho;
    private javax.swing.JLabel pago;
    private javax.swing.JButton salir;
    private javax.swing.JButton seis;
    private javax.swing.JButton siete;
    private javax.swing.JButton tres;
    private javax.swing.JButton uno;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables
}
