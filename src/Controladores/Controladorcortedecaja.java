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
import ticket.PolloCrudoxPiezas;
import ticket.ticketcortedecaja;
import ticket.ticketprocesadospiezas;

/**
 *
 * @author Alexis
 */
public class Controladorcortedecaja {
    public static float ventasmenosgastos,variablemontoentregado;
  public static PolloCrudoxPiezas PolloCrudoxPiezas;//SI SE IMPRIME
  public static ticketprocesadospiezas ticketprocesadospiezas;//SI SE IMPRIME
  public static ListaGastos TicketGastosAll ;      //SI SE IMPRIME    
   public static ticketcortedecaja tikectcorte; //SI SE IMPRIME
   public static Listaventascondescuento ventascdesc;
   
  public static DecimalFormat solodosdecimales = new DecimalFormat("#.##");

   public static   ArrayList nombrescrudovendidos=new ArrayList();
  public static ArrayList cantidadescocido = new  ArrayList();
         public static ArrayList sobrantecocido = new  ArrayList();
          public static  ArrayList importescocidosvendidos = new  ArrayList();
          
   public static   ArrayList nombrescocidovendidos=new ArrayList();
   public static  ArrayList cantidadescrudo = new  ArrayList();
   public static  ArrayList importescrudosvendidos = new  ArrayList();
   public static        ArrayList sobrantecrudo= new  ArrayList();
  
   //nombre, sobrante piezas y total
  public static  ArrayList nombrecocido = new ArrayList();
       public static       ArrayList sobrantedecocido = new ArrayList();    
         public static     ArrayList piezasdecocido = new ArrayList();
                public static           ArrayList totalesdecocido = new ArrayList();
   public static      ArrayList nombrecrudo = new ArrayList(); 
       public static      ArrayList sobrantedecrudo = new ArrayList();          
       public static       ArrayList piezasdecrudo = new ArrayList();
     public static         ArrayList totalesdecrudo = new ArrayList();     
    public static  ArrayList columna1 = new ArrayList(); 
          public static   ArrayList columna2 = new ArrayList();     
 public static final float pagopollo=20*90, tacos=60, almuerzo=28;//datos para la tabla utilidad
 public static float  totalchorizoyhuesito,totaldedescuentos, totaldepagos,diferenciaentablautilidad, utilidades, total_de_crudo, total_de_procesados, ventasdeldia, gastosdeldia, montodeapertura, diferencia, diferenciafinal, precio;
 public static short apertura, numerodescuentos;
 public static int  id_usuario=Integer.parseInt(SI_Inicio.iduser.getText());

   public static float ticketmonto, ticketventa, ticketgasto, ticketdiferencia;

      public static ArrayList totalcdesc = new ArrayList();     public static ArrayList venta = new ArrayList();      
    public static void montoFocusLost(){                               
        // *********************   CAJA DE TEXTO DE PAGOO *********
        if(Cortecaja.monto.getText().trim().equals("")){
           Cortecaja.monto.setText("00.00");
        }
        Cortecaja.monto.setForeground(new Color(236, 240, 241));
    } 
     public static void montoFocusGained(){                                  
        // *********************   CAJA DE TEXTO DE PAGOO *********
        if(Cortecaja.monto.getText().trim().equals("00.00")){
            Cortecaja.monto.setText("");
            //user_usuario.setForeground(Color.red);
        }
        Cortecaja.monto.setForeground(Color.blue);
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
        Modelocortedecaja.pagoshechoseneldiaactual();
        Modelocortedecaja.total_numeros_y_descuentos();
           Cortecaja.numerosdescuentos.setText(String.valueOf(numerodescuentos));
           Cortecaja.totaldescuentos.setText(String.valueOf("$"+totaldedescuentos));
        Cortecaja.aperturacantidad.setText(String.valueOf(montodeapertura));
        Cortecaja.Ventasfortoday1.setText(String.valueOf(ventasdeldia));
        Cortecaja.Gastosfromtoday.setText(String.valueOf(gastosdeldia));
       Cortecaja.pagosmadetoday.setText(String.valueOf(totaldepagos));
     }
}
