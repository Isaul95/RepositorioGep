package si;
import java.awt.Color;
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
import static si.Apertura.fecha;
import static si.Apertura.monto;
import ticket.ticketcortedecaja;
import ticket.ticketpollocrudo;
import ticket.ticketpollocrudosolopiezas;
import ticket.ticketprocesadospiezas;
import ticket.tikectprocesados;
import ticket.PolloCrudoBienTocketc;
import ticket.PolloCrudoxPiezas;


public class Pantalla_CorteCaja extends javax.swing.JFrame  implements Runnable{
    Thread hilo; 
    float ventasmenosgastos,variablemontoentregado;
    String hora,minutos,segundos;
    Statement sent;  
  ResultSet rs;    
  
  PolloCrudoxPiezas PolloCrudoxPiezas;
  
  ticketcortedecaja tikectcorte; 
  ticketpollocrudo tikectpollocrudo;  
  PolloCrudoBienTocketc  PolloCrudoBienTocketc;  
  ticketpollocrudosolopiezas  ticketpollocrudosolopiezas;
  tikectprocesados tikectprocesados;
  ticketprocesadospiezas ticketprocesadospiezas;
  DecimalFormat solodosdecimales = new DecimalFormat("#.##");
            
  final float pagopollo=20*90, tacos=60, almuerzo=28;//datos para la tabla utilidad
float totaldepagos,diferenciaentablautilidad, utilidades, total_de_crudo, total_de_procesados, ventasdeldia, gastosdeldia, montodeapertura, diferencia, diferenciafinal, precio;
int apertura;
String  usuarioname=SI_Inicio.text_user.getText();
int  id_usuario=Integer.parseInt(SI_Inicio.iduser.getText());
    Calendar fecha_actual = new GregorianCalendar();
  float ticketmonto, ticketventa, ticketgasto, ticketdiferencia;
    public Pantalla_CorteCaja() {
        initComponents();
         hilo=new Thread(this);
     hilo.start();
        this.setLocationRelativeTo(null); // CENTRAR FORMULARIO
        Fecha.setText(fecha());
        ventaseneldia();
        metodogastosdeldia();
        aperturadeldia();
        pagoshechoseneldiaactual();
        aperturacantidad.setText(String.valueOf(montodeapertura));
        Ventasfortoday1.setText(String.valueOf(ventasdeldia));
        Gastosfromtoday.setText(String.valueOf(gastosdeldia));
        pagosmadetoday.setText(String.valueOf(totaldepagos));
        
        user.setText(usuarioname);
        
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
    
public void ventaseneldia(){
        try{ // La suma de todos los importes
    
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select SUM(total) from venta where fecha_reporte= '"+fecha()+"'");
                                            while(rs.next()){
                                                      ventasdeldia =Float.parseFloat(rs.getString("SUM(total)"));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                      }// fin del precio-catch del producto
    }

public void metodogastosdeldia(){
        try{ // La suma de todos los importes
    
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select SUM(total) from egreso where fecha= '"+fecha()+"'");
                                            while(rs.next()){
                                                      gastosdeldia =Float.parseFloat(rs.getString("SUM(total)"));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                      }// fin del precio-catch del producto
    }

    public void aperturadeldia(){
        try{ // La suma de todos los importes
    
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select * from apertura where fecha= '"+fecha()+"'");
                                            while(rs.next()){
                                                      apertura =rs.getInt("id_apertura");
                                                      montodeapertura= rs.getFloat("monto");
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                      }// fin del precio-catch del producto
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Corte_btncancelar = new javax.swing.JButton();
        Corte_btnImprimirticket = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        monto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Gastosfromtoday = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Ventasfortoday1 = new javax.swing.JLabel();
        Fecha = new javax.swing.JLabel();
        Reloj = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        aperturacantidad = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        pagosmadetoday = new javax.swing.JLabel();
        user = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CORTE DE CAJA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 36))); // NOI18N
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setLayout(null);

        Corte_btncancelar.setBackground(new java.awt.Color(0, 51, 102));
        Corte_btncancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Corte_btncancelar.setForeground(new java.awt.Color(255, 255, 255));
        Corte_btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/salir-flecha-derecha (1).png"))); // NOI18N
        Corte_btncancelar.setText("Cancelar");
        Corte_btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Corte_btncancelarActionPerformed(evt);
            }
        });
        jPanel2.add(Corte_btncancelar);
        Corte_btncancelar.setBounds(340, 380, 250, 60);

        Corte_btnImprimirticket.setBackground(new java.awt.Color(0, 51, 102));
        Corte_btnImprimirticket.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Corte_btnImprimirticket.setForeground(new java.awt.Color(255, 255, 255));
        Corte_btnImprimirticket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/casilla-de-verificacion (1).png"))); // NOI18N
        Corte_btnImprimirticket.setText("Realizar Corte");
        Corte_btnImprimirticket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Corte_btnImprimirticketActionPerformed(evt);
            }
        });
        jPanel2.add(Corte_btnImprimirticket);
        Corte_btnImprimirticket.setBounds(30, 380, 250, 60);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("La apertura de caja fue:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(340, 80, 240, 29);

        monto.setBackground(new java.awt.Color(0, 148, 204));
        monto.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        monto.setForeground(new java.awt.Color(255, 255, 255));
        monto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        monto.setText("00.00");
        monto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        monto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                montoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                montoFocusLost(evt);
            }
        });
        monto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                montoActionPerformed(evt);
            }
        });
        jPanel2.add(monto);
        monto.setBounds(360, 290, 230, 60);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Pagos");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(10, 260, 170, 29);

        Gastosfromtoday.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        Gastosfromtoday.setForeground(new java.awt.Color(255, 255, 255));
        Gastosfromtoday.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Gastosfromtoday.setText("00.00");
        jPanel2.add(Gastosfromtoday);
        Gastosfromtoday.setBounds(10, 210, 180, 29);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Ventas");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(10, 80, 180, 29);

        Ventasfortoday1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        Ventasfortoday1.setForeground(new java.awt.Color(255, 255, 255));
        Ventasfortoday1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Ventasfortoday1.setText("00.00");
        jPanel2.add(Ventasfortoday1);
        Ventasfortoday1.setBounds(10, 120, 180, 29);

        Fecha.setFont(new java.awt.Font("Arial Black", 1, 27)); // NOI18N
        Fecha.setForeground(new java.awt.Color(255, 255, 255));
        Fecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Fecha.setText("DD/MM/YYYY");
        jPanel2.add(Fecha);
        Fecha.setBounds(20, 10, 250, 60);

        Reloj.setFont(new java.awt.Font("Arial Black", 1, 27)); // NOI18N
        Reloj.setForeground(new java.awt.Color(255, 255, 255));
        Reloj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reloj.setText("00:00:00");
        jPanel2.add(Reloj);
        Reloj.setBounds(350, 10, 250, 60);

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("   Monto entregado:");
        jPanel2.add(jLabel5);
        jLabel5.setBounds(340, 250, 240, 29);

        aperturacantidad.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        aperturacantidad.setForeground(new java.awt.Color(255, 255, 255));
        aperturacantidad.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        aperturacantidad.setText("00.00");
        jPanel2.add(aperturacantidad);
        aperturacantidad.setBounds(420, 110, 180, 40);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Gastos");
        jPanel2.add(jLabel7);
        jLabel7.setBounds(10, 170, 170, 29);

        pagosmadetoday.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        pagosmadetoday.setForeground(new java.awt.Color(255, 255, 255));
        pagosmadetoday.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pagosmadetoday.setText("00.00");
        jPanel2.add(pagosmadetoday);
        pagosmadetoday.setBounds(10, 300, 180, 29);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 50, 620, 450);

        user.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jPanel1.add(user);
        user.setBounds(500, 0, 180, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 644, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 1, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 511, javax.swing.GroupLayout.PREFERRED_SIZE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Corte_btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Corte_btncancelarActionPerformed
        // BOTON DE CANCELAR LA INSERCION DE NUEVO USUARIO
       // int dialogButton = JOptionPane.YES_NO_OPTION;
        //int result = JOptionPane.showConfirmDialog(null, "¿Regresar a pagina anterior?","   Aviso",dialogButton);
        //if(result == 0){
            dispose();   //}
    }//GEN-LAST:event_Corte_btncancelarActionPerformed
     
    public void vaciartodoeninventario(){
              try{              
           PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad= 0");
                  int a = ps.executeUpdate();
                if(a>0){    
                }
                           }catch(Exception e){
                               System.err.print(e);
                           }
            }
    public void vaciartodoelpollococidoenprocesados(){
              try{              
           PreparedStatement ps = ca.prepareStatement ("UPDATE procesados SET total= 0, piezas=0");
                  int a = ps.executeUpdate();
                if(a>0){    
                }
                           }catch(Exception e){
                               System.err.print(e);
                           }
            }
        public void vaciartodoelpollocrudoendevolucioncrudo(){
              try{              
           PreparedStatement ps = ca.prepareStatement ("UPDATE devolucion_crudo SET total= 0, piezas=0");
                  int a = ps.executeUpdate();
                if(a>0){    
                }
                           }catch(Exception e){
                               System.err.print(e);
                           }
            }
    
 public void sobrantedepollocrudodeldiaparaticketcantidadesypiezas(){//TICKET DEVOLUCION CRUDO,  LAS CANTIDADES Y PIEZAS
      try{//SOLO SE LLAMA A LA CANTIDAD PORQUE EN EL TICKET YA SE DEFINIRÁN LOS NOMBRES DE CADA ARTICULO
           ArrayList columna = new ArrayList(); 
            ArrayList columna2 = new ArrayList();          
             ArrayList columna3 = new ArrayList();
          sent  = (Statement)ca.createStatement();
                      rs = sent.executeQuery("select nombre, piezas, total from devolucion_crudo where nombre in ( 'Pechuga', 'Muslo', 'Pierna', 'Ala')");
                         while(rs.next()){
                             columna.add(rs.getString(1));
                             columna2.add(rs.getFloat(2));
                             columna3.add(rs.getFloat(3));
                         }
                         total_del_día_devolucion_crudo();
   PolloCrudoxPiezas = new PolloCrudoxPiezas();          
   PolloCrudoxPiezas.PolloCrudoxPiezas(columna, columna2, columna3,total_de_crudo);                       
      }catch(Exception e){                                             
          System.out.println("ERROR en pantalla gasts: " + e.getMessage());
      }
 }//TICKET DEVOLUCION CRUDO,  LAS CANTIDADES Y PIEZAS
 public void sobrantedepollocrudodeldiaparaticketperosolocantidades(){ //TICKET DEVOLUCION CRUDO, SOLO LAS CANTIDADES
      try{//SOLO SE LLAMA A LA CANTIDAD PORQUE EN EL TICKET YA SE DEFINIRÁN LOS NOMBRES DE CADA ARTICULO
          ArrayList columna = new ArrayList(); 
           ArrayList columna2 = new ArrayList();   
          sent  = (Statement)ca.createStatement();
                      rs = sent.executeQuery("select nombre, piezas from devolucion_crudo where nombre in ('Patas', 'Huacal', 'Cadera', 'Cabeza', 'Molleja')");
                         while(rs.next()){
                             columna.add(rs.getString(1));
                             columna2.add(rs.getFloat(2));
                         }                      
                         total_del_día_devolucion_crudo();
   PolloCrudoBienTocketc = new PolloCrudoBienTocketc();          
   PolloCrudoBienTocketc.PolloCrudoBienTocketc(columna, columna2);
      }catch(Exception e){                                             
          System.out.println("ERROR" + e.getMessage());
      }
 }//TICKET DEVOLUCION CRUDO, SOLO LAS CANTIDADES
 
  public void sobrantedepollococidodeldiaparaticketcantidadesypiezas(){//TICKET DEVOLUCION COCIDO,  LAS CANTIDADES Y PIEZAS
      try{//SOLO SE LLAMA A LA CANTIDAD PORQUE EN EL TICKET YA SE DEFINIRÁN LOS NOMBRES DE CADA ARTICULO
             ArrayList columna = new ArrayList();
             ArrayList columna1 = new ArrayList();    
             ArrayList columna2 = new ArrayList();
          sent  = (Statement)ca.createStatement();
                      rs = sent.executeQuery("select nombre, piezas, total from procesados");
                         while(rs.next()){
                             columna.add(rs.getString(1));
                             columna1.add(rs.getFloat(2));
                             columna2.add(rs.getFloat(3));
                         }
                        
     ticketprocesadospiezas = new ticketprocesadospiezas();       
      total_del_día_procesados();
      //JOptionPane.showMessageDialog(null, "TOTAL PROCESADOS de ALEXIA:"+total_de_procesados);
     ticketprocesadospiezas.ticketprocesadospiezas(columna,columna1,columna2, total_de_procesados);                
      }catch(Exception e){                                                     
          // System.out.println("DESDE PROCESADOS"+ total_de_procesados);
      }
 }//TICKET DEVOLUCION COCIDO,  LAS CANTIDADES Y PIEZAS
  
 public void sobrantedepollococidodeldiaparaticketperosolocantidades(){ //TICKET DEVOLUCION COCIDO, SOLO LAS CANTIDADES
      try{//SOLO SE LLAMA A LA CANTIDAD PORQUE EN EL TICKET YA SE DEFINIRÁN LOS NOMBRES DE CADA ARTICULO
          ArrayList columna = new ArrayList();
          ArrayList columna2 = new ArrayList();
          sent  = (Statement)ca.createStatement();
                      rs = sent.executeQuery("select nombre, piezas from procesados");
                         while(rs.next()){ 
                             columna.add(rs.getString(1));
                             columna2.add(rs.getFloat(2));
                         }
  tikectprocesados = new tikectprocesados();          
   tikectprocesados.tikectprocesados(columna,columna2); 
      }catch(Exception e){                                             
        
      }
 }//TICKET DEVOLUCION COCIDO, SOLO LAS CANTIDADES
 
 
  public void obteniendolosvaloresdelcortedecajadeldiadehoyparaelticket(){
      try{//SOLO SE LLAMA A LA CANTIDAD PORQUE EN EL TICKET YA SE DEFINIRÁN LOS NOMBRES DE CADA ARTICULO
                      sent  = (Statement)ca.createStatement();
                      rs = sent.executeQuery("select monto_entregado, gastos, ventas, diferencia from cortes where fecha=  '"+fecha()+"' ");
                         while(rs.next()){
                             ticketmonto=rs.getFloat(1);
                             ticketgasto=rs.getFloat(2);
                             ticketventa=rs.getFloat(3);
                             ticketdiferencia=rs.getFloat(4);
                         }
                 
                         pagoshechoseneldiaactual();
   tikectcorte = new ticketcortedecaja();     
   tikectcorte.ticketcortedecaja(ticketmonto, ticketgasto, ticketventa, Float.parseFloat(solodosdecimales.format(ticketdiferencia)), ventasmenosgastos, totaldepagos);  
      }catch(Exception e){                                             
      }
 }
  public boolean validarFormulario(String cantidaddecorte) { // VALIDACION DE TXT MONTO
        boolean next = false;
        Pattern patGastos = Pattern.compile("^[0-9]+([.])?([0-9]+)?$");
        Matcher matGastos = patGastos.matcher(cantidaddecorte);

        if (matGastos.matches()&&!cantidaddecorte.equals("")&&!cantidaddecorte.equals("0")) {
            next = true;

        } else {
            JOptionPane.showMessageDialog(null, "Solo escribe numeros, así como no puede quedar vacio", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            monto.setText("");
        }
        return next;
    }
    
    private void Corte_btnImprimirticketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Corte_btnImprimirticketActionPerformed
         boolean pass2 = validarFormulario(monto.getText());
                 if(pass2){//ESTO VALIDA QUE EL TEXTO ESCRITO NO TENGA INCOHERENCIAS   
                 int decision=JOptionPane.showConfirmDialog(null,"¿Desea continuar?","UNA VEZ REALIZADO EL CORTE, SOLO EL ADMIN PUEDE ENTRAR",JOptionPane.CANCEL_OPTION);
            if(decision==0){// SI SE ELIGE QUE SI, PROCEDEMOS A REALIZAR EL CORTE E IMPRIMIR LOS 5 TICKET
                //QUE SON, 1.- CORTE DE CAJA, 2.- CRUDO, PECHUGA PIERNA, ALA Y MUSLO, 3.- CRUDO DEL RESTO PERO EN CANTIDADES
                //4.-COCIDO, TOTALES Y PIEZAS, 5.- COCIDO PIEZAS
            
                float  menosapertura;
                variablemontoentregado=Float.parseFloat(monto.getText());
        if(variablemontoentregado>0){//COMPROBANDO QUE EL MONTO NO ESTE VACIO
               
          try{ //la insersion a la tabla ventas
              ventaseneldia();
              metodogastosdeldia();
              aperturadeldia();
              pagoshechoseneldiaactual();
             ventasmenosgastos=ventasdeldia-gastosdeldia+totaldepagos; 
        //    JOptionPane.showMessageDialog(null,"ventasmenosgastosmenospagos: "+ventasmenosgastos);
                 diferencia=variablemontoentregado-ventasmenosgastos;
                  JOptionPane.showMessageDialog(null,"Se abrio con : "+montodeapertura);
                 
                   diferenciafinal=montodeapertura-diferencia;
                   if(diferencia>0){
                       JOptionPane.showMessageDialog(null,"Sobro la cantidad de $:"+diferencia);
                   }else if(diferencia<0){
                           JOptionPane.showMessageDialog(null,"Hace falta  la cantidad de $:"+diferencia);
                   }else{
                         JOptionPane.showMessageDialog(null,"Todo está en orden :) ");
           
                   }
                
    String sql = "INSERT INTO  cortes(id_apertura, monto_entregado, gastos, ventas, diferencia, fecha, hora, usuario)  VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement pst = ca.prepareCall(sql); //hasta aqui vamos
               
                pst.setInt(1,apertura);
                 pst.setFloat(2,variablemontoentregado);
                  pst.setFloat(3,gastosdeldia);
                   pst.setFloat(4,ventasdeldia);
                    pst.setFloat(5,Float.parseFloat(solodosdecimales.format(diferencia)));
                pst.setString(6,fecha());
                pst.setString(7,Reloj.getText());
                pst.setInt(8,id_usuario);
               
                int a=pst.executeUpdate();
                if(a>0){
        
                //PRIMERO SE REGISTRAN LOS PRODUCTOS SOBRANTES TANTO LOS COCIDOS COMO LOS CRUDOS 
                
                insertaradevoluciondecrudopiezasycantidades();//SE REGISTRAN LAS PIEZAS, CANTIDADES Y TOTALES DE TODO CRUDO
                insertaradevoluciondecocidopiezasycantidades();//SE REGISTRAN LAS PIEZAS, CANTIDADES Y TOTALES DE TODO COCIDO
                
                //LUEGO AQUI SE PUEDE REALIZAR CADA TICKET CORRESPONDIENTE (ESTOS SON LOS 5 TICKET)
 sobrantedepollococidodeldiaparaticketcantidadesypiezas();//SOBRANTE DE COCIDO PARA TICKET MOSTRANDO CANTIDADES Y TOTALES
 sobrantedepollococidodeldiaparaticketperosolocantidades();//SOBRANTE DE COCIDO PARA TICKET MOSTRANDO CANTIDADES
                   sobrantedepollocrudodeldiaparaticketcantidadesypiezas();//SOBRANTE DE PECHUGA, PIERNA ALA, MUSLO, VA PARA TICKET
                    sobrantedepollocrudodeldiaparaticketperosolocantidades();//SOBRANTE DE TODO MENOS PECHUGA, PIERNA ALA, MUSLO, VA PARA TICKET
                   obteniendolosvaloresdelcortedecajadeldiadehoyparaelticket();//LOS DATOS DEL TICKET CORTE DE CAJA                                                      
      llenar_tabla_utilidad(gastosdeldia, ventasdeldia);
      vaciartodoelpollococidoenprocesados();
          vaciartodoelpollocrudoendevolucioncrudo();
         vaciartodoeninventario();//UNA VEZ IMPRESO LOS 5 TICKETS SE VACIA TODO EL INVENTARIO            
                JOptionPane.showMessageDialog(null,"Nos vemos pronto","Saliendo del sistema...",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
                }
            }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
            }//fin de la insersion a la tabla ventas         
    }//FIN DE COMPROBANDO QUE EL MONTO NO ESTE VACIO
else{//CUANDO EL MONTO ESTA VACIO
            JOptionPane.showMessageDialog(null,"El monto no puede estar vacio o en 0"); 
}// FIN DE CUANDO EL MONTO ESTA VACIO
            }//SI SE ELIGE QUE SI, PROCEDEMOS A REALIZAR EL CORTE E IMPRIMIR LOS 5 TICKET
                 }   
    }//GEN-LAST:event_Corte_btnImprimirticketActionPerformed

 public void total_del_día_devolucion_crudo(){
    try{
        String sql = "select  SUM(total) from devolucion_crudo";
        PreparedStatement ps= ca.prepareStatement(sql);
        rs= ps.executeQuery();
        if(rs.next()){
            total_de_crudo=rs.getFloat("SUM(total)");
        }
        
    }catch(Exception j){
        
    }
}
   
