package Controladores;
import Modelos.Modeloventa;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import si.nucleo;
import ticket.ticketventa;

import ticket.ticketventacondescuento;
import ticket.ticketventacredito;

/**
 *
 * @author Alexis
 */
public class Controladorventa{
public static ticketventa mandardatosticketventa;
public static float  porcentajedescontadotipiezastcketcket;
public static float cantidaddeproductos=0, cantidadparapollocrudo=0;
public static float  gastos;
public static float variablepago, piezassuficientes, cantidadporerrordeusuario, 
           NoPcantidad=0, cantidadenventa,  cantidadenventasumada ,
           cantidadpolloenDB, porcentaje, importe,cambio,precio, 
           NoPimporte=0,sumadeimportesenturno;
   
 public static short  id_producto, ciclofor,fila,id_usuario, id_de_la_venta_incrementable,totalcomprobacion, primerventa;//SI SE OCUPAN   
 
    public static String  fechadesde="",fechahasta="", fechaparaventasdesde="", fechaparaventashasta="";
public static String nombredepiezaseleccionada="";
public static String estadoinactivo="Inactivo", estadoactivo="Activo", NoP="",
         estadorealizado="Realizada", estadoenturno="En turno";

 public static ArrayList storage = new ArrayList(); // para guardar los id de cada producto que se ha agregado a la tabla venta
public static ArrayList idsenturno = new ArrayList();
        public static ArrayList cantidaddecadaidenturno = new ArrayList();    
  public  static String[] piezas = {"pollo crudo", "Pechuga", "Muslo","Pierna","Ala","Huacal","Cadera","Cabeza", "Molleja", "Patas"};
public static String[] piezasdemedio = {"Medio pollo","Pechuga", "Muslo","Pierna","Ala","Huacal", "Molleja", "Patas"};

