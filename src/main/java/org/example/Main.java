package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        OrderMapRepo myOrderMapRepo = new OrderMapRepo(new HashMap<>());
        ShopService myShopService = new ShopService(myOrderMapRepo);
        Product tv = new Product(149384904, "UltraHD Smart TV", "TechVision", 599.99);
        Product earbuds = new Product(24342421, "Wireless Earbuds", "SoundWave", 79.99);
        Product laptop = new Product(357467674, "Gaming Laptop", "PowerGamer", 1299.99);
        Product blender = new Product(46352246, "Blender Pro", "KitchenMaster", 89.49);
        Product shoes = new Product(59809209, "Running Shoes", "Speedster", 129.99);
        Product apple = new Product(1, "Apple", "Fresh Red Apple", 1.0);
        Product pencil = new Product(2, "Pencil", "HB Graphite Pencil", 2.0);
        Product candyBar = new Product(3, "Candy Bar", "Chocolate Delight", 3.0);


        Order mariasOrder = new Order(laptop, 8,
                "Maria Kaufmann, Eschenweg 38b, 34820 Zeusenen");
        Order susisOrder = new Order(candyBar, 3,
                "Susi Sonntag, Hauptstr. 10, 12345 Deppstedt");
        susisOrder = susisOrder.removeAllItemsOfProduct(candyBar);
        susisOrder = susisOrder.addProduct(apple, 2);
        susisOrder = susisOrder.addProduct(pencil, 10);
        susisOrder = susisOrder.changeQuantity(pencil, 1);
        System.out.println(susisOrder);
    }
}