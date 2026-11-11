package com.ecommerce.model;

public class Product {
    private String productId;
    private String name;
    private double price;
    private String companyName;
    private int stockQuantity;

    public Product(String productId, String name, double price, String companyName, int stockQuantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.companyName = companyName;
        this.stockQuantity = stockQuantity;
    }

    public String getProductId() { return productId; }
    public void setProductId(String productId) { this.productId = productId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public int getStockQuantity() { return stockQuantity; }
    public void setStockQuantity(int stockQuantity) { this.stockQuantity = stockQuantity; }

    public boolean reduceStock(int quantity) {
        if (stockQuantity >= quantity) {
            stockQuantity -= quantity;
            return true;
        }
        return false;
    }

    @Override
    public String toString() {
        return String.format("[%s] %s by %s - $%.2f (Stock: %d)", productId, name, companyName, price, stockQuantity);
    }
}
