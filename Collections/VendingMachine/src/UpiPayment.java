public class UpiPayment implements PaymentStrategy{
    private VendingMachine machine;

    public UpiPayment(VendingMachine machine) {
        this.machine = machine;
    }

    @Override
    public boolean pay(int amount) {
        //
        System.out.println("-> Generating UPI QR/ payment of ₹"+amount);
        System.out.println("Payment Successful!");
        machine.addBalance(amount);
        return true;
    }

    @Override
    public String getDescription() {
        return "UPI (gpay/phonepay/paytm)";
    }
}
