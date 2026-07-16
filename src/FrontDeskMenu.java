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

    // Registers a single patient with auto-generated ID
    private static void registerPatient() {
        System.out.println("\n--- Register New Patient ---");

        String name = ScannerHelper.readString("Enter Name: ");
        String gender = ScannerHelper.readString("Enter Gender: ");
        int age = ScannerHelper.readInt("Enter Age: ");
        String mobile = ScannerHelper.readMobileNumber("Enter Mobile Number: ");

        String id = String.format("P%04d", idCounter);
        idCounter++;

        Patient patient = new Patient(id, name, gender, age, mobile);
        patients.add(patient);

        System.out.println("\nPatient registered successfully! Patient ID: " + id);
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