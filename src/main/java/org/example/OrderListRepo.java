package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class OrderListRepo implements OrderRepo {
    private List<Order> orderList = new ArrayList<>();

    @Override
    public void addOrder(Order order
    ) {
        orderList.add(order);
    }

    @Override
    public void removeOrder(int id) {
        Order order = getOrderById(id);
        orderList.remove(order);
    }

    @Override
    public Order getOrderById(int id) {
        Order searchedOrder = null;
        for (Order order : orderList) {
            if (order.id() == id) {
                searchedOrder = order;
            }
        }
        if (searchedOrder == null) {
            System.out.println("Something went wrong when trying to get order.");
        }
        return searchedOrder;
    };

    /////////////////////////////////boiler plate methods////////////////////////////////////////////////

    public OrderListRepo(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public List<Order> getOrderList() {
        return orderList;
    }

    public void setOrderList(List<Order> orderList) {
        this.orderList = orderList;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OrderListRepo that = (OrderListRepo) o;
        return Objects.equals(orderList, that.orderList);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(orderList);
    }

    @Override
    public String toString() {
        return "OrderListRepo{" +
                "orderList=" + orderList +
                '}';
    }
}
