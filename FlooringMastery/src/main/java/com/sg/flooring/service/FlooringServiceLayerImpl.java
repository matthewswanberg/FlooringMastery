/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.service;

import com.sg.flooring.exceptions.NoOrdersFoundException;
import com.sg.flooring.dao.OrderListDao;
import com.sg.flooring.dao.ProductTypeDao;
import com.sg.flooring.dao.StatesDao;
import com.sg.flooring.exceptions.ModePersistenceException;
import com.sg.flooring.exceptions.OrderPersistenceException;
import com.sg.flooring.exceptions.ProductNotFoundException;
import com.sg.flooring.exceptions.ProductTypePersistenceException;
import com.sg.flooring.exceptions.StateNotFoundException;
import com.sg.flooring.exceptions.StatesPersistenceException;
import com.sg.flooring.exceptions.TrainingModeException;
import com.sg.flooring.model.Order;
import com.sg.flooring.model.ProductType;
import com.sg.flooring.model.State;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matthewswanberg
 */
public class FlooringServiceLayerImpl implements FlooringServiceLayer{
    
    private OrderListDao orderDao;
    private ProductTypeDao productDao;
    private StatesDao statesDao;
    
    public FlooringServiceLayerImpl(OrderListDao orderDao, ProductTypeDao productDao, StatesDao statesDao){
        this.orderDao = orderDao;
        this.productDao = productDao;
        this.statesDao = statesDao;
    }


    @Override
    public void createOrder(Order order) {
        orderDao.addOrder(order);
        }

    @Override
    public List<Order> getAllOrders() {
        return orderDao.getAllOrders();
    }
    
    @Override
    public List<Order> getOrdersbyDate(LocalDate date) throws NoOrdersFoundException {
        List<Order> ordersByDate = new ArrayList<>();
        List<Order> orderList = orderDao.getAllOrders();
        for (Order anOrder : orderList) {
            if(anOrder.getOrderDate().equals(date)){
                ordersByDate.add(anOrder);
            }
           } return ordersByDate;
        
    }

    @Override
    public Order getOrder(int orderNumber) {
        return orderDao.getOrder(orderNumber);
    }
    
    @Override
    public void editOrder(int orderNumber, Order changedOrder){
        orderDao.updateOrder(orderNumber, changedOrder);
    }

    @Override
    public Order removeOrder(int orderNumber) {
        Order removedOrder;
        removedOrder = orderDao.getOrder(orderNumber);
        orderDao.removeOrder(orderNumber);
        return removedOrder;    
    }

    @Override
    public State getState(String abbreviation) throws StateNotFoundException {
        
        if (statesDao.getState(abbreviation.toUpperCase()) == null){
            throw new StateNotFoundException ("Location not available.");
        }
        return statesDao.getState(abbreviation.toUpperCase());
    }
    
    @Override
    public List<State> getAllStates() {
        return statesDao.getAllStates();
    }

    @Override
    public ProductType getProduct(String name) throws ProductNotFoundException{
        
        if (productDao.getProduct(name) == null){
            throw new ProductNotFoundException("Product not available.");
        }
        
        return productDao.getProduct(name);
    }
    
    @Override
    public List<ProductType> getAllProducts() {
        return productDao.getAllProducts();
    }
    
    @Override
    public void loadStates() throws StatesPersistenceException {
        statesDao.loadAllStates();
    }
    
    @Override
    public void loadProducts() throws ProductTypePersistenceException {
        productDao.loadAllProducts();
    }
    
    @Override
    public void loadOrderFiles() throws OrderPersistenceException{
        orderDao.loadAllOrders();
    }
    
    @Override
    public void saveOrderFiles() throws OrderPersistenceException, TrainingModeException, ModePersistenceException {
        orderDao.saveAllChanges();
    }
    
    @Override
    public String findMode() throws ModePersistenceException {
        return orderDao.getMode();    
    }
    
}
