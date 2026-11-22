package model;

/**
 * Core transactional entity representing a patient's appointment booking.
 */
public class Appointment {
    private String appointmentId;
    private Patient patient;
    private Doctor doctor;
    private TimeSlot timeSlot;
    private String status; // PENDING, CONFIRMED, CANCELLED
    private double totalCharge;

    public Appointment(String appointmentId, Patient patient, Doctor doctor, TimeSlot timeSlot) {
        this.appointmentId = appointmentId;
        this.patient = patient;
        this.doctor = doctor;
        this.timeSlot = timeSlot;
        this.status = "PENDING";
        this.totalCharge = doctor.calculateTotalConsultationBill(0.10); // 10% hospital service fee
    }

    public boolean confirmBooking() {
        if (timeSlot != null && timeSlot.reserveSlot()) {
            this.status = "CONFIRMED";
            return true;
        }
        return false;
    }

    public boolean cancelAppointment(int hoursBeforeSlot) {
        // Relational and logical operator validation: at least 2 hours before slot
        if (this.status.equals("CONFIRMED") && hoursBeforeSlot >= 2) {
            this.status = "CANCELLED";
            if (timeSlot != null) {
                timeSlot.releaseSlot();
            }
            return true;
        }
        return false;
    }

    public String getSummary() {
        return "Appt ID: " + appointmentId + " | Patient: " + patient.getFullName() +
               " | Doctor: Dr. " + doctor.getFullName() + " | Time: " + timeSlot.getTimeRange() +
               " | Status: " + status + " | Total: $" + totalCharge;
    }

    // Getters and Setters
    public String getAppointmentId() { return appointmentId; }
    public void setAppointmentId(String appointmentId) { this.appointmentId = appointmentId; }

    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public TimeSlot getTimeSlot() { return timeSlot; }
    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
    public double getTotalCharge() { return totalCharge; }
}
