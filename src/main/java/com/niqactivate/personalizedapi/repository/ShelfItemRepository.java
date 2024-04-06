package com.niqactivate.personalizedapi.repository;

import com.niqactivate.personalizedapi.entity.ShelfItem;
import com.niqactivate.personalizedapi.entity.ShopperProductList;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ShelfItemRepository extends JpaRepository<ShelfItem, Long> {
    List<ShelfItem> findByShopperProductList(ShopperProductList shopperProductList);
}