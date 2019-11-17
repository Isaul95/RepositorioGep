/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ticket;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;

/**
 *
 * @author saube
 */
public class TikectVentas {

    public void tikectVentas(String folio, String empleada, String cliente, int piezas, String total, String pago, String cambio, String[] prod, String[] prec, String[] cant, String[] impor) {

        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");

        ServicioImp impServicio = new ServicioImp(); // se crea objeto 
        System.out.println(impServicio.getImpresoras()); // imprime todas las impresoras instaladas
        String auxs = "";

        String impra = "Juarez"; // Nombre de la impresora

        // Se llama al metodo para imprimir una cadena
        auxs += "COMPROBANTE DE VENTA\n\n";   System.out.println("COMPROBANTE DE VENTA\n\n");
         auxs += "POLLERIA LA GRANJA\n";             System.out.println("POLLERIA LA GRANJA\n");
        auxs+= "PROP.JOSE MIGUEL CASTREZANA B.\n";  System.out.println("PROP.JOSE MIGUEL CASTREZANA B.\n");
        auxs+= "R.F.C. CABM850201PR1\n";            System.out.println("R.F.C. CABM850201PR1\n");       
        auxs += "CARR. IGUALA-TAXCO KM.1.5\n";          System.out.println("CARR. IGUALA-TAXCO KM.1.5\n");
        auxs += "LOCAL 10 Y 11\n";                      System.out.println("LOCAL 10 Y 11\n");
        auxs += "CENTRAL DE ABASTOS\n";                System.out.println("CENTRAL DE ABASTOS\n");
        auxs += "COL.INSURGENTES  C.P.40033\n";         System.out.println("COL.INSURGENTES  C.P.40033\n");
        auxs+= "IGUALA DE LA INDEPENDENCIA\n";        System.out.println("IGUALA DE LA INDEPENDENCIA\n");
        auxs += "Folio: " + folio + "\n";              System.out.println("Folio: " + folio + "\n");
        auxs += "Le atendio: " + empleada + "\n";   System.out.println("Le atendio: " + empleada + "\n");                                       
        auxs += "Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n";  System.out.println("Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n");    
        auxs += "Cliente: " + cliente + "\n";                      System.out.println("Cliente: " + cliente + "\n");  
        auxs += "===================================\n";          System.out.println("===================================\n");  
        auxs += "Cant Descripcion   Precio  Importe\n";          System.out.println("Cant Descripcion   Precio  Importe\n");
        auxs += "===================================\n";         System.out.println("===================================\n");

        for (int i = 0; i < cant.length; i++) // for ejemplo para varios productos
        {
            if (prod[i].length() > 17) { // si la descripcion_producto es mayor a 17 la corta
                prod[i] = prod[i].substring(0, 17);
            }
            // Se formatea la cadena a imprimir con String.format para varios string 
            auxs += String.format("%-4s" + "%-13s" + "$%-5s" + "$%-5s", cant[i], prod[i], prec[i], impor[i]);  System.out.println(String.format("%-4s" + "%-13s" + "$%-5s" + "$%-5s", cant[i], prod[i], prec[i], impor[i]));
             //auxs += String.format("%-4s" + " " + "%-17s" + " " + "$%-8s" + " " + "$%-8s", cant[i], prod[i], prec[i], impor[i]);
            auxs += "\n";                 System.out.println("\n");
        }
        auxs += "\n";                     System.out.println("\n");
        auxs += String.format("\t                TOTAL:" + " " + "$%-10s", total);   System.out.println(String.format("\t                TOTAL:" + " " + "$%-10s", total));

        auxs += "\n";                      System.out.println("\n");
        auxs += String.format("\t         PAGO CLIENTE:" + " " + "$%-10s", pago); System.out.println(String.format("\t         PAGO CLIENTE:" + " " + "$%-10s", pago));

        auxs += "\n";                      System.out.println("\n");
        auxs += String.format("\t               CAMBIO:" + " " + "$%-10s", cambio); System.out.println(String.format("\t               CAMBIO:" + " " + "$%-10s", cambio));
        auxs += "\n==========================================\n";                  System.out.println("\n==========================================\n");
        auxs += "Gracias por su compra\n\n\n\n\n";// Varios saltos para no cortar antes
  System.out.println("Gracias por su compra\n\n\n\n\n");

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
