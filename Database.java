import java.util.ArrayList;

public abstract class Database<T extends Record> {
    protected ArrayList<T> records;
    protected String filename;

    public abstract void readFromFile();
    public abstract T createRecordFrom(String line);
    public abstract ArrayList<T> returnAllRecords();
    public abstract boolean contains(String key);
    public abstract T getRecord(String key);
    public abstract void insertRecord(T record);
    public abstract void deleteRecord(String key);
    public abstract void saveToFile();
}
