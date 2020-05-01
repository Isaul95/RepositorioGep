/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;
import static Controladores.Controladorgastos.pass;
import static Controladores.Controladorgastos.pass2;
import static Controladores.Controladorgastos.pass3;
import static Controladores.Controladorgastos.validarFormulariotexto;
import Controladores.Controladorinventarioventas;
import static Controladores.Controladorinventarioventas.cambioticket;
import static Controladores.Controladorinventarioventas.descuentoticket;
import static Controladores.Controladorinventarioventas.importesticket;
import static Controladores.Controladorinventarioventas.mandardatosticketventacancelada;
import static Controladores.Controladorinventarioventas.nombreproductoticket;
import static Controladores.Controladorinventarioventas.pagoticket;
import static Controladores.Controladorinventarioventas.piezastcket;
import static Controladores.Controladorinventarioventas.preciounitarioticket;
import static Controladores.Controladorinventarioventas.subtotalticket;
import static Controladores.Controladorinventarioventas.totalticket;
import Controladores.Controladorventa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableColumnModel;
import si.nucleo;
import si.Gastos;
import si.SI;
import si.nucleo;
import ticket.ticketventacondescuento;
import ticket.ticketventa;
import ticket.ticketventacancelada;
/**
 *
 * @author Alexis
 */
public class Modeloventa extends Controladorventa{
   static SI cc= new SI();
   static Statement sent;  
static ResultSet rs;  
    
