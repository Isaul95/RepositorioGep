
package Modelos;

import static Modelos.Modeloventa.cc;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JOptionPane;
/* NOTAS DE FECHAS;
SELECT CURDATE();   ==>   2020-03-01     SELECT CURTIME();   ==>   14:16:34     */

public class Modelo_guardadoDB_email {
    Connection ca= cc.conexion(); // conexio ala db    
   // Connection conexion = null;
    String correo;
    String destinatario;
    String asunto;
        Date date = new Date();
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd"); 
        DateFormat hourFormat = new SimpleDateFormat("HH:mm:ss");   
    
    public Modelo_guardadoDB_email(String valor1, String valor2, String valor3) {
        this.correo = valor1;
        this.destinatario = valor2;
        this.asunto = valor3;
        guardar();
    }
    
    
//   isaherna244@gmail.com;rihr.9523@gmail.com;isaulhernandez@gepsof.com;
//   alexluna@gepsof.com;jesusvazquez@gepsof.com    sh-pro10.hostgator.mx:2096

    public void guardar() {
System.out.println("entro al guardar desde el Modelo_guardadoDB_email");
       // Connection cn1 = conectar();
            String sql4 = "";
            sql4 = "insert into reportes_email(fecha,hora,nombre_archivo,destinatario,asunto) values(?,?,?,?,?)";
System.out.println("ESTOS SON LOS DATOS SAVE INTO DB ===>> DESTINATIRARIO "+destinatario+" asunto "+asunto);
  System.out.println("Fecha: " + dateFormat.format(date));
  System.out.println(" Hora: " + hourFormat.format(date));        

try {
                PreparedStatement pst = ca.prepareStatement(sql4); 
                pst.setString(1, dateFormat.format(date));
                pst.setString(2, hourFormat.format(date));
                pst.setString(3, Modelo_proceso_email.nom);
                pst.setString(4, destinatario);
                pst.setString(5, asunto);
                int n = pst.executeUpdate(); 
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null, "error4" + e);
            }
        }     
    
}
