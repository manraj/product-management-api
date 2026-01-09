package com.arivoli.pms.repository;

import com.arivoli.pms.entity.SkuEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SkuRepository extends JpaRepository<SkuEntity, Long> {

    List<SkuEntity> findByProductId(Long productId);
}
