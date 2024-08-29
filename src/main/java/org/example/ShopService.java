package org.example;

import java.util.*;

public class ShopService {
    private OrderMapRepo orderMapRepo;
    Map<Integer, Integer> inventory;

    public void decreaseInventory(Product product, int quantitySold) {
        int newQuantity = inventory.get(product.id()) - quantitySold;
        inventory.put(product.id(), newQuantity);
    }

    public void increaseInventory(Product product, int quantityBought) {
        int newQuantity = inventory.get(product.id()) + quantityBought;
        inventory.put(product.id(), newQuantity);
    }

    public void placeOrderOfOneProduct(Product product, int quantity, String deliveryAddress, List<Product> productList) {
        if (productList.contains(product)) {
            Order newOrder = new Order(product, quantity, deliveryAddress);
            orderMapRepo.getOrderList().add(newOrder);
        }
        {System.out.println("Something went wrong when trying to place order.");}
    }

//    public void placeOrder(Map<Integer, Product> productsMap, Map<Integer, Product> quantities, String deliveryAddress, List<Product> productList) {
//        Set<Integer> productsSet = productsMap.values();
//        for (Product product : productsSet)
//        {
//            if (!productList.contains(product)) {
//                System.out.println("Something went wrong when trying to place order.");
//            }
//        }
//
//            Order newOrder = new Order(product, quantity, deliveryAddress);
//            orderMapRepo.getOrderList().add(newOrder);
//
//
//    }

    public void placeOrder(Order newOrder, List<Order> orderList, List<Product> productList) {
        Set<Integer> productIds = newOrder.orderedProducts().keySet();
        for (int id : productIds) {
            Product product = ProductRepo.getProductById(id, productList);
            if (!productList.contains(product)) {
                System.out.println("Something went wrong when trying to place order.");
                return;
            }
        }
        orderList.add(newOrder);
    }

    /////////////////////////////////boiler plate methods////////////////////////////////////////////////


    public ShopService(OrderMapRepo orderMapRepo) {
        this.orderMapRepo = orderMapRepo;
    }

    public OrderMapRepo getOrderMapRepo() {
        return orderMapRepo;
    }

    public void setOrderMapRepo(OrderMapRepo orderMapRepo) {
        this.orderMapRepo = orderMapRepo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ShopService that = (ShopService) o;
        return Objects.equals(orderMapRepo, that.orderMapRepo);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderMapRepo);
    }

    @Override
    public String toString() {
        return "ShopService{" +
                "orderMapRepo=" + orderMapRepo +
                '}';
    }
}
