package com.marinel;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.effect.DropShadow;
import javafx.scene.effect.Reflection;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.time.LocalTime;
import java.util.Optional;

public class Main extends Application {

    // Login details
    String userName = "marinel";
    String password = "0000";
    // Helper variables for login details
    String checkUserName;
    String checkPassword;


    @Override
    public void start(Stage primaryStage) { // add throws Exception if you don't want to use the login window

        primaryStage.setTitle("Login Window!!");
        primaryStage.centerOnScreen();
        BorderPane borderPane = new BorderPane();
        borderPane.setPadding(new Insets(10,50,150,50));

        //Adding HBox
        HBox hBox = new HBox();
        hBox.setPadding(new Insets(20,20,20,20));
        hBox.setAlignment(Pos.CENTER);
        hBox.setMaxHeight(50);

        //Adding GridPane
        GridPane gridPane = new GridPane();
        gridPane.setPadding(new Insets(40,20,20,20));
        gridPane.setAlignment(Pos.CENTER);
        gridPane.setHgap(10);
        gridPane.setVgap(7);
        gridPane.setMinWidth(900);
        gridPane.setMinHeight(216);

        //Implementing Nodes for GridPane
        Label labelUserName = new Label("Username:");
        labelUserName.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
        labelUserName.setId("label_user_name"); // for css

        // Get the user nume
        final TextField textFieldUserName = new TextField();
        textFieldUserName.setPrefWidth(250);

        Label labelPassword = new Label("Password:");
        labelPassword.setFont(Font.font("Courier New", FontWeight.BOLD, 14));
        labelPassword.setId("label_password"); // for css

        // Get the user password
        final PasswordField passwordField = new PasswordField();
        passwordField.setPrefWidth(250);

        Button btnLogin = new Button("Login");
        btnLogin.setFont(Font.font("Courier New", FontWeight.BOLD, 14));

        // Show message in this label if user has entered the correct login details or not
        final Label labelMessage = new Label();

        //Adding Nodes to GridPane layout
        gridPane.add(labelUserName, 0, 0);
        gridPane.add(textFieldUserName, 1, 0);
        gridPane.add(labelPassword, 0, 1);
        gridPane.add(passwordField, 1, 1);
        gridPane.add(btnLogin, 2, 1);
        gridPane.add(labelMessage, 1, 2);

        //Reflection for gridPane
        Reflection reflection = new Reflection();
        reflection.setFraction(1.2f);
        gridPane.setEffect(reflection);

        //DropShadow effect for text title
        DropShadow dropShadow = new DropShadow();
        dropShadow.setOffsetX(5);
        dropShadow.setOffsetY(5);

        //Adding text title and DropShadow effect to it
        Label labelTitle = new Label("Please Login");
        labelTitle.setFont(Font.font("Courier New", FontWeight.BOLD, 28));
        labelTitle.setEffect(dropShadow);

        //Adding text to HBox
        hBox.getChildren().add(labelTitle);

        //Add ID's to Nodes to use them in styles.css
        borderPane.setId("border_pane");
        gridPane.setId("grid_pane_root");
        btnLogin.setId("btn_Login");
        labelTitle.setId("login_text_title");

        // setOnAction for btnLogin
        btnLogin.setOnAction(new EventHandler<>() {
            @Override
            public void handle(ActionEvent e) {
                checkUserName = textFieldUserName.getText();
                checkPassword = passwordField.getText();
                if (checkUserName.equals(userName) && checkPassword.equals(password)) {
                    try {
                        labelMessage.setText(checkUserName + " congratulations you are in!");
                        labelMessage.setTextFill(Color.WHITE);
                        ButtonType enterBtn = new ButtonType("Enter", ButtonBar.ButtonData.NEXT_FORWARD);
                        Alert successfulLogin = new Alert(Alert.AlertType.CONFIRMATION,"", enterBtn);
                        successfulLogin.setTitle("Successful Login");

                        LocalTime localTime = LocalTime.now();
                        LocalTime midNight = LocalTime.of(0, 0, 0);
                        LocalTime midDay = LocalTime.of(12, 0, 0);
                        LocalTime afterNoon = LocalTime.of(19, 0, 0);
                        LocalTime nightTime = LocalTime.of(23, 59, 59);

                        if (localTime.isAfter(midNight) && localTime.isBefore(midDay)) {
                            successfulLogin.setHeaderText("Good morning " + checkUserName.toUpperCase() + ", I'm glad to see you again");
                        }

                        if (localTime.isAfter(midDay) && localTime.isBefore(afterNoon)) {
                            successfulLogin.setHeaderText("Good afternoon " + checkUserName.toUpperCase() + ", I'm glad to see you again");
                        }

                        if (localTime.isAfter(afterNoon) && localTime.isBefore(nightTime)) {
                            successfulLogin.setHeaderText("Good evening " + checkUserName.toUpperCase() + ", I'm glad to see you again");
                        }

                        successfulLogin.setContentText(checkUserName.toUpperCase() + " you are logged in successfully, click Enter to continue");
                        successfulLogin.setWidth(400);
                        successfulLogin.setHeight(110);
                        successfulLogin.showAndWait();
                        // If logged in successfully load the main fxml
                        Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
                        primaryStage.setTitle("MANAGEMENT AND ACCOUNTING");
                        primaryStage.setScene(new Scene(root, 1000, 450));
                        primaryStage.centerOnScreen();
                        primaryStage.show();
                    } catch (IOException g) {
                        g.printStackTrace();
                    }
                } else {
                    labelMessage.setText("Incorrect user name or password");
                    labelMessage.setTextFill(Color.WHITE);
                    labelMessage.setPadding(new Insets(2,2,2,12));
                    labelMessage.setFont(Font.font("Courier New", FontWeight.BOLD, 12));
                    ButtonType goBackBtn = new ButtonType("Go Back", ButtonBar.ButtonData.BACK_PREVIOUS);
                    Alert unsuccessfulLogin = new Alert(Alert.AlertType.ERROR,"", goBackBtn);
                    unsuccessfulLogin.setTitle("Unsuccessful Login");
                    unsuccessfulLogin.setHeaderText(null);
                    unsuccessfulLogin.setWidth(400);
                    unsuccessfulLogin.setHeight(110);
                    unsuccessfulLogin.setContentText(checkUserName + " you are not logged in. Please click Go Back" +
                            " and reenter the correct login details.");
                    unsuccessfulLogin.showAndWait();
                }
                // clear the login fields
                textFieldUserName.setText("");
                passwordField.setText("");
            }
        });

        //Add HBox and GridPane layout to BorderPane Layout
        borderPane.setTop(hBox);
        borderPane.setCenter(gridPane);

        //Adding BorderPane to the scene and loading CSS
        Scene scene = new Scene(borderPane);
        scene.getStylesheets().add(this.getClass().getResource("styles.css").toExternalForm());
        primaryStage.setScene(scene);
        primaryStage.setResizable(true);
        primaryStage.show();

        /*
          Use this block code below in case you don't want to use the login option
          Modify the public void start() method by adding the throws Exception
          Delete all the lines from line 29 to line 198
          Delete the variable checkUserName from public void stop() method
          Uncomment the lines 199 to 205
        */

        //Parent root = FXMLLoader.load(getClass().getResource("main.fxml"));
        //root.getStylesheets().add(this.getClass().getResource("styles.css").toExternalForm());
        //primaryStage.setTitle("MANAGEMENT AND ACCOUNTING");
        //primaryStage.setScene(new Scene(root, 1000, 450));
        //primaryStage.centerOnScreen();
        //primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }


    @Override
    public void stop() throws Exception {

        try {
            ButtonType closeTheProgramBtn = new ButtonType("Close The Program", ButtonBar.ButtonData.OK_DONE);
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"", closeTheProgramBtn);
            alert.initStyle(StageStyle.UNDECORATED);

            LocalTime localTime = LocalTime.now();
            LocalTime midNight = LocalTime.of(0, 0, 0);
            LocalTime midDay = LocalTime.of(12, 0, 0);
            LocalTime afterNoon = LocalTime.of(19, 0, 0);
            LocalTime nightTime = LocalTime.of(23, 0, 0);
            LocalTime sleepTime = LocalTime.of(23, 59, 59);

            if (localTime.isAfter(midNight) && localTime.isBefore(midDay)) {
                alert.setHeaderText("Să aveți o dimineaţă frumoasă, la revedere " + checkUserName.toUpperCase());
                alert.setContentText("Пожелавам ви добро утро, довиждане " + checkUserName.toUpperCase());
            }

            if (localTime.isAfter(midDay) && localTime.isBefore(afterNoon)) {
                alert.setHeaderText("Să aveți o zi frumoasă, la revedere " + checkUserName.toUpperCase());
                alert.setContentText("Пожелавам ви добър ден, довиждане " + checkUserName.toUpperCase());
            }

            if (localTime.isAfter(afterNoon) && localTime.isBefore(nightTime)) {
                alert.setHeaderText("Să aveți o seară frumoasă, la revedere " + checkUserName.toUpperCase());
                alert.setContentText("Пожелавам ви хубава вечер, довиждане " + checkUserName.toUpperCase());
            }

            if (localTime.isAfter(nightTime) && localTime.isBefore(sleepTime)) {
                alert.setHeaderText("Să aveți o noapte bună, la revedere " + checkUserName.toUpperCase());
                alert.setContentText("Пожелавам ви лека нощ, довиждане " + checkUserName.toUpperCase());
            }

            alert.setWidth(400);
            alert.setHeight(110);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == closeTheProgramBtn) {
                super.stop();
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
