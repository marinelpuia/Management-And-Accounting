package com.marinel;

import com.marinel.datamodel.MonthlyProfit;
import com.marinel.datamodel.MonthlyProfitData;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Predicate;

public class MainMonthlyProfitController {

    private MonthlyProfitData monthlyProfitData;
    private FilteredList<MonthlyProfit> profitFilteredList;

    // All this is for search box option
    Text textTitle = new Text();
    private final TextField searchBox = new TextField();
    private Stage dialogSearchBox = new Stage();
    Rectangle2D checkScreenBounds;
    VBox dialogVBox;
    Scene dialogScene;


    @FXML
    private TableView<MonthlyProfit> monthlyProfitTableView;
    @FXML
    private BorderPane monthlyProfitMainPanel;

    @FXML
    private Menu menuBtn;
    @FXML
    private Menu buttonSelectLanguage;

    @FXML
    private Label labelTitle;
    @FXML
    private MenuItem addProfitBtn;
    @FXML
    private MenuItem editProfitBtn;
    @FXML
    private MenuItem deleteProfitBtn;

    @FXML
    private TableColumn<MonthlyProfit,?> numberOfRow;
    @FXML
    private TableColumn<MonthlyProfit,TextField> monthlyName;
    @FXML
    private TableColumn<MonthlyProfit,TextField> yearNumber;
    @FXML
    private TableColumn<MonthlyProfit,TextField> grossProfit;
    @FXML
    private TableColumn<MonthlyProfit,TextField> productsOriginalPrice;
    @FXML
    private TableColumn<MonthlyProfit,TextField> rentAmount;
    @FXML
    private TableColumn<MonthlyProfit,TextField> salaryAmount;
    @FXML
    private TableColumn<MonthlyProfit,TextField> electricityCost;
    @FXML
    private TableColumn<MonthlyProfit,TextField> heatingCost;
    @FXML
    private TableColumn<MonthlyProfit,TextField> waterCost;
    @FXML
    private TableColumn<MonthlyProfit,TextField> advertisingCost;
    @FXML
    private TableColumn<MonthlyProfit,TextField> religiousDayExpenses;
    @FXML
    private TableColumn<MonthlyProfit,TextField> otherExpenses;
    @FXML
    private TableColumn<MonthlyProfit,String> netIncome;

    // For bulgarian version
    @FXML
    private MenuItem addProfitBtnBul;
    @FXML
    private MenuItem editProfitBtnBul;
    @FXML
    private MenuItem deleteProfitBtnBul;

    @FXML
    private Menu openSearchBox;
    @FXML
    private MenuItem searchBoxOpenBtn;
    @FXML
    private MenuItem searchBoxCloseBtn;

    @FXML
    private Menu legend;
    @FXML
    private MenuItem openLegendBtn;


