package com.marinel;

import com.marinel.datamodel.YearProfit;
import com.marinel.datamodel.YearProfitData;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.beans.binding.Bindings;
import javafx.beans.property.ReadOnlyObjectWrapper;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.scene.control.*;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.util.Duration;

import java.io.IOException;
import java.util.Optional;
import java.util.function.Predicate;

public class YearProfitController {

    private YearProfitData yearProfitData;
    private FilteredList<YearProfit> yearProfitFilteredList;

    /* === BAR CHART === */
    private boolean showTheBarChart = false; // use to activate the barChart
    ObservableList<XYChart.Series<String, Number>> barChartData;
    private final Timeline timeline = new Timeline();
    String yearLabel = ""; //ANI SUB FORMĂ DE COLOANE

    // All this is for search box option
    private final Text textTitle = new Text();
    private final TextField searchBox = new TextField();
    private Stage dialogSearchBox = new Stage();
    Rectangle2D checkScreenBounds;
    VBox dialogVBox;
    Scene dialogScene;

    // All this is for number of products in table option
    Text textTitleNoProd = new Text();
    private final Label labelNoProd = new Label();
    Stage dialogNoProd = new Stage();
    Rectangle2D checkScreenBoundsNoProd;
    VBox dialogVBoxNoProd;
    Scene dialogSceneNoProd;


    @FXML
    private TableView<YearProfit> yearProfitTableView;
    @FXML
    private BorderPane yearProfitMainPanel;

    @FXML
    private Menu menuBtn;
    @FXML
    private Menu buttonSelectLanguage;
    @FXML
    private Label labelTitle;
    @FXML
    private MenuItem addYearProfitBtn;
    @FXML
    private MenuItem editYearProfitBtn;
    @FXML
    private MenuItem deleteYearProfitBtn;

    @FXML
    private TableColumn<YearProfit, Number> numberOfRow;
    @FXML
    private TableColumn<YearProfit, TextField> yearNumber;
    @FXML
    private TableColumn<YearProfit, TextField> grossProfitBeforeTax;
    @FXML
    private TableColumn<YearProfit, TextField> amountOfTaxPaid;
    @FXML
    private TableColumn<YearProfit, TextField> netProfitAfterTax;

    // For bulgarian version
    @FXML
    private MenuItem addYearProfitBtnBul;
    @FXML
    private MenuItem editYearProfitBtnBul;
    @FXML
    private MenuItem deleteYearProfitBtnBul;

    @FXML
    private Menu openSearchBox;
    @FXML
    private MenuItem searchBoxOpenBtn;
    @FXML
    private MenuItem searchBoxCloseBtn;


    // Bar chart
    @FXML
    private BarChart<String, Number> barChart;
    @FXML
    private NumberAxis yAxis;


