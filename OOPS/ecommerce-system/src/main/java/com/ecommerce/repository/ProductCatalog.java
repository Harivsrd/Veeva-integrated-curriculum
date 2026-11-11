package com.ecommerce.repository;

import com.ecommerce.model.Product;
import java.util.*;
import java.util.stream.Collectors;

public class ProductCatalog {
    private Map<String, Product> catalog = new HashMap<>();

    public void addProduct(Product product) {
        catalog.put(product.getProductId(), product);
    }

    public void removeProduct(String productId) {
        catalog.remove(productId);
    }

    public void updateProduct(Product product) {
        if (catalog.containsKey(product.getProductId())) {
            catalog.put(product.getProductId(), product);
        }
    }

    // Queries requested in Requirement 3
    // i. Product ID given -> return corresponding product details
    public Product getProductById(String id) {
        return catalog.get(id);
    }

    // ii. Product name given -> return list of products matching name
    public List<Product> findByName(String name) {
        return catalog.values().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name))
                .collect(Collectors.toList());
    }

    // iii. Product name and price given -> return products matching those
    public List<Product> findByNameAndPrice(String name, double price) {
        return catalog.values().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name) && p.getPrice() == price)
                .collect(Collectors.toList());
    }

    // iv. Product name and range of price given -> return list of products within range
    public List<Product> findByNameAndPriceRange(String name, double minPrice, double maxPrice) {
        return catalog.values().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name) && p.getPrice() >= minPrice && p.getPrice() <= maxPrice)
                .collect(Collectors.toList());
    }

    // v. Product name and company name given -> return list of products by company
    public List<Product> findByNameAndCompany(String name, String companyName) {
        return catalog.values().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name) && p.getCompanyName().equalsIgnoreCase(companyName))
                .collect(Collectors.toList());
    }

    // vi. Product name, company name, and price given -> return products matching those
    public List<Product> findByNameCompanyAndPrice(String name, String companyName, double price) {
        return catalog.values().stream()
                .filter(p -> p.getName().equalsIgnoreCase(name) && 
                             p.getCompanyName().equalsIgnoreCase(companyName) && 
                             p.getPrice() == price)
                .collect(Collectors.toList());
    }

    public List<Product> getAllProducts() {
        return new ArrayList<>(catalog.values());
    }
}
