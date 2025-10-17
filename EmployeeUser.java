public class EmployeeUser implements Record{
    private String employeeId;
    private String Name;
    private String Email;
    private String Address;
    private String PhoneNumber;
    
    
    public EmployeeUser(String employeeId, String Name, String Email, String Address, String PhoneNumber){
        this.employeeId = employeeId;
        this.Name = Name;
        this.Email = Email;
        this.Address = Address;
        this.PhoneNumber = PhoneNumber;
    }
    
    @Override
    public String lineRepresentation(){
        String line = this.employeeId+","+this.Name+","+this.Email+","+this.Address+","+this.PhoneNumber;
        return line;
    }
    
    @Override
    public String getSearchKey(){
        return getEmployeeId();
    }
    
    
    public String getEmployeeId() {
        return employeeId;
    }
    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }
    public String getName() {
        return Name;
    }
    public void setName(String name) {
        Name = name;
    }
    public String getEmail() {
        return Email;
    }
    public void setEmail(String email) {
        Email = email;
    }
    public String getAddress() {
        return Address;
    }
    public void setAddress(String address) {
        Address = address;
    }
    public String getPhoneNumber() {
        return PhoneNumber;
    }
    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }
}
