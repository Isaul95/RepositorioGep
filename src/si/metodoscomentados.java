/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package si;

/**
 *
 * @author Alexis
 */
public class metodoscomentados {
    /* ESTO VA A IR EN MENU PRINCIPAL
    METODOS PARA DESCONTAR 1 POLLO  O N POLLOS EN BASE A LAS PIEZAS QUE SE HAN DESCONTADO
             public static void descuentodepollo(){
                 minimodelaspiezasdepollocrudoquesoninparesentablaproductos();
                 minimodelaspiezasdepollocrudoquesonparesentablaproductos();
                 pollocrudoeninventario();
                 if(minimodelaspiezasinparesdepollocrudoeninventario==0){
                     actualizarpollocrudoeninventario(0);
                 }
                 else if(minimodelaspiezasparesdepollocrudoeninventario==1){
                      actualizarpollocrudoeninventario(0);
                 }
                 else if(pollo_crudoeninventario!=minimodelaspiezasinparesdepollocrudoeninventario&&
                         minimodelaspiezasparesdepollocrudoeninventario!=(minimodelaspiezasinparesdepollocrudoeninventario*2)&&
                         pollo_crudoeninventario>minimodelaspiezasinparesdepollocrudoeninventario){
                      actualizarpollocrudoeninventario(minimodelaspiezasinparesdepollocrudoeninventario);
                  
                 }else if(pollo_crudoeninventario==minimodelaspiezasinparesdepollocrudoeninventario&&
                         minimodelaspiezasparesdepollocrudoeninventario==(minimodelaspiezasinparesdepollocrudoeninventario*2)||
                         pollo_crudoeninventario==minimodelaspiezasinparesdepollocrudoeninventario&&
                         minimodelaspiezasparesdepollocrudoeninventario!=(minimodelaspiezasinparesdepollocrudoeninventario*2)&&
                         minimodelaspiezasparesdepollocrudoeninventario>(minimodelaspiezasinparesdepollocrudoeninventario*2)){
                       actualizarpollocrudoeninventario(minimodelaspiezasinparesdepollocrudoeninventario);
                 }
                 else{
                     if(minimodelaspiezasparesdepollocrudoeninventario%2!=0){
                         actualizarpollocrudoeninventario((minimodelaspiezasparesdepollocrudoeninventario-1)/2);
                     }else{
                          actualizarpollocrudoeninventario(minimodelaspiezasparesdepollocrudoeninventario/2); 
                     }
                     //ESTO ELIMINA LOS DECIMALES DEL SOBRANTE
                    //AQUI EL LINK
                    //https://www.yoelprogramador.com/como-usar-el-formateador-decimal-en-java/
                     java.text.DecimalFormat formatoSalida = new java.text.DecimalFormat("0");//para ningun decimal
                     Float.parseFloat(formatoSalida.format(minimodelaspiezasparesdepollocrudoeninventario/2));
                 }
             }
             public static void actualizarpollocrudoeninventario(float actualizaciondepollo){
                 try{              Connection ca= cc.conexion();     
           PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+actualizaciondepollo+"'WHERE nombre_producto='"+pollo_crudo+"'");
                  int a = ps.executeUpdate();
                if(a>0){    
                }
                           }catch(Exception e){
    JOptionPane.showMessageDialog(null, "Error, actualizarpollocrudoeninventario","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE); 
                           }finally{
                  cc.getClose();
             }
             }
             
     public static void pollocrudoeninventario(){
              String consulta="select cantidad from productos WHERE nombre_producto = 'pollo crudo' ";
              try{    Connection ca= cc.conexion();     
                     sent  = (Statement)ca.createStatement();
                                             rs = sent.executeQuery(consulta);
                                       while(rs.next()){
                                                      pollo_crudoeninventario =rs.getFloat(1);//Esto muestra la cantidad actual para compararlo
                                                        }
                 }catch(Exception e){
                       JOptionPane.showMessageDialog(null, "Error, pollocrudoeninventario","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE);                  
                }finally{
                  cc.getClose();
             }
          }
              public static void minimodelaspiezasdepollocrudoquesoninparesentablaproductos(){
              String consulta="select MIN(cantidad) from productos WHERE nombre_producto IN('Huacal', 'Cadera', 'Cabeza', 'Molleja', 'Pechuga')";
              try{ Connection ca= cc.conexion();     
                     sent  = (Statement)ca.createStatement();
                                             rs = sent.executeQuery(consulta);
                                       while(rs.next()){
                                                      minimodelaspiezasinparesdepollocrudoeninventario =rs.getFloat(1);//Esto muestra la cantidad actual para compararlo
                                                        }
                 }catch(Exception e){ 
                                JOptionPane.showMessageDialog(null, "Error, minimodelaspiezasdepollocrudoquesoninparesentablaproductos","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE);                  
                }finally{
                  cc.getClose();
             }
          }
          public static void minimodelaspiezasdepollocrudoquesonparesentablaproductos(){
              String consulta="select MIN(cantidad) from productos WHERE nombre_producto IN('Muslo', 'Pierna', 'Ala', 'Patas')";
              try{Connection ca= cc.conexion();    
                     sent  = (Statement)ca.createStatement();
                                             rs = sent.executeQuery(consulta);
                                       while(rs.next()){
                                                      minimodelaspiezasparesdepollocrudoeninventario =rs.getFloat(1);//Esto muestra la cantidad actual para compararlo
                                                        }
                 }catch(Exception e){ 
              JOptionPane.showMessageDialog(null, "Error, minimodelaspiezasdepollocrudoquesoninparesentablaproductos","HELPER DEVELOPER",JOptionPane.INFORMATION_MESSAGE);                  
                 }finally{
                  cc.getClose();
             }
          }
          
        FIN METODOS PARA DESCONTAR 1 POLLO  O N POLLOS EN BASE A LAS PIEZAS QUE SE HAN DESCONTADO
    */
    
