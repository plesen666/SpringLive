package org.skypro.skyshop4;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.skyshop4.exeption.NoSuchProductException;
import org.skypro.skyshop4.model.basket.ProductBasket;
import org.skypro.skyshop4.model.product.Product;
import org.skypro.skyshop4.model.product.SimpleProduct;
import org.skypro.skyshop4.service.BasketService;
import org.skypro.skyshop4.service.StorageService;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class ProductBasketTest {
    @Mock
    StorageService storageService;
    @Mock
    ProductBasket productBasket;
    @InjectMocks
    BasketService basketService;

    @Test
    void addingNonExistentProductToTheShoppingCart() {
        UUID test = UUID.randomUUID();
        when(storageService.getProductById((test))).thenThrow(new NoSuchProductException());
        assertThrows(NoSuchProductException.class, () -> basketService.addProductBasketId(test));
    }

    @Test
    void addingAnExistingProductCallsThe_addProduct() {
        UUID test = UUID.randomUUID();
        Product product = new SimpleProduct(test, "Product1", 800);
        when(storageService.getProductById(eq(test))).thenReturn(Optional.of(product));
        basketService.addProductBasketId(test);
        Mockito.verify(productBasket, Mockito.times(1)).addProductBasket(test);
    }

    @Test
    void ifThe_ProductBasket_isEmptyReturnTheEmptyBasket() {
        basketService.getUserBasket();
        Mockito.verify(productBasket, Mockito.atMostOnce()).getProductBasket();
    }

    @Test
    void returnSuitableShoppingCartIfIn_ProductBasket_hasProducts() {
        UUID test = UUID.randomUUID();
        Product product = new SimpleProduct(test, "Product1", 800);
        when(storageService.getProductById(eq(test))).thenReturn(Optional.of(product));
        basketService.addProductBasketId(test);
        Mockito.verify(productBasket, Mockito.times(1)).addProductBasket(test);
        basketService.getUserBasket();
        Mockito.verify(productBasket, Mockito.atLeastOnce()).getProductBasket();
    }
}