package com.marinel;

import com.marinel.datamodel.Product;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public class ProductDialogBulgarianController {

    Product newProduct;
    private final DecimalFormat decimalFormat = new DecimalFormat("#.##");

    @FXML
    private TextField productNameField;
    @FXML
    private TextField brandNameField;
    @FXML
    private TextField modelNumberField;
    @FXML
    private TextField colorNameField;
    @FXML
    private TextField sizeValueField;
    @FXML
    private TextField categoryNameField;
    @FXML
    private TextField webAddressField;
    @FXML
    private DatePicker getDateOption;
    @FXML
    private TextField dateOfPurchaseField;
    @FXML
    private TextField volumeQuantityField;
    @FXML
    private  TextField buyingPriceField;
    @FXML
    private TextField sellingPriceField;
    @FXML
    private TextField remainingStockField;


    // Collect all data from productDialogBulgarian.fxml and then update the constructor in Product.java
    public Product getNewProduct() {

        String productName = productNameField.getText();

        if (productNameField.getText() == null || productNameField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение ... празни полета");
            alert.setHeaderText(null);
            alert.setWidth(400);
            alert.setHeight(250);
            alert.setContentText("не сте попълнили полето Продукт, но ви уверявам, че можете да го редактирате по-късно, " +
                    "просто използвайте опцията за редактиране в менюто.");
            alert.showAndWait();
            productNameField.setText("Нула");
            productName = productNameField.getText();
        }

        String brandName = brandNameField.getText();

        if (brandNameField.getText() == null || brandNameField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение ... празни полета");
            alert.setHeaderText(null);
            alert.setWidth(400);
            alert.setHeight(250);
            alert.setContentText("ти не попълни полето Марк, но ви уверявам, че можете да го редактирате по-късно, " +
                    "просто използвайте опцията за редактиране в менюто.");
            alert.showAndWait();
            brandNameField.setText("Нула");
            brandName = brandNameField.getText();
        }

        String modelNumber = modelNumberField.getText();

        if (modelNumberField.getText() == null || modelNumberField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение ... празни полета");
            alert.setHeaderText(null);
            alert.setWidth(400);
            alert.setHeight(250);
            alert.setContentText("не си попълнил полето Model, но те уверявам, че можеш да го редактираш по-късно, " +
                    "просто използвай опцията за редактиране в менюто.");
            alert.showAndWait();
            modelNumberField.setText("Нула");
            modelNumber = modelNumberField.getText();
        }

        String colorName = colorNameField.getText();

        if (colorNameField.getText() == null || colorNameField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение ... празни полета");
            alert.setHeaderText(null);
            alert.setWidth(400);
            alert.setHeight(250);
            alert.setContentText("ти не попълни полето Цвят, но ви уверявам, че можете да го редактирате по-късно, " +
                    "просто използвайте опцията за редактиране в менюто.");
            alert.showAndWait();
            colorNameField.setText("Нула");
            colorName = colorNameField.getText();
        }

        String sizeValue = sizeValueField.getText();

        if (sizeValueField.getText() == null || sizeValueField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение ... празни полета");
            alert.setHeaderText(null);
            alert.setWidth(400);
            alert.setHeight(250);
            alert.setContentText("ти не попълни полето Размер, но ви уверявам, че можете да го редактирате по-късно, " +
                    "просто използвайте опцията за редактиране в менюто.");
            alert.showAndWait();
            sizeValueField.setText("Нула");
            sizeValue = sizeValueField.getText();
        }

        String categoryName = categoryNameField.getText();

        if (categoryNameField.getText() == null || categoryNameField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение ... празни полета");
            alert.setHeaderText(null);
            alert.setWidth(400);
            alert.setHeight(250);
            alert.setContentText("ти не попълни полето Категория, но ви уверявам, че можете да го редактирате по-късно, " +
                    "просто използвайте опцията за редактиране в менюто.");
            alert.showAndWait();
            categoryNameField.setText("Нула");
            categoryName = categoryNameField.getText();
        }

        String webAddress = webAddressField.getText();

        if (webAddressField.getText() == null || webAddressField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение ... празни полета");
            alert.setHeaderText(null);
            alert.setWidth(400);
            alert.setHeight(250);
            alert.setContentText("ти не попълни полето за уеб адрес, но ви уверявам, че можете да го редактирате по-късно, " +
                    "просто използвайте опцията за редактиране в менюто.");
            alert.showAndWait();
            webAddressField.setText("Нула");
            webAddress = webAddressField.getText();
        }

        String purchaseDate = dateOfPurchaseField.getText();

        if (dateOfPurchaseField.getText() == null || dateOfPurchaseField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение ... празни полета");
            alert.setHeaderText(null);
            alert.setWidth(400);
            alert.setHeight(250);
            alert.setContentText("ти не попълни полето Данни, но ви уверявам, че можете да го редактирате по-късно, " +
                    "просто използвайте опцията за редактиране в менюто.");
            alert.showAndWait();
            dateOfPurchaseField.setText("Нула");
            purchaseDate = dateOfPurchaseField.getText();

        }

        String volumeQuantity = volumeQuantityField.getText();

        if (volumeQuantityField.getText() == null || volumeQuantityField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение ... празни полета");
            alert.setHeaderText(null);
            alert.setWidth(400);
            alert.setHeight(250);
            alert.setContentText("ти не попълни полето Количество (двойка или парче), но ви уверявам, че можете да " +
                    "го редактирате по-късно, просто използвайте опцията за редактиране в менюто.");
            alert.showAndWait();
            volumeQuantityField.setText(String.valueOf(0));
            volumeQuantity = volumeQuantityField.getText();
        }

        String buyingPrice = buyingPriceField.getText();

        if (buyingPriceField.getText() == null || buyingPriceField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение ... празни полета");
            alert.setHeaderText(null);
            alert.setWidth(400);
            alert.setHeight(250);
            alert.setContentText("не си попълнил полето Цена на продукта (двойка или парче), но уверявам те, че можеш да " +
                    "го редактираш по-късно, просто използвай опцията за редактиране на менюто.");
            alert.showAndWait();
            buyingPriceField.setText(String.valueOf(0));
            buyingPrice = buyingPriceField.getText();

        }

        // Lot price
        double valueOfVolumeQuantityField = Double.parseDouble(volumeQuantity);
        double valueOfBuyingPriceField = Double.parseDouble(buyingPrice);
        double costOfStack = valueOfVolumeQuantityField * valueOfBuyingPriceField;
        String stackPrice = "" + decimalFormat.format(costOfStack) + " ( LEV )";

        String sellingPrice = sellingPriceField.getText();

        if (sellingPriceField.getText() == null || sellingPriceField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение ... празни полета");
            alert.setHeaderText(null);
            alert.setWidth(400);
            alert.setHeight(250);
            alert.setContentText("ти не попълни полето Продажна цена (двойка или парче), но те уверявам, че можеш да я " +
                    "редактираш по-късно, просто трябва да използваш опцията за редактиране на менюто.");
            alert.showAndWait();
            sellingPriceField.setText(String.valueOf(0));
            sellingPrice = sellingPriceField.getText();

        }

        // Profit per pair or pieces (per product)
        double valueOfBuyingPrice = Double.parseDouble(buyingPrice);
        double valueOfSellingPrice = Double.parseDouble(sellingPrice);
        double profitPerProduct = valueOfSellingPrice - valueOfBuyingPrice;
        String profitAmount = "" + decimalFormat.format(profitPerProduct) + " ( LEV )";

        // Profit brut per lot
        double valueOfVolumeQuantity = Double.parseDouble(volumeQuantity);
        double profitTotalPerStack = valueOfVolumeQuantity * valueOfSellingPrice;
        String profitStack = "" + decimalFormat.format(profitTotalPerStack) + " ( LEV )";

        String remainingStock = remainingStockField.getText();

        if (remainingStockField.getText() == null || remainingStockField.getText().trim().isEmpty()) {

            Alert alert= new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Предупреждение ... празни полета");
            alert.setHeaderText(null);
            alert.setWidth(400);
            alert.setHeight(250);
            alert.setContentText("ти не попълни текущото поле за акции, но ви уверявам, че можете да го редактирате по-късно, " +
                    "просто използвайте опцията за редактиране в менюто.");
            alert.showAndWait();
            remainingStockField.setText(String.valueOf(0));
            remainingStock = remainingStockField.getText();

        }

        // Profit net per product or (per lot if the stock is = 0) deducted from remaining stock
        double valueOfProductSold = Double.parseDouble(volumeQuantity) - Double.parseDouble(remainingStock);
        double profitPerProductNet = (valueOfSellingPrice - valueOfBuyingPrice) * valueOfProductSold;
        String remainingStockProfit = "" + decimalFormat.format(profitPerProductNet) + " ( LEV )";

        // Create new object whit the data collected above
        newProduct = new Product(productName, brandName, modelNumber, colorName, sizeValue, categoryName, webAddress, purchaseDate,
                volumeQuantity, buyingPrice, stackPrice, sellingPrice, profitAmount, profitStack, remainingStock, remainingStockProfit);

        return newProduct;
    }

    // Method to edit the product
    public void editProduct(Product product) {

        productNameField.setText(product.getProductName());
        brandNameField.setText(product.getBrandName());
        modelNumberField.setText(product.getModelNumber());
        colorNameField.setText(product.getColorName());
        sizeValueField.setText(product.getSizeValue());
        categoryNameField.setText(product.getCategoryName());
        webAddressField.setText(product.getWebAddress());
        dateOfPurchaseField.setText(product.getPurchaseDate());
        volumeQuantityField.setText(product.getVolumeQuantity());
        buyingPriceField.setText(product.getBuyingPrice());
        sellingPriceField.setText(product.getSellingPrice());
        remainingStockField.setText(product.getRemainingStock());
    }

    // Method to update the product
    public void updateProduct(Product product) {

        product.setProductName(productNameField.getText());
        product.setBrandName(brandNameField.getText());
        product.setModelNumber(modelNumberField.getText());
        product.setColorName(colorNameField.getText());
        product.setSizeValue(sizeValueField.getText());
        product.setCategoryName(categoryNameField.getText());
        product.setWebAddress(webAddressField.getText());
        product.setPurchaseDate(dateOfPurchaseField.getText());
        product.setVolumeQuantity(volumeQuantityField.getText());
        product.setBuyingPrice(buyingPriceField.getText());
        product.setStackPrice(getNewProduct().getStackPrice());
        product.setSellingPrice(sellingPriceField.getText());
        product.setProfitAmount(getNewProduct().getProfitAmount());
        product.setProfitStack(getNewProduct().getProfitStack());
        product.setRemainingStock(remainingStockField.getText());
        product.setRemainingStockProfit(getNewProduct().getRemainingStockProfit());
    }

    // Method to setPromptText in input fields when the user enter the mouse in the dialog box it self
    // onMouseMoved method productDiaLogBulgarian.fxml
    public void userInputsWarning() {
        if (volumeQuantityField.getText().equals("")){
            volumeQuantityField.setPromptText("Изпълнете Задължително!");
            buyingPriceField.setPromptText("Изпълнете Задължително!");
            sellingPriceField.setPromptText("Изпълнете Задължително!");
            remainingStockField.setPromptText("Изпълнете Задължително!");
        } else {
            volumeQuantityField.setPromptText("");
            buyingPriceField.setPromptText("");
            sellingPriceField.setPromptText("");
            remainingStockField.setPromptText("");
        }
    }

    // Method to paste the date from dataPiker in to dateOfPurchaseField
    public void pasteDate() {
        String pastedDate = getDateOption.getValue().format(DateTimeFormatter.ofPattern("dd-MMMM-yyyy"));
        dateOfPurchaseField.setText(pastedDate);
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("INFO!");
        alert.setHeaderText(null);
        alert.setContentText("датата е въведена, продължете към следващите раздели!");
        alert.showAndWait();
    }
}

