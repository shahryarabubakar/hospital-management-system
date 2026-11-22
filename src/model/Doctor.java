package model;

/**
 * Concrete subclass representing a Doctor. Demonstrates Hierarchical Inheritance.
 */
public class Doctor extends User {
    private String doctorId;
    private String specialization;
    private double consultationFee;
    private int yearsOfExperience;

    public Doctor(String userId, String fullName, String email, String phoneNumber,
                  String doctorId, String specialization, double consultationFee, int yearsOfExperience) {
        super(userId, fullName, email, phoneNumber);
        this.doctorId = doctorId;
        this.specialization = specialization;
        this.consultationFee = consultationFee;
        this.yearsOfExperience = yearsOfExperience;
    }

    @Override
    public String getRole() {
        return "DOCTOR_ROLE";
    }

    // Arithmetic calculation with consultation fee and tax/service charge
    public double calculateTotalConsultationBill(double taxRate) {
        return consultationFee + (consultationFee * taxRate);
    }

    public String getDoctorDetails() {
        return "Dr. " + super.getFullName() + " [" + specialization + "] - Fee: $" + consultationFee + " (" + yearsOfExperience + " yrs exp)";
    }

    // Getters and Setters
    public String getDoctorId() { return doctorId; }
    public void setDoctorId(String doctorId) { this.doctorId = doctorId; }

    public String getSpecialization() { return specialization; }
    public void setSpecialization(String specialization) { this.specialization = specialization; }

    public double getConsultationFee() { return consultationFee; }
    public void setConsultationFee(double consultationFee) { this.consultationFee = consultationFee; }

    public int getYearsOfExperience() { return yearsOfExperience; }
    public void setYearsOfExperience(int yearsOfExperience) { this.yearsOfExperience = yearsOfExperience; }
}
