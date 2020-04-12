/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Modelo_edicion_paciente;
import java.awt.Color;
import java.util.Calendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import si.Edicion_pacientes;
import si.Existencias;
import si.nucleo;

/**
 *
 * @author Alexis
 */
public class Controlador_edicion_paciente extends Modelo_edicion_paciente{
    public static short fila;
    public static String nombredepiezaseleccionada,editar="Editar", fecha_nacimiento_edicion,exito="Editado", edad_paraedicion="";
    public static String[] palabra;
    public static void mostrartodoslosproductosenexistencias(String nombre){
  
    }
    public static void metodosalabriredicion_paciente(){
       mostrarusuarioaeditar();
    }
    public static void confirmar_cambios(){
    actualizar_paciente();
   
    }
    public static void paciente_no_actualizado(){
        añadir_respuesta_que_no_se_actualizo();
    }
     public static boolean validarFormulariotexto_edicion_paciente(String nombre) { // VALIDACION DE TXTDESCRIPCION
        boolean next = false;      //"^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24}[\\s]*)+$"
        Pattern patGastos = Pattern.compile("^[A-Za-z\\s]+$");// ^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24}[\\s]*)+$
        Matcher matGastos = patGastos.matcher(nombre);
        if (matGastos.matches()&&!nombre.equals("")) {
            next = true;
        } else {
            Edicion_pacientes.user_nombre_edicion.setBackground(Color.red);
            JOptionPane.showMessageDialog(null, "Solo escribe letras y no puedes dejar vacio el campo");
        }
        return next;
    }   
       public static boolean validarFormulario_paciente(String cantidaddelatabla) { // VALIDACION DE TXT MONTO
        boolean next = false;
        Pattern patGastos = Pattern.compile("^[0-9]+([.])?([0-9]+)?$");
        Matcher matGastos = patGastos.matcher(cantidaddelatabla);
  if (matGastos.matches()&&!cantidaddelatabla.equals("")&&!cantidaddelatabla.equals("0")) {
            next = true;
        } else {
            Edicion_pacientes.user_edad_edicion.setBackground(Color.red);
            JOptionPane.showMessageDialog(null, "No puedes escribir letras, dejar vacio el campo ni meter un 0", "Advertencia", JOptionPane.INFORMATION_MESSAGE);    
        }
        return next;
    }
       public static String fecha_de_nacimiento_del_paciente(){
       int año= Edicion_pacientes.calendar_fecha_nacimiento_edicion.getCalendar().get(Calendar.YEAR);
       int mes= Edicion_pacientes.calendar_fecha_nacimiento_edicion.getCalendar().get(Calendar.MONTH)+1;
       int dia= Edicion_pacientes.calendar_fecha_nacimiento_edicion.getCalendar().get(Calendar.DAY_OF_MONTH);
       if(dia<10){
           String nuevodia= "0"+dia;
             fecha_nacimiento_edicion= año+"/"+mes+"/"+nuevodia;
             if(mes<10){String nuevomes= "0"+mes;   fecha_nacimiento_edicion= año+"/"+nuevomes+"/"+nuevodia;}
       }
       else if(mes<10){
           String nuevomes= "0"+mes;
             fecha_nacimiento_edicion= año+"/"+nuevomes+"/"+dia;
       }
       else{
           fecha_nacimiento_edicion= año+"/"+mes+"/"+dia;
       }
       return fecha_nacimiento_edicion;  
   }
}
