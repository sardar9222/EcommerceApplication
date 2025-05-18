package org.example.service;

import org.example.model.product.Category;
import org.example.model.product.Product;
import org.example.model.product.Review;
import org.example.repository.ProductRepository;
import org.example.repository.ReviewRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class ProductService {
    private ProductRepository productRepository = new ProductRepository();

    public void addProduct(Product product) {
        productRepository.addProduct(product);
    }

    public Product findProductById(String productId) {
        return productRepository.findById(productId);
    }

    public List<Product> getAllProducts() {
        return productRepository.getAllProducts();
    }

    public ProductRepository getProductRepository() {
        return productRepository;
    }
}
