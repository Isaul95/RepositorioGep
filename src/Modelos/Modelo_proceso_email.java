
package Modelos;

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
/* NOTAS DE FECHAS;
SELECT CURDATE();   ==>   2020-03-01  SELECT CURTIME();   ==>   14:16:34    */

public class Modelo_proceso_email implements Runnable{
   static String correo= "isaulhernandez@gepsof.com", contrasena= "Isaul.hernandez952", nom, dest, subject, msn, rout;
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
            //rout = "C:\\Users\\cachorra\\Desktop\\contrato.docx";  
        rout = "C:\\Users\\COMIMSA\\Documents\\reportes\\32_Rafael isaul hernandez ramirez.pdf";
// esta es la ruta donde la tengo la de prueb pero solo se cambia la ruta y ya         
        if (rout.equals("")) {

        final String usuario = "isaulhernandez@gepsof.com"; // rihr_952@hotmail.com
        final String pass = "Isaul.hernandez952";       //  corazonhtrm5609
           
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
                estado.setText("Enviando Mensaje desde gepsof");
JOptionPane.showConfirmDialog(null, "Esta es tu ruta desde el proceso" + ruta);      
System.out.println("Esta es tu ruta desde el proceso" + ruta.getText());      
                for (int i = 0; i < vect.length; i++) {
                    Message message = new MimeMessage(session);
                    message.setFrom(new InternetAddress(usuario));
                    message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(vect[i]));
                    message.setSubject(subject);
                   // message.setText(msn);
                    Transport.send(message);
                   // guardadoDB objeto = new guardadoDB(correo,vect[i],subject);
                }
                estado.setText("Mensaje enviado desde gepsof");
               // mensaje.setText("");
                asunto.setText("");
                destinatario.setText("");
            } catch (MessagingException e) {
                estado.setText("Error al enviar mensaje desde gepsof");
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
                estado.setText("Enviando Mensaje desde gepsof C/adjunto");
                for (int i = 0; i < vect.length; i++) {
                    //texto.setText(msn);
                    BodyPart adjunto = new MimeBodyPart();
                    adjunto.setDataHandler(new DataHandler(new FileDataSource(rout)));
JOptionPane.showConfirmDialog(null, "Esta es el rout desde el proceso =" + rout);      
System.out.println("Esta es el rout desde el proceso =" + rout);                    
                    adjunto.setFileName(nom);
JOptionPane.showConfirmDialog(null, "Esta es el nom desde el proceso =" + nom);      
System.out.println("Esta es el nom desde el proceso =" + nom);
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
                }
                estado.setText("Mensaje enviado desde gepsof C/adjunto");
               // mensaje.setText("");
                asunto.setText("");
                destinatario.setText("");              
                
            } catch (MessagingException ex) {
                estado.setText("Error al enviar mensaje desde gepsof");
                JOptionPane.showMessageDialog(null, "Algo salio mal compruebe la conexion a internet desde gepsof" + ex.getMessage());
            }
        }                        
    }            
    public void start() {
        new Thread(this).start();
    }    
}
