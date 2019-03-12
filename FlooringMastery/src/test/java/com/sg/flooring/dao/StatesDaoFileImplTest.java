/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.model.State;
import java.io.FileWriter;
import java.math.BigDecimal;
import java.util.List;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 *
 * @author matthewswanberg
 */
public class StatesDaoFileImplTest {

    StatesDao testDao;

    public StatesDaoFileImplTest() {
    }

    @Before
    public void setUp() throws Exception {
        String testFile = "TestData/testStates.txt";
        new FileWriter(testFile); // Just use the FileWriter to blank the file
        testDao = new StatesDaoFileImpl(testFile);
    }

    State[] testStates = {
        new State("TX", "Texas", new BigDecimal(4.45)),
        new State("WA", "Washington", new BigDecimal(9.25)),
        new State("CA", "California", new BigDecimal(25.00))
    };

    @Test
    public void testaddgetState() {
        State testState = new State("KY", "Kentucky", new BigDecimal("6.00"));
        testDao.addState(testState);

        State shouldBeState = testDao.getState("KY");

        Assert.assertEquals(shouldBeState.getName(), testState.getName());
        Assert.assertEquals(shouldBeState.getAbbreviation(), testState.getAbbreviation());
        Assert.assertEquals(shouldBeState.getTaxRate(), testState.getTaxRate());
    }

    @Test
    public void testGetAllItems() throws Exception {
        //add to Dao
        testDao.addState(testStates[0]);
        testDao.addState(testStates[1]);
        testDao.addState(testStates[2]);

        //retrieve
        List<State> allStates = testDao.getAllStates();

        Assert.assertEquals(3, allStates.size());

        Assert.assertTrue(testDao.getAllStates().contains(testStates[0]));
        Assert.assertTrue(testDao.getAllStates().contains(testStates[1]));
        Assert.assertTrue(testDao.getAllStates().contains(testStates[2]));
    }

    @Test
    public void testRemoveState() throws Exception {
        //Arrange
        testDao.addState(testStates[0]);
        testDao.addState(testStates[1]);
        testDao.addState(testStates[2]);

        //Remove State
        State removedState = testDao.removeState(testStates[0].getAbbreviation());

        //Removed State is first one
        Assert.assertEquals(removedState, testStates[0]);

        //retrieve
        List<State> allStates = testDao.getAllStates();

        Assert.assertEquals(2, allStates.size());

        Assert.assertFalse(allStates.contains(testStates[0]));

    }

}