 public static double medio=0.50, cuarto=0.25;


//DATA MAX FULL VERSION
  public static boolean soypechugaenbisteck=false, 
          descuentoactivo=false, suficientespiezas=true, block_unlock=true,tablaventaactiva=false;
   public static boolean noduplicarcrudo=false, noduplicarcocido=false, noduplicaracompañantes=false, 
            noduplicarexistencias=false, noduplicarcorte=false, noduplicargastos=false, noduplicarexternos=false;

         
   public static DecimalFormat solodosdecimales = new DecimalFormat("#.##");
   
   
   public Controladorventa(){
   }
   //CUANDO SE VA A HACER EL PAGO
  public Controladorventa(float variablepago){
      this.variablepago=variablepago;
 Modeloventa.metodo_de_cobro(this.variablepago); 
  }
     //CUANDO RECIBE LA CANTIDAD POR DEFAULT DE 1
   public Controladorventa(String piezaseleccionada, int cantidaddeproductos){
         this.cantidaddeproductos=cantidaddeproductos;
      this.nombredepiezaseleccionada=piezaseleccionada;
        this.cantidadparapollocrudo=cantidaddeproductos;
                agregandoaventa(nombredepiezaseleccionada,cantidaddeproductos);
    }
      //CUANDO RECIBE UNA CANTIDAD POR PARTE DE LA CALCULADORA O UN MEDIO O UN CUARTO
  public Controladorventa(float cantidaddeproductos, String piezaseleccionada){
      this.cantidaddeproductos=cantidaddeproductos;
      this.nombredepiezaseleccionada=piezaseleccionada;
      this.cantidadparapollocrudo=cantidaddeproductos;
     agregandoaventa(nombredepiezaseleccionada, cantidaddeproductos);
  }
   public static String fecha(){ /* SE DECARA LA FECHA DEL SISTEMA */
        Date fecha=new Date();
        SimpleDateFormat formatoFecha= new SimpleDateFormat("YYYY/MM/dd");
        return formatoFecha.format(fecha);
    }
   //INICIO DE AGREGANDO A VENTA
    public void agregandoaventa(String nombredepiezaseleccionada, float cantidaddeproductos) {
        if (nombredepiezaseleccionada.equals("Pierna completa")) {
            String[] piernafull = {"Muslo", "Pierna"};
            for (int i = 0; i < piernafull.length; i++) {
                agregarpiezasaventa(piernafull[i].toString());
            }
        } else if (nombredepiezaseleccionada.equals("Huacal completo")) {
            String[] hucalfull = {"Huacal", "Cadera"};
            for (int i = 0; i < hucalfull.length; i++) {
                agregarpiezasaventa(hucalfull[i].toString());
            }
        } else if (nombredepiezaseleccionada.equals("pollo crudo")) {//TODAS LAS PIEZAS QUE CORRESONDE A UN POLLO ENTERO
            for (int i = 0; i < piezas.length; i++) {
                if (piezas[i].toString().equals("Muslo")
                        || piezas[i].toString().equals("Pierna")
                        || piezas[i].toString().equals("Ala")
                        || piezas[i].toString().equals("Patas")) {
                   Modeloventa.piezassuficientes(piezas[i].toString(), cantidaddeproductos);
            if (suficientespiezas == true) {
                cantidaddeproductos = 2 * cantidadparapollocrudo;
                    Modeloventa.comprobar_registro(piezas[i].toString(), cantidaddeproductos);
            }else{  JOptionPane.showMessageDialog(null, "Solo hay " + piezassuficientes + " piezas de "+piezas[i].toString(), "Advertencia", JOptionPane.ERROR_MESSAGE);}
             } else {
                    if (piezas[i].toString().equals("pollo crudo")) {
                        cantidaddeproductos = 1 * cantidadparapollocrudo;
                        agregarpiezasaventa(piezas[i].toString());
                    } 
                    else {   Modeloventa.piezassuficientes(piezas[i].toString(), cantidaddeproductos);//verifica primero que haya las suficientes piezas para agregar un producto a la venta    
            if (suficientespiezas == true) {
                cantidaddeproductos = 1 * cantidadparapollocrudo;
                        Modeloventa.comprobar_registro(piezas[i].toString(), cantidaddeproductos);
            }else{  JOptionPane.showMessageDialog(null, "Solo hay " + piezassuficientes + " piezas de "+piezas[i].toString(), "Advertencia", JOptionPane.ERROR_MESSAGE);}
             }
                }
            }
        } else if (nombredepiezaseleccionada.equals("Medio pollo")){//ESTO INDICA QUE ES MEDIO POLLO
            for (int i = 0; i < piezasdemedio.length; i++) {
                if (piezasdemedio[i].toString().equals("Pechuga")) {
                 Modeloventa.piezassuficientes(piezas[i].toString(), cantidaddeproductos);
                 if (suficientespiezas == true) {
                cantidaddeproductos = (float) medio * cantidadparapollocrudo;
                    Modeloventa.comprobar_registro(piezasdemedio[i].toString(), cantidaddeproductos);
            }else{  JOptionPane.showMessageDialog(null, "Solo hay " + piezassuficientes + " piezas de "+piezasdemedio[i].toString(), "Advertencia", JOptionPane.ERROR_MESSAGE);}
         
                } else {
                    if (piezasdemedio[i].toString().equals("Medio pollo")) {
                        cantidaddeproductos = 1 * cantidadparapollocrudo;
                        agregarpiezasaventa(piezasdemedio[i].toString());
                    } else {
                       Modeloventa.piezassuficientes(piezas[i].toString(), cantidaddeproductos);
                       if (suficientespiezas == true) {
                   cantidaddeproductos = 1 * cantidadparapollocrudo;
                        Modeloventa.comprobar_registro(piezasdemedio[i].toString(), cantidaddeproductos);
            }else{ JOptionPane.showMessageDialog(null, "Solo hay " + piezassuficientes + " piezas de "+piezasdemedio[i].toString(), "Advertencia", JOptionPane.ERROR_MESSAGE);}
          }
                }
            }
        } else if (nombredepiezaseleccionada.equals("Huesito") || nombredepiezaseleccionada.equals("Longaniza")) {
            if (nombredepiezaseleccionada.equals("Huesito")) {
                agregarpiezasaventa("Huesito");
            } else if (nombredepiezaseleccionada.equals("Taxi")) {
                agregarpiezasaventa("Taxi");
            }else {
                agregarpiezasaventa("Longaniza");
}
        } else if (nombredepiezaseleccionada.equals("Pechuga en bisteck")) {
              Modeloventa.piezassuficientes("Pechuga", cantidaddeproductos);
                if (suficientespiezas == true) {
                soypechugaenbisteck=true;
                agregarpiezasaventa("Pechuga");
            } else {
                JOptionPane.showMessageDialog(null, "Solo hay " + piezassuficientes + " piezas de Pechuga", "Advertencia", JOptionPane.ERROR_MESSAGE);
           }
  } else if(!nombredepiezaseleccionada.equals("pollo crudo")||!nombredepiezaseleccionada.equals("Medio pollo")){
            agregarpiezasaventa(nombredepiezaseleccionada);
        }
    }//FIN DE AGREGANDO AVENTA
    
