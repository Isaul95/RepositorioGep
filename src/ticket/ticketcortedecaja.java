
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
        String impra = "alkhemy"; // Nombre de la impresora

        // Se llama al metodo para imprimir una cadena
       auxs+= "CORTE DE CAJA\n\n";  System.out.println("CORTE DE CAJA\n\n");      
     auxs+= "LAB. AL-KHEMY\n"; System.out.println("LAB. AL-KHEMY\n");
       //  auxs+= "PROP.JOSE MIGUEL CASTREZANA B.\n";  System.out.println("PROP.JOSE MIGUEL CASTREZANA B.\n");
        auxs+= "Q.F.B. JULIO ENRIQUE OCEGUERA PEÑA\n"; System.out.println("Q.F.B. JULIO ENRIQUE OCEGUERA PEÑA\n");         
         auxs+= "IGNACIO ZARAGOZA NO. 20\n"; System.out.println("IGNACIO ZARAGOZA NO. 20\n");
        auxs += "C.P.4446802\n";             System.out.println("C.P.4446802\n");
        auxs += "SEGUNDA DE SAN MIGUEL\n";        System.out.println("SEGUNDA DE SAN MIGUEL\n");
        auxs += "COATEPEC HARINAS, MEX.\n";  System.out.println("COATEPEC HARINAS, MEX.");
       auxs+= "TEL:(723) 14-502-62\n"; System.out.println("TEL:(723) 14-502-62\n"); 
        
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
