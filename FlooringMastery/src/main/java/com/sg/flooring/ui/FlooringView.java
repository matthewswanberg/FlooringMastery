/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.ui;

import com.sg.flooring.model.Order;
import com.sg.flooring.model.ProductType;
import com.sg.flooring.model.State;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.TreeMap;

/**
 *
 * @author matthewswanberg
 */
public class FlooringView {

    private UserIO io;

    public FlooringView(UserIO io) {
        this.io = io;
    }

    public void welcome() {
        io.print("");
        io.print("	");
        io.print("                             _______________________________________  ");
        io.print("                            /  ___________________________________  \\");
        io.print("                           /  /_/_/_/_/_|_|_|_|_|_|_|_|_|_\\_\\_\\_\\_\\  \\");
        io.print("                          /  /_/_/_/_J__L_L_L_|_|_|_J_J_J__L_\\_\\_\\_\\  \\");
        io.print("                         /  /_/_/_J__L_J__L_L_|_|_|_J_J__L_J__L_\\_\\_\\  \\");
        io.print("                        /  /_/_J__L_J__L_J_J__L_|_J__L_L_J__L_J__L_\\_\\  \\");
        io.print("                       /  /_/__L_/__L_J__L_J__L_|_J__L_J__L_J__\\_J__\\_\\  \\");
        io.print("                      /  /_J__/_J__/__L_J__|__L_|_J__|__L_J__\\__L_\\__L_\\  \\");
        io.print("                     /  /  F /  F J  J  |  F J  |  F J  |  F  F J  \\ J  \\  \\");
        io.print("                    /  /--/-J--/--L--|--L-J--J--|--L--L-J--|--J--\\--L-\\--\\  \\");
        io.print("                   /  /__/__L_J__J___L_J__J__|__|__|__L__L_J___L__L_J__\\__\\  \\");
        io.print("                  /  /  /  /  F  F  J  J  |  |  |  |  |  F  F  J  J  \\  \\  \\  \\");
        io.print("                 /  /  /--/--/--/--J---L--|--|--|--o--|--|--|--J---L--\\--\\--\\--\\  ");
        io.print("                /  /__/__J__J___L__J___L__L__L__|__J__J__J___L__J___L__L__\\__\\  \\");
        io.print("               /  /  /   F  F  J   F  J  J   F  |  J   F  F  J   F  J  J   \\  \\  \\");
        io.print("              /  /--/---/--J---L--J---L--|--J---|---L--|--J---L--J---L--\\---\\--\\  \\");
        io.print("             /  /__J___/___L__/___L__J___L__J___|___L__J___L__J___\\__J___\\___L__\\  \\");
        io.print("            /  /   F  J   /  J   J   |  J   J   |   F   F  |   F   F  \\   F  J   \\  \\");
        io.print("           /  /---/---L--J---L---L---L--|---|---|---|---|--J---J---J---L--J---\\---\\  \\");
        io.print("          /  /___/___/___L__J___J___J___|___|___|___|___|___L___L___L__J___\\___\\___\\  \\");
        io.print("         /  /   /   /   /   F   F   F   F   F   |   J   J   J   J   J   \\   \\   \\   \\  \\");
        io.print("        /  /___/___J___J___J___J___J____L___L___|___J___J____L___L___L___L___L___\\___\\  \\");
        io.print("       /  /   /    F   F   F   |   |   J    F   |   J    F   |   |   J   J   J    \\   \\  \\");
        io.print("      /  /___J____/___/___J____L___L___|___J____|____L___|___J___J____L___\\___\\____L___\\  \\");
        io.print("     /  /    F   /   J    F   J   J    |   J    |    F   |    F   F   J    F   \\   J    \\  \\");
        io.print("    /  /____/___J____L___/____L___|____L___|____|____|___J____|___J____\\___J____L___\\____\\  \\");
        io.print("   /  /    /    F   /   J    J    F   J    F    |    J    F   J    F    F   \\   J    \\    \\  \\");
        io.print("  /  /____/____/___J____L____|____L___J____L____|____J____L___J____|____J____L___\\____\\____\\  \\");
        io.print(" /                                                                                             \\");
        io.print("/_______________________________________________________________________________________________\\");
        io.print("");
        io.print("                           Welcome to Floor King's Order Creation System.");
        io.print("             This application features state-of-the-art, never before seen advances in");
        io.print("          computer programming technology. IBM's Watson wishes he could possess the power ");
        io.print("         and sophistication of this loosely-coupled, MVC principle adhering, and thoroghly");
        io.print("                               unit tested, console application.");
        io.print("           ");
        io.print("                                       Welcome to the future.");
        io.print("                                    Now let's make some floors!");
        io.print("");
        io.print("");

    }
    
