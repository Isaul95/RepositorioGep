
package Modelos;
import java.awt.Color;
import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static si.Capturar_resultados.envio_email;
import si.Envio_email;
/* NOTAS DE FECHAS;
SELECT CURDATE();   ==>   2020-03-01  SELECT CURTIME();   ==>   14:16:34    */
// isaulhernandez@gepsof.com       Isaul.
public class Modelo_proceso_email implements Runnable{
   static String correo= "atencionacliente@laboratoriosalkhemy.com", contrasena= "Atencion.2020", nom, dest, subject, msn, rout;
    JTextField destinatario, asunto, ruta;
    JTextArea mensaje;
    JLabel estado;
    String[] vect;
    
        public Modelo_proceso_email(JTextField valor3, JTextField valor4, /*JTextArea valor5,JTextField valor6, */String valor7,  JLabel valor8) {       
                   this.destinatario = valor3;  // db save
                   this.asunto = valor4;         // db save      
       this.nom = valor7; // NOMBRE DEL FILE K SE LE DA DE FORMA STATICA
                         this.estado = valor8;
                      dest = destinatario.getText();    // db save
                      subject = asunto.getText();        // db save       
                          vect = dest.split(";"); // separacion concatencion multiusuariio
    }
               
    @Override
    public void run() {      
        rout = "C:\\reportes\\"+nom;
        
        if (rout.equals("")) {
        final String usuario = "atencionacliente@laboratoriosalkhemy.com"; // @gepsof.com
        final String pass = "Atencion.2020";       //  Isaul
           
            Properties props = new Properties();
            props.put("mail.transport.protocol", "smtp");
            props.put("mail.smtp.host", "sh-pro10.hostgator.mx");
            props.put("mail.smtp.socketFactory.port", "587");
            props.put("mail.smtp.socketFactory.fallback", "false");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.port", "587");

            Session session = Session.getInstance(props,
                    new javax.mail.Authenticator() {
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(usuario, pass);
                        }
                    });
            session.setDebug(false);

            try {
                estado.setText("Enviando Mensaje");
                for (int i = 0; i < vect.length; i++) {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(usuario));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(vect[i]));
                    message.setSubject(subject);
                    Transport.send(message);
                }
                JOptionPane.showMessageDialog(null,"Mensaje enviado");
                estado.setText(""); // cuando se se aceptar de Mensaje Enviado se borra el label
                asunto.setText("");
                destinatario.setText("");
            } catch (MessagingException e) {               
                JOptionPane.showMessageDialog(null, "Algo salio mal compruebe la conexion a internet");
            }
        } else {

            Properties props = new Properties();
            props.setProperty("mail.smtp.host", "sh-pro10.hostgator.mx");
            props.setProperty("mail.smtp.starttls.enable", "true");
            props.setProperty("mail.smtp.port", "587");
            props.setProperty("mail.smtp.auth", "true");
            Session session = Session.getDefaultInstance(props);
            session.setDebug(false);
            BodyPart texto = new MimeBodyPart();

            try {
                estado.setText("Enviando Mensaje");
                for (int i = 0; i < vect.length; i++) {
                    //texto.setText(msn);
                    BodyPart adjunto = new MimeBodyPart();
                    adjunto.setDataHandler(new DataHandler(new FileDataSource(rout)));
                    adjunto.setFileName(nom);
                    MimeMultipart multiParte = new MimeMultipart();
                  //  multiParte.addBodyPart(texto);
                    multiParte.addBodyPart(adjunto);
                    MimeMessage message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(correo));
                    message.addRecipient(Message.RecipientType.TO, new InternetAddress(vect[i]));
                    message.setSubject(subject);
                    message.setContent(multiParte);
                    Modelo_guardadoDB_email objeto = new Modelo_guardadoDB_email(correo,vect[i],subject);
                    Transport t = null;
                    t = session.getTransport("smtp");
                    t.connect(correo, contrasena);
                    t.sendMessage(message, message.getAllRecipients());
                    t.close();
                } // estado.setText("Mensaje enviado desde gepsof C/adjunto");
                JOptionPane.showMessageDialog(null,"Mensaje enviado");
                Envio_email.para.setBackground(Color.white);
  for (int t = 0; t < vect.length; t++){
        Modelo_guardadoDB_email nb = new Modelo_guardadoDB_email(correo,vect[t],subject);
        nb.guardar();   //aki es donde una vez k pase el mensaje de enviado correctamente se captura enn la base de datos de lo contrario no se captura
   }                
   envio_email.setEnabled(false); // blokear el boton despues de enviar el email de la venta Envio_Email
                estado.setText(""); // cuando se se aceptar de Mensaje Enviado se borra el label
                asunto.setText("");
                destinatario.setText("");                              
            } catch (MessagingException ex) {                   
           JOptionPane.showMessageDialog(null, "Algo salio mal compruebe la conexion a internet \n y/o el Dirección de correo es INVALIDA");
            Envio_email.para.setBackground(Color.red);
           estado.setText("Favor de ingresar una dirección de correo VALIDA...!!!");
            }
        }                        
    }            
    public void start() {
        new Thread(this).start();
    }    
}
