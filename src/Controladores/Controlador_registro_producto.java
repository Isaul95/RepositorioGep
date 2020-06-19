/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Modelo_registro_producto;
import java.awt.Color;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import si.Registro_producto;

/**
 *
 * @author aleks
 */
public class Controlador_registro_producto {
    public static ArrayList<String> lista_llenado_categoria_estudios = new ArrayList<String>();
    public static int id_descripcion_producto;
    public static void metodos_al_abrir_registro_productos(){
        Modelo_registro_producto.llenarcombotipoproducto();
    }
    public static void registrar_nuevo_producto(){
        if(Registro_producto.nuevacategoria.isSelected()){
            if(Registro_producto.estudio.getText().isEmpty()
                ||Registro_producto.nueva_categoria.getText().isEmpty()
                ||Registro_producto.unidades.getText().isEmpty()
                ||Registro_producto.valor_referencia.getText().isEmpty()
                ||Registro_producto.precio.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Llene todos los campos de texto antes de guardar cambios","                              AVISO",JOptionPane.INFORMATION_MESSAGE);
        }else{
         Modelo_registro_producto.registrar_producto(Registro_producto.nueva_categoria.getText());
        Modelo_registro_producto.llenarcombotipoproducto();
        RetornarValoresRegistro();
        }
        }
        else{
             if(Registro_producto.estudio.getText().isEmpty()
                ||Registro_producto.tipodecategoria.getSelectedItem().equals("")
                ||Registro_producto.unidades.getText().isEmpty()
                ||Registro_producto.valor_referencia.getText().isEmpty()
                ||Registro_producto.precio.getText().isEmpty()){
            JOptionPane.showMessageDialog(null,"Llene todos los campos de texto antes de guardar cambios","                              AVISO",JOptionPane.INFORMATION_MESSAGE);
        }else{
       Modelo_registro_producto.registrar_producto(Registro_producto.tipodecategoria.getSelectedItem().toString());
        Modelo_registro_producto.llenarcombotipoproducto();
        RetornarValoresRegistro();
        }
        }
    }
     public static void RetornarValoresRegistro(){ /* UN AVEZ K SE INGRESAN LOS DATOS RETORNA LOS VALORES DE LOS PLACEHOLD */
   Registro_producto.estudio .setText("");
       Registro_producto.estudio.setForeground(new Color(0,0,0));
       Registro_producto.nueva_categoria.setText("");
                          Registro_producto.nueva_categoria.setForeground(new Color(0,0,0));   
                           Registro_producto.unidades.setText("");
                          Registro_producto.unidades.setForeground(new Color(0,0,0)); 
                           Registro_producto.valor_referencia.setText("");
                          Registro_producto.valor_referencia.setForeground(new Color(0,0,0)); 
            Registro_producto.precio .setText("");
                Registro_producto.precio.setForeground(new Color(0,0,0));              
    }
}
