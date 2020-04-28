package com.marinel;

import com.marinel.datamodel.Invoice;
import com.marinel.datamodel.InvoiceData;
import javafx.beans.binding.Bindings;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Rectangle2D;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Dialog;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.util.Optional;
import java.util.function.Predicate;

public class InvoiceController {

    private InvoiceData invoiceData;
    private FilteredList<Invoice> invoicesFilteredList;
    private boolean flag = true; // used for alert when user try to open a file

    @FXML
    private TableView<Invoice> linksOfFilesTableView;
    @FXML
    private BorderPane invoiceMainBorderPanel;

    // All this is for search box option
    Text textTitle = new Text();
    private final TextField searchBox = new TextField();
    private Stage dialogSearchBox = new Stage();
    Rectangle2D checkScreenBounds;
    VBox dialogVBox;
    Scene dialogScene;
    // End search box option

    public void initialize() {

        invoiceData = new InvoiceData();
        invoiceData.loadInvoice();

        // Initialize the TableView<Invoice> linksOfFilesTableView
        linksOfFilesTableView.getItems().setAll(invoiceData.getInvoiceData());
        //linksOfFilesTableView.setItems(invoiceData.getInvoiceData()); // Use in case of non FilteredList

        searchBox.setId("searchBox");

        invoicesFilteredList = new FilteredList<>(invoiceData.getInvoiceData());
        searchBox.setOnKeyReleased(e -> {
            searchBox.textProperty().addListener((observableValue, oldValue, newValue) ->
                    invoicesFilteredList.setPredicate((Predicate<? super Invoice>) invoices -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }

                        String lowerCaseFilter = newValue.toLowerCase();
                        return invoices.getInvoiceName().toLowerCase().contains(lowerCaseFilter);
                    }));

            SortedList<Invoice> invoicesSortedList = new SortedList<>(invoicesFilteredList);
            invoicesSortedList.comparatorProperty().bind(linksOfFilesTableView.comparatorProperty());

            linksOfFilesTableView.setItems(invoicesSortedList);
            linksOfFilesTableView.getSelectionModel().setSelectionMode(SelectionMode.SINGLE); // select only on note in the list
            linksOfFilesTableView.getSelectionModel().selectFirst(); // open the app whit the first item selected
        });

        /* ===  Edit or Delete the item from the table view when the user select the item and then click the right mouse button === */
        linksOfFilesTableView.setRowFactory(tableView -> {
            final TableRow<Invoice> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem removeMenuItem = new MenuItem("Delete This Invoice");
            final MenuItem editItemRo = new MenuItem("Edit This Invoice");
            final MenuItem previewTheInvoice = new MenuItem("Preview This Invoice");
            removeMenuItem.setOnAction(event -> {
                Invoice selectedInvoice = linksOfFilesTableView.getSelectionModel().getSelectedItem();

                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirm the delete action!");
                alert.setHeaderText(null);
                alert.setWidth(300);
                alert.setHeight(200);
                alert.setContentText("You are sure you want to delete the tax invoice with the name ( " + selectedInvoice.getInvoiceName()
                        + " ) and the link  ( " + selectedInvoice.getFileLink() + " ) ?");

                Optional<ButtonType> result = alert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    invoiceData.deleteInvoice(selectedInvoice);
                    invoiceData.saveInvoice();
                    linksOfFilesTableView.getItems().setAll(invoiceData.getInvoiceData());
                    linksOfFilesTableView.refresh();
                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                    infoAlert.setTitle("Confirm the deletion!");
                    infoAlert.setHeaderText(null);
                    infoAlert.setWidth(300);
                    infoAlert.setHeight(100);
                    infoAlert.setContentText("The delete operation was successfully executed!!!");
                    infoAlert.showAndWait();
                }
            });
            // delete selected item
            contextMenu.getItems().add(removeMenuItem);

            // edit the selected item RO
            editItemRo.setOnAction(event -> showEditInvoiceDialog());
            contextMenu.getItems().add(editItemRo);

            // preview the invoice
            previewTheInvoice.setOnAction(event -> previewFile());
            contextMenu.getItems().add(previewTheInvoice);

            // Set context menu on row, but use a binding to make it only show for non-empty rows:
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );
            return row;
        });
    }

    @FXML
    public void showAddInvoiceDialog() {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(invoiceMainBorderPanel.getScene().getWindow());
        dialog.setTitle("Adaugă invoice!");
        dialog.setContentText("Te rog să completezi informațiile facturi fiscale!");
        dialog.setX(550);
        dialog.setY(100);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("invoiceDialog.fxml"));
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
            InvoiceDialogController invoiceDialogController = fxmlLoader.getController();
            Invoice newInvoice = invoiceDialogController.getNewInvoice();
            invoiceData.addInvoice(newInvoice);
            invoiceData.saveInvoice();
            linksOfFilesTableView.getItems().setAll(invoiceData.getInvoiceData());
            linksOfFilesTableView.refresh();
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
    public void showEditInvoiceDialog() {

        Invoice selectedInvoice = linksOfFilesTableView.getSelectionModel().getSelectedItem();
        if (selectedInvoice == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertisment ... invoice neselectat!");
            alert.setHeaderText(null);
            alert.setContentText("Te rog să selectezi factura fiscală pe care doresti să o editezi!");
            alert.showAndWait();
            return;

        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(linksOfFilesTableView.getScene().getWindow());
        dialog.setTitle("Editează factura fiscală Daniel!");
        dialog.setHeaderText(null);
        dialog.setContentText("Te rog să editezi informațiile pentru factura fiscală selectată");
        dialog.setX(550);
        dialog.setY(100);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("invoiceDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Could not load the dialog pane");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        InvoiceDialogController invoiceDialogController = fxmlLoader.getController();
        invoiceDialogController.editInvoice(selectedInvoice);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            invoiceDialogController.updateInvoice(selectedInvoice);
            invoiceData.saveInvoice();
            linksOfFilesTableView.refresh();
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
    public void deleteInvoice() {

        Invoice selectedInvoice = linksOfFilesTableView.getSelectionModel().getSelectedItem();
        if (selectedInvoice == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertisment ... factură fiscală neselectată!");
            alert.setHeaderText(null);
            alert.setContentText("De rog să selectezi factura fiscală pe care dorești să o ștergi!");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmă acțiunea de ștergere");
        alert.setHeaderText(null);
        alert.setWidth(300);
        alert.setHeight(200);
        alert.setContentText("Ești sigur că vrei să ștergi din listă factura fiscală cu numele ( " + selectedInvoice.getInvoiceName()
                + " ) si linkul  ( " + selectedInvoice.getFileLink() + " ) ?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            invoiceData.deleteInvoice(selectedInvoice);
            invoiceData.saveInvoice();
            linksOfFilesTableView.getItems().setAll(invoiceData.getInvoiceData());
            linksOfFilesTableView.refresh();
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Confirmare stergere.");
            infoAlert.setHeaderText(null);
            infoAlert.setWidth(300);
            infoAlert.setHeight(100);
            infoAlert.setContentText("Operațiunea de ștergere a fost executată cu succes!!!");
            infoAlert.showAndWait();
        }
    }

    @FXML
    public void previewFile() {

        String bayBay = "La revedere!";
        String sorry = "Sorry!!!";

        try {

            File anyFile = new File(linksOfFilesTableView.getSelectionModel().getSelectedItem().getFileLink());

            if (anyFile.exists()) {

                if (Desktop.isDesktopSupported()) {
                    Desktop.getDesktop().open(anyFile);
                } else {
                    Alert alert = new Alert(Alert.AlertType.WARNING);
                    alert.setTitle("Confirmare că s-a produs o eroare.");
                    alert.setHeaderText(null);
                    alert.setWidth(300);
                    alert.setHeight(100);
                    alert.setContentText("Desktop is not supported!" + sorry);
                    alert.showAndWait();
                }

            } else {

                if (flag) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Confirmare că s-a produs o eroare.");
                    alert.setHeaderText(null);
                    alert.setWidth(300);
                    alert.setHeight(200);
                    alert.setContentText("Acest fişier:\n" + anyFile + "\nnu este disponibil, deoarece la-i şters sau la-i mutat" +
                            " într-un alt folder. " + sorry);
                    alert.showAndWait();

                    alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Confirmare că fişierul nu a fost deschis.");
                    alert.setHeaderText(null);
                    alert.setWidth(300);
                    alert.setHeight(200);
                    alert.setContentText("Acest fişier:\n" + anyFile + "\nnu a fost deschis. " + sorry);
                    alert.showAndWait();
                    flag = false;
                }
            }

            if(flag) {
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Confirmare că fişierul a fost deschis.");
                alert.setHeaderText(null);
                alert.setWidth(300);
                alert.setHeight(200);
                alert.setContentText("Acest fişier:\n" + anyFile + "\na fost deschis cu succes. " + bayBay);
                alert.showAndWait();

            }

        } catch (Exception ex) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertisment ... factură fiscală neselectată!");
            alert.setHeaderText(null);
            alert.setWidth(300);
            alert.setHeight(150);
            alert.setContentText("Te rog să selectezi factura fiscală pe care doreşti să o deschizi!");
            alert.showAndWait();
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

        textTitle.setText("You can search for a invoice in the box below.");
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
        searchBox.setPromptText("Type here: (Invoice Name)");
        dialogSearchBox.show();
    }

    /* ==== CLOSE SEARCH BOX  === */
    @FXML
    public void closeSearchBox() {
        searchBox.clear();
        dialogSearchBox.close();
    }
}
