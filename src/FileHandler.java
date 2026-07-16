import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class FileHandler {

    // Reads CSV, validates each row, returns list of valid doctors.
    // Note: id is passed in as null here — AdminMenu assigns real IDs after.
    public static ArrayList<Doctor> readDoctorsFromCsv(String filePath) {
        ArrayList<Doctor> doctorList = new ArrayList<>();

        // Try-with-resources: file auto-closes even if an error happens
        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            int lineNumber = 0;

            while ((line = reader.readLine()) != null) {
                lineNumber++;
                line = line.trim();

                if (line.isEmpty()) {
                    continue; // skip blank lines
                }

                String[] parts = line.split(",");

                // Expecting exactly 4 columns: name, specialization, experience, shift
                if (parts.length != 4) {
                    System.out.println("Line " + lineNumber + " skipped: wrong number of columns.");
                    continue;
                }

                String name = parts[0].trim();
                String specText = parts[1].trim().toUpperCase();
                String expText = parts[2].trim();
                String shiftText = parts[3].trim().toUpperCase();

                // Validate specialization against the enum
                Specialization spec;
                try {
                    spec = Specialization.valueOf(specText);
                } catch (IllegalArgumentException e) {
                    System.out.println("Line " + lineNumber + " skipped: invalid specialization '" + parts[1].trim() + "'.");
                    continue;
                }

                // Validate shift against the enum
                Shift shift;
                try {
                    shift = Shift.valueOf(shiftText);
                } catch (IllegalArgumentException e) {
                    System.out.println("Line " + lineNumber + " skipped: invalid shift '" + parts[3].trim() + "'.");
                    continue;
                }

                // Validate experience is an integer
                int exp;
                try {
                    exp = Integer.parseInt(expText);
                } catch (NumberFormatException e) {
                    System.out.println("Line " + lineNumber + " skipped: experience '" + expText + "' is not a number.");
                    continue;
                }

                // id is null for now — AdminMenu will assign the real ID
                Doctor doctor = new Doctor(null, name, spec, exp, shift);
                doctorList.add(doctor);
            }

        } catch (IOException e) {
            System.out.println("Error: Could not read file. " + e.getMessage());
        }

        return doctorList;
    }
}