    //INICIO DE AGREGARPIEZASAVENTA
    public void agregarpiezasaventa(String nombredepieza){
         /* ******************** BOTON DE ADD NUEVO PRODUCTO PARA SU VENTA ******************** */
          Modeloventa.primer_ventadelsistema(); // 482 - 498   Comprueba que ya haya por lo menos un id registrado en la base o en su defecto que no lo haya
       Modeloventa.piezassuficientes(nombredepieza,cantidaddeproductos);//verifica primero que haya las suficientes piezas para agregar un producto a la venta    
      if(suficientespiezas==true){ // si hay piezas suficientes para agregar el articulo a la venta       
          if(primerventa==0){ //indicando que aún no se crea la primer venta del sistema
              Modeloventa.get_id_usuario();        //entonces lo que haría despues será entrar al metodo get_id_usuario, para asignar una venta al usuario que haya iniciado sesión en la maquina
             block_unlock=false;   //se desactiva la condicion que indica que ya no se agregue otro id venta ya que aún no se ha concluido la primer venta
           if(nombredepieza.equals("Huesito")||nombredepieza.equals("Longaniza")||nombredepieza.equals("Taxi")){
        Modeloventa.insertorupdateoverbonnie(nombredepieza, cantidaddeproductos);
          }else if(soypechugaenbisteck==true){
                 Modeloventa.insertorupdatepechugaenbisteck("Pechuga", cantidaddeproductos);
          } 
           else{
               Modeloventa.comprobar_registro(nombredepieza,cantidaddeproductos); // esto es para agregar los productos a la tabla de descripcion de venta y 
           }   
          // ya una vez concluida la venta el mismo metodo agregará dicho resultado total de la venta (a la tabla venta, bueno solo los resultados 
            // correspondientes como lo son; total, pago y cambio)
           }    
           else if(primerventa!=0){ //indicando que por lo menos ya hay una venta
               Modeloventa.verificar_id_ingresadoalsistema(); //Comprueba que el usuario que acab de iniciar sesion coincida con el usuario anteriormente registrado
        Modeloventa.comprobar_venta_resagada();//579 - 605 verifica que no haya una venta cancelada
Modeloventa.get_id_usuario();// 255 -280
              block_unlock=false;   
             if(nombredepieza.equals("Huesito")||nombredepieza.equals("Longaniza")||nombredepieza.equals("Taxi")){
       Modeloventa.insertorupdateoverbonnie(nombredepieza, cantidaddeproductos);
          }else if(soypechugaenbisteck==true){
                 Modeloventa.insertorupdatepechugaenbisteck("Pechuga", cantidaddeproductos);
          }   
             else{
               Modeloventa.comprobar_registro(nombredepieza, cantidaddeproductos); // esto es para agregar los productos a la tabla de descripcion de venta y 
           } 
           }
            }
            else{//No hay piezas suficientes para agregar el articulo a la venta
                JOptionPane.showMessageDialog(null,"Solo hay "+piezassuficientes+ "piezas de "+nombredepieza,"Advertencia", JOptionPane.ERROR_MESSAGE);
            }
    }//FIN DE AGREGAR PIEZAS A VENTA
    
    
    public static void accionesdespuesinsertarendescripciondeventaoactualizarenlamismatabla(String nombredepieza, float cantidaddeproductos){
 if(nombredepieza.equals("Huesito")||nombredepieza.equals("Longaniza")){
       Modeloventa.acciones_despues_de_regresaroagregaraventa();
     Modeloventa.mostrartabladeventas();
                    tablaventaactiva=true;   
                  NoP="";
 } else{
      Modeloventa.acciones_despues_de_regresaroagregaraventa();
       Modeloventa.descontardeinventario(nombredepieza, cantidaddeproductos);
                    //  descuentodepollo();                  
                    Modeloventa.mostrartabladeventas();
                    soypechugaenbisteck=false;
                    tablaventaactiva=true;
               NoP="";
 }
 }
 public static boolean validarFormulario(String cantidaddelatabla) { // VALIDACION DE TXT MONTO
        boolean next = false;
        Pattern patGastos = Pattern.compile("^[0-9]+([.])?([0-9]+)?$");
        Matcher matGastos = patGastos.matcher(cantidaddelatabla);
  if (matGastos.matches()&&!cantidaddelatabla.equals("")&&!cantidaddelatabla.equals("0")) {
            next = true;
        } else {
            JOptionPane.showMessageDialog(null, "No puedes escribir letras, dejar vacio el campo ni meter un 0", "Advertencia", JOptionPane.INFORMATION_MESSAGE);    
        }
        return next;
    }
    public static void descuentos(){
                   float totalparadescuentos = Float.parseFloat(nucleo.subtotal.getText());
               if(totalparadescuentos>0){
                   try{
                       String nombre="";
                      do{
                       boolean pass =validarFormulario(nombre=JOptionPane.showInputDialog(null, "Cantidad a descontar"));
                       if(pass){
                            porcentaje = Float.parseFloat(nombre);
                        if(porcentaje>=totalparadescuentos){
                            JOptionPane.showMessageDialog(null, "No se puede aplicar el 100% de descuento, lo siento, vuelve a intentarlo");
                        }
                       } 
                   }while(porcentaje>=totalparadescuentos); 
                   }catch(NullPointerException NP){}
                   if(porcentaje>0){
                       descuentoactivo=true;
               nucleo.descuentocombo.setText(String.valueOf(porcentaje));
               nucleo.total.setText(String.valueOf(totalparadescuentos - porcentaje));
                 nucleo.total.setVisible(true);
nucleo.deletedescuento.setVisible(true);
                   }else{
                   JOptionPane.showMessageDialog(null,"El descuento no puede ser 0", "Error", JOptionPane.ERROR_MESSAGE);
               }
               }
               else{
                   JOptionPane.showMessageDialog(null, "Aún no hay productos para hacer descuento");
               }
            }
    public static void vaciarlistasdeticket(){
                Controladorinventarioventas.nombreproductoticket.clear();
              Controladorinventarioventas.piezastcket.clear();
              Controladorinventarioventas.preciounitarioticket.clear(); 
              Controladorinventarioventas.importesticket.clear();
           }
    public static void accionesdespuesderealizarcualquierventa(){
      //  descuentodepollo();  
     Modeloventa.get_id_usuario();
                              Modeloventa.mostrartabladeventas();
                                    limpiardatosdeventa(); //Los datos que aparecen en la venta se mostraran
                                descuentoactivo=false;
                            //    storage.clear();    
    }
    public static void  limpiardatosdeventa(){
        nucleo.subtotal.setText("00.00");
        nucleo.cambiocombobox.setText("00.00");
        nucleo.descuentocombo.setText("00.00");
        nucleo.total.setText("00.00");
        nucleo.tablaventa.setVisible(false);
        tablaventaactiva=false;
        descuentoactivo=false;
    } 
              
