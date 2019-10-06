
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
     int cantidad;
     String tipo;
     String total;
     String fecha;
     Calendar fechahoy;
     int id_usuario;
     
     
     //String nombre_producto;
     String[] piezas;
     float precioxpieza;
     int totalpiezaspollo;

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

     
    public String[] getPiezas() {
        return piezas;
    }

    public void setPiezas(String[] piezas) {
        this.piezas = piezas;
    }
     
     
    

    /*
    Gastos(String[] piezas, float precioxpieza, int pollosdivididos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }*/
    
    
    public float getPrecioxpieza() {
        return precioxpieza;
    }

    public void setPrecioxpieza(float precioxpieza) {
        this.precioxpieza = precioxpieza;
    }

    public int getTotalpiezaspollo() {
        return totalpiezaspollo;
    }

    public void setTotalpiezaspollo(int totalpiezaspollo) {
        this.totalpiezaspollo = totalpiezaspollo;
    }
     

   
/*
    public String getNombre_producto() {
        return nombre_producto;
    }

    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    } */

    
     
    
     
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
     cantidad =0;
     tipo = "";
     total = "";
     fecha = "";
     //id_usuario = "";
    }
     
      Gastos(/*String descripcion,*/String[] piezaspollo, float precioxpieza, int totalpiezaspollo) {
         //this.tipo = descripcion;
         this.piezas[0] = piezaspollo[0];
         this.piezas[1] = piezaspollo[1];
         this.piezas[2] = piezaspollo[2];
         
          this.precioxpieza = precioxpieza;
         this.totalpiezaspollo = totalpiezaspollo;
        //this.fecha = fecha;
    }
     

   public Gastos(int cantidad, String descripcion, String total, int nombre, String fecha) {
       this.cantidad = cantidad; 
       this.tipo = descripcion; // tipo lo almaceno en descirpcion 
        this.total = total;      
         this.id_usuario = nombre;  //empleado_idempleado lo almaceno en turno asi se usa en controlador
        this.fecha = fecha;
    }
   
   public Calendar Gastos(int cantidad, String descripcion, String total, int nombre, Calendar fechahoy) {
       this.cantidad = cantidad;  
       this.tipo = descripcion; // tipo lo almaceno en descirpcion
        this.total = total;      
        this.id_usuario = nombre; 
        
        this.fecha_actual = fechahoy;
        return null;
    }

    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
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

   /* public String getUsuario() {
        return id_usuario;
    }

    public void setUsuario(String usuario) {
        this.id_usuario = usuario;
    } */                                       
    
    SI cc= new SI(); // CONEXION ALA DB
 Connection ca= cc.conexion();
 
 
    
    public boolean Gastosinsert() /*throws SQLException*/ {
        String sql = null;
        try {
            
           sent = ca.createStatement(); 
          sql = "INSERT INTO egreso (cantidad, tipo, fecha, total, usuario)  VALUES (?,?,?,?,?)";
         PreparedStatement pst = ca.prepareCall(sql);
           // sql = "INSERT INTO egreso (tipo,fecha, total, user_id_usuario)  VALUES (?,?,?,?)";
           pst.setInt(1, getCantidad());
           pst.setString(2, getTipo());
           pst.setString(3, getFecha());
           pst.setString(4, getTotal());
           pst.setInt(5, getId_usuario());
                      
            pst.executeUpdate();
            pst.close();
             
        } catch (SQLException ex) {
            System.err.print(ex);
            return false;
        } /*finally{
            ca.close();
        }  */
       return true;
    }    
    
    
    
    
   /*  public boolean GastosinsertProductos()  {
        String sql = null;
        try {
            
           sent = ca.createStatement(); 
          sql = "INSERT INTO productos (nombre_producto, precio, cantidad)  VALUES (?,?,?)";
         PreparedStatement pst = ca.prepareCall(sql);
           // sql = "INSERT INTO egreso (tipo,fecha, total, user_id_usuario)  VALUES (?,?,?,?)";
           
           pst.setString(1, getPiezas());
           pst.setFloat(2, getPrecioxpieza());
           pst.setInt(3, getTotalpiezaspollo());
          // pst.setString(4, getFecha());
                      
            pst.executeUpdate();
            pst.close();
             
        } catch (SQLException ex) {
            System.err.print(ex);
            return false;
        } /*finally{
            ca.close();
        }  */
      /* return true;
    } */
    
    
    /*
     
     public class Program {
    public static void main(String[] args) {
    
    int piezasxpollo  = 3;
    int precioxunapieza = 18;
    int ttpiezasxuno =  (precioxunapieza/piezasxpollo);
    
    System.out.println(ttpiezasxuno); 
    
     String[] piezas = new String [3];
           piezas [0] = ("pechuga");
           piezas [1] = ("ala");
           piezas [2] = ("pierna");
     
  //    Scanner teclado = new Scanner(System.in);
         for(int i=0; i<piezas.length; i++) {
          System.out.println(piezas [i] +" "+ "precio x piezas -- " + ttpiezasxuno); 
          } 
        
    }
} */
     
     
     
     
     
    
    
    
    }



