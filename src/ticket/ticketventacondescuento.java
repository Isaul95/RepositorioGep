
package ticket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class ticketventacondescuento {
    
    
       public void tikectdeventacondescuento(ArrayList nombre, 
               ArrayList piezas, 
               ArrayList precio, 
               ArrayList importe, 
               float total, float pago, float cambio, float porcentaje, float descuento, int numerodeventa) {
       
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");                
        
        ServicioImp impServicio = new ServicioImp(); // se crea objeto 
        System.out.println(impServicio.getImpresoras()); // imprime todas las impresoras instaladas
        //int clientesNum = clientes.size();
        String auxs="";       
        String impra = "Juarez"; // Nombre de la impresora

        // Se llama al metodo para imprimir una cadena
         auxs+= "Ticket con descuento\n\n";          System.out.println("Ticket con descuento\n\n");
         auxs+= "MCS Developers GI\n";                  System.out.println("MCS Developers GI\n");
         auxs+= "Altamirano No 8-B\n";                  System.out.println("Altamirano No 8-B\n");
         auxs+= "Iguala de la Independencia\n";         System.out.println("Iguala de la Independencia\n");
        //impServicio.printCadena(impra, "Folio: " + folio + "\n");        
       auxs+= "Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n";  System.out.println("Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n");
       auxs+= String.format("Venta: "+"%-20s", numerodeventa); System.out.println(String.format("Venta: "+"%-20s", numerodeventa));
       System.out.println("==========================================\n");  auxs+= "==========================================\n";              
         auxs+= "Nombre     Piezas     Precio     Importe\n";           System.out.println("Nombre     Piezas     Precio     Importe\n");       
         auxs+= "==========================================\n";   System.out.println("==========================================\n");                 
            for(int n=0;n<=nombre.size()-1;n++){
                    // Se formatea la cadena a imprimir con String.format para varios string
         auxs+= String.format("%-15s" + " " + "%-6s"+"  "+"$%-9s"+"  "+"$%-8s", nombre.get(n), piezas.get(n), precio.get(n), importe.get(n)); System.out.println(String.format("%-15s" + " " + "%-6s"+"  "+"$%-9s"+"  "+"$%-8s", nombre.get(n), piezas.get(n), precio.get(n), importe.get(n)));      
             } 
       auxs+= "\n==========================================\n";  System.out.println("\n==========================================\n");  
       auxs+= String.format("Total: "+"$%-20s", total); System.out.println(String.format("Total: "+"$%-20s", total));
       auxs+= "\n";     
       auxs+= String.format("Pago: "+"$%-20s", pago); System.out.println(String.format("Pago: "+"$%-20s", pago));
       auxs+= "\n"; 
       auxs+= String.format("Cambio: "+"$%-20s", cambio); System.out.println(String.format("Cambio: "+"$%-20s", cambio));
       auxs+= "\n";   
       auxs+= String.format("Porcentaje descontado: "+"%-20s", porcentaje+"%"); System.out.println(String.format("Porcentaje descontado: "+"%-20s", porcentaje+"%"));
       auxs+= "\n";   
       auxs+= String.format("Descuento: "+"$%-20s", descuento); System.out.println(String.format("Descuento: "+"$%-20s", descuento));
       auxs+= "\n";   
       auxs+= "    Ticket con descuento       \n\n\n\n\n";// Varios saltos para no cortar antes  
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
