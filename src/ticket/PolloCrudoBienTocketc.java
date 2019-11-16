
package ticket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class PolloCrudoBienTocketc {
    
       public void PolloCrudoBienTocketc(ArrayList pollocrudonombre, ArrayList pollocrudopieza){
       
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
         auxs+= "SOBRO POLLO CRUDO\n";                  System.out.println("SOBRO POLLO CRUDO\n");
         auxs+= "Altamirano No 8-B\n";                  System.out.println("Altamirano No 8-B\n");
         auxs+= "Iguala de la Independencia\n";         System.out.println("Iguala de la Independencia\n");
       auxs+= "Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n";  System.out.println("Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n");
        System.out.println("==========================================\n");  auxs+= "==========================================\n";                
         auxs+= "Nombre           Piezas\n";           System.out.println("Nombre           Piezas\n");      
         auxs+= "==========================================\n";   System.out.println("==========================================\n");                 
         for(int n=0;n<=pollocrudonombre.size()-1;n++){                 
            auxs+= String.format("%-20s" + "%-8s", pollocrudonombre.get(n), pollocrudopieza.get(n)); System.out.println(String.format("%-20s" + "%-8s", pollocrudonombre.get(n), pollocrudopieza.get(n)));
             auxs+= "\n";   
        }                  
       auxs+= "\n==========================================\n";  System.out.println("\n==========================================\n");  
       
       //auxs+= String.format("      "+"TOTAL  :"+ "$" + totaldecrudo);    System.out.println(String.format("      "+"TOTAL  :"+ "$" + totaldecrudo));
                  auxs+= "\n\n\n\n";  // saltos para k no lo corte luego
         
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
