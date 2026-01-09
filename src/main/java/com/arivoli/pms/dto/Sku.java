package com.arivoli.pms.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
public class Sku implements Serializable {

    Long id;
    String code;
    Long productId;
    String unitOfMeasure;
    Double weight;
    BigDecimal price;
    Integer quantity;
    String status;
    LocalDateTime createAt;
    LocalDateTime updatedAt;

}
