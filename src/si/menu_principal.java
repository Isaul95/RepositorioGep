package si;
import java.awt.Color;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
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
import java.text.DecimalFormat;
          import java.text.SimpleDateFormat;  /* HORA DEL SISTEMA LIBRERIAS */
import java.util.ArrayList;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import ticket.ticketventacondescuento;
import ticket.ticketventa;
import ticket.ticketventacredito;
public final class menu_principal extends javax.swing.JFrame implements Runnable{
static SI cc= new SI();
  static boolean noduplicarexistencias=false, noduplicarcorte=false, noduplicargastos=false, noduplicarexternos=false;
                  private final String logotipo = "/Reportes/logo1.jpeg"; // icono de DATAMAX
   static ticketventacondescuento mandardatosaticketventacondescuento;  
   static ticketventa mandardatosticketventa;
    static ticketventacredito mandardatosticketventacredito;
    String  hora,minutos,segundos, fechadesde="",fechahasta="", fechaparaventasdesde="", fechaparaventashasta="";
    Thread hilo;
static Statement sent;  
  static ResultSet rs;      
  static float subtotalticket, totalticket, pagoticket, cambioticket, porcentajedescontadoticket, descuentoticket;
  static float utilidadfinal, utilidades, gastos, sumadetotalesdeventasdehoy, conteodeventascanceladas;
      static int  id=0, conteototaldeventas, id_ventapencredito, evaluadordepiezaspares=0, evaluadordepiezasinpares=0,  resultadoprimerproveedor, id_de_la_venta_incrementable,totalcomprobacion, primerventa, resultfirstselling, existencia;   
  static int fila, id_proveedor,id_usuario,id_producto,id_venta,aux1,aux2,variablede0=0;
   static float   totaldeventaenturno, variablepago, variablepagocondescuento, piezassuficientes, cantidadporerrordeusuario,productos, NoPcantidad=0, cantidadenventa,  cantidadenventasumada ,cantidaddesdelatablaeditable, piezasxunpollo=14, piezasdepollopares=2, piezasdepollosinpares=1, resultadodepiezaspares,resultadodepiezasinpares, minimodelaspiezasparesdepollocrudoeninventario, minimodelaspiezasinparesdepollocrudoeninventario, pollo_crudoeninventario, addpiezas, cantidadpolloenDB, porcentaje, importe,totalf=0,comprobacion,cambio,precio, NoPimporte=0,sumadeimportes, sumadeimportesparaeltotal, sumadeimportescreditopendiente,descuentocantidad, totalfinalcondescuento;
  static ArrayList storage = new ArrayList(); // para guardar los id de cada producto que se ha agregado a la tabla venta
 static String[] piezas = {"pollo crudo", "Pechuga", "Muslo","Pierna","Ala","Huacal","Cadera","Cabeza", "Molleja", "Patas"};
static String[] piezasdemedio = {"Medio pollo","Pechuga", "Muslo","Pierna","Ala","Huacal", "Molleja", "Patas"};

