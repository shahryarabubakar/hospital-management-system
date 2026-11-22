package model;

/**
 * Component class owned via Composition by Patient.
 */
public class MedicalProfile {
    private String profileId;
    private String emergencyContactName;
    private String emergencyContactPhone;
    private String knownAllergies;

    public MedicalProfile(String profileId, String emergencyContactName, String emergencyContactPhone, String knownAllergies) {
        this.profileId = profileId;
        this.emergencyContactName = emergencyContactName;
        this.emergencyContactPhone = emergencyContactPhone;
        this.knownAllergies = knownAllergies;
    }

    public void updateAllergies(String newAllergies) {
        this.knownAllergies = newAllergies;
    }

    public String getMedicalSummary() {
        return "Profile [" + profileId + "] | Allergies: " + knownAllergies + " | Emergency: " + emergencyContactName + " (" + emergencyContactPhone + ")";
    }

    // Getters and Setters
    public String getProfileId() { return profileId; }
    public void setProfileId(String profileId) { this.profileId = profileId; }

    public String getEmergencyContactName() { return emergencyContactName; }
    public void setEmergencyContactName(String emergencyContactName) { this.emergencyContactName = emergencyContactName; }

    public String getEmergencyContactPhone() { return emergencyContactPhone; }
    public void setEmergencyContactPhone(String emergencyContactPhone) { this.emergencyContactPhone = emergencyContactPhone; }

    public String getKnownAllergies() { return knownAllergies; }
    public void setKnownAllergies(String knownAllergies) { this.knownAllergies = knownAllergies; }
}
