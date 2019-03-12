/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.model.ProductType;
import java.io.FileWriter;
import org.junit.Before;
import org.junit.Test;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import org.junit.Assert;

/**
 *
 * @author matthewswanberg
 */
public class ProductTypeDaoFileImplTest {

    ProductTypeDao testDao;

    public ProductTypeDaoFileImplTest() {
    }

    @Before
    public void setUp() throws Exception {
        String testFile = "TestData/testproduct.txt";
        new FileWriter(testFile); // Just use the FileWriter to blank the file
        testDao = new ProductTypeDaoFileImpl(testFile);
    }

    ProductType[] testProducts = {
        new ProductType("Wood", new BigDecimal(4.35), new BigDecimal(5)),
        new ProductType("Carpet", new BigDecimal(3.20), new BigDecimal(4)),
        new ProductType("Cement", new BigDecimal(5.75), new BigDecimal(2)),
        new ProductType("Dirt", new BigDecimal(1.50), new BigDecimal(.5))
    };
    private List<ProductType> testList = Arrays.asList(testProducts);

    @Test
    public void testAddGetProduct() throws Exception {
        //Create a test input
        ProductType newProduct = new ProductType();
        newProduct.setName("Tile");
        newProduct.setCostPerSqFt(new BigDecimal(3.50));
        newProduct.setLaborPerSqFt(new BigDecimal(4.15));

        //Add Product
        testDao.addProduct(newProduct);

        // Get the product from the Dao
        ProductType retrievedProduct = testDao.getProduct("Tile");

        //Assert
        Assert.assertEquals(newProduct.getName(), retrievedProduct.getName());
        Assert.assertEquals(newProduct.getCostPerSqFt(), retrievedProduct.getCostPerSqFt());
        Assert.assertEquals(newProduct.getLaborPerSqFt(), retrievedProduct.getLaborPerSqFt());

    }

    @Test
    public void testGetAllItems() {
        //add to Dao
        testDao.addProduct(testProducts[0]);
        testDao.addProduct(testProducts[1]);
        testDao.addProduct(testProducts[2]);
        testDao.addProduct(testProducts[3]);

        //retrieve
        List<ProductType> allProducts = testDao.getAllProducts();

        Assert.assertEquals(4, allProducts.size());

        Assert.assertTrue(testDao.getAllProducts().contains(testProducts[0]));
        Assert.assertTrue(testDao.getAllProducts().contains(testProducts[1]));
        Assert.assertTrue(testDao.getAllProducts().contains(testProducts[2]));
        Assert.assertTrue(testDao.getAllProducts().contains(testProducts[3]));

    }

}
