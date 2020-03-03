package sample.InterfejsUzytkownika;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.*;

import java.io.IOException;

public class MainScreenController {
    @FXML
    private  StackPane mainStackPane;


    @FXML
    public void initialize() {
        loadPanelGlowny();

    }

    public void loadPanelGlowny() {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("PlikiFXML/PanelGlowny.fxml"));

        BorderPane borderPaneMenuGlownego = null;

        try {
            borderPaneMenuGlownego = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        PanelGlownyController panelGlowny = loader.getController();
        panelGlowny.setMainScreenController(this);
        setScreen(borderPaneMenuGlownego);
    }


    public void setScreen(BorderPane borderPane) {
        mainStackPane.getChildren().clear();
        mainStackPane.getChildren().add(borderPane);
    }
}