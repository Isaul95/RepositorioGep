/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Controladores.Controladorexistencias;
import static Modelos.Modeloventa.cc;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import net.sf.jasperreports.engine.JasperExportManager;
import static org.codehaus.groovy.runtime.DefaultGroovyMethods.is;
import si.Existencias;
import si.SI;
import si.nucleo;

/**
 *
 * @author Alexis
 */
public class Modeloexistencias extends Controladorexistencias {

    public static SI cc = new SI();
    public static Statement sent;
    public static ResultSet rs;

    public static void mostrartodoslosproductosenexistencias() {
        nucleo.existenciadeproductos.setVisible(true);    //hace visible la tabla de proveedores 
        DefaultTableModel modelo = new DefaultTableModel(); // Se crea un objeto para agregar los nombres de las columnas a la tabla
        modelo.addColumn("Nombre");
        modelo.addColumn("Precio");
        nucleo.existenciadeproductos.setModel(modelo);  // Ya una vez asignado todos los nombres se le envia el objeto a la tabla proveedores
        String[] datos = new String[2];     //Un arreglo con la cantidad de nombres en las columnas
        try {
            Connection ca = cc.conexion();
            sent = ca.createStatement();
            rs = sent.executeQuery("select nombre_producto, precio from  productos"); // se ejecuta la sentencia dentro del parentesis
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = "$"+rs.getString(2);
                modelo.addRow(datos); //se asigna el arreglo  entero a todo el objeto llamado modelo  
            }
            nucleo.existenciadeproductos.setModel(modelo); // Se vuelve a enviar nuevamente el objeto modelo a la tabla  
            //PARA AJUSTAR EL ANCHO DE LAS TABLAS
            TableColumnModel columnModel = nucleo.existenciadeproductos.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(250);
        } catch (SQLException ex) {
            Logger.getLogger(nucleo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo mostrar ningun dato porque tu consulta está mal");
        } finally {
            cc.getClose();
        }
    }

