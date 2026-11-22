package model;

import interfaces.Notifiable;

/**
 * Concrete notification handler for Email messages.
 */
public class EmailNotification implements Notifiable {
    private String notificationId;
    private String emailAddress;
    private String subject;
    private String messageBody;
    private boolean delivered;

    public EmailNotification(String notificationId, String emailAddress, String subject, String messageBody) {
        this.notificationId = notificationId;
        this.emailAddress = emailAddress;
        this.subject = subject;
        this.messageBody = messageBody;
        this.delivered = false;
    }

    @Override
    public boolean sendNotification() {
        if (emailAddress != null && emailAddress.contains("@")) {
            System.out.println("[EMAIL SMTP] Sending Email to " + emailAddress + " | Subject: " + subject + " | Body: \"" + messageBody + "\"");
            this.delivered = true;
            return true;
        }
        return false;
    }

    @Override
    public String getDeliveryStatus() {
        return delivered ? "EMAIL_SENT" : "EMAIL_QUEUED";
    }

    // Getters and Setters
    public String getNotificationId() { return notificationId; }
    public String getEmailAddress() { return emailAddress; }
    public String getSubject() { return subject; }
}
