package Modelos;

import Controladores.Controladorcortedecaja;
import Controladores.Controladorventa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import si.Cortecaja;
import si.SI;
import ticket.ListaGastos;
import ticket.Listaventascondescuento;
import ticket.PolloCrudoxPiezas;
import ticket.ticketcortedecaja;
import ticket.ticketprocesadospiezas;

/**
 *
 * @author Alexis
 */
public class Modelocortedecaja extends Controladorcortedecaja{
    public static SI cc= new SI();
        public static Statement sent;  
  public static ResultSet rs;    
  public static PreparedStatement ps;
    public static void total_del_día_devolucion_crudo(){
    try{Connection ca= cc.conexion();
        String sql = "select SUM(total) from devolucion_crudo";
         ps= ca.prepareStatement(sql);
        rs= ps.executeQuery();
        if(rs.next()){
            total_de_crudo=rs.getFloat("SUM(total)");
        }
    }catch(Exception j){
    }finally{cc.getClose();}
}
   
public static void total_del_día_procesados(){
    try{Connection ca= cc.conexion();
        String sql = "select  SUM(total) from procesados";
         ps= ca.prepareStatement(sql);
        rs= ps.executeQuery();
        if(rs.next()){
            total_de_procesados=rs.getFloat("SUM(total)");
        }
    }catch(Exception j){
    }finally{cc.getClose();}
}
public static void pagoshechoseneldiaactual(){
       try{Connection ca= cc.conexion();
        String sql = "select SUM(total) from venta where estado_venta='Credito-pagado' and fecha_reporte = '"+Controladorventa.fecha()+ "' ";
         ps= ca.prepareStatement(sql);
        rs= ps.executeQuery();
        if(rs.next()){
            totaldepagos=rs.getFloat("SUM(total)");
        }
    }catch(Exception j){
    }finally{cc.getClose();}
}
 public static void llenar_tabla_utilidad(float gastosdeldia, float ventasdeldia){
     pagoshechoseneldiaactual();
     total_del_día_procesados();
     total_del_día_devolucion_crudo();
    diferenciaentablautilidad=(total_de_crudo+tacos-total_de_procesados-almuerzo);
 utilidades=(ventasdeldia+totaldepagos+total_de_crudo+tacos-total_de_procesados-pagopollo-almuerzo);
 try{Connection ca= cc.conexion(); //la insersion a la tabla ventas
                String sql = "INSERT INTO  utilidad(totaldeventas,pagos,totaldevolucioncrudo,totalprocesados,pagopollo,tacos,utilidad,almuerzo, diferencia, gastos, fecha)  VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                 ps = ca.prepareCall(sql); //hasta aqui vamos
               ps.setDouble(1, Double.parseDouble(solodosdecimales.format(ventasdeldia).replace(",", ".")));
               ps.setDouble(2,Double.parseDouble(solodosdecimales.format(totaldepagos).replace(",", ".")));
                ps.setDouble(3, Double.parseDouble(solodosdecimales.format(total_de_crudo).replace(",", ".")));
                ps.setDouble(4,Double.parseDouble(solodosdecimales.format(total_de_procesados).replace(",", ".")));
                ps.setDouble(5, pagopollo);
                ps.setDouble(6, tacos);
                ps.setDouble(7, Double.parseDouble(solodosdecimales.format(utilidades).replace(",", ".")));
                ps.setDouble(8, almuerzo);
                ps.setDouble(9, Double.parseDouble(solodosdecimales.format(diferenciaentablautilidad).replace(",", ".")));
                ps.setDouble(10, Double.parseDouble(solodosdecimales.format(gastosdeldia).replace(",", ".")));  
                ps.setString(11, Controladorventa.fecha());
                int a=ps.executeUpdate();
                if(a>0){           
                    }
            }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
            }finally{cc.getClose();}//fin de la insersion a la tabla ventas  
 }
 public static void productoscrudosvendidos(){
     try{Connection ca= cc.conexion();//SOLO SE LLAMA A LA CANTIDAD PORQUE EN EL TICKET YA SE DEFINIRÁN LOS NOMBRES DE CADA ARTICULO
                      sent  = (Statement)ca.createStatement();
                       rs = sent.executeQuery("SELECT DISTINCT nombre_producto FROM descripcion_de_venta where estado in('Realizada','Credito-pendiente','Credito-pagado') and fecha=curdate() and id_producto in (14,15,16,17,18,19,20,21,22,23,57,59) ORDER BY FIELD(nombre_producto, 'pollo crudo', 'Medio pollo', 'Pechuga', 'Pechuga en bisteck', 'Muslo', 'Pierna', 'Ala', 'Huacal', 'Cadera', 'Cabeza', 'Molleja', 'Patas')");
                      while(rs.next()){
                             nombrescrudovendidos.add(rs.getString(1));
                         }
      }catch(Exception e){                                             
      }finally{cc.getClose();}
 }
 public static void productoscocidosvendidos(){
     try{Connection ca= cc.conexion();//SOLO SE LLAMA A LA CANTIDAD PORQUE EN EL TICKET YA SE DEFINIRÁN LOS NOMBRES DE CADA ARTICULO
                      sent  = (Statement)ca.createStatement();
                       rs = sent.executeQuery("SELECT DISTINCT nombre_producto FROM descripcion_de_venta where estado in ('Realizada', 'Credito-pendiente', 'Credito-pagado') and fecha=curdate() and id_producto in (24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,49,50,51,52,54,55,56) ORDER BY FIELD(nombre_producto,'Mole rojo','Mole verde','Miel','Pollo rostizado','Pollo asado','Pech. broaster','Muslo broaster','Pierna broaster','Ala broaster', 'Alitas bbq','Barbacoa de pollo','Salsa guajillo (gde)','Spagueti blanco','Spagueti rojo','Arroz blanco','Arroz rojo','Frijoles puercos','Frijoles peruanos','Frijoles charros', 'Cochinita','Pure','Ens. de manzana','Ensalada de col','Ensalada rusa','Pasta de codito','Chiles en vinagre','Ver. encurtidas','Nuggets','Mininuggets','Tacos','Brochetas')");
                      while(rs.next()){
                             nombrescocidovendidos.add(rs.getString(1));
                         }
      }catch(Exception e){                                             
      }finally{cc.getClose();}
 }
    public static void insertaradevoluciondecrudopiezasycantidades(){//AQUI SE INSERTAN LAS PIEZAS Y CANTIDADES DE PECHUGA, MUSLO ALA Y PIERNA
  productoscrudosvendidos();
        for(int aa =0; aa<=nombrescrudovendidos.size()-1; aa++){

             try{Connection ca= cc.conexion();//SOLO SE LLAMA A LA CANTIDAD PORQUE EN EL TICKET YA SE DEFINIRÁN LOS NOMBRES DE CADA ARTICULO
                      sent  = (Statement)ca.createStatement();
                       rs = sent.executeQuery("select sum(cantidad), sum(importe) from descripcion_de_venta where nombre_producto='"+nombrescrudovendidos.get(aa)+"' and fecha=curdate() and estado in('Realizada', 'Credito-pendiente','Credito-pagado') " );
                       while(rs.next()){
                             cantidadescrudo.add(rs.getFloat(1));
                             importescrudosvendidos.add(rs.getFloat(2));
                         }
      }catch(Exception e){                                             
      }finally{cc.getClose();}
              Modeloventa.precio_producto(nombrescrudovendidos.get(aa).toString());
             try{Connection ca= cc.conexion();//SOLO SE LLAMA A LA CANTIDAD PORQUE EN EL TICKET YA SE DEFINIRÁN LOS NOMBRES DE CADA ARTICULO
                      sent  = (Statement)ca.createStatement();
                      rs = sent.executeQuery("select cantidad from productos WHERE nombre_producto='" +nombrescrudovendidos.get(aa)+"' ");
                      while(rs.next()){
                         sobrantecrudo.add(rs.getFloat("cantidad"));
                        }
      }catch(Exception e){                                             
      }  finally{cc.getClose();}
            try{ Connection ca= cc.conexion();//la insersion a la tabla ventas
               PreparedStatement ps = ca.prepareStatement ("UPDATE devolucion_crudo SET piezas='"+cantidadescrudo.get(aa)+"',total = '"+solodosdecimales.format(importescrudosvendidos.get(aa)).replace(",", ".")+"',fecha = '"+Controladorventa.fecha()+"',sobrante = '"+sobrantecrudo.get(aa)+"'WHERE nombre= '"+nombrescrudovendidos.get(aa)+"' ");  
                int a=ps.executeUpdate();
                if(a>0){
                 }
            }catch(SQLException e)  { //fin de la insersion a la tabla ventas
             }finally{cc.getClose();}//fin de la insersion a la tabla ventas
        }
    }//AQUI SE INSERTAN LAS PIEZAS Y CANTIDADES DE PECHUGA, MUSLO ALA Y PIERNA
        
   public static void insertaradevoluciondecocidopiezasycantidades(){//AQUI SE INSERTAN LAS PIEZAS Y CANTIDADES DE PECHUGA, MUSLO ALA Y PIERNA
 productoscocidosvendidos();
       for(int aa =0; aa<=nombrescocidovendidos.size()-1; aa++){
            try{Connection ca= cc.conexion();//SOLO SE LLAMA A LA CANTIDAD PORQUE EN EL TICKET YA SE DEFINIRÁN LOS NOMBRES DE CADA ARTICULO
                      sent  = (Statement)ca.createStatement();
                      rs = sent.executeQuery("select sum(cantidad),sum(importe) from descripcion_de_venta where nombre_producto='"+nombrescocidovendidos.get(aa)+"'and fecha=curdate() and estado in('Realizada','Credito-pendiente','Credito-pagado') " );
                      while(rs.next()){
                           cantidadescocido.add(rs.getFloat(1));
                           importescocidosvendidos.add(rs.getFloat(2));
                        }
      }catch(Exception e){                                             
      }finally{cc.getClose();}
              Modeloventa.precio_producto(nombrescocidovendidos.get(aa).toString());
              try{Connection ca= cc.conexion();//SOLO SE LLAMA A LA CANTIDAD PORQUE EN EL TICKET YA SE DEFINIRÁN LOS NOMBRES DE CADA ARTICULO
                      sent  = (Statement)ca.createStatement();
                      rs = sent.executeQuery("select cantidad from productos WHERE nombre_producto='" +nombrescocidovendidos.get(aa)+"' ");
                      while(rs.next()){
                         sobrantecocido.add(rs.getFloat("cantidad"));
                        }
      }catch(Exception e){                                             
      }finally{cc.getClose();}
            try{ Connection ca= cc.conexion();//la insersion a la tabla ventas
                PreparedStatement ps = ca.prepareStatement ("UPDATE procesados SET piezas='"+cantidadescocido.get(aa)+"',total = '"+ solodosdecimales.format(importescocidosvendidos.get(aa)).replace(",", ".")+"',fecha = '"+Controladorventa.fecha()+"',sobrante = '"+sobrantecocido.get(aa)+"'WHERE nombre= '"+nombrescocidovendidos.get(aa)+"' ");  
                int a=ps.executeUpdate();
                if(a>0){
                 }
            }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
            }finally{cc.getClose();}//fin de la insersion a la tabla ventas
        }
    }//AQUI SE INSERTAN LAS PIEZAS Y CANTIDADES DE PECHUGA, MUSLO ALA Y PIERNA
     public static void total_numeros_y_descuentos(){
 try{ Connection ca= cc.conexion(); // La suma de todos los importes
    Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select COUNT(descuento), SUM(descuento) from venta where estado_venta ='Realizada' and descuento!=0 and fecha_reporte=curdate()");
                                            while(rs.next()){
                                                numerodescuentos=rs.getShort("COUNT(descuento)");
                                                totaldedescuentos=rs.getFloat("SUM(descuento)");
                                            }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                      }finally{cc.getClose();}// fin del precio-catch del producto    
    }//PENDIENTE
   

    
