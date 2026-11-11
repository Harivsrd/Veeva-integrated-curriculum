class LowQuantityException extends Exception {
    public LowQuantityException(String message) {
        super(message);
    }
}

class Product {
    private String prId;
    private String name;
    private double price;
    private String dateOfManufacture;
    private int quantityInHand;
    private String companyName;

    public Product(String prId, String name, double price, String dateOfManufacture, int quantityInHand,
            String companyName) throws LowQuantityException {
        if (quantityInHand < 100) {
            throw new LowQuantityException("Error: Product '" + name + "' (ID: " + prId + ") has quantity "
                    + quantityInHand + " (< 100). Cannot be added to storage!");
        }
        this.prId = prId;
        this.name = name;
        this.price = price;
        this.dateOfManufacture = dateOfManufacture;
        this.quantityInHand = quantityInHand;
        this.companyName = companyName;
    }

    public String getPrId() {
        return prId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDateOfManufacture() {
        return dateOfManufacture;
    }

    public int getQuantityInHand() {
        return quantityInHand;
    }

    public String getCompanyName() {
        return companyName;
    }

    @Override
    public String toString() {
        return "Product [ID=" + prId + ", Name=" + name + ", Price=" + price +
                ", DOM=" + dateOfManufacture + ", Qty=" + quantityInHand +
                ", Company=" + companyName + "]";
    }
}