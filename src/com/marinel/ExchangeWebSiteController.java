package com.marinel;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Hyperlink;
import javafx.scene.layout.BorderPane;
import javafx.scene.web.WebEngine;
import javafx.scene.web.WebView;

public class ExchangeWebSiteController {

    @FXML
    private BorderPane exchangeBorderPane;
    @FXML
    private WebView webView;
    @FXML
    private Hyperlink exchangeLink;

    public void initialize() {

        exchangeBorderPane.setVisible(true);

        exchangeLink.setText("Hello, I'm Your Google Assistant, Click Me And I Will Check For You Pound Sterling To Lev Exchange Course For Today," +
                " I'am Glad To Help You.");
        exchangeLink.setMaxWidth(Double.POSITIVE_INFINITY);
        exchangeLink.setAlignment(Pos.CENTER);

        webView.setMaxWidth(Double.POSITIVE_INFINITY);
        webView.setMaxHeight(Double.POSITIVE_INFINITY);

    }

    @FXML
    public void handleExchangeLink() {

        // Access a web page using the web view controls
        WebEngine webEngine = webView.getEngine();
        webEngine.load("https://www.google.com/search?rlz=1C2CHBH_en-GBGB724GB724&sxsrf=ALeKk03-m0D4K_8bMkBLe7gR9jANnx6HkA%3A1582199416544&source=hp&ei=eHJOXo2NHsmEaYOIi6AM&q=pound+to+lev&oq=po&gs_l=psy-ab.1.1.35i39l3j0i131j0i131i67j0l2j0i131l2j0.599.752..2749...1.0..0.63.121.2......0....1..gws-wiz.......0i67.sI5r5GC43Fc");
        exchangeLink.setText("This is the best exchange rate I found for you today, I hope you are happy with the exchange rate.");

    }
}
