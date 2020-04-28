package com.marinel;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import java.net.URL;
import java.text.DecimalFormat;
import java.util.ResourceBundle;

public class CalculatorController implements Initializable {

    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    Float data = 0f;
    Float secondOperand;
    Float theOutcome;
    int operation = -1;
    int createLeftString = 0;
    String showLeftString;

    @FXML
    private TextField display;
    @FXML
    private TextField displayLeft;
    @FXML
    private TextField displayMemo;
    @FXML
    private TextField displayMemo_2;
    @FXML
    private Button clear;
    @FXML
    private Button addToMemo;
    @FXML
    private Button clearMemo;
    @FXML
    private Button addToMemo_2;
    @FXML
    private Button clearMemo_2;
    @FXML
    private Button memory_1_recall;
    @FXML
    private Button memory_2_recall;
    @FXML
    private Button infoHelp;
    @FXML
    private Button minus;
    @FXML
    private Button division;
    @FXML
    private Button multiplication;
    @FXML
    private Button plus;
    @FXML
    private Button equals;
    @FXML
    private Button dot;
    @FXML
    private Button zero;
    @FXML
    private Button one;
    @FXML
    private Button two;
    @FXML
    private Button three;
    @FXML
    private Button four;
    @FXML
    private Button five;
    @FXML
    private Button six;
    @FXML
    private Button seven;
    @FXML
    private Button eight;
    @FXML
    private Button nine;


    @FXML
    public void handleButtonAction(ActionEvent event) {
        if (event.getSource() == one) {
            display.setText(display.getText() + "1");
            displayLeft.setText(display.getText());
        } else if (event.getSource() == two) {
            display.setText(display.getText() + "2");
            displayLeft.setText(display.getText());
        } else if (event.getSource() == three) {
            display.setText(display.getText() + "3");
            displayLeft.setText(display.getText());
        } else if (event.getSource() == four) {
            display.setText(display.getText() + "4");
            displayLeft.setText(display.getText());
        } else if (event.getSource() == five) {
            display.setText(display.getText() + "5");
            displayLeft.setText(display.getText());
        } else if (event.getSource() == six) {
            display.setText(display.getText() + "6");
            displayLeft.setText(display.getText());
        } else if (event.getSource() == seven) {
            display.setText(display.getText() + "7");
            displayLeft.setText(display.getText());
        } else if (event.getSource() == eight) {
            display.setText(display.getText() + "8");
            displayLeft.setText(display.getText());
        } else if (event.getSource() == nine) {
            display.setText(display.getText() + "9");
            displayLeft.setText(display.getText());
        } else if (event.getSource() == zero) {
            display.setText(display.getText() + "0");
            displayLeft.setText(display.getText());
        } else if (event.getSource() == clear) {
            display.setText("");
            displayLeft.setText("");
        } else if (event.getSource() == dot) {
            display.setText(display.getText() + ".");
            displayLeft.setText(display.getText());
        } else if (event.getSource() == addToMemo) {
            displayMemo.setText(display.getText());
        } else if (event.getSource() == clearMemo) {
            displayMemo.setText("");
        } else if (event.getSource() == addToMemo_2) {
            displayMemo_2.setText(display.getText());
        } else if (event.getSource() == clearMemo_2) {
            displayMemo_2.setText("");
        } else if (event.getSource() == memory_1_recall) {
            display.setText(displayMemo.getText());
        } else if (event.getSource() == memory_2_recall) {
            display.setText(displayMemo_2.getText());
        } else if (event.getSource() == plus) {
            createLeftString = 1;
            displayLeft.setText(display.getText() + "+");

            data = Float.parseFloat(display.getText());
            operation = 1; //Addition
            display.setText("");
        } else if (event.getSource() == minus) {
            createLeftString = 2;
            displayLeft.setText(display.getText() + "−");

            data = Float.parseFloat(display.getText());
            operation = 2; //Substraction
            display.setText("");
        } else if (event.getSource() == multiplication) {
            createLeftString = 3;
            displayLeft.setText(display.getText() + "×");

            data = Float.parseFloat(display.getText());
            operation = 3; //Multiplication
            display.setText("");
        } else if (event.getSource() == division) {
            createLeftString = 4;
            displayLeft.setText(display.getText() + "÷");

            data = Float.parseFloat(display.getText());
            operation = 4; //Division
            display.setText("");
        } else if (event.getSource() == equals) {
            secondOperand = Float.parseFloat(display.getText());
            switch (operation) {
                case 1: //Addition
                    theOutcome = data + secondOperand;
                    display.setText(decimalFormat.format(theOutcome));
                    break;
                case 2: //Subtraction
                    theOutcome = data - secondOperand;
                    display.setText(decimalFormat.format(theOutcome));
                    break;
                case 3: //Multiplication
                    theOutcome = data * secondOperand;
                    display.setText(decimalFormat.format(theOutcome));
                    break;
                case 4: //Division
                    theOutcome = 0f;
                    try {
                        theOutcome = data / secondOperand;
                    } catch(Exception e) {
                        display.setText("Error");
                    }
                    display.setText(decimalFormat.format(theOutcome));
                    break;
            }

            switch (createLeftString) {
                case 1: //Addition
                    showLeftString = decimalFormat.format(data) + " + " + decimalFormat.format(secondOperand) + " =";
                    displayLeft.setText(showLeftString);

                    break;
                case 2: //Subtraction
                    showLeftString = decimalFormat.format(data) + " - " + decimalFormat.format(secondOperand) + " =";
                    displayLeft.setText(showLeftString);

                    break;
                case 3: //Multiplication
                    showLeftString = decimalFormat.format(data) + " × " + decimalFormat.format(secondOperand) + " =";
                    displayLeft.setText(showLeftString);

                    break;
                case 4: //Division
                    showLeftString = decimalFormat.format(data) + " ÷ " + decimalFormat.format(secondOperand) + " =";
                    displayLeft.setText(showLeftString);
                    break;
            }
        }
    }

