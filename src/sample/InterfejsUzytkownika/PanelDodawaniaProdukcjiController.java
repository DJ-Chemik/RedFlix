
package sample.InterfejsUzytkownika;

import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import org.omg.CORBA.DATA_CONVERSION;
import sample.DaneTekstoweWListach.BazaAllTexts;
import sample.Main;
import sample.StricteBazaProdukcji.Film;
import sample.StricteBazaProdukcji.Live;
import sample.StricteBazaProdukcji.Produkcja;
import sample.StricteBazaProdukcji.Serial;
import sample.Symulacja.Dystrybutor;
import sample.Symulacja.Promocja;
import sample.TypyDanychPomocnicznych.Aktor;
import sample.TypyDanychPomocnicznych.Data;

import java.util.ArrayList;
import java.util.Random;

public class PanelDodawaniaProdukcjiController extends Panel {



    @FXML
    private TextField textTytul;
    @FXML
    private TextField textAdresDoZdjecia;
    @FXML
    private TextArea textOpis;
    @FXML
    private TextField textCzasTrwania;
    @FXML
    private TextField textCena;
    @FXML
    private TextField textOcenaUzytkownikow;
    @FXML
    private TextField textCzasNaObejrzenie;
    @FXML
    private TextField textIloscSezonow;
    @FXML
    private TextField textDataEmisjiDzien;
    @FXML
    private TextField textDataEmisjiMiesiac;
    @FXML
    private TextField textDataEmisjiRok;
    @FXML
    private TextField textDataPromocjiPoczatekDzien;
    @FXML
    private TextField textDataPromocjiPoczatekMiesiac;
    @FXML
    private TextField textDataPromocjiPoczatekRok;
    @FXML
    private TextField textDataPromocjiKoniecDzien;
    @FXML
    private TextField textDataPromocjiKoniecMiesiac;
    @FXML
    private TextField textDataPromocjiKoniecRok;
    @FXML
    private TextField textWysokoscPromocji;


    @FXML
    private ComboBox<String> comboBoxKrajProdukcji;
    @FXML
    private ComboBox<String> comboBoxWyborTypuProdukcji;
    @FXML
    private ComboBox<String> comboBoxWyborGatunku;
    @FXML
    private ComboBox<String> comboBoxLinkDoZwiastunu;
    @FXML
    private ComboBox<String> comboBoxWyborDystrybutora;
    @FXML
    private ComboBox<String> comboBoxAktorzy;

    @FXML
    private Button buttonDodajLink;
    @FXML
    private Button buttonUsunLink;
    @FXML
    private Button buttonDodajAktorow;
    @FXML
    private Button buttonUsunAktora;
    @FXML
    private Button buttonDodajProdukcje;


    @FXML
    private Label labelGatunek;
    @FXML
    private Label labelLinkDoZwiastunow;
    @FXML
    private Label labelCzasNaObejrzenie;
    @FXML
    private Label labelIloscSezonow;
    @FXML
    private Label labelDataEmisji;
    @FXML
    private Label labelAktor;
    @FXML
    private Label labelDataPromocjiPoczatek;
    @FXML
    private Label labelDataPromocjiKoniec;
    @FXML
    private Label labelWysokoscPromocji;


    private ArrayList<Aktor> listaAktorowObjektow = new ArrayList<>();


    /**
     * Dzięki tej zmiennej jeśli opuscimy Panel Dodawania Produkcji i udamy się do Panelu wyboru aktorów, to nie
     * stracimy tego co już wprowadziliśmy :)
     */
    static boolean czyZachowacDaneWszystkichPolPoOpuszczeniuOkna = false;

    static public void setCzyZachowacDaneWszystkichPolPoOpuszczeniuOkna(boolean czyZachowac) {
        czyZachowacDaneWszystkichPolPoOpuszczeniuOkna = czyZachowac;
    }

    @FXML
    public void initialize() {
        pobranieDanychZPuliDanychDlaKontrolerow(); //wykorzystywane żeby zachowywać wartości, ale niepotrzebne gdy pola są losowane - można będzie użyć gdy się zrobi żeby w pewnych sytuacjach zachowywało dane



        if(!czyZachowacDaneWszystkichPolPoOpuszczeniuOkna) {
            losujWartosciWszystkichPol();
        }


    }