      /* ESTO ES PARA ABONOS
                    else if (Float.parseFloat(pagodeventacredito)<totalacredito){
                        try{

                            cambio = Float.parseFloat(pagodeventacredito)-sumadeimportescreditopendiente;

                            PreparedStatement ps = ca.prepareStatement ("UPDATE porcentajedescontado='"+variable0+"',descuento='"+ variable0+"',pago='"+Float.parseFloat(pagodeventacredito)+"',cambio='"+cambio+"',fecha_reporte='"+fecha()+"'WHERE id_venta='"+id_ventapencredito+"'");
                            ps.executeUpdate();

                        }catch(Exception ex){
                            JOptionPane.showMessageDialog(null, "Error en venta" + ex.getMessage());
                        }
                    }
                    */
    //3
    /*REGRESAR PRODUCTOS A INVENTARIO POR DESCUENTO DE PIEZA
public void regresarproductos_a_inventario(String nombredepieza){ // este metodo devuelve los productos que fueron agregados a la venta y posteriormente fueron cancelados
              if(nombredepieza.equals("Huesito")){
                    accionesdespuesderegresarproductosainventarios();
                    descuentocombo.setText("00.00");
                    totalcondescuento.setText("00.00");
              }else{//CUANDO NO ES HUESITO
                   
                      id_max_de_venta();
                    block_unlock=true;   
                    //pendiente la restauracion de venta a inventario
                    id_producto(nombredepieza);
                  cantidadenventa(id_producto);
                       cantidadpolloenDByname(id_producto);
                       
                    if(cantidaddeproductos<=cantidadenventa){//VERIFICA QUE NO SEA MAYOR LA CANTIDAD DE PRODUCTOS  A REGRESAR
                        if(cantidadenventa<=cantidaddeproductos&&cantidadenventa!=0){//SI EL ULTIMO PRODUCTO A DESCONTAR ES 1, SE VA A CANCELAR TODA LA VENTA O PORQUE MEJOR AUN, ELIMNAR DICHO PRODUCTO DE LA TABLA VENTA
                           if(medio==true){
                   cantidadpolloenDB  +=(float) cantidaddemedio;
             
                }
                else if(cuarto==true){
                      cantidadpolloenDB  +=(float) cantidaddecuarto;
               
                }
           
                else{
         cantidadpolloenDB+=cantidaddeproductos;
                }         
 id_producto(nombredepieza);
                        try{ //SUMANDO A INVENTARIO EL ULTIMO, 
                            PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+cantidadpolloenDB+"'WHERE id_producto='"+id_producto+"'");
                            ps.executeUpdate();
                         }catch(Exception s){
JOptionPane.showMessageDialog(null, "Error en venta aqui" + s.getMessage());
                        }// SUMANDO A INVENTARIO EL ULTIMO, 
                        //ELIMINAR DE VENTA EL ARTICULO
                        id_producto(nombredepieza);
                        id_max_de_venta();
                        try{
            String sql = "DELETE from descripcion_de_venta where id_producto= '"+id_producto+"' and id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and estado= '"+estadoenturno+"' ";
            sent = ca.createStatement();
            int n = sent.executeUpdate(sql);
            if(n>0){
                id_producto(nombredepieza);
                for(int r=0;r<=storage.size()-1;r++){
                 if(Integer.parseInt(storage.get(r).toString())==id_producto){
                     storage.remove(r);
                 }
             }
                 accionesdespuesderegresarproductosainventarios();
                    descuentocombo.setText("00.00");
                    totalcondescuento.setText("00.00");
              //  mostrartablaarticulos();
//                autocompletar();
            }
        }catch(Exception e){
            JOptionPane.showMessageDialog(null, "ERROR" + e.getMessage());
        } //ELIMINAR DE VENTA EL ARTICULO
                       }else if(cantidadenventa>cantidaddeproductos){ // CUANDO AUN HAY MÁS DE UN ARTICULO EN LA TABLA VENTA
                           cantidadpolloenDB+=cantidaddeproductos;
                           id_producto(nombredepieza);
                        try{ //SUMANDO A INVENTARIO1 
                            PreparedStatement ps = ca.prepareStatement ("UPDATE productos SET cantidad='"+cantidadpolloenDB+"'WHERE id_producto='"+id_producto+"'");
                            ps.executeUpdate();
                        }catch(Exception s){
JOptionPane.showMessageDialog(null, "Error en venta aqui" + s.getMessage());
                        }// SUMANDO A INVENTARIO 1
                        id_producto(nombredepieza);
                        id_max_de_venta();
                        cantidadenventa-=cantidaddeproductos;
                precio_producto(nombredepieza);
                NoPimporte = cantidadenventa*precio;
                            try{//RESTA DE DESCRIPCION DE VENTA 1
                            PreparedStatement ps = ca.prepareStatement ("UPDATE descripcion_de_venta SET cantidad='"+cantidadenventa+"',importe = '"+NoPimporte+"'WHERE id_producto='"+id_producto+"' and id_venta= '"+id_de_la_venta_incrementable+"' and fecha= '"+fecha()+"' and estado= '"+estadoenturno+"'");
                            ps.executeUpdate();
                         accionesdespuesderegresarproductosainventarios();
                        }catch(Exception s){
JOptionPane.showMessageDialog(null, "Error en venta aqui" + s.getMessage());
                        }//RESTA DE DESCRIPCION DE VENTA 1
                       }// CUANDO AUN HAY MÁS DE UN ARTICULO EN LA TABLA VENTA
                   if("pollo crudo".equalsIgnoreCase(nombredepieza)){
                    regresarpiezasdepollocrudodeinventario();
                }
                    }//VERIFICA QUE LA CANTIDAD DE PRODUCTOS NO SEA MAYOR A LA CANTIDAD EN VENTA
                    else if(cantidaddeproductos>cantidadenventa){
                        JOptionPane.showMessageDialog(null, "No puedes descontar "+cantidaddeproductos+ " piezas de "+nombredepieza+ " en venta cuando solo hay "+cantidadenventa+" piezas", "ADVERTENCIA", JOptionPane.WARNING_MESSAGE);
                    } 
              }//CUANDO NO ES HUESITO          
}
    
REGRESAR PRODUCTOS A INVENTARIO, POR DESCUENTO DE PIEZA*/
        //3
    
