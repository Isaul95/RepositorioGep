package si;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane; 
public class SI {
 //  String MySQLpassword="programahtrm5609"; //  remototcdrw3tHcq
 String MySQLpassword=""; //  remototcdrw3tHcq
 
//clase para hacer la conexión a mysql 
   Connection conectar=null;
       public Connection conexion(){
                  try{//CAMBIOS, MALDITA ZORRA
          Class.forName("com.mysql.jdbc.Driver");
          
//conectar= (Connection) DriverManager.getConnection("jdbc:mysql://sh-pro10.hostgator.mx:3306/gepsofco_inventariostore_alkimia_test?autoReconnect=true&useSSL=false","gepsofco_Alkhemy",MySQLpassword);  

// la de abajo es la k estaba local conected
conectar= (Connection) DriverManager.getConnection("jdbc:mysql://localhost/inventariostore_alkimia_test?autoReconnect=true&useSSL=false","root",MySQLpassword);  
  
/*      DATOS CONEXXION AL HOSTGATOR
DB:    gepsofco_inventariostore_alkimia_test
user:  gepsofco_Alkhemy	       
passw:     programahtrm5609
video de ayuda
https://www.youtube.com/watch?v=23BNB-FJ5n4
      */

//PRODUCCIONconectar= (Connection) DriverManager.getConnection("jdbc:mysql://localhost/inventariostore_prod?autoReconnect=true&useSSL=false","root",MySQLpassword);  
     if(conectar!=null){
           System.out.println("Conexión abierta");
      }
       }catch(ClassNotFoundException CNE){
          JOptionPane.showMessageDialog(null,"CLASS NOT FOUND EXCEPTION"+CNE);
          System.err.print(CNE.getMessage());
                        }catch(SQLException SQL){
                             JOptionPane.showMessageDialog(null,"SQL EXCEPTION"+SQL);
                        }
                  finally{
            return conectar;
       }
       }
       public void getClose(){
        try {
            conectar.close();
           System.out.println("Conexión cerrada");
        } catch (SQLException SQL) {
            Logger.getLogger(SI.class.getName()).log(Level.SEVERE, "SQL EXCEPTION"+SQL);
        }
    }
  }
 
         
       

