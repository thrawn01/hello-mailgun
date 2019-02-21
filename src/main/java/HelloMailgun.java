import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.util.Properties;

public class HelloMailgun {

    public static void main(String[] args) throws Exception {
        SendSMTP();
    }

    public static void SendSMTP() throws Exception {
        String host = "smtp.mailgun.org";
        String user = "postmaster@<your-domain>";
        String pass = "<your-smtp-password>";

        Properties props = System.getProperties();
        props.put("mail.smtps.host","smtp.mailgun.org");
        props.put("mail.smtp.user", user);
        props.put("mail.smtp.password", pass);
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtps.auth","true");
        props.put("mail.smtp.port", "587");
        Session session = Session.getDefaultInstance(props, null);

        BodyPart bodyPart = new MimeBodyPart();
        bodyPart.setText("This is a test file attachment.");

        Multipart multipart = new MimeMultipart();
        multipart.addBodyPart(bodyPart);

        // Provide a file
        String filename = "/Users/thrawn/mail.go";

        BodyPart attachment = new MimeBodyPart();
        DataSource source = new FileDataSource(filename);
        attachment.setDataHandler(new DataHandler(source));
        attachment.setFileName(filename);
        multipart.addBodyPart(attachment);

        MimeMessage message = new MimeMessage(session);
        message.setFrom(new InternetAddress("you@<your-domain>"));
        message.addRecipient(Message.RecipientType.TO, new InternetAddress("recipient@gmail.com"));
        message.setSubject("Hello Mailgun, this is javax.mail");
        message.setContent(multipart);

        // Uncomment this to print the MIME encoded envelope with attachment
        //message.writeTo(System.out);

        Transport transport = session.getTransport("smtp");
        transport.connect(host, user, pass);
        transport.sendMessage(message, message.getAllRecipients());
        transport.close();
    }
}