    public static void mostrartodoslosproductosenexistenciasporbusqueda(String textoabuscar) {
        nucleo.existenciadeproductos.setVisible(true);    //hace visible la tabla de proveedores 
        DefaultTableModel modelo = new DefaultTableModel(); // Se crea un objeto para agregar los nombres de las columnas a la tabla
        modelo.addColumn("Nombre");
         modelo.addColumn("Precio");
        nucleo.existenciadeproductos.setModel(modelo);  // Ya una vez asignado todos los nombres se le envia el objeto a la tabla proveedores
        String[] datos = new String[2];     //Un arreglo con la cantidad de nombres en las columnas
        try {
            Connection ca = cc.conexion();
            sent = ca.createStatement();
            if (textoabuscar.equals("")) {
                rs = sent.executeQuery("select nombre_producto, precio from  productos"); // se ejecuta la sentencia dentro del parentesis
            } else {
                rs = sent.executeQuery("select nombre_producto, precio  from  productos where nombre_producto LIKE '%" + textoabuscar + "%' "); // se ejecuta la sentencia dentro del parentesis
            }
            while (rs.next()) {
                 datos[0] = rs.getString(1);
                datos[1] = "$"+rs.getString(2);
                modelo.addRow(datos); //se asigna el arreglo  entero a todo el objeto llamado modelo  
            }
            nucleo.existenciadeproductos.setModel(modelo); // Se vuelve a enviar nuevamente el objeto modelo a la tabla
            TableColumnModel columnModel = nucleo.existenciadeproductos.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(250);
        } catch (SQLException ex) {
            Logger.getLogger(nucleo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo mostrar  porque tu consulta está mal");
        } finally {
            cc.getClose();
        }
    }
    
    //DE AQUI EN ADELANTE ES PARA DESCARGAR LOS ARCHIVOS
    
 public static void mostrar_archivos(){
     Existencias.existenciadeproductos.setVisible(true);    //hace visible la tabla de proveedores 
        DefaultTableModel modelo = new DefaultTableModel(); // Se crea un objeto para agregar los nombres de las columnas a la tabla
        modelo.addColumn("Paciente");
        modelo.addColumn("Archivo");
        modelo.addColumn("Ingreso");
        Existencias.existenciadeproductos.setModel(modelo);  // Ya una vez asignado todos los nombres se le envia el objeto a la tabla proveedores
        Object[] datos = new Object[3];     //Un arreglo con la cantidad de nombres en las columnas
        try {
            Connection ca = cc.conexion();
            sent = ca.createStatement();
            rs = sent.executeQuery("SELECT p.nombre,f.nombre_archivo, p.fecha_ingreso from archivos f inner join pacientes p on p.id_paciente = f.id_paciente"); // se ejecuta la sentencia dentro del parentesis
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                 datos[2] = rs.getTimestamp("p.fecha_ingreso");
                modelo.addRow(datos); //se asigna el arreglo  entero a todo el objeto llamado modelo  
            }
            Existencias.existenciadeproductos.setModel(modelo); // Se vuelve a enviar nuevamente el objeto modelo a la tabla  
            //PARA AJUSTAR EL ANCHO DE LAS TABLAS
            TableColumnModel columnModel = Existencias.existenciadeproductos.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(250);
        } catch (SQLException ex) {
            Logger.getLogger(nucleo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo mostrar ningun dato porque tu consulta está mal");
        } finally {
            cc.getClose();
        }
 }
 public static void mostrararchivosporbusqueda(String textoabuscar) {
        Existencias.existenciadeproductos.setVisible(true);    //hace visible la tabla de proveedores 
        DefaultTableModel modelo = new DefaultTableModel(); // Se crea un objeto para agregar los nombres de las columnas a la tabla
        modelo.addColumn("Paciente");
        modelo.addColumn("Archivo");
        modelo.addColumn("Ingreso");
        Existencias.existenciadeproductos.setModel(modelo);  // Ya una vez asignado todos los nombres se le envia el objeto a la tabla proveedores
        Object[] datos = new Object[3];     //Un arreglo con la cantidad de nombres en las columnas
      try {
            Connection ca = cc.conexion();
            sent = ca.createStatement();
            if (textoabuscar.equals("")) {
              rs = sent.executeQuery("SELECT p.nombre,f.nombre_archivo, p.fecha_ingreso from archivos f inner join pacientes p on p.id_paciente = f.id_paciente"); // se ejecuta la sentencia dentro del parentesis
            } else {
                rs = sent.executeQuery("SELECT p.nombre,f.nombre_archivo, p.fecha_ingreso from archivos f inner join pacientes p on p.id_paciente = f.id_paciente where p.nombre LIKE '%" + textoabuscar + "%' "); // se ejecuta la sentencia dentro del parentesis
            }
            while (rs.next()) {
                datos[0] = rs.getString(1);
                datos[1] = rs.getString(2);
                 datos[2] = rs.getTimestamp("p.fecha_ingreso");
                modelo.addRow(datos); //se asigna el arreglo  entero a todo el objeto llamado modelo  
            }
            Existencias.existenciadeproductos.setModel(modelo); // Se vuelve a enviar nuevamente el objeto modelo a la tabla
            TableColumnModel columnModel = Existencias.existenciadeproductos.getColumnModel();
            columnModel.getColumn(0).setPreferredWidth(250);
        } catch (SQLException ex) {
            Logger.getLogger(nucleo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo mostrar  porque tu consulta está mal");
        } finally {
            cc.getClose();
        }
    }
 public static void descarga_de_archivo(String archivo){
                         try{ Connection ca= cc.conexion();// el id del usuario para obtener el id del usuario y comprobar si hay o no algun registro
                         InputStream is =null; 
                         Blob archivodescargado = null;
                         sent = ca.createStatement();
                          ResultSet rs= sent.executeQuery("SELECT archivo  FROM  archivos where nombre_archivo ='"+archivo+"'");
                          if(rs.next()){
                             archivodescargado =  rs.getBlob("archivo");
                              is = archivodescargado.getBinaryStream();
                                  // id_usuario=Short.parseShort(rs.getString("id_usuario"));
                         }
                          guardar_el_archivo_descargado(is,archivo);
                         }catch(Exception a){
                                       JOptionPane.showMessageDialog(null, "Error, get_id_usuario; SELECT *  FROM  user where nombre_usuario = ","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
                            }finally{
                            cc.getClose(); 
                           }
                         }
 
 public static void guardar_el_archivo_descargado(InputStream archivo, String nombre){
        try {File fichaero_a_la_rutr = new File("C:/reportes_descargados/"+nombre);
  BufferedInputStream in = new BufferedInputStream(archivo);
            BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(fichaero_a_la_rutr));
            byte[] bytes = new byte[8096];
            int len = 0;
             while ((len = in.read(bytes))>0){
                 out.write(bytes,0,len);
             }
             out.flush();
             out.close();
             in.close();
        }  
        catch (FileNotFoundException ex) {
            JOptionPane.showMessageDialog(null,"Su archivo se encuentra descargado y abierto, cierre primero el archivo antes de descargarlo","Advertencia",JOptionPane.ERROR_MESSAGE);
            Logger.getLogger(Modeloexistencias.class.getName()).log(Level.SEVERE, null, ex);
        }catch (IOException ex) {
             Logger.getLogger(Modeloexistencias.class.getName()).log(Level.SEVERE, null, ex);
         }
         
  
 }
}
