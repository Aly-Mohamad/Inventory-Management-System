import java.util.*;
import java.io.*;

public class EmployeeUserDatabase extends Database<EmployeeUser>{
    private ArrayList<EmployeeUser> records;
    private String filename;

    public EmployeeUserDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }

    @Override
    public void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                EmployeeUser employee = createRecordFrom(line);
                if (employee != null) {
                    records.add(employee);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }  
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
    public ArrayList<EmployeeUser> returnAllRecords() {
        return records; 
    }

    @Override
    public boolean contains(String key) {
        for (EmployeeUser employee : records) {
            if (employee.getSearchKey().equals(key)) {
                return true;
            } 
        }
        return false;
    }

    @Override
    public EmployeeUser getRecord(String key) {
        for (EmployeeUser employee : records) {
            if (employee.getSearchKey().equals(key)) {
                return employee;
            }
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
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename , true))) {
            for (EmployeeUser employee : records) {
                writer.println(employee.lineRepresentation());
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
}