package com.marinel;

import com.marinel.datamodel.Product;
import com.marinel.datamodel.ProductData;
import javafx.application.Platform;
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
import javafx.scene.control.Button;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.time.LocalDate;
import java.util.Optional;
import java.util.function.Predicate;

public class Controller {

    private ProductData productData;
    private FilteredList<Product> productFilteredList;
    private final LocalDate localDate = LocalDate.now(); // for copyright

    // Search box option
    Text textTitle = new Text();
    private final TextField searchBox = new TextField();
    private Stage dialogSearchBox = new Stage();
    Rectangle2D checkScreenBounds;
    VBox dialogVBox;
    Scene dialogScene;
    // End search box option

    // Number of products in tableView
    Text textTitleNoProd = new Text();
    private final Label labelNoProd = new Label();
    Stage dialogNoProd = new Stage();
    Rectangle2D checkScreenBoundsNoProd;
    VBox dialogVBoxNoProd;
    Scene dialogSceneNoProd;
    // End number of products in tableView

    @FXML
    private BorderPane mainPanel;
    @FXML
    private TableView<Product> productsTable;

    @FXML
    private Menu menuBtn;
    @FXML
    private MenuItem addProductBtn;
    @FXML
    private MenuItem editProductBtn;
    @FXML
    private MenuItem cloneProductBtn;
    @FXML
    private MenuItem deleteProductBtn;
    @FXML
    private MenuItem addProductBtnBul;
    @FXML
    private MenuItem editProductBtnBul;
    @FXML
    private MenuItem cloneProductBtnBul;
    @FXML
    private MenuItem deleteProductBtnBul;
    @FXML
    private MenuItem monthlyProfit;
    @FXML
    private MenuItem yearProfit;
    @FXML
    private Menu help;
    @FXML
    private MenuItem instructiuniBtn;
    @FXML
    private MenuItem ciorna;
    @FXML
    private MenuItem exchange;
    @FXML
    private MenuItem googleHomePage;
    @FXML
    private MenuItem uploadFileBtn;
    @FXML
    private Menu uploadFile;
    @FXML
    private Menu buttonSelectLanguage;
    @FXML
    private Menu visitTheWebSite;
    @FXML
    private MenuItem visitTheWebSiteBtn;
    @FXML
    private MenuItem calculatorBtn;
    @FXML
    private Menu openSearchBox;
    @FXML
    private MenuItem searchBoxOpenBtn;
    @FXML
    private MenuItem searchBoxCloseBtn;
    @FXML
    private Menu closeProgram;
    @FXML
    private MenuItem closeApp;
    @FXML
    private Label labelTitle;
    @FXML
    private Button numberOfProducts;
    @FXML
    private Label copyRightLabel;

    @FXML
    private TableColumn<Product,?> numberOfRow;
    @FXML
    private TableColumn<Product,TextField> productName;
    @FXML
    private  TableColumn<Product,TextField> brandName;
    @FXML
    private TableColumn<Product,TextField> modelNumber;
    @FXML
    private TableColumn<Product,TextField> colorName;
    @FXML
    private TableColumn<Product,TextField> sizeValue;
    @FXML
    private TableColumn<Product,TextField> categoryName;
    @FXML
    private TableColumn<Product,TextField> webAddress;
    @FXML
    private TableColumn<Product,TextField> dataColumn;
    @FXML
    private TableColumn<Product,TextField> quantityColumn;
    @FXML
    private TableColumn<Product,TextField> priceColumn;
    @FXML
    private TableColumn<Product,TextField> lotCost;
    @FXML
    private TableColumn<Product,TextField> sellePrice;
    @FXML
    private TableColumn<Product,TextField> itemProfit;
    @FXML
    private TableColumn<Product,TextField> lotProfit;
    @FXML
    private TableColumn<Product,TextField> remainingStock;
    @FXML
    private TableColumn<Product,TextField> remainingStockProfit;


