package sample.InterfejsUzytkownika;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import sample.DaneTekstoweWListach.BazaAllTexts;
import sample.Main;
import sample.Symulacja.Abonamenty.*;
import sample.Symulacja.Dystrybutor;
import sample.Symulacja.User;
import sample.TypyDanychPomocnicznych.Aktor;
import sample.TypyDanychPomocnicznych.Data;

import java.util.Random;


public class PanelDodawaniaLudziController extends Panel {

    @FXML
    private TextField textImieAktora;
    @FXML
    private TextField textNazwiskoAktora;
    @FXML
    private TextField textDzienAktora;
    @FXML
    private TextField textMiesiacAktora;
    @FXML
    private TextField textRokAktora;
    ////////////////////////////////////////////////////////////////////////
    @FXML
    private TextField textNazwaDystrybutora;
    @FXML
    private TextField textNrKontaDystrybutora;
    @FXML
    private TextField textNrKontaDystrybutora2;
    @FXML
    private TextField textNrKontaDystrybutora3;
    @FXML
    private TextField textNrKontaDystrybutora4;
    @FXML
    private TextField textNrKontaDystrybutora5;
    @FXML
    private TextField textNrKontaDystrybutora6;
    @FXML
    private TextField textNrKontaDystrybutora7;
    @FXML
    private TextField textKwotaDystrybutoraStala;
    @FXML
    private TextField textKwotaDystrybutoraRyczalt;
    /////////////////////////////////////////////////////////////////////////
    @FXML
    private TextField textIDUsera;
    @FXML
    private TextField textLoginUsera;
    @FXML
    private TextField textEmailUsera;
    @FXML
    private TextField textNrKartyUsera1;
    @FXML
    private TextField textNrKartyUsera2;
    @FXML
    private TextField textNrKartyUsera3;
    @FXML
    private TextField textNrKartyUsera4;
    @FXML
    private TextField textDzienUsera;
    @FXML
    private TextField textMiesiacUsera;
    @FXML
    private TextField textRokUsera;
    @FXML
    private ComboBox<String> comboBoxAbonamentUsera;


    @FXML
    public void initialize() {
        comboBoxAbonamentUsera.getItems().setAll(DaneDlaKontrolerow.typyAbonamentow);
        losujWartosciWszystkichPol();
    }


    @FXML
    public void openPanelAdministratora() {
        openPanel("PlikiFXML/PanelAdministratora.fxml");
    }

    @FXML
    public void openPanelListyLudzi() {
        openPanel("PlikiFXML/PanelListyLudzi.fxml");
    }

    @FXML
    public void openPanelListyAktorow() {
        openPanel("PlikiFXML/PanelListyAktorow.fxml");
    }


    private void losujWartosciWszystkichPol() {
        Random generator = new Random();
        losujWartosciPolAktora(generator);
        losujWartosciPolDystrybutora(generator);
        losujWartosciPolUsera(generator);


    }

    private void losujWartosciPolUsera(Random generator) {
        String login = BazaAllTexts.loginy.getBazaLoginow().get(generator.nextInt(BazaAllTexts.loginy.getBazaLoginow().size()));
        textLoginUsera.setText(login);
        String mail = BazaAllTexts.maile.getBazaMaili().get(generator.nextInt(BazaAllTexts.loginy.getBazaLoginow().size()));
        textEmailUsera.setText(mail);
        int numerID = generator.nextInt(1_000_000_000);
        textIDUsera.setText(Integer.toString(numerID));
        int nrKarty[] = new int[4];
        for (int i = 0; i < 4; i++) {
            nrKarty[i] = generator.nextInt(8999) + 1000;
        }
        textNrKartyUsera1.setText(Integer.toString(nrKarty[0]));
        textNrKartyUsera2.setText(Integer.toString(nrKarty[1]));
        textNrKartyUsera3.setText(Integer.toString(nrKarty[2]));
        textNrKartyUsera4.setText(Integer.toString(nrKarty[3]));

        int miesiac = Data.losujMiesiac();
        textDzienUsera.setText(Integer.toString(Data.losujDzien(miesiac)));
        textMiesiacUsera.setText(Integer.toString(miesiac));
        textRokUsera.setText(Integer.toString(Data.losujRok(1940, 2020)));

        comboBoxAbonamentUsera.setValue(DaneDlaKontrolerow.typyAbonamentow.get(generator.nextInt(4)));

    }

