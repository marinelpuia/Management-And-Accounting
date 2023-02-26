package com.marinel.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class YearProfit {

    // Declare the fields' information for all incoming and outgoing and we use
    // SimpleStringProperty to take advantage of data binding to update the
    // user interface
    
    private final SimpleStringProperty yearNumber = new SimpleStringProperty("");
    private final SimpleStringProperty grossProfitBeforeTax = new SimpleStringProperty("");
    private final SimpleStringProperty amountOfTaxPaid = new SimpleStringProperty("");
    private final SimpleStringProperty netProfitAfterTax = new SimpleStringProperty("");

    // Create default with the same parameters that accepts no values
    // that's for when we're loading our constructor that accepts no value
    public YearProfit(){}

    // Create constructor by hand and use String instead of SimpleStringProperty
    public YearProfit(String yearNumber, String grossProfitBeforeTax, String amountOfTaxPaid, String netProfitAfterTax) {
        this.yearNumber.set(yearNumber);
        this.grossProfitBeforeTax.set(grossProfitBeforeTax);
        this.amountOfTaxPaid.set(amountOfTaxPaid);
        this.netProfitAfterTax.set(netProfitAfterTax);
    }

    // Create getter and setter
    public String getYearNumber() {
        return yearNumber.get();
    }

    public SimpleStringProperty yearNumberProperty() {
        return yearNumber;
    }

    public void setYearNumber(String yearNumber) {
        this.yearNumber.set(yearNumber);
    }

    public String getGrossProfitBeforeTax() {
        return grossProfitBeforeTax.get();
    }

    public SimpleStringProperty grossProfitBeforeTaxProperty() {
        return grossProfitBeforeTax;
    }

    public void setGrossProfitBeforeTax(String grossProfitBeforeTax) {
        this.grossProfitBeforeTax.set(grossProfitBeforeTax);
    }

    public String getAmountOfTaxPaid() {
        return amountOfTaxPaid.get();
    }

    public SimpleStringProperty amountOfTaxPaidProperty() {
        return amountOfTaxPaid;
    }

    public void setAmountOfTaxPaid(String amountOfTaxPaid) {
        this.amountOfTaxPaid.set(amountOfTaxPaid);
    }

    public String getNetProfitAfterTax() {
        return netProfitAfterTax.get();
    }

    public SimpleStringProperty netProfitAfterTaxProperty() {
        return netProfitAfterTax;
    }

    public void setNetProfitAfterTax(String netProfitAfterTax) {
        this.netProfitAfterTax.set(netProfitAfterTax);
    }
}
