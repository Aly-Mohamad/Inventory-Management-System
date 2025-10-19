import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class EmployeeRole implements Role{
    private ProductDatabase productDatabase;
    private CustomerProductDatabase customerProductDatabase;

    //Constructon
    public EmployeeRole(){
        productDatabase = new ProductDatabase("Product.txt");
        productDatabase.readFromFile();
        customerProductDatabase = new CustomerProductDatabase("Customer.txt");
        customerProductDatabase.readFromFile();
    }

    //Setters and Getters
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
        Product product = productDatabase.getRecord(productId);
        if (product == null) {
            return false;
        }
        int quantity = product.getQuantity();
        if (quantity == 0){
            return false;
        }
        product.setQuantity(quantity - 1);
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
            Product product = productDatabase.getRecord(productId);
            if (product != null) {
                product.setQuantity(product.getQuantity() + 1);
                float price = product.getPrice();
                customerProductDatabase.deleteRecord(customerSSN + "," + productId + "," + purchaseDate.format(DateTimeFormatter.ofPattern("dd-MM-yyyy")));
                return price;
            }
        }
        return -1;
    }


    public boolean applyPayment(String customerSSN,LocalDate purchaseDate) {
        CustomerProduct [] customerProducts = this.getListOfPurchasingOperations();
        for(CustomerProduct customerProduct : customerProducts){
            if(customerProduct.getCustomerSSN().equals(customerSSN) && customerProduct.getPurchaseDate().equals(purchaseDate)){
                String searchKey = customerProduct.getSearchKey();
                CustomerProduct record = customerProductDatabase.getRecord(searchKey);
                if (record != null) {
                    record.setPaid(true);
                    customerProductDatabase.saveToFile();
                    return true;
                }
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