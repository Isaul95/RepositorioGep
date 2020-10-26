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
import ticket.Descuentosdeldia;
import ticket.ListaGastos;
import ticket.Listapagos;
import ticket.Listaventascondescuento;
import ticket.Ventasdeldia;
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
 pagosdeldia();
  utilidades=((ventasdeldia+pagosdeldia)-gastosdeldia);
 try{Connection ca= cc.conexion(); //la insersion a la tabla ventas
                String sql = "INSERT INTO  utilidad(totaldeventas,pagos,gastos,utilidad, fecha)  VALUES (?,?,?,?,?)";
               ps = ca.prepareCall(sql); //hasta aqui vamos
               ps.setDouble(1, Double.parseDouble(solodosdecimales.format(ventasdeldia).replace(",", ".")));//TOTAL DE VENTAS
               ps.setDouble(2,Double.parseDouble(solodosdecimales.format(pagosdeldia).replace(",", ".")));//PAGOS
               ps.setDouble(3, Double.parseDouble(solodosdecimales.format(gastosdeldia).replace(",", ".")));  
                ps.setDouble(4, Double.parseDouble(solodosdecimales.format(utilidades).replace(",", ".")));
                ps.setString(5, Controladorventa.fecha());
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
                                         ResultSet  rs = sent.executeQuery("select SUM(total) from venta where estado_venta in ('Realizada') and fecha_reporte= '"+Controladorventa.fecha()+"' ");
                                            while(rs.next()){
                                                      ventasdeldia =Float.parseFloat(rs.getString("SUM(total)"));
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
public static void pagosdeldia(){
        try{  Connection ca= cc.conexion();// La suma de todos los importes    
                                         Statement sent  =(Statement)ca.createStatement();
 ResultSet  rs = sent.executeQuery("select SUM(monto) from pagos  where fecha= '"+Controladorventa.fecha()+"' ");
 
 /*VENTA DE $100 A CREDITO FECHA 1 OCTUBRE DE LA VENTA
 FIRST CASE
 VENTAS $500 - GASTOS $200  
 DÍA 1 SE DEJAN $20 - 1 OCTUBRE - CREDITO PENDIENTE
 
 VENTAS $500 - GASTOS $200   
 DÍA 2 SE DEJAN $20 - 2 OCTUBRE - CREDITO PENDIENTE

 VENTAS $500 - GASTOS $200   
 DÍA 3 SE DEJAN $20 - 3 OCTUBRE- CREDITO PENDIENTE

 VENTAS $500 - GASTOS $200  
DÍA 4 SE DEJAN $20 - 4 OCTUBRE- CREDITO PENDIENTE

 VENTAS $500 - GASTOS $200  
 DÍA 5 SE DEJAN $20 - 5 OCTUBRE- REALIZADA
 
 SECOND CASE
  VENTAS $500 - GASTOS $200  
  DÍA 1 SE DEJAN $20 - 1 OCTUBRE - CREDITO PENDIENTE
 
  VENTAS $500 - GASTOS $200  
  DÍA 1 SE DEJAN $20 - 1 OCTUBRE- CREDITO PENDIENTE
 
  VENTAS $500 - GASTOS $200  
  DÍA 1 SE DEJAN $20 - 1 OCTUBRE- CREDITO PENDIENTE
 
  VENTAS $500 - GASTOS $200  
  DÍA 1 SE DEJAN $20 - 1 OCTUBRE- CREDITO PENDIENTE
 
  VENTAS $500 - GASTOS $200  
  DÍA 1 SE DEJAN $20 - 1 OCTUBRE- REALIZADA
 */
                                            while(rs.next()){
                                                      pagosdeldia =Float.parseFloat(rs.getString("SUM(monto)"));
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
                                                      hora_apertura=rs.getString("hora");
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                      }finally{cc.getClose();}// fin del precio-catch del producto    
    }
    public static void Listadepagosdeldia(){ // recibe como parametro                          
        try {Connection ca= cc.conexion();
                
            sent  = (Statement)ca.createStatement();
           rs = sent.executeQuery("select * from pagos where fecha = CURDATE()");
            while (rs.next()) {                
                             Controladorcortedecaja.columna1.add(rs.getString(2));
                             Controladorcortedecaja.columna2.add(rs.getFloat(3));
                             Controladorcortedecaja.columna3.add(rs.getString(6));
                             
            }            
              if(!Controladorcortedecaja.columna1.isEmpty()){ //Si las listas no estan vacias imprime las ventas con desccuento
           Listapagos Ticketpagos  = new Listapagos();       
            Ticketpagos.Listapagos(Controladorcortedecaja.columna1,Controladorcortedecaja.columna2,columna3); 
      }else{
                             System.out.println("No hay pagos el día de hoy");
                         }
    } catch (Exception e) {
       JOptionPane.showMessageDialog(null, "ERROR EN METODO: Listadepagosdeldia"+e.getMessage(),"DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{cc.getClose();}
}
   public static void ListadeGastosAlHacerCorteCaja(){ // recibe como parametro                          
        try {Connection ca= cc.conexion();
                
            sent  = (Statement)ca.createStatement();
           rs = sent.executeQuery("select tipo, total from egreso where fecha = CURDATE()");
            while (rs.next()) {                
                             Controladorcortedecaja.columna1.add(rs.getString(1));
                             Controladorcortedecaja.columna2.add(rs.getFloat(2));
            }            if(!columna2.isEmpty()){
                ListaGastos TicketGastosAll ; 
        TicketGastosAll = new ListaGastos();          
  TicketGastosAll.ListaGastos(Controladorcortedecaja.columna1,Controladorcortedecaja.columna2); 
            }else{
                System.out.println("No hay gastos para hoy");
            }
    } catch (Exception e) {
       JOptionPane.showMessageDialog(null, "ERROR EN METODO: ListadeGastosAlHacerCorteCaja","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{cc.getClose();}
}
    public static void ListadeventasAlHacerCorteCaja(){ // recibe como parametro                          
        try {Connection ca= cc.conexion();
            sent  = (Statement)ca.createStatement();
           rs = sent.executeQuery("SELECT CONCAT( v.id_venta ,p.nombre ) , v.total\n" +
                                               "FROM venta v\n" +
                                               "INNER JOIN descripcion_de_venta dv ON dv.id_venta = v.id_venta\n" +
                                               "INNER JOIN pacientes p ON p.id_paciente = dv.id_paciente where v.fecha_reporte = CURDATE() and  v.estado_venta = 'Realizada' and descuento = 0");
            while (rs.next()) {                
                             Controladorcortedecaja.columna1.add(rs.getString(1));
                             Controladorcortedecaja.columna2.add(rs.getFloat(2));
            }           if(!Controladorcortedecaja.columna2.isEmpty()){
                 Ventasdeldia Ventasdeldia ; 
        Ventasdeldia = new Ventasdeldia();          
  Ventasdeldia.Listadeventas(Controladorcortedecaja.columna1,Controladorcortedecaja.columna2); 
            }else{
                System.out.println("No hay ventas el día de hoy");
            }
    } catch (Exception e) {
       JOptionPane.showMessageDialog(null, "ERROR EN METODO: ListadeGastosAlHacerCorteCaja","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{cc.getClose();}
}
        public static void Listadeventascondescuento(){ // recibe como parametro                          
        try {Connection ca= cc.conexion();
            sent  = (Statement)ca.createStatement();
           rs = sent.executeQuery("SELECT CONCAT( v.id_venta, p.nombre ) , v.subtotal, v.descuento, v.total FROM venta v\n" +
"INNER JOIN descripcion_de_venta dv ON dv.id_venta = v.id_venta\n" +
"INNER JOIN pacientes p ON p.id_paciente = dv.id_paciente\n" +
"WHERE v.fecha_reporte = CURDATE( )\n" +
"AND v.estado_venta = 'Realizada' and v.descuento > 0");
            while (rs.next()) {                
                             Controladorcortedecaja.columna1.add(rs.getString(1));
                             Controladorcortedecaja.columna2.add(rs.getFloat(2));
                             Controladorcortedecaja.columna3.add(rs.getFloat(3));
                             Controladorcortedecaja.columna4.add(rs.getFloat(4));
            }  
                  if(!Controladorcortedecaja.columna3.isEmpty()){ //Si las listas no estan vacias imprime las ventas con desccuento
                    Descuentosdeldia descuentosdeldia ; 
        descuentosdeldia = new Descuentosdeldia();          
  descuentosdeldia.Listadedescuentos(Controladorcortedecaja.columna1,Controladorcortedecaja.columna2,Controladorcortedecaja.columna3,Controladorcortedecaja.columna4); 
    
      }else{
                             System.out.println("No hay ventas con descuento");
                         }
       } catch (Exception e) {
       JOptionPane.showMessageDialog(null, "ERROR EN METODO: ListadeGastosAlHacerCorteCaja","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{cc.getClose();}
}
  public static void obteniendolosvaloresdelcortedecajadeldiadehoyparaelticket(String horainiciosesion){
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
   tikectcorte.ticketcortedecaja(horainiciosesion,ticketmonto, ticketgasto, ticketventa, Float.parseFloat(solodosdecimales.format(ticketdiferencia).replace(",", ".")), Float.parseFloat(solodosdecimales.format(ventasmenosgastos).replace(",", ".")), Float.parseFloat(solodosdecimales.format(0)));  
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
                         if(!venta.isEmpty()){ //Si las listas no estan vacias imprime las ventas con desccuento
           ventascdesc = new Listaventascondescuento();     
   ventascdesc.ventascondescuento(venta, totalcdesc);  
      }else{
                             System.out.println("No hay ventas con descuento");
                         }
  
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
             ventasmenosgastos=Float.parseFloat(Cortecaja.Ventasfortoday1.getText())-gastosdeldia; 
                 diferencia=Float.parseFloat(Cortecaja.monto.getText())-ventasmenosgastos; 
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
             //LUEGO AQUI SE PUEDE REALIZAR CADA TICKET CORRESPONDIENTE (ESTOS SON LOS 3 TICKET)
ListadeGastosAlHacerCorteCaja();  // TOCKET DE LISTA DE GASTOS
 //total_numeros_y_descuentos(); //ESTO ES LA INFORMACIÓN QUE SE MANDA EN EL METODO DE ABAJO   
 obteniendolosvaloresdelcortedecajadeldiadehoyparaelticket(hora_apertura);//LOS DATOS DEL TICKET CORTE DE CAJA                                                      
//ventascondescuento(); // SE GENERA EL TICKET DE VENTAS CON DESCUENTO EN CASO DE HABER VENTAS C/DESC
 vaciarcolumnas();
 Listadepagosdeldia();
 vaciarcolumnas();
 ListadeventasAlHacerCorteCaja();
 vaciarcolumnas();
 Listadeventascondescuento();
        llenar_tabla_utilidad(gastosdeldia, ventasdeldia); //  omitido
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
  
