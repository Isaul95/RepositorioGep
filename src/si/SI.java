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
         conectar= (Connection) DriverManager.getConnection("jdbc:mysql://localhost:3308/inventariostore?autoReconnect=true&useSSL=false","root",MySQLpassword);  
      // conectar= (Connection) DriverManager.getConnection("jdbc:mysql://localhost/inventariostore?autoReconnect=true&useSSL=false","root",MySQLpassword);  
       if(conectar!=null){
           System.out.println("Se hizo la conexión exitosa");
      }
       }catch(ClassNotFoundException e){
          JOptionPane.showMessageDialog(null,"C NOT FOUND EXCEPTION"+e);
          System.err.print(e.getMessage());
                        }catch(SQLException SQL){
                             JOptionPane.showMessageDialog(null,"Error la conexion"+SQL);
                        }
                  finally{
            return conectar;
       }
       }
       public void getClose(){
        try {
            conectar.close();
           System.out.println("Conexión cerrada");
        } catch (SQLException ex) {
            Logger.getLogger(SI.class.getName()).log(Level.SEVERE, "Error al cerrar la conexion "+ex);
        }
    }
  }
 
         
       