 public static void agregarventaacredito(){
     Modeloventa.insertarventaacredito();
 }
 public static boolean validarFormulariotexto(String nombre) { // VALIDACION DE TXTDESCRIPCION
        boolean next = false;      //"^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24}[\\s]*)+$"
        Pattern patGastos = Pattern.compile("^[A-Za-z\\s]+$");// ^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24}[\\s]*)+$
        Matcher matGastos = patGastos.matcher(nombre);
        if (matGastos.matches()&&!nombre.equals("")) {
            next = true;
        } else {
            JOptionPane.showMessageDialog(null, "Solo escribe letras");
        }
        return next;
    }   
 
 public static void botones_salir(){
        if(tablaventaactiva==true){
            int decision=JOptionPane.showConfirmDialog(null,"¿Desea continuar?","Advertencia: Tiene una venta inconclusa",JOptionPane.CANCEL_OPTION);
            if(decision==0){ //opción si
                cerrandosesion_o_limpiandoventa();
                System.exit(0);
            }
        }
        else{
            int decision2=JOptionPane.showConfirmDialog(null,"¿Desea continuar?","Saliendo del sistema",JOptionPane.CANCEL_OPTION);
            if(decision2==0){
                 System.exit(0);
            }
        }
}
 public static void cerrandosesion_o_limpiandoventa(){
     Modeloventa.id_max_de_venta();
                Modeloventa.regresarproductos_a_inventariodescontandotodaslaspiezas("En turno", id_de_la_venta_incrementable); //pone en estatus de cancelada la venta inconclusa
               // descuentodepollo();
            Modeloventa.borrarventasenestadoenturnoporerrordeusuario_limpiarventa_o_cerrarsesion();
          limpiardatosdeventa();  //limpia en su mayoria los campos de texto que pertenezcan al apartado venta
                nucleo.tablaventa.setVisible(false); //Desaparece la tabla
               storage.clear();
    }//BOTON CERRAR SESION, PERO COMPRUEBA SI HAY UNA VENTA PEN PARA CANCELAR EN CASO DE SALIR  public void eliminarhuesito(int id){
    
