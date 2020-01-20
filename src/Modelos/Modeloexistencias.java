/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import static si.Existencias.existenciadeproductos;
import si.SI;
import si.nucleo;

/**
 *
 * @author Alexis
 */
public class Modeloexistencias {
       public static SI cc= new SI();
 public static Statement sent;  
 public static  ResultSet rs;     
     public static void mostrartodoslosproductosenexistencias(){
            existenciadeproductos.setVisible(true);    //hace visible la tabla de proveedores 
              DefaultTableModel modelo = new DefaultTableModel(); // Se crea un objeto para agregar los nombres de las columnas a la tabla
    modelo.addColumn("Nombre");
    modelo.addColumn("Piezas");
     existenciadeproductos.setModel(modelo);  // Ya una vez asignado todos los nombres se le envia el objeto a la tabla proveedores
     String []datos = new String[2];     //Un arreglo con la cantidad de nombres en las columnas
    try {Connection ca= cc.conexion();
             sent = ca.createStatement();   
                       rs= sent.executeQuery("select nombre_producto, cantidad  from  productos where not nombre_producto in ('Huesito','Medio pollo','Pechuga en bisteck','Taxi')"); // se ejecuta la sentencia dentro del parentesis
            while(rs.next()){        
            datos[0]=rs.getString(1);
            datos[1]=rs.getString(2);
            modelo.addRow(datos); //se asigna el arreglo  entero a todo el objeto llamado modelo  
            }
           existenciadeproductos.setModel(modelo); // Se vuelve a enviar nuevamente el objeto modelo a la tabla  
           //PARA AJUSTAR EL ANCHO DE LAS TABLAS
              TableColumnModel columnModel =  existenciadeproductos.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(250);
    columnModel.getColumn(1).setPreferredWidth(50);
        } catch (SQLException ex) {
            Logger.getLogger(nucleo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo mostrar ningun dato porque tu consulta está mal");
        } finally{cc.getClose();}
    }
     public static void mostrartodoslosproductosenexistenciasporbusqueda(String textoabuscar){
            existenciadeproductos.setVisible(true);    //hace visible la tabla de proveedores 
              DefaultTableModel modelo = new DefaultTableModel(); // Se crea un objeto para agregar los nombres de las columnas a la tabla
    modelo.addColumn("Nombre");
    modelo.addColumn("Piezas");
     existenciadeproductos.setModel(modelo);  // Ya una vez asignado todos los nombres se le envia el objeto a la tabla proveedores
    String []datos = new String[2];     //Un arreglo con la cantidad de nombres en las columnas
    try {Connection ca= cc.conexion();
             sent = ca.createStatement();   
                       if(textoabuscar.equals("")){
                          rs= sent.executeQuery("select nombre_producto, cantidad  from  productos where nombre_producto not in ('Huesito','Medio pollo','Pechuga en bisteck','Taxi')"); // se ejecuta la sentencia dentro del parentesis
                       }
                       else{
                          rs= sent.executeQuery("select nombre_producto, cantidad  from  productos where nombre_producto LIKE '%" +textoabuscar+"%' and nombre_producto not in ('Huesito','Medio pollo','Pechuga en bisteck','Taxi')"); // se ejecuta la sentencia dentro del parentesis
                       }
             while(rs.next()){        
            datos[0]=rs.getString(1);
            datos[1]=rs.getString(2);
            modelo.addRow(datos); //se asigna el arreglo  entero a todo el objeto llamado modelo  
            }
           existenciadeproductos.setModel(modelo); // Se vuelve a enviar nuevamente el objeto modelo a la tabla
                         TableColumnModel columnModel =  existenciadeproductos.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(250);
    columnModel.getColumn(1).setPreferredWidth(50);
        } catch (SQLException ex) {
            Logger.getLogger(nucleo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo mostrar  porque tu consulta está mal");
        } finally{cc.getClose();}
    }

}
