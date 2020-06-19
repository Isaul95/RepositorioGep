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
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import si.Registro_paquete;
import si.Registro_producto;
import si.SI;
import si.nucleo;
/**
 *
 * @author aleks
 */
public class Modelo_registro_paquete extends Controlador_registro_paquete{
    public static SI cc= new SI();
        public static Statement sent;  
  public static ResultSet rs;    
  public static PreparedStatement ps;
    public static ArrayList<String> llenarcombodepaquetes(){// este metodo obtiene los tipo_producto disponibles en la base de datos actualmente
        Registro_paquete.paquetes.removeAllItems();//Ésta linea es importante ya que cada vez que se llama este metodo se eliminan los item que previamente se cargaron en la llamada anterior, ESTO PARA QUE NO SE VUELVAN AGREGAR LOS MISMOS ITEMS, MÁS DE 1 VEZ
         lista_llenado_categoria_estudios.clear();
        try{Connection ca= cc.conexion();
            sent = (Statement)ca.createStatement();
            rs= sent.executeQuery("select DISTINCT nombre_producto from paquetes where venta in ('PAQUETES')");
            while(rs.next()){
                lista_llenado_categoria_estudios.add(rs.getString("nombre_producto"));
            }
             for(int a=0; a<lista_llenado_categoria_estudios.size(); a++){
            Registro_paquete.paquetes.addItem(lista_llenado_categoria_estudios.get(a));
        }
        }catch(Exception e){
            System.err.print(e);
        }
        return lista_llenado_categoria_estudios;
    }
    public static ArrayList<String> llenarcombodepaquetessinproductos(){// este metodo obtiene los tipo_producto disponibles en la base de datos actualmente
        Registro_paquete.paquetes_sinproducto.removeAllItems();//Ésta linea es importante ya que cada vez que se llama este metodo se eliminan los item que previamente se cargaron en la llamada anterior, ESTO PARA QUE NO SE VUELVAN AGREGAR LOS MISMOS ITEMS, MÁS DE 1 VEZ
         lista_llenado_categoria_estudios.clear();
        try{Connection ca= cc.conexion();
            sent = (Statement)ca.createStatement();
            rs= sent.executeQuery("select nombre_paquete from paquetes_id where analisis is null");
            while(rs.next()){
                lista_llenado_categoria_estudios.add(rs.getString("nombre_paquete"));
            }
             for(int a=0; a<lista_llenado_categoria_estudios.size(); a++){
            Registro_paquete.paquetes_sinproducto.addItem(lista_llenado_categoria_estudios.get(a));
        }
        }catch(Exception e){
            System.err.print(e);
        }
        return lista_llenado_categoria_estudios;
    }
    public static void registrar_producto(String categoria){
        try{Connection ca= cc.conexion();
                String sql1 = "INSERT INTO paquetes (nombre_producto,categoria_estudios,unidades,valordereferencia,precio, venta)  VALUES (?,?,?,?,?,?)";
                PreparedStatement pst1 = ca.prepareCall(sql1);
                pst1.setString(1,Registro_producto.estudio.getText().toUpperCase());
                pst1.setString(2,categoria.toUpperCase());
                pst1.setString(3,Registro_producto.unidades.getText().toUpperCase());
                pst1.setString(4,Registro_producto.valor_referencia.getText().toUpperCase());
                pst1.setFloat(5, Float.parseFloat(String.valueOf(Registro_producto.precio.getText())));
                pst1.setString(6,Registro_producto.tipoventa.getSelectedItem().toString());
                int a=pst1.executeUpdate();
                if(a>0){
                    JOptionPane.showMessageDialog(null,"PRODUCTO GUARDADO CORRECTAMENTE","EXITO",JOptionPane.INFORMATION_MESSAGE);
                    if(categoria.equalsIgnoreCase("PAQUETE ANÁLISIS")){
                        registrar_paquete(Registro_producto.estudio.getText().toUpperCase());
                    }
                }
            } catch(SQLException e)  {
                //JOptionPane.showMessageDialog(null,"ERROR DE DATOS");
                JOptionPane.showMessageDialog(null, "Error registrar_producto" + e.getMessage());
            }
        catch(NumberFormatException nE){
            JOptionPane.showMessageDialog(null, "Verifique que ingresa ccorrectamente los valores en donde corresponden");
        }
    }
     public static void registrar_paquete(String PAQUETE){
        try{Connection ca= cc.conexion();
                String sql1 = "INSERT INTO paquetes_id (nombre_paquete)  VALUES (?)";
                PreparedStatement pst1 = ca.prepareCall(sql1);
                pst1.setString(1,PAQUETE.toUpperCase());
                int a=pst1.executeUpdate();
                if(a>0){
                    JOptionPane.showMessageDialog(null,"PAQUETE GUARDADO GUARDADO CORRECTAMENTE","EXITO",JOptionPane.INFORMATION_MESSAGE);  
                }
            } catch(SQLException e)  {
                //JOptionPane.showMessageDialog(null,"ERROR DE DATOS");
                JOptionPane.showMessageDialog(null, "Error registrar_paquete" + e.getMessage());
            }
        catch(NumberFormatException nE){
            JOptionPane.showMessageDialog(null, "Verifique que ingresa ccorrectamente los valores en donde corresponden");
        }
    }
     
