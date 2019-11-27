
package ticket;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

 public class ticketcortedecaja{
  public void ticketcortedecaja(float monto, float gasto, float venta , float diferencia, float ventasmenosgastos, float totaldepagos, int numerodedescuentos, float totaldescuentos) {
     Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");                
        
        ServicioImp impServicio = new ServicioImp(); // se crea objeto 
        System.out.println(impServicio.getImpresoras()); // imprime todas las impresoras instaladas
        //int clientesNum = clientes.size();
        String auxs="";       
        String impra = "Juarez"; // Nombre de la impresora

        // Se llama al metodo para imprimir una cadena
       auxs+= "CORTE DE CAJA\n\n";  System.out.println("CORTE DE CAJA\n\n");      
       auxs+= "POLLERIA LA GRANJA\n"; System.out.println("POLLERIA LA GRANJA\n");
        auxs+= "PROP.JOSE MIGUEL CASTREZANA B.\n";  System.out.println("PROP.JOSE MIGUEL CASTREZANA B.\n");
        auxs+= "R.F.C. CABM850201PR1\n"; System.out.println("R.F.C. CABM850201PR1\n");         
         auxs+= "CARR. IGUALA-TAXCO KM.1.5\n"; System.out.println("CARR. IGUALA-TAXCO KM.1.5\n");
        auxs += "LOCAL 10 Y 11\n";             System.out.println("LOCAL 10 Y 11\n");
        auxs += "CENTRAL DE ABASTOS\n";        System.out.println("CENTRAL DE ABASTOS\n");
        auxs += "COL.INSURGENTES  C.P.4003\n";  System.out.println("COL.INSURGENTES  C.P.40033");
        auxs+= "IGUALA DE LA INDEPENDENCIA\n"; System.out.println("IGUALA DE LA INDEPENDENCIA\n"); 
        
        auxs+= "Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n";  System.out.println("Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n");
        auxs+= "\n==============================\n";  System.out.println("\n==============================\n");            
         auxs+= "SE HICIERON:     "+numerodedescuentos+" DESCUENTOS"+"\n";             System.out.println("SE HICIERON:     "+numerodedescuentos+" DESCUENTOS"+"\n");  
         auxs+= "DANDO UN TOTAL DE:     $"+totaldescuentos+"\n";             System.out.println("DANDO UN TOTAL DE:     $"+totaldescuentos+"\n");  
         auxs+= "TOTAL DE VENTAS:     $"+venta+"\n";             System.out.println("TOTAL DE VENTAS:     $"+venta+"\n");      
          auxs+= "+"+"\n";      System.out.println("+"+"\n");
          auxs+= "TOTAL DE PAGOS:      $"+totaldepagos+"\n";      System.out.println("TOTAL DE PAGOS:      $"+totaldepagos+"\n");
          auxs+= "-"+"\n";      System.out.println("-"+"\n");
          auxs+= "TOTAL DE GASTOS      $"+gasto+"\n";             System.out.println("TOTAL DE GASTOS      $"+gasto+"\n");        
          auxs+= "="+"\n";      System.out.println("="+"\n");
          auxs+= "TOTAL DE EFECTIVO:   $"+ventasmenosgastos+"\n"; System.out.println("TOTAL DE EFECTIVO:   $"+ventasmenosgastos+"\n");                   
          auxs+= "="+"\n";      System.out.println("="+"\n");
          auxs+= "TOTAL DE ENTREGADO:  $"+monto+"\n";             System.out.println("TOTAL DE ENTREGADO:  $"+monto+"\n"); 
        auxs+= "\n==============================\n";  System.out.println("\n==============================\n");       
        if(diferencia>0){
         auxs+= "SOBRO LA CANTIDAD DE: $ "+String.format("%.2f", diferencia)+"\n";   System.out.println("DIFERENCIA: $ "+String.format("%.2f", diferencia)+"\n");           
        //PENDIENTE CORREGIR EL METODO DIFERENCIA
        }else if(diferencia<0){
          auxs+= "HACE FALTA LA CANTIDAD DE: $ "+String.format("%.2f", diferencia)+"\n";   System.out.println("DIFERENCIA: $ "+String.format("%.2f", diferencia)+"\n");           
        }else{
        auxs+= "TODO ESTÁ EN ORDEN:  "+String.format("%.2f", diferencia)+"\n";   System.out.println("DIFERENCIA: $ "+String.format("%.2f", diferencia)+"\n");           
                   }  
         auxs+= "\n\n\n"; // SALTOS PARA K NO LO CORTE LUEGO
       try {
            impServicio.printCadena(impra, auxs);
            // Cortar el papel ....
            byte[] cutP = new byte[]{0x1d, 'V', 1}; // comado para cortar
            impServicio.printBytes(impra, cutP); // se imprime el bruto 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El tikect no se pudo imprimir","warning",JOptionPane.WARNING_MESSAGE);
        }                       
    }        
      
       
}
