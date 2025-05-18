package org.skypro.skyshop4.model.product;

import java.util.UUID;

public class DiscountedProduct extends Product {
    private int basePrice;
    private int discountProcent;

    public DiscountedProduct(UUID id, String nameProduct, int basePrice, int discountProcent) {
        super(id, nameProduct);
        if (basePrice <= 0) {
            throw new IllegalArgumentException("ОШИБКА В ЦЕНЕ ТОВАРА " + super.getNameProduct());
        }
        if (discountProcent < 0 || discountProcent > 100) {
            throw new IllegalArgumentException("ОШИБКА В % СКИДКИ " + super.getNameProduct());
        }
        this.basePrice = basePrice;
        this.discountProcent = discountProcent;
    }

    @Override
    public int getPrice() {
        return (int) (basePrice * (1 - discountProcent / 100f));
    }

    @Override
    public String toString() {
        return String.format("%30s%20d%5s%5d%3s%23d%4s", getNameProduct(), basePrice, " руб", discountProcent, "%", getPrice(), "руб");
    }

    @Override
    public boolean isSpecial() {
        return true;
    }

}