public static void ventaseneldia(){
        try{  Connection ca= cc.conexion();// La suma de todos los importes
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select SUM(importe) from descripcion_de_venta where estado in ('Realizada','Credito-pendiente','Credito-pagado') and fecha= '"+Controladorventa.fecha()+"'");
                                            while(rs.next()){
                                                      ventasdeldia =Float.parseFloat(rs.getString("SUM(importe)"));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                      }finally{cc.getClose();}// fin del precio-catch del producto
    
}

public static void metodogastosdeldia(){
        try{  Connection ca= cc.conexion();// La suma de todos los importes    
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select SUM(total) from egreso where fecha= '"+Controladorventa.fecha()+"'");
                                            while(rs.next()){
                                                      gastosdeldia =Float.parseFloat(rs.getString("SUM(total)"));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                      }finally{cc.getClose();}// fin del precio-catch del producto

}

    public static void aperturadeldia(){
        try{  Connection ca= cc.conexion();// La suma de todos los importes    
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select * from apertura where fecha= '"+Controladorventa.fecha()+"'");
                                            while(rs.next()){
                                                      apertura =rs.getShort("id_apertura");
                                                      montodeapertura= rs.getFloat("monto");
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                      }finally{cc.getClose();}// fin del precio-catch del producto    
    }
        public static void vaciartodoeninventario(){
              try{ Connection ca= cc.conexion();              
           PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad= 0 WHERE nombre_producto NOT IN ('Huacal', 'Cadera', 'Cabeza', 'Molleja', 'Patas', 'Longaniza', 'Mole rojo', 'Mole verde', 'Miel', 'Ens. de manzana', 'Ensalada de col', 'Ensalada rusa', 'Pasta de codito', 'Chiles en vinagre', 'Ver. encurtidas')");
                  int a = ps.executeUpdate();
                if(a>0){    
                }
                           }catch(Exception e){
                               System.err.print(e);
                           } finally{cc.getClose();}
            }
    public static void vaciartodoelpollococidoenprocesados(){
              try{    Connection ca= cc.conexion();           
           PreparedStatement ps = ca.prepareStatement ("UPDATE procesados SET total= 0, piezas=0, sobrante=0");
                  int a = ps.executeUpdate();
                if(a>0){    
                }
                           }catch(Exception e){
                               System.err.print(e);
                           }finally{cc.getClose();}
            }
        public static void vaciartodoelpollocrudoendevolucioncrudo(){
              try{       Connection ca= cc.conexion();        
           PreparedStatement ps = ca.prepareStatement ("UPDATE devolucion_crudo SET total= 0, piezas=0, sobrante=0");
                  int a = ps.executeUpdate();
                if(a>0){    
                }
                           }catch(Exception e){
                               System.err.print(e);
                           }finally{cc.getClose();}
            }
    
 public static void sobrantedepollocrudodeldiaparaticketcantidadesypiezas(){//TICKET DEVOLUCION CRUDO,  LAS CANTIDADES Y PIEZAS
      try{ Connection ca= cc.conexion();//SOLO SE LLAMA A LA CANTIDAD PORQUE EN EL TICKET YA SE DEFINIRÁN LOS NOMBRES DE CADA ARTICULO
           
          sent  = (Statement)ca.createStatement();
                      rs = sent.executeQuery("select nombre, sobrante, piezas, total from devolucion_crudo ORDER BY FIELD(nombre, 'pollo crudo', 'Medio pollo', 'Pechuga', 'Pechuga en bisteck', 'Muslo', 'Pierna', 'Ala', 'Huacal', 'Cadera', 'Cabeza', 'Molleja', 'Patas')");
                         while(rs.next()){
                             nombrecrudo.add(rs.getString(1));
                             sobrantedecrudo.add(rs.getFloat(2));
                             piezasdecrudo.add(rs.getFloat(3));
                              totalesdecrudo.add(rs.getFloat(4));
                         }
                         total_del_día_devolucion_crudo();
   PolloCrudoxPiezas = new PolloCrudoxPiezas();          
   PolloCrudoxPiezas.PolloCrudoxPiezas(nombrecrudo, sobrantedecrudo, piezasdecrudo,totalesdecrudo, Float.parseFloat(solodosdecimales.format(total_de_crudo).replace(",", ".")));                       
      }catch(Exception e){                                             
          System.out.println("ERROR en sobrantedepollocrudodeldiaparaticketcantidadesypiezas: " + e.getMessage());
      }finally{cc.getClose();}
 }//TICKET DEVOLUCION CRUDO,  LAS CANTIDADES Y PIEZAS
   public static void ListadeGastosAlHacerCorteCaja(){ // recibe como parametro                          
        try {Connection ca= cc.conexion();
                
            sent  = (Statement)ca.createStatement();
           rs = sent.executeQuery("select tipo, total from egreso where fecha = CURDATE()");
            while (rs.next()) {                
                             Controladorcortedecaja.columna1.add(rs.getString(1));
                             Controladorcortedecaja.columna2.add(rs.getFloat(2));
            }            ListaGastos TicketGastosAll ; 
        TicketGastosAll = new ListaGastos();          
  TicketGastosAll.ListaGastos(Controladorcortedecaja.columna1,Controladorcortedecaja.columna2); 
    } catch (Exception e) {
       JOptionPane.showMessageDialog(null, "ERROR EN METODO: productosvendidoseneldia","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{cc.getClose();}
}
       
       
       public static void insertartotaldechorizo(){

       try{
       Connection ca= cc.conexion();//SOLO SE LLAMA A LA CANTIDAD PORQUE EN EL TICKET YA SE DEFINIRÁN LOS NOMBRES DE CADA ARTICULO
                      sent  = (Statement)ca.createStatement();
                      rs = sent.executeQuery("select SUM(importe) from descripcion_de_venta where nombre_producto='Longaniza' and fecha=  '"+Controladorventa.fecha()+"' ");
                   if(rs.next()){
                     totalchorizoyhuesito=rs.getFloat(1);
                        }                 
     }catch(Exception e){   
         JOptionPane.showMessageDialog(null, "errror en select insertartotaldechorizo");
      }finally{
             cc.getClose();}
      try{ 
                Connection ca= cc.conexion();//la insersion a la tabla ventas
                PreparedStatement ps = ca.prepareStatement ("UPDATE procesados SET piezas='"+0+"',total = '"+totalchorizoyhuesito +"',fecha = '"+Controladorventa.fecha()+"',sobrante = '"+0+"'WHERE nombre= 'Longaniza' ");  
                int a=ps.executeUpdate();
                if(a>0){
                 }
            }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
            }finally{
                 cc.getClose();}//fin de la insersion a la tabla ventas
  }
       public static void insertartotaldehuesito(){
       try{
       Connection ca= cc.conexion();//SOLO SE LLAMA A LA CANTIDAD PORQUE EN EL TICKET YA SE DEFINIRÁN LOS NOMBRES DE CADA ARTICULO
                      sent  = (Statement)ca.createStatement();
                      rs = sent.executeQuery("select SUM(importe) from descripcion_de_venta where nombre_producto='Huesito' and fecha=  '"+Controladorventa.fecha()+"' ");
                   if(rs.next()){
                     totalchorizoyhuesito=rs.getFloat(1);
                        }                 
     }catch(Exception e){   
         JOptionPane.showMessageDialog(null, "errror en select insertartotaldehuesito");
      }finally{
             cc.getClose();}
      try{ 
                Connection ca= cc.conexion();//la insersion a la tabla ventas
                PreparedStatement ps = ca.prepareStatement ("UPDATE procesados SET piezas='"+0+"',total = '"+totalchorizoyhuesito +"',fecha = '"+Controladorventa.fecha()+"',sobrante = '"+0+"'WHERE nombre= 'Huesito' ");  
                int a=ps.executeUpdate();
                if(a>0){
                 }
            }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
            }finally{
                 cc.getClose();}//fin de la insersion a la tabla ventas
  }
 
       
  public static void sobrantedepollococidodeldiaparaticketcantidadesypiezas(){//TICKET DEVOLUCION COCIDO,  LAS CANTIDADES Y PIEZAS
      try{Connection ca= cc.conexion();//SOLO SE LLAMA A LA CANTIDAD PORQUE EN EL TICKET YA SE DEFINIRÁN LOS NOMBRES DE CADA ARTICULO
             //  sent  = (Statement)ca.createStatement();
                      rs = sent.executeQuery("select nombre, sobrante, piezas, total from procesados ORDER BY FIELD(nombre, 'Huesito','Longaniza','Mole rojo','Mole verde','Miel','Pollo rostizado','Pollo asado','Pech. broaster','Muslo broaster','Pierna broaster','Ala broaster', 'Alitas bbq','Barbacoa de pollo','Salsa guajillo (gde)','Spagueti blanco','Spagueti rojo','Arroz blanco','Arroz rojo','Frijoles puercos','Frijoles peruanos','Frijoles charros', 'Cochinita','Pure','Ens. de manzana','Ensalada de col','Ensalada rusa','Pasta de codito','Chiles en vinagre','Ver. encurtidas','Nuggets','Mininuggets','Tacos','Brochetas')\n" +
"        ");
                         while(rs.next()){
                             nombrecocido.add(rs.getString(1));
                             sobrantedecocido.add(rs.getFloat(2));
                             piezasdecocido.add(rs.getFloat(3));
                              totalesdecocido.add(rs.getFloat(4));                             
                         }                        
    ticketprocesadospiezas = new ticketprocesadospiezas();       
     total_del_día_procesados();    
     ticketprocesadospiezas.ticketprocesadospiezas(nombrecocido,sobrantedecocido,piezasdecocido, totalesdecocido, Float.parseFloat(solodosdecimales.format(total_de_procesados).replace(",", ".")));                
      }catch(Exception e){                                                     
          // System.out.println("DESDE PROCESADOS"+ total_de_procesados);
      }finally{cc.getClose();}
 }//TICKET DEVOLUCION COCIDO,  LAS CANTIDADES Y PIEZAS
 
  public static void obteniendolosvaloresdelcortedecajadeldiadehoyparaelticket(int numerodedescuentos, float totaldescuentos){
      try{Connection ca= cc.conexion();//SOLO SE LLAMA A LA CANTIDAD PORQUE EN EL TICKET YA SE DEFINIRÁN LOS NOMBRES DE CADA ARTICULO
                      sent  = (Statement)ca.createStatement();
                      rs = sent.executeQuery("select monto_entregado, gastos, ventas, diferencia from cortes where fecha=  '"+Controladorventa.fecha()+"' ");
                         while(rs.next()){
                             ticketmonto=rs.getFloat(1);
                             ticketgasto=rs.getFloat(2);
                             ticketventa=rs.getFloat(3);
                             ticketdiferencia=rs.getFloat(4);
                         }                 
                         pagoshechoseneldiaactual();
   tikectcorte = new ticketcortedecaja();     
   tikectcorte.ticketcortedecaja(ticketmonto, ticketgasto, ticketventa, Float.parseFloat(solodosdecimales.format(ticketdiferencia).replace(",", ".")), Float.parseFloat(solodosdecimales.format(ventasmenosgastos).replace(",", ".")), Float.parseFloat(solodosdecimales.format(totaldepagos)), numerodedescuentos, totaldescuentos);  
      }catch(Exception e){                                             
      }finally{cc.getClose();}
 }
   public static void ventascondescuento(){
      try{Connection ca= cc.conexion();//SOLO SE LLAMA A LA CANTIDAD PORQUE EN EL TICKET YA SE DEFINIRÁN LOS NOMBRES DE CADA ARTICULO
                      sent  = (Statement)ca.createStatement();
                      rs = sent.executeQuery("select id_venta, descuento from venta where fecha_reporte=  '"+Controladorventa.fecha()+"' and estado_venta='Realizada' and descuento>0");
                         while(rs.next()){
                             venta.add(rs.getShort(1));
                             totalcdesc.add(rs.getFloat(2));
                         }                 
                     
   ventascdesc = new Listaventascondescuento();     
   ventascdesc.ventascondescuento(venta, totalcdesc);  
      }catch(Exception e){                                             
      }finally{cc.getClose();}
 }
  public static void hacercorte(){
       boolean pass2 = Controladorventa.validarFormulario(Cortecaja.monto.getText());
                 if(pass2){//ESTO VALIDA QUE EL TEXTO ESCRITO NO TENGA INCOHERENCIAS   
                 int decision=JOptionPane.showConfirmDialog(null,"¿Desea continuar?","UNA VEZ REALIZADO EL CORTE, SOLO EL ADMIN PUEDE ENTRAR",JOptionPane.CANCEL_OPTION);
            if(decision==0){// SI SE ELIGE QUE SI, PROCEDEMOS A REALIZAR EL CORTE E IMPRIMIR LOS 5 TICKET
                //QUE SON, 1.- CORTE DE CAJA, 2.- CRUDO, PECHUGA PIERNA, ALA Y MUSLO, 3.- CRUDO DEL RESTO PERO EN CANTIDADES
                //4.-COCIDO, TOTALES Y PIEZAS, 5.- COCIDO PIEZAS            
        if(Float.parseFloat(Cortecaja.monto.getText())>0){//COMPROBANDO QUE EL MONTO NO ESTE VACIO               
          try{ Connection ca= cc.conexion();//la insersion a la tabla ventas
              metodogastosdeldia();
              aperturadeldia();
              pagoshechoseneldiaactual();
             ventasmenosgastos=Float.parseFloat(Cortecaja.Ventasfortoday1.getText())-gastosdeldia+totaldepagos; 
                 diferencia=Float.parseFloat(Cortecaja.monto.getText())-ventasmenosgastos;
                  diferenciafinal=montodeapertura-diferencia;
                
    String sql = "INSERT INTO  cortes(id_apertura, monto_entregado, gastos, ventas, diferencia, fecha, hora, usuario)  VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement pst = ca.prepareCall(sql); //hasta aqui vamos
                pst.setInt(1,apertura);
                 pst.setFloat(2,Float.parseFloat(Cortecaja.monto.getText()));
                  pst.setFloat(3,gastosdeldia);
                   pst.setFloat(4,ventasdeldia);
                    pst.setFloat(5,Float.parseFloat(solodosdecimales.format(diferencia).replace(",", ".")));
                pst.setString(6,Controladorventa.fecha());
                pst.setString(7,Cortecaja.Reloj.getText());
                pst.setInt(8,id_usuario);
                int a=pst.executeUpdate();
                if(a>0){insertartotaldechorizo();insertartotaldehuesito();
             //PRIMERO SE REGISTRAN LOS PRODUCTOS SOBRANTES TANTO LOS COCIDOS COMO LOS CRUDOS 
             //LUEGO AQUI SE PUEDE REALIZAR CADA TICKET CORRESPONDIENTE (ESTOS SON LOS 5 TICKET)
                 insertaradevoluciondecrudopiezasycantidades();//SE REGISTRAN LAS PIEZAS, CANTIDADES Y TOTALES DE TODO CRUDO
            insertaradevoluciondecocidopiezasycantidades();//SE REGISTRAN LAS PIEZAS, CANTIDADES Y TOTALES DE TODO COCIDO                
            
 sobrantedepollocrudodeldiaparaticketcantidadesypiezas();//SOBRANTE Y VENDIDO DE CRDUO
 sobrantedepollococidodeldiaparaticketcantidadesypiezas();//SOBRANTE Y VENDIDO DE COCIDO
ListadeGastosAlHacerCorteCaja();  // TOCKET DE LISTA DE GASTOS
 total_numeros_y_descuentos();    
 /*sii*/obteniendolosvaloresdelcortedecajadeldiadehoyparaelticket(numerodescuentos, totaldedescuentos);//LOS DATOS DEL TICKET CORTE DE CAJA                                                      
  ventascondescuento();
            llenar_tabla_utilidad(gastosdeldia, ventasdeldia);
      //vaciartodoelpollococidoenprocesados();
        //  vaciartodoelpollocrudoendevolucioncrudo();
     // vaciartodoeninventario();//UNA VEZ IMPRESO LOS 5 TICKETS SE VACIA TODO EL INVENTARIO            
                JOptionPane.showMessageDialog(null,"Nos vemos pronto","Saliendo del sistema...",JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
                }
            }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
            }finally{cc.getClose();}//fin de la insersion a la tabla ventas         
    }//FIN DE COMPROBANDO QUE EL MONTO NO ESTE VACIO
else{//CUANDO EL MONTO ESTA VACIO
            JOptionPane.showMessageDialog(null,"El monto no puede estar vacio o en 0"); 
}// FIN DE CUANDO EL MONTO ESTA VACIO
            }//SI SE ELIGE QUE SI, PROCEDEMOS A REALIZAR EL CORTE E IMPRIMIR LOS 5 TICKET
                 }   
  }
}
  
