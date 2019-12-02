package si;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane; 
public class SI {
    String MySQLpassword=""; //41523129
 //clase para hacer la conexión a mysql 
   Connection conectar=null;
       public Connection conexion(){
                  try{
          Class.forName("com.mysql.jdbc.Driver");
         conectar=DriverManager.getConnection("jdbc:mysql://localhost/inventariostore","root",MySQLpassword);  
          System.out.println("Se hizo la conexion exitosa");
      }catch(Exception e){
          JOptionPane.showMessageDialog(null,"Error la conexion"+e);
          System.err.print(e.getMessage());
                        }
        return conectar;
       }
       public void getClose(){
        try {
            conectar.close();
        } catch (SQLException ex) {
            Logger.getLogger(SI.class.getName()).log(Level.SEVERE, "Error al cerrar la conexion "+ex);
        }
    }
  }
 
         
       

