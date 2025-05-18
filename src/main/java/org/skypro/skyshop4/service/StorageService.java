package org.skypro.skyshop4.service;

import org.skypro.skyshop4.model.article.Article;
import org.skypro.skyshop4.model.product.DiscountedProduct;
import org.skypro.skyshop4.model.product.FixPriceProduct;
import org.skypro.skyshop4.model.product.Product;
import org.skypro.skyshop4.model.product.SimpleProduct;
import org.skypro.skyshop4.model.search.Searchable;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

@Service
public class StorageService {
    private final Map<UUID, Product> storageProduct;
    private final Map<UUID, Article> storageArticle;

    public StorageService(Map<UUID, Product> storageProduct, Map<UUID, Article> storageArticle) {
        this.storageProduct = new HashMap<>(test().values().stream().filter(product -> product.getContentType().equals("PRODUCT")).collect(Collectors.toMap(Searchable::getId, product -> (Product) product)));
        this.storageArticle = new HashMap<>(test().values().stream().filter(product -> product.getContentType().equals("ARTICLE")).collect(Collectors.toMap(Searchable::getId, product -> (Article) product)));
    }

    public Collection<Product> getAllProducts() {

        return storageProduct.values();
    }

    public Collection<Article> getAllArticle() {
        return storageArticle.values();
    }

    public Map<UUID, Searchable> entireCollection() {
        Map<UUID, Searchable> entire = new HashMap<>(storageProduct);
        entire.putAll(storageArticle);
        return entire;
    }

    public Map<UUID, Product> availableProducts(UUID id) {
        return storageProduct.keySet().stream().filter(Objects::nonNull).filter(o -> o.equals(id)).collect(Collectors.toMap(o -> o, storageProduct::get));

    }

    public Optional<Product> getProductById(UUID id) {
        return Optional.ofNullable(availableProducts(id).get(id));
    }

    private static Map<UUID, Searchable> test() {
        Product[] products = {
                new SimpleProduct(UUID.randomUUID(), "Конфеты", 800),
                new SimpleProduct(UUID.randomUUID(), "Макароны", 80),
                new SimpleProduct(UUID.randomUUID(), "Пельмени", 350),
                new SimpleProduct(UUID.randomUUID(), "Масло", 120),
                new SimpleProduct(UUID.randomUUID(), "Сосиски", 150),
                new SimpleProduct(UUID.randomUUID(), "Кетчуп", 120),
                new SimpleProduct(UUID.randomUUID(), "Майонез", 77),
                new SimpleProduct(UUID.randomUUID(), "Молоко", 80),
                new SimpleProduct(UUID.randomUUID(), "Кефир", 80),
                new SimpleProduct(UUID.randomUUID(), "Сметана", 210),
                new SimpleProduct(UUID.randomUUID(), "Яйца", 100),
                new DiscountedProduct(UUID.randomUUID(), "Мыло", 23, 10),
                new DiscountedProduct(UUID.randomUUID(), "Шампунь ", 888, 10),
                new DiscountedProduct(UUID.randomUUID(), "Стиральный порошок", 780, 15),
                new DiscountedProduct(UUID.randomUUID(), "Пена для бритья", 455, 15),
                new FixPriceProduct(UUID.randomUUID(), "Хлеб ржаной"),
                new FixPriceProduct(UUID.randomUUID(), "Хлеб Бородинский"),
                new FixPriceProduct(UUID.randomUUID(), "Туалетная бумага"),
                new FixPriceProduct(UUID.randomUUID(), "Батон нарезной 2 кат."),
                new FixPriceProduct(UUID.randomUUID(), "Батон нарезной 1 кат."),
                new FixPriceProduct(UUID.randomUUID(), "Батон нарезной 3 кат.")
        };
        Article[] articles = {
                new Article(UUID.randomUUID(), "Шампунь", "Шампунь Ромашка"),
                new Article(UUID.randomUUID(), "Конфеты", "Конфеты Cладкоежка"),
                new Article(UUID.randomUUID(), "Макароны", "Макароны Макфа"),
                new Article(UUID.randomUUID(), "Пельмени", "Пельмени Рузские"),
                new Article(UUID.randomUUID(), "Масло", "Масло Вологодское"),
                new Article(UUID.randomUUID(), "Сосиски", "Сосиски Микояновские"),
                new Article(UUID.randomUUID(), "Кетчуп", "Кетчуп острый"),
                new Article(UUID.randomUUID(), "Кетчуп", "Кетчуп острый"),
                new Article(UUID.randomUUID(), "Кетчуп", "Кетчуп острый"),
                new Article(UUID.randomUUID(), "Кетчуп", "Кетчуп острый"),
                new Article(UUID.randomUUID(), "Майонез", "Майонез Провансаль"),
                new Article(UUID.randomUUID(), "Молоко", "Молоко Рузское"),
                new Article(UUID.randomUUID(), "Сметана", "Сметана домашняя"),
                new Article(UUID.randomUUID(), "Яйца", "Яйца куриные первой категории"),
                new Article(UUID.randomUUID(), "Мыло", "Мыло Palmolive"),
                new Article(UUID.randomUUID(), "Хлеб ржаной", "Хлебобулочное изделие"),
                new Article(UUID.randomUUID(), "Хлеб Бородинский", "Хлебобулочное изделие"),
                new Article(UUID.randomUUID(), "Батон нарезной 4 кат", "Батон нарезной 4 кат"),
                new Article(UUID.randomUUID(), "Батон нарезной 4 кат", "Батон нарезной 4 кат"),
                new Article(UUID.randomUUID(), "Стиральный порошок", "Стиральный порошок Tide"),
                new Article(UUID.randomUUID(), "Пена для бритья", "Пена для бритья Tide")
        };
        Map<UUID, Searchable> testArray;
        testArray = stream(articles).collect(Collectors.toMap(Searchable::getId, product -> product));
        testArray.putAll(stream(products).collect(Collectors.toMap(Searchable::getId, product -> product)));
        return testArray;
    }
}