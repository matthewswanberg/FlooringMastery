/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.controller;

import com.sg.flooring.exceptions.ModePersistenceException;
import com.sg.flooring.exceptions.NoOrdersFoundException;
import com.sg.flooring.exceptions.OrderPersistenceException;
import com.sg.flooring.exceptions.ProductNotFoundException;
import com.sg.flooring.exceptions.ProductTypePersistenceException;
import com.sg.flooring.exceptions.StateNotFoundException;
import com.sg.flooring.exceptions.StatesPersistenceException;
import com.sg.flooring.exceptions.TrainingModeException;
import com.sg.flooring.model.Order;
import com.sg.flooring.model.ProductType;
import com.sg.flooring.model.State;
import com.sg.flooring.service.FlooringServiceLayer;
import com.sg.flooring.ui.FlooringView;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author matthewswanberg
 */
public class FlooringController {

    private FlooringServiceLayer service;
    private FlooringView view;

    public FlooringController(FlooringServiceLayer service, FlooringView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {

        view.welcome();

        try {
            if (service.findMode().equals("Training")) {
                view.printTrainingMode();
            }
        } catch (ModePersistenceException ex) {
            view.displayErrorMessage(ex.getMessage());
        }

        boolean keepGoing = true;
        int menuSelection = 0;

        try {
            service.loadProducts();
            service.loadStates();
            service.loadOrderFiles();
        } catch (StatesPersistenceException ex) {
            view.displayErrorMessage(ex.getMessage());
            keepGoing = false;
        } catch (ProductTypePersistenceException ex) {
            view.displayErrorMessage(ex.getMessage());
            keepGoing = false;
        } catch (OrderPersistenceException ex) {
            view.displayErrorMessage(ex.getMessage());
            keepGoing = false;
        }

        while (keepGoing) {

            menuSelection = getMenuSelection();

            switch (menuSelection) {
                case 1:
                    displayOrder();
                    break;
                case 2:
                    addOrder();
                    break;
                case 3:
                    editOrder();
                    break;
                case 4:
                    removeOrder();
                    break;
                case 5:
                    save();
                    break;
                case 6:
                    quit();
                    keepGoing = false;
                    break;
                default:
                    view.unknownCommand();
            }
        }
        view.exitMessage();
    }

    private int getMenuSelection() {

        return view.printMenuAndGetSelection();
    }

    private void displayOrder() {
        try {
            LocalDate ordersToDisplay = view.getDisplayOrdersDate();
            List<Order> ordersByDate = service.getOrdersbyDate(ordersToDisplay);
            view.diplayOrders(ordersByDate, ordersToDisplay);
        } catch (NoOrdersFoundException ex) {
            view.displayErrorMessage(ex.getMessage());
        }

    }

    private void addOrder() {
        view.displayNewOrderBanner();

        // New Order Date, needs validation
        boolean inValidDateInput;
        LocalDate newOrderDate;
        do {
            newOrderDate = view.getNewOrderDate();
            if (newOrderDate.isBefore(LocalDate.now())) {
                inValidDateInput = true;
            } else {
                inValidDateInput = false;
            }
        } while (inValidDateInput);

        // New Order Customer Name
        boolean inValidNameInput;
        String customerName;

        //check for valid name
        do {
            customerName = view.getNewCustomerName();
            if (customerName.isEmpty()) {
                inValidNameInput = true;
            } else {
                inValidNameInput = false;
            }
        } while (inValidNameInput);

        // Location Abbreviation to retrieve location
        boolean invalidStateInput = true;
        boolean invalidProductInput = true;
        List<State> controllerStateList = service.getAllStates();
        view.displayListOfLocations(controllerStateList);
        // check for valid state
        do {
            try {
                String LocationCode = view.getNewLocationAbbrev();
                State Location = service.getState(LocationCode);

                BigDecimal taxRate = Location.getTaxRate();
                invalidStateInput = false;

                // Product Name to get product
                List<ProductType> controllerProductList = service.getAllProducts();
                view.displayListOfProducts(controllerProductList);

                // check for valid product
                do {
                    try {
                        String name = view.getNewProductTypeName();
                        ProductType aProduct = service.getProduct(name);
                        BigDecimal materialSqFt = aProduct.getCostPerSqFt();
                        BigDecimal laborSqFt = aProduct.getLaborPerSqFt();
                        invalidProductInput = false;

                        // Project area
                        BigDecimal area = view.getNewArea();

                        Order customerOrder = new Order(newOrderDate, customerName, LocationCode, taxRate, materialSqFt, laborSqFt, name, area);

                        view.proposedOrderSummary(customerOrder);
                        int placement = view.getOrderPlacementConfirmation();
                        // confirm order placement
                        if (placement == 1) {
                            int orderNumber = makeOrderNumber();
                            customerOrder.setOrderNumber(orderNumber);

                            // grab order number from service layer
                            service.createOrder(customerOrder);
                            view.orderHasBeenPlaced(orderNumber);

                        }
                    } catch (ProductNotFoundException ex) {
                        view.displayErrorMessage(ex.getMessage());
                    }
                } while (invalidProductInput);

            } catch (StateNotFoundException ex) {
                view.displayErrorMessage(ex.getMessage());
            }
        } while (invalidStateInput);

    }

    private void editOrder() {
        view.displayEditBanner();
        try {
            // show orders by date
            LocalDate ordersToDisplayDate = view.getEditOrdersDate();
            List<Order> ordersByDate = service.getOrdersbyDate(ordersToDisplayDate);
            if (ordersByDate.isEmpty()) {
                view.displayNoOrdersListed(ordersToDisplayDate);
                return;
            }

            int orderNumberForEdit = view.getEditNumber();

            Order originalOrder = service.getOrder(orderNumberForEdit);

            if (!ordersByDate.contains(originalOrder)) {
                throw new NoOrdersFoundException("Could not find order.");
            }

            view.orderInfo(originalOrder);
            // Customer Name
            view.editInstructions();
            String customerName = view.getEditCustomerName(originalOrder.getCustomerName());
            if (customerName.isEmpty()) {
                customerName = originalOrder.getCustomerName();
            }

            boolean invalidStateInput = true;
            boolean invalidProductInput = true;
            List<State> controllerLocationListforEdit = service.getAllStates();
            view.displayListOfLocations(controllerLocationListforEdit);
            // check state input
            do {
                try {

                    // Location Abbreviation to retrieve location
                    String LocationCode = view.getEditLocationAbbrev(originalOrder.getLocationAbbreviation());
                    if (LocationCode.isEmpty()) {
                        LocationCode = originalOrder.getLocationAbbreviation();
                    }
                    State Location = service.getState(LocationCode);
                    BigDecimal taxRate = Location.getTaxRate();
                    invalidStateInput = false;

                    // Product Name to get product
                    List<ProductType> controllerProductListForEdit = service.getAllProducts();
                    view.displayListOfProducts(controllerProductListForEdit);

                    // check product input
                    do {
                        try {
                            String name = view.getEditProductTypeName(originalOrder.getProductName());
                            if (name.isEmpty()) {
                                name = originalOrder.getProductName();
                            }
                            ProductType aProduct = service.getProduct(name);
                            BigDecimal materialSqFt = aProduct.getCostPerSqFt();
                            BigDecimal laborSqFt = aProduct.getLaborPerSqFt();
                            invalidProductInput = false;

                            // Project area
                            String areaString = view.getEditArea(originalOrder.getArea());
                            BigDecimal area;
                            if (areaString.isEmpty()) {
                                area = originalOrder.getArea();
                            } else {
                                area = new BigDecimal(areaString);
                            }

                            Order updatedOrder = new Order(originalOrder.getOrderDate(), customerName, LocationCode, taxRate, materialSqFt, laborSqFt, name, area);
                            updatedOrder.setOrderNumber(orderNumberForEdit);
                            view.proposedOrderSummary(updatedOrder);
                            int placement = view.getEditFinishedConfirmation();
                            if (placement == 1) {
                                service.editOrder(orderNumberForEdit, updatedOrder);
                                view.editCompleted(orderNumberForEdit);

                            }

                        } catch (ProductNotFoundException ex) {
                            view.displayErrorMessage(ex.getMessage());
                        }
                    } while (invalidProductInput);

                } catch (StateNotFoundException ex) {
                    view.displayErrorMessage(ex.getMessage());
                }
            } while (invalidStateInput);

        } catch (NoOrdersFoundException ex) {
            view.displayErrorMessage(ex.getMessage());

        }
    }

    private void removeOrder() {
        view.displayRemoveBanner();
        // check for orders on this date
        try {
            LocalDate ordersToDisplayDate = view.getDeleteOrdersDate();
            List<Order> ordersByDate = service.getOrdersbyDate(ordersToDisplayDate);
            if (ordersByDate.isEmpty()) {
                view.displayNoOrdersListed(ordersToDisplayDate);
                return;
            }

            int orderNumberForDelete = view.getDeleteNumber();
            Order OrderToDelete = service.getOrder(orderNumberForDelete);
            if (!ordersByDate.contains(OrderToDelete)) {
                throw new NoOrdersFoundException("Could not find order.");
            }

            view.orderInfo(OrderToDelete);
            int finaldelete = view.finalDeletionConfirmation();
            if (finaldelete == 1) {
                service.removeOrder(orderNumberForDelete);
                view.deleteCompleted(orderNumberForDelete);
            } else {
                view.deleteCancelled(orderNumberForDelete);
            }

        } catch (NoOrdersFoundException ex) {
            view.displayErrorMessage(ex.getMessage());
        }

    }

    private void save() {
        view.displaySaveBanner();
        int save = view.SaveChangesConfirmation();

        // confirm save changes
        if (save == 1) {
            try {
                service.saveOrderFiles();
                view.changesSaved();
            } catch (OrderPersistenceException ex) {
                view.displayErrorMessage(ex.getMessage());
            } catch (TrainingModeException ex) {
                view.displayErrorMessage("Program is currently in Training Mode and is unable to save changes.");
            } catch (ModePersistenceException ex) {
                view.displayErrorMessage(ex.getMessage());
            }
        }

    }

    // helper method for making order numbers
    private int makeOrderNumber() {
        List<Order> orderList;
        orderList = service.getAllOrders();
        if (service.getAllOrders().size() == 0) {
            return 1;
        } else {

            int previousOrderNumber = 1;

            for (Order anOrder : orderList) {
                if (anOrder.getOrderNumber() > previousOrderNumber) {
                    previousOrderNumber = anOrder.getOrderNumber();
                }
            }
            return (previousOrderNumber + 1);
        }
    }

    private void quit() {

        int save = view.SaveChangesConfirmation();

        if (save == 1) {
            try {
                service.saveOrderFiles();
                view.changesSaved();
            } catch (OrderPersistenceException ex) {
                view.displayErrorMessage(ex.getMessage());
            } catch (TrainingModeException ex) {
                view.displayErrorMessage("Program is currently in Training and is unable to save changes.");
            } catch (ModePersistenceException ex) {
                view.displayErrorMessage(ex.getMessage());
            }
        }
    }

}
