/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Modeloexistencias;

/**
 *
 * @author Alexis
 */
public class Controladorexistencias {
    public static void mostrartodoslosproductosenexistencias(String nombre){
        Modeloexistencias.mostrartodoslosproductosenexistenciasporbusqueda(nombre);
    }
    public static void metodosalabrirexistencias(){
        Modeloexistencias.mostrartodoslosproductosenexistencias();
    }
}
