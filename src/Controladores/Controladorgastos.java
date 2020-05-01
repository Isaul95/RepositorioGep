package Controladores;
import Modelos.Modelogastos;
import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import si.Gastos;
import si.SI_Inicio;
import ticket.TikectGasto;

public class Controladorgastos {
      public static int  id_usuario=Integer.parseInt(SI_Inicio.iduser.getText());
     public static String  buscap = "";
     public static  boolean pass=false, pass2=false, pass3=false;
     public static  TikectGasto tikectGastos;
    public static void busquedadegastos(){
       Modelogastos.mostrartodoslosproductosenexistenciasporbusqueda(Gastos.busquedagastos1.getText());
    }
    public static void insercionengastos(){
          // ABRE NUEVA VENTANA PARA Registro de Gastos
          if (Gastos.txtdescripcionq.getText().isEmpty() || Gastos.txtmonto1.getText().isEmpty()) {
         Gastos.txtdescripcionq.setBackground(Color.red);Gastos.txtmonto1.setBackground(Color.red);
          } else {             
                   pass = Controladorventa.validarFormulario(Gastos.txtmonto1.getText());
                   pass2 = validarFormulariotexto(Gastos.txtdescripcionq.getText());
                    if (pass && pass2) {
              Modelogastos.insertarengastos(Gastos.txtdescripcionq.getText(), Float.parseFloat(Gastos.txtmonto1.getText()));
                         Modelogastos.LlenarTabla(Gastos.jTableGastos); // LLENANDO LA TABLA AL INSERTAR CORRECTAMEBTE
                            tikectGastos = new TikectGasto();
                            tikectGastos.TikectGasto(0 ,Gastos.txtdescripcionq.getText(), String.valueOf(Gastos.txtmonto1.getText()));
                              limpiar();
                              Gastos.txtdescripcionq.setBackground(new Color(135,193,193));Gastos.txtmonto1.setBackground(new Color(135,193,193));
                    }
                } 
    }
    public static boolean validarFormulariotexto(String gastos) { // VALIDACION DE TXTDESCRIPCION
        boolean next = false;      //"^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24}[\\s]*)+$"
        Pattern patGastos = Pattern.compile("^[A-Za-z\\s]+$");// ^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24}[\\s]*)+$
        Matcher matGastos = patGastos.matcher(gastos);
        if (matGastos.matches()) {
            next = true;
        } else {
          Gastos.txtdescripcionq.setBackground(Color.red);
        }
        return next;
    }
     
                   
      public static void limpiar(){     /*====  VACIAR CAMPOS */
            Gastos.txtdescripcionq.setText(null);
            Gastos.txtmonto1.setText(null);
         }  
}
