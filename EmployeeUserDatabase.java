import java.util.*;

public class EmployeeUserDatabase extends Database<EmployeeUser>{

    //Constructor
    public EmployeeUserDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }

    //Creating Employee Users Database
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
}