import java.util.ArrayList;
import java.util.List;

public class ProductManagementApp {
    private static List<Product> productList = new ArrayList<>();

    public static void addProduct(String prId, String name, double price, String dom, int qty, String company) {
        try {
            Product p = new Product(prId, name, price, dom, qty, company);
            productList.add(p);
            System.out.println("Successfully added: " + name);
        } catch (LowQuantityException e) {
            System.out.println("Exception Caught -> " + e.getMessage());
        }
    }

    public static void sortByPriceDescending() {
        productList.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
        System.out.println("\n--- Products Sorted by Price (Descending) ---");
        displayAll();
    }

    public static void sortByPriceAndDateDescending() {
        productList.sort((p1, p2) -> {
            int priceCompare = Double.compare(p2.getPrice(), p1.getPrice());
            if (priceCompare != 0) {
                return priceCompare;
            }
            return p2.getDateOfManufacture().compareTo(p1.getDateOfManufacture());
        });
        System.out.println("\n--- Products Sorted by Price & Date of Manufacture (Descending) ---");
        displayAll();
    }

    public static void queryByName(String searchName) {
        System.out.println("\n--- Query 1: Search by Product Name ('" + searchName + "') ---");
        boolean found = false;
        for (Product p : productList) {
            if (p.getName().equalsIgnoreCase(searchName)) {
                System.out.println(p);
                found = true;
            }
        }
        if (!found)
            System.out.println("No products found with this name.");
    }

    public static void queryByDateOfManufacture(String searchDate) {
        System.out.println("\n--- Query 2: Search by Date of Manufacture ('" + searchDate + "') ---");
        boolean found = false;
        for (Product p : productList) {
            if (p.getDateOfManufacture().equals(searchDate)) {
                System.out.println(p);
                found = true;
            }
        }
        if (!found)
            System.out.println("No products found for this date.");
    }

    public static void queryByPriceAndCompany(double searchPrice, String searchCompany) {
        System.out.println(
                "\n--- Query 3: Search by Price (" + searchPrice + ") & Company ('" + searchCompany + "') ---");
        boolean found = false;
        for (Product p : productList) {
            if (p.getPrice() == searchPrice && p.getCompanyName().equalsIgnoreCase(searchCompany)) {
                System.out.println(p);
                found = true;
            }
        }
        if (!found)
            System.out.println("No matching products found.");
    }

    public static void getTopKProducts(int k) {
        System.out.println("\n--- Query 4: Top " + k + " Products (Highest Price) ---");
        List<Product> temp = new ArrayList<>(productList);
        temp.sort((p1, p2) -> Double.compare(p2.getPrice(), p1.getPrice()));
        for (int i = 0; i < Math.min(k, temp.size()); i++) {
            System.out.println(temp.get(i));
        }
    }

    public static void getLeastKProducts(int k) {
        System.out.println("\n--- Query 5: Least " + k + " Products (Lowest Price) ---");
        List<Product> temp = new ArrayList<>(productList);
        temp.sort((p1, p2) -> Double.compare(p1.getPrice(), p2.getPrice()));
        for (int i = 0; i < Math.min(k, temp.size()); i++) {
            System.out.println(temp.get(i));
        }
    }

    public static void displayAll() {
        for (Product p : productList) {
            System.out.println(p);
        }
    }

    public static void main(String[] args) {
        System.out.println("=== Starting Product Application ===");


        addProduct("P001", "Laptop", 75000.0, "2026-01-10", 150, "Dell");
        addProduct("P002", "Mouse", 1200.0, "2026-02-15", 50, "Logitech"); // Throws LowQuantityException
        addProduct("P003", "Keyboard", 2500.0, "2026-01-10", 120, "Logitech");
        addProduct("P004", "Monitor", 18000.0, "2026-03-01", 200, "Dell");
        addProduct("P005", "Headphones", 3000.0, "2026-02-15", 110, "Sony");

        sortByPriceDescending();
        sortByPriceAndDateDescending();

        queryByName("Laptop");
        queryByDateOfManufacture("2026-01-10");
        queryByPriceAndCompany(18000.0, "Dell");

        getTopKProducts(2);
        getLeastKProducts(2);
    }
}
