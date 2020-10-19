/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Modelocortedecaja;
import java.awt.Color;
import java.text.DecimalFormat;
import java.util.ArrayList;
import si.Cortecaja;
import si.SI_Inicio;
import ticket.ListaGastos;
import ticket.Listaventascondescuento;
import ticket.ticketcortedecaja;


/**
 *
 * @author Alexis
 */
public class Controladorcortedecaja {
    public static float ventasmenosgastos,variablemontoentregado;
  public static ListaGastos TicketGastosAll ;      //SI SE IMPRIME    
   public static ticketcortedecaja tikectcorte; //SI SE IMPRIME
   public static Listaventascondescuento ventascdesc;
   
  public static DecimalFormat solodosdecimales = new DecimalFormat("#.##");



         public static ArrayList sobrantecocido = new  ArrayList();
          
   public static   ArrayList nombrescocidovendidos=new ArrayList();
   public static  ArrayList cantidadescrudo = new  ArrayList();
   public static  ArrayList importescrudosvendidos = new  ArrayList();
   public static        ArrayList sobrantecrudo= new  ArrayList();
  

  
    public static  ArrayList columna1 = new ArrayList(); 
          public static   ArrayList columna2 = new ArrayList();     
          public static ArrayList columna3 = new ArrayList();
 public static float  totaldedescuentos, utilidades , ventasdeldia, gastosdeldia, pagosdeldia,montodeapertura, diferencia, diferenciafinal, precio,diferenciaparavista;
 public static short apertura, numerodescuentos;
 public static int  id_usuario=SI_Inicio.id_usuario;

   public static float ticketmonto, ticketventa, ticketgasto, ticketdiferencia;

      public static ArrayList totalcdesc = new ArrayList();     public static ArrayList venta = new ArrayList();      
      public static String hora_apertura="";
    public static void montoFocusLost(){                               
        // *********************   CAJA DE TEXTO DE PAGOO *********
        if(Cortecaja.monto.getText().trim().equals("")){
           Cortecaja.monto.setText("00.00");
        }
        Cortecaja.monto.setForeground(new Color(0, 0, 0));
    } 
     public static void montoFocusGained(){                                  
        // *********************   CAJA DE TEXTO DE PAGOO *********
        if(Cortecaja.monto.getText().trim().equals("00.00")){
            Cortecaja.monto.setText("");
            //user_usuario.setForeground(Color.red);
        }
        Cortecaja.monto.setForeground(new Color(0, 0, 0));
    }  
     public static void botoncorte(){
         Modelocortedecaja.hacercorte();
     }
     public static void metodosalabrircortedecaja(){
         //ventas en el dìa
        
            //ventas en el dìa
            Modelocortedecaja.ventaseneldia();
        Modelocortedecaja.metodogastosdeldia();
        Modelocortedecaja.aperturadeldia();
        Modelocortedecaja.pagosdeldia();
           Cortecaja.horadeinicio.setText(hora_apertura);
        Cortecaja.aperturacantidad.setText(String.valueOf(montodeapertura));
        Cortecaja.Ventasfortoday1.setText(String.valueOf(ventasdeldia));
        Cortecaja.Gastosfromtoday.setText(String.valueOf(gastosdeldia));
        Cortecaja.pagos.setText(String.valueOf(pagosdeldia));
        diferenciaparavista=ventasdeldia-gastosdeldia;
      Cortecaja.monto_a_entregar.setText(String.valueOf((gastosdeldia>ventasdeldia ? "Gastos altos" :diferenciaparavista)));
     }
}
