/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.dao;

import com.sg.flooring.exceptions.ModePersistenceException;
import com.sg.flooring.exceptions.OrderPersistenceException;
import com.sg.flooring.exceptions.TrainingModeException;
import com.sg.flooring.model.Order;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import static java.lang.Integer.parseInt;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 *
 * @author matthewswanberg
 */
public class OrderListDaoFileImpl implements OrderListDao {

    private Map<Integer, Order> orders = new HashMap<>();

    private final String ORDERS_FILE;
    private static final String DELIMITER = ",";
    private static final String SETTINGS_DELIMITER = "::";

    public OrderListDaoFileImpl() {
        ORDERS_FILE = "Data/Orders";
    }

    public OrderListDaoFileImpl(String productTextFile) {
        ORDERS_FILE = productTextFile;
    }

    private Order unmarshallOrder(String orderAsText) {

        String[] orderTokens = orderAsText.split(DELIMITER);

        Order orderFromFile = new Order();
        orderFromFile.setOrderNumber(parseInt(orderTokens[0]));
        orderFromFile.setCustomerName(orderTokens[1]);
        orderFromFile.setLocationAbbreviation(orderTokens[2]);
        orderFromFile.setTaxRate(new BigDecimal(orderTokens[3]));
        orderFromFile.setProductName(orderTokens[4]);
        orderFromFile.setArea(new BigDecimal(orderTokens[5]));
        orderFromFile.setCostPerSqFt(new BigDecimal(orderTokens[6]));
        orderFromFile.setLaborPerSqFt(new BigDecimal(orderTokens[7]));
        orderFromFile.setMaterialCost(new BigDecimal(orderTokens[8]));
        orderFromFile.setLaborCost(new BigDecimal(orderTokens[9]));
        orderFromFile.setTaxAmt(new BigDecimal(orderTokens[10]));
        orderFromFile.setTotal(new BigDecimal(orderTokens[11]));

        return orderFromFile;

    }

    @Override
    public void loadAllOrders() throws OrderPersistenceException {
        Scanner scanner;

        // folder for orders
        File folder = new File(ORDERS_FILE);
        // get files from folder
        String[] files = folder.list();

        // iterate over a File, unmarshall and grab date from file name.
        for (String aFile : files) {

            try {
                scanner = new Scanner(
                        new BufferedReader(
                                new FileReader(ORDERS_FILE + "/" + aFile)));
            } catch (FileNotFoundException e) {
                throw new OrderPersistenceException("-_- Could not load data into memory.", e);
            }

            String currentLine;
            Order currentOrder;

            while (scanner.hasNextLine()) {
                currentLine = scanner.nextLine();
                currentOrder = unmarshallOrder(currentLine);
                LocalDate dateForCurrentOrder = LocalDate.parse(aFile.substring(7, 15), DateTimeFormatter.ofPattern("MMddyyyy"));
                currentOrder.setOrderDate(dateForCurrentOrder);
                orders.put(currentOrder.getOrderNumber(), currentOrder);
            }
            scanner.close();

        }

    }

    private String marshallOrder(Order anOrder) {

        String orderAsText = anOrder.getOrderNumber() + DELIMITER;

        orderAsText += anOrder.getCustomerName() + DELIMITER;
        orderAsText += anOrder.getLocationAbbreviation() + DELIMITER;
        orderAsText += anOrder.getTaxRate() + DELIMITER;
        orderAsText += anOrder.getProductName() + DELIMITER;
        orderAsText += anOrder.getArea().toString() + DELIMITER;
        orderAsText += anOrder.getCostPerSqFt().toString() + DELIMITER;
        orderAsText += anOrder.getLaborPerSqFt().toString() + DELIMITER;
        orderAsText += anOrder.getMaterialCost().toString() + DELIMITER;
        orderAsText += anOrder.getLaborCost().toString() + DELIMITER;
        orderAsText += anOrder.getTaxAmt().toString() + DELIMITER;
        orderAsText += anOrder.getTotal().toString() + DELIMITER;

        return orderAsText;
    }

    @Override
    public void saveAllChanges() throws OrderPersistenceException, TrainingModeException, ModePersistenceException {
        try {
            if (getMode().equals("Training")) {
                throw new TrainingModeException("Program is currently in Training Mode and is unable to save changes.");
            }
        } catch (ModePersistenceException e) {
            throw new ModePersistenceException("Could not determine training or production mode.", e);
        }

        // grab Orders from memory
        List<Order> orderList = this.getAllOrders();

        // delete all the files currently in Data/Orders
        File folder = new File(ORDERS_FILE);
        File[] files = folder.listFiles();

        for (File afile : files) {
            afile.delete();
        }

        //initialize array for Order Dates, iterate over orders and add each unique date to array
        List<LocalDate> listOfDates = new ArrayList<>();

        for (Order anOrder : orderList) {

            LocalDate OrderDate = anOrder.getOrderDate();

            if (!listOfDates.contains(OrderDate)) {
                listOfDates.add(OrderDate);
            }
        }

        // create a new file for every date in listOfDate, add Order if Order Date matches date in Array
        for (LocalDate aDate : listOfDates) {
            PrintWriter out;

            try {
                out = new PrintWriter(new FileWriter("Data/Orders/Orders_" + aDate.format(DateTimeFormatter.ofPattern("MMddyyyy")).toString() + ".txt"));
            } catch (IOException e) {
                throw new OrderPersistenceException("Could not save Order List Data", e);
            }

            String orderAsText;

            for (Order currentOrder : orderList) {
                LocalDate currentOrderDate = currentOrder.getOrderDate();
                if (aDate.equals(currentOrderDate)) {
                    orderAsText = marshallOrder(currentOrder);
                    out.println(orderAsText);

                }
                out.flush();
            }

            out.close();
        }

    }

    @Override
    public Order addOrder(Order anOrder) {
        orders.put(anOrder.getOrderNumber(), anOrder);
        return anOrder;
    }

    @Override
    public List<Order> getAllOrders() {
        return new ArrayList<>(orders.values());
    }

    @Override
    public Order getOrder(int orderNumber) {
        Order anOrder = orders.get(orderNumber);
        return anOrder;
    }

    @Override
    public void updateOrder(int orderNumber, Order changedOrder) {
        orders.replace(orderNumber, changedOrder);
    }

    @Override
    public Order removeOrder(int orderNumber) {
        Order anOrder = orders.get(orderNumber);
        orders.remove(orderNumber);
        return anOrder;
    }

    @Override
    public String getMode() throws ModePersistenceException {
        Scanner modeScanner;
        File appSettings = new File("Data/Mode.txt");

        try {
            // Create scanner for reading file
            modeScanner = new Scanner(
                    new BufferedReader(
                            new FileReader(appSettings.getPath())));
        } catch (FileNotFoundException e) {
            throw new ModePersistenceException("-_- Could not load data into memory.", e);
        }

        String modeLine;

        modeLine = modeScanner.nextLine();
        String[] modeTokens = modeLine.split(SETTINGS_DELIMITER);
        String mode = modeTokens[1];
        return mode;
    }

}
