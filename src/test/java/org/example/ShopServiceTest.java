package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class ShopServiceTest {

    @Test
    void decreaseInventory() {
        //GIVEN
        Product testProduct = new Product(3, "Candy Bar", "Chocolate Delight", 3.0);
        Map<Integer, Integer> testInventory = new HashMap<>();
        testInventory.put(testProduct.id(), 200);
        ShopService testShopService = new ShopService(testInventory, null, null);
        //WHEN
        int expected = 100;
        testShopService.decreaseInventory(testProduct, 100);
        int actual = testInventory.get(testProduct.id());
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void increaseInventory() {
        //GIVEN
        Product testProduct = new Product(3, "Candy Bar", "Chocolate Delight", 3.0);
        Map<Integer, Integer> testInventory = new HashMap<>();
        testInventory.put(testProduct.id(), 200);
        ShopService testShopService = new ShopService(testInventory, null, null);
        //WHEN
        int expected = 300;
        testShopService.increaseInventory(testProduct, 100);
        int actual = testInventory.get(testProduct.id());
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void changeOrder() {
        //GIVEN
        Product testProduct = new Product(3, "Candy Bar", "Chocolate Delight", 3.0);
        Product testProduct2 = new Product(1, "Apple", "Fresh Red Apple", 1.0);
        ProductRepo testProductRepo = new ProductRepo(new ArrayList<>());
        testProductRepo.addProduct(testProduct);
        testProductRepo.addProduct(testProduct2);
        OrderMapRepo testOrderMapRepo = new OrderMapRepo(new HashMap<>());
        Order testOrder2 = Order.orderSeveralProducts(new HashMap<>(Map.ofEntries(
                Map.entry(testProduct.id(), testProduct), Map.entry(testProduct2.id(), testProduct2))), new HashMap<>(Map.ofEntries(
                Map.entry(testProduct.id(), 5), Map.entry(testProduct2.id(), 1))), "Noch eine Testadresse 568");
        testOrderMapRepo.addOrder(testOrder2);
        Map<Integer, Integer> testInventory = new HashMap<>();
        testInventory.put(testProduct2.id(), 100);
        ShopService testShopService = new ShopService(testInventory, testOrderMapRepo, testProductRepo);
        //WHEN
        Map<Integer, Integer> newQuantities = new HashMap<>(Map.ofEntries(Map.entry(testProduct.id(), 5), Map.entry(testProduct2.id(), 4)));
        testShopService.changeOrder(testOrder2, testOrder2.orderedProducts(), newQuantities);
        int expected = 4;
        int actual = testShopService.getOrderMapRepo().getOrderMap().get(testOrder2.id()).quantities().get(testProduct2.id());
        //THEN
        assertEquals(expected, actual);
    }


    @Test
    void placeOrder_singleProduct() {
        //GIVEN
        Product testProduct = new Product(3, "Candy Bar", "Chocolate Delight", 3.0);
        Product testProduct2 = new Product(1, "Apple", "Fresh Red Apple", 1.0);
        ProductRepo testProductRepo = new ProductRepo(new ArrayList<>());
        testProductRepo.addProduct(testProduct);
        OrderMapRepo testOrderMapRepo = new OrderMapRepo(new HashMap<>());
        Map<Integer, Integer> testInventory = new HashMap<>();
        testInventory.put(testProduct.id(), 200);
        ShopService testShopService = new ShopService(testInventory, testOrderMapRepo, testProductRepo);
        // WHEN
        int expected = 1;
        testShopService.placeOrder(testProduct, 2, "Testadresse 123");
        int actual = testShopService.getOrderMapRepo().getOrderMap().keySet().size();
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void placeOrder_severalProducts() {
        //GIVEN
        Product testProduct = new Product(3, "Candy Bar", "Chocolate Delight", 3.0);
        Product testProduct2 = new Product(1, "Apple", "Fresh Red Apple", 1.0);
        ProductRepo testProductRepo = new ProductRepo(new ArrayList<>());
        testProductRepo.addProduct(testProduct);
        testProductRepo.addProduct(testProduct2);
        OrderMapRepo testOrderMapRepo = new OrderMapRepo(new HashMap<>());
        Map<Integer, Integer> testInventory = new HashMap<>();
        testInventory.put(testProduct.id(), 200);
        testInventory.put(testProduct2.id(), 100);
        ShopService testShopService = new ShopService(testInventory, testOrderMapRepo, testProductRepo);
        //WHEN
        int expected = 1;
        Map<Integer, Product> orderedProducts = new HashMap<>(Map.ofEntries(
                Map.entry(testProduct.id(), testProduct), Map.entry(testProduct2.id(), testProduct2)));
        Map<Integer, Integer> quantities = new HashMap<>(Map.ofEntries(
                Map.entry(testProduct.id(), 5), Map.entry(testProduct2.id(), 1)));
        testShopService.placeOrder(orderedProducts, quantities, "Testadresse 123");
        int actual = testShopService.getOrderMapRepo().getOrderMap().keySet().size();
        System.out.println(testShopService.getOrderMapRepo().getOrderMap().keySet().size());
        //THEN
        assertEquals(expected, actual);
    }
}