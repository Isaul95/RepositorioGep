package si;
import Controladores.Controladorexistencias;
import Controladores.Controladorventa;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.KeyEvent;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

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
        Controladorventa.metodos_al_iniciar_menuprincipal(usuarioname);
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
        descuento = new javax.swing.JButton();
        deletedescuento = new javax.swing.JButton();
        cleanall = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        existenciadeproductos = new javax.swing.JTable();
        busqueda = new javax.swing.JTextField();
        jLabel80 = new javax.swing.JLabel();
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
        AgregarGastos = new javax.swing.JButton();
        inventarioventas = new javax.swing.JButton();
        Cortedecaja = new javax.swing.JButton();
        Existencias = new javax.swing.JButton();
        Registro_user = new javax.swing.JButton();
        reimprimirventa = new javax.swing.JCheckBox();
        Registro_productos = new javax.swing.JButton();
        ventacredito = new javax.swing.JCheckBox();
        ventas_acredito = new javax.swing.JButton();
        Registro_paquete = new javax.swing.JButton();
        pagos = new javax.swing.JButton();
        utilidades = new javax.swing.JButton();

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
        jPanel2.add(jButton1, new org.netbeans.lib.awtextra.AbsoluteConstraints(1090, 0, -1, 50));

        Reloj.setFont(new java.awt.Font("Times New Roman", 1, 27)); // NOI18N
        Reloj.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Reloj.setText("00:00:00");
        jPanel2.add(Reloj, new org.netbeans.lib.awtextra.AbsoluteConstraints(210, 1, 128, 55));

        Fecha.setFont(new java.awt.Font("Times New Roman", 1, 27)); // NOI18N
        Fecha.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        Fecha.setText("DD/MM/YYYY");
        jPanel2.add(Fecha, new org.netbeans.lib.awtextra.AbsoluteConstraints(6, 1, -1, 55));

        venta.add(jPanel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1220, 60));

        prueba.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        prueba.setForeground(new java.awt.Color(255, 255, 255));
        prueba.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/005-discount.png"))); // NOI18N
        venta.add(prueba, new org.netbeans.lib.awtextra.AbsoluteConstraints(-40, 170, 40, -1));

        tablaventa = new javax.swing.JTable(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        tablaventa.setFont(new java.awt.Font("Dialog", 1, 16)); // NOI18N
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

        venta.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 100, 740, 290));

        jLabel75.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel75.setText("En venta :");
        venta.add(jLabel75, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 60, 120, -1));

        descuento.setBackground(new java.awt.Color(135, 193, 193));
        descuento.setFont(new java.awt.Font("Arial Black", 1, 23)); // NOI18N
        descuento.setForeground(new java.awt.Color(255, 255, 255));
        descuento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/005-discount.png"))); // NOI18N
        descuento.setToolTipText("Hacer descuento");
        descuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                descuentoActionPerformed(evt);
            }
        });
        venta.add(descuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 100, 40, 50));

        deletedescuento.setBackground(new java.awt.Color(135, 193, 193));
        deletedescuento.setFont(new java.awt.Font("Arial Black", 1, 23)); // NOI18N
        deletedescuento.setForeground(new java.awt.Color(255, 51, 51));
        deletedescuento.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/005-discount.png"))); // NOI18N
        deletedescuento.setToolTipText("Eliminar descuento");
        deletedescuento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                deletedescuentoActionPerformed(evt);
            }
        });
        venta.add(deletedescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 180, 40, 50));

        cleanall.setBackground(new java.awt.Color(135, 193, 193));
        cleanall.setFont(new java.awt.Font("Tahoma", 1, 22)); // NOI18N
        cleanall.setForeground(new java.awt.Color(255, 255, 255));
        cleanall.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/cancelar2.png"))); // NOI18N
        cleanall.setToolTipText("Borrar toda la venta");
        cleanall.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        cleanall.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        cleanall.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        cleanall.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cleanallActionPerformed(evt);
            }
        });
        venta.add(cleanall, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 260, 40, 50));

        existenciadeproductos = new javax.swing.JTable(){
            public boolean isCellEditable(int filas, int columnas){
                return false;
            }
        };
        existenciadeproductos.setBackground(new java.awt.Color(135, 193, 193));
        existenciadeproductos.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        existenciadeproductos.setFont(new java.awt.Font("Dialog", 1, 17)); // NOI18N
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

        venta.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 490, 1130, 160));

        busqueda.setBackground(new java.awt.Color(135, 193, 193));
        busqueda.setFont(new java.awt.Font("Dialog", 0, 18)); // NOI18N
        busqueda.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        busqueda.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                busquedaKeyReleased(evt);
            }
        });
        venta.add(busqueda, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 450, 610, 30));

        jLabel80.setFont(new java.awt.Font("Dialog", 1, 24)); // NOI18N
        jLabel80.setText("Busqueda de estudios.");
        venta.add(jLabel80, new org.netbeans.lib.awtextra.AbsoluteConstraints(80, 410, 290, -1));

        jPanel10.setBackground(new java.awt.Color(135, 193, 193));
        jPanel10.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "   Venta   ", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.TOP, new java.awt.Font("Arial", 1, 20))); // NOI18N
        jPanel10.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        cambiocombobox.setFont(new java.awt.Font("Trebuchet MS", 1, 36)); // NOI18N
        cambiocombobox.setForeground(new java.awt.Color(255, 0, 0));
        cambiocombobox.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        cambiocombobox.setText("00.00");
        jPanel10.add(cambiocombobox, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 240, 140, 28));

        jLabel31.setFont(new java.awt.Font("Trebuchet MS", 1, 22)); // NOI18N
        jLabel31.setText("Cambio:");
        jPanel10.add(jLabel31, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 230, 90, -1));

        jLabel30.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        jLabel30.setText("Subt.");
        jLabel30.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel10.add(jLabel30, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 60, 70, -1));

        subtotal.setBackground(new java.awt.Color(0, 0, 0));
        subtotal.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        subtotal.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        subtotal.setText("00.00");
        jPanel10.add(subtotal, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 50, 140, 28));

        descuentolabel.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        descuentolabel.setText("Pago");
        descuentolabel.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel10.add(descuentolabel, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 190, 60, -1));

        total.setBackground(new java.awt.Color(0, 0, 0));
        total.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        total.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        total.setText("00.00");
        jPanel10.add(total, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 130, 150, 50));

        labeldescuento.setFont(new java.awt.Font("Trebuchet MS", 1, 22)); // NOI18N
        labeldescuento.setText("Desc:");
        jPanel10.add(labeldescuento, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 100, 60, -1));

        descuentocombo.setFont(new java.awt.Font("Arial Black", 1, 28)); // NOI18N
        descuentocombo.setForeground(new java.awt.Color(255, 0, 51));
        descuentocombo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        descuentocombo.setText("00.00");
        jPanel10.add(descuentocombo, new org.netbeans.lib.awtextra.AbsoluteConstraints(100, 90, 140, -1));

        monto.setBackground(new java.awt.Color(135, 193, 193));
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
        jPanel10.add(monto, new org.netbeans.lib.awtextra.AbsoluteConstraints(90, 190, 150, -1));

        descuentolabel1.setFont(new java.awt.Font("Trebuchet MS", 1, 24)); // NOI18N
        descuentolabel1.setText("Total");
        descuentolabel1.setHorizontalTextPosition(javax.swing.SwingConstants.RIGHT);
        jPanel10.add(descuentolabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 140, 60, -1));

        venta.add(jPanel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(900, 80, 240, 280));

        AgregarGastos.setBackground(new java.awt.Color(135, 193, 193));
        AgregarGastos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        AgregarGastos.setForeground(new java.awt.Color(255, 255, 255));
        AgregarGastos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/combustible (1).png"))); // NOI18N
        AgregarGastos.setToolTipText("Registrar gastos");
        AgregarGastos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                AgregarGastosActionPerformed(evt);
            }
        });
        venta.add(AgregarGastos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 210, 40, 50));

        inventarioventas.setBackground(new java.awt.Color(135, 193, 193));
        inventarioventas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/simbolodolar.png"))); // NOI18N
        inventarioventas.setToolTipText("Ventas realizadas");
        inventarioventas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                inventarioventasActionPerformed(evt);
            }
        });
        venta.add(inventarioventas, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 90, 40, -1));

        Cortedecaja.setBackground(new java.awt.Color(135, 193, 193));
        Cortedecaja.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Cortedecaja.setForeground(new java.awt.Color(255, 255, 255));
        Cortedecaja.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/004-cash register.png"))); // NOI18N
        Cortedecaja.setToolTipText("Corte de caja");
        Cortedecaja.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CortedecajaActionPerformed(evt);
            }
        });
        venta.add(Cortedecaja, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 270, 40, 50));

        Existencias.setBackground(new java.awt.Color(135, 193, 193));
        Existencias.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Existencias.setForeground(new java.awt.Color(204, 0, 0));
        Existencias.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/pdf.png"))); // NOI18N
        Existencias.setToolTipText("Descargar o eliminar PDF´S");
        Existencias.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Existencias.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Existencias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ExistenciasActionPerformed(evt);
            }
        });
        venta.add(Existencias, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 330, 40, 50));

        Registro_user.setBackground(new java.awt.Color(135, 193, 193));
        Registro_user.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Registro_user.setForeground(new java.awt.Color(204, 0, 0));
        Registro_user.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/plan-de-estudios.png"))); // NOI18N
        Registro_user.setToolTipText("Registrar usuarios");
        Registro_user.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Registro_user.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Registro_user.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Registro_userActionPerformed(evt);
            }
        });
        venta.add(Registro_user, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 390, 40, 50));

        reimprimirventa.setBackground(new java.awt.Color(135, 193, 193));
        reimprimirventa.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        reimprimirventa.setText("Reimprimirventa");
        reimprimirventa.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        venta.add(reimprimirventa, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 70, -1, -1));

        Registro_productos.setBackground(new java.awt.Color(135, 193, 193));
        Registro_productos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Registro_productos.setForeground(new java.awt.Color(204, 0, 0));
        Registro_productos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/Prodcuts2.png"))); // NOI18N
        Registro_productos.setToolTipText("Registrar nuevo producto");
        Registro_productos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Registro_productos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Registro_productos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Registro_productosActionPerformed(evt);
            }
        });
        venta.add(Registro_productos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 460, 40, 50));

        ventacredito.setBackground(new java.awt.Color(135, 193, 193));
        ventacredito.setFont(new java.awt.Font("Dialog", 1, 20)); // NOI18N
        ventacredito.setText("Venta credito");
        ventacredito.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(255, 255, 255)));
        venta.add(ventacredito, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 70, -1, -1));

        ventas_acredito.setBackground(new java.awt.Color(135, 193, 193));
        ventas_acredito.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/egresos1.png"))); // NOI18N
        ventas_acredito.setToolTipText("Ventas a credito");
        ventas_acredito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ventas_acreditoActionPerformed(evt);
            }
        });
        venta.add(ventas_acredito, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 150, 40, -1));

        Registro_paquete.setBackground(new java.awt.Color(135, 193, 193));
        Registro_paquete.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        Registro_paquete.setForeground(new java.awt.Color(204, 0, 0));
        Registro_paquete.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/Prodcuts2.png"))); // NOI18N
        Registro_paquete.setToolTipText("Registrar nuevo producto");
        Registro_paquete.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        Registro_paquete.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        Registro_paquete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                Registro_paqueteActionPerformed(evt);
            }
        });
        venta.add(Registro_paquete, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 530, 40, 50));

        pagos.setBackground(new java.awt.Color(135, 193, 193));
        pagos.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        pagos.setForeground(new java.awt.Color(204, 0, 0));
        pagos.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/simbolo-del-dolar-americano (1).png"))); // NOI18N
        pagos.setToolTipText("Pagos");
        pagos.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        pagos.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        pagos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                pagosActionPerformed(evt);
            }
        });
        venta.add(pagos, new org.netbeans.lib.awtextra.AbsoluteConstraints(10, 600, 40, 50));

        utilidades.setBackground(new java.awt.Color(135, 193, 193));
        utilidades.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        utilidades.setForeground(new java.awt.Color(204, 0, 0));
        utilidades.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/casilla-de-verificacion (1).png"))); // NOI18N
        utilidades.setToolTipText("Utilidades");
        utilidades.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        utilidades.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        utilidades.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                utilidadesActionPerformed(evt);
            }
        });
        venta.add(utilidades, new org.netbeans.lib.awtextra.AbsoluteConstraints(840, 340, 40, 50));

        getContentPane().add(venta, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1220, 670));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        Controladorventa.botones_salir();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void tablaventaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tablaventaMouseClicked
       Controladorventa.tablaventa();
    }//GEN-LAST:event_tablaventaMouseClicked

    private void existenciadeproductosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_existenciadeproductosMouseClicked
        Controladorexistencias.mandaraventa();
    }//GEN-LAST:event_existenciadeproductosMouseClicked

    private void busquedaKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_busquedaKeyReleased
        Controladorexistencias.mostrartodoslosproductosenexistencias(busqueda.getText());
    }//GEN-LAST:event_busquedaKeyReleased

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

    private void ExistenciasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ExistenciasActionPerformed
        if(Controladorventa.noduplicarexistencias==false){new Archivos().setVisible(true);}
    }//GEN-LAST:event_ExistenciasActionPerformed

    private void Registro_userActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Registro_userActionPerformed
        if(Controladorventa.noduplicar_registro_usuario==false){new Registro_usuario().setVisible(true);} 
    }//GEN-LAST:event_Registro_userActionPerformed

    private void cleanallActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cleanallActionPerformed
        Controladorventa.limpiarventa();
    }//GEN-LAST:event_cleanallActionPerformed

    private void deletedescuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_deletedescuentoActionPerformed
        Controladorventa.deletedescuento();
    }//GEN-LAST:event_deletedescuentoActionPerformed

    private void descuentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_descuentoActionPerformed
        Controladorventa.descuentos();
    }//GEN-LAST:event_descuentoActionPerformed

    private void Registro_productosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Registro_productosActionPerformed
            if(Controladorventa.noduplicar_nuevo_producto==false){new Registro_producto().setVisible(true);} 
    }//GEN-LAST:event_Registro_productosActionPerformed

    private void ventas_acreditoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ventas_acreditoActionPerformed
       if(Controladorventa.noduplicar_vista_de_ventas_a_credito==false){new Ventas_a_credito().setVisible(true);}
    }//GEN-LAST:event_ventas_acreditoActionPerformed

    private void montoKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_montoKeyReleased
        char tecla = evt.getKeyChar();
        if(tecla==KeyEvent.VK_ENTER){
            Controladorventa.montoKeyRealeased();
            if(Controladorventa.sepuedeagregarpaciente==true){//SOLO SE ABRE LA VENTANA DE AGREGAR PACIENTES SI NO HUBO ALGUNA INCONSISTENCIA
                this.setVisible(false);
            }else{//CUANDO HUBO ALGUNA INCONSISTENCIA SE REINICIA LA VARIBALE PARA LAS SIGUIENTES EJECUCIONES
                Controladorventa.sepuedeagregarpaciente=true;
            }

        }
    }//GEN-LAST:event_montoKeyReleased

    private void montoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_montoFocusLost
        Controladorventa.montoFocusLost();
    }//GEN-LAST:event_montoFocusLost

    private void montoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_montoFocusGained
        Controladorventa.montoFocusGained();
    }//GEN-LAST:event_montoFocusGained

    private void Registro_paqueteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_Registro_paqueteActionPerformed
       if(Controladorventa.noduplicar_nuevo_paquete==false){new Registro_paquete().setVisible(true);} 
    }//GEN-LAST:event_Registro_paqueteActionPerformed

    private void pagosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_pagosActionPerformed
         if(Controladorventa.noduplicar_inventario_pagos==false){new inventariopagos().setVisible(true);;}  
    }//GEN-LAST:event_pagosActionPerformed

    private void utilidadesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_utilidadesActionPerformed
          if(Controladorventa.noduplicat_utilidades==false){new utilidades().setVisible(true);;}  
    }//GEN-LAST:event_utilidadesActionPerformed
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
    private javax.swing.JButton Existencias;
    private javax.swing.JLabel Fecha;
    public static javax.swing.JButton Registro_paquete;
    public static javax.swing.JButton Registro_productos;
    public static javax.swing.JButton Registro_user;
    public static javax.swing.JLabel Reloj;
    private javax.swing.JTextField busqueda;
    public static javax.swing.JLabel cambiocombobox;
    public static javax.swing.JButton cleanall;
    public static javax.swing.JButton deletedescuento;
    public static javax.swing.JButton descuento;
    public static javax.swing.JLabel descuentocombo;
    public static javax.swing.JLabel descuentolabel;
    private javax.swing.JLabel descuentolabel1;
    public static javax.swing.JTable existenciadeproductos;
    private javax.swing.JButton inventarioventas;
    private javax.swing.JButton jButton1;
    private javax.swing.JLabel jLabel30;
    private javax.swing.JLabel jLabel31;
    private javax.swing.JLabel jLabel75;
    private javax.swing.JLabel jLabel80;
    private javax.swing.JPanel jPanel10;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JLabel labeldescuento;
    public static javax.swing.JTextField monto;
    public static javax.swing.JButton pagos;
    private javax.swing.JLabel prueba;
    public static javax.swing.JCheckBox reimprimirventa;
    public static javax.swing.JLabel subtotal;
    public static javax.swing.JTable tablaventa;
    public static javax.swing.JLabel total;
    private javax.swing.JLabel user;
    public static javax.swing.JButton utilidades;
    public static javax.swing.JPanel venta;
    public static javax.swing.JCheckBox ventacredito;
    private javax.swing.JButton ventas_acredito;
    // End of variables declaration//GEN-END:variables
}