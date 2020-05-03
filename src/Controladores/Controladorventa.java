package Controladores;
import Modelos.Modeloventa;
import java.awt.Color;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import si.Existencias;
import si.nucleo;
//import AppPackage.AnimationClass;
 
/**
 *
 * @author Alexis
 */
public class Controladorventa{
    //public static AnimationClass animacion= new AnimationClass();
public static float cantidaddeproductos=0, cantidadparapollocrudo=0;
public static float  gastos;
public static float variablepago, piezassuficientes, cantidadporerrordeusuario, 
           NoPcantidad=0, cantidadenventa,  cantidadenventasumada ,
           cantidadpolloenDB, porcentaje, importe,cambio,precio, 
           NoPimporte=0,sumadeimportesenturno;
   
 public static short  id_paciente,id_producto, ciclofor,fila,id_usuario, id_de_la_venta_incrementable,totalcomprobacion, primerventa;//SI SE OCUPAN   
 
    public static String  fechadesde="",fechahasta="", fechaparaventasdesde="", fechaparaventashasta="", fecha_nacimiento="";
public static String nombredepiezaseleccionada="",nombre_paciente="", edad_paciente="",sexo_paciente, medico;
public static String estadoinactivo="Inactivo", estadoactivo="Activo", NoP="",
         estadorealizado="Realizada", estadoenturno="En turno";

