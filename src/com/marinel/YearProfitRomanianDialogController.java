package com.marinel;

import com.marinel.datamodel.YearProfit;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public class YearProfitRomanianDialogController {

    // Create new YearProfit object
    YearProfit newYearProfit;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @FXML
    private TextField yearNumberField;
    @FXML
    private DatePicker getDateOption;
    @FXML
    private TextField grossProfitBeforeTaxField;
    @FXML
    private TextField amountOfTaxPaidField;

    // Collect all data from YearProfitRomanianDialog.fxml and then update the constructor in YearProfit.java
    public YearProfit getNewYearProfit() {

        String yearNumber = yearNumberField.getText();

        if (yearNumberField.getText() == null || yearNumberField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertisment... câmpuri necompletate!");
            alert.setHeaderText(null);
            alert.setWidth(300);
            alert.setHeight(200);
            alert.setContentText("Nu ai completat câmpul Anul, dar te asigur " +
                    "că îl poți edita mai târziu, trebuie doar să folosești opțiunea de editare din meniu.");
            alert.showAndWait();
            yearNumberField.setText("Null");
            yearNumber = yearNumberField.getText();
        }

        String grossProfitBeforeTax = grossProfitBeforeTaxField.getText();

        if (grossProfitBeforeTaxField.getText() == null || grossProfitBeforeTaxField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertisment... câmpuri necompletate!");
            alert.setHeaderText(null);
            alert.setWidth(300);
            alert.setHeight(200);
            alert.setContentText("Nu ai completat câmpul Profit Brut Inainte De Taxe, dar te asigur " +
                    "că îl poți edita mai târziu, trebuie doar să folosești opțiunea de editare din meniu.");
            alert.showAndWait();
            grossProfitBeforeTaxField.setText(String.valueOf(0));
            grossProfitBeforeTax = grossProfitBeforeTaxField.getText();
        }

        String amountOfTaxPaid = amountOfTaxPaidField.getText();

        if (amountOfTaxPaidField.getText() == null || amountOfTaxPaidField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertisment... câmpuri necompletate!");
            alert.setHeaderText(null);
            alert.setWidth(300);
            alert.setHeight(200);
            alert.setContentText("Nu ai completat câmpul Inpozit Pe Profit, dar te asigur " +
                    "că îl poți edita mai târziu, trebuie doar să folosești opțiunea de editare din meniu.");
            alert.showAndWait();
            amountOfTaxPaidField.setText(String.valueOf(0));
            amountOfTaxPaid = amountOfTaxPaidField.getText();
        }

        double valueOfGrossProfitBeforeTaxField = Double.parseDouble(grossProfitBeforeTax);
        double valueOfAmountOfTaxPaidField = Double.parseDouble(amountOfTaxPaid);

        double valueOfNetProfitAfterTax = valueOfGrossProfitBeforeTaxField - valueOfAmountOfTaxPaidField;

        String netProfitAfterTax = "" + decimalFormat.format(valueOfNetProfitAfterTax);

        newYearProfit = new YearProfit(yearNumber, grossProfitBeforeTax, amountOfTaxPaid, netProfitAfterTax);

        return newYearProfit;
    }


    // Method to edit the YearProfit
    public void editYearProfit(YearProfit yearProfit) {
        yearNumberField.setText(yearProfit.getYearNumber());
        grossProfitBeforeTaxField.setText(yearProfit.getGrossProfitBeforeTax());
        amountOfTaxPaidField.setText(yearProfit.getAmountOfTaxPaid());
    }

    // Method to update the YearProfit
    public void updateYearProfit(YearProfit yearProfit) {
        yearProfit.setYearNumber(yearNumberField.getText());
        yearProfit.setGrossProfitBeforeTax(grossProfitBeforeTaxField.getText());
        yearProfit.setAmountOfTaxPaid(amountOfTaxPaidField.getText());
        yearProfit.setNetProfitAfterTax(getNewYearProfit().getNetProfitAfterTax());

    }

    // Method to setPromptText in input fields when the user enter the mouse in the dialog box it self
    // onMouseMoved method yearProfitRomanianDiaLog.fxml
    public void userInputsWarning() {
        if (yearNumberField.getText().equals("")){
            yearNumberField.setPromptText("Completează Obligatoriu!");
            grossProfitBeforeTaxField.setPromptText("Completează Obligatoriu!");
            amountOfTaxPaidField.setPromptText("Completează Obligatoriu!");
        }
        else {
            yearNumberField.setPromptText("");
            yearNumberField.setPromptText("");
            grossProfitBeforeTaxField.setPromptText("");
            amountOfTaxPaidField.setPromptText("");
        }
    }

    // Method to paste the date from dataPiker in to yearNumberField
    public void pasteDate() {

        String pastedDate = getDateOption.getValue().format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy"));
        yearNumberField.setText(pastedDate);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFO!");
        alert.setHeaderText(null);
        alert.setContentText("Data a fost introdusă, continuă la următoarea rubrică!");
        alert.showAndWait();
    }
}
