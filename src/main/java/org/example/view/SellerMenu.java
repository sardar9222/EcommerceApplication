package org.example.view;


import org.example.model.product.Product;
import org.example.model.user.Seller;
import org.example.service.InventoryService;
import org.example.service.ProductService;

import java.util.Scanner;

public class SellerMenu {
    private Seller seller;
    private ProductService productService;
    private InventoryService inventoryService;

    public SellerMenu(Seller seller, ProductService productService, InventoryService inventoryService) {
        this.seller = seller;
        this.productService = productService;
        this.inventoryService = inventoryService;
    }

    public void displayMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nSeller Menu:");
            System.out.println("1. Add Product");
            System.out.println("2. Update Inventory");
            System.out.println("3. Check Low Stock");
            System.out.println("4. Update Profile");
            System.out.println("0. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    addProduct(scanner);
                    break;
                case 2:
                    seller.updateInventory();
                    break;
                case 3:
                    inventoryService.checkLowStock();
                    break;
                case 4:
                    seller.updateProfile();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void addProduct(Scanner scanner) {
        System.out.print("Enter product name: ");
        String name = scanner.nextLine();
        System.out.print("Enter price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();
        System.out.print("Enter description: ");
        String description = scanner.nextLine();
        System.out.print("Enter initial stock quantity: ");
        int stock = scanner.nextInt();
        scanner.nextLine();

        String productId = "P" + (productService.getAllProducts().size() + 1);
        Product product = new Product(productId, name, price, description, stock);
        seller.addProduct(product);
        productService.addProduct(product);
        inventoryService.addProduct(product);
    }
}
