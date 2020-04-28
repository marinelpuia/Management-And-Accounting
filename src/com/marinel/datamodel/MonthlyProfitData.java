package com.marinel.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

public class MonthlyProfitData {

    // *** This class MonthlyProfitData store all our expenses details ***

    private static final String MONTHLY_PROFIT_FILE = "monthlyProfit.xml";

    private static final String MONTHLY_PROFIT = "monthly_profit";
    private static final String MONTHLY_NAME = "monthly_name";
    private static final String YEAR_NUMBER = "year_number";
    private static final String GROSS_PROFIT = "gross_profit";
    private static final String PRODUCTS_ORIGINAL_PRICE = "products_original_price";
    private static final String RENT_AMOUNT = "rent_amount";
    private static final String SALARY_AMOUNT = "salary_amount";
    private static final String ELECTRICITY_COST= "electricity_cost";
    private static final String HEATING_COST = "heating_cost";
    private static final String WATER_COST = "water_cost";
    private static final String ADVERTISING_COST = "advertising_cost";
    private static final String RELIGIOUS_DAY_EXPENSES = "religious_day_expenses";
    private static final String OTHER_EXPENSES = "other_expenses";
    private static final String NET_INCOME = "net_income";

    private final ObservableList<MonthlyProfit> monthlyProfits;

    // *** initialize the monthlyProfits list ***
    public MonthlyProfitData() {
        monthlyProfits = FXCollections.observableArrayList();
    }

    // *** Add methods to add/delete/access monthly profits ***
    // *** Method to get the monthly profits ***


    public ObservableList<MonthlyProfit> getProfits() {
        return monthlyProfits;
    }

    // *** Method to add the monthly profits ***

    public void addProfit(MonthlyProfit item) {
        monthlyProfits.add(item);
    }

    // *** Method to delete monthly profit ***

    public void deleteProfit(MonthlyProfit item) {
        monthlyProfits.remove(item);
    }

    // *** Method to load the monthly profits from XML file ***