    private void losujWartosciPolDystrybutora(Random generator) {
        String nazwaDystrybutora = BazaAllTexts.nazwyDystrybutorow.getBazaNazw().get(
                generator.nextInt(BazaAllTexts.nazwyDystrybutorow.getBazaNazw().size()));
        textNazwaDystrybutora.setText(nazwaDystrybutora);

        int jakaFormaPlatnosci = generator.nextInt(2);
        if (jakaFormaPlatnosci == 0) {
            //TUTAJ TRZEBA WPISAć MAX KWOTĘ ZA JEDNORAZOWE ODTWORZENIE
            textKwotaDystrybutoraStala.setText(Integer.toString(generator.nextInt(50) + 10));
            textKwotaDystrybutoraRyczalt.setText("0");
        } else {
            textKwotaDystrybutoraRyczalt.setText(Integer.toString(generator.nextInt(500) + 300));
            textKwotaDystrybutoraStala.setText("0");
        }


        textNrKontaDystrybutora.setText(Integer.toString(generator.nextInt(89) + 10));
        textNrKontaDystrybutora2.setText(Integer.toString(generator.nextInt(8999) + 1000));
        textNrKontaDystrybutora3.setText(Integer.toString(generator.nextInt(8999) + 1000));
        textNrKontaDystrybutora4.setText(Integer.toString(generator.nextInt(8999) + 1000));
        textNrKontaDystrybutora5.setText(Integer.toString(generator.nextInt(8999) + 1000));
        textNrKontaDystrybutora6.setText(Integer.toString(generator.nextInt(8999) + 1000));
        textNrKontaDystrybutora7.setText(Integer.toString(generator.nextInt(8999) + 1000));

    }

    private void losujWartosciPolAktora(Random generator) {
        String imie = BazaAllTexts.imiona.getBazaImion().get(generator.nextInt(BazaAllTexts.imiona.getBazaImion().size()));
        String nazwisko = BazaAllTexts.nazwiska.getBazaNazw().get(generator.nextInt(BazaAllTexts.nazwiska.getBazaNazw().size()));
        textImieAktora.setText(imie);
        textNazwiskoAktora.setText(nazwisko);

        int miesiac = Data.losujMiesiac();
        textDzienAktora.setText(Integer.toString(Data.losujDzien(miesiac)));
        textMiesiacAktora.setText(Integer.toString(miesiac));
        textRokAktora.setText(Integer.toString(Data.losujRok(1940, 2020)));

    }


    @FXML
    public void dodajAktora() {

        String imie = textImieAktora.getText();
        String nazwisko = textNazwiskoAktora.getText();
        int dzien = Integer.parseInt(textDzienAktora.getText());
        int miesiac = Integer.parseInt(textMiesiacAktora.getText());
        int rok = Integer.parseInt(textRokAktora.getText());
        Data data = new Data(dzien, miesiac, rok);
        Aktor aktor = new Aktor(imie, nazwisko, data);

        Main.pulaAktorow.pula.dodajDoPuli(aktor);
        losujWartosciPolAktora(new Random());
        Main.pulaAktorow.zapiszPuleDoPliku();

    }

    @FXML
    public void dodajDystrybutora() {
        String nazwaDystrybutora = textNazwaDystrybutora.getText();
        int nrKonta = Integer.parseInt(textNrKontaDystrybutora.getText());
        float kwotaJednorazowa = Float.parseFloat(textKwotaDystrybutoraStala.getText());
        float ryczaltMiesieczny = Float.parseFloat(textKwotaDystrybutoraRyczalt.getText());
        Dystrybutor dystrybutor = new Dystrybutor(nazwaDystrybutora, nrKonta, kwotaJednorazowa, ryczaltMiesieczny);
        Main.systemSymulacji.stworzWatekDystrybutora(dystrybutor);

        Main.pulaDystrybutorow.pula.dodajDoPuli(dystrybutor);
        losujWartosciPolDystrybutora(new Random());
        Main.pulaDystrybutorow.zapiszPuleDoPliku();

    }

    @FXML
    public void dodajUsera() {

        int kod = Integer.parseInt(textIDUsera.getText());
        String login = textLoginUsera.getText();
        int nrKarty1 = Integer.parseInt(textNrKartyUsera1.getText());
        int nrKarty2 = Integer.parseInt(textNrKartyUsera2.getText());
        int nrKarty3 = Integer.parseInt(textNrKartyUsera3.getText());
        int nrKarty4 = Integer.parseInt(textNrKartyUsera4.getText());
        String email = textEmailUsera.getText();
        int dzien = Integer.parseInt(textDzienAktora.getText());
        int miesiac = Integer.parseInt(textMiesiacAktora.getText());
        int rok = Integer.parseInt(textRokAktora.getText());
        Data data = new Data(dzien, miesiac, rok);
        Abonament abonament = null;
        switch (comboBoxAbonamentUsera.getSelectionModel().getSelectedIndex()) {
            case 0:
                abonament = new AbonamentNieistniejacy();
                break;
            case 1:
                abonament = new AbonamentBasic();
                break;
            case 2:
                abonament = new AbonamentFamily();
                break;
            case 3:
                abonament = new AbonamentPremium();
                break;
        }
        User user = new User(kod, login, data, email, nrKarty1, nrKarty2, nrKarty3, nrKarty4, abonament);
        Main.systemSymulacji.stworzWatekUsera(user);
        Main.pulaUserow.pula.dodajDoPuli(user);
        losujWartosciPolUsera(new Random());
        Main.pulaUserow.zapiszPuleDoPliku();


    }
}
