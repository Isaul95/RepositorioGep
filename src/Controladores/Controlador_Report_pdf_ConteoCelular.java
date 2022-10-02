
package Controladores;
import Modelos.Modelo_capturar_resultados;
import java.sql.Connection;
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
import si.Captura_Conteo_Celular;
import si.Capturar_resultados;
import static si.Capturar_resultados.envio_email;
import si.Muestra_de_Cultivos;
import si.SI;
import si.nucleo;

public class Controlador_Report_pdf_ConteoCelular {
    
    private final String logotipo = "/Reportes/original.jpg"; //  imagen del Logotipo----- logoAlk.jpg
    private final String logoback2 = "/Reportes/original.jpg"; // backimage.jpg
    private final String firma = "/Reportes/firma.png";      //  imahgen de firma.png  
     public void Generacion_PDF_ConteoCelular(){                                           
   // ***********************    REPORTE DE CULTIVOS    ************************** 
        SI cc= new SI();  
        Connection ca= cc.conexion();  
 
        int dialogButton = JOptionPane.YES_NO_OPTION;          
            try {
                Map parametro = new HashMap(); // parameter1 <<-- ESTE PARAMETRO VIENE DESDE EL REPORTE SOLO SE ESTA LLAMANDO 
                parametro.put("parameter1",Captura_Conteo_Celular.id_venta.getText()); 
                parametro.put("Logo5", this.getClass().getResourceAsStream(logotipo));
                parametro.put("logoback", this.getClass().getResourceAsStream(logoback2));
                parametro.put("firma5", this.getClass().getResourceAsStream(firma));

                JasperReport reporte = null; // String path = "src/Reportes/report3.jasper";
                //String path = "src/Reportes/reporte_coproparasitos.jasper";                
                reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/reporte_conteocelular.jasper")); // ASI MANDO A LLAMAR LOS REPORTES CON .jasper 
                // ========================= LLENADO DEL REPORTE  ======================  /
                //  path --> LA RUTA DEL REPORTE 
//   --> LOS PARAMETROS K SE ENVIAN ALA REPORTE AKI SE RECIBEN IGUAL K CONEXION DB-->(ca) B.D
                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, ca);    

// JasperExportManager es propiedad de jasper el jprint es la k contirnr el docuemto 
//ya caragdo entonces solo especificas la ruta de donde guardarlo         
                  //System.out.println("ARCHIVO :"+Capturar_resultados.id_paciente.getText()+"_"+Capturar_resultados.paciente.getText()+".pdf");
  JasperExportManager.exportReportToPdfFile( jprint, "C:/reportes/"+Capturar_resultados.id_paciente.getText()+"_"+Capturar_resultados.paciente.getText()+".pdf");

  envio_email.setEnabled(true); // se activa el envio email cuando se haya generado el pdf del paciente
  Modelo_capturar_resultados.subir_archivo(Capturar_resultados.id_paciente.getText()+"_"+Capturar_resultados.paciente.getText()+".pdf", Integer.parseInt(Capturar_resultados.id_venta.getText()));
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
           
        //}FIN DEL IF DEL CONDICIONAL CUANDO SE DECIDE SI SE QUIERE GENERAR EL PDF O NO   
     } // fin del metodo                         
    
}
