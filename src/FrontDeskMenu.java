import java.util.ArrayList;
import java.util.Random;

public class FrontDeskMenu {

    private static final int PATIENT_REGISTRATION = 1;
    private static final int BOOK_APPOINTMENT = 2;
    private static final int VIEW_PATIENTS = 3;
    private static final int LOGOUT = 4;

    private static final int MAX_MOBILE_ATTEMPTS = 3;   // UC14: security threshold

    private static ArrayList<Appointment> appointments = new ArrayList<>();

    // Static list retains patient data across method calls
    private static ArrayList<Patient> patients = new ArrayList<>();
    private static int idCounter = 1;


    public static void show() {
        boolean logout = false;

        while (!logout) {
            displayFrontDeskOptions();
            int choice = ScannerHelper.readInt("Enter your choice: ");

            switch (choice) {
                case PATIENT_REGISTRATION:
                    registerPatient();
                    break;
                case BOOK_APPOINTMENT:
                    bookAppointment();
                    break;
                case VIEW_PATIENTS:
                    viewPatients();
                    break;
                case LOGOUT:
                    System.out.println("Logging out from Front Desk. Returning to main menu...");
                    logout = true;
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1 to 4.");
            }
        }
    }


    private static void displayFrontDeskOptions() {
        System.out.println("\n===== FRONT DESK MENU =====");
        System.out.println("1. Patient Registration");
        System.out.println("2. Book Appointment");
        System.out.println("3. View Patients");
        System.out.println("4. Logout");
    }


    // Registers a patient only if mobile number is not already present
    private static void registerPatient() {
        System.out.println("\n--- Register New Patient ---");

        String mobile = ScannerHelper.readMobileNumber("Enter Mobile Number: ");

        Patient existing = findByMobile(mobile);

        if (existing != null) {
            System.out.println("\nWelcome back, " + existing.getName() + "!");
            System.out.println("You are already registered with us.");
            System.out.println("\nYour existing details:");
            System.out.printf("%-8s %-15s %-10s %-6s %-15s%n",
                    "ID", "Name", "Gender", "Age", "Mobile");
            System.out.println(existing);
            return;
        }

        String name = ScannerHelper.readString("Enter Name: ");
        String gender = ScannerHelper.readString("Enter Gender: ");
        int age = ScannerHelper.readInt("Enter Age: ");

        String id = String.format("P%04d", idCounter);
        idCounter++;

        Patient patient = new Patient(id, name, gender, age, mobile);
        patients.add(patient);
        AuditLogger.log("Patient registered: " + patient.getName(), "INFO");

        System.out.println("\nPatient registered successfully! Patient ID: " + id);
    }

    // Linear search - returns the Patient if mobile matches, else null
    private static Patient findByMobile(String mobile) {
        for (Patient patient : patients) {
            if (patient.getMobileNumber().equals(mobile)) {
                return patient;
            }
        }
        return null;
    }

    // Displays all registered patients
    private static void viewPatients() {
        if (patients.isEmpty()) {
            System.out.println("\nNo patients registered yet. Please use option 1 first.");
            return;
        }

        System.out.println("\n===== PATIENTS' LIST =====");
        System.out.printf("%-8s %-15s %-10s %-6s %-15s%n",
                "ID", "Name", "Gender", "Age", "Mobile");
        System.out.println("------------------------------------------------------------");
        for (Patient patient : patients) {
            System.out.println(patient);
        }
    }


    private static void bookAppointment() {
        System.out.println("\n--- Book Appointment ---");

        // UC14: wrong mobile threshold -> security WARNING
        Patient patient = null;
        int wrongAttempts = 0;

        while (true) {
            String mobile = ScannerHelper.readMobileNumber("Enter Patient Mobile Number: ");
            patient = findByMobile(mobile);

            if (patient != null) {
                break;
            }

            wrongAttempts++;
            System.out.println("No patient found with this mobile.");

            if (wrongAttempts >= MAX_MOBILE_ATTEMPTS) {
                AuditLogger.log("SECURITY: " + MAX_MOBILE_ATTEMPTS
                        + " failed mobile lookups - possible unauthorized attempt", "WARNING");
                System.out.println("Too many failed attempts. Returning to menu.");
                return;
            }
        }

        System.out.println("Patient found: " + patient.getName());

        // UC10: specialization input
        Specialization requestedSpec = ScannerHelper.readSpecialization();

        // Get preferred slot
        String slot = ScannerHelper.readSlotChoice();

        ArrayList<Doctor> doctors = AdminMenu.getDoctors();
        if (doctors.isEmpty()) {
            System.out.println("No doctors available in the system.");
            return;
        }

        // Specialization == AND shift AND slot free
        java.util.List<Doctor> freeDoctors = doctors.stream()
                .filter(doc -> doc.getSpecialization() == requestedSpec)
                .filter(doc -> doc.isTimeInShift(slot))
                .filter(doc -> doc.isSlotAvailable(slot))
                .collect(java.util.stream.Collectors.toList());

        if (freeDoctors.isEmpty()) {
            System.out.println("No " + requestedSpec + " doctor available at "
                    + slot + ". Please try another slot or specialization.");
            AuditLogger.log("Booking failed - no doctor for " + requestedSpec, "WARNING");
            return;
        }

        // If more than one free, pick one at random
        Random rand = new Random();
        Doctor assignedDoc = freeDoctors.get(rand.nextInt(freeDoctors.size()));

        assignedDoc.bookSlot(slot);

        Appointment appointment = new Appointment(patient, assignedDoc, slot);
        appointments.add(appointment);
        AuditLogger.log("Appointment booked for " + patient.getName()
                + " with Dr. " + assignedDoc.getName(), "INFO");

        System.out.println("\nAppointment booked successfully!");
        System.out.println(appointment);
    }
}