package sample.InterfejsUzytkownika;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import sample.Main;
import sample.StricteBazaProdukcji.Produkcja;
import sample.Symulacja.Dystrybutor;
import sample.Symulacja.User;

import java.util.ArrayList;


public class PanelListyLudziController extends Panel {

    @FXML
    private ListView<String> listaDystrybutorow;
    @FXML
    private ListView<String> listaUserow;

    @FXML
    public void initialize() {
        zaladujListeDystrybutorow();
        zaladujListeUserow();
    }

    private void zaladujListeDystrybutorow(){

        for (int i = 0; i<Main.pulaDystrybutorow.pula.getSize(); i++) {
            listaDystrybutorow.getItems().addAll(Main.pulaDystrybutorow.pula.getElement(i).toString());
        }

    }

    private void zaladujListeUserow(){
        for (int i=0; i<Main.pulaUserow.pula.getSize(); i++){
            listaUserow.getItems().addAll(Main.pulaUserow.pula.getElement(i).toString());
        }
    }

    @FXML
    public void openPanelAdministratora() {
        openPanel("PlikiFXML/PanelAdministratora.fxml");
    }

    @FXML
    public void openPanelDodawaniaLudzi(){
        openPanel("PlikiFXML/PanelDodawaniaLudzi.fxml");
    }

    @FXML
    public synchronized void usunDystrybutora(){

        int wybranyIndex = listaDystrybutorow.getSelectionModel().getSelectedIndex();
        Dystrybutor dystrybutorDoUsuniecia = Main.pulaDystrybutorow.pula.getElement(wybranyIndex);


        usunProdukcjeNalezaceDoDanegoDystrybuora(dystrybutorDoUsuniecia);
        Main.systemSymulacji.usunWatekDystrybutora(dystrybutorDoUsuniecia);
        Main.pulaDystrybutorow.pula.usunZPuli(wybranyIndex);
        Main.pulaDystrybutorow.zapiszPuleDoPliku();
        listaDystrybutorow.getItems().clear();
        zaladujListeDystrybutorow();

    }

    /**
     * Metoda mająca na celu usunięcie produkcji danego dystrybutora, gdy jego samego usuwamy.
     * @param dystrybutorDoUsuniecia
     */
    public synchronized void usunProdukcjeNalezaceDoDanegoDystrybuora(Dystrybutor dystrybutorDoUsuniecia) {
        for (int i = Main.pulaProduktow.pula.getSize()-1; i>=0; i--) {

            if (Main.pulaProduktow.pula.getElement(i).getDystrybutorProdukcji()==dystrybutorDoUsuniecia)
                Main.pulaProduktow.pula.usunZPuli(Main.pulaProduktow.pula.getElement(i));

        }
        Main.pulaProduktow.zapiszPuleDoPliku();
    }

    @FXML
    public void usunUzytkownika(){
        int wybranyIndeks = listaUserow.getSelectionModel().getSelectedIndex();
        User userDoUsuniecia = Main.pulaUserow.pula.getElement(wybranyIndeks);



        Main.systemSymulacji.usunWatekUsera(userDoUsuniecia);
        Main.pulaUserow.pula.usunZPuli(wybranyIndeks);
        Main.pulaUserow.zapiszPuleDoPliku();
        listaUserow.getItems().clear();
        zaladujListeUserow();
    }

    public void openPanelInfoDystrybutor(){
        openPanel("PlikiFXML/PanelInfoDystrybutor.fxml");
    }

    public void openPanelInfoUser(){
        openPanel("PlikiFXML/PanelInfoUser.fxml");
    }

    @FXML
    public void wyswietlInfoDystrybutor(){
        DaneDlaKontrolerow.indeksWybranegoDystrybutora=listaDystrybutorow.getSelectionModel().getSelectedIndex();
        openPanelInfoDystrybutor();
    }

    @FXML
    public void wyswietlInfoUser(){
        DaneDlaKontrolerow.indeksWybranegoUsera=listaUserow.getSelectionModel().getSelectedIndex();
        openPanelInfoUser();

    }



}
