package org.skypro.skyshop4.service;

import org.skypro.skyshop4.model.basket.BasketItem;
import org.skypro.skyshop4.model.basket.ProductBasket;
import org.skypro.skyshop4.model.basket.UserBasket;
import org.skypro.skyshop4.model.product.Product;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BasketService {
    private final ProductBasket productBasket;
    private final StorageService storageService;

    public BasketService(ProductBasket productBasket, StorageService storageService) {
        this.productBasket = productBasket;
        this.storageService = storageService;
    }

    public void addProductBasketId(UUID id) {
        if (storageService.getProductById(id).isPresent()) {
            productBasket.addProductBasket(id);
        } else {
            storageService.getProductById(id).orElseThrow(() -> new IllegalArgumentException("Продукта с указанным ID в магазине нет"));
        }
    }

    public UserBasket getUserBasket() {
        List<BasketItem> basketItems = new ArrayList<>();
        for (UUID variable : productBasket.getProductBasket().keySet()) {
            Optional<Product> prod = storageService.getAllProducts().stream().filter(product -> product.getId().equals(variable)).findFirst();
            basketItems.add(new BasketItem(prod.get(), productBasket.getProductBasket().get(variable)));
        }
        return new UserBasket(basketItems);
    }
}