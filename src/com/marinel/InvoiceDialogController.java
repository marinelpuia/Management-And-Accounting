package com.marinel;

import com.marinel.datamodel.Invoice;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.DialogPane;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;

import java.io.File;
import java.time.format.DateTimeFormatter;

public class InvoiceDialogController {

    Invoice newInvoice;
    FileChooser fileChooser = new FileChooser();

    @FXML
    private TextField invoiceNameField;
    @FXML
    private TextField pickUpLinkField;
    @FXML
    private DialogPane dialogPane;
    @FXML
    private DatePicker getDateOption;
    @FXML
    private TextField pickUpDateFiled;


    public Invoice getNewInvoice() {

        String invoiceName = invoiceNameField.getText();

        if (invoiceNameField.getText() == null || invoiceNameField.getText().trim().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertisment... câmpuri necompletate!");
            alert.setHeaderText(null);
            alert.setWidth(300);
            alert.setHeight(200);
            alert.setContentText("Nu ai completat câmpul Invoice Name, dar te asigur " +
                    "că îl poți edita mai târziu, trebuie doar să folosești opțiunea de editare din meniu.");
            alert.showAndWait();
            invoiceNameField.setText("Null");
            invoiceName = invoiceNameField.getText();

        }

        String dateAdded = pickUpDateFiled.getText();

        if (pickUpDateFiled.getText() == null || pickUpDateFiled.getText().trim().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertisment... câmpuri necompletate!");
            alert.setHeaderText(null);
            alert.setWidth(300);
            alert.setHeight(200);
            alert.setContentText("Nu ai completat câmpul Date Added, dar te asigur " +
                    "că îl poți edita mai târziu, trebuie doar să folosești opțiunea de editare din meniu.");
            alert.showAndWait();
            pickUpDateFiled.setText("Null");
            dateAdded = pickUpDateFiled.getText();

        }


        String fileLink = pickUpLinkField.getText();

        if (pickUpLinkField.getText() == null || pickUpLinkField.getText().trim().isEmpty()) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertisment... câmpuri necompletate!");
            alert.setHeaderText(null);
            alert.setWidth(300);
            alert.setHeight(200);
            alert.setContentText("Nu ai completat câmpul Invoice Link, dar te asigur " +
                    "că îl poți edita mai târziu, trebuie doar să folosești opțiunea de editare din meniu.");
            alert.showAndWait();
            pickUpLinkField.setText("Null");
            fileLink = pickUpLinkField.getText();

        }

        newInvoice = new Invoice(invoiceName, dateAdded, fileLink);

        return newInvoice;

    }

    // Method to edit the Invoice
    public void editInvoice(Invoice invoice) {

        invoiceNameField.setText(invoice.getInvoiceName());
        pickUpDateFiled.setText(invoice.getDateAdded());
        pickUpLinkField.setText(invoice.getFileLink());
    }

    // Method to update the Invoice
    public void updateInvoice(Invoice invoice) {

        invoice.setInvoiceName(invoiceNameField.getText());
        invoice.setDateAdded(pickUpDateFiled.getText());
        invoice.setFileLink(pickUpLinkField.getText());
    }

    // Method to upload the file from user system
    public void getTheFile() {

        fileChooser.setTitle("Upload Invoice File");
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("ALL FILES", "*.*"),
                new FileChooser.ExtensionFilter("ZIP", "*.zip"),
                new FileChooser.ExtensionFilter("PDF", "*.pdf"),
                new FileChooser.ExtensionFilter("TEXT", "*.txt"),
                new FileChooser.ExtensionFilter("IMAGE FILES", "*.jpg", "*.png", "*.gif")
        );


        //File file = fileChooser.showSaveDialog(dialogPane.getScene().getWindow()); // save the file not in use
        File file = fileChooser.showOpenDialog(dialogPane.getScene().getWindow());


        if (file != null) {

            pickUpLinkField.setText(file.getPath());

        } else  {
            System.out.println("error");
        }

    }

    // Method to paste the date from dataPiker in to pickUpDateFiled
    public void pasteDate() {
        String pastedDate = getDateOption.getValue().format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy"));
        pickUpDateFiled.setText(pastedDate);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFO!");
        alert.setHeaderText(null);
        alert.setContentText("Data a fost introdusă mergi mai departe la Upload Link !");
        alert.showAndWait();
    }
}
