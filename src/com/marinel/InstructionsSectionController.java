package com.marinel;

import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.paint.Color;

import java.awt.*;
import java.net.URI;

public class InstructionsSectionController {

    @FXML
    private Label labelTitleInstruction;
    @FXML
    private TextArea textAreaInstruction;


    public void initialize() {

        labelTitleInstruction.setTextFill(Color.BLUE);
        labelTitleInstruction.setMaxWidth(Double.POSITIVE_INFINITY);
        labelTitleInstruction.setAlignment(Pos.CENTER);

        textAreaInstruction.setText("(#) - Selectează Limba Română !\n(#) - Избери български език !");
        textAreaInstruction.setMaxWidth(Double.POSITIVE_INFINITY);

    }

    /* === Instructions In Romanian Language === */

    @FXML
    private void instructionsRomanianLanguage() {

        labelTitleInstruction.setText("INSTRUCȚIUNI DE UTILIZARE A PROGRAMULUI");

        textAreaInstruction.setText("- Te rog să citeşti cu atenţie toate instrucţiunile următoare deoarece sunt foarte importante. Instrucţiunile" +
                " sunt scrise în ordinea naturală a paşilor pe care tu trebuie să-i urmezi pentru a folosi cu succes acest program.\n" +
                " \n" +
                "# Paşi naturali sunt următori:\n" +
                "# -------------------------------\n" +
                " \n" +
                " (1) - Primul pas constă în adăugarea produsului achiziţionat şi toate detaliile aferente  produsului în gestiune folosind interfaţa" +
                " principală - GESTIUNE GENERALĂ ȘI CONTABILITATE DETALIATĂ - \n" +
                " (2) - Pasul doi constă în adăugarea detaliilor aferente pentru a calcula profitul general pe o perioadă de o lună şi a avea o evidenţă" +
                " a profitului lunar folosind interfaţa - CONTABILITATE LUNARĂ - \n" +
                " (3) - Pasul trei constă în adăugarea detaliilor necesare pentru a calcula profitul anual şi a avea o evidenţă a profitului anual" +
                " folosind interfaţa - CONTABILITATE ANUALĂ - \n" +
                "\n" +
                "- Pentru fiecare pas menţionat mai sus o să găseşti mai jos instrucţiuni cum să foloseşti interfaţele pentru a finaliza fiecare pas în" +
                " parte.\n" +
                "\n" +
                "# CUM SĂ FOLOSEȘTI INTERFAȚA GESTIUNE GENERALĂ ȘI CONTABILITATE DETALIATĂ\n" +
                "# ---------------------------------------------------------------------------------------------\n" +
                "\n" +
                "- În interfaţa - GESTIUNE GENERALĂ ȘI CONTABILITATE DETALIATĂ - trebuie să adaugi produsul nou achiziţionat şi toate detaliile aferente" +
                " produsului. Pentru a executa această sarcină tu trebuie să accesezi meniul şi să selectezi butonul Adaugă Produs. O nouă interfaţă se" +
                " va deschide şi va trebui să completezi detaliile aferente produsului.\n" +
                "  - Pentru a edita un produs trebuie să selectezi din listă produsul pe care vrei s-ăl editezi şi apoi să selectezi din meniu butonul" +
                " Editează Produs. O nouă interfaţă se va deschide şi va trebui să inroduci noile detalii şi apoi să apeşi pe butonul ok.\n" +
                "  - Pentru a şterge un produs trebuie să selectezi din listă produsul pe care vrei s-ăl stergi şi apoi să selectezi din meniu butonul" +
                " Șterge Produs. O nouă interfaţă se va deschide şi va trebui să confirmi actiunea de ştergere apăsând butonul ok.\n" +
                "\n" +
                "# CUM SĂ ACTUALIZEZI STOCUL ZILNIC\n" +
                "# -----------------------------------------\n" +
                "\n" +
                "- După ce ai introdus în program toate produsele care le ai pe stoc, mai exact produsele care le ai la vânzare în magazin pe rafturi" +
                " plus produsele care le ai în depozit tu va trebui să începi să actualizezi stocul.\n" +
                "\n" +
                "# EXEMPLU DE ACTUALIZARE A STOCULUI LA SFÎRȘITUL ZILEI:\n" +
                "# --------------------------------------------------------------------\n" +
                "\n" +
                "- O să folosesc în aceste instrucţiuni doar un singur produs ca exemplu pentru că operaţiunea este aceaşi indiferent câte produse ai" +
                " vândut în ziua respectivă.\n" +
                "- Să presupunem ai pe stoc 100 de tricouri Nike şi pană la sfîrşitul zilei ai vândut 5 tricouri, model A123456. Acum tu trebuie să" +
                " actualizezi stocul acestui produs executând urmatori paşi:\n" +
                "\n" +
                "# PASUL UNU:\n" +
                "# ---------- \n" +
                "\n" +
                "- Deschide programul şi în interfaţa GESTIUNE GENERALĂ ȘI CONTABILITATE DETALIATĂ caută produsul cu modelu A123456, selctează-l şi apoi" +
                " din meniu selectează Editează Produs. O nouă interfaţă se va deschide şi va trebui să inroduci noile detalii în rubrica Stoc Actual" +
                " şi apoi să apeşi pe butonul ok.\n" +
                "\n" +
                "# EXEMPLU PENTRU PASUL UNU:\n" +
                "# -------------------------------------\n" +
                " \n" +
                "- În nouă interfaţă care se va deschide, la rubrica Cantitate o să ai numărul 100 iar la rubrica Stoc Actual acelaşi numărul 100, va" +
                " trebui să sustragi produsele vândute din rubrica Cantitate şi să actualizezi rubrica Stoc Actual cu deferenta, adică dacă ai vandut" +
                " 5 tricouri va trebui să introduci în rubrica Stoc Actual cifra 95. A doua zi dacă mai vinzi înca 5 tricouri repeţi acaestă operaţiune" +
                " şi actualizezi rubrica Stoc Actual de la 95 la 90 pentru că ai mai vandut 5 tricouri şi aşa mai departe de fiecare dată când vinzii" +
                " acest produs până ajungi la cifra 0 în rubrica Stoc Actual , mai exact ai vândut toate cele 100 de tricouri care le aveai pe stoc." +
                " Operaţiunea asta trebuia să o faci pentru fiecare produs în parte în fiecare zi, pentru că o să vinzi produse diversificate zilnic.\n" +
                "\n" +
                "# PASUL DOI: CUM SĂ FOLOSEȘTI INTERFAȚA CONTABILITATE LUNARĂ\n" +
                "# ------------------------------------------------------------------------------\n" +
                " \n" +
                "- Dupa ce actualizezi stocul produsului vândut, înainte de a deschide interfaţa CONTABILITATE LUNARĂ trebuie să mai faci doua calcule" +
                " adiţionale pentru a obţine profitul brut si investiţia facută pe produsul respectiv. Deschide interfaţa Ciornă din meniu şi urmează" +
                " instrucţiunile. Rezultatul acestor două calcule tu trebuie să le introduci în interfaţa CONTABILITATE LUNARĂ.\n" +
                "\n" +
                "# EXEMPLU PASUL DOI:\n" +
                "# ---------------------------\n" +
                " \n" +
                "- Produsul vândut este tricou Nike, model A123456 şi ai vândut 5 tricouri. Va trebuia să verifici preţul de vânzare al produsului în" +
                " interfaţa GESTIUNE GENERALĂ ȘI CONTABILITATE DETALIATĂ în cazul de faţă preţul de vânzare al produsului este de 35 de lev.\n" +
                " \n" +
                "- Pentru a afla profitul brut înmulţesti numarul de produse vândute cu preţul de vânzare mai exact 5 * 35 = 175, rezultatul trebuie" +
                " salvat pentru că va trebui s-ăl introduci în interfaţa CONTABILITATE LUNARĂ la rubrica Profit Brut. \n" +
                "\n" +
                "- Apoi trebuie să verifici preţul de achiziţie al produsului din interfaţa GESTIUNE GENERALĂ ȘI CONTABILITATE DETALIATĂ la rubrica" +
                " Preţul Produsului, în cazul nostru preţul de achiziţie al produsului este 17.50 lev.\n" +
                " \n" +
                "- Pentru a afla investiţia înmulteşti numărul de produse vândute cu Preţul Produsului mai exact 5 * 17.50 = 87.5, rezultatul trebuie" +
                " salvat pentru că va trebui s-ăl introduci în interfaţa CONTABILITATE LUNARĂ la rubrica Investiţie.\n" +
                " \n" +
                "Repeţi aceste două operatiuni şi pentru al doilea produs vândut şi aşa mai departe dacă ai mai multe produse vândute în ziua" +
                " respectiva.\n" +
                "\n" +
                "# EXEMPLU ÎN CARE AI MAI MULTE PRODUSE VÂNDUTE IN ZIUA RESPECTIVĂ\n" +
                "# -----------------------------------------------------------------------------------\n" +
                "\n" +
                "- Produs 1\n" +
                " Calculează profitul brut: 5 * 35 = 175\n" +
                " Calculează investiţia: 5 * 17.50 = 87.5\n" +
                "\n" +
                "- Produs 2\n" +
                " Calculează profitul brut: 5 * 30 = 150\n" +
                " Calculează investiţia: 5 * 15 = 75\n" +
                "\n" +
                "- Suma totală a profitului brut pentru ambele produse: 175 + 150 = 325 ( 325 este suma care trebuie introdusă în rubrica Profit Brut în" +
                " interfaţa CONTABILITATE LUNARĂ )\n" +
                "- Suma totală a investiţie pentru ambele produse: 87.5 + 75 = 162.5 ( 162.5 este suma care trebuie introdusă în rubrica Investiţie în" +
                " interfaţa CONTABILITATE LUNARĂ )\n" +
                "\n" +
                "- Repeţi aceste calcule pentru fiecare produs vândut in ziua respectivă după actualizarea stocului, pentru a afla valorile profitului" +
                " brut şi a invetiţiei, valori care trebuie sa le introduci in interfaţa CONTABILITATE LUNARĂ la rubricile aferente.\n" +
                "\n" +
                "# PASUL TREI:\n" +
                "# -----------------\n" +
                " \n" +
                "- Deschizi interfaţa CONTABILITATE LUNARĂ şi începi să introduci datele aferente.\n" +
                "\n" +
                "# EXEMPLU PASUL TREI:\n" +
                "# -------------------------\n" +
                " \n" +
                "- Introduci luna, apoi anul, apoi Profit Brut (aici  trebuie să introduci suma totală a profitului brut pentru ambele produse: 325 în" +
                " cazul de faţă), apoi Investiţia (aici  trebuie să introduci suma totală a investiţie pentru ambele produse: 162.5 în cazul de faţă), apoi Chirie, Salariu şi aşa mai departe.\n" +
                "\n" +
                "- Repetă aceşti trei paşi în fiecare zi, pentru a actualiza stocul şi a actualiza profitul lunar.\n" +
                "\n" +
                "- Atenţie, în ziua următoare când tu o să actualizezi stocul şi o să ajungi la pasul trei în rubrica Profit Brut şi în rubrica" +
                " Investiţie o să existe deja valori de la ziua anterioară. Aceste valori trebuie să le aduni cu valorile din ziua a doua şi aşa mai" +
                " departe pentru ziua a treia, pentru ziua a patra până se termina luna.  \n" +
                "\n" +
                "# PASUL PATRU: CUM SĂ FOLOSEȘTI INTERFAȚA CONTABILITATE ANUALĂ\n" +
                "# ------------------------------------------------------------------------------\n" +
                " \n" +
                "- Deschizi interfaţa CONTABILITATE ANUALĂ şi începi să introduci datele aferente.\n" +
                "\n" +
                "# EXEMPLU PASUL PATRU: \n" +
                "# ---------------------------\n" +
                "\n" +
                "- Această operaţiune o faci doar o singură dată pe an după ce plăteşti impozitul pe profit adică la inchiderea anului financiar.\n" +
                " \n" +
                "- Deschizi interfaţa CONTABILITATE LUNARĂ şi aduni valorile din rubrica Profit Net ( LEV ) pentru fiecare lună in parte din anul" +
                " respectiv, apoi rezultatul îl introduci in interfaţa CONTABILITATE ANUALĂ la rubrica Profit Brut Inainte De Taxe ( LEV ), apoi introduci impozitul pe profit plătit pe anul respectiv pentru a actualiza rubrica Profit Net Dupa Inpozit ( LEV ).\n" +
                " \n" +
                "- Repetă pasul patru în fiecare an după ce plateşti inpozitul pe profit pentru a actualiza profitul anual.\n" +
                "\n" +
                "- Aceştea sunt paşi care trebuie s-ăi urmezi la sfarşitul zilei pentru a fi zilnic şi lunar cu stocul actualizat şi lunar cu profitul" +
                " actualizat, şi pasul patru o dată pe an pentru a actualiza profitul anual. Pentru a viziona o demonstraţie live te rog selectează" +
                " din meniu opţiunea Video (Monthly profit) sau Video (Year profit) după caz. ");

    }

