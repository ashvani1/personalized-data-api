package com.niqactivate.personalizedapi.service;

import com.niqactivate.personalizedapi.entity.Product;
import com.niqactivate.personalizedapi.entity.ShelfItem;
import com.niqactivate.personalizedapi.entity.ShopperProductList;
import com.niqactivate.personalizedapi.repository.ProductMetadataRepository;
import com.niqactivate.personalizedapi.repository.ShelfItemRepository;
import com.niqactivate.personalizedapi.repository.ShopperProductListRepository;
import com.niqactivate.personalizedapi.services.ShoppersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class ShoppersServiceImplTest {

    @Mock
    private ShopperProductListRepository shopperProductListRepository;

    @Mock
    private ShelfItemRepository shelfItemRepository;

    @Mock
    private ProductMetadataRepository productMetadataRepository;

    @InjectMocks
    private ShoppersServiceImpl shoppersServiceimpl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetProductsByShopperId() {
        // Mocking repository responses
        String shopperId = "S-1000";
        String category = "Category1";
        String brand = "Brand1";
        int limit = 10;

        List<ShopperProductList> shopperProductLists = new ArrayList<>();
        ShopperProductList shopperProductList = new ShopperProductList();
        shopperProductLists.add(shopperProductList);
        when(shopperProductListRepository.findByShopperId(shopperId)).thenReturn(shopperProductLists);

        List<ShelfItem> shelfItems = new ArrayList<>();
        ShelfItem shelfItem = new ShelfItem();
        shelfItem.setProductId("P-1001");
        shelfItems.add(shelfItem);
        when(shelfItemRepository.findByShopperProductList(shopperProductList)).thenReturn(shelfItems);

        Product product = new Product();
        product.setProductId("P-1001");
        product.setCategory(category);
        product.setBrand(brand);
        when(productMetadataRepository.findByProductId("P-1001")).thenReturn(product);

        // Calling the service method
        List<Product> result = shoppersServiceimpl.getProductsByShopperId(shopperId, category, brand, limit);

        // Verifying the result
        assertEquals(1, result.size());
        assertEquals("P-1001", result.get(0).getProductId());

        // Verifying repository method invocations
        verify(shopperProductListRepository, times(1)).findByShopperId(shopperId);
        verify(shelfItemRepository, times(1)).findByShopperProductList(shopperProductList);
        verify(productMetadataRepository, times(1)).findByProductId("P-1001");
    }
}