    ////////////////////////////////////////////////////////////////////////////////////////
    /*Metody do przechodzenia między oknami*/                  /////////////////////////////
    ////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    @Override
    public void openPanelGlowny(){
        super.openPanelGlowny();
        czyZachowacDaneWszystkichPolPoOpuszczeniuOkna = false;
    }

    @FXML
    public void openPanelAdministratora() {
        czyZachowacDaneWszystkichPolPoOpuszczeniuOkna = false;
        openPanel("PlikiFXML/PanelAdministratora.fxml");

    }

    @FXML
    public void openPanelWyboruAktorow() {
        czyZachowacDaneWszystkichPolPoOpuszczeniuOkna = true;
        zrzutPamieciDoPuliDanychDlaKontrolerow();
        openPanel("PlikiFXML/PanelWyboruAktorow.fxml");
    }

    /////////////////////////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////////////////////////

    /////////////////////////////////////////////////////////////////////////////////////////

    @FXML
    private void wybranieAktoraZComboBoxa(){
        //buttonUsunAktora.setDisable(false);

    }

    @FXML
    private void usunAktoraZComboBoxa(){
        for (int i=0;i<comboBoxAktorzy.getItems().size();i++){
            if (comboBoxAktorzy.getSelectionModel().getSelectedIndex()==i){
                comboBoxAktorzy.getItems().remove(i);
                listaAktorowObjektow.remove(i);
            }

        }
    }

    private void losujNowyElementListyKrajow(){
        Random generator = new Random();
        String jedenKrajProdukcji = BazaAllTexts.kraje.getListaKrajow().get(
                generator.nextInt(BazaAllTexts.kraje.getListaKrajow().size()));
        comboBoxKrajProdukcji.setValue(jedenKrajProdukcji);
    }

    private void losujNowyElementListyLinkow(){
        Random generator = new Random();
        String linkDoZwiastunu = BazaAllTexts.linkiDoZwiastunow.getListaLinkow().get(
                generator.nextInt(BazaAllTexts.linkiDoZwiastunow.getListaLinkow().size()));
        comboBoxLinkDoZwiastunu.setValue(linkDoZwiastunu);
    }

    private void losujDatyPromocji() {
        int rok2 = Data.losujRok(2018,2020);
        int rok3 = Data.losujRok(2018,2020);
        int miesiac2 = Data.losujMiesiac();
        int miesiac3 = Data.losujMiesiac();
        if(rok3>rok2){
            textDataPromocjiPoczatekRok.setText(Integer.toString(rok2));
            textDataPromocjiKoniecRok.setText(Integer.toString(rok3));
            textDataPromocjiPoczatekMiesiac.setText(Integer.toString(miesiac2));
            textDataPromocjiKoniecMiesiac.setText(Integer.toString(miesiac3));
        }else{
            textDataPromocjiPoczatekRok.setText(Integer.toString(rok3));
            textDataPromocjiKoniecRok.setText(Integer.toString(rok2));
            if (rok3==rok2){
                if (miesiac3>miesiac2){
                    textDataPromocjiPoczatekMiesiac.setText(Integer.toString(miesiac2));
                    textDataPromocjiKoniecMiesiac.setText(Integer.toString(miesiac3));
                }else{
                    textDataPromocjiPoczatekMiesiac.setText(Integer.toString(miesiac3));
                    textDataPromocjiKoniecMiesiac.setText(Integer.toString(miesiac2));
                }
            }else{ //jak rok3<rok2 kolejność miesiecy nie ma znaczenia
                textDataPromocjiPoczatekMiesiac.setText(Integer.toString(miesiac2));
                textDataPromocjiKoniecMiesiac.setText(Integer.toString(miesiac3));
            }
        }
        //losuje dni dla kwietnia - do trzydziestu - przy lutym może wypaść np 30 luty ale to się poprawi
        //kiedy indziej
        int dzien2;
        int dzien3;
        do {
            dzien2 = Data.losujDzien(4);
            dzien3 = Data.losujDzien(4);
        }while(dzien2==dzien3);
        if (dzien2<dzien3) {
            textDataPromocjiPoczatekDzien.setText(Integer.toString(dzien2));
            textDataPromocjiKoniecDzien.setText(Integer.toString(dzien3));
        }else {
            textDataPromocjiPoczatekDzien.setText(Integer.toString(dzien3));
            textDataPromocjiKoniecDzien.setText(Integer.toString(dzien2));
        }
    }

