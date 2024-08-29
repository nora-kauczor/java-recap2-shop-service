package org.example;

import java.util.List;

public interface OrderRepo {

    void addOrder(int id);
    void removeOrder(int id);
    Order getOrderById(int id);
    List<Order> getOrderList();
}
