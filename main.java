import java.time.LocalDate;

public class main {
    public static void main(String[] args) {
        System.out.println("=== Inventory Management System Full Test ===\n");
        
        AdminRole admin = new AdminRole();
        System.out.println("--- AdminRole Tests ---");
        admin.addEmployee("E1200", "Ahmed", "ahmed_1999@gmail.com", "Alexandria", "01088877345");
        admin.addEmployee("E1201", "Sara", "sara@gmail.com", "Cairo", "01122334455");
        admin.addEmployee("E1202", "aly", "aly@gmail.com", "alex", "01122534455");
        admin.addEmployee("E1203", "john", "john@gmail.com", "loran", "013422334455");
        System.out.println("\nList of Employees:");
        for (EmployeeUser emp : admin.getListOfEmployees()) {
            System.out.println(emp.lineRepresentation());
        }
        System.out.println("\nTesting EmployeeUserDatabase.contains() and getRecord():");
        if (admin.getDatabase().contains("E1200")) {
            EmployeeUser e = admin.getDatabase().getRecord("E1200");
            System.out.println("Found employee: " + e.lineRepresentation());
        } else {
            System.out.println("Employee not found.");
        }
        admin.removeEmployee("E1201");
        System.out.println("\nAfter removing E1201:");
        for (EmployeeUser emp : admin.getListOfEmployees()) {
            System.out.println(emp.lineRepresentation());
        }
        admin.logout();
        System.out.println("Admin logout successful.\n");
        
        System.out.println("--- EmployeeRole Tests ---");
        EmployeeRole employee = new EmployeeRole();
        employee.addProduct("P2394", "Laptop", "Apple", "TechSupplier", 10, 500);
        employee.addProduct("P2395", "Phone", "Samsung", "MobileWorld", 5, 700);
        employee.addProduct("P2396", "tv", "LG", "MobileWorld", 2, 1000);
        employee.addProduct("P2397", "headphones", "Samsung", "MobileWorld", 12, 300);
        System.out.println("\nList of Products:");
        for (Product p : employee.getListOfProducts()) {
            System.out.println(p.lineRepresentation());
        }
        System.out.println("\nTesting ProductDatabase.contains() and getRecord():");
        if (employee.getProductDatabase().contains("P2394")) {
            Product p = employee.getProductDatabase().getRecord("P2394");
            System.out.println("Found product: " + p.lineRepresentation());
        } else {
            System.out.println("Product not found.");
        }
        LocalDate today = LocalDate.now();
        boolean purchaseResult = employee.purchaseProduct("7845345678", "P2394", today);
        System.out.println("\nPurchase Product P2394: " + (purchaseResult ? "Success" : "Failed"));
        
        purchaseResult = employee.purchaseProduct("468325732", "P2397", today);
        System.out.println("\nPurchase Product P2395: " + (purchaseResult ? "Success" : "Failed"));
        System.out.println("\nList of Purchases:");
        for (CustomerProduct cp : employee.getListOfPurchasingOperations()) {
            System.out.println(cp.lineRepresentation());
        }
        boolean paymentApplied = employee.applyPayment("7845345678", today);
        System.out.println("\nPayment applied for purchase on " + today + ": " + paymentApplied);
        System.out.println("\nTesting CustomerProductDatabase.contains() and getRecord():");
        String key = "7845345678,P2394," + today.format(java.time.format.DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        if (employee.getCustomerProductDatabase().contains(key)) {
            CustomerProduct record = employee.getCustomerProductDatabase().getRecord(key);
            System.out.println("Found record: " + record.lineRepresentation());
        } else {
            System.out.println("Record not found.");
        }
        double refund = employee.returnProduct("7845345678", "P2394", today, today.plusDays(5));
        System.out.println("\nReturn Product P2394: " + (refund != -1 ? "Refunded " + refund : "Failed"));
        employee.getProductDatabase().deleteRecord("P2395");
        System.out.println("\nAfter deleting product P2395:");
        for (Product p : employee.getListOfProducts()) {
            System.out.println(p.lineRepresentation());
        }
        employee.logout();
        System.out.println("Employee logout successful.");
        System.out.println("\n=== All Tests Completed Successfully ===");
    }
}