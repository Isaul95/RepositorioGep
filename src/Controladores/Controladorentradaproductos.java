/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Modeloentradaproductos;

import si.Entradaproductos;

/**
 *
 * @author Alexis
 */
public class Controladorentradaproductos {
    public static float cantidaddesdelatablaeditable;
     public static void metodos_al_iniciar_entradasproductos(){
              Modeloentradaproductos.TablallenadoparaEntradas(Entradaproductos.Jtable_ProductosEntradas);
                  Modeloentradaproductos.ParaLAVenta(Entradaproductos.JtablepaLaVenta);
      }
}
