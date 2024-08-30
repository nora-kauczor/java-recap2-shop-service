package org.example;

import java.security.Key;
import java.util.*;


public class OrderMapRepo implements OrderRepo {
    private Map<Integer, Order> orderMap = new HashMap<>();

    @Override
    public void addOrder(Order order) {
        orderMap.put(order.id(), order);
        System.out.println("Print von getOrderMap() aus placeOrder-methode: "+getOrderMap());
    }

    @Override
    public void removeOrder(int id) {
        orderMap.remove(id);
    }

    @Override
    public Order getOrderById(int id) {
        Order order = orderMap.get(id);
        if (order == null) {
            System.out.println("Something went wrong when trying to get order by ID.");
        }
        return order;
    }

    @Override
    public List<Order> getOrderList() {
        return new ArrayList<>(orderMap.values());
    }

    /////////////////////////////////boiler plate methods////////////////////////////////////////////////


    public OrderMapRepo(Map<Integer, Order> orderList) {
        this.orderMap = orderList;
    }

    public Map<Integer, Order> getOrderMap() {
        return orderMap;
    }

    public void setOrderMap(Map<Integer, Order> orderList) {
        this.orderMap = orderList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderMapRepo that = (OrderMapRepo) o;
        return Objects.equals(orderMap, that.orderMap);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderMap);
    }

    @Override
    public String toString() {
        return "OrderMapRepo{" +
                "orderMap=" + orderMap +
                '}';
    }
}
