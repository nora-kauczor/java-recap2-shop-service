package org.example;

import java.util.HashMap;
import java.util.Map;

public record Order(
        int id,
        Map<Integer, Product> orderedProducts,
        double amount,
        String deliveryAddress
        ) {



}
