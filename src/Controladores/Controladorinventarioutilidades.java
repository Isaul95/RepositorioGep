/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import static Controladores.Controladorcortedecaja.ventasdeldia;
import static Controladores.Controladorventa.fechaparaventasdesde;
import static Controladores.Controladorventa.fechaparaventashasta;
import static Controladores.Controladorventa.validarFormularioparamostrardescripciondeproductosporid;
import Modelos.Modelocortedecaja;
import Modelos.Modeloinventarioutilidades;
import Modelos.Modeloventa;
import si.utilidades;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import si.Edicion_pacientes;
import ticket.Tickets_de_venta;


/**
 *
 * @author Alexis
 */
public class Controladorinventarioutilidades {
public static double totalutilidades;

    public static void botonbuscarventas_porfecha(){
            try{
           String fechaparaventasdesde= llenarfechadesdeparamostrarlosidventas();
             String fechaparaventashasta= llenarfechahastaparamostrarlosidventas();
            if(fechaparaventasdesde.equals("")&&fechaparaventashasta.equals("")){
                JOptionPane.showMessageDialog(null, "Primero debe elegir un rango de fechas en los calendarios");
            }else{
     Modeloinventarioutilidades.showidventasporfechas(utilidades.jTable2, llenarfechadesdeparamostrarlosidventas(),llenarfechahastaparamostrarlosidventas());
            }
        }catch(NullPointerException NP){
            JOptionPane.showMessageDialog(null,"Debes de elegir un rango de fechas en los botones para la fecha", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        }
        public static String llenarfechadesdeparamostrarlosidventas(){ // Ordena la fecha del componente Jcalendar  que esta de la sig. manera:  dia /mes / aÃ±o, lo cual para la base de datos no es la manera correcta de ingresarlo, sino asÃ­: aÃ±o/mes/dia
            int año= utilidades.fechainicial.getCalendar().get(Calendar.YEAR);
       int mes= utilidades.fechainicial.getCalendar().get(Calendar.MONTH)+1;
       int dia= utilidades.fechainicial.getCalendar().get(Calendar.DAY_OF_MONTH);
       if(dia<10){
           String nuevodia= "0"+dia;
             fechaparaventasdesde= año+"/"+mes+"/"+nuevodia;
             if(mes<10){String nuevomes= "0"+mes;   fechaparaventasdesde= año+"/"+nuevomes+"/"+nuevodia;}
       }
       else if(mes<10){
           String nuevomes= "0"+mes;
             fechaparaventasdesde= año+"/"+nuevomes+"/"+dia;
       }
       else{
           fechaparaventasdesde= año+"/"+mes+"/"+dia;
       }
       return fechaparaventasdesde;
      }
                     
    public static String llenarfechahastaparamostrarlosidventas(){ // Ordena la fecha del componente Jcalendar  que esta de la sig. manera:  dia /mes / aÃ±o, lo cual para la base de datos no es la manera correcta de ingresarlo, sino asÃ­: aÃ±o/mes/dia
       int año= utilidades.fechafinal.getCalendar().get(Calendar.YEAR);
       int mes= utilidades.fechafinal.getCalendar().get(Calendar.MONTH)+1;
       int dia= utilidades.fechafinal.getCalendar().get(Calendar.DAY_OF_MONTH);
       if(dia<10){
           String newday= "0"+dia;
             fechaparaventashasta= año+"/"+mes+"/"+newday;
             if(mes<10){String newmonth= "0"+mes;   fechaparaventashasta= año+"/"+newmonth+"/"+newday;}
       }
       else if(mes<10){
           String newmonth= "0"+mes;
             fechaparaventashasta= año+"/"+newmonth+"/"+dia;
       }
     else{
           fechaparaventashasta= año+"/"+mes+"/"+dia;
       }
        return fechaparaventashasta;
    }
    public static void metodos_al_iniciar_inventarioventas(){
   Modeloinventarioutilidades.llenartablaidventasconidrealizados();
      Modeloinventarioutilidades.totalutilidades();
      utilidades.totalutilidades.setText(String.valueOf(totalutilidades));
     }
}
