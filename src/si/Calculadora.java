package si;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.swing.JOptionPane;
public class Calculadora extends javax.swing.JFrame {
static String pieza="", descripcion="", uso="";
static float cantidadacobrar=0;    
public Calculadora(String pieza, String descripcion){
    this.pieza=pieza;
    this.descripcion=descripcion;
}
    public Calculadora() {
        initComponents();
         piezalabel.setText(this.pieza);
         descripcionlabel.setText(this.descripcion);
         this.setLocationRelativeTo(null); // CENTRAR FORMULARIO        
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        n9 = new javax.swing.JButton();
        n8 = new javax.swing.JButton();
        n7 = new javax.swing.JButton();
        n6 = new javax.swing.JButton();
        n5 = new javax.swing.JButton();
        n4 = new javax.swing.JButton();
        n1 = new javax.swing.JButton();
        n2 = new javax.swing.JButton();
        n3 = new javax.swing.JButton();
        borrar = new javax.swing.JButton();
        listo = new javax.swing.JButton();
        regresa = new javax.swing.JButton();
        n0 = new javax.swing.JButton();
        descripcionlabel = new javax.swing.JLabel();
        usodelacalculadora = new javax.swing.JLabel();
        piezalabel = new javax.swing.JLabel();
        cantidad = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel1.setBackground(new java.awt.Color(0, 51, 102));

        n9.setBackground(new java.awt.Color(0, 51, 102));
        n9.setForeground(new java.awt.Color(255, 255, 255));
        n9.setText("9");
        n9.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n9ActionPerformed(evt);
            }
        });

        n8.setBackground(new java.awt.Color(0, 51, 102));
        n8.setForeground(new java.awt.Color(255, 255, 255));
        n8.setText("8");
        n8.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n8ActionPerformed(evt);
            }
        });

        n7.setBackground(new java.awt.Color(0, 51, 102));
        n7.setForeground(new java.awt.Color(255, 255, 255));
        n7.setText("7");
        n7.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n7ActionPerformed(evt);
            }
        });

        n6.setBackground(new java.awt.Color(0, 51, 102));
        n6.setForeground(new java.awt.Color(255, 255, 255));
        n6.setText("6");
        n6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n6ActionPerformed(evt);
            }
        });

        n5.setBackground(new java.awt.Color(0, 51, 102));
        n5.setForeground(new java.awt.Color(255, 255, 255));
        n5.setText("5");
        n5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n5ActionPerformed(evt);
            }
        });

        n4.setBackground(new java.awt.Color(0, 51, 102));
        n4.setForeground(new java.awt.Color(255, 255, 255));
        n4.setText("4");
        n4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n4ActionPerformed(evt);
            }
        });

        n1.setBackground(new java.awt.Color(0, 51, 102));
        n1.setForeground(new java.awt.Color(255, 255, 255));
        n1.setText("1");
        n1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n1ActionPerformed(evt);
            }
        });

        n2.setBackground(new java.awt.Color(0, 51, 102));
        n2.setForeground(new java.awt.Color(255, 255, 255));
        n2.setText("2");
        n2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n2ActionPerformed(evt);
            }
        });

        n3.setBackground(new java.awt.Color(0, 51, 102));
        n3.setForeground(new java.awt.Color(255, 255, 255));
        n3.setText("3");
        n3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n3ActionPerformed(evt);
            }
        });

        borrar.setBackground(new java.awt.Color(0, 51, 102));
        borrar.setForeground(new java.awt.Color(255, 255, 255));
        borrar.setText("BORRAR");
        borrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                borrarActionPerformed(evt);
            }
        });

        listo.setBackground(new java.awt.Color(0, 51, 102));
        listo.setForeground(new java.awt.Color(255, 255, 255));
        listo.setText("LISTO");
        listo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                listoActionPerformed(evt);
            }
        });

        regresa.setBackground(new java.awt.Color(255, 255, 255));
        regresa.setForeground(new java.awt.Color(255, 0, 0));
        regresa.setIcon(new javax.swing.ImageIcon(getClass().getResource("/si/IconosJava/salir-flecha-derecha (1).png"))); // NOI18N
        regresa.setText("Regresar");
        regresa.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                regresaActionPerformed(evt);
            }
        });

        n0.setBackground(new java.awt.Color(0, 51, 102));
        n0.setForeground(new java.awt.Color(255, 255, 255));
        n0.setText("0");
        n0.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                n0ActionPerformed(evt);
            }
        });

        descripcionlabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        descripcionlabel.setForeground(new java.awt.Color(255, 255, 255));

        usodelacalculadora.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        usodelacalculadora.setForeground(new java.awt.Color(255, 255, 255));

        piezalabel.setFont(new java.awt.Font("Dialog", 1, 18)); // NOI18N
        piezalabel.setForeground(new java.awt.Color(255, 255, 255));

        cantidad.setBackground(new java.awt.Color(0, 0, 0));
        cantidad.setFont(new java.awt.Font("Tahoma", 3, 18)); // NOI18N
        cantidad.setForeground(new java.awt.Color(255, 255, 255));
        cantidad.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        cantidad.setBorder(null);
        cantidad.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(descripcionlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 343, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(usodelacalculadora, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(borrar, javax.swing.GroupLayout.PREFERRED_SIZE, 211, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(regresa, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(n9, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(n8, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(n7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(n6, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(n5, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(n4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(n1, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(n2, javax.swing.GroupLayout.PREFERRED_SIZE, 110, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(n3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(listo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(n0, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(piezalabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(cantidad))
                .addContainerGap(19, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(piezalabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(descripcionlabel, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(usodelacalculadora, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(cantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(n9, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(n8, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(n7, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(n6, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(n5, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(n4, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(n1, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(n2, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(n3, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(listo, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(n0, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(regresa, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(borrar, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
  public boolean validarFormulario(String cantidaddelatabla) { // VALIDACION DE TXT MONTO
        boolean next = false;
        Pattern patGastos = Pattern.compile("^[0-9]+([.])?([0-9]+)?$");
        Matcher matGastos = patGastos.matcher(cantidaddelatabla);
        if (matGastos.matches()&&!cantidaddelatabla.equals("")&&!cantidaddelatabla.equals("0")) {
            next = true;               
        } else {
            JOptionPane.showMessageDialog(null, "No puedes escribir letras, dejar vacio el campo ni meter un 0", "Advertencia", JOptionPane.INFORMATION_MESSAGE);    
        cantidad.setText("");
        }
        return next;
    }
    private void borrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_borrarActionPerformed
      cantidad.setText("");     
    }//GEN-LAST:event_borrarActionPerformed

    private void n3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n3ActionPerformed
   String th="3";
        if(!cantidad.equals("")){
            cantidad.setText(cantidad.getText()+th);
        }
        else {
            cantidad.setText(th);
            cantidad=cantidad;
        }
    }//GEN-LAST:event_n3ActionPerformed

    private void n2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n2ActionPerformed
String two="2";
        if(!cantidad.equals("")){
            cantidad.setText(cantidad.getText()+two);
        }
        else {
            cantidad.setText(two);
            cantidad=cantidad;
        }        // TODO add your handling code here:
    }//GEN-LAST:event_n2ActionPerformed

    private void n1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n1ActionPerformed
   String one="1";
        if(!cantidad.equals("")){
            cantidad.setText(cantidad.getText()+one);
        }
        else {
            cantidad.setText(one);
            cantidad=cantidad;
        }
    }//GEN-LAST:event_n1ActionPerformed

    private void n4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n4ActionPerformed
      String fo="4";
        if(!cantidad.equals("")){
            cantidad.setText(cantidad.getText()+fo);
        }
        else {
            cantidad.setText(fo);
            cantidad=cantidad;
        }
    }//GEN-LAST:event_n4ActionPerformed

    private void n5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n5ActionPerformed
       String five="5";
        if(!cantidad.equals("")){
            cantidad.setText(cantidad.getText()+five);
        }
        else {
            cantidad.setText(five);
            cantidad=cantidad;
        }
    }//GEN-LAST:event_n5ActionPerformed

    private void n6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n6ActionPerformed
       String six="6";
        if(!cantidad.equals("")){
            cantidad.setText(cantidad.getText()+six);
        }
        else {
            cantidad.setText(six);
            cantidad=cantidad;
        }
    }//GEN-LAST:event_n6ActionPerformed

    private void n7ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n7ActionPerformed
          String sevem="7";
        if(!cantidad.equals("")){

            cantidad.setText(cantidad.getText()+sevem);
        }
        else {
            cantidad.setText(sevem);
            cantidad=cantidad;
        }
    }//GEN-LAST:event_n7ActionPerformed

    private void n8ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n8ActionPerformed
       String eight="8";
        if(!cantidad.equals("")){
            cantidad.setText(cantidad.getText()+eight);
        }
        else {
            cantidad.setText(eight);
            cantidad=cantidad;
        }
    }//GEN-LAST:event_n8ActionPerformed

    private void n9ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n9ActionPerformed
      String nine="9";
        if(!cantidad.equals("")){
            cantidad.setText(cantidad.getText()+nine);
        }
        else {
            cantidad.setText(nine);
            cantidad=cantidad;
        }
    }//GEN-LAST:event_n9ActionPerformed

    private void regresaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_regresaActionPerformed
        dispose();   //}
    }//GEN-LAST:event_regresaActionPerformed

    private void listoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_listoActionPerformed
        boolean pass2 = validarFormulario(cantidad.getText());
                 if(pass2){//ESTO VALIDA QUE EL TEXTO ESCRITO NO TENGA INCOHERENCIAS   
                  //CUANDO SE USA PARA AGREGAR CANTIDADES O PIEZAS
            menu_principal enviar = new menu_principal(Float.parseFloat(cantidad.getText()), this.pieza);
                 this.setVisible(false);
                 }                     
    }//GEN-LAST:event_listoActionPerformed

    private void n0ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_n0ActionPerformed
      String one="0";
        if(!cantidad.equals("")){

            cantidad.setText(cantidad.getText()+one);
        }
        else {
            cantidad.setText(one);
            cantidad=cantidad;
        }
    }//GEN-LAST:event_n0ActionPerformed

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
            java.util.logging.Logger.getLogger(Calculadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Calculadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Calculadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Calculadora.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Calculadora().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton borrar;
    public static javax.swing.JTextField cantidad;
    public javax.swing.JLabel descripcionlabel;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JButton listo;
    private javax.swing.JButton n0;
    private javax.swing.JButton n1;
    private javax.swing.JButton n2;
    private javax.swing.JButton n3;
    private javax.swing.JButton n4;
    private javax.swing.JButton n5;
    private javax.swing.JButton n6;
    private javax.swing.JButton n7;
    private javax.swing.JButton n8;
    private javax.swing.JButton n9;
    public javax.swing.JLabel piezalabel;
    private javax.swing.JButton regresa;
    public javax.swing.JLabel usodelacalculadora;
    // End of variables declaration//GEN-END:variables
}
