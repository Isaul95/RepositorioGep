/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;
import Controladores.Controlador_registro_paquete;
import Controladores.Controlador_registro_producto;
import Controladores.Controladorventa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import si.Crud_productos;
import si.SI;
/**
 *
 * @author aleks
 */
public class Modelo_crud_productos extends Controlador_registro_paquete{
    public static SI cc= new SI();
        public static Statement sent;  
  public static ResultSet rs;    
  public static PreparedStatement ps;
    public static ArrayList<String> llenarcombodepaquetes(){// este metodo obtiene los tipo_producto disponibles en la base de datos actualmente
        Crud_productos.paquetes.removeAllItems();//Ésta linea es importante ya que cada vez que se llama este metodo se eliminan los item que previamente se cargaron en la llamada anterior, ESTO PARA QUE NO SE VUELVAN AGREGAR LOS MISMOS ITEMS, MÁS DE 1 VEZ
         lista_llenado_categoria_estudios.clear();
        try{Connection ca= cc.conexion();
            sent = (Statement)ca.createStatement();
            rs= sent.executeQuery("select DISTINCT venta from paquetes where venta not in ('SOLO EN PAQUETE')");
            while(rs.next()){
                lista_llenado_categoria_estudios.add(rs.getString("venta"));
            }
             for(int a=0; a<lista_llenado_categoria_estudios.size(); a++){
            Crud_productos.paquetes.addItem(lista_llenado_categoria_estudios.get(a));
        }
        }catch(Exception e){
            System.err.print(e);
        }
        return lista_llenado_categoria_estudios;
    }
     
       public static void mostrartodoslosproductosdelpaquete(String paquete) {
        Crud_productos.Jtable_crud_productos.setVisible(true);    //hace visible la tabla de proveedores 
        DefaultTableModel modelo = new DefaultTableModel(); // Se crea un objeto para agregar los nombres de las columnas a la tabla
        modelo.addColumn("");
        modelo.addColumn("Nombre");
        modelo.addColumn("Categoria");
         modelo.addColumn("Unidades");
        modelo.addColumn("Valor");
        modelo.addColumn("Precio");
        Crud_productos.Jtable_crud_productos.setModel(modelo);  // Ya una vez asignado todos los nombres se le envia el objeto a la tabla proveedores
        Object[] datos = new Object[6];     //Un arreglo con la cantidad de nombres en las columnas
        try {
            Connection ca = cc.conexion();
            sent = ca.createStatement();
            rs = sent.executeQuery("select id_producto,nombre_producto, categoria_estudios, unidades, valordereferencia, precio from paquetes where venta  = '"+paquete+"' "); // se ejecuta la sentencia dentro del parentesis
            while (rs.next()) {
                datos[0] = rs.getInt(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                datos[4] = rs.getString(5);
                datos[5] = rs.getFloat(6);
                modelo.addRow(datos); //se asigna el arreglo  entero a todo el objeto llamado modelo  
            }
            Crud_productos.Jtable_crud_productos.setModel(modelo); // Se vuelve a enviar nuevamente el objeto modelo a la tabla  
            //PARA AJUSTAR EL ANCHO DE LAS TABLAS
            TableColumnModel columnModel = Crud_productos.Jtable_crud_productos.getColumnModel();
  columnModel.getColumn(0).setMaxWidth(10);
    columnModel.getColumn(0).setMinWidth(10);
    columnModel.getColumn(1).setMaxWidth(350);
    columnModel.getColumn(1).setMinWidth(450);
    columnModel.getColumn(2).setMaxWidth(300);
    columnModel.getColumn(2).setMinWidth(300);
    columnModel.getColumn(3).setMaxWidth(100);
    columnModel.getColumn(3).setMinWidth(100);
    columnModel.getColumn(4).setMaxWidth(300);
    columnModel.getColumn(4).setMinWidth(300);
    columnModel.getColumn(5).setMaxWidth(200);
    columnModel.getColumn(5).setMinWidth(200);
    
    modelo.addTableModelListener(new TableModelListener(){
                @Override
                public void tableChanged(TableModelEvent e) {
                    int fila =Crud_productos.Jtable_crud_productos.getSelectedRow();
                    int col =Crud_productos.Jtable_crud_productos.getSelectedColumn();            
                    int id_producto = Integer.valueOf(Crud_productos.Jtable_crud_productos.getValueAt(fila, 0).toString());
                    if(e.getType() == TableModelEvent.UPDATE){
                        if(fila>=0){// CUANDO UNA CELDA SE SELECCIONO        
                            String nombre_producto = modelo.getValueAt(fila, 1).toString();
                            String categoria_estudios = modelo.getValueAt(fila, 2).toString();
                            String unidades = modelo.getValueAt(fila, 3).toString();
                            String valor_de_referencia = modelo.getValueAt(fila, 4).toString();
                            Float precio = Float.parseFloat(modelo.getValueAt(fila, 5).toString());
                            
                              String sql = "UPDATE paquetes SET nombre_producto='"+nombre_producto+"',categoria_estudios = '"+categoria_estudios+"',unidades = '"+unidades+"',valordereferencia = '"+valor_de_referencia+"',precio = '"+precio+"' WHERE id_producto="+id_producto;            
              PreparedStatement pst;
                          try{Connection ca= cc.conexion();
                               pst = ca.prepareStatement(sql);
                               int rows = pst.executeUpdate();
                          if(rows>=1){
                             System.out.println("Se actualiza el id_producto: "+id_producto);
                          }
                         } catch (SQLException ex) {
                               JOptionPane.showMessageDialog(null, "ERROR EN METODO: tableChanged"+ex.getMessage(),"DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
                           }finally{cc.getClose();}
      
                             }                             
                    }
   }

            });
        } catch (SQLException ex) {
          
            JOptionPane.showMessageDialog(null, "No se pudo mostrar ningun dato porque tu consulta está mal");
        } finally {
            cc.getClose();
        }
    }
   
 
  
    
       }

