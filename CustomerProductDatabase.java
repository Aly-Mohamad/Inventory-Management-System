import java.util.*;
import java.time.LocalDate;


public class CustomerProductDatabase extends Database<CustomerProduct>{

    public CustomerProductDatabase (String filename){
        this.filename = filename;
        this.records = new ArrayList<>();
    }

    @Override
    public CustomerProduct createRecordFrom(String line) {
        try {
            String[] parts = line.split(",");
            if (parts.length == 3) {
                return new CustomerProduct(parts[0].trim(), parts[1].trim(), LocalDate.parse(parts[2])); 
            }
        } catch (Exception e) {
            System.out.println("An error occured in reading: " + line);
        }
        return null;
    }
}
