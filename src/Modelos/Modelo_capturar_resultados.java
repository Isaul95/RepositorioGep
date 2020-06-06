/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Controladores.Controlador_capturar_resultados;
import Controladores.Controladorventa;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
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
         String sSQL = "select pacientes.nombre, pacientes.edad, pacientes.sexo, pacientes.fecha_nacimiento, venta.fecha_reporte, venta.hora, descripcion_de_venta.nombre_producto, descripcion_de_venta.resultado, pacientes.medico from venta INNER JOIN descripcion_de_venta on venta.id_venta = descripcion_de_venta.id_venta inner join pacientes on descripcion_de_venta.id_paciente = pacientes.id_paciente WHERE descripcion_de_venta.estado = 'Realizada' and descripcion_de_venta.id_venta ="+id_venta;
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
                 Capturar_resultados.medico.setText(rs.getString(9));//Se almacena el nombre del paciente
                
                modeloT.addRow(columna);
            }        TableColumnModel columnModel =  tablaD.getColumnModel();
   // columnModel.getColumn(0).setPreferredWidth(250); columnModel.getColumn(1).setPreferredWidth(50); 
    columnModel.getColumn(0).setMaxWidth(560);
    columnModel.getColumn(0).setMinWidth(560);
    columnModel.getColumn(1).setMaxWidth(220);
    columnModel.getColumn(1).setMinWidth(220);
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
                              //Modeloventa.id_y_precio_producto(valor); 
                              id_producto(valor,id_venta);
                              System.out.println("ID :"+id_producto+" VENTA "+id_venta);
                              String sql = "UPDATE descripcion_de_venta SET resultado='"+resultado_del_estudio+"' WHERE id_producto="+id_producto+" AND id_venta="+id_venta;            
                             PreparedStatement pst;
                          try{Connection ca= cc.conexion();
                               pst = ca.prepareStatement(sql);
                               int rows = pst.executeUpdate();
                               if(rows>0){
                                  activar_boton_pdf(id_venta);
                                   if(Controlador_capturar_resultados.respuesta_para_activar_el_pdf==0){
                                       System.out.println("Modelo if: "+Controlador_capturar_resultados.respuesta_para_activar_el_pdf);
                                       Capturar_resultados.genetrar_Pdf.setEnabled(true);
                                   }
                                   else{   
                                       System.out.println("Modelo else: "+Controlador_capturar_resultados.respuesta_para_activar_el_pdf);
Capturar_resultados.genetrar_Pdf.setEnabled(false);}
                               }else{  }
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
    public static void subir_archivo(String nombre_archivo, int id_venta){
             String file = "C:\\reportes\\"+nombre_archivo;
     id_paciente(id_venta);
             ///////////////////////////////////////////////////////////////////////////////////////////////////
         try{
              FileInputStream input = null;
            input = new FileInputStream(new File(file));
            
             Connection ca= cc.conexion(); //la insersion a la tabla ventas
                String sql = "INSERT INTO ARCHIVOS (nombre_archivo,archivo,id_paciente,estado_archivo,fecha)  VALUES (?,?,?,'Realizada',?)";
                PreparedStatement pst = ca.prepareCall(sql); //hasta aqui vamos
               pst.setString(1,nombre_archivo);
                pst.setBinaryStream(2, input);
                pst.setInt(3, Controlador_capturar_resultados.id_paciente);
                pst.setString(4,Controladorventa.fecha());
                int a=pst.executeUpdate();
                if(a>0){
                System.out.println("Archivo insertado");
                 input.close();
                }else{//CUANDO NO SE PUDO INSERTAR
                System.out.println("Archivo no insertado");  
                }
            }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                    JOptionPane.showMessageDialog(null, "Error, comprobar_registro INSERT INTO"+e.getMessage(),"HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE);                  
              
            } catch (FileNotFoundException ex) {
            Logger.getLogger(Modelo_capturar_resultados.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(Modelo_capturar_resultados.class.getName()).log(Level.SEVERE, null, ex);
        }
            finally{
             cc.getClose();
             }
    }
    public static void id_paciente(int id_venta){
            try{ Connection ca= cc.conexion();// CUENTA EL TODAL DE CUANTAS VENTAS SE REALIZARON
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select pacientes.id_paciente from venta INNER join descripcion_de_venta on venta.id_venta = descripcion_de_venta.id_venta inner join pacientes on descripcion_de_venta.id_paciente = pacientes.id_paciente WHERE venta.id_venta = '"+id_venta+"' ");
                                            if(rs.next()){
                                                      Controlador_capturar_resultados.id_paciente =Integer.parseInt(String.valueOf(rs.getInt("pacientes.id_paciente")));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                           JOptionPane.showMessageDialog(null, "ERROR EN METODO: conteodeventasrealizadasdehoy: "+e.getMessage(),"DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
                                                      }// fin del precio-catch del producto
        finally{
                  cc.getClose();
             }
    }
  public static int activar_boton_pdf(int id_venta){
try{ Connection ca= cc.conexion();// CUENTA EL TODAL DE CUANTAS VENTAS SE REALIZARON
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select count(*) from descripcion_de_venta dv WHERE  dv.id_venta = '"+id_venta+"' and dv.resultado in ('') and SUBSTRING(nombre_producto,1,7) not in ('PAQUETE')");
                                            if(rs.next()){
                                                      Controlador_capturar_resultados.respuesta_para_activar_el_pdf =Integer.parseInt(String.valueOf(rs.getInt("count(*)")));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                           JOptionPane.showMessageDialog(null, "ERROR EN METODO: activar_boton_pdf: "+e.getMessage(),"DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
                                                      }// fin del precio-catch del producto
        finally{
                  cc.getClose();
             }
      return Controlador_capturar_resultados.respuesta_para_activar_el_pdf;
  }
  public static int  cantidad_de_estudios(int id_venta){
      int resultado=0;      
      try{ Connection ca= cc.conexion();// CUENTA EL TODAL DE CUANTAS VENTAS SE REALIZARON
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select count(nombre_producto) from descripcion_de_venta  WHERE id_venta = '"+id_venta+"' and estado = 'Realizada' ");
                                            if(rs.next()){
                                                      resultado =rs.getInt(1);
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                           JOptionPane.showMessageDialog(null, "ERROR EN METODO: conteodeventasrealizadasdehoy: "+e.getMessage(),"DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
                                                      }// fin del precio-catch del producto
        finally{
                  cc.getClose();
             }
  return resultado;  
  }
  public static String  verificandopaquete(int id_venta){
      String resultado="";      
      try{ Connection ca= cc.conexion();// CUENTA EL TODAL DE CUANTAS VENTAS SE REALIZARON
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select nombre_producto from descripcion_de_venta  WHERE id_venta = '"+id_venta+"' and estado = 'Realizada' ");
                                            if(rs.next()){
                                                      resultado =rs.getString(1);
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                           JOptionPane.showMessageDialog(null, "ERROR EN METODO: conteodeventasrealizadasdehoy: "+e.getMessage(),"DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
                                                      }// fin del precio-catch del producto
        finally{
                  cc.getClose();
             }
  return resultado;  
  }
  public static void  obtener_id_paciente_nombre_credito_estado_fecha_para_el_paquete(int id_venta){
      String resultado="";      
      try{ Connection ca= cc.conexion();// CUENTA EL TODAL DE CUANTAS VENTAS SE REALIZARON
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select distinct id_paciente, nombre_credito, estado, fecha from descripcion_de_venta  WHERE id_venta = '"+id_venta+"' and estado = 'Realizada' ");
                                            if(rs.next()){
                                                id_paciente_para_el_paquete=rs.getInt(1);
                                                nombre_credito_para_el_paquete = rs.getString(2);
                                                estado_para_el_paquete = rs.getString(3);
                                                fecha_para_el_paquete=rs.getString(4);
                                                
                                                      resultado =rs.getString(1);
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                           JOptionPane.showMessageDialog(null, "ERROR EN METODO: conteodeventasrealizadasdehoy: "+e.getMessage(),"DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
                                                      }// fin del precio-catch del producto
        finally{
                  cc.getClose();
             }
  }
   public static void  id_producto(String nombredepieza, int id_venta){
          try{ Connection ca= cc.conexion();//el id del producto
                                                      sent  =(Statement)ca.createStatement();
                                                      rs = sent.executeQuery("select * from descripcion_de_venta where nombre_producto= '"+nombredepieza+"' and id_venta ='"+id_venta+"'");
                                                      if(rs.next()){
                                                      id_producto =Short.parseShort(rs.getString("id_producto"));
                                                      }
                                                      }//fin del try - id del producto
                                                      catch (Exception e){
                                                            JOptionPane.showMessageDialog(null, "Error, id_producto","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
                                                      } finally{
                  cc.getClose();
             }//fin del id-catch del producto
    }
   public static boolean HAY_UN_PAQUETE_EN_LA_VENTA(int id_venta){
       boolean soy_verdad = false;
              try{ Connection ca= cc.conexion();//el id del producto
                                                      sent  =(Statement)ca.createStatement();
                                                      rs = sent.executeQuery("select nombre_producto  from  descripcion_de_venta where nombre_producto LIKE 'PAQUETE%' AND ID_VENTA ='"+id_venta+"'  AND ESTADO ='Realizada'");
                                                      if(rs.next()){
                                                          soy_verdad=true;
                                                
                                                      }else{
                                                          soy_verdad=false;
                                                      }
                                                      }//fin del try - id del producto
                                                      catch (Exception e){
                                                            JOptionPane.showMessageDialog(null, "Error, id_producto","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
                                                      } finally{
                  cc.getClose();
             }//fin del id-catch del producto
             return soy_verdad;
   }
}
