package eu.telecomnancy.amio.notification.notifiers.email;

import android.util.Log;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import eu.telecomnancy.amio.notification.Constants;
import eu.telecomnancy.amio.notification.annotations.EventNotifier;
import eu.telecomnancy.amio.notification.contexts.NotificationContext;
import eu.telecomnancy.amio.notification.flags.NotificationType;
import eu.telecomnancy.amio.notification.notifiers.INotifier;

/**
 * Define a INotifier able to propagate a notification by mail using an SMTP server
 *
 * @see INotifier
 * @see eu.telecomnancy.amio.notification.flags.NotificationType
 */
@EventNotifier(target = NotificationType.EMAIL)
public class SmtpNotifier extends EmailNotifier {

    /**
     * Android logging tag for this class
     */
    private static final String TAG = SmtpNotifier.class.getName();

    /**
     * Create a new notifier
     *
     * @param payload Notification payload to be later propagated
     */
    public SmtpNotifier(NotificationContext payload) {
        super(payload);
    }

    /**
     * Forge the email to be sent with SMTP
     *
     * @param session Opened SMTP connection to use
     * @return The forged message, along with its recipients, sender and content
     * @throws MessagingException If any error occurs while forging the mail
     */
    private Message forgeMessage(Session session) throws MessagingException {
        // Create a default MimeMessage object.
        Message message = new MimeMessage(session);

        message.setFrom(
                new InternetAddress(Constants.Mail.SENDER));

        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(
                        Constants.Mail.RECEIVER));

        // Set Subject: header field
        message.setSubject(
                Constants.Mail.SUBJECT);

        // Now set the actual message
        message.setContent(Constants.Mail.CONTENT, "text/html");

        return message;
    }

    /**
     * Generate all properties in order to establish the connection with the SMTP server
     *
     * @return A set of pre-filled properties to be used to establish the connection
     */
    private Properties generateSmtpProperties() {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", Constants.Mail.Smtp.Config.HOST);
        properties.put("mail.smtp.port", String.valueOf(Constants.Mail.Smtp.Config.PORT));

        return properties;
    }

    /**
     * Generate the SMTP session to further send emails from a set of properties
     *
     * @param properties SMTP properties used for the session configuration
     * @return An opened session with the SMTP server
     */
    private Session generateSmtpSession(Properties properties) {
        return Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                Constants.Mail.Smtp.Credentials.LOGIN,
                                Constants.Mail.Smtp.Credentials.PASSWORD);
                    }
                });
    }

    @Override
    public void sendNotification() {
        Log.d(TAG, "Sending notification");

        // SMTP connection
        Properties properties = generateSmtpProperties();
        Session session = generateSmtpSession(properties);
        Log.d(TAG, "Connection to the SMTP server successfully established");

        // Email sending
        try {
            Message message = forgeMessage(session);
            Log.d(TAG, "Email generated");

            Transport.send(message);
            Log.d(TAG, "Email sent");
        } catch (MessagingException e) {
            Log.e(TAG, "Unable to send the notification mail", e);
            throw new RuntimeException(e);
        }
    }

}
