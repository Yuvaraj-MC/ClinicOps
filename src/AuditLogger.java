import java.util.ArrayList;

public class AuditLogger {

    // static list — antha app lో logs oke chota store avutాయి
    private static ArrayList<AuditLog> logs = new ArrayList<>();

    public static void log(String message, String level) {
        logs.add(new AuditLog(message, level));
    }

    public static void viewLogs() {
        if (logs.isEmpty()) {
            System.out.println("\nNo audit logs available yet.");
            return;
        }
        System.out.println("\n===== AUDIT LOGS =====");
        for (AuditLog entry : logs) {
            System.out.println(entry);
        }
    }
}