 static double cantidaddemedio, cantidaddecuarto, medio=0.50, cuarto=0.25;
        ArrayList idsenturno = new ArrayList();
       ArrayList cantidaddecadaidenturno = new ArrayList();
       static ArrayList nombreproductoticket = new ArrayList();
static ArrayList piezastcket = new ArrayList();
static ArrayList preciounitarioticket = new ArrayList();
static ArrayList importesticket = new ArrayList();
//DATA MAX FULL VERSION
  static boolean soypechugaenbisteck=false, voyacobrar=false, descuentoactivo=false, suficientespiezas=true, block_unlock=true,tablaventaactiva=false;
static String nombredepiezaseleccionada="";
static float cantidaddeproductos=0, cantidadparapollocrudo=0;
static boolean seagregoexterno=false;
      // String  usuarioname=SI_Inicio.text_user.getText(); //variable para obtener el nombre del usuario o administrador que ingreso al sistema
            static String nameenventa,name, pollo_crudo="pollo crudo", estadoinactivo="Inactivo", estadoactivo="Activo", NoP="",estadocancelado= "Cancelada",estadorealizado="Realizada", estadoenturno="En turno", creditopendiente="Credito-pendiente", creditopagado="Credito-pagado", fechayhora="",fechasinhora="", usuarioname=SI_Inicio.text_user.getText(); //variable para obtener el nombre del usuario o administrador que ingreso al sistema
   static DecimalFormat solodosdecimales = new DecimalFormat("#.##");
//ESO ES DESPUES DE AGREGAR PRODUCTO EXTERNO
   public menu_principal(boolean seagregoexterno){
    this.seagregoexterno=seagregoexterno;
    if(this.seagregoexterno==true){
         ParaLAVenta(JtablepaLaVenta);
    }
}    
     //CUANDO RECIBE UNA CANTIDAD POR PARTE DE LA CALCULADORA O UN MEDIO O UN CUARTO
  menu_principal(float cantidaddeproductos, String piezaseleccionada){
      this.cantidaddeproductos=cantidaddeproductos;
      this.nombredepiezaseleccionada=piezaseleccionada;
      this.cantidadparapollocrudo=cantidaddeproductos;
     agregandoaventa(nombredepiezaseleccionada, cantidaddeproductos);
  }
 //CUANDO SE VA A HACER EL PAGO
  menu_principal(float variablepago){
      this.variablepago=variablepago;
 metodo_de_cobro(this.variablepago); 
  }
    public menu_principal() {
        initComponents();
            //  this.setExtendedState(MAXIMIZED_BOTH);
        sumadeutilidades();
        sumadegastos();
        utilidadfinal=utilidades-gastos;
        ids_y_cantidades_enturno_por_error_de_usuario();
       // borrarventasenestadoenturnoporerrordeusuario();//ESTO ES CUANDO EL USUARIO SE EQUIVOCA Y CIERRA SESION DIRECTAMENTE EN LA X
       totaldelasventasdehoy(); // PARA LA SUMA DE LOS TOTALES DE LA VENTA
                    conteodeventasrealizadasdehoy(); // CUANTAS VENTAS SE REALIZARON? 5 O 60 O XX
                ventaseneldiasumadas.setText(String.valueOf(sumadetotalesdeventasdehoy));// VIENE DEL METODO ventaseneldiaREALIZADAS()
                        conteodelasventasrealizadas.setText(String.valueOf(conteototaldeventas)); // VIENE DEL METODO totalventasxdia(); ES UN CONTEO DE VENTAS
                 llenartablaidventasconidrealizados();
                         productosvendidoseneldia();//MUESTRA LAS VENTAS REA
                  llenartablaconventasacreditopendiente();
                  TablallenadoparaEntradas(Jtable_ProductosEntradas);
                  ParaLAVenta(JtablepaLaVenta);
        setIconImage(getIconImage());  //La variable que le manda la imagen (DataMax) al proyecto 
        quienentroalsistema();//Dependiendo quien entre al sistema serán las opciones que se le activarán
     Fecha.setText(fecha()); // SE OBTIENE LA FECHA DEL SISTEMA PARA MOSTAR EN PANTALLA
     hilo=new Thread(this);
     hilo.start();
        setVisible(true);  // SE OBTIENE LA HORA DEL SISTEMA PARA MOSTAR EN PANTALLA
//        autocompletar(); //metodo autocompletar que sirve para cuando el usuario escriba un articulo y encuentre coincidencias en la base de datos
this.setLocationRelativeTo(null); // esto elimina los botones de cerrar, minimizar y maximizar
       // update.setEnabled(false); //mantiene el boton de actualizar usuarios oculto hasta ser llamado cuando se le necesite
  // AutoCompleteDecorator.decorate(searchforproducts);  //es un metodo parte de la libreria autocompleter
      user.setText(usuarioname);
    totalventarealizada.setVisible(false);
    labelparaeltotal.setVisible(false);
    labeldescuento.setVisible(true);
     descuentolabel.setVisible(true);
               veridventas.setVisible(false);
               imprimirventa.setVisible(false);
               cancelarventa.setVisible(false);
deletedescuento.setVisible(false);
   //DE LA TABLA CREDITO PENDIENTE
    labelnombre.setVisible(false);
        labelcredito.setVisible(false);
        deudor.setVisible(false);
        totalventacreditoenturno.setVisible(false);
        veridventasacreditopendiente.setVisible(false);
         pagarventaacredito.setVisible(false);
         piezasparaacomplettarpollo.setVisible(true);
    }
      //CUANDO RECIBE LA CANTIDAD POR DEFAULT DE 1
  public void piezaseleccionadaycantidadunica(String piezaseleccionada, int cantidaddeproductos){
        this.cantidaddeproductos=cantidaddeproductos;
      this.nombredepiezaseleccionada=piezaseleccionada;
        this.cantidadparapollocrudo=cantidaddeproductos;
agregandoaventa(nombredepiezaseleccionada, cantidaddeproductos);
  }   
    //CUANDO RECIBE UNA CANTIDAD POR PARTE DE LA CALCULADORA O UN MEDIO O UN CUARTO
  public void piezaseleccionadaycantidadvariable(float cantidaddeproductos, String piezaseleccionada){
      this.cantidaddeproductos=cantidaddeproductos;
      this.nombredepiezaseleccionada=piezaseleccionada;
      this.cantidadparapollocrudo=cantidaddeproductos;
     agregandoaventa(nombredepiezaseleccionada, cantidaddeproductos);
  }
  public void sumadeutilidades(){
              try{ Connection ca= cc.conexion();// La suma de las utilidades
    Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select SUM(utilidad) from utilidad");
                                            while(rs.next()){
                                                      utilidades =rs.getFloat(1);
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                           JOptionPane.showMessageDialog(null, "ERROR EN METODO: sumadeutilidades","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);       
                                                      }// fin del precio-catch del producto
             finally{
                  cc.getClose();
             }
  }
    public void sumadegastos(){
              try{ Connection ca= cc.conexion();// La suma de las utilidades
    Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select SUM(gastos) from utilidad");
                                            while(rs.next()){
                                                      gastos =rs.getFloat(1);
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                           JOptionPane.showMessageDialog(null, "ERROR EN METODO: sumadeutilidades","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);       
                                                      }// fin del precio-catch del producto
                  finally{
                  cc.getClose();
             }
  }
    public static String fechaventasrealizadas(){ /* SE DECARA LA FECHA DEL SISTEMA */
        Date fecha=new Date();
        SimpleDateFormat formatoFecha= new SimpleDateFormat("YYYY/MM/dd");
        return formatoFecha.format(fecha);
    }
    //AGREGAR METODO PARA ELIMINAR LAS PIEXZAS EN TURNO
    //DE DESCRIPCION DE VENTA, QUE NO PERTENEZCAN AL MISMO DIA
    public void ids_y_cantidades_enturno_por_error_de_usuario(){
try {Connection ca= cc.conexion();
        id_max_de_venta();
             sent = ca.createStatement();   
                 rs= sent.executeQuery("select id_producto, cantidad from  descripcion_de_venta where id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and  estado = '"+estadoenturno+"'"); // se ejecuta la sentencia dentro del parentesis
            while(rs.next()){        
            idsenturno.add(0, rs.getInt(1));
            cantidaddecadaidenturno.add(0, rs.getFloat(2));
            }
  regresar_cantidades_enturno_por_error_de_usuario();//SE REGRESAN LAS CANTIDADES
  borrarventasenestadoenturnoporerrordeusuario_limpiarventa_o_cerrarsesion();//SE BORRA DE DESCRIPCION DE VENTA
borrarventasenestadoenturnoporerrordeusuario_que_no_coincidenconlafechadehoy();//ESTA SITUACION ES EXCLUSIVA CUANDO SE ESTABA HACIENDO UNA VENTA, AVANZÓ AL DÍA SIGUIENTE MIENTRAS EL SISTEMA ESTABA CORRIENDO, SE CERRÓ SISTEMA
//SE ALMACENÓ LA VENTA COMO EN TURNO Y EL METODO ANTERIOR A ESTE NO LA PUEDE DETECTAR PORQUE SOLO ELIMINA LAS VENTA EN TURNO DEL DÍA, NO DEL DÍA DE AYER NI DEL PASADO
} catch (SQLException ex) {
            System.out.println();
      JOptionPane.showMessageDialog(null, "ERROR EN METODO: ids_y_cantidades_enturno_por_error_de_usuario","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
     } finally{
                  cc.getClose();
             } 
    }
  public void regresar_cantidades_enturno_por_error_de_usuario(){
     
      for (int i = 0; i < idsenturno.size(); i++) {    
             try { Connection ca= cc.conexion(); 
             sent = ca.createStatement();   
                       rs= sent.executeQuery("select cantidad from productos where id_producto= '"+idsenturno.get(i)+"'"); // se ejecuta la sentencia dentro del parentesis
            while(rs.next()){        
            cantidadporerrordeusuario = rs.getFloat(1);
             }
             } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR EN METODO: regresar_cantidades_enturno_por_error_de_usuario"+", PRIMER CATCH","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
       }finally{
                  cc.getClose();
             } 
             //DEVOLVIENDO LA CANTIDAD DE PRODUCTOS EN TURNO A LA TABLA PRODUCTOS
           try{Connection cu= cc.conexion(); 
                 PreparedStatement ps = cu.prepareStatement ("UPDATE productos SET cantidad= ? WHERE id_producto= ? ");
                 ps.setFloat(1, cantidadporerrordeusuario+=Float.parseFloat(cantidaddecadaidenturno.get(i).toString()));
                 ps.setInt(2, Integer.parseInt(idsenturno.get(i).toString()));
                 ps.executeUpdate();
             }catch(Exception e){
                  JOptionPane.showMessageDialog(null, "ERROR EN METODO: regresar_cantidades_enturno_por_error_de_usuario"+", SEGUNDO CATCH","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
       }finally{
                  cc.getClose();
             } 
       }//FIN DEL  FOR
  }    
   public void borrarventasenestadoenturnoporerrordeusuario_limpiarventa_o_cerrarsesion(){    
         id_max_de_venta();
     try{Connection ca= cc.conexion(); 
            String sql = "DELETE from descripcion_de_venta where id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and estado= '"+estadoenturno+"' ";
            sent = ca.createStatement();
            int n = sent.executeUpdate(sql);
            if(n>0){
             }else{
            }
        }catch(Exception e){
         JOptionPane.showMessageDialog(null, "ERROR EN METODO: borrarventasenestadoenturnoporerrordeusuario_limpiarventa_o_cerrarsesion","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }  finally{
                  cc.getClose();
             }    
     }
     public void borrarventasenestadoenturnoporerrordeusuario_que_no_coincidenconlafechadehoy(){    
         id_max_de_venta();
     try{Connection ca= cc.conexion(); 
            String sql = "DELETE from descripcion_de_venta where id_venta= '"+id_de_la_venta_incrementable+"' and fecha!= '"+fecha()+"' and estado= '"+estadoenturno+"' ";
            sent = ca.createStatement();
            int n = sent.executeUpdate(sql);
            if(n>0){
             }
        }catch(Exception e){
         JOptionPane.showMessageDialog(null, "ERROR EN METODO: borrarventasenestadoenturnoporerrordeusuario_que_no_coincidenconlafechadehoy","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }    finally{
                  cc.getClose();
             }  
     }
 public static void llenartablaconventasacreditopendiente(){
        Object[] columna = new Object[4];  //crear un obj con el nombre de colunna
         
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  ventasacreditopendiente.setModel(modeloT);  // add modelo ala tabla 
       modeloT.addColumn("Venta");
        modeloT.addColumn("Total");        
        modeloT.addColumn("Fecha");
        modeloT.addColumn("Nombre"); 
       try {   Connection ca= cc.conexion(); // CONEXION DB 
String sSQL = " select distinct venta.id_venta, venta.total, venta.fecha_reporte, descripcion_de_venta.nombre_credito from venta INNER JOIN descripcion_de_venta ON venta.id_venta = descripcion_de_venta.id_venta where venta.estado_venta = 'Credito-pendiente' ";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                columna[0] = rs.getString("venta.id_venta");
                columna[1] = rs.getString("venta.total");
                columna[2] = rs.getString("venta.fecha_reporte");    
                columna[3] = rs.getString("descripcion_de_venta.nombre_credito");
                modeloT.addRow(columna);
            }
        }
    } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "ERROR EN METODO: llenartablaconventasacreditopendiente"+e.getStackTrace(),"DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{
                  cc.getClose();
             }
    }
          public static void llenartablaidventasconidrealizados(){ // recibe como parametro 
         Object[] columna = new Object[3];  //crear un obj con el nombre de colunna
          
              DefaultTableModel modeloTE = new DefaultTableModel(); 
                  jTable2.setModel(modeloTE);  // add modelo ala tabla 
        modeloTE.addColumn("Venta");
        modeloTE.addColumn("Total");        
        modeloTE.addColumn("Fecha");
    jTable2.setModel(modeloTE);  // add modelo ala tabla         
        try {  Connection ca= cc.conexion(); // CONEXION DB 
         String sSQL = "SELECT id_venta, total, fecha_reporte FROM venta WHERE estado_venta='"+estadorealizado+"' AND fecha_reporte = '"+fecha()+"' ";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
                columna[0] = rs.getInt(1);
                columna[1] = rs.getFloat(2);
                 columna[2] = rs.getString(3);
                modeloTE.addRow(columna);
            }
          jTable2.setModel(modeloTE);  // add modelo ala tabla 
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "ERROR EN METODO: llenartablaidventasconidrealizados","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
       }finally{
                  cc.getClose();
             }
}
           public static void vaciarlistasdeticket(){
                nombreproductoticket.clear();
              piezastcket.clear();
              preciounitarioticket.clear(); 
              importesticket.clear();
           }
            public void reimpresiondeventa(int numerodeventa){
         try {Connection ca= cc.conexion();
                 String sSQL = "SELECT nombre_producto, cantidad, precio_unitario, importe FROM descripcion_de_venta WHERE estado='Realizada' AND id_venta = '"+numerodeventa+"' ";  
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
              nombreproductoticket.add(rs.getString(1));
              piezastcket.add(rs.getString(2));
              preciounitarioticket.add(rs.getString(3)); 
              importesticket.add(rs.getString(4));
            }
            total_pagoycambiopararelticketdeventa(numerodeventa);
                            //estas dos lineas mandan los datos para el ticket
                 mandardatosaticketventacondescuento = new ticketventacondescuento();
                 mandardatosaticketventacondescuento.tikectdeventacondescuento(nombreproductoticket, 
                         piezastcket, 
                         preciounitarioticket, 
                         importesticket,
                         subtotalticket, totalticket, pagoticket, cambioticket, descuentoticket, numerodeventa);
            //totalcdescticket agregar al metodo de arriba
                 vaciarlistasdeticket();
    } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "ERROR EN METODO: reimpresiondeventa","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{
                  cc.getClose();
             }
    }
     public static void descripciondelosprouductosparaelticketdeventacredito(int numerodeventa){
         String nombre="";
                try {Connection ca= cc.conexion();
                 String sSQL = "SELECT nombre_producto, cantidad, precio_unitario, importe, nombre_credito FROM descripcion_de_venta WHERE estado='Credito-pendiente' AND id_venta = '"+numerodeventa+"' ";  
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
              nombreproductoticket.add(rs.getString(1));
              piezastcket.add(rs.getString(2));
              preciounitarioticket.add(rs.getString(3)); 
              importesticket.add(rs.getString(4));
              nombre=rs.getString(5);
            }
            total_pagoycambiopararelticketdeventacredito(numerodeventa);
                 //estas dos lineas mandan los datos para el ticket
                 mandardatosticketventacredito = new ticketventacredito();    
                 mandardatosticketventacredito.tikectdeventaacredito(nombre, nombreproductoticket, 
                   piezastcket, 
                         preciounitarioticket, 
                         importesticket,
                         subtotalticket, totalticket, pagoticket, cambioticket, numerodeventa);
 vaciarlistasdeticket();
    } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "ERROR EN METODO: descripciondelosprouductosparaelticketdeventacredito","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{
                  cc.getClose();
             }
    }
            public static void descripciondelosprouductosparaelticketdeventa(int numerodeventa){
         try {Connection ca= cc.conexion();
                 String sSQL = "SELECT nombre_producto, cantidad, precio_unitario, importe FROM descripcion_de_venta WHERE estado='Realizada' AND id_venta = '"+numerodeventa+"' ";  
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
              nombreproductoticket.add(rs.getString(1));
              piezastcket.add(rs.getString(2));
              preciounitarioticket.add(rs.getString(3)); 
              importesticket.add(rs.getString(4));
            }
            total_pagoycambiopararelticketdeventa(numerodeventa);
            if(descuentoactivo==true){
                            //estas dos lineas mandan los datos para el ticket
                 mandardatosaticketventacondescuento = new ticketventacondescuento();
                 mandardatosaticketventacondescuento.tikectdeventacondescuento(nombreproductoticket, 
                         piezastcket, 
                         preciounitarioticket, 
                         importesticket,
                         subtotalticket, totalticket, pagoticket, cambioticket, descuentoticket, numerodeventa);
            //totalcdescticket agregar al metodo de arriba
                 vaciarlistasdeticket();
            }else{//venta simple
                 //estas dos lineas mandan los datos para el ticket
                 mandardatosticketventa = new ticketventa();
                 mandardatosticketventa.tikectdeventa(nombreproductoticket, 
                   piezastcket, 
                         preciounitarioticket, 
                         importesticket,
                         subtotalticket, totalticket, pagoticket, cambioticket, numerodeventa);
 vaciarlistasdeticket();
            }
         } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "ERROR EN METODO: descripciondelosprouductosparaelticketdeventa","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{
                  cc.getClose();
             }
    }
 public static void total_pagoycambiopararelticketdeventa(int id){ // recibe como parametro 
         Object[] columna = new Object[5];  //crear un obj con el nombre de colunna
        try {Connection ca= cc.conexion();
         String sSQL = "SELECT subtotal, total, pago, cambio, descuento FROM venta WHERE estado_venta='"+estadorealizado+"' AND fecha_reporte = '"+fecha()+"' and id_venta='"+id+"' ";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
               subtotalticket = rs.getFloat(1);
              totalticket = rs.getFloat(2);
                pagoticket = rs.getFloat(3);
                 cambioticket = rs.getFloat(4);
                 descuentoticket = rs.getFloat(5);           
          }  
    } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "ERROR EN METODO: total_pagoycambiopararelticketdeventa","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
       }finally{
                  cc.getClose();
             }
}
   public static void total_pagoycambiopararelticketdeventacredito(int id){ // recibe como parametro 
         Object[] columna = new Object[5];  //crear un obj con el nombre de colunna
        try {Connection ca= cc.conexion();
         String sSQL = "SELECT subtotal, total, pago, cambio, descuento FROM venta WHERE estado_venta='"+creditopendiente+"' AND fecha_reporte = '"+fecha()+"' and id_venta='"+id+"' ";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
               subtotalticket = rs.getFloat(1);
              totalticket = rs.getFloat(2);
                pagoticket = rs.getFloat(3);
                 cambioticket = rs.getFloat(4);
                 descuentoticket = rs.getFloat(5);           
          }  
    } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "ERROR EN METODO: total_pagoycambiopararelticketdeventacredito","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
       }finally{
                  cc.getClose();
             }
}
   public static void totaldelasventasdehoy(){
        try{ Connection ca= cc.conexion();// La suma de todos los importes
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select SUM(total) from venta where fecha_reporte= '"+fechaventasrealizadas()+"' AND estado_venta='Realizada' ");
                                            while(rs.next()){
                                                      sumadetotalesdeventasdehoy =rs.getFloat(1);
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                           JOptionPane.showMessageDialog(null, "ERROR EN METODO: totaldelasventasdehoy","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);       
                                                      }// fin del precio-catch del producto
        finally{
                  cc.getClose();
             }
    }
     public static void conteodeventasrealizadasdehoy(){
        try{ Connection ca= cc.conexion();// CUENTA EL TODAL DE CUANTAS VENTAS SE REALIZARON
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("SELECT COUNT(id_venta) FROM venta WHERE fecha_reporte = '"+fechaventasrealizadas()+"' AND estado_venta='Realizada'");
                                            while(rs.next()){
                                                      conteototaldeventas =rs.getInt("COUNT(id_venta)");
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                           JOptionPane.showMessageDialog(null, "ERROR EN METODO: conteodeventasrealizadasdehoy","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
                                                      }// fin del precio-catch del producto
        finally{
                  cc.getClose();
             }
    }
 // CONSULTA DE PRODUCTOS MAS DENVIDOS            
     public static void productosmasvendidos(JTable tablaD){ // recibe como parametro 
         Object[] columna = new Object[3];  //crear un obj con el nombre de colunna
         
              DefaultTableModel modeloTE = new DefaultTableModel(); 
                  tablaD.setModel(modeloTE);  // add modelo ala tabla 
        modeloTE.addColumn("Nombre");
        modeloTE.addColumn("PIezas");        
        modeloTE.addColumn("Fecha");
        try {   Connection ca= cc.conexion(); // CONEXION DB 
         String sSQL = "SELECT nombre_producto, SUM(cantidad), fecha FROM descripcion_de_venta WHERE estado='Realizada' AND fecha = '"+fecha()+"' GROUP BY nombre_producto ORDER by SUM(cantidad) DESC";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                columna[0] = rs.getString(1);
                columna[1] = rs.getFloat(2);
                 columna[2] = rs.getString(3);
                //columna[5] = rs.getString("nombre");                
                modeloTE.addRow(columna);
            }
        }
       
    } catch (Exception e) {
       JOptionPane.showMessageDialog(null, "ERROR EN METODO: productosmasvendidos","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{
                  cc.getClose();
             }
}
      // CONSULTA DE PRODUCTOS CON PRECIOS PARA LA VENTA           
     public static void ParaLAVenta(JTable tablaD){ // recibe como parametro 
         Object[] columna = new Object[3];  //crear un obj con el nombre de colunna
           
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  tablaD.setModel(modeloT);  // add modelo ala tabla         
        modeloT.addColumn("nombre_producto");    
        modeloT.addColumn("precio");
        modeloT.addColumn("cantidad");
        try { Connection ca= cc.conexion(); // CONEXION DB 
         String sSQL = "SELECT nombre_producto, precio, cantidad FROM productos WHERE nombre_producto NOT IN ('Huesito', 'Medio pollo','Pechuga en bisteck')";
         PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                columna[0] = rs.getString(1);
                columna[1] = rs.getFloat(2);
                 columna[2] = (int) rs.getFloat(3);
                modeloT.addRow(columna);
            }
        }
    } catch (Exception e) { JOptionPane.showMessageDialog(null, "ERROR EN METODO: ParaLAVenta","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{
                  cc.getClose();
             }
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
           public static  boolean validarFormularioparaentradadeproductos(String cantidaddelatabla) { // VALIDACION DE TXT MONTO
        boolean next = false;
        Pattern patGastos = Pattern.compile("^-[0-9]+([.])?([0-9]+)?$");//con simbolomenos
        Pattern patGastos1 = Pattern.compile("^[0-9]+([.])?([0-9]+)?$");
        Matcher matGastos = patGastos.matcher(cantidaddelatabla);
        Matcher matGastos1 = patGastos1.matcher(cantidaddelatabla);
        if (matGastos.matches()&&!cantidaddelatabla.equals("")||matGastos1.matches()) {
            next = true;
        } else {
            JOptionPane.showMessageDialog(null, "No puedes escribir letras o dejar vacio el campo", "Advertencia", JOptionPane.INFORMATION_MESSAGE);    
        }
        return next;
    }
               public boolean validarFormularioparamostrardescripciondeproductosporid(String cantidaddelatabla) { // VALIDACION DE TXT MONTO
        boolean next = false;
        Pattern patGastos = Pattern.compile("^[0-9]+([.])?([0-9]+)?$");
        Matcher matGastos = patGastos.matcher(cantidaddelatabla);
        if (matGastos.matches()&&!cantidaddelatabla.equals("")&&!cantidaddelatabla.equals("0")) {
            next = true;
        } else {
            JOptionPane.showMessageDialog(null, "Para volver a visualizar las ventas debes de dar clic en el boton de 'ver las ventas' a la derecha de la tabla ", "Advertencia", JOptionPane.INFORMATION_MESSAGE);    
        }
        return next;
    }
 
public static void insertandopiezasdepolloporhaberagregadoxcantidaddepollocrudo(String valor, float cantidaddesdelatablaeditable){
  if(valor.equals("pollo crudo")){// si estan modificando sobre pollo crudo, se inserta primero en la linea de arriba y luego las otras piezas de pollo a su equivalente
                       
 for(int i=0; i<piezas.length; i++) {//RECORRIENDO EL ARREGLO DE POLLOS
      try{    Connection ca= cc.conexion(); // el id del usuario
                if(piezas[i].equals("Muslo")||
                   piezas[i].equals("Pierna")||
                   piezas[i].equals("Ala")||
                   piezas[i].equals("Patas")){
               resultadodepiezaspares=cantidaddesdelatablaeditable*piezasdepollopares;
               PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+resultadodepiezaspares+"'WHERE nombre_producto='"+piezas[i]+"'");
               int ty = ps.executeUpdate();      
                 if(ty>0){
                     ParaLAVenta(JtablepaLaVenta);  // ***********************                      
                 }else{ 
                 }
           }
           else if(piezas[i].equals("Huacal")||piezas[i].equals("Cadera")||
                   piezas[i].equals("Cabeza")||
                   piezas[i].equals("Molleja")||piezas[i].equals("Pechuga")){
            resultadodepiezasinpares=cantidaddesdelatablaeditable*piezasdepollosinpares;
         PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+resultadodepiezasinpares+"'WHERE nombre_producto='"+piezas[i]+"'");
               int ty = ps.executeUpdate();
                 if(ty>0){
                     ParaLAVenta(JtablepaLaVenta);  // ***********************        
                 }else{
                 }
        }
        }//fin del id del usuario
                 catch(Exception w){
                  JOptionPane.showMessageDialog(null, "ERROR EN METODO: insertandopiezasdepolloporhaberagregadoxcantidaddepollocrudo","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
                 }finally{
                  cc.getClose();
             }//fin del id del usuario
 }// RECORRIENDO EL ARREGLO DE POLLOS                      
}// si estan modificando sobre pollo crudo, se inserta primero en la linea de arriba y luego las otras piezas de pollo a su equivalente

}
      // CONSULTA DE PRODUCTOS EN EXITENCIA EN INVENTARIO            
     public static void TablallenadoparaEntradas(JTable tablaD){ // recibe como parametro 
           Object[] columna = new Object[2];  //crear un obj con el nombre de colunna
           
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  tablaD.setModel(modeloT);  // add modelo ala tabla 
        modeloT.addColumn("nombre_producto");
        modeloT.addColumn("cantidad");
        try { Connection ca= cc.conexion(); // CONEXION DB 
         String sSQL = "SELECT  nombre_producto, cantidad FROM productos WHERE nombre_producto NOT IN('Huesito', 'Medio pollo','Pechuga en bisteck')";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                columna[0] = rs.getString(1);
                 columna[1] = (int) rs.getFloat(2);
                modeloT.addRow(columna);
            }
                        modeloT.addTableModelListener(new TableModelListener(){
                @Override
                public void tableChanged(TableModelEvent e) {
                    int fila =Jtable_ProductosEntradas.getSelectedRow();
                    int col =Jtable_ProductosEntradas.getSelectedColumn();            
     
                    if(e.getType() == TableModelEvent.UPDATE){
                        if(fila>=0){// CUANDO UNA CELDA SE SELECCIONO         
                     boolean pass = validarFormularioparaentradadeproductos(modeloT.getValueAt(e.getFirstRow(), e.getColumn()).toString());
                             if(pass){
                                   String valor = Jtable_ProductosEntradas.getValueAt(fila, 0).toString();
                            cantidaddesdelatablaeditable = Float.parseFloat(modeloT.getValueAt(e.getFirstRow(), e.getColumn()).toString());
                              id_producto(valor); 
                             cantidadpolloenDByname(id_producto);
                              cantidaddesdelatablaeditable+=cantidadpolloenDB;
                              String sql = "UPDATE productos SET cantidad='"+cantidaddesdelatablaeditable+"' WHERE id_producto="+id_producto;            
    insertandopiezasdepolloporhaberagregadoxcantidaddepollocrudo(valor, cantidaddesdelatablaeditable);
                          PreparedStatement pst;
                          try{Connection ca= cc.conexion();
                               pst = ca.prepareStatement(sql);
                               int rows = pst.executeUpdate();
                       ParaLAVenta(JtablepaLaVenta);  // ***********************
                          } catch (SQLException ex) {
                               JOptionPane.showMessageDialog(null, "ERROR EN METODO: tableChanged","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
                           }finally{cc.getClose();}
      }
                             }                             
                    }
   }
            });
 }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "ERROR EN METODO: TablallenadoparaEntradas","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{
                  cc.getClose();
             }
}
   public String llenarfechadesdeparamostrarlosidventas(){ // Ordena la fecha del componente Jcalendar  que esta de la sig. manera:  dia /mes / aÃ±o, lo cual para la base de datos no es la manera correcta de ingresarlo, sino asÃ­: aÃ±o/mes/dia
            int año= fechainicial.getCalendar().get(Calendar.YEAR);
       int mes= fechainicial.getCalendar().get(Calendar.MONTH)+1;
       int dia= fechainicial.getCalendar().get(Calendar.DAY_OF_MONTH);
       if(dia<10){
           String nuevodia= "0"+dia;
             fechaparaventasdesde= año+"/"+mes+"/"+nuevodia;
       }
       else{
           fechaparaventasdesde= año+"/"+mes+"/"+dia;
       }
       return fechaparaventasdesde;
      }
    public String llenarfechahastaparamostrarlosidventas(){ // Ordena la fecha del componente Jcalendar  que esta de la sig. manera:  dia /mes / aÃ±o, lo cual para la base de datos no es la manera correcta de ingresarlo, sino asÃ­: aÃ±o/mes/dia
       int año= fechafinal.getCalendar().get(Calendar.YEAR);
       int mes= fechafinal.getCalendar().get(Calendar.MONTH)+1;
       int dia= fechafinal.getCalendar().get(Calendar.DAY_OF_MONTH);
      if(dia<10){
           String newday= "0"+dia;
             fechaparaventashasta= año+"/"+mes+"/"+newday;
       }
       else{
           fechaparaventashasta= año+"/"+mes+"/"+dia;
       }
        return fechaparaventashasta;
    } // CONSULTA DE VENTAS  REALIZADAS
     public static void productosvendidoseneldia(){ // recibe como parametro 
         Object[] columna = new Object[3];  //crear un obj con el nombre de colunna
         
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  jTable3.setModel(modeloT);  // add modelo ala tabla 
        modeloT.addColumn("Producto");
        modeloT.addColumn("Cantidad");        
        modeloT.addColumn("importe");
          jTable3.setModel(modeloT);  // add modelo ala tabla 
         try {   Connection ca= cc.conexion(); // CONEXION DB 
         String sSQL = "SELECT nombre_producto, SUM(cantidad), SUM(importe) FROM  descripcion_de_venta WHERE estado = 'Realizada' AND fecha = CURDATE() GROUP BY nombre_producto";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
                columna[0] = rs.getString(1);
                columna[1] = rs.getString(2);
                columna[2] = rs.getString(3);                
                modeloT.addRow(columna);
            } jTable3.setModel(modeloT);  // add modelo ala tabla 
    } catch (Exception e) { JOptionPane.showMessageDialog(null, "ERROR EN METODO: productosvendidoseneldia","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{
                  cc.getClose();
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
                try {Connection ca= cc.conexion();
            Statement st = ca.createStatement();
            ResultSet rs= st.executeQuery("select * from  user where nombre_usuario ='"+usuarioname+"'");
            while(rs.next()){
            usuario=rs.getString(1);
            }
            if(!usuario.equals("")){ //Si el nombre del usuario se encontro en la base de datos quiere decir que entro un usuario al sistema
             } 
            else{ //No entro un usuario y entro el administrador
                //JOptionPane.showMessageDialog(null, "ERROR quienentroalsistema ADMIN:" + usuario + usuarioname );    
                this.Proveedores9.setEnabledAt(0, false); //Desactiva la parte de la venta dejando unicamente activo, proveedores
                      //productos y usuarios
                      Proveedores9.setSelectedIndex(1); //Esto hace que la pestaña 1 sea la que se muestre cuando al admin inicie sesión
            }
        } catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "ERROR EN METODO: quienentroalsistema","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
           }finally{
                  cc.getClose();
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
     public static void piezassuficientes(String pieza){
          if(pieza.equals("Huesito")||pieza.equals("Longaniza")||pieza.equals("pollo crudo")||pieza.equals("Medio pollo")){
   suficientespiezas=true;
}
          else{
              try{Connection ca= cc.conexion(); //el id del producto
                                                         sent  =(Statement)ca.createStatement();
                                                      rs = sent.executeQuery("select * from productos where nombre_producto= '"+pieza+"'");
                                                      while(rs.next()){
                                                      piezassuficientes =rs.getFloat("cantidad");
                                                      }   
                                                      if(piezassuficientes>=cantidaddeproductos){
                                                          suficientespiezas=true;
                                                      }
                                                      else {
                                                          suficientespiezas=false;
                                                      }
                                                     }//fin del try - id del producto
                                                       catch (Exception e){
                                                            JOptionPane.showMessageDialog(null, "ERROR EN METODO: piezassuficientes","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
                                                      } //fin del id-catch del producto
              finally{
                  cc.getClose();
             }
          }
    }
    //METODO PARA VERIFICAR QUE HAYA SUFICIENTES PIEZAS DE ALGÚN PRODUCTO PARA HACER UNA VENTA
   
           public void descontarpiernacompleta(float cantidaddeproductos){
                String[] piernafull = {"Muslo","Pierna"};

               for (int i = 0; i < piernafull.length; i++) {
                   id_producto(piernafull[i]);
               cantidadpolloenDByname(id_producto);
               cantidadpolloenDB-=cantidaddeproductos;
               try{Connection ca= cc.conexion();
               PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+cantidadpolloenDB+"'WHERE id_producto='"+id_producto+"'");
                ps.executeUpdate();
                 }//fin del id del usuario
                 catch(Exception w){
               JOptionPane.showMessageDialog(null, "Error en descontarpiernacompleta","DEVELOPER HELPER" ,JOptionPane.INFORMATION_MESSAGE);
                 }//fin del id del usuario
               finally{
                  cc.getClose();
             }
               }
 }
           public static void descontardeinventario(String nombredepieza, float cantidaddeproductos){
               id_producto(nombredepieza);
               cantidadpolloenDByname(id_producto);
               if(cantidadpolloenDB>=cantidaddeproductos){
              try{Connection ca= cc.conexion();// el id del usuario
               cantidadpolloenDB=cantidadpolloenDB-cantidaddeproductos; 
               id_producto(nombredepieza);
                 PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+cantidadpolloenDB+"'WHERE id_producto='"+id_producto+"'");
                ps.executeUpdate();
                 }//fin del id del usuario
                 catch(Exception w){
                 JOptionPane.showMessageDialog(null, "Error en descontardeinventario","DEVELOPER HELPER",JOptionPane.INFORMATION_MESSAGE);
                 }finally{
                  cc.getClose();
             }//fin del id del usuario
               }
    }

public static void cantidadpolloenDByname(int pieza){
    try{Connection ca= cc.conexion();
 sent  = (Statement)ca.createStatement();
   rs = sent.executeQuery("select * from productos  where id_producto='"+pieza+"'");
     while(rs.next()){
            name =rs.getString("nombre_producto");
        cantidadpolloenDB =rs.getFloat("cantidad"); // piezas en la db
     }                                          
    }catch (Exception f){
                    JOptionPane.showMessageDialog(null, "Error, cantidadpolloenDByname","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
         }finally{
                  cc.getClose();
             }
}           

public void cantidadenventa(int pieza){
    id_max_de_venta();   //Cantidad en venta
                try{Connection ca= cc.conexion();
                sent  =(Statement)ca.createStatement(); 
                     rs = sent.executeQuery("select * from descripcion_de_venta where id_producto= '"+pieza+"'AND estado='"+estadoenturno+"'and id_venta='"+id_de_la_venta_incrementable+"'and fecha='"+fecha()+"'  ");       
                while(rs.next()){    
                    cantidadenventa =rs.getFloat("cantidad");      
                    nameenventa=rs.getString("nombre_producto");
                }
                }catch(Exception e){
 JOptionPane.showMessageDialog(null, "Error, cantidadenventa","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
                }finally{
                  cc.getClose();
             }
}
  
           public void regresarpiezasdepollocrudodeinventario(int id, float addpiezas){//INICIO DE DESCONTAR POLLO CRUDO DE INVENTARIO
    try{Connection ca= cc.conexion();// el id del usuario
      PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+addpiezas+"'WHERE id_producto='"+id+"'");
               int ty = ps.executeUpdate();
                 if(ty>0){
                       }else{
                     }
  }   catch(Exception w){
           JOptionPane.showMessageDialog(null, "Error, regresarpiezasdepollocrudodeinventario","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
                }finally{
                  cc.getClose();
             }//fin del id del usuario
   }//FIN DE DESCONTAR POLLO CRUDO DE INVENTARIO
           
           public static void precio_producto(String nombredepieza){
        try{ Connection ca= cc.conexion();// el precio del producto
                                sent  =(Statement)ca.createStatement();
                                           rs = sent.executeQuery("select * from productos where nombre_producto= '"+nombredepieza+"'");
                                            while(rs.next()){
                                                      precio =Float.parseFloat(rs.getString("precio"));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
    JOptionPane.showMessageDialog(null, "Error, precio_producto","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
                                                      }finally{
                  cc.getClose();
             }// fin del precio-catch del producto
    }

           public static void id_producto(String nombredepieza){
          try{ Connection ca= cc.conexion();//el id del producto
                                                      sent  =(Statement)ca.createStatement();
                                                      rs = sent.executeQuery("select * from productos where nombre_producto= '"+nombredepieza+"'");
                                                      while(rs.next()){
                                                      id_producto =Integer.parseInt(rs.getString("id_producto"));
                                                      }
                                                      }//fin del try - id del producto
                                                      catch (Exception e){
                                                            JOptionPane.showMessageDialog(null, "Error, id_producto","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
                                                      } finally{
                  cc.getClose();
             }//fin del id-catch del producto
    }
  public static void  limpiardatosdeventa(){
        subtotal.setText("00.00");
        cambiocombobox.setText("00.00");
        descuentocombo.setText("00.00");
        total.setText("00.00");
        tablaventa.setVisible(false);
        tablaventaactiva=false;
    } 
        public static void get_id_usuario(){
      try{Connection ca= cc.conexion();// el id del usuario para obtener el id del usuario y comprobar si hay o no algun registro
                         try{ 
                          sent = ca.createStatement();
                          ResultSet rs= sent.executeQuery("SELECT *  FROM  user where nombre_usuario ='"+usuarioname+"'");
                          while(rs.next()){
                                   id_usuario=Integer.parseInt(rs.getString("id_usuario"));
                         }
                         }catch(Exception a){
                                       JOptionPane.showMessageDialog(null, "Error, get_id_usuario; SELECT *  FROM  user where nombre_usuario = ","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
   }finally{
                  cc.getClose();
             }
                         if(block_unlock==true){Connection cu= cc.conexion();
                               String sql = "INSERT INTO  venta(id_usuario)  VALUES (?)";
                         PreparedStatement pst = cu.prepareCall(sql); 
                         pst.setInt(1,id_usuario);
                         int a=pst.executeUpdate();
                         if(a>0){
                         }
                         }
      }catch(Exception w){
                                       JOptionPane.showMessageDialog(null, "Error, get_id_usuario; INSERT INTO  venta ","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
   }finally{
                  cc.getClose();
             }//fin del id del usuario para comprobar si hay o no elementos ya guardados
  }

        public static void id_max_de_venta(){
                        try{ Connection ca= cc.conexion();
                          sent = ca.createStatement();
                          ResultSet rs= sent.executeQuery("SELECT  max(id_venta) FROM  venta");
                          while(rs.next()){
                                   id_de_la_venta_incrementable=rs.getInt(1);
                         }
                         }catch(Exception a){
                                JOptionPane.showMessageDialog(null, "Error, id_max_de_venta","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
                                                     }finally{
                  cc.getClose();
             }
  }
             static void mostrartabladeventas(){ // solo muestra la tabla de proveedores 
         tablaventa.setVisible(true);    //hace visible la tabla de proveedores 
              DefaultTableModel modelo = new DefaultTableModel(); // Se crea un objeto para agregar los nombres de las columnas a la tabla
    modelo.addColumn("Producto");
    modelo.addColumn("Cantidad");
    modelo.addColumn("Precio");
     modelo.addColumn("Importe");
     tablaventa.setModel(modelo);  // Ya una vez asignado todos los nombres se le envia el objeto a la tabla proveedores
    String []datos = new String[4];     //Un arreglo con la cantidad de nombres en las columnas
    try {Connection ca= cc.conexion();
        id_max_de_venta();
             sent = ca.createStatement();   
                       rs= sent.executeQuery("select * from  descripcion_de_venta where id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and  estado = '"+estadoenturno+"'"); // se ejecuta la sentencia dentro del parentesis
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
          JOptionPane.showMessageDialog(null, "Error, mostrartabladeventas","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
                                         } finally{
                  cc.getClose();
             }
    }
  public static void primer_ventadelsistema(){
             try {Connection ca= cc.conexion();
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
             JOptionPane.showMessageDialog(null, "Error, primer_ventadelsistema","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
         }
         catch(NumberFormatException NFE){ // caso contrario que la variable resultfirstselling tuviese un valor null indicaria que no hay ninguna venta en el sistema
                    primerventa=0; //y por tal la variable primerventa tendra el valor de 0
        }finally{
                  cc.getClose();
             }
   } 
            public void vaciartodoelpollocrudodeinventario(){
              try{              Connection ca= cc.conexion();
           PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad= 0 WHERE nombre_producto in ('pollo crudo', 'Pechuga', 'Muslo', 'Ala', 'Pierna', 'Huacal', 'Cadera', 'Cabeza', 'Molleja', 'Patas')");
                  int a = ps.executeUpdate();
                if(a>0){    
                }
                           }catch(Exception e){
                                   JOptionPane.showMessageDialog(null, "Error, vaciartodoelpollocrudodeinventario","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
            }finally{
                  cc.getClose();
             }
            }
             //METODOS PARA DESCONTAR 1 POLLO  O N POLLOS EN BASE A LAS PIEZAS QUE SE HAN DESCONTADO
             public static void descuentodepollo(){
                 minimodelaspiezasdepollocrudoquesoninparesentablaproductos();
                 minimodelaspiezasdepollocrudoquesonparesentablaproductos();
                 pollocrudoeninventario();
                 if(minimodelaspiezasinparesdepollocrudoeninventario==0){
                     actualizarpollocrudoeninventario(0);
                 }
                 else if(minimodelaspiezasparesdepollocrudoeninventario==1){
                      actualizarpollocrudoeninventario(0);
                 }
                 else if(pollo_crudoeninventario!=minimodelaspiezasinparesdepollocrudoeninventario&&
                         minimodelaspiezasparesdepollocrudoeninventario!=(minimodelaspiezasinparesdepollocrudoeninventario*2)&&
                         pollo_crudoeninventario>minimodelaspiezasinparesdepollocrudoeninventario){
                      actualizarpollocrudoeninventario(minimodelaspiezasinparesdepollocrudoeninventario);
                  
                 }else if(pollo_crudoeninventario==minimodelaspiezasinparesdepollocrudoeninventario&&
                         minimodelaspiezasparesdepollocrudoeninventario==(minimodelaspiezasinparesdepollocrudoeninventario*2)||
                         pollo_crudoeninventario==minimodelaspiezasinparesdepollocrudoeninventario&&
                         minimodelaspiezasparesdepollocrudoeninventario!=(minimodelaspiezasinparesdepollocrudoeninventario*2)&&
                         minimodelaspiezasparesdepollocrudoeninventario>(minimodelaspiezasinparesdepollocrudoeninventario*2)){
                       actualizarpollocrudoeninventario(minimodelaspiezasinparesdepollocrudoeninventario);
                 }
                 else{
                     if(minimodelaspiezasparesdepollocrudoeninventario%2!=0){
                         actualizarpollocrudoeninventario((minimodelaspiezasparesdepollocrudoeninventario-1)/2);
                     }else{
                          actualizarpollocrudoeninventario(minimodelaspiezasparesdepollocrudoeninventario/2); 
                     }
                     //ESTO ELIMINA LOS DECIMALES DEL SOBRANTE
                    //AQUI EL LINK
                    //https://www.yoelprogramador.com/como-usar-el-formateador-decimal-en-java/
                     java.text.DecimalFormat formatoSalida = new java.text.DecimalFormat("0");//para ningun decimal
                     Float.parseFloat(formatoSalida.format(minimodelaspiezasparesdepollocrudoeninventario/2));
                 }
             }
             public static void actualizarpollocrudoeninventario(float actualizaciondepollo){
                 try{              Connection ca= cc.conexion();     
           PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+actualizaciondepollo+"'WHERE nombre_producto='"+pollo_crudo+"'");
                  int a = ps.executeUpdate();
                if(a>0){    
                }
                           }catch(Exception e){
    JOptionPane.showMessageDialog(null, "Error, actualizarpollocrudoeninventario","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
                           }finally{
                  cc.getClose();
             }
             }
             
     public static void pollocrudoeninventario(){
              String consulta="select cantidad from productos WHERE nombre_producto = 'pollo crudo' ";
              try{    Connection ca= cc.conexion();     
                     sent  = (Statement)ca.createStatement();
                                             rs = sent.executeQuery(consulta);
                                       while(rs.next()){
                                                      pollo_crudoeninventario =rs.getFloat(1);//Esto muestra la cantidad actual para compararlo
                                                        }
                 }catch(Exception e){
                       JOptionPane.showMessageDialog(null, "Error, pollocrudoeninventario","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE);                  
                }finally{
                  cc.getClose();
             }
          }
              public static void minimodelaspiezasdepollocrudoquesoninparesentablaproductos(){
              String consulta="select MIN(cantidad) from productos WHERE nombre_producto IN('Huacal', 'Cadera', 'Cabeza', 'Molleja', 'Pechuga')";
              try{ Connection ca= cc.conexion();     
                     sent  = (Statement)ca.createStatement();
                                             rs = sent.executeQuery(consulta);
                                       while(rs.next()){
                                                      minimodelaspiezasinparesdepollocrudoeninventario =rs.getFloat(1);//Esto muestra la cantidad actual para compararlo
                                                        }
                 }catch(Exception e){ 
                                JOptionPane.showMessageDialog(null, "Error, minimodelaspiezasdepollocrudoquesoninparesentablaproductos","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE);                  
                }finally{
                  cc.getClose();
             }
          }
          public static void minimodelaspiezasdepollocrudoquesonparesentablaproductos(){
              String consulta="select MIN(cantidad) from productos WHERE nombre_producto IN('Muslo', 'Pierna', 'Ala', 'Patas')";
              try{Connection ca= cc.conexion();    
                     sent  = (Statement)ca.createStatement();
                                             rs = sent.executeQuery(consulta);
                                       while(rs.next()){
                                                      minimodelaspiezasparesdepollocrudoeninventario =rs.getFloat(1);//Esto muestra la cantidad actual para compararlo
                                                        }
                 }catch(Exception e){ 
              JOptionPane.showMessageDialog(null, "Error, minimodelaspiezasdepollocrudoquesoninparesentablaproductos","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE);                  
                 }finally{
                  cc.getClose();
             }
          }
          
        //FIN METODOS PARA DESCONTAR 1 POLLO  O N POLLOS EN BASE A LAS PIEZAS QUE SE HAN DESCONTADO
 public static void accionesdespuesinsertarendescripciondeventaoactualizarenlamismatabla(String nombredepieza, float cantidaddeproductos){
 if(nombredepieza.equals("Huesito")||nombredepieza.equals("Longaniza")){
     mostrartabladeventas();
                    tablaventaactiva=true;   
                cantidaddecuarto=0;
                cantidaddemedio=0;
                   total_venta_enturno();
                    totalf=sumadeimportes;
                    subtotal.setText(String.valueOf(totalf));   
                    total.setText(String.valueOf(totalf));
                    NoP="";
 } else{
       descontardeinventario(nombredepieza, cantidaddeproductos);
                    //  descuentodepollo();                  
                    mostrartabladeventas();
                    soypechugaenbisteck=false;
                    tablaventaactiva=true;
                cantidaddecuarto=0;
                cantidaddemedio=0;
                   total_venta_enturno();
                    totalf=sumadeimportes;
                    subtotal.setText(String.valueOf(totalf));
                    total.setText(String.valueOf(totalf));
                    NoP="";
 }
 }
 public static void obtenerelnombredeproductoylacantidaddelmismo_en_descripcion_deventa(String nombredepieza){
 try{Connection ca= cc.conexion();   
          id_producto(nombredepieza);
    id_max_de_venta();
   sent  = (Statement)ca.createStatement();
                                           rs = sent.executeQuery("select * from descripcion_de_venta  where id_producto='"+id_producto+"' and id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and estado= '"+estadoenturno+"'");
                                            while(rs.next()){
                                               NoP =rs.getString("nombre_producto");//NOMBRE DEL PRODUCTO
                                                      NoPcantidad =Float.parseFloat(rs.getString("cantidad")); //CANTIDAD DEL MISMO
                                            NoPimporte = Float.parseFloat(rs.getString("importe"));         
                                            }
 }catch (Exception f){
     JOptionPane.showMessageDialog(null, "Error, obtenerelnombredeproductoylacantidaddelmismo_en_descripcion_deventa","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE);                  
                    }finally{
                  cc.getClose();
             }
 }
 public static void noguardaridrepetidoenstorage(int id){//NO PERMITE AGREGAR IDS REPETIDOS A STORAGE
     boolean loencontre=false;
     if(storage.size()>0){
         for(int n=0;n<=storage.size()-1;n++){  
         if(Integer.parseInt(storage.get(n).toString())==id){
          loencontre=true;  
         }
         }if(loencontre==false){
             storage.add(id);
         }
     }else{
          storage.add(id);
     }
 }
  public static void comprobar_registro (String nombredepieza){
  obtenerelnombredeproductoylacantidaddelmismo_en_descripcion_deventa(nombredepieza);
if(NoP.equals(nombredepieza)&&NoPimporte!=0){ //Si el nombre del producto es diferente del estado vacio, en palabras más sencillas; si se encuentra el producto que se quiere agregar para que no se asigne nuevamente  
    try{Connection ca= cc.conexion();   // ESTE ES PARA EL UPDATE
          obtenerelnombredeproductoylacantidaddelmismo_en_descripcion_deventa(nombredepieza);
      NoPcantidad=NoPcantidad+cantidaddeproductos;
                precio_producto(nombredepieza);
                NoPimporte = NoPcantidad*precio;
              id_producto(nombredepieza);
                    id_max_de_venta();
                 PreparedStatement ps = ca.prepareStatement ("UPDATE descripcion_de_venta SET cantidad='"+NoPcantidad+"',importe = '"+NoPimporte+"'WHERE importe !=0 and id_producto='"+id_producto+"' and id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and estado= '"+estadoenturno+"' ");
               int a=  ps.executeUpdate();
               if(a>0){
                      accionesdespuesinsertarendescripciondeventaoactualizarenlamismatabla(nombredepieza, cantidaddeproductos);
                    if(descuentoactivo==true){
                if(Float.parseFloat(subtotal.getText())>0){
               totalfinalcondescuento =  Float.parseFloat(subtotal.getText()) - Float.parseFloat(descuentocombo.getText());
               total.setText(String.valueOf(totalfinalcondescuento));
                    }
                 }
               }else{
                   JOptionPane.showMessageDialog(null, " NO SE PUDO ACTIALIZAR");
               }
        }//fin del id del usuario
                 catch(Exception w){
               JOptionPane.showMessageDialog(null, "Error, comprobar_registro UPDATE","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE);                  
                      }finally{
                  cc.getClose();
             }//fin del id del usuario
               }
   else{
  try{Connection ca= cc.conexion(); //la insersion a la tabla ventas
                String sql = "INSERT INTO descripcion_de_venta (id_producto,nombre_producto,cantidad,precio_unitario,importe,id_venta,estado, fecha)  VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement pst = ca.prepareCall(sql); //hasta aqui vamos
                id_producto(nombredepieza); 
                pst.setInt(1,id_producto);
                noguardaridrepetidoenstorage(id_producto);
               pst.setString(2,nombredepieza);
                                 pst.setFloat(3,cantidaddeproductos);    
                //EL METODO A CONTINUACION VA HACIENDO EL CONTEO DE LAS PIEZAS INDIVIDUALES
                // PARA UNA VEZ LLEGANDO A UN POLLO ENTERO DESCONTARLO DE LA BASE           
                precio_producto(nombredepieza);
               pst.setFloat(4,precio);
                importe = (float)cantidaddeproductos*precio;        
                pst.setFloat(5,importe);
               id_max_de_venta();
                pst.setInt(6,(id_de_la_venta_incrementable));
                pst.setString(7, estadoenturno);
                pst.setString(8, fecha());
                int a=pst.executeUpdate();
                if(a>0){
                    accionesdespuesinsertarendescripciondeventaoactualizarenlamismatabla(nombredepieza, cantidaddeproductos);
                    if(descuentoactivo==true){//DESCUENTOACTIVO
                 if(Float.parseFloat(subtotal.getText())>0){
               totalfinalcondescuento =  Float.parseFloat(subtotal.getText()) - Float.parseFloat(descuentocombo.getText());
               total.setText(String.valueOf(totalfinalcondescuento));
                    }
                 }//DESCUENTOACTIVO            
                }else{//CUANDO NO SE PUDO INSERTAR
                   }
            }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
            }finally{
                  cc.getClose();
             }//fin de la insersion a la tabla ventas
    }
  }
            public  void descuentos(){
                   float totalparadescuentos = Float.parseFloat(subtotal.getText());
               if(totalparadescuentos>0){
                   try{
                       String nombre="";
                      do{
                       boolean pass =validarFormulario(nombre=JOptionPane.showInputDialog(null, "Cantidad a descontar"));
                       if(pass){
                            porcentaje = Float.parseFloat(nombre);
                        if(porcentaje>=totalparadescuentos){
                            JOptionPane.showMessageDialog(null, "No se puede aplicar el 100% de descuento, lo siento, vuelve a intentarlo");
                        }
                       } 
                   }while(porcentaje>=totalparadescuentos); 
                   }catch(NullPointerException NP){}
                   if(porcentaje>0){
                       descuentoactivo=true;
               descuentocantidad= porcentaje;
               descuentocombo.setText(String.valueOf(descuentocantidad));
               totalfinalcondescuento = totalparadescuentos - descuentocantidad;
               total.setText(String.valueOf(totalfinalcondescuento));
                 total.setVisible(true);
               descuentolabel.setVisible(true);
                labeldescuento.setVisible(true);
deletedescuento.setVisible(true);
                   }else{
                   JOptionPane.showMessageDialog(null,"El descuento no puede ser 0", "Error", JOptionPane.ERROR_MESSAGE);
               }
               }
               else{
                   JOptionPane.showMessageDialog(null, "Aún no hay productos para hacer descuento");
               }
            }
public void cantidadenventasumadecantidadesfinales(int pieza){
    id_max_de_venta();  //Cantidad en venta
                try{Connection ca= cc.conexion();
                sent  =(Statement)ca.createStatement(); 
                     rs = sent.executeQuery("select SUM(cantidad) from descripcion_de_venta where id_producto= '"+pieza+"'AND estado='"+estadoenturno+"'and id_venta='"+id_de_la_venta_incrementable+"'and fecha='"+fecha()+"'  ");       
                while(rs.next()){    
                    cantidadenventasumada =rs.getFloat("SUM(cantidad)");      
                    nameenventa=rs.getString("nombre_producto");
                }
                }catch(Exception e){
                }finally{
                    cc.getClose();
                }
}
public void descontartodaslaspechugasenbisteck(float cantidadpolloenDB){
    try{Connection ca= cc.conexion();
                            PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+cantidadpolloenDB+"'WHERE id_producto='"+15+"'");
                            ps.executeUpdate();
                        }catch(Exception s){
JOptionPane.showMessageDialog(null, "Error en venta aqui" + s.getMessage());
                        } finally{
                    cc.getClose();
                }
}
                public void regresarproductos_a_inventariodescontandotodaslaspiezas(){ // este metodo devuelve los productos que fueron agregados a la venta y posteriormente fueron cancelados
                 int piezasenventa=0;
                                  float piezasenlatablapiezas=0;
                    id_max_de_venta();
                    block_unlock=true;
                for(int n=0;n<=storage.size()-1;n++){
                    if(storage.get(n).toString().equals("57")){
                         cantidadpolloenDByname(15);
                        cantidadenventasumadecantidadesfinales(57);
                       cantidadpolloenDB+=cantidadenventasumada;
                       descontartodaslaspechugasenbisteck(cantidadpolloenDB);
                    }
                 cantidadpolloenDByname(Integer.parseInt(storage.get(n).toString()));
                        cantidadenventasumadecantidadesfinales(Integer.parseInt(storage.get(n).toString()));
                       cantidadpolloenDB+=cantidadenventasumada;
                        try{Connection ca= cc.conexion();
                            PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+cantidadpolloenDB+"'WHERE id_producto='"+storage.get(n)+"'");
                            ps.executeUpdate();
                        }catch(Exception s){
JOptionPane.showMessageDialog(null, "Error en venta aqui" + s.getMessage());
                        }  finally{
                    cc.getClose();
                }
                 //PENDIENTE EL REGRESO DE POLLO A INVENTARIO 
                }//fin del ciclo for              
}
 public void accionesdespuesderegresarproductosainventarios(){
                            cantidaddecuarto=0;
                cantidaddemedio=0;
                total_venta_enturno();
                             totalf=sumadeimportes;
                    subtotal.setText(String.valueOf(totalf));
                    total.setText(String.valueOf(totalf));
                    if(descuentoactivo==true){
               if(Float.parseFloat(subtotal.getText())>0){
               totalfinalcondescuento =  Float.parseFloat(subtotal.getText()) - Float.parseFloat(descuentocombo.getText());
               total.setText(String.valueOf(totalfinalcondescuento));
                    }
                 }
 }         
public void regresarproductos_pechugaenbisteck(String nombredepieza){ // este metodo devuelve los productos que fueron agregados a la venta y posteriormente fueron cancelados
              id_max_de_venta();
                    block_unlock=true;   
                    //pendiente la restauracion de venta a inventario
                    id_producto(nombredepieza);
                  cantidadenventa(57);
                       cantidadpolloenDByname(id_producto);
   cantidadpolloenDB+=cantidadenventa;
 id_producto(nombredepieza);
                        try{Connection ca= cc.conexion(); //SUMANDO A INVENTARIO EL ULTIMO, 
                            PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+cantidadpolloenDB+"'WHERE id_producto='"+id_producto+"'");
                            ps.executeUpdate();
                         }catch(Exception s){
JOptionPane.showMessageDialog(null, "Error en venta aqui" + s.getMessage());
                        }finally{
                    cc.getClose();
                }// SUMANDO A INVENTARIO EL ULTIMO, 
                        //ELIMINAR DE VENTA EL ARTICULO
                        id_max_de_venta();
                        try{Connection ca= cc.conexion();
            String sql = "DELETE from descripcion_de_venta where id_producto= '"+57+"' and id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and estado= '"+estadoenturno+"' ";
            sent = ca.createStatement();
            int n = sent.executeUpdate(sql);
            if(n>0){
 eliminarpolloenterodestorage(57);
                 accionesdespuesderegresarproductosainventarios();
                    descuentocombo.setText("00.00");
                    total.setText(subtotal.getText());
              //  mostrartablaarticulos();     //     autocompletar();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage());
        }finally{
                    cc.getClose();
                } //ELIMINAR DE VENTA EL ARTICULO     
}
                
 public void regresarproductos_a_inventario(String nombredepieza){ // este metodo devuelve los productos que fueron agregados a la venta y posteriormente fueron cancelados
              id_max_de_venta();
                    block_unlock=true;   
                    //pendiente la restauracion de venta a inventario
                    id_producto(nombredepieza);
                  cantidadenventa(id_producto);
                       cantidadpolloenDByname(id_producto);
   cantidadpolloenDB+=cantidadenventa;
 id_producto(nombredepieza);
                        try{ Connection ca= cc.conexion();//SUMANDO A INVENTARIO EL ULTIMO, 
                            PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+cantidadpolloenDB+"'WHERE id_producto='"+id_producto+"'");
                            ps.executeUpdate();
                         }catch(Exception s){
JOptionPane.showMessageDialog(null, "Error en venta aqui" + s.getMessage());
                        }finally{
                    cc.getClose();
                }// SUMANDO A INVENTARIO EL ULTIMO, 
                        //ELIMINAR DE VENTA EL ARTICULO
                        id_producto(nombredepieza);
                        id_max_de_venta();
                        try{Connection ca= cc.conexion();
            String sql = "DELETE from descripcion_de_venta where id_producto= '"+id_producto+"' and id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and estado= '"+estadoenturno+"' ";
            sent = ca.createStatement();
            int n = sent.executeUpdate(sql);
            if(n>0){
                id_producto(nombredepieza);
 eliminarpolloenterodestorage(id_producto);
                 accionesdespuesderegresarproductosainventarios();
                    descuentocombo.setText("00.00");
                    total.setText(subtotal.getText());
              //  mostrartablaarticulos();   //      autocompletar();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage());
        }finally{
                    cc.getClose();
                } //ELIMINAR DE VENTA EL ARTICULO     
}
                public void status_cancelado(int id){
        try{Connection ca= cc.conexion();
                    PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET estado_venta='"+estadocancelado+"'WHERE id_venta='"+id+"'");
                ps.executeUpdate();
        }
            catch (Exception e){ 
               JOptionPane.showMessageDialog(null, "Error en venta" + e.getMessage());
            }   finally{
                    cc.getClose();
                }      
        try{Connection ca= cc.conexion();
            id_max_de_venta();
                PreparedStatement ps = ca.prepareStatement ("UPDATE descripcion_de_venta SET estado= '"+estadocancelado+"' WHERE id_venta='"+id+"'");
                ps.executeUpdate();
        }
        catch(Exception ex){
                           JOptionPane.showMessageDialog(null, "Error en venta" + ex.getMessage());
        } finally{
                    cc.getClose();
                }    
   }

                    public static void comprobar_venta_resagada(){ //verifica que haya o no una venta que fue cancelada previamente en la sesio anterior
        id_max_de_venta();
    int id_para_comprobacion = id_de_la_venta_incrementable;
        try {Connection ca= cc.conexion();
             sent = ca.createStatement();   
             rs = sent.executeQuery("select * from venta where id_venta= '"+id_para_comprobacion+"'");
            while(rs.next()){        
            totalcomprobacion=Integer.parseInt(rs.getString(5));
            }
            if(totalcomprobacion>0){
                //PRIMERO, CUANDO ES MAYOR A 0, QUIERE DECIR QUE LA VENTA ANTERIOR SE REALIZO
                //SEGUNDO, CUANDO LA VENTA ES IGUAL A 0 SE CANCELO
                //TERCERO, CUANDO TIENE TOTAL NEGATIVO SIGNIFICA QUE ES UNA VENTA A CREDITO PENDIENTE POR PAGAR
                block_unlock=true;
            }
        } catch (SQLException ex) {
            Logger.getLogger(menu_principal.class.getName()).log(Level.SEVERE, null, ex);
        }
        catch(NumberFormatException NFE){
                            block_unlock=false; //Se desactiva para que no se agregue otra venta al usuario en turno , así al hacer otra venta 
                            //se agregará a e ésta venta resagada
        }finally{
                    cc.getClose();
                }  
    }

                    public static void verificar_id_ingresadoalsistema(){ //verifica que haya o no una venta que fue cancelada previamente en la sesio anterior
        id_max_de_venta();
    int id_para_comprobacion = id_de_la_venta_incrementable, id=0;
        try {Connection ca= cc.conexion();
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
                }finally{
                    cc.getClose();
                }  
            }
           } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        catch(NumberFormatException NFE){                           
        }finally{
                    cc.getClose();
                }
    }
                public static void total_venta_enturno(){
        try{ Connection ca= cc.conexion();// La suma de todos los importes
           id_max_de_venta();
                                          sent  =(Statement)ca.createStatement();
                                           rs = sent.executeQuery("select SUM(importe) from descripcion_de_venta where id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and  estado = '"+estadoenturno+"'");
                                            while(rs.next()){
                                                      sumadeimportes =Float.parseFloat(rs.getString("SUM(importe)"));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                          sumadeimportes=0;
                                                      }finally{
                    cc.getClose();
                }// fin del precio-catch del producto
    }
     public void total_venta_creditopendiente(int id){
        try{ Connection ca= cc.conexion();// La suma de todos los importes
                                          sent  =(Statement)ca.createStatement();
                                           rs = sent.executeQuery("select total from venta where id_venta= '"+id+"'");
                                            while(rs.next()){
                                                      sumadeimportescreditopendiente =Float.parseFloat(rs.getString("total"));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                          sumadeimportescreditopendiente=0;
                                                      }finally{
                    cc.getClose();
                }// fin del precio-catch del producto
    }
       public void total_ventaporid(int id){
        try{ Connection ca= cc.conexion();// La suma de todos los importes
                                          sent  =(Statement)ca.createStatement();
                                           rs = sent.executeQuery("select total from venta where id_venta= '"+id+"'");
                                            while(rs.next()){
                                                      sumadeimportesparaeltotal =Float.parseFloat(rs.getString("total"));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                          sumadeimportesparaeltotal=0;
                                                      }finally{
                    cc.getClose();
                }// fin del precio-catch del producto
    }    
    // FIN DE METODOS PARA EL AREA DE VENTAS -----------------------------------------------------------------------------------------------------------------------------------------------------
       
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
        jPanel10 = new javax.swing.JPanel();
        cambiocombobox = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        jSeparator22 = new javax.swing.JSeparator();
        subtotal = new javax.swing.JLabel();
        jSeparator7 = new javax.swing.JSeparator();
        descuentolabel = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        jSeparator20 = new javax.swing.JSeparator();
        labeldescuento = new javax.swing.JLabel();
        descuentocombo = new javax.swing.JLabel();
        jSeparator21 = new javax.swing.JSeparator();
        monto = new javax.swing.JTextField();
        descuentolabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        user = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        user1 = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Reloj = new javax.swing.JLabel();
        Fecha = new javax.swing.JLabel();
        IblReloj = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        tablaventa = new rojerusan.RSTableMetro();
        total3 = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        Cortedecaja = new javax.swing.JButton();
        AgregarGastos = new javax.swing.JButton();
        descuento = new javax.swing.JButton();
        ventaacredito = new javax.swing.JButton();
        deletedescuento = new javax.swing.JButton();
        cleanall = new javax.swing.JButton();
        cobro = new javax.swing.JButton();
        Existencias = new javax.swing.JButton();
        jLabel74 = new javax.swing.JLabel();
        piezasparaacomplettarpollo = new javax.swing.JCheckBox();
        jTabbedPane1 = new javax.swing.JTabbedPane();
        CRUDO = new javax.swing.JPanel();
        crudo = new javax.swing.JButton();
        masdeunapiezacrudo = new javax.swing.JCheckBox();
        jButton42 = new javax.swing.JButton();
        jButton43 = new javax.swing.JButton();
        jButton44 = new javax.swing.JButton();
        jButton45 = new javax.swing.JButton();
        jButton46 = new javax.swing.JButton();
        jButton47 = new javax.swing.JButton();
        jButton48 = new javax.swing.JButton();
        jButton49 = new javax.swing.JButton();
        jButton50 = new javax.swing.JButton();
        jButton51 = new javax.swing.JButton();
        jButton52 = new javax.swing.JButton();
        jButton53 = new javax.swing.JButton();
        jButton54 = new javax.swing.JButton();
        jButton55 = new javax.swing.JButton();
        bones = new javax.swing.JButton();
        COCIDO = new javax.swing.JPanel();
        polloasado = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        masdeunapiezacocido = new javax.swing.JCheckBox();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jButton31 = new javax.swing.JButton();
        jButton32 = new javax.swing.JButton();
        jButton33 = new javax.swing.JButton();
        jButton34 = new javax.swing.JButton();
        jButton35 = new javax.swing.JButton();
        jButton36 = new javax.swing.JButton();
        jButton37 = new javax.swing.JButton();
        jButton38 = new javax.swing.JButton();
        jButton39 = new javax.swing.JButton();
        jButton40 = new javax.swing.JButton();
        jButton41 = new javax.swing.JButton();
        ACOMPAÑANTES = new javax.swing.JPanel();
        salsaguajillo = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        masdeunapieza = new javax.swing.JCheckBox();
        agregar_articulo = new javax.swing.JPanel();
        producto_sobrante3 = new javax.swing.JPanel();
        jPanel31 = new javax.swing.JPanel();
        jLabel83 = new javax.swing.JLabel();
        jLabel100 = new javax.swing.JLabel();
        jButton10 = new javax.swing.JButton();
        jLabel101 = new javax.swing.JLabel();
        jPanel32 = new javax.swing.JPanel();
        jScrollPane16 = new javax.swing.JScrollPane();
        JtablepaLaVenta = new rojerusan.RSTableMetro();
        ventascanceladaseneldia5 = new javax.swing.JLabel();
        agregar = new javax.swing.JButton();
        jPanel33 = new javax.swing.JPanel();
        jScrollPane17 = new javax.swing.JScrollPane();
        Jtable_ProductosEntradas = new rojerusan.RSTableMetro();
        jLabel102 = new javax.swing.JLabel();
        ventascanceladaseneldia3 = new javax.swing.JLabel();
        jPanel12 = new javax.swing.JPanel();
        Administrador = new javax.swing.JPanel();
        jPanel23 = new javax.swing.JPanel();
        jScrollPane6 = new javax.swing.JScrollPane();
        Jtable_ventasRealizadas = new rojerusan.RSTableMetro();
        veridventas = new javax.swing.JButton();
        jLabel91 = new javax.swing.JLabel();
        jLabel92 = new javax.swing.JLabel();
        fechainicial = new com.toedter.calendar.JDateChooser();
        fechafinal = new com.toedter.calendar.JDateChooser();
        buscarventasporfecha = new javax.swing.JButton();
        labelparaeltotal = new javax.swing.JLabel();
        jLabel99 = new javax.swing.JLabel();
        jLabel103 = new javax.swing.JLabel();
        totalventarealizada = new javax.swing.JLabel();
        jScrollPane7 = new javax.swing.JScrollPane();
        tabladeidventas = new rojerusan.RSTableMetro();
        jLabel63 = new javax.swing.JLabel();
        conteodelasventasrealizadas = new javax.swing.JLabel();
        jLabel96 = new javax.swing.JLabel();
        ventaseneldiasumadas = new javax.swing.JLabel();
        jScrollPane14 = new javax.swing.JScrollPane();
        jTable3 = new rojerusan.RSTableMetro();
        jScrollPane12 = new javax.swing.JScrollPane();
        jTable2 = new rojerusan.RSTableMetro();
        imprimirventa = new javax.swing.JButton();
        cancelarventa = new javax.swing.JButton();
        jPanel24 = new javax.swing.JPanel();
        jLabel93 = new javax.swing.JLabel();
        jLabel94 = new javax.swing.JLabel();
        jButton8 = new javax.swing.JButton();
        jLabel95 = new javax.swing.JLabel();
        jPanel26 = new javax.swing.JPanel();
        jScrollPane13 = new javax.swing.JScrollPane();
        ventasacreditopendiente = new rojerusan.RSTableMetro();
        deudor = new javax.swing.JLabel();
        veridventasacreditopendiente = new javax.swing.JButton();
        pagarventaacredito = new javax.swing.JButton();
        totalventacreditoenturno = new javax.swing.JLabel();
        labelcredito = new javax.swing.JLabel();
        labelnombre = new javax.swing.JLabel();

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
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Proveedores9.setFont(new java.awt.Font("Tahoma", 1, 19)); // NOI18N

        venta.setBackground(new java.awt.Color(0, 51, 102));
        venta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(0, 51, 102));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Venta   ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cambiocombobox.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        cambiocombobox.setForeground(new java.awt.Color(255, 0, 0));
        cambiocombobox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cambiocombobox.setText("00.00");
        jPanel10.add(cambiocombobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, 140, 28));

        jLabel31.setFont(new java.awt.Font("Trebuchet MS", 1, 22)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/camb5.png"))); // NOI18N
        jLabel31.setText("Cambio:");
        jPanel10.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 140, -1));

        jLabel30.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/total.png"))); // NOI18N
        jLabel30.setText("Subtotal");
        jLabel30.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel10.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, 150, -1));
        jPanel10.add(jSeparator22, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 270, 140, 10));

        subtotal.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        subtotal.setForeground(new java.awt.Color(255, 255, 255));
        subtotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subtotal.setText("00.00");
        jPanel10.add(subtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 30, 140, 28));
        jPanel10.add(jSeparator7, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 60, 140, 10));

        descuentolabel.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        descuentolabel.setForeground(new java.awt.Color(255, 255, 255));
        descuentolabel.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/flecha-hacia-la-izquierda (1).png"))); // NOI18N
        descuentolabel.setText("Pago");
        descuentolabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel10.add(descuentolabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 190, -1));

        total.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        total.setForeground(new java.awt.Color(255, 255, 255));
        total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total.setText("00.00");
        jPanel10.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 150, 50));
        jPanel10.add(jSeparator20, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 170, 140, 10));

        labeldescuento.setFont(new java.awt.Font("Trebuchet MS", 1, 22)); // NOI18N
        labeldescuento.setForeground(new java.awt.Color(255, 255, 255));
        labeldescuento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/005-discount.png"))); // NOI18N
        labeldescuento.setText("Descuento:");
        jPanel10.add(labeldescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 90, 170, -1));

        descuentocombo.setFont(new java.awt.Font("Arial Black", 1, 28)); // NOI18N
        descuentocombo.setForeground(new java.awt.Color(255, 0, 51));
        descuentocombo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descuentocombo.setText("00.00");
        jPanel10.add(descuentocombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 80, 140, -1));
        jPanel10.add(jSeparator21, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 120, 140, 10));

        monto.setBackground(new java.awt.Color(0, 148, 204));
        monto.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
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
        monto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                montoKeyReleased(evt);
            }
        });
        jPanel10.add(monto, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 150, -1));

        descuentolabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        descuentolabel1.setForeground(new java.awt.Color(255, 255, 255));
        descuentolabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/total.png"))); // NOI18N
        descuentolabel1.setText("Total");
        descuentolabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel10.add(descuentolabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 190, -1));

        venta.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 450, 280));

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

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/salir-flecha-derecha (1).png"))); // NOI18N
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
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

        tablaventa = new rojerusan.RSTableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        tablaventa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        tablaventa.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tablaventa.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tablaventa.setColorSelForeground(new java.awt.Color(0, 0, 0));
        tablaventa.setGrosorBordeFilas(0);
        tablaventa.setGrosorBordeHead(0);
        tablaventa.setMultipleSeleccion(false);
        tablaventa.setRowHeight(25);
        tablaventa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaventaMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(tablaventa);

        venta.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 440, 290));

        total3.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        total3.setForeground(new java.awt.Color(255, 255, 255));
        total3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total3.setText("00.00");
        venta.add(total3, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 30, 140, 28));

        jPanel27.setBackground(new java.awt.Color(0, 51, 102));
        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Otras opciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Cortedecaja.setBackground(new java.awt.Color(0, 51, 102));
        Cortedecaja.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Cortedecaja.setForeground(new java.awt.Color(255, 255, 255));
        Cortedecaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/004-cash register.png"))); // NOI18N
        Cortedecaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CortedecajaActionPerformed(evt);
            }
        });
        jPanel27.add(Cortedecaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 60));

        AgregarGastos.setBackground(new java.awt.Color(0, 51, 102));
        AgregarGastos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        AgregarGastos.setForeground(new java.awt.Color(255, 255, 255));
        AgregarGastos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/combustible (1).png"))); // NOI18N
        AgregarGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarGastosActionPerformed(evt);
            }
        });
        jPanel27.add(AgregarGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, 60));

        descuento.setBackground(new java.awt.Color(0, 51, 102));
        descuento.setFont(new java.awt.Font("Arial Black", 1, 23)); // NOI18N
        descuento.setForeground(new java.awt.Color(255, 255, 255));
        descuento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/005-discount.png"))); // NOI18N
        descuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descuentoActionPerformed(evt);
            }
        });
        jPanel27.add(descuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 60));

        ventaacredito.setBackground(new java.awt.Color(0, 51, 102));
        ventaacredito.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        ventaacredito.setForeground(new java.awt.Color(255, 255, 255));
        ventaacredito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/dinero (1).png"))); // NOI18N
        ventaacredito.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ventaacredito.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        ventaacredito.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ventaacredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventaacreditoActionPerformed(evt);
            }
        });
        jPanel27.add(ventaacredito, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 60, 60));

        deletedescuento.setBackground(new java.awt.Color(255, 0, 0));
        deletedescuento.setFont(new java.awt.Font("Arial Black", 1, 23)); // NOI18N
        deletedescuento.setForeground(new java.awt.Color(255, 51, 51));
        deletedescuento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/005-discount.png"))); // NOI18N
        deletedescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletedescuentoActionPerformed(evt);
            }
        });
        jPanel27.add(deletedescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, 60));

        cleanall.setBackground(new java.awt.Color(0, 51, 102));
        cleanall.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        cleanall.setForeground(new java.awt.Color(255, 255, 255));
        cleanall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/cancelar2.png"))); // NOI18N
        cleanall.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cleanall.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        cleanall.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cleanall.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cleanallMouseClicked(evt);
            }
        });
        cleanall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanallActionPerformed(evt);
            }
        });
        jPanel27.add(cleanall, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 60, 60));

        cobro.setBackground(new java.awt.Color(0, 51, 102));
        cobro.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        cobro.setForeground(new java.awt.Color(255, 255, 255));
        cobro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/flecha-hacia-la-izquierda (1).png"))); // NOI18N
        cobro.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cobro.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        cobro.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cobro.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cobroActionPerformed(evt);
            }
        });
        jPanel27.add(cobro, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 240, 150, 40));

        venta.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(490, 380, 170, 290));

        Existencias.setBackground(new java.awt.Color(255, 255, 255));
        Existencias.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Existencias.setForeground(new java.awt.Color(204, 0, 0));
        Existencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/actualizar.png"))); // NOI18N
        Existencias.setText("¿?");
        Existencias.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Existencias.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Existencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExistenciasActionPerformed(evt);
            }
        });
        venta.add(Existencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(540, 80, 80, 90));

        jLabel74.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(255, 255, 255));
        jLabel74.setText("En venta :");
        venta.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 120, -1));

        piezasparaacomplettarpollo.setBackground(new java.awt.Color(0, 51, 102));
        piezasparaacomplettarpollo.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        piezasparaacomplettarpollo.setForeground(new java.awt.Color(255, 255, 255));
        piezasparaacomplettarpollo.setText("Completar pollo");
        venta.add(piezasparaacomplettarpollo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 180, -1));

        jTabbedPane1.setForeground(new java.awt.Color(255, 255, 255));
        jTabbedPane1.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N

        CRUDO.setBackground(new java.awt.Color(0, 51, 102));
        CRUDO.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        CRUDO.setLayout(null);

        crudo.setBackground(new java.awt.Color(255, 255, 255));
        crudo.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        crudo.setForeground(new java.awt.Color(255, 0, 0));
        crudo.setText("pollo crudo");
        crudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                crudoActionPerformed(evt);
            }
        });
        CRUDO.add(crudo);
        crudo.setBounds(6, 54, 172, 80);

        masdeunapiezacrudo.setBackground(new java.awt.Color(0, 51, 102));
        masdeunapiezacrudo.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        masdeunapiezacrudo.setForeground(new java.awt.Color(255, 255, 255));
        masdeunapiezacrudo.setText("Agregar más de una pieza");
        CRUDO.add(masdeunapiezacrudo);
        masdeunapiezacrudo.setBounds(161, 6, 325, 36);

        jButton42.setBackground(new java.awt.Color(255, 255, 255));
        jButton42.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton42.setForeground(new java.awt.Color(255, 0, 0));
        jButton42.setText("Medio pollo");
        jButton42.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton42ActionPerformed(evt);
            }
        });
        CRUDO.add(jButton42);
        jButton42.setBounds(230, 50, 170, 81);

        jButton43.setBackground(new java.awt.Color(255, 255, 255));
        jButton43.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton43.setForeground(new java.awt.Color(255, 0, 0));
        jButton43.setText("Media pechuga");
        jButton43.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton43ActionPerformed(evt);
            }
        });
        CRUDO.add(jButton43);
        jButton43.setBounds(430, 50, 160, 81);

        jButton44.setBackground(new java.awt.Color(255, 255, 255));
        jButton44.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton44.setForeground(new java.awt.Color(255, 0, 0));
        jButton44.setText("Pechuga");
        jButton44.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton44ActionPerformed(evt);
            }
        });
        CRUDO.add(jButton44);
        jButton44.setBounds(430, 150, 160, 77);

        jButton45.setBackground(new java.awt.Color(255, 255, 255));
        jButton45.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton45.setForeground(new java.awt.Color(255, 0, 0));
        jButton45.setText("Hua completo");
        jButton45.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton45ActionPerformed(evt);
            }
        });
        CRUDO.add(jButton45);
        jButton45.setBounds(230, 150, 170, 76);

        jButton46.setBackground(new java.awt.Color(255, 255, 255));
        jButton46.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton46.setForeground(new java.awt.Color(255, 0, 0));
        jButton46.setText("Pierna completa");
        jButton46.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton46ActionPerformed(evt);
            }
        });
        CRUDO.add(jButton46);
        jButton46.setBounds(10, 150, 172, 77);

        jButton47.setBackground(new java.awt.Color(255, 255, 255));
        jButton47.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton47.setForeground(new java.awt.Color(255, 0, 0));
        jButton47.setText("Muslo");
        jButton47.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton47ActionPerformed(evt);
            }
        });
        CRUDO.add(jButton47);
        jButton47.setBounds(10, 240, 170, 74);

        jButton48.setBackground(new java.awt.Color(255, 255, 255));
        jButton48.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton48.setForeground(new java.awt.Color(255, 0, 0));
        jButton48.setText("Pierna");
        jButton48.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton48ActionPerformed(evt);
            }
        });
        CRUDO.add(jButton48);
        jButton48.setBounds(230, 240, 170, 74);

        jButton49.setBackground(new java.awt.Color(255, 255, 255));
        jButton49.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton49.setForeground(new java.awt.Color(255, 0, 0));
        jButton49.setText("Ala");
        jButton49.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton49ActionPerformed(evt);
            }
        });
        CRUDO.add(jButton49);
        jButton49.setBounds(430, 240, 158, 74);

        jButton50.setBackground(new java.awt.Color(255, 255, 255));
        jButton50.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton50.setForeground(new java.awt.Color(255, 0, 0));
        jButton50.setText("Cabeza");
        jButton50.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton50ActionPerformed(evt);
            }
        });
        CRUDO.add(jButton50);
        jButton50.setBounds(430, 330, 160, 70);

        jButton51.setBackground(new java.awt.Color(255, 255, 255));
        jButton51.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton51.setForeground(new java.awt.Color(255, 0, 0));
        jButton51.setText("Cadera");
        jButton51.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton51ActionPerformed(evt);
            }
        });
        CRUDO.add(jButton51);
        jButton51.setBounds(230, 330, 170, 70);

        jButton52.setBackground(new java.awt.Color(255, 255, 255));
        jButton52.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton52.setForeground(new java.awt.Color(255, 0, 0));
        jButton52.setText("Huacal");
        jButton52.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton52ActionPerformed(evt);
            }
        });
        CRUDO.add(jButton52);
        jButton52.setBounds(10, 330, 170, 75);

        jButton53.setBackground(new java.awt.Color(255, 255, 255));
        jButton53.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton53.setForeground(new java.awt.Color(255, 0, 0));
        jButton53.setText("Molleja");
        jButton53.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton53ActionPerformed(evt);
            }
        });
        CRUDO.add(jButton53);
        jButton53.setBounds(10, 420, 170, 69);

        jButton54.setBackground(new java.awt.Color(255, 255, 255));
        jButton54.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton54.setForeground(new java.awt.Color(255, 0, 0));
        jButton54.setText("Patas");
        jButton54.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton54ActionPerformed(evt);
            }
        });
        CRUDO.add(jButton54);
        jButton54.setBounds(230, 420, 170, 69);

        jButton55.setBackground(new java.awt.Color(255, 255, 255));
        jButton55.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton55.setForeground(new java.awt.Color(255, 0, 0));
        jButton55.setText("Pech./ bisteck");
        jButton55.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton55ActionPerformed(evt);
            }
        });
        CRUDO.add(jButton55);
        jButton55.setBounds(430, 420, 160, 69);

        bones.setBackground(new java.awt.Color(255, 255, 255));
        bones.setFont(new java.awt.Font("Tahoma", 1, 16)); // NOI18N
        bones.setForeground(new java.awt.Color(255, 0, 0));
        bones.setText("Huesitos");
        bones.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bonesActionPerformed(evt);
            }
        });
        CRUDO.add(bones);
        bones.setBounds(10, 500, 160, 40);

        jTabbedPane1.addTab("CRUDO", CRUDO);

        COCIDO.setBackground(new java.awt.Color(0, 51, 102));
        COCIDO.setFont(new java.awt.Font("Dialog", 0, 36)); // NOI18N

        polloasado.setBackground(new java.awt.Color(255, 255, 255));
        polloasado.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        polloasado.setForeground(new java.awt.Color(255, 0, 0));
        polloasado.setText("Pollo asado");
        polloasado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                polloasadoActionPerformed(evt);
            }
        });

        jButton23.setBackground(new java.awt.Color(255, 255, 255));
        jButton23.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton23.setForeground(new java.awt.Color(255, 0, 0));
        jButton23.setText("Rostizado");
        jButton23.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton23ActionPerformed(evt);
            }
        });

        masdeunapiezacocido.setBackground(new java.awt.Color(0, 51, 102));
        masdeunapiezacocido.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        masdeunapiezacocido.setForeground(new java.awt.Color(255, 255, 255));
        masdeunapiezacocido.setText("Agregar más de una pieza");

        jButton24.setBackground(new java.awt.Color(255, 255, 255));
        jButton24.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton24.setForeground(new java.awt.Color(255, 0, 0));
        jButton24.setText("Pech. broaster");
        jButton24.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton24ActionPerformed(evt);
            }
        });

        jButton25.setBackground(new java.awt.Color(255, 255, 255));
        jButton25.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton25.setForeground(new java.awt.Color(255, 0, 0));
        jButton25.setText("Pierna broast.");
        jButton25.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton25ActionPerformed(evt);
            }
        });

        jButton26.setBackground(new java.awt.Color(255, 255, 255));
        jButton26.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton26.setForeground(new java.awt.Color(255, 0, 0));
        jButton26.setText("Medio asado");
        jButton26.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton26ActionPerformed(evt);
            }
        });

        jButton27.setBackground(new java.awt.Color(255, 255, 255));
        jButton27.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton27.setForeground(new java.awt.Color(255, 0, 0));
        jButton27.setText("Medio rostizado");
        jButton27.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton27ActionPerformed(evt);
            }
        });

        jButton28.setBackground(new java.awt.Color(255, 255, 255));
        jButton28.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton28.setForeground(new java.awt.Color(255, 0, 0));
        jButton28.setText("Cuar. rostizado");
        jButton28.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton28ActionPerformed(evt);
            }
        });

        jButton29.setBackground(new java.awt.Color(255, 255, 255));
        jButton29.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton29.setForeground(new java.awt.Color(255, 0, 0));
        jButton29.setText("Cuarto asado");
        jButton29.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton29ActionPerformed(evt);
            }
        });

        jButton30.setBackground(new java.awt.Color(255, 255, 255));
        jButton30.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton30.setForeground(new java.awt.Color(255, 0, 0));
        jButton30.setText("Ala broaster");
        jButton30.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton30ActionPerformed(evt);
            }
        });

        jButton31.setBackground(new java.awt.Color(255, 255, 255));
        jButton31.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton31.setForeground(new java.awt.Color(255, 0, 0));
        jButton31.setText("Nuggets");
        jButton31.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton31ActionPerformed(evt);
            }
        });

        jButton32.setBackground(new java.awt.Color(255, 255, 255));
        jButton32.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton32.setForeground(new java.awt.Color(255, 0, 0));
        jButton32.setText("Barbacoa de pollo");
        jButton32.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton32ActionPerformed(evt);
            }
        });

        jButton33.setBackground(new java.awt.Color(255, 255, 255));
        jButton33.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton33.setForeground(new java.awt.Color(255, 0, 0));
        jButton33.setText("Muslo broast.");
        jButton33.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton33ActionPerformed(evt);
            }
        });

        jButton34.setBackground(new java.awt.Color(255, 255, 255));
        jButton34.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton34.setForeground(new java.awt.Color(255, 0, 0));
        jButton34.setText("Alitas bbq");
        jButton34.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton34ActionPerformed(evt);
            }
        });

        jButton35.setBackground(new java.awt.Color(255, 255, 255));
        jButton35.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton35.setForeground(new java.awt.Color(255, 0, 0));
        jButton35.setText("Tacos");
        jButton35.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton35ActionPerformed(evt);
            }
        });

        jButton36.setBackground(new java.awt.Color(255, 255, 255));
        jButton36.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton36.setForeground(new java.awt.Color(255, 0, 0));
        jButton36.setText("Brochetas");
        jButton36.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton36ActionPerformed(evt);
            }
        });

        jButton37.setBackground(new java.awt.Color(255, 255, 255));
        jButton37.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton37.setForeground(new java.awt.Color(255, 0, 0));
        jButton37.setText("Mole verde");
        jButton37.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton37ActionPerformed(evt);
            }
        });

        jButton38.setBackground(new java.awt.Color(255, 255, 255));
        jButton38.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton38.setForeground(new java.awt.Color(255, 0, 0));
        jButton38.setText("Mole rojo");
        jButton38.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton38ActionPerformed(evt);
            }
        });

        jButton39.setBackground(new java.awt.Color(255, 255, 255));
        jButton39.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton39.setForeground(new java.awt.Color(255, 0, 0));
        jButton39.setText("Mininuggets");
        jButton39.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton39ActionPerformed(evt);
            }
        });

        jButton40.setBackground(new java.awt.Color(255, 255, 255));
        jButton40.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton40.setForeground(new java.awt.Color(255, 0, 0));
        jButton40.setText("Longaniza");
        jButton40.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton40ActionPerformed(evt);
            }
        });

        jButton41.setBackground(new java.awt.Color(255, 255, 255));
        jButton41.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton41.setForeground(new java.awt.Color(255, 0, 0));
        jButton41.setText("Miel");
        jButton41.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton41ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout COCIDOLayout = new javax.swing.GroupLayout(COCIDO);
        COCIDO.setLayout(COCIDOLayout);
        COCIDOLayout.setHorizontalGroup(
            COCIDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, COCIDOLayout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(masdeunapiezacocido)
                .addGap(122, 122, 122))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, COCIDOLayout.createSequentialGroup()
                .addGroup(COCIDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(COCIDOLayout.createSequentialGroup()
                        .addGroup(COCIDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                            .addComponent(jButton28, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton27, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton23, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton37, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(COCIDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(polloasado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton29, javax.swing.GroupLayout.DEFAULT_SIZE, 136, Short.MAX_VALUE)
                            .addComponent(jButton38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButton41, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 187, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(COCIDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton33, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton34, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(COCIDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton31, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton36, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE)
                    .addComponent(jButton39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 140, Short.MAX_VALUE))
                .addGap(8, 8, 8))
        );
        COCIDOLayout.setVerticalGroup(
            COCIDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, COCIDOLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(masdeunapiezacocido)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(COCIDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton23, javax.swing.GroupLayout.DEFAULT_SIZE, 71, Short.MAX_VALUE)
                    .addComponent(polloasado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton24, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton31, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(COCIDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton27, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                    .addComponent(jButton26, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton25, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton35, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(COCIDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton28, javax.swing.GroupLayout.DEFAULT_SIZE, 66, Short.MAX_VALUE)
                    .addComponent(jButton29, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton30, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton36, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(12, 12, 12)
                .addGroup(COCIDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jButton33, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton40, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton38, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton39, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(COCIDOLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton37, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton41, javax.swing.GroupLayout.PREFERRED_SIZE, 63, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton34, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jButton32, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(80, Short.MAX_VALUE))
        );

        jTabbedPane1.addTab("COCIDO", COCIDO);

        ACOMPAÑANTES.setBackground(new java.awt.Color(0, 51, 102));

        salsaguajillo.setBackground(new java.awt.Color(255, 255, 255));
        salsaguajillo.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        salsaguajillo.setForeground(new java.awt.Color(255, 0, 0));
        salsaguajillo.setText("Salsa guajillo");
        salsaguajillo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                salsaguajilloActionPerformed(evt);
            }
        });

        jButton5.setBackground(new java.awt.Color(255, 255, 255));
        jButton5.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton5.setForeground(new java.awt.Color(255, 0, 0));
        jButton5.setText("Spa blanco");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton7.setBackground(new java.awt.Color(255, 255, 255));
        jButton7.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton7.setForeground(new java.awt.Color(255, 0, 0));
        jButton7.setText("Arroz blanco");
        jButton7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton7ActionPerformed(evt);
            }
        });

        jButton9.setBackground(new java.awt.Color(255, 255, 255));
        jButton9.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton9.setForeground(new java.awt.Color(255, 0, 0));
        jButton9.setText("Arroz rojo");
        jButton9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton9ActionPerformed(evt);
            }
        });

        jButton11.setBackground(new java.awt.Color(255, 255, 255));
        jButton11.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton11.setForeground(new java.awt.Color(255, 0, 0));
        jButton11.setText("Spagueti rojo");
        jButton11.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton11ActionPerformed(evt);
            }
        });

        jButton12.setBackground(new java.awt.Color(255, 255, 255));
        jButton12.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton12.setForeground(new java.awt.Color(255, 0, 0));
        jButton12.setText("Frij puercos");
        jButton12.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton12ActionPerformed(evt);
            }
        });

        jButton13.setBackground(new java.awt.Color(255, 255, 255));
        jButton13.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton13.setForeground(new java.awt.Color(255, 0, 0));
        jButton13.setText("Cochinita");
        jButton13.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton13ActionPerformed(evt);
            }
        });

        jButton14.setBackground(new java.awt.Color(255, 255, 255));
        jButton14.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton14.setForeground(new java.awt.Color(255, 0, 0));
        jButton14.setText("Frij charros");
        jButton14.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton14ActionPerformed(evt);
            }
        });

        jButton15.setBackground(new java.awt.Color(255, 255, 255));
        jButton15.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton15.setForeground(new java.awt.Color(255, 0, 0));
        jButton15.setText("Frij peruanos");
        jButton15.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton15ActionPerformed(evt);
            }
        });

        jButton16.setBackground(new java.awt.Color(255, 255, 255));
        jButton16.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton16.setForeground(new java.awt.Color(255, 0, 0));
        jButton16.setText("Pure");
        jButton16.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton16ActionPerformed(evt);
            }
        });

        jButton17.setBackground(new java.awt.Color(255, 255, 255));
        jButton17.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton17.setForeground(new java.awt.Color(255, 0, 0));
        jButton17.setText("Ens. manzana");
        jButton17.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton17ActionPerformed(evt);
            }
        });

        jButton18.setBackground(new java.awt.Color(255, 255, 255));
        jButton18.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton18.setForeground(new java.awt.Color(255, 0, 0));
        jButton18.setText("Ens. de col");
        jButton18.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton18ActionPerformed(evt);
            }
        });

        jButton19.setBackground(new java.awt.Color(255, 255, 255));
        jButton19.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton19.setForeground(new java.awt.Color(255, 0, 0));
        jButton19.setText("Chiles en vinagre");
        jButton19.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton19ActionPerformed(evt);
            }
        });

        jButton20.setBackground(new java.awt.Color(255, 255, 255));
        jButton20.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton20.setForeground(new java.awt.Color(255, 0, 0));
        jButton20.setText("Pasta de codo");
        jButton20.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton20ActionPerformed(evt);
            }
        });

        jButton21.setBackground(new java.awt.Color(255, 255, 255));
        jButton21.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton21.setForeground(new java.awt.Color(255, 0, 0));
        jButton21.setText("Ensalada rusa");
        jButton21.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton21ActionPerformed(evt);
            }
        });

        jButton22.setBackground(new java.awt.Color(255, 255, 255));
        jButton22.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        jButton22.setForeground(new java.awt.Color(255, 0, 0));
        jButton22.setText("Ver. encurtidas");
        jButton22.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton22ActionPerformed(evt);
            }
        });

        masdeunapieza.setBackground(new java.awt.Color(0, 51, 102));
        masdeunapieza.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        masdeunapieza.setForeground(new java.awt.Color(255, 255, 255));
        masdeunapieza.setText("Agregar más de una pieza");

        javax.swing.GroupLayout ACOMPAÑANTESLayout = new javax.swing.GroupLayout(ACOMPAÑANTES);
        ACOMPAÑANTES.setLayout(ACOMPAÑANTESLayout);
        ACOMPAÑANTESLayout.setHorizontalGroup(
            ACOMPAÑANTESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(ACOMPAÑANTESLayout.createSequentialGroup()
                .addGroup(ACOMPAÑANTESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ACOMPAÑANTESLayout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(ACOMPAÑANTESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ACOMPAÑANTESLayout.createSequentialGroup()
                                .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 156, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(jButton22)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(ACOMPAÑANTESLayout.createSequentialGroup()
                                .addGroup(ACOMPAÑANTESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(ACOMPAÑANTESLayout.createSequentialGroup()
                                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton12, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(ACOMPAÑANTESLayout.createSequentialGroup()
                                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jButton17, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                    .addGroup(ACOMPAÑANTESLayout.createSequentialGroup()
                                        .addComponent(salsaguajillo, javax.swing.GroupLayout.PREFERRED_SIZE, 152, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(3, 3, 3)
                                        .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 159, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(ACOMPAÑANTESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(jButton15, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton5, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addGroup(ACOMPAÑANTESLayout.createSequentialGroup()
                                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 134, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(0, 0, Short.MAX_VALUE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(ACOMPAÑANTESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(jButton7, javax.swing.GroupLayout.DEFAULT_SIZE, 129, Short.MAX_VALUE)
                                    .addComponent(jButton14, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(jButton18, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(68, 68, 68))))
                    .addGroup(ACOMPAÑANTESLayout.createSequentialGroup()
                        .addGroup(ACOMPAÑANTESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(ACOMPAÑANTESLayout.createSequentialGroup()
                                .addGap(153, 153, 153)
                                .addComponent(masdeunapieza))
                            .addGroup(ACOMPAÑANTESLayout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 162, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        ACOMPAÑANTESLayout.setVerticalGroup(
            ACOMPAÑANTESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ACOMPAÑANTESLayout.createSequentialGroup()
                .addComponent(masdeunapieza)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(ACOMPAÑANTESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(salsaguajillo, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(30, 30, 30)
                .addGroup(ACOMPAÑANTESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                    .addGroup(ACOMPAÑANTESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 67, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jButton15, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jButton14, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(35, 35, 35)
                .addGroup(ACOMPAÑANTESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(ACOMPAÑANTESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, ACOMPAÑANTESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 66, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(26, 26, 26)
                .addGroup(ACOMPAÑANTESLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton21, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, ACOMPAÑANTESLayout.createSequentialGroup()
                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 58, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jButton20, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 76, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(107, 107, 107))
        );

        jTabbedPane1.addTab("ACOMPAÑANTES", ACOMPAÑANTES);

        venta.add(jTabbedPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(680, 70, 600, 630));
        jTabbedPane1.getAccessibleContext().setAccessibleName("ventas\n");

        Proveedores9.addTab("      Venta      ", venta);

        agregar_articulo.setBackground(new java.awt.Color(0, 51, 102));
        agregar_articulo.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        agregar_articulo.setDoubleBuffered(false);
        agregar_articulo.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        producto_sobrante3.setBackground(new java.awt.Color(0, 51, 102));
        producto_sobrante3.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        producto_sobrante3.setDoubleBuffered(false);
        producto_sobrante3.setLayout(null);

        jPanel31.setBackground(new java.awt.Color(255, 255, 255));

        jLabel83.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel83.setText("Entradas");

        jLabel100.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/portapapeles.png"))); // NOI18N

        jButton10.setBackground(new java.awt.Color(255, 255, 255));
        jButton10.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton10.setForeground(new java.awt.Color(255, 0, 0));
        jButton10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/salir-flecha-derecha (1).png"))); // NOI18N
        jButton10.setText("Salir");
        jButton10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton10ActionPerformed(evt);
            }
        });

        jLabel101.setBackground(new java.awt.Color(0, 160, 204));
        jLabel101.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jLabel101.setForeground(new java.awt.Color(255, 255, 255));
        jLabel101.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/bloggif_5bd54d091a235.jpeg"))); // NOI18N

        javax.swing.GroupLayout jPanel31Layout = new javax.swing.GroupLayout(jPanel31);
        jPanel31.setLayout(jPanel31Layout);
        jPanel31Layout.setHorizontalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel31Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jLabel101)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
                .addComponent(jLabel83, javax.swing.GroupLayout.PREFERRED_SIZE, 324, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(414, 414, 414)
                .addComponent(jButton10)
                .addGap(41, 41, 41))
        );
        jPanel31Layout.setVerticalGroup(
            jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel31Layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel83, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel31Layout.createSequentialGroup()
                        .addGroup(jPanel31Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel100, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE)
                            .addComponent(jLabel101)
                            .addGroup(jPanel31Layout.createSequentialGroup()
                                .addGap(6, 6, 6)
                                .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addContainerGap())))
        );

        producto_sobrante3.add(jPanel31);
        jPanel31.setBounds(0, 0, 1288, 66);

        jPanel32.setBackground(new java.awt.Color(0, 51, 102));
        jPanel32.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Inventario actualizado", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel32.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        JtablepaLaVenta = new rojerusan.RSTableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        JtablepaLaVenta.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "nombre_producto", "precio", "cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        JtablepaLaVenta.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        JtablepaLaVenta.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        JtablepaLaVenta.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JtablepaLaVenta.setFuenteFilas(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        JtablepaLaVenta.setGrosorBordeFilas(0);
        JtablepaLaVenta.setGrosorBordeHead(0);
        JtablepaLaVenta.setMultipleSeleccion(false);
        JtablepaLaVenta.setRowHeight(30);
        jScrollPane16.setViewportView(JtablepaLaVenta);

        jPanel32.add(jScrollPane16, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 650, 430));

        ventascanceladaseneldia5.setFont(new java.awt.Font("Trebuchet MS", 1, 22)); // NOI18N
        ventascanceladaseneldia5.setForeground(new java.awt.Color(255, 0, 0));
        ventascanceladaseneldia5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ventascanceladaseneldia5.setText("productos en existencia en inventario del dia de hoy");
        jPanel32.add(ventascanceladaseneldia5, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 650, 50));

        agregar.setBackground(new java.awt.Color(0, 51, 102));
        agregar.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        agregar.setForeground(new java.awt.Color(0, 255, 102));
        agregar.setText("Agregar producto externo");
        agregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                agregarActionPerformed(evt);
            }
        });
        jPanel32.add(agregar, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 290, 40));

        producto_sobrante3.add(jPanel32);
        jPanel32.setBounds(610, 80, 670, 570);

        jPanel33.setBackground(new java.awt.Color(0, 51, 102));
        jPanel33.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Entrada piezas de productos a inventario   ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel33.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Jtable_ProductosEntradas = new rojerusan.RSTableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                if(columnas==1){
                    return true;
                } else{
                    return false;
                }
            }
        };
        Jtable_ProductosEntradas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "nombre_producto", "cantidad"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                true, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Jtable_ProductosEntradas.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        Jtable_ProductosEntradas.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        Jtable_ProductosEntradas.setFuenteFilas(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        Jtable_ProductosEntradas.setGrosorBordeFilas(0);
        Jtable_ProductosEntradas.setGrosorBordeHead(0);
        Jtable_ProductosEntradas.setMultipleSeleccion(false);
        Jtable_ProductosEntradas.setName(""); // NOI18N
        Jtable_ProductosEntradas.setRowHeight(30);
        Jtable_ProductosEntradas.getTableHeader().setReorderingAllowed(false);
        jScrollPane17.setViewportView(Jtable_ProductosEntradas);

        jPanel33.add(jScrollPane17, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 110, 580, 430));

        jLabel102.setFont(new java.awt.Font("Trebuchet MS", 1, 22)); // NOI18N
        jLabel102.setForeground(new java.awt.Color(255, 0, 0));
        jLabel102.setText("   Ingresar cantidad de piezas a cada producto para ");
        jPanel33.add(jLabel102, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 550, 50));

        ventascanceladaseneldia3.setFont(new java.awt.Font("Trebuchet MS", 1, 22)); // NOI18N
        ventascanceladaseneldia3.setForeground(new java.awt.Color(255, 0, 0));
        ventascanceladaseneldia3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ventascanceladaseneldia3.setText("el inventario de hoy");
        jPanel33.add(ventascanceladaseneldia3, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 60, 530, 50));

        producto_sobrante3.add(jPanel33);
        jPanel33.setBounds(0, 80, 600, 570);

        agregar_articulo.add(producto_sobrante3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 670));

        Proveedores9.addTab("      Entradas Productos      ", agregar_articulo);

        Administrador.setBackground(new java.awt.Color(0, 51, 102));
        Administrador.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        Administrador.setDoubleBuffered(false);
        Administrador.setLayout(null);

        jPanel23.setBackground(new java.awt.Color(0, 51, 102));
        jPanel23.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ventas de hoy realizadas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel23.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Jtable_ventasRealizadas = new rojerusan.RSTableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        Jtable_ventasRealizadas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "nombre_producto", "cantidad", "importe", "total"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        Jtable_ventasRealizadas.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        Jtable_ventasRealizadas.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        Jtable_ventasRealizadas.setColorSelForeground(new java.awt.Color(0, 0, 0));
        Jtable_ventasRealizadas.setGrosorBordeFilas(0);
        Jtable_ventasRealizadas.setGrosorBordeHead(0);
        Jtable_ventasRealizadas.setMultipleSeleccion(false);
        Jtable_ventasRealizadas.setRowHeight(25);
        Jtable_ventasRealizadas.setSelectionBackground(new java.awt.Color(204, 0, 51));
        jScrollPane6.setViewportView(Jtable_ventasRealizadas);

        jPanel23.add(jScrollPane6, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 70, 10, 0));

        veridventas.setBackground(new java.awt.Color(0, 51, 102));
        veridventas.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        veridventas.setForeground(new java.awt.Color(255, 255, 255));
        veridventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/flecha-hacia-la-izquierda (1).png"))); // NOI18N
        veridventas.setText("Ver las ventas");
        veridventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                veridventasActionPerformed(evt);
            }
        });
        jPanel23.add(veridventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(1060, 70, -1, -1));

        jLabel91.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel91.setForeground(new java.awt.Color(255, 255, 255));
        jLabel91.setText("Hasta");
        jPanel23.add(jLabel91, new org.netbeans.lib.awtextra.AbsoluteConstraints(880, 20, 60, 50));

        jLabel92.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel92.setForeground(new java.awt.Color(255, 255, 255));
        jLabel92.setText("Productos vendidos hoy:");
        jPanel23.add(jLabel92, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 30, 280, 50));

        fechainicial.setBackground(new java.awt.Color(0, 51, 102));
        fechainicial.setForeground(new java.awt.Color(0, 96, 255));
        fechainicial.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jPanel23.add(fechainicial, new org.netbeans.lib.awtextra.AbsoluteConstraints(700, 20, 160, 40));

        fechafinal.setBackground(new java.awt.Color(0, 51, 102));
        fechafinal.setForeground(new java.awt.Color(0, 96, 255));
        fechafinal.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jPanel23.add(fechafinal, new org.netbeans.lib.awtextra.AbsoluteConstraints(940, 20, 160, 40));

        buscarventasporfecha.setBackground(new java.awt.Color(0, 51, 102));
        buscarventasporfecha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        buscarventasporfecha.setForeground(new java.awt.Color(255, 255, 255));
        buscarventasporfecha.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/image/magnifier.png"))); // NOI18N
        buscarventasporfecha.setText("Buscar");
        buscarventasporfecha.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        buscarventasporfecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buscarventasporfechaActionPerformed(evt);
            }
        });
        jPanel23.add(buscarventasporfecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 20, 130, 40));

        labelparaeltotal.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelparaeltotal.setForeground(new java.awt.Color(255, 255, 255));
        labelparaeltotal.setText("00.00");
        jPanel23.add(labelparaeltotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(1160, 180, 90, 30));

        jLabel99.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel99.setForeground(new java.awt.Color(255, 255, 255));
        jLabel99.setText("Desde");
        jPanel23.add(jLabel99, new org.netbeans.lib.awtextra.AbsoluteConstraints(630, 20, 60, 50));

        jLabel103.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel103.setForeground(new java.awt.Color(255, 255, 255));
        jLabel103.setText("Ventas de hoy:");
        jPanel23.add(jLabel103, new org.netbeans.lib.awtextra.AbsoluteConstraints(400, 20, 280, 50));

        totalventarealizada.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        totalventarealizada.setForeground(new java.awt.Color(255, 255, 255));
        totalventarealizada.setText("Total de ésta venta:");
        jPanel23.add(totalventarealizada, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 180, 190, 30));

        Jtable_ventasRealizadas = new rojerusan.RSTableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        tabladeidventas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {}
            },
            new String [] {

            }
        ));
        tabladeidventas.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        tabladeidventas.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        tabladeidventas.setColorSelForeground(new java.awt.Color(0, 0, 0));
        tabladeidventas.setGrosorBordeFilas(0);
        tabladeidventas.setGrosorBordeHead(0);
        tabladeidventas.setMultipleSeleccion(false);
        tabladeidventas.setRowHeight(25);
        tabladeidventas.setSelectionBackground(new java.awt.Color(204, 0, 51));
        jScrollPane7.setViewportView(tabladeidventas);

        jPanel23.add(jScrollPane7, new org.netbeans.lib.awtextra.AbsoluteConstraints(410, 70, 10, 10));

        jLabel63.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel63.setForeground(new java.awt.Color(255, 255, 255));
        jLabel63.setText("Ventas realizadas:");
        jPanel23.add(jLabel63, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 210, 180, 50));

        conteodelasventasrealizadas.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        conteodelasventasrealizadas.setForeground(new java.awt.Color(255, 255, 255));
        conteodelasventasrealizadas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        conteodelasventasrealizadas.setText("00");
        jPanel23.add(conteodelasventasrealizadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(1140, 210, 120, 50));

        jLabel96.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jLabel96.setForeground(new java.awt.Color(255, 255, 255));
        jLabel96.setText("Ventas de hoy : $ ");
        jPanel23.add(jLabel96, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 250, 170, 50));

        ventaseneldiasumadas.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        ventaseneldiasumadas.setForeground(new java.awt.Color(255, 255, 255));
        ventaseneldiasumadas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        ventaseneldiasumadas.setText("00.00");
        jPanel23.add(ventaseneldiasumadas, new org.netbeans.lib.awtextra.AbsoluteConstraints(1120, 250, 120, 50));

        jTable3 = new rojerusan.RSTableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        jTable3.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable3.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        jTable3.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        jTable3.setGrosorBordeFilas(0);
        jTable3.setGrosorBordeHead(0);
        jTable3.setRowHeight(25);
        jScrollPane14.setViewportView(jTable3);

        jPanel23.add(jScrollPane14, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 360, 220));

        jTable2 = new rojerusan.RSTableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        jTable2.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        jTable2.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        jTable2.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        jTable2.setGrosorBordeFilas(0);
        jTable2.setGrosorBordeHead(0);
        jTable2.setRowHeight(25);
        jTable2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jTable2MouseClicked(evt);
            }
        });
        jScrollPane12.setViewportView(jTable2);

        jPanel23.add(jScrollPane12, new org.netbeans.lib.awtextra.AbsoluteConstraints(390, 70, 560, 220));

        imprimirventa.setBackground(new java.awt.Color(0, 51, 102));
        imprimirventa.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        imprimirventa.setForeground(new java.awt.Color(255, 255, 255));
        imprimirventa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/flecha-hacia-la-izquierda (1).png"))); // NOI18N
        imprimirventa.setText("Imprimir venta");
        imprimirventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                imprimirventaActionPerformed(evt);
            }
        });
        jPanel23.add(imprimirventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(1040, 120, 210, -1));

        cancelarventa.setBackground(new java.awt.Color(0, 51, 102));
        cancelarventa.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        cancelarventa.setForeground(new java.awt.Color(255, 255, 255));
        cancelarventa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/cancelar2.png"))); // NOI18N
        cancelarventa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelarventaActionPerformed(evt);
            }
        });
        jPanel23.add(cancelarventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(960, 70, 60, 90));

        Administrador.add(jPanel23);
        jPanel23.setBounds(10, 70, 1260, 300);

        jPanel24.setBackground(new java.awt.Color(255, 255, 255));

        jLabel93.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel93.setText("                Ventas");

        jLabel94.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel94.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/total.png"))); // NOI18N

        jButton8.setBackground(new java.awt.Color(255, 255, 255));
        jButton8.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton8.setForeground(new java.awt.Color(255, 0, 0));
        jButton8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/salir-flecha-derecha (1).png"))); // NOI18N
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 67, Short.MAX_VALUE)
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
        jPanel24.setBounds(0, 0, 1288, 60);

        jPanel26.setBackground(new java.awt.Color(0, 51, 102));
        jPanel26.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ventas a credito pendiente por pagar", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel26.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        tabladeidventas = new rojerusan.RSTableMetro(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        ventasacreditopendiente.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {

            }
        ));
        ventasacreditopendiente.setColorFilasForeground1(new java.awt.Color(0, 0, 0));
        ventasacreditopendiente.setColorFilasForeground2(new java.awt.Color(0, 0, 0));
        ventasacreditopendiente.setColorSelForeground(new java.awt.Color(0, 0, 0));
        ventasacreditopendiente.setGrosorBordeFilas(0);
        ventasacreditopendiente.setGrosorBordeHead(0);
        ventasacreditopendiente.setMultipleSeleccion(false);
        ventasacreditopendiente.setRowHeight(25);
        ventasacreditopendiente.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                ventasacreditopendienteMouseClicked(evt);
            }
        });
        jScrollPane13.setViewportView(ventasacreditopendiente);

        jPanel26.add(jScrollPane13, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 70, 590, 210));

        Administrador.add(jPanel26);
        jPanel26.setBounds(10, 370, 610, 290);
        jPanel26.getAccessibleContext().setAccessibleName("Ventas que fueron canceladas");

        deudor.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        deudor.setForeground(new java.awt.Color(255, 255, 255));
        Administrador.add(deudor);
        deudor.setBounds(750, 520, 280, 50);

        veridventasacreditopendiente.setBackground(new java.awt.Color(0, 51, 102));
        veridventasacreditopendiente.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        veridventasacreditopendiente.setForeground(new java.awt.Color(255, 255, 255));
        veridventasacreditopendiente.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/flecha-hacia-la-izquierda (1).png"))); // NOI18N
        veridventasacreditopendiente.setText("Ver las ventas");
        veridventasacreditopendiente.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                veridventasacreditopendienteActionPerformed(evt);
            }
        });
        Administrador.add(veridventasacreditopendiente);
        veridventasacreditopendiente.setBounds(620, 610, 189, 46);

        pagarventaacredito.setBackground(new java.awt.Color(0, 51, 102));
        pagarventaacredito.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        pagarventaacredito.setForeground(new java.awt.Color(255, 255, 255));
        pagarventaacredito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/casilla-de-verificacion (1).png"))); // NOI18N
        pagarventaacredito.setText("Pagar venta");
        pagarventaacredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagarventaacreditoActionPerformed(evt);
            }
        });
        Administrador.add(pagarventaacredito);
        pagarventaacredito.setBounds(860, 610, 170, 46);

        totalventacreditoenturno.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        totalventacreditoenturno.setForeground(new java.awt.Color(255, 255, 255));
        totalventacreditoenturno.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        totalventacreditoenturno.setText("00.00");
        Administrador.add(totalventacreditoenturno);
        totalventacreditoenturno.setBounds(930, 560, 120, 50);

        labelcredito.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelcredito.setForeground(new java.awt.Color(255, 255, 255));
        labelcredito.setText("Total de ésta venta : $");
        Administrador.add(labelcredito);
        labelcredito.setBounds(620, 560, 210, 50);

        labelnombre.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        labelnombre.setForeground(new java.awt.Color(255, 255, 255));
        labelnombre.setText("A nombre de: ");
        Administrador.add(labelnombre);
        labelnombre.setBounds(620, 520, 130, 50);

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
            .addComponent(Administrador, javax.swing.GroupLayout.DEFAULT_SIZE, 707, Short.MAX_VALUE)
        );

        Proveedores9.addTab("      Inventario Ventas      ", jPanel12);

        getContentPane().add(Proveedores9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1290, 740));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void modifyActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modifyActionPerformed
        
    }//GEN-LAST:event_modifyActionPerformed

    private void eliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarActionPerformed
                
    }//GEN-LAST:event_eliminarActionPerformed

    private void modificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarActionPerformed

    }//GEN-LAST:event_modificarActionPerformed

    private void dropActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_dropActionPerformed

    }//GEN-LAST:event_dropActionPerformed

    public void cerrandosesion_o_limpiandoventa(){
                regresarproductos_a_inventariodescontandotodaslaspiezas(); //pone en estatus de cancelada la venta inconclusa
               // descuentodepollo();
                //EL SIGUIENTE METODO LIMPIA LA TABLA VENTA YA UNA VEZ QUE SE REGRESARON LAS PIEZAS
            borrarventasenestadoenturnoporerrordeusuario_limpiarventa_o_cerrarsesion();
            /* ESTOS 3 METODOS
            1.- AGREGA EL STATUS CANCELADO A LA TABLA VENTA, OSEA PONE TODOS LOS TOTALES EN 0
            2.- EN EL PUNTO 1, ACTIVA LA VARIABLE block_unlock=true; PARA QUE EN EL METODO GET ID USUARIO INCREMENTE UN NUEVO ID
            3.- Y POSTERIOR SE DESACTIVA LA VARIABLE POR QUE YA SE INCREMENTO UN NUEVO ID
            status_cancelado();  //pone en estatus de cancelada la venta inconclusa y cada producto que lo compone
            get_id_usuario(); //vuelve a asiganr otro id_venta para que así no se repita con el id anterior que tuvo una venta cancelada
            block_unlock=false; //se bloquea la opcion de poder agregar otro id_usuario a la tabla de venta y así abrir una nueva venta
            */
            limpiardatosdeventa();  //limpia en su mayoria los campos de texto que pertenezcan al apartado venta
                tablaventa.setVisible(false); //Desaparece la tabla
               storage.clear();
    }//BOTON CERRAR SESION, PERO COMPRUEBA SI HAY UNA VENTA PEN PARA CANCELAR EN CASO DE SALIR
    
    private void modificarusuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_modificarusuariosActionPerformed

    }//GEN-LAST:event_modificarusuariosActionPerformed

    private void eliminarusuariosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_eliminarusuariosActionPerformed

    }//GEN-LAST:event_eliminarusuariosActionPerformed

    private void activarusuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_activarusuarioActionPerformed
    
    }//GEN-LAST:event_activarusuarioActionPerformed