    /**
     * Zapisuje dane z ComboBoxów w tymczasowej klasie statycznej, aby nie znikały gdy przechodzimy między oknami.
     * Potrzebne było kiedyś, ale obecnie wszystkie dane są losowane, więc metoda ta nie ma praktycznego zastosowania.
     */
    private void zrzutPamieciDoPuliDanychDlaKontrolerow() {
        DaneDlaKontrolerow.krajeProdukcji.clear();
        DaneDlaKontrolerow.krajeProdukcji.addAll(comboBoxKrajProdukcji.getItems());
        DaneDlaKontrolerow.linkiDoZwiastunow.clear();
        DaneDlaKontrolerow.linkiDoZwiastunow.addAll(comboBoxLinkDoZwiastunu.getItems());
        DaneDlaKontrolerow.listaAktorow.clear();
        DaneDlaKontrolerow.listaAktorow.addAll(listaAktorowObjektow);  //TU MOŻNA SZUKAC PROBLEMU W PRZYSZLOSCI


        DaneDlaKontrolerow.tytul = this.textTytul.getText();
        DaneDlaKontrolerow.adresDoZdjecia = this.textAdresDoZdjecia.getText();
        DaneDlaKontrolerow.opis = this.textOpis.getText();
        DaneDlaKontrolerow.czasTrwania=Integer.parseInt(this.textCzasTrwania.getText());
        DaneDlaKontrolerow.cena=Float.parseFloat(this.textCena.getText());
        DaneDlaKontrolerow.ocenaUzytkownikow=Float.parseFloat(this.textOcenaUzytkownikow.getText());

        DaneDlaKontrolerow.indeksTypuProdukcji=comboBoxWyborTypuProdukcji.getSelectionModel().getSelectedIndex();
        DaneDlaKontrolerow.indeksDystrybutoraPrzyDodawaniuProdukcji=comboBoxWyborDystrybutora.getSelectionModel().getSelectedIndex();
        DaneDlaKontrolerow.czasNaObejrzenie=Integer.parseInt(this.textCzasNaObejrzenie.getText());
        DaneDlaKontrolerow.indeksGatunku=this.comboBoxWyborGatunku.getSelectionModel().getSelectedIndex();
        DaneDlaKontrolerow.czasNaObejrzenie=Integer.parseInt(this.textCzasNaObejrzenie.getText());
        DaneDlaKontrolerow.iloscSezonow=Integer.parseInt(this.textIloscSezonow.getText());
        DaneDlaKontrolerow.dzien[0]=(this.textDataEmisjiMiesiac.getText());
        DaneDlaKontrolerow.dzien[1]=(this.textDataPromocjiPoczatekDzien.getText());
        DaneDlaKontrolerow.dzien[2]=(this.textDataPromocjiKoniecMiesiac.getText());
        DaneDlaKontrolerow.miesiac[0]=(this.textDataEmisjiMiesiac.getText());
        DaneDlaKontrolerow.miesiac[1]=(this.textDataPromocjiPoczatekMiesiac.getText());
        DaneDlaKontrolerow.miesiac[2]=(this.textDataPromocjiKoniecMiesiac.getText());
        DaneDlaKontrolerow.rok[0]=(this.textDataEmisjiRok.getText());
        DaneDlaKontrolerow.rok[1]=(this.textDataPromocjiPoczatekRok.getText());
        DaneDlaKontrolerow.rok[2]=(this.textDataPromocjiKoniecRok.getText());
        DaneDlaKontrolerow.wysokoscProcentowaPromocji=Integer.parseInt(textWysokoscPromocji.getText());


    }

