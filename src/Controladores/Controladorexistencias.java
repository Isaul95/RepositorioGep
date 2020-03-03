/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Modeloexistencias;
import javax.swing.JOptionPane;
import si.Existencias;

/**
 *
 * @author Alexis
 */
public class Controladorexistencias {
    public static short fila;
    public static String nombredepiezaseleccionada;
    public static void mostrartodoslosproductosenexistencias(String nombre){
        Modeloexistencias.mostrartodoslosproductosenexistenciasporbusqueda(nombre);
    }
    public static void metodosalabrirexistencias(){
        Modeloexistencias.mostrartodoslosproductosenexistencias();
    }
    public static void mandaraventa(){
          fila =Short.parseShort(String.valueOf(Existencias.existenciadeproductos.getSelectedRow()));
 if(fila>=0){// CUANDO UNA CELDA SE SELECCIONO
            nombredepiezaseleccionada=Existencias.existenciadeproductos.getValueAt(fila,0).toString();
    // BOOLEANAS PARA SABER CUALES NO SE VA A REGRESAR
            Controladorventa manda = new Controladorventa(nombredepiezaseleccionada,1);   
        }else{
            JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila primero","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
