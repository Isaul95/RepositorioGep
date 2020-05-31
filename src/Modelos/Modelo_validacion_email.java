
package Modelos;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.swing.JLabel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
/*            
     USER: atencionacliente@laboratoriosalkhemy.com
     PASS: Atencion.2020
*/
public class Modelo_validacion_email implements Runnable{
    JTextField correo;
    JPasswordField contrasena;
    JLabel error;   
    String mail, passs;

     public Modelo_validacion_email(JTextField valor1, JPasswordField valor2, JLabel valor3) {
        this.correo = valor1;
        this.contrasena = valor2;
        this.error = valor3;     
        mail = correo.getText();
        passs = contrasena.getText();
    }
           
    @Override
    public void run() {
        Properties props = new Properties();

        final String usuario = "atencionacliente@laboratoriosalkhemy.com"; // isaulhernandez@gepsof.com
        final String pass = "Atencion.2020"; // Isaul.

        props.put("mail.transport.protocol", "smtp");
        props.put("mail.smtp.host", "sh-pro10.hostgator.mx");
        props.put("mail.smtp.socketFactory.port", "587");
        props.put("mail.smtp.socketFactory.fallback", "false");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "587");

        Session session = Session.getInstance(props,
                new javax.mail.Authenticator() {
                    protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                        return new javax.mail.PasswordAuthentication(usuario, pass);
                    }
                });
        session.setDebug(false);

        Transport transport = null;

        try {
            transport = session.getTransport("smtp");
        } catch (javax.mail.NoSuchProviderException ex) {
            Logger.getLogger(Modelo_validacion_email.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            transport.connect();
            error.setVisible(false);        
        } catch (MessagingException ex) {
            error.setVisible(true);            
        }
    }             
}
