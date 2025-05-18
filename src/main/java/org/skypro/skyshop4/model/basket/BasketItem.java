package org.skypro.skyshop4.model.basket;


import org.skypro.skyshop4.model.product.Product;

public class BasketItem {
    private final Product product;
    private final int quantity;

    public BasketItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return product;
    }

    public int getQuantity() {
        return quantity;
    }

    @Override
    public String toString() {
        return "Название " +
                "продукта: " + product +
                ", количество: " + quantity;
    }
}