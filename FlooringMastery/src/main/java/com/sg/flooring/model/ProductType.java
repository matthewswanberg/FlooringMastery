/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.flooring.model;

import java.math.BigDecimal;
import java.util.Objects;

/**
 *
 * @author matthewswanberg
 */
public class ProductType {
    private String name;
    private BigDecimal costPerSqFt;
    private BigDecimal laborPerSqFt;

    public ProductType() {
    }

    public ProductType(String name, BigDecimal costPerSqFt, BigDecimal laborPerSqFt) {
        this.name = name;
        this.costPerSqFt = costPerSqFt;
        this.laborPerSqFt = laborPerSqFt;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 17 * hash + Objects.hashCode(this.name);
        hash = 17 * hash + Objects.hashCode(this.costPerSqFt);
        hash = 17 * hash + Objects.hashCode(this.laborPerSqFt);
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
        final ProductType other = (ProductType) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.costPerSqFt, other.costPerSqFt)) {
            return false;
        }
        if (!Objects.equals(this.laborPerSqFt, other.laborPerSqFt)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProductType{" + "name=" + name + ", costPerSqFt=" + costPerSqFt + ", laborPerSqFt=" + laborPerSqFt + '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
    
    
}
