package org.skypro.skyshop4.model.product;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.skypro.skyshop4.model.search.Searchable;

import java.util.Objects;
import java.util.UUID;

public abstract class Product implements Searchable {
    private String nameProduct;
    private final UUID id;

    public Product(UUID id, String nameProduct) throws IllegalArgumentException {
        if (nameProduct.isBlank()) {
            throw new IllegalArgumentException("Ошибка - не введено название продукта в продуктовом массиве магазина");
        }
        this.nameProduct = nameProduct.trim();
        this.id = id;
    }

    @Override
    public UUID getId() {
        return id;
    }

    public String getNameProduct() {
        return nameProduct;
    }

    public abstract int getPrice();

    public boolean isSpecial() {
        return false;
    }

    @JsonIgnore
    @Override
    public String getSearchTemp() {
        return nameProduct;
    }

    @JsonIgnore
    @Override
    public String getContentType() {
        return "PRODUCT";
    }

    @JsonIgnore
    public String sortingElement() {
        return nameProduct;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (object == null || getClass() != object.getClass()) return false;
        Product product = (Product) object;
        return Objects.equals(nameProduct, product.nameProduct);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(nameProduct);
    }


}