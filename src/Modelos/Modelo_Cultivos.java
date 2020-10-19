
package Modelos;
import Controladores.Controladorgastos;
import Controladores.Controladorventa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import si.Gastos;
import si.SI;
import si.nucleo;


public class Modelo_Cultivos {
           public static ResultSet rs;
                    public static SI cc= new SI();
              public static   Statement sent;                             
/*
public static void inserciondeResultadosdeCultivos(String nombre, float monto){
        try{ Connection ca= cc.conexion(); // CONEXION DB // el id del usuario para obtener el id del usuario y comprobar si hay o no algun registro
// String sql = "INSERT INTO  egreso(cantidad, tipo, fecha, total, usuario)  VALUES (?,?,?,?,?)";
   String sql = "INSERT INTO  resultados_cultivos(nombre_medicina, resultados)  VALUES (?,?)";
                         PreparedStatement pst = ca.prepareCall(sql); 
                         //pst.setInt(1,0);
                         pst.setString(1,nombre);
                       //  pst.setString(3,Controladorventa.fecha());
                         pst.setFloat(2,monto);
                         //pst.setInt(5,Controladorgastos.id_usuario);
                         int a=pst.executeUpdate();
                         if(a>0){   // UPDATE `productoexternoblanca` SET `pieza`=0;
                  }                                                 
      }catch(Exception w){
                     JOptionPane.showMessageDialog(null,"insertarengastos"+w);
      }//fin del id del usuario para comprobar si hay o no elementos ya guardados
        finally{cc.getClose();}
    }
 */

    // ESTA TABLA ES LA QUE LLENA LAPRIMERA TABLA CON EL FIRLTRADO DE LOS ESTUDIIOS K SI PERTENECEN A LOS CULTIVOS
    public static void LlenarTablaCultivos(JTable tablaD) {
        Object[] columna = new Object[5];  //crear un obj con el nombre de colunna           
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaD.setModel(modeloT);  // add modelo ala tabla 
        modeloT.addColumn("Estudios");
        modeloT.addColumn("Categoria");
        //modeloT.addColumn("Fecha");
        try {
            Connection ca = cc.conexion(); // CONEXION DB 
//            String sSQL = "SELECT `tipo`,`fecha`,`total`,`nombre` FROM `egreso` INNER JOIN user WHERE egreso.`usuario` = user.id_usuario order by fecha desc";
         String sSQL = "select descripcion_de_venta.nombre_producto, descripcion_de_venta.resultado, paquetes.categoria_estudios from venta INNER JOIN descripcion_de_venta on venta.id_venta = descripcion_de_venta.id_venta inner join paquetes on descripcion_de_venta.id_producto = paquetes.id_producto WHERE descripcion_de_venta.estado = 'Realizada' and descripcion_de_venta.resultado = '+'";  
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

    public static void inserciondeResultadosdeCultivos(String nombre, String item) {
        try {
            Connection ca = cc.conexion(); // CONEXION DB // el id del usuario para obtener el id del usuario y comprobar si hay o no algun registro
// String sql = "INSERT INTO  egreso(cantidad, tipo, fecha, total, usuario)  VALUES (?,?,?,?,?)";
            String sql = "INSERT INTO  resultados_cultivos(nombre_medicina, resultados)  VALUES (?,?)";
            PreparedStatement pst = ca.prepareCall(sql);
            //pst.setInt(1,0);
            pst.setString(1, nombre);
            //  pst.setString(3,Controladorventa.fecha());
            pst.setString(2, item);
            //pst.setInt(5,Controladorgastos.id_usuario);
            int a = pst.executeUpdate();
            if (a > 0) {   // UPDATE `productoexternoblanca` SET `pieza`=0;
            }
        } catch (Exception w) {
            JOptionPane.showMessageDialog(null, "insertarengastos" + w);
        }//fin del id del usuario para comprobar si hay o no elementos ya guardados
        finally {
            cc.getClose();
        }
    }

    public static void LlenarTabladeResultadosInterpretaciones(JTable tablaD) {
        Object[] columna = new Object[2];
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaD.setModel(modeloT);
        //modeloT.addColumn("Tipo"); 
        modeloT.addColumn("Antimicrobiano");
        modeloT.addColumn("Interpretacion");
        try {
            Connection ca = cc.conexion(); // CONEXION DB 
            String sSQL = "SELECT nombre_medicina, resultados FROM resultados_cultivos";
            PreparedStatement ps = ca.prepareStatement(sSQL);
            try (ResultSet rs = ps.executeQuery(sSQL)) {
                while (rs.next()) {
                    columna[0] = rs.getString("nombre_medicina");
                    columna[1] = rs.getString("resultados");
                    modeloT.addRow(columna);
                }
            }
            TableColumnModel columnModel = tablaD.getColumnModel();
            columnModel.getColumn(0).setMinWidth(440);
            columnModel.getColumn(0).setMaxWidth(440);
            columnModel.getColumn(1).setMinWidth(300);
            columnModel.getColumn(1).setMaxWidth(300);           
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e, "Advertencia", JOptionPane.PLAIN_MESSAGE);
        } finally {
            cc.getClose();
        }
    }
    
    
    
    
    
    
}
