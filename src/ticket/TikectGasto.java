
package ticket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

public class TikectGasto{
   
    public void TikectGasto(float cantidad, String descripcion, String totalmonto){
       
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");
        
        
        
        ServicioImp impServicio = new ServicioImp(); // se crea objeto 
        System.out.println(impServicio.getImpresoras()); // imprime todas las impresoras instaladas
        String auxs="";
       
        //  String impra = "MP-4200 TH";  Nombre de la impresora   
        // ---->> Star SP500 Tear Bar (SP512)
          String impra = "Juarez";

        // Se llama al metodo para imprimir una cadena
         auxs+= "GASTO\n\n";  System.out.println("GASTO\n\n");
         auxs+= "MCS Developers GI\n"; System.out.println("MCS Developers GI\n");
         auxs+= "Altamirano No 8-B\n"; System.out.println("Altamirano No 8-B\n");
         auxs+= "Iguala de la Independencia\n"; System.out.println("Iguala de la Independencia\n");
        //impServicio.printCadena(impra, "Folio: " + folio + "\n");
        System.out.println("Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n");
         auxs+= "Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n";
        System.out.println("==========================================\n");
         auxs+= "==========================================\n";       
         auxs+= "Cantidad   Descripcion          Total\n"; System.out.println("Cantidad   Descripcion          Total\n");
      System.out.println("==========================================\n");
         auxs+= "==========================================\n";                   
            if (descripcion.length() > 20) { // si la descripcion_producto es mayor a 17 la corta
                descripcion = descripcion.substring(0, 17);
            }
            // Se formatea la cadena a imprimir con String.format para varios string
             auxs+= String.format("%-10s" + "%-21s" + "%-10s", cantidad, descripcion, totalmonto);
 System.out.println(String.format("%-10s" + "%-21s" + "%-10s", cantidad, descripcion, totalmonto));
             auxs+= "\n";            
         auxs+= "\n==========================================\n";  System.out.println("\n==========================================\n");  
       auxs+= "    Gasto      \n MCS Developer \n\n\n\n\n";// Varios saltos para no cortar antes 
  System.err.println("    Gasto      \n MCS Developer \n\n\n\n\n");       
         
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