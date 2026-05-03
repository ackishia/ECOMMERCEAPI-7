package com.ws101.calambasgalupo.EcommerceApi.service;

import com.ws101.calambasgalupo.EcommerceApi.model.Category;
import com.ws101.calambasgalupo.EcommerceApi.model.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class ProductService {

    private final List<Product> products = new ArrayList<>();
    private Long nextId = 1L;

    /**
     * Constructor - initializes sample data
     */
    public ProductService() {

        // Create categories
        Category bag = new Category();
        bag.setId(1L);
        bag.setName("Bag");

        Category heels = new Category();
        heels.setId(2L);
        heels.setName("Heels");

        Category dress = new Category();
        dress.setId(3L);
        dress.setName("Dress");

        // Add products
        products.add(new Product(nextId++, "Classic Leather Bag", "brown leather", 499.0, bag, 10, "img1.jpg"));
        products.add(new Product(nextId++, "Scarf Tote Bag", "scarf brown", 1999.0, bag, 8, "img2.jpg"));
        products.add(new Product(nextId++, "Black Hand Bag", "scarf black", 1999.0, bag, 5, "img3.jpg"));
        products.add(new Product(nextId++, "White Leather Bag", "white leather", 1999.0, bag, 15, "img4.jpg"));

        products.add(new Product(nextId++, "Red Stiletto", "red", 2199.0, heels, 12, "img5.jpg"));
        products.add(new Product(nextId++, "Black Heels", "black heels", 3499.0, heels, 20, "img6.jpg"));
        products.add(new Product(nextId++, "Maroon Stiletto", "maroon", 2500.0, heels, 18, "img7.jpg"));
        products.add(new Product(nextId++, "Ribbon Heels", "red ribbon", 1299.0, heels, 3, "img8.jpg"));

        products.add(new Product(nextId++, "Red Dress", "red dress", 1299.0, dress, 25, "img9.jpg"));
        products.add(new Product(nextId++, "Blush Dress", "sexy dress", 499.0, dress, 6, "img10.jpg"));
    }

    public List<Product> getAllProducts() {
        return products;
    }

    public Product getProductById(Long id) {
        return products.stream()
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public Product addProduct(Product product) {
        product.setId(nextId++);
        products.add(product);
        return product;
    }

    public Product updateProduct(Long id, Product updatedProduct) {
        Product existing = getProductById(id);

        existing.setName(updatedProduct.getName());
        existing.setDescription(updatedProduct.getDescription());
        existing.setPrice(updatedProduct.getPrice());
        existing.setCategory(updatedProduct.getCategory()); // already correct
        existing.setStockQuantity(updatedProduct.getStockQuantity());
        existing.setImageUrl(updatedProduct.getImageUrl());

        return existing;
    }

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
            Category category = new Category();
            category.setName((String) updates.get("category"));
            product.setCategory(category); //  FIXED
        }

        if (updates.containsKey("stockQuantity")) {
            product.setStockQuantity(((Number) updates.get("stockQuantity")).intValue());
        }

        if (updates.containsKey("imageUrl")) {
            product.setImageUrl((String) updates.get("imageUrl"));
        }

        return product;
    }

    public void deleteProduct(Long id) {
        Product product = getProductById(id);
        products.remove(product);
    }

    public List<Product> filterProducts(String filterType, String filterValue) {
        return products.stream().filter(product -> {
            switch (filterType.toLowerCase()) {
                case "name":
                    return product.getName().equalsIgnoreCase(filterValue);
                case "category":
                    return product.getCategory().getName().equalsIgnoreCase(filterValue); //  FIXED
                case "price":
                    return product.getPrice() == Double.parseDouble(filterValue);
                default:
                    return false;
            }
        }).toList();
    }

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