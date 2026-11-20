import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class VendingMachine {
    private final Map<String, Product> productCatalog = new LinkedHashMap<>();
    private final Inventory cashInventory;
    private Product selectedItem = null;
    private int currentBalance = 0;
    private boolean transactionActive = false;

    public VendingMachine() {
        this.cashInventory = new Inventory();
        initializeCatalog();
    }

    private void initializeCatalog() {
        productCatalog.put("Soda", new Product("Soda",20,10));
        productCatalog.put("Chips", new Product("Chips", 15,15));
        productCatalog.put("Candy", new Product("Candy", 10, 20));
        productCatalog.put("Drink", new Product("Drink", 25, 12));
        productCatalog.put("Chocolate", new Product("Chocolate", 35, 8));
    }

    public void displayCatalog() {
        System.out.println("\n╔══════════════════════════════════════════╗");
        System.out.println("║        VENDING MACHINE – MENU          ║");
        System.out.println("╠══════════════════════════════════════════╣");
        productCatalog.values().forEach(p ->
                System.out.println("║  " + p + "║"));
        System.out.println("╚══════════════════════════════════════════╝");
    }

    public void selectProduct(String name) throws SoldOutException {
        Product p = productCatalog.get(name);
        if(p ==  null) throw new SoldOutException("Product '" + name + "' not found in catalog.");
        if(!p.isAvailable()) throw new SoldOutException("'" + name + "' is SOLD OUT");
        selectedItem = p;
        transactionActive=true;
        System.out.print("Selected: "+p.getName() + "(₹" + p.getPrice() + ")");
    }

    public void insertCoin(CoinDenomination coin) {
        cashInventory.addCoin(coin);
        currentBalance += coin.getValue();
        System.out.println("  + Coin ₹" + coin.getValue() + " inserted. Balance: ₹" + currentBalance);
    }

    public void insertNote(NoteDenomination note) {
        cashInventory.addNote(note);
        currentBalance += note.getValue();
        System.out.println("  + Note ₹" + note.getValue() + " inserted. Balance: ₹" + currentBalance);
    }

    public void payViaUPI() throws InsufficientFundsException {
        if (selectedItem == null) throw new InsufficientFundsException("No product selected.");
        int price = selectedItem.getPrice();
        System.out.println("  → UPI Payment of ₹" + price + " successful.");
        currentBalance = price;
    }

    public void addBalance(int amt) {
        currentBalance += amt;
    }
    public int getCurrentBalance() {
        return currentBalance;
    }
    public Product getSelectedProduct() {
        return selectedItem;
    }

    public void dispense() throws InsufficientFundsException, InsufficientChangeException {
        if(selectedItem == null) throw new InsufficientFundsException("No product selected.");

        int price = selectedItem.getPrice();

        if(currentBalance < price) {
            throw new InsufficientFundsException("Insufficient funds! You inserted ₹" + currentBalance + " but need ₹" + price + ". Short by ₹" + (price - currentBalance) + ".");
        }

        int change = currentBalance - price;
        List<String> changeList = cashInventory.makeChange(change);

        selectedItem.decrementStock();
        System.out.println("\n Dispensing: " + selectedItem.getName());
        if(change > 0) {
            System.out.println("Change returned: "+changeList);
        }
        else  {
            System.out.println(" No change due");
        }

        currentBalance = 0;
        selectedItem = null;
        transactionActive = false;
    }

    public void cancelTransaction() {
        if(!transactionActive) {
            System.out.println("No active transaction to cancel! ");
        }
        System.out.println("--Transaction CANCELLED --");
        if(currentBalance > 0) {
            System.out.println("Refund of "+currentBalance+ " is issued.");
        }
        currentBalance = 0;
        selectedItem = null;
        transactionActive = false;
    }

    public void printStats() {
        System.out.println("\n--- Product Stock ---");
        productCatalog.values().forEach(System.out::println);
        cashInventory.printCashInventory();
    }


}
