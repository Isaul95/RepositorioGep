package si;
import com.mxrck.autocompleter.TextAutoCompleter;
import java.awt.Color;
import java.awt.Font;
import java.awt.Image;
import java.awt.Toolkit;
import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;  
          import java.util.Date;             /* HORA DEL SISTEMA LIBRERIAS */
          import java.util.*;             /* HORA DEL SISTEMA LIBRERIAS */
import java.text.DateFormat;
          import java.text.SimpleDateFormat;  /* HORA DEL SISTEMA LIBRERIAS */
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import org.jdesktop.swingx.autocomplete.AutoCompleteDecorator;
import javax.imageio.ImageIO;
import javax.swing.JTable;
import javax.swing.JTextField;
import si.Pantalla_Gastos;
import static si.Pantalla_Gastos.fecha;

public final class menu_principal extends javax.swing.JFrame implements Runnable{
                  private final String logotipo = "/Reportes/logo1.jpeg"; // icono de DATAMAX
                                     
    String hora,minutos,segundos, fechadesde="",fechahasta="";
    Thread hilo;
Statement sent;  
  ResultSet rs;      
  
  float ventasdeldia, TOTALVENTAS, TOTALVENTASCANECL;
  //int id_de_la_venta_incrementable;
       int  evaluadordepiezaspares=0, evaluadordepiezasinpares=0, piezassuficientes, resultadoprimerproveedor, id_de_la_venta_incrementable,totalcomprobacion, primerventa, resultfirstselling,NoPcantidad=0,existencia;   
  //int id_usuario,id_producto,id_venta,productos,cantidadenventa,cantidadeninventario,aux1,aux2;
         int id_proveedor,id_usuario,id_producto,id_venta,productos,cantidadenventa,cantidadeninventario,aux1,aux2,variablede0=0;
  //float importe,totalf=0,comprobacion,cambio,precio;
          float minimodelaspiezasparesdepollocrudoeninventario, minimodelaspiezasinparesdepollocrudoeninventario, pollo_crudoeninventario, addpiezas, cantidadpolloenDB, porcentaje, importe,totalf=0,comprobacion,cambio,precio, NoPimporte=0,sumadeimportes,descuentocantidad, totalfinalcondescuento;
  ArrayList storage = new ArrayList(); // para guardar los id de cada producto que se ha agregado a la tabla venta
  ArrayList piezasdepollovendidas = new ArrayList();// guarda cada id de cada pieza de pollo que se vaya registrando
  ArrayList nombredelapiezadepollovendida = new ArrayList();// guarda cada id de cada pieza de pollo que se vaya registrando
  
  boolean descuentoactivo=false, suficientespiezas=true, block_unlock=true,tablaventaactiva=false, primerproducto=true, productoagregado=false, productorepetido=false;
      int suma=0,resta=0;  //variables creadas para los botones de adicionar o quitar en 1 la cantidad de articulos
       // String  usuarioname=SI_Inicio.text_user.getText(); //variable para obtener el nombre del usuario o administrador que ingreso al sistema
            String pollo_crudo="pollo crudo", estadoinactivo="Inactivo", estadoactivo="Activo", NoP="",estadocancelado= "Cancelada",estadorealizado="Realizada", estadoenturno="En turno", fechayhora="",fechasinhora="", usuarioname=SI_Inicio.text_user.getText(); //variable para obtener el nombre del usuario o administrador que ingreso al sistema
    public menu_principal() {
        initComponents();
        
              ventaseneldiaREALIZADAS(); // PARA LA SUMA DE LOS TOTALES DE LA VENTA
                    totalventasxdia(); // CUANTAS VENTAS SE REALIZARON? 5 O 60 O XX
                    ventasCanceladas();
                Ventasfortoday1.setText(String.valueOf(ventasdeldia));  
                        VentasTotal.setText(String.valueOf(TOTALVENTAS)); // // TOTAL VENTAS REALIZADAS
                         VentasTotalCance.setText(String.valueOf(TOTALVENTASCANECL));  // TOTAL VENTAS CANCEKADAS
                  LlenarTablaventasRe(Jtable_ventasRealizadas);
                  LlenarTablaventasCance(Jtable_ventasCanceladas);
                  LlenarTablaproductosmasvendidos(Jtable_productosmasven);
        
      //  this.setExtendedState(MAXIMIZED_BOTH);// MAXIMIZED_BOTH=6 se puede asi o pornerle directo 6 para k sea FULLSCREEN TODA LA PANTALLA SE ADAPTA
        
        setIconImage(getIconImage());  //La variable que le manda la imagen (DataMax) al proyecto 
        quienentroalsistema();//Dependiendo quien entre al sistema serán las opciones que se le activarán
     Fecha.setText(fecha()); // SE OBTIENE LA FECHA DEL SISTEMA PARA MOSTAR EN PANTALLA
     hilo=new Thread(this);
     hilo.start();
        setVisible(true);  // SE OBTIENE LA HORA DEL SISTEMA PARA MOSTAR EN PANTALLA
        autocompletar(); //metodo autocompletar que sirve para cuando el usuario escriba un articulo y encuentre coincidencias en la base de datos
        this.setLocationRelativeTo(null); // esto elimina los botones de cerrar, minimizar y maximizar
        update.setEnabled(false); //mantiene el boton de actualizar usuarios oculto hasta ser llamado cuando se le necesite
        actualizarpro.setEnabled(false); //de igual manera que el anterior, solo que este es para los proveedores
                agregarpro.setEnabled(false);
      update_users.setEnabled(false);
        llenarcomboarticulos(); //es un metodo para cargar los datos de los id de los proveedores disponibles actualmente en la base de datos
        llenarcombotipoproducto();// es un metodo para mostrar todos los tipos de productos que se tienen en  la base de datos
       AutoCompleteDecorator.decorate(searchforproducts);  //es un metodo parte de la libreria autocompleter
      user.setText(usuarioname);
    TablaDatosUsuarios();   /********* METODO LLAMADO AL INICIAR EL SISTEMA LOS DATOS YA ESTAN CARAGADOS  *********/
    Ocultoetiquetas();
    OcultosProductos();
    labeldescuento.setVisible(false);
    descuentocombo.setVisible(false);
                   totalcondescuento.setVisible(false);
               jLabel61.setVisible(false);
               descuentolabel.setVisible(false);

    }
    
    public static String fechaventasrealizadas(){ /* SE DECARA LA FECHA DEL SISTEMA */
        Date fecha=new Date();
        SimpleDateFormat formatoFecha= new SimpleDateFormat("YYYY/MM/dd");
        return formatoFecha.format(fecha);
    }
    
    public void ventaseneldiaREALIZADAS(){
        try{ // La suma de todos los importes
    
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select SUM(total) from venta where fecha_reporte= '"+fechaventasrealizadas()+"'");
                                            while(rs.next()){
                                                      ventasdeldia =Float.parseFloat(rs.getString("SUM(total)"));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                      }// fin del precio-catch del producto
    }
    
     public void totalventasxdia(){
        try{ // CUENTA EL TODAL DE CUANTAS VENTAS SE REALIZARON
    
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("SELECT COUNT(`id_venta`) FROM `venta` WHERE fecha_reporte = '"+fechaventasrealizadas()+"' AND `total`!=0");
                                            while(rs.next()){
                                                      TOTALVENTAS =Float.parseFloat(rs.getString("COUNT(`id_venta`)"));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                      }// fin del precio-catch del producto
    }
     
      public void ventasCanceladas(){
        try{ // CUENTA EL TODAL DE CUANTAS VENTAS SE CANCELADAS
    
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("SELECT COUNT(`id_venta`) FROM `venta` WHERE fecha_reporte = '"+fechaventasrealizadas()+"' AND `total`=0");
                                            while(rs.next()){
                                                      TOTALVENTASCANECL =Float.parseFloat(rs.getString("COUNT(`id_venta`)"));
                                                      }
                                                      }//fin del try-precio del producto  SELECT COUNT(`id_venta`) FROM `venta` WHERE fecha_reporte = CURDATE() AND `total`=0;

                                                      catch (Exception e){
                                                      }// fin del precio-catch del producto
    }
      
      
    // CONSULTA DE PRODUCTOS MAS DENVIDOS            
     public void LlenarTablaproductosmasvendidos(JTable tablaD){ // recibe como parametro 
         Object[] columna = new Object[4];  //crear un obj con el nombre de colunna
            Connection ca= cc.conexion(); // CONEXION DB 
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  tablaD.setModel(modeloT);  // add modelo ala tabla 
        
        modeloT.addColumn("nombre_producto");
        modeloT.addColumn("cantidad");        
        modeloT.addColumn("precio_unitario");
        modeloT.addColumn("fecha_reporte");

        try {
         String sSQL = "SELECT `nombre_producto`, `cantidad`, `precio_unitario`, venta.fecha_reporte FROM descripcion_de_venta inner join venta on descripcion_de_venta.`id_venta` = venta.id_venta WHERE fecha_reporte = CURDATE() ORDER BY `cantidad` DESC";
         
         
                 
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                columna[0] = rs.getString("nombre_producto");
                columna[1] = rs.getString("cantidad");
                columna[2] = rs.getString("precio_unitario");
                columna[3] = rs.getString("fecha_reporte");
                //columna[5] = rs.getString("nombre");                
                modeloT.addRow(columna);
            }
        }
        ps.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Advertencia", JOptionPane.PLAIN_MESSAGE);    
    }
}
     
     public String llenarfechadesde(){ // Ordena la fecha del componente Jcalendar  que esta de la sig. manera:  dia /mes / aÃ±o, lo cual para la base de datos no es la manera correcta de ingresarlo, sino asÃ­: aÃ±o/mes/dia
        
       int año= fecha_inicioestadis.getCalendar().get(Calendar.YEAR);
       int mes= fecha_inicioestadis.getCalendar().get(Calendar.MONTH)+1;
       int dia= fecha_inicioestadis.getCalendar().get(Calendar.DAY_OF_MONTH);
       fechadesde= año+"-"+mes+"-"+dia;
        return fechadesde;
    }
    
