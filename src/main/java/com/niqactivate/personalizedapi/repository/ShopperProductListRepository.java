package com.niqactivate.personalizedapi.repository;
import com.niqactivate.personalizedapi.entity.ShopperProductList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShopperProductListRepository extends JpaRepository<ShopperProductList, Long> {
    List<ShopperProductList> findByShopperId(String shopperId);
}
