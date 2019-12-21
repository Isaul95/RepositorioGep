/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelos;
import Controladores.Controladorventa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import javax.swing.table.DefaultTableModel;
import si.Entradaproductos;
import si.Inventarioventas;
import si.SI;
import si.nucleo;
import ticket.ticketventacondescuento;
import ticket.ticketventa;
import ticket.ticketventacancelada;
import ticket.ticketventacredito;
/**
 *
 * @author Alexis
 */
public class Modeloventa extends Controladorventa{
   static SI cc= new SI();
   static Statement sent;  
static ResultSet rs;  
 public static void piezassuficientes(String pieza, float cantidaddeproductos){ //INICIO DE PIEZAS SUFICIENTES
     if(pieza.equals("Huesito")||pieza.equals("Longaniza")||pieza.equals("pollo crudo")||pieza.equals("Medio pollo")){
 suficientespiezas=true;
}
          else{
              try{Connection ca= cc.conexion(); //el id del producto
                                                         sent  =(Statement)ca.createStatement();
                                                      rs = sent.executeQuery("select * from productos where nombre_producto= '"+pieza+"'");
                                                      if(rs.next()){
                                                     piezassuficientes =rs.getFloat("cantidad");
                                                      }   
                                                      if(piezassuficientes>=cantidaddeproductos){
                                                          suficientespiezas=true;
                                                      }
                                                      else {
                                                          suficientespiezas=false;
                                                      }
                                                     }//fin del try - id del producto
                                                       catch (Exception e){
                                                            JOptionPane.showMessageDialog(null, "ERROR EN METODO: piezassuficientes","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
                                                      } //fin del id-catch del producto
              finally{
                  cc.getClose();
             }
          }
    }//FIN DE PIEZAS SUFICIENTES
    
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

//OBTENER

public static void obtenerelnombredeproductoylacantidaddelmismo_en_descripcion_deventa(String nombredepieza){
 try{Connection ca= cc.conexion();   
          id_producto(nombredepieza);
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
public static void nombredeproductoylacantidaddelmismo_en_descripcion_deventaparamedioocompletospollos(String nombredepieza){
 try{Connection ca= cc.conexion();   
          id_producto(nombredepieza);
    id_max_de_venta();
   sent  = (Statement)ca.createStatement();
                                           rs = sent.executeQuery("select * from descripcion_de_venta  where id_producto='"+id_producto+"' and id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and estado= '"+estadoenturno+"' and importe=0");
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

 public static void id_producto(String nombredepieza){
          try{ Connection ca= cc.conexion();//el id del producto
                                                      sent  =(Statement)ca.createStatement();
                                                      rs = sent.executeQuery("select * from productos where nombre_producto= '"+nombredepieza+"'");
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
  public static void insertorupdatepechugaenbisteck(String nombredepieza, float cantidaddeproductos){
  obtenerelnombredeproductoylacantidaddelmismo_en_descripcion_deventa("Pechuga en bisteck");
if(NoP.equals("Pechuga en bisteck")&&NoPimporte!=0){ //Si el nombre del producto es diferente del estado vacio, en palabras más sencillas; si se encuentra el producto que se quiere agregar para que no se asigne nuevamente  
    try{Connection ca= cc.conexion();// ESTE ES PARA EL UPDATE
          obtenerelnombredeproductoylacantidaddelmismo_en_descripcion_deventa(nombredepieza);
          NoPcantidad=NoPcantidad+cantidaddeproductos;
                precio_producto(nombredepieza);
                NoPimporte = NoPcantidad*precio;
    id_producto(nombredepieza);
                    id_max_de_venta();
                 PreparedStatement ps = ca.prepareStatement ("UPDATE descripcion_de_venta SET cantidad='"+NoPcantidad+"',importe = '"+NoPimporte+"'WHERE importe !=0 and id_producto='"+57+"' and id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and estado= '"+estadoenturno+"' ");
               int a=  ps.executeUpdate();
               if(a>0){
                      Controladorventa.accionesdespuesinsertarendescripciondeventaoactualizarenlamismatabla(nombredepieza,cantidaddeproductos);
                   
               }else{
                   JOptionPane.showMessageDialog(null, " NO SE PUDO ACTIALIZAR");
               }
        }//fin del id del usuario//fin del id del usuario
                 catch(Exception w){
                     JOptionPane.showMessageDialog(null, "Error en todo el codigo de update de metodo comprobar_registro" + w.getMessage());
                 }finally{cc.getClose();}//fin del id del usuario
     }
       else{
            try{Connection ca= cc.conexion(); //la insersion a la tabla ventas
                String sql = "INSERT INTO descripcion_de_venta (id_producto,nombre_producto,cantidad,precio_unitario,importe,id_venta,estado, fecha)  VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement pst = ca.prepareCall(sql); //hasta aqui vamos
                id_producto(nombredepieza); 
                pst.setInt(1,57);
               noguardaridrepetidoenstorage(57);
               pst.setString(2,"Pechuga en bisteck");
                pst.setFloat(3,cantidaddeproductos);            
                //EL METODO A CONTINUACION VA HACIENDO EL CONTEO DE LAS PIEZAS INDIVIDUALES
                // PARA UNA VEZ LLEGANDO A UN POLLO ENTERO DESCONTARLO DE LA BASE           
                 precio_producto(nombredepieza);
                pst.setFloat(4,precio);
                     importe = (float)cantidaddeproductos*precio;        
                pst.setFloat(5,importe);
                id_max_de_venta();
                pst.setInt(6,(id_de_la_venta_incrementable));
                pst.setString(7, estadoenturno);
                pst.setString(8, fecha());
                int a=pst.executeUpdate();
                if(a>0){
                    Controladorventa.accionesdespuesinsertarendescripciondeventaoactualizarenlamismatabla(nombredepieza, cantidaddeproductos);
                              
                }else{//CUANDO NO SE PUDO INSERTAR
                   }
            }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
            }finally{cc.getClose();}//fin de la insersion a la tabla ventas
        }
    }
   
    public static void noguardaridrepetidoenstorage(int id){//NO PERMITE AGREGAR IDS REPETIDOS A STORAGE
     boolean loencontre=false;
     if(storage.size()>0){
         for(ciclofor=0; ciclofor<=storage.size()-1;ciclofor++){  
         if(Integer.parseInt(storage.get(ciclofor).toString())==id){
          loencontre=true;  
         }
         }if(loencontre==false){
             storage.add(id);
         }
     }else{
          storage.add(id);
     }
 }
    public static void precio_producto(String nombredepieza){
        try{ Connection ca= cc.conexion();// el precio del producto
                                sent  =(Statement)ca.createStatement();
                                           rs = sent.executeQuery("select * from productos where nombre_producto= '"+nombredepieza+"'");
                                            if(rs.next()){
                                                      precio =Float.parseFloat(rs.getString("precio"));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
    JOptionPane.showMessageDialog(null, "Error, precio_producto","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
                                                      }finally{
                  cc.getClose();
             }// fin del precio-catch del producto
    }
    public static void comprobar_registro (String nombredepieza, float cantidaddeproductos){
  obtenerelnombredeproductoylacantidaddelmismo_en_descripcion_deventa(nombredepieza);
if(NoP.equals(nombredepieza)&&NoPimporte!=0){ //Si el nombre del producto es diferente del estado vacio, en palabras más sencillas; si se encuentra el producto que se quiere agregar para que no se asigne nuevamente  
try{Connection ca= cc.conexion();   // ESTE ES PARA EL UPDATE
          obtenerelnombredeproductoylacantidaddelmismo_en_descripcion_deventa(nombredepieza);
      NoPcantidad=NoPcantidad+cantidaddeproductos;
                precio_producto(nombredepieza);
                if(nombredepieza.equals("Pollo rostizado")&&cantidaddeproductos==0.50){
                    NoPimporte = ((cantidaddeproductos*100)+NoPimporte);
                } else if(nombredepieza.equals("Pollo rostizado")&&cantidaddeproductos==0.25){
                     NoPimporte = ((cantidaddeproductos*88)+NoPimporte);
                }
                else if(nombredepieza.equals("Pollo asado")&&cantidaddeproductos==0.50){
                     NoPimporte = ((cantidaddeproductos*100)+NoPimporte);
                } else if(nombredepieza.equals("Pollo asado")&&cantidaddeproductos==0.25){
                     NoPimporte = ((cantidaddeproductos*88)+NoPimporte);
                }else{
                         NoPimporte = ((cantidaddeproductos*precio)+NoPimporte);
              }
              id_producto(nombredepieza);
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
                String sql = "INSERT INTO descripcion_de_venta (id_producto,nombre_producto,cantidad,precio_unitario,importe,id_venta,estado, fecha)  VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement pst = ca.prepareCall(sql); //hasta aqui vamos
                id_producto(nombredepieza); 
                pst.setInt(1,id_producto);
                noguardaridrepetidoenstorage(id_producto);
               pst.setString(2,nombredepieza);
                                 pst.setFloat(3,cantidaddeproductos);    
                //EL METODO A CONTINUACION VA HACIENDO EL CONTEO DE LAS PIEZAS INDIVIDUALES
                // PARA UNA VEZ LLEGANDO A UN POLLO ENTERO DESCONTARLO DE LA BASE           
                precio_producto(nombredepieza);
               pst.setFloat(4,precio);
               if(nombredepieza.equals("Pollo rostizado")&&cantidaddeproductos==0.50){
                    importe = ((float)cantidaddeproductos*100);        
                } else if(nombredepieza.equals("Pollo rostizado")&&cantidaddeproductos==0.25){
                       importe = ((float)cantidaddeproductos*88);        
                }
                else if(nombredepieza.equals("Pollo asado")&&cantidaddeproductos==0.50){
                 importe = ((float)cantidaddeproductos*100);        
                } else if(nombredepieza.equals("Pollo asado")&&cantidaddeproductos==0.25){
                           importe = ((float)cantidaddeproductos*88);        
             }else{ importe = (float)cantidaddeproductos*precio;}
                pst.setFloat(5,importe);
               id_max_de_venta();
                pst.setInt(6,(id_de_la_venta_incrementable));
                pst.setString(7, estadoenturno);
                pst.setString(8, fecha());
                int a=pst.executeUpdate();
                if(a>0){
                   Controladorventa.accionesdespuesinsertarendescripciondeventaoactualizarenlamismatabla(nombredepieza, cantidaddeproductos);
        
                }else{//CUANDO NO SE PUDO INSERTAR
                   }
            }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
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
    public static void insertorupdateoverbonnie(String nombredepieza, float cantidaddeproductos){
   obtenerelnombredeproductoylacantidaddelmismo_en_descripcion_deventa(nombredepieza);
if(NoP.equals(nombredepieza)){ //Si el nombre del producto es diferente del estado vacio, en palabras más sencillas; si se encuentra el producto que se quiere agregar para que no se asigne nuevamente  
     try{Connection ca= cc.conexion();// ESTE ES PARA EL UPDATE
          obtenerelnombredeproductoylacantidaddelmismo_en_descripcion_deventa(nombredepieza);
               NoPcantidad=1;
                NoPimporte+= +(1*cantidaddeproductos);
         id_producto(nombredepieza);
                    id_max_de_venta();
                 PreparedStatement ps = ca.prepareStatement ("UPDATE descripcion_de_venta SET cantidad='"+NoPcantidad+"',importe = '"+NoPimporte+"'WHERE id_producto='"+id_producto+"' and id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and estado= '"+estadoenturno+"'");
               int a=  ps.executeUpdate();
               if(a>0){      
                   Controladorventa.accionesdespuesinsertarendescripciondeventaoactualizarenlamismatabla(nombredepieza, cantidaddeproductos);
                   
               }else{
                   JOptionPane.showMessageDialog(null, " NO SE PUDO ACTIALIZAR");
               }
        }//fin del id del usuario//fin del id del usuario
                 catch(Exception w){
                     JOptionPane.showMessageDialog(null, "Error en todo el codigo de update de metodo comprobar_registro" + w.getMessage());
                 }finally{cc.getClose();}//fin del id del usuario
               }
   else{
  try{Connection ca= cc.conexion(); //la insersion a la tabla ventas
                String sql = "INSERT INTO descripcion_de_venta (id_producto,nombre_producto,cantidad,precio_unitario,importe,id_venta,estado, fecha)  VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement pst = ca.prepareCall(sql); //hasta aqui vamos
                id_producto(nombredepieza); 
                pst.setInt(1,id_producto);
                noguardaridrepetidoenstorage(id_producto);
                 pst.setString(2,nombredepieza);
                pst.setFloat(3,1);
                pst.setFloat(4,0);
                importe = (float)1*cantidaddeproductos;     
                pst.setFloat(5,importe);
                id_max_de_venta();
                pst.setInt(6,(id_de_la_venta_incrementable));
                pst.setString(7, estadoenturno);
                pst.setString(8, fecha());
                int a=pst.executeUpdate();
                if(a>0){
                     Controladorventa.accionesdespuesinsertarendescripciondeventaoactualizarenlamismatabla(nombredepieza, cantidaddeproductos);
                  //DESCUENTOACTIVO            
                }else{//CUANDO NO SE PUDO INSERTAR
                   }
            }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
            }finally{cc.getClose();}//fin de la insersion a la tabla ventas
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
  public static void descontardeinventario(String nombredepieza, float cantidaddeproductos){
               id_producto(nombredepieza);
               cantidadpolloenDByname(id_producto);
               if(cantidadpolloenDB>=cantidaddeproductos){
              try{Connection ca= cc.conexion();// el id del usuario
               cantidadpolloenDB=cantidadpolloenDB-cantidaddeproductos; 
               id_producto(nombredepieza);
                 PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+cantidadpolloenDB+"'WHERE id_producto='"+id_producto+"'");
                ps.executeUpdate();
                 }//fin del id del usuario
                 catch(Exception w){
                 JOptionPane.showMessageDialog(null, "Error en descontardeinventario","DEVELOPER HELPER",JOptionPane.INFORMATION_MESSAGE);
                 }finally{
                  cc.getClose();
             }//fin del id del usuario
               }
    }
  public static void cantidadpolloenDByname(int pieza){
    try{Connection ca= cc.conexion();
 sent  = (Statement)ca.createStatement();
   rs = sent.executeQuery("select * from productos  where id_producto='"+pieza+"'");
     if(rs.next()){
        cantidadpolloenDB =rs.getFloat("cantidad"); // piezas en la db
     }                                          
    }catch (Exception f){
                    JOptionPane.showMessageDialog(null, "Error, cantidadpolloenDByname","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
         }finally{
                  cc.getClose();
             }
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
                       rs= sent.executeQuery("select * from  descripcion_de_venta where id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and  estado = '"+estadoenturno+"'"); // se ejecuta la sentencia dentro del parentesis
            while(rs.next()){        
            datos[0]=rs.getString(3);
            datos[1]=rs.getString(4);
            datos[2]=rs.getString(5);
            datos[3]=rs.getString(6);
            modelo.addRow(datos); //se asigna el arreglo  entero a todo el objeto llamado modelo  
            }
          nucleo.tablaventa.setModel(modelo); // Se vuelve a enviar nuevamente el objeto modelo a la tabla
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Error, mostrartabladeventas","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
                                         } finally{
                  cc.getClose();
             }
    }
   public static void ids_y_cantidades_porcancelacion(short id){
try {Connection ca= cc.conexion();
        id_max_de_venta();
             sent = ca.createStatement();   
                 rs= sent.executeQuery("select id_producto, cantidad from  descripcion_de_venta where id_venta= '"+id+"' and fecha= '"+fecha()+"' and  estado = '"+estadocancelado+"'"); // se ejecuta la sentencia dentro del parentesis
            while(rs.next()){        
            idsenturno.add(0, rs.getInt(1));
            cantidaddecadaidenturno.add(0, rs.getFloat(2));
            }
  regresar_cantidades_porcancelacion();//SE REGRESAN LAS CANTIDADES
  idsenturno.clear();
            cantidaddecadaidenturno.clear();
 //SE ALMACENÓ LA VENTA COMO EN TURNO Y EL METODO ANTERIOR A ESTE NO LA PUEDE DETECTAR PORQUE SOLO ELIMINA LAS VENTA EN TURNO DEL DÍA, NO DEL DÍA DE AYER NI DEL PASADO
} catch (SQLException ex) {
            System.out.println();
      JOptionPane.showMessageDialog(null, "ERROR EN METODO: ids_y_cantidades_enturno_por_error_de_usuario","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
     } finally{
                  cc.getClose();
             } 
    }
   public static void regresar_cantidades_porcancelacion(){
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
   // CONSULTA DE PRODUCTOS CON PRECIOS PARA LA VENTA           
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
                 columna[2] = (int) rs.getFloat(3);
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
                       
 for(ciclofor=0; ciclofor<piezas.length; ciclofor++) {//RECORRIENDO EL ARREGLO DE POLLOS
      try{    Connection ca= cc.conexion(); // el id del usuario
                if(piezas[ciclofor].equals("Muslo")||
                   piezas[ciclofor].equals("Pierna")||
                   piezas[ciclofor].equals("Ala")||
                   piezas[ciclofor].equals("Patas")){
               PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+((cantidaddesdelatablaeditable*2))+"'WHERE nombre_producto='"+piezas[ciclofor]+"'");
               int ty = ps.executeUpdate();      
                 if(ty>0){
                     ParaLAVenta(Entradaproductos.JtablepaLaVenta);  // ***********************                      
                 }else{ 
                 }
           } 
           else if(piezas[ciclofor].equals("Huacal")||piezas[ciclofor].equals("Cadera")||
                   piezas[ciclofor].equals("Cabeza")||
                   piezas[ciclofor].equals("Molleja")||piezas[ciclofor].equals("Pechuga")){
         PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+((cantidaddesdelatablaeditable*1))+"'WHERE nombre_producto='"+piezas[ciclofor]+"'");
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
}public static void llenartablaconventasacreditopendiente(){
        Object[] columna = new Object[4];  //crear un obj con el nombre de colunna
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  Inventarioventas.ventasacreditopendiente.setModel(modeloT);  // add modelo ala tabla 
       modeloT.addColumn("Venta");
        modeloT.addColumn("Total");        
        modeloT.addColumn("Fecha");
        modeloT.addColumn("Nombre"); 
       try {   Connection ca= cc.conexion(); // CONEXION DB 
String sSQL = " select distinct venta.id_venta, venta.total, venta.fecha_reporte, descripcion_de_venta.nombre_credito from venta INNER JOIN descripcion_de_venta ON venta.id_venta = descripcion_de_venta.id_venta where venta.estado_venta = 'Credito-pendiente' ";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                columna[0] = rs.getString("venta.id_venta");
                columna[1] = rs.getString("venta.total");
                columna[2] = rs.getString("venta.fecha_reporte");    
                columna[3] = rs.getString("descripcion_de_venta.nombre_credito");
                modeloT.addRow(columna);
            }
        }
    } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "ERROR EN METODO: llenartablaconventasacreditopendiente"+e.getStackTrace(),"DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{
                  cc.getClose();
             }
    } public static void llenartablaidventasconidrealizados(){ // recibe como parametro 
         Object[] columna = new Object[3];  //crear un obj con el nombre de colunna
             DefaultTableModel modeloTE = new DefaultTableModel(); 
                  Inventarioventas.jTable2.setModel(modeloTE);  // add modelo ala tabla 
        modeloTE.addColumn("Venta");
        modeloTE.addColumn("Total");        
        modeloTE.addColumn("Fecha");
    Inventarioventas.jTable2.setModel(modeloTE);  // add modelo ala tabla         
        try {  Connection ca= cc.conexion(); // CONEXION DB 
             String sSQL = "SELECT id_venta, total, fecha_reporte FROM venta WHERE estado_venta in('Realizada') AND fecha_reporte = '"+fecha()+"' ";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
                columna[0] = rs.getInt(1);
                columna[1] = rs.getFloat(2);
                 columna[2] = rs.getString(3);
                modeloTE.addRow(columna);
            }
          Inventarioventas.jTable2.setModel(modeloTE);  // add modelo ala tabla 
    } catch (Exception e) {
      JOptionPane.showMessageDialog(null, "ERROR EN METODO: llenartablaidventasconidrealizados","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
       }finally{
                  cc.getClose();
             }
}
       public static void totaldelasventasdehoy(){
        try{ Connection ca= cc.conexion();// La suma de todos los importes
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select SUM(total) from venta where fecha_reporte= '"+Controladorventa.fecha()+"' AND estado_venta='Realizada' ");
                                            if(rs.next()){
                                                      sumadetotalesdeventasdehoy =rs.getFloat(1);
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                           JOptionPane.showMessageDialog(null, "ERROR EN METODO: totaldelasventasdehoy","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);       
                                                      }// fin del precio-catch del producto
        finally{
                  cc.getClose();
             }
    }
       
       public static void conteodeventasrealizadasdehoy(){
        try{ Connection ca= cc.conexion();// CUENTA EL TODAL DE CUANTAS VENTAS SE REALIZARON
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("SELECT COUNT(id_venta) FROM venta WHERE fecha_reporte = '"+Controladorventa.fecha()+"' AND estado_venta='Realizada'");
                                            if(rs.next()){
                                                      conteototaldeventas =Short.parseShort(String.valueOf(rs.getInt("COUNT(id_venta)")));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                           JOptionPane.showMessageDialog(null, "ERROR EN METODO: conteodeventasrealizadasdehoy","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
                                                      }// fin del precio-catch del producto
        finally{
                  cc.getClose();
             }
    }
       // CONSULTA DE PRODUCTOS MAS DENVIDOS            
     public static void productosmasvendidos(JTable tablaD){ // recibe como parametro 
         Object[] columna = new Object[3];  //crear un obj con el nombre de colunna
           DefaultTableModel modeloTE = new DefaultTableModel(); 
                  tablaD.setModel(modeloTE);  // add modelo ala tabla 
        modeloTE.addColumn("Nombre");
        modeloTE.addColumn("PIezas");        
        modeloTE.addColumn("Fecha");
        try {   Connection ca= cc.conexion(); // CONEXION DB 
         String sSQL = "SELECT nombre_producto, SUM(cantidad), fecha FROM descripcion_de_venta WHERE estado='Realizada' AND fecha = '"+fecha()+"' GROUP BY nombre_producto ORDER by SUM(cantidad) DESC";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                columna[0] = rs.getString(1);
                columna[1] = rs.getFloat(2);
                 columna[2] = rs.getString(3);
                //columna[5] = rs.getString("nombre");                
                modeloTE.addRow(columna);
            }
        } } catch (Exception e) {
       JOptionPane.showMessageDialog(null, "ERROR EN METODO: productosmasvendidos","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{
                  cc.getClose();
             }
}
     public static void productosvendidoseneldia(){ // recibe como parametro 
         Object[] columna = new Object[3];  //crear un obj con el nombre de colunna
          DefaultTableModel modeloT = new DefaultTableModel(); 
                  Inventarioventas.jTable3.setModel(modeloT);  // add modelo ala tabla 
        modeloT.addColumn("Producto");
        modeloT.addColumn("Cantidad");        
        modeloT.addColumn("importe");
          Inventarioventas.jTable3.setModel(modeloT);  // add modelo ala tabla 
         try {   Connection ca= cc.conexion(); // CONEXION DB 
         String sSQL = "SELECT nombre_producto, SUM(cantidad), SUM(importe) FROM  descripcion_de_venta WHERE estado in('Realizada') AND fecha = CURDATE() GROUP BY nombre_producto";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
                columna[0] = rs.getString(1);
                columna[1] = rs.getString(2);
                columna[2] = rs.getString(3);                
                modeloT.addRow(columna);
            } Inventarioventas.jTable3.setModel(modeloT);  // add modelo ala tabla 
    } catch (Exception e) { JOptionPane.showMessageDialog(null, "ERROR EN METODO: productosvendidoseneldia","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{
                  cc.getClose();
             }
}
      
      public static void cantidadenventasumadecantidadesfinales(int pieza){
    id_max_de_venta();  //Cantidad en venta
                try{Connection ca= cc.conexion();
                sent  =(Statement)ca.createStatement(); 
                     rs = sent.executeQuery("select SUM(cantidad) from descripcion_de_venta where id_producto= '"+pieza+"'AND estado='"+estadoenturno+"'and id_venta='"+id_de_la_venta_incrementable+"'and fecha='"+fecha()+"'  ");       
                if(rs.next()){    
                    cantidadenventasumada =rs.getFloat("SUM(cantidad)");      
            
                }
                }catch(Exception e){
                }finally{
                    cc.getClose();
                }
}
       public static void showidventasporfechas(JTable tablaventas, String fechadesde, String fechahasta){
        Object[] columna = new Object[3];  //crear un obj con el nombre de colunna
            Connection ca= cc.conexion(); // CONEXION DB 
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  tablaventas.setModel(modeloT);  // add modelo ala tabla 
           modeloT.addColumn("Venta");
        modeloT.addColumn("Total");        
        modeloT.addColumn("Fecha");
    try {
           String sSQL = "SELECT id_venta, total, fecha_reporte FROM venta WHERE estado_venta='Realizada' AND fecha_reporte BETWEEN '"+fechadesde+"' AND '"+fechahasta+ "' ";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                columna[0] = rs.getString(1);
                columna[1] = rs.getInt(2);
                   columna[2] = rs.getString(3);
                modeloT.addRow(columna);
            }
        }
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Advertencia", JOptionPane.PLAIN_MESSAGE);    
    }finally{cc.getClose();}
    }
       public static void descripciondeproductosenbasealnumerodeventaporcreditopendiente(int numerodeventa){
        Object[] columna = new Object[4];  //crear un obj con el nombre de colunna
            Connection ca= cc.conexion(); // CONEXION DB 
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  Inventarioventas.ventasacreditopendiente.setModel(modeloT);  // add modelo ala tabla 
       // modeloT.addColumn("id_venta");    // add al modelo las 5 columnas con los nombrs TABLA
        modeloT.addColumn("Nombre");
        modeloT.addColumn("Piezas");        
        modeloT.addColumn("Precio");
        modeloT.addColumn("Importe");
       try {
               String sSQL = "SELECT nombre_producto, cantidad, precio_unitario, importe, nombre_credito FROM descripcion_de_venta WHERE estado='Credito-pendiente' AND id_venta = '"+numerodeventa+"' ";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                columna[0] = rs.getString(1);
                columna[1] = rs.getString(2);
                columna[2] = rs.getString(3);      
                columna[3] = rs.getString(4); 
                Inventarioventas.deudor.setText(rs.getString(5));
                modeloT.addRow(columna);
            }
        }
       
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Advertencia", JOptionPane.PLAIN_MESSAGE);    
    }finally{cc.getClose();}
    }public static void descripciondeproductosenbasealnumerodeventa(int numerodeventa){
        Object[] columna = new Object[4];  //crear un obj con el nombre de colunna
            Connection ca= cc.conexion(); // CONEXION DB 
              DefaultTableModel modeloT = new DefaultTableModel(); 
                  Inventarioventas.jTable2.setModel(modeloT);  // add modelo ala tabla 
        modeloT.addColumn("Nombre");
        modeloT.addColumn("Piezas");        
        modeloT.addColumn("Precio");
        modeloT.addColumn("Importe");
            Inventarioventas.jTable2.setModel(modeloT);  // add modelo ala tabla 
      try { String sSQL = "SELECT nombre_producto, cantidad, precio_unitario, importe FROM descripcion_de_venta WHERE estado='Realizada' AND id_venta = '"+numerodeventa+"' ";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
               // columna[0] = rs.getString("id_venta");  /* === LA DB == */
                columna[0] = rs.getString(1);
                columna[1] = rs.getString(2);
                columna[2] = rs.getString(3);      
                columna[3] = rs.getString(4); 
                modeloT.addRow(columna);
            }    Inventarioventas.jTable2.setModel(modeloT);  // add modelo ala tabla 
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Advertencia", JOptionPane.PLAIN_MESSAGE);    
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
                            tablaventaactiva=false;
                            nucleo.cambiocombobox.setText(String.valueOf(cambio=variablepago-Float.parseFloat(nucleo.total.getText())));
                            block_unlock=true;
                            try{Connection ca= cc.conexion();// el id del usuario
                                id_max_de_venta();
                                 PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET subtotal='"+solodosdecimales.format(Float.parseFloat(nucleo.subtotal.getText())).replace(",", ".")+"',total='"+solodosdecimales.format(Float.parseFloat(nucleo.total.getText())).replace(",", ".")+"',descuento='"+solodosdecimales.format(Float.parseFloat(nucleo.descuentocombo.getText())).replace(",", ".")+"',pago='"+variablepago+"',cambio='"+solodosdecimales.format(Float.parseFloat(nucleo.cambiocombobox.getText())).replace(",", ".")+"',fecha_reporte='"+fecha()+"',estado_venta='"+estadorealizado+"'WHERE id_venta='"+id_de_la_venta_incrementable+"'");
  ps.executeUpdate();
                              //ACTUALIZACION EN LA TABLA DESCRIPCION DE VENTA A REALIZADA
                                id_max_de_venta();
                                try{
                                    id_max_de_venta();
                                    PreparedStatement ps2 = ca.prepareStatement ("UPDATE descripcion_de_venta SET estado= '"+estadorealizado+"' WHERE id_venta='"+id_de_la_venta_incrementable+"'");
                                    int result = ps2.executeUpdate();
                                         if(result>0){
                                            Modelogastos.insertarengastos("Descuento",Float.parseFloat(nucleo.descuentocombo.getText()));
                                              JOptionPane.showMessageDialog(null, "El cambio es de: "+nucleo.cambiocombobox.getText()," Se realizo una venta",JOptionPane.YES_OPTION);
                      if(nucleo.reimprimirventa.isSelected()){        descripciondelosprouductosparaelticketdeventa(id_de_la_venta_incrementable);//DATOS PARA EL TICKET DE VENTA          
                                           descripciondelosprouductosparaelticketdeventa(id_de_la_venta_incrementable);//DATOS PARA EL TICKET DE VENTA          
                                  Controladorventa.accionesdespuesderealizarcualquierventa(); }else{descripciondelosprouductosparaelticketdeventa(id_de_la_venta_incrementable);//DATOS PARA EL TICKET DE VENTA          
                                      Controladorventa.accionesdespuesderealizarcualquierventa();}
                                         }
                                }
                                catch(Exception ex){
                                    JOptionPane.showMessageDialog(null, "Error en metodo_de_cobro update desc" + ex.getMessage());
                                }
                                //autocompletar();
                            }//fin del id del usuario//fin del id del usuario//fin del id del usuario//fin del id del usuario
                            catch(Exception w){
                                JOptionPane.showMessageDialog(null,"error en metodo_de_cobro update venta"+w);
                            }finally{cc.getClose();}
                        }
                    } //FIN DE CUANDO EL DESCUENTO ESTÁ ACTIVO
else{ //CUANDO EL DESCUENTO NO ESTÁ ACTIVO
if(variablepago<Float.parseFloat(nucleo.subtotal.getText())){ // comprueba que la cantidad recibida sea mayor al total
                            JOptionPane.showMessageDialog(null,"El pago es menor a la cantidad a pagar, por favor, verifique","Advertencia",JOptionPane.INFORMATION_MESSAGE);
                        }
                        else {
                            tablaventaactiva=false;
                            nucleo.cambiocombobox.setText(String.valueOf(cambio=variablepago-sumadeimportesenturno));
                            block_unlock=true;
                            try{Connection ca= cc.conexion();// el id del usuario
                                id_max_de_venta();
                           PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET subtotal='"+solodosdecimales.format(Float.parseFloat(nucleo.subtotal.getText())).replace(",", ".")+"',total='"+solodosdecimales.format(Float.parseFloat(nucleo.total.getText())).replace(",", ".")+"',descuento='"+0+"',pago='"+variablepago+"',cambio='"+solodosdecimales.format(Float.parseFloat(nucleo.cambiocombobox.getText())).replace(",", ".")+"',fecha_reporte='"+fecha()+"',estado_venta='"+estadorealizado+"'WHERE id_venta='"+id_de_la_venta_incrementable+"'");
 ps.executeUpdate();
                                //ACTUALIZACION EN LA TABLA DESCRIPCION DE VENTA A REALIZADA
                                id_max_de_venta();
                                try{
                                    id_max_de_venta();
                                    PreparedStatement ps2 = ca.prepareStatement ("UPDATE descripcion_de_venta SET estado= '"+estadorealizado+"' WHERE id_venta='"+id_de_la_venta_incrementable+"'");
                                   int resultado=  ps2.executeUpdate();
                                     if(resultado>0){
                                             JOptionPane.showMessageDialog(null, "El cambio es de: "+nucleo.cambiocombobox.getText()," Se realizo una venta",JOptionPane.YES_OPTION);
                               if(nucleo.reimprimirventa.isSelected()){        descripciondelosprouductosparaelticketdeventa(id_de_la_venta_incrementable);//DATOS PARA EL TICKET DE VENTA          
                                           descripciondelosprouductosparaelticketdeventa(id_de_la_venta_incrementable);//DATOS PARA EL TICKET DE VENTA          
                                  Controladorventa.accionesdespuesderealizarcualquierventa(); }else{descripciondelosprouductosparaelticketdeventa(id_de_la_venta_incrementable);//DATOS PARA EL TICKET DE VENTA          
                                      Controladorventa.accionesdespuesderealizarcualquierventa();}
                                             
                                     }
                                }
                                catch(Exception ex){
                                    JOptionPane.showMessageDialog(null, "Error en metodo_de_cobro sin desc update desc" + ex.getMessage());
                                
                                }
                                //autocompletar();
                            }//fin del id del usuario//fin del id del usuario//fin del id del usuario//fin del id del usuario
                            catch(Exception w){
                                JOptionPane.showMessageDialog(null,"error en id usuario"+w);
                            }finally{
                                cc.getClose();
                            }
                        }
                    } //FIN CUANDO EL DESCUENTO NO ESTÁ ACTIVO
                }
                else if(nucleo.subtotal.getText().isEmpty()){
                    JOptionPane.showMessageDialog(null,"Aún no hay nada por pagar","!Espera!",JOptionPane.INFORMATION_MESSAGE);
                }
            }catch(Exception NFE){//Number format exception para cuando el usuario no ingrese ningun dato en la caja
                JOptionPane.showMessageDialog(null,"No tiene valor la cantidad recibida","!Espera!",JOptionPane.INFORMATION_MESSAGE);
}
    }

public static void descripciondelosprouductosparaelticketdeventacredito(int numerodeventa){
         String nombre="";
                try {Connection ca= cc.conexion();
                 String sSQL = "SELECT nombre_producto, cantidad, precio_unitario, importe, nombre_credito FROM descripcion_de_venta WHERE estado='Credito-pendiente' AND id_venta = '"+numerodeventa+"' ";  
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
              nombreproductoticket.add(rs.getString(1));
              piezastcket.add(rs.getString(2));
              preciounitarioticket.add(rs.getString(3)); 
              importesticket.add(rs.getString(4));
              nombre=rs.getString(5);
            }
            total_pagoycambiopararelticketdeventacredito(numerodeventa);
                 //estas dos lineas mandan los datos para el ticket
                 mandardatosticketventacredito = new ticketventacredito();    
                 mandardatosticketventacredito.tikectdeventaacredito(nombre, nombreproductoticket, 
                   piezastcket, 
                         preciounitarioticket, 
                         importesticket,
                         subtotalticket, totalticket, pagoticket, cambioticket, numerodeventa);
 Controladorventa.vaciarlistasdeticket();
    } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "ERROR EN METODO: descripciondelosprouductosparaelticketdeventacredito","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{
                  cc.getClose();
             }
    }
public static void total_pagoycambiopararelticketdeventacredito(int id){ // recibe como parametro 
         Object[] columna = new Object[5];  //crear un obj con el nombre de colunna
        try {Connection ca= cc.conexion();
         String sSQL = "SELECT subtotal, total, pago, cambio, descuento FROM venta WHERE estado_venta='"+creditopendiente+"' AND fecha_reporte = '"+fecha()+"' and id_venta='"+id+"' ";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
               subtotalticket = rs.getFloat(1);
              totalticket = rs.getFloat(2);
                pagoticket = rs.getFloat(3);
                 cambioticket = rs.getFloat(4);
                 descuentoticket = rs.getFloat(5);           
          }  
    } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "ERROR EN METODO: total_pagoycambiopararelticketdeventacredito","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
       }finally{
                  cc.getClose();
             }
}
public static void descripciondelosprouductosparaelticketdeventa(int numerodeventa){
         try {Connection ca= cc.conexion();
                 String sSQL = "SELECT nombre_producto, cantidad, precio_unitario, importe FROM descripcion_de_venta WHERE estado='Realizada' AND id_venta = '"+numerodeventa+"' ";  
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
              nombreproductoticket.add(rs.getString(1));
              piezastcket.add(rs.getString(2));
              preciounitarioticket.add(rs.getString(3)); 
              importesticket.add(rs.getString(4));
            }
            total_pagoycambiopararelticketdeventa(numerodeventa);
            if(descuentoactivo==true){
                            //estas dos lineas mandan los datos para el ticket
                 mandardatosaticketventacondescuento = new ticketventacondescuento();
                 mandardatosaticketventacondescuento.tikectdeventacondescuento(nombreproductoticket, 
                         piezastcket, 
                         preciounitarioticket, 
                         importesticket,
                         subtotalticket, totalticket, pagoticket, cambioticket, descuentoticket, numerodeventa);
            //totalcdescticket agregar al metodo de arriba
                 Controladorventa.vaciarlistasdeticket();
            }else{//venta simple
                 //estas dos lineas mandan los datos para el ticket
                 mandardatosticketventa = new ticketventa();
                 mandardatosticketventa.tikectdeventa(nombreproductoticket, 
                   piezastcket, 
                         preciounitarioticket, 
                         importesticket,
                         subtotalticket, totalticket, pagoticket, cambioticket, numerodeventa);
 Controladorventa.vaciarlistasdeticket();
            }
         } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "ERROR EN METODO: descripciondelosprouductosparaelticketdeventa","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{
                  cc.getClose();
             }
    }