    /**
     * Pobiera dane ze statycznej klasy która przechowuje tymczasowe wartosci.
     * Potrzebne było kiedyś, ale obecnie wszystkie dane są losowane, więc metoda ta nie ma praktycznego zastosowania.
     */
    public void pobranieDanychZPuliDanychDlaKontrolerow(){
        this.comboBoxWyborTypuProdukcji.getItems().setAll(DaneDlaKontrolerow.typyProdukcji); //dodaje pola do comboBoxa z wyborem typu produckji
        this.comboBoxWyborGatunku.getItems().setAll(DaneDlaKontrolerow.gatunkiProdukcji);
        zaladowanieComboBoxaDystrybutora();


        this.textTytul.setText(DaneDlaKontrolerow.tytul);
        this.textAdresDoZdjecia.setText(DaneDlaKontrolerow.adresDoZdjecia);
        this.textOpis.setText(DaneDlaKontrolerow.opis);
        this.textCzasTrwania.setText(Integer.toString(DaneDlaKontrolerow.czasTrwania));
        this.textCena.setText(Float.toString(DaneDlaKontrolerow.cena));
        this.textOcenaUzytkownikow.setText(Float.toString(DaneDlaKontrolerow.ocenaUzytkownikow));
        this.comboBoxKrajProdukcji.getItems().setAll(DaneDlaKontrolerow.krajeProdukcji);


        this.comboBoxWyborTypuProdukcji.getSelectionModel().select(DaneDlaKontrolerow.indeksTypuProdukcji);
        deaktywacjaPolNiewlasciwychDlaDanegoTypuProdukcji();
        this.comboBoxWyborDystrybutora.getSelectionModel().select(DaneDlaKontrolerow.indeksDystrybutoraPrzyDodawaniuProdukcji);
        this.comboBoxLinkDoZwiastunu.getItems().setAll(DaneDlaKontrolerow.linkiDoZwiastunow);
        this.textCzasNaObejrzenie.setText(Integer.toString(DaneDlaKontrolerow.czasNaObejrzenie));
        for (int i=0;i<DaneDlaKontrolerow.listaAktorow.size();i++){

            this.comboBoxAktorzy.getItems().add(DaneDlaKontrolerow.listaAktorow.get(i).toString());
            this.listaAktorowObjektow.add(DaneDlaKontrolerow.listaAktorow.get(i));

        }
        this.comboBoxWyborGatunku.getSelectionModel().select(DaneDlaKontrolerow.indeksGatunku);
        this.textIloscSezonow.setText(Integer.toString(DaneDlaKontrolerow.iloscSezonow));
        this.textDataEmisjiDzien.setText(DaneDlaKontrolerow.dzien[0]);
        this.textDataEmisjiMiesiac.setText(DaneDlaKontrolerow.miesiac[0]);
        this.textDataEmisjiRok.setText(DaneDlaKontrolerow.rok[0]);
        this.textDataPromocjiPoczatekDzien.setText(DaneDlaKontrolerow.dzien[1]);
        this.textDataPromocjiPoczatekMiesiac.setText(DaneDlaKontrolerow.miesiac[1]);
        this.textDataPromocjiPoczatekRok.setText(DaneDlaKontrolerow.rok[1]);
        this.textDataPromocjiKoniecDzien.setText(DaneDlaKontrolerow.dzien[2]);
        this.textDataPromocjiKoniecMiesiac.setText(DaneDlaKontrolerow.miesiac[2]);
        this.textDataPromocjiKoniecRok.setText(DaneDlaKontrolerow.rok[2]);
        this.textWysokoscPromocji.setText(Integer.toString(DaneDlaKontrolerow.wysokoscProcentowaPromocji));
    }


