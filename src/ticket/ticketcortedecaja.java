    
package ticket;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

 public class ticketcortedecaja{
  public void ticketcortedecaja(float montoapertura, String hora_inicio_sesion,float monto, float gasto, float venta , float diferencia, float ventasmenosgastos, float totaldepagos) {
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
          auxs+= "LABORATORIO \n"; System.out.println("LABORATORIO\n");
       //  auxs+= "PROP.JOSE MIGUEL CASTREZANA B.\n";  System.out.println("PROP.JOSE MIGUEL CASTREZANA B.\n");
        auxs+= "AL-KHEMY\n"; System.out.println("AL-KHEMY\n");         
         auxs+= "IGNACIO ZARAGOZA NO. 20\n"; System.out.println("IGNACIO ZARAGOZA NO. 20\n");
        auxs += "C.P.4446802\n";             System.out.println("C.P.4446802\n");
        auxs += "SEGUNDA DE SAN MIGUEL\n";        System.out.println("SEGUNDA DE SAN MIGUEL\n");
        auxs += "COATEPEC HARINAS, MEX.\n";  System.out.println("COATEPEC HARINAS, MEX.");
        auxs+= "TEL:(723) 14-502-62\n"; System.out.println("TEL:(723) 14-502-62\n"); 
        
        auxs+= "Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n";  System.out.println("Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n");
        auxs+= "\n==============================\n";  System.out.println("\n==============================\n");   
        auxs+= "INICIO DE SESIÓN: "+hora_inicio_sesion+"\n";             System.out.println("INICIO DE SESIÓN: "+hora_inicio_sesion+"\n");   
         auxs+= "APERTURA:     $"+montoapertura+"\n";             System.out.println("APERTURA:      $"+montoapertura+"\n");  
        auxs+= "TOTAL DE VENTAS:     $"+venta+"\n";             System.out.println("TOTAL DE VENTAS:     $"+venta+"\n");      
          auxs+= "+"+"\n";      System.out.println("+"+"\n");
          auxs+= "TOTAL DE GASTOS      $"+gasto+"\n";             System.out.println("TOTAL DE GASTOS      $"+gasto+"\n");        
          auxs+= "="+"\n";      System.out.println("="+"\n");
          auxs+= "TOTAL EN CAJA:   $"+ventasmenosgastos+"\n"; System.out.println("EN FISICO:   $"+ventasmenosgastos+"\n");                   
          auxs+= "="+"\n";      System.out.println("="+"\n");
          auxs+= "ENTREGADO:  $"+monto+"\n";             System.out.println("ENTREGADO:  $"+monto+"\n"); 
        auxs+= "\n==============================\n";  System.out.println("\n==============================\n");       
        
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
