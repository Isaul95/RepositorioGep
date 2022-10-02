
package Modelos;
import Controladores.Controlador_capturar_resultados;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import si.Captura_Bacterias;
import si.Captura_Conteo_Celular;
import si.Captura_Resultados_Cultivos;
import si.Muestra_de_Cultivos;
import static si.Muestra_de_Cultivos.jTableMuestraCultivos;
import si.SI;


public class Modelo_ConteoCelular {
           public static ResultSet rs;
                    public static SI cc= new SI();
              public static   Statement sent;                             

    // ESTA TABLA ES LA QUE LLENA LAPRIMERA TABLA CON EL FIRLTRADO DE LOS ESTUDIIOS K SI PERTENECEN A LOS CULTIVOS
    public static void LlenarTablaCultivos(JTable tablaD) {
        Object[] columna = new Object[5];  
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaD.setModel(modeloT);  
        modeloT.addColumn("Estudios");
        modeloT.addColumn("Categoria");        
        try {
            Connection ca = cc.conexion();                                                                                                                                                                                                                                                                                                                         // and fecha = curdate()
         String sSQL = "select distinct descripcion_de_venta.nombre_producto, descripcion_de_venta.resultado, paquetes.categoria_estudios from venta INNER JOIN descripcion_de_venta on venta.id_venta = descripcion_de_venta.id_venta inner join paquetes on descripcion_de_venta.id_producto = paquetes.id_producto WHERE descripcion_de_venta.estado = 'Realizada' and descripcion_de_venta.resultado = 'POSITIVO' and descripcion_de_venta.id_venta = '"+Muestra_de_Cultivos.id_venta.getText()+"'";  
PreparedStatement ps = ca.prepareStatement(sSQL);
            try (ResultSet rs = ps.executeQuery(sSQL)) {
                while (rs.next()) {
                    columna[0] = rs.getString("nombre_producto");
                    columna[1] = rs.getString("categoria_estudios");
                    modeloT.addRow(columna);
                }
            }
            TableColumnModel columnModel = tablaD.getColumnModel();
            columnModel.getColumn(0).setMinWidth(340);
            columnModel.getColumn(0).setMaxWidth(340);
            columnModel.getColumn(1).setMinWidth(500);
            columnModel.getColumn(1).setMaxWidth(500);
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Advertencia", JOptionPane.PLAIN_MESSAGE);
        } finally {
            cc.getClose();
        }
    }

    public static void inserciondeResultadosdeCultivos(String nombre, String item, String bacteria, String porcentaje) {
        try {            
            Connection ca = cc.conexion();
            String sql = "INSERT INTO  resultados_cultivos(id_venta, nombre_medicina, resultados, estudio, nombre_bacteria, porcentaje)  VALUES (?,?,?,?,?,?)";
            PreparedStatement pst = ca.prepareCall(sql);
            pst.setInt(1,Controlador_capturar_resultados.id_a_actualizar_resultados);
            pst.setString(2, nombre);
            pst.setString(3, item);
            pst.setString(4, Captura_Bacterias.nombre_estudio.getText()+"-"+Captura_Resultados_Cultivos.txt_bacteria.getText());
            pst.setString(5, bacteria);
            pst.setString(6, porcentaje);
            
            int a = pst.executeUpdate();            
            if (a > 0) {  
            }
        } catch (Exception w) {
            JOptionPane.showMessageDialog(null, "insertarengastos" + w);
        }//fin del id del usuario para comprobar si hay o no elementos ya guardados
        finally {
            cc.getClose();
        }
    }

