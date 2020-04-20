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
import ticket.ticketcortedecaja;

/**
 *
 * @author Alexis
 */
public class Modelocortedecaja extends Controladorcortedecaja{
    public static SI cc= new SI();
        public static Statement sent;  
  public static ResultSet rs;    
  public static PreparedStatement ps;
 public static void llenar_tabla_utilidad(float gastosdeldia, float ventasdeldia){
    diferenciaentablautilidad=(0+tacos-0-almuerzo);
 utilidades=(ventasdeldia+0+0+tacos-0-pagopollo-almuerzo);
 try{Connection ca= cc.conexion(); //la insersion a la tabla ventas
                String sql = "INSERT INTO  utilidad(totaldeventas,pagos,totaldevolucioncrudo,totalprocesados,pagopollo,tacos,utilidad,almuerzo, diferencia, gastos, fecha)  VALUES (?,?,?,?,?,?,?,?,?,?,?)";
                 ps = ca.prepareCall(sql); //hasta aqui vamos
               ps.setDouble(1, Double.parseDouble(solodosdecimales.format(ventasdeldia).replace(",", ".")));
               ps.setDouble(2,Double.parseDouble(solodosdecimales.format(0).replace(",", ".")));
                ps.setDouble(3, Double.parseDouble(solodosdecimales.format(0).replace(",", ".")));
                ps.setDouble(4,Double.parseDouble(solodosdecimales.format(0).replace(",", ".")));
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
                                         ResultSet  rs = sent.executeQuery("select SUM(importe) from descripcion_de_venta where estado in ('Realizada','Credito-pendiente','Credito-pagado') and fecha= '"+Controladorventa.fecha()+"'and id_producto != 60");
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
       JOptionPane.showMessageDialog(null, "ERROR EN METODO: ListadeGastosAlHacerCorteCaja","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{cc.getClose();}
}
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
   tikectcorte = new ticketcortedecaja();     
   tikectcorte.ticketcortedecaja(ticketmonto, ticketgasto, ticketventa, Float.parseFloat(solodosdecimales.format(ticketdiferencia).replace(",", ".")), Float.parseFloat(solodosdecimales.format(ventasmenosgastos).replace(",", ".")), Float.parseFloat(solodosdecimales.format(0)), numerodedescuentos, totaldescuentos);  
      }catch(Exception e){                   JOptionPane.showMessageDialog(null, "ERROR EN METODO: obteniendolosvaloresdelcortedecajadeldiadehoyparaelticket","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);                                                 
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
           JOptionPane.showMessageDialog(null, "ERROR EN METODO: ventascondescuento","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);                                  
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
             ventasmenosgastos=Float.parseFloat(Cortecaja.Ventasfortoday1.getText())-gastosdeldia+0; 
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
                if(a>0){
             //PRIMERO SE REGISTRAN LOS PRODUCTOS SOBRANTES TANTO LOS COCIDOS COMO LOS CRUDOS 
             //LUEGO AQUI SE PUEDE REALIZAR CADA TICKET CORRESPONDIENTE (ESTOS SON LOS 5 TICKET)
ListadeGastosAlHacerCorteCaja();  // TOCKET DE LISTA DE GASTOS
 total_numeros_y_descuentos();    
 /*sii*/obteniendolosvaloresdelcortedecajadeldiadehoyparaelticket(numerodescuentos, totaldedescuentos);//LOS DATOS DEL TICKET CORTE DE CAJA                                                      
  ventascondescuento();
            llenar_tabla_utilidad(gastosdeldia, ventasdeldia);
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
  
