package org.example.controller;

import org.example.view.CustomerMenu;
import org.example.view.SellerMenu;

import java.util.Scanner;

public class SellerController {
    private SellerMenu sellerMenu;

    public SellerController(SellerMenu sellerMenu) {
        this.sellerMenu = sellerMenu;
    }

    public void displayMenu(final Scanner scanner) {
        sellerMenu.displayMenu(scanner);
    }
}
