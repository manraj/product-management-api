package com.arivoli.pms.controller;

import com.arivoli.pms.entity.SkuEntity;
import com.arivoli.pms.service.SkuService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/skus")
public class SkuController {

    private final SkuService skuService;

    public SkuController(SkuService skuService) {
        this.skuService = skuService;
    }

    /**
     * Create SKU
     * POST /api/skus
     */
    @PostMapping
    public ResponseEntity<SkuEntity> create(
            @RequestBody SkuEntity sku) {

        SkuEntity createdSku = skuService.create(sku);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdSku);
    }

    /**
     * Get SKU by ID
     * GET /api/skus/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<SkuEntity> getById(
            @PathVariable Long id) {

        return ResponseEntity.ok(skuService.getById(id));
    }

    /**
     * Update SKU
     * PUT /api/skus/{id}
     */
    @PutMapping("/{id}")
    public ResponseEntity<SkuEntity> update(
            @PathVariable Long id,
            @RequestBody SkuEntity sku) {

        return ResponseEntity.ok(
                skuService.update(id, sku));
    }

    /**
     * Deactivate SKU (Soft delete)
     * PATCH /api/skus/{id}/deactivate
     */
    @PatchMapping("/{id}/deactivate")
    public ResponseEntity<Void> deactivateSku(
            @PathVariable Long id) {

        skuService.deactivate(id);
        return ResponseEntity.noContent().build();
    }
}