    public void printTrainingMode() {
        io.print("------------------------------------------------------------------------");
        io.print("| Program is currently in Training Mode and is unable to save changes. |");
        io.print("------------------------------------------------------------------------");
    }

    public int printMenuAndGetSelection() {
        io.print(" __________________________");
        io.print("|| Main Menu               |");
        io.print("||-------------------------|");
        io.print("|| 1. Display Orders       |");
        io.print("|| 2. Add an Order         |");
        io.print("|| 3. Edit an Order        |");
        io.print("|| 4. Remove an Order      |");
        io.print("|| 5. Save Current Work    |");
        io.print("|| 6. Quit                 |");
        io.print("||_________________________|");
        io.print("`--------------------------'");

        return io.readInt("Please select from the above choices.", 1, 6);
    }

    public void displayNewOrderBanner() {
        io.print("-------------------------");
        io.print("|   Create New Order    |");
        io.print("-------------------------");
    }
    
    public void displayEditBanner() {
        io.print("-------------------------");
        io.print("|     Edit an Order     |");
        io.print("-------------------------");
    }
    
    public void displayRemoveBanner() {
        io.print("-------------------------");
        io.print("|    Remove an Order    |");
        io.print("-------------------------");
    }
    
    public void displaySaveBanner() {
        io.print("-------------------------");
        io.print("|      Save Changes     |");
        io.print("-------------------------");
    }
    
    

    public LocalDate getNewOrderDate() {

        LocalDate newOrderDate;
        //String DateString = io.readString("Please enter order date MM/DD/YYYY");
        //newOrderDate = LocalDate.parse(DateString, DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        newOrderDate = io.readDate("Please enter order date MM/DD/YYYY.");

        return newOrderDate;

    }

    public LocalDate getEditOrdersDate() {
       
        LocalDate displayDate = io.readDate("Please enter the date for the order you would like to edit. MM/DD/YYYY");
        return displayDate;
    }
    
    public LocalDate getDeleteOrdersDate() {
       
        LocalDate displayDate = io.readDate("Please enter the date for the order you would like to delete. MM/DD/YYYY");
        return displayDate;
    }
    
    public LocalDate getDisplayOrdersDate() {
       
        LocalDate displayDate = io.readDate("Please enter the date you would like to display orders for. MM/DD/YYYY");
        return displayDate;
    }

