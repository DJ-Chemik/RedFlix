package sample.InterfejsUzytkownika;

import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import sample.Main;

public class PanelListyAktorowController extends Panel {


    @FXML
    private ListView<String> listaAktorow; //Lista Stringów reprezentujacych poszczególnych aktorów


    @FXML
    public void initialize() {
        zaladujListeAktorow();
    }


    @FXML
    public void openPanelDodawaniaLudzi() {
        openPanel("PlikiFXML/PanelDodawaniaLudzi.fxml");
    }



    private void zaladujListeAktorow(){
        listaAktorow.getItems().clear();
        for (int i = 0; i< Main.pulaAktorow.pula.getSize(); i++) {
            listaAktorow.getItems().addAll(Main.pulaAktorow.pula.getElement(i).toString());
        }
    }



    @FXML
    private void usunWybranegoAktoraZSystemu(){

        Main.pulaAktorow.pula.usunZPuli(listaAktorow.getSelectionModel().getSelectedIndex());
        zaladujListeAktorow();
    }

}
