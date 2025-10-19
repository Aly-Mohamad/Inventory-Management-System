import java.util.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class CustomerProductDatabase extends Database<CustomerProduct>{

    //Constructor
    public CustomerProductDatabase (String filename){
        this.filename = filename;
        this.records = new ArrayList<>();
    }

    //Creating Customer Products Database
    @Override
    public CustomerProduct createRecordFrom(String line) {
        try {
            String[] parts = line.split(",");
            if (parts.length == 4) {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                CustomerProduct customerProduct = new CustomerProduct(parts[0].trim(), parts[1].trim(), LocalDate.parse(parts[2].trim(), formatter));
                customerProduct.setPaid(Boolean.parseBoolean(parts[3].trim()));
                return customerProduct;
            }
        } catch (Exception e) {
            System.out.println("An error occured in reading: " + line);
        }
        return null;
    }
}
