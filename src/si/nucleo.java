package si;
import Controladores.Controladorexistencias;
import Controladores.Controladorventa;
import Modelos.Modeloventa;
import static com.lowagie.text.pdf.PdfName.ca;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.sql.Connection;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import static javax.swing.WindowConstants.DISPOSE_ON_CLOSE;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.view.JasperViewer;
import AppPackage.AnimationClass;
public final class nucleo extends javax.swing.JFrame implements Runnable{
   
    Thread hilo;
public static String hora,minutos,segundos,usuarioname=SI_Inicio.text_user.getText();
//ESO ES DESPUES DE AGREGAR PRODUCTO EXTERNO
      
  public nucleo() {
        initComponents(); 
        this.setLocation(30, 0);
       hilo=new Thread(this);
     hilo.start();
        setVisible(true);  // SE OBTIENE LA HORA DEL SISTEMA PARA MOSTAR EN PANTALLA
user.setText(usuarioname);
 setIconImage(getIconImage());  //La variable que le manda la imagen (DataMax) al proyecto 
        Controladorventa.metodos_al_iniciar_menuprincipal();
         Controladorexistencias.metodosalabrirexistencias();
            Fecha.setText(Controladorventa.fecha().toString()); // SE OBTIENE LA FECHA DEL SISTEMA PARA MOSTAR EN PANTALLA
  
    }
  public static void hora(){
        Calendar calendario= new GregorianCalendar();
        Date horaactual=new Date();
        calendario.setTime(horaactual);
    hora=calendario.get(Calendar.HOUR_OF_DAY)>9?""+calendario.get(Calendar.HOUR_OF_DAY):"0"+calendario.get(Calendar.HOUR_OF_DAY);
    minutos=calendario.get(Calendar.MINUTE)>9?""+calendario.get(Calendar.MINUTE):"0"+calendario.get(Calendar.MINUTE);
    segundos= calendario.get(Calendar.SECOND)>9?""+calendario.get(Calendar.SECOND):"0"+calendario.get(Calendar.SECOND);        
     }
    //  ICONO AL EJECUTAR EL PROYECTO
                 public Image getIconImage(){
                     Image retValue = Toolkit.getDefaultToolkit().getImage(ClassLoader.getSystemResource("Reportes/logo.jpg"));
                     return retValue;
                 }
   public void run() {
      Thread ct = Thread.currentThread();
      while(ct==hilo){
          hora();
          Reloj.setText(hora+":"+minutos+":"+segundos);
      }
    }   @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        venta = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        user = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Reloj = new javax.swing.JLabel();
        Fecha = new javax.swing.JLabel();
        prueba = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaventa = new javax.swing.JTable();
        jLabel75 = new javax.swing.JLabel();
        user_nombre = new javax.swing.JTextField();
        jLabel76 = new javax.swing.JLabel();
        jLabel78 = new javax.swing.JLabel();
        user_edad = new javax.swing.JTextField();
        jLabel79 = new javax.swing.JLabel();
        user_sexo = new javax.swing.JComboBox();
        calendar_fecha_nacimiento = new com.toedter.calendar.JDateChooser();
        descuento = new javax.swing.JButton();
        deletedescuento = new javax.swing.JButton();
        cleanall = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        existenciadeproductos = new javax.swing.JTable();
        busqueda = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
        jLabel81 = new javax.swing.JLabel();
        jLabel82 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel77 = new javax.swing.JLabel();
        AgregarGastos = new javax.swing.JButton();
        inventarioventas = new javax.swing.JButton();
        Cortedecaja = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        venta.setBackground(new java.awt.Color(135, 193, 193));
        venta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        user.setBackground(new java.awt.Color(0, 160, 204));
        user.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        user.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel2.add(user, new org.netbeans.lib.awtextra.AbsoluteConstraints(538, 1, 311, 50));