    public void displayNoOrdersListed(LocalDate dateOfOrders) {
            io.print("There are no orders listed for " + dateOfOrders.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")).toString() + ".");
            io.readString("Hit enter to continue.");
        
        
    }

    public void diplayOrders(List<Order> ordersByDate, LocalDate dateOfOrders) {

        if (ordersByDate.isEmpty()) {
            io.print("There are no orders listed for " + dateOfOrders.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")).toString() + ".");
            
        } else {

            io.print("Here are the orders for " + dateOfOrders.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")).toString() + ":");

            
            for (Order anOrder : ordersByDate) {
                io.print("");
                io.print("------------------------------------");
                io.print(" Order Number: " + anOrder.getOrderNumber());
                io.print(" Customer: " + anOrder.getCustomerName());
                io.print(" Location: " + anOrder.getLocationAbbreviation());
                io.print(" Tax Rate: " + anOrder.getTaxRate().toString());
                io.print(" Product Type: " + anOrder.getProductName());
                io.print(" Job Area: " + anOrder.getArea().toString());
                io.print(" Material Cost per Sq Ft.: $" + anOrder.getCostPerSqFt().toString());
                io.print(" Labor Cost per Sq. Ft.: $" + anOrder.getLaborPerSqFt().toString());
                io.print(" Total Material Cost: $" + anOrder.getMaterialCost().toString());
                io.print(" Total Labor Cost: $" + anOrder.getLaborCost().toString());
                io.print(" Tax: $" + anOrder.getTaxAmt().toString());
                io.print(" Total: $" + anOrder.getTotal().toString());
                io.print("------------------------------------");
            }
        }
        io.readString("Hit enter to continue.");
    }

    public String getNewCustomerName() {
        String name = io.readString("Please enter Name of customer.");
        return name;
    }
    
    public String getEditCustomerName(String OldName) {
        String name = io.readString("Please enter Name of customer. (" + OldName + ")");
        return name;
    }
    
    public void displayListOfLocations(List<State> controllerStateList) {
        io.print(" ______________________");
        io.print("|| Available Locations |");
        io.print("||---------------------|");
        
        TreeMap<String, State> orderedStates = new TreeMap<String, State>();

        for (State aState : controllerStateList) {
            orderedStates.put(aState.getAbbreviation(), aState);
        }
        
        for (String stateKey : orderedStates.keySet()) {
            
            String aStateSet = stateKey + " - " + orderedStates.get(stateKey).getName();
            
            io.print("||  " + rightpad(aStateSet, 19) + "|");
            
        }
        io.print("||_____________________|");
        io.print("`----------------------'");
        
    }

    public String getNewLocationAbbrev() {
        String location = io.readString("Please enter location of order.");
        return location;
    }
    
    public String getEditLocationAbbrev(String State) {
        String location = io.readString("Please enter location of order. (" + State + ")");
        return location;
    }

    public void displayListOfProducts(List<ProductType> controllerProductList) {
        
        io.print(" ______________________");
        io.print("|| Available Products  |");
        io.print("||---------------------|");
        
        for (ProductType aProduct : controllerProductList) {
            String aProductName = aProduct.getName();
            //int whiteSpace = 18 - aProductName.length();
            
            io.print("||   " + rightpad(aProductName, 18) + "|");
            
        }

        io.print("||_____________________|");
        io.print("`----------------------'");
    }
    
    public String getNewProductTypeName() {
        String productName = io.readString("Please enter selected product type.");
        return productName;
    }
    
    public String getEditProductTypeName(String OldProductName) {
        String productName = io.readString("Please enter selected product type. (" + OldProductName + ")");
        return productName;
    }
    
    public String getEditArea(BigDecimal oldArea) {
        String AreaString;
        int checkArea = 100;
        
        do {
        AreaString = io.readString("Please enter the Sqaure footage (min. 100). (" + oldArea + ")");
        if (!AreaString.isEmpty()){
        checkArea = Integer.parseInt(AreaString);
        } 
        } while (checkArea < 100);
        return AreaString;
        
    }

    public BigDecimal getNewArea() {
        BigDecimal area = new BigDecimal(io.readInt("Please enter the Square footage (min. 100).", 100, 1000000));
        return area;
    }
    
    public void orderInfo(Order ourOrder){
                io.print("");
                io.print("---------------------------------");
                io.print(" Order Number: " + ourOrder.getOrderNumber());
                io.print(" Customer: " + ourOrder.getCustomerName());
                io.print(" Location: " + ourOrder.getLocationAbbreviation());
                io.print(" Tax Rate: " + ourOrder.getTaxRate().toString());
                io.print(" Product Type: " + ourOrder.getProductName());
                io.print(" Job Area: " + ourOrder.getArea().toString());
                io.print(" Material Cost per Sq Ft.: $" + ourOrder.getCostPerSqFt().toString());
                io.print(" Labor Cost per Sq. Ft.: $" + ourOrder.getLaborPerSqFt().toString());
                io.print(" Total Material Cost: $" + ourOrder.getMaterialCost().toString());
                io.print(" Total Labor Cost: $" + ourOrder.getLaborCost().toString());
                io.print(" Tax: $" + ourOrder.getTaxAmt().toString());
                io.print(" Total: $" + ourOrder.getTotal().toString());
                io.print("---------------------------------");
    }

    public void proposedOrderSummary(Order proposedOrder) {
        io.print("Here is a summary of you're order:");
        io.print("");
        io.print("---------------------------------");
        io.print(" Order Date: " + proposedOrder.getOrderDate().format(DateTimeFormatter.ofPattern("MM/dd/yyyy")).toString());
        io.print(" Customer: " + proposedOrder.getCustomerName());
        io.print(" Location: " + proposedOrder.getLocationAbbreviation());
        io.print(" Tax Rate: " + proposedOrder.getTaxRate().toString());
        io.print(" Product Type: " + proposedOrder.getProductName());
        io.print(" Job Area: " + proposedOrder.getArea().toString());
        io.print(" Material Cost per Sq Ft.: $" + proposedOrder.getCostPerSqFt().toString());
        io.print(" Labor Cost per Sq. Ft.: $" + proposedOrder.getLaborPerSqFt().toString());
        io.print(" Total Material Cost: $" + proposedOrder.getMaterialCost().toString());
        io.print(" Total Labor Cost: $" + proposedOrder.getLaborCost().toString());
        io.print(" Tax: $" + proposedOrder.getTaxAmt().toString());
        io.print(" Total: $" + proposedOrder.getTotal().toString());
        io.print("---------------------------------");
    }

    public int getOrderPlacementConfirmation() {
        io.print("");
        return io.readInt("Would you like to place this order? Yes(1)  No(2)", 1, 2);
    }

    public int SaveChangesConfirmation() {
        io.print("");
        return io.readInt("Would you like to save your changes? Yes(1)  No(2)", 1, 2);
    }
    
    public int getDeletionConfirmation() {
        io.print("");
        return io.readInt("Would you like to delete any of these orders? Yes(1)  No(2)", 1, 2);
    }
    
    public int finalDeletionConfirmation() {
        io.print("");
        return io.readInt("Are you sure you want to delete this order? Yes(1)  No(2)", 1, 2);
    }
    
    
    public int getDeleteNumber() {
        return io.readInt("Which order would you like to Delete?");
    }
    
    public int getEditConfirmation() {
        io.print("");
        return io.readInt("Would you like to edit any of these orders? Yes(1)  No(2)", 1, 2);
    }
    
    public void editInstructions(){
        io.print("");
        io.print("If you would like to edit a field, simply enter new information");
        io.print("when prompted. Otherwise, hit enter to skip a field.");
        io.readString("Hit enter to continue.");
    }
    
    public int getEditFinishedConfirmation() {
        io.print("");
        return io.readInt("Would you like to update this order? Yes(1)  No(2)", 1, 2);
    }
    
    public void editCompleted(int orderNumber) {
        io.print("Order #" + orderNumber + " has been updated.");
    }
    
    public int getEditNumber() {
        return io.readInt("Which order would you like to Edit?");
    }
    
    public void deleteCompleted(int orderNumber) {
        io.print("Order #" + orderNumber + " has been deleted.");
    }
    
    public void deleteCancelled(int orderNumber) {
        io.print("Order #" + orderNumber + " has not been deleted.");
    }

    public void changesSaved() {
        io.print("Your Changes have been saved.");
    }

    public void orderHasBeenPlaced(int orderNumber) {
        io.print("Order #" + orderNumber + " has been placed.");
        io.readString("Hit enter to continue.");
    }

    public void unknownCommand() {
        io.print("UnkownCommand");
    }

    public void exitMessage() {
        io.print("Good Bye");
    }

    public void displayErrorMessage(String errorMsg) {
        io.print(errorMsg);
    }
    
    private String rightpad(String text, int length) {
    return String.format("%-" + length + "." + length + "s", text);
}

}
