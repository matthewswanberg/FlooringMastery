/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.exceptions.ProductTypePersistenceException;
import com.sg.flooring.model.ProductType;
import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author matthewswanberg
 */
public class ProductTypeDaoFileImpl implements ProductTypeDao {

    private Map<String, ProductType> Products = new HashMap<>();

    private final String PRODUCT_TYPE_FILE;
    public static final String DELIMITER = ",";

    public ProductTypeDaoFileImpl() {
        PRODUCT_TYPE_FILE = "Data/products.txt";
    }

    public ProductTypeDaoFileImpl(String productTextFile) {
        PRODUCT_TYPE_FILE = productTextFile;
    }

    private ProductType unmarshallProduct(String productAsText) {

        String[] itemTokens = productAsText.split(DELIMITER);

        ProductType productFromFile = new ProductType();
        productFromFile.setName(itemTokens[0]);
        productFromFile.setCostPerSqFt(new BigDecimal(itemTokens[1]));
        productFromFile.setLaborPerSqFt(new BigDecimal(itemTokens[2]));

        return productFromFile;

    }

    @Override
    public void loadAllProducts() throws ProductTypePersistenceException {
        Scanner scanner;
        try {
            // Create scanner for reading file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(PRODUCT_TYPE_FILE)));
        } catch (FileNotFoundException e) {
            throw new ProductTypePersistenceException("-_- Could not load data into memory.", e);
        }

        String currentLine;
        ProductType currentProduct;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentProduct = unmarshallProduct(currentLine);
            Products.put(currentProduct.getName(), currentProduct);
        }
        scanner.close();
    }

    private String marshallProduct(ProductType aProduct) {

        String productAsText = aProduct.getName() + DELIMITER;

        productAsText += aProduct.getCostPerSqFt().toString() + DELIMITER;
        productAsText += aProduct.getLaborPerSqFt().toString() + DELIMITER;

        return productAsText;
    }

    @Override
    public void saveAllChanges() throws ProductTypePersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(PRODUCT_TYPE_FILE));
        } catch (IOException e) {
            throw new ProductTypePersistenceException("Could not save Product Type Data", e);
        }

        String productAsText;
        List<ProductType> productList = this.getAllProducts();

        for (ProductType currentItem : productList) {
            productAsText = marshallProduct(currentItem);
            out.println(productAsText);
            out.flush();
        }
        out.close();
    }

    @Override
    public ProductType addProduct(ProductType aProduct) {
        Products.put(aProduct.getName(), aProduct);
        return aProduct;
    }

    @Override
    public List<ProductType> getAllProducts() {
        return new ArrayList<>(Products.values());
    }

    @Override
    public ProductType getProduct(String name) {
        ProductType aProduct = Products.get(name);
        return aProduct;
    }

    @Override
    public void updateProduct(String name, ProductType changedProduct) {
        Products.replace(name, changedProduct);
    }

    @Override
    public ProductType removeProduct(String name) {
        ProductType aProduct = Products.get(name);
        Products.remove(name);
        return aProduct;
    }

}
