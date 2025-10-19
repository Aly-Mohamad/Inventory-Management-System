# Inventory Management System

A Java-based inventory management system that allows administrators to manage employees and employees to manage products, customer purchases, and returns.

## Features

### Admin Role
- **Employee Management**
  - Add new employees with ID, name, email, address, and phone number
  - View all employees in the system
  - Remove employees from the system
  - Data persistence to `Employees.txt`

### Employee Role
- **Product Management**
  - Add products with custom or default pricing (default: $300.00)
  - View all products in inventory
  - Track product quantities and pricing

- **Customer Operations**
  - Process customer product purchases
  - Handle product returns (14-day return policy)
  - Apply payments to customer purchases
  - View all purchase operations

- **Data Persistence**
  - Products saved to `Product.txt`
  - Customer purchases saved to `Customer.txt`

## Project Structure

```
Inventory-Management-System/
├── main.java                   # Main application with comprehensive test suite
├── Database.java               # Abstract base class for database operations
├── Record.java                 # Interface for database records
├── Product.java                # Product entity class
├── ProductDatabase.java        # Product database operations
├── EmployeeUser.java           # Employee entity class
├── EmployeeUserDatabase.java   # Employee database operations
├── CustomerProduct.java        # Customer purchase entity class
├── CustomerProductDatabase.java # Customer purchase database operations
├── AdminRole.java              # Admin functionality
├── EmployeeRole.java           # Employee functionality
├── Role.java                   # Role interface
├── Product.txt                 # Product data file
├── Employees.txt               # Employee data file
├── Customer.txt                # Customer purchase data file
└── README.md                   # This file
```

## Class Architecture

### Core Classes
- **`Record`**: Interface defining `lineRepresentation()` and `getSearchKey()` methods
- **`Database<T>`**: Abstract generic class handling file I/O operations for any Record type
- **`Role`**: Interface defining `logout()` method for role-based operations

### Entity Classes
- **`Product`**: Represents inventory items with ID, name, manufacturer, supplier, quantity, and price
- **`EmployeeUser`**: Represents employees with ID, name, email, address, and phone number
- **`CustomerProduct`**: Represents customer purchases with SSN, product ID, purchase date, and payment status

### Database Classes
- **`ProductDatabase`**: Manages product records with CSV parsing
- **`EmployeeUserDatabase`**: Manages employee records with CSV parsing
- **`CustomerProductDatabase`**: Manages customer purchase records with date formatting

### Role Classes
- **`AdminRole`**: Handles employee management operations
- **`EmployeeRole`**: Handles product and customer operations

## Data Formats

### Product.txt Format
```
productID,productName,manufacturerName,supplierName,quantity,price
P2394,Laptop,Apple,TechSupplier,10,500.0
```

### Employees.txt Format
```
employeeId,name,email,address,phoneNumber
E1200,Ahmed,ahmed_1999@gmail.com,Alexandria,01088877345
```

### Customer.txt Format
```
customerSSN,productID,purchaseDate,paid
7845345678,P2394,19-10-2025,true
```

## Key Features

### Search Key Implementation
- **Product**: Uses `productID` as search key
- **EmployeeUser**: Uses `employeeId` as search key
- **CustomerProduct**: Uses composite key `customerSSN,productID,dd-MM-yyyy`

### Error Handling
- Null pointer exception prevention
- File I/O error handling
- Input validation for user interactions
- Proper date format validation (dd-MM-yyyy)

### Data Integrity
- Prevents duplicate records
- Proper file overwriting (no data corruption)
- Consistent search key usage across all operations

## How to Run

1. **Compile the project:**
   ```bash
   javac *.java
   ```

2. **Run the comprehensive test suite:**
   ```bash
   java main
   ```

3. **Test Results:**
   - The application runs a complete test of all functionality
   - Tests AdminRole operations (add/view/remove employees)
   - Tests EmployeeRole operations (products, purchases, returns, payments)
   - Verifies all database operations and search functionality
   - Demonstrates data persistence and file operations

## Usage Examples

### Running the Test Suite
The `main.java` file contains a comprehensive test suite that demonstrates all functionality:

```bash
javac *.java
java main
```

### Test Coverage
The test suite verifies:
- **AdminRole**: Employee management (add, view, remove, search)
- **EmployeeRole**: Product management (add, view, delete, search)
- **Customer Operations**: Purchase processing, payment application, returns
- **Database Operations**: All search key functionality and data persistence
- **Error Handling**: Null checks and proper data validation

### Sample Test Output
```
=== Inventory Management System Full Test ===

--- AdminRole Tests ---
List of Employees:
E1200,Ahmed,ahmed_1999@gmail.com,Alexandria,01088877345
E1201,Sara,sara@gmail.com,Cairo,01122334455
...

--- EmployeeRole Tests ---
List of Products:
P2394,Laptop,Apple,TechSupplier,9,500.0
P2395,Phone,Samsung,MobileWorld,5,700.0
...

Purchase Product P2394: Success
Payment applied for purchase on 2025-10-20: true
Return Product P2394: Refunded 500.0

=== All Tests Completed Successfully ===
```

## Technical Details

### Design Patterns
- **Template Method Pattern**: `Database<T>` abstract class
- **Strategy Pattern**: Role-based functionality (`AdminRole`, `EmployeeRole`)
- **Generic Programming**: Type-safe database operations

### File Operations
- **Reading**: Buffered file reading with error handling
- **Writing**: PrintWriter with proper file overwriting
- **Parsing**: CSV format with trim() and validation

### Date Handling
- Uses `LocalDate` for purchase and return dates
- Consistent `dd-MM-yyyy` format throughout the system
- 14-day return policy validation

## Error Prevention

- **Null Checks**: All database operations include null validation
- **File Safety**: Proper resource management with try-with-resources
- **Data Validation**: Input validation for all user interactions
- **Search Consistency**: Proper search key usage prevents data mismatches

## Test Results

The system has been thoroughly tested and all functionality is working correctly:

### ✅ **AdminRole Tests - All Passed**
- Employee addition, viewing, and removal
- Database search operations (`contains()`, `getRecord()`)
- Data persistence to `Employees.txt`

### ✅ **EmployeeRole Tests - All Passed**
- Product addition, viewing, and deletion
- Customer purchase processing
- Payment application and return processing
- Database search operations
- Data persistence to `Product.txt` and `Customer.txt`

### ✅ **System Verification**
- **Compilation**: No errors
- **Runtime**: No crashes
- **Data Integrity**: No corruption
- **Search Consistency**: All search keys working correctly
- **Error Handling**: Null checks preventing crashes

## Future Enhancements

- User authentication system
- Advanced reporting features
- Inventory alerts for low stock
- Customer purchase history
- Export functionality for reports

---

**Author**: [Your Name]  
**Course**: Programming 2  
**Lab**: Lab 4 - Inventory Management System  
**Date**: [Current Date]
