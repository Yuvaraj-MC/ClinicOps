public class Doctor {
    private String id;
    private String name;
    private Specialization specialization;
    private int experience;
    private Shift shift;

    private java.util.ArrayList<String> bookedSlots = new java.util.ArrayList<>();

    public Doctor(String id, String name, Specialization specialization, int experience, Shift shift) {
        this.id = id;
        this.name = name;
        this.specialization = specialization;
        this.experience = experience;
        this.shift = shift;
    }

    public boolean isSlotAvailable(String slot) {
        return !bookedSlots.contains(slot);
    }

    public void bookSlot(String slot) {
        bookedSlots.add(slot);
    }

    public String getId() { return id; }
    public String getName() { return name; }
    public Specialization getSpecialization() {
        return this.specialization;
    }
    public int getExperience() { return experience; }
    public Shift getShift() { return shift; }
    public void setId(String id) { this.id = id; }


    @Override
    public String toString() {
        return String.format("%-8s %-15s %-18s %-12d %-15s",
                id, name, specialization, experience, shift);
    }


}