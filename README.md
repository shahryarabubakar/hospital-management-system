# Hospital Appointment Management System
**Course:** Software Design & Analysis – Assignment 4  
**Student Name:** Shahriyar  
**Registration Number:** 23P-0617  
**IDE:** Eclipse IDE  
**Language:** Java (JDK 17+)

---

## 1. Project Overview
The **Hospital Appointment Management System** is a robust, modular, object-oriented console application developed in Java. It models the core operational and clinical workflows of a hospital, including patient registration, doctor schedule lookup, appointment booking, slot cancellation, and multi-channel notifications.

---

## 2. List of Classes & Architecture
The project is organized into three distinct packages inside the `src` folder:

### Package: `interfaces`
1. **`Notifiable` (Interface):** Defines the messaging contract with `sendNotification()` and `getDeliveryStatus()`.

### Package: `model`
2. **`User` (Abstract Class):** Common abstract superclass modeling generalized authenticated users (`userId`, `fullName`, `email`, `phoneNumber`), providing concrete authentication (`login()`, `updateProfile()`) and enforcing the abstract method `getRole()`.
3. **`MedicalProfile` (Component Class):** Encapsulates sensitive clinical history (`profileId`, `emergencyContactName`, `emergencyContactPhone`, `knownAllergies`). Composed strictly inside `Patient`.
4. **`Patient` (Concrete Subclass 1 of User):** Represents registered patients with demographics (`patientId`, `bloodType`, `age`) and compositionally owns a `MedicalProfile`.
5. **`Doctor` (Concrete Subclass 2 of User):** Represents hospital specialists (`doctorId`, `specialization`, `consultationFee`, `yearsOfExperience`) with bill calculation logic.
6. **`TimeSlot` (Domain Entity):** Models bookable consulting intervals (`slotId`, `doctorId`, `timeRange`, `isReserved`).
7. **`Appointment` (Transactional Entity):** Links patient, doctor, and slot, maintaining status transitions (`PENDING`, `CONFIRMED`, `CANCELLED`) and charge calculations.
8. **`SMSNotification` (Class implementing Notifiable):** Dispatches text messages via an SMS gateway simulator.
9. **`EmailNotification` (Class implementing Notifiable):** Dispatches emails via an SMTP client simulator.
10. **`AppointmentManager` (Control Class):** Business logic controller demonstrating method overloading, array processing, and polymorphic notification dispatch.

### Package: `main`
11. **`Main` (Console Demo Application):** Interactive text-based menu entry point providing access to all system use cases.

---

## 3. How to Run the Program

### Method A: Running via Eclipse IDE (Recommended)
1. Open **Eclipse IDE**.
2. Go to **File -> Open Projects from File System...**
3. Click **Directory...** and select the project root folder:
   ```
   C:\Users\shahr\Assignment4
   ```
4. Eclipse will automatically detect the `.project` and `.classpath` files with project name `HospitalAppointmentSystem`. Click **Finish**.
5. In the *Package Explorer*, expand `src` -> `main`.
6. Right-click on `Main.java` and select **Run As -> Java Application**.
7. Use the interactive menu in the Eclipse Console.

### Method B: Running via Command Line (PowerShell / Command Prompt)
1. Open PowerShell and navigate to the project directory:
   ```powershell
   cd C:\Users\shahr\Assignment4
   ```
2. Compile the Java source files into the `bin` directory:
   ```powershell
   javac -d bin -sourcepath src src/main/Main.java
   ```
3. Run the compiled application:
   ```powershell
   java -cp bin main.Main
   ```

---

## 4. Key OOP Principles Demonstrated
- **Hierarchical Inheritance:** `User` extended by `Patient` and `Doctor`.
- **Composition:** `Patient` exclusively contains and manages `MedicalProfile`.
- **Polymorphism:** 
  - Dynamic Method Overriding via `User.getRole()` and `Notifiable.sendNotification()`.
  - Static Method Overloading via `AppointmentManager.processBooking(...)`.
- **Encapsulation:** All fields private with comprehensive public accessors/mutators.
- **Array Processing:** Object arrays (`Doctor[]`, `TimeSlot[]`, `Patient[]`, `Appointment[]`) and array-based search routines.
