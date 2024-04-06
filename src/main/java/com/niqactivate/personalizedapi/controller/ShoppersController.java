package com.niqactivate.personalizedapi.controller;

import com.niqactivate.personalizedapi.entity.Product;
import com.niqactivate.personalizedapi.exception.ProductNotFoundException;
import com.niqactivate.personalizedapi.services.ShoppersService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
@Log4j2
public class ShoppersController {
    @Autowired
    private ShoppersService shoppersService;

    @GetMapping("/products/{shopperId}")
    @Cacheable(value = "productsCache", key = "#shopperId + '_' + #categoryId + '_' + #brand")
    public List<Product> getProductsByShopperId(
            @PathVariable String shopperId,
            @RequestParam(required = false, name = "category") String categoryId,
            @RequestParam(required = false, name = "brand") String brand,
            @RequestParam(required = false, name = "limit", defaultValue = "10") int limit) {
        log.debug("ShoppersController :: getProductsByShopperId with shopperId {} ", shopperId);
        List<Product> products = shoppersService.getProductsByShopperId(shopperId, categoryId, brand, limit);
        if (products.isEmpty()) {
            throw new ProductNotFoundException("Products not found for shopperId: " + shopperId);
        }
        return products;
    }
}
