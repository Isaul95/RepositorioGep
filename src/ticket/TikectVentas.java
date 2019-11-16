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
        auxs += "COMPROBANTE DE VENTA\n\n";   System.out.println("");
        auxs += "FARMACIAS GI\n";             System.out.println("");
        auxs += "AltaMIRANO #6-A\n";          System.out.println("");
        auxs += "Iguala de la Independencia\n"; System.out.println("");
        auxs += "Folio: " + folio + "\n"; System.out.println("");
        auxs += "Le atendio: " + empleada + "\n";  System.out.println("");
  System.out.println("");
        auxs += "Fecha: " + dateFormat.format(date) + " Hora: " + hourFormat.format(date) + "\n";
  System.out.println("");
        auxs += "Cliente: " + cliente + "\n";
  System.out.println("");
        auxs += "==========================================\n";
  System.out.println("");
        auxs += "Cant Descripcion       Precio    Importe\n";
 System.out.println("");
        auxs += "==========================================\n";

        for (int i = 0; i < cant.length; i++) // for ejemplo para varios productos
        {
            if (prod[i].length() > 17) { // si la descripcion_producto es mayor a 17 la corta
                prod[i] = prod[i].substring(0, 17);
            }
            // Se formatea la cadena a imprimir con String.format para varios string
 System.out.println("");
            auxs += String.format("%-4s" + " " + "%-17s" + " " + "$%-8s" + " " + "$%-8s", cant[i], prod[i], prec[i], impor[i]);
            auxs += "\n";
        }
        auxs += "\n";
        auxs += String.format("\t                TOTAL:" + " " + "$%-10s", total);
 System.out.println("");

        auxs += "\n";
        auxs += String.format("\t         PAGO CLIENTE:" + " " + "$%-10s", pago);
 System.out.println("");

        auxs += "\n";
        auxs += String.format("\t               CAMBIO:" + " " + "$%-10s", cambio);
 System.out.println("");
        auxs += "\n==========================================\n";
 System.out.println("");
        auxs += "Gracias por su compra\n Expertos en tu salud\n\n\n\n\n";// Varios saltos para no cortar antes
 System.out.println("Gracias por su compra\n Expertos en tu salud\n\n\n\n\n");

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
