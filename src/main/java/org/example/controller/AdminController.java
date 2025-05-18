package org.example.controller;

import org.example.view.AdminMenu;
import org.example.view.SellerMenu;

import java.util.Scanner;

public class AdminController {
    private AdminMenu adminMenu;

    public AdminController(AdminMenu adminMenu) {
        this.adminMenu = adminMenu;
    }

    public void displayMenu(final Scanner scanner) {
        adminMenu.displayMenu(scanner);
    }
}