    public void initialize() {

        // Initialize the object productData from ProductData.java
        productData = new ProductData();
        productData.loadProducts();

        // Initialize the TableView<Product> productsTable
        productsTable.getItems().setAll(productData.getProducts());
        //productsTable.setItems(productData.getProducts()); // Use in case of non FilteredList

        labelTitle.setTextFill(Color.BLUE);
        labelTitle.setMaxWidth(Double.POSITIVE_INFINITY);
        labelTitle.setAlignment(Pos.CENTER);

        numberOfRow.setMinWidth(22);
        numberOfRow.setMaxWidth(22);
        numberOfRow.setSortable(false);
        numberOfRow.setCellValueFactory(column-> new ReadOnlyObjectWrapper(productsTable.getItems().indexOf(column.getValue()) + 1));

        sizeValue.setMinWidth(60);
        sizeValue.setMaxWidth(60);
        quantityColumn.setMinWidth(71);
        quantityColumn.setMaxWidth(71);

        searchBox.setId("searchBox");
        numberOfProducts.setMaxWidth(Double.POSITIVE_INFINITY);
        copyRightLabel.setText("© 2019 - " + localDate.getYear() + " Drepturi de autor Marinel");


        // create a FilteredList , a SortedList and then set the SortedList to the TableView
        productFilteredList = new FilteredList<>(productData.getProducts());
        searchBox.setOnKeyReleased(e -> {
            searchBox.textProperty().addListener((observableValue, oldValue, newValue) ->
                    productFilteredList.setPredicate((Predicate<? super Product>) products -> {
                        if (newValue == null || newValue.isEmpty()) {
                            return true;
                        }

                        String lowerCaseFilter = newValue.toLowerCase();

                        if (products.getProductName().toLowerCase().contains(lowerCaseFilter)) {
                            return true;
                        } else if (products.getBrandName().toLowerCase().contains(lowerCaseFilter)) {
                            return true;
                        } else return products.getModelNumber().toLowerCase().contains(lowerCaseFilter);
                    }));

            SortedList<Product> productSortedList = new SortedList<>(productFilteredList);
            productSortedList.comparatorProperty().bind(productsTable.comparatorProperty());

            productsTable.setItems(productSortedList);
            productsTable.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
            productsTable.getSelectionModel().selectFirst();
        });

        /* ===  Edit,Delete,Clone the item in the TableView when the user select the item and then click the right mouse button === */
        productsTable.setRowFactory(tableView -> {
            final TableRow<Product> row = new TableRow<>();
            final ContextMenu contextMenu = new ContextMenu();
            final MenuItem removeItem = new MenuItem("Delete this product");
            final MenuItem editItemRo = new MenuItem("Editează acest produs");
            final MenuItem editItemBg = new MenuItem("Редактиране на този продукт");
            final MenuItem cloneItemRo = new MenuItem("Clonează acest produs");
            final MenuItem cloneItemBg = new MenuItem("Клонирайте този продукт");
            final MenuItem visitWebSite = new MenuItem("Visit the website");
            removeItem.setOnAction(event -> {
                Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();

                Alert deleteAlert = new Alert(Alert.AlertType.CONFIRMATION);
                deleteAlert.setTitle("Confirm the delete action!");
                deleteAlert.setHeaderText(null);
                deleteAlert.setWidth(300);
                deleteAlert.setHeight(100);
                deleteAlert.setContentText("You're sure you want to delete ( " + selectedProduct.getProductName()
                        + " ) the brand ( " + selectedProduct.getBrandName() + " ) from the list?");

                Optional<ButtonType> result = deleteAlert.showAndWait();
                if (result.isPresent() && result.get() == ButtonType.OK) {
                    productData.deleteProduct(selectedProduct);
                    productData.saveProducts();
                    productsTable.setItems(productData.getProducts());
                    productsTable.refresh();
                    Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
                    infoAlert.setTitle("Confirm the deletion!");
                    infoAlert.setHeaderText(null);
                    infoAlert.setWidth(300);
                    infoAlert.setHeight(100);
                    infoAlert.setContentText("The delete operation was successfully executed!!!");
                    infoAlert.showAndWait();
                }
            });

            // delete the selected item
            contextMenu.getItems().add(removeItem);

            // edit the selected item RO
            editItemRo.setOnAction(event -> showEditProductDialog());
            contextMenu.getItems().add(editItemRo);

            // edit the selected item BG
            editItemBg.setOnAction(event -> showEditProductDialogBul());
            contextMenu.getItems().add(editItemBg);

            // clone the selected item RO
            cloneItemRo.setOnAction(event -> showCloneProductDialog());
            contextMenu.getItems().add(cloneItemRo);

            // clone the selected item BG
            cloneItemBg.setOnAction(event -> showCloneProductDialogBul());
            contextMenu.getItems().add(cloneItemBg);

            // visit the website
            visitWebSite.setOnAction(event -> visitTheWebSite());
            contextMenu.getItems().add(visitWebSite);

            // Set context menu on row, but use a binding to make it only show for non-empty rows:
            row.contextMenuProperty().bind(
                    Bindings.when(row.emptyProperty())
                            .then((ContextMenu) null)
                            .otherwise(contextMenu)
            );
            return row;
        });
    }

