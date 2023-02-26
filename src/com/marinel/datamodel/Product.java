package com.marinel.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class Product {

    // Declare the fields' information for all products
    // We use SimpleStringProperty to take advantage of data binding to update the user interface

    private final SimpleStringProperty productName = new SimpleStringProperty("");
    private final SimpleStringProperty brandName = new SimpleStringProperty("");
    private final SimpleStringProperty modelNumber = new SimpleStringProperty("");
    private final SimpleStringProperty colorName = new SimpleStringProperty("");
    private final SimpleStringProperty sizeValue = new SimpleStringProperty("");
    private final SimpleStringProperty categoryName = new SimpleStringProperty("");
    private final SimpleStringProperty webAddress = new SimpleStringProperty("");
    private final SimpleStringProperty purchaseDate = new SimpleStringProperty("");
    private final SimpleStringProperty volumeQuantity = new SimpleStringProperty("");
    private final SimpleStringProperty buyingPrice = new SimpleStringProperty("");
    private final SimpleStringProperty stackPrice = new SimpleStringProperty("");
    private final SimpleStringProperty sellingPrice = new SimpleStringProperty("");
    private final SimpleStringProperty profitAmount =  new SimpleStringProperty("");
    private final SimpleStringProperty profitStack = new SimpleStringProperty("");
    private final SimpleStringProperty remainingStock = new SimpleStringProperty("");
    private final SimpleStringProperty remainingStockProfit = new SimpleStringProperty("");


    // Create default constructor whit the same parameters that accepts no values
    // that's for when we're loading our constructor that accepts no value
    public Product() {}

    // Create constructor by hand and use String instead of SimpleStringProperty
    public Product(String productName, String brandName, String modelNumber, String colorName, String sizeValue, String categoryName,
                   String webAddress, String purchaseDate, String volumeQuantity, String buyingPrice, String stackPrice, String sellingPrice,
                   String profitAmount, String profitStack, String remainingStock, String remainingStockProfit) {
        this.productName.set(productName);
        this.brandName.set(brandName);
        this.modelNumber.set(modelNumber);
        this.colorName.set(colorName);
        this.sizeValue.set(sizeValue);
        this.categoryName.set(categoryName);
        this.webAddress.set(webAddress);
        this.purchaseDate.set(purchaseDate);
        this.volumeQuantity.set(volumeQuantity);
        this.buyingPrice.set(buyingPrice);
        this.stackPrice.set(stackPrice);
        this.sellingPrice.set(sellingPrice);
        this.profitAmount.set(profitAmount);
        this.profitStack.set(profitStack);
        this.remainingStock.set(remainingStock);
        this.remainingStockProfit.set(remainingStockProfit);
    }

    // Create setter and getters for our fields
    // We use only the String methods
    public String getProductName() {
        return productName.get();
    }

    public SimpleStringProperty productNameProperty() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName.set(productName);
    }

    public String getBrandName() {
        return brandName.get();
    }

    public SimpleStringProperty brandNameProperty() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName.set(brandName);
    }

    public String getModelNumber() {
        return modelNumber.get();
    }

    public SimpleStringProperty modelNumberProperty() {
        return modelNumber;
    }

    public void setModelNumber(String modelNumber) {
        this.modelNumber.set(modelNumber);
    }

    public String getColorName() {
        return colorName.get();
    }

    public SimpleStringProperty colorNameProperty() {
        return colorName;
    }

    public void setColorName(String colorName) {
        this.colorName.set(colorName);
    }

    public String getSizeValue() {
        return sizeValue.get();
    }

    public SimpleStringProperty sizeValueProperty() {
        return sizeValue;
    }

    public void setSizeValue(String sizeValue) {
        this.sizeValue.set(sizeValue);
    }

    public String getVolumeQuantity() {
        return volumeQuantity.get();
    }

    public SimpleStringProperty volumeQuantityProperty() {
        return volumeQuantity;
    }

    public void setVolumeQuantity(String volumeQuantity) {
        this.volumeQuantity.set(volumeQuantity);
    }

    public String getBuyingPrice() {
        return buyingPrice.get();
    }

    public SimpleStringProperty buyingPriceProperty() {
        return buyingPrice;
    }

    public void setBuyingPrice(String buyingPrice) {
        this.buyingPrice.set(buyingPrice);
    }

    public String getSellingPrice() {
        return sellingPrice.get();
    }

    public SimpleStringProperty sellingPriceProperty() {
        return sellingPrice;
    }

    public void setSellingPrice(String sellingPrice) {
        this.sellingPrice.set(sellingPrice);
    }

    public String getProfitAmount() {
        return profitAmount.get();
    }

    public SimpleStringProperty profitAmountProperty() {
        return profitAmount;
    }

    public void setProfitAmount(String profitAmount) {
        this.profitAmount.set(profitAmount);
    }

    public String getCategoryName() {
        return categoryName.get();
    }

    public SimpleStringProperty categoryNameProperty() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName.set(categoryName);
    }

    public String getWebAddress() {
        return webAddress.get();
    }

    public SimpleStringProperty webAddressProperty() {
        return webAddress;
    }

    public void setWebAddress(String webAddress) {
        this.webAddress.set(webAddress);
    }

    public String getProfitStack() {
        return profitStack.get();
    }

    public SimpleStringProperty profitStackProperty() {
        return profitStack;
    }

    public void setProfitStack(String profitStack) {
        this.profitStack.set(profitStack);
    }

    public String getPurchaseDate() {
        return purchaseDate.get();
    }

    public SimpleStringProperty purchaseDateProperty() {
        return purchaseDate;
    }

    public void setPurchaseDate(String purchaseDate) {
        this.purchaseDate.set(purchaseDate);
    }

    public String getStackPrice() {
        return stackPrice.get();
    }

    public SimpleStringProperty stackPriceProperty() {
        return stackPrice;
    }

    public void setStackPrice(String stackPrice) {
        this.stackPrice.set(stackPrice);
    }

    public String getRemainingStock() {
        return remainingStock.get();
    }

    public SimpleStringProperty remainingStockProperty() {
        return remainingStock;
    }

    public void setRemainingStock(String remainingStock) {
        this.remainingStock.set(remainingStock);
    }

    public String getRemainingStockProfit() {
        return remainingStockProfit.get();
    }

    public SimpleStringProperty remainingStockProfitProperty() {
        return remainingStockProfit;
    }

    public void setRemainingStockProfit(String remainingStockProfit) {
        this.remainingStockProfit.set(remainingStockProfit);
    }
}
