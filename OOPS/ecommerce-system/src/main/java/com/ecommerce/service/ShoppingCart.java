package com.ecommerce.service;

import com.ecommerce.model.Product;
import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    private List<Product> items = new ArrayList<>();

    public void addProduct(Product product) {
        items.add(product);
        System.out.println("Added to basket: " + product.getName());
    }

    public void removeProduct(Product product) {
        items.remove(product);
        System.out.println("Removed from basket: " + product.getName());
    }

    public List<Product> getItems() {
        return items;
    }

    public void clear() {
        items.clear();
    }

    public double calculateSubtotal() {
        return items.stream().mapToDouble(Product::getPrice).sum();
    }

    public boolean isEmpty() {
        return items.isEmpty();
    }
}