    private void deaktywacjaPolNiewlasciwychDlaDanegoTypuProdukcji(){

        labelLinkDoZwiastunow.setVisible(false);
        labelAktor.setVisible(false);
        labelCzasNaObejrzenie.setVisible(false);
        labelGatunek.setVisible(false);
        labelIloscSezonow.setVisible(false);
        labelDataEmisji.setVisible(false);
        labelDataPromocjiPoczatek.setVisible(false);
        labelDataPromocjiKoniec.setVisible(false);
        labelWysokoscPromocji.setVisible(false);


        comboBoxWyborGatunku.setVisible(false);
        comboBoxAktorzy.setVisible(false);
        comboBoxLinkDoZwiastunu.setVisible(false);
        buttonDodajLink.setVisible(false);
        buttonUsunLink.setVisible(false);
        buttonDodajAktorow.setVisible(false);
        buttonUsunAktora.setVisible(false);

        textCzasNaObejrzenie.setVisible(false);
        textIloscSezonow.setVisible(false);
        textDataEmisjiDzien.setVisible(false);
        textDataEmisjiMiesiac.setVisible(false);
        textDataEmisjiRok.setVisible(false);
        textDataPromocjiPoczatekDzien.setVisible(false);
        textDataPromocjiPoczatekMiesiac.setVisible(false);
        textDataPromocjiPoczatekRok.setVisible(false);
        textDataPromocjiKoniecDzien.setVisible(false);
        textDataPromocjiKoniecMiesiac.setVisible(false);
        textDataPromocjiKoniecRok.setVisible(false);
        textWysokoscPromocji.setVisible(false);

        if (this.comboBoxWyborTypuProdukcji.getValue() == "Wydarzenie na Żywo") {
            labelDataEmisji.setVisible(true);
            textDataEmisjiDzien.setVisible(true);
            textDataEmisjiMiesiac.setVisible(true);
            textDataEmisjiRok.setVisible(true);

            labelDataPromocjiPoczatek.setVisible(true);
            labelDataPromocjiKoniec.setVisible(true);
            labelWysokoscPromocji.setVisible(true);
            textDataPromocjiPoczatekDzien.setVisible(true);
            textDataPromocjiPoczatekMiesiac.setVisible(true);
            textDataPromocjiPoczatekRok.setVisible(true);
            textDataPromocjiKoniecDzien.setVisible(true);
            textDataPromocjiKoniecMiesiac.setVisible(true);
            textDataPromocjiKoniecRok.setVisible(true);
            textWysokoscPromocji.setVisible(true);


        } else if (this.comboBoxWyborTypuProdukcji.getValue()=="Film"){
            labelGatunek.setVisible(true);
            comboBoxWyborGatunku.setVisible(true);
            labelLinkDoZwiastunow.setVisible(true);
            comboBoxLinkDoZwiastunu.setVisible(true);
            buttonDodajLink.setVisible(true);
            buttonUsunLink.setVisible(true);
            labelCzasNaObejrzenie.setVisible(true);
            textCzasNaObejrzenie.setVisible(true);

            labelAktor.setVisible(true);
            comboBoxAktorzy.setVisible(true);
            buttonDodajAktorow.setVisible(true);
            buttonUsunAktora.setVisible(true);

            labelDataPromocjiPoczatek.setVisible(true);
            labelDataPromocjiKoniec.setVisible(true);
            labelWysokoscPromocji.setVisible(true);
            textDataPromocjiPoczatekDzien.setVisible(true);
            textDataPromocjiPoczatekMiesiac.setVisible(true);
            textDataPromocjiPoczatekRok.setVisible(true);
            textDataPromocjiKoniecDzien.setVisible(true);
            textDataPromocjiKoniecMiesiac.setVisible(true);
            textDataPromocjiKoniecRok.setVisible(true);
            textWysokoscPromocji.setVisible(true);

        }else if (this.comboBoxWyborTypuProdukcji.getValue()=="Serial"){
            labelGatunek.setVisible(true);
            comboBoxWyborGatunku.setVisible(true);
            labelIloscSezonow.setVisible(true);
            textIloscSezonow.setVisible(true);
            labelAktor.setVisible(true);
            comboBoxAktorzy.setVisible(true);
            buttonDodajAktorow.setVisible(true);
            buttonUsunAktora.setVisible(true);

        }


    }

    @FXML
    private void wybranieTypuProdukcjiZComboBoxa(){
        deaktywacjaPolNiewlasciwychDlaDanegoTypuProdukcji();
    }


    private void zaladowanieComboBoxaDystrybutora(){

        for (int i=0; i<Main.pulaDystrybutorow.pula.getSize(); i++){
            comboBoxWyborDystrybutora.getItems().addAll(Main.pulaDystrybutorow.pula.getElement(i).toString());
        }

    }


    private void wyczyscWidoczneWartosciPol() {
        textTytul.clear();
        textAdresDoZdjecia.clear();
        textOpis.clear();
        textCzasTrwania.clear();
        textCena.clear();
        textOcenaUzytkownikow.clear();
        comboBoxKrajProdukcji.getItems().clear();
        comboBoxKrajProdukcji.setValue(null);


        comboBoxLinkDoZwiastunu.getItems().clear();
        comboBoxLinkDoZwiastunu.setValue(null);
        textCzasNaObejrzenie.clear();
        textIloscSezonow.clear();
        textDataEmisjiDzien.clear();
        textDataEmisjiMiesiac.clear();
        textDataEmisjiRok.clear();
    }



    @FXML
    private void dodajKrajProdukcji(){
        this.comboBoxKrajProdukcji.getItems().add(this.comboBoxKrajProdukcji.getValue());
        this.comboBoxKrajProdukcji.getSelectionModel().clearSelection(); //czyści panel wpisywwania comboBoxa po dodaniu kraju
        this.zrzutPamieciDoPuliDanychDlaKontrolerow();
        losujNowyElementListyKrajow();
    }

