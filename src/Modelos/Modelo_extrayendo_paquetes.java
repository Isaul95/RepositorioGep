/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Controladores.Controlador_capturar_resultados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;
import si.SI;

/**
 *
 * @author aleks
 */
public class Modelo_extrayendo_paquetes {
        static SI cc= new SI();
   static Statement sent;  
   static PreparedStatement PS;
static ResultSet rs;  
 public static String  id_que_pertencen_a_cada_paquete(String  nombre_del_paquete){
      String resultado="";      
      try{ Connection ca= cc.conexion();// CUENTA EL TODAL DE CUANTAS VENTAS SE REALIZARON
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select analisis from paquetes_id  WHERE nombre_paquete = '"+nombre_del_paquete+"' ");
                                            if(rs.next()){
                                                      resultado =rs.getString(1);
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                           JOptionPane.showMessageDialog(null, "ERROR EN METODO: id_que_pertencen_a_cada_paquete: "+e.getMessage(),"DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
                                                      }// fin del precio-catch del producto
        finally{
                  cc.getClose();
             }
  return resultado;  
  }
 public static void  armando_el_paquete(String  ID_PRODUCTOS, int ID_VENTA, int ID_PACIENTE, String NOMBRE_CREDITO, String ESTADO, String FECHA){   
      DESCRIPCION_VENTA ASIGN; 
       Controlador_capturar_resultados.PRODUCTOS.clear();
      try{ Connection ca= cc.conexion();// CUENTA EL TODAL DE CUANTAS VENTAS SE REALIZARON
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select id_producto, nombre_producto from paquetes WHERE id_producto in  ("+ID_PRODUCTOS+") ");
                                            while(rs.next()){
                                                ASIGN = new DESCRIPCION_VENTA(); //SE INICIALIZA SIEMPRE UN NUEVO OBJETO YA QUE SI SE OCUPA UNO SOLO, SOLO SE ALMACENA EL ULTIMO VALOR
                                            ASIGN.setID_PRODUCTO(rs.getInt(1));
                                            ASIGN.setNOMBRE_PRODUCTO(rs.getString(2));
                                            ASIGN.setCANTIDAD(1);
                                            ASIGN.setPRECIO_UNITARIO(0);
                                            ASIGN.setIMPORTE(0);
                                            ASIGN.setRESULTADO("");
                                            ASIGN.setID_VENTA(ID_VENTA);
                                            ASIGN.setID_PACIENTE(ID_PACIENTE);
                                            ASIGN.setNOMBRE_CREDITO(NOMBRE_CREDITO);
                                            ASIGN.setESTADO(ESTADO);
                                            ASIGN.setFECHA(FECHA);
                                          Controlador_capturar_resultados.PRODUCTOS.add(ASIGN);
                                                      }
                                                      }//fin del try-precio del producto//fin del try-precio del producto
                                                      catch (Exception e){
                                                           JOptionPane.showMessageDialog(null, "ERROR EN METODO: armando_el_paquete: "+e.getMessage(),"DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
                                                      }// fin del precio-catch del producto
        finally{
                  cc.getClose();
             }
  }
 public static void imprimir_el_paquete_armado(){
     for(DESCRIPCION_VENTA PRINT:Controlador_capturar_resultados.PRODUCTOS){
         System.out.println("ID_PRODUCTO: "+PRINT.getID_PRODUCTO()+"\n"
         +"NOMBRE_PRODUCTO: "+PRINT.getNOMBRE_PRODUCTO()+"\n"
         +"CANTIDAD: "+PRINT.getCANTIDAD()+"\n"
         +"PRECIO_UNITARIO: "+PRINT.getPRECIO_UNITARIO()+"\n"
         +"IMPORTE: "+PRINT.getIMPORTE()+"\n"
         +"ID_VENTA: "+PRINT.getID_VENTA()+"\n"
         +"ID_PACIENTE: "+PRINT.getID_PACIENTE()+"\n"
         +"NOMBRE_CREDITO: "+PRINT.getNOMBRE_CREDITO()+"\n"
         +"ESTADO: "+PRINT.getESTADO()+"\n"
         +"FECHA: "+PRINT.getFECHA()+"\n");
     }
 }
 public static void  insertando_analisis_del_paquete(){   
String query ="insert into descripcion_de_venta (id_producto, nombre_producto, cantidad, precio_unitario, importe, resultado, id_venta, id_paciente,nombre_credito, estado, fecha) values (?,?,?,?,?,?,?,?,?,?,?)";
      try{ Connection ca= cc.conexion();// CUENTA EL TODAL DE CUANTAS VENTAS SE REALIZARON
			PS = ca.prepareStatement(query);    
                        for(DESCRIPCION_VENTA INSERT:Controlador_capturar_resultados.PRODUCTOS){
                            PS.setObject(1, INSERT.getID_PRODUCTO());
                                PS.setObject(2, INSERT.getNOMBRE_PRODUCTO());
                                PS.setObject(3, INSERT.getCANTIDAD());
                                PS.setObject(4, INSERT.getPRECIO_UNITARIO());
                                PS.setObject(5, INSERT.getIMPORTE());
                                PS.setObject(6, INSERT.getRESULTADO());
                                PS.setObject(7, INSERT.getID_VENTA());
                                PS.setObject(8, INSERT.getID_PACIENTE());
                                PS.setObject(9, INSERT.getNOMBRE_CREDITO());
                                PS.setObject(10, INSERT.getESTADO());
                                PS.setObject(11, INSERT.getFECHA());			
				PS.executeUpdate();
                        }         
                                                      }//fin del try-precio del producto//fin del try-precio del producto
                                                      catch (Exception e){
                                                           JOptionPane.showMessageDialog(null, "ERROR EN METODO: insertando_analisis_del_paquete: "+e.getMessage(),"DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
                                                      }// fin del precio-catch del producto
        finally{
                  cc.getClose();
             }
  }
}