     public String llenarfechahasta(){ // Ordena la fecha del componente Jcalendar  que esta de la sig. manera:  dia /mes / aÃ±o, lo cual para la base de datos no es la manera correcta de ingresarlo, sino asÃ­: aÃ±o/mes/dia
        
       int año= fecha_finalestadis.getCalendar().get(Calendar.YEAR);
       int mes= fecha_finalestadis.getCalendar().get(Calendar.MONTH)+1;
       int dia= fecha_finalestadis.getCalendar().get(Calendar.DAY_OF_MONTH);
       fechahasta= año+"-"+mes+"-"+dia;
        return fechahasta;
    }
     
     
      /*  ======   HACIENDO UNA CONSULTA MAS VENDIDOS RANGO DE FECHAs =======A*/          
          public void LlenarTablaBusquedproMasvendidosfecha(JTable tablaD, String fecha_inicioestadis, String fecha_finalestadis){ // recibe como parametro 
       
               Object[] columna = new Object[4];  //crear un obj con el nombre de colunna
            Connection ca= cc.conexion(); // CONEXION DB 
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  tablaD.setModel(modeloT);  // add modelo ala tabla 
        
        modeloT.addColumn("nombre_producto");
        modeloT.addColumn("cantidad");        
        modeloT.addColumn("precio_unitario"); 
        modeloT.addColumn("fecha_reporte");

        try {
         String sSQL = "SELECT `nombre_producto`, `cantidad`, `precio_unitario` , venta.fecha_reporte FROM descripcion_de_venta inner join venta on descripcion_de_venta.`id_venta` = venta.id_venta where venta.fecha_reporte BETWEEN '"+llenarfechadesde()+"' AND '"+llenarfechahasta()+"' ORDER BY `cantidad` DESC";
         
                 
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                columna[0] = rs.getString("nombre_producto");
                columna[1] = rs.getString("cantidad");
                columna[2] = rs.getString("precio_unitario");
                columna[3] = rs.getString("fecha_reporte");
                //columna[5] = rs.getString("nombre");                
                modeloT.addRow(columna);
            }
        }
        ps.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Advertencia", JOptionPane.PLAIN_MESSAGE);    
    }
}
          
          
   // CONSULTA DE VENTAS  REALIZADAS
            
     public void LlenarTablaventasRe(JTable tablaD){ // recibe como parametro 
         Object[] columna = new Object[5];  //crear un obj con el nombre de colunna
            Connection ca= cc.conexion(); // CONEXION DB 
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  tablaD.setModel(modeloT);  // add modelo ala tabla 
        
        modeloT.addColumn("id_venta");    // add al modelo las 5 columnas con los nombrs TABLA
        modeloT.addColumn("nombre_producto");
        modeloT.addColumn("cantidad");        
        modeloT.addColumn("importe");
        modeloT.addColumn("total");
       //modeloT.addColumn("nombre");               
         /* SELECT `idegreso`, `tipo`, `total`, `fecha`, turno FROM `egreso` \n" + "  INNER JOIN empleado\n" + "WHERE egreso.`empleado_idempleado` = empleado.idempleado";     */    
        try {
         String sSQL = "SELECT venta.`id_venta`, `nombre_producto`,`cantidad`,`importe`, venta.total FROM `descripcion_de_venta` \n" +
"  inner join venta \n" +
"  on descripcion_de_venta.`id_venta` = venta.id_venta WHERE `estado` = 'Realizada' AND fecha_reporte = CURDATE()";
         
         
         /*   VENTAS FREALIZADAS
        
      SELECT venta.`id_venta`, `nombre_producto`,`cantidad`,`importe`, venta.total FROM `descripcion_de_venta` 
  inner join venta 
  on descripcion_de_venta.`id_venta` = venta.id_venta WHERE `estado` = 'Realizada';
                
        */
  
         
         //   "SELECT *FROM egreso\n" + "WHERE fecha = '2019-07-20'";            
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                columna[0] = rs.getString("id_venta");  /* === LA DB == */
                columna[1] = rs.getString("nombre_producto");
                columna[2] = rs.getString("cantidad");
                columna[3] = rs.getString("importe");
                columna[4] = rs.getString("total");
                //columna[5] = rs.getString("nombre");                
                modeloT.addRow(columna);
            }
        }
        ps.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Advertencia", JOptionPane.PLAIN_MESSAGE);    
    }
}
     
     
      // CONSULTA DE TOTAL DE VENTAS CANCELADAS
            
     public void LlenarTablaventasCance(JTable tablaD){ // recibe como parametro 
         Object[] columna = new Object[5];  //crear un obj con el nombre de colunna
            Connection ca= cc.conexion(); // CONEXION DB 
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  tablaD.setModel(modeloT);  // add modelo ala tabla 
        
        modeloT.addColumn("id_venta");    // add al modelo las 5 columnas con los nombrs TABLA
        modeloT.addColumn("nombre_producto");
        modeloT.addColumn("cantidad");        
        modeloT.addColumn("importe");
        modeloT.addColumn("total");
       //modeloT.addColumn("nombre");               
         /* SELECT `idegreso`, `tipo`, `total`, `fecha`, turno FROM `egreso` \n" + "  INNER JOIN empleado\n" + "WHERE egreso.`empleado_idempleado` = empleado.idempleado";     */    
        try {
         String sSQL = "SELECT venta.`id_venta`, `nombre_producto`,`cantidad`,`importe`, venta.total FROM `descripcion_de_venta` \n" +
"  inner join venta \n" +
"  on descripcion_de_venta.`id_venta` = venta.id_venta WHERE `estado` = 'Cancelada' AND fecha_reporte = CURDATE()";
         
         
         /* VENTAS CANECLADASSS                          
         
         
         SELECT venta.`id_venta`, `nombre_producto`,`cantidad`,`importe`, venta.total, venta.fecha_reporte FROM `descripcion_de_venta` 
         inner join venta on descripcion_de_venta.`id_venta` = venta.id_venta WHERE `estado` = 'Cancelada' AND fecha_reporte = CURDATE();
         
         */
  
         
         //   "SELECT *FROM egreso\n" + "WHERE fecha = '2019-07-20'";            
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                columna[0] = rs.getString("id_venta");  /* === LA DB == */
                columna[1] = rs.getString("nombre_producto");
                columna[2] = rs.getString("cantidad");
                columna[3] = rs.getString("importe");
                columna[4] = rs.getString("total");
                //columna[5] = rs.getString("nombre");                
                modeloT.addRow(columna);
            }
        }
        ps.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Advertencia", JOptionPane.PLAIN_MESSAGE);    
    }
}

    
    
    
    
    
    
    
     //  ICONO AL EJECUTAR EL PROYECTO
                 public Image getIconImage(){
                     Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Reportes/logo5.png"));
                     return retValue;
                 }
             // FIN DEL ICONO
    

    
     public void quienentroalsistema(){
                String usuario="";
                try {
            Statement st = ca.createStatement();
            ResultSet rs= st.executeQuery("select * from  user where nombre_usuario ='"+usuarioname+"'");
            while(rs.next()){
            usuario=rs.getString(1);
            }
            if(!usuario.equals("")){ //Si el nombre del usuario se encontro en la base de datos quiere decir que entro un usuario al sistema
                this.Proveedores9.setEnabledAt(1, false); //Desactiva la parte de proveedores
                        this.Proveedores9.setEnabledAt(3, false); //Desactiva la parte de usuarios
                        //Dejando unicamente activa la parte de venta y de consulta de productos
            } 
            else{ //No entro un usuario y entro el administrador
                     this.Proveedores9.setEnabledAt(0, false); //Desactiva la parte de la venta dejando unicamente activo, proveedores
                      //productos y usuarios
                      Proveedores9.setSelectedIndex(1); //Esto hace que la pestaña 1 sea la que se muestre cuando al admin inicie sesión

            }
        } catch (SQLException ex) {
            Logger.getLogger(menu_principal.class.getName()).log(Level.SEVERE, null, ex);
        }
            }
    
     public static String fecha(){ /* SE DECARA LA FECHA DEL SISTEMA */
        Date fecha=new Date();
        SimpleDateFormat formatoFecha= new SimpleDateFormat("YYYY/MM/dd");
        return formatoFecha.format(fecha);
    }
     
    public void hora(){
        Calendar calendario=new GregorianCalendar();
        Date horaactual=new Date();
        calendario.setTime(horaactual);
    hora=calendario.get(Calendar.HOUR_OF_DAY)>9?""+calendario.get(Calendar.HOUR_OF_DAY):"0"+calendario.get(Calendar.HOUR_OF_DAY);
    minutos=calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
    segundos= calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);        
     }
    public void run() {
      Thread ct = Thread.currentThread();
      while(ct==hilo){
          hora();
          Reloj.setText(hora+":"+minutos+":"+segundos+" ");
      }
    }   // HORA DEL SISTEMA REFLEJADOO EN EL FRAME
    
    
         //METODO PARA VERIFICAR QUE HAYA SUFICIENTES PIEZAS DE ALGÚN PRODUCTO PARA HACER UNA VENTA
    
    
     public void piezassuficientes(){
          try{ //el id del producto
                                                      sent  =(Statement)ca.createStatement();
                                                      rs = sent.executeQuery("select * from productos where nombre_producto= '"+searchforproducts.getSelectedItem()+"'");
                                                      while(rs.next()){
                                                      piezassuficientes =Integer.parseInt(rs.getString("cantidad"));
                                                      }
                                                      if(piezassuficientes>=Integer.parseInt(cantidad.getText())){
                                                          suficientespiezas=true;
                                                      }
                                                      else {
                                                          suficientespiezas=false;
                                                      }
                                                     }//fin del try - id del producto
                                                       catch (Exception e){
                                                      } //fin del id-catch del producto
    }
    
    
    //METODO PARA VERIFICAR QUE HAYA SUFICIENTES PIEZAS DE ALGÚN PRODUCTO PARA HACER UNA VENTA
    
    
    
   public void TablaDatosUsuarios(){  /********* METODOS DE LA TABLA DE LOS USUARIOS  *********/
       tabla_usuarios.setVisible(true);
              DefaultTableModel modelo = new DefaultTableModel();
    
    modelo.addColumn("Id Usuario");           modelo.addColumn("Nombre");
    modelo.addColumn("Usuario");              modelo.addColumn("Apellido Paterno");
    modelo.addColumn("Apellido Materno");     modelo.addColumn("ContraseÃ±a");
    modelo.addColumn("Email");                modelo.addColumn("RFC");
    modelo.addColumn("Telefono");             modelo.addColumn("Estado");
    modelo.addColumn("Fecha y Hora de registro");
    
     tabla_usuarios.setModel(modelo);
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
           tabla_usuarios.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(menu_principal.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }  // FIN DE LA TABLA DE DATOS DE LOS USUSARIOS ******************
    
   
    
    public void Ocultoetiquetas(){  // ETIKETAS CORRECTOS/INCO... OCULTAS AL INICIO DE SISTEMA LA PARTE DE LOS PROVEEDORES
    jLabel38.setVisible(false);        jLabel67.setVisible(false);      jLabel47.setVisible(false);      jLabel51.setVisible(false);  
    jLabel45.setVisible(false);        jLabel36.setVisible(false);      jLabel68.setVisible(false);      jLabel34.setVisible(false); 
    jLabel44.setVisible(false);        jLabel37.setVisible(false);      jLabel73.setVisible(false);      jLabel66.setVisible(false);
    jLabel50.setVisible(false);        jLabel40.setVisible(false);      jLabel48.setVisible(false);      jLabel49.setVisible(false); 
    jLabel39.setVisible(false);        jLabel65.setVisible(false);      jLabel72.setVisible(false);      jLabel46.setVisible(false);
    jLabel70.setVisible(false);        jLabel43.setVisible(false);      jLabel35.setVisible(false);      jLabel69.setVisible(false);  
    jLabel41.setVisible(false);        jLabel42.setVisible(false);      jLabel71.setVisible(false);
       agregarpro.setEnabled(false);  /* BOTON DE AGREGAR AL INICIAR EL SISTEMA PERMANECE OCULTO */
    }
    
    public void OcultosProductos(){  /* ETIKETAS CON ICONOS DE CORECTO E INCORRECTO INICIALIZADOS COMO OCULTOS DE LA PARTE DE LOS PRODUCTOS */
        jLabel56.setVisible(false);  /* ETIQUETAS DE DATOS CORRECTOS  */
        jLabel52.setVisible(false);          jLabel74.setVisible(false);
        jLabel75.setVisible(false);          jLabel53.setVisible(false);        jLabel55.setVisible(false);     
        agregar.setEnabled(false);  /* BOTON DE AGREGAR AL INICIAR EL SISTEMA PERMANECE OCULTO */
    }
    
    
      public boolean TodoValido(){ // Creacion del metodo de las validaciones de los campos de texto  ---->>> PROVEEDORESS
    if(!proem.getForeground().equals(new Color(236, 240, 0xf1)) || !proname.getForeground().equals(new Color(236, 240, 0xf1)) || !propater.getForeground().equals(new Color(236, 240, 0xf1)) || !promater.getForeground().equals(new Color(236, 240, 0xf1)) ||
            !promail.getForeground().equals(new Color(236, 240, 0xf1)) || !prorfc.getForeground().equals(new Color(236, 240, 0xf1)) || !protel.getForeground().equals(new Color(236, 240, 0xf1)) || !tipopro.getForeground().equals(new Color(236, 240, 0xf1)) || !despro.getForeground().equals(new Color(236, 240, 0xf1))){  
           return true; 
        }    
        return false;
    }
      
      public boolean TodoValidoProducts(){ // Creacion del metodo de las validaciones de los campos de texto ---->>> PRODUCTOSSS
    if(!namep.getForeground().equals(new Color(236, 240, 0xf1)) || !preciop.getForeground().equals(new Color(236, 240, 0xf1)) ){  
           return true; 
        }    
        return false;
    }  
    
           public void RetornaValorAddProduct(){ /* UN AVEZ K SE INGRESAN LOS DATOS RETORNA LOS VALORES DE LOS PLACEHOLD */
   proem.setText("Micro Computer Systems");
      proem.setFont(new Font("Arial",Font.ITALIC, 17));
        proem.setForeground(new  Color (34,167,240));
                 proname.setText("Alexis");
                    proname.setFont(new Font("Arial",Font.ITALIC, 17));
                       proname.setForeground(new  Color (34,167,240));
          propater.setText("Rodriguez");
              propater.setFont(new Font("Arial",Font.ITALIC, 17));
                  propater.setForeground(new  Color (34,167,240));
                              promater.setText("Reyes");
                                   promater.setFont(new Font("Arial",Font.ITALIC, 17));
                                      promater.setForeground(new  Color (34,167,240));
        promail.setText("usuario@hotmail.com");
           promail.setFont(new Font("Arial",Font.ITALIC, 17));
               promail.setForeground(new  Color (34,167,240));
                               prorfc.setText("COGR830816T88");
                                   prorfc.setFont(new Font("Arial",Font.ITALIC, 17));
                                   prorfc.setForeground(new  Color (34,167,240));
      protel.setText("733-117-0056");
         protel.setFont(new Font("Arial",Font.ITALIC, 17));
           protel.setForeground(new  Color (34,167,240));
                             tipopro.setText("Ejemplo: Lacteos");
                                 tipopro.setFont(new Font("Arial",Font.ITALIC, 17));
                                    tipopro.setForeground(new  Color (34,167,240));
          despro.setText("Productos de Origen Animal");
              despro.setFont(new Font("Arial",Font.ITALIC, 17));
                  despro.setForeground(new  Color (34,167,240));
    }
           
        public void RetornaValorProducts(){ /* UN AVEZ K SE INGRESAN LOS DATOS RETORNA LOS VALORES DE LOS PLACEHOLD  productosss*/
               namep.setText("Leche 1lt");
               namep.setFont(new Font("Arial",Font.ITALIC, 17));
               namep.setForeground(new  Color (34,167,240));
                                        preciop.setText("00.00");
                                        preciop.setFont(new Font("Arial",Font.ITALIC, 17));
                                        preciop.setForeground(new  Color (34,167,240));
    }
               public void RetornaValorUpdateProducts(){ /* UN AVEZ K SE INGRESAN LOS DATOS RETORNA LOS VALORES DE LOS PLACEHOLD */
                    namep.setText("");
                    namep.setFont(new Font("Arial",Font.ITALIC, 17));
                    namep.setForeground(new Color(236, 240, 241));
                                preciop.setText("");
                                preciop.setFont(new Font("Arial",Font.ITALIC, 17));
                                preciop.setForeground(new Color(236, 240, 241));
               }
               
               public void restaurarmodificacionesregistrousuario(){
       user_UserUp.setText("USUARIO");
       user_ContraUp.setText("COTRASEÑA");
       user_EmailUp.setText("EMAIL");
       user_TelefonoUp.setText("TELEFONO");
    }
           
           public void RetornaValorUpdate(){ /* UN AVEZ K SE INGRESAN LOS DATOS RETORNA LOS VALORES DE LOS PLACEHOLD */
   proem.setText("");
   proem.setFont(new Font("Tahoma",Font.BOLD, 17));
   proem.setForeground(new Color(236, 240, 241));
                 proname.setText("");
                 proname.setFont(new Font("Tahoma",Font.BOLD, 17));
                 proname.setForeground(new Color(236, 240, 241));
          propater.setText("");
          propater.setFont(new Font("Tahoma",Font.BOLD, 17));
          propater.setForeground(new Color(236, 240, 241));
                              promater.setText("");
                              promater.setFont(new Font("Tahoma",Font.BOLD, 17));
                              promater.setForeground(new Color(236, 240, 241));
        promail.setText("");
        promail.setFont(new Font("Tahoma",Font.BOLD, 17));
        promail.setForeground(new Color(236, 240, 241));
                               prorfc.setText("");
                               prorfc.setFont(new Font("Tahoma",Font.BOLD, 17));
                               prorfc.setForeground(new Color(236, 240, 241));
      protel.setText("");
      protel.setFont(new Font("Tahoma",Font.BOLD, 17));
      protel.setForeground(new Color(236, 240, 241));
              tipopro.setText(""); tipopro.setFont(new Font("Tahoma",Font.BOLD, 17)); tipopro.setForeground(new Color(236, 240, 241));
    despro.setText(""); despro.setFont(new Font("Tahoma",Font.BOLD, 17)); despro.setForeground(new Color(236, 240, 241));  
    }

           public void descontardeinventario(){
            try{// el id del usuario
                try{
                       sent  = (Statement)ca.createStatement();
                                           rs = sent.executeQuery("select * from productos where id_producto='"+id_producto+"' and fecha= '"+fecha()+"'");
                                            while(rs.next()){
                                                      productos =Integer.parseInt(rs.getString("cantidad"));
                                                      }
                }catch (Exception f){
                }
                productos=productos-Integer.parseInt(cantidad.getText());
                id_producto();
                 PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+productos+"'WHERE id_producto='"+id_producto+"' and fecha= '"+fecha()+"'");
                ps.executeUpdate();
                 }//fin del id del usuario
                 catch(Exception w){
                     JOptionPane.showMessageDialog(null, "Error" + w.getMessage());
                 }//fin del id del usuario
           if("pollo crudo".equalsIgnoreCase(searchforproducts.getSelectedItem().toString())){
                descontarpiezasdepollocrudodeinventario();
            }
    }

           public void descontarpiezasdepollocrudodeinventario(){//INICIO DE DESCONTAR POLLO CRUDO DE INVENTARIO
Pantalla_Gastos ven = new Pantalla_Gastos();
               String name="";
           for(int i=0; i<ven.piezas.length; i++) {
          //System.out.println(piezas [i]); 
    
            try{
                       sent  = (Statement)ca.createStatement();
                                           rs = sent.executeQuery("select * from productos  where nombre_producto='"+ven.piezas[i]+"' and fecha= '"+fecha()+"'");
                                            while(rs.next()){
                                                      name =rs.getString("nombre_producto");
                                                     cantidadpolloenDB =rs.getInt("cantidad"); // piezas en la db
                                                      }
         
   if(name.equals(ven.piezas[i])){ //Si el nombre del producto es diferente del estado vacio, en palabras más sencillas; si se encuentra el producto que se quiere agregar para que no se asigne nuevamente  
        try{// el id del usuario
                if(ven.piezas[i].equals("Pechuga")||ven.piezas[i].equals("Muslo")||
                   ven.piezas[i].equals("Pierna")||
                   ven.piezas[i].equals("Ala")||
                   ven.piezas[i].equals("Patas")){
addpiezas=cantidadpolloenDB-(2*Float.parseFloat(cantidad.getText()));
            
               PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+addpiezas+"'WHERE nombre_producto='"+ven.piezas[i]+"' and fecha= '"+fecha()+"'");
               int ty = ps.executeUpdate();
                
                 if(ty>0){
                  
            
                 }else{
     
                 }
           }
           else if(ven.piezas[i].equals("Huacal")||ven.piezas[i].equals("Cadera")||
                   ven.piezas[i].equals("Cabeza")||
                   ven.piezas[i].equals("Molleja")){
       
         addpiezas=cantidadpolloenDB-Float.parseFloat(cantidad.getText());
          PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+addpiezas+"'WHERE nombre_producto='"+ven.piezas[i]+"' and fecha= '"+fecha()+"'");
               int ty = ps.executeUpdate();
                
                 if(ty>0){
          
                 }else{
                 
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

           }//FIN DE DESCONTAR POLLO CRUDO DE INVENTARIO
           
           public void regresarpiezasdepollocrudodeinventario(){//INICIO DE DESCONTAR POLLO CRUDO DE INVENTARIO
      Pantalla_Gastos ven = new Pantalla_Gastos();
                  String pollocrudo="pollo crudo"; 
               String name="";
           for(int i=0; i<ven.piezas.length; i++) {
          //System.out.println(piezas [i]); 
       //AGREGAR EL TRY DE LA CANTIDAD EN VENTA
             
             id_max_de_venta();
           
            try{// 
                //Cantidad en venta
                try{
                sent  =(Statement)ca.createStatement(); 
                     rs = sent.executeQuery("select * from descripcion_de_venta where id_producto= '"+pollocrudo+"'AND estado='"+estadoenturno+"'and id_venta='"+id_de_la_venta_incrementable+"'");    
                    
                while(rs.next()){    
                    cantidadenventa =Integer.parseInt(rs.getString("cantidad"));    
                      
               }
                }catch(Exception e){
                    
                }
               sent  = (Statement)ca.createStatement();
                                           rs = sent.executeQuery("select * from productos  where nombre_producto='"+ven.piezas[i]+"' and fecha= '"+fecha()+"'");
                                            while(rs.next()){
                                                      name =rs.getString("nombre_producto");
                                                     cantidadpolloenDB =rs.getInt("cantidad"); // piezas en la db
                                                      }
           
   if(name.equals(ven.piezas[i])){ //Si el nombre del producto es diferente del estado vacio, en palabras más sencillas; si se encuentra el producto que se quiere agregar para que no se asigne nuevamente  
        try{// el id del usuario
                if(ven.piezas[i].equals("Pechuga")||ven.piezas[i].equals("Muslo")||
                   ven.piezas[i].equals("Pierna")||
                   ven.piezas[i].equals("Ala")||
                   ven.piezas[i].equals("Patas")){
                   
                      addpiezas=cantidadpolloenDB+(2*cantidadenventa);
               
               PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+addpiezas+"'WHERE nombre_producto='"+ven.piezas[i]+"' and fecha= '"+fecha()+"'");
               int ty = ps.executeUpdate();
                
                 if(ty>0){
                  
            
                 }else{
     
                 }
           }
           else if(ven.piezas[i].equals("Huacal")||ven.piezas[i].equals("Cadera")||
                   ven.piezas[i].equals("Cabeza")||
                   ven.piezas[i].equals("Molleja")){
        addpiezas=cantidadpolloenDB+cantidadenventa;
         PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+addpiezas+"'WHERE nombre_producto='"+ven.piezas[i]+"' and fecha= '"+fecha()+"'");
               int ty = ps.executeUpdate();
                
                 if(ty>0){
          
                 }else{
                 
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

           }//FIN DE DESCONTAR POLLO CRUDO DE INVENTARIO
           public void precio_producto(){
       
        try{ // el precio del producto
                                sent  =(Statement)ca.createStatement();
                                           rs = sent.executeQuery("select * from productos where nombre_producto= '"+searchforproducts.getSelectedItem()+"'");
                                            while(rs.next()){
                                                      precio =Float.parseFloat(rs.getString("precio"));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                      }// fin del precio-catch del producto
    }

    
  
           
           public void id_producto(){
          try{ //el id del producto
                                                      sent  =(Statement)ca.createStatement();
                                                      rs = sent.executeQuery("select * from productos where nombre_producto= '"+searchforproducts.getSelectedItem()+"'");
                                                      while(rs.next()){
                                                      id_producto =Integer.parseInt(rs.getString("id_producto"));
                                                      }
                                                      }//fin del try - id del producto
                                                      catch (Exception e){
                                                      } //fin del id-catch del producto
    }

   
           public void  limpiardatosdeventa(){
        cantidad.setText("");
        total.setText("00.00");
        pagocombobox.setText("00.00");
        cambiocombobox.setText("00.00");
        tablaventa.setVisible(false);
        tablaventaactiva=false;
        labeldescuento.setVisible(false);
    descuentocombo.setVisible(false);
                   totalcondescuento.setVisible(false);
               jLabel61.setVisible(false);
               descuentolabel.setVisible(false);
    } 
        public void get_id_usuario(){
      try{// el id del usuario para obtener el id del usuario y comprobar si hay o no algun registro
                         try{ 
                          sent = ca.createStatement();
                          ResultSet rs= sent.executeQuery("SELECT *  FROM  user where nombre_usuario ='"+usuarioname+"'");
                          while(rs.next()){
                                   id_usuario=Integer.parseInt(rs.getString("id_usuario"));
                         }
                         }catch(Exception a){
                             
                         }
                         if(block_unlock==true){
                               String sql = "INSERT INTO  venta(id_usuario)  VALUES (?)";
                         PreparedStatement pst = ca.prepareCall(sql); 
                         pst.setInt(1,id_usuario);
                         int a=pst.executeUpdate();
                         if(a>0){
                         }
                         }
                       
                         
      }catch(Exception w){
                     JOptionPane.showMessageDialog(null,"error en id usuario"+w);
      }//fin del id del usuario para comprobar si hay o no elementos ya guardados
  }

   
        public void id_max_de_venta(){
                        try{ 
                          sent = ca.createStatement();
                          ResultSet rs= sent.executeQuery("SELECT  max(id_venta) FROM  venta");
                          while(rs.next()){
                                   id_de_la_venta_incrementable=rs.getInt(1);
                         }
                         }catch(Exception a){
                              JOptionPane.showMessageDialog(null,a,"error ",JOptionPane.INFORMATION_MESSAGE);
                        
                         }
  }
             void mostrartabladeventas(){ // solo muestra la tabla de proveedores 
         tablaventa.setVisible(true);    //hace visible la tabla de proveedores 
              DefaultTableModel modelo = new DefaultTableModel(); // Se crea un objeto para agregar los nombres de las columnas a la tabla
    modelo.addColumn("Nombre del producto");
    modelo.addColumn("Cantidad");
    modelo.addColumn("Precio");
     modelo.addColumn("Importe");
     tablaventa.setModel(modelo);  // Ya una vez asignado todos los nombres se le envia el objeto a la tabla proveedores
    String []datos = new String[4];     //Un arreglo con la cantidad de nombres en las columnas
    try {
        id_max_de_venta();
             sent = ca.createStatement();   
                               //      rs = sent.executeQuery("select * from descripcion_de_venta where id_venta= '"+id_de_la_venta_incrementable+"'");
                       rs= sent.executeQuery("select * from  descripcion_de_venta where id_venta= '"+id_de_la_venta_incrementable+"' and  estado = '"+estadoenturno+"'"); // se ejecuta la sentencia dentro del parentesis

            while(rs.next()){        
            datos[0]=rs.getString(3);
            datos[1]=rs.getString(4);
            datos[2]=rs.getString(5);
            datos[3]=rs.getString(6);
            modelo.addRow(datos); //se asigna el arreglo  entero a todo el objeto llamado modelo  
            }
           tablaventa.setModel(modelo); // Se vuelve a enviar nuevamente el objeto modelo a la tabla
        } catch (SQLException ex) {
            Logger.getLogger(menu_principal.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo mostrar ningun dato porque tu consulta está mal");
        } 
    }

    public void autocompletar(){ //metodo sin retorno para obtener la lista de campos de la tabla productos la cual obtiene cada uno de los nombres para poder hacer algunas coincidencias al momento que el usuario estÃ¡ escribiendo
          ArrayList<String> lista = new ArrayList<String>();
      
        searchforproducts.removeAllItems(); //Ã‰sta linea es importante ya que cada vez que se llama este metodo se eliminan los item que previamente se cargaron en la llamada anterior, ESTO PARA QUE NO SE VUELVAN AGREGAR LOS MISMOS ITEMS, MÃ�S DE 1 VEZ
        try{
            sent  =(Statement)ca.createStatement();
           rs = sent.executeQuery("select nombre_producto from productos where cantidad > 0");
            while(rs.next()){
               searchforproducts.addItem(rs.getString("nombre_producto"));
            }
            for(int a=0; a<lista.size(); a++){
           searchforproducts.addItem(lista.get(a)); //Este ciclo lo que hace es ordenarlos de manera de lista descendente
        }
        }catch (Exception e){
            
        }
    }
    
            public void primer_ventadelsistema(){
             //metodo agregado 11 septiembre 2018
             try {
             sent = ca.createStatement();   
             rs = sent.executeQuery("select * from venta");
            while(rs.next()){        
            resultfirstselling=Integer.parseInt(rs.getString(1)); //Obtiene el id de la venta
            }
            if(resultfirstselling!=0){ //si el id resultante de la consulta es diferente de 0 quiere decir que ya hay por lo menos una venta en el sistema
            primerventa=1; //entonces el valor de "primerventa" se convertirá en 1, indicando que ya hay por lo menos una venta
            }
        } 
             catch (SQLException ex) {
            Logger.getLogger(menu_principal.class.getName()).log(Level.SEVERE, null, ex);
        }
             
         catch(NumberFormatException NFE){ // caso contrario que la variable resultfirstselling tuviese un valor null indicaria que no hay ninguna venta en el sistema
                    primerventa=0; //y por tal la variable primerventa tendra el valor de 0
        }
   } 
            public void vaciartodoelpollocrudodeinventario(){
              try{              
           PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad= 0 WHERE nombre_producto in ('pollo crudo', 'Pechuga', 'Muslo', 'Ala', 'Pierna', 'Huacal', 'Cadera', 'Cabeza', 'Molleja', 'Patas')");
                  int a = ps.executeUpdate();
                if(a>0){    
                }
                           }catch(Exception e){
                               System.err.print(e);
                           }
            }
             //METODOS PARA DESCONTAR 1 POLLO  O N POLLOS EN BASE A LAS PIEZAS QUE SE HAN DESCONTADO
             public void descuentodepollo(){
                 minimodelaspiezasdepollocrudoquesoninparesentablaproductos();
                 minimodelaspiezasdepollocrudoquesonparesentablaproductos();
                 if(minimodelaspiezasinparesdepollocrudoeninventario==0){
                     actualizarpollocrudoeninventario(minimodelaspiezasinparesdepollocrudoeninventario);
                 }
                 else if(minimodelaspiezasparesdepollocrudoeninventario==1){
                      actualizarpollocrudoeninventario(0);
                 }
                 else if(pollo_crudoeninventario!=minimodelaspiezasinparesdepollocrudoeninventario&&minimodelaspiezasparesdepollocrudoeninventario==(minimodelaspiezasinparesdepollocrudoeninventario*2)){
                      actualizarpollocrudoeninventario(minimodelaspiezasinparesdepollocrudoeninventario);
                 }else if(minimodelaspiezasinparesdepollocrudoeninventario!=(minimodelaspiezasparesdepollocrudoeninventario/2)){
                    //ESTO ELIMINA LOS DECIMALES DEL SOBRANTE
                    //AQUI EL LINK
                    //https://www.yoelprogramador.com/como-usar-el-formateador-decimal-en-java/
                     java.text.DecimalFormat formatoSalida = new java.text.DecimalFormat("0");//para ningun decimal
                     actualizarpollocrudoeninventario(Float.parseFloat(formatoSalida.format(minimodelaspiezasparesdepollocrudoeninventario/2)));
                 }
             }
             
             public void actualizarpollocrudoeninventario(float actualizaciondepollo){
                 try{              
           PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+actualizaciondepollo+"'WHERE nombre_producto='"+pollo_crudo+"'");
                  int a = ps.executeUpdate();
                if(a>0){    
                }
                           }catch(Exception e){
                               System.err.print(e);
                           }
             }
             
     
              public void minimodelaspiezasdepollocrudoquesoninparesentablaproductos(){
              String consulta="select MIN(cantidad) from productos WHERE nombre_producto IN('Huacal', 'Cadera', 'Cabeza', 'Molleja')";
              try{
                     sent  = (Statement)ca.createStatement();
                                             rs = sent.executeQuery(consulta);
                                       while(rs.next()){
                                                      minimodelaspiezasinparesdepollocrudoeninventario =rs.getFloat(1);//Esto muestra la cantidad actual para compararlo
                                                        }
                 }catch(Exception e){  
                }
          }
          public void minimodelaspiezasdepollocrudoquesonparesentablaproductos(){
              String consulta="select MIN(cantidad) from productos WHERE nombre_producto IN('Pechuga', 'Muslo', 'Pierna', 'Ala', 'Patas')";
              try{
                     sent  = (Statement)ca.createStatement();
                                             rs = sent.executeQuery(consulta);
                                       while(rs.next()){
                                                      minimodelaspiezasparesdepollocrudoeninventario =rs.getFloat(1);//Esto muestra la cantidad actual para compararlo
                                                        }
                 }catch(Exception e){  
                 }
          }
        //FIN METODOS PARA DESCONTAR 1 POLLO  O N POLLOS EN BASE A LAS PIEZAS QUE SE HAN DESCONTADO
                
            public void comprobar_registro (){
        id_producto();
    id_max_de_venta();
  try{
                       sent  = (Statement)ca.createStatement();
                                           rs = sent.executeQuery("select * from descripcion_de_venta  where id_producto='"+id_producto+"' and id_venta= '"+id_de_la_venta_incrementable+"' and estado= '"+estadoenturno+"'");
                                            while(rs.next()){
                                                      NoP =rs.getString("nombre_producto");
                                                      }
                              
                                    
   if(NoP.equals(searchforproducts.getSelectedItem().toString())){ //Si el nombre del producto es diferente del estado vacio, en palabras más sencillas; si se encuentra el producto que se quiere agregar para que no se asigne nuevamente  
      try{// el id del usuario
                try{
                    id_producto();
                      id_max_de_venta();
                       sent  = (Statement)ca.createStatement();
                                          rs = sent.executeQuery("select * from descripcion_de_venta  where id_producto='"+id_producto+"' and id_venta= '"+id_de_la_venta_incrementable+"' and estado= '"+estadoenturno+"'");
                                           while(rs.next()){
                                                      NoPcantidad =Integer.parseInt(rs.getString("cantidad"));
                                                      }
                }catch (Exception f){
                }
                NoPcantidad=NoPcantidad+Integer.parseInt(cantidad.getText());
                precio_producto();
                NoPimporte = NoPcantidad*precio;
                id_producto();
                    id_max_de_venta();
                 PreparedStatement ps = ca.prepareStatement ("UPDATE descripcion_de_venta SET cantidad='"+NoPcantidad+"',importe = '"+NoPimporte+"'WHERE id_producto='"+id_producto+"' and id_venta= '"+id_de_la_venta_incrementable+"' and estado= '"+estadoenturno+"'");
                ps.executeUpdate();
                descontardeinventario();
                  descuentodepollo();
                    mostrartabladeventas();
                    tablaventaactiva=true;
                    total_venta_enturno();
                    totalf=sumadeimportes;
                    total.setText(String.valueOf(totalf));
                    if(descuentoactivo==true){
                   JOptionPane.showMessageDialog(null, "descuento aplicado");
               if(Float.parseFloat(total.getText())>0){
               totalfinalcondescuento =  Float.parseFloat(total.getText()) - Float.parseFloat(descuentocombo.getText());
               totalcondescuento.setText(String.valueOf(totalfinalcondescuento));
                    }
                 }JOptionPane.showMessageDialog(null,"Articulo agregado correctamente: " +searchforproducts.getSelectedItem().toString(),"             Aviso",JOptionPane.INFORMATION_MESSAGE);
              cantidad.setText("");
        }//fin del id del usuario
                 catch(Exception w){
                     JOptionPane.showMessageDialog(null, "Error" + w.getMessage());
                 }//fin del id del usuario
               }
   else{
  try{ //la insersion a la tabla ventas
                String sql = "INSERT INTO  descripcion_de_venta(id_producto,nombre_producto,cantidad,precio_unitario,importe,id_venta,estado)  VALUES (?,?,?,?,?,?,?)";
                PreparedStatement pst = ca.prepareCall(sql); //hasta aqui vamos
                id_producto(); 
                pst.setInt(1,id_producto);
                storage.add(id_producto); //almacena cada id de cada producto en éste arreglo dinamico
                pst.setString(2,searchforproducts.getSelectedItem().toString());
                pst.setString(3,cantidad.getText());
                //EL METODO A CONTINUACION VA HACIENDO EL CONTEO DE LAS PIEZAS INDIVIDUALES
                // PARA UNA VEZ LLEGANDO A UN POLLO ENTERO DESCONTARLO DE LA BASE
                
                 precio_producto();
                pst.setFloat(4,precio);
                importe = Integer.parseInt(cantidad.getText())*precio;
                pst.setFloat(5,importe);
                id_max_de_venta();
                pst.setInt(6,(id_de_la_venta_incrementable));
                pst.setString(7, estadoenturno);
                int a=pst.executeUpdate();
                if(a>0){
                    descontardeinventario();
                      descuentodepollo();
                    mostrartabladeventas();
                    tablaventaactiva=true;
                   total_venta_enturno();
                    totalf=sumadeimportes;
                    total.setText(String.valueOf(totalf));
                    primerproducto=false;
                    productorepetido=true;
                    productoagregado=true;
                    if(descuentoactivo==true){
                   JOptionPane.showMessageDialog(null, "descuento aplicado");
               if(Float.parseFloat(total.getText())>0){
               totalfinalcondescuento =  Float.parseFloat(total.getText()) - Float.parseFloat(descuentocombo.getText());
               totalcondescuento.setText(String.valueOf(totalfinalcondescuento));
                    }
                 }
                                        JOptionPane.showMessageDialog(null,"Articulo agregado correctamente: " +searchforproducts.getSelectedItem().toString(),"             Aviso",JOptionPane.INFORMATION_MESSAGE);
              cantidad.setText("");
                }
            }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
            }//fin de la insersion a la tabla ventas
    }
         }catch (Exception f){
                    JOptionPane.showMessageDialog(null, "Error, nombre de producto no registrado" + f.getMessage());
                
         }
    }
            public  void descuentos(){
                   float totalparadescuentos = Float.parseFloat(total.getText());
                      
               if(totalparadescuentos>0){
                   do{
                        porcentaje = Float.parseFloat(JOptionPane.showInputDialog(null, "Porcentaje a descontar", JOptionPane.INFORMATION_MESSAGE));
                        if(porcentaje==100){
                            JOptionPane.showMessageDialog(null, "No se puede aplicar el 100% de descuento, lo siento, vuelve a intentarlo");
                        }
                   }while(porcentaje>=100);
                  
                descuentoactivo=true;
               descuentocantidad=(totalparadescuentos * porcentaje)/100;
               descuentocombo.setText(String.valueOf(descuentocantidad));
               totalfinalcondescuento = totalparadescuentos - descuentocantidad;
               totalcondescuento.setText(String.valueOf(totalfinalcondescuento));
                 totalcondescuento.setVisible(true);
               jLabel61.setVisible(true);
               descuentolabel.setVisible(true);
                labeldescuento.setVisible(true);
    descuentocombo.setVisible(true);
 
               }
               else{
                   JOptionPane.showMessageDialog(null, "Aún no hay productos para hacer descuento");
               }
                 
            }

            
                public void regresarproductos_a_inventario(){ // este metodo devuelve los productos que fueron agregados a la venta y posteriormente fueron cancelados
                  String es_pollo_crudo="";
                   int piezasenventa=0;
                                  float piezasenlatablapiezas=0;
                    id_max_de_venta();
                    block_unlock=true;
                for(int n=0;n<=storage.size()-1;n++){
                    //pendiente la restauracion de venta a inventario
                    try{ //obteniendo la cantidad en la tabla de descripcion_de_venta
                        sent  =(Statement)ca.createStatement();
                             rs = sent.executeQuery("select * from descripcion_de_venta where id_producto= '"+storage.get(n)+"'AND estado='"+estadoenturno+"'and id_venta='"+id_de_la_venta_incrementable+"'");
                    
                        while(rs.next()){
                            cantidadenventa =Integer.parseInt(rs.getString("cantidad"));
                            es_pollo_crudo=rs.getString("nombre_producto");
                        }
                       try{
                            sent  =(Statement)ca.createStatement();
                            rs = sent.executeQuery("select * from productos where id_producto='"+storage.get(n)+"'");
                            while(rs.next()){
                                cantidadeninventario =Integer.parseInt(rs.getString("cantidad"));
                            }
                        }catch (Exception f){
                            JOptionPane.showMessageDialog(null, "Error en inventario" + f.getMessage());
                        }
                        cantidadeninventario+=cantidadenventa;
                        try{
                            PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+cantidadeninventario+"'WHERE id_producto='"+storage.get(n)+"'");
                            ps.executeUpdate();
                        }catch(Exception s){
JOptionPane.showMessageDialog(null, "Error en venta aqui" + s.getMessage());
                        } 
                    }catch(Exception e){///obteniendo la cantidad en la tabla de descripcion_de_venta
                        JOptionPane.showMessageDialog(null, "Error en venta aqui otra vez" + e.getMessage());
                    } //obteniendo la cantidad en la tabla de descripcion_de_venta
                if("pollo crudo".equalsIgnoreCase(es_pollo_crudo)){
                    regresarpiezasdepollocrudodeinventario();
                }
                }//fin del ciclo for
                
                
            
               
}
                public void status_cancelado(){
       id_max_de_venta();
        try{
                    PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET total='"+variablede0+"',porcentajedescontado='"+variablede0+"',descuento='"+variablede0+"',pago='"+variablede0+"',cambio='"+variablede0+"'WHERE id_venta='"+id_de_la_venta_incrementable+"'");
                ps.executeUpdate();
        }
            catch (Exception e){
               JOptionPane.showMessageDialog(null, "Error en venta" + e.getMessage());
            }
        
         // SEPARACION DE LA FECHA Y HORA DEL TIMESTAMP
                 try{ 
              sent  =(Statement)ca.createStatement();
                                           rs = sent.executeQuery("select * from venta where id_venta= '"+id_de_la_venta_incrementable+"'");
                                            while(rs.next()){
                                                      fechayhora =rs.getString("fecha_y_hora");
                                                      }
                                            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = dt.parse(fechayhora);

        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        ///System.out.println(dt1.format(date));   ---------  dt1.format(date) es donde se almacena solo la fecha
                                            fechasinhora=dt1.format(date);
                 }//fin del try-fechasin
                                                      catch (Exception e){
                                                      }// fin del fechasin
                

                // FIN DE LA SEPARACION DE FECHA Y HORA DEL TIMESTAMP
                
                //INSERTANDO SOLO LA FECHA EN LA TABLA VENTA
                 try{
                            PreparedStatement ps1 = ca.prepareStatement ("UPDATE venta SET fecha_reporte='"+fechasinhora+"'WHERE id_venta='"+id_de_la_venta_incrementable+"'");
                            ps1.executeUpdate();
                        }catch(Exception s){
JOptionPane.showMessageDialog(null, "Error en venta" + s.getMessage());
                        }
                  //INSERTANDO SOLO LA FECHA EN LA TABLA VENTA
        try{
            id_max_de_venta();
            id_max_de_venta();
                PreparedStatement ps = ca.prepareStatement ("UPDATE descripcion_de_venta SET estado= '"+estadocancelado+"' WHERE id_venta='"+id_de_la_venta_incrementable+"'");
                ps.executeUpdate();
        JOptionPane.showMessageDialog(null, "Venta cancelada","                  Aviso",JOptionPane.WARNING_MESSAGE);
        descuentoactivo=false; 
        storage.clear();
        }
        catch(Exception ex){
                           JOptionPane.showMessageDialog(null, "Error en venta" + ex.getMessage());
        }
   }

                    public void comprobar_venta_resagada(){ //verifica que haya o no una venta que fue cancelada previamente en la sesio anterior
        id_max_de_venta();
    int id_para_comprobacion = id_de_la_venta_incrementable;
        try {
             sent = ca.createStatement();   
             rs = sent.executeQuery("select * from venta where id_venta= '"+id_para_comprobacion+"'");
            while(rs.next()){        
            totalcomprobacion=Integer.parseInt(rs.getString(3));
            }
            if(totalcomprobacion!=0){// esta condicion dice que si no hay una venta previamente cancelada
                block_unlock=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(menu_principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(NumberFormatException NFE){
                            block_unlock=false; //Se desactiva para que no se agregue otra venta al usuario en turno , así al hacer otra venta 
                            //se agregará a e ésta venta resagada
        }
    }

                    public void verificar_id_ingresadoalsistema(){ //verifica que haya o no una venta que fue cancelada previamente en la sesio anterior
        id_max_de_venta();
    int id_para_comprobacion = id_de_la_venta_incrementable, id=0;
        try {
             sent = ca.createStatement();   
             rs = sent.executeQuery("select * from venta where id_venta= '"+id_para_comprobacion+"'");
            while(rs.next()){        
            id=Integer.parseInt(rs.getString(2));
            }
            block_unlock=false;
            get_id_usuario();
            if(id==id_usuario){// esta condicion sirve para saber si "id" (el id almacenado en la ultima venta) es igual al "id_usuario" (el usuario que ingreo al sistema
            }
            else {
            id_max_de_venta();
                try{
                     PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET id_usuario='"+id_usuario+"' WHERE id_venta= '"+id_de_la_venta_incrementable+"'");
          ps.executeUpdate();
                }catch (Exception e){
                     JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
                }
            }
           
        } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        catch(NumberFormatException NFE){
                           
        }
    }

                public void total_venta_enturno(){
        try{ // La suma de todos los importes
           id_max_de_venta();
                                          sent  =(Statement)ca.createStatement();
                                           rs = sent.executeQuery("select SUM(importe) from descripcion_de_venta where id_venta= '"+id_de_la_venta_incrementable+"' and estado='"+estadoenturno+"'");
                                            while(rs.next()){
                                                      sumadeimportes =Float.parseFloat(rs.getString("SUM(importe)"));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                      }// fin del precio-catch del producto
    }

    
    // FIN DE METODOS PARA EL AREA DE VENTAS -----------------------------------------------------------------------------------------------------------------------------------------------------

    //DIVERSOS METODOS  CON UTILIDAD EN MUCHAS DE LAS AREAS DEL SISTEMA----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    public String llenarfecha(){ // Ordena la fecha del componente Jcalendar  que esta de la sig. manera:  dia /mes / aÃ±o, lo cual para la base de datos no es la manera correcta de ingresarlo, sino asÃ­: aÃ±o/mes/dia
        String fecha="";
       int año= fechap.getCalendar().get(Calendar.YEAR);
       int mes= fechap.getCalendar().get(Calendar.MONTH)+1;
       int dia= fechap.getCalendar().get(Calendar.DAY_OF_MONTH);
       fecha= año+"-"+mes+"-"+dia;
        return fecha;
    }
    
   public ArrayList<String> llenarcomboarticulos(){ // este metodo obtiene los id_proveedor disponibles en la base de datos actualmente
        ArrayList<String> lista = new ArrayList<String>();
        proveedorarticulo.removeAllItems();  //Ã‰sta linea es importante ya que cada vez que se llama este metodo se eliminan los item que previamente se cargaron en la llamada anterior, ESTO PARA QUE NO SE VUELVAN AGREGAR LOS MISMOS ITEMS, MÃ�S DE 1 VEZ
        try{
            sent = ca.createStatement();
             rs= sent.executeQuery("select * from  proveedores");
            while(rs.next()){
                lista.add(rs.getString("nombre_de_la_empresa"));
            }
             for(int a=0; a<lista.size(); a++){
            proveedorarticulo.addItem(lista.get(a));
        }
        }catch(Exception e){
            System.err.print(e);
        }
        
        return lista;
    }
   
    public ArrayList<String> llenarcombotipoproducto(){// este metodo obtiene los tipo_producto disponibles en la base de datos actualmente
        ArrayList<String> lista = new ArrayList<String>();
        
        tipodeproducto.removeAllItems();//Ã‰sta linea es importante ya que cada vez que se llama este metodo se eliminan los item que previamente se cargaron en la llamada anterior, ESTO PARA QUE NO SE VUELVAN AGREGAR LOS MISMOS ITEMS, MÃ�S DE 1 VEZ
        try{
            Statement st = ca.createStatement();
            rs= st.executeQuery("select * from  categoria_producto");
            while(rs.next()){
                lista.add(rs.getString("tipo_producto"));
            }
             for(int a=0; a<lista.size(); a++){
            tipodeproducto.addItem(lista.get(a));
        }
        }catch(Exception e){
            System.err.print(e);
        }
        
        return lista;
    }
//FIN DE DIVERSOS METODOS  CON UTILIDAD EN MUCHAS DE LAS AREAS DEL SISTEMA----------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------


// METODOS PARA MOSTRAR TABLAS --------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------------
    void mostrartablaproveedores(){ // solo muestra la tabla de proveedores 

         proveedores.setVisible(true);    //hace visible la tabla de proveedores 
              DefaultTableModel modelo = new DefaultTableModel(); // Se crea un objeto para agregar los nombres de las columnas a la tabla
    modelo.addColumn("Id del proveedor");
    modelo.addColumn("Nombre de la empresa");
    modelo.addColumn("Nombre del proveedor");
     modelo.addColumn("Apellido paterno");
     modelo.addColumn("Apellido materno");
     modelo.addColumn("E-mail");
    modelo.addColumn("RFC");
    modelo.addColumn("Telefono");
      modelo.addColumn("Fecha y hora de registro");
     proveedores.setModel(modelo);  // Ya una vez asignado todos los nombres se le envia el objeto a la tabla proveedores
    String []datos = new String[9];     //Un arreglo con la cantidad de nombres en las columnas
    try {
             sent = ca.createStatement();   
            rs= sent.executeQuery("select * from  proveedores"); // se ejecuta la sentencia dentro del parentesis
            while(rs.next()){        
            datos[0]=rs.getString(1);
            datos[1]=rs.getString(2);
            datos[2]=rs.getString(3);
            datos[3]=rs.getString(4);
            datos[4]=rs.getString(5);
            datos[5]=rs.getString(6);
            datos[6]=rs.getString(7);
            datos[7]=rs.getString(8);
            datos[8]=rs.getString(10);
            
        
            modelo.addRow(datos); //se asigna el arreglo  entero a todo el objeto llamado modelo  
            }
           proveedores.setModel(modelo); // Se vuelve a enviar nuevamente el objeto modelo a la tabla
        } catch (SQLException ex) {
            Logger.getLogger(menu_principal.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    void mostrartablaarticulos(){
        tabla_agregar.setVisible(true);
              DefaultTableModel modelo = new DefaultTableModel();
    
    modelo.addColumn("Id del producto");
    modelo.addColumn("Nombre del producto");
    modelo.addColumn("Tipo de producto");
     modelo.addColumn("Precio");
     modelo.addColumn("Cantidad");
     modelo.addColumn("Fecha de caducidad");
    modelo.addColumn("Id del proveedor");
    modelo.addColumn("fecha y hora de registro");
    tabla_agregar.setModel(modelo);
    String []datos = new String[8];    
    try {
            Statement st = ca.createStatement();
            ResultSet rs= st.executeQuery("select * from  productos");
            while(rs.next()){
            datos[0]=rs.getString(1);
            datos[1]=rs.getString(2);
            datos[2]=rs.getString(3);
            datos[3]=rs.getString(4);
            datos[4]=rs.getString(5);
            datos[5]=rs.getString(6);
            datos[6]=rs.getString(7);
            datos[7]=rs.getString(8);
        
            modelo.addRow(datos);
            }
           tabla_agregar.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(menu_principal.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    void mostrartabladeventa(){
        tablaventa.setVisible(true);
              DefaultTableModel modelo = new DefaultTableModel();
    
   
    modelo.addColumn("Nombre del producto");
     modelo.addColumn("Cantidad");
     modelo.addColumn("Precio Unitario");
     modelo.addColumn("Importe");
     
    tabla_agregar.setModel(modelo);
    String []datos = new String[8];    
    try {
            Statement st = ca.createStatement();
            ResultSet rs= st.executeQuery("select * from  productos");
            while(rs.next()){
            datos[0]=rs.getString(1);
            datos[1]=rs.getString(2);
            datos[2]=rs.getString(3);
            datos[3]=rs.getString(4);
            
            modelo.addRow(datos);
            }
           tabla_agregar.setModel(modelo);
        } catch (SQLException ex) {
            Logger.getLogger(menu_principal.class.getName()).log(Level.SEVERE, null, ex);
        } 
    }
    
    void limpiardatosarticulos(){
           namep.setText("");
           preciop.setText("");
           cantp.setText("");
        fechap.cleanup();
  fechap.setDate(null);
    }
    void limpiardatosproveedores(){
           proem.setText("");
           proname.setText("");
           propater.setText("");
           promater.setText("");
           promail.setText("");
           prorfc.setText("");
           protel.setText("");
    }
       
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        tabla_articulos = new javax.swing.JPopupMenu();
        modify = new javax.swing.JMenuItem();
        eliminar = new javax.swing.JMenuItem();
        tabla_proveedores = new javax.swing.JPopupMenu();
        modificar = new javax.swing.JMenuItem();
        drop = new javax.swing.JMenuItem();
        tablausuarios = new javax.swing.JPopupMenu();
        modificarusuarios = new javax.swing.JMenuItem();
        eliminarusuarios = new javax.swing.JMenuItem();
        activarusuario = new javax.swing.JMenuItem();
        Proveedores9 = new javax.swing.JTabbedPane();
        venta = new javax.swing.JPanel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaventa = new javax.swing.JTable();
        searchforproducts = new javax.swing.JComboBox<>();
        jLabel28 = new javax.swing.JLabel();
        s1 = new javax.swing.JButton();
        r1 = new javax.swing.JButton();
        cantidad = new javax.swing.JTextField();
        agregar2 = new javax.swing.JButton();
        jLabel29 = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        jPanel10 = new javax.swing.JPanel();
        pagocombobox = new javax.swing.JTextField();
        cambiocombobox = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        venta_lista = new javax.swing.JButton();
        jLabel31 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jLabel32 = new javax.swing.JLabel();
        jButton5 = new javax.swing.JButton();
        total = new javax.swing.JLabel();
        totalcondescuento = new javax.swing.JLabel();
        jLabel61 = new javax.swing.JLabel();
        descuentolabel = new javax.swing.JLabel();
        jSeparator20 = new javax.swing.JSeparator();
        jSeparator22 = new javax.swing.JSeparator();
        jPanel2 = new javax.swing.JPanel();
        user = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        user1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Reloj = new javax.swing.JLabel();
        Fecha = new javax.swing.JLabel();
        IblReloj = new javax.swing.JLabel();
        AgregarGastos = new javax.swing.JButton();
        Cortedecaja = new javax.swing.JButton();
        descuento = new javax.swing.JButton();
        labeldescuento = new javax.swing.JLabel();
        descuentocombo = new javax.swing.JLabel();
        total1 = new javax.swing.JLabel();
        total2 = new javax.swing.JLabel();
        jSeparator21 = new javax.swing.JSeparator();
        agregar_proveedor = new javax.swing.JPanel();
        agregarpro = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel23 = new javax.swing.JLabel();
        jLabel24 = new javax.swing.JLabel();
        filler1 = new javax.swing.Box.Filler(new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 0), new java.awt.Dimension(0, 32767));
        agregarpro1 = new javax.swing.JButton();
        actualizarpro = new javax.swing.JButton();
        tipopro = new javax.swing.JTextField();
        despro = new javax.swing.JTextField();
        jScrollPane3 = new javax.swing.JScrollPane();
        proveedores = new javax.swing.JTable();
        jPanel3 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel33 = new javax.swing.JLabel();
        jPanel6 = new javax.swing.JPanel();
        jLabel9 = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        jLabel21 = new javax.swing.JLabel();
        proname = new javax.swing.JTextField();
        promater = new javax.swing.JTextField();
        prorfc = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        jLabel20 = new javax.swing.JLabel();
        proem = new javax.swing.JTextField();
        protel = new javax.swing.JTextField();
        promail = new javax.swing.JTextField();
        propater = new javax.swing.JTextField();
        jSeparator9 = new javax.swing.JSeparator();
        jSeparator10 = new javax.swing.JSeparator();
        jSeparator11 = new javax.swing.JSeparator();
        jSeparator12 = new javax.swing.JSeparator();
        jSeparator13 = new javax.swing.JSeparator();
        jSeparator14 = new javax.swing.JSeparator();
        jSeparator15 = new javax.swing.JSeparator();
        jLabel35 = new javax.swing.JLabel();
        jLabel65 = new javax.swing.JLabel();
        jLabel36 = new javax.swing.JLabel();
        jLabel37 = new javax.swing.JLabel();
        jLabel38 = new javax.swing.JLabel();
        jLabel39 = new javax.swing.JLabel();
        jLabel40 = new javax.swing.JLabel();
        jLabel41 = new javax.swing.JLabel();
        jLabel42 = new javax.swing.JLabel();
        jLabel44 = new javax.swing.JLabel();
        jLabel45 = new javax.swing.JLabel();
        jLabel47 = new javax.swing.JLabel();
        jLabel48 = new javax.swing.JLabel();
        jLabel49 = new javax.swing.JLabel();
        jLabel50 = new javax.swing.JLabel();
        jLabel67 = new javax.swing.JLabel();
        jLabel68 = new javax.swing.JLabel();
        jLabel70 = new javax.swing.JLabel();
        jLabel71 = new javax.swing.JLabel();
        jLabel72 = new javax.swing.JLabel();
        jLabel73 = new javax.swing.JLabel();
        jSeparator16 = new javax.swing.JSeparator();
        jSeparator17 = new javax.swing.JSeparator();
        jLabel34 = new javax.swing.JLabel();
        jLabel43 = new javax.swing.JLabel();
        jLabel46 = new javax.swing.JLabel();
        jLabel51 = new javax.swing.JLabel();
        jLabel66 = new javax.swing.JLabel();
        jLabel69 = new javax.swing.JLabel();
        Reportes = new javax.swing.JButton();
        agregar_articulo = new javax.swing.JPanel();
        agregar = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tabla_agregar = new javax.swing.JTable();
        mostrar = new javax.swing.JButton();
        update = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton2 = new javax.swing.JButton();
        jLabel22 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel57 = new javax.swing.JLabel();
        jPanel11 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        namep = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        proveedorarticulo = new javax.swing.JComboBox<>();
        fechap = new com.toedter.calendar.JDateChooser();
        preciop = new javax.swing.JTextField();
        cantp = new javax.swing.JTextField();
        tipodeproducto = new javax.swing.JComboBox<>();
        r = new javax.swing.JButton();
        s = new javax.swing.JButton();
        jSeparator18 = new javax.swing.JSeparator();
        jSeparator19 = new javax.swing.JSeparator();
        jLabel53 = new javax.swing.JLabel();
        jLabel74 = new javax.swing.JLabel();
        jLabel75 = new javax.swing.JLabel();
        jLabel52 = new javax.swing.JLabel();
        jLabel55 = new javax.swing.JLabel();
        jLabel56 = new javax.swing.JLabel();
        ReportesProduct = new javax.swing.JButton();
        ReportesVenta = new javax.swing.JButton();
        jLabel59 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        agregar_usuario = new javax.swing.JPanel();
        jScrollPane4 = new javax.swing.JScrollPane();
        tabla_usuarios = new javax.swing.JTable();
        jPanel7 = new javax.swing.JPanel();
        user_NombreUp = new javax.swing.JTextField();
        user_UserUp = new javax.swing.JTextField();
        user_ContraUp = new javax.swing.JTextField();
        user_EmailUp = new javax.swing.JTextField();
        user_TelefonoUp = new javax.swing.JTextField();
        jSeparator2 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();
        jSeparator5 = new javax.swing.JSeparator();
        jSeparator6 = new javax.swing.JSeparator();
        jLabel11 = new javax.swing.JLabel();
        jLabel25 = new javax.swing.JLabel();
        jLabel26 = new javax.swing.JLabel();
        jLabel27 = new javax.swing.JLabel();
        jLabel54 = new javax.swing.JLabel();
        jPanel9 = new javax.swing.JPanel();
        Reporte_user = new javax.swing.JButton();
        Reporte_user1 = new javax.swing.JButton();
        jPanel8 = new javax.swing.JPanel();
        agregar3 = new javax.swing.JButton();
        update_users = new javax.swing.JButton();
        jPanel5 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        jButton4 = new javax.swing.JButton();
        jLabel58 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        Administrador = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane7 = new javax.swing.JScrollPane();
        Jtable_ventasRealizadas = new javax.swing.JTable();
        jLabel63 = new javax.swing.JLabel();
        jTextField1 = new javax.swing.JTextField();
        Ventasfortoday1 = new javax.swing.JLabel();
        VentasTotal = new javax.swing.JLabel();
        jLabel90 = new javax.swing.JLabel();
        jPanel24 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel95 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane9 = new javax.swing.JScrollPane();
        Jtable_ventasCanceladas = new javax.swing.JTable();
        jLabel62 = new javax.swing.JLabel();
        VentasTotalCance = new javax.swing.JLabel();
        jPanel13 = new javax.swing.JPanel();
        producto_sobrante = new javax.swing.JPanel();
        jPanel20 = new javax.swing.JPanel();
        jLabel79 = new javax.swing.JLabel();
        jLabel88 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel89 = new javax.swing.JLabel();
        jPanel25 = new javax.swing.JPanel();
        fecha_inicioestadis = new com.toedter.calendar.JDateChooser();
        jLabel60 = new javax.swing.JLabel();
        fecha_finalestadis = new com.toedter.calendar.JDateChooser();
        buscarproductosfecha = new javax.swing.JButton();
        jScrollPane5 = new javax.swing.JScrollPane();
        Jtable_productosmasven = new rojerusan.RSTableMetro();

        tabla_articulos.setComponentPopupMenu(tabla_articulos);

        modify.setText("Modificar");
        modify.setComponentPopupMenu(tabla_articulos);
        modify.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modifyActionPerformed(evt);
            }
        });
        tabla_articulos.add(modify);

        eliminar.setText("Eliminar");
        eliminar.setComponentPopupMenu(tabla_articulos);
        eliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarActionPerformed(evt);
            }
        });
        tabla_articulos.add(eliminar);

        modificar.setText("Modificar");
        modificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarActionPerformed(evt);
            }
        });
        tabla_proveedores.add(modificar);

        drop.setText("Eliminar");
        drop.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                dropActionPerformed(evt);
            }
        });
        tabla_proveedores.add(drop);

        modificarusuarios.setText("Modificar");
        modificarusuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                modificarusuariosActionPerformed(evt);
            }
        });
        tablausuarios.add(modificarusuarios);

        eliminarusuarios.setText("Eliminar");
        eliminarusuarios.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                eliminarusuariosActionPerformed(evt);
            }
        });
        tablausuarios.add(eliminarusuarios);

        activarusuario.setText("Activar Usuario");
        activarusuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                activarusuarioActionPerformed(evt);
            }
        });
        tablausuarios.add(activarusuario);

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Proveedores9.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N

        venta.setBackground(new java.awt.Color(0, 51, 102));
        venta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tablaventa.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tablaventa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        jScrollPane2.setViewportView(tablaventa);

        venta.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 150, 1207, 242));

        searchforproducts.setEditable(true);
        searchforproducts.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        venta.add(searchforproducts, new org.netbeans.lib.awtextra.AbsoluteConstraints(150, 100, 410, 30));

        jLabel28.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel28.setForeground(new java.awt.Color(255, 255, 255));
        jLabel28.setText("Producto:");
        venta.add(jLabel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 120, -1));

        s1.setBackground(new java.awt.Color(0, 148, 204));
        s1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        s1.setForeground(new java.awt.Color(255, 255, 255));
        s1.setText("+");
        s1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                s1ActionPerformed(evt);
            }
        });
        venta.add(s1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 100, 50, 30));

        r1.setBackground(new java.awt.Color(0, 148, 204));
        r1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        r1.setForeground(new java.awt.Color(255, 255, 255));
        r1.setText("-");
        r1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                r1ActionPerformed(evt);
            }
        });
        venta.add(r1, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 100, 50, 30));

        cantidad.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        cantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cantidad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cantidadActionPerformed(evt);
            }
        });
        venta.add(cantidad, new org.netbeans.lib.awtextra.AbsoluteConstraints(710, 100, 130, 30));

        agregar2.setBackground(new java.awt.Color(0, 148, 204));
        agregar2.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        agregar2.setForeground(new java.awt.Color(255, 255, 255));
        agregar2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/compra.png"))); // NOI18N
        agregar2.setText("Agregar");
        agregar2.setHorizontalAlignment(javax.swing.SwingConstants.LEADING);
        agregar2.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        agregar2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar2ActionPerformed(evt);
            }
        });
        venta.add(agregar2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 70, 190, 70));

        jLabel29.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel29.setForeground(new java.awt.Color(255, 255, 255));
        jLabel29.setText("Piezas:");
        venta.add(jLabel29, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 100, 90, -1));
        venta.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 460, 140, 10));

        jPanel10.setBackground(new java.awt.Color(0, 51, 102));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Venta   ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pagocombobox.setBackground(new java.awt.Color(0, 148, 204));
        pagocombobox.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        pagocombobox.setForeground(new java.awt.Color(255, 255, 255));
        pagocombobox.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        pagocombobox.setText("00.00");
        pagocombobox.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        pagocombobox.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pagocomboboxFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pagocomboboxFocusLost(evt);
            }
        });
        pagocombobox.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagocomboboxActionPerformed(evt);
            }
        });
        jPanel10.add(pagocombobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 90, 140, 30));

        cambiocombobox.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        cambiocombobox.setForeground(new java.awt.Color(255, 255, 255));
        cambiocombobox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cambiocombobox.setText("00.00");
        jPanel10.add(cambiocombobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 150, 140, 28));
        jPanel10.add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 120, 140, 10));

        venta_lista.setBackground(new java.awt.Color(0, 148, 204));
        venta_lista.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        venta_lista.setForeground(new java.awt.Color(255, 255, 255));
        venta_lista.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/dinero.png"))); // NOI18N
        venta_lista.setText("Cobrar");
        venta_lista.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        venta_lista.setVerticalAlignment(javax.swing.SwingConstants.BOTTOM);
        venta_lista.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        venta_lista.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                venta_listaActionPerformed(evt);
            }
        });
        venta_lista.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                venta_listaKeyPressed(evt);
            }
        });
        jPanel10.add(venta_lista, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 180, -1));

        jLabel31.setFont(new java.awt.Font("Trebuchet MS", 1, 22)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/camb5.png"))); // NOI18N
        jLabel31.setText("Cambio:");
        jPanel10.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 140, 140, -1));

        jLabel30.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/total.png"))); // NOI18N
        jLabel30.setText(" Total:");
        jLabel30.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel10.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 30, 120, -1));

        jLabel32.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel32.setForeground(new java.awt.Color(255, 255, 255));
        jLabel32.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/simbolodolar.png"))); // NOI18N
        jLabel32.setText("  Pagó:");
        jPanel10.add(jLabel32, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 90, 130, -1));

        jButton5.setBackground(new java.awt.Color(0, 148, 204));
        jButton5.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 255, 255));
        jButton5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/cancelar2.png"))); // NOI18N
        jButton5.setText("Cancelar");
        jButton5.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton5.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jButton5.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        jPanel10.add(jButton5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 130, 180, 100));

        total.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        total.setForeground(new java.awt.Color(255, 255, 255));
        total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total.setText("00.00");
        jPanel10.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 140, 28));

        totalcondescuento.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        totalcondescuento.setForeground(new java.awt.Color(255, 255, 255));
        totalcondescuento.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalcondescuento.setText("00.00");
        jPanel10.add(totalcondescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 210, 140, 28));

        jLabel61.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel61.setForeground(new java.awt.Color(255, 255, 255));
        jLabel61.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/total.png"))); // NOI18N
        jLabel61.setText("Total con");
        jLabel61.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel10.add(jLabel61, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 190, 170, -1));

        descuentolabel.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        descuentolabel.setForeground(new java.awt.Color(255, 255, 255));
        descuentolabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/total.png"))); // NOI18N
        descuentolabel.setText("Descuento :");
        descuentolabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel10.add(descuentolabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(190, 230, 190, -1));
        jPanel10.add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 250, 140, 10));
        jPanel10.add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 180, 140, 10));

        venta.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 400, 570, 270));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        user.setBackground(new java.awt.Color(0, 160, 204));
        user.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        user.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

        jLabel8.setBackground(new java.awt.Color(0, 160, 204));
        jLabel8.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/bloggif_5bd54d091a235.jpeg"))); // NOI18N

        user1.setBackground(new java.awt.Color(0, 160, 204));
        user1.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        user1.setForeground(new java.awt.Color(255, 255, 255));
        user1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/equipo.png"))); // NOI18N
        user1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        jButton1.setBackground(new java.awt.Color(0, 51, 102));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/serrar.png"))); // NOI18N
        jButton1.setText("Salir");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        Reloj.setFont(new java.awt.Font("Times New Roman", 1, 27)); // NOI18N
        Reloj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reloj.setText("00:00:00");

        Fecha.setFont(new java.awt.Font("Times New Roman", 1, 27)); // NOI18N
        Fecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Fecha.setText("DD/MM/YYYY");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(126, 126, 126)
                .addComponent(user1, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 72, Short.MAX_VALUE)
                .addComponent(Fecha)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(Reloj, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jButton1)
                .addGap(42, 42, 42))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(user, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Reloj, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jLabel8)
                        .addComponent(user1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4))
        );

        venta.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 60));

        IblReloj.setFont(new java.awt.Font("Times New Roman", 1, 24)); // NOI18N
        IblReloj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        IblReloj.setText("00:00:00");
        venta.add(IblReloj, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        AgregarGastos.setBackground(new java.awt.Color(0, 148, 204));
        AgregarGastos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        AgregarGastos.setForeground(new java.awt.Color(255, 255, 255));
        AgregarGastos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/egresos1.png"))); // NOI18N
        AgregarGastos.setText("Agregar Gastos");
        AgregarGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarGastosActionPerformed(evt);
            }
        });
        venta.add(AgregarGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(50, 400, 190, 50));

        Cortedecaja.setBackground(new java.awt.Color(0, 148, 204));
        Cortedecaja.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Cortedecaja.setForeground(new java.awt.Color(255, 255, 255));
        Cortedecaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/cajero1.png"))); // NOI18N
        Cortedecaja.setText("Corte de Caja");
        Cortedecaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CortedecajaActionPerformed(evt);
            }
        });
        venta.add(Cortedecaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 400, 190, 50));

        descuento.setBackground(new java.awt.Color(0, 148, 204));
        descuento.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        descuento.setForeground(new java.awt.Color(255, 255, 255));
        descuento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/cajero1.png"))); // NOI18N
        descuento.setText("Descuento");
        descuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descuentoActionPerformed(evt);
            }
        });
        venta.add(descuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(470, 400, 190, 50));

        labeldescuento.setFont(new java.awt.Font("Trebuchet MS", 1, 22)); // NOI18N
        labeldescuento.setForeground(new java.awt.Color(255, 255, 255));
        labeldescuento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/camb5.png"))); // NOI18N
        labeldescuento.setText("Descuento:");
        venta.add(labeldescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(350, 470, 170, -1));

        descuentocombo.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        descuentocombo.setForeground(new java.awt.Color(255, 255, 255));
        descuentocombo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descuentocombo.setText("00.00");
        venta.add(descuentocombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 480, 140, 28));

        total1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        total1.setForeground(new java.awt.Color(255, 255, 255));
        total1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total1.setText("00.00");
        venta.add(total1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 430, 140, 28));

        total2.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        total2.setForeground(new java.awt.Color(255, 255, 255));
        total2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total2.setText("00.00");
        venta.add(total2, new org.netbeans.lib.awtextra.AbsoluteConstraints(1080, 430, 140, 28));
        venta.add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 510, 140, 10));

        Proveedores9.addTab("      Venta      ", venta);

        agregar_proveedor.setBackground(new java.awt.Color(0, 51, 102));
        agregar_proveedor.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        agregarpro.setBackground(new java.awt.Color(0, 148, 204));
        agregarpro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        agregarpro.setForeground(new java.awt.Color(255, 255, 255));
        agregarpro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/Add1.png"))); // NOI18N
        agregarpro.setText("Agregar ");
        agregarpro.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        agregarpro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        agregarpro.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        agregarpro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        agregarpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarproActionPerformed(evt);
            }
        });
        agregar_proveedor.add(agregarpro, new org.netbeans.lib.awtextra.AbsoluteConstraints(780, 340, -1, 97));

        jLabel12.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("¿QUÉ TIPO DE PRODUCTOS OFRECE?");
        agregar_proveedor.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 310, 350, -1));

        jLabel23.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel23.setForeground(new java.awt.Color(255, 255, 255));
        jLabel23.setText("Tipo de producto:");
        agregar_proveedor.add(jLabel23, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 350, 160, -1));

        jLabel24.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel24.setForeground(new java.awt.Color(255, 255, 255));
        jLabel24.setText("Descripción :");
        agregar_proveedor.add(jLabel24, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 400, 120, -1));
        agregar_proveedor.add(filler1, new org.netbeans.lib.awtextra.AbsoluteConstraints(970, 343, -1, -1));

        agregarpro1.setBackground(new java.awt.Color(0, 148, 204));
        agregarpro1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        agregarpro1.setForeground(new java.awt.Color(255, 255, 255));
        agregarpro1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/plan-de-estudios.png"))); // NOI18N
        agregarpro1.setText("Mostrar");
        agregarpro1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        agregarpro1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        agregarpro1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        agregarpro1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarpro1ActionPerformed(evt);
            }
        });
        agregar_proveedor.add(agregarpro1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1000, 340, 100, 97));

        actualizarpro.setBackground(new java.awt.Color(0, 148, 204));
        actualizarpro.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        actualizarpro.setForeground(new java.awt.Color(255, 255, 255));
        actualizarpro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/actualizar.png"))); // NOI18N
        actualizarpro.setText("Actualizar");
        actualizarpro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        actualizarpro.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        actualizarpro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        actualizarpro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                actualizarproActionPerformed(evt);
            }
        });
        agregar_proveedor.add(actualizarpro, new org.netbeans.lib.awtextra.AbsoluteConstraints(890, 340, 100, 97));

        tipopro.setBackground(new java.awt.Color(0, 51, 102));
        tipopro.setFont(new java.awt.Font("Arial", 2, 17)); // NOI18N
        tipopro.setForeground(new java.awt.Color(255, 255, 255));
        tipopro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        tipopro.setText("Ejemplo: Lacteos");
        tipopro.setBorder(null);
        tipopro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                tipoproFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                tipoproFocusLost(evt);
            }
        });
        tipopro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                tipoproActionPerformed(evt);
            }
        });
        tipopro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                tipoproKeyTyped(evt);
            }
        });
        agregar_proveedor.add(tipopro, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 340, 221, 30));

        despro.setBackground(new java.awt.Color(0, 51, 102));
        despro.setFont(new java.awt.Font("Arial", 2, 17)); // NOI18N
        despro.setForeground(new java.awt.Color(255, 255, 255));
        despro.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        despro.setText("Productos de Origen Animal");
        despro.setBorder(null);
        despro.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                desproFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                desproFocusLost(evt);
            }
        });
        despro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                desproActionPerformed(evt);
            }
        });
        despro.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                desproKeyTyped(evt);
            }
        });
        agregar_proveedor.add(despro, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 390, 221, 30));

        proveedores.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        proveedores.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        proveedores.setComponentPopupMenu(tabla_proveedores);
        jScrollPane3.setViewportView(proveedores);

        agregar_proveedor.add(jScrollPane3, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 1240, 200));

        jPanel3.setBackground(new java.awt.Color(255, 255, 255));

        jLabel10.setBackground(new java.awt.Color(204, 255, 0));
        jLabel10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel10.setText("Agregar Nuevo Proveedor");

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/user022.png"))); // NOI18N

        jButton3.setBackground(new java.awt.Color(0, 51, 102));
        jButton3.setFont(new java.awt.Font("Tahoma", 1, 23)); // NOI18N
        jButton3.setForeground(new java.awt.Color(255, 0, 0));
        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/serrar.png"))); // NOI18N
        jButton3.setText("Salir");
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jLabel33.setBackground(new java.awt.Color(0, 160, 204));
        jLabel33.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel33.setForeground(new java.awt.Color(255, 255, 255));
        jLabel33.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/bloggif_5bd54d091a235.jpeg"))); // NOI18N

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jLabel33, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(55, 55, 55)
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 318, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 410, Short.MAX_VALUE)
                .addComponent(jButton3)
                .addGap(24, 24, 24))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 39, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel15)
                    .addComponent(jLabel33)
                    .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(0, 0, Short.MAX_VALUE))
        );

        agregar_proveedor.add(jPanel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 60));

        jPanel6.setBackground(new java.awt.Color(0, 51, 102));
        jPanel6.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "     DATOS GENERALES     ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Arial", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel6.setLayout(null);

        jLabel9.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Nombre de la empresa:");
        jPanel6.add(jLabel9);
        jLabel9.setBounds(16, 41, 198, 21);

        jLabel17.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel17.setForeground(new java.awt.Color(255, 255, 255));
        jLabel17.setText("Apellido paterno:");
        jPanel6.add(jLabel17);
        jLabel17.setBounds(16, 139, 158, 21);

        jLabel19.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel19.setForeground(new java.awt.Color(255, 255, 255));
        jLabel19.setText("Email:");
        jPanel6.add(jLabel19);
        jLabel19.setBounds(629, 78, 52, 21);

        jLabel21.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel21.setForeground(new java.awt.Color(255, 255, 255));
        jLabel21.setText("Telefono:");
        jPanel6.add(jLabel21);
        jLabel21.setBounds(630, 180, 81, 21);

        proname.setBackground(new java.awt.Color(0, 51, 102));
        proname.setFont(new java.awt.Font("Arial", 2, 17)); // NOI18N
        proname.setForeground(new java.awt.Color(255, 255, 255));
        proname.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        proname.setText("Alexis");
        proname.setBorder(null);
        proname.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                pronameFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                pronameFocusLost(evt);
            }
        });
        proname.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pronameActionPerformed(evt);
            }
        });
        proname.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                pronameKeyTyped(evt);
            }
        });
        jPanel6.add(proname);
        proname.setBounds(230, 80, 221, 30);

        promater.setBackground(new java.awt.Color(0, 51, 102));
        promater.setFont(new java.awt.Font("Arial", 2, 16)); // NOI18N
        promater.setForeground(new java.awt.Color(255, 255, 255));
        promater.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        promater.setText("Reyes");
        promater.setBorder(null);
        promater.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                promaterFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                promaterFocusLost(evt);
            }
        });
        promater.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                promaterActionPerformed(evt);
            }
        });
        promater.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                promaterKeyTyped(evt);
            }
        });
        jPanel6.add(promater);
        promater.setBounds(800, 20, 221, 30);

        prorfc.setBackground(new java.awt.Color(0, 51, 102));
        prorfc.setFont(new java.awt.Font("Arial", 2, 17)); // NOI18N
        prorfc.setForeground(new java.awt.Color(255, 255, 255));
        prorfc.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        prorfc.setText("COGR830816T88");
        prorfc.setBorder(null);
        prorfc.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                prorfcFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                prorfcFocusLost(evt);
            }
        });
        prorfc.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                prorfcActionPerformed(evt);
            }
        });
        prorfc.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                prorfcKeyTyped(evt);
            }
        });
        jPanel6.add(prorfc);
        prorfc.setBounds(800, 120, 221, 30);

        jLabel16.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel16.setForeground(new java.awt.Color(255, 255, 255));
        jLabel16.setText("Nombre del proveedor:");
        jPanel6.add(jLabel16);
        jLabel16.setBounds(16, 92, 198, 21);

        jLabel18.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel18.setForeground(new java.awt.Color(255, 255, 255));
        jLabel18.setText("Apellido materno:");
        jPanel6.add(jLabel18);
        jLabel18.setBounds(629, 33, 153, 21);

        jLabel20.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel20.setForeground(new java.awt.Color(255, 255, 255));
        jLabel20.setText("RFC:");
        jPanel6.add(jLabel20);
        jLabel20.setBounds(630, 130, 52, 21);

        proem.setBackground(new java.awt.Color(0, 51, 102));
        proem.setFont(new java.awt.Font("Arial", 2, 17)); // NOI18N
        proem.setForeground(new java.awt.Color(255, 255, 255));
        proem.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        proem.setText("Micro Computer Systems");
        proem.setBorder(null);
        proem.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                proemFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                proemFocusLost(evt);
            }
        });
        proem.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proemActionPerformed(evt);
            }
        });
        proem.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                proemKeyTyped(evt);
            }
        });
        jPanel6.add(proem);
        proem.setBounds(230, 30, 221, 30);

        protel.setBackground(new java.awt.Color(0, 51, 102));
        protel.setFont(new java.awt.Font("Arial", 2, 17)); // NOI18N
        protel.setForeground(new java.awt.Color(255, 255, 255));
        protel.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        protel.setText("733-117-0056");
        protel.setBorder(null);
        protel.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                protelFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                protelFocusLost(evt);
            }
        });
        protel.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                protelActionPerformed(evt);
            }
        });
        protel.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                protelKeyTyped(evt);
            }
        });
        jPanel6.add(protel);
        protel.setBounds(800, 170, 221, 30);

        promail.setBackground(new java.awt.Color(0, 51, 102));
        promail.setFont(new java.awt.Font("Arial", 2, 17)); // NOI18N
        promail.setForeground(new java.awt.Color(255, 255, 255));
        promail.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        promail.setText("usuario@hotmail.com");
        promail.setBorder(null);
        promail.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                promailFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                promailFocusLost(evt);
            }
        });
        promail.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                promailActionPerformed(evt);
            }
        });
        promail.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                promailKeyTyped(evt);
            }
        });
        jPanel6.add(promail);
        promail.setBounds(800, 70, 221, 30);

        propater.setBackground(new java.awt.Color(0, 51, 102));
        propater.setFont(new java.awt.Font("Arial", 2, 17)); // NOI18N
        propater.setForeground(new java.awt.Color(255, 255, 255));
        propater.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        propater.setText("Rodriguez");
        propater.setBorder(null);
        propater.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                propaterFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                propaterFocusLost(evt);
            }
        });
        propater.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                propaterActionPerformed(evt);
            }
        });
        propater.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                propaterKeyTyped(evt);
            }
        });
        jPanel6.add(propater);
        propater.setBounds(230, 131, 221, 30);
        jPanel6.add(jSeparator9);
        jSeparator9.setBounds(230, 60, 220, 20);
        jPanel6.add(jSeparator10);
        jSeparator10.setBounds(230, 160, 220, 20);
        jPanel6.add(jSeparator11);
        jSeparator11.setBounds(230, 110, 220, 20);
        jPanel6.add(jSeparator12);
        jSeparator12.setBounds(800, 150, 220, 10);
        jPanel6.add(jSeparator13);
        jSeparator13.setBounds(800, 50, 220, 10);
        jPanel6.add(jSeparator14);
        jSeparator14.setBounds(800, 200, 220, 20);
        jPanel6.add(jSeparator15);
        jSeparator15.setBounds(800, 100, 220, 10);

        jLabel35.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel35.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/borrado.gif"))); // NOI18N
        jPanel6.add(jLabel35);
        jLabel35.setBounds(1030, 170, 30, 30);

        jLabel65.setFont(new java.awt.Font("Arial Black", 3, 18)); // NOI18N
        jLabel65.setForeground(new java.awt.Color(255, 51, 51));
        jLabel65.setText("CAMPO VACIO");
        jPanel6.add(jLabel65);
        jLabel65.setBounds(1030, 20, 160, 30);

        jLabel36.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/correcto.gif"))); // NOI18N
        jPanel6.add(jLabel36);
        jLabel36.setBounds(460, 70, 48, 48);

        jLabel37.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/correcto.gif"))); // NOI18N
        jPanel6.add(jLabel37);
        jLabel37.setBounds(460, 120, 48, 48);

        jLabel38.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/correcto.gif"))); // NOI18N
        jPanel6.add(jLabel38);
        jLabel38.setBounds(460, 20, 48, 48);

        jLabel39.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/correcto.gif"))); // NOI18N
        jPanel6.add(jLabel39);
        jLabel39.setBounds(1040, 10, 48, 48);

        jLabel40.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/correcto.gif"))); // NOI18N
        jPanel6.add(jLabel40);
        jLabel40.setBounds(1040, 60, 48, 48);

        jLabel41.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/correcto.gif"))); // NOI18N
        jPanel6.add(jLabel41);
        jLabel41.setBounds(1040, 100, 48, 48);

        jLabel42.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/correcto.gif"))); // NOI18N
        jPanel6.add(jLabel42);
        jLabel42.setBounds(1040, 150, 48, 48);

        jLabel44.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel44.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/borrado.gif"))); // NOI18N
        jPanel6.add(jLabel44);
        jLabel44.setBounds(460, 80, 30, 30);

        jLabel45.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel45.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/borrado.gif"))); // NOI18N
        jPanel6.add(jLabel45);
        jLabel45.setBounds(460, 30, 30, 30);

        jLabel47.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel47.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/borrado.gif"))); // NOI18N
        jPanel6.add(jLabel47);
        jLabel47.setBounds(1030, 20, 30, 30);

        jLabel48.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel48.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/borrado.gif"))); // NOI18N
        jPanel6.add(jLabel48);
        jLabel48.setBounds(1030, 70, 30, 30);

        jLabel49.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel49.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/borrado.gif"))); // NOI18N
        jPanel6.add(jLabel49);
        jLabel49.setBounds(1030, 120, 30, 30);

        jLabel50.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel50.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/borrado.gif"))); // NOI18N
        jPanel6.add(jLabel50);
        jLabel50.setBounds(460, 130, 30, 30);

        jLabel67.setFont(new java.awt.Font("Arial Black", 3, 18)); // NOI18N
        jLabel67.setForeground(new java.awt.Color(255, 51, 51));
        jLabel67.setText("CAMPO VACIO");
        jPanel6.add(jLabel67);
        jLabel67.setBounds(460, 30, 160, 30);

        jLabel68.setFont(new java.awt.Font("Arial Black", 3, 18)); // NOI18N
        jLabel68.setForeground(new java.awt.Color(255, 51, 51));
        jLabel68.setText("CAMPO VACIO");
        jPanel6.add(jLabel68);
        jLabel68.setBounds(460, 80, 160, 30);

        jLabel70.setFont(new java.awt.Font("Arial Black", 3, 18)); // NOI18N
        jLabel70.setForeground(new java.awt.Color(255, 51, 51));
        jLabel70.setText("CAMPO VACIO");
        jPanel6.add(jLabel70);
        jLabel70.setBounds(1030, 60, 160, 30);

        jLabel71.setFont(new java.awt.Font("Arial Black", 3, 18)); // NOI18N
        jLabel71.setForeground(new java.awt.Color(255, 51, 51));
        jLabel71.setText("CAMPO VACIO");
        jPanel6.add(jLabel71);
        jLabel71.setBounds(1030, 110, 160, 30);

        jLabel72.setFont(new java.awt.Font("Arial Black", 3, 18)); // NOI18N
        jLabel72.setForeground(new java.awt.Color(255, 51, 51));
        jLabel72.setText("CAMPO VACIO");
        jPanel6.add(jLabel72);
        jLabel72.setBounds(1030, 170, 160, 30);

        jLabel73.setFont(new java.awt.Font("Arial Black", 3, 18)); // NOI18N
        jLabel73.setForeground(new java.awt.Color(255, 51, 51));
        jLabel73.setText("CAMPO VACIO");
        jPanel6.add(jLabel73);
        jLabel73.setBounds(460, 130, 160, 30);

        agregar_proveedor.add(jPanel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 80, 1200, 220));
        agregar_proveedor.add(jSeparator16, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 420, 220, 20));
        agregar_proveedor.add(jSeparator17, new org.netbeans.lib.awtextra.AbsoluteConstraints(270, 370, 220, 20));

        jLabel34.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/correcto.gif"))); // NOI18N
        agregar_proveedor.add(jLabel34, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 380, -1, -1));

        jLabel43.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/correcto.gif"))); // NOI18N
        agregar_proveedor.add(jLabel43, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 330, -1, -1));

        jLabel46.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel46.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/borrado.gif"))); // NOI18N
        agregar_proveedor.add(jLabel46, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 390, 30, 30));

        jLabel51.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel51.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/borrado.gif"))); // NOI18N
        agregar_proveedor.add(jLabel51, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 340, 30, 30));

        jLabel66.setFont(new java.awt.Font("Arial Black", 3, 18)); // NOI18N
        jLabel66.setForeground(new java.awt.Color(255, 51, 51));
        jLabel66.setText("CAMPO VACIO");
        agregar_proveedor.add(jLabel66, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 350, 160, 30));

        jLabel69.setFont(new java.awt.Font("Arial Black", 3, 18)); // NOI18N
        jLabel69.setForeground(new java.awt.Color(255, 51, 51));
        jLabel69.setText("CAMPO VACIO");
        agregar_proveedor.add(jLabel69, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 390, 160, 30));

        Reportes.setBackground(new java.awt.Color(0, 148, 204));
        Reportes.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Reportes.setForeground(new java.awt.Color(255, 255, 255));
        Reportes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/pdf.png"))); // NOI18N
        Reportes.setText("Proveedores");
        Reportes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Reportes.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Reportes.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Reportes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportesActionPerformed(evt);
            }
        });
        agregar_proveedor.add(Reportes, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 340, 130, 97));

        Proveedores9.addTab("      Nuevo  Proveedor      ", agregar_proveedor);

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
        agregar_articulo.add(agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 330, 95, 97));

        tabla_agregar.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
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
        tabla_agregar.setComponentPopupMenu(tabla_articulos);
        tabla_agregar.setSelectionBackground(new java.awt.Color(255, 255, 255));
        tabla_agregar.setSelectionForeground(new java.awt.Color(0, 135, 204));
        jScrollPane1.setViewportView(tabla_agregar);

        agregar_articulo.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 460, 1240, 200));

        mostrar.setBackground(new java.awt.Color(0, 148, 204));
        mostrar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        mostrar.setForeground(new java.awt.Color(255, 255, 255));
        mostrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/plan-de-estudios.png"))); // NOI18N
        mostrar.setText("Mostrar");
        mostrar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        mostrar.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        mostrar.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        mostrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                mostrarActionPerformed(evt);
            }
        });
        agregar_articulo.add(mostrar, new org.netbeans.lib.awtextra.AbsoluteConstraints(800, 330, 100, 97));

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
        agregar_articulo.add(update, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 330, 100, 97));

        jPanel4.setBackground(new java.awt.Color(255, 255, 255));

        jButton2.setBackground(new java.awt.Color(0, 51, 102));
        jButton2.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton2.setForeground(new java.awt.Color(255, 0, 0));
        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/serrar.png"))); // NOI18N
        jButton2.setText("Salir");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jLabel22.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/Prodcuts2.png"))); // NOI18N

        jLabel5.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel5.setText("Agregar Nuevo Producto");

        jLabel57.setBackground(new java.awt.Color(0, 160, 204));
        jLabel57.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel57.setForeground(new java.awt.Color(255, 255, 255));
        jLabel57.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/bloggif_5bd54d091a235.jpeg"))); // NOI18N

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addComponent(jLabel57)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 61, Short.MAX_VALUE)
                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel22, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(386, 386, 386)
                .addComponent(jButton2)
                .addGap(45, 45, 45))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel57)
                    .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel4Layout.createSequentialGroup()
                            .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4))
                        .addComponent(jLabel22, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        agregar_articulo.add(jPanel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 60));

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
        namep.setText("Leche 1lt");
        namep.setBorder(null);
        namep.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                namepFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                namepFocusLost(evt);
            }
        });
        namep.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                namepActionPerformed(evt);
            }
        });
        namep.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                namepKeyTyped(evt);
            }
        });
        jPanel11.add(namep);
        namep.setBounds(230, 30, 200, 30);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("  Tipo de producto:");
        jPanel11.add(jLabel3);
        jLabel3.setBounds(10, 80, 170, 30);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("  Cantidad (en piezas):");
        jPanel11.add(jLabel2);
        jLabel2.setBounds(10, 130, 200, 30);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("  Precio:");
        jPanel11.add(jLabel4);
        jLabel4.setBounds(10, 180, 80, 30);

        jLabel7.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("  Fecha de caducidad:");
        jPanel11.add(jLabel7);
        jLabel7.setBounds(10, 230, 190, 30);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Proveedor");
        jPanel11.add(jLabel6);
        jLabel6.setBounds(20, 280, 170, 30);

        proveedorarticulo.setBackground(new java.awt.Color(0, 153, 204));
        proveedorarticulo.setFont(new java.awt.Font("Tahoma", 0, 17)); // NOI18N
        proveedorarticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                proveedorarticuloActionPerformed(evt);
            }
        });
        jPanel11.add(proveedorarticulo);
        proveedorarticulo.setBounds(230, 280, 200, 30);

        fechap.setBackground(new java.awt.Color(0, 153, 204));
        fechap.setForeground(new java.awt.Color(0, 96, 255));
        fechap.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jPanel11.add(fechap);
        fechap.setBounds(230, 230, 200, 30);

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
        preciop.setBounds(230, 180, 200, 30);

        cantp.setBackground(new java.awt.Color(0, 148, 204));
        cantp.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        cantp.setForeground(new java.awt.Color(255, 255, 255));
        cantp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cantp.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        cantp.setEnabled(false);
        jPanel11.add(cantp);
        cantp.setBounds(230, 130, 80, 30);

        tipodeproducto.setBackground(new java.awt.Color(0, 153, 204));
        tipodeproducto.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jPanel11.add(tipodeproducto);
        tipodeproducto.setBounds(230, 80, 200, 30);

        r.setBackground(new java.awt.Color(0, 148, 204));
        r.setFont(new java.awt.Font("Arial Black", 1, 17)); // NOI18N
        r.setForeground(new java.awt.Color(255, 255, 255));
        r.setText("-");
        r.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                rActionPerformed(evt);
            }
        });
        jPanel11.add(r);
        r.setBounds(320, 130, 50, 30);

        s.setBackground(new java.awt.Color(0, 148, 204));
        s.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        s.setForeground(new java.awt.Color(255, 255, 255));
        s.setText("+");
        s.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                sActionPerformed(evt);
            }
        });
        jPanel11.add(s);
        s.setBounds(380, 130, 50, 30);
        jPanel11.add(jSeparator18);
        jSeparator18.setBounds(230, 210, 200, 20);
        jPanel11.add(jSeparator19);
        jSeparator19.setBounds(230, 60, 200, 10);

        jLabel53.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/correcto.gif"))); // NOI18N
        jPanel11.add(jLabel53);
        jLabel53.setBounds(440, 170, 48, 48);

        jLabel74.setFont(new java.awt.Font("Arial Black", 3, 18)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(255, 51, 51));
        jLabel74.setText(" VACIO");
        jPanel11.add(jLabel74);
        jLabel74.setBounds(420, 180, 90, 30);

        jLabel75.setFont(new java.awt.Font("Arial Black", 3, 18)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 51, 51));
        jLabel75.setText("VACIO");
        jPanel11.add(jLabel75);
        jLabel75.setBounds(430, 30, 90, 30);

        jLabel52.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/correcto.gif"))); // NOI18N
        jPanel11.add(jLabel52);
        jLabel52.setBounds(440, 20, 48, 48);

        jLabel55.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel55.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/borrado.gif"))); // NOI18N
        jPanel11.add(jLabel55);
        jLabel55.setBounds(440, 170, 50, 40);

        jLabel56.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel56.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/borrado.gif"))); // NOI18N
        jPanel11.add(jLabel56);
        jLabel56.setBounds(440, 20, 50, 40);

        agregar_articulo.add(jPanel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 80, 510, 360));

        ReportesProduct.setBackground(new java.awt.Color(0, 148, 204));
        ReportesProduct.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ReportesProduct.setForeground(new java.awt.Color(255, 255, 255));
        ReportesProduct.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/pdf.png"))); // NOI18N
        ReportesProduct.setText("Productos");
        ReportesProduct.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ReportesProduct.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        ReportesProduct.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ReportesProduct.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportesProductActionPerformed(evt);
            }
        });
        agregar_articulo.add(ReportesProduct, new org.netbeans.lib.awtextra.AbsoluteConstraints(910, 330, 110, 97));

        ReportesVenta.setBackground(new java.awt.Color(0, 148, 204));
        ReportesVenta.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        ReportesVenta.setForeground(new java.awt.Color(255, 255, 255));
        ReportesVenta.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/pdf.png"))); // NOI18N
        ReportesVenta.setText("Ventas");
        ReportesVenta.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ReportesVenta.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        ReportesVenta.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ReportesVenta.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ReportesVentaActionPerformed(evt);
            }
        });
        agregar_articulo.add(ReportesVenta, new org.netbeans.lib.awtextra.AbsoluteConstraints(1030, 330, 110, 97));

        jLabel59.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel59.setForeground(new java.awt.Color(255, 255, 255));
        jLabel59.setText("*Nota: No puedes agregar ningún producto a menos que ya exista 1 proveedor");
        agregar_articulo.add(jLabel59, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 60, 690, 40));

        Proveedores9.addTab("      Nuevo Producto      ", agregar_articulo);

        agregar_usuario.setBackground(new java.awt.Color(0, 51, 102));
        agregar_usuario.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        agregar_usuario.setDoubleBuffered(false);
        agregar_usuario.setLayout(null);

        tabla_usuarios.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        tabla_usuarios.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tabla_usuarios.setComponentPopupMenu(tablausuarios);
        tabla_usuarios.setSelectionBackground(new java.awt.Color(255, 255, 255));
        tabla_usuarios.setSelectionForeground(new java.awt.Color(0, 135, 204));
        tabla_usuarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tabla_usuariosMouseClicked(evt);
            }
        });
        jScrollPane4.setViewportView(tabla_usuarios);

        agregar_usuario.add(jScrollPane4);
        jScrollPane4.setBounds(20, 460, 1240, 200);

        jPanel7.setBackground(new java.awt.Color(0, 51, 102));
        jPanel7.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "  Datos a Modificar  ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel7.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user_NombreUp.setBackground(new java.awt.Color(0, 51, 102));
        user_NombreUp.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        user_NombreUp.setForeground(new java.awt.Color(255, 255, 255));
        user_NombreUp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        user_NombreUp.setText("NOMBRE");
        user_NombreUp.setBorder(null);
        user_NombreUp.setEnabled(false);
        jPanel7.add(user_NombreUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 40, 210, 30));

        user_UserUp.setBackground(new java.awt.Color(0, 51, 102));
        user_UserUp.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        user_UserUp.setForeground(new java.awt.Color(255, 255, 255));
        user_UserUp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        user_UserUp.setText("USUARIO");
        user_UserUp.setBorder(null);
        user_UserUp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_UserUpActionPerformed(evt);
            }
        });
        jPanel7.add(user_UserUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 90, 210, 30));

        user_ContraUp.setBackground(new java.awt.Color(0, 51, 102));
        user_ContraUp.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        user_ContraUp.setForeground(new java.awt.Color(255, 255, 255));
        user_ContraUp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        user_ContraUp.setText("CONTRASEÑA");
        user_ContraUp.setBorder(null);
        jPanel7.add(user_ContraUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 140, 210, 30));

        user_EmailUp.setBackground(new java.awt.Color(0, 51, 102));
        user_EmailUp.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        user_EmailUp.setForeground(new java.awt.Color(255, 255, 255));
        user_EmailUp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        user_EmailUp.setText("EMAIL");
        user_EmailUp.setBorder(null);
        jPanel7.add(user_EmailUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 190, 210, 30));

        user_TelefonoUp.setBackground(new java.awt.Color(0, 51, 102));
        user_TelefonoUp.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        user_TelefonoUp.setForeground(new java.awt.Color(255, 255, 255));
        user_TelefonoUp.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        user_TelefonoUp.setText("TELEFONO");
        user_TelefonoUp.setBorder(null);
        jPanel7.add(user_TelefonoUp, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 240, 210, 30));
        jPanel7.add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 270, 210, 10));
        jPanel7.add(jSeparator3, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 70, 210, 10));
        jPanel7.add(jSeparator4, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 120, 210, 10));
        jPanel7.add(jSeparator5, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 170, 210, 10));
        jPanel7.add(jSeparator6, new org.netbeans.lib.awtextra.AbsoluteConstraints(160, 220, 210, 10));

        jLabel11.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("  Telefono:");
        jPanel7.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, 100, 30));

        jLabel25.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel25.setForeground(new java.awt.Color(255, 255, 255));
        jLabel25.setText("  Nombre:");
        jPanel7.add(jLabel25, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 40, 100, 30));

        jLabel26.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel26.setForeground(new java.awt.Color(255, 255, 255));
        jLabel26.setText("  Usuario:");
        jPanel7.add(jLabel26, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 100, 30));

        jLabel27.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel27.setForeground(new java.awt.Color(255, 255, 255));
        jLabel27.setText("  Contraseña:");
        jPanel7.add(jLabel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 140, 120, 30));

        jLabel54.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel54.setForeground(new java.awt.Color(255, 255, 255));
        jLabel54.setText("  Email:");
        jPanel7.add(jLabel54, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 190, 100, 30));

        agregar_usuario.add(jPanel7);
        jPanel7.setBounds(40, 120, 400, 300);
        jPanel7.getAccessibleContext().setAccessibleName("Registro ");

        jPanel9.setBackground(new java.awt.Color(0, 51, 102));
        jPanel9.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Reportes   ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel9.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Reporte_user.setBackground(new java.awt.Color(0, 148, 204));
        Reporte_user.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Reporte_user.setForeground(new java.awt.Color(255, 255, 255));
        Reporte_user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/pdf.png"))); // NOI18N
        Reporte_user.setText("Usuarios");
        Reporte_user.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Reporte_user.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Reporte_user.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Reporte_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Reporte_userActionPerformed(evt);
            }
        });
        jPanel9.add(Reporte_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 40, 100, 97));

        Reporte_user1.setBackground(new java.awt.Color(0, 148, 204));
        Reporte_user1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Reporte_user1.setForeground(new java.awt.Color(255, 255, 255));
        Reporte_user1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/verificacion-de-la-lista-de-entrega-simbolo-de-portapapeles.png"))); // NOI18N
        Reporte_user1.setText("Detalles de Ventas");
        Reporte_user1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Reporte_user1.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        Reporte_user1.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Reporte_user1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Reporte_user1ActionPerformed(evt);
            }
        });
        jPanel9.add(Reporte_user1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 150, 160, 100));

        agregar_usuario.add(jPanel9);
        jPanel9.setBounds(860, 120, 300, 300);

        jPanel8.setBackground(new java.awt.Color(0, 51, 102));
        jPanel8.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Usuarios   ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 14), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel8.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        agregar3.setBackground(new java.awt.Color(0, 148, 204));
        agregar3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        agregar3.setForeground(new java.awt.Color(255, 255, 255));
        agregar3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/trabajo-en-equipo.png"))); // NOI18N
        agregar3.setText("Nuevo Usuario");
        agregar3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        agregar3.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        agregar3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        agregar3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregar3ActionPerformed(evt);
            }
        });
        jPanel8.add(agregar3, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 40, 145, 100));

        update_users.setBackground(new java.awt.Color(0, 148, 204));
        update_users.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        update_users.setForeground(new java.awt.Color(255, 255, 255));
        update_users.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/actualizar.png"))); // NOI18N
        update_users.setText("Actualizar");
        update_users.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        update_users.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        update_users.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        update_users.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                update_usersActionPerformed(evt);
            }
        });
        jPanel8.add(update_users, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 170, 100, 97));

        agregar_usuario.add(jPanel8);
        jPanel8.setBounds(500, 120, 300, 300);

        jPanel5.setBackground(new java.awt.Color(255, 255, 255));

        jLabel13.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel13.setText("Agregar Nuevo Usuario ");

        jLabel14.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/Usuario02.png"))); // NOI18N

        jButton4.setBackground(new java.awt.Color(0, 51, 102));
        jButton4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton4.setForeground(new java.awt.Color(255, 0, 0));
        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/serrar.png"))); // NOI18N
        jButton4.setText("Salir");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jLabel58.setBackground(new java.awt.Color(0, 160, 204));
        jLabel58.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel58.setForeground(new java.awt.Color(255, 255, 255));
        jLabel58.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/bloggif_5bd54d091a235.jpeg"))); // NOI18N

        javax.swing.GroupLayout jPanel5Layout = new javax.swing.GroupLayout(jPanel5);
        jPanel5.setLayout(jPanel5Layout);
        jPanel5Layout.setHorizontalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel5Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel58)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(393, 393, 393)
                .addComponent(jButton4)
                .addGap(41, 41, 41))
        );
        jPanel5Layout.setVerticalGroup(
            jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel5Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel58)
                    .addGroup(jPanel5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel5Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4))
                        .addComponent(jLabel13, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        agregar_usuario.add(jPanel5);
        jPanel5.setBounds(0, 0, 1290, 60);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1288, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(agregar_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 1288, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 674, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel1Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(agregar_usuario, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        Proveedores9.addTab("      Usuarios      ", jPanel1);

        Administrador.setBackground(new java.awt.Color(0, 51, 102));
        Administrador.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Administrador.setDoubleBuffered(false);
        Administrador.setLayout(null);

        jPanel23.setBackground(new java.awt.Color(0, 51, 102));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Ventas del Dia Realizadas   ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Jtable_ventasRealizadas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Jtable_ventasRealizadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id_venta", "nombre_producto", "cantidad", "importe", "total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Jtable_ventasRealizadas.setSelectionBackground(new java.awt.Color(255, 255, 255));
        Jtable_ventasRealizadas.setSelectionForeground(new java.awt.Color(0, 135, 204));
        Jtable_ventasRealizadas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Jtable_ventasRealizadasMouseClicked(evt);
            }
        });
        jScrollPane7.setViewportView(Jtable_ventasRealizadas);

        jPanel23.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 80, 1240, 200));

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("Total de Ventas Realizadas:");
        jPanel23.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, 270, 50));

        jTextField1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField1ActionPerformed(evt);
            }
        });
        jPanel23.add(jTextField1, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 30, 100, 30));

        Ventasfortoday1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        Ventasfortoday1.setForeground(new java.awt.Color(255, 255, 255));
        Ventasfortoday1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Ventasfortoday1.setText("00.00");
        jPanel23.add(Ventasfortoday1, new org.netbeans.lib.awtextra.AbsoluteConstraints(250, 20, 120, 50));

        VentasTotal.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        VentasTotal.setForeground(new java.awt.Color(255, 255, 255));
        VentasTotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        VentasTotal.setText("00");
        jPanel23.add(VentasTotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 20, 120, 50));

        jLabel90.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel90.setForeground(new java.awt.Color(255, 255, 255));
        jLabel90.setText("Total de Ventas del Dia : $");
        jPanel23.add(jLabel90, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 20, 280, 50));

        Administrador.add(jPanel23);
        jPanel23.setBounds(0, 70, 1260, 290);

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel93.setText("                Ventas");

        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel94.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/total.png"))); // NOI18N

        jButton8.setBackground(new java.awt.Color(0, 51, 102));
        jButton8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 0, 0));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/serrar.png"))); // NOI18N
        jButton8.setText("Salir");
        jButton8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton8ActionPerformed(evt);
            }
        });

        jLabel95.setBackground(new java.awt.Color(0, 160, 204));
        jLabel95.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel95.setForeground(new java.awt.Color(255, 255, 255));
        jLabel95.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/bloggif_5bd54d091a235.jpeg"))); // NOI18N

        javax.swing.GroupLayout jPanel24Layout = new javax.swing.GroupLayout(jPanel24);
        jPanel24.setLayout(jPanel24Layout);
        jPanel24Layout.setHorizontalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel24Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel95)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jLabel93, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel94, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(405, 405, 405)
                .addComponent(jButton8)
                .addGap(41, 41, 41))
        );
        jPanel24Layout.setVerticalGroup(
            jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel24Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel95)
                    .addGroup(jPanel24Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel94, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel24Layout.createSequentialGroup()
                            .addGap(6, 6, 6)
                            .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGap(4, 4, 4))
                        .addComponent(jLabel93, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
        );

        Administrador.add(jPanel24);
        jPanel24.setBounds(0, 0, 1290, 60);

        jPanel26.setBackground(new java.awt.Color(0, 51, 102));
        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Ventas del Dia Canceladas   ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Jtable_ventasCanceladas.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        Jtable_ventasCanceladas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "id_venta", "nombre_producto", "cantidad", "importe", "total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Jtable_ventasCanceladas.setSelectionBackground(new java.awt.Color(255, 255, 255));
        Jtable_ventasCanceladas.setSelectionForeground(new java.awt.Color(0, 135, 204));
        Jtable_ventasCanceladas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Jtable_ventasCanceladasMouseClicked(evt);
            }
        });
        jScrollPane9.setViewportView(Jtable_ventasCanceladas);

        jPanel26.add(jScrollPane9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 80, 1240, 200));

        jLabel62.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel62.setForeground(new java.awt.Color(255, 0, 0));
        jLabel62.setText("Total de Ventas Cancelados :");
        jPanel26.add(jLabel62, new org.netbeans.lib.awtextra.AbsoluteConstraints(870, 20, 290, 50));

        VentasTotalCance.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        VentasTotalCance.setForeground(new java.awt.Color(255, 0, 0));
        VentasTotalCance.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        VentasTotalCance.setText("00");
        jPanel26.add(VentasTotalCance, new org.netbeans.lib.awtextra.AbsoluteConstraints(1110, 20, 120, 50));

        Administrador.add(jPanel26);
        jPanel26.setBounds(10, 390, 1260, 290);

        javax.swing.GroupLayout jPanel12Layout = new javax.swing.GroupLayout(jPanel12);
        jPanel12.setLayout(jPanel12Layout);
        jPanel12Layout.setHorizontalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel12Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(Administrador, javax.swing.GroupLayout.PREFERRED_SIZE, 1298, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        jPanel12Layout.setVerticalGroup(
            jPanel12Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel12Layout.createSequentialGroup()
                .addComponent(Administrador, javax.swing.GroupLayout.PREFERRED_SIZE, 686, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        Proveedores9.addTab("      Ventas      ", jPanel12);

        producto_sobrante.setBackground(new java.awt.Color(0, 51, 102));
        producto_sobrante.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        producto_sobrante.setDoubleBuffered(false);
        producto_sobrante.setLayout(null);

        jPanel20.setBackground(new java.awt.Color(255, 255, 255));

        jLabel79.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel79.setText("Agregar Nuevo Usuario ");

        jLabel88.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/portapapeles.png"))); // NOI18N

        jButton6.setBackground(new java.awt.Color(0, 51, 102));
        jButton6.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton6.setForeground(new java.awt.Color(255, 0, 0));
        jButton6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/serrar.png"))); // NOI18N
        jButton6.setText("Salir");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jLabel89.setBackground(new java.awt.Color(0, 160, 204));
        jLabel89.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel89.setForeground(new java.awt.Color(255, 255, 255));
        jLabel89.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/bloggif_5bd54d091a235.jpeg"))); // NOI18N

        javax.swing.GroupLayout jPanel20Layout = new javax.swing.GroupLayout(jPanel20);
        jPanel20.setLayout(jPanel20Layout);
        jPanel20Layout.setHorizontalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel20Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel89)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 65, Short.MAX_VALUE)
                .addComponent(jLabel79, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(414, 414, 414)
                .addComponent(jButton6)
                .addGap(41, 41, 41))
        );
        jPanel20Layout.setVerticalGroup(
            jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel20Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel79, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel20Layout.createSequentialGroup()
                        .addGroup(jPanel20Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel88, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel89)
                            .addGroup(jPanel20Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );

        producto_sobrante.add(jPanel20);
        jPanel20.setBounds(0, 0, 1290, 71);

        jPanel25.setBackground(new java.awt.Color(0, 51, 102));
        jPanel25.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "    Productos mas Vendidos   ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel25.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        fecha_inicioestadis.setBackground(new java.awt.Color(0, 153, 204));
        fecha_inicioestadis.setForeground(new java.awt.Color(0, 96, 255));
        fecha_inicioestadis.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jPanel25.add(fecha_inicioestadis, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 210, 40));

        jLabel60.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        jLabel60.setForeground(new java.awt.Color(255, 255, 255));
        jLabel60.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel60.setText(" A");
        jPanel25.add(jLabel60, new org.netbeans.lib.awtextra.AbsoluteConstraints(520, 60, 60, 40));

        fecha_finalestadis.setBackground(new java.awt.Color(0, 153, 204));
        fecha_finalestadis.setForeground(new java.awt.Color(0, 96, 255));
        fecha_finalestadis.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jPanel25.add(fecha_finalestadis, new org.netbeans.lib.awtextra.AbsoluteConstraints(590, 60, 210, 40));

        buscarproductosfecha.setBackground(new java.awt.Color(0, 148, 204));
        buscarproductosfecha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buscarproductosfecha.setForeground(new java.awt.Color(255, 255, 255));
        buscarproductosfecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/magnifier.png"))); // NOI18N
        buscarproductosfecha.setText("Buscar");
        buscarproductosfecha.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        buscarproductosfecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarproductosfechaActionPerformed(evt);
            }
        });
        jPanel25.add(buscarproductosfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 50, 180, 50));

        Jtable_productosmasven.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "nombre_producto", "cantidad", "precio_unitario", "fecha_reporte"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Jtable_productosmasven.setGrosorBordeFilas(0);
        Jtable_productosmasven.setGrosorBordeHead(0);
        Jtable_productosmasven.setMultipleSeleccion(false);
        jScrollPane5.setViewportView(Jtable_productosmasven);

        jPanel25.add(jScrollPane5, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 120, 1220, 300));

        producto_sobrante.add(jPanel25);
        jPanel25.setBounds(10, 80, 1270, 510);

        javax.swing.GroupLayout jPanel13Layout = new javax.swing.GroupLayout(jPanel13);
        jPanel13.setLayout(jPanel13Layout);
        jPanel13Layout.setHorizontalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1288, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(producto_sobrante, javax.swing.GroupLayout.PREFERRED_SIZE, 1288, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        jPanel13Layout.setVerticalGroup(
            jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 674, Short.MAX_VALUE)
            .addGroup(jPanel13Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(jPanel13Layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(producto_sobrante, javax.swing.GroupLayout.PREFERRED_SIZE, 674, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );

        Proveedores9.addTab("      Estadisticas      ", jPanel13);

        getContentPane().add(Proveedores9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 700));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void modifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyActionPerformed
        int fila =tabla_agregar.getSelectedRow();
             RetornaValorUpdateProducts();
      if(fila>=0){
             update.setEnabled(true);
          namep.setText(tabla_agregar.getValueAt(fila,1).toString());
                preciop.setText(tabla_agregar.getValueAt(fila,3).toString());
         cantp.setText(tabla_agregar.getValueAt(fila,4).toString());
   fechap.cleanup();   
      }
      else
          JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila primero","Aviso",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_modifyActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
      
          //eliminar datos 
        int fila = tabla_agregar.getSelectedRow();
           if(fila>=0){
              try{
            String sql = "DELETE from productos where id_producto=" +tabla_agregar.getValueAt(fila, 0);
            sent = ca.createStatement();
            int n = sent.executeUpdate(sql);
            if(n>0){
                JOptionPane.showMessageDialog(null,"Datos eliminados");
                mostrartablaarticulos();
                autocompletar();
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage());
        }  
            }
           else
                JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila primero","Aviso",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_eliminarActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed
   
        int fila =proveedores.getSelectedRow();
               
   RetornaValorUpdate();  
   if(fila>=0){    
actualizarpro.setEnabled(false);
        proem.setText(proveedores.getValueAt(fila,1).toString());
        proname.setText(proveedores.getValueAt(fila,2).toString());
        propater.setText(proveedores.getValueAt(fila,3).toString());
        promater.setText(proveedores.getValueAt(fila,4).toString());
        promail.setText(proveedores.getValueAt(fila,5).toString());
        prorfc.setText(proveedores.getValueAt(fila,6).toString());
        protel.setText(proveedores.getValueAt(fila,7).toString());
         actualizarpro.setEnabled(true); 
         
         try{
                            sent  =(Statement)ca.createStatement();
                            rs = sent.executeQuery("select * from categoria_producto where id_categoria='"+proveedores.getValueAt(fila,0).toString()+"'");
                            while(rs.next()){
                                tipopro.setText(rs.getString("tipo_producto"));
                 despro.setText(rs.getString("descripcion"));
                            }

                        }catch (Exception f){
                            JOptionPane.showMessageDialog(null, "Error en inventario" + f.getMessage());
                        }
         
         
                
   }
   else
          JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila primero","Aviso",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_modificarActionPerformed

    private void dropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropActionPerformed
    //eliminar datos 
        int fila = proveedores.getSelectedRow();
           if(fila>=0){
              try{
            String sql = "DELETE from proveedores where id_proveedor=" +proveedores.getValueAt(fila, 0);
            String sql1 = "DELETE from categoria_producto  where id_categoria=" +proveedores.getValueAt(fila, 0);
            
            sent = ca.createStatement();  
            int n = sent.executeUpdate(sql); 
            int n1 = sent.executeUpdate(sql1);

            if(n>0){
                JOptionPane.showMessageDialog(null,"Datos eliminados");
                mostrartablaproveedores();
                llenarcomboarticulos();
                  llenarcombotipoproducto();
            }

        }catch(Exception e){
            int respuesta =JOptionPane.showConfirmDialog(null, "Si desea eliminar éste proveedor, también se eliminarán los productos que se la han asignado anteriormente \n                                                                 ¿Está de acuerdo?" ,"                                                                                                Advertencia",JOptionPane.YES_NO_OPTION);
           
            if(respuesta==0){
               if(fila>=0){
                   try{
                         String sql = "DELETE from proveedores where id_proveedor=" +proveedores.getValueAt(fila, 0);
                        String sql1 = "DELETE from categoria_producto  where id_categoria=" +proveedores.getValueAt(fila, 0);
                         String sql2 = "DELETE from productos  where id_proveedor=" +proveedores.getValueAt(fila, 0);
            
            sent = ca.createStatement();  
            int n2  = sent.executeUpdate(sql2), n1 = sent.executeUpdate(sql1), n = sent.executeUpdate(sql);

            if(n>0&&n1>0&&n2>0){
                JOptionPane.showMessageDialog(null,"                       Provedor y articulos eliminados");
                mostrartablaproveedores();
                llenarcomboarticulos();
                  llenarcombotipoproducto();
                  mostrartablaarticulos();
            }
                   }catch(Exception a){
                       JOptionPane.showConfirmDialog(null,"Error"+a.getMessage());
                   }
               }
          }
        }  
            }
           else
                JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila primero","Aviso",JOptionPane.INFORMATION_MESSAGE);
        
    }//GEN-LAST:event_dropActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
      cerrandosesion();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void update_usersActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_update_usersActionPerformed
                                   //  MODIFICAR  DATOS DE LOS     ------- >>>>>>>>>>>>>>>>>>>>>>>>>>>>
   //codigo para actualizar  TABLA DE usuarios
        if(user_UserUp.getText().isEmpty()||user_ContraUp.getText().isEmpty()||user_EmailUp.getText().isEmpty()||user_TelefonoUp.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Llene todos los campos de texto antes de guardar cambios","                              AVISO",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            try{
                int fila =tabla_usuarios.getSelectedRow();
                
                PreparedStatement ps = ca.prepareStatement ("UPDATE user SET nombre_usuario='"+user_UserUp.getText()+"',contraseña='"+user_ContraUp.getText()+"',email='"+user_EmailUp.getText()+"',Telefono='"+user_TelefonoUp.getText()+"'WHERE id_usuario='"+tabla_usuarios.getValueAt(fila,0).toString()+"'");
                ps.executeUpdate();
              JOptionPane.showMessageDialog(null,"Datos modificados");
                restaurarmodificacionesregistrousuario();            
                update_users.setEnabled(false);               
                TablaDatosUsuarios();
              //limpiardatosarticulos();
                //RetornaValorProducts();
               // OcultosProductos();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
            }
        
        }

    }//GEN-LAST:event_update_usersActionPerformed

    private void rActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_rActionPerformed

        if(!  cantp.getText().equals("0")&&!  cantp.getText().isEmpty()){
            resta =  Integer.parseInt(  cantp.getText())  ;
            resta-=1;
            cantp.setText(Integer.toString(resta));
        }
    }//GEN-LAST:event_rActionPerformed

    private void sActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_sActionPerformed
        if(  cantp.getText().isEmpty()         ){
            suma =   1 ;
            cantp.setText(Integer.toString(suma));
        }
        else{
            suma =  Integer.parseInt(  cantp.getText())  ;
            suma+=1;
            cantp.setText(Integer.toString(suma));
        }
    }//GEN-LAST:event_sActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        cerrandosesion();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void updateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_updateActionPerformed
        //codigo para actualizar  TABLA DE PRODUCTOS
        obtener_id_del_proveedor(proveedorarticulo.getSelectedItem().toString());
        if(namep.getText().isEmpty()||tipodeproducto.getSelectedItem().equals("")||cantp.getText().isEmpty()||cantp.getText().equals("0")||preciop.getText().isEmpty()||fechap.getDate()==null||proveedorarticulo.getSelectedItem().equals("")){
            JOptionPane.showMessageDialog(null,"Llene todos los campos de texto antes de guardar cambios","                              AVISO",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            try{
                int fila =tabla_agregar.getSelectedRow();

                PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET tipo_producto='"+tipodeproducto.getSelectedItem().toString()+"',precio='"+preciop.getText()+"',cantidad='"+cantp.getText()+"',fecha_de_caducidad='"+llenarfecha()+"',id_proveedor='"+id_proveedor+"',nombre_producto='"+namep.getText()+"'WHERE id_producto='"+tabla_agregar.getValueAt(fila,0).toString()+"'");
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Datos modificados");
                autocompletar();
                mostrartablaarticulos();
                update.setEnabled(false);  
                limpiardatosarticulos();
                RetornaValorProducts();
                OcultosProductos();
            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
            }
        }
    }//GEN-LAST:event_updateActionPerformed

    private void mostrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_mostrarActionPerformed
        mostrartablaarticulos();
    }//GEN-LAST:event_mostrarActionPerformed
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
    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        //codigo para agregar un nuevo articulo

        if(namep.getText().isEmpty()||tipodeproducto.getSelectedItem().equals("")||cantp.getText().isEmpty()||cantp.getText().equals("0")||preciop.getText().isEmpty()||proveedorarticulo.getSelectedItem().equals("")||fechap.getDate()==null){
            JOptionPane.showMessageDialog(null,"Debe de llenar todos los campos de texto antes de guardar un nuevo articulo","                    AVISO",JOptionPane.INFORMATION_MESSAGE);
        }
        // String a= new String (proveedorarticulo.getSelectedIndex());
        else{
            //  String proveedor=Integer.parseInt(proveedorarticulo.getSelectedIndex());
            //proveedorarticulo.getSelectedItem();
            try{
                String sql = "INSERT INTO productos(nombre_producto,tipo_producto,precio,cantidad,fecha_de_caducidad,id_proveedor, fecha)  VALUES (?,?,?,?,?,?,?)";
                PreparedStatement pst = ca.prepareCall(sql);
                pst.setString(1,namep.getText());
                pst.setString(2,tipodeproducto.getSelectedItem().toString());
                pst.setString(3,preciop.getText());
                pst.setString(4,cantp.getText());
                pst.setString(5,llenarfecha());
                obtener_id_del_proveedor(proveedorarticulo.getSelectedItem().toString());
                 pst.setInt(6,id_proveedor);
                pst.setString(7,fecha());
                
                int a=pst.executeUpdate();
                if(a>0){
                    JOptionPane.showMessageDialog(null,"Datos guardados correctamente");
                    limpiardatosarticulos();
                    mostrartablaarticulos(); 
                    autocompletar();
                    RetornaValorProducts();  
                    OcultosProductos();
                }
            } catch(SQLException e)  {
                JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
            }
        }
    }//GEN-LAST:event_agregarActionPerformed

    private void desproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_desproActionPerformed
    }//GEN-LAST:event_desproActionPerformed
    private void tipoproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_tipoproActionPerformed
    }//GEN-LAST:event_tipoproActionPerformed
    private void protelActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_protelActionPerformed
    }//GEN-LAST:event_protelActionPerformed
    private void prorfcActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_prorfcActionPerformed
    }//GEN-LAST:event_prorfcActionPerformed
    private void promailActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_promailActionPerformed
    }//GEN-LAST:event_promailActionPerformed
    private void promaterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_promaterActionPerformed
    }//GEN-LAST:event_promaterActionPerformed
    private void propaterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_propaterActionPerformed
    }//GEN-LAST:event_propaterActionPerformed
    private void pronameActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pronameActionPerformed
    }//GEN-LAST:event_pronameActionPerformed

    private void actualizarproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_actualizarproActionPerformed
       // agregarpro.setEnabled(false);
        
        int fila = proveedores.getSelectedRow();
        String id_categoria="";agregarpro.setEnabled(false);
        if(proem.getText().isEmpty()||proname.getText().isEmpty()||propater.getText().isEmpty()||promater.getText().isEmpty()||protel.getText().isEmpty()||prorfc.getText().isEmpty()||promail.getText().isEmpty()||tipopro.getText().isEmpty()||despro.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Llene todos los campos de texto antes de guardar cambios, 1: "+proem.getText()+"2: "+proname.getText()+"3: "+propater.getText()+"4: "+promater.getText()+"5: "+promail.getText()+" 6: "+prorfc.getText()+"7: "+protel.getText(),"                                                           AVISO",JOptionPane.INFORMATION_MESSAGE);
        } 
        else{
            try{
                 PreparedStatement ps1 = ca.prepareStatement ("UPDATE categoria_producto SET tipo_producto='"+tipopro.getText()+"', descripcion='"+despro.getText()+"'WHERE id_categoria='"+proveedores.getValueAt(fila,0).toString()+"'");
                ps1.executeUpdate();
              
             try{
                  PreparedStatement ps = ca.prepareStatement ("UPDATE proveedores SET nombre_de_la_empresa='"+proem.getText()+"',nombre_del_proveedor='"+proname.getText()+"',apellido_paterno='"+propater.getText()+"',apellido_materno='"+promater.getText()+"',email='"+promail.getText()+"',RFC='"+prorfc.getText()+"',Telefono='"+protel.getText()+"'WHERE id_proveedor='"+proveedores.getValueAt(fila,0).toString()+"'");
                ps.executeUpdate();
                JOptionPane.showMessageDialog(null,"Datos modificados correctamente: ");
                limpiardatosproveedores();
                mostrartablaproveedores();
                  RetornaValorAddProduct();    
                Ocultoetiquetas();
             }catch(Exception ex1){
                JOptionPane.showMessageDialog(null, "Error" + ex1.getMessage());
            } 
               
                actualizarpro.setEnabled(false);

            }catch(Exception ex){
                JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
            }
        }
                  
    }//GEN-LAST:event_actualizarproActionPerformed

    private void agregarpro1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarpro1ActionPerformed
        mostrartablaproveedores();
        
    }//GEN-LAST:event_agregarpro1ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
      cerrandosesion();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void agregarproActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarproActionPerformed
        //codigo para agregar un nuevo proveedor
        if(proem.getText().isEmpty()||proname.getText().isEmpty()||propater.getText().isEmpty()||promater.getText().isEmpty()||promail.getText().isEmpty()||prorfc.getText().isEmpty()||protel.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Debe de llenar todos los campos de texto antes de guardar un nuevo articulo","                    AVISO",JOptionPane.INFORMATION_MESSAGE);
        }
        else{
            //Verificando si ya hay al menos un proveedor creado
              try {
             sent = ca.createStatement();   
             rs = sent.executeQuery("select * from proveedores where  nombre_de_la_empresa='"+proem.getText()+"' and  nombre_del_proveedor='"+proname.getText()+"'and apellido_paterno='"+propater.getText()+"'and  apellido_materno='"+promater.getText()+"'        ");
            while(rs.next()){        
            resultadoprimerproveedor=Integer.parseInt(rs.getString(1)); //Obtiene el id del proveedor
            } 
              if(resultadoprimerproveedor!=0){ //si el id resultante de la consulta es diferente de 0 quiere decir que ya hay por lo menos una venta en el sistema
            JOptionPane.showMessageDialog(null,"Proveedor ya registrado");
           // resultadoprimerproveedor=0;
              }
              else{
                  try{
                String sql = "INSERT INTO proveedores(nombre_de_la_empresa,nombre_del_proveedor,apellido_paterno,apellido_materno,email,RFC,Telefono)  VALUES (?,?,?,?,?,?,?)";
                PreparedStatement pst = ca.prepareCall(sql);
                pst.setString(1,proem.getText());
                pst.setString(2,proname.getText());
                pst.setString(3,propater.getText());
                pst.setString(4,promater.getText());
                pst.setString(5,promail.getText());
                pst.setString(6,prorfc.getText());
                pst.setString(7,protel.getText());

                String sql1 = "INSERT INTO categoria_producto(tipo_producto,descripcion)  VALUES (?,?)";
                PreparedStatement pst1 = ca.prepareCall(sql1);
                pst1.setString(1,tipopro.getText());
                pst1.setString(2,despro.getText());

                int a=pst.executeUpdate(),b=pst1.executeUpdate();
                if(a>0&&b>0){
                                       JOptionPane.showMessageDialog(null,"Datos guardados correctamente");
                    limpiardatosproveedores();
                    mostrartablaproveedores();
                    llenarcomboarticulos();  
                    llenarcombotipoproducto();
                    RetornaValorAddProduct(); 
                   Ocultoetiquetas();
                }
            } catch(SQLException e)  {
                JOptionPane.showMessageDialog(null, "Entro al error" + e.getMessage());
            }
              }
        } 
             catch (SQLException ex) {
           // Logger.getLogger(menu_principal.class.getName()).log(Level.SEVERE, null, ex);
            try{
                String sql = "INSERT INTO proveedores(nombre_de_la_empresa,nombre_del_proveedor,apellido_paterno,apellido_materno,email,RFC,Telefono)  VALUES (?,?,?,?,?,?,?)";
                PreparedStatement pst = ca.prepareCall(sql);
                pst.setString(1,proem.getText());
                pst.setString(2,proname.getText());
                pst.setString(3,propater.getText());
                pst.setString(4,promater.getText());
                pst.setString(5,promail.getText());
                pst.setString(6,prorfc.getText());
                pst.setString(7,protel.getText());

                String sql1 = "INSERT INTO categoria_producto(tipo_producto,descripcion)  VALUES (?,?)";
                PreparedStatement pst1 = ca.prepareCall(sql1);
                pst1.setString(1,tipopro.getText());
                pst1.setString(2,despro.getText());

                int a=pst.executeUpdate(),b=pst1.executeUpdate();
                if(a>0&&b>0){
                                      JOptionPane.showMessageDialog(null,"Datos guardados correctamente");
                    limpiardatosproveedores();
                    mostrartablaproveedores();
                    llenarcomboarticulos();  
                    llenarcombotipoproducto();
                    RetornaValorAddProduct(); 
                   Ocultoetiquetas();
                }
            } catch(SQLException e)  {
                JOptionPane.showMessageDialog(null, "Entro al error" + e.getMessage());
            }
            
            
        }
            //FIN DE LA COMPROBACIÓN DEL PRIMER PROVEEDOR
        }        // TODO add your handling code here:
    }//GEN-LAST:event_agregarproActionPerformed

    private void proemActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proemActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_proemActionPerformed

    private void pagocomboboxActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagocomboboxActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_pagocomboboxActionPerformed

    private void venta_listaKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_venta_listaKeyPressed
        if(!total.getText().isEmpty()&&!pagocombobox.getText().isEmpty()&&!cantidad.getText().isEmpty()&&!searchforproducts.getSelectedItem().equals("")){
            cambiocombobox.setText(String.valueOf(cambio=Float.parseFloat(pagocombobox.getText())-totalf));
            block_unlock=true;
            try{// el id del usuario
                id_max_de_venta();
                PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET total='"+totalf+"',pago='"+pagocombobox.getText()+"',cambio='"+cambiocombobox.getText()+"'WHERE id_venta='"+id_de_la_venta_incrementable+"'");
                ps.executeUpdate();
            }//fin del id del usuario
            catch(Exception w){
                JOptionPane.showMessageDialog(null,"error en id usuario"+w);
            }//fin del id del usuario
        }
    }//GEN-LAST:event_venta_listaKeyPressed

    private void venta_listaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_venta_listaActionPerformed
         /*   ********************  BOTON DE COBRAR LA VENTA ****************  */
             /*   ******************  BOTON DE COBRAR LA VENTA **************  */
           try{ 
       float variabletotal = Float.parseFloat(total.getText());
            float variablepago = Float.parseFloat(pagocombobox.getText());
            float variablepagocondescuento =  Float.parseFloat(totalcondescuento.getText());
            
        if(!total.getText().isEmpty()&&!pagocombobox.getText().isEmpty()){
           if(descuentoactivo==true){ //CUANDO EL DESCUENTO ESTÁ ACTIVO
               
               if(variablepago<variablepagocondescuento){ // comprueba que la cantidad recibida sea mayor al total
                    JOptionPane.showMessageDialog(null,"El pago es menor a la cantidad a pagar, por favor, verifique","Advertencia",JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                tablaventaactiva=false;
            cambiocombobox.setText(String.valueOf(cambio=Float.parseFloat(pagocombobox.getText())-variablepagocondescuento));
            block_unlock=true;
            try{// el id del usuario
                id_max_de_venta();
                if(descuentoactivo==true){
                    PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET total='"+Float.parseFloat(totalcondescuento.getText())+"',porcentajedescontado='"+porcentaje+"',descuento='"+ Float.parseFloat(descuentocombo.getText())+"',pago='"+pagocombobox.getText()+"',cambio='"+cambiocombobox.getText()+"'WHERE id_venta='"+id_de_la_venta_incrementable+"'");
                ps.executeUpdate();
                }
                else{
                    PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET total='"+totalf+"',porcentajedescontado='"+variablede0+"',descuento='"+ variablede0+"',pago='"+pagocombobox.getText()+"',cambio='"+cambiocombobox.getText()+"'WHERE id_venta='"+id_de_la_venta_incrementable+"'");
               ps.executeUpdate();
                }
                    
                
                // SEPARACION DE LA FECHA Y HORA DEL TIMESTAMP

                 try{ 
              sent  =(Statement)ca.createStatement();
                                           rs = sent.executeQuery("select * from venta where id_venta= '"+id_de_la_venta_incrementable+"'");
                                            while(rs.next()){
                                                      fechayhora =rs.getString("fecha_y_hora");
                                                      }
                                            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = dt.parse(fechayhora);

        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        ///System.out.println(dt1.format(date));   ---------  dt1.format(date) es donde se almacena solo la fecha
                                            fechasinhora=dt1.format(date);
                 }//fin del try-fechasin
                                                      catch (Exception e){
                                                      }// fin del fechasin
                

                // FIN DE LA SEPARACION DE FECHA Y HORA DEL TIMESTAMP
                
                //INSERTANDO SOLO LA FECHA EN LA TABLA VENTA
                
                 try{
                            PreparedStatement ps1 = ca.prepareStatement ("UPDATE venta SET fecha_reporte='"+fechasinhora+"'WHERE id_venta='"+id_de_la_venta_incrementable+"'");
                            ps1.executeUpdate();
                        }catch(Exception s){
JOptionPane.showMessageDialog(null, "Error en venta" + s.getMessage());
                        }
                
                //FIN DE INSERTAR LA FECHA EN LA TABLA VENTA
         //ACTUALIZACION EN LA TABLA DESCRIPCION DE VENTA A REALIZADA
  
       id_max_de_venta();
        try{
            id_max_de_venta();
                PreparedStatement ps2 = ca.prepareStatement ("UPDATE descripcion_de_venta SET estado= '"+estadorealizado+"' WHERE id_venta='"+id_de_la_venta_incrementable+"'");
                ps2.executeUpdate();
       
        }
        catch(Exception ex){
                           JOptionPane.showMessageDialog(null, "Error en venta" + ex.getMessage());
        }
   
          //ACTUALIZACION EN LA TABLA DESCRIPCION DE VENTA A REALIZADA
  //evaluartablapiezaspararestar1pollo();
  descuentodepollo();
                get_id_usuario();
                            block_unlock=true;
                                                JOptionPane.showMessageDialog(null,"Venta realizada con descuento");
descuentoactivo=false;
autocompletar();
            }//fin del id del usuario
            catch(Exception w){
                JOptionPane.showMessageDialog(null,"error en id usuario"+w);
            }
                   limpiardatosdeventa(); //Los datos que aparecen en la venta se mostraran

            }
               
           } //FIN DE CUANDO EL DESCUENTO ESTÁ ACTIVO
           
           else{ //CUANDO EL DESCUENTO NO ESTÁ ACTIVO
               
               if(variablepago<variabletotal){ // comprueba que la cantidad recibida sea mayor al total
                    JOptionPane.showMessageDialog(null,"El pago es menor a la cantidad a pagar, por favor, verifique","Advertencia",JOptionPane.INFORMATION_MESSAGE);
            }
            else {
                tablaventaactiva=false;
            cambiocombobox.setText(String.valueOf(cambio=Float.parseFloat(pagocombobox.getText())-totalf));
            block_unlock=true;
            try{// el id del usuario
                id_max_de_venta();
                if(descuentoactivo==true){
                    PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET total='"+Float.parseFloat(totalcondescuento.getText())+"',porcentajedescontado='"+porcentaje+"',descuento='"+ Float.parseFloat(descuentocombo.getText())+"',pago='"+pagocombobox.getText()+"',cambio='"+cambiocombobox.getText()+"'WHERE id_venta='"+id_de_la_venta_incrementable+"'");
                ps.executeUpdate();
                }
                else{
                    PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET total='"+totalf+"',porcentajedescontado='"+variablede0+"',descuento='"+ variablede0+"',pago='"+pagocombobox.getText()+"',cambio='"+cambiocombobox.getText()+"'WHERE id_venta='"+id_de_la_venta_incrementable+"'");
               ps.executeUpdate();
                }
                    
                
                // SEPARACION DE LA FECHA Y HORA DEL TIMESTAMP

                 try{ 
              sent  =(Statement)ca.createStatement();
                                           rs = sent.executeQuery("select * from venta where id_venta= '"+id_de_la_venta_incrementable+"'");
                                            while(rs.next()){
                                                      fechayhora =rs.getString("fecha_y_hora");
                                                      }
                                            SimpleDateFormat dt = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        Date date = dt.parse(fechayhora);

        SimpleDateFormat dt1 = new SimpleDateFormat("yyyy-MM-dd");
        ///System.out.println(dt1.format(date));   ---------  dt1.format(date) es donde se almacena solo la fecha
                                            fechasinhora=dt1.format(date);
                 }//fin del try-fechasin
                                                      catch (Exception e){
                                                      }// fin del fechasin
                

                // FIN DE LA SEPARACION DE FECHA Y HORA DEL TIMESTAMP
                
                //INSERTANDO SOLO LA FECHA EN LA TABLA VENTA
                
                 try{
                            PreparedStatement ps1 = ca.prepareStatement ("UPDATE venta SET fecha_reporte='"+fechasinhora+"'WHERE id_venta='"+id_de_la_venta_incrementable+"'");
                            ps1.executeUpdate();
                        }catch(Exception s){
JOptionPane.showMessageDialog(null, "Error en venta" + s.getMessage());
                        }
                
                //FIN DE INSERTAR LA FECHA EN LA TABLA VENTA
         //ACTUALIZACION EN LA TABLA DESCRIPCION DE VENTA A REALIZADA
  
       id_max_de_venta();
        try{
            id_max_de_venta();
                PreparedStatement ps2 = ca.prepareStatement ("UPDATE descripcion_de_venta SET estado= '"+estadorealizado+"' WHERE id_venta='"+id_de_la_venta_incrementable+"'");
                ps2.executeUpdate();
       
        }
        catch(Exception ex){
                           JOptionPane.showMessageDialog(null, "Error en venta" + ex.getMessage());
        }
   
          //ACTUALIZACION EN LA TABLA DESCRIPCION DE VENTA A REALIZADA
 // evaluartablapiezaspararestar1pollo();
 descuentodepollo();
                get_id_usuario();
                            block_unlock=true;
                                                JOptionPane.showMessageDialog(null,"Venta realizada");
descuentoactivo=false;
 LlenarTablaventasRe(Jtable_ventasRealizadas);
autocompletar();
            }//fin del id del usuario
            catch(Exception w){
                JOptionPane.showMessageDialog(null,"error en id usuario"+w);
            }
                   limpiardatosdeventa(); //Los datos que aparecen en la venta se mostraran

            }
               
           } //FIN CUANDO EL DESCUENTO NO ESTÁ ACTIVO
            
           
        }
        else if(total.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Aún no hay nada por pagar","!Espera!",JOptionPane.INFORMATION_MESSAGE);
        }
        else if(pagocombobox.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null,"Aún no has ingresado la cantidad recibida","!Espera!",JOptionPane.INFORMATION_MESSAGE);
        }
        else if(pagocombobox.getText().isEmpty()){
                        JOptionPane.showMessageDialog(null,"Aún no hay articulos ingresados","!Espera!",JOptionPane.INFORMATION_MESSAGE);
        }
      }catch(Exception NFE){//Number format exception para cuando el usuario no ingrese ningun dato en la caja
                            JOptionPane.showMessageDialog(null,"No tiene valor la cantidad recibida","!Espera!",JOptionPane.INFORMATION_MESSAGE);
      }
    }//GEN-LAST:event_venta_listaActionPerformed

    private void agregar2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar2ActionPerformed
       
        /* ******************** BOTON DE ADD NUEVO PRODUCTO PARA SU VENTA ******************** */
      primer_ventadelsistema(); // 482 - 498   Comprueba que ya haya por lo menos un id registrado en la base o en su defecto que no lo haya
 
        if(cantidad.getText().isEmpty()||searchforproducts.getSelectedItem().equals("")&&Integer.parseInt(cantidad.getText())!=0&&Integer.parseInt(cantidad.getText())>0){ // comprobación que los datos estén vacios
            JOptionPane.showMessageDialog(null,"Debe de llenar todos los campos de texto antes de guardar un nuevo articulo","                    AVISO",JOptionPane.INFORMATION_MESSAGE);

        }
        else{ //en caso que si estuviesen llenos
            piezassuficientes();//verifica primero que haya las suficientes piezas para agregar un producto a la venta
            if(suficientespiezas==true){ // si hay piezas suficientes para agregar el articulo a la venta
                if(primerventa==0){ //indicando que aún no se crea la primer venta del sistema
              get_id_usuario();        //entonces lo que haría despues será entrar al metodo get_id_usuario, para asignar una venta al usuario que haya iniciado sesión en la maquina
              block_unlock=false;   //se desactiva la condicion que indica que ya no se agregue otro id venta ya que aún no se ha concluido la primer venta
            comprobar_registro(); // esto es para agregar los productos a la tabla de descripcion de venta y 
            // ya una vez concluida la venta el mismo metodo agregará dicho resultado total de la venta (a la tabla venta, bueno solo los resultados 
            // correspondientes como lo son; total, pago y cambio)
           }    
           else if(primerventa!=0){ //indicando que por lo menos ya hay una venta
        verificar_id_ingresadoalsistema(); //Comprueba que el usuario que acab de iniciar sesion coincida con el usuario anteriormente registrado

               comprobar_venta_resagada();//579 - 605 verifica que no haya una venta cancelada
              get_id_usuario();// 255 -280
              block_unlock=false;   
            comprobar_registro(); //608 - 691 
           }
            }
            else{//No hay piezas suficientes para agregar el articulo a la venta
                JOptionPane.showMessageDialog(null,"No tiene suficientes piezas para agregar el articulo:  "+searchforproducts.getSelectedItem()+JOptionPane.ERROR_MESSAGE);
                JOptionPane.showMessageDialog(null,"Las piezas disponibles en inventario son:  "+piezassuficientes);
            }
        }
    }//GEN-LAST:event_agregar2ActionPerformed

    private void cantidadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cantidadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_cantidadActionPerformed

    private void r1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_r1ActionPerformed
        if(!cantidad.getText().equals("0")&&!cantidad.getText().isEmpty()){
            resta =  Integer.parseInt(cantidad.getText())  ;
            resta-=1;
            cantidad.setText(Integer.toString(resta));
        }
    }//GEN-LAST:event_r1ActionPerformed

    private void s1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_s1ActionPerformed
        if(cantidad.getText().isEmpty()         ){
            suma =   1 ;
            cantidad.setText(Integer.toString(suma));
        }
        else{
            suma =  Integer.parseInt(cantidad.getText())  ;
            suma+=1;
            cantidad.setText(Integer.toString(suma));
        }
    }//GEN-LAST:event_s1ActionPerformed
