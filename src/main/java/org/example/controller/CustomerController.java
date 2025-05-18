package org.example.controller;

import org.example.view.CustomerMenu;

import java.util.Scanner;

public class CustomerController {
    public CustomerController(CustomerMenu customerMenu) {
        this.customerMenu = customerMenu;
    }

    private CustomerMenu customerMenu;
    public void displayMenu(final Scanner scanner) {

        customerMenu.displayMenu(scanner);
    }
}
