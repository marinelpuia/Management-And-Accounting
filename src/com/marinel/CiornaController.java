package com.marinel;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

public class CiornaController {

    @FXML
    private Label labelTitleCiorna;
    @FXML
    private TextArea textAreaCiorna;

    public void initialize() {

        labelTitleCiorna.setTextFill(Color.BLUE);
        labelTitleCiorna.setMaxWidth(Double.POSITIVE_INFINITY);
        labelTitleCiorna.setAlignment(Pos.CENTER);
        textAreaCiorna.setText("(#) - Selectează Limba Română !\n(#) - Избери български език !");
        textAreaCiorna.setMaxWidth(Double.POSITIVE_INFINITY);

    }

    @FXML
    private void ciornaRomanianLanguage() {
        labelTitleCiorna.setText("CIORNĂ");
        textAreaCiorna.setText("Deschide interfaţa CONTABILITATE LUNARĂ şi din rubrica Profit Brut şi de la rubrica Investiţie scrie mai jos valorile" +
                " aferente.\n" +
                "(#) - Profitul brut anterior     =  \n" +
                "(#) - Investiţia anterioară      =\n" +
                "(#) - Între liniile de mai jos începi să faci calculele aferente a profitului brut şi a investiţiei pentru fiecare produs în parte şi apoi" +
                " adunăle între ele ca să afli totalul lor.\n(#) - Copiază valoarea de la profitul brut anterior si iclude-o in calcul ca primă valoare" +
                " de calcul.\n(#) - Copiază valoarea de la investiţia anterioară şi iclude-o in calcul ca primă  valoare de calcul.\n" +
                "------------------------------------------------------------------------------------------------------------\n" +
                "(#) - Profitul brut total :\n" +
                "(#) - Investiţia totală    :\n" +
                "------------------------------------------------------------------------------------------------------------\n" +
                "(#) - Dupa ce ai terminat calculele, copiază aceste două valorile şi apoi înlocuieşte valorile existente din interfaţa CONTABILITATE" +
                " LUNARĂ la rubrica Profit Brut şi la rubrica Investiţie cu aceste două valori.\n" +
                "Atenţie, dacă copiezi numărul direct din interfaţa calculatorului unde ai făcut calculele , nu uita să stergi virgula dintre numere," +
                " este foarte important să introduci numarul în formatul corect mai exact doar punctul înainte de decimale. Dacă omiţi să faci treaba" +
                " asta algoritmul de calcul din interfaţa respectivă nu va putea să execute calculul deoarece ai introdus un număr în format incorect.");
    }

    @FXML
    private void ciornaBulgarianLanguage() {
        labelTitleCiorna.setText("проект");
        textAreaCiorna.setText("Отворете МЕСЕЧНИЯ СЧЕТОВОДЕН интерфейс и от секцията „Брут на печалбата“ и от секцията „Инвестиции“ напишете" +
                " стойностите по-долу.\n" +
                "(#) - Предишна брутна печалба  =\n" +
                "(#) - Предишна инвестиция         =\n" +
                "(#) - Между редовете по-долу започвате да правите изчисления на брутната печалба и инвестиции за всеки продукт и след това" +
                " добавяте заедно, за да разберете общата им стойност.\n(#) - Копирайте стойността от предишната брутна печалба и я включете в " +
                "изчислението като първа стойност на изчисление.\n(#) - Копирайте стойността от предишната инвестиция и я включете в изчислението като" +
                " първа стойност на изчисление.\n" +
                "------------------------------------------------------------------------------------------------------------\n" +
                "(#) - Обща брутна печалба :\n" +
                "(#) - Обща инвестиция        :\n" +
                "------------------------------------------------------------------------------------------------------------\n" +
                "(#) - След като завършите изчисленията, копирайте тези две стойности и след това заменете съществуващите стойности от интерфейса на" +
                " МЕСЕЧНОТО СЧЕТОВОДСТВО в секцията „Брут на печалбата“ и в секцията „Инвестиции“ с тези две стойности.\n" +
                "Внимание, ако копирате номера директно от компютърния интерфейс, където сте направили изчисленията, не забравяйте да изтриете" +
                " запетаята между числата, много е важно да въведете числото в правилния формат по-точно точно до точката преди десетичните знаци." +
                " Ако не успеете да направите това, алгоритъмът за изчисление в този интерфейс няма да може да извърши изчислението, защото сте" +
                " въвели число в грешен формат.");
    }
}
