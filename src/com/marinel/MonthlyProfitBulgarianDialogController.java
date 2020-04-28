package com.marinel;

import com.marinel.datamodel.MonthlyProfit;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public class MonthlyProfitBulgarianDialogController {

    // Create new MonthlyProfit object
    MonthlyProfit newMonthlyProfit;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @FXML
    private DatePicker getDateOption;
    @FXML
    private TextField monthNameField;
    @FXML
    private TextField yearNumberField;
    @FXML
    private TextField grossProfitField;
    @FXML
    private TextField productsOriginalPriceField;
    @FXML
    private TextField rentAmountField;
    @FXML
    private  TextField salaryAmountField;
    @FXML
    private TextField electricityCostField;
    @FXML
    private TextField heatingCostField;
    @FXML
    private TextField waterCostField;
    @FXML
    private TextField advertisingCostField;
    @FXML
    private TextField religiousDayExpensesField;
    @FXML
    private TextField otherExpensesField;


    // Collect all data from monthlyProfitBulgarianDialog.fxml and then
    // update the constructor in MonthlyProfit.java
    public MonthlyProfit getNewProfit() {

        String monthlyName = monthNameField.getText();

        if (monthNameField.getText() == null || monthNameField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Внимание ... празно поле за въвеждане!");
            alert.setHeaderText(null);
            alert.setWidth(400);
            alert.setHeight(250);
            alert.setContentText("ти не попълни полето за месец, но те уверявам, че можеш да го редактираш по-късно, " +
                    "просто трябва да използваш опцията за редактиране на менюто.");
            alert.showAndWait();
            monthNameField.setText("Null");
            monthlyName = monthNameField.getText();
        }

        String yearNumber = yearNumberField.getText();

        if (yearNumberField.getText() == null || yearNumberField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Внимание ... празно поле за въвеждане!");
            alert.setHeaderText(null);
            alert.setWidth(400);
            alert.setHeight(250);
            alert.setContentText("Гти не попълни полето за година, но те уверявам, че можеш да го редактираш по-късно, " +
                    "просто използвай опцията за редактиране на менюто.");
            alert.showAndWait();
            yearNumberField.setText("Null");
            yearNumber = yearNumberField.getText();
        }

        String grossProfit = grossProfitField.getText();

        if (grossProfitField.getText() == null || grossProfitField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Внимание ... празно поле за въвеждане!");
            alert.setHeaderText(null);
            alert.setWidth(400);
            alert.setHeight(250);
            alert.setContentText("ти не попълни полето за брутна печалба, но ви уверявам, че можете да го редактирате по-късно, " +
                    "просто трябва да използвате опцията за редактиране на менюто.");
            alert.showAndWait();
            grossProfitField.setText(String.valueOf(0));
            grossProfit = grossProfitField.getText();
        }

        String productsOriginalPrice = productsOriginalPriceField.getText();

        if (productsOriginalPriceField.getText() == null || productsOriginalPriceField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Внимание ... празно поле за въвеждане!");
            alert.setHeaderText(null);
            alert.setWidth(400);
            alert.setHeight(250);
            alert.setContentText("ти не попълни инвестиционното поле, но те уверявам, че можеш да го редактираш по-късно, " +
                    "просто използвай опцията за редактиране на менюто.");
            alert.showAndWait();
            productsOriginalPriceField.setText(String.valueOf(0));
            productsOriginalPrice = productsOriginalPriceField.getText();
        }

        String rentAmount = rentAmountField.getText();

        if (rentAmountField.getText() == null || rentAmountField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Внимание ... празно поле за въвеждане!");
            alert.setHeaderText(null);
            alert.setWidth(400);
            alert.setHeight(250);
            alert.setContentText("ти не попълни полето за наем, но ви уверявам, че можете да го редактирате по-късно, " +
                    "просто използвайте опцията за редактиране в менюто.");
            alert.showAndWait();
            rentAmountField.setText(String.valueOf(0));
            rentAmount = rentAmountField.getText();
        }

        String salaryAmount = salaryAmountField.getText();

        if (salaryAmountField.getText() == null || salaryAmountField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Внимание ... празно поле за въвеждане!");
            alert.setHeaderText(null);
            alert.setWidth(400);
            alert.setHeight(250);
            alert.setContentText("ти не попълни полето за заплати, но те уверявам, че можеш да го редактираш по-късно, " +
                    "просто използвай опцията за редактиране в менюто.");
            alert.showAndWait();
            salaryAmountField.setText(String.valueOf(0));
            salaryAmount = salaryAmountField.getText();
        }

        String electricityCost = electricityCostField.getText();

        if (electricityCostField.getText() == null || electricityCostField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Внимание ... празно поле за въвеждане!");
            alert.setHeaderText(null);
            alert.setWidth(400);
            alert.setHeight(250);
            alert.setContentText("ти не попълни електрическото поле, но ви уверявам, че можете да го редактирате по-късно, " +
                    "просто използвайте опцията за редактиране на менюто.");
            alert.showAndWait();
            electricityCostField.setText(String.valueOf(0));
            electricityCost = electricityCostField.getText();
        }

        String heatingCost = heatingCostField.getText();

        if (heatingCostField.getText() == null || heatingCostField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Внимание ... празно поле за въвеждане!");
            alert.setHeaderText(null);
            alert.setWidth(400);
            alert.setHeight(250);
            alert.setContentText("ти не попълни полето за отопление, но ви уверявам, че можете да го редактирате по-късно, " +
                    "просто използвайте опцията за редактиране на менюто.");
            alert.showAndWait();
            heatingCostField.setText(String.valueOf(0));
            heatingCost = heatingCostField.getText();
        }

        String waterCost = waterCostField.getText();

        if (waterCostField.getText() == null || waterCostField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Внимание ... празно поле за въвеждане!");
            alert.setHeaderText(null);
            alert.setWidth(400);
            alert.setHeight(250);
            alert.setContentText("ти не попълни водното поле, но ви уверявам, че можете да го редактирате по-късно, " +
                    "просто използвайте опцията за редактиране в менюто.");
            alert.showAndWait();
            waterCostField.setText(String.valueOf(0));
            waterCost = waterCostField.getText();
        }

        String advertisingCost = advertisingCostField.getText();

        if (advertisingCostField.getText() == null || advertisingCostField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Внимание ... празно поле за въвеждане!");
            alert.setHeaderText(null);
            alert.setWidth(400);
            alert.setHeight(250);
            alert.setContentText("ти не попълни рекламното поле, но ви уверявам, че можете да го редактирате по-късно, " +
                    "просто трябва да използвате опцията за редактиране на менюто.");
            alert.showAndWait();
            advertisingCostField.setText(String.valueOf(0));
            advertisingCost = advertisingCostField.getText();
        }

        String religiousDayExpenses = religiousDayExpensesField.getText();

        if (religiousDayExpensesField.getText() == null || religiousDayExpensesField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Внимание ... празно поле за въвеждане!");
            alert.setHeaderText(null);
            alert.setWidth(400);
            alert.setHeight(250);
            alert.setContentText("не си попълнила празничното поле, но ви уверявам, че можете да го редактирате по-късно, " +
                    "просто трябва да използвате опцията за редактиране на менюто.");
            alert.showAndWait();
            religiousDayExpensesField.setText(String.valueOf(0));
            religiousDayExpenses = religiousDayExpensesField.getText();
        }

        String otherExpenses = otherExpensesField.getText();

        if (otherExpensesField.getText() == null || otherExpensesField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Внимание ... празно поле за въвеждане!");
            alert.setHeaderText(null);
            alert.setWidth(400);
            alert.setHeight(250);
            alert.setContentText("ти не попълни полето за други разходи, но те уверявам, че можеш да го редактираш по-късно, " +
                    "просто използвай опцията за редактиране на менюто.");
            alert.showAndWait();
            otherExpensesField.setText(String.valueOf(0));
            otherExpenses = otherExpensesField.getText();
        }


        double valueOfGrossProfitField = Double.parseDouble(grossProfit);
        double valueOfProductsOriginalPrice = Double.parseDouble(productsOriginalPrice);
        double valueOfRentAmount = Double.parseDouble(rentAmount);
        double valueOfSalaryAmountField = Double.parseDouble(salaryAmount);
        double valueOfElectricityCostField = Double.parseDouble(electricityCost);
        double valueOfHeatingCostField = Double.parseDouble(heatingCost);
        double valueOfWaterCostField = Double.parseDouble(waterCost);
        double valueOfAdvertisingCostField = Double.parseDouble(advertisingCost);
        double valueOfReligiousDayExpensesField = Double.parseDouble(religiousDayExpenses);
        double valueOfOtherExpensesField = Double.parseDouble(otherExpenses);
        double valueOfNetProfit = valueOfGrossProfitField - (valueOfProductsOriginalPrice + valueOfRentAmount + valueOfSalaryAmountField +
                valueOfOtherExpensesField + valueOfElectricityCostField + valueOfHeatingCostField +
                valueOfWaterCostField + valueOfAdvertisingCostField + valueOfReligiousDayExpensesField);

        String netIncome = "" + decimalFormat.format(valueOfNetProfit);

        // Actualise new MonthlyProfit object created in line 15 whit the data collected above
        newMonthlyProfit = new MonthlyProfit(monthlyName, yearNumber, grossProfit, productsOriginalPrice, rentAmount, salaryAmount, electricityCost,
                heatingCost, waterCost, advertisingCost, religiousDayExpenses, otherExpenses, netIncome);

        return newMonthlyProfit;
    }

    // Method to edit the monthlyProfit
    public void editProfit(MonthlyProfit monthlyProfit) {

        monthNameField.setText(monthlyProfit.getMonthlyName());
        yearNumberField.setText(monthlyProfit.getYearNumber());
        grossProfitField.setText(monthlyProfit.getGrossProfit());
        productsOriginalPriceField.setText(monthlyProfit.getProductsOriginalPrice());
        rentAmountField.setText(monthlyProfit.getRentAmount());
        salaryAmountField.setText(monthlyProfit.getSalaryAmount());
        electricityCostField.setText(monthlyProfit.getElectricityCost());
        heatingCostField.setText(monthlyProfit.getHeatingCost());
        waterCostField.setText(monthlyProfit.getWaterCost());
        advertisingCostField.setText(monthlyProfit.getAdvertisingCost());
        religiousDayExpensesField.setText(monthlyProfit.getReligiousDayExpenses());
        otherExpensesField.setText(monthlyProfit.getOtherExpenses());
    }

    // Method to update the monthlyProfit
    public void updateProfit(MonthlyProfit monthlyProfit) {

        monthlyProfit.setMonthlyName(monthNameField.getText());
        monthlyProfit.setYearNumber(yearNumberField.getText());
        monthlyProfit.setGrossProfit(grossProfitField.getText());
        monthlyProfit.setProductsOriginalPrice(productsOriginalPriceField.getText());
        monthlyProfit.setRentAmount(rentAmountField.getText());
        monthlyProfit.setSalaryAmount(salaryAmountField.getText());
        monthlyProfit.setElectricityCost(electricityCostField.getText());
        monthlyProfit.setHeatingCost(heatingCostField.getText());
        monthlyProfit.setOtherExpenses(otherExpensesField.getText());
        monthlyProfit.setWaterCost(waterCostField.getText());
        monthlyProfit.setAdvertisingCost(advertisingCostField.getText());
        monthlyProfit.setReligiousDayExpenses(religiousDayExpensesField.getText());
        monthlyProfit.setNetIncome(getNewProfit().getNetIncome());
    }

    // Method to setPromptText in input fields when the user enter the mouse in the dialog box it self
    // onMouseMoved method monthlyProfitBulgarianDialog.fxml
    public void userInputsWarning() {

        if (monthNameField.getText().equals("")){
            monthNameField.setPromptText("Изпълнете Задължително!");
            yearNumberField.setPromptText("Изпълнете Задължително!");
            grossProfitField.setPromptText("Изпълнете Задължително!");
            productsOriginalPriceField.setPromptText("Изпълнете Задължително!");
            rentAmountField.setPromptText("Изпълнете Задължително!");
            salaryAmountField.setPromptText("Изпълнете Задължително!");
            electricityCostField.setPromptText("Изпълнете Задължително!");
            heatingCostField.setPromptText("Изпълнете Задължително!");
            waterCostField.setPromptText("Изпълнете Задължително!");
            advertisingCostField.setPromptText("Изпълнете Задължително!");
            religiousDayExpensesField.setPromptText("Изпълнете Задължително!");
            otherExpensesField.setPromptText("Изпълнете Задължително!");
        } else {
            monthNameField.setPromptText("");
            yearNumberField.setPromptText("");
            grossProfitField.setPromptText("");
            productsOriginalPriceField.setPromptText("");
            rentAmountField.setPromptText("");
            salaryAmountField.setPromptText("");
            electricityCostField.setPromptText("");
            heatingCostField.setPromptText("");
            waterCostField.setPromptText("");
            advertisingCostField.setPromptText("");
            religiousDayExpensesField.setPromptText("");
            otherExpensesField.setPromptText("");
        }
    }

    // Method to paste the date from dataPiker in to monthNameField and yearNumberField
    public void pasteDate() {
        String pastedMonth = getDateOption.getValue().format(DateTimeFormatter.ofPattern("MMMM"));
        monthNameField.setText(pastedMonth);

        String pastedYear = getDateOption.getValue().format(DateTimeFormatter.ofPattern("yyyy"));
        yearNumberField.setText(pastedYear);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("ИНФОРМАЦИЯ!");
        alert.setHeaderText(null);
        alert.setContentText("Гери, Луна и Година бяха представени, продължете към следващите раздели!");
        alert.showAndWait();
    }
}
