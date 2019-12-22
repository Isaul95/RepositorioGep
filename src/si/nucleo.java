package si;
import Controladores.Controladorventa;
import Modelos.Modeloventa;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import javax.swing.JOptionPane;
import static si.Entradaproductos.JtablepaLaVenta;
import static si.nucleo.Cortedecaja;
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
        jPanel10 = new javax.swing.JPanel();
        cambiocombobox = new javax.swing.JLabel();
        jLabel31 = new javax.swing.JLabel();
        jLabel30 = new javax.swing.JLabel();
        subtotal = new javax.swing.JLabel();
        descuentolabel = new javax.swing.JLabel();
        total = new javax.swing.JLabel();
        labeldescuento = new javax.swing.JLabel();
        descuentocombo = new javax.swing.JLabel();
        monto = new javax.swing.JTextField();
        descuentolabel1 = new javax.swing.JLabel();
        reimprimirventa = new javax.swing.JCheckBox();
        jPanel2 = new javax.swing.JPanel();
        user = new javax.swing.JLabel();
        jButton1 = new javax.swing.JButton();
        Reloj = new javax.swing.JLabel();
        Fecha = new javax.swing.JLabel();
        jPanel27 = new javax.swing.JPanel();
        Cortedecaja = new javax.swing.JButton();
        AgregarGastos = new javax.swing.JButton();
        descuento = new javax.swing.JButton();
        ventaacredito = new javax.swing.JButton();
        deletedescuento = new javax.swing.JButton();
        cleanall = new javax.swing.JButton();
        jLabel74 = new javax.swing.JLabel();
        piezasparaacomplettarpollo = new javax.swing.JCheckBox();
        jScrollPane1 = new javax.swing.JScrollPane();
        tablaventa = new javax.swing.JTable();
        Botoncrudo = new javax.swing.JButton();
        Botoncocido = new javax.swing.JButton();
        Botonacompañantes = new javax.swing.JButton();
        jPanel28 = new javax.swing.JPanel();
        Existencias = new javax.swing.JButton();
        inventarioventas = new javax.swing.JButton();
        entradaproductos = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        venta.setBackground(new java.awt.Color(0, 51, 102));
        venta.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jPanel10.setBackground(new java.awt.Color(0, 51, 102));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Venta   ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cambiocombobox.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        cambiocombobox.setForeground(new java.awt.Color(255, 0, 0));
        cambiocombobox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cambiocombobox.setText("00.00");
        jPanel10.add(cambiocombobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 240, 140, 28));

        jLabel31.setFont(new java.awt.Font("Trebuchet MS", 1, 22)); // NOI18N
        jLabel31.setForeground(new java.awt.Color(255, 255, 255));
        jLabel31.setText("Cambio:");
        jPanel10.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 140, -1));

        jLabel30.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel30.setForeground(new java.awt.Color(255, 255, 255));
        jLabel30.setText("Subtotal");
        jLabel30.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel10.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 150, -1));

        subtotal.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        subtotal.setForeground(new java.awt.Color(255, 255, 255));
        subtotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subtotal.setText("00.00");
        jPanel10.add(subtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 50, 140, 28));

        descuentolabel.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        descuentolabel.setForeground(new java.awt.Color(255, 255, 255));
        descuentolabel.setText("Pago");
        descuentolabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel10.add(descuentolabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 190, -1));

        total.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        total.setForeground(new java.awt.Color(255, 255, 255));
        total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total.setText("00.00");
        jPanel10.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 130, 150, 50));

        labeldescuento.setFont(new java.awt.Font("Trebuchet MS", 1, 22)); // NOI18N
        labeldescuento.setForeground(new java.awt.Color(255, 255, 255));
        labeldescuento.setText("Descuento:");
        jPanel10.add(labeldescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 170, -1));

        descuentocombo.setFont(new java.awt.Font("Arial Black", 1, 28)); // NOI18N
        descuentocombo.setForeground(new java.awt.Color(255, 0, 51));
        descuentocombo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descuentocombo.setText("00.00");
        jPanel10.add(descuentocombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 90, 140, -1));

        monto.setBackground(new java.awt.Color(0, 148, 204));
        monto.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
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
        monto.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                montoKeyReleased(evt);
            }
        });
        jPanel10.add(monto, new org.netbeans.lib.awtextra.AbsoluteConstraints(280, 190, 150, -1));

        descuentolabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        descuentolabel1.setForeground(new java.awt.Color(255, 255, 255));
        descuentolabel1.setText("Total");
        descuentolabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel10.add(descuentolabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 190, -1));

        reimprimirventa.setBackground(new java.awt.Color(0, 51, 102));
        reimprimirventa.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        reimprimirventa.setForeground(new java.awt.Color(255, 255, 255));
        reimprimirventa.setText("Reimprimirventa");
        jPanel10.add(reimprimirventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 20, 240, -1));

        venta.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 380, 450, 280));

        jPanel2.setBackground(new java.awt.Color(255, 255, 255));

        user.setBackground(new java.awt.Color(0, 160, 204));
        user.setFont(new java.awt.Font("Tahoma", 1, 36)); // NOI18N
        user.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);

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

        Reloj.setFont(new java.awt.Font("Times New Roman", 1, 27)); // NOI18N
        Reloj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reloj.setText("00:00:00");

        Fecha.setFont(new java.awt.Font("Times New Roman", 1, 27)); // NOI18N
        Fecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Fecha.setText("DD/MM/YYYY");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(Fecha)
                .addGap(18, 18, 18)
                .addComponent(Reloj, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 70, Short.MAX_VALUE)
                .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(54, 54, 54)
                .addComponent(jButton1))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGap(0, 1, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(user, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Reloj, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(Fecha, javax.swing.GroupLayout.PREFERRED_SIZE, 55, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(4, 4, 4))
        );

        venta.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 890, 60));

        jPanel27.setBackground(new java.awt.Color(0, 51, 102));
        jPanel27.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Otras opciones", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel27.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Cortedecaja.setBackground(new java.awt.Color(0, 51, 102));
        Cortedecaja.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Cortedecaja.setForeground(new java.awt.Color(255, 255, 255));
        Cortedecaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/004-cash register.png"))); // NOI18N
        Cortedecaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CortedecajaActionPerformed(evt);
            }
        });
        jPanel27.add(Cortedecaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 30, -1, 60));

        AgregarGastos.setBackground(new java.awt.Color(0, 51, 102));
        AgregarGastos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        AgregarGastos.setForeground(new java.awt.Color(255, 255, 255));
        AgregarGastos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/combustible (1).png"))); // NOI18N
        AgregarGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarGastosActionPerformed(evt);
            }
        });
        jPanel27.add(AgregarGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 30, -1, 60));

        descuento.setBackground(new java.awt.Color(0, 51, 102));
        descuento.setFont(new java.awt.Font("Arial Black", 1, 23)); // NOI18N
        descuento.setForeground(new java.awt.Color(255, 255, 255));
        descuento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/005-discount.png"))); // NOI18N
        descuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descuentoActionPerformed(evt);
            }
        });
        jPanel27.add(descuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 60));

        ventaacredito.setBackground(new java.awt.Color(0, 51, 102));
        ventaacredito.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        ventaacredito.setForeground(new java.awt.Color(255, 255, 255));
        ventaacredito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/dinero (1).png"))); // NOI18N
        ventaacredito.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        ventaacredito.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        ventaacredito.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        ventaacredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventaacreditoActionPerformed(evt);
            }
        });
        jPanel27.add(ventaacredito, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 100, 60, 60));

        deletedescuento.setBackground(new java.awt.Color(255, 0, 0));
        deletedescuento.setFont(new java.awt.Font("Arial Black", 1, 23)); // NOI18N
        deletedescuento.setForeground(new java.awt.Color(255, 51, 51));
        deletedescuento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/005-discount.png"))); // NOI18N
        deletedescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletedescuentoActionPerformed(evt);
            }
        });
        jPanel27.add(deletedescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 170, -1, 60));

        cleanall.setBackground(new java.awt.Color(0, 51, 102));
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
        jPanel27.add(cleanall, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 170, 60, 60));

        venta.add(jPanel27, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 380, 170, 290));

        jLabel74.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel74.setForeground(new java.awt.Color(255, 255, 255));
        jLabel74.setText("En venta :");
        venta.add(jLabel74, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 120, -1));

        piezasparaacomplettarpollo.setBackground(new java.awt.Color(0, 51, 102));
        piezasparaacomplettarpollo.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        piezasparaacomplettarpollo.setForeground(new java.awt.Color(255, 255, 255));
        piezasparaacomplettarpollo.setText("Completar pollo");
        venta.add(piezasparaacomplettarpollo, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 60, 180, -1));

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

        venta.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, -1, 280));

        Botoncrudo.setBackground(new java.awt.Color(255, 255, 255));
        Botoncrudo.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        Botoncrudo.setForeground(new java.awt.Color(255, 0, 0));
        Botoncrudo.setText("CRUDO");
        Botoncrudo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotoncrudoActionPerformed(evt);
            }
        });
        venta.add(Botoncrudo, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 100, 350, 77));

        Botoncocido.setBackground(new java.awt.Color(255, 255, 255));
        Botoncocido.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        Botoncocido.setForeground(new java.awt.Color(255, 0, 0));
        Botoncocido.setText("COCIDO");
        Botoncocido.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotoncocidoActionPerformed(evt);
            }
        });
        venta.add(Botoncocido, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 190, 350, 80));

        Botonacompañantes.setBackground(new java.awt.Color(255, 255, 255));
        Botonacompañantes.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
        Botonacompañantes.setForeground(new java.awt.Color(255, 0, 0));
        Botonacompañantes.setText("ACOMPAÑANTES");
        Botonacompañantes.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonacompañantesActionPerformed(evt);
            }
        });
        venta.add(Botonacompañantes, new org.netbeans.lib.awtextra.AbsoluteConstraints(500, 280, 350, 81));

        jPanel28.setBackground(new java.awt.Color(0, 51, 102));
        jPanel28.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "Ventanas", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 18), new java.awt.Color(255, 255, 255))); // NOI18N
        jPanel28.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        Existencias.setBackground(new java.awt.Color(255, 255, 255));
        Existencias.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Existencias.setForeground(new java.awt.Color(204, 0, 0));
        Existencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/actualizar.png"))); // NOI18N
        Existencias.setText("¿?");
        Existencias.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Existencias.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Existencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExistenciasActionPerformed(evt);
            }
        });
        jPanel28.add(Existencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 30, 80, 90));

        inventarioventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/simbolodolar.png"))); // NOI18N
        inventarioventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventarioventasActionPerformed(evt);
            }
        });
        jPanel28.add(inventarioventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 30, 80, 90));

        entradaproductos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/compra.png"))); // NOI18N
        entradaproductos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                entradaproductosActionPerformed(evt);
            }
        });
        jPanel28.add(entradaproductos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 140, 80, 100));

        venta.add(jPanel28, new org.netbeans.lib.awtextra.AbsoluteConstraints(690, 380, 200, 290));

        getContentPane().add(venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, -3, 898, 750));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void ExistenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExistenciasActionPerformed
        if(Controladorventa.noduplicarexistencias==false){new Existencias().setVisible(true);}
    }//GEN-LAST:event_ExistenciasActionPerformed

    private void BotonacompañantesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonacompañantesActionPerformed
         if(Controladorventa.noduplicaracompañantes==false){new Acompañantes().setVisible(true);}
    }//GEN-LAST:event_BotonacompañantesActionPerformed

    private void BotoncocidoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotoncocidoActionPerformed
       if(Controladorventa.noduplicarcocido==false){new Cocido().setVisible(true);}
    }//GEN-LAST:event_BotoncocidoActionPerformed

    private void BotoncrudoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotoncrudoActionPerformed
         if(Controladorventa.noduplicarcrudo==false){new Crudo().setVisible(true);}   
    }//GEN-LAST:event_BotoncrudoActionPerformed

    private void cleanallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanallActionPerformed
        Controladorventa.limpiarventa();
    }//GEN-LAST:event_cleanallActionPerformed

    private void deletedescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletedescuentoActionPerformed
        Controladorventa.deletedescuento();
    }//GEN-LAST:event_deletedescuentoActionPerformed

    private void ventaacreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventaacreditoActionPerformed
        Controladorventa.agregarventaacredito();
    }//GEN-LAST:event_ventaacreditoActionPerformed

    private void descuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descuentoActionPerformed
        Controladorventa.descuentos();
    }//GEN-LAST:event_descuentoActionPerformed

    private void AgregarGastosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_AgregarGastosActionPerformed
        if(Controladorventa.noduplicargastos==false){new Gastos().setVisible(true);}
    }//GEN-LAST:event_AgregarGastosActionPerformed

    private void CortedecajaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CortedecajaActionPerformed
     // if(Controladorventa.noduplicarcorte==false){ 
          new Cortecaja().setVisible(true);  
      //}  if(Integer.parseInt(hora)>=17||Integer.parseInt(hora)>=16){   }else JOptionPane.showMessageDialog(null,"Aún es muy pronto, el corte se hace después de las 5:15 pm","Verifica",JOptionPane.INFORMATION_MESSAGE);//Se puede habilitar el corte alas 5:15 pm
    }//GEN-LAST:event_CortedecajaActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Controladorventa.botones_salir();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void montoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_montoKeyReleased
        char tecla = evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            Controladorventa.montoKeyRealeased();
                    
        }
    }//GEN-LAST:event_montoKeyReleased

    private void montoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_montoFocusLost
        Controladorventa.montoFocusLost();
    }//GEN-LAST:event_montoFocusLost

    private void montoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_montoFocusGained
        Controladorventa.montoFocusGained();
    }//GEN-LAST:event_montoFocusGained

    private void inventarioventasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_inventarioventasActionPerformed
