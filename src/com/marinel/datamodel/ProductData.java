package com.marinel.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

public class ProductData {

    // *** This class ProductData store all our products details ***

    private static final String PRODUCTS_FILE = "products.xml";

    private static final String PRODUCT = "product";
    private static final String PRODUCT_NAME = "product_name";
    private static final String BRAND_NAME = "brand_name";
    private static final String MODEL_NUMBER = "model_number";
    private static final String COLOR_NAME = "color_name";
    private static final String SIZE_VALUE = "size_value";
    private static final String CATEGORY_NAME = "category_name";
    private static final String WEB_ADDRESS = "web_address";
    private static final String PURCHASE_DATE = "purchase_date";
    private static final String VOLUME_QUANTITY = "volume_quantity";
    private static final String BUYING_PRICE = "buying_price";
    private static final String STACK_PRICE = "stack_price";
    private static final String SELLING_PRICE = "selling_price";
    private static final String PROFIT_AMOUNT = "profit_amount";
    private static final String PROFIT_STACK = "profit_stack";
    private static final String REMAINING_STOCK = "remaining_stock";
    private static final String REMAINING_STOCK_PROFIT = "remaining_stock_profit";


    private final ObservableList<Product> products;

    public ProductData() {
        // *** initialize the products list  ***
        products = FXCollections.observableArrayList();
    }

    // *** Method to get the products ***
    public ObservableList<Product> getProducts() {
        return products;
    }

    // *** Method to add the product ***
    public void addProduct(Product item) {
        products.add(item);
    }

    // *** Method to delete product ***
    public void deleteProduct(Product item) {
        products.remove(item);
    }

