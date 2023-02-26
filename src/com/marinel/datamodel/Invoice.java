package com.marinel.datamodel;

import javafx.beans.property.SimpleStringProperty;

public class Invoice {

    private final SimpleStringProperty invoiceName = new SimpleStringProperty("");
    private final SimpleStringProperty dateAdded = new SimpleStringProperty("");
    private final SimpleStringProperty fileLink = new SimpleStringProperty("");

    // Create default constructor with the same parameters that accepts no values
    // that's for when we're loading our constructor that accepts no value
    public Invoice() {}

    // Create constructor by hand and use String instead of SimpleStringProperty
    public Invoice(String invoiceName, String dateAdded, String fileLink) {
        this.invoiceName.set(invoiceName);
        this.dateAdded.set(dateAdded);
        this.fileLink.set(fileLink);
    }

    // Getter and setter
    // We use only the String methods
    public String getInvoiceName() {
        return invoiceName.get();
    }

    public SimpleStringProperty invoiceNameProperty() {
        return invoiceName;
    }

    public void setInvoiceName(String invoiceName) {
        this.invoiceName.set(invoiceName);
    }

    public String getDateAdded() {
        return dateAdded.get();
    }

    public SimpleStringProperty dateAddedProperty() {
        return dateAdded;
    }

    public void setDateAdded(String dateAdded) {
        this.dateAdded.set(dateAdded);
    }

    public String getFileLink() {
        return fileLink.get();
    }

    public SimpleStringProperty fileLinkProperty() {
        return fileLink;
    }

    public void setFileLink(String fileLink) {
        this.fileLink.set(fileLink);
    }
}
