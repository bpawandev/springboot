package com.orderapp.service;

import com.orderapp.model.Product;
import com.orderapp.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    public Product getProductById(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        return optionalProduct.orElse(null);
    }

    public Product createProduct(Product product) {
        // You can add additional business logic/validation here if needed
        return productRepository.save(product);
    }

    public Product updateProduct(Long productId, Product updatedProduct) {
        Optional<Product> optionalProduct = productRepository.findById(productId);

        if (optionalProduct.isPresent()) {
            // Update the existing product with the new data
            Product existingProduct = optionalProduct.get();
            existingProduct.setName(updatedProduct.getName());
            existingProduct.setDescription(updatedProduct.getDescription());
            existingProduct.setPrice(updatedProduct.getPrice());

            // Save the updated product
            return productRepository.save(existingProduct);
        } else {
            return null; // Product not found
        }
    }

    public void deleteProduct(Long productId) {
        productRepository.deleteById(productId);
    }
}
