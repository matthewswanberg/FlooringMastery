/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.service;

import com.sg.flooring.dao.OrderListDao;
import com.sg.flooring.dao.ProductTypeDao;
import com.sg.flooring.dao.StatesDao;
import com.sg.flooring.exceptions.ProductNotFoundException;
import com.sg.flooring.model.Order;
import com.sg.flooring.model.ProductType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 *
 * @author matthewswanberg
 */
public class FlooringServiceLayerImplTest {

    public FlooringServiceLayer service;

    public OrderListDao orderDao;

    public ProductTypeDao productDao;

    public StatesDao statesDao;

    public FlooringServiceLayerImplTest() {

        ApplicationContext ctx
                = new ClassPathXmlApplicationContext("applicationContext.xml");
        orderDao = ctx.getBean("orderDaoStub", OrderListDao.class);
        productDao = ctx.getBean("productDaoStub", ProductTypeDao.class);
        statesDao = ctx.getBean("statesDaoStub", StatesDao.class);
        service = ctx.getBean("serviceLayer", FlooringServiceLayer.class);

    }

    @Test
    public void getProductPassThrough() {
        try {
            //ARRANGE
            ProductType cloneProduct = new ProductType();
            cloneProduct.setName("Spaghetti");
            cloneProduct.setCostPerSqFt(BigDecimal.TEN);
            cloneProduct.setLaborPerSqFt(BigDecimal.ONE);

            //Act
            ProductType shouldBeSpagh = service.getProduct("Spaghetti");
            Assert.assertNotNull("Getting Spagh should not be null", shouldBeSpagh);
            Assert.assertEquals("Product should be spaghetti", cloneProduct, shouldBeSpagh);

            Assert.assertEquals("Cost per sq ft should be ten", BigDecimal.TEN, shouldBeSpagh.getCostPerSqFt());
        } catch (ProductNotFoundException ex) {
            Logger.getLogger(FlooringServiceLayerImplTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Test
    public void testGetAllOrders() {
        //Arrange
        String DateString = "11/25/2020";
        LocalDate OrderDate = LocalDate.parse(DateString, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        Order orderClone = new Order(OrderDate, "My name", "KY", new BigDecimal(4.5),
                new BigDecimal(4), new BigDecimal(5), "Tile", new BigDecimal(100));
        orderClone.setOrderNumber(420);

        Assert.assertEquals("Should only have one", 1, service.getAllOrders().size());
        Assert.assertTrue("This one shouf be My Name", service.getAllOrders().contains(orderClone));
    }

}
