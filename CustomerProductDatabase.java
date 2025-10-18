import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.time.LocalDate;


public class CustomerProductDatabase extends Database<CustomerProduct>{
    private ArrayList<CustomerProduct> records;
    private String filename;

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


    @Override
    public void insertRecord(CustomerProduct record) {
        if (record != null && !contains(record.getSearchKey())) {
            records.add(record);
        } 
    }

    @Override
    public void deleteRecord(String key) {
        CustomerProduct Remove = null;
        for (CustomerProduct customer : records) {
            if (customer.getSearchKey().equals(key)) {
                Remove = customer;
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
            for (CustomerProduct customer : records) {
                writer.println(customer.lineRepresentation());
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
}
