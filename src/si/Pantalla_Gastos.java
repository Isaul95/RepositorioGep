package si;

import java.awt.Color;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import si.Gastos;
import static si.menu_principal.venta; // DANDO ACCESOO ALA INTERFAZ PRINCIPAL

public class Pantalla_Gastos extends javax.swing.JFrame {        
   
    Calendar fecha_actual = new GregorianCalendar();
    String fechahoy=""; 
    Gastos gastos;
    String id_usuario; 
    String usuarioname=SI_Inicio.text_user.getText(); //variable para obtener el nombre del usuario o administrador que ingreso al sistema
    public Pantalla_Gastos() {
        initComponents();
        this.setLocationRelativeTo(null); // CENTRAR FORMULARIO
        jDateChooserFecha.setCalendar(fecha_actual);       
    }
        
     public boolean validarFormulario(String gastos) {
        boolean next = false;
        Pattern patGastos = Pattern.compile("^[0-9]+([.])?([0-9]+)?$");
        Matcher matGastos = patGastos.matcher(gastos);

        if (matGastos.matches()) {
            next = true;

        } else {
            JOptionPane.showMessageDialog(null, "Solo Numeros");
            txtmonto.setBackground(Color.red);
        }
        return next;
    }
                               
             public boolean validarFormulariotexto(String gastos) {
        boolean next = false;
        Pattern patGastos = Pattern.compile("^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24}[\\s]*)+$");// ^([a-zA-ZÁÉÍÓÚ]{1}[a-zñáéíóú]{1,24}[\\s]*)+$
        Matcher matGastos = patGastos.matcher(gastos);

        if (matGastos.matches()) {
            next = true;

        } else {
            JOptionPane.showMessageDialog(null, "Solo letras");
            txtdescripcion.setBackground(Color.red);
        }
        return next;
    }                                       
             
    /*========      LISTAR  GASTOS    ===========*/
     public ArrayList<Gastos> listGastos() {
          ArrayList  listaGastos = new ArrayList();
          Gastos gastos; // obj de tipo persona 
          try {              
               Connection ca= cc.conexion();
              
             String sql = "SELECT * FROM egreso";            
            PreparedStatement pst = (PreparedStatement) ca.prepareStatement(sql);
            ResultSet resultado = pst.executeQuery();     // valo devuelto se almaena en resultado                                      
           
            while (resultado.next()) {
                
                gastos = new Gastos();
               gastos.setIdegreso(resultado.getInt(1));
                gastos.setTipo(resultado.getString(2));                
                gastos.setTotal(resultado.getString(3));
                gastos.setFecha(resultado.getString(4));
                gastos.setUsuario(resultado.getString(5));
                listaGastos.add(gastos);                
            }     
            pst.close();                                              
          } catch (Exception e) {
          }
          return listaGastos;
      }                                
     
      /* ********************** LLENDO DE LA TABLA DE GASTOS  ******************************** */    
     public DefaultTableModel LlenarTabla(JTable tablaD){ // recibe como parametro 
        DefaultTableModel modeloT = new DefaultTableModel();
        tablaD.setModel(modeloT);  // add modelo ala tabla 
        
        modeloT.addColumn("Idegreso");    // add al modelo las 5 columnas con los nombrs
        modeloT.addColumn("Descripcion");
        modeloT.addColumn("Fecha");
        modeloT.addColumn("Total");
        modeloT.addColumn("Turno");
        
        Object[] columna = new Object[5];  //crear un obj con el nombre de colunna
        
        int numResgistros = listGastos().size(); // crear una varibal de tipo int k almacena con el numero de regitrsos k se recupera de la db
        
        for (int i = 0; i < numResgistros; i++ ) { // de cero a uno antes del total de numero de resgitros
            columna[0] = listGastos().get(i).getIdegreso();
            columna[1] = listGastos().get(i).getTipo();
            columna[2] = listGastos().get(i).getFecha(); //  llenado de las columnas de la tbla
            columna[3] = listGastos().get(i).getTotal();
            columna[4] = listGastos().get(i).getUsuario();
              modeloT.addRow(columna); // add una fila alas colimnas
        }                
        return modeloT;
    }  
     
     
     public void limpiar(){     /*====  VACIAR CAMPOS */
            txtdescripcion.setText(null);
            txtmonto.setText(null);
           // vistaGastos.jDateChooserFecha.setDate(null);
         }        
     
     
     
     
     
