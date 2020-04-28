package com.marinel.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

public class YearProfitData {

    // *** This class YearProfitData store all our expenses details ***
    private static final String YEAR_PROFIT_FILE = "yearProfit.xml";
    private static final String YEAR_PROFIT = "year_profit";

    private static final String YEAR_NUMBER = "year_number";
    private static final String GROSS_PROFIT_BEFORE_TAX = "gross_profit_before_tax";
    private static final String AMOUNT_OF_TAX_PAID = "amount_of_tax_paid";
    private static final String NET_PROFIT_AFTER_TAX = "net_profit_after_tax";

    private final ObservableList<YearProfit> yearProfits;

    // *** initialize the yearProfits list ***
    public YearProfitData() {
        yearProfits = FXCollections.observableArrayList();
    }

    // *** Add methods to add/delete/access yearProfits ***
    // *** Method to get the yearProfits ***
    public ObservableList<YearProfit> getYearProfits() {
        return yearProfits;
    }

    // *** Method to add the yearProfits ***
    public void addYearProfit(YearProfit item) {
        yearProfits.add(item);
    }

    // *** Method to delete yearProfits ***
    public void deleteYearProfit(YearProfit item) {
        yearProfits.remove(item);
    }

    // *** Method to load the yearProfits from XML file ***
    public void loadYearProfits() {
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(YEAR_PROFIT_FILE);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            YearProfit yearProfit = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have a yearProfits item, we create a new yearProfits
                    if (startElement.getName().getLocalPart().equals(YEAR_PROFIT)) {
                        yearProfit = new YearProfit();
                        continue;
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(YEAR_NUMBER)) {
                            event = eventReader.nextEvent();
                            if (yearProfit != null) {
                                yearProfit.setYearNumber(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(GROSS_PROFIT_BEFORE_TAX)) {
                            event = eventReader.nextEvent();
                            if (yearProfit != null) {
                                yearProfit.setGrossProfitBeforeTax(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(AMOUNT_OF_TAX_PAID)) {
                            event = eventReader.nextEvent();
                            if (yearProfit != null) {
                                yearProfit.setAmountOfTaxPaid(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(NET_PROFIT_AFTER_TAX)) {
                            event = eventReader.nextEvent();
                            if (yearProfit != null) {
                                yearProfit.setNetProfitAfterTax(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                }

                // If we reach the end of a yearProfits element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(YEAR_PROFIT)) {
                        yearProfits.add(yearProfit);
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

    // ***  Method to write the yearProfits in to XML file ***
    public void saveYearProfits() {

        try {
            // create an XMLOutputFactory
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            // create XMLEventWriter
            XMLEventWriter eventWriter = outputFactory
                    .createXMLEventWriter(new FileOutputStream(YEAR_PROFIT_FILE));
            // create an EventFactory
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD("\n");
            // create and write Start Tag
            StartDocument startDocument = eventFactory.createStartDocument();
            eventWriter.add(startDocument);
            eventWriter.add(end);

            StartElement yearProfitsStartElement = eventFactory.createStartElement("",
                    "", "yearProfits");
            eventWriter.add(yearProfitsStartElement);
            eventWriter.add(end);

            for (YearProfit yearProfit: yearProfits) {
                saveYearProfits(eventWriter, eventFactory, yearProfit);
            }

            eventWriter.add(eventFactory.createEndElement("", "", "yearProfits"));
            eventWriter.add(end);
            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Problem with YearProfit file: " + e.getMessage());
            e.printStackTrace();
        }
        catch (XMLStreamException e) {
            System.out.println("Problem writing yearProfits: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // *** Method to save the yearProfits in to XML file ***
    private void saveYearProfits(XMLEventWriter eventWriter, XMLEventFactory eventFactory, YearProfit yearProfit)
            throws FileNotFoundException, XMLStreamException {

        XMLEvent end = eventFactory.createDTD("\n");

        // create yearProfits open tag
        StartElement configStartElement = eventFactory.createStartElement("",
                "", YEAR_PROFIT);
        eventWriter.add(configStartElement);
        eventWriter.add(end);
        // Write the different nodes
        createNode(eventWriter, YEAR_NUMBER, yearProfit.getYearNumber());
        createNode(eventWriter, GROSS_PROFIT_BEFORE_TAX, yearProfit.getGrossProfitBeforeTax());
        createNode(eventWriter, AMOUNT_OF_TAX_PAID, yearProfit.getAmountOfTaxPaid());
        createNode(eventWriter, NET_PROFIT_AFTER_TAX, yearProfit.getNetProfitAfterTax());

        eventWriter.add(eventFactory.createEndElement("", "", YEAR_PROFIT));
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