    public void initialize() {

        yearProfitData = new YearProfitData();
        yearProfitData.loadYearProfits();
        //initialize the TableView<Profit> monthlyProfitTableView
        yearProfitTableView.getItems().setAll(yearProfitData.getYearProfits());
        //yearProfitTableView.setItems(yearProfitData.getYearProfits()); // Use in case of non FilteredList

        labelTitle.setMaxWidth(Double.POSITIVE_INFINITY);
        labelTitle.setAlignment(Pos.CENTER);
        barChart.setBarGap(20);
        yAxis.setLabel("The value of the profit");
        searchBox.setId("searchBox");
        numberOfRow.setMinWidth(22);
        numberOfRow.setMaxWidth(22);
        numberOfRow.setSortable(false);
        numberOfRow.setCellValueFactory(column-> new ReadOnlyObjectWrapper(yearProfitTableView.getItems().indexOf(column.getValue()) + 1));

        // Create a filteredList
        yearProfitFilteredList = new FilteredList<>(yearProfitData.getYearProfits());
        searchBox.setOnKeyReleased(e -> {
            searchBox.textProperty().addListener((observableValue, oldValue, newValue) ->
                    yearProfitFilteredList.setPredicate((Predicate<? super YearProfit>) profits -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }

                        String lowerCaseFilter = newValue.toLowerCase();

                        return profits.getYearNumber().toLowerCase().contains(lowerCaseFilter);
                    }));

            SortedList<YearProfit> profitSortedList = new SortedList<>(yearProfitFilteredList);
            profitSortedList.comparatorProperty().bind(yearProfitTableView.comparatorProperty());

            yearProfitTableView.setItems(profitSortedList);
            yearProfitTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); // select only on note in the list
            yearProfitTableView.getSelectionModel().selectFirst(); // open the app whit the first item selected
            numberOfYearsInTable();
        });

        /* ===  Edit or Delete the item from the table view when the user select the item and then click the right mouse button === */
        yearProfitTableView.setRowFactory(tableView -> {
            final TableRow<YearProfit> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem removeMenuItem = new MenuItem("Delete this year");
            final MenuItem editItemRo = new MenuItem("Editează anul acesta");
            final MenuItem editItemBg = new MenuItem("Редактиране тази година");
            removeMenuItem.setOnAction(event -> {
                YearProfit selectedProfit = yearProfitTableView.getSelectionModel().getSelectedItem();

                if (yearProfitTableView.getSelectionModel().getSelectedItem() != null) {
                    int selectedIndices = yearProfitTableView.getSelectionModel().getSelectedIndices().get(0);

                    if ((selectedIndices == 0) || (selectedIndices == 1) || (selectedIndices == 2) || (selectedIndices == 3)
                            || (selectedIndices == 4) || (selectedIndices == 5) || (selectedIndices == 6) || (selectedIndices == 7)
                            || (selectedIndices == 8) || (selectedIndices == 9) || (selectedIndices == 10)) {
                        Alert wrongYearAlert = new Alert(Alert.AlertType.WARNING);
                        wrongYearAlert.setTitle("Warning ... incorrect profit selected!");
                        wrongYearAlert.setHeaderText(null);
                        wrongYearAlert.setWidth(300);
                        wrongYearAlert.setHeight(180);
                        wrongYearAlert.setContentText("The profit for the year ( " + selectedProfit.getYearNumber()
                                + " ) with net profit after tax = with the sum of:  ( " + selectedProfit.getNetProfitAfterTax() + " LEV ) it cannot be deleted, just edited.");
                        wrongYearAlert.showAndWait();

                    } else {

                        Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
                        deleteAlert.setTitle("Confirm the delete action!");
                        deleteAlert.setHeaderText(null);
                        deleteAlert.setWidth(300);
                        deleteAlert.setHeight(180);
                        deleteAlert.setContentText("You are sure you want to delete the profit for the year ( " + selectedProfit.getYearNumber()
                                + " ) with net profit after tax = with the sum of:  ( " + selectedProfit.getNetProfitAfterTax() + " LEV ) ?");

                        Optional<ButtonType> result = deleteAlert.showAndWait();
                        if (result.isPresent() && result.get() == ButtonType.OK) {
                            yearProfitData.deleteYearProfit(selectedProfit);
                            yearProfitData.saveYearProfits();
                            yearProfitTableView.setItems(yearProfitData.getYearProfits());
                            yearProfitTableView.refresh();
                            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                            infoAlert.setTitle("Confirm the deletion!");
                            infoAlert.setHeaderText(null);
                            infoAlert.setWidth(300);
                            infoAlert.setHeight(100);
                            infoAlert.setContentText("The delete operation was successfully executed!!!");
                            infoAlert.showAndWait();
                        }
                    }
                }
            });
            // delete the selected item
            contextMenu.getItems().add(removeMenuItem);

            // edit the selected item RO
            editItemRo.setOnAction(event -> showEditYearProfitDialog());
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
    public void showAddYearProfitDialog() {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(yearProfitMainPanel.getScene().getWindow());
        dialog.setTitle("Adaugă profit anual!");
        dialog.setContentText("Te rog să completezi informațiile profitului in functie de an!");
        dialog.setX(550);
        dialog.setY(100);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("yearProfitRomanianDialog.fxml"));
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
            YearProfitRomanianDialogController yearProfitRomanianDialogController = fxmlLoader.getController();
            YearProfit newYearProfit = yearProfitRomanianDialogController.getNewYearProfit();
            yearProfitData.addYearProfit(newYearProfit);
            yearProfitData.saveYearProfits();
            yearProfitTableView.setItems(yearProfitData.getYearProfits());
            yearProfitTableView.refresh();
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
    public void showEditYearProfitDialog() {

        YearProfit selectedProfit = yearProfitTableView.getSelectionModel().getSelectedItem();
        if (selectedProfit == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertisment ... profit neselectat!");
            alert.setHeaderText(null);
            alert.setContentText("Te rog să selectezi profitul pe care doresti să-l editezi!");
            alert.showAndWait();
            return;

        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(yearProfitTableView.getScene().getWindow());
        dialog.setTitle("Editați profitul!");
        dialog.setHeaderText(null);
        dialog.setContentText("Te rog să editezi informațiile profitului pe anul selectat.");
        dialog.setX(550);
        dialog.setY(100);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("yearProfitRomanianDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        YearProfitRomanianDialogController yearProfitRomanianDialogController = fxmlLoader.getController();
        yearProfitRomanianDialogController.editYearProfit(selectedProfit);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            yearProfitRomanianDialogController.updateYearProfit(selectedProfit);
            yearProfitData.saveYearProfits();
            yearProfitTableView.refresh();
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
    public void deleteYearProfit() {

        YearProfit selectedProfit = yearProfitTableView.getSelectionModel().getSelectedItem();
        if (selectedProfit == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertisment ... profit neselectat!");
            alert.setHeaderText(null);
            alert.setContentText("Te rog să selectezi profitul pe care doresti să-l ștergi!");
            alert.showAndWait();
            return;
        }

        if (yearProfitTableView.getSelectionModel().getSelectedItem() != null) {
            int selectedIndices = yearProfitTableView.getSelectionModel().getSelectedIndices().get(0);

            if ((selectedIndices == 0) || (selectedIndices == 1) || (selectedIndices == 2) || (selectedIndices == 3)
                    || (selectedIndices == 4) || (selectedIndices == 5) || (selectedIndices == 6) || (selectedIndices == 7)
                    || (selectedIndices == 8) || (selectedIndices == 9) || (selectedIndices == 10)) {
                Alert wrongYearAlert = new Alert(Alert.AlertType.WARNING);
                wrongYearAlert.setTitle("Avertisment ... incorect profit selectat!");
                wrongYearAlert.setHeaderText(null);
                wrongYearAlert.setWidth(300);
                wrongYearAlert.setHeight(180);
                wrongYearAlert.setContentText("Profitul din anul ( " + selectedProfit.getYearNumber()
                        + " ) cu profitul net după inpozit = cu suma de:  ( " + selectedProfit.getNetProfitAfterTax() + " LEV ) nu poate fi șters, doar editat.");
                wrongYearAlert.showAndWait();
                //System.out.println("THE PROFIT NUMBER = " + selectionModel + " CAN NOT BE DELETED");

            } else {

                Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
                deleteAlert.setTitle("Confirmă acțiunea de ștergere!");
                deleteAlert.setHeaderText(null);
                deleteAlert.setWidth(300);
                deleteAlert.setHeight(180);
                deleteAlert.setContentText("Ești sigur că vrei să ștergi din listă profitul din anul ( " + selectedProfit.getYearNumber()
                        + " ) cu profitul net după inpozit = cu suma de:  ( " + selectedProfit.getNetProfitAfterTax() + " LEV ) ?");

                Optional<ButtonType> result = deleteAlert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    yearProfitData.deleteYearProfit(selectedProfit);
                    yearProfitData.saveYearProfits();
                    yearProfitTableView.setItems(yearProfitData.getYearProfits());
                    yearProfitTableView.refresh();
                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                    infoAlert.setTitle("Confirmare stergere.");
                    infoAlert.setHeaderText(null);
                    infoAlert.setWidth(300);
                    infoAlert.setHeight(100);
                    infoAlert.setContentText("Operațiunea de ștergere a fost executată cu succes!!!");
                    infoAlert.showAndWait();
                }
            }
        }
    }

    @FXML
    public void showAddProfitBulgarianDialog() {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(yearProfitMainPanel.getScene().getWindow());
        dialog.setTitle("Добавя годишна печалба!");
        dialog.setContentText("моля попълни информацията за печалбата, в зависимост от годината!");
        dialog.setX(550);
        dialog.setY(100);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("yearProfitBulgarianDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            YearProfitBulgarianDialogController yearProfitBulgarianDialogController = fxmlLoader.getController();
            YearProfit newYearProfit = yearProfitBulgarianDialogController.getNewYearProfit();
            yearProfitData.addYearProfit(newYearProfit);
            yearProfitData.saveYearProfits();
            yearProfitTableView.setItems(yearProfitData.getYearProfits());
            yearProfitTableView.refresh();
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

        YearProfit selectedProfit = yearProfitTableView.getSelectionModel().getSelectedItem();
        if (selectedProfit == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение ... неизбрана печалба!");
            alert.setHeaderText(null);
            alert.setContentText("моля, изберете печалбата, която искате да редактирате!");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(yearProfitTableView.getScene().getWindow());
        dialog.setTitle("Редактирайте печалбата си!");
        dialog.setHeaderText(null);
        dialog.setContentText("моля, редактирай информацията за печалбата за избраната година");
        dialog.setX(550);
        dialog.setY(100);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("yearProfitBulgarianDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        YearProfitBulgarianDialogController yearProfitBulgarianDialogController = fxmlLoader.getController();
        yearProfitBulgarianDialogController.editYearProfit(selectedProfit);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            yearProfitBulgarianDialogController.updateYearProfit(selectedProfit);
            yearProfitData.saveYearProfits();
            yearProfitTableView.refresh();
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

        YearProfit selectedProfit = yearProfitTableView.getSelectionModel().getSelectedItem();
        if (selectedProfit == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение ... неизбрана печалба!");
            alert.setHeaderText(null);
            alert.setContentText("моля изберете печалбата, която искате да изтриете!");
            alert.showAndWait();
            return;
        }

        if (yearProfitTableView.getSelectionModel().getSelectedItem() != null) {
            int selectedIndices = yearProfitTableView.getSelectionModel().getSelectedIndices().get(0);

            if ((selectedIndices == 0) || (selectedIndices == 1) || (selectedIndices == 2) || (selectedIndices == 3)
                    || (selectedIndices == 4) || (selectedIndices == 5) || (selectedIndices == 6) || (selectedIndices == 7)
                    || (selectedIndices == 8) || (selectedIndices == 9) || (selectedIndices == 10)) {
                Alert wrongYearAlertBul = new Alert(Alert.AlertType.WARNING);
                wrongYearAlertBul.setTitle("Предупреждение ... избрана е неправилна печалба!");
                wrongYearAlertBul.setHeaderText(null);
                wrongYearAlertBul.setWidth(300);
                wrongYearAlertBul.setHeight(200);
                wrongYearAlertBul.setContentText("Гпечалбата за годината ( " + selectedProfit.getYearNumber()
                        + " ) с нетна печалба след данък = със сумата от:  ( " + selectedProfit.getNetProfitAfterTax() + " LEV ) то не може да бъде изтрито, току що редактирано.");
                wrongYearAlertBul.showAndWait();

            } else {

                Alert deleteAlertBul = new Alert(Alert.AlertType.CONFIRMATION);
                deleteAlertBul.setTitle("Потвърдете действието за изтриване");
                deleteAlertBul.setHeaderText(null);
                deleteAlertBul.setWidth(300);
                deleteAlertBul.setHeight(180);
                deleteAlertBul.setContentText("Cигурен си, че искаш да изтриеш печалбата за годината от списъка ( " + selectedProfit.getYearNumber()
                        + " ) с нетна печалба след данък = със сумата от:  ( " + selectedProfit.getNetProfitAfterTax() + " LEV ) ?");

                Optional<ButtonType> result = deleteAlertBul.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    yearProfitData.deleteYearProfit(selectedProfit);
                    yearProfitData.saveYearProfits();
                    yearProfitTableView.setItems(yearProfitData.getYearProfits());
                    yearProfitTableView.refresh();
                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                    infoAlert.setTitle("Потвърждение за изтриване.");
                    infoAlert.setHeaderText(null);
                    infoAlert.setWidth(300);
                    infoAlert.setHeight(100);
                    infoAlert.setContentText("Oперацията за изтриване беше успешна!!!");
                    infoAlert.showAndWait();
                }
            }
        }
    }

    /* ==== OPEN SEARCH BOX  === */
    @FXML
    public void openSearchBox() {

        barChart.setVisible(false);
        dialogSearchBox = new Stage();
        dialogSearchBox.setTitle("DETAILED SEARCH");
        dialogSearchBox.initStyle(StageStyle.UNDECORATED);
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
        searchBox.setPromptText("Type here: (Year)");
        dialogSearchBox.show();

        /* === NUMBER OF ENTRY IN THE TABLE VIEW === */

        dialogNoProd = new Stage();
        dialogNoProd.setTitle("NUMBER OF PRODUCTS");
        dialogNoProd.initStyle(StageStyle.UNDECORATED);
        dialogNoProd.initModality(Modality.NONE);
        dialogNoProd.setResizable(false);
        checkScreenBoundsNoProd = Screen.getPrimary().getVisualBounds();
        dialogNoProd.setX((checkScreenBoundsNoProd.getWidth() / 3));
        dialogNoProd.setY((checkScreenBoundsNoProd.getHeight() / 1.29)); // 1.35

        int numbersOfRow = yearProfitTableView.getItems().size();
        textTitleNoProd.setText("TOTAL NUMBER OF YEARS REGISTERED IN THE TABLE = " + numbersOfRow);
        textTitleNoProd.setId("searchBoxTitle");
        String color_2 = "#ffffff";
        textTitleNoProd.setFill(Paint.valueOf(color_2));

        dialogVBoxNoProd = new VBox(2);
        dialogVBoxNoProd.setId("dialogSearch");
        dialogVBoxNoProd.setAlignment(Pos.CENTER);
        dialogVBoxNoProd.setPadding(new Insets(17, 5, 1, 5));
        dialogVBoxNoProd.getChildren().addAll(textTitleNoProd,labelNoProd);
        dialogSceneNoProd = new Scene(dialogVBoxNoProd, 500, 40); // 61
        dialogSceneNoProd.getStylesheets().add(this.getClass().getResource("styles.css").toExternalForm());
        dialogNoProd.setScene(dialogSceneNoProd);

        labelNoProd.setMaxHeight(25);
        dialogNoProd.show();
    }

    /* ==== CLOSE SEARCH BOX  === */
    @FXML
    public void closeSearchBox() {

        searchBox.clear();
        yearProfitTableView.setItems(yearProfitData.getYearProfits());
        yearProfitTableView.refresh();
        barChart.setVisible(true);
        dialogNoProd.close();
        dialogSearchBox.close();
    }

    /* ==== METHOD TO DYNAMICALLY POPULATE THE BAR CHART WHEN DATA CHANGE IN THE TABLE VIEW  === */
    @FXML
    public void displayBarChart() {

        yearLabel = "YEARS REPRESENTED ON COLUMNS";
        barChartData = FXCollections.observableArrayList();

        final BarChart.Series<String, Number> series1 = new BarChart.Series<>();
        final BarChart.Series<String, Number> series2 = new BarChart.Series<>();
        final BarChart.Series<String, Number> series3 = new BarChart.Series<>();
        final BarChart.Series<String, Number> series4 = new BarChart.Series<>();
        final BarChart.Series<String, Number> series5 = new BarChart.Series<>();
        final BarChart.Series<String, Number> series6 = new BarChart.Series<>();
        final BarChart.Series<String, Number> series7 = new BarChart.Series<>();
        final BarChart.Series<String, Number> series8 = new BarChart.Series<>();
        final BarChart.Series<String, Number> series9 = new BarChart.Series<>();
        final BarChart.Series<String, Number> series10 = new BarChart.Series<>();
        final BarChart.Series<String, Number> series11 = new BarChart.Series<>();

        String a = yearProfitTableView.getColumns().get(4).getCellObservableValue(0).getValue().toString();
        Float aConverted = Float.valueOf(a);
        String b = yearProfitTableView.getColumns().get(4).getCellObservableValue(1).getValue().toString();
        Float bConverted = Float.valueOf(b);
        String c = yearProfitTableView.getColumns().get(4).getCellObservableValue(2).getValue().toString();
        Float cConverted = Float.valueOf(c );
        String d = yearProfitTableView.getColumns().get(4).getCellObservableValue(3).getValue().toString();
        Float dConverted = Float.valueOf(d);
        String e = yearProfitTableView.getColumns().get(4).getCellObservableValue(4).getValue().toString();
        Float eConverted = Float.valueOf(e);
        String f = yearProfitTableView.getColumns().get(4).getCellObservableValue(5).getValue().toString();
        Float fConverted = Float.valueOf(f);
        String g = yearProfitTableView.getColumns().get(4).getCellObservableValue(6).getValue().toString();
        Float gConverted = Float.valueOf(g);
        String h = yearProfitTableView.getColumns().get(4).getCellObservableValue(7).getValue().toString();
        Float hConverted = Float.valueOf(h);
        String i = yearProfitTableView.getColumns().get(4).getCellObservableValue(8).getValue().toString();
        Float iConverted = Float.valueOf(i);
        String j = yearProfitTableView.getColumns().get(4).getCellObservableValue(9).getValue().toString();
        Float jConverted = Float.valueOf(j);
        String k = yearProfitTableView.getColumns().get(4).getCellObservableValue(10).getValue().toString();
        Float kConverted = Float.valueOf(k);

        series1.setName("2020");
        series1.getData().add(new XYChart.Data<>(yearLabel, aConverted));
        barChartData.add(series1);
        series2.setName("2021");
        series2.getData().add(new XYChart.Data<>(yearLabel, bConverted));
        barChartData.add(series2);
        series3.setName("2022");
        series3.getData().add(new XYChart.Data<>(yearLabel, cConverted));
        barChartData.add(series3);
        series4.setName("2023");
        series4.getData().add(new XYChart.Data<>(yearLabel, dConverted));
        barChartData.add(series4);
        series5.setName("2024");
        series5.getData().add(new XYChart.Data<>(yearLabel, eConverted));
        barChartData.add(series5);
        series6.setName("2025");
        series6.getData().add(new XYChart.Data<>(yearLabel, fConverted));
        barChartData.add(series6);
        series7.setName("2026");
        series7.getData().add(new XYChart.Data<>(yearLabel, gConverted));
        barChartData.add(series7);
        series8.setName("2027");
        series8.getData().add(new XYChart.Data<>(yearLabel, hConverted));
        barChartData.add(series8);
        series9.setName("2028");
        series9.getData().add(new XYChart.Data<>(yearLabel, iConverted));
        barChartData.add(series9);
        series10.setName("2029");
        series10.getData().add(new XYChart.Data<>(yearLabel, jConverted));
        barChartData.add(series10);
        series11.setName("2030");
        series11.getData().add(new XYChart.Data<>(yearLabel, kConverted));
        barChartData.add(series11);

        barChart.setTitle("PROFIT OF THE YEARS REPRESENTED IN A GRAPHICAL TABLE WITH COLUMNS");
        barChart.setData(barChartData);
        startAutomaticallyBarChart();

    }

    /* === STAR ANIMATE THE BAR CHART AUTOMATICALLY=== */
    public void startAutomaticallyBarChart() {

        timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(1000),
                        actionEvent -> {
                            for (XYChart.Series<String, Number> series : barChart.getData()) {
                                for (XYChart.Data<String, Number> barChart : series.getData()) {
                                    barChart.setYValue(Math.random() * 60000);
                                }
                            }
                        }
                ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();
        stopAnimatedBarChart();

    }

    /* === STAR ANIMATE THE BAR CHART ON MOUSE EXIT FROM THE BAR CHART AREA === */
    public void startAnimatedBarChartOnExit() {

        timeline.getKeyFrames().add(
                new KeyFrame(Duration.millis(1000),
                        actionEvent -> {
                            for (XYChart.Series<String, Number> series : barChart.getData()) {
                                for (XYChart.Data<String, Number> barChart : series.getData()) {
                                    barChart.setYValue(Math.random() * 60000);
                                }
                            }
                        }
                ));
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.setAutoReverse(true);
        timeline.play();

    }

    /* === STOP ANIMATE THE BAR CHART ON MOUSE ENTERED IN THE BAR CHART AREA === */
    public void stopAnimatedBarChart() {
        timeline.stop();
    }

    /* ==== NUMBERS OF YEARS IN THE TABLE  === */
    public void numberOfYearsInTable() {
        int numbersOfRow = yearProfitTableView.getItems().size();
        textTitleNoProd.setText("TOTAL NUMBER OF YEARS REGISTERED IN THE TABLE = " + numbersOfRow);
    }

    /* === INFO ALERT HOW TO PAINT THE DATA IN THE BAR CHART === */
    public void  informativeAlert() {

        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
        infoAlert.setTitle("Confirm the action!");
        infoAlert.setHeaderText(null);
        infoAlert.setWidth(300);
        infoAlert.setHeight(250);
        infoAlert.setContentText("Moves the mouse to the gray area below if you want to see" +
                " the profit of the years represented in graphical table. To stop the animation of the" +
                " graphic keep the mouse in the graphical table area or remove the mouse from graphical table to restart the animation.");
        infoAlert.showAndWait();
    }


    /* === MAKE BAR CHART VISIBLE === */
    public void activateBarChart() {

        if (!showTheBarChart) {
            showTheBarChart = true;
            barChart.setVisible(true);
            informativeAlert();
        }

    }

    /* === Delete the item from the table view when the user select the item and then press the delete key ==== */
    @FXML
    public void handleDeleteKeyPressed(KeyEvent keyEvent) {

        YearProfit selectedProfit = yearProfitTableView.getSelectionModel().getSelectedItem();

        if (selectedProfit != null) {
            if (keyEvent.getCode().equals(KeyCode.DELETE)) {
                int selectedIndices = yearProfitTableView.getSelectionModel().getSelectedIndices().get(0);

                if ((selectedIndices == 0) || (selectedIndices == 1) || (selectedIndices == 2) || (selectedIndices == 3)
                        || (selectedIndices == 4) || (selectedIndices == 5) || (selectedIndices == 6) || (selectedIndices == 7)
                        || (selectedIndices == 8) || (selectedIndices == 9) || (selectedIndices == 10)) {
                    Alert wrongYearAlert = new Alert(Alert.AlertType.WARNING);
                    wrongYearAlert.setTitle("Avertisment ... incorect profit selectat!");
                    wrongYearAlert.setHeaderText(null);
                    wrongYearAlert.setWidth(300);
                    wrongYearAlert.setHeight(180);
                    wrongYearAlert.setContentText("Profitul din anul ( " + selectedProfit.getYearNumber()
                            + " ) cu profitul net după inpozit = cu suma de:  ( " + selectedProfit.getNetProfitAfterTax() + " LEV ) nu poate fi șters, doar editat.");
                    wrongYearAlert.showAndWait();

                } else {

                    Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
                    deleteAlert.setTitle("Confirmă acțiunea de ștergere Daniel!");
                    deleteAlert.setHeaderText(null);
                    deleteAlert.setWidth(300);
                    deleteAlert.setHeight(180);
                    deleteAlert.setContentText("Ești sigur că vrei să ștergi din listă profitul din anul ( " + selectedProfit.getYearNumber()
                            + " ) cu profitul net după inpozit = cu suma de:  ( " + selectedProfit.getNetProfitAfterTax() + " LEV ) ?");

                    Optional<ButtonType> result = deleteAlert.showAndWait();
                    if (result.isPresent() && result.get() == ButtonType.OK) {
                        yearProfitData.deleteYearProfit(selectedProfit);
                        yearProfitData.saveYearProfits();
                        yearProfitTableView.setItems(yearProfitData.getYearProfits());
                        yearProfitTableView.refresh();
                        Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                        infoAlert.setTitle("Confirmare stergere.");
                        infoAlert.setHeaderText(null);
                        infoAlert.setWidth(300);
                        infoAlert.setHeight(100);
                        infoAlert.setContentText("Operațiunea de ștergere a fost executată cu succes!!!");
                        infoAlert.showAndWait();
                    }
                }
            }
        }
    }

    /* ==== MENU BULGARIAN VERSION  === */
    public void bulgarianLanguage() {

        menuBtn.setText("меню");
        addYearProfitBtn.setVisible(false);
        editYearProfitBtn.setVisible(false);
        deleteYearProfitBtn.setVisible(false);

        addYearProfitBtnBul.setVisible(true);
        editYearProfitBtnBul.setVisible(true);
        deleteYearProfitBtnBul.setVisible(true);

        buttonSelectLanguage.setText("Selectează Limba");

        openSearchBox.setText("Поле за търсене");
        searchBoxOpenBtn.setText("Отворете полето за търсене");
        searchBoxCloseBtn.setText("Затворете полето за търсене");
        textTitle.setText("Даниел или Гери, можете да потърсите продукт в полето по-долу.");
        //searchBox.setPromptText("Въведете тук: (година)");

        labelTitle.setText("ГОДИШЕН СЧЕТОВОДСТВО");
        yearNumber.setText("Платен на дата");
        grossProfitBeforeTax.setText("Брутна печалба преди данъчно облагане (LEV)");
        amountOfTaxPaid.setText("Данък върху печалбата (LEV)");
        netProfitAfterTax.setText("Нетна печалба след данък (LEV)");
    }

    /* ==== MENU ROMANIAN VERSION  === */
    public void romanianLanguage() {

        menuBtn.setText("Meniu");
        addYearProfitBtn.setVisible(true);
        editYearProfitBtn.setVisible(true);
        deleteYearProfitBtn.setVisible(true);

        addYearProfitBtnBul.setVisible(false);
        editYearProfitBtnBul.setVisible(false);
        deleteYearProfitBtnBul.setVisible(false);

        buttonSelectLanguage.setText("Изберете Език");

        openSearchBox.setText("Căsuţă De Căutare");
        searchBoxOpenBtn.setText("Deschide căsuţa de căutare");
        searchBoxCloseBtn.setText("Închide căsuţa de căutare");
        textTitle.setText("You can search for a product in the box below.");
        //searchBox.setPromptText("Introduceți aici: (Anul)");

        labelTitle.setText("CONTABILITATE ANUALĂ");
        yearNumber.setText("Plătit La Data");
        grossProfitBeforeTax.setText("Profit Brut Înainte De Inpozit ( LEV )");
        amountOfTaxPaid.setText("Impozit Pe Profit ( LEV )");
        netProfitAfterTax.setText("Profit Net După Inpozit ( LEV )");

    }
}
