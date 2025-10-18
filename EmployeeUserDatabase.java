import java.util.*;
import java.io.*;

public class EmployeeUserDatabase extends Database<EmployeeUser>{

    public EmployeeUserDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }


    @Override
    public EmployeeUser createRecordFrom(String line) {
        try {
            String[] parts = line.split(",");
            if (parts.length == 5) {
                return new EmployeeUser(parts[0].trim(), parts[1].trim(), parts[2].trim(), 
                                    parts[3].trim(), parts[4].trim()); }
        } catch (Exception e) {
            System.out.println("An error occured in reading: " + line);
        }
        return null;
    }


    @Override
    public void insertRecord(EmployeeUser record) {
        if (record != null && !contains(record.getSearchKey())) {
            records.add(record);
        }
    }

    @Override
    public void deleteRecord(String key) {
        EmployeeUser Remove = null;
        for (EmployeeUser employee : records) {
            if (employee.getSearchKey().equals(key)) {
                Remove = employee;
                break;
            }
        }
        if (Remove != null) {
            records.remove(Remove);
        }
    }
    
    @Override
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (EmployeeUser employee : records) {
                writer.println(employee.lineRepresentation());
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
}