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
import Modelos.Modeloinventariopagos;
import Modelos.Modeloventa;
import si.inventariopagos;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import si.Edicion_pacientes;
import ticket.Tickets_de_venta;


/**
 *
 * @author Alexis
 */
public class Controladorinventariopagos {
    public static Tickets_de_venta mandardatosticketventacancelada;

    public static float 
            sumadeimportescreditopendiente,  sumadeimportesparaeltotal, 
            descuentoticket, cambioticket, pagoticket, totalticket, subtotalticket, descuentoenventa;
    public static short conteototaldeventas, id_ventapencredito, id=0,id_paciente_para_modificar;
    public static String nombre_deudor="",Paciente_a_editar="Editar",creditopagado="Credito-pagado", estadocancelado= "Cancelada", creditopendiente="Credito-pendiente";
    public static ArrayList importesticket = new ArrayList();
    public static ArrayList preciounitarioticket = new ArrayList();
    public static ArrayList piezastcket = new ArrayList();
              public static ArrayList nombreproductoticket = new ArrayList();
              
                           
    public static void mostrarresultadosenlabusquedadenombres(String datoabuscar){
        Modeloinventariopagos.consultarlosresultadosenlabusquedadenombres(datoabuscar);
    }
    public static void ventasdehoy(){
     Modeloinventariopagos.llenartablaidventasconidrealizados();                     
    }
    public static void ocultarloscomponentes(){
               inventariopagos.fechainicial.cleanup();
        inventariopagos.fechainicial.setDate(null);
        inventariopagos.fechafinal.cleanup();
        inventariopagos.fechafinal.setDate(null);
        inventariopagos.veridventas.setVisible(false);
        inventariopagos.labelparaeltotal.setText("00.00");
        inventariopagos.totalventarealizada.setVisible(false);
        inventariopagos.labelparaeltotal.setVisible(false);
        inventariopagos.imprimirventa.setVisible(false);
        inventariopagos.cancelarventa.setVisible(false);
          inventariopagos.opciones.setVisible(false);
 inventariopagos.verpagos.setVisible(false);         
    }
    public static void botonveridventas(){ //INVENTARIOVENTAS
            Modeloinventariopagos.llenartablaidventasconidrealizados(); //CARGA NUEVAMENTE LAS VENTAS POR ID
            ocultarloscomponentes();
 }
  public static void mostrarloscomponentes(){
      inventariopagos.opciones.setVisible(true);
      inventariopagos.veridventas.setVisible(true);
           inventariopagos.labelparaeltotal.setVisible(true);
            inventariopagos.labelparaeltotal.setText(String.valueOf(sumadeimportesparaeltotal));
                inventariopagos.totalventarealizada.setVisible(true);
                inventariopagos.imprimirventa.setVisible(true);
                inventariopagos.cancelarventa.setVisible(true);
                 inventariopagos.verpagos.setVisible(true);     
              
  } 
  
         public static void verdescripcionenbaseaventarealizada(){
            int fila =inventariopagos.jTable2.getSelectedRow();
 if(fila>=0){
            boolean pass= validarFormularioparamostrardescripciondeproductosporid(inventariopagos.jTable2.getValueAt(fila,0).toString());
            if(pass){
               
                id=Short.parseShort(inventariopagos.jTable2.getValueAt(fila,0).toString());
                Modeloinventariopagos.descripciondeproductosenbasealnumerodeventa(id);
                  mostrarloscomponentes();    
                         inventariopagos.labelbusqueda.setVisible(false);
                    inventariopagos.busqueda.setVisible(false);
            }
        }
        else
        JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila primero","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }
     public static void botoncancelarventa(short id){
            Modeloinventariopagos.status_cancelado(id); //Cancela venta: se refiera a poner el estado "Cancelada" tando a la tabla venta y descripcion_de_venta que correspondan a èste ID
        Modeloinventariopagos.llenartablaidventasconidrealizados(); //Carga nuevamente las ventas por ID
       ocultarloscomponentes();
 Modeloinventariopagos.la_venta_tiene_descuento_si_o_no((int)id);
         Modeloinventariopagos.eliminar_idventa_sitienedescuento(descuentoenventa,(int)id);
  Controladorventa.storage.clear();
  Modeloinventariopagos.impresiondeventacancelada(id);
        }
     
 public static void ver_pagos(int id_venta){
      Modeloinventariopagos.mostrarpagos(id_venta);
 }
        public static void botonbuscarventas_porfecha(){
            try{
           String fechaparaventasdesde= llenarfechadesdeparamostrarlosidventas();
             String fechaparaventashasta= llenarfechahastaparamostrarlosidventas();
            if(fechaparaventasdesde.equals("")&&fechaparaventashasta.equals("")){
                JOptionPane.showMessageDialog(null, "Primero debe elegir un rango de fechas en los calendarios");
            }else{
     Modeloinventariopagos.showidventasporfechas(inventariopagos.jTable2, llenarfechadesdeparamostrarlosidventas(),llenarfechahastaparamostrarlosidventas());
            }
        }catch(NullPointerException NP){
            JOptionPane.showMessageDialog(null,"Debes de elegir un rango de fechas en los botones para la fecha", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
        }
        public static String llenarfechadesdeparamostrarlosidventas(){ // Ordena la fecha del componente Jcalendar  que esta de la sig. manera:  dia /mes / aÃ±o, lo cual para la base de datos no es la manera correcta de ingresarlo, sino asÃ­: aÃ±o/mes/dia
            int año= inventariopagos.fechainicial.getCalendar().get(Calendar.YEAR);
       int mes= inventariopagos.fechainicial.getCalendar().get(Calendar.MONTH)+1;
       int dia= inventariopagos.fechainicial.getCalendar().get(Calendar.DAY_OF_MONTH);
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
       int año= inventariopagos.fechafinal.getCalendar().get(Calendar.YEAR);
       int mes= inventariopagos.fechafinal.getCalendar().get(Calendar.MONTH)+1;
       int dia= inventariopagos.fechafinal.getCalendar().get(Calendar.DAY_OF_MONTH);
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
   Modeloinventariopagos.llenartablaidventasconidrealizados();
                   
               inventariopagos.totalventarealizada.setVisible(false);
    inventariopagos.labelparaeltotal.setVisible(false);
       inventariopagos.veridventas.setVisible(false);
               inventariopagos.imprimirventa.setVisible(false);
               inventariopagos.cancelarventa.setVisible(false);
               inventariopagos.verpagos.setVisible(false);
     }
}