      /*  ======   HACIENDO UNA CONSULTA DE LOS GASTOS A BUSCAR CON -- ((UNA)) -- FECHA DETERINADA =======A*/          
          public void LlenarTablaBusquedaFecha(JTable tablaD, String jDateXUnaFecha){ // recibe como parametro 
         Object[] columna = new Object[5];  //crear un obj con el nombre de colunna
            Connection ca= cc.conexion(); // CONEXION DB 
              DefaultTableModel modeloT = new DefaultTableModel();
                  tablaD.setModel(modeloT);  // add modelo ala tabla 
        
        modeloT.addColumn("Idegreso");    // add al modelo las 5 columnas con los nombrs TABLA
        modeloT.addColumn("Descripcion");        
        modeloT.addColumn("Total");
        modeloT.addColumn("Fecha");
       modeloT.addColumn("Turno");               
         /* SELECT `idegreso`, `tipo`, `total`, `fecha`, turno FROM `egreso` \n" + "  INNER JOIN empleado\n" + "WHERE egreso.`empleado_idempleado` = empleado.idempleado";     */    
        try {
         String sSQL = "SELECT `idegreso`,`tipo`,`fecha`,`total`,`usuario` FROM `egreso` WHERE fecha = '"+llenarfechadehoy()+"'";
         
  // String sSQL = "SELECT * FROM egreso\n" + "WHERE fecha = '"+llenarfechadehoy()+"'";
         
         //   "SELECT *FROM egreso\n" + "WHERE fecha = '2019-07-20'";            
        PreparedStatement ps = ca.prepareStatement(sSQL);       
        try (ResultSet rs = ps.executeQuery(sSQL)) {
            while (rs.next()) {
                columna[0] = rs.getString("idegreso");  /* === LA DB == */
                columna[1] = rs.getString("tipo");
                columna[2] = rs.getString("total");
                columna[3] = rs.getString("fecha");
                columna[4] = rs.getString("Turno");                
                modeloT.addRow(columna);
            }
        }
        ps.close();
    } catch (Exception e) {
        JOptionPane.showMessageDialog(null, e, "Advertencia", JOptionPane.PLAIN_MESSAGE);    
    }
}
     
