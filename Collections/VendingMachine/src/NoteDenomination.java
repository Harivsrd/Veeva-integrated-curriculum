public enum NoteDenomination {
    ONE_HUNDRED(100), TWO_HUNDRED(200), FIVE_HUNDRED(500), TWO_THOUSAND(2000);

    private final int value;
    NoteDenomination(int value) {
        this.value = value;
    }
    public int getValue() {
        return value;
    }
}
