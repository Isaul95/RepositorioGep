package Controladores;

import Modelos.Modeloproductoexterno;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import si.ProductosExternos;
import static si.ProductosExternos.J_tableLlenados;
import static si.ProductosExternos.cantidad;
import ticket.TicketVentaExterna;

public class Controladorproductoexterno {
    public static float piezaendb=0;
    public static float totalapagar=0;
   public static  float cantidadnumerica=0, total=0;
   public static   DecimalFormat solodosdecimales = new DecimalFormat("#.##");
         public static   ArrayList nombres = new ArrayList();
       public static      ArrayList piezas = new ArrayList();
  public static      String productoexternoblanca="productoexternoblanca";
  public static      String productoexternocentral="productoexternocentral";
public static        String productoexternozapata="productoexternozapata";
public static       TicketVentaExterna ticketVentasExternas;
public static int a=0;
    public static void alpagarproductos(String nom, float total){
           if(total!=0){
               Modeloproductoexterno.insertarengastos(nom, total);
               Modeloproductoexterno.agregaraproductos();   // UPDATE `productoexternoblanca` SET `pieza`=0;
    Modeloproductoexterno.vaciandotablas(); 
              Modeloproductoexterno.LlenarTableDatosblanca(J_tableLlenados); 
              Modeloproductoexterno.LlenarTableDatoszapata(J_tableLlenados);
              Modeloproductoexterno.LlenarTableDatosMercado(J_tableLlenados);
                Controladorventa.noduplicarexternos=false;
           }
           else{
               Modeloproductoexterno.agregaraproductos();   // UPDATE `productoexternoblanca` SET `pieza`=0;
    Modeloproductoexterno.vaciandotablas(); 
              Modeloproductoexterno.LlenarTableDatosblanca(J_tableLlenados); 
              Modeloproductoexterno.LlenarTableDatoszapata(J_tableLlenados);
              Modeloproductoexterno.LlenarTableDatosMercado(J_tableLlenados);
                Controladorventa.noduplicarexternos=false;
           }
   
    }
    public static void alagregarenexterno(){
        Modeloproductoexterno.agregarexternos();
    }
}
