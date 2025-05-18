package org.example.model.product;


/**
 * Class representing a product category in the E-Retail platform.
 */
public class Category {
    private String Id;
    private String name;

    public Category() {
    }

// Getters and setters
    public void setId(String id) {
        this.Id = id;
    }
    public String getId() {
        return Id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return name;
    }
}