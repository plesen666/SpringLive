package org.skypro.skyshop4.model.basket;

import java.util.ArrayList;
import java.util.List;

public class UserBasket {
    private final List<BasketItem> listProduct;
    private final int costProduct;

    public List<BasketItem> getListProduct() {
        return listProduct;
    }

    public int getCostProduct() {
        return costProduct;
    }

    public UserBasket(List<BasketItem> listProduct) {
        this.listProduct = new ArrayList<>(listProduct);
        this.costProduct = listProduct.stream().mapToInt(o -> o.getProduct().getPrice() * o.getQuantity()).sum();
    }

    @Override
    public String toString() {
        return "Продукт=" + listProduct +
                "  Итоговая цена=" + costProduct;
    }
}