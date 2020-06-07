/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Controladores;

import Modelos.Modeloapertura;
import java.awt.Color;
import static si.Apertura.monto;
import si.SI_Inicio;

/**
 *
 * @author Alexis
 */
public class Controladorapertura {
   
     public static short id_usuario=SI_Inicio.id_usuario;
    public static void registrarapertura(){
        Modeloapertura.Guardarapertura();
    }
  public static void montoFocusGained(){   // *********************   CAJA DE TEXTO DE PAGOO *********
        if(monto.getText().trim().equals("00.00")){
            monto.setText("");
        }
        monto.setForeground(Color.black);
  }
  public static void montoFocusLost(){
        // *********************   CAJA DE TEXTO DE PAGOO *********
        if(monto.getText().trim().equals("")){
            monto.setText("00.00");
        }
        monto.setForeground(Color.black);
  }
  
}
