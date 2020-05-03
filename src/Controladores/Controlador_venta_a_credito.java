/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Modelo_venta_a_credito;
import javax.swing.JOptionPane;
import si.Ventas_a_credito;

/**
 *
 * @author aleks
 */
public class Controlador_venta_a_credito {
    public static float pendienteporpaga,variable0=0, totalacredito=0, nuevopago=0,nuevocambio=0;
   public static String pagodeventacredito="";
    public static void agregar_venta_a_credito(){
        Modelo_venta_a_credito.insertar_venta_a_credito();
    }
   public static void ver_ventas_a_credito(){
       Modelo_venta_a_credito.ventas_a_credito();
       ocultar_deudas();
   }
   public static void consulta_de_ventas_a_credito(String contexto){
       Modelo_venta_a_credito.consultarlosresultadosenlabusquedadenombres(contexto);
   }
   public static void ver_descrpcion_venta_a_credito(){
         int fila =Ventas_a_credito.tabla_ventas_a_credito.getSelectedRow();
        if(fila>=0){//Inventarioventas.ventasacreditopendiente.getValueAt(fila,0).toString()
            mostrar_deudas();
              Ventas_a_credito.idventa.setText(String.valueOf(Ventas_a_credito.tabla_ventas_a_credito.getValueAt(fila, 0)));
              Ventas_a_credito.total.setText(String.valueOf(Ventas_a_credito.tabla_ventas_a_credito.getValueAt(fila, 4)));
              Ventas_a_credito.abono.setText(String.valueOf(Ventas_a_credito.tabla_ventas_a_credito.getValueAt(fila, 5)));
              Ventas_a_credito.pendiente.setText(String.valueOf(Ventas_a_credito.tabla_ventas_a_credito.getValueAt(fila, 6))); 
        }
        else
        JOptionPane.showMessageDialog(null,"Por favor, seleccione una fila primero","Aviso",JOptionPane.INFORMATION_MESSAGE);
   
   }
   public static void ocultar_deudas(){
       Ventas_a_credito.idventa.setVisible(false);
        Ventas_a_credito.labelventa.setVisible(false);
         Ventas_a_credito.labeltotal.setVisible(false);
          Ventas_a_credito.total.setVisible(false);
           Ventas_a_credito.labelpendiente.setVisible(false);
            Ventas_a_credito.pendiente.setVisible(false);
            Ventas_a_credito.labelabono.setVisible(false);
            Ventas_a_credito.abono.setVisible(false);
            
   }
   public static void mostrar_deudas(){
       Ventas_a_credito.idventa.setVisible(true);
        Ventas_a_credito.labelventa.setVisible(true);
         Ventas_a_credito.labeltotal.setVisible(true);
          Ventas_a_credito.total.setVisible(true);
           Ventas_a_credito.labelpendiente.setVisible(true);
            Ventas_a_credito.pendiente.setVisible(true);
                Ventas_a_credito.labelabono.setVisible(true);
            Ventas_a_credito.abono.setVisible(true);
   }
   public static void pagar_venta(float total, float pago_abono, float cambio_pendiente, int id_venta){
       Modelo_venta_a_credito.cerrar_o_abonar_venta_a_credito(total,pago_abono,cambio_pendiente,id_venta);
   }
}