    /* === Instructions In Bulgarian Language === */

    @FXML
    private  void instructionsBulgarianLanguage() {

        labelTitleInstruction.setText("УКАЗАНИЯ ЗА ИЗПОЛЗВАНЕ НА ПРОГРАМАТА");

        textAreaInstruction.setText("- моля, прочетете внимателно всички следващи инструкции, защото те са много важни. Инструкциите са написани" +
                " в естествения ред на стъпките, които трябва да следвате, за да използвате успешно тази програма.\n" +
                " \n" +
                "# Естествените стъпки са следните:\n" +
                "# -------------------------------------------\n" +
                " \n" +
                " (1) - Първата стъпка е да добавите закупения продукт и всички подробности, свързани с продукта, който се управлява, като използвате" +
                " основния интерфейс - ОБЩО УПРАВЛЕНИЕ И ПОДРОБНО СЧЕТОВОДСТВО -\n" +
                " (2) - Втората стъпка се състои в добавяне на съответните подробности за изчисляване на общата печалба за период от един месец и запис" +
                " на месечната печалба чрез интерфейса - МЕСЕЧНО СЧЕТОВОДСТВО -\n" +
                " (3) - Третата стъпка се състои в добавяне на необходимите подробности, за да се изчисли годишната печалба и да има годишен запис на" +
                " печалбата, използвайки интерфейса - ГОДИШЕН СЧЕТОВОДСТВО -\n" +
                "\n" +
                "- За всяка посочена по-горе стъпка ще намерите по-долу инструкции как да използвате интерфейсите, за да завършите всяка стъпка" +
                " поотделно.\n" +
                "\n" +
                "# КАК ДА ИЗПОЛЗВАТЕ ИНТЕРФЕЙС ЗА ОБЩО УПРАВЛЕНИЕ И ПОДРОБНО СЧЕТОВОДСТВО\n" +
                "# ----------------------------------------------------------------------------------------------------------\n" +
                "\n" +
                "- В интерфейса - ОБЩО УПРАВЛЕНИЕ И ПОДРОБНО Счетоводство - трябва да добавите новозакупения продукт и всички подробности, свързани" +
                " с продукта. За да изпълните тази задача, трябва да влезете в менюто и да изберете бутона Добавяне на продукт. Ще се отвори нов" +
                " интерфейс и ще трябва да попълните подробностите за продукта.\n" +
                "  - За да редактирате продукт, трябва да изберете от списъка продукта, който искате да редактирате, и след това от менюто да изберете" +
                " бутона Редактиране на продукт. Ще се отвори нов интерфейс и ще трябва да въведете новите детайли и след това да натиснете бутона ОК.\n" +
                "  - За да изтриете продукт, трябва да изберете продукта, който искате да изтриете, от списъка и след това да изберете бутона Изтрий" +
                " продукта от менюто. Ще се отвори нов интерфейс и ще трябва да потвърдите действието за изтриване, като натиснете бутона OK.\n" +
                "\n" +
                "# КАК ДА ОБНОВНЕТЕ ДНЕВНИЯТ СЪД\n" +
                "# ---------------------------------------\n" +
                "\n" +
                "- След като представите всички продукти, които имате на склад, по-точно продуктите, които имате за продажба в магазина на рафтове" +
                " плюс продуктите, които имате в магазина си, ще трябва да започнете да актуализирате запасите.\n" +
                "\n" +
                "# ПРИМЕР ЗА актуализиране на запаса в края на деня:\n" +
                "# ------------------------------------------------------------------\n" +
                "\n" +
                "- Ще използвам само един продукт в тези инструкции като пример, тъй като операцията е една и съща, независимо колко продукта сте" +
                " продали през този ден.\n" +
                "- Да предположим, че имате 100 налични тениски на Nike и до края на деня сте продали 5 тениски, модел A123456. Сега трябва да" +
                " актуализирате наличността на този продукт, като изпълните следните стъпки:\n" +
                "\n" +
                "# СТЪПКА 1:\n" +
                "# ----------\n" +
                "\n" +
                "- Отворете програмата и в интерфейса за ОБЩО УПРАВЛЕНИЕ И ПОДРОБНО Счетоводство потърсете продукта с модел A123456, изберете го и" +
                " след това изберете Редактиране на продукта от менюто. Ще се отвори нов интерфейс и ще трябва да въведете новите детайли в полето" +
                " Current Stock и след това да натиснете бутона OK.\n" +
                "\n" +
                "# ПРИМЕР ЗА СТЪПКА 1:\n" +
                "# ---------------------------\n" +
                " \n" +
                "- В новия интерфейс, който ще се отвори, в секцията Количество ще имате числото 100, а в секцията Текуща наличност същото число 100," +
                " ще трябва да извадите продуктите, продадени от секцията Количество, и да актуализирате артикула на акцията със стойността, т.е. ако" +
                " сте продали 5 ризи ще трябва да въведете в полето Действителен номер на стока 95. На следващия ден, ако продадете още 5 ризи," +
                " повторете тази операция и актуализирайте кутията Актуални запаси от 95 до 90, защото сте продали 5 ризи и така нататък всеки път," +
                " когато продавате това продукт, докато не достигнете числото 0 в полето Актуални запаси, по-точно сте продали всичките 100 ризи," +
                " които сте имали на склад. Тази операция трябваше да се извършва за всеки конкретен продукт всеки ден, защото вие ще продавате" +
                " разнообразни продукти всеки ден.\n" +
                "\n" +
                "# СТЪПКА ВТОРА: КАК ДА ИЗПОЛЗВАТЕ МЕСЕЧНИЯ СЧЕТОВОДЕН ИНТЕРФЕЙС\n" +
                "# --------------------------------------------------------------------------------------\n" +
                " \n" +
                "- След актуализиране на запасите от продадения продукт, преди да отворите МЕСЕЧНИЯ СЧЕТОВОДЕН интерфейс, трябва да направите две" +
                " допълнителни изчисления, за да получите брутната печалба и инвестицията, направена за съответния продукт. Отворете интерфейса" +
                " Ciornă от менюто и следвайте инструкциите. Резултатът от тези две изчисления трябва да въведете в МЕСЕЧЕН СЧЕТОВОДЕН интерфейс.\n" +
                "\n" +
                "# ПРИМЕР СТЪПКА ВТОРИ:\n" +
                "# ------------------------------\n" +
                " \n" +
                "- Продуктът, който се продава е риза Nike, модел A123456 и сте продали 5 ризи. Ще трябва да проверите продажната цена на продукта в" +
                " интерфейса ОБЩО УПРАВЛЕНИЕ И ПОДРОБНО СЧЕТОВОДСТВО в този случай продажната цена на продукта е 35 лева.\n" +
                " \n" +
                "- За да намерите брутната печалба, умножете броя на продадените продукти с продажната цена по-точно 5 * 35 = 175, резултатът трябва" +
                " да бъде запазен, защото ще трябва да го въведете в МЕСЕЧНИЯ СЧЕТОВОДЕН интерфейс в секцията Брутна печалба.\n" +
                "\n" +
                "- След това трябва да проверите изкупната цена на продукта от интерфейса за ОБЩО УПРАВЛЕНИЕ И ПОДРОБНО СЧЕТОВОДСТВО в раздела Цена" +
                " на продукта, в нашия случай покупната цена на продукта е 17,50 лева.\n" +
                " \n" +
                "- За да разберете инвестицията, умножете броя продадени продукти с цената на продукта по-точно 5 * 17.50 = 87.5, резултатът трябва" +
                " да бъде запазен, тъй като ще трябва да го въведете в МЕСЕЧНИЯ СЧЕТОВОДЕН интерфейс под заглавието „Инвестиции“.\n" +
                " \n" +
                "Повторете тези две операции за втория продаден продукт и така нататък, ако имате повече продадени продукти през този ден.\n" +
                "\n" +
                "# ПРИМЕР, КОЙТО ИМАТЕ ПОВЕЧЕ ПРОДУКТИ, ПРОДАДЕНИ НА ТОВА ДЕН\n" +
                "# ---------------------------------------------------------------------------------------\n" +
                "\n" +
                "- Продукт 1\n" +
                " Изчислете брутната печалба: 5 * 35 = 175\n" +
                " Изчислете инвестицията: 5 * 17.50 = 87.5\n" +
                "\n" +
                "- Продукт 2\n" +
                " Изчислява брутна печалба: 5 * 30 = 150\n" +
                " Изчислете инвестицията: 5 * 15 = 75\n" +
                "\n" +
                "- Общата сума на брутната печалба и за двата продукта: 175 + 150 = 325 (325 е сумата, която трябва да бъде въведена в секцията" +
                " „Брутна печалба“ в интерфейса на МЕСЕЧНОТО СЧЕТОВОДСТВО)\n" +
                "- Общият размер на инвестицията и за двата продукта: 87,5 + 75 = 162,5 (162,5 е сумата, която трябва да бъде въведена в заглавието" +
                " „Инвестиции“ в интерфейса на МЕСЕЧНОТО СЧЕТОВОДСТВО)\n" +
                "\n" +
                "- Повторете тези изчисления за всеки продукт, продаден в този ден след актуализацията на запасите, за да разберете брутната печалба" +
                " и стойностите на инвестициите, стойности, които трябва да въведете в МЕСЕЧЕН СЧЕТОВОДЕН интерфейс в съответните полета.\n" +
                "\n" +
                "# ТРИ СТЪПКА:\n" +
                "# --------------------\n" +
                " \n" +
                "- Отворете МЕСЕЧНИЯ СЧЕТОВОДЕН интерфейс и започнете да въвеждате съответните данни.\n" +
                "\n" +
                "# ПРИМЕР ТРЕТАТА СТЪПКА:\n" +
                "# --------------------------------\n" +
                " \n" +
                "- Въведете месеца, после годината, след това брутната печалба (тук трябва да въведете общата брутна печалба и за двата продукта:" +
                " 325 в този случай), след това инвестицията (тук трябва да въведете общата сума на инвестицията и за двата продукта: 162.5 в случай на отпред), след това под наем, заплата и т.н.\n" +
                "\n" +
                "- Повторете тези три стъпки всеки ден, за да актуализирате запасите си и да актуализирате месечната си печалба.\n" +
                "\n" +
                "- Внимание, на следващия ден, когато ще актуализирате акциите и ще достигнете стъпка трета в секцията „Брут на печалбата и" +
                " инвестициите“, вече ще има стойности от предходния ден. Тези стойности трябва да се комбинират със стойностите на втория ден и така нататък за третия ден, за четвъртия ден до края на месеца.\n" +
                "\n" +
                "# СТЪПКА 4: КАК ДА ИЗПОЛЗВАТЕ ГОДИШНИЯ СЧЕТОВОДЕН ИНТЕРФЕЙС\n" +
                "# -----------------------------------------------------------------------------------\n" +
                " \n" +
                "- Отворете интерфейса на годишния счетоводство и започнете да въвеждате съответните данни.\n" +
                "\n" +
                "# ПРИМЕР СТЪПКА 4:\n" +
                "# --------------------------\n" +
                "\n" +
                "- Тази операция се извършва само веднъж годишно, след като платите данъка върху печалбата, т.е. в края на финансовата година.\n" +
                " \n" +
                "- Отворете МЕСЕЧНИЯ СЧЕТОВОДЕН интерфейс и съберете стойностите от нетната печалба (LEV) за всеки месец през дадена година, след" +
                " това въведете резултата в интерфейса ГОДИШЕН СЧЕТОВОДНИК в раздела Брутна печалба преди данъци (LEV), след което въведете данъка върху печалбата, изплатена на същата година, за да актуализирате заглавието Нетна печалба след данък (LEV).\n" +
                " \n" +
                "- Повторете стъпка четири всяка година, след като платите данъка си върху дохода, за да актуализирате годишната си печалба.\n" +
                "\n" +
                "- Това са стъпки, които трябва да следвате в края на деня, за да бъдете ежедневно и месечно с актуализирани запаси и месечно с" +
                " актуализирана печалба, и стъпка четири веднъж годишно, за да актуализирате годишната печалба. За да гледате демонстрация на живо," +
                " изберете Video (Monthly profit) или Video (Year profit) от менюто, както е подходящо.");
    }

    /* === OPEN THE VIDEO LINK FOR UPLOAD PRODUCTS=== */
    @FXML
    public void previewVideoUploadProduct() {

        String link = "https://drive.google.com/open?id=19yrt1ST66VxYpHSmZEdk-eK0K-9vp_76";

        try {
            URI uri = new URI(link);
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* === OPEN THE VIDEO LINK FOR CLONE PRODUCTS=== */
    @FXML
    public void previewVideoCloneProduct() {

        String link = "https://drive.google.com/open?id=1Jfr1c5vmM9xPJ5jFV_scw_MW80tV3p04";

        try {
            URI uri = new URI(link);
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* === OPEN THE VIDEO LINK FOR MONTHLY PROFIT=== */
    @FXML
    public void previewVideoMonthlyProfit() {

        String link = "https://www.google.com/";

        try {
            URI uri = new URI(link);
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /* === OPEN THE VIDEO LINK FOR YEAR PROFIT=== */
    @FXML
    public void previewVideoYearProfit() {

        String link = "https://drive.google.com/open?id=1LNUNaNEdynKIPXuTOQkIx2OaqoWA_lWs";

        try {
            URI uri = new URI(link);
            Desktop desktop = Desktop.getDesktop();
            desktop.browse(uri);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