public void eliminarhuesito(int id){
    try{Connection ca= cc.conexion();
        String sql = "delete from descripcion_de_venta where id_producto = '"+id+"' ";
        PreparedStatement ps = ca.prepareStatement(sql);
       ps.execute();       
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "No se elimino huesito");
    }finally{
        cc.getClose();
    }
}

public void eliminarpolloenterodestorage(int id_producto){
    for(int r=0;r<=storage.size()-1;r++){
                 if(Integer.parseInt(storage.get(r).toString())==id_producto){
                     storage.remove(r);
                 }
             }
}

    public static void accionesdespuesderealizarcualquierventa(){
      //  descuentodepollo();
                                get_id_usuario();
                                totaldelasventasdehoy(); // PARA LA SUMA DE LOS TOTALES DE LA VENTA
                                conteodeventasrealizadasdehoy(); // CUANTAS VENTAS SE REALIZARON? 5 O 60 O XX
                               ventaseneldiasumadas.setText(String.valueOf(sumadetotalesdeventasdehoy));// VIENE DEL METODO ventaseneldiaREALIZADAS()
                                conteodelasventasrealizadas.setText(String.valueOf(conteototaldeventas)); // VIENE DEL METODO totalventasxdia(); ES UN CONTEO DE VENTAS
                                llenartablaidventasconidrealizados();
                                productosvendidoseneldia();//MUESTRA LAS VENTAS REA
                                llenartablaconventasacreditopendiente();
                                TablallenadoparaEntradas(Jtable_ProductosEntradas);
                                ParaLAVenta(JtablepaLaVenta);
                                    limpiardatosdeventa(); //Los datos que aparecen en la venta se mostraran
                                descuentoactivo=false;
                                storage.clear();
                                voyacobrar=false;        
    }
    
    public boolean validarFormulariotexto(String nombre) { // VALIDACION DE TXTDESCRIPCION
        boolean next = false;      //"^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24}[\\s]*)+$"
        Pattern patGastos = Pattern.compile("^[A-Za-z\\s]+$");// ^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24}[\\s]*)+$
        Matcher matGastos = patGastos.matcher(nombre);
        if (matGastos.matches()&&!nombre.equals("")) {
            next = true;
        } else {
            JOptionPane.showMessageDialog(null, "Solo escribe letras");
        }
        return next;
    }     
   public static void agregandoaventa(String nombredepiezaseleccionada, float cantidaddeproductos) {
        if (nombredepiezaseleccionada.equals("Pierna completa")) {
            String[] piernafull = {"Muslo", "Pierna"};
            for (int i = 0; i < piernafull.length; i++) {
                agregarpiezasaventa(piernafull[i].toString());
            }
        } else if (nombredepiezaseleccionada.equals("Huacal completo")) {
            String[] hucalfull = {"Huacal", "Cadera"};
            for (int i = 0; i < hucalfull.length; i++) {
                agregarpiezasaventa(hucalfull[i].toString());
            }
        } else if (nombredepiezaseleccionada.equals("pollo crudo")) {//TODAS LAS PIEZAS QUE CORRESONDE A UN POLLO ENTERO
            for (int i = 0; i < piezas.length; i++) {
                if (piezas[i].toString().equals("Muslo")
                        || piezas[i].toString().equals("Pierna")
                        || piezas[i].toString().equals("Ala")
                        || piezas[i].toString().equals("Patas")) {
                    piezassuficientes(piezas[i].toString());//verifica primero que haya las suficientes piezas para agregar un producto a la venta    
            if (suficientespiezas == true) {
                cantidaddeproductos = 2 * cantidadparapollocrudo;
                    acompletarpollo(piezas[i].toString(), cantidaddeproductos);
            }else{  JOptionPane.showMessageDialog(null, "Solo hay " + piezassuficientes + " piezas de "+piezas[i].toString(), "Advertencia", JOptionPane.ERROR_MESSAGE);}
                    
                } else {
                    if (piezas[i].toString().equals("pollo crudo")) {
                        cantidaddeproductos = 1 * cantidadparapollocrudo;
                        agregarpiezasaventa(piezas[i].toString());
                    } 
                    else { piezassuficientes(piezas[i].toString());//verifica primero que haya las suficientes piezas para agregar un producto a la venta    
            if (suficientespiezas == true) {
                cantidaddeproductos = 1 * cantidadparapollocrudo;
                        acompletarpollo(piezas[i].toString(), cantidaddeproductos);
            }else{  JOptionPane.showMessageDialog(null, "Solo hay " + piezassuficientes + " piezas de "+piezas[i].toString(), "Advertencia", JOptionPane.ERROR_MESSAGE);}
             }
                }
            }
        } else if (nombredepiezaseleccionada.equals("Medio pollo")) {//ESTO INDICA QUE ES MEDIO POLLO
            for (int i = 0; i < piezasdemedio.length; i++) {
                if (piezasdemedio[i].toString().equals("Pechuga")) {
                    piezassuficientes(piezasdemedio[i].toString());//verifica primero que haya las suficientes piezas para agregar un producto a la venta    
            if (suficientespiezas == true) {
                cantidaddeproductos = (float) medio * cantidadparapollocrudo;
                    acompletarpollo(piezasdemedio[i].toString(), cantidaddeproductos);
            }else{  JOptionPane.showMessageDialog(null, "Solo hay " + piezassuficientes + " piezas de "+piezasdemedio[i].toString(), "Advertencia", JOptionPane.ERROR_MESSAGE);}
         
                } else {
                    if (piezasdemedio[i].toString().equals("Medio pollo")) {
                        cantidaddeproductos = 1 * cantidadparapollocrudo;
                        agregarpiezasaventa(piezasdemedio[i].toString());
                    } else {
                             piezassuficientes(piezasdemedio[i].toString());//verifica primero que haya las suficientes piezas para agregar un producto a la venta    
            if (suficientespiezas == true) {
                   cantidaddeproductos = 1 * cantidadparapollocrudo;
                        acompletarpollo(piezasdemedio[i].toString(), cantidaddeproductos);
            }else{ JOptionPane.showMessageDialog(null, "Solo hay " + piezassuficientes + " piezas de "+piezasdemedio[i].toString(), "Advertencia", JOptionPane.ERROR_MESSAGE);}
          }
                }
            }
        } else if (nombredepiezaseleccionada.equals("Huesito") || nombredepiezaseleccionada.equals("Longaniza")) {
            if (nombredepiezaseleccionada.equals("Huesito")) {
                agregarpiezasaventa("Huesito");
            } else {
                agregarpiezasaventa("Longaniza");
}
        } else if (nombredepiezaseleccionada.equals("Pechuga en bisteck")) {
     piezassuficientes("Pechuga");//verifica primero que haya las suficientes piezas para agregar un producto a la venta    
            if (suficientespiezas == true) {
                soypechugaenbisteck=true;
                agregarpiezasaventa("Pechuga");
            } else {
                JOptionPane.showMessageDialog(null, "Solo hay " + piezassuficientes + " piezas de Pechuga", "Advertencia", JOptionPane.ERROR_MESSAGE);
           }
  } else if(!nombredepiezaseleccionada.equals("pollo crudo")||!nombredepiezaseleccionada.equals("Medio pollo")){
            agregarpiezasaventa(nombredepiezaseleccionada);
        }
    }
    
    public static void metodo_de_cobro(float variablepago){
    /*   ********************  BOTON DE COBRAR LA VENTA ****************  */
            /*   ******************  BOTON DE COBRAR LA VENTA **************  */
            try{
             totaldeventaenturno =  Float.parseFloat(subtotal.getText());
              variablepagocondescuento =  Float.parseFloat(total.getText());
                if(!subtotal.getText().isEmpty()&&variablepago>0&&Float.parseFloat(subtotal.getText())>0){
                    if(descuentoactivo==true){ //CUANDO EL DESCUENTO ESTÁ ACTIVO
                        if(variablepago<variablepagocondescuento){ // comprueba que la cantidad recibida sea mayor al total
                            JOptionPane.showMessageDialog(null,"El pago es menor a la cantidad a pagar, por favor, verifique","Advertencia",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else {
                            tablaventaactiva=false;
                            cambiocombobox.setText(String.valueOf(cambio=variablepago-variablepagocondescuento));
                            block_unlock=true;
                            try{Connection ca= cc.conexion();// el id del usuario
                                id_max_de_venta();
                                PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET subtotal='"+solodosdecimales.format(Float.parseFloat(subtotal.getText()))+"',total='"+solodosdecimales.format(Float.parseFloat(total.getText()))+"',descuento='"+ solodosdecimales.format(Float.parseFloat(descuentocombo.getText()))+"',pago='"+solodosdecimales.format(variablepago)+"',cambio='"+solodosdecimales.format(Float.parseFloat(cambiocombobox.getText()))+"',fecha_reporte='"+fecha()+"',estado_venta='"+estadorealizado+"'WHERE id_venta='"+id_de_la_venta_incrementable+"'");
                                    ps.executeUpdate();
                                //ACTUALIZACION EN LA TABLA DESCRIPCION DE VENTA A REALIZADA
                                id_max_de_venta();
                                try{
                                    id_max_de_venta();
                                    PreparedStatement ps2 = ca.prepareStatement ("UPDATE descripcion_de_venta SET estado= '"+estadorealizado+"' WHERE id_venta='"+id_de_la_venta_incrementable+"'");
                                    int result = ps2.executeUpdate();
                                         if(result>0){
                                             JOptionPane.showMessageDialog(null, "El cambio es de: "+cambiocombobox.getText()," Se realizo una venta",JOptionPane.YES_OPTION);
                               descripciondelosprouductosparaelticketdeventa(id_de_la_venta_incrementable); //DATOS PARA EL TICKET DE VENTA
                                        accionesdespuesderealizarcualquierventa();
                                         }
                                }
                                catch(Exception ex){
                                    JOptionPane.showMessageDialog(null, "Error en venta" + ex.getMessage());
                                }
                                //autocompletar();
                            }//fin del id del usuario
                            catch(Exception w){
                                JOptionPane.showMessageDialog(null,"error en id usuario"+w);
                            }finally{cc.getClose();}
                        }
                    } //FIN DE CUANDO EL DESCUENTO ESTÁ ACTIVO
else{ //CUANDO EL DESCUENTO NO ESTÁ ACTIVO
if(variablepago<totaldeventaenturno){ // comprueba que la cantidad recibida sea mayor al total
                            JOptionPane.showMessageDialog(null,"El pago es menor a la cantidad a pagar, por favor, verifique","Advertencia",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else {
                            tablaventaactiva=false;
                            cambiocombobox.setText(String.valueOf(cambio=variablepago-totalf));
                            block_unlock=true;
                            try{Connection ca= cc.conexion();// el id del usuario
                                id_max_de_venta();
                                PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET subtotal='"+solodosdecimales.format(totalf)+"',total='"+solodosdecimales.format(totalf)+"',descuento='"+ variablede0+"',pago='"+solodosdecimales.format(variablepago)+"',cambio='"+solodosdecimales.format(Float.parseFloat(cambiocombobox.getText()))+"',fecha_reporte='"+fecha()+"',estado_venta='"+estadorealizado+"'WHERE id_venta='"+id_de_la_venta_incrementable+"'");
                                ps.executeUpdate();
                                //ACTUALIZACION EN LA TABLA DESCRIPCION DE VENTA A REALIZADA
                                id_max_de_venta();
                                try{
                                    id_max_de_venta();
                                    PreparedStatement ps2 = ca.prepareStatement ("UPDATE descripcion_de_venta SET estado= '"+estadorealizado+"' WHERE id_venta='"+id_de_la_venta_incrementable+"'");
                                   int resultado=  ps2.executeUpdate();
                                     if(resultado>0){
                                             JOptionPane.showMessageDialog(null, "El cambio es de: "+cambiocombobox.getText()," Se realizo una venta",JOptionPane.YES_OPTION);
                                             descripciondelosprouductosparaelticketdeventa(id_de_la_venta_incrementable);//DATOS PARA EL TICKET DE VENTA          
                                      accionesdespuesderealizarcualquierventa();
                                     }
                                }
                                catch(Exception ex){
                                    JOptionPane.showMessageDialog(null, "Error en venta" + ex.getMessage());
                                }
                                //autocompletar();
                            }//fin del id del usuario
                            catch(Exception w){
                                JOptionPane.showMessageDialog(null,"error en id usuario"+w);
                            }finally{
                                cc.getClose();
                            }
                        }
                    } //FIN CUANDO EL DESCUENTO NO ESTÁ ACTIVO
                }
                else if(subtotal.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Aún no hay nada por pagar","!Espera!",JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(Exception NFE){//Number format exception para cuando el usuario no ingrese ningun dato en la caja
                JOptionPane.showMessageDialog(null,"No tiene valor la cantidad recibida","!Espera!",JOptionPane.INFORMATION_MESSAGE);
}
    }
    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
   new ProductosExternos().setVisible(true);        
    }                                        
    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        // TODO add your handling code here:
    }//GEN-LAST:event_formWindowClosed

    private void pagarventaacreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagarventaacreditoActionPerformed
        try{
            String pagodeventacredito="";
            float variable0=0, totalacredito=0, cambio=0;
            boolean pass2 = validarFormulario(pagodeventacredito= JOptionPane.showInputDialog(null,"Escriba el monto de pago","Pagando venta a credito", JOptionPane.INFORMATION_MESSAGE));
            if(pass2){//ESTO VALIDA QUE EL TEXTO ESCRITO NO TENGA INCOHERENCIAS
int decision=JOptionPane.showConfirmDialog(null,"¿Desea continuar?","Estás por pagar una venta a credito",JOptionPane.CANCEL_OPTION);
                if(decision==0){ //opción si
                    total_venta_creditopendiente(id_ventapencredito);
                    if(Float.parseFloat(pagodeventacredito)>=sumadeimportescreditopendiente){
                        try{Connection ca= cc.conexion();
                            cambio = Float.parseFloat(pagodeventacredito)-sumadeimportescreditopendiente;
                            PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET descuento='"+ variable0+"',pago='"+solodosdecimales.format(Float.parseFloat(pagodeventacredito))+"',cambio='"+solodosdecimales.format(cambio)+"',fecha_reporte='"+fecha()+"',estado_venta='"+creditopagado+"'WHERE id_venta='"+id_ventapencredito+"'");
                            ps.executeUpdate();
                        }catch(Exception ex){
                            JOptionPane.showMessageDialog(null, "Error en venta" + ex.getMessage());
                        }finally{
                            cc.getClose();
                        }
                        try{Connection ca= cc.conexion();
                            id_max_de_venta();
                            PreparedStatement ps2 = ca.prepareStatement ("UPDATE descripcion_de_venta SET estado= '"+creditopagado+"' WHERE id_venta='"+id_ventapencredito+"'");
                            int a = ps2.executeUpdate();
                            if(a>0){
                                accionesdespuesderealizarcualquierventa();
                                pagarventaacredito.setVisible(false);
                                labelnombre.setVisible(false);
                                labelcredito.setVisible(false);
                                totalventacreditoenturno.setVisible(false);
                                deudor.setVisible(false);
                                veridventasacreditopendiente.setVisible(false);
                                JOptionPane.showMessageDialog(null, "Venta a credito pagada");
  }
                        }
                        catch(Exception ex){
                            JOptionPane.showMessageDialog(null, "Error en venta" + ex.getMessage());
                        }finally{
                            cc.getClose();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "El pago es menor al total", "Verifique por favor", JOptionPane.INFORMATION_MESSAGE);
                    }
    }
  }
        }catch(NullPointerException NP){//ESTO EVITA QUE EL USUARIO META UN VALOR VACIO
 }
    }//GEN-LAST:event_pagarventaacreditoActionPerformed

    private void veridventasacreditopendienteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_veridventasacreditopendienteActionPerformed
 llenartablaconventasacreditopendiente(); //CARGA NUEVAMENTE LAS VENTAS POR ID
 labelnombre.setVisible(false);
        labelcredito.setVisible(false);
        deudor.setVisible(false);
        totalventacreditoenturno.setVisible(false);
        veridventasacreditopendiente.setVisible(false);
        pagarventaacredito.setVisible(false);
    }//GEN-LAST:event_veridventasacreditopendienteActionPerformed

    private void ventasacreditopendienteMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_ventasacreditopendienteMouseClicked
        int fila =ventasacreditopendiente.getSelectedRow();
        if(fila>=0){
            boolean pass =validarFormularioparamostrardescripciondeproductosporid(ventasacreditopendiente.getValueAt(fila,0).toString());
            if(pass){
                id_ventapencredito=Integer.parseInt(ventasacreditopendiente.getValueAt(fila,0).toString());
                descripciondeproductosenbasealnumerodeventaporcreditopendiente(Integer.parseInt(ventasacreditopendiente.getValueAt(fila,0).toString()));
                total_venta_creditopendiente(id_ventapencredito);
                totalventacreditoenturno.setText(String.valueOf(sumadeimportescreditopendiente));
                labelnombre.setVisible(true);
                labelcredito.setVisible(true);
                deudor.setVisible(true);
                totalventacreditoenturno.setVisible(true);
                veridventasacreditopendiente.setVisible(true);
                pagarventaacredito.setVisible(true);
            }
        }
        else
        JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila primero","Aviso",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_ventasacreditopendienteMouseClicked

    private void jButton8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton8ActionPerformed
        if(tablaventaactiva==true&&(storage.size())>0){
            int decision=JOptionPane.showConfirmDialog(null,"¿Desea continuar?","Advertencia: Tiene una venta inconclusa",JOptionPane.CANCEL_OPTION);
            if(decision==0){ //opción si
                cerrandosesion_o_limpiandoventa();
                JOptionPane.showMessageDialog(null,"Nos vemos pronto","Saliendo del sistema...",JOptionPane.INFORMATION_MESSAGE);
                new SI_Inicio().setVisible(true);
                this.setVisible(false);
            }
        }
        else{
            int decision2=JOptionPane.showConfirmDialog(null,"¿Desea continuar?","Saliendo del sistema",JOptionPane.CANCEL_OPTION);
            if(decision2==0){
                JOptionPane.showMessageDialog(null,"Nos vemos pronto","Saliendo del sistema...",JOptionPane.INFORMATION_MESSAGE);
                new SI_Inicio().setVisible(true);
                this.setVisible(false);
            }
        }
    }//GEN-LAST:event_jButton8ActionPerformed

    private void cancelarventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelarventaActionPerformed
        status_cancelado(id);
        fechainicial.cleanup();
        fechainicial.setDate(null);
        fechafinal.cleanup();
        fechafinal.setDate(null);
        llenartablaidventasconidrealizados(); //CARGA NUEVAMENTE LAS VENTAS POR ID
        veridventas.setVisible(false);
        labelparaeltotal.setText("00.00");
        totalventarealizada.setVisible(false);
        labelparaeltotal.setVisible(false);
        imprimirventa.setVisible(false);
  cancelarventa.setVisible(false);
    }//GEN-LAST:event_cancelarventaActionPerformed

    private void imprimirventaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_imprimirventaActionPerformed
        reimpresiondeventa(id);
    }//GEN-LAST:event_imprimirventaActionPerformed

    private void jTable2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jTable2MouseClicked
        int fila =jTable2.getSelectedRow();
 if(fila>=0){
            boolean pass= validarFormularioparamostrardescripciondeproductosporid(jTable2.getValueAt(fila,0).toString());
            if(pass){
                veridventas.setVisible(true);
                id=Integer.parseInt(jTable2.getValueAt(fila,0).toString());
                descripciondeproductosenbasealnumerodeventa(id);
                total_ventaporid(id);
                labelparaeltotal.setVisible(true);
                labelparaeltotal.setText(String.valueOf(sumadeimportesparaeltotal));
                totalventarealizada.setVisible(true);
                imprimirventa.setVisible(true);
                cancelarventa.setVisible(true);
            }
        }
        else
        JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila primero","Aviso",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_jTable2MouseClicked

    private void buscarventasporfechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buscarventasporfechaActionPerformed
        try{
            String fechaparaventasdesde= llenarfechadesdeparamostrarlosidventas();
            String fechaparaventashasta= llenarfechahastaparamostrarlosidventas();
            if(fechaparaventasdesde.equals("")&&fechaparaventashasta.equals("")){
                JOptionPane.showMessageDialog(null, "Primero debe elegir un rango de fechas en los calendarios");
            }else{
     showidventasporfechas(jTable2, llenarfechadesdeparamostrarlosidventas(),llenarfechahastaparamostrarlosidventas());
            }
        }catch(NullPointerException NP){
            JOptionPane.showMessageDialog(null,"Debes de elegir un rango de fechas en los botones para la fecha", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_buscarventasporfechaActionPerformed

    private void veridventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_veridventasActionPerformed
        fechainicial.cleanup();
        fechainicial.setDate(null);
        fechafinal.cleanup();
        fechafinal.setDate(null);
        llenartablaidventasconidrealizados(); //CARGA NUEVAMENTE LAS VENTAS POR ID
        veridventas.setVisible(false);
        labelparaeltotal.setText("00.00");
        totalventarealizada.setVisible(false);
        labelparaeltotal.setVisible(false);
        imprimirventa.setVisible(false);
 cancelarventa.setVisible(false);
    }//GEN-LAST:event_veridventasActionPerformed

    private void agregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_agregarActionPerformed
        if(noduplicarexternos==false){new ProductosExternos().setVisible(true); }
    }//GEN-LAST:event_agregarActionPerformed

    private void jButton10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton10ActionPerformed
        if(tablaventaactiva==true&&(storage.size())>0){
            int decision=JOptionPane.showConfirmDialog(null,"¿Desea continuar?","Advertencia: Tiene una venta inconclusa",JOptionPane.CANCEL_OPTION);
            if(decision==0){ //opción si
                cerrandosesion_o_limpiandoventa();
                JOptionPane.showMessageDialog(null,"Nos vemos pronto","Saliendo del sistema...",JOptionPane.INFORMATION_MESSAGE);
                new SI_Inicio().setVisible(true);
                this.setVisible(false);
            }
        }
        else{
            int decision2=JOptionPane.showConfirmDialog(null,"¿Desea continuar?","Saliendo del sistema",JOptionPane.CANCEL_OPTION);
            if(decision2==0){
                JOptionPane.showMessageDialog(null,"Nos vemos pronto","Saliendo del sistema...",JOptionPane.INFORMATION_MESSAGE);
                new SI_Inicio().setVisible(true);
                this.setVisible(false);
            }
        }
    }//GEN-LAST:event_jButton10ActionPerformed

    private void jButton22ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton22ActionPerformed
        if(masdeunapieza.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Ver. encurtidas","Escribe la cantidad");
   new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Ver. encurtidas",1);
        }
    }//GEN-LAST:event_jButton22ActionPerformed

    private void jButton21ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton21ActionPerformed
        if(masdeunapieza.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Ensalada rusa","Escribe la cantidad");
   new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Ensalada rusa",1);
        }
    }//GEN-LAST:event_jButton21ActionPerformed

    private void jButton20ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton20ActionPerformed
        if(masdeunapieza.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Pasta de codito","Escribe la cantidad");
 new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Pasta de codito",1);
        }
    }//GEN-LAST:event_jButton20ActionPerformed

    private void jButton19ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton19ActionPerformed
        if(masdeunapieza.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Chiles en vinagre","Escribe la cantidad");
   new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Chiles en vinagre",1);
        }
    }//GEN-LAST:event_jButton19ActionPerformed

    private void jButton18ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton18ActionPerformed
        if(masdeunapieza.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Ensalada de col","Escribe la cantidad");
   new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Ensalada de col",1);
        }
    }//GEN-LAST:event_jButton18ActionPerformed

    private void jButton17ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton17ActionPerformed
        if(masdeunapieza.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Ens. de manzana","Escribe la cantidad");
   new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Ens. de manzana",1);
        }
    }//GEN-LAST:event_jButton17ActionPerformed

    private void jButton16ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton16ActionPerformed
        if(masdeunapieza.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Pure","Escribe la cantidad");
     new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Pure",1);
        }
    }//GEN-LAST:event_jButton16ActionPerformed

    private void jButton15ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton15ActionPerformed
        if(masdeunapieza.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Frijoles peruanos","Escribe la cantidad");
   new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Frijoles peruanos",1);
        }
    }//GEN-LAST:event_jButton15ActionPerformed

    private void jButton14ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton14ActionPerformed
        if(masdeunapieza.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Frijoles charros","Escribe la cantidad");
   new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Frijoles charros",1);
        }
    }//GEN-LAST:event_jButton14ActionPerformed

    private void jButton13ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton13ActionPerformed
        if(masdeunapieza.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Cochinita","Escribe la cantidad");
    new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Cochinita",1);
        }
    }//GEN-LAST:event_jButton13ActionPerformed

    private void jButton12ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton12ActionPerformed
        if(masdeunapieza.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Frijoles puercos","Escribe la cantidad");
     new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Frijoles puercos",1);
        }
    }//GEN-LAST:event_jButton12ActionPerformed

    private void jButton11ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton11ActionPerformed
 if(masdeunapieza.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Spagueti rojo","Escribe la cantidad");
            new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Spagueti rojo",1);
        }
    }//GEN-LAST:event_jButton11ActionPerformed

    private void jButton9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton9ActionPerformed
        if(masdeunapieza.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Arroz rojo","Escribe la cantidad");
   new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Arroz rojo",1);
        }
    }//GEN-LAST:event_jButton9ActionPerformed

    private void jButton7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton7ActionPerformed
        if(masdeunapieza.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Arroz blanco","Escribe la cantidad");
     new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Arroz blanco",1);
        }
    }//GEN-LAST:event_jButton7ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        if(masdeunapieza.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Spagueti blanco","Escribe la cantidad");
    new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Spagueti blanco",1);
        }
    }//GEN-LAST:event_jButton5ActionPerformed

    private void salsaguajilloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_salsaguajilloActionPerformed
        if(masdeunapieza.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Salsa guajillo (gde)","Escribe la cantidad");
            new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Salsa guajillo (gde)",1);
        }
    }//GEN-LAST:event_salsaguajilloActionPerformed

    private void jButton41ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton41ActionPerformed
  if(masdeunapiezacocido.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Miel","Escribe la cantidad");
     new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Miel",1);
  }
    }//GEN-LAST:event_jButton41ActionPerformed

    private void jButton40ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton40ActionPerformed
   if(masdeunapiezacocido.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Longaniza","Escribe la cantidad en pesos");
     new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            Calculadora enviar = new Calculadora("Longaniza","Escribe la cantidad en pesos");
     new Calculadora().setVisible(true);
        }
    }//GEN-LAST:event_jButton40ActionPerformed

    private void jButton39ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton39ActionPerformed
 if(masdeunapiezacocido.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Mininuggets","Escribe la cantidad");
      new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Mininuggets",1);
        }
    }//GEN-LAST:event_jButton39ActionPerformed

    private void jButton38ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton38ActionPerformed
  if(masdeunapiezacocido.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Mole rojo","Escribe la cantidad");
    new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Mole rojo",1);
        }
    }//GEN-LAST:event_jButton38ActionPerformed

    private void jButton37ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton37ActionPerformed
        if(masdeunapiezacocido.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Mole verde","Escribe la cantidad");
     new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Mole verde",1);
        }
    }//GEN-LAST:event_jButton37ActionPerformed

    private void jButton36ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton36ActionPerformed
        if(masdeunapiezacocido.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Brochetas","Escribe la cantidad");
   new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Brochetas",1);
        }
    }//GEN-LAST:event_jButton36ActionPerformed

    private void jButton35ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton35ActionPerformed
   if(masdeunapiezacocido.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Tacos","Escribe la cantidad");
     new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Tacos",1);
        }
    }//GEN-LAST:event_jButton35ActionPerformed

    private void jButton34ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton34ActionPerformed
 if(masdeunapieza.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Alitas bbq","Escribe la cantidad");
     new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Alitas bbq",1);
        }
    }//GEN-LAST:event_jButton34ActionPerformed

    private void jButton33ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton33ActionPerformed
        if(masdeunapiezacocido.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Muslo broaster","Escribe la cantidad");
     new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Muslo broaster",1);
        }
    }//GEN-LAST:event_jButton33ActionPerformed

    private void jButton32ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton32ActionPerformed
  if(masdeunapiezacocido.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Barbacoa de pollo","Escribe la cantidad");
   new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Barbacoa de pollo",1);
        }
    }//GEN-LAST:event_jButton32ActionPerformed

    private void jButton31ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton31ActionPerformed
        if(masdeunapiezacocido.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Nuggets","Escribe la cantidad");
   new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Nuggets",1);
        }
    }//GEN-LAST:event_jButton31ActionPerformed

    private void jButton30ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton30ActionPerformed
 if(masdeunapiezacocido.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Ala broaster","Escribe la cantidad");
            new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Ala broaster",1);
        }
    }//GEN-LAST:event_jButton30ActionPerformed

    private void jButton29ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton29ActionPerformed
        if(masdeunapiezacocido.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            piezaseleccionadaycantidadvariable((float)cuarto, "Pollo asado");
 }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadvariable((float)cuarto, "Pollo asado");
        }
    }//GEN-LAST:event_jButton29ActionPerformed

    private void jButton28ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton28ActionPerformed
        if(masdeunapiezacocido.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            piezaseleccionadaycantidadvariable((float)cuarto, "Pollo rostizado");
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadvariable((float)cuarto, "Pollo rostizado");
        }
    }//GEN-LAST:event_jButton28ActionPerformed

    private void jButton27ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton27ActionPerformed
        if(masdeunapiezacocido.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            piezaseleccionadaycantidadvariable((float)medio, "Pollo rostizado");
 }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadvariable((float)medio, "Pollo rostizado");

        }
    }//GEN-LAST:event_jButton27ActionPerformed

    private void jButton26ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton26ActionPerformed
        if(masdeunapieza.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            piezaseleccionadaycantidadvariable((float)medio, "Pollo asado");
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadvariable((float)medio, "Pollo asado");
        }
    }//GEN-LAST:event_jButton26ActionPerformed

    private void jButton25ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton25ActionPerformed
        if(masdeunapiezacocido.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Pierna broaster","Escribe la cantidad");
   new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Pierna broaster",1);
        }
    }//GEN-LAST:event_jButton25ActionPerformed

    private void jButton24ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton24ActionPerformed
        if(masdeunapiezacocido.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Pech. broaster","Escribe la cantidad");
 new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Pech. broaster",1);
        }
    }//GEN-LAST:event_jButton24ActionPerformed

    private void jButton23ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton23ActionPerformed
        if(masdeunapiezacocido.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Pollo rostizado","Escribe la cantidad");
 new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Pollo rostizado",1);
        }
    }//GEN-LAST:event_jButton23ActionPerformed

    private void polloasadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_polloasadoActionPerformed
        if(masdeunapiezacocido.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Pollo asado","Escribe la cantidad");
  new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Pollo asado",1);
        }
    }//GEN-LAST:event_polloasadoActionPerformed

    private void bonesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bonesActionPerformed
        if(masdeunapiezacocido.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Huesito","Escribe la cantidad en pesos");
            new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            Calculadora enviar = new Calculadora("Huesito","Escribe la cantidad en pesos");
            new Calculadora().setVisible(true);
        }
    }//GEN-LAST:event_bonesActionPerformed

    private void jButton55ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton55ActionPerformed
        if(masdeunapiezacrudo.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Pechuga en bisteck","Escribe la cantidad");
            new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Pechuga en bisteck",1);
        }
    }//GEN-LAST:event_jButton55ActionPerformed

    private void jButton54ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton54ActionPerformed
        if(masdeunapiezacrudo.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Patas","Escribe la cantidad");
            new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Patas",1);
        }
    }//GEN-LAST:event_jButton54ActionPerformed

    private void jButton53ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton53ActionPerformed
        if(masdeunapiezacrudo.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Molleja","Escribe la cantidad");
            new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Molleja",1);
        }
    }//GEN-LAST:event_jButton53ActionPerformed

    private void jButton52ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton52ActionPerformed
        if(masdeunapiezacrudo.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Huacal","Escribe la cantidad");
            new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Huacal",1);
        }
    }//GEN-LAST:event_jButton52ActionPerformed

    private void jButton51ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton51ActionPerformed
        if(masdeunapiezacrudo.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Cadera","Escribe la cantidad");
            new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Cadera",1);
        }
    }//GEN-LAST:event_jButton51ActionPerformed

    private void jButton50ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton50ActionPerformed
        if(masdeunapiezacrudo.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Cabeza","Escribe la cantidad");
            new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Cabeza",1);
        }
    }//GEN-LAST:event_jButton50ActionPerformed

    private void jButton49ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton49ActionPerformed
        if(masdeunapiezacrudo.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Ala","Escribe la cantidad");
            new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Ala",1);
        }
    }//GEN-LAST:event_jButton49ActionPerformed

    private void jButton48ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton48ActionPerformed
        if(masdeunapiezacrudo.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Pierna","Escribe la cantidad");
            new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Pierna",1);
        }
    }//GEN-LAST:event_jButton48ActionPerformed

    private void jButton47ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton47ActionPerformed
        if(masdeunapiezacrudo.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Muslo","Escribe la cantidad");
            new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Muslo",1);
        }
    }//GEN-LAST:event_jButton47ActionPerformed

    private void jButton46ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton46ActionPerformed
 if(masdeunapiezacrudo.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Pierna completa","Escribe la cantidad");
    new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Pierna completa",1);
        }
    }//GEN-LAST:event_jButton46ActionPerformed

    private void jButton45ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton45ActionPerformed
        if(masdeunapiezacrudo.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Huacal completo","Escribe la cantidad");
    new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Huacal completo",1);
        }
    }//GEN-LAST:event_jButton45ActionPerformed

    private void jButton44ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton44ActionPerformed
   if(masdeunapiezacrudo.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Pechuga","Escribe la cantidad");
   new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("Pechuga",1);
        }
    }//GEN-LAST:event_jButton44ActionPerformed

    private void jButton43ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton43ActionPerformed
        if(masdeunapiezacrudo.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            piezaseleccionadaycantidadvariable((float)medio, "Pechuga");
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadvariable((float)medio, "Pechuga");
        }
    }//GEN-LAST:event_jButton43ActionPerformed

    private void jButton42ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton42ActionPerformed
        if(masdeunapiezacrudo.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("Medio pollo","Escribe la cantidad");
   new Calculadora().setVisible(true);
 }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadvariable(1, "Medio pollo");
        }
    }//GEN-LAST:event_jButton42ActionPerformed

    private void crudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_crudoActionPerformed
        if(masdeunapiezacrudo.isSelected()){//CUANDO SE SELECCIONÓ LA CASILLA MÁS DE UNA PIEZA, TE HABILITA LA CALCU
            Calculadora enviar = new Calculadora("pollo crudo","Escribe la cantidad");
 new Calculadora().setVisible(true);
        }else{ //CUANDO NO, SE AGREGA UNA PIEZA POR BOTON SELECCIONADO
            piezaseleccionadaycantidadunica("pollo crudo",1);
        }
    }//GEN-LAST:event_crudoActionPerformed

    private void ExistenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExistenciasActionPerformed
        if(noduplicarexistencias==false){
            new Existencias().setVisible(true);
        }
    }//GEN-LAST:event_ExistenciasActionPerformed

    private void cobroActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cobroActionPerformed
        if(tablaventaactiva==true&&!subtotal.getText().equals("")){
            String cobrando="Cobrando";
            CalculadoraCobro enviar = new CalculadoraCobro(Float.parseFloat(subtotal.getText().toString()), cobrando);
            new CalculadoraCobro().setVisible(true);
        }else
        JOptionPane.showMessageDialog(null, "Aún no hay productos en venta para cobrar", "Verifique", JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_cobroActionPerformed

    private void cleanallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanallActionPerformed
        // ************************  BTN CANCELARR   ***********************
        //ESTO ELIMINA TODA LA VENTA
        if(tablaventaactiva==true){
            cerrandosesion_o_limpiandoventa();
        }
        else{
            JOptionPane.showMessageDialog(null, "No hay datos en la tabla de venta","No se puede borrar", JOptionPane.INFORMATION_MESSAGE);
        }
    }//GEN-LAST:event_cleanallActionPerformed

    private void cleanallMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cleanallMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_cleanallMouseClicked

    private void deletedescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletedescuentoActionPerformed
        descuentoactivo=false;
        descuentocombo.setText("00.00");
        total.setText(subtotal.getText());
        deletedescuento.setVisible(false);
    }//GEN-LAST:event_deletedescuentoActionPerformed

    private void ventaacreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventaacreditoActionPerformed
        float totalparaventaacredito = Float.parseFloat(subtotal.getText());