    //1
/*EL METODO ANTERIOR DEBE DE IR ACOMPAÑADO DE ESTE OTRO ESTO
    IRIA EN LA TABLA VENTA PARA QUE LE MANDE EL NOMBRE DE PIEZA A EL METODO DE ARRIBA
       //ESTO DESCUENTA UN PRODUCTO A LA VEZ Y LO DEVUELVE A INVENTARIO
        fila =tablaventa.getSelectedRow();

        if(fila>=0){// CUANDO UNA CELDA SE SELECCIONO
            nombredepiezaseleccionada=tablaventa.getValueAt(fila,0).toString();
            voyaregresar=true;
            voyaagregar=false;//SE DESACTIVA PARA QUE SOLO ENTRE A VOY A REGRESAR
            if(nombredepiezaseleccionada.equals("Huesito")||nombredepiezaseleccionada.equals("Longaniza")){
                id_producto(nombredepiezaseleccionada);
                eliminarhuesito(id_producto);
            }
                
if(nombredepiezaseleccionada.equals("Pollo rostizado")||nombredepiezaseleccionada.equals("Pollo asado")){
                   
                   Object[] options = { "Entero", "Medio", "Cuarto" };
  int choice = JOptionPane.showOptionDialog(null, 
      "¿Cuánto descuenta?", 
      "Elige una opcion", 
      JOptionPane.YES_NO_OPTION, 
      JOptionPane.QUESTION_MESSAGE, 
      null, 
      options, 
      options[0]);
if (choice == JOptionPane.YES_OPTION){
    entero= true;
      calculadora.setVisible(true);
      calculatorstate.setVisible(true);
       calculatorstate.setText("Eliminando: "+nombredepiezaseleccionada);
             calculatorstate.setForeground(Color.red);
  }else if(choice == JOptionPane.NO_OPTION){
      medio=true;
      cantidaddemedio=(float) 0.50;
      cantidaddeproductos=(float)cantidaddemedio;
      calculadora.setVisible(false);
          regresarproductos_a_inventario(nombredepiezaseleccionada); //pone en estatus de cancelada la venta inconclusa
          descuentodepollo();
          mostrartabladeventas();
           
  }
  else if(choice == JOptionPane.CANCEL_OPTION){
      cuarto=true;
      cantidaddecuarto= (float) 0.25;
      cantidaddeproductos=(float)cantidaddecuarto;
      calculadora.setVisible(false);
          regresarproductos_a_inventario(nombredepiezaseleccionada); //pone en estatus de cancelada la venta inconclusa
          descuentodepollo();
          mostrartabladeventas(); 
  }else{
  calculadora.setVisible(true);
        calculatorstate.setVisible(true);
  calculatorstate.setText("Eliminando: "+nombredepiezaseleccionada);
             calculatorstate.setForeground(Color.red);
         }
          }//POLLO ASAADO Y ROSTIZADO
      
  //
  
else if(nombredepiezaseleccionada.equals("Pierna")||nombredepiezaseleccionada.equals("Huacal")){
                   
                   Object[] optionstwo = { "Completa", "Individual"};
  int choices = JOptionPane.showOptionDialog(null, 
      "¿Vas a eliminar"+nombredepiezaseleccionada+ "completa o individual?", 
      "Elige una opcion", 
      JOptionPane.YES_NO_OPTION, 
      JOptionPane.QUESTION_MESSAGE, 
      null, 
      optionstwo, 
      optionstwo[0]);
if (choices == JOptionPane.YES_OPTION){
             calculadora.setVisible(true);
             calculatorstate.setVisible(true);
             if(nombredepiezaseleccionada.equals("Pierna")){
               calculatorstate.setText("Eliminando: "+nombredepiezaseleccionada+ " completa");
             }
             else {calculatorstate.setText("Eliminando: "+nombredepiezaseleccionada+ " completo");} 
      
             calculatorstate.setForeground(Color.red);// CUANDO ELIGIO DESCONTAR ENTERO
             
  }else if(choices == JOptionPane.NO_OPTION){
        calculadora.setVisible(true);
        calculatorstate.setVisible(true);
  calculatorstate.setText("Eliminando: "+nombredepiezaseleccionada);
             calculatorstate.setForeground(Color.red);
  }//CUANDO ELIGIO PIEZA INDIVIDUAL
      
   }//PIERNA Y HUACAL ENTEROS
 //ELIMINANDO PECHUGA O POLLO  POR ENTERO O POR MEDIO
else if(nombredepiezaseleccionada.equals("Pechuga")){
 Object[] optionsppc = { "Completa", "Medio"};
  int choicesppc = JOptionPane.showOptionDialog(null, 
      "¿Vas a eliminar "+nombredepiezaseleccionada+" completo o medio?", 
      "Elige una opcion", 
      JOptionPane.YES_NO_OPTION, 
      JOptionPane.QUESTION_MESSAGE, 
      null, 
      optionsppc, 
      optionsppc[0]);
if (choicesppc == JOptionPane.YES_OPTION){
     calculadora.setVisible(true);
        calculatorstate.setVisible(true);
  calculatorstate.setText("Eliminando: "+nombredepiezaseleccionada);
             calculatorstate.setForeground(Color.red);
  }else if(choicesppc == JOptionPane.NO_OPTION){
        medio=true;
      cantidaddemedio=(float) 0.50;
      cantidaddeproductos=(float)cantidaddemedio;
      calculadora.setVisible(false);
          regresarproductos_a_inventario(nombredepiezaseleccionada); //pone en estatus de cancelada la venta inconclusa
          descuentodepollo();
          mostrartabladeventas();
  }
     } 
//
          else{
  calculadora.setVisible(true);
        calculatorstate.setVisible(true);
  calculatorstate.setText("Eliminando: "+nombredepiezaseleccionada);
             calculatorstate.setForeground(Color.red);
         } //SI ES PIEZA NORMAL
        }
          else{
            JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila primero","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }
        //ESTO DESCUENTA UN PRODUCTO A LA VEZ Y LO DEVUELVE A INVENTARIO
    
    EL METODO ANTERIOR DEBE DE IR ACOMPAÑADO DE ESTE OTRO ESTO IRIA EN LA TABLA VENTA 
    PARA QUE LE MANDE EL NOMBRE DE PIEZA A EL METODO DE ARRIBA*/
 //1
    
    
    
