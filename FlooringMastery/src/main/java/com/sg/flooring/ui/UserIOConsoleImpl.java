/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.ui;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 *
 * @author matthewswanberg
 */
public class UserIOConsoleImpl implements UserIO {

    final private Scanner console = new Scanner(System.in);

    /**
     *
     * A very simple method that takes in a message to display on the console
     * and then waits for a integer answer from the user to return.
     *
     * @param msg - String of information to display to the user.
     *
     */
    @Override
    public void print(String msg) {
        System.out.println(msg);
    }

    /**
     *
     * A simple method that takes in a message to display on the console, and
     * then waits for an answer from the user to return.
     *
     * @param msgPrompt - String explaining what information you want from the
     * user.
     * @return the answer to the message as string
     */
    @Override
    public String readString(String msgPrompt) {
        System.out.println(msgPrompt);
        return console.nextLine();
    }

    /**
     *
     * A simple method that takes in a message to display on the console, and
     * then waits for a integer answer from the user to return.
     *
     * @param msgPrompt - String explaining what information you want from the
     * user.
     * @return the answer to the message as integer
     */
    @Override
    public int readInt(String msgPrompt) {
        boolean invalidInput = true;
        int num = 0;
        while (invalidInput) {
            try {
                // print the message msgPrompt (ex: asking for the # of cats!)
                String stringValue = this.readString(msgPrompt);
                // Get the input line, and try and parse
                num = Integer.parseInt(stringValue); // if it's 'bob' it'll break
                invalidInput = false; // or you can use 'break;'
            } catch (NumberFormatException e) {
                // If it explodes, it'll go here and do this.
                this.print("Input error. Please try again.");
            }
        }
        return num;
    }

    /**
     *
     * A slightly more complex method that takes in a message to display on the
     * console, and then waits for a integer answer within the specified min/max
     * range and returns it.
     *
     * @param msgPrompt - String explaining what information you want from the
     * user.
     * @param min - minimum acceptable value for return
     * @param max - maximum acceptable value for return
     * @return an integer value as an answer to the message prompt within the
     * min/max range
     */
    @Override
    public int readInt(String msgPrompt, int min, int max) {
        int result;
        do {
            result = readInt(msgPrompt);
        } while (result < min || result > max);

        return result;
    }

    /**
     *
     * A simple method that takes in a message to display on the console, and
     * then waits for a long answer from the user to return.
     *
     * @param msgPrompt - String explaining what information you want from the
     * user.
     * @return the answer to the message as long
     */
    @Override
    public long readLong(String msgPrompt) {
        while (true) {
            try {
                return Long.parseLong(this.readString(msgPrompt));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }

    /**
     * A slightly more complex method that takes in a message to display on the
     * console, and then waits for a long answer within the specified min/max
     * range and returns it.
     *
     * @param msgPrompt - String explaining what information you want from the
     * user.
     * @param min - minimum acceptable value for return
     * @param max - maximum acceptable value for return
     * @return an long value as an answer to the message prompt within the
     * min/max range
     */
    @Override
    public long readLong(String msgPrompt, long min, long max) {
        long result;
        do {
            result = readLong(msgPrompt);
        } while (result < min || result > max);

        return result;
    }

    /**
     *
     * A simple method that takes in a message to display on the console, and
     * then waits for a float answer from the user to return.
     *
     * @param msgPrompt - String explaining what information you want from the
     * user.
     * @return the answer to the message as float
     */
    @Override
    public float readFloat(String msgPrompt) {
        while (true) {
            try {
                return Float.parseFloat(this.readString(msgPrompt));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }

    /**
     *
     * A slightly more complex method that takes in a message to display on the
     * console, and then waits for a float answer within the specified min/max
     * range and returns it.
     *
     * @param msgPrompt - String explaining what information you want from the
     * user.
     * @param min - minimum acceptable value for return
     * @param max - maximum acceptable value for return
     * @return an float value as an answer to the message prompt within the
     * min/max range
     */
    @Override
    public float readFloat(String msgPrompt, float min, float max) {
        float result;
        do {
            result = readFloat(msgPrompt);
        } while (result < min || result > max);

        return result;
    }

    /**
     *
     * A simple method that takes in a message to display on the console, and
     * then waits for a double answer from the user to return.
     *
     * @param msgPrompt - String explaining what information you want from the
     * user.
     * @return the answer to the message as double
     */
    @Override
    public double readDouble(String msgPrompt) {
        while (true) {
            try {
                return Double.parseDouble(this.readString(msgPrompt));
            } catch (NumberFormatException e) {
                this.print("Input error. Please try again.");
            }
        }
    }

    /**
     *
     * A slightly more complex method that takes in a message to display on the
     * console, and then waits for a double answer within the specified min/max
     * range and returns it.
     *
     * @param msgPrompt - String explaining what information you want from the
     * user.
     * @param min - minimum acceptable value for return
     * @param max - maximum acceptable value for return
     * @return an double value as an answer to the message prompt within the
     * min/max range
     */
    @Override
    public double readDouble(String msgPrompt, double min, double max) {
        double result;
        do {
            result = readDouble(msgPrompt);
        } while (result < min || result > max);
        return result;
    }

    @Override
    public LocalDate readDate(String msgPrompt) {
        boolean invalidInput = true;
        LocalDate validDate = null;
        while (invalidInput) {
            try {
                // print the message msgPrompt (ex: asking for the # of cats!)
                String stringValue = this.readString(msgPrompt);
                // Get the input line, and try and parse
                validDate = LocalDate.parse(stringValue, DateTimeFormatter.ofPattern("MM/dd/yyyy")); // if it's 'bob' it'll break
                invalidInput = false; // or you can use 'break;'
            } catch (DateTimeParseException e) {
                // If it explodes, it'll go here and do this.
                this.print("Input error. Date must follow this format: MM/DD/YYYY. Please try again.");
                invalidInput = true;
            }
        }
        return validDate;
    }

}
