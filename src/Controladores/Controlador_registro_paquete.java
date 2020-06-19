/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Modelo_registro_paquete;
import Modelos.Modelo_registro_producto;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import static si.Registro_paquete.Tablepaquetessinanalisis;
import si.Registro_producto;
import static si.nucleo.Registro_paquete;

/**
 *
 * @author aleks
 */
public class Controlador_registro_paquete {
    public static ArrayList<String> lista_llenado_categoria_estudios = new ArrayList<String>();
    public static int id_descripcion_producto;
    public static void metodos_al_abrir_registro_paquetes(){
        Modelo_registro_paquete.llenarcombodepaquetes();
        Modelo_registro_paquete.llenarcombodepaquetessinproductos();
        Modelo_registro_paquete.mostrartodoslosanalisis_sinpaquete(Modelo_registro_paquete.analisis_ya_registrados());
    
        
    }
    
    public static void llenartabladeproductospertenecientesapaquete(String paquete){
        Modelo_registro_paquete.mostrartodoslosproductosdelpaquete(paquete);
    }
     public static String concatenartodoslosidpaquetedelatabla(){
    String valor="";
     
          int fila = Tablepaquetessinanalisis.getRowCount();
for (int i = 0; i <fila; i++) {
     valor = valor +String.valueOf(Tablepaquetessinanalisis.getValueAt(i, 0))+",";
  
    }//Tablepaquetessinanalisis.getSelectedColumns();

     return valor.substring(0,valor.length()-1);
     }
}
