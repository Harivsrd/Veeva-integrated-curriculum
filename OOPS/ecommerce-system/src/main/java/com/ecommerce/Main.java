package com.ecommerce;

import com.ecommerce.model.*;
import com.ecommerce.payment.*;
import com.ecommerce.service.EcommercePlatform;
import com.ecommerce.service.ShoppingCart;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        EcommercePlatform platform = new EcommercePlatform();
        Scanner scanner = new Scanner(System.in);

        // Prepopulate some initial catalog and customers for testing
        seedData(platform);

        Customer loggedInCustomer = null;
        boolean running = true;

        System.out.println("==================================================");
        System.out.println("   WELCOME TO THE E-COMMERCE MANAGEMENT PLATFORM  ");
        System.out.println("==================================================");

        while (running) {
            if (loggedInCustomer == null) {
                printMainMenu();
                System.out.print("Enter your choice: ");
                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        loggedInCustomer = handleLogin(platform, scanner);
                        break;
                    case "2":
                        handleRegistration(platform, scanner);
                        break;
                    case "3":
                        handleProductExplorationAndQueries(platform, scanner);
                        break;
                    case "4":
                        handleCustomerPoolQueries(platform, scanner);
                        break;
                    case "5":
                        System.out.println("Thank you for using our e-commerce platform. Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice! Please enter a number between 1 and 5.");
                }
            } else {
                printCustomerMenu(loggedInCustomer);
                System.out.print("Enter your choice: ");
                String choice = scanner.nextLine().trim();

                switch (choice) {
                    case "1":
                        handleProductExplorationAndQueries(platform, scanner);
                        break;
                    case "2":
                        handleShoppingCart(platform, loggedInCustomer, scanner);
                        break;
                    case "3":
                        handleCheckout(platform, loggedInCustomer, scanner);
                        break;
                    case "4":
                        handleOrderTracking(platform, scanner);
                        break;
                    case "5":
                        System.out.println("Logged out successfully: " + loggedInCustomer.getName());
                        loggedInCustomer = null;
                        break;
                    case "6":
                        System.out.println("Thank you for using our e-commerce platform. Goodbye!");
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid choice! Please enter a number between 1 and 6.");
                }
            }
            System.out.println();
        }
        scanner.close();
    }

    private static void seedData(EcommercePlatform platform) {
        // Products
        platform.getCatalog().addProduct(new Product("P101", "Laptop", 1200.00, "TechCorp", 10));
        platform.getCatalog().addProduct(new Product("P102", "Smartphone", 800.00, "TechCorp", 25));
        platform.getCatalog().addProduct(new Product("P103", "Smartphone", 450.00, "BudgetMobiles", 15));
        platform.getCatalog().addProduct(new Product("P104", "Headphones", 150.00, "SoundMaster", 50));
        platform.getCatalog().addProduct(new Product("P105", "Smartwatch", 250.00, "TechCorp", 30));

        // Customers
        Address addr1 = new Address("123 Main St", "New York", "NY", "10001");
        platform.registerCustomer("C001", "Alice Smith", "alice@example.com", "5550100", addr1, "password123");

        Address addr2 = new Address("452 Park Ave", "Sydney", "NSW", "2000");
        platform.registerCustomer("C002", "Bob Jones", "bob@example.com", "5550200", addr2, "password123");
    }

    private static void printMainMenu() {
        System.out.println("\n--- MAIN MENU ---");
        System.out.println("1. Login");
        System.out.println("2. Register New Customer");
        System.out.println("3. Explore Product Catalog & Queries");
        System.out.println("4. Query Customer Database (Admin)");
        System.out.println("5. Exit");
    }

    private static void printCustomerMenu(Customer customer) {
        System.out.println("\n--- CUSTOMER DASHBOARD (" + customer.getName() + ") ---");
        System.out.println("1. Browse / Search Products");
        System.out.println("2. Manage Shopping Cart / Basket");
        System.out.println("3. Checkout & Place Order");
        System.out.println("4. Track Order Status & Shipping Details");
        System.out.println("5. Logout");
        System.out.println("6. Exit Platform");
    }

    private static Customer handleLogin(EcommercePlatform platform, Scanner scanner) {
        System.out.println("\n=== CUSTOMER LOGIN ===");
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();

        return platform.login(email, password);
    }

    private static void handleRegistration(EcommercePlatform platform, Scanner scanner) {
        System.out.println("\n=== CUSTOMER REGISTRATION ===");
        System.out.print("Enter Customer ID (e.g., C003): ");
        String id = scanner.nextLine().trim();
        System.out.print("Enter Full Name: ");
        String name = scanner.nextLine().trim();
        System.out.print("Enter Email: ");
        String email = scanner.nextLine().trim();
        System.out.print("Enter Phone Number: ");
        String phone = scanner.nextLine().trim();
        
        System.out.print("Enter Street Address: ");
        String street = scanner.nextLine().trim();
        System.out.print("Enter City: ");
        String city = scanner.nextLine().trim();
        System.out.print("Enter State: ");
        String state = scanner.nextLine().trim();
        System.out.print("Enter Zip Code: ");
        String zip = scanner.nextLine().trim();

        System.out.print("Enter Password: ");
        String password = scanner.nextLine().trim();

        Address address = new Address(street, city, state, zip);
        platform.registerCustomer(id, name, email, phone, address, password);
    }

    private static void handleProductExplorationAndQueries(EcommercePlatform platform, Scanner scanner) {
        boolean exploring = true;
        while (exploring) {
            System.out.println("\n--- PRODUCT CATALOG & SEARCH MENU ---");
            System.out.println("1. View All Products");
            System.out.println("2. Query by Product ID");
            System.out.println("3. Query by Product Name");
            System.out.println("4. Query by Product Name & Exact Price");
            System.out.println("5. Query by Product Name & Price Range");
            System.out.println("6. Query by Product Name & Company Name");
            System.out.println("7. Query by Product Name, Company Name & Price");
            System.out.println("8. Return to Previous Menu");
            System.out.print("Choose query option (1-8): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    List<Product> all = platform.getCatalog().getAllProducts();
                    printProductList(all);
                    break;
                case "2":
                    System.out.print("Enter Product ID: ");
                    String id = scanner.nextLine().trim();
                    Product p = platform.getCatalog().getProductById(id);
                    if (p != null) {
                        System.out.println("Found: " + p);
                    } else {
                        System.out.println("No product found with ID: " + id);
                    }
                    break;
                case "3":
                    System.out.print("Enter Product Name: ");
                    String name = scanner.nextLine().trim();
                    printProductList(platform.getCatalog().findByName(name));
                    break;
                case "4":
                    System.out.print("Enter Product Name: ");
                    String pName = scanner.nextLine().trim();
                    System.out.print("Enter Price: ");
                    double price = Double.parseDouble(scanner.nextLine().trim());
                    printProductList(platform.getCatalog().findByNameAndPrice(pName, price));
                    break;
                case "5":
                    System.out.print("Enter Product Name: ");
                    String rangeName = scanner.nextLine().trim();
                    System.out.print("Enter Minimum Price: ");
                    double min = Double.parseDouble(scanner.nextLine().trim());
                    System.out.print("Enter Maximum Price: ");
                    double max = Double.parseDouble(scanner.nextLine().trim());
                    printProductList(platform.getCatalog().findByNameAndPriceRange(rangeName, min, max));
                    break;
                case "6":
                    System.out.print("Enter Product Name: ");
                    String compNameQuery = scanner.nextLine().trim();
                    System.out.print("Enter Company Name: ");
                    String company = scanner.nextLine().trim();
                    printProductList(platform.getCatalog().findByNameAndCompany(compNameQuery, company));
                    break;
                case "7":
                    System.out.print("Enter Product Name: ");
                    String exactName = scanner.nextLine().trim();
                    System.out.print("Enter Company Name: ");
                    String exactComp = scanner.nextLine().trim();
                    System.out.print("Enter Price: ");
                    double exactPrice = Double.parseDouble(scanner.nextLine().trim());
                    printProductList(platform.getCatalog().findByNameCompanyAndPrice(exactName, exactComp, exactPrice));
                    break;
                case "8":
                    exploring = false;
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void printProductList(List<Product> products) {
        if (products.isEmpty()) {
            System.out.println("No products found matching criteria.");
        } else {
            System.out.println("--- Results (" + products.size() + " found) ---");
            for (Product p : products) {
                System.out.println("  " + p);
            }
        }
    }

    private static void handleCustomerPoolQueries(EcommercePlatform platform, Scanner scanner) {
        boolean querying = true;
        while (querying) {
            System.out.println("\n--- CUSTOMER DATABASE QUERIES (ADMIN) ---");
            System.out.println("1. View All Customers");
            System.out.println("2. Query Customer by Name");
            System.out.println("3. Query Customer by City");
            System.out.println("4. Query Customer by Phone Number");
            System.out.println("5. Query Customer by Email");
            System.out.println("6. Return to Main Menu");
            System.out.print("Choose option (1-6): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    List<Customer> customers = platform.getCustomerDb().getAllCustomers();
                    customers.forEach(c -> System.out.println("  " + c));
                    break;
                case "2":
                    System.out.print("Enter Customer Name: ");
                    String name = scanner.nextLine().trim();
                    List<Customer> byName = platform.getCustomerDb().findByName(name);
                    byName.forEach(c -> System.out.println("  " + c));
                    break;
                case "3":
                    System.out.print("Enter City Name: ");
                    String city = scanner.nextLine().trim();
                    List<Customer> byCity = platform.getCustomerDb().findByCity(city);
                    byCity.forEach(c -> System.out.println("  " + c));
                    break;
                case "4":
                    System.out.print("Enter Phone Number: ");
                    String phone = scanner.nextLine().trim();
                    Customer cPhone = platform.getCustomerDb().findByPhone(phone);
                    System.out.println(cPhone != null ? "  " + cPhone : "No customer found.");
                    break;
                case "5":
                    System.out.print("Enter Email: ");
                    String email = scanner.nextLine().trim();
                    Customer cEmail = platform.getCustomerDb().findByEmail(email);
                    System.out.println(cEmail != null ? "  " + cEmail : "No customer found.");
                    break;
                case "6":
                    querying = false;
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void handleShoppingCart(EcommercePlatform platform, Customer customer, Scanner scanner) {
        ShoppingCart cart = platform.getCartForCustomer(customer.getCustomerId());
        boolean managing = true;
        while (managing) {
            System.out.println("\n--- SHOPPING CART / BASKET ---");
            System.out.println("1. View Cart Items & Subtotal");
            System.out.println("2. Add Product to Cart (by Product ID)");
            System.out.println("3. Remove Product from Cart (by Product ID)");
            System.out.println("4. Clear Cart");
            System.out.println("5. Return to Dashboard");
            System.out.print("Choose option (1-5): ");
            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    List<Product> items = cart.getItems();
                    if (items.isEmpty()) {
                        System.out.println("Your shopping cart is empty.");
                    } else {
                        System.out.println("Items in cart:");
                        for (Product p : items) {
                            System.out.println("  - " + p);
                        }
                        System.out.printf("Subtotal: $%.2f%n", cart.calculateSubtotal());
                    }
                    break;
                case "2":
                    System.out.print("Enter Product ID to add: ");
                    String pId = scanner.nextLine().trim();
                    Product p = platform.getCatalog().getProductById(pId);
                    if (p != null) {
                        cart.addProduct(p);
                    } else {
                        System.out.println("Invalid Product ID.");
                    }
                    break;
                case "3":
                    System.out.print("Enter Product ID to remove: ");
                    String remId = scanner.nextLine().trim();
                    Product target = cart.getItems().stream().filter(item -> item.getProductId().equalsIgnoreCase(remId)).findFirst().orElse(null);
                    if (target != null) {
                        cart.removeProduct(target);
                    } else {
                        System.out.println("Product not found in your cart.");
                    }
                    break;
                case "4":
                    cart.clear();
                    System.out.println("Cart cleared.");
                    break;
                case "5":
                    managing = false;
                    break;
                default:
                    System.out.println("Invalid option!");
            }
        }
    }

    private static void handleCheckout(EcommercePlatform platform, Customer customer, Scanner scanner) {
        ShoppingCart cart = platform.getCartForCustomer(customer.getCustomerId());
        if (cart.isEmpty()) {
            System.out.println("Your cart is empty. Add products before checkout.");
            return;
        }

        System.out.println("\n--- CHECKOUT & PAYMENT ---");
        System.out.printf("Subtotal: $%.2f%n", cart.calculateSubtotal());
        System.out.print("Enter Shipping Charge: $");
        double shipping = Double.parseDouble(scanner.nextLine().trim());
        double total = cart.calculateSubtotal() + shipping;
        System.out.printf("Total Amount (Order Amount + Shipping): $%.2f%n", total);

        System.out.println("Select Payment Mode:");
        System.out.println("1. UPI");
        System.out.println("2. Credit/Debit Card");
        System.out.println("3. Cash on Delivery (COD)");
        System.out.print("Enter payment choice (1-3): ");
        String pChoice = scanner.nextLine().trim();

        Payment payment = null;
        switch (pChoice) {
            case "1":
                System.out.print("Enter UPI ID (e.g. user@oksbi): ");
                String upiId = scanner.nextLine().trim();
                payment = new UpiPayment(upiId);
                break;
            case "2":
                System.out.print("Enter Card Number (16 digits): ");
                String cardNo = scanner.nextLine().trim();
                System.out.print("Enter Cardholder Name: ");
                String cardHolder = scanner.nextLine().trim();
                payment = new CardPayment(cardNo, cardHolder);
                break;
            case "3":
                payment = new CodPayment();
                break;
            default:
                System.out.println("Invalid payment option selected. Checkout aborted.");
                return;
        }

        System.out.print("Enter new Order ID (e.g., ORD-3001): ");
        String orderId = scanner.nextLine().trim();

        Order order = platform.checkoutOrder(orderId, customer, shipping, payment);
        if (order != null) {
            System.out.println();
            order.printSummary();
        }
    }

    private static void handleOrderTracking(EcommercePlatform platform, Scanner scanner) {
        System.out.println("\n--- ORDER TRACKING & STATUS MANAGEMENT ---");
        System.out.print("Enter Order ID: ");
        String orderId = scanner.nextLine().trim();
        Order order = platform.getOrder(orderId);

        if (order == null) {
            System.out.println("Order not found with ID: " + orderId);
            return;
        }

        order.printSummary();

        System.out.print("\nDo you want to update the order status (Admin simulation)? (y/n): ");
        String ans = scanner.nextLine().trim();
        if (ans.equalsIgnoreCase("y")) {
            System.out.println("Select New Status:");
            System.out.println("1. CREATED_AND_RECEIVED");
            System.out.println("2. DISPATCHED");
            System.out.println("3. COURIER_AGENT_IN_TRANSIT");
            System.out.println("4. DELIVERED");
            System.out.println("5. RETURNED_AND_RECEIVED");
            System.out.print("Choose status (1-5): ");
            String sChoice = scanner.nextLine().trim();

            OrderStatus status = OrderStatus.CREATED_AND_RECEIVED;
            switch (sChoice) {
                case "1": status = OrderStatus.CREATED_AND_RECEIVED; break;
                case "2": status = OrderStatus.DISPATCHED; break;
                case "3": status = OrderStatus.COURIER_AGENT_IN_TRANSIT; break;
                case "4": status = OrderStatus.DELIVERED; break;
                case "5": status = OrderStatus.RETURNED_AND_RECEIVED; break;
                default: System.out.println("Invalid status choice."); return;
            }

            System.out.print("Enter tracking details / courier note: ");
            String trackingNote = scanner.nextLine().trim();

            order.updateStatus(status, trackingNote);
            System.out.println("Order status successfully updated!");
            order.printSummary();
        }
    }
}
