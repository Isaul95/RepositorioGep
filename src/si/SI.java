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
          //TEST 
          conectar= (Connection) DriverManager.getConnection("jdbc:mysql://localhost/inventariostore?autoReconnect=true&useSSL=false","root",MySQLpassword);  
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
 
         
       

