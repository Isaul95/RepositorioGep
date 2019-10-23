package si;

import com.sun.awt.AWTUtilities;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.Timer;
//import static si.menu_principal.Proveedores9;

public class Cargainiciodelsistema extends javax.swing.JFrame implements Runnable{

    private Thread tiempo = null;
       private Timer t ;
          private ActionListener time;
          private int x = 0;
    
    public Cargainiciodelsistema() {
        
                  time = new ActionListener() {
             @Override
            public void actionPerformed(ActionEvent e) {
                if(jProgressBar1.getValue() < 100){
                    x = x+1;
                    jProgressBar1.setValue(x);
                } else{
                    t.stop();
                   // JOptionPane.showMessageDialog(null, "acabo el progreso");
                }
            } 
        };
        
        t = new Timer(90, time); // milisengusndos
       t.start();         
        
        initComponents();
        this.setLocationRelativeTo(null);
        AWTUtilities.setWindowOpaque(this, false);
        tiempo = new Thread(this);
        tiempo.start();
    }
           //  ICONO AL EJECUTAR EL PROYECTO
                 public Image getIconImage(){
                     Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Reportes/logo5.png"));
                     return retValue;
                 }
             // FIN DEL ICONO
       
Statement sent;  
  ResultSet rs;     

   
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        panel1 = new java.awt.Panel();
        datamaxlogo = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jProgressBar1 = new javax.swing.JProgressBar();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setBackground(new java.awt.Color(255, 255, 255));
        setIconImage(getIconImage());
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        panel1.setBackground(new java.awt.Color(255, 255, 255));

        datamaxlogo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/imagenes/logo.jpg"))); // NOI18N

        jLabel3.setBackground(new java.awt.Color(255, 255, 255));
        jLabel3.setFont(new java.awt.Font("Segoe UI Black", 1, 36)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(0, 51, 204));
        jLabel3.setText("C a r g a n d o . . .");

        javax.swing.GroupLayout panel1Layout = new javax.swing.GroupLayout(panel1);
        panel1.setLayout(panel1Layout);
        panel1Layout.setHorizontalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addComponent(datamaxlogo)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 350, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(65, 65, 65))
        );
        panel1Layout.setVerticalGroup(
            panel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panel1Layout.createSequentialGroup()
                .addComponent(datamaxlogo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 64, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        getContentPane().add(panel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 510, 230));
        getContentPane().add(jProgressBar1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 230, 510, 44));

        pack();
    }// </editor-fold>//GEN-END:initComponents

   
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
            java.util.logging.Logger.getLogger(Cargainiciodelsistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Cargainiciodelsistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Cargainiciodelsistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Cargainiciodelsistema.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Cargainiciodelsistema().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel datamaxlogo;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JProgressBar jProgressBar1;
    private java.awt.Panel panel1;
    // End of variables declaration//GEN-END:variables

    @Override
    public void run() {
        while(tiempo != null){
            try {
                Thread.sleep(10000);
                tiempo = null;
                
                 int admin=0;
                try {
            Statement st = ca.createStatement();
            ResultSet rs= st.executeQuery("select * from  admin");
            while(rs.next()){
            admin=Integer.parseInt(rs.getString("id_admin"));
            }
            if(admin>0){ //Si el nombre del usuario se encontro en la base de datos quiere decir que entro un usuario al sistema
                this.dispose();
                new SI_Inicio().setVisible(true);
            } 
            else{ //No entro un usuario y entro el administrador
                               JOptionPane.showMessageDialog(null, "Para empezar aún no se crea el administrador del sistema, por favor acompañame al siguiente formulario para crearlo","                                                                                        Bienvenido a Data Max",JOptionPane.INFORMATION_MESSAGE);
                this.dispose();
                new Registrodeladmin().setVisible(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(menu_principal.class.getName()).log(Level.SEVERE, null, ex);
          
                    
        }
            
            } catch (InterruptedException ex) {
                Logger.getLogger(Cargainiciodelsistema.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
      /** CONEXION ALA BASE DE DATOS */
    SI cc= new SI();
 Connection ca= cc.conexion();
}
