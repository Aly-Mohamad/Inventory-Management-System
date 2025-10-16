import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
import java.time.LocalDate;


public class CustomerProductDatabase{
    private ArrayList<CustomerProduct> records;
    private String filename;

    public CustomerProductDatabase (String filename){
        this.filename = filename;
        this.records = new ArrayList<>();
    }

    public void readFromFile() {
            try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
                String line;
                while ((line = reader.readLine()) != null) {
                    CustomerProduct customer = createRecordFrom(line);
                    if (customer != null) {
                        records.add(customer);
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("File not found: " + filename);
            } catch (IOException e) {
                System.out.println("Error reading file: " + e.getMessage());
            }  
    }

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

    public ArrayList<CustomerProduct> returnAllRecords() {
            return records; 
    }

    public boolean contains(String key) {
            for (CustomerProduct customer : records) {
                if (customer.getSearchKey().equals(key)) {
                    return true;
                } 
            }
            return false;
    }

    public CustomerProduct getRecord(String key) {
            for (CustomerProduct customer : records) {
                if (customer.getSearchKey().equals(key)) {
                    return customer;
                }
            }
            return null;
    }

    public void insertRecord(CustomerProduct record) {
            if (record != null && !contains(record.getSearchKey())) {
                records.add(record);
            } 
    }

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
