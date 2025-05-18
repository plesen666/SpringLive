package org.skypro.skyshop4.controller;

import org.skypro.skyshop4.model.article.Article;
import org.skypro.skyshop4.model.basket.UserBasket;
import org.skypro.skyshop4.model.product.Product;
import org.skypro.skyshop4.service.BasketService;
import org.skypro.skyshop4.service.SearchService;
import org.skypro.skyshop4.service.StorageService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collection;
import java.util.Optional;
import java.util.UUID;

import static java.util.Optional.ofNullable;

@RestController
public class ShopController {

    private final StorageService storageService;
    private final BasketService basketService;

    public ShopController(StorageService storageService, BasketService basketService) {
        this.storageService = storageService;
        this.basketService = basketService;
    }

    @GetMapping("/products")
    public Collection<Product> getAllProducts() {
        return storageService.getAllProducts();
    }

    @GetMapping("/articles")
    public Collection<Article> getAllArticle() {
        return storageService.getAllArticle();
    }

    @GetMapping("/basket/{id}")
    public String addProduct(@PathVariable("id") UUID id) {
        basketService.addProductBasketId(id);
        return "Продукт добавлен";
    }

    @GetMapping("/basket")
    public UserBasket getUserBasket() {
        return basketService.getUserBasket();
    }

    @GetMapping("/search")
    public String search(@RequestParam("pattern") String pattern) {
        Optional<StorageService> safeNull = ofNullable(storageService);
        Optional<String> optional;
        optional = safeNull.map(product -> new SearchService(product.entireCollection()).search(pattern)
                .toString()).orElse("Тестовые значения в StorageService не введены").describeConstable();
        String outputString = "";
        if (optional.isPresent()) {
            outputString = optional.get();
        }
        return outputString;
    }
}