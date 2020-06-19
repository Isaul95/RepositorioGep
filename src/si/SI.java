package si;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane; 
public class SI {
    String MySQLpassword="";//"MNMddm11857";//"tcdrw3tHcq"; //41523129
 //clase para hacer la conexión a mysql 
   Connection conectar=null;
       public Connection conexion(){
                  try{//CAMBIOS, MALDITA ZORRA
          Class.forName("com.mysql.jdbc.Driver");

          //TEST node54849-alkhemydev2.jl.serv.net.mx
        //conectar= (Connection) DriverManager.getConnection("jdbc:mysql://sql3.freesqldatabase.com:3306/sql3347953?autoReconnect=true&useSSL=false","sql3347953",MySQLpassword);  
   conectar= (Connection) DriverManager.getConnection("jdbc:mysql://localhost/inventariostore_alkimia_test?autoReconnect=true&useSSL=false","root",MySQLpassword);  
     //conectar= (Connection) DriverManager.getConnection("jdbc:mysql://node54849-alkhemydev2.jl.serv.net.mx:3306/inventariostore_alkimia_test","root",MySQLpassword);  
         
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
 