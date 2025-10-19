

import java.io.IOException;
import java.time.LocalDate;

public class TestClass {
    private AdminRole admin;
    private EmployeeRole employee;

    public TestClass() {
        admin = new AdminRole();
        employee = new EmployeeRole();
    }

    public void testAdminRole() throws IOException {

        EmployeeUser[] employees = admin.getListOfEmployees();
        for (EmployeeUser emp : employees) {
            admin.removeEmployee(emp.getSearchKey());
        }
        admin.logout();

        admin = new AdminRole();

        employees = admin.getListOfEmployees();
        if (employees.length != 0) {
            System.out.println("There is a problem in removing old employees from the employees file or arraylist");
            return;
        }

        admin.addEmployee("EMP-001", "Ahmed", "ahmed@email.com", "123 Street Cairo", "+20123456789");

        admin.addEmployee("EMP-002", "Mohamed", "mohamed@email.com", "456 Boulevard Giza", "+20198765432");

        admin.addEmployee("EMP-003", "Mariam", "mariam@email.com", "789 Avenue Alexandria", "+20145678923");

        admin.addEmployee("EMP-004", "Hossam", "hossam@email.com", "101 Lane Luxor", "+20132165498");
        admin.addEmployee("EMP-005", "Nour", "nour@email.com", "202 Drive Aswan", "+20165432178");

        admin.logout();

        admin = new AdminRole();
        employees = admin.getListOfEmployees();
        if (employees.length != 5) {
            System.out.println("There is a problem in adding new employees to the employees file or arraylist");
            return;
        }
        admin.logout();
        System.out.println("You have passed the tests of admin role.\nHowever, there will be different tests during the discussion.\nMake sure that you have implemented all the requirements correctly");

    }

    private boolean addPurchaseOperation(CustomerProduct purchaseOperation) throws IOException {
        return employee.purchaseProduct(purchaseOperation.getCustomerSSN(),
                purchaseOperation.getProductID(),
                purchaseOperation.getPurchaseDate());
    }

    public void testEmployeeRole() throws IOException {
        Product[] oldProducts = employee.getListOfProducts();
        
        employee.addProduct("LPT-001", "MacBook Pro 16-inch", "Apple", "BestBuy", 2, 5000);
        employee.addProduct("SMT-002", "Samsung Galaxy S22", "Samsung", "Amazon", 3, 1200);
        employee.addProduct("GMC-003", "PlayStation 5", "Sony", "GameStop", 4, 500);
        employee.addProduct("SPK-004", "Google Nest Audio", "Google", "Walmart", 1, 200);
        employee.addProduct("WTCH-005", "Fitbit Versa 3", "Fitbit", "Target", 2, 150);
        Product[] newProducts = employee.getListOfProducts();
        if (newProducts.length - oldProducts.length != 5) {
            System.out.println("There is a problem in adding new products to the products file or arraylist");
            return;
        }
        CustomerProduct[] oldOperations = employee.getListOfPurchasingOperations();

        String customer1 = "1111111";
        String customer2 = "2222222";
        String customer3 = "3333333";
        boolean result;
        CustomerProduct oP1 = new CustomerProduct(customer1, "LPT-001", LocalDate.of(2023, 6, 1));
        CustomerProduct oP2 = new CustomerProduct(customer1, "LPT-001", LocalDate.of(2023, 6, 5));
        CustomerProduct oP3 = new CustomerProduct(customer2, "SPK-004", LocalDate.of(2023, 5, 11));
        CustomerProduct oP4 = new CustomerProduct(customer3, "SPK-004", LocalDate.of(2023, 5, 12));
        CustomerProduct oP5 = new CustomerProduct(customer3, "SPK-004", LocalDate.of(2023, 5, 20));
        result = addPurchaseOperation(oP1);
        if (!result) {
            System.out.println("Purchase1: it is a valid purchase.\nHowever, your code refuses it. Fix your code!!!");
            return;
        }
        result = addPurchaseOperation(oP2);
        if (!result) {
            System.out.println("Purchase2: it is a valid purchase.\nHowever, your code refuses it. Fix your code!!!");
            return;
        }
        result = addPurchaseOperation(oP3);
        if (!result) {
            System.out.println("Purchase3: it is a valid purchase.\nHowever, your code refuses it. Fix your code!!!");
            return;
        }
        result = addPurchaseOperation(oP4);
        if (result) {
            System.out.println("Purchase4: it is an invalid purchase.\nHowever, your code accepts it. Fix your code!!!");
            return;
        }
        CustomerProduct[] newOperations = employee.getListOfPurchasingOperations();
        if (newOperations.length - oldOperations.length != 3) {
            System.out.println("There is a problem in adding new operations to the customers products file or arraylist");
            return;
        }
        double returnValue=employee.returnProduct(oP3.getCustomerSSN(), oP3.getProductID(), oP3.getPurchaseDate(), LocalDate.of(2023, 5, 17));
        if(returnValue != newProducts[oldProducts.length+3].getPrice()){
            System.out.println("There is a problem in returning the products");
            return;
        }
        result = addPurchaseOperation(oP5);
        
        if (!result) {
            System.out.println("Purchase5: it is a valid purchase.\nHowever, your code refuses it. Fix your code!!!");
            return;
        }        
        employee.logout();
        System.out.println("You have passed the tests of employee role.\nHowever, there will be different tests during the discussion.\nMake sure that you have implemented all the requirements correctly");
    }

    public static void main(String[] args) throws IOException {
        TestClass test = new TestClass();
        test.testAdminRole();
        System.out.println("---------------------------------------------------\n---------------------------------------------------");
        test.testEmployeeRole();
        
    }
}

