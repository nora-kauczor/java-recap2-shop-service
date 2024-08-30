package org.example;

import java.util.*;

public class ShopService {
    private OrderMapRepo orderMapRepo;
    private ProductRepo productRepo; // Katalog
    private Map<Integer, Integer> inventory; // ProductId | Quantity in stock


    public void decreaseInventory(Product product, int quantitySold) {
        int newQuantity = inventory.get(product.id()) - quantitySold;
        inventory.put(product.id(), newQuantity);
    }

    public void increaseInventory(Product product, int quantityBought) {
        int newQuantity = inventory.get(product.id()) + quantityBought;
        inventory.put(product.id(), newQuantity);
    }

    public void placeOrder(Product product, int quantity, String deliveryAddress) {
        if (!productRepo.getProductList().contains(product)) {
            System.out.println("Product not contained in catalogue.");
            return;
        }
        if (inventory.get(product.id()) > quantity) {
            System.out.println("The product is not in stock in the quantity you wish to order."); return;
        }
        decreaseInventory(product, quantity);
        Order newOrder = new Order(product, quantity, deliveryAddress);
        orderMapRepo.getOrderList().add(newOrder);
    }

    public void placeOrder(Map<Integer, Product> orderedProducts, Map<Integer, Integer> quantities,
                           String deliveryAddress) {
        Set<Integer> productsIds = orderedProducts.keySet();
        for (int id : productsIds) {
            Product product = productRepo.getProductById(id);
            if (!productRepo.getProductList().contains(product)) {
                System.out.println("One or more of the products are not contained in catalogue.");
            }
            int quantityOrdered = quantities.get(id);
            int quantityInStock = inventory.get(id);
            if (quantityInStock > quantityOrdered) {
                System.out.println("One or more of the products are not in stock in the quantity you wish to order.");
            } else {
                decreaseInventory(product, quantityOrdered);
            }
        }
        Order newOrder = Order.OrderSeveralProducts(orderedProducts, quantities, deliveryAddress);
        orderMapRepo.getOrderList().add(newOrder);
    }

    /////////////////////////////////boiler plate methods////////////////////////////////////////////////


    public ShopService(Map<Integer, Integer> inventory, OrderMapRepo orderMapRepo, ProductRepo productRepo) {
        this.inventory = inventory;
        this.orderMapRepo = orderMapRepo;
        this.productRepo = productRepo;
    }

    public OrderMapRepo getOrderMapRepo() {
        return orderMapRepo;
    }

    public void setOrderMapRepo(OrderMapRepo orderMapRepo) {
        this.orderMapRepo = orderMapRepo;
    }

    public Map<Integer, Integer> getInventory() {
        return inventory;
    }

    public void setInventory(Map<Integer, Integer> inventory) {
        this.inventory = inventory;
    }

    public ProductRepo getProductRepo() {
        return productRepo;
    }

    public void setProductRepo(ProductRepo productRepo) {
        this.productRepo = productRepo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        ShopService that = (ShopService) o;
        return Objects.equals(orderMapRepo, that.orderMapRepo) && Objects.equals(productRepo, that.productRepo) && Objects.equals(inventory, that.inventory);
    }

    @Override
    public int hashCode() {
        return Objects.hash(orderMapRepo, productRepo, inventory);
    }

    @Override
    public String toString() {
        return "ShopService{" +
                "inventory=" + inventory +
                ", orderMapRepo=" + orderMapRepo +
                ", productRepo=" + productRepo +
                '}';
    }
}
