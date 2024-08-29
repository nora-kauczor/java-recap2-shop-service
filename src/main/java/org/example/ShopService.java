package org.example;

import java.util.List;
import java.util.Objects;
import java.util.Set;

public class ShopService {
    private OrderMapRepo orderMapRepo;




    public void placeOrder(Order newOrder, List<Order> orderList, List<Product> productList) {
        Set<Integer> productIds = newOrder.orderedProducts().keySet();
        for (int id : productIds) {
            Product product = ProductRepo.getProductById(id, productList);
            if (!productList.contains(product)) {
                System.out.println("Something went wrong when trying to place order."); return;
            }
        }
        // wie w¨rde man das schreiben, wenn nicht in OderListRepo schon stünde, dass die list eine ArrayList sein soll
        orderList.add(newOrder);
    }

    public void placeOrder(Order newOrder){

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
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
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
