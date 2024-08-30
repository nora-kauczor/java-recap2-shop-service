package org.example;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static org.junit.jupiter.api.Assertions.*;

class ProductRepoTest {

    @Test
    void addProduct() {
        //GIVEN
        Product testProduct = new Product(3, "Candy Bar", "Chocolate Delight",
                3.0);
        ProductRepo testProductRepo = new ProductRepo(new ArrayList<>());
        //WHEN
        int expected = 1;
        testProductRepo.addProduct(testProduct);
        int actual = testProductRepo.getProductList().size();
        //THEN
        assertEquals(expected, actual);
    }


    @Test
    void removeProduct() {
        //GIVEN
        Product testProduct = new Product(3, "Candy Bar", "Chocolate Delight",
                3.0);
        ProductRepo testProductRepo = new ProductRepo(new ArrayList<>());
        testProductRepo.addProduct(testProduct);
        //WHEN
        int expected = 0;
        testProductRepo.removeProduct(testProduct.id());
        int actual = testProductRepo.getProductList().size();
        //THEN
        assertEquals(expected, actual);
    }

    @Test
    void getProductById() {
        //GIVEN
        Product testProduct = new Product(3, "Candy Bar", "Chocolate Delight",
                3.0);
        ProductRepo testProductRepo = new ProductRepo(new ArrayList<>());
        testProductRepo.addProduct(testProduct);
        //WHEN
        Product expected = testProduct;
        Product actual = testProductRepo.getProductById(testProduct.id());
        //THEN
        assertEquals(expected, actual);
    }


}