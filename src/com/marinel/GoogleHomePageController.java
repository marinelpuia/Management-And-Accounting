package com.marinel;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

public class GoogleHomePageController {

    @FXML
    private BorderPane googleBorderPane;
    @FXML
    private Hyperlink googleLink;
    @FXML
    private WebView googleWebView;
    @FXML
    private Button googleBtn;



    public void initialize() {

        googleBorderPane.setVisible(true);
        googleLink.setText("Hello, I'm Your Google Assistant, Click Me To Make Me Active And I Will Do The Best I Can To Help You");
        googleLink.setMaxWidth(Double.POSITIVE_INFINITY);
        googleLink.setAlignment(Pos.CENTER);
        googleWebView.setMaxWidth(Double.POSITIVE_INFINITY);
        googleWebView.setMaxHeight(Double.POSITIVE_INFINITY);
        googleBtn.setMaxWidth(Double.POSITIVE_INFINITY);

    }

    @FXML
    public void handleGoogleLink() throws IOException {

        WebEngine webEngine = googleWebView.getEngine();
        webEngine.load("https://www.google.com/");
        googleLink.setText("Write Your Request Below And Let Me Do The Hard Work For You.");

    }

    @FXML
    public void googleDeskTop() {
        Desktop desktop = Desktop.getDesktop();
        try {
            desktop.browse(new URI("https://www.google.com/"));
        } catch (IOException | URISyntaxException e) {
            e.printStackTrace();
        }
    }
}
