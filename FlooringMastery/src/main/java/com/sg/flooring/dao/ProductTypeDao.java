/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.exceptions.ProductTypePersistenceException;
import com.sg.flooring.model.ProductType;
import java.util.List;

/**
 *
 * @author matthewswanberg
 */
public interface ProductTypeDao {
    
    public void loadAllProducts() throws ProductTypePersistenceException;
    
    public void saveAllChanges() throws ProductTypePersistenceException;

    public ProductType addProduct(ProductType aProduct);

    public List<ProductType> getAllProducts();

    public ProductType getProduct(String name);
    
    public void updateProduct(String name, ProductType changedProduct);

    public ProductType removeProduct(String name);

}
