package org.example;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public record Order(
        int id,
        Map<Integer, Product> orderedProducts,
        Map<Integer, Integer> quantities,
        double amount,
        String deliveryAddress
) {

    public Order(Product product, int quantity,
                 String deliveryAddress) {
        this(new Random().nextInt(1000),
                new HashMap<>(Map.ofEntries(Map.entry(product.id(), product))),
                new HashMap<>(Map.ofEntries(Map.entry(product.id(), quantity))),
                product.price() * quantity, deliveryAddress);
    }

    public Order addProduct(Product product, int quantity) {
        Map<Integer, Product> newOrderedProducts = orderedProducts;
        newOrderedProducts.put(product.id(), product);
        Map<Integer, Integer> newQuantities = quantities;
        newQuantities.put(product.id(), quantity);
        double newAmount = amount + product.price() * quantity;
        return new Order(id, newOrderedProducts, newQuantities, newAmount, deliveryAddress);
    }

    public Order removeOneItemOfProduct(Product product) {
        Map<Integer, Product> newOrderedProducts = orderedProducts;
        Map<Integer, Integer> newQuantities = quantities;
        int oldQuantity = quantities.get(product.id());
        int newQuantity = oldQuantity - 1;
        if (newQuantity == 0) {
            newOrderedProducts.remove(product.id());
            newQuantities.remove(product.id());
        } else {
            newQuantities.put(product.id(), newQuantity);
        }
        double newAmount = amount - product.price();
        return new Order(id, newOrderedProducts, newQuantities, newAmount, deliveryAddress);
    }

    public Order removeAllItemsOfProduct(Product product) {
        Map<Integer, Product> newOrderedProducts = orderedProducts;
        newOrderedProducts.remove(product.id());
        int quantity = quantities.get(product.id());
        double newAmount = amount - product.price()*quantity;
        Map<Integer, Integer> newQuantities = quantities;
        newQuantities.remove(product.id());
        return new Order(id, newOrderedProducts, newQuantities, newAmount, deliveryAddress);
    }

    public Order changeQuantity(Product product, int newQuantity) {
        int oldQuantity = quantities.get(product.id());
        int difference = oldQuantity - newQuantity;
        if (difference < 1){removeAllItemsOfProduct(product); return null;}
        double newAmount = amount - product.price()*difference;
        Map<Integer, Integer> newQuantities = quantities;
        newQuantities.put(product.id(), newQuantity);
        return new Order(id, orderedProducts, newQuantities, newAmount, deliveryAddress);
    }


}
