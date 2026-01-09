package com.arivoli.pms.service;

import com.arivoli.pms.entity.SkuEntity;
import com.arivoli.pms.repository.ProductRepository;
import com.arivoli.pms.repository.SkuRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class SkuService {

    private final SkuRepository skuRepository;
    private final ProductRepository productRepository;

    public SkuService(SkuRepository skuRepository,
                      ProductRepository productRepository) {
        this.skuRepository = skuRepository;
        this.productRepository = productRepository;
    }

    public SkuEntity create(SkuEntity sku) {

        // Validate Product existence
        boolean productExists = productRepository.existsById(sku.getProductId());
        if (!productExists) {
            throw new EntityNotFoundException(
                    "Product not found with id: " + sku.getProductId());
        }

        // Business defaults
        sku.setStatus("ACTIVE");
        sku.setCreatedAt(LocalDateTime.now());

        // Persist
        return skuRepository.save(sku);
    }

    public SkuEntity getById(Long skuId) {
        return skuRepository.findById(skuId)
                .orElseThrow(() ->
                        new EntityNotFoundException("SKU not found with id: " + skuId));
    }

    public List<SkuEntity> getSkusByProductId(Long productId) {

        // Optional: validate product existence
        if (!productRepository.existsById(productId)) {
            throw new EntityNotFoundException(
                    "Product not found with id: " + productId);
        }

        return skuRepository.findByProductId(productId);
    }

    public SkuEntity update(Long skuId, SkuEntity updatedSku) {

        SkuEntity existingSku = getById(skuId);

        existingSku.setUnitOfMeasure(updatedSku.getUnitOfMeasure());
        existingSku.setWeight(updatedSku.getWeight());
        existingSku.setPrice(updatedSku.getPrice());
        existingSku.setQuantity(updatedSku.getQuantity());
        existingSku.setStatus(updatedSku.getStatus());
        existingSku.setUpdatedAt(LocalDateTime.now());

        return skuRepository.save(existingSku);
    }

    public void deactivate(Long skuId) {

        SkuEntity sku = getById(skuId);

        sku.setStatus("INACTIVE");
        sku.setUpdatedAt(LocalDateTime.now());

        skuRepository.save(sku);
    }
}
