
package Controladores;

import java.sql.Connection;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import si.Capturar_resultados;
import si.SI;
import si.nucleo;


public class Controlador_Report_pdf {   
    private final String logotipo = "/Reportes/logoAlk.jpg";
    
     public void Generacion_PDF_client(){                                           
  // ***********************    REPORTE DE USUARIOS    **************************
 
        SI cc= new SI();
        Connection ca= cc.conexion();
 
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Desea Generar Reporte para el usuario?", "REPORTE GENERAL ESTUDIOS",dialogButton);
        if(result == 0){
            
            try {
 JOptionPane.showMessageDialog(null, " id_venta del paciente" + Capturar_resultados.id_venta.getText());
                Map parametro = new HashMap(); // parameter1 <<-- ESTE PARAMETRO VIENE DESDE EL REPORTE SOLO SE ESTA LLAMANDO 
                parametro.put("parameter1",Capturar_resultados.id_venta.getText()); 
                parametro.put("Logo", this.getClass().getResourceAsStream(logotipo));

                JasperReport reporte = null;
                String path = "src/Reportes/ReporteCliente.jasper";

                //  reporte = (JasperReport) JRLoader.loadObjectFromFile(path);
                reporte = (JasperReport) JRLoader.loadObject(getClass().getResource("/Reportes/ReporteCliente.jasper")); // ASI MANDO A LLAMAR LOS REPORTES CON .jasper 
                // ========================= LLENADO DEL REPORTE  ======================  /
                //  path --> LA RUTA DEL REPORTE
                //     --> LOS PARAMETROS K SE LE PUEDE ENVIAR ALA REPORTE IN THIS CASE ES NULL y la concion-->(ca) B.D
                JasperPrint jprint = JasperFillManager.fillReport(reporte, parametro, ca);

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
      
     
     
}
        