    @FXML
    public void handleInfoHelp() {
        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Instruction for use");
        infoAlert.setHeaderText(null);
        infoAlert.setWidth(300);
        infoAlert.setHeight(300);
        infoAlert.setContentText("PAY ATTENTION: with this calculator you can only do one operation at a time." +
                " EXAMPLE:   1 + 1 = 2\n   (#) You can't do that:   1 + 1 + 1 - 2 = 1\n   (#) OR:   2 × 2 - 1 = 3\n" +
                "   (#) OR:   2 × 2 × 2 + 5 - 3 = 10\nBecause this calculator" +
                " is designed to perform only simple operations: add, subtract, multiply, divide only between two numbers.");
        infoAlert.showAndWait();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {

        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Instruction for use");
        infoAlert.setHeaderText(null);
        infoAlert.setWidth(300);
        infoAlert.setHeight(300);
        infoAlert.setContentText("PAY ATTENTION: with this calculator you can only do one operation at a time." +
                " EXAMPLE:   1 + 1 = 2\n   (#) You can't do that:   1 + 1 + 1 - 2 = 1\n   (#) Or:   2 × 2 - 1 = 3\n" +
                "   (#) Or:   2 × 2 × 2 + 5 - 3 = 10\nBecause this calculator" +
                " is designed to perform only simple operations: add, subtract, multiply, divide only between two numbers.");
        infoAlert.showAndWait();

        displayLeft.setMaxWidth(Double.POSITIVE_INFINITY);
        display.setMaxWidth(Double.POSITIVE_INFINITY);
        clear.setMaxWidth(Double.POSITIVE_INFINITY);
        addToMemo.setMaxWidth(Double.POSITIVE_INFINITY);
        displayMemo.setMaxWidth(Double.POSITIVE_INFINITY);
        clearMemo.setMaxWidth(Double.POSITIVE_INFINITY);
        addToMemo_2.setMaxWidth(Double.POSITIVE_INFINITY);
        displayMemo_2.setMaxWidth(Double.POSITIVE_INFINITY);
        clearMemo_2.setMaxWidth(Double.POSITIVE_INFINITY);
        memory_1_recall.setMaxWidth(Double.POSITIVE_INFINITY);
        memory_2_recall.setMaxWidth(Double.POSITIVE_INFINITY);
        infoHelp.setMaxWidth(Double.POSITIVE_INFINITY);
    }
}
