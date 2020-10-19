/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;
import Controladores.Controladorinventarioutilidades;
import Controladores.Controladorventa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import si.utilidades;
import si.SI;
import si.nucleo;
import ticket.Tickets_de_venta;


/**
 *
 * @author aleks
 */
public class Modeloinventarioutilidades extends Controladorinventarioutilidades{
    static SI cc= new SI();
   static Statement sent;  
static ResultSet rs;  

public static void llenartablaidventasconidrealizados(){ // recibe como parametro 
         Object[] columna = new Object[5];  //crear un obj con el nombre de colunna
             DefaultTableModel modeloTE = new DefaultTableModel(); 
                  utilidades.jTable2.setModel(modeloTE);  // add modelo ala tabla 
        modeloTE.addColumn("Ventas");
        modeloTE.addColumn("Pagos");
        modeloTE.addColumn("Gastos"); 
        modeloTE.addColumn("Utilidad");
        modeloTE.addColumn("Fecha");
    utilidades.jTable2.setModel(modeloTE);  // add modelo ala tabla         
        try {  Connection ca= cc.conexion(); // CONEXION DB 
             String sSQL = "SELECT * FROM utilidad";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
                columna[0] = "$"+String.valueOf(rs.getFloat(2));
                columna[1] = "$"+String.valueOf(rs.getFloat(3));
                columna[2] = "$"+String.valueOf(rs.getFloat(4));
                 columna[3] = "$"+String.valueOf(rs.getFloat(5));
                columna[4] = rs.getString(6);
                modeloTE.addRow(columna);
            }
          utilidades.jTable2.setModel(modeloTE);  // add modelo ala tabla 
                             TableColumnModel columnModel =   utilidades.jTable2.getColumnModel();
                               // columnModel.getColumn(0).setPreferredWidth(10);
                              columnModel.getColumn(0).setMaxWidth(100);
                               columnModel.getColumn(0).setMinWidth(100);
                             //columnModel.getColumn(1).setPreferredWidth(250);
                               columnModel.getColumn(1).setMaxWidth(390);
                               columnModel.getColumn(1).setMinWidth(390);
                           //  columnModel.getColumn(2).setPreferredWidth(20); 
                               columnModel.getColumn(2).setMaxWidth(100);
                               columnModel.getColumn(2).setMinWidth(100);
                           //  columnModel.getColumn(3).setPreferredWidth(10);
                              columnModel.getColumn(3).setMaxWidth(100);
                               columnModel.getColumn(3).setMinWidth(100);
                           //  columnModel.getColumn(4).setPreferredWidth(10);
                             columnModel.getColumn(4).setMaxWidth(100);
                               columnModel.getColumn(4).setMinWidth(100);
                           
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "ERROR EN METODO: llenartablaidventasconidrealizados","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
       }finally{
                  cc.getClose();
             }
}
public static void showidventasporfechas(JTable tablaventas, String fechadesde, String fechahasta){
        Object[] columna = new Object[5];  //crear un obj con el nombre de colunna
            Connection ca= cc.conexion(); // CONEXION DB 
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  tablaventas.setModel(modeloT);  // add modelo ala tabla 
           modeloT.addColumn("Ventas");
        modeloT.addColumn("Pagos");
        modeloT.addColumn("Gastos"); 
        modeloT.addColumn("Utilidad");
        modeloT.addColumn("Fecha");
    try {        
           String sSQL = "SELECT  * from utilidad WHERE fecha BETWEEN '"+fechadesde+"' AND '"+fechahasta+ "' ";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                columna[0] = "$"+String.valueOf(rs.getFloat(2));
                columna[1] = "$"+String.valueOf(rs.getFloat(3));
                columna[2] = "$"+String.valueOf(rs.getFloat(4));
                 columna[3] = "$"+String.valueOf(rs.getFloat(5));
                columna[4] = rs.getString(6);
                modeloT.addRow(columna);
            }
        }                 utilidades.jTable2.setModel(modeloT);  // add modelo ala tabla 
                             TableColumnModel columnModel =   utilidades.jTable2.getColumnModel();
                           // columnModel.getColumn(0).setPreferredWidth(10);
                              columnModel.getColumn(0).setMaxWidth(100);
                               columnModel.getColumn(0).setMinWidth(100);
                             //columnModel.getColumn(1).setPreferredWidth(250);
                               columnModel.getColumn(1).setMaxWidth(390);
                               columnModel.getColumn(1).setMinWidth(390);
                           //  columnModel.getColumn(2).setPreferredWidth(20); 
                               columnModel.getColumn(2).setMaxWidth(100);
                               columnModel.getColumn(2).setMinWidth(100);
                           //  columnModel.getColumn(3).setPreferredWidth(10);
                              columnModel.getColumn(3).setMaxWidth(100);
                               columnModel.getColumn(3).setMinWidth(100);
                           //  columnModel.getColumn(4).setPreferredWidth(10);
                             columnModel.getColumn(4).setMaxWidth(100);
                               columnModel.getColumn(4).setMinWidth(100);
                            
     } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Advertencia", JOptionPane.PLAIN_MESSAGE);    
    }finally{cc.getClose();}
    }
public static void totalutilidades(){
        try{  Connection ca= cc.conexion();// La suma de todos los importes    
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select SUM(utilidad) from utilidad");
                                            while(rs.next()){
                                                      totalutilidades =Float.parseFloat(rs.getString("SUM(utilidad)"));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                      }finally{cc.getClose();}// fin del precio-catch del producto

}
}
