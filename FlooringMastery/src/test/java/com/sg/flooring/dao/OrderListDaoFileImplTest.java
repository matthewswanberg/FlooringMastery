/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.model.Order;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author matthewswanberg
 */
public class OrderListDaoFileImplTest {
    
    OrderListDao testDao;
    
    public OrderListDaoFileImplTest() {
    }
    
    @Before
    public void setUp() throws Exception {
        String testFile = "TestData/OrdersTest/testOrders.txt";
        new FileWriter(testFile); // Just use the FileWriter to blank the file
        testDao = new OrderListDaoFileImpl(testFile);
    }



    @Test
    public void testAddGetOrder() throws Exception {
        //creating test order
        String DateString = "11/25/2020";
        LocalDate OrderDate = LocalDate.parse(DateString, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Order newOrder = new Order(OrderDate, "My name", "KY", new BigDecimal(4.5), 
            new BigDecimal(4), new BigDecimal(5), "Tile", new BigDecimal(100));
            newOrder.setOrderNumber(1);
        
        //add it
        testDao.addOrder(newOrder);
        
        // Get the product from the Dao
        Order retrievedOrder = testDao.getOrder(1);
        
        //Assert
        Assert.assertEquals(newOrder.getOrderNumber(), retrievedOrder.getOrderNumber());
        Assert.assertEquals(newOrder.getTotal(), retrievedOrder.getTotal());
        Assert.assertEquals(newOrder.getTaxRate(), retrievedOrder.getTaxRate());
        
    }
    
//    @Test
//    public List<Order> testGetAllOrders() {
        // arrange
       // private Map<Integer, Order> testOrders = new HashMap<>();
        
        
  
    
    
    
}