    @FXML
    private void usunKrajProdukcji(){
        comboBoxKrajProdukcji.getItems().remove(comboBoxKrajProdukcji.getValue());
        comboBoxKrajProdukcji.getSelectionModel().clearSelection();
        zrzutPamieciDoPuliDanychDlaKontrolerow();
        losujNowyElementListyKrajow();
    }

    @FXML
    private void dodajLinkDoZwiastunu(){
        comboBoxLinkDoZwiastunu.getItems().add(comboBoxLinkDoZwiastunu.getValue());
        comboBoxLinkDoZwiastunu.getSelectionModel().clearSelection();
        zrzutPamieciDoPuliDanychDlaKontrolerow();
        losujNowyElementListyLinkow();
    }

    @FXML
    private void usunLinkDoZwiastunu(){
        comboBoxLinkDoZwiastunu.getItems().remove(comboBoxLinkDoZwiastunu.getValue());
        comboBoxLinkDoZwiastunu.getSelectionModel().clearSelection();
        zrzutPamieciDoPuliDanychDlaKontrolerow();
        losujNowyElementListyLinkow();
    }

    @FXML
    private void wyczyscWszystkiePola(){
        DaneDlaKontrolerow.kasujDaneZTymczasowychList();

        wyczyscWidoczneWartosciPol();
    }

    @FXML
    public void losujWszystkiePola(){
        losujWartosciWszystkichPol();
    }

    private void losujWartosciWszystkichPol(){
        Random generator = new Random();

        String tytul = BazaAllTexts.tytuly.getListaTytulow().get(generator.nextInt(BazaAllTexts.tytuly.getListaTytulow().size()));
        String adresZdjecia = BazaAllTexts.zdjecia.getListaZdjec().get(generator.nextInt(BazaAllTexts.zdjecia.getListaZdjec().size()));
        String opis = BazaAllTexts.opisy.getListaOpisow().get(generator.nextInt(BazaAllTexts.opisy.getListaOpisow().size()));
        int czasTrwania = generator.nextInt(299)+1; //czasTrwania nie większy niż 5h i większy niz 1 minuta
        int cena = generator.nextInt(60)+60;
        float ocenaUzytkownikow = (generator.nextFloat()*10 % 9) + 1;
        comboBoxWyborDystrybutora.getSelectionModel().select(generator.nextInt(comboBoxWyborDystrybutora.getItems().size()));

        comboBoxKrajProdukcji.getItems().clear();
        losujNowyElementListyKrajow();
        textTytul.setText(tytul);
        textAdresDoZdjecia.setText(adresZdjecia);
        textOpis.setText(opis);
        textCzasTrwania.setText(Integer.toString(czasTrwania));
        textCena.setText(Integer.toString(cena));
        textOcenaUzytkownikow.setText(Float.toString(ocenaUzytkownikow));


        //te dwie linijki ustawiają jeden z 3 typów produkcji
        int typProdukcji = generator.nextInt(3);
        comboBoxWyborTypuProdukcji.setValue(comboBoxWyborTypuProdukcji.getItems().get(typProdukcji));
        deaktywacjaPolNiewlasciwychDlaDanegoTypuProdukcji();

        int gatunek = generator.nextInt(6);
        comboBoxWyborGatunku.setValue(comboBoxWyborGatunku.getItems().get(gatunek));



        int czasNaObejrzenie = generator.nextInt(1000);
        int iloscSezonow = generator.nextInt(20)+1;
        int miesiac = Data.losujMiesiac();
        comboBoxLinkDoZwiastunu.getItems().clear();
        losujNowyElementListyLinkow();
        textCzasNaObejrzenie.setText(Integer.toString(czasNaObejrzenie));
        textIloscSezonow.setText(Integer.toString(iloscSezonow));
        textDataEmisjiRok.setText(Integer.toString(Data.losujRok(1970, 2020)));
        textDataEmisjiMiesiac.setText(Integer.toString(miesiac));
        textDataEmisjiDzien.setText(Integer.toString(Data.losujDzien(miesiac)));

        losujDatyPromocji();

        textWysokoscPromocji.setText(Integer.toString(Promocja.losujWysokoscPromocji()));

    }

