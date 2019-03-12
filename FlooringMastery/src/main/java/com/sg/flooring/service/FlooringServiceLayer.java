/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.service;

import com.sg.flooring.exceptions.ModePersistenceException;
import com.sg.flooring.exceptions.OrderPersistenceException;
import com.sg.flooring.exceptions.ProductTypePersistenceException;
import com.sg.flooring.exceptions.StatesPersistenceException;
import com.sg.flooring.exceptions.NoOrdersFoundException;
import com.sg.flooring.exceptions.ProductNotFoundException;
import com.sg.flooring.exceptions.StateNotFoundException;
import com.sg.flooring.exceptions.TrainingModeException;
import com.sg.flooring.model.Order;
import com.sg.flooring.model.ProductType;
import com.sg.flooring.model.State;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author matthewswanberg
 */
public interface FlooringServiceLayer {
    
    public void createOrder(Order order);
    
    public List<Order> getAllOrders();
    
    public List<Order> getOrdersbyDate(LocalDate date) throws NoOrdersFoundException;
    
    public Order getOrder(int orderNumber);
    
    public void editOrder(int orderNumber, Order changedOrder);
    
    public Order removeOrder(int orderNumber);
    
    public State getState(String abbreviation) throws StateNotFoundException;
    
    public List<State> getAllStates();
    
    public ProductType getProduct(String name) throws ProductNotFoundException;
    
    public List<ProductType> getAllProducts();
    
    public void loadStates() throws StatesPersistenceException;
    
    public void loadProducts() throws ProductTypePersistenceException;
    
    public void loadOrderFiles() throws OrderPersistenceException;
    
    public void saveOrderFiles() throws OrderPersistenceException, TrainingModeException, ModePersistenceException;

    public String findMode() throws ModePersistenceException;
}
