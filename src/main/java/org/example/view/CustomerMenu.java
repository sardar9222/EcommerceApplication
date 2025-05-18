package org.example.view;

import org.example.model.order.CartItem;
import org.example.model.order.Order;
import org.example.model.payment.CreditCardPayment;
import org.example.model.payment.PayPalPayment;
import org.example.model.payment.PaymentProcessor;
import org.example.model.product.Product;
import org.example.model.user.Customer;
import org.example.service.OrderService;
import org.example.service.PaymentService;
import org.example.service.ProductService;
import org.example.service.RecommendationService;

import java.util.List;
import java.util.Scanner;

public class CustomerMenu {
    private Customer customer;
    private ProductService productService;
    private OrderService orderService;
    private PaymentService paymentService;
    private RecommendationService recommendationService;

    public CustomerMenu(Customer customer, ProductService productService, OrderService orderService, PaymentService paymentService, RecommendationService recommendationService) {
        this.customer = customer;
        this.productService = productService;
        this.orderService = orderService;
        this.paymentService = paymentService;
        this.recommendationService = recommendationService;
    }

    public void displayMenu(Scanner scanner) {
        while (true) {
            System.out.println("\nCustomer Menu:");
            System.out.println("1. View Products");
            System.out.println("2. Add to Cart");
            System.out.println("3. View Cart");
            System.out.println("4. Checkout");
            System.out.println("5. View Orders");
            System.out.println("6. View Wishlist");
            System.out.println("7. Add to Wishlist");
            System.out.println("8. View Recommendations");
            System.out.println("9. Update Profile");
            System.out.println("0. Logout");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1:
                    viewProducts();
                    break;
                case 2:
                    addToCart(scanner);
                    break;
                case 3:
                    viewCart();
                    break;
                case 4:
                    checkout(scanner);
                    break;
                case 5:
                    viewOrders();
                    break;
                case 6:
                    viewWishlist();
                    break;
                case 7:
                    addToWishlist(scanner);
                    break;
                case 8:
                    viewRecommendations();
                    break;
                case 9:
                    customer.updateProfile();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Invalid choice");
            }
        }
    }

    private void viewProducts() {
        System.out.println("\nAvailable Products:");
        List<Product> products = productService.getAllProducts();
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            System.out.println((i + 1) + ". " + p.getName() + " - $" + p.getPrice() + " (Stock: " + p.getStockQuantity() + ")");
            System.out.println("   " + p.getDescription());
        }
    }

    private void addToCart(Scanner scanner) {
        viewProducts();
        System.out.print("Select product to add to cart (or 0 to cancel): ");
        int choice = scanner.nextInt();
        if (choice == 0) return;
        List<Product> products = productService.getAllProducts();
        if (choice > 0 && choice <= products.size()) {
            Product product = products.get(choice - 1);
            System.out.print("Enter quantity: ");
            int quantity = scanner.nextInt();
            if (quantity > 0 && quantity <= product.getStockQuantity()) {
                CartItem item = new CartItem(product, quantity);
                customer.getCart().addItem(item);
            } else {
                System.out.println("Invalid quantity or insufficient stock.");
            }
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private void viewCart() {
        System.out.println("\nYour Cart:");
        List<CartItem> items = customer.getCart().getItems();
        if (items.isEmpty()) {
            System.out.println("Cart is empty.");
        } else {
            for (CartItem item : items) {
                System.out.println(item.getProduct().getName() + " - Qty: " + item.getQuantity() + " - Subtotal: $" + item.getSubtotal());
            }
            System.out.println("Total: $" + customer.getCart().getTotalAmount());
        }
    }

    private void checkout(Scanner scanner) {
        if (customer.getCart().getItems().isEmpty()) {
            System.out.println("Cart is empty. Add items to checkout.");
            return;
        }

        System.out.println("\nCheckout - Total: $" + customer.getCart().getTotalAmount());
        System.out.println("Select payment method:");
        System.out.println("1. Credit Card");
        System.out.println("2. PayPal");
        System.out.println("0. Cancel");
        int paymentChoice = scanner.nextInt();
        PaymentProcessor processor = null;
        switch (paymentChoice) {
            case 1:
                processor = new CreditCardPayment();
                break;
            case 2:
                processor = new PayPalPayment();
                break;
            case 0:
                return;
            default:
                System.out.println("Invalid choice.");
                return;
        }

        boolean paymentSuccess = paymentService.processPayment(processor, customer.getCart().getTotalAmount());
        if (paymentSuccess) {
            Order order = orderService.createOrder(customer.getUserId(), customer.getCart());
            orderService.processOrder(order);
            customer.getCart().clear();
            System.out.println("Checkout successful! Order ID: " + order.getOrderId());
        } else {
            System.out.println("Payment failed. Please try again.");
        }
    }

    private void viewOrders() {
        System.out.println("\nYour Orders:");
        List<Order> orders = orderService.getCustomerOrders(customer.getUserId());
        if (orders.isEmpty()) {
            System.out.println("No orders found.");
        } else {
            for (Order order : orders) {
                System.out.println("Order ID: " + order.getOrderId() + " | Status: " + order.getStatus() + " | Total: $" + order.getTotalAmount());
            }
        }
    }

    private void viewWishlist() {
        System.out.println("\nYour Wishlist:");
        List<String> wishlist = customer.getWishlist();
        if (wishlist.isEmpty()) {
            System.out.println("Wishlist is empty.");
        } else {
            for (String productId : wishlist) {
                Product product = productService.findProductById(productId);
                if (product != null) {
                    System.out.println(product.getName() + " - $" + product.getPrice());
                }
            }
        }
    }

    private void addToWishlist(Scanner scanner) {
        viewProducts();
        System.out.print("Select product to add to wishlist (or 0 to cancel): ");
        int choice = scanner.nextInt();
        if (choice == 0) return;
        List<Product> products = productService.getAllProducts();
        if (choice > 0 && choice <= products.size()) {
            Product product = products.get(choice - 1);
            customer.addToWishlist(product.getProductId());
        } else {
            System.out.println("Invalid selection.");
        }
    }

    private void viewRecommendations() {
        System.out.println("\nRecommended Products:");
        List<Product> recommendations = recommendationService.getRecommendations(productService.getAllProducts());
        for (Product p : recommendations) {
            System.out.println(p.getName() + " - $" + p.getPrice() + " (Stock: " + p.getStockQuantity() + ")");
        }
    }
}