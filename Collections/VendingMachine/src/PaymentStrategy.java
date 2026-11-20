public interface PaymentStrategy {
    boolean pay(int amount);
    String getDescription();
}
