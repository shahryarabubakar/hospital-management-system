# Software Design & Analysis – Assignment 4 Report
**Project Name:** Hospital Appointment Management System (`HospitalAppointmentSystem`)  
**Student Name:** Shahriyar  
**Registration Number:** 23P-0617  
**Package Architecture:** `interfaces`, `model`, `main`

---

## 1. Inheritance Hierarchy
* **Type of Inheritance Used:** **Hierarchical Inheritance**
* **Structure:** The abstract base class `model.User` serves as the common parent class, extended independently by two specialized concrete child classes: `model.Patient` and `model.Doctor`.
* **Subclass Additions:**
  - `Patient` adds patient-specific fields (`patientId`, `bloodType`, `age`, and composed `medicalProfile`) and methods (`getPatientSummary()`, `getMedicalProfile()`).
  - `Doctor` adds clinical practice fields (`doctorId`, `specialization`, `consultationFee`, `yearsOfExperience`) and methods (`calculateTotalConsultationBill()`, `getDoctorDetails()`).
* **Use of `super` Keyword:**
  - **Constructors:** `Patient` calls `super(userId, fullName, email, phoneNumber)` and `super(userId, fullName)`; `Doctor` calls `super(userId, fullName, email, phoneNumber)`.
  - **Methods:** `Patient.getPatientSummary()` calls `super.getFullName()`; `Doctor.getDoctorDetails()` calls `super.getFullName()`.

---

## 2. Abstract Class vs. Interface Explanation
*(Required: 3–4 sentences explaining the architectural rationale using lecture concepts)*

> In our architecture, **`model.User` is designed as an abstract class** because `Patient` and `Doctor` share an intrinsic identity ("is-a" relationship) along with common state fields (`userId`, `fullName`, `email`, `phoneNumber`) and concrete base implementations like `login()` and `updateProfile()`. In contrast, **`interfaces.Notifiable` is designed as an interface** to establish a pure functional contract ("can-do" capability) for disparate notification dispatchers without imposing any inheritance hierarchy or shared mutable state. An abstract class is optimal when closely related subclasses share code and internal structure, whereas an interface provides loose coupling and multiple contract implementations across independent notification channels such as `SMSNotification` and `EmailNotification`.

---

## 3. Method Overloading vs. Overriding & Static vs. Dynamic Binding
*(Required: 5–6 sentences explaining differences with project code examples)*

> **Method overloading** represents compile-time (static) polymorphism where multiple methods within the same class share the identical name but differ in parameter signatures, such as in `AppointmentManager` where `processBooking(...)` is defined once taking direct object references `(String, Patient, Doctor, TimeSlot)` and overloaded to accept array search parameters `(String, Patient, Doctor, TimeSlot[], String)`. Here, **static binding** occurs because the compiler resolves which method to execute during compilation based solely on the argument types provided at the call site. Conversely, **method overriding** represents runtime (dynamic) polymorphism where a child class provides a specialized implementation of a method already declared in its parent or interface, demonstrated by `Patient` and `Doctor` overriding the abstract `getRole()` method from `User`, and `SMSNotification` and `EmailNotification` overriding `sendNotification()` from `Notifiable`. In this scenario, **dynamic binding** takes place at runtime when the JVM inspects the actual underlying object type referenced by a parent pointer (such as iterating through `User[] systemUsers` or `Notifiable[] channels`) to dispatch the subclass's overridden method rather than the parent's definition.

---

## 4. Classes, Objects, and Constructors Compliance Matrix

