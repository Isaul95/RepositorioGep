/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Controladores.Controladorapertura;
import Controladores.Controladorventa;
import static Modelos.Modeloventa.cc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import si.Apertura;
import si.nucleo;

/**
 *
 * @author Alexis
 */
public class Modeloapertura {
    public static PreparedStatement pst;
    public static void Guardarapertura(){
        boolean pass2 = Controladorventa.validarFormulario(Apertura.monto.getText());
                 if(pass2){//ESTO VALIDA QUE EL TEXTO ESCRITO NO TENGA INCOHERENCIAS   
                       try{
 Connection ca= cc.conexion(); //la insersion a la tabla ventas

    String sql = "INSERT INTO  apertura(monto,fecha,hora,usuario)  VALUES (?,?,?,?)";
                PreparedStatement pst = ca.prepareCall(sql); //hasta aqui vamos
                pst.setFloat(1,Float.parseFloat(Apertura.monto.getText()));
                pst.setString(2,Controladorventa.fecha());
                pst.setString(3,Apertura.Reloj.getText());
                pst.setInt(4,Controladorapertura.id_usuario);
                int a=pst.executeUpdate();
                if(a>0){
                    insertarpiezaspordefault();
                 }
            }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
            }finally{cc.getClose();}//fin de la insersion a la tabla ventas 
                 }
    }
    public static void insertarpiezaspordefault(){
   String nombres[]= {"Pollo rostizado",
       "Pollo asado",
       "Pech. broaster",
       "Muslo broaster",
       "Pierna broaster",
       "Ala broaster",
       "Alitas bbq",
       "Barbacoa de pollo",
       "Spagueti blanco",
       "Spagueti rojo",
       "Arroz blanco",
       "Arroz rojo",
       "Frijoles puercos",
       "Frijoles peruanos",
       "Frijoles charros",
       "Cochinita",
       "Pure",
       "Nuggets",
       "Mininuggets",
       "Tacos"};
   int cantidades[]={2,
       2,
       7,
       7,
       7,
       7,
       2,
       1,
       2,
       1,
       1,
       2,
       1,
       2,
       2,
       1,
       1,
       4,
       8,
       30};
   for(int a=0; a<nombres.length; a++){
       try{              Connection ca= cc.conexion(); 
            pst = ca.prepareStatement ("UPDATE productos SET cantidad='"+cantidades[a]+"'WHERE nombre_producto='"+nombres[a]+"'");
                  int b = pst.executeUpdate();
                if(b>0){   
                }
                  }catch(Exception e){
                               System.err.print(e);
                     } finally{cc.getClose();}
   }
}
}
