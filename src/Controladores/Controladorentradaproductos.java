/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Modeloventa;
import si.Entradaproductos;

/**
 *
 * @author Alexis
 */
public class Controladorentradaproductos {
     public static void metodos_al_iniciar_entradasproductos(){
              Modeloventa.TablallenadoparaEntradas(Entradaproductos.Jtable_ProductosEntradas);
                  Modeloventa.ParaLAVenta(Entradaproductos.JtablepaLaVenta);
      }
}
