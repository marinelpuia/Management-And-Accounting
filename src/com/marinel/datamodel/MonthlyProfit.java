package com.marinel.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class MonthlyProfit {

    // Declare the fields information for all incoming and outgoing and we use
    // SimpleStringProperty to take advantage of data binding to update the
    // user interface

    private final SimpleStringProperty monthlyName = new SimpleStringProperty("");
    private final SimpleStringProperty yearNumber = new SimpleStringProperty("");
    private final SimpleStringProperty grossProfit = new SimpleStringProperty("");
    private final SimpleStringProperty productsOriginalPrice = new SimpleStringProperty("");
    private final SimpleStringProperty rentAmount = new SimpleStringProperty("");
    private final SimpleStringProperty salaryAmount = new SimpleStringProperty("");
    private final SimpleStringProperty electricityCost = new SimpleStringProperty("");
    private final SimpleStringProperty heatingCost = new SimpleStringProperty("");
    private final SimpleStringProperty waterCost =  new SimpleStringProperty("");
    private final SimpleStringProperty advertisingCost =  new SimpleStringProperty("");
    private final SimpleStringProperty religiousDayExpenses = new SimpleStringProperty("");
    private final SimpleStringProperty otherExpenses = new SimpleStringProperty("");
    private final SimpleStringProperty netIncome = new SimpleStringProperty("");

    // Create default whit the same parameters that accepts no values
    // that's for when we're loading our constructor that accepts no value

    public MonthlyProfit(){}

    // Create constructor by hand and use String instead of SimpleStringProperty

    public MonthlyProfit(String monthlyName, String yearNumber, String grossProfit, String productsOriginalPrice, String rentAmount,
                  String salaryAmount, String electricityCost, String heatingCost, String waterCost, String advertisingCost,
                  String religiousDayExpenses, String otherExpenses, String netIncome){
        this.monthlyName.set(monthlyName);
        this.yearNumber.set(yearNumber);
        this.grossProfit.set(grossProfit);
        this.productsOriginalPrice.set(productsOriginalPrice);
        this.rentAmount.set(rentAmount);
        this.salaryAmount.set(salaryAmount);
        this.electricityCost.set(electricityCost);
        this.heatingCost.set(heatingCost);
        this.waterCost.set(waterCost);
        this.advertisingCost.set(advertisingCost);
        this.religiousDayExpenses.set(religiousDayExpenses);
        this.otherExpenses.set(otherExpenses);
        this.netIncome.set(netIncome);
    }

    // Create getter and setter


    public String getMonthlyName() {
        return monthlyName.get();
    }

    public SimpleStringProperty monthlyNameProperty() {
        return monthlyName;
    }

    public void setMonthlyName(String monthlyName) {
        this.monthlyName.set(monthlyName);
    }

    public String getYearNumber() {
        return yearNumber.get();
    }

    public SimpleStringProperty yearNumberProperty() {
        return yearNumber;
    }

    public void setYearNumber(String yearNumber) {
        this.yearNumber.set(yearNumber);
    }

    public String getGrossProfit() {
        return grossProfit.get();
    }

    public SimpleStringProperty grossProfitProperty() {
        return grossProfit;
    }

    public void setGrossProfit(String grossProfit) {
        this.grossProfit.set(grossProfit);
    }

    public String getProductsOriginalPrice() {
        return productsOriginalPrice.get();
    }

    public SimpleStringProperty productsOriginalPriceProperty() {
        return productsOriginalPrice;
    }

    public void setProductsOriginalPrice(String productsOriginalPrice) {
        this.productsOriginalPrice.set(productsOriginalPrice);
    }

    public String getRentAmount() {
        return rentAmount.get();
    }

    public SimpleStringProperty rentAmountProperty() {
        return rentAmount;
    }

    public void setRentAmount(String rentAmount) {
        this.rentAmount.set(rentAmount);
    }

    public String getSalaryAmount() {
        return salaryAmount.get();
    }

    public SimpleStringProperty salaryAmountProperty() {
        return salaryAmount;
    }

    public void setSalaryAmount(String salaryAmount) {
        this.salaryAmount.set(salaryAmount);
    }

    public String getElectricityCost() {
        return electricityCost.get();
    }

    public SimpleStringProperty electricityCostProperty() {
        return electricityCost;
    }

    public void setElectricityCost(String electricityCost) {
        this.electricityCost.set(electricityCost);
    }

    public String getHeatingCost() {
        return heatingCost.get();
    }

    public SimpleStringProperty heatingCostProperty() {
        return heatingCost;
    }

    public void setHeatingCost(String heatingCost) {
        this.heatingCost.set(heatingCost);
    }

    public String getWaterCost() {
        return waterCost.get();
    }

    public SimpleStringProperty waterCostProperty() {
        return waterCost;
    }

    public void setWaterCost(String waterCost) {
        this.waterCost.set(waterCost);
    }

    public String getAdvertisingCost() {
        return advertisingCost.get();
    }

    public SimpleStringProperty advertisingCostProperty() {
        return advertisingCost;
    }

    public void setAdvertisingCost(String advertisingCost) {
        this.advertisingCost.set(advertisingCost);
    }

    public String getReligiousDayExpenses() {
        return religiousDayExpenses.get();
    }

    public SimpleStringProperty religiousDayExpensesProperty() {
        return religiousDayExpenses;
    }

    public void setReligiousDayExpenses(String religiousDayExpenses) {
        this.religiousDayExpenses.set(religiousDayExpenses);
    }

    public String getOtherExpenses() {
        return otherExpenses.get();
    }

    public SimpleStringProperty otherExpensesProperty() {
        return otherExpenses;
    }

    public void setOtherExpenses(String otherExpenses) {
        this.otherExpenses.set(otherExpenses);
    }

    public String getNetIncome() {
        return netIncome.get();
    }

    public SimpleStringProperty netIncomeProperty() {
        return netIncome;
    }

    public void setNetIncome(String netIncome) {
        this.netIncome.set(netIncome);
    }
}
