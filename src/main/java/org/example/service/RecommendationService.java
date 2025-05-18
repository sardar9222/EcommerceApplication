package org.example.service;

import org.example.model.order.Order;
import org.example.model.order.OrderItem;
import org.example.model.product.Product;
import org.example.repository.OrderRepository;
import org.example.repository.ProductRepository;

import java.util.*;
import java.util.stream.Collectors;

import java.util.*;

public class RecommendationService {
    public List<Product> getRecommendations(List<Product> allProducts) {
        // Simple recommendation: return products with high stock as "popular"
        return allProducts.stream()
                .sorted((p1, p2) -> Integer.compare(p2.getStockQuantity(), p1.getStockQuantity()))
                .limit(3)
                .toList();
    }
}