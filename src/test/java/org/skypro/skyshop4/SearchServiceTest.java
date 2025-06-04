package org.skypro.skyshop4;

import org.junit.jupiter.api.Test;
import org.skypro.skyshop4.model.article.Article;
import org.skypro.skyshop4.model.product.Product;
import org.skypro.skyshop4.model.product.SimpleProduct;
import org.skypro.skyshop4.model.search.Searchable;
import org.skypro.skyshop4.service.SearchService;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;
import static org.junit.jupiter.api.Assertions.*;

public class SearchServiceTest {

    @Test
    void searchIfThereAreNoObjectsIn_StorageServis_AnEmpty() {
        String stringCheck = "Мыло";
        assertThrows(NullPointerException.class, () -> new SearchService(null).search(stringCheck));
    }

    @Test
    void searchIfThereAreObjectsButThereIsNoSuitableOneIn_StorageService() {
        String stringCheck = "Мыло";
        assertEquals("[]", new SearchService(test()).search(stringCheck).toString());
    }

    @Test
    void searchWhenThereIsSuitableObjectIn_StorageService() {
        String stringCheck = "Пельмени";
        assertTrue(new SearchService(test()).search(stringCheck).toString().contains(stringCheck));
    }

    private Map<UUID, Searchable> test() {
        Product[] products = {
                new SimpleProduct(UUID.randomUUID(), "Конфеты", 800),
                new SimpleProduct(UUID.randomUUID(), "Макароны", 80),
                new SimpleProduct(UUID.randomUUID(), "Пельмени", 250),

        };
        Article[] articles = {
                new Article(UUID.randomUUID(), "Конфеты", "Конфеты Cладкоежка"),
                new Article(UUID.randomUUID(), "Макароны", "Макароны Макфа"),
        };
        Map<UUID, Searchable> testArray;
        testArray = stream(articles).collect(Collectors.toMap(Searchable::getId, product -> product));
        testArray.putAll(stream(products).collect(Collectors.toMap(Searchable::getId, product -> product)));
        return testArray;
    }
}