package com.arivoli.pms.service;

import com.arivoli.pms.entity.ProductEntity;
import com.arivoli.pms.repository.ProductRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ProductService {

    private final ProductRepository productRepository;

    public ProductService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    /**
     * Create Product
     */
    public ProductEntity create(ProductEntity product) {

        // Basic validation
        if (product.getName() == null || product.getName().isBlank()) {
            throw new IllegalArgumentException("Product name is required");
        }

        if (product.getCode() == null || product.getCode().isBlank()) {
            throw new IllegalArgumentException("Product code is required");
        }

        product.setStatus("ACTIVE");
        product.setCreatedAt(LocalDateTime.now());

        return productRepository.save(product);
    }

    /**
     * Get Product by ID
     */
    public ProductEntity getById(Long productId) {
        return productRepository.findById(productId)
                .orElseThrow(() ->
                        new EntityNotFoundException(
                                "Product not found with id: " + productId));
    }

    /**
     * Get all Products
     */
    public List<ProductEntity> getAll() {
        return productRepository.findAll();
    }

    /**
     * Update Product
     */
    public ProductEntity update(Long productId,
                                ProductEntity updatedProduct) {

        ProductEntity existingProduct = getById(productId);

        existingProduct.setName(updatedProduct.getName());
        existingProduct.setDescription(updatedProduct.getDescription());
        existingProduct.setCategory(updatedProduct.getCategory());
        existingProduct.setBrand(updatedProduct.getBrand());
        existingProduct.setStatus(updatedProduct.getStatus());
        existingProduct.setUpdatedAt(LocalDateTime.now());

        return productRepository.save(existingProduct);
    }

    /**
     * Deactivate Product (Soft Delete)
     */
    public void deactivate(Long productId) {

        ProductEntity product = getById(productId);

        product.setStatus("INACTIVE");
        product.setUpdatedAt(LocalDateTime.now());

        productRepository.save(product);
    }
}
