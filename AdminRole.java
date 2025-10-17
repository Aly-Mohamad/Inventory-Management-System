import java.util.ArrayList;

public class AdminRole implements Role{
    private EmployeeUserDatabase database;
    
    public AdminRole(){
        database = new EmployeeUserDatabase("Employees.txt");
        database.readFromFile();
    }

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
        employeeUsers = records.toArray(new EmployeeUser[0]);
        return employeeUsers;
    }

    public void removeEmployee(String key){
        database.deleteRecord(key);
    }
    
    @Override
    public void logout(){
        database.saveToFile();
    }
}
