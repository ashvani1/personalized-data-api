package com.niqactivate.personalizedapi.services;

import com.niqactivate.personalizedapi.entity.Product;
import com.niqactivate.personalizedapi.entity.ShelfItem;
import com.niqactivate.personalizedapi.entity.ShopperProductList;
import com.niqactivate.personalizedapi.repository.ProductMetadataRepository;
import com.niqactivate.personalizedapi.repository.ShelfItemRepository;
import com.niqactivate.personalizedapi.repository.ShopperProductListRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InternalService {
    @Autowired
    private ShopperProductListRepository shopperProductListRepository;
    @Autowired
    private ShelfItemRepository shelfItemRepository;

    @Autowired
    private ProductMetadataRepository productRepository;

    public void saveShopperProductList(ShopperProductList request) {
        ShopperProductList shopperProductList = new ShopperProductList();
        shopperProductList.setShopperId(request.getShopperId());

        // Save the ShopperProductList entity first to ensure it has an ID
        shopperProductList = shopperProductListRepository.save(shopperProductList);

        // Convert the shelf items from the request to ShelfItem entities
        List<ShelfItem> shelfItems = new ArrayList<>();
        for (ShelfItem shelfItemRequest : request.getShelfItems()) {
            ShelfItem shelfItem = new ShelfItem();
            shelfItem.setProductId(shelfItemRequest.getProductId());
            shelfItem.setRelevancyScore(shelfItemRequest.getRelevancyScore());
            shelfItem.setShopperProductList(shopperProductList); // Set the parent ShopperProductList
            shelfItems.add(shelfItem);
        }

        // Save the shelf items
        shelfItemRepository.saveAll(shelfItems);
    }

    public void saveProduct(Product product) {
        productRepository.save(product);
    }
}

