package sample.InterfejsUzytkownika;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.BorderPane;

import java.io.IOException;

/**
 * Ta klasa zapewnia że Panele poboczne w naszej aplikacji mogą bezpośrednio przechodzić do Panelu Głównego.
 * W ten sposób nie trzeba wszędzie kopiować tego kodu
 */
public abstract class Panel {

    private MainScreenController mainScreenController;

    public void setMainScreenController(MainScreenController mainScreenController) {
        this.mainScreenController = mainScreenController;
    }

    @FXML
    public void openPanelGlowny() {
        this.mainScreenController.loadPanelGlowny();
    }

    @FXML
    public void openPanel(String adres){

        FXMLLoader loader = new FXMLLoader(this.getClass().getResource(adres));
        BorderPane borderPane = null;
        try {
            borderPane = loader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Panel panel = loader.getController();
        panel.setMainScreenController(mainScreenController);
        mainScreenController.setScreen(borderPane);

    }

}
