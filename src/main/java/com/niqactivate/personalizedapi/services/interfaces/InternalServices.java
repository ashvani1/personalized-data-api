package com.niqactivate.personalizedapi.services.interfaces;

import com.niqactivate.personalizedapi.entity.Product;
import com.niqactivate.personalizedapi.entity.ShopperProductList;

public interface InternalServices {
    void saveShopperProductList(ShopperProductList request);

    void saveProduct(Product product);
}
