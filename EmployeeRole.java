import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EmployeeRole implements Role{
    private ProductDatabase productDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public EmployeeRole(){
        productDatabase = new ProductDatabase("Product.txt");
        productDatabase.readFromFile();
        customerProductDatabase = new CustomerProductDatabase("Customer.txt");
        customerProductDatabase.readFromFile();
    }


    public ProductDatabase getProductDatabase() {
        return productDatabase;
    }

    public CustomerProductDatabase getCustomerProductDatabase() {
        return customerProductDatabase;
    }

    public void setProductDatabase(ProductDatabase productDatabase) {
        this.productDatabase = productDatabase;
    }

    public void setCustomerProductDatabase(CustomerProductDatabase customerProductDatabase) {
        this.customerProductDatabase = customerProductDatabase;
    }

    public void addProduct(String productId,String productName,String manufacturerName,String supplierName,int quantity) {
        float defaultPrice = 300.0f;
        Product product = new Product(productId, productName, manufacturerName, supplierName, quantity, defaultPrice);
        productDatabase.insertRecord(product);
    }

    public void addProduct(String productId,String productName,String manufacturerName,String supplierName,int quantity,float price) {
        Product product = new Product(productId, productName, manufacturerName, supplierName, quantity, price);
        productDatabase.insertRecord(product);
    }

    public Product[] getListOfProducts() {
        ArrayList<Product> products = productDatabase.returnAllRecords();
        Product[] productArray = products.toArray(new Product[0]);
        return productArray;
    }

    public CustomerProduct[] getListOfPurchasingOperations(){
        ArrayList<CustomerProduct> customerProducts = customerProductDatabase.returnAllRecords();
        CustomerProduct[] customerProductArray = customerProducts.toArray(new CustomerProduct[0]);
        return customerProductArray;
    }

    public boolean purchaseProduct(String customerSSN,String productId,LocalDate purchaseDate) {
        int quantity = productDatabase.getRecord(productId).getQuantity();
        if (quantity == 0){
            return false;
        }
        productDatabase.getRecord(productId).setQuantity(quantity - 1);
        customerProductDatabase.insertRecord(new CustomerProduct(customerSSN, productId, purchaseDate));
        return true;
    }

    public double returnProduct(String customerSSN,String productId,LocalDate purchaseDate,LocalDate returnDate) {
        boolean canReturn = true;
        if(purchaseDate.isAfter(returnDate) || returnDate.isAfter(purchaseDate.plusDays(14))){
            canReturn = false;
        }
        else if(!productDatabase.contains(productId)){
            canReturn = false;
        }else if(!customerProductDatabase.contains(customerSSN + "," + productId + "," + purchaseDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")))){
            canReturn = false;
        }
        if(canReturn){
            productDatabase.getRecord(productId).setQuantity(productDatabase.getRecord(productId).getQuantity() + 1);
            float price = productDatabase.getRecord(productId).getPrice();
            customerProductDatabase.deleteRecord(customerSSN + "," + productId + "," + purchaseDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
           //productDatabase.saveToFile();
            return price;
        }
        return -1;
    }

    public boolean applyPayment(String customerSSN,LocalDate purchaseDate) {
        CustomerProduct [] customerProducts = this.getListOfPurchasingOperations();
        for(CustomerProduct customerProduct : customerProducts){
            if(customerProduct.getCustomerSSN().equals(customerSSN) && customerProduct.getPurchaseDate().equals(purchaseDate)){
                customerProduct.setPaid(true);
                customerProductDatabase.saveToFile();
                return true;
            }
        }
        return false;
    }

    @Override
    public void logout(){
        productDatabase.saveToFile();
        customerProductDatabase.saveToFile();
    }
}