    private Promocja ustawDanePromocji(){
        Promocja promocja = new Promocja();
        Data data1;
        Data data2;
        int dzien = Integer.parseInt(textDataPromocjiPoczatekDzien.getText());
        int miesiac = Integer.parseInt(textDataPromocjiPoczatekMiesiac.getText());
        int rok = Integer.parseInt(textDataPromocjiPoczatekRok.getText());
        data1 = new Data(dzien,miesiac,rok);
        promocja.setDataRozpoczecia(data1);

        dzien = Integer.parseInt(textDataPromocjiKoniecDzien.getText());
        miesiac = Integer.parseInt(textDataPromocjiKoniecMiesiac.getText());
        rok = Integer.parseInt(textDataPromocjiKoniecRok.getText());
        data2 = new Data(dzien,miesiac,rok);
        promocja.setDataZakonczenia(data2);
        promocja.setUpustProcentowy(Integer.parseInt(textWysokoscPromocji.getText()));

        return promocja;
    }

    @FXML
    public void dodajProdukcjeDoPuli(){

        Produkcja produkcja;// = null;

        if (this.comboBoxWyborTypuProdukcji.getValue() == "Film"){
            produkcja = new Film();
            ((Film) produkcja).setGatunek(comboBoxWyborGatunku.getValue());

            ArrayList<String> listaZwiastunow = new ArrayList<>(DaneDlaKontrolerow.linkiDoZwiastunow);
            ((Film) produkcja).setListaLinkowDoZwiastunow(listaZwiastunow);

            ((Film) produkcja).setCzasMozliwoscOgladania(Integer.parseInt(textCzasNaObejrzenie.getText()));  //LICZBA
            ((Film) produkcja).setPromocja(ustawDanePromocji());
            ArrayList<Aktor> listaAktorow = new ArrayList<>(DaneDlaKontrolerow.listaAktorow);
            ((Film) produkcja).setListaAktorow(listaAktorow);


        }else if (this.comboBoxWyborTypuProdukcji.getValue() == "Serial"){
            produkcja = new Serial();
            ((Serial) produkcja).setGatunek(comboBoxWyborGatunku.getValue());
            ((Serial) produkcja).setIloscSezonow(Integer.parseInt(textIloscSezonow.getText()));              //LICZBA
            ArrayList<Aktor> listaAktorow = new ArrayList<>(DaneDlaKontrolerow.listaAktorow);
            ((Serial) produkcja).setListaAktorow(listaAktorow);
        }else{ //if(this.comboBoxWyborTypuProdukcji.getValue() == "Wydarzenie na Żywo") {
            produkcja = new Live();
            int dzien = Integer.parseInt(textDataEmisjiDzien.getText());
            int miesiac = Integer.parseInt(textDataEmisjiMiesiac.getText());
            int rok = Integer.parseInt(textDataEmisjiRok.getText());
            ((Live) produkcja).setDataWyswietlenia(dzien,miesiac,rok);
            ((Live) produkcja).setPromocja(ustawDanePromocji());



        }

        produkcja.setNazwa(this.textTytul.getText());
        produkcja.setZdjecie(this.textAdresDoZdjecia.getText());
        produkcja.setOpis(this.textOpis.getText());
        ArrayList<String> listaKrajowProdukcji = new ArrayList<>(DaneDlaKontrolerow.krajeProdukcji);
        produkcja.setKrajeProdukcji(listaKrajowProdukcji);

        int indexDystrybutora = comboBoxWyborDystrybutora.getSelectionModel().getSelectedIndex();
        Dystrybutor wybranyDystrybutor = Main.pulaDystrybutorow.pula.getElement(indexDystrybutora);
        produkcja.setDystrybutorProdukcji(wybranyDystrybutor);
        produkcja.setCzasTrwania(Integer.parseInt(this.textCzasTrwania.getText()));             //LICZBA
        produkcja.setCena(Float.parseFloat(this.textCena.getText()));                           //LICZBA
        produkcja.setOcenaUzytkownikow(Float.parseFloat(this.textOcenaUzytkownikow.getText())); //LICZBA



        Main.pulaProduktow.pula.dodajDoPuli(produkcja);
        losujWartosciWszystkichPol();
        DaneDlaKontrolerow.kasujDaneZTymczasowychList();
        Main.pulaProduktow.zapiszPuleDoPliku();



        System.out.println(Main.pulaProduktow.pula.getSize());
    }

}
