
package Controladores;

import Modelos.Modelo_proceso_email;
import java.io.File;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import si.Capturar_resultados;
import si.Envio_email;
import si.SI;
import si.nucleo;

public class Controlador_Report_pdf {   
    private final String logotipo = "/Reportes/logoAlk.jpg"; // Logotipo
    private final String firma = "/Reportes/firma.png";  //  firma   firma.png
    // con el nom se obtiene la nomenclatura para el guaraddo del archivo pdf
    public String nom = Capturar_resultados.id_venta.getText()+"_"+Capturar_resultados.paciente.getText()+".pdf";
     public void Generacion_PDF_client(){                                           
   // ***********************    REPORTE DE USUARIOS    ************************** 
        SI cc= new SI();
        Connection ca= cc.conexion();
 
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Desea Generar Reporte para el usuario?", "REPORTE GENERAL ESTUDIOS",dialogButton);
        if(result == 0){            
            try {
                Map parametro = new HashMap(); // parameter1 <<-- ESTE PARAMETRO VIENE DESDE EL REPORTE SOLO SE ESTA LLAMANDO 
                parametro.put("parameter1",Capturar_resultados.id_venta.getText()); 
                parametro.put("Logo5", this.getClass().getResourceAsStream(logotipo));
                parametro.put("firma5", this.getClass().getResourceAsStream(firma));

                JasperReport reporte = null;
                String path = "src/Reportes/report3.jasper";                
                reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/report3.jasper")); // ASI MANDO A LLAMAR LOS REPORTES CON .jasper 
                // ========================= LLENADO DEL REPORTE  ======================  /
                //  path --> LA RUTA DEL REPORTE
//   --> LOS PARAMETROS K SE ENVIAN ALA REPORTE AKI SE RECIBEN IGUAL K CONEXION DB-->(ca) B.D
                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, ca);    
JOptionPane.showMessageDialog(null, "juntando nomenclatuta -->>"+Capturar_resultados.id_venta.getText()+"_"+Capturar_resultados.paciente.getText());
                
// JasperExportManager es propiedad de jasper el jprint es la k contirnr el docuemto 
//ya caragdo entonces solo especificas la ruta de donde guardarlo       
   
  JasperExportManager.exportReportToPdfFile( jprint, "C:/Users/COMIMSA/Documents/reportes/"+nom);

                // ========================= CREAR LA VISTA DEL REPORTE  ======================  
                JasperViewer vista = new JasperViewer(jprint, false);
                // ============= UN CIERRE LA VISTA DEL REPORTE CUANDO SE PRESIONE LA X de cerrar ============  
                vista.setDefaultCloseOperation(DISPOSE_ON_CLOSE);
                // ==================== MOSTRAR CMO VISIBLE ESTE REPORTE  ======================  
                vista.setVisible(true);
                vista.setTitle("REPORTE DE ESTUDIOS");
            } catch (JRException ex) {
                Logger.getLogger(nucleo.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
  
     } // fin del metodo      
     
     
         public void btn_Envio_email() {
        JOptionPane.showMessageDialog(null, "Estas oprimiendo el boton de enviar email \n desde la clase controlador reporte pdf");

        if (Envio_email.para.getText().equals("")) {
            JOptionPane.showMessageDialog(null, "No ha digitado el destinatario");
        } else {
            int valor = 5;
            if (Envio_email.asunto.getText().equals("")) {
                JOptionPane.showConfirmDialog(null, "Esta seguro que desea enviar el correo sin asunto?");
            }
            if (valor == 5 || valor == 0) {
                Modelo_proceso_email objeto = new Modelo_proceso_email(Envio_email.para, Envio_email.asunto,/*pantalla_Principal.texto,pantalla_Principal.ruta,*/ nom,  Envio_email.label);
 JOptionPane.showConfirmDialog(null,"Esta es el nom CONTROLADOR REPORYE PDF USAUL =" + nom);
 
 
 
 
 
 
 
// esta cosa nom es el nombre del file k se carga pero como le doy un nombre statico pues no es necesario
                objeto.start();
                objeto = null;
            }
        }
    }    
}


