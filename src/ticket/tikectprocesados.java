
package ticket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import javax.swing.JOptionPane;

public class tikectprocesados {
    
    
       public void tikectprocesados(ArrayList procesadosnombre, ArrayList procesadospiezas) {
       
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");                
        
        ServicioImp impServicio = new ServicioImp(); // se crea objeto 
        System.out.println(impServicio.getImpresoras()); // imprime todas las impresoras instaladas
        //int clientesNum = clientes.size();
        String auxs="";       
        String impra = "Juarez"; // Nombre de la impresora

        // Se llama al metodo para imprimir una cadena
         auxs+= "PROCESADOS\n";          System.out.println("PROCESADOS\n");
         auxs+= "SOBRANTE DE POLLO CRUDO\n";          System.out.println("SOBRANTE DE POLLO CRUDO\n");
         auxs+= "PROP.JOSE MIGUEL CASTREZANA B.\n";  System.out.println("PROP.JOSE MIGUEL CASTREZANA B.\n");
        auxs+= "R.F.C. CABM850201PR1\n"; System.out.println("R.F.C. CABM850201PR1\n");
         auxs+= "POLLERIA LA GRANJA\n"; System.out.println("POLLERIA LA GRANJA\n");
         auxs+= "CARR. IGUALA-TAXCO KM.1.5\n"; System.out.println("CARR. IGUALA-TAXCO KM.1.5\n");
        auxs += "LOCAL 10 Y 11\n";             System.out.println("LOCAL 10 Y 11\n");
        auxs += "CENTRAL DE ABASTOS\n";        System.out.println("CENTRAL DE ABASTOS\n");
        auxs += "COL.INSURGENTES  C.P.4003\n";  System.out.println("COL.INSURGENTES  C.P.40033");
        auxs+= "IGUALA DE LA INDEPENDENCIA\n"; System.out.println("IGUALA DE LA INDEPENDENCIA\n");     
        
       auxs+= "Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n";  System.out.println("Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n");
        System.out.println("\n==============================\n");            auxs+= "\n==============================\n";                
                      auxs+= "Nombre              Piezas\n";           System.out.println("Nombre              Piezas\n");      
         auxs+= "\n==============================\n";   System.out.println("\n==============================\n");                            
                   for(int n=0;n<=procesadosnombre.size()-1;n++){                 
            auxs+= String.format("%-23s" + "%-9s" , procesadosnombre.get(n), procesadospiezas.get(n)); System.out.println(String.format("%-23s" + "%-9s" , procesadosnombre.get(n), procesadospiezas.get(n)));
             auxs+= "\n";   
        }
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
