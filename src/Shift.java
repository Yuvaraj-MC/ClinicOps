public enum Shift {
    MORNING("Morning (9:00 AM - 1:00 PM)"),
    EVENING("Evening (4:00 PM - 8:00 PM)"),
    BOTH("Both (Morning & Evening)");

    private final String description;

    Shift(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }
}