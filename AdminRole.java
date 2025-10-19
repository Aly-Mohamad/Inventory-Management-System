import java.util.ArrayList;

public class AdminRole implements Role{
    private EmployeeUserDatabase database;
    
    //Constructor
    public AdminRole(){
        database = new EmployeeUserDatabase("Employees.txt");
        database.readFromFile();
    }

    //Getters and Setters
    public EmployeeUserDatabase getDatabase() {
        return database;
    }

    public void setDatabase(EmployeeUserDatabase database) {
        this.database = database;
    }       
    

    public void addEmployee(String employeeId,String name,String email,String address,String phoneNumber){
        EmployeeUser employeeUser = new EmployeeUser(employeeId,name,email,address,phoneNumber);
        database.insertRecord(employeeUser);
    }

    public EmployeeUser[] getListOfEmployees(){
        ArrayList<EmployeeUser> records = database.returnAllRecords();
        EmployeeUser[] employeeUsers;
        employeeUsers = records.toArray(new EmployeeUser[0]); //Setting the size to 0 makes the new array dynamic
        return employeeUsers;
    }

    public void removeEmployee(String key){
        database.deleteRecord(key);
    }
    
    //Overriding the Role interface
    @Override
    public void logout(){
        database.saveToFile();
    }
}
