/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.exceptions.StatesPersistenceException;
import com.sg.flooring.model.State;
import java.util.List;

/**
 *
 * @author matthewswanberg
 */
public interface StatesDao {

  public void loadAllStates() throws StatesPersistenceException;
    
  public void saveAllChanges() throws StatesPersistenceException;
    
    public State addState(State aState);

    public List<State> getAllStates();

    public State getState(String abbreviation);

    public void updateState(String abbreviation, State changedState);

    public State removeState(String abbreviation);
}
