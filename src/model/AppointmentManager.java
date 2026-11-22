package model;

import interfaces.Notifiable;

/**
 * Control class orchestrating bookings, cancellations, array searches, and notifications.
 */
public class AppointmentManager {
    private String managerId;
    private int totalBookingsProcessed;
    private double totalRevenueCollected;

    public AppointmentManager(String managerId) {
        this.managerId = managerId;
        this.totalBookingsProcessed = 0;
        this.totalRevenueCollected = 0.0;
    }

    // Method Overloading 1: Book appointment using objects directly
    public Appointment processBooking(String apptId, Patient patient, Doctor doctor, TimeSlot slot) {
        if (patient != null && doctor != null && slot != null && slot.isAvailable()) {
            Appointment appt = new Appointment(apptId, patient, doctor, slot);
            if (appt.confirmBooking()) {
                totalBookingsProcessed++;
                totalRevenueCollected += appt.getTotalCharge();
                dispatchNotifications(patient, "Confirmed: " + apptId + " with Dr. " + doctor.getFullName() + " at " + slot.getTimeRange());
                return appt;
            }
        }
        return null;
    }

    // Method Overloading 2: Book appointment by searching slotId inside a TimeSlot array
    public Appointment processBooking(String apptId, Patient patient, Doctor doctor, TimeSlot[] slots, String targetSlotId) {
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null && slots[i].getSlotId().equalsIgnoreCase(targetSlotId)) {
                return processBooking(apptId, patient, doctor, slots[i]);
            }
        }
        return null;
    }

    // Passing an array to a method to count available slots
    public int countAvailableSlots(TimeSlot[] slots) {
        int availableCount = 0;
        for (int i = 0; i < slots.length; i++) {
            if (slots[i] != null && slots[i].isAvailable()) {
                availableCount++;
            }
        }
        return availableCount;
    }

    // Demonstrates Interface-based Runtime Polymorphism
    private void dispatchNotifications(Patient patient, String message) {
        Notifiable[] channels = new Notifiable[2];
        channels[0] = new SMSNotification("NOTIF-SMS-" + totalBookingsProcessed, patient.getPhoneNumber(), message);
        channels[1] = new EmailNotification("NOTIF-EML-" + totalBookingsProcessed, patient.getEmail(), "Hospital Appointment Confirmation", message);

        for (int i = 0; i < channels.length; i++) {
            channels[i].sendNotification();
        }
    }

    public boolean processCancellation(Appointment appt, int hoursBefore) {
        if (appt != null && appt.cancelAppointment(hoursBefore)) {
            totalRevenueCollected -= appt.getTotalCharge();
            dispatchNotifications(appt.getPatient(), "Cancelled: Appointment " + appt.getAppointmentId() + " has been cancelled.");
            return true;
        }
        return false;
    }

    // Getters
    public String getManagerId() { return managerId; }
    public int getTotalBookingsProcessed() { return totalBookingsProcessed; }
    public double getTotalRevenueCollected() { return totalRevenueCollected; }
}
