package org.example;

import java.util.*;

public class ShopService {
    private OrderMapRepo orderMapRepo;
    private ProductRepo productRepo; // Katalog
    private Map<Integer, Integer> inventory; // ProductId | Quantity in stock


    public void decreaseInventory(Product product, int quantitySold) {
        int inStock = inventory.get(product.id());
        if (inStock < quantitySold) {
            System.out.println("Something went wrong.");
        }
        int newStock = inStock - quantitySold;
        inventory.put(product.id(), newStock);
    }

    public void increaseInventory(Product product, int quantityBought) {
        int newQuantity = inventory.get(product.id()) + quantityBought;
        inventory.put(product.id(), newQuantity);
    }

    public void changeOrder(Order oldOrder, Map<Integer, Product> newOrderedProducts, Map<Integer, Integer> newQuantities) {
        if (oldOrder.quantities().equals(newQuantities)) {
            System.out.println("No changes made in the order.");
            return;
        }
        Set<Integer> productIds = newQuantities.keySet();
        double newAmount = 0.00;
        for (int id : productIds) {
            Product product = newOrderedProducts.get(id);
            int quantity = newQuantities.get(id);
            newAmount = newAmount + product.price() * quantity;

        }
        Order changedOrder = new Order(oldOrder.id(), newOrderedProducts, newQuantities, newAmount, oldOrder.deliveryAddress());
        orderMapRepo.addOrder(changedOrder);
    }

    public void placeOrder(Product product, int quantity, String deliveryAddress) {
        if (!productRepo.getProductList().contains(product)) {
            System.out.println("Product not contained in catalogue.");
            return;
        }
        if (inventory.get(product.id()) < quantity) {
            System.out.println("The product is not in stock in the quantity you wish to order.");
            return;
        }
        decreaseInventory(product, quantity);
        Order newOrder = new Order(product, quantity, deliveryAddress);
        orderMapRepo.getOrderMap().put(newOrder.id(), newOrder);
    }

    public void placeOrder(Map<Integer, Product> orderedProducts, Map<Integer,
            Integer> quantities, String deliveryAddress) {
        Set<Integer> productsIds = orderedProducts.keySet();
        for (int id : productsIds) {
            Product product = productRepo.getProductById(id);
            if (!productRepo.getProductList().contains(product)) {
                System.out.println("One or more of the products are not contained in catalogue."); return;
            }
            int quantityOrdered = quantities.get(id);
            int quantityInStock = inventory.get(id);
            System.out.println("quantityOrdered: "+quantityOrdered);
            System.out.println("quantityInStock: "+quantityInStock);
            if (quantityInStock < quantityOrdered) {
                System.out.println("One or more of the products are not in stock in the quantity you wish to order."); return;
            } else {
                decreaseInventory(product, quantityOrdered);
            }
        }
        Order newOrder = Order.orderSeveralProducts(orderedProducts, quantities, deliveryAddress);
        orderMapRepo.addOrder(newOrder);
        System.out.println("Print von getOrderMapRepo() aus placeOrder-methode: "+getOrderMapRepo());
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
