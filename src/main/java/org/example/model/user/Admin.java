package org.example.model.user;


import org.example.repository.ProductRepository;
import org.example.repository.UserRepository;

import java.util.Scanner;

public class Admin extends User {
    private int adminLevel;

    public Admin(String userId, String username, String email, String password, int adminLevel) {
        super(userId, username, email, password);
        this.adminLevel = adminLevel;
    }

    public void moderateContent() {
        System.out.println("Content moderation feature is under development.");
    }

    public void manageUsers(UserRepository userRepository) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Manage Users:");
        System.out.println("1. List all users");
        System.out.println("2. Delete user");
        System.out.println("0. Back");
        int choice = scanner.nextInt();
        scanner.nextLine();

        switch (choice) {
            case 1:
                userRepository.getAllUsers().forEach(u -> System.out.println(u.getUsername() + " (" + u.getEmail() + ")"));
                break;
            case 2:
                System.out.print("Enter username to delete: ");
                String username = scanner.nextLine();
                User user = userRepository.findByUsername(username);
                if (user != null) {
                    userRepository.deleteUser(user.getUserId());
                    System.out.println("User deleted.");
                } else {
                    System.out.println("User not found.");
                }
                break;
            case 0:
                break;
            default:
                System.out.println("Invalid choice.");
        }
    }

    public void generateReports(ProductRepository productRepository) {
        System.out.println("Sales Report:");
        double totalRevenue = productRepository.getAllProducts().stream()
                .mapToDouble(p -> p.getPrice() * (10 - p.getStockQuantity()))
                .sum();
        System.out.println("Total Revenue (based on sold items): $" + totalRevenue);
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