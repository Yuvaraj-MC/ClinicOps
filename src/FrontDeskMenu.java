import java.util.ArrayList;

public class FrontDeskMenu {

    private static final int PATIENT_REGISTRATION = 1;
    private static final int BOOK_APPOINTMENT = 2;
    private static final int VIEW_PATIENTS = 3;
    private static final int LOGOUT = 4;

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
                    System.out.println(">> Book Appointment - logic coming in a future use case.");
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

        // Read mobile number FIRST
        String mobile = ScannerHelper.readMobileNumber("Enter Mobile Number: ");

        // Linear search: check if this mobile already exists
        Patient existing = findByMobile(mobile);

        if (existing != null) {
            System.out.println("\nWelcome back, " + existing.getName() + "!");
            System.out.println("You are already registered with us.");
            System.out.println("\nYour existing details:");
            System.out.printf("%-8s %-15s %-10s %-6s %-15s%n",
                    "ID", "Name", "Gender", "Age", "Mobile");
            System.out.println(existing);
            return; // stop registration
        }

        // Not found -> continue with full registration
        String name = ScannerHelper.readString("Enter Name: ");
        String gender = ScannerHelper.readString("Enter Gender: ");
        int age = ScannerHelper.readInt("Enter Age: ");

        String id = String.format("P%04d", idCounter);
        idCounter++;

        Patient patient = new Patient(id, name, gender, age, mobile);
        patients.add(patient);

        System.out.println("\nPatient registered successfully! Patient ID: " + id);
    }

    // Linear search - returns the Patient if mobile matches, else null
    private static Patient findByMobile(String mobile) {
        for (Patient patient : patients) {
            if (patient.getMobileNumber().equals(mobile)) {
                return patient;
            }
        }
        return null; // not found
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
}