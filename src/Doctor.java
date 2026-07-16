public class Doctor {
    private String id;
    private String name;
    private String specialization;
    private int experience;
    private String shift;

    // Constructor - sets the initial state of a Doctor object
    public Doctor(String id, String name, String specialization, int experience, String shift) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.experience = experience;
        this.shift = shift;
    }

    // Getters
    public String getId() { return id; }
    public String getName() { return name; }
    public String getSpecialization() { return specialization; }
    public int getExperience() { return experience; }
    public String getShift() { return shift; }

    // Clean way to represent a doctor as a formatted row
    @Override
    public String toString() {
        return String.format("%-8s %-15s %-18s %-12d %-15s",
                id, name, specialization, experience, shift);
    }
}