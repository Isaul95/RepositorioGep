/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Controladores.Controladorventa;
import static Controladores.Controladorventa.conteototaldeventas;
import static Controladores.Controladorventa.fechaparaventasdesde;
import static Controladores.Controladorventa.fechaparaventashasta;
import static Controladores.Controladorventa.id;
import static Controladores.Controladorventa.sumadeimportescreditopendiente;
import static Controladores.Controladorventa.sumadeimportesparaeltotal;
import static Controladores.Controladorventa.sumadetotalesdeventasdehoy;
import static Controladores.Controladorventa.validarFormularioparamostrardescripciondeproductosporid;
import si.Inventarioventas;
import Modelos.Modeloventa;
import java.util.Calendar;
import javax.swing.JOptionPane;

/**
 *
 * @author Alexis
 */
public class Controladorinventarioventas {
    public static void ventasdehoy(){
           Modeloventa.totaldelasventasdehoy(); // PARA LA SUMA DE LOS TOTALES DE LA VENTA
                                Modeloventa.conteodeventasrealizadasdehoy(); // CUANTAS VENTAS SE REALIZARON? 5 O 60 O XX
                            Inventarioventas.ventaseneldiasumadas.setText(String.valueOf(Controladorventa.sumadetotalesdeventasdehoy));// VIENE DEL METODO ventaseneldiaREALIZADAS()
                             Inventarioventas.conteodelasventasrealizadas.setText(String.valueOf(Controladorventa.conteototaldeventas)); // VIENE DEL METODO totalventasxdia(); ES UN CONTEO DE VENTAS
                            
    Inventarioventas.ventaseneldiasumadas.setText(String.valueOf(Controladorventa.sumadetotalesdeventasdehoy));// VIENE DEL METODO ventaseneldiaREALIZADAS()
                             Inventarioventas.conteodelasventasrealizadas.setText(String.valueOf(Controladorventa.conteototaldeventas)); // VIENE DEL METODO totalventasxdia(); ES UN CONTEO DE VENTAS
    Modeloventa.llenartablaidventasconidrealizados();
                                Modeloventa.productosvendidoseneldia();//MUESTRA LAS VENTAS REA
                                Modeloventa.llenartablaconventasacreditopendiente();
                              
    }
    public static void botonveridventas(){ //INVENTARIOVENTAS
     Inventarioventas.fechainicial.cleanup();
        Inventarioventas.fechainicial.setDate(null);
        Inventarioventas.fechafinal.cleanup();
        Inventarioventas.fechafinal.setDate(null);
        Modeloventa.llenartablaidventasconidrealizados(); //CARGA NUEVAMENTE LAS VENTAS POR ID
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
                Controladorventa.id_ventapencredito=Short.parseShort(Inventarioventas.ventasacreditopendiente.getValueAt(fila,0).toString());
                Modeloventa.descripciondeproductosenbasealnumerodeventaporcreditopendiente(Integer.parseInt(Inventarioventas.ventasacreditopendiente.getValueAt(fila,0).toString()));
                Modeloventa.total_venta_creditopendiente(Controladorventa.id_ventapencredito);
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
                Modeloventa.descripciondeproductosenbasealnumerodeventa(id);
                Modeloventa.total_ventaporid(id);
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
            Modeloventa.status_cancelado(id);
        Inventarioventas.fechainicial.cleanup();
        Inventarioventas.fechainicial.setDate(null);
        Inventarioventas.fechafinal.cleanup();
        Inventarioventas.fechafinal.setDate(null);
        Modeloventa.llenartablaidventasconidrealizados(); //CARGA NUEVAMENTE LAS VENTAS POR ID
        Inventarioventas.veridventas.setVisible(false);
        Inventarioventas.labelparaeltotal.setText("00.00");
        Inventarioventas.totalventarealizada.setVisible(false);
        Inventarioventas.labelparaeltotal.setVisible(false);
        Inventarioventas.imprimirventa.setVisible(false);
  Inventarioventas.cancelarventa.setVisible(false);
  Modeloventa.ids_y_cantidades_porcancelacion(id);
  Modeloventa.impresiondeventacancelada(id);
  
        }
        public static void botonveridventasacreditopendientes(){
             Modeloventa.llenartablaconventasacreditopendiente(); //CARGA NUEVAMENTE LAS VENTAS POR ID
 Inventarioventas.labelnombre.setVisible(false);
         Inventarioventas.labelcredito.setVisible(false);
         Inventarioventas.deudor.setVisible(false);
         Inventarioventas.totalventacreditoenturno.setVisible(false);
         Inventarioventas.veridventasacreditopendiente.setVisible(false);
         Inventarioventas.pagarventaacredito.setVisible(false);
        }
        public static void boton_pagar_venta_credito(int id){
            Modeloventa.pagarventacredito(id);
        }
        public static void botonbuscarventas_porfecha(){
            try{
           String fechaparaventasdesde= llenarfechadesdeparamostrarlosidventas();
             String fechaparaventashasta= llenarfechahastaparamostrarlosidventas();
            if(fechaparaventasdesde.equals("")&&fechaparaventashasta.equals("")){
                JOptionPane.showMessageDialog(null, "Primero debe elegir un rango de fechas en los calendarios");
            }else{
     Modeloventa.showidventasporfechas(Inventarioventas.jTable2, llenarfechadesdeparamostrarlosidventas(),llenarfechahastaparamostrarlosidventas());
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
            Modeloventa.totaldelasventasdehoy(); // PARA LA SUMA DE LOS TOTALES DE LA VENTA
                     Modeloventa.conteodeventasrealizadasdehoy(); // CUANTAS VENTAS SE REALIZARON? 5 O 60 O XX
                  Modeloventa.llenartablaidventasconidrealizados();
                          Modeloventa.productosvendidoseneldia();//MUESTRA LAS VENTAS REA
                   Modeloventa.llenartablaconventasacreditopendiente();
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
