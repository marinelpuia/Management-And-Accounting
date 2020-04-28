package com.marinel.datamodel;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javax.xml.stream.*;
import javax.xml.stream.events.*;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.InputStream;

public class InvoiceData {

    // *** This class InvoiceData store all our invoices ***

    private static final String INVOICE_FILE = "invoice.xml";
    private static final String INVOICE = "invoice";
    private static final String INVOICE_NAME = "invoice_name";
    private static final String DATE_ADDED = "date_added";
    private static final String FILE_LINK = "file_link";


    private final ObservableList<Invoice> invoicesData;

    // *** initialize the invoice list ***
    public InvoiceData() {
        invoicesData = FXCollections.observableArrayList();
    }

    // *** Add methods to add/delete/access invoice ***
    // *** Method to get the invoices ***
    public ObservableList<Invoice> getInvoiceData() {
        return invoicesData;
    }

    // *** Method to add the invoices **
    public void addInvoice(Invoice item) {
        invoicesData.add(item);
    }

    // *** Method to delete invoices ***
    public void deleteInvoice(Invoice item) {
        invoicesData.remove(item);
    }

    // *** Method to load the invoices from XML file ***
    public void loadInvoice() {
        try {
            // First, create a new XMLInputFactory
            XMLInputFactory inputFactory = XMLInputFactory.newInstance();
            // Setup a new eventReader
            InputStream in = new FileInputStream(INVOICE_FILE);
            XMLEventReader eventReader = inputFactory.createXMLEventReader(in);
            // read the XML document
            Invoice invoice = null;

            while (eventReader.hasNext()) {
                XMLEvent event = eventReader.nextEvent();

                if (event.isStartElement()) {
                    StartElement startElement = event.asStartElement();
                    // If we have a invoice item, we create a new invoice
                    if (startElement.getName().getLocalPart().equals(INVOICE)) {
                        invoice = new Invoice();
                        continue;
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(INVOICE_NAME)) {
                            event = eventReader.nextEvent();
                            if (invoice != null) {
                                invoice.setInvoiceName(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(DATE_ADDED)) {
                            event = eventReader.nextEvent();
                            if (invoice != null) {
                                invoice.setDateAdded(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }

                    if (event.isStartElement()) {
                        if (event.asStartElement().getName().getLocalPart()
                                .equals(FILE_LINK)) {
                            event = eventReader.nextEvent();
                            if (invoice != null) {
                                invoice.setFileLink(event.asCharacters().getData());
                            }
                            continue;
                        }
                    }


                }

                // If we reach the end of a invoice element, we add it to the list
                if (event.isEndElement()) {
                    EndElement endElement = event.asEndElement();
                    if (endElement.getName().getLocalPart().equals(INVOICE)) {
                        invoicesData.add(invoice);
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


    // ***  Method to write the invoices in to XML file ***mma
    public void saveInvoice() {

        try {
            // create an XMLOutputFactory
            XMLOutputFactory outputFactory = XMLOutputFactory.newInstance();
            // create XMLEventWriter
            XMLEventWriter eventWriter = outputFactory
                    .createXMLEventWriter(new FileOutputStream(INVOICE_FILE));
            // create an EventFactory
            XMLEventFactory eventFactory = XMLEventFactory.newInstance();
            XMLEvent end = eventFactory.createDTD("\n");
            // create and write Start Tag
            StartDocument startDocument = eventFactory.createStartDocument();
            eventWriter.add(startDocument);
            eventWriter.add(end);

            StartElement invoicesStartElement = eventFactory.createStartElement("",
                    "", "invoicesData");
            eventWriter.add(invoicesStartElement);
            eventWriter.add(end);

            for (Invoice invoice: invoicesData) {
                saveInvoice(eventWriter, eventFactory, invoice);
            }

            eventWriter.add(eventFactory.createEndElement("", "", "invoicesData"));
            eventWriter.add(end);
            eventWriter.add(eventFactory.createEndDocument());
            eventWriter.close();
        }
        catch (FileNotFoundException e) {
            System.out.println("Problem with Invoice file: " + e.getMessage());
            e.printStackTrace();
        }
        catch (XMLStreamException e) {
            System.out.println("Problem writing invoice: " + e.getMessage());
            e.printStackTrace();
        }
    }

    // *** Method to save the invoices in to XML file ***
    private void saveInvoice(XMLEventWriter eventWriter, XMLEventFactory eventFactory, Invoice invoice)
            throws FileNotFoundException, XMLStreamException {

        XMLEvent end = eventFactory.createDTD("\n");

        // create invoice open tag
        StartElement configStartElement = eventFactory.createStartElement("",
                "", INVOICE);
        eventWriter.add(configStartElement);
        eventWriter.add(end);

        // Write the different nodes
        createNode(eventWriter, INVOICE_NAME , invoice.getInvoiceName());
        createNode(eventWriter, DATE_ADDED, invoice.getDateAdded());
        createNode(eventWriter, FILE_LINK, invoice.getFileLink());


        eventWriter.add(eventFactory.createEndElement("", "", INVOICE));
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
