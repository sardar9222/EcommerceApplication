package org.example.model.product;

public class Product {
    private String productId;
    private String name;
    private double price;
    private String description;
    private int stockQuantity;

    public Product(String productId, String name, double price, String description, int stockQuantity) {
        this.productId = productId;
        this.name = name;
        this.price = price;
        this.description = description;
        this.stockQuantity = stockQuantity;
    }

    public String getProductId() {
        return productId;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public void updatePrice(double newPrice) {
        this.price = newPrice;
        System.out.println("Price updated for " + name + " to $" + newPrice);
    }
}
