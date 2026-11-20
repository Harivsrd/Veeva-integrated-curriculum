import java.util.*;

public class Inventory {
    private Map<CoinDenomination, Integer> coinStock = new EnumMap<>(CoinDenomination.class);
    private Map<NoteDenomination, Integer> noteStock = new EnumMap<>(NoteDenomination.class);

    public Inventory() {
        Arrays.stream(CoinDenomination.values())
                .forEach(c -> coinStock.put(c, 20));
        Arrays.stream(NoteDenomination.values())
                .forEach( n -> noteStock.put(n, 10));
    }

    public boolean hasCoin(CoinDenomination c) {
        return coinStock.getOrDefault(c, 0) > 0;
    }
    public boolean hasNote(NoteDenomination n) {
        return noteStock.getOrDefault(n, 0) > 0;
    }
    public void addCoin(CoinDenomination c) {
        coinStock.put(c, coinStock.getOrDefault(c,0) + 1);
    }
    public void addNote(NoteDenomination n) {
        noteStock.put(n, noteStock.getOrDefault(n,0) + 1);
    }
    public void removeCoin(CoinDenomination c) {
        coinStock.put(c, coinStock.getOrDefault(c,0) - 1);
    }
    public void removeNote(NoteDenomination n) {
        noteStock.put(n, noteStock.getOrDefault(n,0) - 1);
    }

    public List<String> makeChange(int amount) throws InsufficientChangeException {
        List<String> change = new ArrayList<>();
        int remaining = amount;

        for(NoteDenomination note : Arrays.stream(NoteDenomination.values())
                .sorted(Comparator.comparingInt(NoteDenomination::getValue).reversed()).toArray(NoteDenomination[]::new)){
            while (remaining >= note.getValue() && hasNote(note)) {
                removeNote(note);
                change.add("₹"+note.getValue()+" note");
                remaining -= note.getValue();
            }
        }

        for(CoinDenomination coin : Arrays.stream(CoinDenomination.values())
                .sorted(Comparator.comparingInt(CoinDenomination::getValue).reversed()).toArray(CoinDenomination[]::new)){
            while (remaining >= coin.getValue() && hasCoin(coin)) {
                removeCoin(coin);
                change.add("₹"+coin.getValue()+" note");
                remaining -= coin.getValue();
            }
        }

        if(remaining > 0) {
            throw new InsufficientChangeException(
                    "No sufficient change available. Cannot make ₹"+remaining+" change"
            );
        }
        return  change;
    }

    public void printCashInventory() {
        System.out.println("\n--- Cash Inventory ---");
        coinStock.forEach((d, q) -> System.out.printf("  Coin ₹%d : %d%n", d.getValue(), q));
        noteStock.forEach((d, q) -> System.out.printf("  Note ₹%d : %d%n", d.getValue(), q));
    }
}