Connection ca= cc.conexion();
        if(totalparaventaacredito!=0){//SI EL TOTAL NO ES VACIO
            try{
                String nombre="";
                boolean pass2 = validarFormulariotexto(nombre=JOptionPane.showInputDialog(null,"¿A nombre de quien va ésta venta a credito?"));
                if(pass2){//ESTO VALIDA QUE EL TEXTO ESCRITO NO TENGA INCOHERENCIAS
                    int decision=JOptionPane.showConfirmDialog(null,"¿Desea continuar?","Estás por agregar una venta a credito",JOptionPane.CANCEL_OPTION);
                    if(decision==0){ //opción si
                        try{
                            comprobar_venta_resagada();//579 - 605 verifica que no haya una venta cancelada
                            get_id_usuario();// 255 -280
                            block_unlock=false;
                            tablaventaactiva=false;
                            total_venta_enturno();
                            float variable0=0;
 id_max_de_venta();
                            PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET total='"+sumadeimportes+"',descuento='"+ variable0+"',pago='"+variable0+"',cambio='"+variable0+"',fecha_reporte='"+fecha()+"',estado_venta='"+creditopendiente+"'WHERE id_venta='"+id_de_la_venta_incrementable+"'");
                            ps.executeUpdate();
 }catch(Exception ex){
                            JOptionPane.showMessageDialog(null, "Error en venta" + ex.getMessage());
                        }
                        try{
                            id_max_de_venta();
                            PreparedStatement ps2 = ca.prepareStatement ("UPDATE descripcion_de_venta SET estado= '"+creditopendiente+"',nombre_credito='"+nombre+"' WHERE id_venta='"+id_de_la_venta_incrementable+"'");
                            ps2.executeUpdate();
   descripciondelosprouductosparaelticketdeventacredito(id_de_la_venta_incrementable);//DATOS PARA EL TICKET DE VENTA
                            descripciondelosprouductosparaelticketdeventacredito(id_de_la_venta_incrementable);//DATOS PARA EL TICKET DE VENTA
         block_unlock=true;
                            accionesdespuesderealizarcualquierventa();
                        }
                        catch(Exception ex){
                            JOptionPane.showMessageDialog(null, "Error en venta" + ex.getMessage());
                        }
                    }
                }
            }catch(NullPointerException NP){}
            finally{cc.getClose();}
  }//SI EL TOTAL NO ES VACIO
        else{//CUANDO EL TOTAL ES VACIO
            JOptionPane.showMessageDialog(null, "Aún no hay productos para hacer una venta a credito");
        }//CUANDO EL TOTAL ES VACIO
    }//GEN-LAST:event_ventaacreditoActionPerformed

    private void descuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descuentoActionPerformed
        descuentos();
    }//GEN-LAST:event_descuentoActionPerformed

    private void AgregarGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarGastosActionPerformed
        if(noduplicargastos==false){new Pantalla_Gastos().setVisible(true);}
    }//GEN-LAST:event_AgregarGastosActionPerformed

    private void CortedecajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CortedecajaActionPerformed
        if(Integer.parseInt(hora)>=17||Integer.parseInt(hora)>=16){//Se puede habilitar el corte alas 5:15 pm
        if(noduplicarcorte==false){
                new Pantalla_CorteCaja().setVisible(true);
            }
        }
        else
        JOptionPane.showMessageDialog(null,"Aún es muy pronto, el corte se hace después de las 5:15 pm","Verifica",JOptionPane.INFORMATION_MESSAGE);
    }//GEN-LAST:event_CortedecajaActionPerformed

    private void tablaventaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaventaMouseClicked
        //ESTO DESCUENTA UN PRODUCTO A LA VEZ Y LO DEVUELVE A INVENTARIO
        fila =tablaventa.getSelectedRow();
 if(fila>=0){// CUANDO UNA CELDA SE SELECCIONO
            nombredepiezaseleccionada=tablaventa.getValueAt(fila,0).toString();
            if(nombredepiezaseleccionada.equals("Huesito")||nombredepiezaseleccionada.equals("Longaniza")){
                if(nombredepiezaseleccionada.equals("Huesito")){
                    id_producto(nombredepiezaseleccionada);
                    eliminarhuesito(id_producto);
                    accionesdespuesderegresarproductosainventarios();
                    mostrartabladeventas();
                }else{
                    id_producto(nombredepiezaseleccionada);
                    eliminarhuesito(id_producto);
                    accionesdespuesderegresarproductosainventarios();
                    mostrartabladeventas();
                }
  } // BOOLEANAS PARA SABER CUALES NO SE VA A REGRESAR
            else if(nombredepiezaseleccionada.equals("Pechuga en bisteck")){
                regresarproductos_pechugaenbisteck("Pechuga");
                mostrartabladeventas();
            }
            else{
                regresarproductos_a_inventario(nombredepiezaseleccionada); //pone en estatus de cancelada la venta inconclusa
                //descuentodepollo();
                mostrartabladeventas();
            }
        }else{
            JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila primero","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }
        //ESTO DESCUENTA UN PRODUCTO A LA VEZ Y LO DEVUELVE A INVENTARIO
    }//GEN-LAST:event_tablaventaMouseClicked

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        if(tablaventaactiva==true&&(storage.size())>0){
            int decision=JOptionPane.showConfirmDialog(null,"¿Desea continuar?","Advertencia: Tiene una venta inconclusa",JOptionPane.CANCEL_OPTION);
            if(decision==0){ //opción si
                cerrandosesion_o_limpiandoventa();
                JOptionPane.showMessageDialog(null,"Nos vemos pronto","Saliendo del sistema...",JOptionPane.INFORMATION_MESSAGE);
                new SI_Inicio().setVisible(true);
                this.setVisible(false);
            }
        }
        else{
            int decision2=JOptionPane.showConfirmDialog(null,"¿Desea continuar?","Saliendo del sistema",JOptionPane.CANCEL_OPTION);
            if(decision2==0){
                JOptionPane.showMessageDialog(null,"Nos vemos pronto","Saliendo del sistema...",JOptionPane.INFORMATION_MESSAGE);
                new SI_Inicio().setVisible(true);
                this.setVisible(false);
            }
        }
    }//GEN-LAST:event_jButton1ActionPerformed

    private void montoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_montoKeyReleased
    char tecla = evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            boolean pass2 = validarFormulario(monto.getText());
            if(pass2){//ESTO VALIDA QUE EL TEXTO ESCRITO NO TENGA INCOHERENCIAS
                metodo_de_cobro(Float.parseFloat(monto.getText()));
                monto.setText("00.00");
            }
        }
    }//GEN-LAST:event_montoKeyReleased

    private void montoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_montoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_montoActionPerformed

    private void montoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_montoFocusLost
        // *********************   CAJA DE TEXTO DE PAGOO *********
        if(monto.getText().trim().equals("")){
            monto.setText("00.00");
        }
        monto.setForeground(new Color(236, 240, 241));
    }//GEN-LAST:event_montoFocusLost

    private void montoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_montoFocusGained
        // *********************   CAJA DE TEXTO DE PAGOO *********
        if(monto.getText().trim().equals("00.00")){
            monto.setText("");
            //user_usuario.setForeground(Color.red);
        }
        monto.setForeground(Color.blue);
    }//GEN-LAST:event_montoFocusGained
 public static void insertorupdateoverbonnie(String nombredepieza, float cantidaddeproductos){
   obtenerelnombredeproductoylacantidaddelmismo_en_descripcion_deventa(nombredepieza);
if(NoP.equals(nombredepieza)){ //Si el nombre del producto es diferente del estado vacio, en palabras más sencillas; si se encuentra el producto que se quiere agregar para que no se asigne nuevamente  
     try{Connection ca= cc.conexion();// ESTE ES PARA EL UPDATE
          obtenerelnombredeproductoylacantidaddelmismo_en_descripcion_deventa(nombredepieza);
               NoPcantidad=1;
                NoPimporte+= +(1*cantidaddeproductos);
         id_producto(nombredepieza);
                    id_max_de_venta();
                 PreparedStatement ps = ca.prepareStatement ("UPDATE descripcion_de_venta SET cantidad='"+NoPcantidad+"',importe = '"+NoPimporte+"'WHERE id_producto='"+id_producto+"' and id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and estado= '"+estadoenturno+"'");
               int a=  ps.executeUpdate();
               if(a>0){      
                   accionesdespuesinsertarendescripciondeventaoactualizarenlamismatabla(nombredepieza, cantidaddeproductos);
                    if(descuentoactivo==true){
                if(Float.parseFloat(subtotal.getText())>0){
               totalfinalcondescuento =  Float.parseFloat(subtotal.getText()) - Float.parseFloat(descuentocombo.getText());
               total.setText(String.valueOf(totalfinalcondescuento));
                    }
                 }
               }else{
                   JOptionPane.showMessageDialog(null, " NO SE PUDO ACTIALIZAR");
               }
        }//fin del id del usuario
                 catch(Exception w){
                     JOptionPane.showMessageDialog(null, "Error en todo el codigo de update de metodo comprobar_registro" + w.getMessage());
                 }finally{cc.getClose();}//fin del id del usuario
               }
   else{
  try{Connection ca= cc.conexion(); //la insersion a la tabla ventas
                String sql = "INSERT INTO descripcion_de_venta (id_producto,nombre_producto,cantidad,precio_unitario,importe,id_venta,estado, fecha)  VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement pst = ca.prepareCall(sql); //hasta aqui vamos
                id_producto(nombredepieza); 
                pst.setInt(1,id_producto);
                noguardaridrepetidoenstorage(id_producto);
                 pst.setString(2,nombredepieza);
                pst.setFloat(3,1);
                pst.setFloat(4,0);
                importe = (float)1*cantidaddeproductos;     
                pst.setFloat(5,importe);
                id_max_de_venta();
                pst.setInt(6,(id_de_la_venta_incrementable));
                pst.setString(7, estadoenturno);
                pst.setString(8, fecha());
                int a=pst.executeUpdate();
                if(a>0){
                     accionesdespuesinsertarendescripciondeventaoactualizarenlamismatabla(nombredepieza, cantidaddeproductos);
                    if(descuentoactivo==true){//DESCUENTOACTIVO
               if(Float.parseFloat(subtotal.getText())>0){
               totalfinalcondescuento =  Float.parseFloat(subtotal.getText()) - Float.parseFloat(descuentocombo.getText());
               total.setText(String.valueOf(totalfinalcondescuento));
                    }
                 }//DESCUENTOACTIVO            
                }else{//CUANDO NO SE PUDO INSERTAR
                   }
            }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
            }finally{cc.getClose();}//fin de la insersion a la tabla ventas
    }
    }
    public static void acompletarpollo(String nombredepieza, float cantidaddeproductos){
  obtenerelnombredeproductoylacantidaddelmismo_en_descripcion_deventa(nombredepieza);
if(NoP.equals(nombredepieza)&&NoPimporte==0){ //Si el nombre del producto es diferente del estado vacio, en palabras más sencillas; si se encuentra el producto que se quiere agregar para que no se asigne nuevamente  
    try{Connection ca= cc.conexion();// ESTE ES PARA EL UPDATE
          obtenerelnombredeproductoylacantidaddelmismo_en_descripcion_deventa(nombredepieza);
      NoPcantidad=NoPcantidad+cantidaddeproductos;
                precio_producto(nombredepieza);
    id_producto(nombredepieza);
                    id_max_de_venta();
                 PreparedStatement ps = ca.prepareStatement ("UPDATE descripcion_de_venta SET cantidad='"+NoPcantidad+"',importe = '"+0+"'WHERE importe =0 and id_producto='"+id_producto+"' and id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and estado= '"+estadoenturno+"' ");
               int a=  ps.executeUpdate();
               if(a>0){
                      accionesdespuesinsertarendescripciondeventaoactualizarenlamismatabla(nombredepieza,cantidaddeproductos);
                    if(descuentoactivo==true){
                if(Float.parseFloat(subtotal.getText())>0){
               totalfinalcondescuento =  Float.parseFloat(subtotal.getText()) - Float.parseFloat(descuentocombo.getText());
               total.setText(String.valueOf(totalfinalcondescuento));
                    }
                 }
               }else{
                   JOptionPane.showMessageDialog(null, " NO SE PUDO ACTIALIZAR");
               }
        }//fin del id del usuario
                 catch(Exception w){
                     JOptionPane.showMessageDialog(null, "Error en todo el codigo de update de metodo comprobar_registro" + w.getMessage());
                 }finally{cc.getClose();}//fin del id del usuario
     }
       else{Connection ca= cc.conexion();
            try{ //la insersion a la tabla ventas
                String sql = "INSERT INTO descripcion_de_venta (id_producto,nombre_producto,cantidad,precio_unitario,importe,id_venta,estado, fecha)  VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement pst = ca.prepareCall(sql); //hasta aqui vamos
                id_producto(nombredepieza); 
                pst.setInt(1,id_producto);
               noguardaridrepetidoenstorage(id_producto);
                pst.setString(2,nombredepieza);
               
                pst.setFloat(3,cantidaddeproductos);            
                //EL METODO A CONTINUACION VA HACIENDO EL CONTEO DE LAS PIEZAS INDIVIDUALES
                // PARA UNA VEZ LLEGANDO A UN POLLO ENTERO DESCONTARLO DE LA BASE           
                precio_producto(nombredepieza);
                pst.setFloat(4,precio);
               importe = (float)cantidaddeproductos*precio;        
                pst.setFloat(5,0);
                id_max_de_venta();
                pst.setInt(6,(id_de_la_venta_incrementable));
                pst.setString(7, estadoenturno);
                pst.setString(8, fecha());
                int a=pst.executeUpdate();
                if(a>0){
                    accionesdespuesinsertarendescripciondeventaoactualizarenlamismatabla(nombredepieza, cantidaddeproductos);
                    if(descuentoactivo==true){//DESCUENTOACTIVO
                       if(Float.parseFloat(subtotal.getText())>0){
               totalfinalcondescuento =  Float.parseFloat(subtotal.getText()) - Float.parseFloat(descuentocombo.getText());
               total.setText(String.valueOf(totalfinalcondescuento));
                    }
                 }//DESCUENTOACTIVO            
                }else{//CUANDO NO SE PUDO INSERTAR
                   }
            }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
            }finally{cc.getClose();}//fin de la insersion a la tabla ventas
        }
    }
     public static void insertorupdatepechugaenbisteck(String nombredepieza, float cantidaddeproductos){
  obtenerelnombredeproductoylacantidaddelmismo_en_descripcion_deventa("Pechuga en bisteck");
if(NoP.equals("Pechuga en bisteck")&&NoPimporte!=0){ //Si el nombre del producto es diferente del estado vacio, en palabras más sencillas; si se encuentra el producto que se quiere agregar para que no se asigne nuevamente  
    try{Connection ca= cc.conexion();// ESTE ES PARA EL UPDATE
          obtenerelnombredeproductoylacantidaddelmismo_en_descripcion_deventa(nombredepieza);
          NoPcantidad=NoPcantidad+cantidaddeproductos;
                precio_producto(nombredepieza);
                NoPimporte = NoPcantidad*precio;
    id_producto(nombredepieza);
                    id_max_de_venta();
                 PreparedStatement ps = ca.prepareStatement ("UPDATE descripcion_de_venta SET cantidad='"+NoPcantidad+"',importe = '"+NoPimporte+"'WHERE importe !=0 and id_producto='"+57+"' and id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and estado= '"+estadoenturno+"' ");
               int a=  ps.executeUpdate();
               if(a>0){
                      accionesdespuesinsertarendescripciondeventaoactualizarenlamismatabla(nombredepieza,cantidaddeproductos);
                    if(descuentoactivo==true){
                if(Float.parseFloat(subtotal.getText())>0){
               totalfinalcondescuento =  Float.parseFloat(subtotal.getText()) - Float.parseFloat(descuentocombo.getText());
               total.setText(String.valueOf(totalfinalcondescuento));
                    }
                 }
               }else{
                   JOptionPane.showMessageDialog(null, " NO SE PUDO ACTIALIZAR");
               }
        }//fin del id del usuario
                 catch(Exception w){
                     JOptionPane.showMessageDialog(null, "Error en todo el codigo de update de metodo comprobar_registro" + w.getMessage());
                 }finally{cc.getClose();}//fin del id del usuario
     }
       else{
            try{Connection ca= cc.conexion(); //la insersion a la tabla ventas
                String sql = "INSERT INTO descripcion_de_venta (id_producto,nombre_producto,cantidad,precio_unitario,importe,id_venta,estado, fecha)  VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement pst = ca.prepareCall(sql); //hasta aqui vamos
                id_producto(nombredepieza); 
                pst.setInt(1,57);
               noguardaridrepetidoenstorage(57);
               pst.setString(2,"Pechuga en bisteck");
                pst.setFloat(3,cantidaddeproductos);            
                //EL METODO A CONTINUACION VA HACIENDO EL CONTEO DE LAS PIEZAS INDIVIDUALES
                // PARA UNA VEZ LLEGANDO A UN POLLO ENTERO DESCONTARLO DE LA BASE           
                 precio_producto(nombredepieza);
                pst.setFloat(4,precio);
                     importe = (float)cantidaddeproductos*precio;        
                pst.setFloat(5,importe);
                id_max_de_venta();
                pst.setInt(6,(id_de_la_venta_incrementable));
                pst.setString(7, estadoenturno);
                pst.setString(8, fecha());
                int a=pst.executeUpdate();
                if(a>0){
                    accionesdespuesinsertarendescripciondeventaoactualizarenlamismatabla(nombredepieza, cantidaddeproductos);
                    if(descuentoactivo==true){//DESCUENTOACTIVO
                   if(Float.parseFloat(subtotal.getText())>0){
               totalfinalcondescuento =  Float.parseFloat(subtotal.getText()) - Float.parseFloat(descuentocombo.getText());
               total.setText(String.valueOf(totalfinalcondescuento));
                    }
                 }//DESCUENTOACTIVO            
                                        
                }else{//CUANDO NO SE PUDO INSERTAR
                   }
            }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
            }finally{cc.getClose();}//fin de la insersion a la tabla ventas
        }
    }
   public static void agregarpiezasaventa(String nombredepieza){
         /* ******************** BOTON DE ADD NUEVO PRODUCTO PARA SU VENTA ******************** */
          primer_ventadelsistema(); // 482 - 498   Comprueba que ya haya por lo menos un id registrado en la base o en su defecto que no lo haya
       piezassuficientes(nombredepieza);//verifica primero que haya las suficientes piezas para agregar un producto a la venta    
      if(suficientespiezas==true){ // si hay piezas suficientes para agregar el articulo a la venta       
          if(primerventa==0){ //indicando que aún no se crea la primer venta del sistema
              get_id_usuario();        //entonces lo que haría despues será entrar al metodo get_id_usuario, para asignar una venta al usuario que haya iniciado sesión en la maquina
              block_unlock=false;   //se desactiva la condicion que indica que ya no se agregue otro id venta ya que aún no se ha concluido la primer venta
           if(nombredepieza.equals("Huesito")||nombredepieza.equals("Longaniza")){
        insertorupdateoverbonnie(nombredepieza, cantidaddeproductos);
          }else if(soypechugaenbisteck==true){
                 insertorupdatepechugaenbisteck("Pechuga", cantidaddeproductos);
          }else if (piezasparaacomplettarpollo.isSelected() && nombredepiezaseleccionada.equals("Pechuga")&&soypechugaenbisteck==false
                || piezasparaacomplettarpollo.isSelected() && nombredepiezaseleccionada.equals("Muslo")&&soypechugaenbisteck==false
                || piezasparaacomplettarpollo.isSelected() && nombredepiezaseleccionada.equals("Pierna")&&soypechugaenbisteck==false
                || piezasparaacomplettarpollo.isSelected() && nombredepiezaseleccionada.equals("Ala")&&soypechugaenbisteck==false
                || piezasparaacomplettarpollo.isSelected() && nombredepiezaseleccionada.equals("Huacal")&&soypechugaenbisteck==false
                || piezasparaacomplettarpollo.isSelected() && nombredepiezaseleccionada.equals("Cadera")&&soypechugaenbisteck==false
                || piezasparaacomplettarpollo.isSelected() && nombredepiezaseleccionada.equals("Cabeza")&&soypechugaenbisteck==false
                || piezasparaacomplettarpollo.isSelected() && nombredepiezaseleccionada.equals("Molleja")&&soypechugaenbisteck==false
                || piezasparaacomplettarpollo.isSelected() && nombredepiezaseleccionada.equals("Patas")&&soypechugaenbisteck==false) {
            acompletarpollo(nombredepiezaseleccionada, cantidaddeproductos);
        } 
           else{
               comprobar_registro(nombredepieza); // esto es para agregar los productos a la tabla de descripcion de venta y 
           }   
          // ya una vez concluida la venta el mismo metodo agregará dicho resultado total de la venta (a la tabla venta, bueno solo los resultados 
            // correspondientes como lo son; total, pago y cambio)
           }    
           else if(primerventa!=0){ //indicando que por lo menos ya hay una venta
           verificar_id_ingresadoalsistema(); //Comprueba que el usuario que acab de iniciar sesion coincida con el usuario anteriormente registrado
        comprobar_venta_resagada();//579 - 605 verifica que no haya una venta cancelada
get_id_usuario();// 255 -280
              block_unlock=false;   
             if(nombredepieza.equals("Huesito")||nombredepieza.equals("Longaniza")){
       insertorupdateoverbonnie(nombredepieza, cantidaddeproductos);
          }else if(soypechugaenbisteck==true){
                 insertorupdatepechugaenbisteck("Pechuga", cantidaddeproductos);
          }else if (piezasparaacomplettarpollo.isSelected() && nombredepiezaseleccionada.equals("Pechuga")
                || piezasparaacomplettarpollo.isSelected() && nombredepiezaseleccionada.equals("Muslo")
                || piezasparaacomplettarpollo.isSelected() && nombredepiezaseleccionada.equals("Pierna")
                || piezasparaacomplettarpollo.isSelected() && nombredepiezaseleccionada.equals("Ala")
                || piezasparaacomplettarpollo.isSelected() && nombredepiezaseleccionada.equals("Huacal")
                || piezasparaacomplettarpollo.isSelected() && nombredepiezaseleccionada.equals("Cadera")
                || piezasparaacomplettarpollo.isSelected() && nombredepiezaseleccionada.equals("Cabeza")
                || piezasparaacomplettarpollo.isSelected() && nombredepiezaseleccionada.equals("Molleja")
                || piezasparaacomplettarpollo.isSelected() && nombredepiezaseleccionada.equals("Patas")) {
            acompletarpollo(nombredepiezaseleccionada, cantidaddeproductos);
        } 
             else{
               comprobar_registro(nombredepieza); // esto es para agregar los productos a la tabla de descripcion de venta y 
           } 
           }
            }
            else{//No hay piezas suficientes para agregar el articulo a la venta
                JOptionPane.showMessageDialog(null,"Solo hay "+piezassuficientes+ "piezas de "+nombredepieza,"Advertencia", JOptionPane.ERROR_MESSAGE);
            }
    }
      public void descripciondeproductosenbasealnumerodeventa(int numerodeventa){
        Object[] columna = new Object[4];  //crear un obj con el nombre de colunna
            Connection ca= cc.conexion(); // CONEXION DB 
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  jTable2.setModel(modeloT);  // add modelo ala tabla 
        modeloT.addColumn("Nombre");
        modeloT.addColumn("Piezas");        
        modeloT.addColumn("Precio");
        modeloT.addColumn("Importe");
            jTable2.setModel(modeloT);  // add modelo ala tabla 
      try { String sSQL = "SELECT nombre_producto, cantidad, precio_unitario, importe FROM descripcion_de_venta WHERE estado='Realizada' AND id_venta = '"+numerodeventa+"' ";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
               // columna[0] = rs.getString("id_venta");  /* === LA DB == */
                columna[0] = rs.getString(1);
                columna[1] = rs.getString(2);
                columna[2] = rs.getString(3);      
                columna[3] = rs.getString(4); 
                modeloT.addRow(columna);
            }    jTable2.setModel(modeloT);  // add modelo ala tabla 
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Advertencia", JOptionPane.PLAIN_MESSAGE);    
    }
    }
     public void descripciondeproductosenbasealnumerodeventaporcreditopendiente(int numerodeventa){
        Object[] columna = new Object[4];  //crear un obj con el nombre de colunna
            Connection ca= cc.conexion(); // CONEXION DB 
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  ventasacreditopendiente.setModel(modeloT);  // add modelo ala tabla 
       // modeloT.addColumn("id_venta");    // add al modelo las 5 columnas con los nombrs TABLA
        modeloT.addColumn("Nombre");
        modeloT.addColumn("Piezas");        
        modeloT.addColumn("Precio");
        modeloT.addColumn("Importe");
       try {
               String sSQL = "SELECT nombre_producto, cantidad, precio_unitario, importe, nombre_credito FROM descripcion_de_venta WHERE estado='Credito-pendiente' AND id_venta = '"+numerodeventa+"' ";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
               // columna[0] = rs.getString("id_venta");  /* === LA DB == */
                columna[0] = rs.getString(1);
                columna[1] = rs.getString(2);
                columna[2] = rs.getString(3);      
                columna[3] = rs.getString(4); 
                deudor.setText(rs.getString(5));
                modeloT.addRow(columna);
            }
        }
       
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Advertencia", JOptionPane.PLAIN_MESSAGE);    
    }finally{cc.getClose();}
    }
        public void showidventasporfechas(JTable tablaventas, String fechadesde, String fechahasta){
        Object[] columna = new Object[3];  //crear un obj con el nombre de colunna
            Connection ca= cc.conexion(); // CONEXION DB 
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  tablaventas.setModel(modeloT);  // add modelo ala tabla 
           modeloT.addColumn("Venta");
        modeloT.addColumn("Total");        
        modeloT.addColumn("Fecha");
    try {
           String sSQL = "SELECT id_venta, total, fecha_reporte FROM venta WHERE estado_venta='Realizada' AND fecha_reporte BETWEEN '"+fechadesde+"' AND '"+fechahasta+ "' ";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                columna[0] = rs.getString(1);
                columna[1] = rs.getInt(2);
                   columna[2] = rs.getString(3);
                modeloT.addRow(columna);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Advertencia", JOptionPane.PLAIN_MESSAGE);    
    }finally{cc.getClose();}
    }
    
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel ACOMPAÑANTES;
    public static javax.swing.JPanel Administrador;
    private javax.swing.JButton AgregarGastos;
    private javax.swing.JPanel COCIDO;
    private javax.swing.JPanel CRUDO;
    private javax.swing.JButton Cortedecaja;
    private javax.swing.JButton Existencias;
    private javax.swing.JLabel Fecha;
    private javax.swing.JLabel IblReloj;
    public static rojerusan.RSTableMetro Jtable_ProductosEntradas;
    private rojerusan.RSTableMetro Jtable_ventasRealizadas;
    public static rojerusan.RSTableMetro JtablepaLaVenta;
    public static javax.swing.JTabbedPane Proveedores9;
    private javax.swing.JLabel Reloj;
    private javax.swing.JMenuItem activarusuario;
    private javax.swing.JButton agregar;
    public static javax.swing.JPanel agregar_articulo;
    private javax.swing.JButton bones;
    private javax.swing.JButton buscarventasporfecha;
    public static javax.swing.JLabel cambiocombobox;
    private javax.swing.JButton cancelarventa;
    private javax.swing.JButton cleanall;
    private javax.swing.JButton cobro;
    public static javax.swing.JLabel conteodelasventasrealizadas;
    private javax.swing.JButton crudo;
    private javax.swing.JButton deletedescuento;
    private javax.swing.JButton descuento;
    public static javax.swing.JLabel descuentocombo;
    private javax.swing.JLabel descuentolabel;
    private javax.swing.JLabel descuentolabel1;
    private javax.swing.JLabel deudor;
    private javax.swing.JMenuItem drop;
    private javax.swing.JMenuItem eliminar;
    private javax.swing.JMenuItem eliminarusuarios;
    private com.toedter.calendar.JDateChooser fechafinal;
    private com.toedter.calendar.JDateChooser fechainicial;
    private javax.swing.JButton imprimirventa;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton31;
    private javax.swing.JButton jButton32;
    private javax.swing.JButton jButton33;
    private javax.swing.JButton jButton34;
    private javax.swing.JButton jButton35;
    private javax.swing.JButton jButton36;
    private javax.swing.JButton jButton37;
    private javax.swing.JButton jButton38;
    private javax.swing.JButton jButton39;
    private javax.swing.JButton jButton40;
    private javax.swing.JButton jButton41;
    private javax.swing.JButton jButton42;
    private javax.swing.JButton jButton43;
    private javax.swing.JButton jButton44;
    private javax.swing.JButton jButton45;
    private javax.swing.JButton jButton46;
    private javax.swing.JButton jButton47;
    private javax.swing.JButton jButton48;
    private javax.swing.JButton jButton49;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton50;
    private javax.swing.JButton jButton51;
    private javax.swing.JButton jButton52;
    private javax.swing.JButton jButton53;
    private javax.swing.JButton jButton54;
    private javax.swing.JButton jButton55;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel100;
    private javax.swing.JLabel jLabel101;
    private javax.swing.JLabel jLabel102;
    private javax.swing.JLabel jLabel103;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel63;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel83;
    private javax.swing.JLabel jLabel91;
    private javax.swing.JLabel jLabel92;
    private javax.swing.JLabel jLabel93;
    private javax.swing.JLabel jLabel94;
    private javax.swing.JLabel jLabel95;
    private javax.swing.JLabel jLabel96;
    private javax.swing.JLabel jLabel99;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel12;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel23;
    private javax.swing.JPanel jPanel24;
    private javax.swing.JPanel jPanel26;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel31;
    private javax.swing.JPanel jPanel32;
    private javax.swing.JPanel jPanel33;
    private javax.swing.JScrollPane jScrollPane12;
    private javax.swing.JScrollPane jScrollPane13;
    private javax.swing.JScrollPane jScrollPane14;
    private javax.swing.JScrollPane jScrollPane16;
    private javax.swing.JScrollPane jScrollPane17;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane6;
    private javax.swing.JScrollPane jScrollPane7;
    private javax.swing.JSeparator jSeparator20;
    private javax.swing.JSeparator jSeparator21;
    private javax.swing.JSeparator jSeparator22;
    private javax.swing.JSeparator jSeparator7;
    private javax.swing.JTabbedPane jTabbedPane1;
    public static rojerusan.RSTableMetro jTable2;
    public static rojerusan.RSTableMetro jTable3;
    private javax.swing.JLabel labelcredito;
    private javax.swing.JLabel labeldescuento;
    private javax.swing.JLabel labelnombre;
    private javax.swing.JLabel labelparaeltotal;
    private javax.swing.JCheckBox masdeunapieza;
    private javax.swing.JCheckBox masdeunapiezacocido;
    private javax.swing.JCheckBox masdeunapiezacrudo;
    private javax.swing.JMenuItem modificar;
    private javax.swing.JMenuItem modificarusuarios;
    private javax.swing.JMenuItem modify;
    public static javax.swing.JTextField monto;
    private javax.swing.JButton pagarventaacredito;
    public static javax.swing.JCheckBox piezasparaacomplettarpollo;
    private javax.swing.JButton polloasado;
    public static javax.swing.JPanel producto_sobrante3;
    private javax.swing.JButton salsaguajillo;
    public static javax.swing.JLabel subtotal;
    private javax.swing.JPopupMenu tabla_articulos;
    private javax.swing.JPopupMenu tabla_proveedores;
    private rojerusan.RSTableMetro tabladeidventas;
    private javax.swing.JPopupMenu tablausuarios;
    public static rojerusan.RSTableMetro tablaventa;
    public static javax.swing.JLabel total;
    private javax.swing.JLabel total3;
    private javax.swing.JLabel totalventacreditoenturno;
    private javax.swing.JLabel totalventarealizada;
    private javax.swing.JLabel user;
    private javax.swing.JLabel user1;
    public static javax.swing.JPanel venta;
    private javax.swing.JButton ventaacredito;
    public static rojerusan.RSTableMetro ventasacreditopendiente;
    private javax.swing.JLabel ventascanceladaseneldia3;
    private javax.swing.JLabel ventascanceladaseneldia5;
    public static javax.swing.JLabel ventaseneldiasumadas;
    private javax.swing.JButton veridventas;
    private javax.swing.JButton veridventasacreditopendiente;
    // End of variables declaration//GEN-END:variables
}
