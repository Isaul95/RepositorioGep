
package ticket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;


public class Listaventascondescuento {
    
      public void ventascondescuento(ArrayList columna1, ArrayList columna2){
       float sumatoriadedescuentos=0;
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        
        
        
        ServicioImp impServicio = new ServicioImp(); // se crea objeto 
        System.out.println(impServicio.getImpresoras()); // imprime todas las impresoras instaladas
        String auxs="";
       
        //  String impra = "MP-4200 TH";  Nombre de la impresora   
        // ---->> Star SP500 Tear Bar (SP512)
         String impra = "alkhemy"; // Nombre de la impresora

        // Se llama al metodo para imprimir una cadena
        auxs+= "VENTAS C/DESC\n\n";  System.out.println("VENTAS C/DESC\n\n");
         auxs+= "LABORATORIO \n"; System.out.println("LABORATORIO\n");
       //  auxs+= "PROP.JOSE MIGUEL CASTREZANA B.\n";  System.out.println("PROP.JOSE MIGUEL CASTREZANA B.\n");
        auxs+= "AL-KHEMY\n"; System.out.println("AL-KHEMY\n");         
         auxs+= "IGNACIO ZARAGOZA NO. 20\n"; System.out.println("IGNACIO ZARAGOZA NO. 20\n");
        auxs += "C.P.4446802\n";             System.out.println("C.P.4446802\n");
        auxs += "SEGUNDA DE SAN MIGUEL\n";        System.out.println("SEGUNDA DE SAN MIGUEL\n");
        auxs += "COATEPEC HARINAS, MEX.\n";  System.out.println("COATEPEC HARINAS, MEX.");
        auxs+= "TEL:(723) 14-502-62\n"; System.out.println("TEL:(723) 14-502-62\n"); 
        
        System.out.println("Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n");
         auxs+= "Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n";        
         auxs+= "\n==============================\n";        System.out.println("\n==============================\n");
           auxs+= "Venta    Total\n"; System.out.println("Venta    Total\n");
      System.out.println("\n==============================\n");
         auxs+= "\n==============================\n";                   
/*   if (descripcion.length() > 20) { // si la descripcion_producto es mayor a 17 la corta
   descripcion = descripcion.substring(0, 17);
  }*/  
   for(int n=0;n<=columna1.size()-1;n++){  
    auxs+= String.format("%-10s" + "%-10s", columna1.get(n), columna2.get(n));
 System.out.println(String.format("%-10s" + "%-10s",columna1.get(n), columna2.get(n)));
 sumatoriadedescuentos+= Float.parseFloat(columna2.get(n).toString());
             auxs+= "\n";                      
//            auxs+= String.format("%-14s" + "%-10s" + "$%-11s" , columna1.get(n), columna2.get(n), columna3.get(n)); System.out.println(String.format("%-14s" + "%-10s" + "$%-11s" , columna1.get(n), columna2.get(n), columna3.get(n)));                 
             }         
          
//            auxs+= String.format("%-14s" + "%-10s" + "$%-11s" , columna1.get(n), columna2.get(n), columna3.get(n)); System.out.println(String.format("%-14s" + "%-10s" + "$%-11s" , columna1.get(n), columna2.get(n), columna3.get(n)));                 
       
            
            
             /* auxs+= String.format("%-6s" + "%-14s" + "%-17s", cantidad, descripcion, totalmonto);
 System.out.println(String.format("%-6s" + "%-14s" + "%-17s", cantidad, descripcion, totalmonto));
             auxs+= "\n";            */
         auxs+= "\n==============================\n";  System.out.println("\n==============================\n");  
         auxs+= String.format("  "+"TOTAL :"+ "$" + sumatoriadedescuentos);    System.out.println(String.format("  "+"TOTAL :"+ "$" + sumatoriadedescuentos));
auxs+= "\n\n";// Varios saltos para no cortar antes 
        
         
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
