package org.example.repository;


import org.example.model.product.Product;
import  org.example.model.product.Review;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * Repository for Review entities.
 * Handles in-memory storage and retrieval of product reviews.
 */
public class ReviewRepository {
    private Map<String, Review> reviews;
    private int nextReviewId;

    public ReviewRepository() {
        this.reviews = new HashMap<>();
        this.nextReviewId = 1;
        initializeReviewData();
    }

    /**
     * Save a review to the repository
     * @param review The review to save
     * @return The saved review with generated ID if new
     */
    public Review save(Review review) {
        // Generate a review ID if it's a new review
        if (review.getReviewId() == null || review.getReviewId().isEmpty()) {
            review.setReviewId("REV" + String.format("%04d", nextReviewId++));
        }

        reviews.put(review.getReviewId(), review);
        return review;
    }

    /**
     * Find a review by its ID
     * @param reviewId The ID of the review to find
     * @return An Optional containing the review if found, or empty if not found
     */
    public Optional<Review> findById(String reviewId) {
        return Optional.ofNullable(reviews.get(reviewId));
    }

    /**
     * Get all reviews in the repository
     * @return A list of all reviews
     */
    public List<Review> findAll() {
        return new ArrayList<>(reviews.values());
    }

    /**
     * Find reviews for a specific product
     * @param productId The ID of the product
     * @return A list of reviews for the specified product
     */
    public List<Review> findByProductId(String productId) {
        return reviews.values().stream()
                .filter(review -> review.getProductId().equals(productId))
                .collect(Collectors.toList());
    }

    /**
     * Find reviews by a specific user
     * @param userId The ID of the user
     * @return A list of reviews written by the specified user
     */
    public Optional<Review> findByUserIdAndProductId(String userId, String productId) {
        return reviews.values().stream()
                .filter(review -> review.getUserId().equals(userId) && review.getProductId().equals(productId))
                .findFirst();
    }

    /**
     * Delete a review from the repository
     * @param reviewId The ID of the review to delete
     */
    public void deleteById(String reviewId) {
        reviews.remove(reviewId);
    }

    /**
     * Calculate the average rating for a product
     * @param productId The ID of the product
     * @return The average rating, or 0 if no reviews exist
     */
    public double calculateAverageRating(String productId) {
        List<Review> productReviews = findByProductId(productId);

        if (productReviews.isEmpty()) {
            return 0;
        }

        int sum = productReviews.stream()
                .mapToInt(Review::getRating)
                .sum();

        return (double) sum / productReviews.size();
    }

    /**
     * Initialize the repository with sample review data
     */
    private void initializeReviewData() {
        // Sample review for the laptop
        Review laptopReview = new Review();
        laptopReview.setReviewId("REV" + String.format("%04d", nextReviewId++));
        laptopReview.setProductId("prod1");
        laptopReview.setUserId("cust1");
        laptopReview.setRating(5);
        laptopReview.setComment("Excellent laptop, very fast and reliable!");
        save(laptopReview);

        // Sample review for the smartphone
        Review smartphoneReview = new Review();
        smartphoneReview.setReviewId("REV" + String.format("%04d", nextReviewId++));
        smartphoneReview.setProductId("prod2");
        smartphoneReview.setUserId("cust1");
        smartphoneReview.setRating(4);
        smartphoneReview.setComment("Good phone, camera quality is impressive.");
        save(smartphoneReview);

        // Sample review for the t-shirt
        Review tshirtReview = new Review();
        tshirtReview.setReviewId("REV" + String.format("%04d", nextReviewId++));
        tshirtReview.setProductId("prod3");
        tshirtReview.setUserId("cust1");
        tshirtReview.setRating(3);
        tshirtReview.setComment("Decent quality but sizing runs small.");
        save(tshirtReview);
    }
}