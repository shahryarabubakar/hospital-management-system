package interfaces;

/**
 * Interface representing entities capable of dispatching notifications.
 */
public interface Notifiable {
    boolean sendNotification();
    String getDeliveryStatus();
}
