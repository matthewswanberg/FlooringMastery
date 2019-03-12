/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.dao.StatesDao;

import com.sg.flooring.exceptions.*;
import com.sg.flooring.exceptions.ProductTypePersistenceException;

import com.sg.flooring.model.State;
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
public class StatesDaoFileImpl implements StatesDao {
    
    private Map <String, State> States = new HashMap<>();
    

    private final String STATES_FILE;
    public static final String DELIMITER = ",";

    public StatesDaoFileImpl() {
        STATES_FILE = "Data/states.txt";
    }

    public StatesDaoFileImpl(String productTextFile) {
        STATES_FILE = productTextFile;
    }
    
        private State unmarshallProduct(String stateAsText) {

        String[] stateTokens = stateAsText.split(DELIMITER);

        State stateFromFile = new State();
        stateFromFile.setAbbreviation(stateTokens[0]);
        stateFromFile.setName(stateTokens[1]);
        stateFromFile.setTaxRate(new BigDecimal(stateTokens[2]));

        return stateFromFile;

    }
        
    @Override
    public void loadAllStates() throws StatesPersistenceException {
        Scanner scanner;
            try {
            // Create scanner for reading file
            scanner = new Scanner(
                    new BufferedReader(
                            new FileReader(STATES_FILE)));
            } catch (FileNotFoundException e) {
                throw new StatesPersistenceException("-_- Could not load data into memory.", e);
            }

        String currentLine;
        State currentProduct;

        while (scanner.hasNextLine()) {
            currentLine = scanner.nextLine();
            currentProduct = unmarshallProduct(currentLine);
            States.put(currentProduct.getAbbreviation(), currentProduct);
        }
        scanner.close();
    }
    
    private String marshallProduct(State aState) {

        String productAsText = aState.getAbbreviation() + DELIMITER;

        productAsText += aState.getName()+ DELIMITER;
        productAsText += aState.getTaxRate().toString() + DELIMITER;

        return productAsText;
    }
        
    @Override
    public void saveAllChanges() throws StatesPersistenceException {

        PrintWriter out;

        try {
            out = new PrintWriter(new FileWriter(STATES_FILE));
        } catch (IOException e) {
            throw new StatesPersistenceException("Could not save Product Type Data", e);
        }

        String productAsText;
        List<State> stateList = this.getAllStates();

        for (State currentItem : stateList) {
            productAsText = marshallProduct(currentItem);
            out.println(productAsText);
            out.flush();
        }
        out.close();
    }
        
    
    @Override
    public State addState(State aState) {
        States.put(aState.getAbbreviation(), aState);
        return aState;
    }

    @Override
    public List<State> getAllStates() {
        return new ArrayList<>(States.values());
    }

    @Override
    public State getState(String abbreviation) {
        State aState = States.get(abbreviation);
        return aState;
    }
    
    @Override
    public void updateState(String abbreviation, State changedState) {
        States.replace(abbreviation, changedState);
    }

    @Override
    public State removeState(String abbreviation) {
        State aState = States.get(abbreviation);
        States.remove(abbreviation);
        return aState;
    }
    
}
