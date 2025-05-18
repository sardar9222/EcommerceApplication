package org.example.model.user;

import org.example.model.order.ShoppingCart;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Customer extends User {
    private ShoppingCart cart;
    private List<String> wishlist;

    public Customer(String userId, String username, String email, String password) {
        super(userId, username, email, password);
        this.cart = new ShoppingCart();
        this.wishlist = new ArrayList<>();
    }

    public ShoppingCart getCart() {
        return cart;
    }

    public List<String> getWishlist() {
        return wishlist;
    }

    public void addToWishlist(String productId) {
        if (!wishlist.contains(productId)) {
            wishlist.add(productId);
            System.out.println("Product added to wishlist.");
        } else {
            System.out.println("Product already in wishlist.");
        }
    }

    public void checkOutOrder() {
        // Handled in controller
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