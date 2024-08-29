package org.example;

import java.security.Key;
import java.util.*;


public class OrderMapRepo implements OrderRepo {
    private Map<Integer, Order> orderMap = new HashMap<>();

    @Override
    public void addOrder(Order order) {
        orderMap.put(order.id(), order);
    }

    @Override
    public void removeOrder(int id) {
        orderMap.remove(id);
    }

    @Override
    public Order getOrderById(int id) {
        if (orderMap.get(id) == null) {
            System.out.println("Something went wrong when trying to get order.");
        }
        return orderMap.get(id);
    }

//    @Override
//    public Order getOrderById(int searchedId) {
//        Order searchedOrder = null;
//        Set<Integer> orderIds = orderList.keySet();
//        for (int id : orderIds) {
//            if (id == searchedId) {
//                searchedOrder = orderList.get(id);
//            }
//        }
//        if (searchedOrder == null) {
//            System.out.println("Something went wrong when trying to get order.");
//        }
//        return searchedOrder;
//    };

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
