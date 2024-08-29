package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        OrderMapRepo myOrderMapRepo = new OrderMapRepo(new HashMap<>());
        ShopService myShopService = new ShopService(myOrderMapRepo);
        // Create products
        Product tv = new Product(149384904, "UltraHD Smart TV", "TechVision", 599.99);
        Product earbuds = new Product(24342421, "Wireless Earbuds", "SoundWave", 79.99);
        Product laptop = new Product(357467674, "Gaming Laptop", "PowerGamer", 1299.99);
        Product blender = new Product(46352246, "Blender Pro", "KitchenMaster", 89.49);
        Product shoes = new Product(59809209, "Running Shoes", "Speedster", 129.99);
        // Create ProductRepo and add Products to it
        ProductRepo myProducts = new ProductRepo(new ArrayList<>());
        myProducts.addProduct(tv);
        myProducts.addProduct(earbuds);
        myProducts.addProduct(laptop);
        myProducts.addProduct(blender);
        myProducts.addProduct(shoes);
        // Create order
//        Order tvAndBlenderOrder = new Order(new Random().nextInt(1000),
//                new HashMap<>(Map.ofEntries(Map.entry(tv.id(), tv), Map.entry(blender.id(), blender))),
//                0.00, "Susi Meier, Hauptstr. 10, 12345 Deppenstedt");
        // Add price
//        tvAndBlenderOrder = tvAndBlenderOrder.orderWithAmount(tv.price() + blender.price());
//        // Create another order
//        Map<Integer, Product> earbudsShoesBlender = new HashMap<>();
//        earbudsShoesBlender.put(earbuds.id(), earbuds);
//        earbudsShoesBlender.put(shoes.id(), shoes);
//        earbudsShoesBlender.put(blender.id(), blender);
//        double earbudsShoesBlenderAmount = earbuds.price() + shoes.price() + blender.price();
//        Order earbudsShoesBlenderOrder = new Order(new Random().nextInt(1000), earbudsShoesBlender, earbudsShoesBlenderAmount, "Markus Meier, Sonntagsgasse 10, 34890 Heidenheim");
//        // Add orders to OrderMapRepo
//        myOrderMapRepo.getOrderMap().put(tvAndBlenderOrder.id(), tvAndBlenderOrder);
//        myOrderMapRepo.addOrder(earbudsShoesBlenderOrder);


        Order mariasOrder = new Order(laptop, 8,
                "Maria Kaufmann, Eschenweg 38b, 34820 Zeusenen");
        System.out.println(mariasOrder);

    }
}