/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.model;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author matthewswanberg
 */
public class Test {
    
    public static void main(String[] args) {
        
        String DateString = "11/25/2020";
        LocalDate OrderDate = LocalDate.parse(DateString, DateTimeFormatter.ofPattern("MM/dd/yyyy"));
        
        
        
        Order newOrder = new Order(OrderDate, "My name", "KY", new BigDecimal(4.5), 
            new BigDecimal(4), new BigDecimal(5), "Tile", new BigDecimal(100));
        
        
        ProductType newProduct = new ProductType("Tile", new BigDecimal(5), new BigDecimal(10));
        
        System.out.println(newProduct.toString());
        
        
        System.out.println(newOrder.toString());
        
        
        
    }
    
    
    
}
