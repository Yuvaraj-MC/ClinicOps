public class Appointment {
    private Patient patient;
    private Doctor doctor;
    private String slot;

    public Appointment(Patient patient, Doctor doctor, String slot) {
        this.patient = patient;   // reference, not copy (Object Composition)
        this.doctor = doctor;
        this.slot = slot;
    }

    public Patient getPatient() { return patient; }
    public Doctor getDoctor() { return doctor; }
    public String getSlot() { return slot; }

    @Override
    public String toString() {
        return String.format("Slot: %-8s | Doctor: %-8s %-15s | Patient: %-8s %-15s",
                slot, doctor.getId(), doctor.getName(),
                patient.getId(), patient.getName());
    }
}