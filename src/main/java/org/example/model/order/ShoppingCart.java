package org.example.model.order;


import org.example.model.product.Product;

import java.util.ArrayList;
import java.util.List;



public class ShoppingCart {
    private List<CartItem> items;
    private double totalAmount;

    public ShoppingCart() {
        this.items = new ArrayList<>();
        this.totalAmount = 0.0;
    }

    public List<CartItem> getItems() {
        return items;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void addItem(CartItem item) {
        items.add(item);
        calculateTotal();
        System.out.println("Added " + item.getQuantity() + " of " + item.getProduct().getName() + " to cart.");
    }

    public void removeItem(CartItem item) {
        items.remove(item);
        calculateTotal();
        System.out.println("Removed " + item.getProduct().getName() + " from cart.");
    }

    public void calculateTotal() {
        totalAmount = items.stream().mapToDouble(CartItem::getSubtotal).sum();
    }

    public void clear() {
        items.clear();
        totalAmount = 0.0;
    }
}


