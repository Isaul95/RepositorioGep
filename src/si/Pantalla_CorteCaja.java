package si;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import static si.Apertura.fecha;
import static si.Apertura.monto;


public class Pantalla_CorteCaja extends javax.swing.JFrame  implements Runnable{
    Thread hilo;
    String hora,minutos,segundos;
float ventasdeldia, gastosdeldia, montodeapertura, diferencia;
int apertura;
String  usuarioname=SI_Inicio.text_user.getText();
int  id_usuario=Integer.parseInt(SI_Inicio.iduser.getText());
    Calendar fecha_actual = new GregorianCalendar();
    public Pantalla_CorteCaja() {
        initComponents();
         hilo=new Thread(this);
     hilo.start();
        this.setLocationRelativeTo(null); // CENTRAR FORMULARIO
        Fecha.setText(fecha());
        ventaseneldia();
        Ventasfortoday1.setText(String.valueOf(ventasdeldia));
        Gastosfromtoday.setText(String.valueOf(gastosdeldia));
        user.setText(usuarioname);
    }
    
    public void hora(){
        Calendar calendario=new GregorianCalendar();
        Date horaactual=new Date();
        calendario.setTime(horaactual);
    hora=calendario.get(Calendar.HOUR)>9?""+calendario.get(Calendar.HOUR):"0"+calendario.get(Calendar.HOUR);
    minutos=calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
    segundos= calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);        
     }
    public void run() {
      Thread ct = Thread.currentThread();
      while(ct==hilo){
          hora();
          Reloj.setText(hora+":"+minutos+":"+segundos+" ");
      }
    }
    
    public static String fecha(){ /* SE DECARA LA FECHA DEL SISTEMA */
        Date fecha=new Date();
        SimpleDateFormat formatoFecha= new SimpleDateFormat("YYYY/MM/dd");
        return formatoFecha.format(fecha);
    }
    