 //PRIMER VENTA DEL SISTEM
 public static void primer_ventadelsistema(){
             try {Connection ca= cc.conexion();
             sent = ca.createStatement();   
             rs = sent.executeQuery("select * from venta");
            if(rs.next()){    //REVISAR
                if(rs.getInt(1)!=0){ //si el id resultante de la consulta es diferente de 0 quiere decir que ya hay por lo menos una venta en el sistema
            primerventa=1; //entonces el valor de "primerventa" se convertirá en 1, indicando que ya hay por lo menos una venta
            }
            }
            
        } 
             catch (SQLException ex) {
             JOptionPane.showMessageDialog(null, "Error, primer_ventadelsistema","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
         }
         catch(NumberFormatException NFE){ // caso contrario que la variable resultfirstselling tuviese un valor null indicaria que no hay ninguna venta en el sistema
                    primerventa=0; //y por tal la variable primerventa tendra el valor de 0
        }finally{
                  cc.getClose();
             }
   } //FIN DE PRIMER VENTA DEL SISTEMA
//INICIO GET ID USUARIO
public static void get_id_usuario(){
      try{Connection ca= cc.conexion();// el id del usuario para obtener el id del usuario y comprobar si hay o no algun registro
                         try{ 
                          sent = ca.createStatement();
                          ResultSet rs= sent.executeQuery("SELECT *  FROM  user where nombre_usuario ='"+nucleo.usuarioname+"'");
                          if(rs.next()){
                                   id_usuario=Short.parseShort(rs.getString("id_usuario"));
                         }
                         }catch(Exception a){
                                       JOptionPane.showMessageDialog(null, "Error, get_id_usuario; SELECT *  FROM  user where nombre_usuario = ","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
   }finally{
                  cc.getClose();
             }
                         if(block_unlock==true){Connection cu= cc.conexion();
                               String sql = "INSERT INTO  venta(id_usuario)  VALUES (?)";
                         PreparedStatement pst = cu.prepareCall(sql); 
                         pst.setInt(1,id_usuario);
                         int a=pst.executeUpdate();
                         if(a>0){
                         }
                         }
      }catch(Exception w){
                                       JOptionPane.showMessageDialog(null, "Error, get_id_usuario; INSERT INTO  venta ","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
   }finally{
                  cc.getClose();
             }//fin del id del usuario para comprobar si hay o no elementos ya guardados
  }//FIN DE GET ID USUARIO 

public static void asignar_id_paciente(){
   try{
        if(block_unlock==true){
                Connection cu= cc.conexion();
             String sql = "INSERT INTO  pacientes(dato_auxiliar)  VALUES (?)";
                         PreparedStatement pst = cu.prepareCall(sql); 
                         pst.setString(1,"En espera de venta");
                         int a=pst.executeUpdate();
                         if(a>0){
                         }
                         }
     
   }catch(Exception w){
       JOptionPane.showMessageDialog(null, "Error, asignar_id_paciente; INSERT INTO  pacientes "+w.getMessage(),"HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
   }finally{
                  cc.getClose();
             }//fin del id del usuario para comprobar si hay o no elementos ya guardados
                         
                         
}
public static void obtener_id_paciente(){
       try{ Connection ca= cc.conexion();
                          sent = ca.createStatement();
                          ResultSet rs= sent.executeQuery("SELECT  max(id_paciente) FROM  pacientes");
                          if(rs.next()){
                                   id_paciente=rs.getShort(1);
                         }
                         }catch(Exception a){
                                JOptionPane.showMessageDialog(null, "Error, obtener_id_paciente","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
                                                     }finally{
                  cc.getClose();
             }
}
//OBTENER

public static void obtenerelnombredeproductoylacantidaddelmismo_en_descripcion_deventa(String nombredepieza){
 try{Connection ca= cc.conexion();   
           id_y_precio_producto(nombredepieza);
    id_max_de_venta();
   sent  = (Statement)ca.createStatement();
                                           rs = sent.executeQuery("select * from descripcion_de_venta  where id_producto='"+id_producto+"' and id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and estado= '"+estadoenturno+"' and importe!=0");
                                            if(rs.next()){
                                               NoP =rs.getString("nombre_producto");//NOMBRE DEL PRODUCTO
                                                      NoPcantidad =Float.parseFloat(rs.getString("cantidad")); //CANTIDAD DEL MISMO
                                            NoPimporte = Float.parseFloat(rs.getString("importe"));         
                                            }
 }catch (Exception f){
     JOptionPane.showMessageDialog(null, "Error, obtenerelnombredeproductoylacantidaddelmismo_en_descripcion_deventa","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE);                  
                    }finally{
                  cc.getClose();
             }
 }

//OBTENER
 
 public static void  id_y_precio_producto(String nombredepieza){
          try{ Connection ca= cc.conexion();//el id del producto
                                                      sent  =(Statement)ca.createStatement();
                                                      rs = sent.executeQuery("select * from productos where nombre_producto= '"+nombredepieza+"'");
                                                      if(rs.next()){
                                                      id_producto =Short.parseShort(rs.getString("id_producto"));
                                                      precio =Float.parseFloat(rs.getString("precio"));
                                                      }
                                                      }//fin del try - id del producto
                                                      catch (Exception e){
                                                            JOptionPane.showMessageDialog(null, "Error, id_producto","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
                                                      } finally{
                  cc.getClose();
             }//fin del id-catch del producto
    }
 ////////////////////////////////////////////////////////////////////////////
   public static void id_max_de_venta(){
                        try{ Connection ca= cc.conexion();
                          sent = ca.createStatement();
                          ResultSet rs= sent.executeQuery("SELECT  max(id_venta) FROM  venta");
                          if(rs.next()){
                                   id_de_la_venta_incrementable=rs.getShort(1);
                         }
                         }catch(Exception a){
                                JOptionPane.showMessageDialog(null, "Error, id_max_de_venta","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
                                                     }finally{
                  cc.getClose();
             }
  }
 
    public static void comprobar_registro (String nombredepieza, float cantidaddeproductos){
  obtenerelnombredeproductoylacantidaddelmismo_en_descripcion_deventa(nombredepieza);
if(NoP.equals(nombredepieza)&&NoPimporte!=0){ //Si el nombre del producto es diferente del estado vacio, en palabras más sencillas; si se encuentra el producto que se quiere agregar para que no se asigne nuevamente  
try{Connection ca= cc.conexion();   // ESTE ES PARA EL UPDATE
          obtenerelnombredeproductoylacantidaddelmismo_en_descripcion_deventa(nombredepieza);
      NoPcantidad=NoPcantidad+cantidaddeproductos;
                id_y_precio_producto(nombredepieza);
                         NoPimporte = ((cantidaddeproductos*precio)+NoPimporte);              
               id_y_precio_producto(nombredepieza);
                    id_max_de_venta();
                 PreparedStatement ps = ca.prepareStatement ("UPDATE descripcion_de_venta SET cantidad='"+NoPcantidad+"',importe = '"+NoPimporte+"'WHERE importe !=0 and id_producto='"+id_producto+"' and id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and estado= '"+estadoenturno+"' ");
               int a=  ps.executeUpdate();
               if(a>0){
                    Controladorventa.accionesdespuesinsertarendescripciondeventaoactualizarenlamismatabla(nombredepieza, cantidaddeproductos);
               }else{
                   JOptionPane.showMessageDialog(null, " NO SE PUDO ACTIALIZAR");
               }
        }//fin del id del usuario//fin del id del usuario
                 catch(Exception w){
               JOptionPane.showMessageDialog(null, "Error, comprobar_registro UPDATE","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE);                  
                      }finally{
                  cc.getClose();
             }//fin del id del usuario
               }
   else{
  try{Connection ca= cc.conexion(); //la insersion a la tabla ventas
                String sql = "INSERT INTO descripcion_de_venta (id_producto,nombre_producto,cantidad,precio_unitario,importe,id_paciente,id_venta,estado, fecha)  VALUES (?,?,?,?,?,?,?,?,?)";
                PreparedStatement pst = ca.prepareCall(sql); //hasta aqui vamos
                 id_y_precio_producto(nombredepieza); 
                pst.setInt(1,id_producto);
               pst.setString(2,nombredepieza);
                                 pst.setFloat(3,cantidaddeproductos);    
                //EL METODO A CONTINUACION VA HACIENDO EL CONTEO DE LAS PIEZAS INDIVIDUALES
                // PARA UNA VEZ LLEGANDO A UN POLLO ENTERO DESCONTARLO DE LA BASE           
                 id_y_precio_producto(nombredepieza);
               pst.setFloat(4,precio);
                importe = (float)cantidaddeproductos*precio;
                pst.setFloat(5,importe);
                obtener_id_paciente();
                pst.setInt(6,(id_paciente));
               id_max_de_venta();
                pst.setInt(7,(id_de_la_venta_incrementable));
                pst.setString(8, estadoenturno);
                pst.setString(9, fecha());
                int a=pst.executeUpdate();
                if(a>0){
                   Controladorventa.accionesdespuesinsertarendescripciondeventaoactualizarenlamismatabla(nombredepieza, cantidaddeproductos);
        
                }else{//CUANDO NO SE PUDO INSERTAR
                   }
            }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                    JOptionPane.showMessageDialog(null, "Error, comprobar_registro INSERT INTO"+e.getMessage(),"HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE);                  
              
            }finally{
                  cc.getClose();
             }//fin de la insersion a la tabla ventas
    }
  }
   public static void verificar_id_ingresadoalsistema(){ //verifica que haya o no una venta que fue cancelada previamente en la sesio anterior
        id_max_de_venta();
    int id_para_comprobacion = id_de_la_venta_incrementable, id=0;
        try {Connection ca= cc.conexion();
             sent = ca.createStatement();   
             rs = sent.executeQuery("select * from venta where id_venta= '"+id_para_comprobacion+"'");
            if(rs.next()){        
            id=Integer.parseInt(rs.getString(2));
            }
            block_unlock=false;
            get_id_usuario();
            if(id==id_usuario){// esta condicion sirve para saber si "id" (el id almacenado en la ultima venta) es igual al "id_usuario" (el usuario que ingreo al sistema
            }
            else {
            id_max_de_venta();
                try{
                     PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET id_usuario='"+id_usuario+"' WHERE id_venta= '"+id_de_la_venta_incrementable+"'");
          ps.executeUpdate();
                }catch (Exception e){
                     JOptionPane.showMessageDialog(null, "Error" + e.getMessage());
                }finally{
                    cc.getClose();
                }  
            }
           } catch (SQLException ex) {
                     JOptionPane.showMessageDialog(null, "Error" + ex.getMessage());
        }
        catch(NumberFormatException NFE){                           
        }finally{
                    cc.getClose();
                }
    }
   public static void comprobar_venta_resagada(){ //verifica que haya o no una venta que fue cancelada previamente en la sesio anterior
        id_max_de_venta();
    int id_para_comprobacion = id_de_la_venta_incrementable;
        try {Connection ca= cc.conexion();
             sent = ca.createStatement();   
             rs = sent.executeQuery("select * from venta where id_venta= '"+id_para_comprobacion+"'");
            if(rs.next()){        
            totalcomprobacion=Short.parseShort(rs.getString(5));
            }
            if(totalcomprobacion>0){
                //PRIMERO, CUANDO ES MAYOR A 0, QUIERE DECIR QUE LA VENTA ANTERIOR SE REALIZO
                //SEGUNDO, CUANDO LA VENTA ES IGUAL A 0 SE CANCELO
                //TERCERO, CUANDO TIENE TOTAL NEGATIVO SIGNIFICA QUE ES UNA VENTA A CREDITO PENDIENTE POR PAGAR
                block_unlock=true;
            }
        } catch (SQLException ex) {
         }
        catch(NumberFormatException NFE){
                            block_unlock=false; //Se desactiva para que no se agregue otra venta al usuario en turno , así al hacer otra venta 
                            //se agregará a e ésta venta resagada
        }finally{
                    cc.getClose();
                }  
    }
                
  public static void total_venta_enturno(){
        try{ Connection ca= cc.conexion();// La suma de todos los importes
           id_max_de_venta();
                                          sent  =(Statement)ca.createStatement();
                                           rs = sent.executeQuery("select SUM(importe) from descripcion_de_venta where id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and  estado = '"+estadoenturno+"'");
                                            if(rs.next()){
                                                      sumadeimportesenturno =Float.parseFloat(rs.getString("SUM(importe)"));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                          sumadeimportesenturno=0;
                                                      }finally{
                    cc.getClose();
                }// fin del precio-catch del producto
    }
   public static void mostrartabladeventas(){ // solo muestra la tabla de proveedores 
         nucleo.tablaventa.setVisible(true);    //hace visible la tabla de proveedores 
              DefaultTableModel modelo = new DefaultTableModel(); // Se crea un objeto para agregar los nombres de las columnas a la tabla
    modelo.addColumn("Producto");
    modelo.addColumn("Cantidad");
    modelo.addColumn("Precio");
     modelo.addColumn("Importe");
     nucleo.tablaventa.setModel(modelo);  // Ya una vez asignado todos los nombres se le envia el objeto a la tabla proveedores
     
     String []datos = new String[4];     //Un arreglo con la cantidad de nombres en las columnas
    try {Connection ca= cc.conexion();
        id_max_de_venta();
             sent = ca.createStatement();   
                       rs= sent.executeQuery("select * from  descripcion_de_venta where id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and  estado in ('En turno')"); // se ejecuta la sentencia dentro del parentesis
            while(rs.next()){        
            datos[0]=rs.getString(3);
            datos[1]=rs.getString(4);
            datos[2]=rs.getString(5);
            datos[3]=rs.getString(6);
            modelo.addRow(datos); //se asigna el arreglo  entero a todo el objeto llamado modelo  
            }
          nucleo.tablaventa.setModel(modelo); // Se vuelve a enviar nuevamente el objeto modelo a la tabla
       TableColumnModel columnModel =  nucleo.tablaventa.getColumnModel();
    //columnModel.getColumn(0).setPreferredWidth(450);
    //columnModel.getColumn(1).setPreferredWidth(5);
   //columnModel.getColumn(2).setPreferredWidth(10);
    //columnModel.getColumn(3).setPreferredWidth(10);
    columnModel.getColumn(0).setMaxWidth(520);
    columnModel.getColumn(0).setMinWidth(520);
         columnModel.getColumn(1).setMaxWidth(70);
    columnModel.getColumn(1).setMinWidth(70);
     columnModel.getColumn(2).setMaxWidth(80);
    columnModel.getColumn(2).setMinWidth(80);
     columnModel.getColumn(3).setMaxWidth(80);
    columnModel.getColumn(3).setMinWidth(80 );


        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error, mostrartabladeventas","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
                                         } finally{
                  cc.getClose();
             }
    }

   
    public static void ids_y_cantidades_enturno_por_error_de_usuario(){
try {Connection ca= cc.conexion();
        id_max_de_venta();
             sent = ca.createStatement();   
                 rs= sent.executeQuery("select id_producto, cantidad from  descripcion_de_venta where id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and  estado = '"+estadoenturno+"'"); // se ejecuta la sentencia dentro del parentesis
            while(rs.next()){        
            idsenturno.add(0, rs.getInt(1));
            cantidaddecadaidenturno.add(0, rs.getFloat(2));
            }
  regresar_cantidades_enturno_por_error_de_usuario();//SE REGRESAN LAS CANTIDADES
  idsenturno.clear();
            cantidaddecadaidenturno.clear();
  borrarventasenestadoenturnoporerrordeusuario_limpiarventa_o_cerrarsesion();//SE BORRA DE DESCRIPCION DE VENTA
borrarventasenestadoenturnoporerrordeusuario_que_no_coincidenconlafechadehoy();//ESTA SITUACION ES EXCLUSIVA CUANDO SE ESTABA HACIENDO UNA VENTA, AVANZÓ AL DÍA SIGUIENTE MIENTRAS EL SISTEMA ESTABA CORRIENDO, SE CERRÓ SISTEMA
//SE ALMACENÓ LA VENTA COMO EN TURNO Y EL METODO ANTERIOR A ESTE NO LA PUEDE DETECTAR PORQUE SOLO ELIMINA LAS VENTA EN TURNO DEL DÍA, NO DEL DÍA DE AYER NI DEL PASADO
} catch (SQLException ex) {
            System.out.println();
      JOptionPane.showMessageDialog(null, "ERROR EN METODO: ids_y_cantidades_enturno_por_error_de_usuario","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
     } finally{
                  cc.getClose();
             } 
    }
   public static void regresar_cantidades_enturno_por_error_de_usuario(){
      for (ciclofor=0; ciclofor < idsenturno.size(); ciclofor++) {    
             try { Connection ca= cc.conexion(); 
             sent = ca.createStatement();   
                       rs= sent.executeQuery("select cantidad from productos where id_producto= '"+idsenturno.get(ciclofor)+"'"); // se ejecuta la sentencia dentro del parentesis
            while(rs.next()){        
            cantidadporerrordeusuario = rs.getFloat(1);
             }
             } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR EN METODO: regresar_cantidades_enturno_por_error_de_usuario"+", PRIMER CATCH","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
       }finally{
                  cc.getClose();
             } 
             //DEVOLVIENDO LA CANTIDAD DE PRODUCTOS EN TURNO A LA TABLA PRODUCTOS
           try{Connection cu= cc.conexion(); 
                 PreparedStatement ps = cu.prepareStatement ("UPDATE productos SET cantidad= ? WHERE id_producto= ? ");
                 ps.setFloat(1, cantidadporerrordeusuario+=Float.parseFloat(cantidaddecadaidenturno.get(ciclofor).toString()));
                 ps.setInt(2, Integer.parseInt(idsenturno.get(ciclofor).toString()));
                 ps.executeUpdate();
             }catch(Exception e){
                  JOptionPane.showMessageDialog(null, "ERROR EN METODO: regresar_cantidades_enturno_por_error_de_usuario"+", SEGUNDO CATCH","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
       }finally{
                  cc.getClose();
             } 
       }//FIN DEL  FOR
  }    
   public static void borrarventasenestadoenturnoporerrordeusuario_limpiarventa_o_cerrarsesion(){    
         id_max_de_venta();
     try{Connection ca= cc.conexion(); 
            String sql = "DELETE from descripcion_de_venta where id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and estado= '"+estadoenturno+"' ";
            sent = ca.createStatement();
            int n = sent.executeUpdate(sql);
            if(n>0){
             }else{
            }
        }catch(Exception e){
         JOptionPane.showMessageDialog(null, "ERROR EN METODO: borrarventasenestadoenturnoporerrordeusuario_limpiarventa_o_cerrarsesion","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }  finally{
                  cc.getClose();
             }    
     }
   public static void borrarventasenestadoenturnoporerrordeusuario_que_no_coincidenconlafechadehoy(){    
         id_max_de_venta();
     try{Connection ca= cc.conexion(); 
            String sql = "DELETE from descripcion_de_venta where id_venta= '"+id_de_la_venta_incrementable+"' and fecha!= '"+fecha()+"' and estado= '"+estadoenturno+"' ";
            sent = ca.createStatement();
            int n = sent.executeUpdate(sql);
            if(n>0){
             }
        }catch(Exception e){
         JOptionPane.showMessageDialog(null, "ERROR EN METODO: borrarventasenestadoenturnoporerrordeusuario_que_no_coincidenconlafechadehoy","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }    finally{
                  cc.getClose();
             }  
     }
        
    public static void metodo_de_cobro(float variablepago){
        DecimalFormat solodosdecimales = new DecimalFormat("#.##");
    /*   ********************  BOTON DE COBRAR LA VENTA ****************  */
            /*   ******************  BOTON DE COBRAR LA VENTA **************  */
            try{
              if(!nucleo.subtotal.getText().isEmpty()&&variablepago>0&&Float.parseFloat(nucleo.subtotal.getText())>0){
                    if(descuentoactivo==true){ //CUANDO EL DESCUENTO ESTÁ ACTIVO
                        if(variablepago<Float.parseFloat(nucleo.total.getText())){ // comprueba que la cantidad recibida sea mayor al total
                            JOptionPane.showMessageDialog(null,"El pago es menor a la cantidad a pagar, por favor, verifique","Advertencia",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else {
                                         pass = Controladorventa.validarFormulario_paciente(nucleo.user_edad.getText());
                   pass2 = validarFormulariotexto_paciente(nucleo.user_nombre.getText());
                   pass3 = validarFormulariotexto_paciente(nucleo.medico.getText());
                try{
                     String fecha_paciente= Controladorventa.fecha_de_nacimiento_del_paciente();
          if (pass && pass2 &&pass3&&!fecha_paciente.equalsIgnoreCase("")) {//ESTO ES PARA VALIDAR QUE SE TENGAN TODOS LOS DATOS DEL CLIENTE
                 tablaventaactiva=false;
                            nucleo.cambiocombobox.setText(String.valueOf(cambio=variablepago-Float.parseFloat(nucleo.total.getText())));
                            block_unlock=true;
                            try{Connection ca= cc.conexion();// el id del usuario
                                id_max_de_venta();
                               
                                 PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET subtotal='"+solodosdecimales.format(Float.parseFloat(nucleo.subtotal.getText())).replace(",", ".")+"',total='"+solodosdecimales.format(Float.parseFloat(nucleo.total.getText())).replace(",", ".")+"',descuento='"+solodosdecimales.format(Float.parseFloat(nucleo.descuentocombo.getText())).replace(",", ".")+"',pago='"+variablepago+"',cambio='"+solodosdecimales.format(Float.parseFloat(nucleo.cambiocombobox.getText())).replace(",", ".")+"',fecha_reporte='"+fecha()+"',estado_venta='"+estadorealizado+"',hora='"+nucleo.Reloj.getText()+"'WHERE id_venta='"+id_de_la_venta_incrementable+"'");
  ps.executeUpdate();
                              //ACTUALIZACION EN LA TABLA DESCRIPCION DE VENTA A REALIZADA
                                id_max_de_venta();
                                try{
                                    id_max_de_venta();
                                    PreparedStatement ps2 = ca.prepareStatement ("UPDATE descripcion_de_venta SET estado= '"+estadorealizado+"' WHERE id_venta='"+id_de_la_venta_incrementable+"'");
                                    int result = ps2.executeUpdate();
                                         if(result>0){
                                            Modelogastos.insertarventacondescuentoengastos("V. "+id_de_la_venta_incrementable+"/Descuento: ",Float.parseFloat(nucleo.descuentocombo.getText()),id_de_la_venta_incrementable);
                                              JOptionPane.showMessageDialog(null, "El cambio es de: "+nucleo.cambiocombobox.getText()," Se realizo una venta",JOptionPane.YES_OPTION);
                      if(nucleo.reimprimirventa.isSelected()){        descripciondelosprouductosparaelticketdeventa(id_de_la_venta_incrementable,estadorealizado);//DATOS PARA EL TICKET DE VENTA          
                                           descripciondelosprouductosparaelticketdeventa(id_de_la_venta_incrementable,estadorealizado);//DATOS PARA EL TICKET DE VENTA          
                                  Controladorventa.accionesdespuesderealizarcualquierventa(); 
                      }else{descripciondelosprouductosparaelticketdeventa(id_de_la_venta_incrementable,estadorealizado);//DATOS PARA EL TICKET DE VENTA          
                                      Controladorventa.accionesdespuesderealizarcualquierventa();}
                                         }
                                }
                                catch(Exception ex){
                                    JOptionPane.showMessageDialog(null, "Error en metodo_de_cobro update desc" + ex.getMessage());
                                }
                                //PARA GUARDAR LOS DATOS DEL PACIENTE
                                
                                try{
                                  obtener_id_paciente();
                             
                                  nombre_paciente=nucleo.user_nombre.getText(); edad_paciente=nucleo.user_edad.getText();   sexo_paciente=nucleo.user_sexo.getSelectedItem().toString(); medico = nucleo.medico.getText();
                                  if(Integer.parseInt(edad_paciente)==1)
                                      edad_paciente=edad_paciente+" año.";
                                  else edad_paciente=edad_paciente+" años.";
                                  PreparedStatement ps3 = ca.prepareStatement ("UPDATE pacientes SET nombre='"+nombre_paciente+"',fecha_nacimiento='"+Controladorventa.fecha_de_nacimiento_del_paciente()+"',edad='"+edad_paciente+"',sexo='"+sexo_paciente+"',medico='"+medico+"'WHERE id_paciente='"+id_paciente+"'");
 int resultado = ps3.executeUpdate();
                                     if(resultado>0){
                                              Modeloventa.asignar_id_paciente(); //Inserta el id_del paciente para no tener error con la llave foranea
                                             limpiardatospaciente(); //Limpia los datos que tengan que ver con el id paciente
                                     }
                                }
                                catch(Exception ex){
                                    JOptionPane.showMessageDialog(null, "Error en metodo_de_cobro con desc update pacientes" + ex.getMessage());
                                
                                }
                                
                                //PARA GUARDAR LOS DATOS DEL PACIENTE
                                //autocompletar();
                            }//fin del id del usuario//fin del id del usuario//fin del id del usuario//fin del id del usuario//fin del id del usuario//fin del id del usuario//fin del id del usuario//fin del id del usuario
                            catch(Exception w){
                                JOptionPane.showMessageDialog(null,"error en metodo_de_cobro update venta"+w);
                            }finally{cc.getClose();}
          } else{
                    JOptionPane.showMessageDialog(null,"Complete todos los datos del paciente", "ERROR", JOptionPane.ERROR_MESSAGE);
          }
                  }catch(NullPointerException NP){
            JOptionPane.showMessageDialog(null,"Debes de elegir la fecha de nacimiento del paciente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }                   
                        }
                    } //FIN DE CUANDO EL DESCUENTO ESTÁ ACTIVO
else{ //CUANDO EL DESCUENTO NO ESTÁ ACTIVO
if(variablepago<Float.parseFloat(nucleo.subtotal.getText())){ // comprueba que la cantidad recibida sea mayor al total
                            JOptionPane.showMessageDialog(null,"El pago es menor a la cantidad a pagar, por favor, verifique","Advertencia",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else {
                  pass = Controladorventa.validarFormulario_paciente(nucleo.user_edad.getText());
                   pass2 = validarFormulariotexto_paciente(nucleo.user_nombre.getText());
                    pass3 = validarFormulariotexto_paciente(nucleo.medico.getText());
                          try{      String fecha_paciente= Controladorventa.fecha_de_nacimiento_del_paciente();
          if (pass && pass2 &&pass3&&!fecha_paciente.equalsIgnoreCase("")) {//ESTO ES PARA VALIDAR QUE SE TENGAN TODOS LOS DATOS DEL CLIENTE
                        tablaventaactiva=false;
                            nucleo.cambiocombobox.setText(String.valueOf(cambio=variablepago-sumadeimportesenturno));
                            block_unlock=true;
                            try{Connection ca= cc.conexion();// el id del usuario
                                id_max_de_venta();
                           PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET subtotal='"+solodosdecimales.format(Float.parseFloat(nucleo.subtotal.getText())).replace(",", ".")+"',total='"+solodosdecimales.format(Float.parseFloat(nucleo.total.getText())).replace(",", ".")+"',descuento='"+0+"',pago='"+variablepago+"',cambio='"+solodosdecimales.format(Float.parseFloat(nucleo.cambiocombobox.getText())).replace(",", ".")+"',fecha_reporte='"+fecha()+"',estado_venta='"+estadorealizado+"',hora='"+nucleo.Reloj.getText()+"'WHERE id_venta='"+id_de_la_venta_incrementable+"'");
 ps.executeUpdate();
                                //ACTUALIZACION EN LA TABLA DESCRIPCION DE VENTA A REALIZADA
                                id_max_de_venta();
                                try{
                                    id_max_de_venta();
                                    PreparedStatement ps2 = ca.prepareStatement ("UPDATE descripcion_de_venta SET estado= '"+estadorealizado+"' WHERE id_venta='"+id_de_la_venta_incrementable+"'");
                                   int resultado=  ps2.executeUpdate();
                                     if(resultado>0){
                                             JOptionPane.showMessageDialog(null, "El cambio es de: "+nucleo.cambiocombobox.getText()," Se realizo una venta",JOptionPane.YES_OPTION);
                               if(nucleo.reimprimirventa.isSelected()){        descripciondelosprouductosparaelticketdeventa(id_de_la_venta_incrementable,estadorealizado);//DATOS PARA EL TICKET DE VENTA          
                                           descripciondelosprouductosparaelticketdeventa(id_de_la_venta_incrementable,estadorealizado);//DATOS PARA EL TICKET DE VENTA          
                                  Controladorventa.accionesdespuesderealizarcualquierventa(); 
                               }else{descripciondelosprouductosparaelticketdeventa(id_de_la_venta_incrementable,estadorealizado);//DATOS PARA EL TICKET DE VENTA          
                                      Controladorventa.accionesdespuesderealizarcualquierventa();}
                                             
                                     }
                                }
                                catch(Exception ex){
                                    JOptionPane.showMessageDialog(null, "Error en metodo_de_cobro sin desc update desc" + ex.getMessage());
                                
                                }
                                //PARA GUARDAR LOS DATOS DEL PACIENTE
                                
                                try{
                                  obtener_id_paciente();
                             
                                  nombre_paciente=nucleo.user_nombre.getText(); edad_paciente=nucleo.user_edad.getText();   sexo_paciente=nucleo.user_sexo.getSelectedItem().toString(); medico = nucleo.medico.getText();
                                             if(Integer.parseInt(edad_paciente)==1)
                                      edad_paciente=edad_paciente+" año.";
                                  else edad_paciente=edad_paciente+" años.";
                                  PreparedStatement ps3 = ca.prepareStatement ("UPDATE pacientes SET nombre='"+nombre_paciente+"',fecha_nacimiento='"+Controladorventa.fecha_de_nacimiento_del_paciente()+"',edad='"+edad_paciente+"',sexo='"+sexo_paciente+"',medico='"+medico+"'WHERE id_paciente='"+id_paciente+"'");
 int resultado = ps3.executeUpdate();
                                     if(resultado>0){
                                               Modeloventa.asignar_id_paciente(); //Inserta el id_del paciente para no tener error con la llave foranea
                                             limpiardatospaciente(); //Limpia los datos que tengan que ver con el id paciente
                                     }
                                }
                                catch(Exception ex){
                                    JOptionPane.showMessageDialog(null, "Error en metodo_de_cobro sin desc update pacientes" + ex.getMessage());
                                
                                }
                                
                                //PARA GUARDAR LOS DATOS DEL PACIENTE
                                //autocompletar();
                            }//fin del id del usuario//fin del id del usuario//fin del id del usuario//fin del id del usuario//fin del id del usuario//fin del id del usuario//fin del id del usuario//fin del id del usuario
                            catch(Exception w){
                                JOptionPane.showMessageDialog(null,"error en id usuario"+w);
                            }finally{
                                cc.getClose();
                            }
                     }//ESTO ES PARA VALIDAR QUE SE TENGAN TODOS LOS DATOS DEL CLIENTE
          else{
                    JOptionPane.showMessageDialog(null,"Complete todos los datos del paciente", "ERROR", JOptionPane.ERROR_MESSAGE);
          }
        }catch(NullPointerException NP){
            JOptionPane.showMessageDialog(null,"Debes de elegir la fecha de nacimiento del paciente", "ERROR", JOptionPane.ERROR_MESSAGE);
        }
                          }
                    } //FIN CUANDO EL DESCUENTO NO ESTÁ ACTIVO
                }
                else if(nucleo.subtotal.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Aún no hay nada por pagar","!Espera!",JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(Exception NFE){//Number format exception para cuando el usuario no ingrese ningun dato en la caja
                JOptionPane.showMessageDialog(null,"No tiene valor la cantidad recibida"+NFE.getMessage(),"!Espera!",JOptionPane.INFORMATION_MESSAGE);
}
    }
public static void descripciondelosprouductosparaelticketdeventa(int numerodeventa,String tipo_de_venta){
         try {Connection ca= cc.conexion();
                 String sSQL = "SELECT dv.nombre_producto, dv.cantidad, dv.precio_unitario, dv.importe, v.subtotal, v.total, v.pago, v.cambio, v.descuento FROM descripcion_de_venta dv inner join venta v on dv.id_venta = v.id_venta WHERE dv.estado='"+tipo_de_venta+"' AND v.id_venta = '"+numerodeventa+"' and v.fecha_reporte = '"+fecha()+"' ";  
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
              Controladorinventarioventas.nombreproductoticket.add(rs.getString(1));
              Controladorinventarioventas.piezastcket.add(rs.getString(2));
              Controladorinventarioventas.preciounitarioticket.add(rs.getString(3)); 
              Controladorinventarioventas.importesticket.add(rs.getString(4));
              
                Controladorinventarioventas.subtotalticket = rs.getFloat(5);
              Controladorinventarioventas.totalticket = rs.getFloat(6);
                Controladorinventarioventas.pagoticket = rs.getFloat(7);
                 Controladorinventarioventas.cambioticket = rs.getFloat(8);
                 Controladorinventarioventas.descuentoticket = rs.getFloat(9); 
            }
        //    total_pagoycambiopararelticketdeventa(numerodeventa);
            if(descuentoactivo==true || Controladorinventarioventas.descuentoticket !=0){
                            if(tipo_de_venta.equalsIgnoreCase("Realizada")){
                                //estas dos lineas mandan los datos para el ticket
                 Controladorinventarioventas.mandardatosaticketventacondescuento = new ticketventacondescuento();
                 Controladorinventarioventas.mandardatosaticketventacondescuento.tikectdeventacondescuento(Controladorinventarioventas.nombreproductoticket, 
                         Controladorinventarioventas.piezastcket, 
                         Controladorinventarioventas.preciounitarioticket, 
                         Controladorinventarioventas.importesticket,
                         Controladorinventarioventas.subtotalticket, Controladorinventarioventas.totalticket, 
                         Controladorinventarioventas.pagoticket, Controladorinventarioventas.cambioticket, 
                         Controladorinventarioventas.descuentoticket, numerodeventa);
            //totalcdescticket agregar al metodo de arriba
                 Controladorventa.vaciarlistasdeticket();
                            }else if(tipo_de_venta.equalsIgnoreCase("Cancelada")){
                                 mandardatosticketventacancelada = new ticketventacancelada();
                 mandardatosticketventacancelada.tikectventacancelada(nombreproductoticket, 
                         piezastcket, 
                         preciounitarioticket, 
                         importesticket,
                         subtotalticket, totalticket, pagoticket, cambioticket, descuentoticket, numerodeventa);
            //totalcdescticket agregar al metodo de arriba
                 Controladorventa.vaciarlistasdeticket();
                            }
            }else if((descuentoactivo==false || Controladorinventarioventas.descuentoticket ==0)){//venta simple
                 if(tipo_de_venta.equalsIgnoreCase("Realizada")){
                     //estas dos lineas mandan los datos para el ticket
                 mandardatosticketventa = new ticketventa();
                 mandardatosticketventa.tikectdeventa(Controladorinventarioventas.nombreproductoticket, 
                  Controladorinventarioventas.piezastcket, 
                         Controladorinventarioventas.preciounitarioticket, 
                         Controladorinventarioventas.importesticket,
                         Controladorinventarioventas.subtotalticket, Controladorinventarioventas.totalticket, 
                         Controladorinventarioventas.pagoticket, Controladorinventarioventas.cambioticket, numerodeventa);
 Controladorventa.vaciarlistasdeticket();
                 } else if(tipo_de_venta.equalsIgnoreCase("Cancelada")){
                                 mandardatosticketventacancelada = new ticketventacancelada();
                 mandardatosticketventacancelada.tikectventacancelada(nombreproductoticket, 
                         piezastcket, 
                         preciounitarioticket, 
                         importesticket,
                         subtotalticket, totalticket, pagoticket, cambioticket, descuentoticket, numerodeventa);
            //totalcdescticket agregar al metodo de arriba
                 Controladorventa.vaciarlistasdeticket();
                            }
                 
            }
         } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "ERROR EN METODO: descripciondelosprouductosparaelticketdeventa"+e.getMessage(),"DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{
                  cc.getClose();
             }
    }
 public static void regresarproductos_a_inventario(String nombredepieza){ // este metodo devuelve los productos que fueron agregados a la venta y posteriormente fueron cancelados
                        //ELIMINAR DE VENTA EL ARTICULO
                         id_y_precio_producto(nombredepieza);
                        id_max_de_venta();
                        try{Connection ca= cc.conexion();
            String sql = "DELETE from descripcion_de_venta where id_producto= '"+id_producto+"' and id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and estado= '"+estadoenturno+"' ";
            sent = ca.createStatement();
            int n = sent.executeUpdate(sql);
            if(n>0){
                Modeloventa.acciones_despues_de_regresaroagregaraventa();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR en regresarproductos_a_inventario" + e.getMessage());
        }finally{
                    cc.getClose();
                } //ELIMINAR DE VENTA EL ARTICULO     
}
public static void acciones_despues_de_regresaroagregaraventa(){
    if(descuentoactivo==true){
                          Modeloventa.total_venta_enturno();
                         if(sumadeimportesenturno!=0){
                         nucleo.subtotal.setText(String.valueOf(sumadeimportesenturno));   
         nucleo.total.setText(String.valueOf(Float.parseFloat(nucleo.subtotal.getText()) - Float.parseFloat(nucleo.descuentocombo.getText())));
                         }else if(sumadeimportesenturno==0){
                             nucleo.subtotal.setText("00.00");
                              nucleo.cambiocombobox.setText("00.00");
                            nucleo.descuentocombo.setText("00.00");
                             nucleo.total.setText("00.00");
                         }
                     }else if(descuentoactivo==false){ 
                              Modeloventa.total_venta_enturno();
                       nucleo.subtotal.setText(String.valueOf(sumadeimportesenturno));   
                            nucleo.total.setText(String.valueOf(sumadeimportesenturno));
                     }
}
public static void obtenerlosiddelavebta_enturno_o_venta_cancelada(String estadodelaventa, int id_enturno_o_cancelado){
                try{Connection ca= cc.conexion();
                sent  =(Statement)ca.createStatement(); 
                     rs = sent.executeQuery("select id_producto from descripcion_de_venta where estado='"+estadodelaventa+"'and id_venta='"+id_enturno_o_cancelado+"'and fecha='"+fecha()+"'  ");       
                while(rs.next()){    
                    storage.add(rs.getInt("id_producto"));     
                            }
                }catch(Exception e){
                }finally{
                    cc.getClose();
                }
}
}