
package si;

import com.mysql.jdbc.Statement;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Calendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import static si.SI_Inicio.text_user;
import si.Pantalla_Gastos;

public class Gastos {
    int idegreso;
     String tipo;
     String total;
     String fecha;
     Calendar fechahoy;
     String usuario;
     
    
     
     java.sql.Statement sent;  
  ResultSet rs;
     
  
  private Connection con;
    SI conn = new SI();
   /*  private Connection con;
    SI conectar = new SI();  */
    
    Calendar fecha_actual = new GregorianCalendar();

    
    
    public Calendar getFechahoy() {
        return fechahoy;
    }

    public void setFechahoy(Calendar fechahoy) {
        this.fechahoy = fechahoy;
    }
    
     public Gastos(){
   //  idegreso = 0;
     tipo = "";
     total = "";
     fecha = "";
     usuario = "";
    }

   public Gastos(String descripcion, String total, String nombre, String fecha) {
        this.tipo = descripcion; // tipo lo almaceno en descirpcion 
        this.total = total;      
         this.usuario = nombre;  //empleado_idempleado lo almaceno en turno asi se usa en controlador
        this.fecha = fecha;
    }
   
   public Calendar Gastos(String descripcion, String total, String nombre, Calendar fechahoy) {
        this.tipo = descripcion; // tipo lo almaceno en descirpcion
        this.total = total;      
        this.usuario = nombre; 
        
        this.fecha_actual = fechahoy;
        return null;
    }
    
    public int getIdegreso() {
        return idegreso;
    }

    public void setIdegreso(int idegreso) {
        this.idegreso = idegreso;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public String getUsuario() {
        return usuario;
    }

    public void setUsuario(String usuario) {
        this.usuario = usuario;
    }                                        
    
    SI cc= new SI(); // CONEXION ALA DB
 Connection ca= cc.conexion();
 
 
    
    public boolean Gastosinsert() {
        String sql = null;
        try {
            
           sent = ca.createStatement(); 
          sql = "INSERT INTO egreso (tipo,fecha, total, usuario)  VALUES (?,?,?,?)";
         PreparedStatement pst = ca.prepareCall(sql);
           // sql = "INSERT INTO egreso (tipo,fecha, total, user_id_usuario)  VALUES (?,?,?,?)";
            pst.setString(1, getTipo());
             pst.setString(2, getFecha());
            pst.setString(3, getTotal());
            pst.setString(4, getUsuario());
                      
            pst.executeUpdate();

            pst.close();
             
        } catch (SQLException ex) {
            System.err.print(ex);
            return false;
        } 
       return true;
    }    
    
    }



