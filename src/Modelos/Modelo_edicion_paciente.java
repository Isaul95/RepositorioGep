/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Controladores.Controlador_edicion_paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

import si.Edicion_pacientes;

import si.SI;
import si.nucleo;

/**
 *
 * @author Alexis
 */
public class Modelo_edicion_paciente  {

    public static SI cc = new SI();
    public static Statement sent;
    public static ResultSet rs;

    public static void actualizar_paciente() {
          boolean pass = Controlador_edicion_paciente.validarFormulario_paciente( Edicion_pacientes.user_edad_edicion.getText());
               boolean    pass2 = Controlador_edicion_paciente.validarFormulariotexto_edicion_paciente( Edicion_pacientes.user_nombre_edicion.getText());
                          try{      String fecha_paciente= Controlador_edicion_paciente.fecha_de_nacimiento_del_paciente();
          if (pass && pass2 &&!fecha_paciente.equalsIgnoreCase("")) {//ESTO ES PARA VALIDAR QUE SE TENGAN TODOS LOS DATOS DEL CLIENTE
                         try{Connection ca= cc.conexion();
                         Controlador_edicion_paciente.edad_paraedicion=Edicion_pacientes.user_edad_edicion.getText();
                          if(Integer.parseInt(Controlador_edicion_paciente.edad_paraedicion)==1)
                                      Controlador_edicion_paciente.edad_paraedicion=Controlador_edicion_paciente.edad_paraedicion+" año.";
                                  else Controlador_edicion_paciente.edad_paraedicion=Controlador_edicion_paciente.edad_paraedicion+" años.";
                    PreparedStatement ps3 = ca.prepareStatement ("UPDATE pacientes SET nombre='"+ Edicion_pacientes.user_nombre_edicion.getText()+"',fecha_nacimiento='"+Controlador_edicion_paciente.fecha_de_nacimiento_del_paciente()+"',edad='"+Controlador_edicion_paciente.edad_paraedicion+"',sexo='"+Edicion_pacientes.user_sexo_edicion.getSelectedItem().toString()+"',dato_auxiliar='"+Controlador_edicion_paciente.exito+"'WHERE dato_auxiliar='"+Controlador_edicion_paciente.editar+"'");
 int resultado = ps3.executeUpdate();
                if(resultado>0){
                     JOptionPane.showMessageDialog(null, "Paciente editado con exito","Paciente editado",JOptionPane.OK_OPTION);
                }
        }
            catch (Exception e){ 
               JOptionPane.showMessageDialog(null, "Error en status_cancelado update venta" + e.getMessage());
            }   finally{
                    cc.getClose();
                }      
                     }//ESTO ES PARA VALIDAR QUE SE TENGAN TODOS LOS DATOS DEL CLIENTE
      
        }catch(NullPointerException NP){
            JOptionPane.showMessageDialog(null,"Debes de elegir la fecha de nacimiento del paciente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        //VALIDACIONES
    }

    public static void mostrarusuarioaeditar() {
     
      try{ Connection ca= cc.conexion();// CUENTA EL TODAL DE CUANTAS VENTAS SE REALIZARON
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select * from pacientes where dato_auxiliar = 'Editar'");
                                            if(rs.next()){
                                                      Edicion_pacientes.user_nombre_edicion.setText(rs.getString("nombre"));
                                                      Controlador_edicion_paciente.palabra = rs.getString("edad").split(" ");
                                                      
                                                      Edicion_pacientes.user_edad_edicion.setText(Controlador_edicion_paciente.palabra[0]);
                                                      java.util.Date fechaParseada= new SimpleDateFormat("yyyy/MM/dd").parse(rs.getString("fecha_nacimiento"));
                                                     Edicion_pacientes.calendar_fecha_nacimiento_edicion.setDate(fechaParseada);
                                                     Edicion_pacientes.user_sexo_edicion.setSelectedItem(rs.getString("sexo"));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                           JOptionPane.showMessageDialog(null, "ERROR EN METODO: mostrarusuarioaeditar: "+e.getMessage(),"DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
                                                      }// fin del precio-catch del producto
        finally{
                  cc.getClose();
             }
    }
    public static void añadir_respuesta_que_no_se_actualizo(){
                      try{Connection ca= cc.conexion();
                    PreparedStatement ps = ca.prepareStatement ("UPDATE pacientes SET dato_auxiliar='Intento de editar' where dato_auxiliar= 'Editar'");
                int resultado = ps.executeUpdate();
                if(resultado>0){
                    
                }
        }
            catch (Exception e){ 
               JOptionPane.showMessageDialog(null, "Error en añadir_respuesta_que_no_se_actualizo" + e.getMessage());
            }   finally{
                    cc.getClose();
                }            
    }

}
