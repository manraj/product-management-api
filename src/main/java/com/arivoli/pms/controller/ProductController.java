package com.arivoli.pms.controller;

import com.arivoli.pms.dto.Product;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/products")
public class ProductController {

    private static Long currentProductId = 0L;
    private final List<Product> products = new ArrayList<>();

    @GetMapping()
    public ResponseEntity<Page<Product>> getAll() {
        Pageable pageable = PageRequest.of(1, 10);
        Page<Product> page = new PageImpl<>(products, pageable, products.size());

        return ResponseEntity.ok(page);
    }

    @PostMapping()
    public ResponseEntity<Product> create(@RequestBody Product product) {
        product.setId(++currentProductId);
        LocalDateTime dateTime = LocalDateTime.now();
        product.setCreatedAt(dateTime);
        product.setUpdatedAt(dateTime);
        products.add(product);

        return new ResponseEntity<>(product, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getById(@PathVariable Long id) {

        Product product = getProduct(id);

        if (product == null) {
            throw new EntityNotFoundException("The product not found: " + id);
        }

        return ResponseEntity.ok(product);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> updateById(@PathVariable Long id, @RequestBody Product product) {
        Product existingProduct = getProduct(id);

        if (existingProduct == null) {
            throw new EntityNotFoundException("The product not found: " + id);
        } else {
            existingProduct.setName(product.getName());
            existingProduct.setDescription(product.getDescription());
            existingProduct.setStatus(product.getStatus());
            existingProduct.setBrand(product.getBrand());
            existingProduct.setCategory(product.getCategory());
            existingProduct.setUpdatedAt(LocalDateTime.now());
        }

        return ResponseEntity.ok(existingProduct);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        Product existingProduct = getProduct(id);

        if (existingProduct == null) {
            throw new EntityNotFoundException("The product not found: " + id);
        } else {
            products.remove(existingProduct);
        }

        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    private Product getProduct(Long id) {
        return products.stream()
                .filter(p -> id.equals(p.getId()))
                .findFirst()
                .orElse(null);
    }

}