    public void loadProfits() {
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(MONTHLY_PROFIT_FILE);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            MonthlyProfit monthlyProfit = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have a profit item, we create a new monthlyProfit
                    if (startElement.getName().getLocalPart().equals(MONTHLY_PROFIT)) {
                        monthlyProfit = new MonthlyProfit();
                        continue;
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(MONTHLY_NAME)) {
                            event = eventReader.nextEvent();
                            if (monthlyProfit != null) {
                                monthlyProfit.setMonthlyName(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(YEAR_NUMBER)) {
                            event = eventReader.nextEvent();
                            if (monthlyProfit != null) {
                                monthlyProfit.setYearNumber(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(GROSS_PROFIT)) {
                            event = eventReader.nextEvent();
                            if (monthlyProfit != null) {
                                monthlyProfit.setGrossProfit(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(PRODUCTS_ORIGINAL_PRICE)) {
                            event = eventReader.nextEvent();
                            if (monthlyProfit != null) {
                                monthlyProfit.setProductsOriginalPrice(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(RENT_AMOUNT)) {
                            event = eventReader.nextEvent();
                            if (monthlyProfit != null) {
                                monthlyProfit.setRentAmount(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(SALARY_AMOUNT)) {
                            event = eventReader.nextEvent();
                            if (monthlyProfit != null) {
                                monthlyProfit.setSalaryAmount(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(ELECTRICITY_COST)) {
                            event = eventReader.nextEvent();
                            if (monthlyProfit != null) {
                                monthlyProfit.setElectricityCost(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(HEATING_COST)) {
                            event = eventReader.nextEvent();
                            if (monthlyProfit != null) {
                                monthlyProfit.setHeatingCost(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(WATER_COST)) {
                            event = eventReader.nextEvent();
                            if (monthlyProfit != null) {
                                monthlyProfit.setWaterCost(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(ADVERTISING_COST)) {
                            event = eventReader.nextEvent();
                            if (monthlyProfit != null) {
                                monthlyProfit.setAdvertisingCost(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(RELIGIOUS_DAY_EXPENSES)) {
                            event = eventReader.nextEvent();
                            if (monthlyProfit != null) {
                                monthlyProfit.setReligiousDayExpenses(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(OTHER_EXPENSES)) {
                            event = eventReader.nextEvent();
                            if (monthlyProfit != null) {
                                monthlyProfit.setOtherExpenses(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals((NET_INCOME))) {
                            event = eventReader.nextEvent();
                            if (monthlyProfit != null) {
                                monthlyProfit.setNetIncome(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                }

                // If we reach the end of a monthly profit element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(MONTHLY_PROFIT)) {
                        monthlyProfits.add(monthlyProfit);
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

    // ***  Method to write the monthly profits in to XML file ***

    public void saveProfits() {

        try {
            // create an XMLOutputFactory
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            // create XMLEventWriter
            XMLEventWriter eventWriter = outputFactory
                    .createXMLEventWriter(new FileOutputStream(MONTHLY_PROFIT_FILE));
            // create an EventFactory
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD("\n");
            // create and write Start Tag
            StartDocument startDocument = eventFactory.createStartDocument();
            eventWriter.add(startDocument);
            eventWriter.add(end);

            StartElement monthlyProfitsStartElement = eventFactory.createStartElement("",
                    "", "monthlyProfits");
            eventWriter.add(monthlyProfitsStartElement);
            eventWriter.add(end);

            for (MonthlyProfit monthlyProfit: monthlyProfits) {
                saveProfit(eventWriter, eventFactory, monthlyProfit);
            }

            eventWriter.add(eventFactory.createEndElement("", "", "monthlyProfits"));
            eventWriter.add(end);
            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Problem with Profit file: " + e.getMessage());
            e.printStackTrace();
        }
        catch (XMLStreamException e) {
            System.out.println("Problem writing profit: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // *** Method to save the monthly profits in to XML file ***
    private void saveProfit(XMLEventWriter eventWriter, XMLEventFactory eventFactory, MonthlyProfit monthlyProfit)
            throws XMLStreamException {

        XMLEvent end = eventFactory.createDTD("\n");

        // create profit open tag
        StartElement configStartElement = eventFactory.createStartElement("",
                "", MONTHLY_PROFIT);
        eventWriter.add(configStartElement);
        eventWriter.add(end);
        // Write the different nodes
        createNode(eventWriter, MONTHLY_NAME, monthlyProfit.getMonthlyName());
        createNode(eventWriter, YEAR_NUMBER, monthlyProfit.getYearNumber());
        createNode(eventWriter, GROSS_PROFIT, monthlyProfit.getGrossProfit());
        createNode(eventWriter, PRODUCTS_ORIGINAL_PRICE, monthlyProfit.getProductsOriginalPrice());
        createNode(eventWriter, RENT_AMOUNT, monthlyProfit.getRentAmount());
        createNode(eventWriter, SALARY_AMOUNT, monthlyProfit.getSalaryAmount());
        createNode(eventWriter, ELECTRICITY_COST, monthlyProfit.getElectricityCost());
        createNode(eventWriter, HEATING_COST, monthlyProfit.getHeatingCost());
        createNode(eventWriter, WATER_COST, monthlyProfit.getWaterCost());
        createNode(eventWriter, ADVERTISING_COST, monthlyProfit.getAdvertisingCost());
        createNode(eventWriter, RELIGIOUS_DAY_EXPENSES, monthlyProfit.getReligiousDayExpenses());
        createNode(eventWriter, OTHER_EXPENSES, monthlyProfit.getOtherExpenses());
        createNode(eventWriter, NET_INCOME, monthlyProfit.getNetIncome());

        eventWriter.add(eventFactory.createEndElement("", "", MONTHLY_PROFIT));
        eventWriter.add(end);
    }

    // *** Method to create node ***
    private void createNode(XMLEventWriter eventWriter, String name,
                            String value) throws XMLStreamException {

        XMLEventFactory eventFactory = XMLEventFactory.newInstance();
        XMLEvent end = eventFactory.createDTD("\n");
        XMLEvent tab = eventFactory.createDTD("\t");
        // create Start node
        StartElement sElement = eventFactory.createStartElement("", "", name);
        eventWriter.add(tab);
        eventWriter.add(sElement);
        // create Content
        Characters characters = eventFactory.createCharacters(value);
        eventWriter.add(characters);
        // create End node
        EndElement eElement = eventFactory.createEndElement("", "", name);
        eventWriter.add(eElement);
        eventWriter.add(end);
    }
}
