
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
        auxs+= "COMPROBANTE DE GASTOS\n\n";  System.out.println("COMPROBANTE DE GASTOS\n\n");
        auxs+= "POLLERIA LA GRANJA\n"; System.out.println("POLLERIA LA GRANJA\n");
         auxs+= "PROP.JOSE MIGUEL CASTREZANA B.\n";  System.out.println("PROP.JOSE MIGUEL CASTREZANA B.\n");
        auxs+= "R.F.C. CABM850201PR1\n"; System.out.println("R.F.C. CABM850201PR1\n");         
         auxs+= "CARR. IGUALA-TAXCO KM.1.5\n"; System.out.println("CARR. IGUALA-TAXCO KM.1.5\n");
        auxs += "LOCAL 10 Y 11\n";             System.out.println("LOCAL 10 Y 11\n");
        auxs += "CENTRAL DE ABASTOS\n";        System.out.println("CENTRAL DE ABASTOS\n");
        auxs += "COL.INSURGENTES  C.P.4003\n";  System.out.println("COL.INSURGENTES  C.P.40033");
        auxs+= "IGUALA DE LA INDEPENDENCIA\n"; System.out.println("IGUALA DE LA INDEPENDENCIA\n"); 
        
        System.out.println("Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n");
         auxs+= "Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n";        
         auxs+= "\n==============================\n";        System.out.println("\n==============================\n");
           auxs+= "Cant  Descripcion    Total\n"; System.out.println("Cant  Descripcion    Total\n");
      System.out.println("\n==============================\n");
         auxs+= "\n==============================\n";                   
            if (descripcion.length() > 20) { // si la descripcion_producto es mayor a 17 la corta
                descripcion = descripcion.substring(0, 17);
            }
            // Se formatea la cadena a imprimir con String.format para varios string
             auxs+= String.format("%-6s" + "%-14s" + "%-17s", cantidad, descripcion, totalmonto);
 System.out.println(String.format("%-6s" + "%-14s" + "%-17s", cantidad, descripcion, totalmonto));
             auxs+= "\n";            
         auxs+= "\n==============================\n";  System.out.println("\n==============================\n");  
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