public static void total_pagoycambiopararelticketdeventa(int id){ // recibe como parametro 
         Object[] columna = new Object[5];  //crear un obj con el nombre de colunna
        try {Connection ca= cc.conexion();
         String sSQL = "SELECT subtotal, total, pago, cambio, descuento FROM venta WHERE estado_venta='"+estadorealizado+"' AND fecha_reporte = '"+fecha()+"' and id_venta='"+id+"' ";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
               subtotalticket = rs.getFloat(1);
              totalticket = rs.getFloat(2);
                pagoticket = rs.getFloat(3);
                 cambioticket = rs.getFloat(4);
                 descuentoticket = rs.getFloat(5);           
          }  
    } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "ERROR EN METODO: total_pagoycambiopararelticketdeventa","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
       }finally{
                  cc.getClose();
             }
}
public static void reimpresiondeventa(int numerodeventa){
         try {Connection ca= cc.conexion();
                 String sSQL = "SELECT nombre_producto, cantidad, precio_unitario, importe FROM descripcion_de_venta WHERE estado='Realizada' AND id_venta = '"+numerodeventa+"' ";  
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
              nombreproductoticket.add(rs.getString(1));
              piezastcket.add(rs.getString(2));
              preciounitarioticket.add(rs.getString(3)); 
              importesticket.add(rs.getString(4));
            }
            total_pagoycambiopararelticketdeventa(numerodeventa);
                            //estas dos lineas mandan los datos para el ticket
                 mandardatosaticketventacondescuento = new ticketventacondescuento();
                 mandardatosaticketventacondescuento.tikectdeventacondescuento(nombreproductoticket, 
                         piezastcket, 
                         preciounitarioticket, 
                         importesticket,
                         subtotalticket, totalticket, pagoticket, cambioticket, descuentoticket, numerodeventa);
            //totalcdescticket agregar al metodo de arriba
                 Controladorventa.vaciarlistasdeticket();
    } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "ERROR EN METODO: reimpresiondeventa","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{
                  cc.getClose();
             }
    }
