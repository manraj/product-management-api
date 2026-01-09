package com.arivoli.pms.controller;

import com.arivoli.pms.entity.SkuEntity;
import com.arivoli.pms.service.SkuService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/products/{id}/skus")
public class ProductSkuController {

    private final SkuService skuService;

    public ProductSkuController(SkuService skuService) {
        this.skuService = skuService;
    }

    @GetMapping
    public List<SkuEntity> getSkusByProductId(
            @PathVariable Long id) {
        return skuService.getSkusByProductId(id);
    }

}