public void ventaseneldia(){
        try{ // La suma de todos los importes
    
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select SUM(total) from venta where fecha_reporte= '"+fecha()+"'");
                                            while(rs.next()){
                                                      ventasdeldia =Float.parseFloat(rs.getString("SUM(total)"));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                      }// fin del precio-catch del producto
    }

public void gastosdeldia(){
        try{ // La suma de todos los importes
    
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select SUM(cantidad) from egreso where fecha= '"+fecha()+"'");
                                            while(rs.next()){
                                                      gastosdeldia =Float.parseFloat(rs.getString("SUM(total)"));
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                      }// fin del precio-catch del producto
    }

    public void aperturadeldia(){
        try{ // La suma de todos los importes
    
                                         Statement sent  =(Statement)ca.createStatement();
                                         ResultSet  rs = sent.executeQuery("select * from apertura where fecha= '"+fecha()+"'");
                                            while(rs.next()){
                                                      apertura =rs.getInt("id_apertura");
                                                      montodeapertura= rs.getFloat("monto");
                                                      }
                                                      }//fin del try-precio del producto
                                                      catch (Exception e){
                                                      }// fin del precio-catch del producto
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        Corte_btncancelar = new javax.swing.JButton();
        Corte_btnImprimirticket = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        monto = new javax.swing.JTextField();
        jLabel4 = new javax.swing.JLabel();
        Gastosfromtoday = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        Ventasfortoday1 = new javax.swing.JLabel();
        Fecha = new javax.swing.JLabel();
        Reloj = new javax.swing.JLabel();
        user = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "CORTE DE CAJA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 36))); // NOI18N
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setLayout(null);

        Corte_btncancelar.setBackground(new java.awt.Color(242, 38, 19));
        Corte_btncancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Corte_btncancelar.setText("Cancelar");
        Corte_btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Corte_btncancelarActionPerformed(evt);
            }
        });
        jPanel2.add(Corte_btncancelar);
        Corte_btncancelar.setBounds(370, 400, 250, 60);

        Corte_btnImprimirticket.setBackground(new java.awt.Color(0, 148, 204));
        Corte_btnImprimirticket.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Corte_btnImprimirticket.setForeground(new java.awt.Color(255, 255, 255));
        Corte_btnImprimirticket.setText("Realizar Corte");
        Corte_btnImprimirticket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Corte_btnImprimirticketActionPerformed(evt);
            }
        });
        jPanel2.add(Corte_btnImprimirticket);
        Corte_btnImprimirticket.setBounds(60, 400, 250, 60);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Monto entregado:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(330, 80, 240, 29);

        monto.setBackground(new java.awt.Color(0, 148, 204));
        monto.setFont(new java.awt.Font("Tahoma", 1, 20)); // NOI18N
        monto.setForeground(new java.awt.Color(255, 255, 255));
        monto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        monto.setText("00.00");
        monto.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        monto.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                montoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                montoFocusLost(evt);
            }
        });
        monto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                montoActionPerformed(evt);
            }
        });
        jPanel2.add(monto);
        monto.setBounds(330, 110, 210, 40);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Gastos");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(10, 210, 160, 29);

        Gastosfromtoday.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        Gastosfromtoday.setForeground(new java.awt.Color(255, 255, 255));
        Gastosfromtoday.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Gastosfromtoday.setText("00.00");
        jPanel2.add(Gastosfromtoday);
        Gastosfromtoday.setBounds(20, 250, 180, 29);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Ventas");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(10, 80, 160, 29);

        Ventasfortoday1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        Ventasfortoday1.setForeground(new java.awt.Color(255, 255, 255));
        Ventasfortoday1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Ventasfortoday1.setText("00.00");
        jPanel2.add(Ventasfortoday1);
        Ventasfortoday1.setBounds(20, 120, 180, 29);

        Fecha.setFont(new java.awt.Font("Times New Roman", 1, 27)); // NOI18N
        Fecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Fecha.setText("DD/MM/YYYY");
        jPanel2.add(Fecha);
        Fecha.setBounds(10, 10, 186, 32);

        Reloj.setFont(new java.awt.Font("Times New Roman", 1, 27)); // NOI18N
        Reloj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reloj.setText("00:00:00");
        jPanel2.add(Reloj);
        Reloj.setBounds(310, 10, 240, 32);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 50, 650, 480);

        user.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        jPanel1.add(user);
        user.setBounds(500, 0, 180, 50);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 677, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void Corte_btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Corte_btncancelarActionPerformed
        // BOTON DE CANCELAR LA INSERCION DE NUEVO USUARIO
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Regresar a pagina anterior?","                    Aviso",dialogButton);
        if(result == 0){
            dispose();   }
    }//GEN-LAST:event_Corte_btncancelarActionPerformed

    private void Corte_btnImprimirticketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Corte_btnImprimirticketActionPerformed
        float variablemontoentregado=Float.parseFloat(monto.getText()), ventasmenosgastos, menosapertura;
        if(variablemontoentregado>0){//COMPROBANDO QUE EL MONTO NO ESTE VACIO
             try{ //la insersion a la tabla ventas
              ventaseneldia();
              gastosdeldia();
              aperturadeldia();
              JOptionPane.showMessageDialog(null, "ventas del dia"+ventasdeldia);
              JOptionPane.showMessageDialog(null, "gastos del dia"+gastosdeldia);
              JOptionPane.showMessageDialog(null, "monto de apertura"+montodeapertura);
               JOptionPane.showMessageDialog(null, "montoentregado"+variablemontoentregado);
               ventasmenosgastos=ventasdeldia-gastosdeldia;
                   JOptionPane.showMessageDialog(null, "VENTAS-{GASTOS"+ventasmenosgastos);
               menosapertura=ventasmenosgastos-montodeapertura;
                JOptionPane.showMessageDialog(null, "VENTAS-GASTOS-APERTURA"+menosapertura);
               diferencia=variablemontoentregado-menosapertura;
               JOptionPane.showMessageDialog(null, "DIFERENCIA=VENTAS-GASTOS-APERTURA"+diferencia);
               
          
    String sql = "INSERT INTO  cortes(id_apertura, monto_entregado, gastos, ventas, diferencia, fecha, hora, usuario)  VALUES (?,?,?,?,?,?,?,?)";
                PreparedStatement pst = ca.prepareCall(sql); //hasta aqui vamos
               
                pst.setInt(1,apertura);
                 pst.setFloat(2,variablemontoentregado);
                  pst.setFloat(3,gastosdeldia);
                   pst.setFloat(4,ventasdeldia);
                    pst.setFloat(5,diferencia);
                pst.setString(6,fecha());
                pst.setString(7,Reloj.getText());
                pst.setInt(8,id_usuario);
               
                int a=pst.executeUpdate();
                if(a>0){
                   JOptionPane.showMessageDialog(null,"Bienvenido Usuario: \n" +usuarioname," Acceso Concedido",JOptionPane.INFORMATION_MESSAGE); //Msg de bienvenida                                                                     
                 this.setVisible(false);  
                                     // HERE 
                              new menu_principal().setVisible(true);
                               this.setIconImage(null);
                           
                }
            }catch(SQLException e)  { //fin de la insersion a la tabla ventas
                JOptionPane.showMessageDialog(null,"Error de datos por id vacio "+e);
            }//fin de la insersion a la tabla ventas
    }//FIN DE COMPROBANDO QUE EL MONTO NO ESTE VACIO
else{//CUANDO EL MONTO ESTA VACIO
            JOptionPane.showMessageDialog(null,"El monto no puede estar vacio o en 0");
 
   
}// FIN DE CUANDO EL MONTO ESTA VACIO
    }//GEN-LAST:event_Corte_btnImprimirticketActionPerformed

    private void montoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_montoFocusGained
        // *********************   CAJA DE TEXTO DE PAGOO *********
        if(monto.getText().trim().equals("00.00")){
            monto.setText("");
            //user_usuario.setForeground(Color.red);
        }
        monto.setForeground(Color.blue);
    }//GEN-LAST:event_montoFocusGained

    private void montoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_montoFocusLost
        // *********************   CAJA DE TEXTO DE PAGOO *********
        if(monto.getText().trim().equals("")){
            monto.setText("00.00");
        }
        monto.setForeground(new Color(236, 240, 241));
    }//GEN-LAST:event_montoFocusLost

    private void montoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_montoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_montoActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Pantalla_CorteCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pantalla_CorteCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pantalla_CorteCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pantalla_CorteCaja.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pantalla_CorteCaja().setVisible(true);
            }
        });
    }
SI cc= new SI();
 Connection ca= cc.conexion();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Corte_btnImprimirticket;
    private javax.swing.JButton Corte_btncancelar;
    private javax.swing.JLabel Fecha;
    private javax.swing.JLabel Gastosfromtoday;
    private javax.swing.JLabel Reloj;
    private javax.swing.JLabel Ventasfortoday1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JTextField monto;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables
}
