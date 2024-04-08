package com.niqactivate.personalizedapi.services.interfaces;

import com.niqactivate.personalizedapi.entity.Product;
import java.util.List;

public interface ShoppersServices {
     List<Product> getProductsByShopperId(String shopperId, String category, String brand, int limit);
}