 public static ArrayList storage = new ArrayList(); // para guardar los id de cada producto que se ha agregado a la tabla venta
public static ArrayList idsenturno = new ArrayList();
        public static ArrayList cantidaddecadaidenturno = new ArrayList();    


//DATA MAX FULL VERSION
  public static boolean 
          descuentoactivo=false, block_unlock=true,tablaventaactiva=false;
   public static boolean noduplicarcrudo=false, noduplicarcocido=false, noduplicaracompañantes=false, 
            noduplicarexistencias=false, noduplicarcorte=false, noduplicarinventarioventas=false,
           noduplicargastos=false, noduplicarexternos=false, noduplicar_edicionpaciente=false, 
           noduplicar_capturaresultados=false,
           noduplicar_registro_usuario=false,
           noduplicar_nucleo=false,
           noduplicar_envio_email=false,
           noduplicar_nuevo_producto=false,
           noduplicar_vista_de_ventas_a_credito=false;

         
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
            agregarpiezasaventa(nombredepiezaseleccionada);
    }//FIN DE AGREGANDO AVENTA
    
    //INICIO DE AGREGARPIEZASAVENTA
    public void agregarpiezasaventa(String nombredepieza){
         /* ******************** BOTON DE ADD NUEVO PRODUCTO PARA SU VENTA ******************** */
          Modeloventa.primer_ventadelsistema(); // 482 - 498   Comprueba que ya haya por lo menos un id registrado en la base o en su defecto que no lo haya
       // si hay piezas suficientes para agregar el articulo a la venta       
          if(primerventa==0){ //indicando que aún no se crea la primer venta del sistema
              Modeloventa.get_id_usuario();        //entonces lo que haría despues será entrar al metodo get_id_usuario, para asignar una venta al usuario que haya iniciado sesión en la maquina
             Modeloventa.asignar_id_paciente(); //Inserta el id_del paciente para no tener error con la llave foranea
              block_unlock=false;   //se desactiva la condicion que indica que ya no se agregue otro id venta ya que aún no se ha concluido la primer venta
          
              Modeloventa.comprobar_registro(nombredepieza,cantidaddeproductos); // esto es para agregar los productos a la tabla de descripcion de venta y 
           
          // ya una vez concluida la venta el mismo metodo agregará dicho resultado total de la venta (a la tabla venta, bueno solo los resultados 
            // correspondientes como lo son; total, pago y cambio)
           }    
           else if(primerventa!=0){ //indicando que por lo menos ya hay una venta
               Modeloventa.verificar_id_ingresadoalsistema(); //Comprueba que el usuario que acab de iniciar sesion coincida con el usuario anteriormente registrado
        Modeloventa.comprobar_venta_resagada();//579 - 605 verifica que no haya una venta cancelada
Modeloventa.get_id_usuario();// 255 -280
 Modeloventa.asignar_id_paciente(); //Inserta el id_del paciente para no tener error con la llave foranea
              block_unlock=false;   
               Modeloventa.comprobar_registro(nombredepieza, cantidaddeproductos); // esto es para agregar los productos a la tabla de descripcion de venta y 
           
           }
            
    }//FIN DE AGREGAR PIEZAS A VENTA
    
    
    public static void accionesdespuesinsertarendescripciondeventaoactualizarenlamismatabla(String nombredepieza, float cantidaddeproductos){
      Modeloventa.acciones_despues_de_regresaroagregaraventa();
                    //  descuentodepollo();                  
                    Modeloventa.mostrartabladeventas();
                    tablaventaactiva=true;
               NoP="";
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
  public static boolean validarFormulario_paciente(String cantidaddelatabla) { // VALIDACION DE TXT MONTO
        boolean next = false;
        Pattern patGastos = Pattern.compile("^[0-9]+([.])?([0-9]+)?$");
        Matcher matGastos = patGastos.matcher(cantidaddelatabla);
  if (matGastos.matches()&&!cantidaddelatabla.equals("")&&!cantidaddelatabla.equals("0")) {
            next = true;
        } else {
             nucleo.user_edad.setBackground(new Color(135,193,193));
            JOptionPane.showMessageDialog(null, "Solo se permiten numeros", "Complete todos los datos del pacienteAdvertencia", JOptionPane.INFORMATION_MESSAGE);    
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
         nucleo.monto.setText("00.00");
        nucleo.tablaventa.setVisible(false);
        tablaventaactiva=false;
        descuentoactivo=false;
    } 
             public static void limpiardatospaciente(){
                     nucleo.calendar_fecha_nacimiento.cleanup();//limpia el calendario de la fecha del paciente
        nucleo.calendar_fecha_nacimiento.setDate(null);
        nucleo.user_edad.setBackground(new Color(135,193,193));
        nucleo.user_edad.setText("");
                nucleo.user_nombre.setBackground(new Color(135,193,193));
                nucleo.user_nombre.setText("");
                nucleo.medico.setBackground(new Color(135,193,193));
                nucleo.medico.setText("");
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
 public static boolean validarFormulariotexto_paciente(String nombre) { // VALIDACION DE TXTDESCRIPCION
        boolean next = false;      //"^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24}[\\s]*)+$"
        Pattern patGastos = Pattern.compile("^[A-Za-z\\s]+$");// ^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24}[\\s]*)+$
        Matcher matGastos = patGastos.matcher(nombre);
        if (matGastos.matches()&&!nombre.equals("")) {
            next = true;
        } else {
            nucleo.user_nombre.setBackground(new Color(135,193,193));
            JOptionPane.showMessageDialog(null, "Solo se permiten letras","Complete todos los datos del paciente",JOptionPane.INFORMATION_MESSAGE);
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
        if(nucleo.ventacredito.isSelected()){//Cuando se selecciono una venta  a credito
            boolean pass2 = validarFormulario(nucleo.monto.getText());
            if(pass2&&Float.parseFloat(nucleo.monto.getText())<Float.parseFloat(nucleo.subtotal.getText())&&Float.parseFloat(nucleo.descuentocombo.getText())==0){//TEXTO SOLO SEAN NUMERO, EL PAGO NO SEA MAYOR O IGUAL AL SUBTOTAL SINO NO PUEDE SER UNA VENTA A ACREDITO Y EL MONTO DESCUENTO NO PUEDE ESTAR ACTIVO
                  Controlador_venta_a_credito.agregar_venta_a_credito();
            }else{
                JOptionPane.showMessageDialog(null," Ell pago no puede ser mayor al subtotal, No puede haber un descuento","Sugerencia de venta a credito",JOptionPane.INFORMATION_MESSAGE);
            }
          
        }else if(nucleo.ventacredito.isSelected()==false){//Cuando la venta a credito no esta seleccionada
            boolean pass2 = validarFormulario(nucleo.monto.getText());
            if(pass2){//ESTO VALIDA QUE EL TEXTO ESCRITO NO TENGA INCOHERENCIAS
                Modeloventa.metodo_de_cobro(Float.parseFloat(nucleo.monto.getText()));
            }
        }
    }
    public static void tablaventa(){
        //ESTO DESCUENTA UN PRODUCTO A LA VEZ Y LO DEVUELVE A INVENTARIO
        fila =Short.parseShort(String.valueOf(nucleo.tablaventa.getSelectedRow()));
 if(fila>=0){// CUANDO UNA CELDA SE SELECCIONO
            nombredepiezaseleccionada=nucleo.tablaventa.getValueAt(fila,0).toString();
    // BOOLEANAS PARA SABER CUALES NO SE VA A REGRESAR
                Modeloventa.regresarproductos_a_inventario(nombredepiezaseleccionada); //pone en estatus de cancelada la venta inconclusa
               Modeloventa.mostrartabladeventas();
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
     
     
    public static void metodos_al_iniciar_menuprincipal(String usuario){
      Modeloventa.ids_y_cantidades_enturno_por_error_de_usuario();

if(usuario.equals("Administrador")){
    nucleo.Registro_user.setVisible(true);
    nucleo.Cortedecaja.setVisible(true);
    nucleo.Registro_productos.setVisible(true);
}else{
    nucleo.Registro_user.setVisible(false);
     nucleo.Cortedecaja.setVisible(false);
      nucleo.Registro_productos.setVisible(false);
}
    }
   public static String fecha_de_nacimiento_del_paciente(){
       int año= nucleo.calendar_fecha_nacimiento.getCalendar().get(Calendar.YEAR);
       int mes= nucleo.calendar_fecha_nacimiento.getCalendar().get(Calendar.MONTH)+1;
       int dia= nucleo.calendar_fecha_nacimiento.getCalendar().get(Calendar.DAY_OF_MONTH);
       if(dia<10){
           String nuevodia= "0"+dia;
             fecha_nacimiento= año+"/"+mes+"/"+nuevodia;
             if(mes<10){String nuevomes= "0"+mes;   fecha_nacimiento= año+"/"+nuevomes+"/"+nuevodia;}
       }
       else if(mes<10){
           String nuevomes= "0"+mes;
             fecha_nacimiento= año+"/"+nuevomes+"/"+dia;
       }
       else{
           fecha_nacimiento= año+"/"+mes+"/"+dia;
       }
       return fecha_nacimiento;  
   }
}
