package si;
import java.awt.Image;
import java.awt.Toolkit;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
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
          JOptionPane.showMessageDialog(null,"Error  en el inicio "+e);
          System.err.print(e.getMessage());
                        }
        return conectar;
       }
       
       
            
    }
 
         
       