new Inventarioventas().setVisible(true);   
    }//GEN-LAST:event_inventarioventasActionPerformed

    private void entradaproductosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_entradaproductosActionPerformed
      new Entradaproductos().setVisible(true);
    }//GEN-LAST:event_entradaproductosActionPerformed

    private void tablaventaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaventaMouseClicked
       Controladorventa.tablaventa();
    }//GEN-LAST:event_tablaventaMouseClicked
 private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {                                         
   new ProductosExternos().setVisible(true);        
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
    private javax.swing.JButton Botonacompañantes;
    private javax.swing.JButton Botoncocido;
    private javax.swing.JButton Botoncrudo;
    public static javax.swing.JButton Cortedecaja;
    private javax.swing.JButton Existencias;
    private javax.swing.JLabel Fecha;
    private javax.swing.JLabel Reloj;
    public static javax.swing.JLabel cambiocombobox;
    public static javax.swing.JButton cleanall;
    public static javax.swing.JButton deletedescuento;
    public static javax.swing.JButton descuento;
    public static javax.swing.JLabel descuentocombo;
    public static javax.swing.JLabel descuentolabel;
    private javax.swing.JLabel descuentolabel1;
    private javax.swing.JButton entradaproductos;
    private javax.swing.JButton inventarioventas;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel74;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel27;
    private javax.swing.JPanel jPanel28;
    private javax.swing.JScrollPane jScrollPane1;
    public static javax.swing.JLabel labeldescuento;
    public static javax.swing.JTextField monto;
    public static javax.swing.JCheckBox piezasparaacomplettarpollo;
    public static javax.swing.JCheckBox reimprimirventa;
    public static javax.swing.JLabel subtotal;
    public static javax.swing.JTable tablaventa;
    public static javax.swing.JLabel total;
    private javax.swing.JLabel user;
    public static javax.swing.JPanel venta;
    public static javax.swing.JButton ventaacredito;
    // End of variables declaration//GEN-END:variables
}
