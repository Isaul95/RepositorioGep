/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import static Controladores.Controladorventa.fechaparaventasdesde;
import static Controladores.Controladorventa.fechaparaventashasta;
import static Controladores.Controladorventa.validarFormularioparamostrardescripciondeproductosporid;
import Modelos.Modeloinventarioventas;
import si.Inventarioventas;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import ticket.ticketventacancelada;
import ticket.ticketventacondescuento;
import ticket.ticketventacredito;


/**
 *
 * @author Alexis
 */
public class Controladorinventarioventas {
    public static ticketventacancelada mandardatosticketventacancelada;
       public static ticketventacondescuento mandardatosaticketventacondescuento;  
       public static ticketventacredito mandardatosticketventacredito;
       
    public static float sumadetotalesdeventasdehoy,
            sumadeimportescreditopendiente,  sumadeimportesparaeltotal, 
            descuentoticket, cambioticket, pagoticket, totalticket, subtotalticket;
    public static short conteototaldeventas, id_ventapencredito, id=0;
    public static String creditopagado="Credito-pagado", estadocancelado= "Cancelada", creditopendiente="Credito-pendiente";
    public static ArrayList importesticket = new ArrayList();
    public static ArrayList preciounitarioticket = new ArrayList();
    public static ArrayList piezastcket = new ArrayList();
              public static ArrayList nombreproductoticket = new ArrayList();
              
                           
        
