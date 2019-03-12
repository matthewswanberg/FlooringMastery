/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.exceptions;

/**
 *
 * @author matthewswanberg
 */
public class StatesPersistenceException extends Exception {

    public StatesPersistenceException(String message) {
        super(message);
    }

    public StatesPersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