//BOTON CERRAR SESION, PERO COMPRUEBA SI HAY UNA VENTA PEN PARA CANCELAR EN CASO DE SALIR
    public void cerrandosesion(){
          //salirrr              
          if(tablaventaactiva==true){
            int decision=JOptionPane.showConfirmDialog(null,"¿Desea continuar?","Advertencia: Tiene una venta inconclusa",JOptionPane.CANCEL_OPTION);
            if(decision==0){ //opción si
                regresarproductos_a_inventario(); //pone en estatus de cancelada la venta inconclusa
                status_cancelado();  //pone en estatus de cancelada la venta inconclusa y cada producto que lo compone
                get_id_usuario(); //vuelve a asiganr otro id_venta para que así no se repita con el id anterior que tuvo una venta cancelada
                block_unlock=false; //se bloquea la opcion de poder agregar otro id_usuario a la tabla de venta y así abrir una nueva venta
                limpiardatosdeventa();  //limpia en su mayoria los campos de texto que pertenezcan al apartado venta
                tablaventa.setVisible(false); //Desaparece la tabla
                JOptionPane.showMessageDialog(null,"Nos vemos pronto","Saliendo del sistema...",JOptionPane.INFORMATION_MESSAGE);
                new SI_Inicio().setVisible(true);
               this.setVisible(false);
             }
       }
       else{
        int decision=JOptionPane.showConfirmDialog(null,"¿Desea continuar?","Saliendo del sistema",JOptionPane.CANCEL_OPTION);
            if(decision==0){
                JOptionPane.showMessageDialog(null,"Nos vemos pronto","Saliendo del sistema...",JOptionPane.INFORMATION_MESSAGE);
                new SI_Inicio().setVisible(true);
               this.setVisible(false);
            }
       }
    }//BOTON CERRAR SESION, PERO COMPRUEBA SI HAY UNA VENTA PEN PARA CANCELAR EN CASO DE SALIR
    
    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
          cerrandosesion();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void agregar3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregar3ActionPerformed
        // ABRE NUEVA VENTANA PARA INGRESO DE NUEVO USUARIO
        new Registro().setVisible(true);
    }//GEN-LAST:event_agregar3ActionPerformed

    private void user_UserUpActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_UserUpActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_UserUpActionPerformed

    private void tabla_usuariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tabla_usuariosMouseClicked
         // TABLA PARA QUE MANDE LOS VALORES A LOS PARAMETROS DE CADA TEXTO DE AREA        
        
    }//GEN-LAST:event_tabla_usuariosMouseClicked

    private void Reporte_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Reporte_userActionPerformed
                            // ***********************    REPORTE DE USUARIOS    **************************
    int dialogButton = JOptionPane.YES_NO_OPTION;  
        int result = JOptionPane.showConfirmDialog(null, "¿Desea Generar Reporte De Los Usuarios?", "REPORTE GENERAL DE USUARIOS ",dialogButton);
        if(result == 0){
          try {
                Map parametro4 = new HashMap(); /* parameter1 <<-- ESTE PARAMETRO VIENE DESDE EL REPORTE SOLO SE ESTA LLAMANDO */
         parametro4.put("logo4", this.getClass().getResourceAsStream(logotipo)); 
              
            JasperReport reporte = null;
            String path = "src\\Reportes\\Usuarios.jasper";  
            
          //  reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
            reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Usuarios.jasper")); /*ASI MANDO A LLAMAR LOS REPORTES CON .jar */
 /* ========================= LLENADO DEL REPORTE  ======================  */
    //  path --> LA RUTA DEL REPORTE
    //     --> LOS PARAMETROS K SE LE PUEDE ENVIAR ALA REPORTE IN THIS CASE ES NULL y la concion-->(ca) B.D
      JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro4, ca);
      
 /* ========================= CREAR LA VISTA DEL REPORTE  ======================  */
   JasperViewer vista = new JasperViewer(jprint, false);
   
 /* ============= UN CIERRE LA VISTA DEL REPORTE CUANDO SE PRESIONE LA X de cerrar ============  */
      vista.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
 /* ==================== MOSTRAR CMO VISIBLE ESTE REPORTE  ======================  */
    vista.setVisible(true);
    vista.setTitle("REPORTE GENERAL DE USUARIOS");
        } catch (JRException ex) {
            Logger.getLogger(menu_principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
    }//GEN-LAST:event_Reporte_userActionPerformed

    private void pagocomboboxFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pagocomboboxFocusGained
        // *********************   CAJA DE TEXTO DE PAGOO *********
        if(pagocombobox.getText().trim().equals("00.00")){
            pagocombobox.setText("");
            //user_usuario.setForeground(Color.red);
        }
          pagocombobox.setForeground(Color.blue);
    }//GEN-LAST:event_pagocomboboxFocusGained

    private void pagocomboboxFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pagocomboboxFocusLost
        // *********************   CAJA DE TEXTO DE PAGOO *********
        if(pagocombobox.getText().trim().equals("")){
            pagocombobox.setText("00.00");
        }
           pagocombobox.setForeground(new Color(236, 240, 241));
    }//GEN-LAST:event_pagocomboboxFocusLost

    private void preciopKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_preciopKeyTyped
        // ******************** PRECIO DEL PRODUCTO RESTRICCION   ********************************
   // Extraer precio;   /($[0-9,]+(.[0-9]{2})?)/       ^[0-9]+([,][0-9]*)?$
        String cadena = preciop.getText();
        Pattern pat = Pattern.compile("^[0-9]+([.][0-9])?$");
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {
            jLabel53.setVisible(true);/* -->> ACTIVO CORRECTO */ 
             jLabel55.setVisible(false);/* -->>NO ACTIVO INCORRECTO */  
               jLabel74.setVisible(false);         
                  preciop.setFont(new Font("Tahoma",Font.BOLD, 17)); // CUANDO ES CORRECTI CAMBIA DE COLOR Y TAMAÑO ES EL MISMO       
                    preciop.setForeground(new Color(236, 240, 241));
        } else if (preciop.getText().isEmpty()) {        
          jLabel53.setVisible(false);       jLabel55.setVisible(false);       jLabel74.setVisible(true);  // ETIKETA DE VACIOO EN ACCION
          preciop.setFont(new Font("Arial",Font.ITALIC, 17));    preciop.setForeground(new  Color (255,0,0)); 
        } else {
             jLabel55.setVisible(true);
                 jLabel53.setVisible(false);  jLabel74.setVisible(false);
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

    private void proemFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_proemFocusGained
        // ****************+ PLACEHOLD NOMBRE DE LA EMPRESA  ****************+
      if(proem.getText().trim().equals("Micro Computer Systems")){
            proem.setText("");
            proem.setForeground(new  Color (34,167,240));
        }
    }//GEN-LAST:event_proemFocusGained
    private void proemFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_proemFocusLost
        // ****************+ PLACEHOLD NOMBRE DE LA EMPRESA  ****************+
     if(proem.getText().trim().equals("")){
            proem.setText("Micro Computer Systems");
        }
    }//GEN-LAST:event_proemFocusLost

    private void pronameFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pronameFocusGained
        // ****************+ PLACEHOLD NOMBRE DEL PROVEEDOR  ****************+
      if(proname.getText().trim().equals("Alexis")){
            proname.setText("");
            proname.setForeground(new  Color (34,167,240));  
        }
    }//GEN-LAST:event_pronameFocusGained

    private void pronameFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_pronameFocusLost
     if(proname.getText().trim().equals("")){
            proname.setText("Alexis");
        }
    }//GEN-LAST:event_pronameFocusLost

    private void propaterFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_propaterFocusLost
        if(propater.getText().trim().equals("")){
            propater.setText("Rodriguez");
        }
    }//GEN-LAST:event_propaterFocusLost

    private void propaterFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_propaterFocusGained
        // ****************+ PLACEHOLD APELLIDO PATERNO DEL PROVEEDOR  ****************+
      if(propater.getText().trim().equals("Rodriguez")){
            propater.setText("");
            propater.setForeground(new  Color (34,167,240));
        }
    }//GEN-LAST:event_propaterFocusGained

    private void promailFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_promailFocusGained
      // ****************+ PLACEHOLD  CORREO DEL PROVEEDOR  ****************+
      if(promail.getText().trim().equals("usuario@hotmail.com")){
            promail.setText("");
            promail.setForeground(new  Color (34,167,240));
        }
    }//GEN-LAST:event_promailFocusGained

    private void promailFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_promailFocusLost
      if(promail.getText().trim().equals("")){
            promail.setText("usuario@hotmail.com");
        }
    }//GEN-LAST:event_promailFocusLost

    private void promaterFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_promaterFocusGained
     // ****************+ PLACEHOLD  APELLIDO MATERNO DEL PROVEEDOR  ****************+
      if(promater.getText().trim().equals("Reyes")){
            promater.setText("");
            promater.setForeground(new  Color (34,167,240));
        }
    }//GEN-LAST:event_promaterFocusGained

    private void promaterFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_promaterFocusLost
      if(promater.getText().trim().equals("")){
            promater.setText("Reyes");
        }        
    }//GEN-LAST:event_promaterFocusLost

    private void prorfcFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_prorfcFocusGained
      // ****************+ PLACEHOLD   RFC DEL PROVEEDOR  ****************+
      if(prorfc.getText().trim().equals("COGR830816T88")){
            prorfc.setText("");
            prorfc.setForeground(new  Color (34,167,240));
        } 
    }//GEN-LAST:event_prorfcFocusGained

    private void prorfcFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_prorfcFocusLost
      if(prorfc.getText().trim().equals("")){
            prorfc.setText("COGR830816T88");
        } 
    }//GEN-LAST:event_prorfcFocusLost

    private void protelFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_protelFocusGained
     // ****************+ PLACEHOLD   TELEFONO DEL PROVEEDOR  ****************+
      if(protel.getText().trim().equals("733-117-0056")){
            protel.setText("");
            protel.setForeground(new  Color (34,167,240));
        }
    }//GEN-LAST:event_protelFocusGained

    private void protelFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_protelFocusLost
     if(protel.getText().trim().equals("")){
            protel.setText("733-117-0056");
        } 
    }//GEN-LAST:event_protelFocusLost

    private void tipoproFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tipoproFocusGained
     // ****************+ PLACEHOLD   TIPO DE PRODUCTO  ****************+
      if(tipopro.getText().trim().equals("Ejemplo: Lacteos")){
            tipopro.setText("");
            tipopro.setForeground(new  Color (34,167,240));
        }
    }//GEN-LAST:event_tipoproFocusGained

    private void tipoproFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_tipoproFocusLost
     if(tipopro.getText().trim().equals("")){
            tipopro.setText("Ejemplo: Lacteos");
        } 
    }//GEN-LAST:event_tipoproFocusLost

    private void desproFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_desproFocusGained
     // ****************+ PLACEHOLD  DESCRIPCION DE PRODUCTO  ****************+
      if(despro.getText().trim().equals("Productos de Origen Animal")){
            despro.setText("");
            despro.setForeground(new  Color (34,167,240));
        }
    }//GEN-LAST:event_desproFocusGained
    private void desproFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_desproFocusLost
    if(despro.getText().trim().equals("")){
            despro.setText("Productos de Origen Animal");
        } 
    }//GEN-LAST:event_desproFocusLost

    private void proemKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_proemKeyTyped
       // NOMBRE DE LA EMPRESA
        String cadena = proem.getText();
        Pattern pat = Pattern.compile("^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24}[\\s]*)+$");
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {
            jLabel38.setVisible(true);/* -->> ACTIVO CORRECTO */ 
             jLabel45.setVisible(false);/* -->>NO ACTIVO INCORRECTO */  
               jLabel67.setVisible(false);         
                  proem.setFont(new Font("Tahoma",Font.BOLD, 17)); // CUANDO ES CORRECTI CAMBIA DE COLOR Y TAMAÑO ES EL MISMO       
                    proem.setForeground(new Color(236, 240, 241));
        } else if (proem.getText().isEmpty()) {        
          jLabel38.setVisible(false);  
               jLabel45.setVisible(false);
                 jLabel67.setVisible(true);  // ETIKETA DE VACIOO EN ACCION
            
        } else {
             jLabel45.setVisible(true);
                 jLabel38.setVisible(false);  jLabel67.setVisible(false);
                    proem.setFont(new Font("Arial",Font.ITALIC, 17));
                       proem.setForeground(Color.red);  
        }
        // LLAMADO DEL METODO DE LAS VALIDACIONES DE LOS CAMPOS DE TEXTOS HABILITAR/DEHABILITAR EL BOTON
            if(TodoValido()){ 
               agregarpro.setEnabled(false); // BOTON ADD ACTIVO
            }else{
               agregarpro.setEnabled(true); // BOTON ADD OCULTO
        } 
    }//GEN-LAST:event_proemKeyTyped

    private void pronameKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_pronameKeyTyped
        // NOMBRE DEL PROVEEDOR
        String cadena = proname.getText();
        Pattern pat = Pattern.compile("^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24}[\\s]*)+$");
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {
            jLabel36.setVisible(true);
             jLabel44.setVisible(false);
               jLabel68.setVisible(false);         
                  proname.setFont(new Font("Tahoma",Font.BOLD, 17)); // CUANDO ES CORRECTI CAMBIA DE COLOR Y TAMAÑO ES EL MISMO       
                    proname.setForeground(new Color(236, 240, 241)); 
        } else if (proname.getText().isEmpty()) {      // vaciooo 
              jLabel36.setVisible(false);  
               jLabel44.setVisible(false);
                 jLabel68.setVisible(true);  // ETIKETA DE VACIOO EN ACCION
        } else {
             jLabel44.setVisible(true);
                 jLabel36.setVisible(false);  jLabel68.setVisible(false);
                    proname.setFont(new Font("Arial",Font.ITALIC, 17));
                       proname.setForeground(Color.red);  
        }
        // LLAMADO DEL METODO DE LAS VALIDACIONES DE LOS CAMPOS DE TEXTOS HABILITAR/DEHABILITAR EL BOTON
            if(TodoValido()){ 
               agregarpro.setEnabled(false); // BOTON ADD ACTIVO
            }else{
               agregarpro.setEnabled(true); // BOTON ADD OCULTO
        }  
    }//GEN-LAST:event_pronameKeyTyped

    private void propaterKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_propaterKeyTyped
       // APELLIDO PATERNO
        String cadena = propater.getText();
        Pattern pat = Pattern.compile("^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24})+$");
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {
            jLabel37.setVisible(true);/* -->> ACTIVO CORRECTO */ 
             jLabel50.setVisible(false);/* -->>NO ACTIVO INCORRECTO */
               jLabel73.setVisible(false);         
                  propater.setFont(new Font("Tahoma",Font.BOLD, 17)); // CUANDO ES CORRECTI CAMBIA DE COLOR Y TAMAÑO ES EL MISMO       
                    propater.setForeground(new Color(236, 240, 241));
        } else if (propater.getText().isEmpty()) {      // vaciooo 
               jLabel37.setVisible(false);  
               jLabel50.setVisible(false);
                 jLabel73.setVisible(true);
        } else {
              jLabel50.setVisible(true);
                 jLabel37.setVisible(false);  jLabel73.setVisible(false);
                    propater.setFont(new Font("Arial",Font.ITALIC, 17));
                       propater.setForeground(Color.red);  
        }
        // LLAMADO DEL METODO DE LAS VALIDACIONES DE LOS CAMPOS DE TEXTOS HABILITAR/DEHABILITAR EL BOTON
             if(TodoValido()){ 
               agregarpro.setEnabled(false); // BOTON ADD ACTIVO
            }else{
               agregarpro.setEnabled(true); // BOTON ADD OCULTO
        } 
    }//GEN-LAST:event_propaterKeyTyped

    private void promaterKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_promaterKeyTyped
       // APELLIDO MATERNO
        String cadena = promater.getText();
        Pattern pat = Pattern.compile("^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24})+$");
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {  
            jLabel39.setVisible(true);
             jLabel47.setVisible(false);
               jLabel65.setVisible(false);         
                  promater.setFont(new Font("Tahoma",Font.BOLD, 17)); // CUANDO ES CORRECTI CAMBIA DE COLOR Y TAMAÑO ES EL MISMO       
                    promater.setForeground(new Color(236, 240, 241));
        } else if (promater.getText().isEmpty()) {      // vaciooo 
              jLabel39.setVisible(false);  
               jLabel47.setVisible(false);
                 jLabel65.setVisible(true);
        } else {
            jLabel47.setVisible(true);
                 jLabel39.setVisible(false);  jLabel65.setVisible(false);
                    promater.setFont(new Font("Arial",Font.ITALIC, 17));
                       promater.setForeground(Color.red);  
        }
        // LLAMADO DEL METODO DE LAS VALIDACIONES DE LOS CAMPOS DE TEXTOS HABILITAR/DEHABILITAR EL BOTON
             if(TodoValido()){ 
               agregarpro.setEnabled(false); // BOTON ADD ACTIVO
            }else{
               agregarpro.setEnabled(true); // BOTON ADD OCULTO
        } 
    }//GEN-LAST:event_promaterKeyTyped

    private void promailKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_promailKeyTyped
      // CORREO  
        String cadena = promail.getText();
        Pattern pat = Pattern.compile("^[_A-zA-Z0-9-\\+]+(\\.[_a-za-z0-9-]+)*@" + "[a-za-z0-9-]+(\\.[a-za-z0-9]+)*(\\.[a-za-z]{2,})$");
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {
             jLabel40.setVisible(true);
             jLabel48.setVisible(false); 
               jLabel70.setVisible(false);        
                  promail.setFont(new Font("Tahoma",Font.BOLD, 17)); // CUANDO ES CORRECTI CAMBIA DE COLOR Y TAMAÑO ES EL MISMO       
                    promail.setForeground(new Color(236, 240, 241));
        } else if (promail.getText().isEmpty()) {      // vaciooo 
              jLabel40.setVisible(false);  
               jLabel48.setVisible(false);
                 jLabel70.setVisible(true);
        } else {
             jLabel48.setVisible(true);
                 jLabel40.setVisible(false);  jLabel70.setVisible(false);
                    promail.setFont(new Font("Arial",Font.ITALIC, 17));
                       promail.setForeground(Color.red); 
        }
        // LLAMADO DEL METODO DE LAS VALIDACIONES DE LOS CAMPOS DE TEXTOS HABILITAR/DEHABILITAR EL BOTON
          if(TodoValido()){ 
               agregarpro.setEnabled(false); // BOTON ADD ACTIVO
            }else{
               agregarpro.setEnabled(true); // BOTON ADD OCULTO
        }  
    }//GEN-LAST:event_promailKeyTyped

    private void prorfcKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_prorfcKeyTyped
       // RFC   example:  CUPU800825569

        String cadena = prorfc.getText();
        Pattern pat = Pattern.compile("^([A-ZÑ\\x26]{3,4}([0-9]{2})(0[1-9]|1[0-2])(0[1-9]|1[0-9]|2[0-9]|3[0-1]))([A-Z\\d]{3})?$");
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {
            jLabel41.setVisible(true);
             jLabel49.setVisible(false);
               jLabel71.setVisible(false);         
                  prorfc.setFont(new Font("Tahoma",Font.BOLD, 17)); // CUANDO ES CORRECTI CAMBIA DE COLOR Y TAMAÑO ES EL MISMO       
                    prorfc.setForeground(new Color(236, 240, 241));
        } else if (prorfc.getText().isEmpty()) {      // vaciooo 
            jLabel41.setVisible(false);
            jLabel49.setVisible(false);
            jLabel71.setVisible(true);
        } else {
            jLabel49.setVisible(true);
                 jLabel41.setVisible(false);  jLabel71.setVisible(false);
                    prorfc.setFont(new Font("Arial",Font.ITALIC, 17));
                       prorfc.setForeground(Color.red); 
        }
        // LLAMADO DEL METODO DE LAS VALIDACIONES DE LOS CAMPOS DE TEXTOS HABILITAR/DEHABILITAR EL BOTON
             if(TodoValido()){ 
               agregarpro.setEnabled(false); // BOTON ADD ACTIVO
            }else{
               agregarpro.setEnabled(true); // BOTON ADD OCULTO
        }   
    }//GEN-LAST:event_prorfcKeyTyped

    private void protelKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_protelKeyTyped
         // ***************  VALIDAR  TELEFONO DEL USUARIO  ******************
    // Pattern pat = Pattern.compile("\\d{3}\\d{3}");  PERMITE UN TELEFONO SIN LADA SOLO 7 DIJISTOS 
    String cadena = protel.getText();
        Pattern pat = Pattern.compile("\\d{3}-\\d{3}-\\d{3}");
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {
            jLabel42.setVisible(true);
             jLabel35.setVisible(false);
               jLabel72.setVisible(false);    
                  protel.setFont(new Font("Tahoma",Font.BOLD, 17)); 
                    protel.setForeground(new Color(236, 240, 241));
        } else if (protel.getText().isEmpty()) {      // vaciooo 
                jLabel42.setVisible(false);  
               jLabel35.setVisible(false);
                 jLabel72.setVisible(true);
        } else {
                 jLabel35.setVisible(true);
                 jLabel42.setVisible(false);  jLabel72.setVisible(false);
                    protel.setFont(new Font("Arial",Font.ITALIC, 17));
                       protel.setForeground(Color.red); 
        }
        // LLAMADO DEL METODO DE LAS VALIDACIONES DE LOS CAMPOS DE TEXTOS HABILITAR/DEHABILITAR EL BOTON
         if(TodoValido()){ 
               agregarpro.setEnabled(false); // BOTON ADD ACTIVO
            }else{
               agregarpro.setEnabled(true); // BOTON ADD OCULTO
        } 
    }//GEN-LAST:event_protelKeyTyped

    private void tipoproKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tipoproKeyTyped
       // TIPO DE PRODUCTO
        String cadena = tipopro.getText();
        Pattern pat = Pattern.compile("^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24}[\\s]*)+$");
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {
            jLabel43.setVisible(true);
            jLabel51.setVisible(false);
            jLabel66.setVisible(false); 
             tipopro.setFont(new Font("Tahoma",Font.BOLD, 17)); 
                    tipopro.setForeground(new Color(236, 240, 241));
        } else if (tipopro.getText().isEmpty()) {      // vaciooo 
            jLabel43.setVisible(false);
            jLabel51.setVisible(false);
            jLabel66.setVisible(true);
        } else {
            jLabel51.setVisible(true);
            jLabel43.setVisible(false);
            jLabel66.setVisible(false);
             tipopro.setFont(new Font("Arial",Font.ITALIC, 17));
                       tipopro.setForeground(Color.red); 
        }
        // LLAMADO DEL METODO DE LAS VALIDACIONES DE LOS CAMPOS DE TEXTOS HABILITAR/DEHABILITAR EL BOTON
            if(TodoValido()){ 
               agregarpro.setEnabled(false); // BOTON ADD ACTIVO
            }else{
               agregarpro.setEnabled(true); // BOTON ADD OCULTO
        } 
    }//GEN-LAST:event_tipoproKeyTyped

    private void desproKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_desproKeyTyped
       // DESCRIPCION
        String cadena = despro.getText();
        Pattern pat = Pattern.compile("^([.a-zA.-ZÁáÀàÉéÈèÍíÌìÓóÒòÚúÙùÑñüÜ\\s]{4,50})$");
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {
             jLabel34.setVisible(true);
            jLabel46.setVisible(false);
            jLabel69.setVisible(false);
             despro.setFont(new Font("Tahoma",Font.BOLD, 17)); 
                    despro.setForeground(new Color(236, 240, 241));
        } else if (despro.getText().isEmpty()) {      // vaciooo 
            jLabel34.setVisible(false);
            jLabel46.setVisible(false);
            jLabel69.setVisible(true);
        } else {
            jLabel34.setVisible(false);
            jLabel46.setVisible(true);
            jLabel69.setVisible(false);
             despro.setFont(new Font("Arial",Font.ITALIC, 17));
                       despro.setForeground(Color.red); 
        }
        // LLAMADO DEL METODO DE LAS VALIDACIONES DE LOS CAMPOS DE TEXTOS HABILITAR/DEHABILITAR EL BOTON
            if(TodoValido()){ 
               agregarpro.setEnabled(false); // BOTON ADD ACTIVO
            }else{
               agregarpro.setEnabled(true); // BOTON ADD OCULTO
        } 
    }//GEN-LAST:event_desproKeyTyped

    private void namepFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_namepFocusGained
        // ************++ NOMBRE DE PRODUCTO  **************
        if(namep.getText().trim().equals("Leche 1lt")){
            namep.setText("");
            namep.setForeground(new  Color (34,167,240));
        }
    }//GEN-LAST:event_namepFocusGained

    private void namepFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_namepFocusLost
        if(namep.getText().trim().equals("")){
            namep.setText("Leche 1lt");
        } 
    }//GEN-LAST:event_namepFocusLost

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

    private void namepKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_namepKeyTyped
         // ************** NOMBRE DEL PRODUCTO  **************  //("^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24}[0-9*]{4}[\\s])+$");
        String cadena = namep.getText(); 
        Pattern pat = Pattern.compile ("^[A-Za-z0-9\\s]+$");
        Matcher mat = pat.matcher(cadena);
        if (mat.matches()) {
               jLabel52.setVisible(true);
               jLabel56.setVisible(false);
               jLabel75.setVisible(false);         
                     namep.setFont(new Font("Tahoma",Font.BOLD, 17)); // CUANDO ES CORRECTI CAMBIA DE COLOR Y TAMAÑO ES EL MISMO       
                     namep.setForeground(new Color(236, 240, 241));
        } else if (namep.getText().isEmpty()) {        
               jLabel52.setVisible(false);     jLabel56.setVisible(false);      jLabel75.setVisible(true); 
              namep.setFont(new Font("Arial",Font.ITALIC, 17));    namep.setForeground(new  Color (255,0,0)); 
        } else{
               jLabel56.setVisible(true);      jLabel75.setVisible(false);      jLabel52.setVisible(false); 
                    namep.setFont(new Font("Arial",Font.ITALIC, 17));
                       namep.setForeground(Color.red);
        }    // LLAMADO DEL METODO DE LAS VALIDACIONES DE LOS CAMPOS DE TEXTOS HABILITAR/DEHABILITAR EL BOTON
                      if(TodoValidoProducts()){ 
                           agregar.setEnabled(false); // BOTON ADD ACTIVO
                      }else{
                           agregar.setEnabled(true); // BOTON ADD OCULTO
                      }
    }//GEN-LAST:event_namepKeyTyped
    private void namepActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_namepActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_namepActionPerformed

    private void ReportesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportesActionPerformed
        //  *******************  REPORTES  DE LOS PROVEEDORES    *******************
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Desea Generar Reporte De los Proveedores?", "REPORTE GENRAL DE PROVEEDORES ",dialogButton);
        if(result == 0){
            try {
                  Map parametro = new HashMap(); /* parameter1 <<-- ESTE PARAMETRO VIENE DESDE EL REPORTE SOLO SE ESTA LLAMANDO */
         parametro.put("logo1", this.getClass().getResourceAsStream(logotipo));
                
                JasperReport reporte = null;
                //String path = "src/Reportes/Proveedores.jasper";

              // reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                 reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Prove.jasper")); /*ASI MANDO A LLAMAR LOS REPORTES CON .jar */
                /* ========================= LLENADO DEL REPORTE  ======================  */
                //  path --> LA RUTA DEL REPORTE
                //     --> LOS PARAMETROS K SE LE PUEDE ENVIAR ALA REPORTE IN THIS CASE ES NULL y la concion-->(ca) B.D
                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, ca);

                /* ========================= CREAR LA VISTA DEL REPORTE  ======================  */
                JasperViewer vista = new JasperViewer(jprint, false);

                /* ============= UN CIERRE LA VISTA DEL REPORTE CUANDO SE PRESIONE LA X de cerrar ============  */
                vista.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                /* ==================== MOSTRAR CMO VISIBLE ESTE REPORTE  ======================  */
                vista.setVisible(true);
            } catch (JRException ex) {
                Logger.getLogger(menu_principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_ReportesActionPerformed

    private void ReportesProductActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportesProductActionPerformed
      //  *******************  REPORTES  PRODUCTOS    *******************
        int dialogButton = JOptionPane.YES_NO_OPTION;  
        int result = JOptionPane.showConfirmDialog(null, "¿Desea Generar Reporte De Productos?", "REPORTE GENERAL DE PRODUCTOS ",dialogButton);
        if(result == 0){
            try {
                   Map parametro2 = new HashMap(); /* parameter1 <<-- ESTE PARAMETRO VIENE DESDE EL REPORTE SOLO SE ESTA LLAMANDO */
         parametro2.put("logo2", this.getClass().getResourceAsStream(logotipo)); 
                
                JasperReport reporte = null;
                String path = "src\\Reportes\\Products.jasper";

           //     reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/Products.jasper")); /*ASI MANDO A LLAMAR LOS REPORTES CON .jar */
                /* ========================= LLENADO DEL REPORTE  ======================  */
                //  path --> LA RUTA DEL REPORTE
                //     --> LOS PARAMETROS K SE LE PUEDE ENVIAR ALA REPORTE IN THIS CASE ES NULL y la concion-->(ca) B.D
                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro2, ca);

                /* ========================= CREAR LA VISTA DEL REPORTE  ======================  */
                JasperViewer vista = new JasperViewer(jprint, false);

                /* ============= UN CIERRE LA VISTA DEL REPORTE CUANDO SE PRESIONE LA X de cerrar ============  */
                vista.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                /* ==================== MOSTRAR CMO VISIBLE ESTE REPORTE  ======================  */
                vista.setVisible(true);
                vista.setTitle("REPORTE GENERAL DE PRODUCTOS");
            } catch (JRException ex) {
                Logger.getLogger(menu_principal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_ReportesProductActionPerformed

    private void ReportesVentaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ReportesVentaActionPerformed
        //  *******************  REPORTES  DE VENTAS    *******************
        int dialogButton = JOptionPane.YES_NO_OPTION;  
        int result = JOptionPane.showConfirmDialog(null, "¿Generar Reporte General De Ventas?", "REPORTE GENERAL DE VENTAS ",dialogButton);
        if(result == 0){
            try {
                   Map parametro3 = new HashMap(); /* parameter1 <<-- ESTE PARAMETRO VIENE DESDE EL REPORTE SOLO SE ESTA LLAMANDO */
         parametro3.put("logo3", this.getClass().getResourceAsStream(logotipo)); 
                
                JasperReport reporte = null;
                //String path = "src\\Reportes\\ReporteVentas.jasper";

              //  reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/ReporteVentas.jasper")); /*ASI MANDO A LLAMAR LOS REPORTES CON .jar */
                /* ========================= LLENADO DEL REPORTE  ======================  */
                
               /*     Map<String, Object> data=new HashMap<String, Object>();
                    data.put("Image", this.getServletContext().getRealPath("/")+"/IconosJava/bloggif_5bd54d091a235.jpeg"); */
                
                
                //  path --> LA RUTA DEL REPORTE
                //     --> LOS PARAMETROS K SE LE PUEDE ENVIAR ALA REPORTE IN THIS CASE ES NULL y la concion-->(ca) B.D
                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro3, ca);

                /* ========================= CREAR LA VISTA DEL REPORTE  ======================  */
                JasperViewer vista = new JasperViewer(jprint, false);

                /* ============= UN CIERRE LA VISTA DEL REPORTE CUANDO SE PRESIONE LA X de cerrar ============  */
                vista.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                /* ==================== MOSTRAR CMO VISIBLE ESTE REPORTE  ======================  */
                vista.setVisible(true); 
                vista.setTitle("REPORTE GENERAL DE VENTAS");
            } catch (JRException ex) {
                Logger.getLogger(menu_principal.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }//GEN-LAST:event_ReportesVentaActionPerformed

    private void Reporte_user1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Reporte_user1ActionPerformed
        //  MOSTRAR NUEVA VENTANA, VENTANA DE LOS REPORTES 
        new Reportes().setVisible(true);
    }//GEN-LAST:event_Reporte_user1ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // ************************  BTN CANCELARR   ***********************
       if(tablaventaactiva==true){
                regresarproductos_a_inventario(); //pone en estatus de cancelada la venta inconclusa
               //y también el metodo de arriba regresa las piezas a la tabla piezas               
                  descuentodepollo();
                 status_cancelado();  //pone en estatus de cancelada la venta inconclusa y cada producto que lo compone
                get_id_usuario(); //vuelve a asiganr otro id_venta para que así no se repita con el id anterior que tuvo una venta cancelada
                block_unlock=false; //se bloquea la opcion de poder agregar otro id_usuario a la tabla de venta y así abrir una nueva venta
                limpiardatosdeventa();  //limpia en su mayoria los campos de texto que pertenezcan al apartado venta
                 LlenarTablaventasCance(Jtable_ventasCanceladas);
                tablaventa.setVisible(false); //Desaparece la tabla
       }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void modificarusuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarusuariosActionPerformed
        int fila =tabla_usuarios.getSelectedRow();
            // RetornaValorUpdateProducts();
      if(fila>=0){
             update_users.setEnabled(true);
             
          user_UserUp.setText(tabla_usuarios.getValueAt(fila,2).toString());
                user_ContraUp.setText(tabla_usuarios.getValueAt(fila,5).toString());
         user_EmailUp.setText(tabla_usuarios.getValueAt(fila,6).toString());
         user_TelefonoUp.setText(tabla_usuarios.getValueAt(fila,8).toString());
         
      }
      else
          JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila primero","Aviso",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_modificarusuariosActionPerformed

    private void eliminarusuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarusuariosActionPerformed
        //eliminar datos 
        int fila = tabla_usuarios.getSelectedRow();
           if(fila>=0){
              try{
            String sql = "update user set estado_activo_inactivo='"+estadoinactivo+"'  where id_usuario=" +tabla_usuarios.getValueAt(fila, 0);
            sent = ca.createStatement();
            int n = sent.executeUpdate(sql);
            if(n>0){
                JOptionPane.showMessageDialog(null,"Datos eliminados");
          restaurarmodificacionesregistrousuario();                      
                TablaDatosUsuarios();
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }  
            }
           else
                JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila primero","Aviso",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_eliminarusuariosActionPerformed

    private void activarusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activarusuarioActionPerformed
        // TODO add your handling code here:
        //Activando usuario 
        int fila = tabla_usuarios.getSelectedRow();
           if(fila>=0){
              try{
            String sql = "update user set estado_activo_inactivo='"+estadoactivo+"'  where id_usuario=" +tabla_usuarios.getValueAt(fila, 0);
            sent = ca.createStatement();
            int n = sent.executeUpdate(sql);
            if(n>0){
                JOptionPane.showMessageDialog(null," Usuario re - activado");
          restaurarmodificacionesregistrousuario();                      
                TablaDatosUsuarios();
            }

        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
        }  
            }
           else
                JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila primero","Aviso",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_activarusuarioActionPerformed

    private void AgregarGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarGastosActionPerformed
        // ABRE NUEVA VENTANA PARA Registro de Gastos
        new Pantalla_Gastos().setVisible(true);
    }//GEN-LAST:event_AgregarGastosActionPerformed

    private void CortedecajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CortedecajaActionPerformed
        // TODO add your handling code here:

        new Pantalla_CorteCaja().setVisible(true);
    }//GEN-LAST:event_CortedecajaActionPerformed

    private void descuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descuentoActionPerformed
     descuentos();       
    }//GEN-LAST:event_descuentoActionPerformed

    private void proveedorarticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_proveedorarticuloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_proveedorarticuloActionPerformed

    private void Jtable_ventasRealizadasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jtable_ventasRealizadasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Jtable_ventasRealizadasMouseClicked

    private void jTextField1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField1ActionPerformed
        // TOTAL DE LAS VENTAS REALIZADAS DESDE TABKE->VENTA  ==  SELECT SUM(`total`) FROM venta;

        // CONSULTA DE  VENTAS CANCELADAS  =  SELECT * FROM `descripcion_de_venta` WHERE `estado` = 'Cancelada';

        /*   VENTAS FREALIZADAS

        SELECT venta.`id_venta`, `nombre_producto`,`cantidad`,`importe`, venta.total FROM `descripcion_de_venta`
        inner join venta
        on descripcion_de_venta.`id_venta` = venta.id_venta WHERE `estado` = 'Realizada';

        */

        /* VENTAS CANECLADASSS --->   SELECT venta.`id_venta`, `nombre_producto`,`cantidad`,`importe`, venta.total FROM `descripcion_de_venta`
        inner join venta
        on descripcion_de_venta.`id_venta` = venta.id_venta WHERE `estado` = 'Cancelada'; */

    }//GEN-LAST:event_jTextField1ActionPerformed

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton8ActionPerformed

    private void Jtable_ventasCanceladasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Jtable_ventasCanceladasMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_Jtable_ventasCanceladasMouseClicked

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton6ActionPerformed

    private void buscarproductosfechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarproductosfechaActionPerformed
        // BOTON PARA LA CONSULTA DE  GASTOS
        String fechadesde= fecha_inicioestadis.toString();
        String fechahasta= fecha_finalestadis.toString();

        LlenarTablaBusquedproMasvendidosfecha(Jtable_productosmasven, llenarfechadesde(),llenarfechahasta());
    }//GEN-LAST:event_buscarproductosfechaActionPerformed

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
            java.util.logging.Logger.getLogger(menu_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(menu_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(menu_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(menu_principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new menu_principal().setVisible(true);
            }
        });
    }
    
