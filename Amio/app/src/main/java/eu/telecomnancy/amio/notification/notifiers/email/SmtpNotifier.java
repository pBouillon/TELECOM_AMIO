package eu.telecomnancy.amio.notification.notifiers.email;

import android.content.Context;
import android.content.res.Resources;
import android.util.Log;

import androidx.preference.PreferenceManager;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import eu.telecomnancy.amio.R;
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
     * @param session  Opened SMTP connection to use
     * @param sender   sender's email address
     * @param receiver reciever email address
     * @return The forged message, along with its recipients, sender and content
     * @throws MessagingException If any error occurs while forging the mail
     */
    private Message forgeMessage(Session session, String sender, String receiver) throws MessagingException {
        // Create a default MimeMessage object.
        Message message = new MimeMessage(session);

        message.setFrom(
                new InternetAddress(sender));

        message.setRecipients(
                Message.RecipientType.TO,
                InternetAddress.parse(
                        receiver));

        // Set Subject: header field
        message.setSubject(
                Constants.Mail.SUBJECT);

        // Now set the actual message
        message.setContent(Constants.Mail.CONTENT, "text/html");

        return message;
    }

    /**
     * Generate all properties in order to establish the connection with the SMTP server
     * @param host SMTP server's name
     * @param port SMTP port number
     * @param timeout in ms, the time allowed to get a response from the server
     * @return A set of pre-filled properties to be used to establish the connection
     */
    private Properties generateSmtpProperties(String host, String port, String timeout) {
        Properties properties = new Properties();

        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.host", host);
        properties.put("mail.smtp.port", port);
        properties.put("mail.smtp.timeout", timeout);

        return properties;
    }

    /**
     * Generate the SMTP session to further send emails from a set of properties
     *
     * @param properties SMTP properties used for the session configuration
     * @param login SMTP usename
     * @param password SMTP password
     * @return An opened session with the SMTP server
     */
    private Session generateSmtpSession(Properties properties, String login, String password) {
        return Session.getInstance(properties,
                new javax.mail.Authenticator() {
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication(
                                login,
                                password);
                    }
                });
    }

    @Override
    public void sendNotification() {
        Log.d(TAG, "Sending notification");

        Context androidContext = payload.eventContext.pollingContext.androidContext;
        Resources resource = androidContext.getResources();

        // Retrieve constants from the Preferences manager
        String login = getParameterStringFromRessourceId(
                androidContext, resource, R.string.smtp_username_key, "");

        String password = getParameterStringFromRessourceId(
                androidContext, resource, R.string.smtp_password_key, "");

        String host = getParameterStringFromRessourceId(
                androidContext, resource, R.string.smtp_host_key,
                resource.getString(R.string.default_smtp_host));

        String port = getParameterStringFromRessourceId(
                androidContext, resource, R.string.smtp_port_key,
                resource.getString(R.string.default_smtp_port));

        String timeout = getParameterStringFromRessourceId(
                androidContext, resource, R.string.smtp_timeout_key,
                resource.getString(R.string.default_smtp_timeout));

        // SMTP connection
        Properties properties = generateSmtpProperties(host, port, timeout);
        Session session = generateSmtpSession(properties, login, password);
        Log.d(TAG, "Connection to the SMTP server successfully established");

        // Email sending
        String sender = getParameterStringFromRessourceId(androidContext, resource, R.string.mail_from_address_key, resource.getString(R.string.default_mail_from_address));
        String receiver = getParameterStringFromRessourceId(androidContext, resource, R.string.mail_to_address_key, "");

        try {
            Message message = forgeMessage(session, sender, receiver);
            Log.d(TAG, "Email generated");

            Transport.send(message);
            Log.d(TAG, "Email sent");
        } catch (MessagingException e) {
            Log.e(TAG, "Unable to send the notification mail", e);
            throw new RuntimeException(e);
        }
    }

    private String getParameterStringFromRessourceId(Context androidContext, Resources ressources, int valueId, String defaultValue) {
        return PreferenceManager.getDefaultSharedPreferences(androidContext).getString(
                ressources.getString(valueId),
                defaultValue);
    }
}
