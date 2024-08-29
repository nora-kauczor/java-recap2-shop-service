package org.example;

import java.util.List;
import java.util.Set;

public class ShopService {
// methoden der anderen repos "spiegeln" z.b. um neue bestellung zu platzieren

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

    // einziges was man am ende in main baut

    /////////////////////////////////boiler plate methods////////////////////////////////////////////////


}
