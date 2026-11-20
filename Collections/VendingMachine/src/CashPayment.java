public class CashPayment implements PaymentStrategy{

    private VendingMachine machine;
    private int totalInserted = 0;

    public CashPayment(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public boolean pay(int amount) {
        totalInserted += amount;
        machine.addBalance(amount);
        return machine.getCurrentBalance() >= machine.getSelectedProduct().getPrice();
    }

    @Override
    public String getDescription() {
        return "Cash (Coins/Notes)";
    }
}
