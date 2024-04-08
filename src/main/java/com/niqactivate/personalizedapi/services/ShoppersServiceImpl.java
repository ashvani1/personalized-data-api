package com.niqactivate.personalizedapi.services;

import com.niqactivate.personalizedapi.entity.Product;
import com.niqactivate.personalizedapi.entity.ShelfItem;
import com.niqactivate.personalizedapi.entity.ShopperProductList;
import com.niqactivate.personalizedapi.repository.ProductMetadataRepository;
import com.niqactivate.personalizedapi.repository.ShelfItemRepository;
import com.niqactivate.personalizedapi.repository.ShopperProductListRepository;
import com.niqactivate.personalizedapi.services.interfaces.ShoppersServices;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Slf4j
public class ShoppersServiceImpl implements ShoppersServices {
    @Autowired
    private ShopperProductListRepository shopperProductListRepository;

    @Autowired
    private ShelfItemRepository shelfItemRepository;

    @Autowired
    private ProductMetadataRepository productMetadataRepository;

    @Override
    public List<Product> getProductsByShopperId(String shopperId, String category, String brand, int limit) {
        List<ShopperProductList> shopperProductLists = shopperProductListRepository.findByShopperId(shopperId);
        log.trace("ShoppersServiceImpl :: getProductsByShopperId() : fetched shopperProductList for shopperId- {} as {} ", shopperId, shopperProductLists);
        List<Product> products = new ArrayList<>();
        for (ShopperProductList shopperProductList : shopperProductLists) {
            List<ShelfItem> shelfItems = shelfItemRepository.findByShopperProductList(shopperProductList);
            log.trace("ShoppersServiceImpl :: getProductsByShopperId() : fetched shelfItem list for shopperProductList- {} as {} ", shopperProductList, shelfItems);

            products = shelfItems.stream()
                    .map(ShelfItem::getProductId)
                    .map(productMetadataRepository::findByProductId)
                    .filter(product -> product != null &&
                            (category == null || product.getCategory().equals(category)) &&
                            (brand == null || product.getBrand().equals(brand)))
                    .limit(limit)
                    .collect(Collectors.toList());
        }
        return products;
    }
}