/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.service;

import com.sg.flooring.dao.OrderListDao;
import com.sg.flooring.exceptions.ModePersistenceException;
import com.sg.flooring.exceptions.OrderPersistenceException;
import com.sg.flooring.model.Order;
import com.sg.flooring.model.ProductType;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matthewswanberg
 */
public class OrderListDaoStubImpl implements OrderListDao{
    
    public Order onlyOrder;
    
    public OrderListDaoStubImpl() {
        
        String DateString = "11/25/2020";
        LocalDate OrderDate = LocalDate.parse(DateString, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        
        onlyOrder = new Order(OrderDate, "My name", "KY", new BigDecimal(4.5), 
            new BigDecimal(4), new BigDecimal(5), "Tile", new BigDecimal(100));
        
        onlyOrder.setOrderNumber(420);      
  
    }
    
    public OrderListDaoStubImpl(Order testOrder){
        this.onlyOrder = testOrder;
    }
    
    

    @Override
    public void loadAllOrders() throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveAllChanges() throws OrderPersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Order addOrder(Order anOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Order> getAllOrders() {
        List<Order> orderList = new ArrayList<>();
        orderList.add(onlyOrder);
        return orderList;
    }

    @Override
    public Order getOrder(int orderNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }


    @Override
    public Order removeOrder(int orderNumber) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void updateOrder(int orderNumber, Order changedOrder) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String getMode() throws ModePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
