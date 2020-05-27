/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.DESCRIPCION_VENTA;
import Modelos.Modelo_capturar_resultados;
import Modelos.Modelo_extrayendo_paquetes;
import java.util.ArrayList;
import java.util.List;

import si.Capturar_resultados;


public class Controlador_capturar_resultados {
    public static float cantidaddesdelatablaeditable;
    public static int id_a_actualizar_resultados, id_paciente, respuesta_para_activar_el_pdf;
    public static int id_paciente_para_el_paquete;
    public static String resultado_del_estudio="", nombre_credito_para_el_paquete="", estado_para_el_paquete="", fecha_para_el_paquete="";
    public static List<DESCRIPCION_VENTA> PRODUCTOS = new ArrayList<>();
            
     public static void metodos_al_iniciar_entradasproductos0(int id_venta){
         if(Modelo_capturar_resultados.cantidad_de_estudios(id_venta)==1){//pretendiendo que es un solo estudio
             if(Modelo_capturar_resultados.verificandopaquete(id_venta).substring(0, 7).equalsIgnoreCase("PAQUETE")){//confirmando que sea un paquete
             Modelo_capturar_resultados.obtener_id_paciente_nombre_credito_estado_fecha_para_el_paquete(id_venta);//OBTENEMOS LOS DATOS FALTANTES PARA ARMAR EL PAQUETE
                 Modelo_extrayendo_paquetes.armando_el_paquete(//ya para armar el paquete
                     Modelo_extrayendo_paquetes.id_que_pertencen_a_cada_paquete(//extrayendo los id que pertenecen al paquete
                             Modelo_capturar_resultados.verificandopaquete(id_venta)),//extrayendo el nombre del paquete de acuerdo al id_venta
                         id_venta, //MANDANDO EL ID VENTA
                         id_paciente_para_el_paquete, //MANDANDO id_paciente
                         nombre_credito_para_el_paquete,//MANDANDO nombre_creditO
                        estado_para_el_paquete,//MANDANDO estado 
                        fecha_para_el_paquete);//MANDANDO fecha
                 Modelo_extrayendo_paquetes.insertando_analisis_del_paquete();
                 Modelo_capturar_resultados.TablallenadoparaEntradas(Capturar_resultados.Jtable_ProductosEntradas, id_venta);
                 
             }else{//no es un paquete, pero si un solo estudio
             Modelo_capturar_resultados.TablallenadoparaEntradas(Capturar_resultados.Jtable_ProductosEntradas, id_venta);
         }
         }else{
             Modelo_capturar_resultados.TablallenadoparaEntradas(Capturar_resultados.Jtable_ProductosEntradas, id_venta);
         }
              
      }
     public static void se_puede_habilitar_el_boton(int id_venta){
        Modelo_capturar_resultados.activar_boton_pdf(id_venta);
         if(Controlador_capturar_resultados.respuesta_para_activar_el_pdf==0){
             System.out.println("Contro if: "+Controlador_capturar_resultados.respuesta_para_activar_el_pdf);
             Capturar_resultados.genetrar_Pdf.setEnabled(true);}
         else{
             System.out.println("Contro else: "+Controlador_capturar_resultados.respuesta_para_activar_el_pdf);
Capturar_resultados.genetrar_Pdf.setEnabled(false);}
     }
}
