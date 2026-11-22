package model;

/**
 * Represents an individual bookable time slot for a doctor.
 */
public class TimeSlot {
    private String slotId;
    private String doctorId;
    private String timeRange;
    private boolean isReserved;

    public TimeSlot(String slotId, String doctorId, String timeRange, boolean isReserved) {
        this.slotId = slotId;
        this.doctorId = doctorId;
        this.timeRange = timeRange;
        this.isReserved = isReserved;
    }

    public boolean reserveSlot() {
        if (!isReserved) {
            this.isReserved = true;
            return true;
        }
        return false;
    }

    public void releaseSlot() {
        this.isReserved = false;
    }

    public boolean isAvailable() {
        return !isReserved;
    }

    // Getters and Setters
    public String getSlotId() { return slotId; }
    public void setSlotId(String slotId) { this.slotId = slotId; }

    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }

    public String getTimeRange() { return timeRange; }
    public void setTimeRange(String timeRange) { this.timeRange = timeRange; }

    public boolean isReserved() { return isReserved; }
    public void setReserved(boolean reserved) { isReserved = reserved; }
}
