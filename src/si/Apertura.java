
package si;
import java.awt.Color;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import static si.Pantalla_CorteCaja.monto;


public class Apertura extends javax.swing.JFrame implements Runnable{
Thread hilo;
    String hora,minutos,segundos;
    
   String  usuarioname=SI_Inicio.text_user.getText();
   int  id_usuario=Integer.parseInt(SI_Inicio.iduser.getText());
    Calendar fecha_actual = new GregorianCalendar();
    public Apertura() {
        initComponents();
        user.setText(usuarioname);
         hilo=new Thread(this);
     hilo.start();
        this.setLocationRelativeTo(null); // CENTRAR FORMULARIO
   Fecha.setText(fecha());
   Calendar calendario = Calendar.getInstance();
   


    }
    
public static String fecha(){ /* SE DECARA LA FECHA DEL SISTEMA */
        Date fecha=new Date();
        SimpleDateFormat formatoFecha= new SimpleDateFormat("YYYY/MM/dd");
        return formatoFecha.format(fecha);
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
    }   // HORA DEL SISTEMA REFLEJADOO EN EL FRAME
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        Corte_btnImprimirticket = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        Fecha = new javax.swing.JLabel();
        Reloj = new javax.swing.JLabel();
        monto = new javax.swing.JTextField();
        Corte_btncancelar = new javax.swing.JButton();
        user = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "APERTURA DE CAJA", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 36))); // NOI18N
        jPanel1.setToolTipText("APERTURA DE CAJA");
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setLayout(null);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Monto para apertura");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(10, 170, 270, 60);

        Corte_btnImprimirticket.setBackground(new java.awt.Color(0, 51, 102));
        Corte_btnImprimirticket.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Corte_btnImprimirticket.setForeground(new java.awt.Color(255, 255, 255));
        Corte_btnImprimirticket.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/casilla-de-verificacion (1).png"))); // NOI18N
        Corte_btnImprimirticket.setText("Abrir caja");
        Corte_btnImprimirticket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Corte_btnImprimirticketActionPerformed(evt);
            }
        });
        jPanel2.add(Corte_btnImprimirticket);
        Corte_btnImprimirticket.setBounds(10, 270, 250, 70);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Usuario");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(10, 80, 220, 60);

        Fecha.setFont(new java.awt.Font("Arial Black", 1, 27)); // NOI18N
        Fecha.setForeground(new java.awt.Color(255, 255, 255));
        Fecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Fecha.setText("DD/MM/YYYY");
        jPanel2.add(Fecha);
        Fecha.setBounds(10, 10, 240, 60);

        Reloj.setFont(new java.awt.Font("Arial Black", 1, 27)); // NOI18N
        Reloj.setForeground(new java.awt.Color(255, 255, 255));
        Reloj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reloj.setText("00:00:00");
        jPanel2.add(Reloj);
        Reloj.setBounds(340, 10, 240, 60);

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
        monto.setBounds(290, 170, 200, 60);

        Corte_btncancelar.setBackground(new java.awt.Color(0, 51, 102));
        Corte_btncancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Corte_btncancelar.setForeground(new java.awt.Color(255, 255, 255));
        Corte_btncancelar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/salir-flecha-derecha (1).png"))); // NOI18N
        Corte_btncancelar.setText("Cancelar");
        Corte_btncancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Corte_btncancelarActionPerformed(evt);
            }
        });
        jPanel2.add(Corte_btncancelar);
        Corte_btncancelar.setBounds(310, 270, 250, 70);

        user.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        user.setForeground(new java.awt.Color(255, 255, 255));
        jPanel2.add(user);
        user.setBounds(290, 80, 190, 60);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 50, 590, 380);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 611, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 450, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
public boolean validarFormulario(String cantidaddecorte) { // VALIDACION DE TXT MONTO
        boolean next = false;
        Pattern patGastos = Pattern.compile("^[0-9]+([.])?([0-9]+)?$");
        Matcher matGastos = patGastos.matcher(cantidaddecorte);

        if (matGastos.matches()&&!cantidaddecorte.equals("")&&!cantidaddecorte.equals("0")) {
            next = true;

        } else {
            JOptionPane.showMessageDialog(null, "Solo escribe numeros, así como no puede quedar vacio", "Advertencia", JOptionPane.INFORMATION_MESSAGE);
            monto.setText("");
            
        }
        return next;
    }
    private void Corte_btnImprimirticketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Corte_btnImprimirticketActionPerformed
  boolean pass2 = validarFormulario(monto.getText());
  //prueba
                 if(pass2){//ESTO VALIDA QUE EL TEXTO ESCRITO NO TENGA INCOHERENCIAS   
                       try{ //la insersion a la tabla ventas

    String sql = "INSERT INTO  apertura(monto,fecha,hora,usuario)  VALUES (?,?,?,?)";
                PreparedStatement pst = ca.prepareCall(sql); //hasta aqui vamos
               
                pst.setFloat(1,Float.parseFloat(monto.getText()));
                pst.setString(2,fecha());
                pst.setString(3,Reloj.getText());
                pst.setInt(4,id_usuario);
               
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
 
       
                 }
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

    private void Corte_btncancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Corte_btncancelarActionPerformed
        // BOTON DE CANCELAR LA INSERCION DE NUEVO USUARIO
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Regresar a pagina anterior?","                    Aviso",dialogButton);
        if(result == 0){
            dispose();   }
    }//GEN-LAST:event_Corte_btncancelarActionPerformed

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
            java.util.logging.Logger.getLogger(Apertura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Apertura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Apertura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Apertura.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Apertura().setVisible(true);
            }
        });
    }
SI cc= new SI();
 Connection ca= cc.conexion();
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton Corte_btnImprimirticket;
    private javax.swing.JButton Corte_btncancelar;
    private javax.swing.JLabel Fecha;
    private javax.swing.JLabel Reloj;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    public static javax.swing.JTextField monto;
    private javax.swing.JLabel user;
    // End of variables declaration//GEN-END:variables
}
