/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Controladores.Controlador_registro_producto;
import Controladores.Controladorventa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import si.Registro_producto;
import si.SI;

/**
 *
 * @author aleks
 */
public class Modelo_registro_producto extends Controlador_registro_producto{
    public static SI cc= new SI();
        public static Statement sent;  
  public static ResultSet rs;    
  public static PreparedStatement ps;
  
    public static ArrayList<String> llenarcombotipoproducto(){// este metodo obtiene los tipo_producto disponibles en la base de datos actualmente
        Registro_producto.tipodecategoria.removeAllItems();//Ésta linea es importante ya que cada vez que se llama este metodo se eliminan los item que previamente se cargaron en la llamada anterior, ESTO PARA QUE NO SE VUELVAN AGREGAR LOS MISMOS ITEMS, MÁS DE 1 VEZ
         lista_llenado_categoria_estudios.clear();
        try{Connection ca= cc.conexion();
            sent = (Statement)ca.createStatement();
            rs= sent.executeQuery("select DISTINCT categoria_estudios from productos where categoria_estudios not in ('Sin categoria')");
            while(rs.next()){
                lista_llenado_categoria_estudios.add(rs.getString("categoria_estudios"));
            }
             for(int a=0; a<lista_llenado_categoria_estudios.size(); a++){
            Registro_producto.tipodecategoria.addItem(lista_llenado_categoria_estudios.get(a));
        }
        }catch(Exception e){
            System.err.print(e);
        }
        return lista_llenado_categoria_estudios;
    }
    public static void registrar_producto(String categoria){
        try{Connection ca= cc.conexion();
                String sql1 = "INSERT INTO productos (nombre_producto,categoria_estudios,unidades,valordereferencia,precio)  VALUES (?,?,?,?,?)";
                PreparedStatement pst1 = ca.prepareCall(sql1);
                pst1.setString(1,Registro_producto.estudio.getText().toUpperCase());
                pst1.setString(2,categoria.toUpperCase());
                pst1.setString(3,Registro_producto.unidades.getText().toUpperCase());
                pst1.setString(4,Registro_producto.valor_referencia.getText().toUpperCase());
                pst1.setFloat(5, Float.parseFloat(String.valueOf(Registro_producto.precio.getText())));
                int a=pst1.executeUpdate();
                if(a>0){
                    JOptionPane.showMessageDialog(null,"PRODUCTO GUARDADO CORRECTAMENTE","EXITO",JOptionPane.INFORMATION_MESSAGE);
                    
                }
            } catch(SQLException e)  {
                //JOptionPane.showMessageDialog(null,"ERROR DE DATOS");
                JOptionPane.showMessageDialog(null, "Error registrar_producto" + e.getMessage());
            }
        catch(NumberFormatException nE){
            JOptionPane.showMessageDialog(null, "Verifique que ingresa ccorrectamente los valores en donde corresponden");
        }
    }
}
