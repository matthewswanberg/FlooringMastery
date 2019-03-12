/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.time.LocalDate;
import java.util.Objects;

/**
 *
 * @author matthewswanberg
 */
public class Order {

    private int orderNumber;
    private LocalDate orderDate;
    private String customerName;
    private String locationAbbreviation;
    private BigDecimal taxRate;
    private String productName;
    private BigDecimal area;
    private BigDecimal costPerSqFt;
    private BigDecimal laborPerSqFt;
    private BigDecimal materialCost;
    private BigDecimal laborCost;
    private BigDecimal subTotal;
    private BigDecimal taxAmt;
    private BigDecimal total;

    public Order() {
    }

    public Order(LocalDate orderDate, String customerName, String locationAbbreviation, BigDecimal TaxRate,
            BigDecimal CostPerSqFt, BigDecimal LaborPerSqFt, String productName, BigDecimal Area) {
        this.orderDate = orderDate;
        this.customerName = customerName;
        this.locationAbbreviation = locationAbbreviation;
        this.taxRate = TaxRate;
        this.costPerSqFt = CostPerSqFt;
        this.laborPerSqFt = LaborPerSqFt;
        this.productName = productName;
        this.area = Area;
        this.materialCost = (costPerSqFt.multiply(area));
        this.laborCost = (laborPerSqFt.multiply(area));
        this.subTotal = (materialCost.add(laborCost));
        this.taxAmt = (subTotal.multiply(taxRate.multiply(new BigDecimal(".01")))).setScale(2, RoundingMode.CEILING);
        this.total = (subTotal.add(taxAmt).setScale(2, RoundingMode.HALF_DOWN));
        
    }


    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + this.orderNumber;
        hash = 97 * hash + Objects.hashCode(this.orderDate);
        hash = 97 * hash + Objects.hashCode(this.customerName);
        hash = 97 * hash + Objects.hashCode(this.locationAbbreviation);
        hash = 97 * hash + Objects.hashCode(this.taxRate);
        hash = 97 * hash + Objects.hashCode(this.productName);
        hash = 97 * hash + Objects.hashCode(this.area);
        hash = 97 * hash + Objects.hashCode(this.costPerSqFt);
        hash = 97 * hash + Objects.hashCode(this.laborPerSqFt);
        hash = 97 * hash + Objects.hashCode(this.materialCost);
        hash = 97 * hash + Objects.hashCode(this.laborCost);
        hash = 97 * hash + Objects.hashCode(this.taxAmt);
        hash = 97 * hash + Objects.hashCode(this.total);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Order other = (Order) obj;
        if (this.orderNumber != other.orderNumber) {
            return false;
        }
        if (!Objects.equals(this.customerName, other.customerName)) {
            return false;
        }
        if (!Objects.equals(this.orderDate, other.orderDate)) {
            return false;
        }
        if (!Objects.equals(this.locationAbbreviation, other.locationAbbreviation)) {
            return false;
        }
        if (!Objects.equals(this.taxRate, other.taxRate)) {
            return false;
        }
        if (!Objects.equals(this.productName, other.productName)) {
            return false;
        }
        if (!Objects.equals(this.area, other.area)) {
            return false;
        }
        if (!Objects.equals(this.costPerSqFt, other.costPerSqFt)) {
            return false;
        }
        if (!Objects.equals(this.laborPerSqFt, other.laborPerSqFt)) {
            return false;
        }
        if (!Objects.equals(this.materialCost, other.materialCost)) {
            return false;
        }
        if (!Objects.equals(this.laborCost, other.laborCost)) {
            return false;
        }
        if (!Objects.equals(this.taxAmt, other.taxAmt)) {
            return false;
        }
        if (!Objects.equals(this.total, other.total)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "orderNumber=" + orderNumber + ", orderDate=" + orderDate + ", customerName=" + customerName + ", location=" + locationAbbreviation + ", taxRate=" + taxRate + ", product=" + productName + ", area=" + area + ", costPerSqFt=" + costPerSqFt + ", laborPerSqFt=" + laborPerSqFt + ", materialCost=" + materialCost + ", laborCost=" + laborCost + ", taxAmt=" + taxAmt + ", total=" + total + '}';
    }

    public int getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(int orderNumber) {
        this.orderNumber = orderNumber;
    }

    public LocalDate getOrderDate() {
        return orderDate;
    }

    public void setOrderDate(LocalDate orderDate) {
        this.orderDate = orderDate;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getLocationAbbreviation() {
        return locationAbbreviation;
    }

    public void setLocationAbbreviation(String locationAbbreviation) {
        this.locationAbbreviation = locationAbbreviation;
    }

    public BigDecimal getTaxRate() {
        return taxRate;
    }

    public void setTaxRate(BigDecimal taxRate) {
        this.taxRate = taxRate;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String product) {
        this.productName = product;
    }

    public BigDecimal getArea() {
        return area;
    }

    public void setArea(BigDecimal area) {
        this.area = area;
    }

    public BigDecimal getCostPerSqFt() {
        return costPerSqFt;
    }

    public void setCostPerSqFt(BigDecimal costPerSqFt) {
        this.costPerSqFt = costPerSqFt;
    }

    public BigDecimal getLaborPerSqFt() {
        return laborPerSqFt;
    }

    public void setLaborPerSqFt(BigDecimal laborPerSqFt) {
        this.laborPerSqFt = laborPerSqFt;
    }

    public BigDecimal getMaterialCost() {
        return materialCost;
    }

    public void setMaterialCost(BigDecimal materialCost) {
        this.materialCost = materialCost;
    }

    public BigDecimal getLaborCost() {
        return laborCost;
    }

    public void setLaborCost(BigDecimal laborCost) {
        this.laborCost = laborCost;
    }

    public BigDecimal getTaxAmt() {
        return taxAmt;
    }

    public void setTaxAmt(BigDecimal taxAmt) {
        this.taxAmt = taxAmt;
    }

    public BigDecimal getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(BigDecimal subTotal) {
        this.subTotal = subTotal;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
    }

}
