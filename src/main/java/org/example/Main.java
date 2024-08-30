package org.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;

public class Main {
    public static void main(String[] args) {
        ////////////////// Build ShopService //////////////////////////////
        OrderMapRepo myOrderMapRepo = new OrderMapRepo(new HashMap<>());
        Product tv = new Product(149384904, "UltraHD Smart TV", "TechVision", 599.99);
        Product earbuds = new Product(24342421, "Wireless Earbuds", "SoundWave", 79.99);
        Product laptop = new Product(357467674, "Gaming Laptop", "PowerGamer", 1299.99);
        Product blender = new Product(46352246, "Blender Pro", "KitchenMaster", 89.49);
        Product shoes = new Product(59809209, "Running Shoes", "Speedster", 129.99);
        Product apple = new Product(1, "Apple", "Fresh Red Apple", 1.0);
        Product pencil = new Product(2, "Pencil", "HB Graphite Pencil", 2.0);
        Product candyBar = new Product(3, "Candy Bar", "Chocolate Delight", 3.0);
        ProductRepo myProductRepo = new ProductRepo(new ArrayList<>());
        myProductRepo.addProduct(tv);
        myProductRepo.addProduct(earbuds);
        myProductRepo.addProduct(laptop);
        myProductRepo.addProduct(blender);
        myProductRepo.addProduct(shoes);
        myProductRepo.addProduct(apple);
        myProductRepo.addProduct(pencil);
        myProductRepo.addProduct(candyBar);
        Map<Integer, Integer> myInventory = new HashMap<>();
        myInventory.put(tv.id(), 200);
        myInventory.put(earbuds.id(), 100);
        myInventory.put(laptop.id(), 10);
        myInventory.put(blender.id(), 1000);
        myInventory.put(shoes.id(), 3);
        myInventory.put(apple.id(), 50);
        myInventory.put(pencil.id(), 10);
        myInventory.put(candyBar.id(), 30);
        ShopService myShopService = new ShopService(myInventory, myOrderMapRepo, myProductRepo);
        ////////////////// Use ShopService //////////////////////////////
//        System.out.println("Laptops in stock: "+myInventory.get(laptop.id()));
        myShopService.placeOrder(laptop, 8,
                "Maria Kaufmann, Eschenweg 38b, 34820 Zeusenen");
        System.out.println("OrderMapRepo: "+myShopService.getOrderMapRepo().getOrderList());
//        System.out.println("Laptops in stock: "+myInventory.get(laptop.id()));
        myShopService.increaseInventory(laptop, 300);
//        System.out.println("Laptops in stock: "+myInventory.get(laptop.id()));

        Order mariasOrder = myOrderMapRepo.getOrderById(303);
        System.out.println("Maria's order: "+mariasOrder);
//        mariasOrder = mariasOrder.removeOneItemOfProduct(laptop);
//        System.out.println("Maria's order: "+mariasOrder);

//        myShopService.changeOrder(mariasOrder);
//        Order susisOrder = new Order(candyBar, 3,
//                "Susi Sonntag, Hauptstr. 10, 12345 Deppstedt");
//        susisOrder = susisOrder.removeAllItemsOfProduct(candyBar);
//        susisOrder = susisOrder.addProduct(apple, 2);
//        susisOrder = susisOrder.addProduct(pencil, 10);
//        susisOrder = susisOrder.changeQuantity(pencil, 1);


    }


}