    //2
    /* ESTO ES DEL BOTON DE LISTO EN LA CALCULADORA, EN LA PARTE DE DESCONTAR PIEZAS
    
    if(voyaregresar==true){//CONDICION QUE DICE QUE VOY A UTILIZAR LA TABLA VENTA
      voyacobrar=false;
                 cantidaddeproductos=Float.parseFloat(cantidad.getText());
                 if(calculatorstate.getText().equals("Eliminando: Pierna completa")){
                     id_producto("Muslo");
                     cantidadenventa=0;
                  cantidadenventa(id_producto);
                  if(cantidadenventa>0){
                     String[] piernafull = {"Pierna","Muslo"};
                           for (int i = 0; i < piernafull.length; i++) {
                     regresarproductos_a_inventario(piernafull[i].toString()); //pone en estatus de cancelada la venta inconclusa
                           }
                  }else{regresarproductos_a_inventario("Pierna"); //pone en estatus de cancelada la venta inconclusa
                           }
                        
                   }else if(calculatorstate.getText().equals("Eliminando: Huacal completo")){
                        id_producto("Cadera");
                     cantidadenventa=0;
                  cantidadenventa(id_producto); 
                     if(cantidadenventa>0){
                         String[] hucalfull = {"Huacal","Cadera"};
                           for (int i = 0; i < hucalfull.length; i++) {
                               regresarproductos_a_inventario(hucalfull[i].toString()); //pone en estatus de cancelada la venta inconclusa
                           }
                     }else{ regresarproductos_a_inventario("Huacal"); //pone en estatus de cancelada la venta inconclusa
                         }
                       
                   }else if(calculatorstate.getText().equals("Eliminando: huesitos")){
                    regresarproductos_a_inventario("Huesito"); //pone en estatus de cancelada la venta inconclusa
              
                   } else{
                        regresarproductos_a_inventario(nombredepiezaseleccionada); //pone en estatus de cancelada la venta inconclusa
                   }
          cantidad.setText("");
                 calculadora.setVisible(false); 
                  calculatorstate.setVisible(false);
            descuentodepollo();
            mostrartabladeventas();
             }//CONDICION QUE DICE QUE VOY A UTILIZAR LA TABLA VENTA
            
    
   ESTO ES DEL BOTON DE LISTO EN LA CALCULADORA, EN LA PARTE DE DESCONTAR PIEZAS */

    
    //DESCUENTO DE POLLO, PARA QUE CUANDO DESCUENTE UNA ´PIEZA, SE EMPLEE Y DESCUENTE 1 POLLO O X POLLOS
    
    
    
    
    //DESCUENTO DE POLLO, PARA QUE CUANDO DESCUENTE UNA ´PIEZA, SE EMPLEE Y DESCUENTE 1 POLLO O X POLLOS
    

}
