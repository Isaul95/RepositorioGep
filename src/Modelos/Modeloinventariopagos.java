/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Controladores.Controladorinventariopagos;
import Controladores.Controladorventa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import si.inventariopagos;
import si.SI;
import si.nucleo;
import ticket.Tickets_de_venta;


/**
 *
 * @author aleks
 */
public class Modeloinventariopagos extends Controladorinventariopagos{
    static SI cc= new SI();
   static Statement sent;  
static ResultSet rs;  
public static void mostrarpagos(int idventa){ // recibe como parametro 
         Object[] columna = new Object[4];  //crear un obj con el nombre de colunna
             DefaultTableModel modeloTE = new DefaultTableModel(); 
                  inventariopagos.jTable2.setModel(modeloTE);  // add modelo ala tabla 
        modeloTE.addColumn("Deudor");
        modeloTE.addColumn("Monto");
        modeloTE.addColumn("Fecha"); 
        modeloTE.addColumn("Hora");
    inventariopagos.jTable2.setModel(modeloTE);  // add modelo ala tabla         
        try {  Connection ca= cc.conexion(); // CONEXION DB 
             String sSQL = "SELECT nombre, monto, fecha, hora from pagos WHERE id_venta in ( "+idventa+")";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
                columna[0] = rs.getString(1);
                columna[1] = "$"+String.valueOf(rs.getFloat(2));
                  columna[2] = rs.getString(3);
                   columna[3] = rs.getString(4);
              
                modeloTE.addRow(columna);
            }
          inventariopagos.jTable2.setModel(modeloTE);  // add modelo ala tabla 
             TableColumnModel columnModel =   inventariopagos.jTable2.getColumnModel();
    columnModel.getColumn(0).setMaxWidth(570);
    columnModel.getColumn(0).setMinWidth(570);
     columnModel.getColumn(1).setMaxWidth(120);
    columnModel.getColumn(1).setMinWidth(120);
     columnModel.getColumn(2).setMaxWidth(120);
    columnModel.getColumn(2).setMinWidth(120);
     columnModel.getColumn(3).setMaxWidth(120);
    columnModel.getColumn(3).setMinWidth(120);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "ERROR EN METODO: mostrarpagos","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
       }finally{
                  cc.getClose();
             }
}
public static void llenartablaidventasconidrealizados(){ // recibe como parametro 
         Object[] columna = new Object[6];  //crear un obj con el nombre de colunna
             DefaultTableModel modeloTE = new DefaultTableModel(); 
                  inventariopagos.jTable2.setModel(modeloTE);  // add modelo ala tabla 
        modeloTE.addColumn("Venta");
        modeloTE.addColumn("Paciente");
        modeloTE.addColumn("Subtotal"); 
        modeloTE.addColumn("Descuento");
        modeloTE.addColumn("Total");
        modeloTE.addColumn("Fecha");
    inventariopagos.jTable2.setModel(modeloTE);  // add modelo ala tabla         
        try {  Connection ca= cc.conexion(); // CONEXION DB 
             String sSQL = "SELECT distinct venta.id_venta, pacientes.nombre,venta.subtotal, venta.descuento,venta.total,venta.fecha_reporte FROM venta inner join descripcion_de_venta on venta.id_venta = descripcion_de_venta.id_venta inner join pacientes on descripcion_de_venta.id_paciente = pacientes.id_paciente WHERE venta.id_venta in ( "+id_de_pago_de_venta()+")";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
                columna[0] = rs.getInt(1);
                columna[1] = rs.getString(2);
                columna[2] = "$"+String.valueOf(rs.getFloat(3));
                 columna[3] = "$"+String.valueOf(rs.getFloat(4));
                columna[4] = "$"+String.valueOf(rs.getFloat(5));
                 columna[5] = rs.getString(6);
                modeloTE.addRow(columna);
            }
          inventariopagos.jTable2.setModel(modeloTE);  // add modelo ala tabla 
                             TableColumnModel columnModel =   inventariopagos.jTable2.getColumnModel();
                               // columnModel.getColumn(0).setPreferredWidth(10);
                              columnModel.getColumn(0).setMaxWidth(100);
                               columnModel.getColumn(0).setMinWidth(100);
                             //columnModel.getColumn(1).setPreferredWidth(250);
                               columnModel.getColumn(1).setMaxWidth(390);
                               columnModel.getColumn(1).setMinWidth(390);
                           //  columnModel.getColumn(2).setPreferredWidth(20); 
                               columnModel.getColumn(2).setMaxWidth(100);
                               columnModel.getColumn(2).setMinWidth(100);
                           //  columnModel.getColumn(3).setPreferredWidth(10);
                              columnModel.getColumn(3).setMaxWidth(100);
                               columnModel.getColumn(3).setMinWidth(100);
                           //  columnModel.getColumn(4).setPreferredWidth(10);
                             columnModel.getColumn(4).setMaxWidth(100);
                               columnModel.getColumn(4).setMinWidth(100);
                             //columnModel.getColumn(5).setPreferredWidth(10);
                              columnModel.getColumn(5).setMaxWidth(100);
                               columnModel.getColumn(5).setMinWidth(100);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "ERROR EN METODO: llenartablaidventasconidrealizados","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
       }finally{
                  cc.getClose();
             }
}
public static void consultarlosresultadosenlabusquedadenombres(String contexto){
    DefaultTableModel modelo = new DefaultTableModel(); // Se crea un objeto para agregar los nombres de las columnas a la tabla
       modelo.addColumn("Venta");
        modelo.addColumn("Paciente");
         modelo.addColumn("Subtotal");  
         modelo.addColumn("Descuento");    
         modelo.addColumn("Total");
        modelo.addColumn("Fecha");
        inventariopagos.jTable2.setModel(modelo);  // Ya una vez asignado todos los nombres se le envia el objeto a la tabla proveedores
        Object[] datos = new Object[6];     //Un arreglo con la cantidad de nombres en las columnas
        try {
            Connection ca = cc.conexion();
            sent = ca.createStatement();
           /* if(){ //Busqueda por el rango de fechas
                
            }else{ //Busqueda del dia actual
                
            }*/
            if (contexto.equals("")) {
                       String sSQL = "SELECT distinct venta.id_venta, pacientes.nombre,venta.subtotal, venta.descuento,venta.total,venta.fecha_reporte FROM venta inner join descripcion_de_venta on venta.id_venta = descripcion_de_venta.id_venta inner join pacientes on descripcion_de_venta.id_paciente = pacientes.id_paciente WHERE venta.id_venta in ( "+id_de_pago_de_venta()+")";
          rs = sent.executeQuery(sSQL); // se ejecuta la sentencia dentro del parentesis
            } else {
                              String sSQLcontesto = "SELECT distinct venta.id_venta, pacientes.nombre,venta.subtotal, venta.descuento,venta.total ,venta.fecha_reporte FROM venta inner join descripcion_de_venta on venta.id_venta = descripcion_de_venta.id_venta inner join pacientes on descripcion_de_venta.id_paciente = pacientes.id_paciente WHERE venta.id_venta in ( "+ id_de_pago_de_venta()+") AND  pacientes.nombre LIKE '%" + contexto + "%'";
               
                rs = sent.executeQuery(sSQLcontesto); // se ejecuta la sentencia dentro del parentesis
            }
            while (rs.next()) {
                 datos[0] = rs.getInt(1);
                datos[1] = rs.getString(2);
                datos[2] = "$"+String.valueOf(rs.getFloat(3));
                datos[3]  = "$"+String.valueOf(rs.getFloat(4));
                 datos[4]  = "$"+String.valueOf(rs.getFloat(5));
                 datos[5] = rs.getString(6);
                modelo.addRow(datos); //se asigna el arreglo  entero a todo el objeto llamado modelo  
            }
             inventariopagos.jTable2.setModel(modelo);  // add modelo ala tabla 
                             TableColumnModel columnModel =   inventariopagos.jTable2.getColumnModel();
                             // columnModel.getColumn(0).setPreferredWidth(10);
                              columnModel.getColumn(0).setMaxWidth(100);
                               columnModel.getColumn(0).setMinWidth(100);
                             //columnModel.getColumn(1).setPreferredWidth(250);
                               columnModel.getColumn(1).setMaxWidth(390);
                               columnModel.getColumn(1).setMinWidth(390);
                           //  columnModel.getColumn(2).setPreferredWidth(20); 
                               columnModel.getColumn(2).setMaxWidth(100);
                               columnModel.getColumn(2).setMinWidth(100);
                           //  columnModel.getColumn(3).setPreferredWidth(10);
                              columnModel.getColumn(3).setMaxWidth(100);
                               columnModel.getColumn(3).setMinWidth(100);
                           //  columnModel.getColumn(4).setPreferredWidth(10);
                             columnModel.getColumn(4).setMaxWidth(100);
                               columnModel.getColumn(4).setMinWidth(100);
                             //columnModel.getColumn(5).setPreferredWidth(10);
                              columnModel.getColumn(5).setMaxWidth(100);
                               columnModel.getColumn(5).setMinWidth(100);
       } catch (SQLException ex) {
            Logger.getLogger(nucleo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo mostrar  porque tu consulta está mal");
        } finally {
            cc.getClose();
        }
}
public static void showidventasporfechas(JTable tablaventas, String fechadesde, String fechahasta){
        Object[] columna = new Object[6];  //crear un obj con el nombre de colunna
            Connection ca= cc.conexion(); // CONEXION DB 
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  tablaventas.setModel(modeloT);  // add modelo ala tabla 
           modeloT.addColumn("Venta");
            modeloT.addColumn("Paciente");
            modeloT.addColumn("Subtotal");
            modeloT.addColumn("Descuento");
        modeloT.addColumn("Total");     
        modeloT.addColumn("Fecha");
    try {        
           String sSQL = "SELECT distinct venta.id_venta, pacientes.nombre,venta.subtotal, venta.descuento,venta.total,venta.fecha_reporte FROM venta inner join descripcion_de_venta on venta.id_venta = descripcion_de_venta.id_venta inner join pacientes on descripcion_de_venta.id_paciente = pacientes.id_paciente WHERE venta.id_venta in ( "+id_de_pago_de_venta()+") AND venta.fecha_reporte BETWEEN '"+fechadesde+"' AND '"+fechahasta+ "' ";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                columna[0] = rs.getString(1);
                  columna[1] = rs.getString(2);
                columna[2] = "$"+String.valueOf(rs.getInt(3));
                 columna[3] = "$"+String.valueOf(rs.getInt(4));
                columna[4] = "$"+String.valueOf(rs.getInt(5));
                   columna[5] = rs.getString(6);
                modeloT.addRow(columna);
            }
        }                 inventariopagos.jTable2.setModel(modeloT);  // add modelo ala tabla 
                             TableColumnModel columnModel =   inventariopagos.jTable2.getColumnModel();
                           // columnModel.getColumn(0).setPreferredWidth(10);
                              columnModel.getColumn(0).setMaxWidth(100);
                               columnModel.getColumn(0).setMinWidth(100);
                             //columnModel.getColumn(1).setPreferredWidth(250);
                               columnModel.getColumn(1).setMaxWidth(390);
                               columnModel.getColumn(1).setMinWidth(390);
                           //  columnModel.getColumn(2).setPreferredWidth(20); 
                               columnModel.getColumn(2).setMaxWidth(100);
                               columnModel.getColumn(2).setMinWidth(100);
                           //  columnModel.getColumn(3).setPreferredWidth(10);
                              columnModel.getColumn(3).setMaxWidth(100);
                               columnModel.getColumn(3).setMinWidth(100);
                           //  columnModel.getColumn(4).setPreferredWidth(10);
                             columnModel.getColumn(4).setMaxWidth(100);
                               columnModel.getColumn(4).setMinWidth(100);
                             //columnModel.getColumn(5).setPreferredWidth(10);
                              columnModel.getColumn(5).setMaxWidth(100);
                               columnModel.getColumn(5).setMinWidth(100);
     } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Advertencia", JOptionPane.PLAIN_MESSAGE);    
    }finally{cc.getClose();}
    }
  public static void descripciondeproductosenbasealnumerodeventa(int numerodeventa){
        Object[] columna = new Object[4];  //crear un obj con el nombre de colunna
            Connection ca= cc.conexion(); // CONEXION DB 
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  inventariopagos.jTable2.setModel(modeloT);  // add modelo ala tabla 
        modeloT.addColumn("Nombre");
        modeloT.addColumn("Piezas");        
        modeloT.addColumn("Precio");
        modeloT.addColumn("Importe");
            inventariopagos.jTable2.setModel(modeloT);  // add modelo ala tabla 
      try { String sSQL = "SELECT distinct dv.nombre_producto, dv.cantidad, dv.precio_unitario, dv.importe, v.total FROM descripcion_de_venta dv inner join venta v on dv.id_venta = v.id_venta WHERE v.id_venta in ( "+numerodeventa+")";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
               // columna[0] = rs.getString("id_venta");  /* === LA DB == */
                columna[0] = rs.getString(1);
                columna[1] = rs.getString(2);
                columna[2] = "$"+rs.getString(3);      
                columna[3] = "$"+rs.getString(4); 
                sumadeimportesparaeltotal =Float.parseFloat(rs.getString(5));
                modeloT.addRow(columna);
            }    inventariopagos.jTable2.setModel(modeloT);  // add modelo ala tabla 
                   TableColumnModel columnModel =   inventariopagos.jTable2.getColumnModel();
           //        columnModel.getColumn(1).setPreferredWidth(5);
   //columnModel.getColumn(2).setPreferredWidth(10);
   // columnModel.getColumn(3).setPreferredWidth(10);
    columnModel.getColumn(0).setMaxWidth(570);
    columnModel.getColumn(0).setMinWidth(570);
     columnModel.getColumn(1).setMaxWidth(120);
    columnModel.getColumn(1).setMinWidth(120);
     columnModel.getColumn(2).setMaxWidth(120);
    columnModel.getColumn(2).setMinWidth(120);
     columnModel.getColumn(3).setMaxWidth(120);
    columnModel.getColumn(3).setMinWidth(120);
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Advertencia", JOptionPane.PLAIN_MESSAGE);    
    }
    }