SI cc= new SI();
 Connection ca= cc.conexion();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JPanel Administrador;
    private javax.swing.JButton AgregarGastos;
    private javax.swing.JButton Cortedecaja;
    private javax.swing.JLabel Fecha;
    private javax.swing.JLabel IblReloj;
    private rojerusan.RSTableMetro Jtable_productosmasven;
    public static javax.swing.JTable Jtable_ventasCanceladas;
    public static javax.swing.JTable Jtable_ventasRealizadas;
    public static javax.swing.JTabbedPane Proveedores9;
    private javax.swing.JLabel Reloj;
    private javax.swing.JButton Reporte_user;
    private javax.swing.JButton Reporte_user1;
    public javax.swing.JButton Reportes;
    public javax.swing.JButton ReportesProduct;
    public javax.swing.JButton ReportesVenta;
    private javax.swing.JLabel VentasTotal;
    private javax.swing.JLabel VentasTotalCance;
    private javax.swing.JLabel Ventasfortoday1;
    private javax.swing.JMenuItem activarusuario;
    public javax.swing.JButton actualizarpro;
    private javax.swing.JButton agregar;
    private javax.swing.JButton agregar2;
    private javax.swing.JButton agregar3;
    public static javax.swing.JPanel agregar_articulo;
    public static javax.swing.JPanel agregar_proveedor;
    public static javax.swing.JPanel agregar_usuario;
    private javax.swing.JButton agregarpro;
    public static javax.swing.JButton agregarpro1;
    private javax.swing.JButton buscarproductosfecha;
    private javax.swing.JLabel cambiocombobox;
    private javax.swing.JTextField cantidad;
    private javax.swing.JTextField cantp;
    private javax.swing.JButton descuento;
    private javax.swing.JLabel descuentocombo;
    private javax.swing.JLabel descuentolabel;
    public static javax.swing.JTextField despro;
    private javax.swing.JMenuItem drop;
    private javax.swing.JMenuItem eliminar;
    private javax.swing.JMenuItem eliminarusuarios;
    private com.toedter.calendar.JDateChooser fecha_finalestadis;
    private com.toedter.calendar.JDateChooser fecha_inicioestadis;
    private com.toedter.calendar.JDateChooser fechap;
    private javax.swing.Box.Filler filler1;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton8;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel21;
    private javax.swing.JLabel jLabel22;
    private javax.swing.JLabel jLabel23;
    private javax.swing.JLabel jLabel24;
    private javax.swing.JLabel jLabel25;
    private javax.swing.JLabel jLabel26;
    private javax.swing.JLabel jLabel27;
    private javax.swing.JLabel jLabel28;
    private javax.swing.JLabel jLabel29;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
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
    private javax.swing.JLabel jLabel48;
    private javax.swing.JLabel jLabel49;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel50;
    private javax.swing.JLabel jLabel51;
    private javax.swing.JLabel jLabel52;
    private javax.swing.JLabel jLabel53;
    private javax.swing.JLabel jLabel54;
    private javax.swing.JLabel jLabel55;
    private javax.swing.JLabel jLabel56;
    private javax.swing.JLabel jLabel57;
    private javax.swing.JLabel jLabel58;
    private javax.swing.JLabel jLabel59;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel60;
    private javax.swing.JLabel jLabel61;
    private javax.swing.JLabel jLabel62;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel65;
    private javax.swing.JLabel jLabel66;
    private javax.swing.JLabel jLabel67;
    private javax.swing.JLabel jLabel68;
    private javax.swing.JLabel jLabel69;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel70;
    private javax.swing.JLabel jLabel71;
    private javax.swing.JLabel jLabel72;
    private javax.swing.JLabel jLabel73;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel88;
    private javax.swing.JLabel jLabel89;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLabel jLabel90;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel11;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel13;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel20;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel25;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JPanel jPanel5;
    private javax.swing.JPanel jPanel6;
    private javax.swing.JPanel jPanel7;
    private javax.swing.JPanel jPanel8;
    private javax.swing.JPanel jPanel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane3;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JScrollPane jScrollPane5;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JScrollPane jScrollPane9;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator10;
    private javax.swing.JSeparator jSeparator11;
    private javax.swing.JSeparator jSeparator12;
    private javax.swing.JSeparator jSeparator13;
    private javax.swing.JSeparator jSeparator14;
    private javax.swing.JSeparator jSeparator15;
    private javax.swing.JSeparator jSeparator16;
    private javax.swing.JSeparator jSeparator17;
    private javax.swing.JSeparator jSeparator18;
    private javax.swing.JSeparator jSeparator19;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    private javax.swing.JSeparator jSeparator5;
    private javax.swing.JSeparator jSeparator6;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JSeparator jSeparator9;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JLabel labeldescuento;
    private javax.swing.JMenuItem modificar;
    private javax.swing.JMenuItem modificarusuarios;
    private javax.swing.JMenuItem modify;
    private javax.swing.JButton mostrar;
    public javax.swing.JTextField namep;
    public static javax.swing.JTextField pagocombobox;
    private javax.swing.JTextField preciop;
    public static javax.swing.JPanel producto_sobrante;
    public static javax.swing.JTextField proem;
    public static javax.swing.JTextField promail;
    public static javax.swing.JTextField promater;
    public static javax.swing.JTextField proname;
    public static javax.swing.JTextField propater;
    public static javax.swing.JTextField prorfc;
    public static javax.swing.JTextField protel;
    public static javax.swing.JComboBox<String> proveedorarticulo;
    public static javax.swing.JTable proveedores;
    private javax.swing.JButton r;
    private javax.swing.JButton r1;
    private javax.swing.JButton s;
    private javax.swing.JButton s1;
    public static javax.swing.JComboBox<String> searchforproducts;
    private javax.swing.JTable tabla_agregar;
    private javax.swing.JPopupMenu tabla_articulos;
    private javax.swing.JPopupMenu tabla_proveedores;
    public static javax.swing.JTable tabla_usuarios;
    private javax.swing.JPopupMenu tablausuarios;
    private javax.swing.JTable tablaventa;
    private javax.swing.JComboBox<String> tipodeproducto;
    public static javax.swing.JTextField tipopro;
    private javax.swing.JLabel total;
    private javax.swing.JLabel total1;
    private javax.swing.JLabel total2;
    private javax.swing.JLabel totalcondescuento;
    private javax.swing.JButton update;
    private javax.swing.JButton update_users;
    private javax.swing.JLabel user;
    private javax.swing.JLabel user1;
    private javax.swing.JTextField user_ContraUp;
    private javax.swing.JTextField user_EmailUp;
    private javax.swing.JTextField user_NombreUp;
    private javax.swing.JTextField user_TelefonoUp;
    private javax.swing.JTextField user_UserUp;
    public static javax.swing.JPanel venta;
    private javax.swing.JButton venta_lista;
    // End of variables declaration//GEN-END:variables
}
