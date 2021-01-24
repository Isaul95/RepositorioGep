
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
               float subtotal, float total, float pago, float cambio, float descuento, long numerodeventa) {
       
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");                
        
        ServicioImp impServicio = new ServicioImp(); // se crea objeto 
        System.out.println(impServicio.getImpresoras()); // imprime todas las impresoras instaladas
        //int clientesNum = clientes.size();
        String auxs="";       
        String impra = "Juarez"; // Nombre de la impresora

        // Se llama al metodo para imprimir una cadena
         auxs+= "TICKET CON DESCUENTO\n\n";          System.out.println("TICKET CON DESCUENTO\n\n");
         auxs += "POLLERIA LA GRANJA\n";             System.out.println("POLLERIA LA GRANJA\n");
       // auxs+= "PROP.JOSE MIGUEL CASTREZANA B.\n";  System.out.println("PROP.JOSE MIGUEL CASTREZANA B.\n");
        auxs+= "R.F.C. CABM850201PR1\n";            System.out.println("R.F.C. CABM850201PR1\n");        
        auxs += "CARR. IGUALA-TAXCO KM.1.5\n";          System.out.println("CARR. IGUALA-TAXCO KM.1.5\n");
        auxs += "LOCAL 10 Y 11\n";                      System.out.println("LOCAL 10 Y 11\n");
        auxs += "CENTRAL DE ABASTOS\n";                System.out.println("CENTRAL DE ABASTOS\n");
        auxs += "COL.INSURGENTES  C.P.40033\n";         System.out.println("COL.INSURGENTES  C.P.40033\n");
        auxs+= "IGUALA DE LA INDEPENDENCIA\n";        System.out.println("IGUALA DE LA INDEPENDENCIA\n"); 
         
       auxs+= "Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n";  System.out.println("Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n");
       auxs+= String.format("Venta: "+"%-20s", numerodeventa); System.out.println(String.format("Venta: "+"%-20s", numerodeventa));
       System.out.println("\n==============================\n");            auxs+= "\n==============================\n";              
                     auxs+= "Descrip  Cant  Precio  Importe\n";   System.out.println("Descrip  Cant  Precio  Importe\n");       
         auxs+= "\n==============================\n";   System.out.println("\n==============================\n");                 
            for(int n=0;n<=nombre.size()-1;n++){
                 String nom1 = nombre.get(n).toString();
                if (nom1.length()> 12) { // si la descripcion_producto es mayor a 17 la corta
                nom1 = nom1.substring(0, 12);           
            }
                 
         auxs+= String.format("%-13s" + " " + "%-3s"+"  "+"$%-4s"+"  "+"$%-4s\n", nom1, piezas.get(n), precio.get(n), importe.get(n)); System.out.println(String.format("%-13s" + " " + "%-3s"+"  "+"$%-4s"+"  "+"$%-4s", nom1, piezas.get(n), precio.get(n), importe.get(n)));      
             } 
       auxs+= "\n==============================\n";  System.out.println("\n==============================\n");  
       auxs+= String.format("Sub total:   " +"$%-20s", subtotal);  System.out.println(String.format("Sub total:  " +"$%-20s", subtotal));
       auxs+= "\n"; 
       auxs+= String.format("Descuento:   " +"$%-20s", descuento); System.out.println(String.format("Descuento:  " +"$%-20s", descuento));
       auxs+= "\n";   
       auxs+= String.format("Total:       " +"$%-20s", total);     System.out.println(String.format("Total:      " +"$%-20s", total));
       auxs+= "\n";  
       auxs+= String.format("Pago:        " +"$%-20s", pago);      System.out.println(String.format("Pago:       " +"$%-20s", pago));
       auxs+= "\n";
       auxs+= String.format("Cambio:      " +"$%-20s", cambio);    System.out.println(String.format("Cambio:     " +"$%-20s", cambio));
       auxs+= "\n";   
       
       
       auxs+= "\n\n";   
   auxs += "     Gracias por su compra\n\n";// \n\n\n\n\n Varios saltos para no cortar antes
  System.out.println("     Gracias por su compra\n\n");
                     auxs+= String.format("  Hecho por GepSoft: \n Tel: 733-117-0055\n\n\n\n\n");   
         System.out.println(String.format("  Hecho por GepSoft: \n Tel: 733-117-0055\n\n\n\n\n"));
       
         try {
            impServicio.printCadena(impra, auxs);
            impServicio.cashdrawerOpen(impra);
            // Cortar el papel ....
            byte[] cutP = new byte[]{0x1d, 'V', 1}; // comado para cortar
            impServicio.printBytes(impra, cutP); // se imprime el bruto 
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "El tikect no se pudo imprimir","warning",JOptionPane.WARNING_MESSAGE);
        }
    }  
    
}