public void total_del_día_procesados(){
    try{
        String sql = "select  SUM(total) from procesados";
        PreparedStatement ps= ca.prepareStatement(sql);
        rs= ps.executeQuery();
        if(rs.next()){
            total_de_procesados=rs.getFloat("SUM(total)");
        }
        
    }catch(Exception j){
        
    }
}
public void pagoshechoseneldiaactual(){
       try{
        String sql = "select SUM(total) from venta where estado_venta='Credito-pagado' and fecha_reporte = '"+fecha()+ "' ";
        PreparedStatement ps= ca.prepareStatement(sql);
        rs= ps.executeQuery();
        if(rs.next()){
            totaldepagos=rs.getFloat("SUM(total)");
        }
        
    }catch(Exception j){
        
    }
}
 public void llenar_tabla_utilidad(float gastosdeldia, float ventasdeldia){
     pagoshechoseneldiaactual();
     total_del_día_procesados();
     total_del_día_devolucion_crudo();
    diferenciaentablautilidad=(total_de_crudo+tacos-total_de_procesados-almuerzo);
 utilidades=(ventasdeldia+totaldepagos+total_de_crudo+tacos-total_de_procesados-pagopollo-almuerzo);
 try{ //la insersion a la tabla ventas
                String sql = "INSERT INTO  utilidad(totaldeventas,pagos,totaldevolucioncrudo,totalprocesados,pagopollo,tacos,utilidad,almuerzo, diferencia, gastos, fecha)  VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = ca.prepareCall(sql); //hasta aqui vamos
               pst.setDouble(1, Double.parseDouble(solodosdecimales.format(ventasdeldia)));
               pst.setDouble(2,Double.parseDouble(solodosdecimales.format(totaldepagos)));
                pst.setDouble(3, Double.parseDouble(solodosdecimales.format(total_de_crudo)));
                pst.setDouble(4,Double.parseDouble(solodosdecimales.format(total_de_procesados)));
                pst.setDouble(5, pagopollo);
                pst.setDouble(6, tacos);
                pst.setDouble(7, Double.parseDouble(solodosdecimales.format(utilidades)));
                pst.setDouble(8, almuerzo);
                pst.setDouble(9, Double.parseDouble(solodosdecimales.format(diferenciaentablautilidad)));
                pst.setDouble(10, Double.parseDouble(solodosdecimales.format(gastosdeldia)));  
                pst.setString(11, fecha());
                int a=pst.executeUpdate();
                if(a>0){           
                    }
            }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
            }//fin de la insersion a la tabla ventas  
 }
    public void insertaradevoluciondecrudopiezasycantidades(){//AQUI SE INSERTAN LAS PIEZAS Y CANTIDADES DE PECHUGA, MUSLO ALA Y PIERNA
         ArrayList cantidades = new  ArrayList();
         ArrayList nombres = new  ArrayList();
double []totales = {35.0, 7.70, 7.70, 5.50, 0, 0, 0, 0, 0};
        try{//SOLO SE LLAMA A LA CANTIDAD PORQUE EN EL TICKET YA SE DEFINIRÁN LOS NOMBRES DE CADA ARTICULO
                      sent  = (Statement)ca.createStatement();
                      rs = sent.executeQuery("select nombre_producto, cantidad from productos where nombre_producto in ('Pechuga', 'Muslo', 'Pierna', 'Ala', 'Huacal', 'Cadera', 'Cabeza', 'Molleja', 'Patas')");
                         while(rs.next()){
                             nombres.add(rs.getString(1));
                             cantidades.add(rs.getFloat(2));
                              
                       
                         }
      }catch(Exception e){                                             
      }
        for(int aa =0; aa<=nombres.size()-1; aa++){
            double total=0;   
            total= Double.parseDouble(String.valueOf(cantidades.get(aa)))*totales[aa];
              try{ //la insersion a la tabla ventas
                PreparedStatement ps = ca.prepareStatement ("UPDATE devolucion_crudo SET piezas='"+cantidades.get(aa)+"',total = '"+solodosdecimales.format(total)+"',fecha = '"+fecha()+"'WHERE nombre= '"+nombres.get(aa)+"' ");  
                int a=ps.executeUpdate();
                if(a>0){
                 }
                         
//cantidad.setText("");
            
            }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
            }//fin de la insersion a la tabla ventas
        }
    }//AQUI SE INSERTAN LAS PIEZAS Y CANTIDADES DE PECHUGA, MUSLO ALA Y PIERNA
        
   
   public void insertaradevoluciondecocidopiezasycantidades(){//AQUI SE INSERTAN LAS PIEZAS Y CANTIDADES DE PECHUGA, MUSLO ALA Y PIERNA
         ArrayList cantidades = new  ArrayList();
         ArrayList nombres = new  ArrayList();
double []totales = {68.0, 68.0, 7.68, 8.15, 7.50, 5.95, 23.00, 16.00, 0.00, 0.00, 11.00, 11.00, 11.00, 11.00, 17.00, 11.00, 11.00, 18.00, 11.00, 13.00, 11.50, 13.00, 11.00, 11.00, 11.00, 5.00, 2.00, 1.00, 8.50};
        try{//SOLO SE LLAMA A LA CANTIDAD PORQUE EN EL TICKET YA SE DEFINIRÁN LOS NOMBRES DE CADA ARTICULO
                      sent  = (Statement)ca.createStatement();
                      rs = sent.executeQuery("select nombre_producto, SUM(cantidad) from descripcion_de_venta where id_producto in (24, 25, 26, 27, 28, 29, 30, 31, 32, 33, 34, 35, 36, 37, 38, 39, 40, 41, 42, 43, 44, 45, 46, 47, 48, 49, 50, 51, 52) and estado='Realizada' and fecha= '"+fecha()+"' GROUP BY nombre_producto");
                   
                      while(rs.next()){
                             nombres.add(rs.getString(1));
                             cantidades.add(rs.getFloat(2));
                              
                       
                         }
      }catch(Exception e){                                             
      }
        for(int aa =0; aa<=nombres.size()-1; aa++){
            double total=0;   
            total= Double.parseDouble(String.valueOf(cantidades.get(aa)))*totales[aa];
            try{ //la insersion a la tabla ventas
                PreparedStatement ps = ca.prepareStatement ("UPDATE procesados SET piezas='"+cantidades.get(aa)+"',total = '"+ solodosdecimales.format(total)+"',fecha = '"+fecha()+"'WHERE nombre= '"+nombres.get(aa)+"' ");  
                int a=ps.executeUpdate();
                if(a>0){
                 }
                         
//cantidad.setText("");
            
            }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
            }//fin de la insersion a la tabla ventas
        }
    }//AQUI SE INSERTAN LAS PIEZAS Y CANTIDADES DE PECHUGA, MUSLO ALA Y PIERNA
      
    
    private void montoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_montoFocusGained
        // *********************   CAJA DE TEXTO DE PAGOO *********
        if(monto.getText().trim().equals("00.00")){
            monto.setText("");
            //user_usuario.setForeground(Color.red);
        }
        monto.setForeground(Color.blue);
    }//GEN-LAST:event_montoFocusGained

    private void montoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_montoFocusLost
        // *********************   CAJA DE TEXTO DE PAGOO *********
        if(monto.getText().trim().equals("")){
            monto.setText("00.00");
        }
        monto.setForeground(new Color(236, 240, 241));
    }//GEN-LAST:event_montoFocusLost

    private void montoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_montoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_montoActionPerformed

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
            java.util.logging.Logger.getLogger(Pantalla_CorteCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pantalla_CorteCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pantalla_CorteCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pantalla_CorteCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pantalla_CorteCaja().setVisible(true);
            }
        });
    }
SI cc= new SI();
 Connection ca= cc.conexion();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Corte_btnImprimirticket;
    private javax.swing.JButton Corte_btncancelar;
    private javax.swing.JLabel Fecha;
    private javax.swing.JLabel Gastosfromtoday;
    private javax.swing.JLabel Reloj;
    private javax.swing.JLabel Ventasfortoday1;
    private javax.swing.JLabel aperturacantidad;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JTextField monto;
    private javax.swing.JLabel pagosmadetoday;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables
}
