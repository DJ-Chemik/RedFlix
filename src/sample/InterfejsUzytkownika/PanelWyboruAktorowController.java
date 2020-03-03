package sample.InterfejsUzytkownika;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import sample.Main;
import sample.Symulacja.PuleDoSerializacji.PulaAktorow;

public class PanelWyboruAktorowController extends Panel {


    @FXML
    private ListView<String> listaAktorow; //Lista Stringów reprezentujacych poszczególnych aktorów


    @FXML
    public void initialize() {
        zaladujListeAktorow();
    }


    @FXML
    public void openPanelDodawaniaProdukcji() {
        openPanel("PlikiFXML/PanelDodawaniaProdukcji.fxml");
    }

    @FXML
    @Override
    public void openPanelGlowny(){
        PanelDodawaniaProdukcjiController.setCzyZachowacDaneWszystkichPolPoOpuszczeniuOkna(false);
        super.openPanelGlowny();
    }

    private void zaladujListeAktorow(){
        for (int i = 0; i< Main.pulaAktorow.pula.getSize(); i++) {
            listaAktorow.getItems().addAll(Main.pulaAktorow.pula.getElement(i).toString());
        }
    }

    @FXML
    private void dodajAktoraDoProdukcji(){
        int index = listaAktorow.getSelectionModel().getSelectedIndex();
        DaneDlaKontrolerow.listaAktorow.add(Main.pulaAktorow.pula.getElement(index));
    }



}
