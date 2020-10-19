/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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

/**
 *
 * @author Alexis
 */
public class Modelogastos {
           public static ResultSet rs;
                    public static SI cc= new SI();
              public static   Statement sent;  
    public static void LlenarTabla(JTable tablaD){ // recibe como parametro 
         Object[] columna = new Object[5];  //crear un obj con el nombre de colunna
           
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  tablaD.setModel(modeloT);  // add modelo ala tabla 
        modeloT.addColumn("Tipo"); 
        modeloT.addColumn("Total");
        modeloT.addColumn("Fecha");             
     try { Connection ca= cc.conexion(); // CONEXION DB 
         String sSQL = "SELECT `tipo`,`fecha`,`total`,`nombre` FROM `egreso` INNER JOIN user WHERE egreso.`usuario` = user.id_usuario order by fecha desc";
     PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                columna[0] = rs.getString("tipo");
                columna[1] = "$"+rs.getString("total");
                columna[2] = rs.getString("fecha");
                modeloT.addRow(columna);
            }
        }  
        TableColumnModel columnModel =   tablaD.getColumnModel();
       
      //  columnModel.getColumn(1).setPreferredWidth(10);   
       // columnModel.getColumn(2).setPreferredWidth(30);
           columnModel.getColumn(0).setMinWidth(340);
    columnModel.getColumn(0).setMaxWidth(340);
    columnModel.getColumn(1).setMinWidth(200);
    columnModel.getColumn(1).setMaxWidth(200);
     columnModel.getColumn(2).setMinWidth(200);
    columnModel.getColumn(2).setMaxWidth(200);

    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Advertencia", JOptionPane.PLAIN_MESSAGE);    
    }finally{cc.getClose();}
}
public static void mostrartodoslosproductosenexistenciasporbusqueda(String textobusqueda){
            Gastos.jTableGastos.setVisible(true);    //hace visible la tabla de proveedores 
              DefaultTableModel modelo = new DefaultTableModel(); // Se crea un objeto para agregar los nombres de las columnas a la tabla
        modelo.addColumn("Tipo"); 
           modelo.addColumn("Total");
        modelo.addColumn("Fecha");
        
     Gastos.jTableGastos.setModel(modelo);  // Ya una vez asignado todos los nombres se le envia el objeto a la tabla proveedores
    String []datos = new String[4];     //Un arreglo con la cantidad de nombres en las columnas
    try {Connection ca= cc.conexion();
             sent = ca.createStatement();   
                       if(textobusqueda.equals("")){
                          rs= sent.executeQuery("SELECT * FROM `egreso` order by fecha desc"); // se ejecuta la sentencia dentro del parentesis
                       }
                       else if(!textobusqueda.equals("")&&!textobusqueda.equalsIgnoreCase("hoy")){
                           rs= sent.executeQuery("SELECT * FROM egreso where tipo LIKE '%" +textobusqueda+"%' or total LIKE '%" +textobusqueda+"%'  or fecha LIKE '%" +textobusqueda+"%'  or cantidad LIKE '%" +textobusqueda+"%' order by fecha desc" ); // se ejecuta la sentencia dentro del parentesis
                       }else if(textobusqueda.equalsIgnoreCase("hoy")){
                             rs= sent.executeQuery("SELECT * FROM `egreso` where fecha = '"+Controladorventa.fecha()+"' order by fecha desc"); // se ejecuta la sentencia dentro del parentesis
                       }
             while(rs.next()){        
            datos[0]=rs.getString(3);
            datos[1]="$"+rs.getString(5);
            datos[2]=rs.getString(4);
            modelo.addRow(datos); //se asigna el arreglo  entero a todo el objeto llamado modelo  
            }
           Gastos.jTableGastos.setModel(modelo); // Se vuelve a enviar nuevamente el objeto modelo a la tabla
           TableColumnModel columnModel =   Gastos.jTableGastos.getColumnModel();
        //    columnModel.getColumn(1).setPreferredWidth(10);   
       // columnModel.getColumn(2).setPreferredWidth(30);
           columnModel.getColumn(0).setMinWidth(340);
    columnModel.getColumn(0).setMaxWidth(340);
     columnModel.getColumn(1).setMinWidth(200);
    columnModel.getColumn(1).setMaxWidth(200);
     columnModel.getColumn(2).setMinWidth(200);
    columnModel.getColumn(2).setMaxWidth(200);
        } 
    catch (SQLException ex) {
            Logger.getLogger(nucleo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo mostrar ningun dato porque tu consulta está mal");
        } finally{cc.getClose();}
    }
public static void insertarengastos(String nombre, float monto){
        try{ Connection ca= cc.conexion(); // CONEXION DB // el id del usuario para obtener el id del usuario y comprobar si hay o no algun registro
   String sql = "INSERT INTO  egreso(cantidad, tipo, fecha, total, usuario)  VALUES (?,?,?,?,?)";
                         PreparedStatement pst = ca.prepareCall(sql); 
                         pst.setInt(1,0);
                         pst.setString(2,nombre);
                         pst.setString(3,Controladorventa.fecha());
                         pst.setFloat(4,monto);
                         pst.setInt(5,Controladorgastos.id_usuario);
                         int a=pst.executeUpdate();
                         if(a>0){   // UPDATE `productoexternoblanca` SET `pieza`=0;
                  }                                                 
      }catch(Exception w){
                     JOptionPane.showMessageDialog(null,"insertarengastos"+w);
      }//fin del id del usuario para comprobar si hay o no elementos ya guardados
        finally{cc.getClose();}
    }
public static void insertarventacondescuentoengastos(String nombre, float monto, int idventa){
        try{ Connection ca= cc.conexion(); // CONEXION DB // el id del usuario para obtener el id del usuario y comprobar si hay o no algun registro
   String sql = "INSERT INTO  egreso(cantidad, tipo, fecha, total, usuario, id_venta)  VALUES (?,?,?,?,?,?)";
                         PreparedStatement pst = ca.prepareCall(sql); 
                         pst.setInt(1,0);
                         pst.setString(2,nombre.toUpperCase());
                         pst.setString(3,Controladorventa.fecha());
                         pst.setFloat(4,monto);
                         pst.setInt(5,Controladorgastos.id_usuario);
                          pst.setInt(6,idventa);
                         int a=pst.executeUpdate();
                         if(a>0){   // UPDATE `productoexternoblanca` SET `pieza`=0;
                  }                                                 
      }catch(Exception w){Controladorventa.sepuedeagregarpaciente=false;
                     JOptionPane.showMessageDialog(null,"insertarengastos"+w);
      }//fin del id del usuario para comprobar si hay o no elementos ya guardados
        finally{cc.getClose();}
    }

public static void insertarpagocomoabono(String nombre, float monto, int idventa){
        try{ Connection ca= cc.conexion(); // CONEXION DB // el id del usuario para obtener el id del usuario y comprobar si hay o no algun registro
   String sql = "INSERT INTO  pagos(nombre, monto, id_venta, fecha,hora)  VALUES (?,?,?,?,?)";
                         PreparedStatement pst = ca.prepareCall(sql); 
                         pst.setString(1,nombre.toUpperCase());
                         pst.setFloat(2,monto);
                         pst.setInt(3,idventa);
                         pst.setString(4,Controladorventa.fecha());
                         pst.setString(5,nucleo.Reloj.getText());
                       
                         int a=pst.executeUpdate();
                         if(a>0){   // UPDATE `productoexternoblanca` SET `pieza`=0;
                  }                                                 
      }catch(Exception w){Controladorventa.sepuedeagregarpaciente=false;
                     JOptionPane.showMessageDialog(null,"insertarpagocomoabono"+w);
      }//fin del id del usuario para comprobar si hay o no elementos ya guardados
        finally{cc.getClose();}
    }

}
