package com.niqactivate.personalizedapi.controller;

import com.niqactivate.personalizedapi.entity.Product;
import com.niqactivate.personalizedapi.entity.ShopperProductList;
import com.niqactivate.personalizedapi.services.InternalService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internal")
public class InternalController {
    @Autowired
    private InternalService internalService;

    @PostMapping("/shopperProductList")
    public ResponseEntity<?> saveShopperProductList(@RequestBody ShopperProductList request) {
        internalService.saveShopperProductList(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/product")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        internalService.saveProduct(product);
        return ResponseEntity.ok().build();
    }
}
