package com.niqactivate.personalizedapi.controller;

import com.niqactivate.personalizedapi.entity.Product;
import com.niqactivate.personalizedapi.entity.ShopperProductList;
import com.niqactivate.personalizedapi.services.InternalServicesImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/internal")
public class InternalController {
    @Autowired
    private InternalServicesImpl internalServicesImpl;

    @PostMapping("/shopperProducts")
    public ResponseEntity<?> saveShopperProductList(@RequestBody ShopperProductList request) {
        internalServicesImpl.saveShopperProductList(request);
        return ResponseEntity.ok().build();
    }

    @PostMapping("/products")
    public ResponseEntity<?> saveProduct(@RequestBody Product product) {
        internalServicesImpl.saveProduct(product);
        return ResponseEntity.ok().build();
    }
}
