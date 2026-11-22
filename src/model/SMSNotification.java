package model;

import interfaces.Notifiable;

/**
 * Concrete notification handler for SMS messages.
 */
public class SMSNotification implements Notifiable {
    private String notificationId;
    private String phoneNumber;
    private String messageBody;
    private boolean delivered;

    public SMSNotification(String notificationId, String phoneNumber, String messageBody) {
        this.notificationId = notificationId;
        this.phoneNumber = phoneNumber;
        this.messageBody = messageBody;
        this.delivered = false;
    }

    @Override
    public boolean sendNotification() {
        if (phoneNumber != null && !phoneNumber.isEmpty()) {
            System.out.println("[SMS GATEWAY] Sending SMS to " + phoneNumber + ": \"" + messageBody + "\"");
            this.delivered = true;
            return true;
        }
        return false;
    }

    @Override
    public String getDeliveryStatus() {
        return delivered ? "SMS_DELIVERED" : "SMS_PENDING";
    }

    // Getters and Setters
    public String getNotificationId() { return notificationId; }
    public String getPhoneNumber() { return phoneNumber; }
    public String getMessageBody() { return messageBody; }
}
