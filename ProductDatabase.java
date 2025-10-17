import java.util.*;
import java.io.*;

public class ProductDatabase extends Database<Product>{
    private ArrayList<Product> records;
    private String filename;

    public ProductDatabase(String filename) {
        this.filename = filename;
        this.records = new ArrayList<>();
    }

    @Override
    public void readFromFile() {
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                Product product = createRecordFrom(line);
                if (product != null) {
                    records.add(product);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }

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

    @Override
    public ArrayList<Product> returnAllRecords() {
        return records;
    }

    @Override
    public boolean contains(String key) {
        for (Product product : records) {
            if (product.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public Product getRecord(String key) {
        for (Product product : records) {
            if (product.getSearchKey().equals(key)) {
                return product;
            }
        }
        return null;
    }

    @Override
    public void insertRecord(Product record) {
        if (record != null && !contains(record.getSearchKey())) {
            records.add(record);
        }
    }

    @Override
    public void deleteRecord(String key) {
        Product remove = null;
        for (Product product : records) {
            if (product.getSearchKey().equals(key)) {
                remove = product;
                break;
            }
        }
        if (remove != null) {
            records.remove(remove);
        }
    }

    @Override
    public void saveToFile() {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Product product : records) {
                writer.println(product.lineRepresentation());
            }
        } catch (IOException e) {
            System.out.println("Error saving to file: " + e.getMessage());
        }
    }
}
