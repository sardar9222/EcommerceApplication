package org.example.model.user;


import org.example.model.product.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Seller extends User {
    private List<Product> inventory;

    public Seller(String userId, String username, String email, String password) {
        super(userId, username, email, password);
        this.inventory = new ArrayList<>();
    }

    public List<Product> getInventory() {
        return inventory;
    }

    public void addProduct(Product product) {
        inventory.add(product);
        System.out.println("Product added to inventory: " + product.getName());
    }

    public void updateInventory() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Your inventory:");
        for (int i = 0; i < inventory.size(); i++) {
            Product p = inventory.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " (Stock: " + p.getStockQuantity() + ")");
        }
        System.out.print("Select product to update stock (or 0 to cancel): ");
        int choice = scanner.nextInt();
        if (choice == 0) return;
        if (choice > 0 && choice <= inventory.size()) {
            Product product = inventory.get(choice - 1);
            System.out.print("Enter new stock quantity: ");
            int newStock = scanner.nextInt();
            product.setStockQuantity(newStock);
            System.out.println("Stock updated for " + product.getName());
        } else {
            System.out.println("Invalid selection.");
        }
    }

    @Override
    public void updateProfile() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter new email (current: " + email + "): ");
        String newEmail = scanner.nextLine();
        System.out.print("Enter new password (leave blank to keep current): ");
        String newPassword = scanner.nextLine();

        if (!newEmail.isEmpty()) setEmail(newEmail);
        if (!newPassword.isEmpty()) setPassword(newPassword);
        System.out.println("Profile updated successfully.");
    }
}
