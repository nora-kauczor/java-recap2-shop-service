package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class OrderMapRepoTest {
    @Test
    void addOrder() {
        //GIVEN
        Product testProduct = new Product(3, "Candy Bar", "Chocolate Delight", 3.0);
        Product testProduct2 = new Product(1, "Apple", "Fresh Red Apple", 1.0);
        OrderMapRepo testOrderMapRepo = new OrderMapRepo(new HashMap<>());
        Order testOrder2 = Order.orderSeveralProducts(new HashMap<>(Map.ofEntries(
                Map.entry(testProduct.id(), testProduct), Map.entry(testProduct2.id(), testProduct2))), new HashMap<>(Map.ofEntries(
                Map.entry(testProduct.id(), 5), Map.entry(testProduct2.id(), 1))), "Noch eine Testadresse 568");
        //WHEN
        int expected = 1;
        testOrderMapRepo.addOrder(testOrder2);
        int actual = testOrderMapRepo.getOrderMap().size();
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void removeOrder() {
        //GIVEN
        Product testProduct = new Product(3, "Candy Bar", "Chocolate Delight", 3.0);
        Product testProduct2 = new Product(1, "Apple", "Fresh Red Apple", 1.0);
        OrderMapRepo testOrderMapRepo = new OrderMapRepo(new HashMap<>());
        Order testOrder2 = Order.orderSeveralProducts(new HashMap<>(Map.ofEntries(
                Map.entry(testProduct.id(), testProduct), Map.entry(testProduct2.id(), testProduct2))), new HashMap<>(Map.ofEntries(
                Map.entry(testProduct.id(), 5), Map.entry(testProduct2.id(), 1))), "Noch eine Testadresse 568");
        testOrderMapRepo.addOrder(testOrder2);
        //WHEN
        int expected = 0;
        testOrderMapRepo.removeOrder(testOrder2.id());
        int actual = testOrderMapRepo.getOrderMap().size();
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void getOrderById() {
        //GIVEN
        Product testProduct = new Product(3, "Candy Bar", "Chocolate Delight", 3.0);
        Product testProduct2 = new Product(1, "Apple", "Fresh Red Apple", 1.0);
        OrderMapRepo testOrderMapRepo = new OrderMapRepo(new HashMap<>());
        Order testOrder2 = Order.orderSeveralProducts(new HashMap<>(Map.ofEntries(
                Map.entry(testProduct.id(), testProduct), Map.entry(testProduct2.id(), testProduct2))), new HashMap<>(Map.ofEntries(
                Map.entry(testProduct.id(), 5), Map.entry(testProduct2.id(), 1))), "Noch eine Testadresse 568");
        testOrderMapRepo.addOrder(testOrder2);
        //WHEN
        Order expected = testOrder2;
        Order actual = testOrderMapRepo.getOrderById(testOrder2.id());
        //THEN
        assertEquals(expected, actual);
    }

}