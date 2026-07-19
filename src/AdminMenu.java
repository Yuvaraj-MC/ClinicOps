import java.util.ArrayList;

public class AdminMenu {

    private static final int DOCTOR_ENTRY = 1;
    private static final int BULK_ENTRY = 2;
    private static final int VIEW_AUDIT = 3;
    private static final int DISPLAY_DOCTORS = 4;
    private static final int LOGOUT = 5;

    private static ArrayList<Doctor> doctors = new ArrayList<>();
    private static int idCounter = 1;

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
                    bulkEntry();
                    break;
                case VIEW_AUDIT:
                    AuditLogger.viewLogs();
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

    private static void displayAdminOptions() {
        System.out.println("\n===== ADMIN MENU =====");
        System.out.println("1. Doctor's Data Entry");
        System.out.println("2. Bulk Data Entry (from .csv file)");
        System.out.println("3. View Audit Logs");
        System.out.println("4. Display Doctors' List");
        System.out.println("5. Logout");
    }

    // Lets other classes (FrontDesk) access the doctor list
    public static ArrayList<Doctor> getDoctors() {
        return doctors;
    }

    // Reads doctors from a CSV file and adds them to the list
    private static void bulkEntry() {
        String filePath = ScannerHelper.readString("Enter the CSV file name with path: ");

        try {
            ArrayList<Doctor> importedDoctors = FileHandler.readDoctorsFromCsv(filePath);

            if (importedDoctors.isEmpty()) {
                System.out.println("No valid doctors were imported.");
                AuditLogger.log("CSV upload failed: no valid doctors in " + filePath, "ERROR");
                return;
            }

            // Assign auto-generated IDs to each imported doctor
            for (Doctor doctor : importedDoctors) {
                String id = String.format("D%04d", idCounter);
                doctor.setId(id);
                idCounter++;
            }

            // Batch add - more efficient than adding one by one
            doctors.addAll(importedDoctors);

            System.out.println("\n" + importedDoctors.size() + " doctors imported successfully!");
            AuditLogger.log(importedDoctors.size() + " doctors imported from CSV: " + filePath, "INFO");

        } catch (Exception e) {
            System.out.println("Error reading CSV file: " + e.getMessage());
            AuditLogger.log("CSV upload failed: " + e.getMessage(), "ERROR");
        }
    }

    private static void registerDoctors() {
        System.out.println("\n--- Enter details for 3 Doctors ---");

        for (int i = 1; i <= 3; i++) {
            System.out.println("\nDoctor " + i + ":");
            String name = ScannerHelper.readString("Enter Name: ");
            Specialization spec = ScannerHelper.readEnumChoice(
                    "Select Specialization:", Specialization.values());
            int exp = ScannerHelper.readInt("Enter Experience (years): ");
            Shift shift = ScannerHelper.readEnumChoice(
                    "Select Shift:", Shift.values());

            String id = String.format("D%04d", idCounter);
            idCounter++;

            Doctor doctor = new Doctor(id, name, spec, exp, shift);
            doctors.add(doctor);
            AuditLogger.log("Doctor registered: " + doctor.getName(), "INFO");
        }

        System.out.println("\nAll 3 doctors registered successfully!");
    }

    private static void displayDoctors() {
        if (doctors.isEmpty()) {
            System.out.println("\nNo doctors registered yet. Please use option 1 first.");
            return;
        }

        System.out.println("\n===== DOCTORS' LIST =====");
        System.out.printf("%-8s %-15s %-18s %-12s %-15s%n",
                "ID", "Name", "Specialization", "Experience", "Shift");
        System.out.println("--------------------------------------------------------------------------");
        for (Doctor doctor : doctors) {
            System.out.println(doctor);
        }
    }
}