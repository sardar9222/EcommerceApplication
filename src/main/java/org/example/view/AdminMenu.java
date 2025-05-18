package org.example.view;
import org.example.model.user.Admin;
import org.example.service.ProductService;
import org.example.service.UserService;

import java.util.Scanner;

public class AdminMenu {
    private Admin admin;
    private UserService userService;
    private ProductService productService;

    public AdminMenu(Admin admin, UserService userService, ProductService productService) {
        this.admin = admin;
        this.userService = userService;
        this.productService = productService;
    }

    public void displayMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nAdmin Menu:");
            System.out.println("1. Manage Users");
            System.out.println("2. Moderate Content");
            System.out.println("3. Generate Reports");
            System.out.println("4. Update Profile");
            System.out.println("0. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    admin.manageUsers(userService.getUserRepository());
                    break;
                case 2:
                    admin.moderateContent();
                    break;
                case 3:
                    admin.generateReports(productService.getProductRepository());
                    break;
                case 4:
                    admin.updateProfile();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }
}