    public void initialize() {

        // initialize the object monthlyProfitData from MonthlyProfitData.java
        monthlyProfitData = new MonthlyProfitData();
        monthlyProfitData.loadProfits();
        //initialize the TableView<MonthlyProfit> monthlyProfitTableView
        monthlyProfitTableView.getItems().setAll(monthlyProfitData.getProfits()); // FilteredList
        //monthlyProfitTableView.setItems(monthlyProfitData.getProfits()); // Use in case of non FilteredList

        labelTitle.setTextFill(Color.BLUE);
        labelTitle.setMaxWidth(Double.POSITIVE_INFINITY);
        labelTitle.setAlignment(Pos.CENTER);

        numberOfRow.setMinWidth(22);
        numberOfRow.setMaxWidth(22);
        numberOfRow.setSortable(false);
        numberOfRow.setCellValueFactory(column-> new ReadOnlyObjectWrapper(monthlyProfitTableView.getItems().indexOf(column.getValue()) + 1));

        searchBox.setId("searchBox");

        // *** Call back to change the background color of the netIncome cell based on the % expenses
        netIncome.setCellFactory(parameter -> new TableCell<>() {
            @Override
            protected void updateItem(String item, boolean empty) {
                if (!empty) {
                    int currentIndex = indexProperty()
                            .getValue() < 0 ? 0
                            : indexProperty().getValue();

                    String gross = parameter
                            .getTableView().getItems()
                            .get(currentIndex).getGrossProfit();

                    String netIncome = parameter
                            .getTableView().getItems()
                            .get(currentIndex).getNetIncome();

                    double grossProfitHelp = Double.parseDouble(gross);
                    double netIncomeConverted = Double.parseDouble(netIncome);
                    double profitZero = grossProfitHelp - grossProfitHelp;
                    double smallProfit = grossProfitHelp * (30.0d / 100.0d);
                    double mediumProfit = grossProfitHelp * (60.0d / 100.0d);
                    double largeProfit = grossProfitHelp * (90.0d / 100.0d);

                    if (netIncomeConverted < 0) {
                        setTextFill(Color.WHITE);
                        setStyle("-fx-font-weight: bold");
                        setStyle("-fx-background-color: red");
                        setText(netIncome);
                    } else if (netIncomeConverted == profitZero) {
                        setTextFill(Color.WHITE);
                        setStyle("-fx-font-weight: bold");
                        setStyle("-fx-background-color: #000000");
                        setText(netIncome);
                    } else if ((netIncomeConverted > profitZero) && (netIncomeConverted <= smallProfit)) {
                        setTextFill(Color.WHITE);
                        setStyle("-fx-font-weight: bold");
                        setStyle("-fx-background-color: #FFA500"); // orange =  0% to 30% small profit
                        setText(netIncome);
                    } else if ((netIncomeConverted > smallProfit) && (netIncomeConverted <= mediumProfit)) {
                        setTextFill(Color.WHITE);
                        setStyle("-fx-font-weight: bold");
                        setStyle("-fx-background-color: blue"); // albastru = 30% to 60% medium profit
                        setText(netIncome);
                    } else if ((netIncomeConverted > mediumProfit) && (netIncomeConverted <= largeProfit)) {
                        setTextFill(Color.WHITE);
                        setStyle("-fx-font-weight: bold");
                        setStyle("-fx-background-color: green"); // verde = 60% to 90% large profit
                        setText(netIncome);
                    } else  {
                        setTextFill(Color.WHITE);
                        setStyle("-fx-font-weight: bold");
                        setStyle("-fx-background-color: #25f709"); // verde aprins = extra large profit peste 90%
                        setText(netIncome);
                    }
                }
            }
        });

        /* === CREATE FILTERED LIST === */
        profitFilteredList = new FilteredList<>(monthlyProfitData.getProfits());
        searchBox.setOnKeyReleased(e -> {
            searchBox.textProperty().addListener((observableValue, oldValue, newValue) ->
                    profitFilteredList.setPredicate((Predicate<? super MonthlyProfit>) profits -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }

                        String lowerCaseFilter = newValue.toLowerCase();

                        if (profits.getMonthlyName().toLowerCase().contains(lowerCaseFilter)) {
                            return true;
                        } else return profits.getYearNumber().toLowerCase().contains(lowerCaseFilter);
                    }));

            SortedList<MonthlyProfit> profitSortedList = new SortedList<>(profitFilteredList);
            profitSortedList.comparatorProperty().bind(monthlyProfitTableView.comparatorProperty());

            monthlyProfitTableView.setItems(profitSortedList);
            monthlyProfitTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            monthlyProfitTableView.getSelectionModel().selectFirst();
            monthlyProfitTableView.refresh();

        });

        /* ===  Edit or Delete the item from the table view when the user select the item and then click the right mouse button === */

        monthlyProfitTableView.setRowFactory(tableView -> {
            final TableRow<MonthlyProfit> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem removeMenuItem = new MenuItem("Delete this month");
            final MenuItem editItemRo = new MenuItem("Editează această lună");
            final MenuItem editItemBg = new MenuItem("Редактиране на този месец");
            removeMenuItem.setOnAction(event -> {
                MonthlyProfit selectedProfit = monthlyProfitTableView.getSelectionModel().getSelectedItem();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm the delete action!");
                alert.setHeaderText(null);
                alert.setWidth(300);
                alert.setHeight(100);
                alert.setContentText("Are you sure you want to delete the profit from ( " + selectedProfit.getMonthlyName()
                        + " ) with the amount of  ( " + selectedProfit.getNetIncome() + " ) ?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    monthlyProfitData.deleteProfit(selectedProfit);
                    monthlyProfitData.saveProfits();
                    monthlyProfitTableView.setItems(monthlyProfitData.getProfits());
                    monthlyProfitTableView.refresh();
                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                    infoAlert.setTitle("Confirm the deletion!");
                    infoAlert.setHeaderText(null);
                    infoAlert.setWidth(300);
                    infoAlert.setHeight(100);
                    infoAlert.setContentText("The delete operation was successfully executed!!!");
                    infoAlert.showAndWait();
                }
                //monthlyProfitTableView.getItems().remove(row.getItem()); // delete the row
            });
            // delete the selected item
            contextMenu.getItems().add(removeMenuItem);

            // edit the selected item RO
            editItemRo.setOnAction(event -> showEditProfitDialog());
            contextMenu.getItems().add(editItemRo);

            // edit the selected item BG
            editItemBg.setOnAction(event -> showEditProfitBulgarianDialog());
            contextMenu.getItems().add(editItemBg);

            // Set context menu on row, but use a binding to make it only show for non-empty rows:
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );
            return row;
        });

    } // END INITIALIZE


    @FXML
    public void showAddProfitDialog() {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(monthlyProfitMainPanel.getScene().getWindow());
        dialog.setTitle("Adaugă profit lunar!");
        dialog.setContentText("Te rog să completezi informațiile profitului in functie de lună!");
        dialog.setX(550);
        dialog.setY(100);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("monthlyProfitRomanianDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            //System.out.println("Could not load the dialog pane");
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            MonthlyProfitRomanianDialogController monthlyProfitRomanianDialogController = fxmlLoader.getController();
            MonthlyProfit newMonthlyProfit = monthlyProfitRomanianDialogController.getNewProfit();
            monthlyProfitData.addProfit(newMonthlyProfit);
            monthlyProfitData.saveProfits();
            monthlyProfitTableView.setItems(monthlyProfitData.getProfits());
            monthlyProfitTableView.refresh();
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Confirmă adăugarea.");
            infoAlert.setHeaderText(null);
            infoAlert.setWidth(300);
            infoAlert.setHeight(100);
            infoAlert.setContentText("Operațiunea de adăugare a fost executată cu succes!!!");
            infoAlert.showAndWait();
        }
    }

    @FXML
    public void showEditProfitDialog() {

        MonthlyProfit selectedProfit = monthlyProfitTableView.getSelectionModel().getSelectedItem();
        if (selectedProfit == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertisment ... profit neselectat!");
            alert.setHeaderText(null);
            alert.setContentText("Te rog să selectezi profitul pe care doresti să-l editezi!");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(monthlyProfitMainPanel.getScene().getWindow());
        dialog.setTitle("Editați produsul!");
        dialog.setHeaderText(null);
        dialog.setContentText("Te rog să editezi informațiile profitului pe luna selectată!");
        dialog.setX(550);
        dialog.setY(100);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("monthlyProfitRomanianDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            //System.out.println("Could not load the dialog pane");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        MonthlyProfitRomanianDialogController monthlyProfitRomanianDialogController = fxmlLoader.getController();
        monthlyProfitRomanianDialogController.editProfit(selectedProfit);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            monthlyProfitRomanianDialogController.updateProfit(selectedProfit);
            monthlyProfitData.saveProfits();
            monthlyProfitTableView.refresh();
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Confirmă editarea.");
            infoAlert.setHeaderText(null);
            infoAlert.setWidth(300);
            infoAlert.setHeight(100);
            infoAlert.setContentText("Operațiunea de editare a fost executată cu succes!!!");
            infoAlert.showAndWait();
        }
    }

    @FXML
    public void deleteProfit() {

        MonthlyProfit selectedProfit = monthlyProfitTableView.getSelectionModel().getSelectedItem();
        if (selectedProfit == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertisment ... profit neselectat!");
            alert.setHeaderText(null);
            alert.setContentText("Te rog să selectezi profitul pe care doresti să-l ștergi!");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmă acțiunea de ștergere");
        alert.setHeaderText(null);
        alert.setWidth(300);
        alert.setHeight(100);
        alert.setContentText("Ești sigur că vrei să ștergi din listă profitul din luna ( " + selectedProfit.getMonthlyName()
                + " ) cu suma de  ( " + selectedProfit.getNetIncome() + " ) ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            monthlyProfitData.deleteProfit(selectedProfit);
            monthlyProfitData.saveProfits();
            monthlyProfitTableView.setItems(monthlyProfitData.getProfits());
            monthlyProfitTableView.refresh();
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Confirmă stergerea.");
            infoAlert.setHeaderText(null);
            infoAlert.setWidth(300);
            infoAlert.setHeight(100);
            infoAlert.setContentText("Operațiunea de ștergere a fost executată cu succes!!!");
            infoAlert.showAndWait();
        }
    }

    /* === FOR BULGARIAN VERSION === */

    @FXML
    public void showAddProfitBulgarianDialog() {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(monthlyProfitMainPanel.getScene().getWindow());
        dialog.setTitle("Добавете месечна печалба!");
        dialog.setContentText("моля попълни информацията за печалбата по месеци!");
        dialog.setX(550);
        dialog.setY(100);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("monthlyProfitBulgarianDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Could not load the dialog pane");
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            MonthlyProfitBulgarianDialogController monthlyProfitBulgarianDialogController = fxmlLoader.getController();
            MonthlyProfit newProfit = monthlyProfitBulgarianDialogController.getNewProfit();
            monthlyProfitData.addProfit(newProfit);
            monthlyProfitData.saveProfits();
            monthlyProfitTableView.setItems(monthlyProfitData.getProfits());
            monthlyProfitTableView.refresh();
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Моля, потвърдете добавянето.");
            infoAlert.setHeaderText(null);
            infoAlert.setWidth(300);
            infoAlert.setHeight(100);
            infoAlert.setContentText("Oперацията за добавяне беше успешна!!!");
            infoAlert.showAndWait();
        }
    }


    @FXML
    public void showEditProfitBulgarianDialog() {

        MonthlyProfit selectedProfit = monthlyProfitTableView.getSelectionModel().getSelectedItem();
        if (selectedProfit == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение ... неизбрана печалба!");
            alert.setHeaderText(null);
            alert.setContentText("моля, изберете печалбата, която искате да редактирате!");
            alert.showAndWait();
            return;

        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(monthlyProfitMainPanel.getScene().getWindow());
        dialog.setTitle("Редактирайте продукта!");
        dialog.setHeaderText(null);
        dialog.setContentText("моля, редактирай информацията за печалбата за избрания месец!");
        dialog.setX(550);
        dialog.setY(100);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("monthlyProfitBulgarianDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Could not load the dialog pane");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        MonthlyProfitBulgarianDialogController monthlyProfitBulgarianDialogController = fxmlLoader.getController();
        monthlyProfitBulgarianDialogController.editProfit(selectedProfit);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            monthlyProfitBulgarianDialogController.updateProfit(selectedProfit);
            monthlyProfitData.saveProfits();
            monthlyProfitTableView.refresh();
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Моля, потвърдете редакцията.");
            infoAlert.setHeaderText(null);
            infoAlert.setWidth(300);
            infoAlert.setHeight(100);
            infoAlert.setContentText("Oперацията за добавяне беше успешна!!!");
            infoAlert.showAndWait();
        }
    }


    @FXML
    public void deleteProfitBulgarian() {

        MonthlyProfit selectedProfit = monthlyProfitTableView.getSelectionModel().getSelectedItem();
        if (selectedProfit == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение ... неизбрана печалба!");
            alert.setHeaderText(null);
            alert.setContentText("моля изберете печалбата, която искате да изтриете!");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Потвърдете действието за изтриване");
        alert.setHeaderText(null);
        alert.setWidth(300);
        alert.setHeight(100);
        alert.setContentText("вие сте сигурни, че искате да изтриете от списъка печалбата за ( " + selectedProfit.getMonthlyName()
                + " ) със сумата ( " + selectedProfit.getNetIncome() + " ) ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            monthlyProfitData.deleteProfit(selectedProfit);
            monthlyProfitData.saveProfits();
            monthlyProfitTableView.setItems(monthlyProfitData.getProfits());
            monthlyProfitTableView.refresh();
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Потвърждение за изтриване.");
            infoAlert.setHeaderText(null);
            infoAlert.setWidth(300);
            infoAlert.setHeight(100);
            infoAlert.setContentText("Oперацията за изтриване беше успешна!!!");
            infoAlert.showAndWait();
        }
    }

    /* ==== OPEN SEARCH BOX  === */

    @FXML
    public void openSearchBox() {

        dialogSearchBox = new Stage();
        dialogSearchBox.setTitle("DETAILED SEARCH");
        dialogSearchBox.initModality(Modality.NONE);
        dialogSearchBox.setResizable(false);
        checkScreenBounds = Screen.getPrimary().getVisualBounds();
        dialogSearchBox.setX((checkScreenBounds.getWidth() / 3));
        dialogSearchBox.setY((checkScreenBounds.getHeight() / 1.2));

        textTitle.setText("You can search for a profit in the box below.");
        textTitle.setId("searchBoxTitle");
        String color = "#ffffff";
        textTitle.setFill(Paint.valueOf(color));

        dialogVBox = new VBox(5);
        dialogVBox.setId("dialogSearch");
        dialogVBox.setAlignment(Pos.CENTER);
        dialogVBox.getChildren().addAll(textTitle,searchBox);
        dialogScene = new Scene(dialogVBox, 500, 61);
        dialogScene.getStylesheets().add(this.getClass().getResource("styles.css").toExternalForm());
        dialogVBox.setPadding(new Insets(5, 5, 5, 5));
        dialogSearchBox.setScene(dialogScene);

        searchBox.setMaxHeight(30);
        searchBox.setFocusTraversable(false);
        searchBox.setPromptText("Type here: (Month) or (Year)");

        dialogSearchBox.show();
        monthlyProfitTableView.refresh();
    }

    /* ==== CLOSE SEARCH BOX  === */

    @FXML
    public void closeSearchBox() {
        searchBox.clear();
        monthlyProfitTableView.setItems(monthlyProfitData.getProfits());
        monthlyProfitTableView.refresh();
        dialogSearchBox.close();
    }

    /* ==== OPEN LEGEND  === */
    @FXML
    public void openLegend() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("legendMonthlyProfit.fxml"));
        root.getStylesheets().add(this.getClass().getResource("styles.css").toExternalForm());
        Stage primaryStageLegend = new Stage();
        primaryStageLegend.setResizable(false);
        primaryStageLegend.setTitle("LEGEND");
        primaryStageLegend.setScene(new Scene(root, 980, 753)); // 700
        primaryStageLegend.show();
    }

    /* === Delete the item from the table view when the user select the item and then press the delete key ==== */

    @FXML
    public void handleDeleteKeyPressed(KeyEvent keyEvent) {

        MonthlyProfit selectedProfit = monthlyProfitTableView.getSelectionModel().getSelectedItem();

        if (selectedProfit != null) {

            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmă acțiunea de ștergere");
            alert.setHeaderText(null);
            alert.setWidth(300);
            alert.setHeight(100);
            alert.setContentText("Ești sigur că vrei să ștergi din listă profitul din luna ( " + selectedProfit.getMonthlyName()
                    + " ) cu suma de  ( " + selectedProfit.getNetIncome() + " ) ?");

            if (keyEvent.getCode().equals(KeyCode.DELETE)) {

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    monthlyProfitData.deleteProfit(selectedProfit);
                    monthlyProfitData.saveProfits();
                    monthlyProfitTableView.setItems(monthlyProfitData.getProfits());
                    monthlyProfitTableView.refresh();
                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                    infoAlert.setTitle("Confirmă stergerea.");
                    infoAlert.setHeaderText(null);
                    infoAlert.setWidth(300);
                    infoAlert.setHeight(100);
                    infoAlert.setContentText("Operațiunea de ștergere a fost executată cu succes!!!");
                    infoAlert.showAndWait();
                }
            }
        }
    }

    /* ==== MENU BULGARIAN LANGUAGE  === */

    public void bulgarianLanguage() {

        menuBtn.setText("меню");
        addProfitBtn.setVisible(false);
        editProfitBtn.setVisible(false);
        deleteProfitBtn.setVisible(false);

        addProfitBtnBul.setVisible(true);
        editProfitBtnBul.setVisible(true);
        deleteProfitBtnBul.setVisible(true);

        buttonSelectLanguage.setText("Selectează Limba");

        legend.setText("Цветова легенда");
        openLegendBtn.setText("Отворена легенда");

        openSearchBox.setText("Поле за търсене");
        searchBoxOpenBtn.setText("Отворете полето за търсене");
        searchBoxCloseBtn.setText("Затворете полето за търсене");
        textTitle.setText("Даниел или Гери, можете да потърсите продукт в полето по-долу.");
        searchBox.setPromptText("Въведете тук: (месец) или (година)");

        labelTitle.setText("МЕСЕЧНО СЧЕТОВОДСТВО");
        monthlyName.setText("месец");
        yearNumber.setText("година");
        grossProfit.setText("Брутна печалба");
        productsOriginalPrice.setText("инвестиция");
        rentAmount.setText("под наем");
        salaryAmount.setText("заплата");
        electricityCost.setText("електричество");
        heatingCost.setText("отопление");
        waterCost.setText("вода");
        advertisingCost.setText("реклама");
        religiousDayExpenses.setText("празнувам");
        otherExpenses.setText("Други разходи");
        netIncome.setText("Нетна печалба ( LEV )");
    }

    /* ==== MENU ROMANIAN LANGUAGE  === */

    public void romanianLanguage() {

        menuBtn.setText("Meniu");
        addProfitBtn.setVisible(true);
        editProfitBtn.setVisible(true);
        deleteProfitBtn.setVisible(true);

        addProfitBtnBul.setVisible(false);
        editProfitBtnBul.setVisible(false);
        deleteProfitBtnBul.setVisible(false);

        buttonSelectLanguage.setText("Изберете Език");

        legend.setText("Legendă Culori");
        openLegendBtn.setText("Deschide Legenda");

        openSearchBox.setText("Căsuţă De Căutare");
        searchBoxOpenBtn.setText("Deschide căsuţa de căutare");
        searchBoxCloseBtn.setText("Închide căsuţa de căutare");
        textTitle.setText("Daniel or Geri, you can search for a product in the box below.");
        searchBox.setPromptText("Introduceți aici: (Lună) sau (Anul)");

        labelTitle.setText("CONTABILITATE LUNARĂ");
        monthlyName.setText("Luna");
        yearNumber.setText("Anul");
        grossProfit.setText("Profit Brut");
        productsOriginalPrice.setText("Investiţie");
        rentAmount.setText("Chirie");
        salaryAmount.setText("Salariu");
        electricityCost.setText("Electricitate");
        heatingCost.setText("Încalzire");
        waterCost.setText("Apă");
        advertisingCost.setText("Publicitate");
        religiousDayExpenses.setText("Sărbători");
        otherExpenses.setText("Alte Cheltuieli");
        netIncome.setText("Profit Net ( LEV )");
    }
}
