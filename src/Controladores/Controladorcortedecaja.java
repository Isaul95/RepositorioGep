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
 public static final float pagopollo=20*90, tacos=60, almuerzo=28;//datos para la tabla utilidad
 public static float  totaldedescuentos,diferenciaentablautilidad, utilidades , ventasdeldia, gastosdeldia, montodeapertura, diferencia, diferenciafinal, precio;
 public static short apertura, numerodescuentos;
 public static int  id_usuario=SI_Inicio.id_usuario;

   public static float ticketmonto, ticketventa, ticketgasto, ticketdiferencia;

      public static ArrayList totalcdesc = new ArrayList();     public static ArrayList venta = new ArrayList();      
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
        Modelocortedecaja.total_numeros_y_descuentos();
           Cortecaja.numerosdescuentos.setText(String.valueOf(numerodescuentos));
           Cortecaja.totaldescuentos.setText(String.valueOf("$"+totaldedescuentos));
        Cortecaja.aperturacantidad.setText(String.valueOf(montodeapertura));
        Cortecaja.Ventasfortoday1.setText(String.valueOf(ventasdeldia));
        Cortecaja.Gastosfromtoday.setText(String.valueOf(gastosdeldia));
      
     }
}