        public static boolean validarFormularioparamostrardescripciondeproductosporid(String cantidaddelatabla) { // VALIDACION DE TXT MONTO
        boolean next = false;
        Pattern patGastos = Pattern.compile("^[0-9]+([.])?([0-9]+)?$");
        Matcher matGastos = patGastos.matcher(cantidaddelatabla);
        if (matGastos.matches()&&!cantidaddelatabla.equals("")&&!cantidaddelatabla.equals("0")) {
            next = true;
        } else {
            JOptionPane.showMessageDialog(null, "Para volver a visualizar las ventas debes de dar clic en el boton de 'ver las ventas' a la derecha de la tabla ", "Advertencia", JOptionPane.INFORMATION_MESSAGE);    
        }
        return next;
    }
        
         // CONSULTA DE VENTAS  REALIZADAS
    public static void montoFocusGained(){
         // *********************   CAJA DE TEXTO DE PAGOO *********
        if(nucleo.monto.getText().trim().equals("00.00")){
            nucleo.monto.setText("");
            //user_usuario.setForeground(Color.red);
        }
        nucleo.monto.setForeground(Color.blue);
    }
    public static void montoFocusLost(){
           // *********************   CAJA DE TEXTO DE PAGOO *********
        if(nucleo.monto.getText().trim().equals("")){
            nucleo.monto.setText("00.00");
        }
        nucleo.monto.setForeground(new Color(236, 240, 241));
    }public static void montoKeyRealeased(){
        boolean pass2 = validarFormulario(nucleo.monto.getText());
            if(pass2){//ESTO VALIDA QUE EL TEXTO ESCRITO NO TENGA INCOHERENCIAS
                Modeloventa.metodo_de_cobro(Float.parseFloat(nucleo.monto.getText()));
                nucleo.monto.setText("00.00");
          
            }
    }
    public static void tablaventa(){
        //ESTO DESCUENTA UN PRODUCTO A LA VEZ Y LO DEVUELVE A INVENTARIO
        fila =Short.parseShort(String.valueOf(nucleo.tablaventa.getSelectedRow()));
 if(fila>=0){// CUANDO UNA CELDA SE SELECCIONO
            nombredepiezaseleccionada=nucleo.tablaventa.getValueAt(fila,0).toString();
            if(nombredepiezaseleccionada.equals("Huesito")||nombredepiezaseleccionada.equals("Longaniza")||nombredepiezaseleccionada.equals("Taxi")){
                if(nombredepiezaseleccionada.equals("Huesito")){
                    Modeloventa.id_producto(nombredepiezaseleccionada);
                    Modeloventa.eliminarhuesito(id_producto);
                   Modeloventa.acciones_despues_de_regresaroagregaraventa();
                    Modeloventa.mostrartabladeventas();
                }else if(nombredepiezaseleccionada.equals("Longaniza")){
                    Modeloventa.id_producto(nombredepiezaseleccionada);
                    Modeloventa.eliminarhuesito(id_producto);
                   Modeloventa.acciones_despues_de_regresaroagregaraventa();
                    Modeloventa.mostrartabladeventas();
                }else{  //TAXI
                    Modeloventa.id_producto(nombredepiezaseleccionada);
                    Modeloventa.eliminarhuesito(id_producto);
                    Modeloventa.acciones_despues_de_regresaroagregaraventa();
                    Modeloventa.mostrartabladeventas();
                }
  } // BOOLEANAS PARA SABER CUALES NO SE VA A REGRESAR
            else if(nombredepiezaseleccionada.equals("Pechuga en bisteck")){
                Modeloventa.regresarproductos_pechugaenbisteck("Pechuga");
                Modeloventa.mostrartabladeventas();
            }
            else{
                Modeloventa.regresarproductos_a_inventario(nombredepiezaseleccionada); //pone en estatus de cancelada la venta inconclusa
                //descuentodepollo();
               Modeloventa.mostrartabladeventas();
            }
        }else{
            JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila primero","Aviso",JOptionPane.INFORMATION_MESSAGE);
        }
        //ESTO DESCUENTA UN PRODUCTO A LA VEZ Y LO DEVUELVE A INVENTARIO
    }
    public static void deletedescuento(){
        descuentoactivo=false;
        nucleo.descuentocombo.setText("00.00");
        nucleo.total.setText(nucleo.subtotal.getText());
        nucleo.deletedescuento.setVisible(false);
    }
    public static void limpiarventa(){
        // ************************  BTN CANCELARR   ***********************
        //ESTO ELIMINA TODA LA VENTA
        if(tablaventaactiva==true){
            cerrandosesion_o_limpiandoventa();
        }
        else{
            JOptionPane.showMessageDialog(null, "No hay datos en la tabla de venta","No se puede borrar", JOptionPane.INFORMATION_MESSAGE);
        }
    }
     
     
    public static void metodos_al_iniciar_menuprincipal(){
      Modeloventa.ids_y_cantidades_enturno_por_error_de_usuario();
nucleo.deletedescuento.setVisible(false);
    }
   public static boolean validarFormularioparaentradadeproductos(String cantidaddelatabla) { // VALIDACION DE TXT MONTO
        boolean next = false;
        Pattern patGastos = Pattern.compile("^-[0-9]+([.])?([0-9]+)?$");//con simbolomenos
        Pattern patGastos1 = Pattern.compile("^[0-9]+([.])?([0-9]+)?$");
        Matcher matGastos = patGastos.matcher(cantidaddelatabla);
        Matcher matGastos1 = patGastos1.matcher(cantidaddelatabla);
        if (matGastos.matches()&&!cantidaddelatabla.equals("")||matGastos1.matches()) {
            next = true;
        } else {
            JOptionPane.showMessageDialog(null, "No puedes escribir letras o dejar vacio el campo", "Advertencia", JOptionPane.INFORMATION_MESSAGE);    
        }
        return next;
    }
  
}
