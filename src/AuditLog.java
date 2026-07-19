import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class AuditLog {
    private String message;
    private String level;          // INFO, WARNING, ERROR
    private LocalDateTime timestamp;

    public AuditLog(String message, String level) {
        this.message = message;
        this.level = level;
        this.timestamp = LocalDateTime.now();   // Time API
    }

    @Override
    public String toString() {
        DateTimeFormatter fmt = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return "[" + timestamp.format(fmt) + "] [" + level + "] " + message;
    }
}