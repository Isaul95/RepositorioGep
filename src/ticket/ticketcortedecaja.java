
package ticket;

import java.text.DateFormat;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

 public class ticketcortedecaja{
  public void ticketcortedecaja(float monto, float gasto, float venta , float diferencia, float ventasmenosgastos, float totaldepagos) {
     Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");                
        
        ServicioImp impServicio = new ServicioImp(); // se crea objeto 
        System.out.println(impServicio.getImpresoras()); // imprime todas las impresoras instaladas
        //int clientesNum = clientes.size();
        String auxs="";       
        String impra = "Juarez"; // Nombre de la impresora

        // Se llama al metodo para imprimir una cadena
        auxs+= "CORTE DE CAJA\n\n";  System.out.println("CORTE DE CAJA\n\n");      
        auxs+= "FARMACIAS GI\n";       System.out.println("FARMACIAS GI\n");
        auxs+= "Altamirano #6-A\n";    System.out.println("Altamirano #6-A\n");
        auxs+= "Iguala de la Independencia\n";     System.out.println("Iguala de la Independencia\n");
        auxs+= "Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n";  System.out.println("Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n");
        auxs+= "==========================================\n";  System.out.println("==========================================\n");            
        auxs+= "TOTAL DE VENTAS:      |   $ "+venta+"\n";             System.out.println("TOTAL DE VENTAS:      |   $ "+venta+"\n");      
        auxs+= "TOTAL DE GASTOS       |   $ "+gasto+"\n";             System.out.println("TOTAL DE GASTOS       |   $ "+gasto+"\n");        
        auxs+= "TOTAL DE EFECTIVO:    |   $ "+ventasmenosgastos+"\n"; System.out.println("TOTAL DE EFECTIVO:    |   $ "+ventasmenosgastos+"\n");                   
        auxs+= "TOTAL DE ENTREGADO:   |   $ "+monto+"\n";             System.out.println("TOTAL DE ENTREGADO:   |   $ "+monto+"\n");
        auxs+= "TOTAL DE PAGOS:   |   $ "+totaldepagos+"\n";             System.out.println("TOTAL DE PAGOS:   |   $ "+totaldepagos+"\n");
        
        auxs+= "==========================================\n";  System.out.println("==========================================\n");       
        auxs+= "DIFERENCIA:     $ "+String.format("%.20f", diferencia)+"\n";   System.out.println("DIFERENCIA:     $ "+String.format("%.10f", diferencia)+"\n");           
        //PENDIENTE CORREGIR EL METODO DIFERENCIA
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
