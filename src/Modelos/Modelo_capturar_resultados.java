/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Controladores.Controlador_capturar_resultados;
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
import javax.swing.table.TableColumnModel;
import si.Capturar_resultados;
import si.SI;

/**
 *
 * @author aleks
 */
public class Modelo_capturar_resultados extends Controlador_capturar_resultados{
       static SI cc= new SI();
   static Statement sent;  
static ResultSet rs;  
    public static void TablallenadoparaEntradas(JTable tablaD, int id_venta){ // recibe como parametro 
           Object[] columna = new Object[2];  //crear un obj con el nombre de colunna
            DefaultTableModel modeloT = new DefaultTableModel(); 
                  tablaD.setModel(modeloT);  // add modelo ala tabla 
        modeloT.addColumn("Estudio");
        modeloT.addColumn("Resultado");
        try { Connection ca= cc.conexion(); // CONEXION DB 
         String sSQL = "select pacientes.nombre, pacientes.edad, pacientes.sexo, pacientes.fecha_nacimiento, venta.fecha_reporte, venta.hora, descripcion_de_venta.nombre_producto, descripcion_de_venta.resultado from venta INNER JOIN descripcion_de_venta on venta.id_venta = descripcion_de_venta.id_venta inner join pacientes on descripcion_de_venta.id_paciente = pacientes.id_paciente WHERE descripcion_de_venta.estado = 'Realizada' and descripcion_de_venta.id_venta ="+id_venta;
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                 Capturar_resultados.paciente.setText(rs.getString(1));//Se almacena el nombre del paciente
                  Capturar_resultados.edad.setText(rs.getString(2));//Se almacena la edad del paciente
                 Capturar_resultados.sexo.setText(rs.getString(3));//Se almacena la edad del paciente
                 Capturar_resultados.fecha_nacimiento.setText(rs.getString(4));//Se almacena la fecha de nacimiento del paciente
                 Capturar_resultados.fecha_hora_ingreso.setText(rs.getString(5)+" "+rs.getString(6));//Se almacena la fecha de nacimiento del paciente
                columna[0] = rs.getString(7);
                 columna[1] = rs.getString(8);
                
                modeloT.addRow(columna);
            }        TableColumnModel columnModel =  tablaD.getColumnModel();
    columnModel.getColumn(0).setPreferredWidth(250); columnModel.getColumn(1).setPreferredWidth(50);    
                        modeloT.addTableModelListener(new TableModelListener(){
                @Override
                public void tableChanged(TableModelEvent e) {
                    int fila =Capturar_resultados.Jtable_ProductosEntradas.getSelectedRow();
                    int col =Capturar_resultados.Jtable_ProductosEntradas.getSelectedColumn();            
     
                    if(e.getType() == TableModelEvent.UPDATE){
                        if(fila>=0){// CUANDO UNA CELDA SE SELECCIONO         
                 //  COMENTADA LA VALIDACION  boolean pass = Controladorventa.validarFormularioparaentradadeproductos(modeloT.getValueAt(e.getFirstRow(), e.getColumn()).toString());
                       //      if(pass){ COMENTADA LA VALIDACION
                                   String valor = Capturar_resultados.Jtable_ProductosEntradas.getValueAt(fila, 0).toString();
                            resultado_del_estudio = modeloT.getValueAt(e.getFirstRow(), e.getColumn()).toString();
                              Modeloventa.id_producto(valor); 
                              String sql = "UPDATE descripcion_de_venta SET resultado='"+resultado_del_estudio+"' WHERE id_producto="+Controladorventa.id_producto+" AND id_venta="+id_venta;            
                             PreparedStatement pst;
                          try{Connection ca= cc.conexion();
                               pst = ca.prepareStatement(sql);
                               int rows = pst.executeUpdate();
                   
                          } catch (SQLException ex) {
                               JOptionPane.showMessageDialog(null, "ERROR EN METODO: tableChanged","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
                           }finally{cc.getClose();}
  //    }COMENTADA LA VALIDACION
                             }                             
                    }
   }

            });
 }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, "ERROR EN METODO: TablallenadoparaEntradas : "+e.getMessage(),"DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{
                  cc.getClose();
             }
}
}
