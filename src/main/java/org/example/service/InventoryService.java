package org.example.service;

import org.example.model.product.Product;
import org.example.repository.ProductRepository;

import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class InventoryService {
    private Map<String, Product> inventory = new HashMap<>();

    public void addProduct(Product product) {
        inventory.put(product.getProductId(), product);
    }

    public void updateStock(Product product, int quantity) {
        product.setStockQuantity(product.getStockQuantity() - quantity);
        System.out.println("Updated stock for " + product.getName() + ": " + product.getStockQuantity());
    }

    public boolean checkStock(Product product, int quantity) {
        return product.getStockQuantity() >= quantity;
    }

    public void checkLowStock() {
        inventory.values().stream()
                .filter(p -> p.getStockQuantity() < 5)
                .forEach(p -> System.out.println("Low stock alert: " + p.getName() + " (Stock: " + p.getStockQuantity() + ")"));
    }
}
