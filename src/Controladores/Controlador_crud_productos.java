/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Modelo_crud_productos;
import Modelos.Modelo_crud_productos;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import si.Crud_productos;
/**
 *
 * @author aleks
 */
public class Controlador_crud_productos {
    public static ArrayList<String> lista_llenado_categoria_estudios = new ArrayList<String>();
    public static int id_descripcion_producto;
    public static void metodos_al_abrir_registro_paquetes(){
        Modelo_crud_productos.llenarcombodepaquetes();
    }
    
    public static void llenartabladeproductospertenecientesapaquete(String paquete){
        Modelo_crud_productos.mostrartodoslosproductosdelpaquete(paquete);
    }
}