     public String llenarfechadehoy(){         
       int año= jDateXUnaFecha.getCalendar().get(Calendar.YEAR);
       int mes= jDateXUnaFecha.getCalendar().get(Calendar.MONTH)+1;
       int dia= jDateXUnaFecha.getCalendar().get(Calendar.DAY_OF_MONTH);
       fechahoy= año+"-"+mes+"-"+dia;
        return fechahoy;
    } 
     
     
    

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableGastos = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        btnListar = new javax.swing.JButton();
        txtmonto = new javax.swing.JTextField();
        txtdescripcion = new javax.swing.JTextField();
        jDateChooserFecha = new com.toedter.calendar.JDateChooser();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTableGastosFechaActual = new javax.swing.JTable();
        jDateXUnaFecha = new com.toedter.calendar.JDateChooser();
        B_cancelar = new javax.swing.JButton();
        btnRegistrarGasto = new javax.swing.JButton();
        btnImprimirticket = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder(null, "GASTOS DE FARMACIA GI", javax.swing.border.TitledBorder.CENTER, javax.swing.border.TitledBorder.DEFAULT_POSITION, new java.awt.Font("Serif", 1, 36))); // NOI18N
        jPanel1.setLayout(null);

        jPanel2.setBackground(new java.awt.Color(0, 51, 102));
        jPanel2.setLayout(null);

        jTableGastos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Idegreso", "Descripcion", "Total", "Fecha", "Turno"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTableGastos.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setViewportView(jTableGastos);

        jPanel2.add(jScrollPane1);
        jScrollPane1.setBounds(10, 240, 650, 230);

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Fecha:");
        jPanel2.add(jLabel1);
        jLabel1.setBounds(10, 150, 90, 29);

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Descripción del Gasto:");
        jPanel2.add(jLabel2);
        jLabel2.setBounds(10, 10, 220, 29);

        btnListar.setBackground(new java.awt.Color(0, 148, 204));
        btnListar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnListar.setForeground(new java.awt.Color(255, 255, 255));
        btnListar.setText("Listar Gastos");
        btnListar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnListarActionPerformed(evt);
            }
        });
        jPanel2.add(btnListar);
        btnListar.setBounds(430, 110, 210, 50);

        txtmonto.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        jPanel2.add(txtmonto);
        txtmonto.setBounds(10, 110, 210, 40);
        jPanel2.add(txtdescripcion);
        txtdescripcion.setBounds(10, 40, 480, 40);

        jDateChooserFecha.setDateFormatString("yyy/MM/dd");
        jDateChooserFecha.setEnabled(false);
        jDateChooserFecha.setFont(new java.awt.Font("Tahoma", 1, 18)); // NOI18N
        jPanel2.add(jDateChooserFecha);
        jDateChooserFecha.setBounds(10, 180, 210, 40);

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Monto del Gasto:");
        jPanel2.add(jLabel3);
        jLabel3.setBounds(10, 80, 160, 29);

        jLabel4.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Busqueda de Gastos");
        jPanel2.add(jLabel4);
        jLabel4.setBounds(870, 10, 250, 40);

        jLabel6.setFont(new java.awt.Font("Tahoma", 1, 17)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Fecha:");
        jPanel2.add(jLabel6);
        jLabel6.setBounds(790, 90, 80, 40);

        jTableGastosFechaActual.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Idegreso", "Descripcion", "Total", "Fecha", "Turno"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTableGastosFechaActual);

        jPanel2.add(jScrollPane2);
        jScrollPane2.setBounds(680, 150, 600, 170);

        jDateXUnaFecha.setDateFormatString("yyyy/MM/d");
        jDateXUnaFecha.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jDateXUnaFecha.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                jDateXUnaFechaPropertyChange(evt);
            }
        });
        jPanel2.add(jDateXUnaFecha);
        jDateXUnaFecha.setBounds(890, 90, 230, 40);

        B_cancelar.setBackground(new java.awt.Color(242, 38, 19));
        B_cancelar.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        B_cancelar.setText("Regresar");
        B_cancelar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                B_cancelarActionPerformed(evt);
            }
        });
        jPanel2.add(B_cancelar);
        B_cancelar.setBounds(1080, 420, 200, 40);

        btnRegistrarGasto.setBackground(new java.awt.Color(0, 148, 204));
        btnRegistrarGasto.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnRegistrarGasto.setForeground(new java.awt.Color(255, 255, 255));
        btnRegistrarGasto.setText("Registrar Gastos");
        btnRegistrarGasto.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRegistrarGastoActionPerformed(evt);
            }
        });
        jPanel2.add(btnRegistrarGasto);
        btnRegistrarGasto.setBounds(430, 170, 210, 50);

        btnImprimirticket.setBackground(new java.awt.Color(0, 148, 204));
        btnImprimirticket.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        btnImprimirticket.setForeground(new java.awt.Color(255, 255, 255));
        btnImprimirticket.setText("Imprimir Tikect");
        btnImprimirticket.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnImprimirticketActionPerformed(evt);
            }
        });
        jPanel2.add(btnImprimirticket);
        btnImprimirticket.setBounds(910, 340, 200, 50);

        jPanel1.add(jPanel2);
        jPanel2.setBounds(10, 50, 1290, 480);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1327, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 541, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void B_cancelarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_B_cancelarActionPerformed
        // BOTON DE CANCELAR LA INSERCION DE NUEVO USUARIO
        int dialogButton = JOptionPane.YES_NO_OPTION;
        int result = JOptionPane.showConfirmDialog(null, "¿Regresar a pagina anterior?","                    Aviso",dialogButton);
        if(result == 0){
            dispose();   }
    }//GEN-LAST:event_B_cancelarActionPerformed

    private void btnListarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnListarActionPerformed
        // Listar Gastos
        LlenarTabla(jTableGastos);
        
    }//GEN-LAST:event_btnListarActionPerformed

    private void btnRegistrarGastoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRegistrarGastoActionPerformed
        // ABRE NUEVA VENTANA PARA Registro de Gastos
          if (txtdescripcion.getText().isEmpty() || txtmonto.getText().isEmpty()) {
                    JOptionPane.showMessageDialog(null, "No dejar campos en blanco");

                } else {
                    boolean pass = validarFormulario(txtmonto.getText());
                    boolean pass2 = validarFormulariotexto(txtdescripcion.getText());

                    if (pass && pass2) {
                         String tipo = txtdescripcion.getText();
                         String total = txtmonto.getText();
                         SimpleDateFormat formatoFecha = new SimpleDateFormat("yyy-MM-dd");  // formato de la fecha e instanciando y darle formato de la fecha 
                         String fecha = formatoFecha.format(jDateChooserFecha.getDate());  
   
                         gastos = new Gastos(tipo, total, usuarioname, fecha);

                        if (gastos.Gastosinsert()) {
                            JOptionPane.showMessageDialog(null,"id_usuario" + usuarioname);
                            JOptionPane.showMessageDialog(null, "Gastos Registrados con Exito");
                            limpiar();
                            //JOptionPane.showMessageDialog(null, "Generando Ticket de Gastos");
                            LlenarTabla(jTableGastos); // LLENANDO LA TABLA AL INSERTAR CORRECTAMEBTE
                            //tikectGastos = new TikectGasto();
                            //tikectGastos.TikectGasto(tipo, total);

                        } else {
                            JOptionPane.showMessageDialog(null, "error", "ERROR", JOptionPane.ERROR_MESSAGE);
                        }
                    }
                }
            
        
    }//GEN-LAST:event_btnRegistrarGastoActionPerformed

    private void btnImprimirticketActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnImprimirticketActionPerformed
        // ABRE NUEVA VENTANA PARA Registro de Gastos
        
    }//GEN-LAST:event_btnImprimirticketActionPerformed

    private void jDateXUnaFechaPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_jDateXUnaFechaPropertyChange
        //  ME HACE LA BUSKEDA AL PONER UNA FECJA EN EL CHOOSERDATE
        String fechahoy= jDateXUnaFecha.toString();
          
            LlenarTablaBusquedaFecha(jTableGastosFechaActual, llenarfechadehoy());
    }//GEN-LAST:event_jDateXUnaFechaPropertyChange

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
            java.util.logging.Logger.getLogger(Pantalla_Gastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Pantalla_Gastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Pantalla_Gastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Pantalla_Gastos.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Pantalla_Gastos().setVisible(true);
            }
        });
    }
    
    
    
    
    SI cc= new SI();
 Connection ca= cc.conexion();

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton B_cancelar;
    private javax.swing.JButton btnImprimirticket;
    private javax.swing.JButton btnListar;
    public javax.swing.JButton btnRegistrarGasto;
    public com.toedter.calendar.JDateChooser jDateChooserFecha;
    public com.toedter.calendar.JDateChooser jDateXUnaFecha;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public javax.swing.JTable jTableGastos;
    public javax.swing.JTable jTableGastosFechaActual;
    public javax.swing.JTextField txtdescripcion;
    public javax.swing.JTextField txtmonto;
    // End of variables declaration//GEN-END:variables
}
