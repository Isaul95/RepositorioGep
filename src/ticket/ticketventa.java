
package ticket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class ticketventa {
    
    
       public void tikectdeventa(ArrayList nombre, 
               ArrayList piezas, 
               ArrayList precio, 
               ArrayList importe, 
               float subtotal, float total, float pago, float cambio, int numerodeventa) {
       
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");                
        
        ServicioImp impServicio = new ServicioImp(); // se crea objeto 
        System.out.println(impServicio.getImpresoras()); // imprime todas las impresoras instaladas
        //int clientesNum = clientes.size();
        String auxs="";       
        String impra = "Juarez"; // Nombre de la impresora

       // Se llama al metodo para imprimir una cadena
        auxs += "COMPROBANTE DE VENTA\n\n";   System.out.println("COMPROBANTE DE VENTA\n\n");
     auxs+= "LAB. AL-KHEMY\n"; System.out.println("LAB. AL-KHEMY\n");
       //  auxs+= "PROP.JOSE MIGUEL CASTREZANA B.\n";  System.out.println("PROP.JOSE MIGUEL CASTREZANA B.\n");
        auxs+= "Q.F.B. JULIO ENRIQUE OCEGUERA PEÑA\n"; System.out.println("Q.F.B. JULIO ENRIQUE OCEGUERA PEÑA\n");         
         auxs+= "IGNACIO ZARAGOZA NO. 20\n"; System.out.println("IGNACIO ZARAGOZA NO. 20\n");
        auxs += "C.P.4446802\n";             System.out.println("C.P.4446802\n");
        auxs += "SEGUNDA DE SAN MIGUEL\n";        System.out.println("SEGUNDA DE SAN MIGUEL\n");
        auxs += "COATEPEC HARINAS, MEX.\n";  System.out.println("COATEPEC HARINAS, MEX.");
        auxs+= "TEL:(723) 14-502-62\n"; System.out.println("TEL:(723) 14-502-62\n");      
        
       auxs+= "Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n";  System.out.println("Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n");
       auxs+= String.format("Venta: "+"%-20s", numerodeventa); System.out.println(String.format("Venta: "+"%-20s", numerodeventa)); 
       System.out.println("\n==============================\n");  auxs+= "\n==============================\n";              
        auxs+= "Descrip  Cant  Precio  Importe\n";           System.out.println("Descrip  Cant  Precio  Importe\n");                       
         auxs+= "==============================\n";   System.out.println("==============================\n");                 
            
         for(int n=0;n<=nombre.size()-1;n++){
             String nom = nombre.get(n).toString();                  
             if (nom.length()> 12) { // si la descripcion_producto es mayor a 17 la corta
                nom = nom.substring(0, 12);
            //   System.out.println("\nCortandooloo....\n");
            }
             
         auxs+= String.format("%-13s" + " " + "%-3s"+"  "+"$%-4s"+"  "+"$%-4s\n", nom, piezas.get(n), precio.get(n), importe.get(n));  System.out.println(String.format("%-13s" + " " + "%-3s"+"  "+"$%-4s"+"  "+"$%-4s", nom, piezas.get(n), precio.get(n), importe.get(n)));      
          //auxs+= "\n"+nom;      System.out.println("\n"+nom);
             }
         
       auxs+= "\n==============================\n";  System.out.println("\n==============================\n");  
       auxs+= String.format("Sub total:  "+"$%-20s", subtotal);   System.out.println(String.format("Sub total:   "+"$%-20s", subtotal));
       auxs+= "\n";
       auxs+= String.format("Total:      "+"$%-20s", total);      System.out.println(String.format("Total:       "+"$%-20s", total));
       auxs+= "\n";                  
       auxs+= String.format("Pago:       "+"$%-20s", pago);       System.out.println(String.format("Pago:        "+"$%-20s", pago));
       auxs+= "\n";                  
       auxs+= String.format("Cambio:     "+"$%-20s", cambio);     System.out.println(String.format("Cambio:      "+"$%-20s", cambio));
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
