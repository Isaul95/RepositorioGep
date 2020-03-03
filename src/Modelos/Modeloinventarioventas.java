/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Controladores.Controladorinventarioventas;
import Controladores.Controladorventa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import si.Inventarioventas;
import static si.Inventarioventas.jTable3;
import si.SI;
import ticket.ticketventacancelada;
import ticket.ticketventacondescuento;
import ticket.ticketventacredito;

/**
 *
 * @author aleks
 */
public class Modeloinventarioventas extends Controladorinventarioventas{
    static SI cc= new SI();
   static Statement sent;  
static ResultSet rs;  
public static void la_venta_tiene_descuento_si_o_no(int id_venta){
        try{ Connection ca= cc.conexion();// La suma de todos los importes
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select descuento from venta where fecha_reporte= '"+Controladorventa.fecha()+"' AND id_venta='"+id_venta+"'");
                                            if(rs.next()){
                                                      descuentoenventa =rs.getFloat(1);
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                           JOptionPane.showMessageDialog(null, "ERROR EN METODO: la_venta_tiene_descuento_si_o_no","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);       
                                                      }// fin del precio-catch del producto
        finally{
                  cc.getClose();
             }
    }
public static void eliminar_idventa_sitienedescuento(float descuento, int id_venta){
    if(descuento!=0){
        try{ Connection ca= cc.conexion();// La suma de todos los importes
                                        String sql = "delete from egreso where id_venta= '"+id_venta+"'";
                                           PreparedStatement ps = ca.prepareStatement(sql);
                                           ps.execute();     
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                           JOptionPane.showMessageDialog(null, "ERROR EN METODO: eliminar_idventa_sitienedescuento","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);       
                                                      }// fin del precio-catch del producto
        finally{
                  cc.getClose();
             }
    }
    }
    public static void totaldelasventasdehoy(){
        try{ Connection ca= cc.conexion();// La suma de todos los importes
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select SUM(total) from venta where fecha_reporte= '"+Controladorventa.fecha()+"' AND estado_venta='Realizada' ");
                                            if(rs.next()){
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
                                         ResultSet  rs = sent.executeQuery("SELECT COUNT(id_venta) FROM venta WHERE fecha_reporte = '"+Controladorventa.fecha()+"' AND estado_venta='Realizada'");
                                            if(rs.next()){
                                                      conteototaldeventas =Short.parseShort(String.valueOf(rs.getInt("COUNT(id_venta)")));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                           JOptionPane.showMessageDialog(null, "ERROR EN METODO: conteodeventasrealizadasdehoy","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
                                                      }// fin del precio-catch del producto
        finally{
                  cc.getClose();
             }
    }
     public static void llenartablaidventasconidrealizados(){ // recibe como parametro 
         Object[] columna = new Object[3];  //crear un obj con el nombre de colunna
             DefaultTableModel modeloTE = new DefaultTableModel(); 
                  Inventarioventas.jTable2.setModel(modeloTE);  // add modelo ala tabla 
        modeloTE.addColumn("Venta");
        modeloTE.addColumn("Total");        
        modeloTE.addColumn("Fecha");
    Inventarioventas.jTable2.setModel(modeloTE);  // add modelo ala tabla         
        try {  Connection ca= cc.conexion(); // CONEXION DB 
             String sSQL = "SELECT id_venta, total, fecha_reporte FROM venta WHERE estado_venta in('Realizada') AND fecha_reporte = '"+Controladorventa.fecha()+"' ";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
                columna[0] = rs.getInt(1);
                columna[1] = "$"+String.valueOf(rs.getFloat(2));
                 columna[2] = rs.getString(3);
                modeloTE.addRow(columna);
            }
          Inventarioventas.jTable2.setModel(modeloTE);  // add modelo ala tabla 
                             TableColumnModel columnModel =   Inventarioventas.jTable2.getColumnModel();columnModel.getColumn(0).setPreferredWidth(30);columnModel.getColumn(1).setPreferredWidth(30);    columnModel.getColumn(2).setPreferredWidth(150);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "ERROR EN METODO: llenartablaidventasconidrealizados","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
       }finally{
                  cc.getClose();
             }
}
     public static void productosvendidoseneldia(){ // recibe como parametro 
         Object[] columna = new Object[3];  //crear un obj con el nombre de colunna
          DefaultTableModel modeloT = new DefaultTableModel(); 
                  Inventarioventas.jTable3.setModel(modeloT);  // add modelo ala tabla 
        modeloT.addColumn("Producto");
        modeloT.addColumn("Cantidad");        
        modeloT.addColumn("importe");
          Inventarioventas.jTable3.setModel(modeloT);  // add modelo ala tabla 
       TableColumnModel columnModel =  jTable3.getColumnModel();columnModel.getColumn(0).setPreferredWidth(200);columnModel.getColumn(1).setPreferredWidth(50);    columnModel.getColumn(1).setPreferredWidth(70);
         try {   Connection ca= cc.conexion(); // CONEXION DB 
         String sSQL = "SELECT nombre_producto, SUM(cantidad), SUM(importe) FROM  descripcion_de_venta WHERE estado in('Realizada', 'Credito-pendiente') AND fecha = CURDATE() GROUP BY nombre_producto";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
                columna[0] = rs.getString(1);
                columna[1] = rs.getString(2);
                columna[2] = "$"+rs.getString(3);                
                modeloT.addRow(columna);
            } Inventarioventas.jTable3.setModel(modeloT);  // add modelo ala tabla 

    } catch (Exception e) { JOptionPane.showMessageDialog(null, "ERROR EN METODO: productosvendidoseneldia","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{
                  cc.getClose();
             }
}
     public static void llenartablaconventasacreditopendiente(){
        Object[] columna = new Object[4];  //crear un obj con el nombre de colunna
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  Inventarioventas.ventasacreditopendiente.setModel(modeloT);  // add modelo ala tabla 
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
                columna[1] = "$"+rs.getString("venta.total");
                columna[2] = rs.getString("venta.fecha_reporte");    
                columna[3] = rs.getString("descripcion_de_venta.nombre_credito");
                modeloT.addRow(columna);
            }
        }       TableColumnModel columnModel =   Inventarioventas.ventasacreditopendiente.getColumnModel();columnModel.getColumn(0).setPreferredWidth(15);columnModel.getColumn(1).setPreferredWidth(15);    columnModel.getColumn(2).setPreferredWidth(50);columnModel.getColumn(3).setPreferredWidth(200);
    } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "ERROR EN METODO: llenartablaconventasacreditopendiente"+e.getStackTrace(),"DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{
                  cc.getClose();
             }
    } 
     public static void showidventasporfechas(JTable tablaventas, String fechadesde, String fechahasta){
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
                columna[1] = "$"+String.valueOf(rs.getInt(2) );
                   columna[2] = rs.getString(3);
                modeloT.addRow(columna);
            }
        }                             TableColumnModel columnModel =   Inventarioventas.jTable2.getColumnModel();columnModel.getColumn(0).setPreferredWidth(30);columnModel.getColumn(1).setPreferredWidth(30);    columnModel.getColumn(2).setPreferredWidth(150);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Advertencia", JOptionPane.PLAIN_MESSAGE);    
    }finally{cc.getClose();}
    }
     public static void pagarventacredito(int id_ventapencredito){
    DecimalFormat solodosdecimales = new DecimalFormat("#.##");
    try{
            String pagodeventacredito="";
            float variable0=0, totalacredito=0, cambio=0;
            boolean pass2 = Controladorventa.validarFormulario(pagodeventacredito= JOptionPane.showInputDialog(null,"Escriba el monto de pago","Pagando venta a credito", JOptionPane.INFORMATION_MESSAGE));
            if(pass2){//ESTO VALIDA QUE EL TEXTO ESCRITO NO TENGA INCOHERENCIAS
int decision=JOptionPane.showConfirmDialog(null,"¿Desea continuar?","Estás por pagar una venta a credito",JOptionPane.CANCEL_OPTION);
                if(decision==0){ //opción si
                    total_venta_creditopendiente(id_ventapencredito);
                    if(Float.parseFloat(pagodeventacredito)>=sumadeimportescreditopendiente){
                        try{Connection ca= cc.conexion();
                            cambio = Float.parseFloat(pagodeventacredito)-sumadeimportescreditopendiente;
                            PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET descuento='"+ variable0+"',pago='"+solodosdecimales.format(Float.parseFloat(pagodeventacredito)).replace(",", ".")+"',cambio='"+solodosdecimales.format(cambio).replace(",", ".")+"',fecha_reporte='"+Controladorventa.fecha()+"',estado_venta='"+creditopagado+"'WHERE id_venta='"+id_ventapencredito+"'");
                            ps.executeUpdate();
                        }catch(Exception ex){
                            JOptionPane.showMessageDialog(null, "Error en pagarventacredito" + ex.getMessage());
                        }finally{
                            cc.getClose();
                        }
                        try{Connection ca= cc.conexion();
                            Modeloventa.id_max_de_venta();
                            PreparedStatement ps2 = ca.prepareStatement ("UPDATE descripcion_de_venta SET estado= '"+creditopagado+"' WHERE id_venta='"+id_ventapencredito+"'");
                            int a = ps2.executeUpdate();
                            if(a>0){
                                Controladorventa.accionesdespuesderealizarcualquierventa();
                                Inventarioventas.pagarventaacredito.setVisible(false);
                                Inventarioventas.labelnombre.setVisible(false);
                                Inventarioventas.labelcredito.setVisible(false);
                                Inventarioventas.totalventacreditoenturno.setVisible(false);
                                Inventarioventas.deudor.setVisible(false);
                                Inventarioventas.veridventasacreditopendiente.setVisible(false);
                                Modeloinventarioventas.llenartablaconventasacreditopendiente(); //CARGA NUEVAMENTE LAS VENTAS POR ID
  }
                        }
                        catch(Exception ex){
                            JOptionPane.showMessageDialog(null, "Error en pagarventacredito" + ex.getMessage());
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
}
     public static void total_venta_creditopendiente(int id){
        try{ Connection ca= cc.conexion();// La suma de todos los importes
                                          sent  =(Statement)ca.createStatement();
                                           rs = sent.executeQuery("select total from venta where id_venta= '"+id+"'");
                                            if(rs.next()){
                                                      sumadeimportescreditopendiente =Float.parseFloat(rs.getString("total"));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){ JOptionPane.showMessageDialog(null, "Error, total_venta_creditopendiente","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
                sumadeimportescreditopendiente=0;
                                                      }finally{
                    cc.getClose();
                }// fin del precio-catch del producto
    }
     public static void impresiondeventacancelada(int numerodeventa){
    try {Connection ca= cc.conexion();
                 String sSQL = "SELECT nombre_producto, cantidad, precio_unitario, importe FROM descripcion_de_venta WHERE estado='Cancelada' AND id_venta = '"+numerodeventa+"' ";  
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
              nombreproductoticket.add(rs.getString(1));
              piezastcket.add(rs.getString(2));
              preciounitarioticket.add(rs.getString(3)); 
              importesticket.add(rs.getString(4));
            }total_pagoycambiopararelticketdeventacancelada(numerodeventa);
                            //estas dos lineas mandan los datos para el ticket
                 mandardatosticketventacancelada = new ticketventacancelada();
                 mandardatosticketventacancelada.tikectventacancelada(nombreproductoticket, 
                         piezastcket, 
                         preciounitarioticket, 
                         importesticket,
                         subtotalticket, totalticket, pagoticket, cambioticket, descuentoticket, numerodeventa);
            //totalcdescticket agregar al metodo de arriba
                 Controladorventa.vaciarlistasdeticket();
    } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "ERROR EN METODO: reimpresiondeventa","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{
                  cc.getClose();
             }
    }
     
public static void total_pagoycambiopararelticketdeventacancelada(int id){ // recibe como parametro 
         Object[] columna = new Object[5];  //crear un obj con el nombre de colunna
        try {Connection ca= cc.conexion();
         String sSQL = "SELECT subtotal, total, pago, cambio, descuento FROM venta WHERE estado_venta='"+estadocancelado+"' AND fecha_reporte = '"+Controladorventa.fecha()+"' and id_venta='"+id+"' ";
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

   public static void ids_y_cantidades_porcancelacion(short id){
try {Connection ca= cc.conexion();
        Modeloventa.id_max_de_venta();
             sent = ca.createStatement();   
                 rs= sent.executeQuery("select id_producto, cantidad from  descripcion_de_venta where id_venta= '"+id+"' and fecha= '"+Controladorventa.fecha()+"' and  estado = '"+estadocancelado+"'"); // se ejecuta la sentencia dentro del parentesis
            while(rs.next()){        
            Controladorventa.idsenturno.add(0, rs.getInt(1));
            Controladorventa.cantidaddecadaidenturno.add(0, rs.getFloat(2));
            }
 Controladorventa.idsenturno.clear();
            Controladorventa.cantidaddecadaidenturno.clear();
 //SE ALMACENÓ LA VENTA COMO EN TURNO Y EL METODO ANTERIOR A ESTE NO LA PUEDE DETECTAR PORQUE SOLO ELIMINA LAS VENTA EN TURNO DEL DÍA, NO DEL DÍA DE AYER NI DEL PASADO
} catch (SQLException ex) {
            System.out.println();
      JOptionPane.showMessageDialog(null, "ERROR EN METODO: ids_y_cantidades_enturno_por_error_de_usuario","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
     } finally{
                  cc.getClose();
             } 
    }
  
   public static void status_cancelado(int id){
        try{Connection ca= cc.conexion();
                    PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET estado_venta='"+estadocancelado+"'WHERE id_venta='"+id+"'");
                ps.executeUpdate();
        }
            catch (Exception e){ 
               JOptionPane.showMessageDialog(null, "Error en status_cancelado update venta" + e.getMessage());
            }   finally{
                    cc.getClose();
                }      
        try{Connection ca= cc.conexion();
            Modeloventa.id_max_de_venta();
                PreparedStatement ps = ca.prepareStatement ("UPDATE descripcion_de_venta SET estado= '"+estadocancelado+"' WHERE id_venta='"+id+"'");
                ps.executeUpdate();
        }
        catch(Exception ex){
                           JOptionPane.showMessageDialog(null, "Error en status_cancelado update desc" + ex.getMessage());
        } finally{
                    cc.getClose();
                }    
   }
   public static void total_ventaporid(int id){
        try{ Connection ca= cc.conexion();// La suma de todos los importes
                                          sent  =(Statement)ca.createStatement();
                                           rs = sent.executeQuery("select total from venta where id_venta= '"+id+"'");
                                            if(rs.next()){
                                                      sumadeimportesparaeltotal =Float.parseFloat(rs.getString("total"));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){                    JOptionPane.showMessageDialog(null, "Error en total_ventaporid" + e.getMessage());
                                                          sumadeimportesparaeltotal=0;
                                                      }finally{
                    cc.getClose();
                }// fin del precio-catch del producto
    }
   public static void descripciondeproductosenbasealnumerodeventaporcreditopendiente(int numerodeventa){
        Object[] columna = new Object[4];  //crear un obj con el nombre de colunna
            Connection ca= cc.conexion(); // CONEXION DB 
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  Inventarioventas.ventasacreditopendiente.setModel(modeloT);  // add modelo ala tabla 
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
                columna[0] = rs.getString(1);
                columna[1] = rs.getString(2);
                columna[2] = "$"+rs.getString(3);      
                columna[3] = "$"+rs.getString(4); 
                Inventarioventas.deudor.setText(rs.getString(5));
                modeloT.addRow(columna);
            }
        }
       TableColumnModel columnModel =   Inventarioventas.ventasacreditopendiente.getColumnModel();columnModel.getColumn(0).setPreferredWidth(200);columnModel.getColumn(1).setPreferredWidth(50);    columnModel.getColumn(2).setPreferredWidth(50);columnModel.getColumn(3).setPreferredWidth(50);
   } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Advertencia", JOptionPane.PLAIN_MESSAGE);    
    }finally{cc.getClose();}
    }
   public static void descripciondeproductosenbasealnumerodeventa(int numerodeventa){
        Object[] columna = new Object[4];  //crear un obj con el nombre de colunna
            Connection ca= cc.conexion(); // CONEXION DB 
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  Inventarioventas.jTable2.setModel(modeloT);  // add modelo ala tabla 
        modeloT.addColumn("Nombre");
        modeloT.addColumn("Piezas");        
        modeloT.addColumn("Precio");
        modeloT.addColumn("Importe");
            Inventarioventas.jTable2.setModel(modeloT);  // add modelo ala tabla 
      try { String sSQL = "SELECT nombre_producto, cantidad, precio_unitario, importe FROM descripcion_de_venta WHERE estado='Realizada' AND id_venta = '"+numerodeventa+"' ";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
               // columna[0] = rs.getString("id_venta");  /* === LA DB == */
                columna[0] = rs.getString(1);
                columna[1] = rs.getString(2);
                columna[2] = "$"+rs.getString(3);      
                columna[3] = "$"+rs.getString(4); 
                modeloT.addRow(columna);
            }    Inventarioventas.jTable2.setModel(modeloT);  // add modelo ala tabla 
                   TableColumnModel columnModel =   Inventarioventas.jTable2.getColumnModel();columnModel.getColumn(0).setPreferredWidth(200);columnModel.getColumn(1).setPreferredWidth(50);    columnModel.getColumn(2).setPreferredWidth(50);columnModel.getColumn(3).setPreferredWidth(50);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Advertencia", JOptionPane.PLAIN_MESSAGE);    
    }
    }
   public static void reimpresiondeventa(int numerodeventa){
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
            Modeloventa.total_pagoycambiopararelticketdeventa(numerodeventa);
                            //estas dos lineas mandan los datos para el ticket
                 mandardatosaticketventacondescuento = new ticketventacondescuento();
                 mandardatosaticketventacondescuento.tikectdeventacondescuento(nombreproductoticket, 
                         piezastcket, 
                         preciounitarioticket, 
                         importesticket,
                         subtotalticket, totalticket, pagoticket, cambioticket, descuentoticket, numerodeventa);
            //totalcdescticket agregar al metodo de arriba
                 Controladorventa.vaciarlistasdeticket();
    } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "ERROR EN METODO: reimpresiondeventa","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{
                  cc.getClose();
             }
    }
   public static void total_pagoycambiopararelticketdeventacredito(int id){ // recibe como parametro 
         Object[] columna = new Object[5];  //crear un obj con el nombre de colunna
        try {Connection ca= cc.conexion();
         String sSQL = "SELECT subtotal, total, pago, cambio, descuento FROM venta WHERE estado_venta='"+creditopendiente+"' AND fecha_reporte = '"+Controladorventa.fecha()+"' and id_venta='"+id+"' ";
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
 Controladorventa.vaciarlistasdeticket();
    } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "ERROR EN METODO: descripciondelosprouductosparaelticketdeventacredito","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{
                  cc.getClose();
             }
    }
}
