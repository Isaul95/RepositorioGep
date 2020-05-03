/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;

import Controladores.Controlador_venta_a_credito;
import static Controladores.Controladorgastos.pass;
import static Controladores.Controladorgastos.pass2;
import static Controladores.Controladorgastos.pass3;
import Controladores.Controladorinventarioventas;
import Controladores.Controladorventa;
import static Controladores.Controladorventa.edad_paciente;
import static Controladores.Controladorventa.id_de_la_venta_incrementable;
import static Controladores.Controladorventa.id_paciente;
import static Controladores.Controladorventa.limpiardatospaciente;
import static Controladores.Controladorventa.medico;
import static Controladores.Controladorventa.nombre_paciente;
import static Controladores.Controladorventa.sexo_paciente;
import static Controladores.Controladorventa.solodosdecimales;
import static Controladores.Controladorventa.sumadeimportesenturno;
import static Controladores.Controladorventa.validarFormulariotexto_paciente;
import static Modelos.Modeloventa.llenar_datos_del_paciente_tras_completar_la_venta;
import static Modelos.Modeloventa.obtener_id_paciente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import si.Inventarioventas;
import si.SI;
import si.Ventas_a_credito;
import si.nucleo;

/**
 *
 * @author aleks
 */
public class Modelo_venta_a_credito {
       static SI cc= new SI();
   static Statement sent;  
static ResultSet rs;  
    public static void insertar_venta_a_credito(){
         float totalparaventaacredito = Float.parseFloat(nucleo.subtotal.getText());
  if(totalparaventaacredito!=0){//SI EL TOTAL NO ES VACIO
            try{Connection ca= cc.conexion();
     //ESTO VALIDA QUE EL TEXTO ESCRITO NO TENGA INCOHERENCIAS
                    int decision=JOptionPane.showConfirmDialog(null,"¿Desea continuar?","Estás por agregar una venta a credito",JOptionPane.CANCEL_OPTION);
                    if(decision==0){ //opción si
                        pass = Controladorventa.validarFormulario_paciente(nucleo.user_edad.getText());
                   pass2 = validarFormulariotexto_paciente(nucleo.user_nombre.getText());
                   pass3 = validarFormulariotexto_paciente(nucleo.medico.getText());
                try{
                     String fecha_paciente= Controladorventa.fecha_de_nacimiento_del_paciente();
          if (pass && pass2 &&pass3&&!fecha_paciente.equalsIgnoreCase("")) {
              //ESTO ES PARA VALIDAR QUE SE TENGAN TODOS LOS DATOS DEL CLIENTE
                        try{
                            Modeloventa.comprobar_venta_resagada();//579 - 605 verifica que no haya una venta cancelada
                            Controladorventa.tablaventaactiva=false;
 Modeloventa.id_max_de_venta();
                            PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET total='"+solodosdecimales.format(Float.parseFloat(nucleo.total.getText())).replace(",", ".")+"',subtotal='"+ solodosdecimales.format(Float.parseFloat(nucleo.subtotal.getText())).replace(",", ".")+"',descuento='"+ solodosdecimales.format(Float.parseFloat(nucleo.descuentocombo.getText())).replace(",", ".")+"',pago='"+solodosdecimales.format(Float.parseFloat(nucleo.monto.getText())).replace(",", ".")+"',cambio='"+solodosdecimales.format(Float.parseFloat(nucleo.subtotal.getText())-Float.parseFloat(nucleo.monto.getText())).replace(",", ".")+"',fecha_reporte='"+Controladorventa.fecha()+"',estado_venta='"+Controladorinventarioventas.creditopendiente+"'WHERE id_venta='"+id_de_la_venta_incrementable+"'");
                            ps.executeUpdate();
                          
 }catch(Exception ex){
                            JOptionPane.showMessageDialog(null, "Error en insertarventaacredito venta" + ex.getMessage());
                        }
                        try{        Modelogastos.insertarventacondescuentoengastos("Cdo "+nucleo.user_nombre.getText(), sumadeimportesenturno,id_de_la_venta_incrementable);
                    
                            Modeloventa.id_max_de_venta(); 
                            PreparedStatement ps2 = ca.prepareStatement ("UPDATE descripcion_de_venta SET estado= '"+Controladorinventarioventas.creditopendiente+"',nombre_credito='"+nucleo.user_nombre.getText()+"' WHERE id_venta='"+id_de_la_venta_incrementable+"'");
                            ps2.executeUpdate();
   Modeloventa.descripciondelosprouductosparaelticketdeventa(id_de_la_venta_incrementable,Controladorinventarioventas.creditopendiente);//DATOS PARA EL TICKET DE VENTA
   Modeloventa.descripciondelosprouductosparaelticketdeventa(id_de_la_venta_incrementable,Controladorinventarioventas.creditopendiente);//DATOS PARA EL TICKET DE VENTA
         Controladorventa.block_unlock=true;
                            Controladorventa.accionesdespuesderealizarcualquierventa();
                     }
                        catch(Exception ex){
                            JOptionPane.showMessageDialog(null, "Error en insertarventaacredito update desc" + ex.getMessage());
                        }
                        llenar_datos_del_paciente_tras_completar_la_venta();
          }else{//CUANDO NO SE LLENAN TODOS LOS DATOS DEL PACIENTE
                    JOptionPane.showMessageDialog(null,"Complete todos los datos del paciente", "ERROR", JOptionPane.ERROR_MESSAGE);
          }//CUANDO NO SE LLENAN TODOS LOS DATOS DEL PACIENTE
                }catch (NullPointerException NPE){//INICIO DEL CATCH
                              JOptionPane.showMessageDialog(null,"Debes de elegir la fecha de nacimiento del paciente", "ERROR", JOptionPane.ERROR_MESSAGE);
                  }//FIN DEL CATCH
                    } //CUANDO ACEPTA COMPLETAR LA VENTA CREDITO
                
            }catch(NullPointerException NP){}
            finally{cc.getClose();}
  }//SI EL TOTAL NO ES VACIO
        else{//CUANDO EL TOTAL ES VACIO
            JOptionPane.showMessageDialog(null, "Aún no hay productos para hacer una venta a credito");
        }//CUANDO EL TOTAL ES VACIO
    }
    