| # | Class Name | Package | Type | Instance Variables (>=3) | Constructors (Overloaded) | Key Operations / Methods |
|---|------------|---------|------|---------------------------|---------------------------|--------------------------|
| 1 | `Notifiable` | `interfaces` | Interface | N/A (Contract) | N/A | `sendNotification()`, `getDeliveryStatus()` |
| 2 | `User` | `model` | Abstract Class | `userId`, `fullName`, `email`, `phoneNumber` | **2 (Overloaded)** | `login()`, `updateProfile()`, `getRole()` (Abstract) |
| 3 | `MedicalProfile` | `model` | Component | `profileId`, `emergencyContactName`, `emergencyContactPhone`, `knownAllergies` | 1 | `updateAllergies()`, `getMedicalSummary()` |
| 4 | `Patient` | `model` | Subclass | `patientId`, `bloodType`, `age`, `medicalProfile` | **2 (Overloaded)** | `getRole()`, `getPatientSummary()`, `getMedicalProfile()` |
| 5 | `Doctor` | `model` | Subclass | `doctorId`, `specialization`, `consultationFee`, `yearsOfExperience` | 1 | `getRole()`, `calculateTotalConsultationBill()`, `getDoctorDetails()` |
| 6 | `TimeSlot` | `model` | Entity | `slotId`, `doctorId`, `timeRange`, `isReserved` | 1 | `reserveSlot()`, `releaseSlot()`, `isAvailable()` |
| 7 | `Appointment` | `model` | Transactional | `appointmentId`, `patient`, `doctor`, `timeSlot`, `status`, `totalCharge` | 1 | `confirmBooking()`, `cancelAppointment()`, `getSummary()` |
| 8 | `SMSNotification` | `model` | Implementation | `notificationId`, `phoneNumber`, `messageBody`, `delivered` | 1 | `sendNotification()`, `getDeliveryStatus()` |
| 9 | `EmailNotification` | `model` | Implementation | `notificationId`, `emailAddress`, `subject`, `messageBody`, `delivered` | 1 | `sendNotification()`, `getDeliveryStatus()` |
| 10 | `AppointmentManager` | `model` | Control Class | `managerId`, `totalBookingsProcessed`, `totalRevenueCollected` | 1 | `processBooking()` (Overloaded), `countAvailableSlots()`, `processCancellation()` |
| 11 | `Main` | `main` | Application Entry | Menu & system state arrays | Default | `main(String[] args)` Console driver |

*All instance variables are strictly `private` and encapsulated with corresponding `public` getter and setter methods.*

---

## 5. Composition and Array Processing
* **Composition:** Demonstrated in `Patient.java`. A `Patient` object strictly owns an instance of `MedicalProfile` (`this.medicalProfile = new MedicalProfile(...)`). If a patient record is instantiated, its medical profile lifecycle is coupled to the patient.
* **Arrays of Objects:**
  - `Doctor[] doctors = new Doctor[2];`
  - `Patient[] patients = new Patient[5];`
  - `TimeSlot[] timeSlots = new TimeSlot[4];`
  - `Appointment[] appointments = new Appointment[10];`
  - `User[] systemUsers = new User[3];`
  - `Notifiable[] channels = new Notifiable[2];`
* **Passing Array to a Method:**
  - `manager.countAvailableSlots(timeSlots)` in `AppointmentManager.java`: Accepts `TimeSlot[] slots` and computes the count of unreserved slots using array iteration.
  - Overloaded `processBooking(..., TimeSlot[] slots, String targetSlotId)`: Iterates through the array to find a matching slot ID.

---

## 6. Control Structures, Operators, and Strings Compliance
1. **Control Structures:**
   - `switch-case`: Handles menu selection in `Main.java` (options 1 to 6).
   - `if-else` / `else-if`: Validates slot availability, patient existence, and hours remaining.
   - `while`: Drives the main application menu loop.
   - `for`: Used exclusively for iterating through arrays (doctors, time slots, appointments, user polymorphism).
2. **Arithmetic Operators:**
   - `Doctor.calculateTotalConsultationBill(double taxRate)`: `consultationFee + (consultationFee * taxRate)`.
   - `AppointmentManager`: `totalRevenueCollected += appt.getTotalCharge()`, `totalRevenueCollected -= appt.getTotalCharge()`.
3. **Relational and Logical Operators:**
   - Relational: `patientCount < patients.length`, `hoursBeforeSlot >= 2`.
   - Logical: `this.status.equals("CONFIRMED") && hoursBeforeSlot >= 2` in `Appointment.java`.
   - Null & State Checks: `phoneNumber != null && !phoneNumber.isEmpty()` in `SMSNotification.java`.
4. **String Concatenation:** Extensively used throughout `getSummary()`, `getPatientSummary()`, and console printouts to present clean, readable reports.
5. **Type Casting:**
   - In `Main.java` Option 5: `int roundedRevenue = (int) manager.getTotalRevenueCollected();` explicitly casts `double` to `int`.

---

## 7. Console Demo & Use Case Coverage
The `Main.java` menu supports the required use cases:
1. **Use Case 1 (Option 1):** Register New Patient (creates `Patient` + composed `MedicalProfile`).
2. **Use Case 2 (Option 2):** View Doctors & Available Slots (processes `Doctor[]` and `TimeSlot[]` arrays).
3. **Use Case 3 (Option 3):** Book an Appointment (executes booking transaction, reserves slot, updates revenue, and dispatches SMS + Email notifications).
4. **Use Case 4 (Option 4):** Cancel an Appointment (validates `>= 2` hours window, releases slot, adjusts revenue, and sends cancellation alerts).
5. **System Polymorphism & Metrics (Option 5):** Demonstrates runtime polymorphism via `User[]` array and explicit numeric type casting.
6. **Exit (Option 6):** Gracefully exits the application.
