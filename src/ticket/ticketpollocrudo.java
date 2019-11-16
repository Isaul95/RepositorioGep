
package ticket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;


public class ticketpollocrudo {          
    
       public void ticketpollocrudo(Object nombre, Object piezas) {
       
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");                
        
        ServicioImp impServicio = new ServicioImp(); // se crea objeto 
        System.out.println(impServicio.getImpresoras()); // imprime todas las impresoras instaladas
        //int clientesNum = clientes.size();
        String auxs="";       
        String impra = "Juarez"; // Nombre de la impresora

        // Se llama al metodo para imprimir una cadena
         auxs+= "SOBRANTE DE POLLO CRUDO\n";          System.out.println("SOBRANTE DE POLLO CRUDO\n");
         auxs+= "MCS Developers GI\n";                  System.out.println("MCS Developers GI\n");
         auxs+= "Altamirano No 8-B\n";                  System.out.println("Altamirano No 8-B\n");
         auxs+= "Iguala de la Independencia\n";         System.out.println("Iguala de la Independencia\n");
       auxs+= "Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n";  System.out.println("Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n");
        System.out.println("==========================================\n");  auxs+= "==========================================\n";                
         auxs+= "Nombre          Piezas\n";           System.out.println("Nombre          Piezas\n");      
         auxs+= "==========================================\n";   System.out.println("==========================================\n");                 
           
            // Se formatea la cadena a imprimir con String.format para varios string
         auxs+= String.format("%-20s" + "%-8s", nombre, piezas); System.out.println(String.format("%-20s" + "%-8s", nombre, piezas));
             auxs+= "\n";            
       auxs+= "\n==========================================\n";  System.out.println("\n==========================================\n");  
       auxs+= "    SOBRANTE DE POLLO CRUDO       \n\n\n\n\n";// Varios saltos para no cortar antes 
System.err.println("    SOBRANTE DE POLLO CRUDO       \n\n\n\n\n");       
         
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