    public static void ventas_a_credito(){
       Object[] columna = new Object[8];  //crear un obj con el nombre de colunna
             DefaultTableModel modeloTE = new DefaultTableModel(); 
                  Ventas_a_credito.tabla_ventas_a_credito.setModel(modeloTE);  // add modelo ala tabla 
        modeloTE.addColumn("Venta");
        modeloTE.addColumn("Paciente");
        modeloTE.addColumn("Subtotal"); 
        modeloTE.addColumn("Descuento");
        modeloTE.addColumn("Total");
         modeloTE.addColumn("Pago/abono");
          modeloTE.addColumn("Restante");
        modeloTE.addColumn("Fecha");
    Ventas_a_credito.tabla_ventas_a_credito.setModel(modeloTE);  // add modelo ala tabla         
        try {  Connection ca= cc.conexion(); // CONEXION DB 
             String sSQL = "SELECT distinct venta.id_venta, pacientes.nombre,venta.subtotal, venta.descuento,venta.total, venta.pago, venta.cambio,venta.fecha_reporte FROM venta inner join descripcion_de_venta on venta.id_venta = descripcion_de_venta.id_venta inner join pacientes on descripcion_de_venta.id_paciente = pacientes.id_paciente WHERE venta.estado_venta in('Credito-pendiente') AND venta.fecha_reporte = '"+Controladorventa.fecha()+"' ";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
                columna[0] = rs.getInt(1);
                columna[1] = rs.getString(2);
                columna[2] = "$"+String.valueOf(rs.getFloat(3));
                 columna[3] = "$"+String.valueOf(rs.getFloat(4));
                columna[4] = "$"+String.valueOf(rs.getFloat(5));
                 columna[5] = "$"+String.valueOf(rs.getFloat(6));
                 columna[6] = "$"+String.valueOf(rs.getFloat(7));
                 columna[7] = rs.getString(8);
                 
                modeloTE.addRow(columna);
            }
          Ventas_a_credito.tabla_ventas_a_credito.setModel(modeloTE);  // add modelo ala tabla 
                             TableColumnModel columnModel =   Ventas_a_credito.tabla_ventas_a_credito.getColumnModel();
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
                             //columnModel.getColumn(5).setPreferredWidth(10);
                              columnModel.getColumn(5).setMaxWidth(100);
                               columnModel.getColumn(5).setMinWidth(100);
                                       
                                columnModel.getColumn(6).setMaxWidth(100);
                               columnModel.getColumn(6).setMinWidth(100);
                               
                                columnModel.getColumn(7).setMaxWidth(100);
                               columnModel.getColumn(7).setMinWidth(100);
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "ERROR EN METODO: llenartablaidventasconidrealizados","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
       }finally{
                  cc.getClose();
             }
}
    public static void consultarlosresultadosenlabusquedadenombres(String contexto){
    DefaultTableModel modelo = new DefaultTableModel(); // Se crea un objeto para agregar los nombres de las columnas a la tabla
       modelo.addColumn("Venta");
        modelo.addColumn("Paciente");
         modelo.addColumn("Subtotal");  
         modelo.addColumn("Descuento");    
         modelo.addColumn("Total");
          modelo.addColumn("Pago/abono");
          modelo.addColumn("Restante");
        modelo.addColumn("Fecha");
       Ventas_a_credito.tabla_ventas_a_credito.setModel(modelo);  // Ya una vez asignado todos los nombres se le envia el objeto a la tabla proveedores
        Object[] datos = new Object[8];     //Un arreglo con la cantidad de nombres en las columnas
        try {
            Connection ca = cc.conexion();
            sent = ca.createStatement();
           /* if(){ //Busqueda por el rango de fechas
                
            }else{ //Busqueda del dia actual
                
            }*/
            if (contexto.equals("")) {
                             String sSQL = "SELECT distinct venta.id_venta, pacientes.nombre,venta.subtotal, venta.descuento , venta.total, venta.pago, venta.cambio,venta.fecha_reporte FROM venta inner join descripcion_de_venta on venta.id_venta = descripcion_de_venta.id_venta inner join pacientes on descripcion_de_venta.id_paciente = pacientes.id_paciente WHERE venta.estado_venta in('Credito-pendiente') AND venta.fecha_reporte = '"+Controladorventa.fecha()+"' ";
                rs = sent.executeQuery(sSQL); // se ejecuta la sentencia dentro del parentesis
            } else {
                              String sSQLcontesto = "SELECT distinct venta.id_venta, pacientes.nombre,venta.subtotal, venta.descuento,venta.total , venta.pago, venta.cambio,venta.fecha_reporte FROM venta inner join descripcion_de_venta on venta.id_venta = descripcion_de_venta.id_venta inner join pacientes on descripcion_de_venta.id_paciente = pacientes.id_paciente WHERE venta.estado_venta in('Credito-pendiente') AND  pacientes.nombre LIKE '%" + contexto + "%'";
               
                rs = sent.executeQuery(sSQLcontesto); // se ejecuta la sentencia dentro del parentesis
            }
            while (rs.next()) {
                 datos[0] = rs.getInt(1);
                datos[1] = rs.getString(2);
                datos[2] = "$"+String.valueOf(rs.getFloat(3));
                datos[3]  = "$"+String.valueOf(rs.getFloat(4));
                 datos[4]  = "$"+String.valueOf(rs.getFloat(5));
                 datos[5] = "$"+String.valueOf(rs.getFloat(6));
                 datos[6] = "$"+String.valueOf(rs.getFloat(7));
                 datos[7] = rs.getString(8);
                 
                modelo.addRow(datos); //se asigna el arreglo  entero a todo el objeto llamado modelo  
            }
             Ventas_a_credito.tabla_ventas_a_credito.setModel(modelo);  // add modelo ala tabla 
                             TableColumnModel columnModel =    Ventas_a_credito.tabla_ventas_a_credito.getColumnModel();
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
                             //columnModel.getColumn(5).setPreferredWidth(10);
                             
                               columnModel.getColumn(5).setMaxWidth(100);
                               columnModel.getColumn(5).setMinWidth(100);
                                       
                                columnModel.getColumn(6).setMaxWidth(100);
                               columnModel.getColumn(6).setMinWidth(100);
                               
                                columnModel.getColumn(7).setMaxWidth(100);
                               columnModel.getColumn(7).setMinWidth(100);
       } catch (SQLException ex) {
            Logger.getLogger(nucleo.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null, "No se pudo mostrar  porque tu consulta está mal");
        } finally {
            cc.getClose();
        }
}
    
    public static void cerrar_o_abonar_venta_a_credito(float total, float pago_abono, float cambio_pendiente, int id_venta){
         DecimalFormat solodosdecimales = new DecimalFormat("#.##");
    try{
            boolean pass2 = Controladorventa.validarFormulario(Controlador_venta_a_credito.pagodeventacredito= JOptionPane.showInputDialog(null,"Escriba el monto de pago","Pagando venta a credito", JOptionPane.INFORMATION_MESSAGE));
            if(pass2){//ESTO VALIDA QUE EL TEXTO ESCRITO NO TENGA INCOHERENCIAS
int decision=JOptionPane.showConfirmDialog(null,"¿Desea continuar?","Estás por pagar una venta a credito",JOptionPane.CANCEL_OPTION);
                if(decision==0){ //opción si
                    if(Float.parseFloat(Controlador_venta_a_credito.pagodeventacredito)>=cambio_pendiente){//CUIANDO EL ABONO ES SUPERIOR AL SALDO PENDIENTE
                        try{Connection ca= cc.conexion();
                        Controlador_venta_a_credito.nuevopago =Float.parseFloat(Controlador_venta_a_credito.pagodeventacredito)+pago_abono;
                        Controlador_venta_a_credito.nuevocambio=Controlador_venta_a_credito.nuevopago-total;
                            PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET descuento='"+ 0+"',pago='"+solodosdecimales.format(Controlador_venta_a_credito.nuevopago).replace(",", ".")+"',cambio='"+solodosdecimales.format(Controlador_venta_a_credito.nuevocambio).replace(",", ".")+"',fecha_reporte='"+Controladorventa.fecha()+"',estado_venta='"+Controladorventa.estadorealizado+"'WHERE id_venta='"+id_venta+"'");
                            ps.executeUpdate();
                        }catch(Exception ex){
                            JOptionPane.showMessageDialog(null, "Error en pagarventacredito" + ex.getMessage());
                        }finally{
                            cc.getClose();
                        }
                        try{Connection ca= cc.conexion();
                            Modeloventa.id_max_de_venta();
                            PreparedStatement ps2 = ca.prepareStatement ("UPDATE descripcion_de_venta SET estado= '"+Controladorventa.estadorealizado+"' WHERE id_venta='"+id_venta+"'");
                            int a = ps2.executeUpdate();
                            if(a>0){eliminar_venta_de_gastos(id_venta);Controlador_venta_a_credito.ver_ventas_a_credito();Controlador_venta_a_credito.ocultar_deudas(); Modeloventa.descripciondelosprouductosparaelticketdeventa(id_venta, Controladorventa.estadorealizado);}
                        }
                        catch(Exception ex){
                            JOptionPane.showMessageDialog(null, "Error en pagarventacredito" + ex.getMessage());
                        }finally{
                            cc.getClose();
                        }
                    }else{// CUANDO EL ABONO ES INFERIOR AL SALDO PENDIENTE
                        try{Connection ca= cc.conexion();
                        Controlador_venta_a_credito.nuevopago =Float.parseFloat(Controlador_venta_a_credito.pagodeventacredito)+pago_abono;
                        Controlador_venta_a_credito.nuevocambio=total-Controlador_venta_a_credito.nuevopago;
                            PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET descuento='"+ 0+"',pago='"+solodosdecimales.format(Controlador_venta_a_credito.nuevopago).replace(",", ".")+"',cambio='"+solodosdecimales.format(Controlador_venta_a_credito.nuevocambio).replace(",", ".")+"',fecha_reporte='"+Controladorventa.fecha()+"'WHERE id_venta='"+id_venta+"'");
                            int a=ps.executeUpdate();
                             if(a>0){
                                 Controlador_venta_a_credito.ver_ventas_a_credito();Controlador_venta_a_credito.ocultar_deudas();
                             }
                        }catch(Exception ex){
                            JOptionPane.showMessageDialog(null, "Error en pagarventacredito" + ex.getMessage());
                        }finally{
                            cc.getClose();
                        }
                    }
    }
  }
        }catch(NullPointerException NP){//ESTO EVITA QUE EL USUARIO META UN VALOR VACIO
 }
    }
    public static void eliminar_venta_de_gastos(int id_venta){
         try{ Connection ca= cc.conexion();// La suma de todos los importes
                                        String sql = "delete from egreso where id_venta= '"+id_venta+"'";
                                           PreparedStatement ps = ca.prepareStatement(sql);
                                           ps.execute();     
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                           JOptionPane.showMessageDialog(null, "ERROR EN METODO: eliminar_idventa_sitienedescuento","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);       
                                                      }// fin del precio-catch del producto
        finally{
                  cc.getClose();
             }
    }
 }
