package com.arivoli.pms.dto;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
public class Product implements Serializable {
    private Long id;
    private String code;
    private String name;
    private String description;
    private String category;
    private String brand;
    private String status;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
}
