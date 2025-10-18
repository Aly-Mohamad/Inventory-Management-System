import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public abstract class Database<T extends Record> {
    protected ArrayList<T> records;
    protected String filename;

    public void readFromFile(){
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                T x = createRecordFrom(line);
                if (x != null) {
                    records.add(x);
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + filename);
        } catch (IOException e) {
            System.out.println("Error reading file: " + e.getMessage());
        }
    }
    public abstract T createRecordFrom(String line);
    public ArrayList<T> returnAllRecords(){
        return records;
    }
    public boolean contains(String key){
        for (T x : records) {
            if (x.getSearchKey().equals(key)) {
                return true;
            }
        }
        return false;
    }
    public T getRecord(String key){
        for (T x : records) {
            if (x.getSearchKey().equals(key)) {
                return x;
            }
        }
        return null;
    }
    public abstract void insertRecord(T record);
    public abstract void deleteRecord(String key);
    public abstract void saveToFile();
}
