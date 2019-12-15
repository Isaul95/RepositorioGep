package Controladores;

import si.Calculadora;
import static si.Calculadora.cantidad;
/**
 *
 * @author Alexis
 */
public class Controladorcalculadora {
   public static String pieza="", descripcion="", uso="";
public static float cantidadacobrar=0;   
    public static void boton1(){
        String one="1";
        if(!cantidad.equals("")){
            cantidad.setText(cantidad.getText()+one);
        }
        else {
            cantidad.setText(one);
            cantidad=cantidad;
        }
    }
    public static void boton2(){
        String two="2";
        if(!cantidad.equals("")){
            cantidad.setText(cantidad.getText()+two);
        }
        else {
            cantidad.setText(two);
            cantidad=cantidad;
        }        // TODO add your handling code here:
    }
    public static void boton3(){
         String th="3";
        if(!cantidad.equals("")){
            cantidad.setText(cantidad.getText()+th);
        }
        else {
            cantidad.setText(th);
            cantidad=cantidad;
        }
    }
    public static void boton4(){
           String fo="4";
        if(!cantidad.equals("")){
            cantidad.setText(cantidad.getText()+fo);
        }
        else {
            cantidad.setText(fo);
            cantidad=cantidad;
        }
    }
    public static void boton5(){
           String five="5";
        if(!cantidad.equals("")){
            cantidad.setText(cantidad.getText()+five);
        }
        else {
            cantidad.setText(five);
            cantidad=cantidad;
        }
    }
    public static void boton6(){
         String six="6";
        if(!cantidad.equals("")){
            cantidad.setText(cantidad.getText()+six);
        }
        else {
            cantidad.setText(six);
            cantidad=cantidad;
        }
    }
    public static void boton7(){
                 String sevem="7";
        if(!cantidad.equals("")){

            cantidad.setText(cantidad.getText()+sevem);
        }
        else {
            cantidad.setText(sevem);
            cantidad=cantidad;
        }
    }
    public static void boton8(){
           String eight="8";
        if(!cantidad.equals("")){
            cantidad.setText(cantidad.getText()+eight);
        }
        else {
            cantidad.setText(eight);
            cantidad=cantidad;
        }
    }
    public static void boton9(){
         String nine="9";
        if(!cantidad.equals("")){
            cantidad.setText(cantidad.getText()+nine);
        }
        else {
            cantidad.setText(nine);
            cantidad=cantidad;
        }
    }
    public static void boton0(){
         String one="0";
        if(!cantidad.equals("")){

            cantidad.setText(cantidad.getText()+one);
        }
        else {
            cantidad.setText(one);
            cantidad=cantidad;
        }
    }
  public static void enviardecalculadora(String pieza){
             boolean pass2 = Controladorventa.validarFormulario(cantidad.getText());
                 if(pass2){//ESTO VALIDA QUE EL TEXTO ESCRITO NO TENGA INCOHERENCIAS   
                  //CUANDO SE USA PARA AGREGAR CANTIDADES O PIEZAS
            Controladorventa enviar = new Controladorventa(Float.parseFloat(Calculadora.cantidad.getText()), pieza);
                 }
        }
}
