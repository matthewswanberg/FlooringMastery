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
public class TrainingModeException extends Exception {
    
    public TrainingModeException(String message) {
        super(message);
    }

    public TrainingModeException(String message, Throwable cause) {
        super(message, cause);
    }
}
