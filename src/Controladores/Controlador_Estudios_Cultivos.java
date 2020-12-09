package Controladores;
import Modelos.Modelo_Cultivos;
import Modelos.Modelo_capturar_resultados;
import javax.swing.JOptionPane;
import si.Captura_Bacterias;
import si.Captura_Resultados_Cultivos;
import si.Capturar_resultados;
import si.Muestra_de_Cultivos;
import si.SI_Inicio;
import ticket.TikectGasto;

public class Controlador_Estudios_Cultivos {
      public static int  id_usuario=SI_Inicio.id_usuario;
     public static String  buscap = "";
     public static  boolean pass=false, pass2=false, pass3=false;
    // public static  TikectGasto tikectGastos;
    /* public static void busquedadegastos(){
       Modelogastos.mostrartodoslosproductosenexistenciasporbusqueda(Gastos.busquedagastos1.getText());
    }  */            
    public static void PrecargaTablaDeEstudiosCultivos(){  // jTableMuestraCultivos                
       Modelo_Cultivos.LlenarTablaCultivos(Muestra_de_Cultivos.jTableMuestraCultivos);
    }
      
    public static void insertResultadosCultivos(){        
        if (Captura_Resultados_Cultivos.txtdescripcionq.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Se debe de ingresar el antimicrobiano.");
        } else{
            String item = Captura_Resultados_Cultivos.ComboResultados.getSelectedItem().toString(); 
        //JOptionPane.showMessageDialog(null, "El valor selected es:" +item);
                      Captura_Resultados_Cultivos.nombre_estudio.getText();            
    Modelo_Cultivos.inserciondeResultadosdeCultivos(Captura_Resultados_Cultivos.txtdescripcionq.getText(), item, Captura_Resultados_Cultivos.txt_bacteria.getText(), Captura_Resultados_Cultivos.txt_porcentaje.getText());            
            Modelo_Cultivos.LlenarTabladeResultadosInterpretaciones(Captura_Resultados_Cultivos.jTableInterpretaciones);
            limpiar(); // limpia las cajas txt
        }                
    }
    
    public static void activarpdfcultivos(){            
    if(Modelo_capturar_resultados.activarVistaCapturadeCultivos(Integer.valueOf(Capturar_resultados.id_venta.getText())) >=1){
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
                       
      public static void limpiar(){     /*====  VACIAR CAMPOS */
            Captura_Resultados_Cultivos.txtdescripcionq.setText(null);            
         }  
      
      public static void limpiarTxtBacterias(){                 
            Captura_Bacterias.txtbacteria.setText(null);
            Captura_Bacterias.txtporcentaje.setText(null);
         }  

    public static void insertBacterias() {
        if (Captura_Bacterias.txtbacteria.getText().isEmpty() || Captura_Bacterias.txtporcentaje.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Debe llenar los campos vacios.");
        } else{        
        Modelo_Cultivos.inserciondeResultadosdeBacterias(Captura_Bacterias.txtbacteria.getText(), Captura_Bacterias.txtporcentaje.getText());
        Modelo_Cultivos.LlenarTabladeBacterias(Captura_Bacterias.jTablebacterias);
        limpiarTxtBacterias();
        }
    }
}
