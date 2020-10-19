package Controladores;
import Modelos.Modelo_Cultivos;
import Modelos.Modelogastos;
import java.awt.Color;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import si.Captura_Resultados_Cultivos;
import static si.Capturar_resultados.id_venta;
import si.Gastos;
import si.Muestra_de_Cultivos;
import si.SI_Inicio;
import static si.Ventas_a_credito.idventa;
import ticket.TikectGasto;

public class Controlador_Estudios_Cultivos {
      public static int  id_usuario=SI_Inicio.id_usuario;
     public static String  buscap = "";
     public static  boolean pass=false, pass2=false, pass3=false;
    // public static  TikectGasto tikectGastos;
    /* public static void busquedadegastos(){
       Modelogastos.mostrartodoslosproductosenexistenciasporbusqueda(Gastos.busquedagastos1.getText());
    }  */    
    
  /*  public static void inserciondeResultadosdeCultivos(){
          // ABRE NUEVA VENTANA PARA Registro de Gastos
          if (Captura_Resultados_Cultivos.txtdescripcionq.getText().isEmpty() || Captura_Resultados_Cultivos.txtmonto1.getText().isEmpty()) {
         Captura_Resultados_Cultivos.txtdescripcionq.setBackground(Color.red);
         Captura_Resultados_Cultivos.txtmonto1.setBackground(Color.red);
          } else {             
                   pass = Controladorventa.validarFormulario(Captura_Resultados_Cultivos.txtmonto1.getText());
                   pass2 = validarFormulariotexto(Captura_Resultados_Cultivos.txtdescripcionq.getText());
                    if (pass && pass2) {
              Modelo_Cultivos.inserciondeResultadosdeCultivos(Captura_Resultados_Cultivos.txtdescripcionq.getText(), Float.parseFloat(Captura_Resultados_Cultivos.txtmonto1.getText()));
                         Modelo_Cultivos.LlenarTabla(Captura_Resultados_Cultivos.jTableGastos); // LLENANDO LA TABLA AL INSERTAR CORRECTAMEBTE                            
                              limpiar();
                              Captura_Resultados_Cultivos.txtdescripcionq.setBackground(new Color(135,193,193));
                              Captura_Resultados_Cultivos.txtmonto1.setBackground(new Color(135,193,193));
                    }
                } 
    }  */
    
    
    
    public static void PrecargaTablaDeEstudiosCultivos(){  // jTableMuestraCultivos                
       Modelo_Cultivos.LlenarTablaCultivos(Muestra_de_Cultivos.jTableMuestraCultivos);
    }
    
    
    
    
    public static void insertResultadosCultivos(){
        
        if (Captura_Resultados_Cultivos.txtdescripcionq.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Se debe de ingresar el antimicrobiano.");
        } else{
            String item = Captura_Resultados_Cultivos.ComboResultados.getSelectedItem().toString(); 
        JOptionPane.showMessageDialog(null, "El valor selected es:" +item);
                      Captura_Resultados_Cultivos.nombre_estudio.getText();
            Modelo_Cultivos.inserciondeResultadosdeCultivos(Captura_Resultados_Cultivos.txtdescripcionq.getText(), item);
            Modelo_Cultivos.LlenarTabladeResultadosInterpretaciones(Captura_Resultados_Cultivos.jTableInterpretaciones);
            limpiar(); // limpia las cajas txt
        }
        
        
    }
    
    
    
   
     
                   
      public static void limpiar(){     /*====  VACIAR CAMPOS */
            Captura_Resultados_Cultivos.txtdescripcionq.setText(null);
            //Captura_Resultados_Cultivos.txtmonto1.setText(null);
         }  
}
