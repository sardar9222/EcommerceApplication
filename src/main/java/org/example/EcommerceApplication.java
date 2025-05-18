package org.example;

import org.example.controller.AdminController;
import org.example.controller.CustomerController;
import org.example.controller.SellerController;
import org.example.model.product.Product;
import org.example.model.user.Admin;
import org.example.model.user.Customer;
import org.example.model.user.Seller;

import org.example.model.user.User;
import org.example.service.*;
import org.example.view.AdminMenu;
import org.example.view.CustomerMenu;
import org.example.view.SellerMenu;

import java.util.Scanner;

public class EcommerceApplication {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        UserService userService = new UserService();
        ProductService productService = new ProductService();
        OrderService orderService = new OrderService();
        PaymentService paymentService = new PaymentService();
        InventoryService inventoryService = new InventoryService();
        RecommendationService recommendationService = new RecommendationService();

        // Initialize sample data
        initializeSampleData(userService, productService, inventoryService);

        // Initial login
        System.out.println("Welcome to E-Retail Platform");
        System.out.println("1. Login\n2. Exit");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            System.out.print("Username: ");
            String username = scanner.nextLine();
            System.out.print("Password: ");
            String password = scanner.nextLine();

            User user = userService.authenticate(username, password);
            if (user != null) {
                if (user instanceof Customer) {
                    CustomerMenu customerMenu = new CustomerMenu((Customer) user, productService, orderService, paymentService, recommendationService);
                    CustomerController customerController = new CustomerController(customerMenu);
                    customerController.displayMenu(scanner);
                } else if (user instanceof Seller) {
                    SellerMenu sellerMenu = new SellerMenu((Seller) user, productService, inventoryService);
                    SellerController sellerController = new SellerController(sellerMenu);
                    sellerController.displayMenu(scanner);
                } else if (user instanceof Admin) {
                    AdminMenu adminMenu = new AdminMenu((Admin) user, userService, productService);
                    AdminController adminController = new AdminController(adminMenu);
                    adminController.displayMenu(scanner);
                }
            } else {
                System.out.println("Invalid credentials");
            }
        }
        scanner.close();
    }

    private static void initializeSampleData(UserService userService, ProductService productService, InventoryService inventoryService) {
        // Sample users
        userService.addUser(new Customer("C1", "customer1", "customer1@example.com", "password"));
        userService.addUser(new Seller("S1", "seller1", "seller1@example.com", "password"));
        userService.addUser(new Admin("A1", "admin", "admin@example.com", "admin123", 1));

        // Sample products
        Product p1 = new Product("P1", "Laptop", 999.99, "High-performance laptop", 10);
        Product p2 = new Product("P2", "Smartphone", 499.99, "Latest smartphone", 20);
        productService.addProduct(p1);
        productService.addProduct(p2);
        inventoryService.addProduct(p1);
        inventoryService.addProduct(p2);
    }
}