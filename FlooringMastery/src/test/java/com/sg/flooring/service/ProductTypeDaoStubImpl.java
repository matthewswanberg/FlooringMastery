/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.service;

import com.sg.flooring.dao.ProductTypeDao;
import com.sg.flooring.exceptions.ProductTypePersistenceException;
import com.sg.flooring.model.ProductType;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author matthewswanberg
 */
public class ProductTypeDaoStubImpl implements ProductTypeDao {
    
    public ProductType onlyProduct;
    
    public ProductTypeDaoStubImpl() {
        onlyProduct = new ProductType();
        onlyProduct.setName("Spaghetti");
        onlyProduct.setCostPerSqFt(BigDecimal.TEN);
        onlyProduct.setLaborPerSqFt(BigDecimal.ONE);
    }
    
    public ProductTypeDaoStubImpl(ProductType testProduct){
        this.onlyProduct = testProduct;
    }

    @Override
    public void loadAllProducts() throws ProductTypePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void saveAllChanges() throws ProductTypePersistenceException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProductType addProduct(ProductType aProduct) {
        if (aProduct.getName().equals(onlyProduct.getName())) {
            return onlyProduct;
        } else {
            return null;
        }
    }

    @Override
    public List<ProductType> getAllProducts() {
        List<ProductType> productList = new ArrayList<>();
        productList.add(onlyProduct);
        return productList;
    }

    @Override
    public ProductType getProduct(String name) {
        if (name.equals(onlyProduct.getName())) {
            return onlyProduct;
        } else {
            return null;
        }
    }

    @Override
    public void updateProduct(String name, ProductType changedProduct) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public ProductType removeProduct(String name) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
