/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Modelo_capturar_resultados;

import si.Capturar_resultados;


public class Controlador_capturar_resultados {
    public static float cantidaddesdelatablaeditable;
    public static int id_a_actualizar_resultados, id_paciente;
    public static String resultado_del_estudio="";
            
     public static void metodos_al_iniciar_entradasproductos0(int id_venta){
              Modelo_capturar_resultados.TablallenadoparaEntradas(Capturar_resultados.Jtable_ProductosEntradas, id_venta);
     
      }
}
