package main;

import java.util.Scanner;
import model.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AppointmentManager manager = new AppointmentManager("MGR-01");

        // Initialize Arrays for Doctors, Patients, and TimeSlots
        Doctor[] doctors = new Doctor[2];
        doctors[0] = new Doctor("U101", "Sarah Jenkins", "s.jenkins@hospital.com", "555-0101", "D01", "Cardiology", 150.0, 12);
        doctors[1] = new Doctor("U102", "Marcus Vance", "m.vance@hospital.com", "555-0102", "D02", "Dermatology", 120.0, 8);

        Patient[] patients = new Patient[5];
        patients[0] = new Patient("U201", "Alice Cooper", "alice@email.com", "555-9001", "P01", "A+", 29, "Bob Cooper", "555-9002", "Penicillin");
        int patientCount = 1;

        TimeSlot[] timeSlots = new TimeSlot[4];
        timeSlots[0] = new TimeSlot("S01", "D01", "09:00 AM - 09:30 AM", false);
        timeSlots[1] = new TimeSlot("S02", "D01", "10:00 AM - 10:30 AM", false);
        timeSlots[2] = new TimeSlot("S03", "D02", "11:00 AM - 11:30 AM", false);
        timeSlots[3] = new TimeSlot("S04", "D02", "02:00 PM - 02:30 PM", false);

        Appointment[] appointments = new Appointment[10];
        int appointmentCount = 0;

        boolean running = true;

        System.out.println("====================================================");
        System.out.println("      HOSPITAL APPOINTMENT MANAGEMENT SYSTEM        ");
        System.out.println("====================================================");

        while (running) {
            System.out.println("\n--- MAIN MENU ---");
            System.out.println("1. Register New Patient (Use Case 1)");
            System.out.println("2. View Doctors & Available Time Slots (Use Case 2)");
            System.out.println("3. Book an Appointment (Use Case 3)");
            System.out.println("4. Cancel an Appointment (Use Case 4)");
            System.out.println("5. System Polymorphism & Metrics Summary");
            System.out.println("6. Exit");
            System.out.print("Enter your choice: ");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            switch (choice) {
                case 1:
                    // USE CASE: Register Patient
                    if (patientCount < patients.length) {
                        System.out.print("Enter Full Name: ");
                        String name = scanner.nextLine();
                        System.out.print("Enter Email: ");
                        String email = scanner.nextLine();
                        System.out.print("Enter Phone: ");
                        String phone = scanner.nextLine();
                        System.out.print("Enter Blood Type: ");
                        String blood = scanner.nextLine();
                        System.out.print("Enter Age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine();
                        System.out.print("Enter Emergency Contact Name: ");
                        String emName = scanner.nextLine();
                        System.out.print("Enter Emergency Contact Phone: ");
                        String emPhone = scanner.nextLine();
                        System.out.print("Enter Known Allergies: ");
                        String allergies = scanner.nextLine();

                        String newUserId = "U20" + (patientCount + 1);
                        String newPatId = "P0" + (patientCount + 1);
                        patients[patientCount] = new Patient(newUserId, name, email, phone, newPatId, blood, age, emName, emPhone, allergies);
                        System.out.println("Patient Registered Successfully! ID: " + newPatId);
                        System.out.println("Medical Profile Created: " + patients[patientCount].getMedicalProfile().getMedicalSummary());
                        patientCount++;
                    } else {
                        System.out.println("Patient registry is full.");
                    }
                    break;

                case 2:
                    // USE CASE: Search Doctor & Availability
                    System.out.println("\n--- DOCTORS & AVAILABLE SLOTS ---");
                    int openSlots = manager.countAvailableSlots(timeSlots);
                    System.out.println("Total Open Slots Across Hospital: " + openSlots);

                    for (int i = 0; i < doctors.length; i++) {
                        System.out.println("\n" + doctors[i].getDoctorDetails());
                        for (int j = 0; j < timeSlots.length; j++) {
                            if (timeSlots[j].getDoctorId().equals(doctors[i].getDoctorId())) {
                                String statusText = timeSlots[j].isAvailable() ? "AVAILABLE" : "BOOKED";
                                System.out.println("   -> Slot ID: " + timeSlots[j].getSlotId() + " | " + timeSlots[j].getTimeRange() + " [" + statusText + "]");
                            }
                        }
                    }
                    break;

                case 3:
                    // USE CASE: Book Appointment
                    System.out.print("Enter Patient ID (e.g., P01): ");
                    String patId = scanner.nextLine();
                    Patient selectedPatient = null;
                    for (int i = 0; i < patientCount; i++) {
                        if (patients[i].getPatientId().equalsIgnoreCase(patId)) {
                            selectedPatient = patients[i];
                            break;
                        }
                    }

                    if (selectedPatient == null) {
                        System.out.println("Error: Patient ID not found.");
                        break;
                    }

                    System.out.print("Enter Doctor ID (D01 or D02): ");
                    String docId = scanner.nextLine();
                    Doctor selectedDoctor = null;
                    for (int i = 0; i < doctors.length; i++) {
                        if (doctors[i].getDoctorId().equalsIgnoreCase(docId)) {
                            selectedDoctor = doctors[i];
                            break;
                        }
                    }

                    if (selectedDoctor == null) {
                        System.out.println("Error: Doctor ID not found.");
                        break;
                    }

                    System.out.print("Enter Slot ID (e.g., S01): ");
                    String slotId = scanner.nextLine();

                    String apptId = "APT-10" + (appointmentCount + 1);
                    Appointment bookedAppt = manager.processBooking(apptId, selectedPatient, selectedDoctor, timeSlots, slotId);

                    if (bookedAppt != null) {
                        appointments[appointmentCount] = bookedAppt;
                        appointmentCount++;
                        System.out.println("SUCCESS! " + bookedAppt.getSummary());
                    } else {
                        System.out.println("FAILED: Slot is invalid or already reserved.");
                    }
                    break;

                case 4:
                    // USE CASE: Cancel Appointment
                    if (appointmentCount == 0) {
                        System.out.println("No appointments found in system.");
                        break;
                    }
                    System.out.print("Enter Appointment ID to cancel (e.g., APT-101): ");
                    String cancelId = scanner.nextLine();
                    Appointment targetAppt = null;
                    for (int i = 0; i < appointmentCount; i++) {
                        if (appointments[i].getAppointmentId().equalsIgnoreCase(cancelId)) {
                            targetAppt = appointments[i];
                            break;
                        }
                    }

                    if (targetAppt == null) {
                        System.out.println("Appointment ID not found.");
                    } else {
                        System.out.print("How many hours remain before the appointment? ");
                        int hoursRemaining = scanner.nextInt();
                        scanner.nextLine();

                        if (manager.processCancellation(targetAppt, hoursRemaining)) {
                            System.out.println("Appointment successfully cancelled and slot released.");
                        } else {
                            System.out.println("Cancellation Denied: Must be at least 2 hours before slot or already cancelled.");
                        }
                    }
                    break;

                case 5:
                    // Runtime Polymorphism Demonstration with Parent Reference Array
                    System.out.println("\n--- POLYMORPHIC USER DIRECTORY ---");
                    User[] systemUsers = new User[3];
                    systemUsers[0] = doctors[0];
                    systemUsers[1] = doctors[1];
                    systemUsers[2] = patients[0];

                    for (int i = 0; i < systemUsers.length; i++) {
                        System.out.println("User: " + systemUsers[i].getFullName() + " | Runtime Role: " + systemUsers[i].getRole());
                    }

                    // Explicit Type Casting Example
                    int roundedRevenue = (int) manager.getTotalRevenueCollected();
                    System.out.println("\nTotal Confirmed Bookings: " + manager.getTotalBookingsProcessed());
                    System.out.println("Total Revenue (Exact): $" + manager.getTotalRevenueCollected());
                    System.out.println("Total Revenue (Type-casted to int): $" + roundedRevenue);
                    break;

                case 6:
                    running = false;
                    System.out.println("Exiting Hospital Appointment System. Goodbye!");
                    break;

                default:
                    System.out.println("Invalid option. Please enter a number between 1 and 6.");
            }
        }
        scanner.close();
    }
}
