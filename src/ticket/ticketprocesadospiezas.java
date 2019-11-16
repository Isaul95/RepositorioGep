
package ticket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class ticketprocesadospiezas {
    
     public void ticketprocesadospiezas(ArrayList procesadosnombre, ArrayList procesadospiezas, ArrayList procesadostotal, float totaldeprocesados) {
       
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");                
        
        ServicioImp impServicio = new ServicioImp(); // se crea objeto 
        System.out.println(impServicio.getImpresoras()); // imprime todas las impresoras instaladas
        //int clientesNum = clientes.size();
        String auxs="";       
        String impra = "Juarez"; // Nombre de la impresora

        // Se llama al metodo para imprimir una cadena
         auxs+= "PROCESADOS CON PIEZAS\n";               System.out.println("PROCESADOS CON PIEZAS\n");
         auxs+= "MCS Developers GI\n";                  System.out.println("MCS Developers GI\n");
         auxs+= "Altamirano No 8-B\n";                  System.out.println("Altamirano No 8-B\n");
         auxs+= "Iguala de la Independencia\n";         System.out.println("Iguala de la Independencia\n");
        //impServicio.printCadena(impra, "Folio: " + folio + "\n");        
       auxs+= "Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n";  System.out.println("Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n");
      System.out.println("==========================================\n");  auxs+= "==========================================\n";                
        auxs+= "Nombre                 Piezas      Total\n";    System.out.println("Nombre                 Piezas      Total\n");               
         auxs+= "==========================================\n";   System.out.println("==========================================\n");                           
             for(int n=0;n<=procesadosnombre.size()-1;n++){                 
                  auxs+= String.format("%-25s" + "%-7s" + "$%-8s" , procesadosnombre.get(n), procesadospiezas.get(n), procesadostotal.get(n)); System.out.println(String.format("%-25s" + "%-10s" + "$%-10s" , procesadosnombre.get(n), procesadospiezas.get(n), procesadostotal.get(n)));
                  auxs+= "\n\n\n\n"; // SALTOS PARA K NO LO CORTE LUEGO   
             }                             
              
       auxs+= "\n==========================================\n";  System.out.println("\n==========================================\n");  
     // auxs+= "TOTAL :";// Varios saltos para no cortar antes 
         auxs+= String.format("      "+"TOTAL  :"+ "$" + totaldeprocesados);    System.out.println(String.format("      "+"TOTAL  :"+ "$" + totaldeprocesados));
                  auxs+= "\n\n\n\n"; // SALTOS PARA K NO LO CORTE LUEGO
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
