package model;

/**
 * Abstract base class representing generalized authenticated users in the hospital system.
 */
public abstract class User {
    private String userId;
    private String fullName;
    private String email;
    private String phoneNumber;

    // Constructor 1: Full parameterization
    public User(String userId, String fullName, String email, String phoneNumber) {
        this.userId = userId;
        this.fullName = fullName;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Constructor 2: Overloaded constructor with basic details
    public User(String userId, String fullName) {
        this(userId, fullName, "not-provided@hospital.com", "000-0000");
    }

    // Concrete method shared across all subclasses
    public boolean login(String inputId) {
        return this.userId != null && this.userId.equals(inputId);
    }

    public void updateProfile(String email, String phoneNumber) {
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    // Abstract method enforced on all subclasses
    public abstract String getRole();

    // Getters and Setters
    public String getUserId() { return userId; }
    public void setUserId(String userId) { this.userId = userId; }

    public String getFullName() { return fullName; }
    public void setFullName(String fullName) { this.fullName = fullName; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
}
