package com.niqactivate.personalizedapi.repository;

import com.niqactivate.personalizedapi.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductMetadataRepository extends JpaRepository<Product, Long> {
    Product findByProductId(String productId);
}