    public static void ventasdehoy(){
           Modeloinventarioventas.totaldelasventasdehoy(); // PARA LA SUMA DE LOS TOTALES DE LA VENTA
                                Modeloinventarioventas.conteodeventasrealizadasdehoy(); // CUANTAS VENTAS SE REALIZARON? 5 O 60 O XX
                            Inventarioventas.ventaseneldiasumadas.setText(String.valueOf(Controladorinventarioventas.sumadetotalesdeventasdehoy));// VIENE DEL METODO ventaseneldiaREALIZADAS()
                             Inventarioventas.conteodelasventasrealizadas.setText(String.valueOf(Controladorinventarioventas.conteototaldeventas)); // VIENE DEL METODO totalventasxdia(); ES UN CONTEO DE VENTAS
                            
    Inventarioventas.ventaseneldiasumadas.setText(String.valueOf(Controladorinventarioventas.sumadetotalesdeventasdehoy));// VIENE DEL METODO ventaseneldiaREALIZADAS()
                             Inventarioventas.conteodelasventasrealizadas.setText(String.valueOf(Controladorinventarioventas.conteototaldeventas)); // VIENE DEL METODO totalventasxdia(); ES UN CONTEO DE VENTAS
    Modeloinventarioventas.llenartablaidventasconidrealizados();
                                Modeloinventarioventas.productosvendidoseneldia();//MUESTRA LAS VENTAS REA
                                Modeloinventarioventas.llenartablaconventasacreditopendiente();
                              
    }
    public static void botonveridventas(){ //INVENTARIOVENTAS
     Inventarioventas.fechainicial.cleanup();
        Inventarioventas.fechainicial.setDate(null);
        Inventarioventas.fechafinal.cleanup();
        Inventarioventas.fechafinal.setDate(null);
        Modeloinventarioventas.llenartablaidventasconidrealizados(); //CARGA NUEVAMENTE LAS VENTAS POR ID
        Inventarioventas.veridventas.setVisible(false);
        Inventarioventas.labelparaeltotal.setText("00.00");
        Inventarioventas.totalventarealizada.setVisible(false);
        Inventarioventas.labelparaeltotal.setVisible(false);
        Inventarioventas.imprimirventa.setVisible(false);
 Inventarioventas.cancelarventa.setVisible(false);
 }
                                public static void verdescripciondeventaacredito(){
                int fila =Inventarioventas.ventasacreditopendiente.getSelectedRow();
        if(fila>=0){
            boolean pass =validarFormularioparamostrardescripciondeproductosporid(Inventarioventas.ventasacreditopendiente.getValueAt(fila,0).toString());
            if(pass){
                Controladorinventarioventas.id_ventapencredito=Short.parseShort(Inventarioventas.ventasacreditopendiente.getValueAt(fila,0).toString());
                Modeloinventarioventas.descripciondeproductosenbasealnumerodeventaporcreditopendiente(Integer.parseInt(Inventarioventas.ventasacreditopendiente.getValueAt(fila,0).toString()));
                Modeloinventarioventas.total_venta_creditopendiente(Controladorinventarioventas.id_ventapencredito);
                Inventarioventas.totalventacreditoenturno.setText(String.valueOf(sumadeimportescreditopendiente));
                Inventarioventas.labelnombre.setVisible(true);
                Inventarioventas.labelcredito.setVisible(true);
                Inventarioventas.deudor.setVisible(true);
                Inventarioventas.totalventacreditoenturno.setVisible(true);
                Inventarioventas.veridventasacreditopendiente.setVisible(true);
                Inventarioventas.pagarventaacredito.setVisible(true);
            }
        }
        else
        JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila primero","Aviso",JOptionPane.INFORMATION_MESSAGE);
    }
                                public static void verdescripcionenbaseaventarealizada(){
            int fila =Inventarioventas.jTable2.getSelectedRow();
 if(fila>=0){
            boolean pass= validarFormularioparamostrardescripciondeproductosporid(Inventarioventas.jTable2.getValueAt(fila,0).toString());
            if(pass){
               Inventarioventas.veridventas.setVisible(true);
                id=Short.parseShort(Inventarioventas.jTable2.getValueAt(fila,0).toString());
                Modeloinventarioventas.descripciondeproductosenbasealnumerodeventa(id);
                Modeloinventarioventas.total_ventaporid(id);
                Inventarioventas.labelparaeltotal.setVisible(true);
                Inventarioventas.labelparaeltotal.setText(String.valueOf(sumadeimportesparaeltotal));
                Inventarioventas.totalventarealizada.setVisible(true);
                Inventarioventas.imprimirventa.setVisible(true);
                Inventarioventas.cancelarventa.setVisible(true);
            }
        }
        else
        JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila primero","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }
     public static void botoncancelarventa(short id){
            Modeloinventarioventas.status_cancelado(id);
        Inventarioventas.fechainicial.cleanup();
        Inventarioventas.fechainicial.setDate(null);
        Inventarioventas.fechafinal.cleanup();
        Inventarioventas.fechafinal.setDate(null);
        Modeloinventarioventas.llenartablaidventasconidrealizados(); //CARGA NUEVAMENTE LAS VENTAS POR ID
        Inventarioventas.veridventas.setVisible(false);
        Inventarioventas.labelparaeltotal.setText("00.00");
        Inventarioventas.totalventarealizada.setVisible(false);
        Inventarioventas.labelparaeltotal.setVisible(false);
        Inventarioventas.imprimirventa.setVisible(false);
  Inventarioventas.cancelarventa.setVisible(false);
  Modeloinventarioventas.ids_y_cantidades_porcancelacion(id);
  Modeloinventarioventas.impresiondeventacancelada(id);
  
        }
        public static void botonveridventasacreditopendientes(){
             Modeloinventarioventas.llenartablaconventasacreditopendiente(); //CARGA NUEVAMENTE LAS VENTAS POR ID
 Inventarioventas.labelnombre.setVisible(false);
         Inventarioventas.labelcredito.setVisible(false);
         Inventarioventas.deudor.setVisible(false);
         Inventarioventas.totalventacreditoenturno.setVisible(false);
         Inventarioventas.veridventasacreditopendiente.setVisible(false);
         Inventarioventas.pagarventaacredito.setVisible(false);
        }
        public static void boton_pagar_venta_credito(int id){
            Modeloinventarioventas.pagarventacredito(id);
        }
        public static void botonbuscarventas_porfecha(){
            try{
           String fechaparaventasdesde= llenarfechadesdeparamostrarlosidventas();
             String fechaparaventashasta= llenarfechahastaparamostrarlosidventas();
            if(fechaparaventasdesde.equals("")&&fechaparaventashasta.equals("")){
                JOptionPane.showMessageDialog(null, "Primero debe elegir un rango de fechas en los calendarios");
            }else{
     Modeloinventarioventas.showidventasporfechas(Inventarioventas.jTable2, llenarfechadesdeparamostrarlosidventas(),llenarfechahastaparamostrarlosidventas());
            }
        }catch(NullPointerException NP){
            JOptionPane.showMessageDialog(null,"Debes de elegir un rango de fechas en los botones para la fecha", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        }
        public static String llenarfechadesdeparamostrarlosidventas(){ // Ordena la fecha del componente Jcalendar  que esta de la sig. manera:  dia /mes / aÃ±o, lo cual para la base de datos no es la manera correcta de ingresarlo, sino asÃ­: aÃ±o/mes/dia
            int año= Inventarioventas.fechainicial.getCalendar().get(Calendar.YEAR);
       int mes= Inventarioventas.fechainicial.getCalendar().get(Calendar.MONTH)+1;
       int dia= Inventarioventas.fechainicial.getCalendar().get(Calendar.DAY_OF_MONTH);
       if(dia<10){
           String nuevodia= "0"+dia;
             fechaparaventasdesde= año+"/"+mes+"/"+nuevodia;
       }
       else{
           fechaparaventasdesde= año+"/"+mes+"/"+dia;
       }
       return fechaparaventasdesde;
      }
                     
    public static String llenarfechahastaparamostrarlosidventas(){ // Ordena la fecha del componente Jcalendar  que esta de la sig. manera:  dia /mes / aÃ±o, lo cual para la base de datos no es la manera correcta de ingresarlo, sino asÃ­: aÃ±o/mes/dia
       int año= Inventarioventas.fechafinal.getCalendar().get(Calendar.YEAR);
       int mes= Inventarioventas.fechafinal.getCalendar().get(Calendar.MONTH)+1;
       int dia= Inventarioventas.fechafinal.getCalendar().get(Calendar.DAY_OF_MONTH);
      if(dia<10){
           String newday= "0"+dia;
             fechaparaventashasta= año+"/"+mes+"/"+newday;
       }
       else{
           fechaparaventashasta= año+"/"+mes+"/"+dia;
       }
        return fechaparaventashasta;
    }
    public static void metodos_al_iniciar_inventarioventas(){
            Modeloinventarioventas.totaldelasventasdehoy(); // PARA LA SUMA DE LOS TOTALES DE LA VENTA
                     Modeloinventarioventas.conteodeventasrealizadasdehoy(); // CUANTAS VENTAS SE REALIZARON? 5 O 60 O XX
                  Modeloinventarioventas.llenartablaidventasconidrealizados();
                          Modeloinventarioventas.productosvendidoseneldia();//MUESTRA LAS VENTAS REA
                   Modeloinventarioventas.llenartablaconventasacreditopendiente();
      Inventarioventas.ventaseneldiasumadas.setText(String.valueOf(sumadetotalesdeventasdehoy));// VIENE DEL METODO ventaseneldiaREALIZADAS()
                        Inventarioventas.conteodelasventasrealizadas.setText(String.valueOf(conteototaldeventas)); // VIENE DEL METODO totalventasxdia(); ES UN CONTEO DE VENTAS
               Inventarioventas.totalventarealizada.setVisible(false);
    Inventarioventas.labelparaeltotal.setVisible(false);
       Inventarioventas.veridventas.setVisible(false);
               Inventarioventas.imprimirventa.setVisible(false);
               Inventarioventas.cancelarventa.setVisible(false);
                Inventarioventas.labelnombre.setVisible(false);
        Inventarioventas.labelcredito.setVisible(false);
        Inventarioventas.deudor.setVisible(false);
        Inventarioventas.totalventacreditoenturno.setVisible(false);
        Inventarioventas.veridventasacreditopendiente.setVisible(false);
         Inventarioventas.pagarventaacredito.setVisible(false);
     }
}
