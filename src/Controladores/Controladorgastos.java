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
     public static  boolean pass=false, pass2=false;
     public static  TikectGasto tikectGastos;
    public static void busquedadegastos(){
       Modelogastos.mostrartodoslosproductosenexistenciasporbusqueda(Gastos.busquedagastos.getText());
    }
    public static void insercionengastos(){
          // ABRE NUEVA VENTANA PARA Registro de Gastos
          if (Gastos.txtdescripcion.getText().isEmpty() || Gastos.txtmonto.getText().isEmpty()) {
         Gastos.txtdescripcion.setBackground(Color.red);Gastos.txtmonto.setBackground(Color.red);
          } else {             
                   pass = Controladorventa.validarFormulario(Gastos.txtmonto.getText());
                   pass2 = validarFormulariotexto(Gastos.txtdescripcion.getText());
                    if (pass && pass2) {
              Modelogastos.insertarengastos(Gastos.txtdescripcion.getText(), Float.parseFloat(Gastos.txtmonto.getText()));
                         Modelogastos.LlenarTabla(Gastos.jTableGastos); // LLENANDO LA TABLA AL INSERTAR CORRECTAMEBTE
                            tikectGastos = new TikectGasto();
                            tikectGastos.TikectGasto(0 ,Gastos.txtdescripcion.getText(), String.valueOf(Gastos.txtmonto.getText()));
                              limpiar();
                              Gastos.txtdescripcion.setBackground(Color.WHITE);Gastos.txtmonto.setBackground(Color.WHITE);
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
          Gastos.txtdescripcion.setBackground(Color.red);
        }
        return next;
    }
     
                   
      public static void limpiar(){     /*====  VACIAR CAMPOS */
            Gastos.txtdescripcion.setText(null);
            Gastos.txtmonto.setText(null);
         }  
}