    public static void LlenarTabladeResultadosInterpretaciones(JTable tablaD) {
        Object[] columna = new Object[4];
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaD.setModel(modeloT);
        modeloT.addColumn("Antimicrobiano");
        modeloT.addColumn("Interpretacion");      
        try {
            
            Connection ca = cc.conexion(); 
                 String sSQL = "SELECT nombre_medicina, resultados FROM resultados_cultivos where nombre_bacteria = '"+Captura_Resultados_Cultivos.txt_bacteria.getText()+"' and estudio= '"+Captura_Resultados_Cultivos.nombre_estudio.getText()+"-"+Captura_Resultados_Cultivos.txt_bacteria.getText() +"' and id_venta ="+Captura_Resultados_Cultivos.id_venta.getText();
            PreparedStatement ps = ca.prepareStatement(sSQL);
            try (ResultSet rs = ps.executeQuery(sSQL)) {
                while (rs.next()) {
                    columna[0] = rs.getString("nombre_medicina");
                    columna[1] = rs.getString("resultados");                    
                    modeloT.addRow(columna);
                }
            }
            TableColumnModel columnModel = tablaD.getColumnModel();
            columnModel.getColumn(0).setMinWidth(300);
            columnModel.getColumn(0).setMaxWidth(300);
            columnModel.getColumn(1).setMinWidth(180);
            columnModel.getColumn(1).setMaxWidth(180);                      
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Advertencia", JOptionPane.PLAIN_MESSAGE);
        } finally {
            cc.getClose();
        }
    }

    
    public static void inserciondeResultadosDeConteoCelular(String conteo1, String conteo2){
        try {                        
            Connection ca = cc.conexion(); 
            String sql = "INSERT INTO resultado_conteocelular(dato_resultado, valor_conteo, id_venta) VALUES (?,?,?)";
            PreparedStatement pst = ca.prepareCall(sql);
            
            pst.setString(1, conteo1);
            pst.setString(2, conteo2);            
            pst.setInt(3,Controlador_capturar_resultados.id_a_actualizar_resultados);                                   
                        
            int a = pst.executeUpdate();            
            if (a > 0) {   
            }
        } catch (Exception w) {
            JOptionPane.showMessageDialog(null, "captura de Resultados De Conteo Celular" + w);
        }
        finally {
            cc.getClose();
        }        
    }
    
    
    public static void inserciondeObservacionesDeConteoCelular(String conteo1, String conteo2){
        try {                        
            Connection ca = cc.conexion(); 
            String sql = "INSERT INTO observaciones_conteocelular(tipo_observaciones, valor_obs, id_venta) VALUES (?,?,?)";
            PreparedStatement pst = ca.prepareCall(sql);
            
            pst.setString(1, conteo1);
            pst.setString(2, conteo2);            
            pst.setInt(3,Controlador_capturar_resultados.id_a_actualizar_resultados);                                   
                        
            int a = pst.executeUpdate();            
            if (a > 0) {   
            }
        } catch (Exception w) {
            JOptionPane.showMessageDialog(null, "captura de Resultados De Conteo Celular" + w);
        }
        finally {
            cc.getClose();
        }        
    }
    
    public static void LlenarTablaConteo(JTable tablaD) {
          Object[] columna = new Object[2];
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaD.setModel(modeloT);       
        modeloT.addColumn("Celula");
        modeloT.addColumn("Valor (%)");        
        
        try {              
            Connection ca = cc.conexion(); 
            String sSQL = "SELECT dato_resultado, valor_conteo FROM resultado_conteocelular where id_venta ='"+Captura_Conteo_Celular.id_venta.getText()+"'";
            PreparedStatement ps = ca.prepareStatement(sSQL);
            try (ResultSet rs = ps.executeQuery(sSQL)) {
                while (rs.next()) {
                    columna[0] = rs.getString("dato_resultado");
                    columna[1] = rs.getString("valor_conteo");                    
                    modeloT.addRow(columna);
                }
            }
            TableColumnModel columnModel = tablaD.getColumnModel();
            columnModel.getColumn(0).setMinWidth(240);
            columnModel.getColumn(0).setMaxWidth(240);
            columnModel.getColumn(1).setMinWidth(150);
            columnModel.getColumn(1).setMaxWidth(150);           
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "LlenarTabladeBacterias", JOptionPane.PLAIN_MESSAGE);
        } finally {
            cc.getClose();
        }        
    }
    

    public static void LlenarTabladeObservaciones(JTable tablaD) {
        Object[] columna = new Object[2];
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaD.setModel(modeloT);       
        modeloT.addColumn("Tipo");
        modeloT.addColumn("Observaciones");
        try {              
            Connection ca = cc.conexion(); 
            String sSQL = "SELECT tipo_observaciones, valor_obs FROM observaciones_conteocelular where id_venta ='"+Captura_Conteo_Celular.id_venta.getText()+"'";
            PreparedStatement ps = ca.prepareStatement(sSQL);
            try (ResultSet rs = ps.executeQuery(sSQL)) {
                while (rs.next()) {
                    columna[0] = rs.getString("tipo_observaciones");
                    columna[1] = rs.getString("valor_obs");                    
                    modeloT.addRow(columna);
                }
            }
            TableColumnModel columnModel = tablaD.getColumnModel();
            columnModel.getColumn(0).setMinWidth(170);
            columnModel.getColumn(0).setMaxWidth(170);
            columnModel.getColumn(1).setMinWidth(600);
            columnModel.getColumn(1).setMaxWidth(600);                       
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "LlenarTabladeBacterias", JOptionPane.PLAIN_MESSAGE);
        } finally {
            cc.getClose();
        }
    }
  
}
