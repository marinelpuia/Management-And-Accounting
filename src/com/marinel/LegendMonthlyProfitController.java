package com.marinel;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;

public class LegendMonthlyProfitController {

    @FXML
    private Circle redCircle;
    @FXML
    private Circle blackCircle;
    @FXML
    private Circle orangeCircle;
    @FXML
    private Circle blueCircle;
    @FXML
    private Circle greenCircle;
    @FXML
    private Circle greenLightCircle;
    @FXML
    private Label redLabel;
    @FXML
    private Label blackLabel;
    @FXML
    private Label orangeLabel;
    @FXML
    private Label blueLabel;
    @FXML
    private Label greenLabel;
    @FXML
    private Label greenLightLabel;
    @FXML
    private Label legendExplicationLabel;
    @FXML
    private TextArea textAreaLegend;

    public void initialize() {

        redCircle.setRadius(19);
        redCircle.setFill(Paint.valueOf("red"));

        blackCircle.setRadius(19);
        blackCircle.setFill(Paint.valueOf("#000000"));

        orangeCircle.setRadius(19);
        orangeCircle.setFill(Paint.valueOf("#FFA500"));

        blueCircle.setRadius(19);
        blueCircle.setFill(Paint.valueOf("blue"));

        greenCircle.setRadius(19);
        greenCircle.setFill(Paint.valueOf("green"));

        greenLightCircle.setRadius(19);
        greenLightCircle.setFill(Paint.valueOf("#25f709"));

        redLabel.setMaxWidth(Double.POSITIVE_INFINITY);
        redLabel.setText("???");
        redLabel.setAlignment(Pos.CENTER);

        blackLabel.setMaxWidth(Double.POSITIVE_INFINITY);
        blackLabel.setText("???");
        blackLabel.setAlignment(Pos.CENTER);

        orangeLabel.setMaxWidth(Double.POSITIVE_INFINITY);
        orangeLabel.setText("???");
        orangeLabel.setAlignment(Pos.CENTER);

        blueLabel.setMaxWidth(Double.POSITIVE_INFINITY);
        blueLabel.setText("???");
        blueLabel.setAlignment(Pos.CENTER);

        greenLabel.setMaxWidth(Double.POSITIVE_INFINITY);
        greenLabel.setText("???");
        greenLabel.setAlignment(Pos.CENTER);

        greenLightLabel.setMaxWidth(Double.POSITIVE_INFINITY);
        greenLightLabel.setText("???");
        greenLightLabel.setAlignment(Pos.CENTER);

        legendExplicationLabel.setMaxWidth(Double.POSITIVE_INFINITY);
        legendExplicationLabel.setText("Alege limba pentru a vedea detaliile , Изберете вашия език, за да видите подробностите.");
        legendExplicationLabel.setAlignment(Pos.CENTER);

        textAreaLegend.setText("");
        textAreaLegend.setMaxWidth(Double.POSITIVE_INFINITY);

    }


    @FXML
    public void legendRomanianLanguage() {
        redLabel.setText("Această culoare reprezintă ca eşti pe minus. Adică investiţia + cheltueli > decât profitul brut.");
        blackLabel.setText("Această culoare reprezintă un profit net = cu zero. Adică profitul brut este = cu investiţia + cheltuieli.");
        orangeLabel.setText("Această culoare reprezintă un profit net mic. Adică între 0% si 30% din profitul brut.");
        blueLabel.setText("Această culoare reprezintă un profit net medium. Adică între 30% si 60% din profitul brut.");
        greenLabel.setText("Această culoare reprezintă un profit net mare. Adică între 60% si 90% din profitul brut.");
        greenLightLabel.setText("Această culoare reprezintă un profit net foarte mare. Adică peste 90% din profitul brut.");

        legendExplicationLabel.setText("Schimbarea culorilor din rubrica profit net are la bază următoarea formulă:");

        textAreaLegend.setText("(#)  A - (B + C + D  + E + F + G  + H + I + J ) = K\n" +
                "\n" +
                "(#)  A = Profit brut\n" +
                "(#)  B = Investiţie \n" +
                "(#)  C = Chirie \n" +
                "(#)  D = Salariu \n" +
                "(#)  E = Electricitate \n" +
                "(#)  F = Încălzire \n" +
                "(#)  G = Apă \n" +
                "(#)  H = Publicitate\n" +
                "(#)  I = Sărbători\n" +
                "(#)  J = Alte Cheltuieli\n" +
                "(#)  K = Profit net\n" +
                "\n" +
                "(#)  Exemplu pentru culoarea portocaliu. Adică profit net mic, între 0% şi 30% din profitul brut:\n" +
                "(#)  100 - (20 + 7.5 + 7.5 + 7.5 + 7.5 + 7.5 + 7.5 + 7.5 + 7.5 ) = 20 \n" +
                "(#)  Acelaşi principiu se aplică pentru fiecare culoare în parte.");
    }

    @FXML
    public void legendBulgarianLanguage() {
        redLabel.setText("Този цвят означава, че сте на минуса. Тоест инвестиции + разходи > от брутната печалба.");
        blackLabel.setText("Този цвят представлява чиста печалба = нула. Тоест брутната печалба е = с инвестицията + разходи.");
        orangeLabel.setText("Този цвят представлява малка нетна печалба. Това е между 0% и 30% от брутната печалба.");
        blueLabel.setText("Този цвят представлява нетна средна печалба. Това е между 30% и 60% от брутната печалба.");
        greenLabel.setText("Този цвят представлява голяма нетна печалба. Тоест между 60% и 90% от брутната печалба.");
        greenLightLabel.setText("Този цвят представлява много висока нетна печалба. Тоест над 90% от брутната печалба.");

        legendExplicationLabel.setText("Промяната на цветовете от позицията на нетната печалба се основава на следната формула:");

        textAreaLegend.setText("(#)  A - (B + C + D + E + F + G + H + I + J) = K\n" +
                "\n" +
                "(#)  A = Брутна печалба\n" +
                "(#)  B = инвестиция\n" +
                "(#)  C = Наем\n" +
                "(#)  D = Заплата\n" +
                "(#)  E = електричество\n" +
                "(#)  F = отопление\n" +
                "(#)  G = вода\n" +
                "(#)  Н = реклама\n" +
                "(#)  I = празници\n" +
                "(#)  J = Други разходи\n" +
                "(#)  K = чиста печалба\n" +
                "\n" +
                "(#)  Пример за оранжев цвят. Тоест, малка нетна печалба, между 0% и 30% от брутната печалба:\n" +
                "(#)  100 - (20 + 7.5 + 7.5 + 7.5 + 7.5 + 7.5 + 7.5 + 7.5 + 7.5 + 7.5) = 20\n" +
                "(#)  Същият принцип важи за всеки цвят.");
    }
}
