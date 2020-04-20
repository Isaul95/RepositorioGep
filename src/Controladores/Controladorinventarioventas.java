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
import Modelos.Modeloinventarioventas;
import Modelos.Modeloventa;
import si.Inventarioventas;
import java.util.ArrayList;
import java.util.Calendar;
import javax.swing.JOptionPane;
import si.Edicion_pacientes;
import ticket.ticketventacancelada;
import ticket.ticketventacondescuento;


/**
 *
 * @author Alexis
 */
public class Controladorinventarioventas {
    public static ticketventacancelada mandardatosticketventacancelada;
       public static ticketventacondescuento mandardatosaticketventacondescuento;  

    public static float sumadetotalesdeventasdehoy,
            sumadeimportescreditopendiente,  sumadeimportesparaeltotal, 
            descuentoticket, cambioticket, pagoticket, totalticket, subtotalticket, descuentoenventa;
    public static short conteototaldeventas, id_ventapencredito, id=0,id_paciente_para_modificar;
    public static String Paciente_a_editar="Editar",creditopagado="Credito-pagado", estadocancelado= "Cancelada", creditopendiente="Credito-pendiente";
    public static ArrayList importesticket = new ArrayList();
    public static ArrayList preciounitarioticket = new ArrayList();
    public static ArrayList piezastcket = new ArrayList();
              public static ArrayList nombreproductoticket = new ArrayList();
              
                           
    public static void mostrarresultadosenlabusquedadenombres(String datoabuscar){
        Modeloinventarioventas.consultarlosresultadosenlabusquedadenombres(datoabuscar);
    }
    public static void ventasdehoy(){
           Modeloinventarioventas.totaldelasventasdehoy(); // PARA LA SUMA DE LOS TOTALES DE LA VENTA
                                Modeloinventarioventas.conteodeventasrealizadasdehoy(); // CUANTAS VENTAS SE REALIZARON? 5 O 60 O XX
                            Inventarioventas.ventaseneldiasumadas.setText(String.valueOf(Controladorinventarioventas.sumadetotalesdeventasdehoy));// VIENE DEL METODO ventaseneldiaREALIZADAS()
                             Inventarioventas.conteodelasventasrealizadas.setText(String.valueOf(Controladorinventarioventas.conteototaldeventas)); // VIENE DEL METODO totalventasxdia(); ES UN CONTEO DE VENTAS
                            
    Inventarioventas.ventaseneldiasumadas.setText(String.valueOf(Controladorinventarioventas.sumadetotalesdeventasdehoy));// VIENE DEL METODO ventaseneldiaREALIZADAS()
                             Inventarioventas.conteodelasventasrealizadas.setText(String.valueOf(Controladorinventarioventas.conteototaldeventas)); // VIENE DEL METODO totalventasxdia(); ES UN CONTEO DE VENTAS
    Modeloinventarioventas.llenartablaidventasconidrealizados();                     
    }
    public static void ocultarloscomponentes(){
               Inventarioventas.fechainicial.cleanup();
        Inventarioventas.fechainicial.setDate(null);
        Inventarioventas.fechafinal.cleanup();
        Inventarioventas.fechafinal.setDate(null);
        Inventarioventas.veridventas.setVisible(false);
        Inventarioventas.labelparaeltotal.setText("00.00");
        Inventarioventas.totalventarealizada.setVisible(false);
        Inventarioventas.labelparaeltotal.setVisible(false);
        Inventarioventas.imprimirventa.setVisible(false);
        Inventarioventas.cancelarventa.setVisible(false);
        Inventarioventas.editar_paciente.setVisible(false);
        Inventarioventas.resultado.setVisible(false);
          Inventarioventas.opciones.setVisible(false);
         
    }
    public static void botonveridventas(){ //INVENTARIOVENTAS
            Modeloinventarioventas.llenartablaidventasconidrealizados(); //CARGA NUEVAMENTE LAS VENTAS POR ID
            ocultarloscomponentes();
 }
  public static void mostrarloscomponentes(){
      Inventarioventas.opciones.setVisible(true);
      Inventarioventas.veridventas.setVisible(true);
           Inventarioventas.labelparaeltotal.setVisible(true);
            Inventarioventas.labelparaeltotal.setText(String.valueOf(sumadeimportesparaeltotal));
                Inventarioventas.totalventarealizada.setVisible(true);
                Inventarioventas.imprimirventa.setVisible(true);
                Inventarioventas.cancelarventa.setVisible(true);
                 Inventarioventas.editar_paciente.setVisible(true);
        Inventarioventas.resultado.setVisible(true);
              
  } 
  
