package model;

/**
 * Concrete subclass representing a Patient. Demonstrates Hierarchical Inheritance and Composition.
 */
public class Patient extends User {
    private String patientId;
    private String bloodType;
    private int age;
    private MedicalProfile medicalProfile; // Composition: Patient strictly owns MedicalProfile

    // Constructor 1: Uses super() and initializes composed MedicalProfile
    public Patient(String userId, String fullName, String email, String phoneNumber,
                   String patientId, String bloodType, int age, String emergencyName, String emergencyPhone, String allergies) {
        super(userId, fullName, email, phoneNumber);
        this.patientId = patientId;
        this.bloodType = bloodType;
        this.age = age;
        this.medicalProfile = new MedicalProfile("MP-" + patientId, emergencyName, emergencyPhone, allergies);
    }

    // Constructor 2: Overloaded constructor
    public Patient(String userId, String fullName, String patientId, int age) {
        super(userId, fullName);
        this.patientId = patientId;
        this.bloodType = "O+";
        this.age = age;
        this.medicalProfile = new MedicalProfile("MP-" + patientId, "None", "N/A", "None");
    }

    @Override
    public String getRole() {
        return "PATIENT_ROLE";
    }

    // Uses super keyword in a method
    public String getPatientSummary() {
        return "Role: " + getRole() + " | ID: " + patientId + " | Name: " + super.getFullName() + " | Blood: " + bloodType;
    }

    public MedicalProfile getMedicalProfile() {
        return medicalProfile;
    }

    // Getters and Setters
    public String getPatientId() { return patientId; }
    public void setPatientId(String patientId) { this.patientId = patientId; }

    public String getBloodType() { return bloodType; }
    public void setBloodType(String bloodType) { this.bloodType = bloodType; }

    public int getAge() { return age; }
    public void setAge(int age) { this.age = age; }
}
