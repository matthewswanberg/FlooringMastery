/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.exceptions.ModePersistenceException;
import com.sg.flooring.exceptions.OrderPersistenceException;
import com.sg.flooring.exceptions.TrainingModeException;
import com.sg.flooring.model.Order;
import java.util.List;

/**
 *
 * @author matthewswanberg
 */
public interface OrderListDao {

    public void loadAllOrders() throws OrderPersistenceException;
    
    public void saveAllChanges() throws OrderPersistenceException, TrainingModeException, ModePersistenceException;
    
    public Order addOrder(Order anOrder);

    public List<Order> getAllOrders();

    public Order getOrder(int orderNumber);
    
    public void updateOrder(int orderNumber, Order changedOrder);

    public Order removeOrder(int orderNumber);
    
    public String getMode() throws ModePersistenceException;

}
