import java.util.*;

public class ProductDatabase extends Database<Product>{

    //Constructor
    public ProductDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }

    //Creating Products Database
    @Override
    public Product createRecordFrom(String line) {
        try {
            String[] parts = line.split(",");
            if (parts.length == 6) {
                return new Product(
                        parts[0].trim(),
                        parts[1].trim(),
                        parts[2].trim(),
                        parts[3].trim(),
                        Integer.parseInt(parts[4].trim()),
                        Float.parseFloat(parts[5].trim())
                );
            }
        } catch (Exception e) {
            System.out.println("Error reading line: " + line);
        }
        return null;
    }
}