public static void impresiondeventacancelada(int numerodeventa){
    try {Connection ca= cc.conexion();
                 String sSQL = "SELECT nombre_producto, cantidad, precio_unitario, importe FROM descripcion_de_venta WHERE estado='Cancelada' AND id_venta = '"+numerodeventa+"' ";  
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
              nombreproductoticket.add(rs.getString(1));
              piezastcket.add(rs.getString(2));
              preciounitarioticket.add(rs.getString(3)); 
              importesticket.add(rs.getString(4));
            }total_pagoycambiopararelticketdeventacancelada(numerodeventa);
                            //estas dos lineas mandan los datos para el ticket
                 mandardatosticketventacancelada = new ticketventacancelada();
                 mandardatosticketventacancelada.tikectventacancelada(nombreproductoticket, 
                         piezastcket, 
                         preciounitarioticket, 
                         importesticket,
                         subtotalticket, totalticket, pagoticket, cambioticket, descuentoticket, numerodeventa);
            //totalcdescticket agregar al metodo de arriba
                 Controladorventa.vaciarlistasdeticket();
    } catch (Exception e) {
          JOptionPane.showMessageDialog(null, "ERROR EN METODO: reimpresiondeventa","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
      }finally{
                  cc.getClose();
             }
    }
public static void total_pagoycambiopararelticketdeventacancelada(int id){ // recibe como parametro 
         Object[] columna = new Object[5];  //crear un obj con el nombre de colunna
        try {Connection ca= cc.conexion();
         String sSQL = "SELECT subtotal, total, pago, cambio, descuento FROM venta WHERE estado_venta='"+estadocancelado+"' AND fecha_reporte = '"+fecha()+"' and id_venta='"+id+"' ";
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        ResultSet rs = ps.executeQuery(sSQL);
            while (rs.next()) {
               subtotalticket = rs.getFloat(1);
              totalticket = rs.getFloat(2);
                pagoticket = rs.getFloat(3);
                 cambioticket = rs.getFloat(4);
                 descuentoticket = rs.getFloat(5);           
          }  
    } catch (Exception e) {
         JOptionPane.showMessageDialog(null, "ERROR EN METODO: total_pagoycambiopararelticketdeventa","DEVELOPER HELPER", JOptionPane.ERROR_MESSAGE);      
       }finally{
                  cc.getClose();
             }
}
public static void descontartodaslaspechugasenbisteck(float cantidadpolloenDB){
    try{Connection ca= cc.conexion();
                            PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+cantidadpolloenDB+"'WHERE id_producto='"+15+"'");
                            ps.executeUpdate();
                        }catch(Exception s){
JOptionPane.showMessageDialog(null, "Error en descontartodaslaspechugasenbisteck" + s.getMessage());
                        } finally{
                    cc.getClose();
                }
}
public static void cantidadenventa(int pieza){
    id_max_de_venta();   //Cantidad en venta
                try{Connection ca= cc.conexion();
                sent  =(Statement)ca.createStatement(); 
                     rs = sent.executeQuery("select * from descripcion_de_venta where id_producto= '"+pieza+"'AND estado='"+estadoenturno+"'and id_venta='"+id_de_la_venta_incrementable+"'and fecha='"+fecha()+"'  ");       
                if(rs.next()){    
                    cantidadenventa =rs.getFloat("cantidad");      
                    
                }
                }catch(Exception e){
 JOptionPane.showMessageDialog(null, "Error, cantidadenventa","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
                }finally{
                  cc.getClose();
             }
} public static void regresarproductos_a_inventario(String nombredepieza){ // este metodo devuelve los productos que fueron agregados a la venta y posteriormente fueron cancelados
              id_max_de_venta();
                    block_unlock=true;   
                    //pendiente la restauracion de venta a inventario
                 id_producto(nombredepieza);
                  cantidadenventa(id_producto);
                       cantidadpolloenDByname(id_producto);
   cantidadpolloenDB+=cantidadenventa;
 id_producto(nombredepieza);
                        try{ Connection ca= cc.conexion();//SUMANDO A INVENTARIO EL ULTIMO, 
                            PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+cantidadpolloenDB+"'WHERE id_producto='"+id_producto+"'");
                            ps.executeUpdate();
                         }catch(Exception s){
JOptionPane.showMessageDialog(null, "Error en regresarproductos_a_inventario" + s.getMessage());
                        }finally{
                    cc.getClose();
                }// SUMANDO A INVENTARIO EL ULTIMO
                        //ELIMINAR DE VENTA EL ARTICULO
                        id_producto(nombredepieza);
                        id_max_de_venta();
                        try{Connection ca= cc.conexion();
            String sql = "DELETE from descripcion_de_venta where id_producto= '"+id_producto+"' and id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and estado= '"+estadoenturno+"' ";
            sent = ca.createStatement();
            int n = sent.executeUpdate(sql);
            if(n>0){
                id_producto(nombredepieza);
         Controladorventa.eliminarpolloenterodestorage(id_producto);
                Modeloventa.acciones_despues_de_regresaroagregaraventa();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR en regresarproductos_a_inventario" + e.getMessage());
        }finally{
                    cc.getClose();
                } //ELIMINAR DE VENTA EL ARTICULO     
}
public static void regresarproductos_pechugaenbisteck(String nombredepieza){ // este metodo devuelve los productos que fueron agregados a la venta y posteriormente fueron cancelados
              id_max_de_venta();
                    block_unlock=true;   
                    //pendiente la restauracion de venta a inventario
                    id_producto(nombredepieza);
                  cantidadenventa(57);
                       cantidadpolloenDByname(id_producto);
   cantidadpolloenDB+=cantidadenventa;
 id_producto(nombredepieza);
                        try{Connection ca= cc.conexion(); //SUMANDO A INVENTARIO EL ULTIMO, 
                            PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+cantidadpolloenDB+"'WHERE id_producto='"+id_producto+"'");
                            ps.executeUpdate();
                         }catch(Exception s){
JOptionPane.showMessageDialog(null, "update error en regresarproductos_pechugaenbisteck" + s.getMessage());
                        }finally{
                    cc.getClose();
                }// SUMANDO A INVENTARIO EL ULTIMO, 
                        //ELIMINAR DE VENTA EL ARTICULO
                        id_max_de_venta();
                        try{Connection ca= cc.conexion();
            String sql = "DELETE from descripcion_de_venta where id_producto= '"+57+"' and id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and estado= '"+estadoenturno+"' ";
            sent = ca.createStatement();
            int n = sent.executeUpdate(sql);
            if(n>0){
 Controladorventa.eliminarpolloenterodestorage(57);
                 Modeloventa.acciones_despues_de_regresaroagregaraventa();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "insert error en regresarproductos_pechugaenbisteck" + e.getMessage());
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
public static void regresarproductos_a_inventariodescontandotodaslaspiezas(){ // este metodo devuelve los productos que fueron agregados a la venta y posteriormente fueron cancelados
                 int piezasenventa=0;
                                  float piezasenlatablapiezas=0;
                    id_max_de_venta();
                    block_unlock=true;
                for(ciclofor=0;ciclofor<=storage.size()-1;ciclofor++){
                    if(storage.get(ciclofor).toString().equals("57")){
                         cantidadpolloenDByname(15);
                        cantidadenventasumadecantidadesfinales(57);
                       cantidadpolloenDB+=cantidadenventasumada;
                       descontartodaslaspechugasenbisteck(cantidadpolloenDB);
                    }
                 cantidadpolloenDByname(Integer.parseInt(storage.get(ciclofor).toString()));
                        cantidadenventasumadecantidadesfinales(Integer.parseInt(storage.get(ciclofor).toString()));
                       cantidadpolloenDB+=cantidadenventasumada;
                        try{Connection ca= cc.conexion();
                            PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+cantidadpolloenDB+"'WHERE id_producto='"+storage.get(ciclofor)+"'");
                            ps.executeUpdate();
                        }catch(Exception s){
JOptionPane.showMessageDialog(null, "Error en regresarproductos_a_inventariodescontandotodaslaspiezas" + s.getMessage());
                        }  finally{
                    cc.getClose();
                }
                 //PENDIENTE EL REGRESO DE POLLO A INVENTARIO 
                }//fin del ciclo for              
}
public  static void eliminarhuesito(int id){
        try{Connection ca= cc.conexion();
        String sql = "delete from descripcion_de_venta where id_producto = '"+id+"' ";
        PreparedStatement ps = ca.prepareStatement(sql);
       ps.execute();       
    }catch(Exception e){
        JOptionPane.showMessageDialog(null, "No se elimino huesito");
    }finally{
        cc.getClose();
    }
} 
public static void insertarventaacredito(){
    float totalparaventaacredito = Float.parseFloat(nucleo.subtotal.getText());
  if(totalparaventaacredito!=0){//SI EL TOTAL NO ES VACIO
            try{Connection ca= cc.conexion();
   String nombre="";
                boolean pass2 = Controladorventa.validarFormulariotexto(nombre=JOptionPane.showInputDialog(null,"¿A nombre de quien va ésta venta a credito?"));
                if(pass2){//ESTO VALIDA QUE EL TEXTO ESCRITO NO TENGA INCOHERENCIAS
                    int decision=JOptionPane.showConfirmDialog(null,"¿Desea continuar?","Estás por agregar una venta a credito",JOptionPane.CANCEL_OPTION);
                    if(decision==0){ //opción si
                        try{
                            comprobar_venta_resagada();//579 - 605 verifica que no haya una venta cancelada
                            get_id_usuario();// 255 -280
                            block_unlock=false;
                            tablaventaactiva=false;
 id_max_de_venta();
                            PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET total='"+solodosdecimales.format(Float.parseFloat(nucleo.total.getText())).replace(",", ".")+"',descuento='"+ solodosdecimales.format(Float.parseFloat(nucleo.descuentocombo.getText())).replace(",", ".")+"',pago='"+0+"',cambio='"+0+"',fecha_reporte='"+fecha()+"',estado_venta='"+creditopendiente+"'WHERE id_venta='"+id_de_la_venta_incrementable+"'");
                            ps.executeUpdate();
 }catch(Exception ex){
                            JOptionPane.showMessageDialog(null, "Error en insertarventaacredito venta" + ex.getMessage());
                        }
                        try{        Modelogastos.insertarengastos("Credito "+nombre, sumadeimportesenturno);
                    
                            id_max_de_venta();
                            PreparedStatement ps2 = ca.prepareStatement ("UPDATE descripcion_de_venta SET estado= '"+creditopendiente+"',nombre_credito='"+nombre+"' WHERE id_venta='"+id_de_la_venta_incrementable+"'");
                            ps2.executeUpdate();
   descripciondelosprouductosparaelticketdeventacredito(id_de_la_venta_incrementable);//DATOS PARA EL TICKET DE VENTA
                            descripciondelosprouductosparaelticketdeventacredito(id_de_la_venta_incrementable);//DATOS PARA EL TICKET DE VENTA
         block_unlock=true;
                            Controladorventa.accionesdespuesderealizarcualquierventa();
                     }
                        catch(Exception ex){
                            JOptionPane.showMessageDialog(null, "Error en insertarventaacredito update desc" + ex.getMessage());
                        }
                    }
                }
            }catch(NullPointerException NP){}
            finally{cc.getClose();}
  }//SI EL TOTAL NO ES VACIO
        else{//CUANDO EL TOTAL ES VACIO
            JOptionPane.showMessageDialog(null, "Aún no hay productos para hacer una venta a credito");
        }//CUANDO EL TOTAL ES VACIO
}
public static void total_ventaporid(int id){
        try{ Connection ca= cc.conexion();// La suma de todos los importes
                                          sent  =(Statement)ca.createStatement();
                                           rs = sent.executeQuery("select total from venta where id_venta= '"+id+"'");
                                            if(rs.next()){
                                                      sumadeimportesparaeltotal =Float.parseFloat(rs.getString("total"));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){                    JOptionPane.showMessageDialog(null, "Error en total_ventaporid" + e.getMessage());
                                                          sumadeimportesparaeltotal=0;
                                                      }finally{
                    cc.getClose();
                }// fin del precio-catch del producto
    }
public static void status_cancelado(int id){
        try{Connection ca= cc.conexion();
                    PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET estado_venta='"+estadocancelado+"'WHERE id_venta='"+id+"'");
                ps.executeUpdate();
        }
            catch (Exception e){ 
               JOptionPane.showMessageDialog(null, "Error en status_cancelado update venta" + e.getMessage());
            }   finally{
                    cc.getClose();
                }      
        try{Connection ca= cc.conexion();
            id_max_de_venta();
                PreparedStatement ps = ca.prepareStatement ("UPDATE descripcion_de_venta SET estado= '"+estadocancelado+"' WHERE id_venta='"+id+"'");
                ps.executeUpdate();
        }
        catch(Exception ex){
                           JOptionPane.showMessageDialog(null, "Error en status_cancelado update desc" + ex.getMessage());
        } finally{
                    cc.getClose();
                }    
   }
public static void pagarventacredito(int id_ventapencredito){
    DecimalFormat solodosdecimales = new DecimalFormat("#.##");
    try{
            String pagodeventacredito="";
            float variable0=0, totalacredito=0, cambio=0;
            boolean pass2 = Controladorventa.validarFormulario(pagodeventacredito= JOptionPane.showInputDialog(null,"Escriba el monto de pago","Pagando venta a credito", JOptionPane.INFORMATION_MESSAGE));
            if(pass2){//ESTO VALIDA QUE EL TEXTO ESCRITO NO TENGA INCOHERENCIAS
int decision=JOptionPane.showConfirmDialog(null,"¿Desea continuar?","Estás por pagar una venta a credito",JOptionPane.CANCEL_OPTION);
                if(decision==0){ //opción si
                    total_venta_creditopendiente(id_ventapencredito);
                    if(Float.parseFloat(pagodeventacredito)>=sumadeimportescreditopendiente){
                        try{Connection ca= cc.conexion();
                            cambio = Float.parseFloat(pagodeventacredito)-sumadeimportescreditopendiente;
                            PreparedStatement ps = ca.prepareStatement ("UPDATE venta SET descuento='"+ variable0+"',pago='"+solodosdecimales.format(Float.parseFloat(pagodeventacredito)).replace(",", ".")+"',cambio='"+solodosdecimales.format(cambio).replace(",", ".")+"',fecha_reporte='"+fecha()+"',estado_venta='"+creditopagado+"'WHERE id_venta='"+id_ventapencredito+"'");
                            ps.executeUpdate();
                        }catch(Exception ex){
                            JOptionPane.showMessageDialog(null, "Error en pagarventacredito" + ex.getMessage());
                        }finally{
                            cc.getClose();
                        }
                        try{Connection ca= cc.conexion();
                            id_max_de_venta();
                            PreparedStatement ps2 = ca.prepareStatement ("UPDATE descripcion_de_venta SET estado= '"+creditopagado+"' WHERE id_venta='"+id_ventapencredito+"'");
                            int a = ps2.executeUpdate();
                            if(a>0){
                                Controladorventa.accionesdespuesderealizarcualquierventa();
                                Inventarioventas.pagarventaacredito.setVisible(false);
                                Inventarioventas.labelnombre.setVisible(false);
                                Inventarioventas.labelcredito.setVisible(false);
                                Inventarioventas.totalventacreditoenturno.setVisible(false);
                                Inventarioventas.deudor.setVisible(false);
                                Inventarioventas.veridventasacreditopendiente.setVisible(false);
                                   Modeloventa.llenartablaconventasacreditopendiente(); //CARGA NUEVAMENTE LAS VENTAS POR ID
  }
                        }
                        catch(Exception ex){
                            JOptionPane.showMessageDialog(null, "Error en pagarventacredito" + ex.getMessage());
                        }finally{
                            cc.getClose();
                        }
                    }else{
                        JOptionPane.showMessageDialog(null, "El pago es menor al total", "Verifique por favor", JOptionPane.INFORMATION_MESSAGE);
                    }
    }
  }
        }catch(NullPointerException NP){//ESTO EVITA QUE EL USUARIO META UN VALOR VACIO
 }
}
 public static void total_venta_creditopendiente(int id){
        try{ Connection ca= cc.conexion();// La suma de todos los importes
                                          sent  =(Statement)ca.createStatement();
                                           rs = sent.executeQuery("select total from venta where id_venta= '"+id+"'");
                                            if(rs.next()){
                                                      sumadeimportescreditopendiente =Float.parseFloat(rs.getString("total"));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){ JOptionPane.showMessageDialog(null, "Error, total_venta_creditopendiente","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
                sumadeimportescreditopendiente=0;
                                                      }finally{
                    cc.getClose();
                }// fin del precio-catch del producto
    }
//METODO FINAL
 
 // CONSULTA DE PRODUCTOS EN EXITENCIA EN INVENTARIO            
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
                 columna[1] = (int) rs.getFloat(2);
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
                              id_producto(valor); 
                             cantidadpolloenDByname(id_producto);
                              cantidaddesdelatablaeditable+=cantidadpolloenDB;
                              String sql = "UPDATE productos SET cantidad='"+cantidaddesdelatablaeditable+"' WHERE id_producto="+id_producto;            
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
   // FIN DE METODOS PARA EL AREA DE VENTAS -----------------------------------------------------------------------------------------------------------------------------------------------------
 //METODO FINAL
}
