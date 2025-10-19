import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class CustomerProduct implements Record{
    private String customerSSN;
    private String productID;
    private LocalDate purchaseDate;
    private boolean paid;
    
    //Constructor
    public CustomerProduct(String customerSSN, String productID, LocalDate purchaseDate){
        this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
        this.paid = false;
    } 
    
    @Override
    public String lineRepresentation(){
        return getSearchKey() + "," + this.paid;
    }
    
    @Override
    public String getSearchKey(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return this.customerSSN + "," + this.productID + "," + this.purchaseDate.format(formatter);
    }
    
    //Setters and Getters

    public String getCustomerSSN() {
        return customerSSN;
    }
    public void setCustomerSSN(String customerSSN) {
        this.customerSSN = customerSSN;
    }
    public String getProductID() {
        return productID;
    }
    public void setProductID(String productID) {
        this.productID = productID;
    }
    public LocalDate getPurchaseDate() {
        return purchaseDate;
    }
    public void setPurchaseDate(LocalDate purchaseDate) {
        this.purchaseDate = purchaseDate;
    }
    public boolean isPaid() {
        return paid;
    }
    public void setPaid(boolean paid) {
        this.paid = paid;
    }
}