        jButton1.setBackground(new java.awt.Color(255, 255, 255));
        jButton1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jButton1.setForeground(new java.awt.Color(255, 0, 0));
        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/salir-flecha-derecha (1).png"))); // NOI18N
        jButton1.setText("Salir");
        jButton1.setHorizontalAlignment(javax.swing.SwingConstants.LEFT);
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(930, 0, -1, 50));

        Reloj.setFont(new java.awt.Font("Times New Roman", 1, 27)); // NOI18N
        Reloj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reloj.setText("00:00:00");
        jPanel2.add(Reloj, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 1, 128, 55));

        Fecha.setFont(new java.awt.Font("Times New Roman", 1, 27)); // NOI18N
        Fecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Fecha.setText("DD/MM/YYYY");
        jPanel2.add(Fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 1, -1, 55));

        venta.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 60));

        prueba.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        prueba.setForeground(new java.awt.Color(255, 255, 255));
        prueba.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/005-discount.png"))); // NOI18N
        venta.add(prueba, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 170, 40, -1));

        tablaventa = new javax.swing.JTable(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        tablaventa.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        tablaventa.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        tablaventa.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tablaventaMouseClicked(evt);
            }
        });
        jScrollPane1.setViewportView(tablaventa);

        venta.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 110, 480, 280));

        jLabel75.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel75.setForeground(new java.awt.Color(255, 255, 255));
        jLabel75.setText("En venta :");
        venta.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 120, -1));

        user_nombre.setBackground(new java.awt.Color(135, 193, 193));
        user_nombre.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        user_nombre.setForeground(new java.awt.Color(255, 255, 255));
        user_nombre.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        user_nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_nombreActionPerformed(evt);
            }
        });
        venta.add(user_nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 150, 380, 30));

        jLabel76.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel76.setForeground(new java.awt.Color(255, 255, 255));
        jLabel76.setText("Datos del paciente:");
        venta.add(jLabel76, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 60, 290, -1));

        jLabel78.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel78.setForeground(new java.awt.Color(255, 255, 255));
        jLabel78.setText("Sexo:");
        venta.add(jLabel78, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 200, 90, -1));

        user_edad.setBackground(new java.awt.Color(135, 193, 193));
        user_edad.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        user_edad.setForeground(new java.awt.Color(255, 255, 255));
        user_edad.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        user_edad.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                user_edadActionPerformed(evt);
            }
        });
        venta.add(user_edad, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 240, 90, 40));

        jLabel79.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel79.setForeground(new java.awt.Color(255, 255, 255));
        jLabel79.setText("Fecha de nacimiento");
        venta.add(jLabel79, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 300, 240, -1));

        user_sexo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "Masculino", "Femenino" }));
        user_sexo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(135, 193, 193)));
        venta.add(user_sexo, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 240, 110, 40));

        calendar_fecha_nacimiento.setBackground(new java.awt.Color(135, 193, 193));
        calendar_fecha_nacimiento.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(135, 193, 193)));
        calendar_fecha_nacimiento.setForeground(new java.awt.Color(255, 255, 255));
        calendar_fecha_nacimiento.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        venta.add(calendar_fecha_nacimiento, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 340, 240, 50));

        descuento.setBackground(new java.awt.Color(255, 255, 255));
        descuento.setFont(new java.awt.Font("Arial Black", 1, 23)); // NOI18N
        descuento.setForeground(new java.awt.Color(255, 255, 255));
        descuento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/005-discount.png"))); // NOI18N
        descuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descuentoActionPerformed(evt);
            }
        });
        venta.add(descuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 140, 70, 70));

        deletedescuento.setBackground(new java.awt.Color(255, 255, 255));
        deletedescuento.setFont(new java.awt.Font("Arial Black", 1, 23)); // NOI18N
        deletedescuento.setForeground(new java.awt.Color(255, 51, 51));
        deletedescuento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/005-discount.png"))); // NOI18N
        deletedescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletedescuentoActionPerformed(evt);
            }
        });
        venta.add(deletedescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 220, 70, 70));

        cleanall.setBackground(new java.awt.Color(255, 255, 255));
        cleanall.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        cleanall.setForeground(new java.awt.Color(255, 255, 255));
        cleanall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/cancelar2.png"))); // NOI18N
        cleanall.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cleanall.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        cleanall.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cleanall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanallActionPerformed(evt);
            }
        });
        venta.add(cleanall, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 310, 70, -1));

        existenciadeproductos = new javax.swing.JTable(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        existenciadeproductos.setBackground(new java.awt.Color(135, 193, 193));
        existenciadeproductos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        existenciadeproductos.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        existenciadeproductos.setForeground(new java.awt.Color(255, 255, 255));
        existenciadeproductos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {},
                {},
                {},
                {}
            },
            new String [] {

            }
        ));
        existenciadeproductos.setRowSelectionAllowed(false);
        existenciadeproductos.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                existenciadeproductosMouseClicked(evt);
            }
        });
        jScrollPane2.setViewportView(existenciadeproductos);

        venta.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, 970, 160));

        busqueda.setBackground(new java.awt.Color(135, 193, 193));
        busqueda.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        busqueda.setForeground(new java.awt.Color(255, 255, 255));
        busqueda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaKeyReleased(evt);
            }
        });
        venta.add(busqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, 250, 30));

        jLabel80.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel80.setForeground(new java.awt.Color(255, 255, 255));
        jLabel80.setText("Busqueda de estudios.");
        venta.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 290, -1));

        jLabel81.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel81.setForeground(new java.awt.Color(255, 255, 255));
        jLabel81.setText("Edad:");
        venta.add(jLabel81, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 200, 290, -1));

        jLabel82.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel82.setForeground(new java.awt.Color(255, 255, 255));
        jLabel82.setText("Nombre:");
        venta.add(jLabel82, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 110, 290, -1));

        jPanel1.setBackground(new java.awt.Color(135, 193, 193));
        jPanel1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel77.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel77.setForeground(new java.awt.Color(255, 255, 255));
        jLabel77.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/portapapeles.png"))); // NOI18N
        jLabel77.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel77.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel77MouseClicked(evt);
            }
        });
        jPanel1.add(jLabel77, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 60, -1));

        AgregarGastos.setBackground(new java.awt.Color(135, 193, 193));
        AgregarGastos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        AgregarGastos.setForeground(new java.awt.Color(255, 255, 255));
        AgregarGastos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/combustible (1).png"))); // NOI18N
        AgregarGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarGastosActionPerformed(evt);
            }
        });
        jPanel1.add(AgregarGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 110, 40, 50));

        inventarioventas.setBackground(new java.awt.Color(135, 193, 193));
        inventarioventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/simbolodolar.png"))); // NOI18N
        inventarioventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventarioventasActionPerformed(evt);
            }
        });
        jPanel1.add(inventarioventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, 40, -1));

        Cortedecaja.setBackground(new java.awt.Color(135, 193, 193));
        Cortedecaja.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Cortedecaja.setForeground(new java.awt.Color(255, 255, 255));
        Cortedecaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/004-cash register.png"))); // NOI18N
        Cortedecaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CortedecajaActionPerformed(evt);
            }
        });
        jPanel1.add(Cortedecaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 230, 40, 50));

        venta.add(jPanel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 70, 130, 370));

        getContentPane().add(venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1060, 660));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cleanallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanallActionPerformed
        Controladorventa.limpiarventa();
    }//GEN-LAST:event_cleanallActionPerformed

    private void deletedescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletedescuentoActionPerformed
        Controladorventa.deletedescuento();
    }//GEN-LAST:event_deletedescuentoActionPerformed

    private void descuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descuentoActionPerformed
        Controladorventa.descuentos();
    }//GEN-LAST:event_descuentoActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Controladorventa.botones_salir();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tablaventaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaventaMouseClicked
       Controladorventa.tablaventa();
    }//GEN-LAST:event_tablaventaMouseClicked

    private void user_nombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_nombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_nombreActionPerformed

    private void user_edadActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_user_edadActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_user_edadActionPerformed

    private void existenciadeproductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_existenciadeproductosMouseClicked
        Controladorexistencias.mandaraventa();
    }//GEN-LAST:event_existenciadeproductosMouseClicked

    private void busquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedaKeyReleased
        Controladorexistencias.mostrartodoslosproductosenexistencias(busqueda.getText());
    }//GEN-LAST:event_busquedaKeyReleased

    private void jLabel77MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel77MouseClicked
   // 
       // 

          
       System.out.println("Entre al MouseClicked: mi posicion en x antes de ejecutar algo es:" +AgregarGastos.getX());
           AnimationClass animacion= new AnimationClass();
             
         // animacion .jLabelXRight(- 40,70, 10, 5, prueba);//   
   //         animacion.jButtonXLeft(20, -40, 20, 10, AgregarGastos);    
             AgregarGastos.setBounds(20, 110, 40, 50);

    System.out.println("Entre al MouseClicked: mi posicion en x intermedio  de ejecutar algo es:" +AgregarGastos.getX());
      //  AnimationClass animacion2= new AnimationClass();
 //animacion2.jLabelXLeft(70,-40, 10, 5, prueba);//
    //   animacion2.jButtonXRight(-40, 20,20, 10, AgregarGastos);

              System.out.println("Entre al MouseClicked: mi posicion en x despues  de ejecutar algo es:" +AgregarGastos.getX());
        
 
            
     
    }//GEN-LAST:event_jLabel77MouseClicked

    private void CortedecajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CortedecajaActionPerformed
        // if(Controladorventa.noduplicarcorte==false){
            new Cortecaja().setVisible(true);
            //}  if(Integer.parseInt(hora)>=17||Integer.parseInt(hora)>=16){   }else JOptionPane.showMessageDialog(null,"Aún es muy pronto, el corte se hace después de las 5:15 pm","Verifica",JOptionPane.INFORMATION_MESSAGE);//Se puede habilitar el corte alas 5:15 pm
    }//GEN-LAST:event_CortedecajaActionPerformed

    private void AgregarGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarGastosActionPerformed
        if(Controladorventa.noduplicargastos==false){new Gastos().setVisible(true);}
    }//GEN-LAST:event_AgregarGastosActionPerformed

    private void inventarioventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventarioventasActionPerformed
        if(Controladorventa.noduplicarinventarioventas==false){  new Inventarioventas().setVisible(true); }
    }//GEN-LAST:event_inventarioventasActionPerformed
 private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
