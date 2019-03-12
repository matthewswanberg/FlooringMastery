/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.exceptions;

import java.io.FileNotFoundException;

/**
 *
 * @author matthewswanberg
 */
public class ModePersistenceException extends Exception {

    public ModePersistenceException(String message) {
        super(message);
    }

    public ModePersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