    // Open the dialog to add product in the table. Romanian version
    @FXML
    public void showAddProductDialog() {
        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Adaugă un produs nou!");
        dialog.setHeaderText("Te rog să completezi informațiile pentru noul produs achiziționat!");
        dialog.setX(550);
        dialog.setY(100);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("productDialog.fxml"));
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
            ProductDialogController productDialogController = fxmlLoader.getController();
            Product newProduct = productDialogController.getNewProduct();
            productData.addProduct(newProduct);
            productData.saveProducts();
            productsTable.setItems(productData.getProducts());
            productsTable.refresh();
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Confirmă adăugarea.");
            infoAlert.setHeaderText(null);
            infoAlert.setWidth(300);
            infoAlert.setHeight(100);
            infoAlert.setContentText("Operațiunea de adăugare a fost executată cu succes!!!");
            infoAlert.showAndWait();
        }
    }

    // Open the dialog to edit the product in the table. Romanian version
    @FXML
    public void showEditProductDialog() {

        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertisment ... produs neselectat!");
            alert.setHeaderText(null);
            alert.setContentText("Te rog să selectezi produsul pe care doresti să-l editezi!");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Editați produsul!");
        dialog.setHeaderText("Te rog să completezi informațiile noi pentru acest produs!");
        dialog.setX(550);
        dialog.setY(100);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("productDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Could not load the dialog pane");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        ProductDialogController productDialogController = fxmlLoader.getController();
        productDialogController.editProduct(selectedProduct);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            productDialogController.updateProduct(selectedProduct);
            productData.saveProducts();
            productsTable.refresh();
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Confirmă editarea.");
            infoAlert.setHeaderText(null);
            infoAlert.setWidth(300);
            infoAlert.setHeight(100);
            infoAlert.setContentText("Operațiunea de editare a fost executată cu succes!!!");
            infoAlert.showAndWait();
        }
    }

    // Open the dialog to clone the product in the table. Romanian version
    @FXML
    public void showCloneProductDialog() {

        Alert infoAlertClone = new Alert(Alert.AlertType.INFORMATION);
        infoAlertClone.setTitle("Info... clonare");
        infoAlertClone.setHeaderText(null);
        infoAlertClone.setWidth(300);
        infoAlertClone.setHeight(500);
        infoAlertClone.setContentText("Foloseşte această opţiune doar dacă vrei să modifici preţul de vânzare al unui produs" +
                " existent sau dacă ai produsul respectiv pe diferite culori sau dacă ai produsul pe diferite mărimi si vrei să vinzi" +
                " produsul cu preţ diferit în functie de culoare sau mărime. De asemenea cănd introduci pentru prima dată în tabel un" +
                " produs care are diferite culori sau diferite mărimi, ca să nu introduci manual fiecare variantă a produsului respectiv," +
                " introduci doar o dată produsul şi după aia doar clonezi produsul respectiv şi modifici parametri necesari ca culoare sau mărime" +
                " sau preţul de vănzare.  Verifică secţiunea Ajutor > Instrucţiuni > Video (Clone Products) pentru instrucţiuni.");
        infoAlertClone.showAndWait();

        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertisment ... produs neselectat!");
            alert.setHeaderText(null);
            alert.setContentText("Te rog să selectezi produsul pe care doresti să-l clonezi!");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Clonează produsul!");
        dialog.setHeaderText("Te rog să completezi informațiile clonări pentru acest produs!");
        dialog.setX(550);
        dialog.setY(100);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("productDialog.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Could not load the dialog pane");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        ProductDialogController productDialogController = fxmlLoader.getController();
        productDialogController.editProduct(selectedProduct);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Product newProduct = productDialogController.getNewProduct();
            productData.addProduct(newProduct);
            productData.saveProducts();
            productsTable.setItems(productData.getProducts());
            productsTable.refresh();
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Confirmă clonarea.");
            infoAlert.setHeaderText(null);
            infoAlert.setWidth(300);
            infoAlert.setHeight(100);
            infoAlert.setContentText("Operațiunea de clonare a fost executată cu succes!!!");
            infoAlert.showAndWait();
        }
    }

    // Open the dialog to delete the product from the table. Romanian version
    @FXML
    public void deleteProduct() {

        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Avertisment ... produs neselectat!");
            alert.setHeaderText(null);
            alert.setContentText("Te rog să selectezi produsul pe care doresti să-l ștergi!");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirmă acțiunea de ștergere!");
        alert.setHeaderText(null);
        alert.setWidth(300);
        alert.setHeight(100);
        alert.setContentText("Ești sigur că vrei să ștergi ( " + selectedProduct.getProductName()
                + " ) marca ( " + selectedProduct.getBrandName() + " ) din listă?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            productData.deleteProduct(selectedProduct);
            productData.saveProducts();
            productsTable.setItems(productData.getProducts());
            productsTable.refresh();
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Confirmare stergere.");
            infoAlert.setHeaderText(null);
            infoAlert.setWidth(300);
            infoAlert.setHeight(100);
            infoAlert.setContentText("Operațiunea de ștergere a fost executată cu succes!!!");
            infoAlert.showAndWait();
        }
    }

    // Open the dialog to add product in the table. Bulgarian version
    @FXML
    public void showAddProductDialogBul() {

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Добавете нов продукт!");
        dialog.setHeaderText("моля попълни информацията за закупения нов продукт!");
        dialog.setX(550);
        dialog.setY(100);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("productDialogBulgarian.fxml"));
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
            ProductDialogBulgarianController productDialogBulgarianController = fxmlLoader.getController();
            Product newProduct = productDialogBulgarianController.getNewProduct();
            productData.addProduct(newProduct);
            productData.saveProducts();
            productsTable.setItems(productData.getProducts());
            productsTable.refresh();
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Моля, потвърдете добавянето.");
            infoAlert.setHeaderText(null);
            infoAlert.setWidth(300);
            infoAlert.setHeight(100);
            infoAlert.setContentText("Oперацията за добавяне беше успешна!!!");
            infoAlert.showAndWait();
        }
    }

    // Open the dialog to edit product in the table. Bulgarian version
    @FXML
    public void showEditProductDialogBul() {

        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение ... неизбран продукт!");
            alert.setHeaderText(null);
            alert.setContentText("моля, изберете продукта, който искате да редактирате!");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("Редактирайте продукта!");
        dialog.setHeaderText("моля попълнете новата информация за този продукт!");
        dialog.setX(550);
        dialog.setY(100);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("productDialogBulgarian.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            //System.out.println("Could not load the dialog pane");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        ProductDialogBulgarianController productDialogBulgarianController = fxmlLoader.getController();
        productDialogBulgarianController.editProduct(selectedProduct);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            productDialogBulgarianController.updateProduct(selectedProduct);
            productData.saveProducts();
            productsTable.refresh();
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Моля, потвърдете редакцията.");
            infoAlert.setHeaderText(null);
            infoAlert.setWidth(300);
            infoAlert.setHeight(100);
            infoAlert.setContentText("Oперацията за добавяне беше успешна!!!");
            infoAlert.showAndWait();
        }
    }

    // Open the dialog window to clone the product in the table. Bulgarian version
    @FXML
    public void showCloneProductDialogBul() {

        Alert infoAlertClone = new Alert(Alert.AlertType.INFORMATION);
        infoAlertClone.setTitle("Информация... клониране");
        infoAlertClone.setHeaderText(null);
        infoAlertClone.setWidth(300);
        infoAlertClone.setHeight(500);
        infoAlertClone.setContentText("Използвайте тази опция само ако искате да промените продажната цена на съществуващ продукт" +
                " или ако имате продукта в различни цветове или ако имате продукта в различни размери и искате да продадете продукта на" +
                " различна цена в зависимост от цвета или размера. Също така, когато за първи път въведете в таблицата продукт с различни" +
                " цветове или размери, така че да не въвеждате ръчно всеки вариант на съответния продукт, въведете продукта само веднъж и" +
                " след това просто клонирайте този продукт и променете необходимите параметри като цвят или размер. или продажната цена." +
                " Проверете секцията Помощ> Инструкции> Видео (продукти за клониране) за инструкции.");
        infoAlertClone.showAndWait();

        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение ... неизбран продукт!");
            alert.setHeaderText(null);
            alert.setContentText("моля, изберете продукта, който искате да клонирате!");
            alert.showAndWait();
            return;
        }

        Dialog<ButtonType> dialog = new Dialog<>();
        dialog.initOwner(mainPanel.getScene().getWindow());
        dialog.setTitle("клонира продукта!");
        dialog.setHeaderText("моля попълни информацията за клониране на този продукт!");
        dialog.setX(550);
        dialog.setY(100);
        FXMLLoader fxmlLoader = new FXMLLoader();
        fxmlLoader.setLocation(getClass().getResource("productDialogBulgarian.fxml"));
        try {
            dialog.getDialogPane().setContent(fxmlLoader.load());
        } catch (IOException e) {
            System.out.println("Could not load the dialog pane");
            e.printStackTrace();
            return;
        }

        dialog.getDialogPane().getButtonTypes().add(ButtonType.OK);
        dialog.getDialogPane().getButtonTypes().add(ButtonType.CANCEL);

        ProductDialogBulgarianController productDialogBulgarianController = fxmlLoader.getController();
        productDialogBulgarianController.editProduct(selectedProduct);

        Optional<ButtonType> result = dialog.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            Product newProduct = productDialogBulgarianController.getNewProduct();
            productData.addProduct(newProduct);
            productData.saveProducts();
            productsTable.setItems(productData.getProducts());
            productsTable.refresh();
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Потвърдете клонирането.");
            infoAlert.setHeaderText(null);
            infoAlert.setWidth(300);
            infoAlert.setHeight(100);
            infoAlert.setContentText("Oперацията по клонирането беше успешно извършена !!!");
            infoAlert.showAndWait();
        }
    }

    // Open the dialog window to delete the product in the table. Bulgarian version
    @FXML
    public void deleteProductBul() {

        Product selectedProduct = productsTable.getSelectionModel().getSelectedItem();
        if (selectedProduct == null) {
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение ... неизбран продукт!");
            alert.setHeaderText(null);
            alert.setContentText("Mоля изберете продукта, който искате да изтриете!");
            alert.showAndWait();
            return;
        }

        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Потвърдете действието за изтриване Гери");
        alert.setHeaderText(null);
        alert.setWidth(300);
        alert.setHeight(100);
        alert.setContentText("сигурна си, че искаш да изтриеш ( " + selectedProduct.getProductName()
                + " ) марка ( " + selectedProduct.getBrandName() + " ) от списъка?");

        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            productData.deleteProduct(selectedProduct);
            productData.saveProducts();
            productsTable.setItems(productData.getProducts());
            productsTable.refresh();
            Alert infoAlert = new Alert(Alert.AlertType.INFORMATION);
            infoAlert.setTitle("Потвърждение за изтриване.");
            infoAlert.setHeaderText(null);
            infoAlert.setWidth(300);
            infoAlert.setHeight(100);
            infoAlert.setContentText("Oперацията за изтриване беше успешна!!!");
            infoAlert.showAndWait();
        }
    }

    // ==== Open the stage, Monthly Profit ===
    @FXML
    private void monthlyProfit() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("mainMonthlyProfit.fxml"));
        root.getStylesheets().add(this.getClass().getResource("styles.css").toExternalForm());
        Stage primaryStageMonthly = new Stage();
        primaryStageMonthly.setTitle("MONTHLY PROFIT ACCOUNTING");
        primaryStageMonthly.setScene(new Scene(root, 1000, 500));
        primaryStageMonthly.show();
    }

    // ==== Open the stage, Year Profit  ===
    @FXML
    private void yearProfit() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("mainYearProfit.fxml"));
        root.getStylesheets().add(this.getClass().getResource("styles.css").toExternalForm());
        Stage primaryStageYear = new Stage();
        primaryStageYear.setTitle("YEAR PROFIT ACCOUNTING");
        primaryStageYear.setScene(new Scene(root, 1000, 515));
        primaryStageYear.show();
    }

    // ==== Open the stage, Help ===
    @FXML
    public void helpSection() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("instructionsSection.fxml"));
        root.getStylesheets().add(this.getClass().getResource("styles.css").toExternalForm());
        Stage primaryStageHelp = new Stage();
        primaryStageHelp.setTitle("INSTRUCTIONS FOR USING THIS PROGRAM");
        primaryStageHelp.setScene(new Scene(root, 1000, 500));
        primaryStageHelp.show();
    }

    // ====  Open the stage, Draft ===
    @FXML
    public void ciornaSection() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("ciorna.fxml"));
        root.getStylesheets().add(this.getClass().getResource("styles.css").toExternalForm());
        Stage primaryStageCiorna = new Stage();
        primaryStageCiorna.setTitle("DRAFT");
        primaryStageCiorna.setScene(new Scene(root, 1000, 500));
        primaryStageCiorna.show();
    }

    // ==== Open the stage, Exchange ===
    @FXML
    public void exchangeHandler() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("exchangeWebSite.fxml"));
        root.getStylesheets().add(this.getClass().getResource("styles.css").toExternalForm());
        Stage primaryStageExchange = new Stage();
        primaryStageExchange.setTitle("POUND STERLING TO LEV EXCHANGE COURSE FOR TODAY");
        primaryStageExchange.setScene(new Scene(root, 1000, 500));
        primaryStageExchange.show();
    }

    // ==== Open the stage, Google ===
    @FXML
    public void googleHandler() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("googleHomePage.fxml"));
        root.getStylesheets().add(this.getClass().getResource("styles.css").toExternalForm());
        Stage primaryStageGoogle = new Stage();
        primaryStageGoogle.setTitle("GOOGLE HOME PAGE");
        primaryStageGoogle.setScene(new Scene(root, 1000, 500));
        primaryStageGoogle.show();
    }

    // ==== Open the website from where the product has benn purchased ===
    @FXML
    public void visitTheWebSite() {

        try {

            URI uri = new URI(productsTable.getSelectionModel().getSelectedItem().getWebAddress());
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(uri);

        } catch (Exception exception) {

            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Warning ... Link not selected or invalid!!!");
            alert.setHeaderText("This security alert has been activated because one\nof the reasons below is true.");
            alert.setContentText("(1) You have not selected from the list the link of the product you want to watch!\n" +
                    "(2) The Null or Нула value is written in the Web Address box, which means that the product does not have a valid link!");
            alert.setWidth(300);
            alert.setHeight(300);
            alert.showAndWait();
        }
    }

    // ==== Open the stage, saved Invoices ===
    @FXML
    public void optionUploadFile() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("mainInvoice.fxml"));
        root.getStylesheets().add(this.getClass().getResource("styles.css").toExternalForm());
        Stage primaryStageUploadFile = new Stage();
        primaryStageUploadFile.setTitle("INVOICES");
        primaryStageUploadFile.setScene(new Scene(root, 1000, 500));
        primaryStageUploadFile.show();
    }

    // ==== Open the search box ===
    @FXML
    public void openSearchBox() {

        dialogSearchBox = new Stage();
        dialogSearchBox.setTitle("DETAILED SEARCH");
        dialogSearchBox.initModality(Modality.NONE);
        dialogSearchBox.setResizable(false);
        checkScreenBounds = Screen.getPrimary().getVisualBounds();
        dialogSearchBox.setX((checkScreenBounds.getWidth() / 3));
        dialogSearchBox.setY((checkScreenBounds.getHeight() / 1.3));

        textTitle.setText("You can search for a product in the box below.");
        textTitle.setId("searchBoxTitle");
        String color = "#ffffff";
        textTitle.setFill(javafx.scene.paint.Paint.valueOf(color));

        dialogVBox = new VBox(5);
        dialogVBox.setId("dialogSearch");
        dialogVBox.setAlignment(Pos.CENTER);
        dialogVBox.getChildren().addAll(textTitle,searchBox);
        dialogScene = new Scene(dialogVBox, 500, 61); // 61
        dialogScene.getStylesheets().add(this.getClass().getResource("styles.css").toExternalForm());
        dialogVBox.setPadding(new Insets(5, 5, 5, 5));
        dialogSearchBox.setScene(dialogScene);

        searchBox.setMaxHeight(30);
        searchBox.setFocusTraversable(false);
        searchBox.setPromptText("Type here: (Name) or (Brand) or (Model)");
        dialogSearchBox.show();
    }

    // ==== Close the search box ===
    @FXML
    public void closeSearchBox() {
        searchBox.clear();
        productsTable.setItems(productData.getProducts());
        productsTable.refresh();
        dialogSearchBox.close();
    }

    // ==== Open the stage, Calculator ===
    @FXML
    public void openCalculator() throws IOException {

        Parent root = FXMLLoader.load(getClass().getResource("calculator.fxml"));
        root.getStylesheets().add(this.getClass().getResource("styles.css").toExternalForm());
        Stage primaryStageCalculator = new Stage();
        primaryStageCalculator.setTitle("CALCULATOR");
        primaryStageCalculator.setScene(new Scene(root, 350, 473)); // 300 473
        primaryStageCalculator.show();
    }

    // ==== Show the numbers of entries in the tableView ===
    public void numberOfEntriesInTable() {

        dialogNoProd = new Stage();
        dialogNoProd.setTitle("NUMBER OF ENTRIES");
        dialogNoProd.initModality(Modality.NONE);
        dialogNoProd.setResizable(false);
        checkScreenBoundsNoProd = Screen.getPrimary().getVisualBounds();
        dialogNoProd.setX((checkScreenBoundsNoProd.getWidth() / 3));
        dialogNoProd.setY((checkScreenBoundsNoProd.getHeight() / 1.3));

        int numbersOfRow = productsTable.getItems().size();
        textTitleNoProd.setText("TOTAL NUMBER OF ENTRIES REGISTERED IN THE TABLE = " + numbersOfRow);
        textTitleNoProd.setId("searchBoxTitle");
        String color = "#ffffff";
        textTitleNoProd.setFill(Paint.valueOf(color));

        dialogVBoxNoProd = new VBox(2);
        dialogVBoxNoProd.setId("dialogSearch");
        dialogVBoxNoProd.setAlignment(Pos.CENTER);
        dialogVBoxNoProd.setPadding(new Insets(17, 5, 1, 5));
        dialogVBoxNoProd.getChildren().addAll(textTitleNoProd,labelNoProd);
        dialogSceneNoProd = new Scene(dialogVBoxNoProd, 500, 40);
        dialogSceneNoProd.getStylesheets().add(this.getClass().getResource("styles.css").toExternalForm());
        dialogNoProd.setScene(dialogSceneNoProd);

        labelNoProd.setMaxHeight(25);
        dialogNoProd.show();
    }

    // ==== Menu romanian language ===
    public void romanianLanguage() {

        menuBtn.setText("Meniu");
        addProductBtn.setVisible(true);
        editProductBtn.setVisible(true);
        cloneProductBtn.setVisible(true);
        deleteProductBtn.setVisible(true);

        addProductBtnBul.setVisible(false);
        editProductBtnBul.setVisible(false);
        cloneProductBtnBul.setVisible(false);
        deleteProductBtnBul.setVisible(false);

        buttonSelectLanguage.setText("Изберете Език");
        monthlyProfit.setText("Profit Lunar");
        yearProfit.setText("Profit Anual");

        help.setText("Ajutor");
        instructiuniBtn.setText("Instrucţiuni");
        ciorna.setText("Ciornă");
        exchange.setText("Schimb Valutar");
        googleHomePage.setText("Google");

        uploadFile.setText("Chitanţe");
        uploadFileBtn.setText("Deschide");

        visitTheWebSite.setText("Accesează Website");
        visitTheWebSiteBtn.setText("Deschide Website");

        calculatorBtn.setText("Deschide");

        openSearchBox.setText("Căsuţă De Căutare");
        searchBoxOpenBtn.setText("Deschide căsuţa de căutare");
        searchBoxCloseBtn.setText("Închide căsuţa de căutare");
        textTitle.setText("You can search for a product in the box below.");
        searchBox.setPromptText("Introduceți aici: (Nume) sau (Marcă) sau (Model)");

        closeProgram.setText("Închide Programul");
        closeApp.setText("Închide Aplicaţia");

        labelTitle.setText("GESTIUNE GENERALĂ ȘI CONTABILITATE DETALIATĂ");
        productName.setText("Produs");
        brandName.setText("Mărcă");
        modelNumber.setText("Model");
        colorName.setText("Culoare");
        sizeValue.setText("Mărime");
        categoryName.setText("Categorie");
        webAddress.setText("Adresă Web");
        dataColumn.setText("Dată");
        quantityColumn.setText("Cantitate");
        priceColumn.setText("Preţul Produsului");
        lotCost.setText("Costul Lotului");
        sellePrice.setText("Preţul De Vânzare");
        itemProfit.setText("Profit Pe Produs");
        lotProfit.setText("Profit brut Pe Lot");
        remainingStock.setText("Stoc Actual");
        remainingStockProfit.setText("Profit Net");

        int numbersOfRow = productsTable.getItems().size();
        textTitleNoProd.setText("NUMĂR TOTAL DE INTRĂRI ÎNREGISTRAȚI ÎN TABEL = " + numbersOfRow);
        numberOfProducts.setText("Afişează numărul de intrări");
        copyRightLabel.setText("© 2019 - " + localDate.getYear() + " Drepturi de autor Marinel");
    }

    // ==== Menu bulgarian language ===
    public void bulgarianLanguage() {

        menuBtn.setText("меню");
        addProductBtn.setVisible(false);
        editProductBtn.setVisible(false);
        cloneProductBtn.setVisible(false);
        deleteProductBtn.setVisible(false);

        addProductBtnBul.setVisible(true);
        editProductBtnBul.setVisible(true);
        cloneProductBtnBul.setVisible(true);
        deleteProductBtnBul.setVisible(true);

        monthlyProfit.setText("месечна печалба");
        yearProfit.setText("Годишна печалба");

        buttonSelectLanguage.setText("Selectează Limba");

        help.setText("Помощ");
        instructiuniBtn.setText("инструкции");
        ciorna.setText("проект");
        exchange.setText("обмяна на валута");
        googleHomePage.setText("Google");

        uploadFile.setText("Постъпления");
        uploadFileBtn.setText("Отворете");

        visitTheWebSite.setText("Отидете на уебсайта");
        visitTheWebSiteBtn.setText("Отворете уебсайта");

        calculatorBtn.setText("отворен");

        openSearchBox.setText("Поле за търсене");
        searchBoxOpenBtn.setText("Отворете полето за търсене");
        searchBoxCloseBtn.setText("Затворете полето за търсене");
        textTitle.setText("можете да потърсите продукт в полето по-долу.");
        searchBox.setPromptText("Въведете тук: (име) или (марка) или (модел)");

        closeProgram.setText("Затворете програмата");
        closeApp.setText("Затворете приложението");

        labelTitle.setText("ОБЩО УПРАВЛЕНИЕ И ПОДРОБНО СЧЕТОВОДСТВО");
        productName.setText("продукт");
        brandName.setText("марка");
        modelNumber.setText("модел");
        colorName.setText("цвят");
        sizeValue.setText("размер");
        categoryName.setText("категория");
        webAddress.setText("уеб адрес");
        dataColumn.setText("път");
        quantityColumn.setText("количество");
        priceColumn.setText("Цена на продукта");
        lotCost.setText("Цена на лот");
        sellePrice.setText("Цена за продажба");
        itemProfit.setText("Печалба от продукта");
        lotProfit.setText("Брутна печалба на лот");
        remainingStock.setText("Текущ запас");
        remainingStockProfit.setText("Нетна печалба");

        int numbersOfRowB = productsTable.getItems().size();
        textTitleNoProd.setText("ОБЩО БРОЙ ВХОДИ, РЕГИСТРИРАНИ В ТАБЛИЦАТА = " + numbersOfRowB);
        numberOfProducts.setText("Показва броя на записите");
        copyRightLabel.setText("© 2019 - " + localDate.getYear() + " Авторско право Marinel");
    }

    // ==== Close the program ===
    @FXML
    public void exitTheProgram() {
        Platform.exit();
    }
}
