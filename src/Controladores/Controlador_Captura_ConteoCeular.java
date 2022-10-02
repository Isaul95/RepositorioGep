package Controladores;

import Modelos.Modelo_ConteoCelular;
import Modelos.Modelo_Cultivos;
import Modelos.Modelo_capturar_resultados;
import javax.swing.JOptionPane;
import si.Captura_Conteo_Celular;
import si.Captura_Resultados_Cultivos;
import si.Capturar_resultados;
import si.Muestra_de_Cultivos;
import si.SI_Inicio;

public class Controlador_Captura_ConteoCeular {
      public static int  id_usuario=SI_Inicio.id_usuario;
     public static String  buscap = "";
     public static  boolean pass=false, pass2=false, pass3=false;
         
    public static void PrecargaTablaDeEstudiosCultivos(){  // jTableMuestraCultivos                
       Modelo_Cultivos.LlenarTablaCultivos(Muestra_de_Cultivos.jTableMuestraCultivos);
    }
      

    
    public static void activarpdfcultivos(){            
    if(Modelo_capturar_resultados.activarVistaParaCapturadeCultivosxx(Integer.valueOf(Capturar_resultados.id_venta.getText())) >=1){
            //JOptionPane.showMessageDialog(null, "Toy enyrando en el valor de resultadi igual a => +");
            new Muestra_de_Cultivos().setVisible(true);
            }
    else{
        //JOptionPane.showMessageDialog(null, "NO hay analisis apra cultivos");
         if (Modelo_capturar_resultados.verificar_estudio_con_longitud_mayor(Integer.valueOf(Capturar_resultados.id_venta.getText())) >= 1) {
            //AQUÍ VA TU NUEVO PDF
//    PDF LARGO
            Controlador_Report_Pdf_ReferenciaMayor ref = new Controlador_Report_Pdf_ReferenciaMayor(); 
            ref.Generacion_PDF_client_referenciaMayor("_02");
            //JOptionPane.showMessageDialog(null, "Se genera pdf de largos porque no hubo cultivos");
//    PDF CORTO
            if (Modelo_capturar_resultados.verificar_estudio_con_longitud_menor(Integer.valueOf(Capturar_resultados.id_venta.getText())) >= 1) {
                Controlador_Report_pdf_paquetes pac = new Controlador_Report_pdf_paquetes();
                pac.Generacion_PDF_client_paquetes("_01"); // llamando el reporte de paketes
                //JOptionPane.showMessageDialog(null, "Se genera pdf de cortos porque no hubo cultivos y hay estudio largo");
            }                    // activarVistaParaCapturadeCultivos
        }        
                     
        else{//PDF CON LONGITUDES DE ANALISIS MENORES A 68
            Controlador_Report_pdf_paquetes pac = new Controlador_Report_pdf_paquetes();
            pac.Generacion_PDF_client_paquetes("_01"); // llamando el reporte de paketes          
        } 
    }
    }
                       
    
      
      public static void limpiarTxtConteos(){                 
            Captura_Conteo_Celular.txtConteo2.setText(null);
         }  
      public static void limpiarTxtObsrvacionesConteos(){                 
            Captura_Conteo_Celular.txtConteo3.setText(null);
         } 

    public static void insertResultadosDeConteoCelular2022() { // ---------------------
        String itemEstudios = Captura_Conteo_Celular.ComboEstudios.getSelectedItem().toString();         
        if (Captura_Conteo_Celular.txtConteo2.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe llenar los campos vacios.");
        }else if(itemEstudios.equals("---------------------")){
            JOptionPane.showMessageDialog(null, "La opción elegida no es la correcta.");
        }else{         
        Modelo_ConteoCelular.inserciondeResultadosDeConteoCelular(itemEstudios,Captura_Conteo_Celular.txtConteo2.getText());
        Modelo_ConteoCelular.LlenarTablaConteo(Captura_Conteo_Celular.jTableCelulas);
        limpiarTxtConteos();
        }
    }
    
    // Se guardan las observaciones del estudio
     public static void insertObservacionesDeConteoCelular2022() { // ---------------------
        String itemObsrvaciones = Captura_Conteo_Celular.ComboEstudios.getSelectedItem().toString();         
        if(Captura_Conteo_Celular.txtConteo3.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe llenar los campos vacios.");
        }else if(itemObsrvaciones.equals("---------------------")){
            JOptionPane.showMessageDialog(null, "La opción elegida no es la correcta.");
        }else{         
        Modelo_ConteoCelular.inserciondeObservacionesDeConteoCelular(itemObsrvaciones,Captura_Conteo_Celular.txtConteo3.getText());
        Modelo_ConteoCelular.LlenarTabladeObservaciones(Captura_Conteo_Celular.jTableObse);
        limpiarTxtObsrvacionesConteos();
        }
    }
}