                                public static void verdescripcionenbaseaventarealizada(){
            int fila =Inventarioventas.jTable2.getSelectedRow();
 if(fila>=0){
            boolean pass= validarFormularioparamostrardescripciondeproductosporid(Inventarioventas.jTable2.getValueAt(fila,0).toString());
            if(pass){
               
                id=Short.parseShort(Inventarioventas.jTable2.getValueAt(fila,0).toString());
                Modeloinventarioventas.descripciondeproductosenbasealnumerodeventa(id);
                Modeloinventarioventas.total_ventaporid(id);
                       mostrarloscomponentes();    
                         Inventarioventas.labelbusqueda.setVisible(false);
                    Inventarioventas.busqueda.setVisible(false);
            }
        }
        else
        JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila primero","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }
     public static void botoncancelarventa(short id){
            Modeloinventarioventas.status_cancelado(id); //Cancela venta: se refiera a poner el estado "Cancelada" tando a la tabla venta y descripcion_de_venta que correspondan a èste ID
        Modeloinventarioventas.llenartablaidventasconidrealizados(); //Carga nuevamente las ventas por ID
       ocultarloscomponentes();
 Modeloinventarioventas.la_venta_tiene_descuento_si_o_no((int)id);
         Modeloinventarioventas.eliminar_idventa_sitienedescuento(descuentoenventa,(int)id);
  Controladorventa.storage.clear();
  Modeloinventarioventas.impresiondeventacancelada(id);
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
       int año= Inventarioventas.fechafinal.getCalendar().get(Calendar.YEAR);
       int mes= Inventarioventas.fechafinal.getCalendar().get(Calendar.MONTH)+1;
       int dia= Inventarioventas.fechafinal.getCalendar().get(Calendar.DAY_OF_MONTH);
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
   Modeloinventarioventas.conteodeventasrealizadasdehoy(); // CUANTAS VENTAS SE REALIZARON? 5 O 60 O XX
                  Modeloinventarioventas.llenartablaidventasconidrealizados();
                        Modelocortedecaja.ventaseneldia();// PARA LA SUMA DE LOS TOTALES DE LA VENTA
      Inventarioventas.ventaseneldiasumadas.setText(String.valueOf(ventasdeldia));// VIENE DEL METODO ventaseneldiaREALIZADAS()
                        Inventarioventas.conteodelasventasrealizadas.setText(String.valueOf(conteototaldeventas)); // VIENE DEL METODO totalventasxdia(); ES UN CONTEO DE VENTAS
               Inventarioventas.totalventarealizada.setVisible(false);
    Inventarioventas.labelparaeltotal.setVisible(false);
       Inventarioventas.veridventas.setVisible(false);
               Inventarioventas.imprimirventa.setVisible(false);
               Inventarioventas.cancelarventa.setVisible(false);
  Inventarioventas.editar_paciente.setVisible(false);
        Inventarioventas.resultado.setVisible(false);      
       
     }
    public static void editar_paciente(short id){
        //PRIMERO SE EDITA EL CAMPO AUXILIAR PARA HACERLE SABER QUE ESE DATO SE VA A MODIFICAR EN LA PROXIMA VENTANA
          if(Controladorventa.noduplicar_edicionpaciente==false){ 
          Modeloinventarioventas.indicar_el_paciente_a_actualizar(Short.parseShort(String.valueOf(id)));
     new Edicion_pacientes().setVisible(true);
          }
        
    }
}
