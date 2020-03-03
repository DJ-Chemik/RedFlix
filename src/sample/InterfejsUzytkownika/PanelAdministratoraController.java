package sample.InterfejsUzytkownika;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.Main;


public class PanelAdministratoraController extends Panel {

    @FXML
    private Label labelWarning;

    @FXML
    private Button buttonDodawanieProdukcji;

    @FXML
    public void initialize(){
        if (Main.pulaDystrybutorow.pula.getSize()==0){
            labelWarning.setVisible(true);
            buttonDodawanieProdukcji.setDisable(true);
            buttonDodawanieProdukcji.setVisible(false);
        }
    }


    @FXML
    public void openPanelDodawaniaProdukcji() {
        openPanel("PlikiFXML/PanelDodawaniaProdukcji.fxml");
    }


    @FXML
    public void openPanelDodawaniaLudzi(){
        openPanel("PlikiFXML/PanelDodawaniaLudzi.fxml");
    }

    @FXML
    public void openPanelListyLudzi(){
        openPanel("PlikiFXML/PanelListyLudzi.fxml");
    }



}