       public static void mostrartodoslosproductosdelpaquete(String paquete) {
        Registro_paquete.Tablepaquetes.setVisible(true);    //hace visible la tabla de proveedores 
        DefaultTableModel modelo = new DefaultTableModel(); // Se crea un objeto para agregar los nombres de las columnas a la tabla
        modelo.addColumn("");
        modelo.addColumn("Nombre");
         modelo.addColumn("Unidades");
        modelo.addColumn("Valor");
        Registro_paquete.Tablepaquetes.setModel(modelo);  // Ya una vez asignado todos los nombres se le envia el objeto a la tabla proveedores
        Object[] datos = new Object[4];     //Un arreglo con la cantidad de nombres en las columnas
        try {
            Connection ca = cc.conexion();
            sent = ca.createStatement();
            rs = sent.executeQuery("select id_producto,nombre_producto, unidades, valordereferencia from paquetes where venta in ('SOLO EN PAQUETE')  AND  categoria_estudios = '"+paquete+"' "); // se ejecuta la sentencia dentro del parentesis
            while (rs.next()) {
                datos[0] = rs.getInt(1);
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                modelo.addRow(datos); //se asigna el arreglo  entero a todo el objeto llamado modelo  
            }
            Registro_paquete.Tablepaquetes.setModel(modelo); // Se vuelve a enviar nuevamente el objeto modelo a la tabla  
            //PARA AJUSTAR EL ANCHO DE LAS TABLAS
            TableColumnModel columnModel = Registro_paquete.Tablepaquetes.getColumnModel();
            //columnModel.getColumn(0).setPreferredWidth(250);
             //   columnModel.getColumn(1).setPreferredWidth(5);
  columnModel.getColumn(0).setMaxWidth(10);
    columnModel.getColumn(0).setMinWidth(10);
    columnModel.getColumn(1).setMaxWidth(450);
    columnModel.getColumn(1).setMinWidth(450);
    columnModel.getColumn(2).setMaxWidth(100);
    columnModel.getColumn(2).setMinWidth(100);
    columnModel.getColumn(3).setMaxWidth(800);
    columnModel.getColumn(3).setMinWidth(800);
        } catch (SQLException ex) {
          
            JOptionPane.showMessageDialog(null, "No se pudo mostrar ningun dato porque tu consulta está mal");
        } finally {
            cc.getClose();
        }
    }
       public static String  analisis_ya_registrados(){
      String resultado="";      
      try{ Connection ca= cc.conexion();// CUENTA EL TODAL DE CUANTAS VENTAS SE REALIZARON
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select analisis from paquetes_id where analisis is not null");
                                            while(rs.next()){
                                                      resultado =resultado+rs.getString(1)+",";
                                                      }System.out.println("CONCATENCAICON "+resultado.substring(0,resultado.length()-1));
                                                      }
                                                     //fin del try-precio del producto
                                                      catch (Exception e){
                                                           JOptionPane.showMessageDialog(null, "ERROR EN METODO: conteodeventasrealizadasdehoy: "+e.getMessage(),"DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
                                                      }// fin del precio-catch del producto
        finally{
                  cc.getClose();
             }
  return resultado.substring(0,resultado.length()-1);  
  }
       public static void mostrartodoslosanalisis_sinpaquete(String ids_sinanalisis) {
        Registro_paquete.Tablepaquetessinanalisis.setVisible(true);    //hace visible la tabla de proveedores 
        DefaultTableModel modelo = new DefaultTableModel(); // Se crea un objeto para agregar los nombres de las columnas a la tabla
        modelo.addColumn("");
        modelo.addColumn("Nombre");
         modelo.addColumn("Unidades");
        modelo.addColumn("Valor");
        Registro_paquete.Tablepaquetessinanalisis.setModel(modelo);  // Ya una vez asignado todos los nombres se le envia el objeto a la tabla proveedores
        Object[] datos = new Object[4];     //Un arreglo con la cantidad de nombres en las columnas
        try {
            Connection ca = cc.conexion();
            sent = ca.createStatement();
            rs = sent.executeQuery("select id_producto,nombre_producto, unidades, valordereferencia from paquetes where venta in ('SOLO EN PAQUETE')  AND  id_producto not in  ("+ids_sinanalisis+") "); // se ejecuta la sentencia dentro del parentesis
            while (rs.next()) {
                datos[0] = rs.getInt(1); 
                datos[1] = rs.getString(2);
                datos[2] = rs.getString(3);
                datos[3] = rs.getString(4);
                modelo.addRow(datos); //se asigna el arreglo  entero a todo el objeto llamado modelo  
            }
            Registro_paquete.Tablepaquetessinanalisis.setModel(modelo); // Se vuelve a enviar nuevamente el objeto modelo a la tabla  
            //PARA AJUSTAR EL ANCHO DE LAS TABLAS
            TableColumnModel columnModel = Registro_paquete.Tablepaquetessinanalisis.getColumnModel();
            //columnModel.getColumn(0).setPreferredWidth(250);
             //   columnModel.getColumn(1).setPreferredWidth(5);
  columnModel.getColumn(0).setMaxWidth(10);
    columnModel.getColumn(0).setMinWidth(10);
    columnModel.getColumn(1).setMaxWidth(450);
    columnModel.getColumn(1).setMinWidth(450);
    columnModel.getColumn(2).setMaxWidth(100);
    columnModel.getColumn(2).setMinWidth(100);
    columnModel.getColumn(3).setMaxWidth(800);
    columnModel.getColumn(3).setMinWidth(800);
        } catch (SQLException ex) {
          
            JOptionPane.showMessageDialog(null, "No se pudo mostrar ningun dato porque tu consulta está mal, mostrartodoslosanalisis_sinpaquete");
        } finally {
            cc.getClose();
        }
    }
       public static void agregar_los_id_al_paquete(String ids){
         try{Connection ca= cc.conexion();   // ESTE ES PARA EL UPDATE
                 PreparedStatement ps = ca.prepareStatement ("UPDATE paquetes_id SET analisis = '"+ids+"' WHERE nombre_paquete='"+Registro_paquete.paquetes_sinproducto.getSelectedItem().toString()+"' ");
               int a=  ps.executeUpdate();
               if(a>0){
                    JOptionPane.showMessageDialog(null, " Productos agregados al paquete");
                  Modelo_registro_paquete.llenarcombodepaquetessinproductos();
        Modelo_registro_paquete.mostrartodoslosanalisis_sinpaquete(Modelo_registro_paquete.analisis_ya_registrados());
                    }else{
                   JOptionPane.showMessageDialog(null, " Los productos no se pudieron agregar, verifique que no exista un paquete con el mismo nombre");
               }
        }//fin del id del usuario//fin del id del usuario
                 catch(Exception w){
               JOptionPane.showMessageDialog(null, "Error, comprobar_registro UPDATE"+w.getMessage(),"HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE);                  
                      }finally{
                  cc.getClose();
             }//fin del id del usuario
}
}
