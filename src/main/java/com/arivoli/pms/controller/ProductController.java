package com.arivoli.pms.controller;

import com.arivoli.pms.dto.Product;
import com.arivoli.pms.entity.ProductEntity;
import com.arivoli.pms.service.ProductService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    private final ProductService productService;

    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    /**
     * Create Product
     * POST /api/pms/products
     */
    @PostMapping
    public ResponseEntity<ProductEntity> create(
            @RequestBody ProductEntity product) {

        ProductEntity createdProduct = productService.create(product);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdProduct);
    }

    /**
     * Get Product by ID
     * GET /api/pms/products/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductEntity> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(productService.getById(id));
    }

    /**
     * Get All Products
     * GET /api/pms/products
     */
    @GetMapping
    public ResponseEntity<List<ProductEntity>> getAll() {
        return ResponseEntity.ok(productService.getAll());
    }

    /**
     * Update Product
     * PUT /api/pms/products/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<ProductEntity> update(
            @PathVariable Long id,
            @RequestBody ProductEntity product) {

        return ResponseEntity.ok(
                productService.update(id, product));
    }

    /**
     * Deactivate Product (Soft delete)
     * PATCH /api/pms/products/{id}/deactivate
     */
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivate(
            @PathVariable Long id) {

        productService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}
