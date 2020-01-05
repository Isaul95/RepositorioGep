/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Controladores.Controladorentradaproductos;
import Controladores.Controladorventa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import si.Entradaproductos;
import si.SI;

/**
 *
 * @author aleks
 */
public class Modeloentradaproductos extends Controladorentradaproductos{
       static SI cc= new SI();
   static Statement sent;  
static ResultSet rs;  
    public static void TablallenadoparaEntradas(JTable tablaD){ // recibe como parametro 
           Object[] columna = new Object[2];  //crear un obj con el nombre de colunna
            DefaultTableModel modeloT = new DefaultTableModel(); 
                  tablaD.setModel(modeloT);  // add modelo ala tabla 
        modeloT.addColumn("nombre_producto");
        modeloT.addColumn("cantidad");
        try { Connection ca= cc.conexion(); // CONEXION DB 
         String sSQL = "SELECT  nombre_producto, cantidad FROM productos WHERE nombre_producto NOT IN('Huesito', 'Medio pollo','Pechuga en bisteck')";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                columna[0] = rs.getString(1);
                 columna[1] = rs.getFloat(2);
                modeloT.addRow(columna);
            }
                        modeloT.addTableModelListener(new TableModelListener(){
                @Override
                public void tableChanged(TableModelEvent e) {
                    int fila =Entradaproductos.Jtable_ProductosEntradas.getSelectedRow();
                    int col =Entradaproductos.Jtable_ProductosEntradas.getSelectedColumn();            
     
                    if(e.getType() == TableModelEvent.UPDATE){
                        if(fila>=0){// CUANDO UNA CELDA SE SELECCIONO         
                     boolean pass = Controladorventa.validarFormularioparaentradadeproductos(modeloT.getValueAt(e.getFirstRow(), e.getColumn()).toString());
                             if(pass){
                                   String valor = Entradaproductos.Jtable_ProductosEntradas.getValueAt(fila, 0).toString();
                            cantidaddesdelatablaeditable = Float.parseFloat(modeloT.getValueAt(e.getFirstRow(), e.getColumn()).toString());
                              Modeloventa.id_producto(valor); 
                             Modeloventa.cantidadpolloenDByname(Controladorventa.id_producto);
                              cantidaddesdelatablaeditable+=Controladorventa.cantidadpolloenDB;
                              String sql = "UPDATE productos SET cantidad='"+cantidaddesdelatablaeditable+"' WHERE id_producto="+Controladorventa.id_producto;            
    insertandopiezasdepolloporhaberagregadoxcantidaddepollocrudo(valor, cantidaddesdelatablaeditable);
                          PreparedStatement pst;
                          try{Connection ca= cc.conexion();
                               pst = ca.prepareStatement(sql);
                               int rows = pst.executeUpdate();
                       ParaLAVenta(Entradaproductos.JtablepaLaVenta);  // ***********************
                          } catch (SQLException ex) {
                               JOptionPane.showMessageDialog(null, "ERROR EN METODO: tableChanged","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
                           }finally{cc.getClose();}
      }
                             }                             
                    }
   }

            });
 }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "ERROR EN METODO: TablallenadoparaEntradas","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{
                  cc.getClose();
             }
}
      public static void ParaLAVenta(JTable tablaD){ // recibe como parametro 
         Object[] columna = new Object[3];  //crear un obj con el nombre de colunna
             DefaultTableModel modeloT = new DefaultTableModel(); 
                  tablaD.setModel(modeloT);  // add modelo ala tabla         
        modeloT.addColumn("nombre_producto");    
        modeloT.addColumn("precio");
        modeloT.addColumn("cantidad");
        try { Connection ca= cc.conexion(); // CONEXION DB 
         String sSQL = "SELECT nombre_producto, precio, cantidad FROM productos WHERE nombre_producto NOT IN ('Huesito', 'Medio pollo','Pechuga en bisteck')";
         PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                columna[0] = rs.getString(1);
                columna[1] = rs.getFloat(2);
                 columna[2] = rs.getFloat(3);
                modeloT.addRow(columna);
            }
        }
    } catch (Exception e) { JOptionPane.showMessageDialog(null, "ERROR EN METODO: ParaLAVenta","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{
                  cc.getClose();
             }
}
        public static void insertandopiezasdepolloporhaberagregadoxcantidaddepollocrudo(String valor, float cantidaddesdelatablaeditable){
  if(valor.equals("pollo crudo")){// si estan modificando sobre pollo crudo, se inserta primero en la linea de arriba y luego las otras piezas de pollo a su equivalente
                       
 for(Controladorventa.ciclofor=0; Controladorventa.ciclofor<Controladorventa.piezas.length; Controladorventa.ciclofor++) {//RECORRIENDO EL ARREGLO DE POLLOS
      try{    Connection ca= cc.conexion(); // el id del usuario
                if(Controladorventa.piezas[Controladorventa.ciclofor].equals("Muslo")||
                   Controladorventa.piezas[Controladorventa.ciclofor].equals("Pierna")||
                   Controladorventa.piezas[Controladorventa.ciclofor].equals("Ala")||
                  Controladorventa.piezas[Controladorventa.ciclofor].equals("Patas")){
                    Modeloventa.id_producto(Controladorventa.piezas[Controladorventa.ciclofor]);
                    Modeloventa.cantidadpolloenDByname((int)Controladorventa.id_producto);
               PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+((cantidaddesdelatablaeditable*2)+Controladorventa.cantidadpolloenDB)+"'WHERE nombre_producto='"+Controladorventa.piezas[Controladorventa.ciclofor]+"'");
               int ty = ps.executeUpdate();      
                 if(ty>0){
                     ParaLAVenta(Entradaproductos.JtablepaLaVenta);  // ***********************                      
                 }else{ 
                 }
           } 
           else if(Controladorventa.piezas[Controladorventa.ciclofor].equals("Huacal")||Controladorventa.piezas[Controladorventa.ciclofor].equals("Cadera")||
                   Controladorventa.piezas[Controladorventa.ciclofor].equals("Cabeza")||
                   Controladorventa.piezas[Controladorventa.ciclofor].equals("Molleja")||Controladorventa.piezas[Controladorventa.ciclofor].equals("Pechuga")){
               Modeloventa.id_producto(Controladorventa.piezas[Controladorventa.ciclofor]);
                    Modeloventa.cantidadpolloenDByname((int)Controladorventa.id_producto);
        PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+((cantidaddesdelatablaeditable*1)+Controladorventa.cantidadpolloenDB)+"'WHERE nombre_producto='"+Controladorventa.piezas[Controladorventa.ciclofor]+"'");
               int ty = ps.executeUpdate();
                 if(ty>0){
                     ParaLAVenta(Entradaproductos.JtablepaLaVenta);  // ***********************        
                 }else{
                 }
        }
        }//fin del id del usuario
                 catch(Exception w){
                  JOptionPane.showMessageDialog(null, "ERROR EN METODO: insertandopiezasdepolloporhaberagregadoxcantidaddepollocrudo","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
                 }finally{
                  cc.getClose();
             }//fin del id del usuario
 }// RECORRIENDO EL ARREGLO DE POLLOS                      
}// si estan modificando sobre pollo crudo, se inserta primero en la linea de arriba y luego las otras piezas de pollo a su equivalente
}
}
