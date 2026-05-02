package com.ws101.calambasgalupo.EcommerceApi.service;

import com.ws101.calambasgalupo.EcommerceApi.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    // In-memory storage (simulates a database)
    private final List<Product> products = new ArrayList<>();

    // Ensures unique IDs across all operations
    private Long nextId = 1L;

    /**
     * Constructor - initializes sample data
     */
    public ProductService() {
        products.add(new Product(nextId++, "Classic Leather Bag", "brown leather", 499.0, "Bag", 10, "img1.jpg"));
        products.add(new Product(nextId++, "Scarf Tote Bag", "scarf brown", 1999.0, "Bag", 8, "img2.jpg"));
        products.add(new Product(nextId++, "Black Hand Bag", "scarf black", 1999.0, "Bag", 5, "img3.jpg"));
        products.add(new Product(nextId++, "White Leather Bag", "white leather", 1999.0, "Bag", 15, "img4.jpg"));
        products.add(new Product(nextId++, "Red Stiletto", "red", 2199.0, "Heels", 12, "img5.jpg"));
        products.add(new Product(nextId++, "Black Heels", "black heels", 3499.0, "Heels", 20, "img6.jpg"));
        products.add(new Product(nextId++, "Maroon Stiletto", "maroon", 2500.0, "Heels", 18, "img7.jpg"));
        products.add(new Product(nextId++, "Ribbon Heels", "red ribbon", 1299.0, "Heels", 3, "img8.jpg"));
        products.add(new Product(nextId++, "Red Dress", "red dress", 1299.0, "Dress", 25, "img9.jpg"));
        products.add(new Product(nextId++, "Blush Dress", "sexy dress", 499.0, "Dress", 6, "img10.jpg"));
    }

    // GET all products
    public List<Product> getAllProducts() {
        return products;
    }

    // GET product by ID
    public Product getProductById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    // CREATE new product
    public Product addProduct(Product product) {
        product.setId(nextId++);
        products.add(product);
        return product;
    }

    // UPDATE product (PUT - full replace)
    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = getProductById(id);

        existing.setName(updatedProduct.getName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setPrice(updatedProduct.getPrice());
        existing.setCategory(updatedProduct.getCategory());
        existing.setStockQuantity(updatedProduct.getStockQuantity());
        existing.setImageUrl(updatedProduct.getImageUrl());

        return existing;
    }

    // PATCH product (partial update)
    public Product patchProduct(Long id, Map<String, Object> updates) {
        Product product = getProductById(id);

        if (updates.containsKey("name")) {
            product.setName((String) updates.get("name"));
        }

        if (updates.containsKey("description")) {
            product.setDescription((String) updates.get("description"));
        }

        if (updates.containsKey("price")) {
            product.setPrice(((Number) updates.get("price")).doubleValue());
        }

        if (updates.containsKey("category")) {
            product.setCategory((String) updates.get("category"));
        }

        if (updates.containsKey("stockQuantity")) {
            product.setStockQuantity(((Number) updates.get("stockQuantity")).intValue());
        }

        if (updates.containsKey("imageUrl")) {
            product.setImageUrl((String) updates.get("imageUrl"));
        }

        return product;
    }

    // DELETE product
    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        products.remove(product);
    }

    // FILTER products (by name, category, price)
    public List<Product> filterProducts(String filterType, String filterValue) {
        return products.stream().filter(product -> {
            switch (filterType.toLowerCase()) {
                case "name":
                    return product.getName().equalsIgnoreCase(filterValue);
                case "category":
                    return product.getCategory().equalsIgnoreCase(filterValue);
                case "price":
                    return product.getPrice() == Double.parseDouble(filterValue);
                default:
                    return false;
            }
        }).toList();
    }

    /**
     * Filters products by price range.
     *
     * Retrieves all products where the price falls within the specified range.
     *
     * @param minPrice the minimum price (inclusive)
     * @param maxPrice the maximum price (inclusive)
     * @return list of products within the price range
     * @throws IllegalArgumentException if minPrice or maxPrice is invalid
     */
    public List<Product> filterProductWithPrice(double minPrice, double maxPrice) {

        if (minPrice < 0 || maxPrice < 0 || minPrice > maxPrice) {
            throw new IllegalArgumentException("Invalid price range");
        }

        List<Product> filteredProducts = new ArrayList<>();

        for (Product product : products) {
            if (product.getPrice() >= minPrice && product.getPrice() <= maxPrice) {
                filteredProducts.add(product);
            }
        }

        return filteredProducts;
    }
}