    // *** Method to load the products from XML file ***
    public void loadProducts() {
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(PRODUCTS_FILE);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            Product product = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have a product item, we create a new product
                    if (startElement.getName().getLocalPart().equals(PRODUCT)) {
                        product = new Product();
                        continue;
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(PRODUCT_NAME)) {
                            event = eventReader.nextEvent();
                            if (product != null) {
                                product.setProductName(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(BRAND_NAME)) {
                            event = eventReader.nextEvent();
                            if (product != null) {
                                product.setBrandName(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(COLOR_NAME)) {
                            event = eventReader.nextEvent();
                            if (product != null) {
                                product.setColorName(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(SIZE_VALUE)) {
                            event = eventReader.nextEvent();
                            if (product != null) {
                                product.setSizeValue(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(MODEL_NUMBER)) {
                            event = eventReader.nextEvent();
                            if (product != null) {
                                product.setModelNumber(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(CATEGORY_NAME)) {
                            event = eventReader.nextEvent();
                            if (product != null) {
                                product.setCategoryName(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(WEB_ADDRESS)) {
                            event = eventReader.nextEvent();
                            if (product != null) {
                                product.setWebAddress(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(PURCHASE_DATE)) {
                        event = eventReader.nextEvent();
                        if (product != null) {
                            product.setPurchaseDate(event.asCharacters().getData());
                        }
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(VOLUME_QUANTITY)) {
                        event = eventReader.nextEvent();
                        if (product != null) {
                            product.setVolumeQuantity(event.asCharacters().getData());
                        }
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(BUYING_PRICE)) {
                        event = eventReader.nextEvent();
                        if (product != null) {
                            product.setBuyingPrice(event.asCharacters().getData());
                        }
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(STACK_PRICE)) {
                        event = eventReader.nextEvent();
                        if (product != null) {
                            product.setStackPrice(event.asCharacters().getData());
                        }
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(SELLING_PRICE)) {
                        event = eventReader.nextEvent();
                        if (product != null) {
                            product.setSellingPrice(event.asCharacters().getData());
                        }
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(PROFIT_AMOUNT)) {
                        event = eventReader.nextEvent();
                        if (product != null) {
                            product.setProfitAmount(event.asCharacters().getData());
                        }
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(PROFIT_STACK)) {
                        event = eventReader.nextEvent();
                        if (product != null) {
                            product.setProfitStack(event.asCharacters().getData());
                        }
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(REMAINING_STOCK)) {
                        event = eventReader.nextEvent();
                        if (product != null) {
                            product.setRemainingStock(event.asCharacters().getData());
                        }
                        continue;
                    }

                    if (event.asStartElement().getName().getLocalPart()
                            .equals(REMAINING_STOCK_PROFIT)) {
                        event = eventReader.nextEvent();
                        if (product != null) {
                            product.setRemainingStockProfit(event.asCharacters().getData());
                        }
                        continue;
                    }

                }

                // If we reach the end of a product element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(PRODUCT)) {
                        products.add(product);
                    }
                }
            }
        }
        catch (FileNotFoundException e) {
            //e.printStackTrace();
        }
        catch (XMLStreamException e) {
            e.printStackTrace();
        }
    }

    // ***  Method to write the products in to XML file ***
    public void saveProducts() {

        try {
            // Create an XMLOutputFactory
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            // Create XMLEventWriter
            XMLEventWriter eventWriter = outputFactory
                    .createXMLEventWriter(new FileOutputStream(PRODUCTS_FILE));
            // Create an EventFactory
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD("\n");
            // Create and write Start Tag
            StartDocument startDocument = eventFactory.createStartDocument();
            eventWriter.add(startDocument);
            eventWriter.add(end);

            StartElement productsStartElement = eventFactory.createStartElement("","", "products");
            eventWriter.add(productsStartElement);
            eventWriter.add(end);

            for (Product product: products) {
                saveProduct(eventWriter, eventFactory, product);
            }

            eventWriter.add(eventFactory.createEndElement("", "", "products"));
            eventWriter.add(end);
            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Problem with Products file: " + e.getMessage());
            e.printStackTrace();
        }
        catch (XMLStreamException e) {
            System.out.println("Problem writing product: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // *** Method to save the products in to XML file ***
    private void saveProduct(XMLEventWriter eventWriter, XMLEventFactory eventFactory, Product product)
            throws XMLStreamException {

        XMLEvent end = eventFactory.createDTD("\n");

        // Create product open tag
        StartElement configStartElement = eventFactory.createStartElement("",
                "", PRODUCT);
        eventWriter.add(configStartElement);
        eventWriter.add(end);
        // Write the different nodes
        createNode(eventWriter, PRODUCT_NAME, product.getProductName());
        createNode(eventWriter, BRAND_NAME, product.getBrandName());
        createNode(eventWriter, MODEL_NUMBER, product.getModelNumber());
        createNode(eventWriter, COLOR_NAME, product.getColorName());
        createNode(eventWriter, SIZE_VALUE, product.getSizeValue());
        createNode(eventWriter, CATEGORY_NAME, product.getCategoryName());
        createNode(eventWriter, WEB_ADDRESS, product.getWebAddress());
        createNode(eventWriter, PURCHASE_DATE, product.getPurchaseDate());
        createNode(eventWriter, VOLUME_QUANTITY, product.getVolumeQuantity());
        createNode(eventWriter, BUYING_PRICE, product.getBuyingPrice());
        createNode(eventWriter, STACK_PRICE, product.getStackPrice());
        createNode(eventWriter, SELLING_PRICE, product.getSellingPrice());
        createNode(eventWriter, PROFIT_AMOUNT, product.getProfitAmount());
        createNode(eventWriter, PROFIT_STACK, product.getProfitStack());
        createNode(eventWriter, REMAINING_STOCK, product.getRemainingStock());
        createNode(eventWriter, REMAINING_STOCK_PROFIT, product.getRemainingStockProfit());


        eventWriter.add(eventFactory.createEndElement("", "", PRODUCT));
        eventWriter.add(end);
    }

    // *** Method to create node ***
    private void createNode(XMLEventWriter eventWriter, String name,
                            String value) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        // Create Start node
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        // Create Content
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        // Create End node
        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);
    }
}