public static void la_venta_tiene_descuento_si_o_no(int id_venta){
        try{ Connection ca= cc.conexion();// La suma de todos los importes
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select descuento from venta where estado_venta = 'Realizada' and fecha_reporte= '"+Controladorventa.fecha()+"' and id_venta='"+id_venta+"'");
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

     public static void impresiondeventacancelada(int numerodeventa){
        Modeloventa.descripciondelosprouductosparaelticketdeventa(numerodeventa,estadocancelado);
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
   public static void reimpresiondeventa(int numerodeventa){
       Modeloventa.descripciondelosprouductosparaelticketdeventa(numerodeventa,Controladorventa.estadorealizado);
    }
    public static String  id_de_pago_de_venta(){
      String resultado="";      
      try{ Connection ca= cc.conexion();// CUENTA EL TODAL DE CUANTAS VENTAS SE REALIZARON
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select id_venta from pagos  WHERE fecha = '"+Controladorventa.fecha()+"' ");
                                            while(rs.next()){
                                                      resultado =resultado+","+rs.getString(1);
                                                      }System.out.println("ID_PRODUCTOS: "+(resultado.isEmpty() ? "0" :resultado.substring(1)));
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                           JOptionPane.showMessageDialog(null, "ERROR EN METODO: id_que_pertencen_a_cada_paquete: "+e.getMessage(),"DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
                                                      }// fin del precio-catch del producto
        finally{
                  cc.getClose();
             }
  return (resultado.isEmpty() ? "0" :resultado.substring(1));  
  }
}
