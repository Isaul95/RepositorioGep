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
public class Modeloapertura {//CAMBIOS
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
                   
                 }
            }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
            }finally{cc.getClose();}//fin de la insersion a la tabla ventas 
                 }
    }
}
