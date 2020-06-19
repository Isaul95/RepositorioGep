/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Modeloexistencias;
import javax.swing.JOptionPane;
import si.Archivos;
import si.nucleo;

/**
 *
 * @author Alexis
 */
public class Controladorexistencias {
    public static short fila;
    public static String nombredepiezaseleccionada;
    public static int idpiezaseleccionada;
    public static float preciopiezaseleccionada;
    public static void mostrartodoslosproductosenexistencias(String nombre){
        Modeloexistencias.mostrartodoslosproductosenexistenciasporbusqueda(nombre);
    }
    public static void metodosalabrirexistencias(){
        Modeloexistencias.mostrartodoslosproductosenexistencias();
       
    }
    public static void mandaraventa(){
          fila =Short.parseShort(String.valueOf(nucleo.existenciadeproductos.getSelectedRow()));
 if(fila>=0){// CUANDO UNA CELDA SE SELECCIONO
     idpiezaseleccionada=Integer.parseInt(nucleo.existenciadeproductos.getValueAt(fila,0).toString());
            nombredepiezaseleccionada=nucleo.existenciadeproductos.getValueAt(fila,1).toString();
            preciopiezaseleccionada=Float.parseFloat(nucleo.existenciadeproductos.getValueAt(fila,3).toString().substring(1));
    // BOOLEANAS PARA SABER CUALES NO SE VA A REGRESAR
            Controladorventa manda = new Controladorventa(idpiezaseleccionada,nombredepiezaseleccionada,1,preciopiezaseleccionada);   
        }else{
            JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila primero","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    //DE AQUI EN ADELANTE SON LOS METODOS PARA BUSQUEDA Y DESCARGA DE ARCHIVOS
    
    public static void metodosalabrirarchivos(){
         Modeloexistencias.mostrar_archivos();
    }
     public static void mostrararchivosporbusqueda(String nombre){
        Modeloexistencias.mostrararchivosporbusqueda(nombre);
    }
    public static void descargararchivo(){
          fila =Short.parseShort(String.valueOf(Archivos.existenciadeproductos.getSelectedRow()));
 if(fila>=0){// CUANDO UNA CELDA SE SELECCIONO
            nombredepiezaseleccionada=Archivos.existenciadeproductos.getValueAt(fila,1).toString();
    // BOOLEANAS PARA SABER CUALES NO SE VA A REGRESAR
            Modeloexistencias.descarga_de_archivo(nombredepiezaseleccionada);
            JOptionPane.showMessageDialog(null,"Archivo descargado");
          }else{
            JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila primero","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }
    }
    public static void eliminar_archivo(){
        fila =Short.parseShort(String.valueOf(Archivos.existenciadeproductos.getSelectedRow()));
 if(fila>=0){// CUANDO UNA CELDA SE SELECCIONO
            nombredepiezaseleccionada=Archivos.existenciadeproductos.getValueAt(fila,1).toString();
         Modeloexistencias.eliminar_archivo(nombredepiezaseleccionada);
          }else{
            JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila primero","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }
    }
}
