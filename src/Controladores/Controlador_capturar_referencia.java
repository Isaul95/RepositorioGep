/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Modelo_capturar_referencia;
import si.Capturar_referencia;

/**
 *
 * @author AsTecI
 */
 public class Controlador_capturar_referencia extends Modelo_capturar_referencia{
    public static String metodos_al_iniciar(String p){
        return obtener_el_valor_de_referencia_del_siguiente_producto(p);
    }
    public static void actualizar_valor_de_referencia(){
        actualizandor_valor_de_referencia();
        
    }
}
