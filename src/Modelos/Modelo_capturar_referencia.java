/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Controladores.Controlador_capturar_referencia;
import Controladores.Controlador_capturar_resultados;
import static Modelos.Modelo_capturar_resultados.sent;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import si.Capturar_referencia;
import si.SI;

/**
 *
 * @author AsTecI
 */
public class Modelo_capturar_referencia {
           static SI cc= new SI();
   static Statement sent;  
static ResultSet rs;  


    public static String obtener_el_valor_de_referencia_del_siguiente_producto(String p){
         String resultado="";
        try{ Connection ca= cc.conexion();// CUENTA EL TODAL DE CUANTAS VENTAS SE REALIZARON
  if(Modelo_capturar_resultados.HAY_UN_PAQUETE_EN_LA_VENTA(Controlador_capturar_resultados.id_a_actualizar_resultados)){
               sent  =(Statement)ca.createStatement(); rs = sent.executeQuery("select * from paquetes WHERE nombre_producto ='"+p+"'");
           }else{  sent  =(Statement)ca.createStatement(); rs = sent.executeQuery("select * from productos WHERE nombre_producto ='"+p+"' ");
            }
           if (rs.next()) {
                  resultado = rs.getString(5);
            }
                  }//fin del try-precio del producto
                  catch (Exception e){
                  JOptionPane.showMessageDialog(null, "ERROR EN METODO: obtener_el_valor_de_referencia_del_siguiente_producto: "+e.getMessage(),"DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
                  }// fin del precio-catch del producto
                  finally{
                  cc.getClose();
                  }
     return resultado;
    }
    public static void actualizandor_valor_de_referencia(){
            try{Connection ca= cc.conexion(); 
            if(Modelo_capturar_resultados.HAY_UN_PAQUETE_EN_LA_VENTA(Controlador_capturar_resultados.id_a_actualizar_resultados)){
                 PreparedStatement ps3 = ca.prepareStatement ("UPDATE paquetes SET  valordereferencia='"+Capturar_referencia.referencia_texto.getText().toUpperCase()+"'WHERE nombre_producto='"+Capturar_referencia.nombre_analisis.getText()+"'");
            int resultado = ps3.executeUpdate();
            if(resultado>0){
                JOptionPane.showMessageDialog(null,"El cambio lo visualizará en el pdf o simplemente abriendo esta ventana nuevamente","VALOR DE REFERENCIA ACTUALIZADO",JOptionPane.INFORMATION_MESSAGE);
            }  
            }
             else{   
                 PreparedStatement ps3 = ca.prepareStatement ("UPDATE productos SET  valordereferencia='"+Capturar_referencia.referencia_texto.getText().toUpperCase()+"'WHERE nombre_producto='"+Capturar_referencia.nombre_analisis.getText()+"'");
                 int resultado = ps3.executeUpdate();
                 if(resultado>0){
                      JOptionPane.showMessageDialog(null,"El cambio lo visualizará en el pdf o simplemente abriendo esta ventana nuevamente","VALOR DE REFERENCIA ACTUALIZADO",JOptionPane.INFORMATION_MESSAGE);
                 } else{
                     JOptionPane.showMessageDialog(null,"El cambio lo visualizará en el pdf o simplemente abriendo esta ventana nuevamente","VALOR DE REFERENCIA ACTUALIZADO",JOptionPane.INFORMATION_MESSAGE);
                 }
             }
                                 
                                }
                                catch(Exception ex){
                                    JOptionPane.showMessageDialog(null, "Error en actualizandor_valor_de_referencia " + ex.getMessage());
                                }finally{
        cc.getClose();
    }
    }
}