// MODIFICAR   new ProductosExternos().setVisible(true);        
    }                                           /**
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
            java.util.logging.Logger.getLogger(nucleo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(nucleo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(nucleo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(nucleo.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new nucleo().setVisible(true);
            }
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    public static javax.swing.JButton AgregarGastos;
    public static javax.swing.JButton Cortedecaja;
    private javax.swing.JLabel Fecha;
    public static javax.swing.JLabel Reloj;
    private javax.swing.JTextField busqueda;
    public static com.toedter.calendar.JDateChooser calendar_fecha_nacimiento;
    public static javax.swing.JButton cleanall;
    public static javax.swing.JButton deletedescuento;
    public static javax.swing.JButton descuento;
    public static javax.swing.JTable existenciadeproductos;
    private javax.swing.JButton inventarioventas;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel76;
    private javax.swing.JLabel jLabel77;
    private javax.swing.JLabel jLabel78;
    private javax.swing.JLabel jLabel79;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JLabel jLabel81;
    private javax.swing.JLabel jLabel82;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JLabel prueba;
    public static javax.swing.JTable tablaventa;
    private javax.swing.JLabel user;
    public static javax.swing.JTextField user_edad;
    public static javax.swing.JTextField user_nombre;
    public static javax.swing.JComboBox user_sexo;
    public static javax.swing.JPanel venta;
    // End of variables declaration//GEN-END:variables
}