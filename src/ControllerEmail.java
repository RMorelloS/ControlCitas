import java.util.*;  
import javax.mail.*;  
import javax.mail.internet.*;  
import javax.activation.*;  
  
public class ControllerEmail  

{   
	static Session getMailSession;
	static MimeMessage generateMailMessage;
  
	public static void sendMail(String to, String nombreNutrologa, String horasCita, String fechaCita, String descripcion) {
		try {
			Properties mailProperties = new Properties();
		    mailProperties.put("mail.smtp.host", "smtp.gmail.com");  
		    mailProperties.put("mail.smtp.auth", "true");  
		    mailProperties.put("mail.smtp.port", "465");  
		    mailProperties.put("mail.smtp.starttls.enable", "true");  
		    mailProperties.put("mail.smtp.socketFactory.port", "465");  
		    mailProperties.put("mail.smtp.socketFactory.fallback", "false");  
		    mailProperties.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
			 
			getMailSession = Session.getDefaultInstance(mailProperties, null);
			generateMailMessage = new MimeMessage(getMailSession);
			generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(to));
			generateMailMessage.setSubject("Cita Clínica de Nutrólogos de la Ibero");
			String emailBody = "Hola, "+ ControllerBaseDatos.buscaPacientePorNombre(to) + "! Usted tiene una cita marcada con la Doctora " + nombreNutrologa + " a las " + horasCita 
									+ " horas, para el dia " + fechaCita  + ". <br>Motivo: " + descripcion + " 	<br><br> Saludos, <br>Clínica de nutrólogos de la Ibero.";
			generateMailMessage.setContent(emailBody, "text/html");
		 
			Transport transport = getMailSession.getTransport("smtp");
	 
			transport.connect("smtp.gmail.com", "ana.nutrologa.ibero@gmail.com", "ibero123");
			transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
			transport.close();
		}catch(Exception e) {
			e.printStackTrace();
		}
	}
}  