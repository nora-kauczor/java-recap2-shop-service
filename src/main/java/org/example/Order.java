package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public record Order(
        int id,
        Map<Integer, Product> orderedProducts,
        double amount,
        String deliveryAddress
) {


    public Order(Product product,
                 String deliveryAddress) {
        this(new Random().nextInt(1000),
                new HashMap<>(Map.ofEntries(Map.entry(product.id(), product))), product.price(), deliveryAddress);
    }


    // die methods sind hier aber irgendwie nicht so sinnvoll weil record..
    public Order addProductUpdatePrice(Product product) {
        Map<Integer, Product> newOrderedProducts = orderedProducts;
        newOrderedProducts.put(product.id(), product);
        double newAmount = amount + product.price();
        return new Order(id, newOrderedProducts, newAmount, deliveryAddress);
    }

    public Order removeProductUpdatePrice(Product product) {
        Map<Integer, Product> newOrderedProducts = orderedProducts;
        newOrderedProducts.remove(product.id());
        double newAmount = amount - product.price();
        return new Order(id, newOrderedProducts, newAmount, deliveryAddress);
    }

    public Order orderWithAmount(double newAmount) {
        return new Order(id(), orderedProducts(), newAmount, deliveryAddress());
    }


}
