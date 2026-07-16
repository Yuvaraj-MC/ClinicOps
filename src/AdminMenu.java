public class AdminMenu {

    private static final int DOCTOR_ENTRY = 1;
    private static final int BULK_ENTRY = 2;
    private static final int VIEW_AUDIT = 3;
    private static final int DISPLAY_DOCTORS = 4;
    private static final int LOGOUT = 5;

    // Class-level variables for 3 doctors (static = shared across methods)
    private static String doctor1Name, doctor1Spec, doctor1Slots;
    private static int doctor1Exp;

    private static String doctor2Name, doctor2Spec, doctor2Slots;
    private static int doctor2Exp;

    private static String doctor3Name, doctor3Spec, doctor3Slots;
    private static int doctor3Exp;

    public static void show() {
        boolean logout = false;

        while (!logout) {
            displayAdminOptions();
            int choice = ScannerHelper.readInt("Enter your choice: ");

            switch (choice) {
                case DOCTOR_ENTRY:
                    registerDoctors();
                    break;
                case BULK_ENTRY:
                    System.out.println(">> Bulk Data Entry - logic coming in a future use case.");
                    break;
                case VIEW_AUDIT:
                    System.out.println(">> View Audit Logs - logic coming in a future use case.");
                    break;
                case DISPLAY_DOCTORS:
                    displayDoctors();
                    break;
                case LOGOUT:
                    System.out.println("Logging out from Admin. Returning to main menu...");
                    logout = true;
                    break;
                default:
                    System.out.println("Invalid option. Please enter 1 to 5.");
            }
        }
    }

    // Displays the admin menu options
    private static void displayAdminOptions() {
        System.out.println("\n===== ADMIN MENU =====");
        System.out.println("1. Doctor's Data Entry");
        System.out.println("2. Bulk Data Entry (from .csv file)");
        System.out.println("3. View Audit Logs");
        System.out.println("4. Display Doctors' List");
        System.out.println("5. Logout");
    }

    // Registers 3 doctors, one by one
    private static void registerDoctors() {
        System.out.println("\n--- Enter details for 3 Doctors ---");

        System.out.println("\nDoctor 1:");
        doctor1Name = ScannerHelper.readString("Enter Name: ");
        doctor1Spec = ScannerHelper.readString("Enter Specialization: ");
        doctor1Exp = ScannerHelper.readInt("Enter Experience (years): ");
        doctor1Slots = ScannerHelper.readString("Enter Slots (Morning/Evening/Both): ");

        System.out.println("\nDoctor 2:");
        doctor2Name = ScannerHelper.readString("Enter Name: ");
        doctor2Spec = ScannerHelper.readString("Enter Specialization: ");
        doctor2Exp = ScannerHelper.readInt("Enter Experience (years): ");
        doctor2Slots = ScannerHelper.readString("Enter Slots (Morning/Evening/Both): ");

        System.out.println("\nDoctor 3:");
        doctor3Name = ScannerHelper.readString("Enter Name: ");
        doctor3Spec = ScannerHelper.readString("Enter Specialization: ");
        doctor3Exp = ScannerHelper.readInt("Enter Experience (years): ");
        doctor3Slots = ScannerHelper.readString("Enter Slots (Morning/Evening/Both): ");

        System.out.println("\nAll 3 doctors registered successfully!");
    }

    // Displays the registered doctors' data
    private static void displayDoctors() {
        if (doctor1Name == null) {
            System.out.println("\nNo doctors registered yet. Please use option 1 first.");
            return;
        }

        System.out.println("\n===== DOCTORS' LIST =====");
        System.out.printf("%-15s %-18s %-12s %-15s%n", "Name", "Specialization", "Experience", "Slots");
        System.out.println("-------------------------------------------------------------");
        System.out.printf("%-15s %-18s %-12d %-15s%n", doctor1Name, doctor1Spec, doctor1Exp, doctor1Slots);
        System.out.printf("%-15s %-18s %-12d %-15s%n", doctor2Name, doctor2Spec, doctor2Exp, doctor2Slots);
        System.out.printf("%-15s %-18s %-12d %-15s%n", doctor3Name, doctor3Spec, doctor3Exp, doctor3Slots);
    }
}