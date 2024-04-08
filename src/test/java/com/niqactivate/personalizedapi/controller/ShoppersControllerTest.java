package com.niqactivate.personalizedapi.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import java.util.Collections;
import java.util.List;

import com.niqactivate.personalizedapi.entity.Product;
import com.niqactivate.personalizedapi.exception.ProductNotFoundException;
import com.niqactivate.personalizedapi.services.ShoppersServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.*;

public class ShoppersControllerTest {

    @InjectMocks
    private ShoppersController shoppersController;

    @Mock
    private ShoppersServiceImpl shoppersServiceimpl;

    @BeforeEach
    public void setup() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testGetProductsByShopperId() {
        // Mocking the behavior of the shoppersServiceimpl
        String shopperId = "123";
        String categoryId = "1";
        String brand = "Nike";
        int limit = 10;
        List<Product> mockedProducts = Collections.singletonList(new Product());
        when(shoppersServiceimpl.getProductsByShopperId(shopperId, categoryId, brand, limit)).thenReturn(mockedProducts);

        // Calling the controller method
        List<Product> result = shoppersController.getProductsByShopperId(shopperId, categoryId, brand, limit);

        // Verifying the result
        assertEquals(mockedProducts, result);
    }

    @Test
    public void testGetProductsByShopperId_ProductNotFoundException() {
        // Mocking the behavior of the shoppersServiceimpl to return an empty list
        String shopperId = "123";
        String categoryId = "1";
        String brand = "Nike";
        int limit = 10;
        when(shoppersServiceimpl.getProductsByShopperId(shopperId, categoryId, brand, limit)).thenReturn(Collections.emptyList());

        // Calling the controller method and expecting ProductNotFoundException
        assertThrows(ProductNotFoundException.class, () -> {
            shoppersController.getProductsByShopperId(shopperId, categoryId, brand, limit);
        });
    }
}

