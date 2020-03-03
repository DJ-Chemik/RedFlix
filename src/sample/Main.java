package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import sample.Symulacja.PuleDoSerializacji.*;
import sample.Symulacja.SystemSymulacji;


public class Main extends Application
{

    public volatile static PulaProduktow pulaProduktow = new PulaProduktow(); //pula produktów dostępna publicznie, aby była jedyną pulą dostępną we wszystkich klasach
    public static PulaAktorow pulaAktorow = new PulaAktorow();
    public static PulaDystrybutorow pulaDystrybutorow = new PulaDystrybutorow();
    public volatile static PulaUserow pulaUserow = new PulaUserow();

    public static SystemSymulacji systemSymulacji = new SystemSymulacji();


    @Override
    public void start(Stage primaryStage) throws Exception
    {
        FXMLLoader loader = new FXMLLoader(this.getClass().getResource("InterfejsUzytkownika/PlikiFXML/MainScreen.fxml"));
        //Parent loader = FXMLLoader.load(getClass().getResource("InterfejsUzytkownika/MainScreen.fxml")); //taki inny sposób
        StackPane stackPane = loader.load();
        Scene scene = new Scene(stackPane,1280,720); //predefiniowana rozdzielczość
        //Scene scene = new Scene(stackPane,1920,1080);
        primaryStage.setTitle("RedFlix - twoje źródło filmowych doznań");
        primaryStage.setScene(scene);
        primaryStage.show();

    }


    /**
     * Tutaj można ręcznie wprowdzić zmiany czy chcemy ładować pule z serializacji do systemu, czy nie.
     * @param args
     */
    public static void main(String[] args)
    {

        /*try {

            Main.pulaDystrybutorow.wczytajPuleZPliku();
        }catch (NieOdnalezionoPlikuDoSerializacjiException e){
            System.out.println("Brak pliku z którego można pobrać dane\n");
        }*/


        //Main.pulaProduktow.wczytajPuleZPliku();
        Main.pulaAktorow.wczytajPuleZPliku();
        //Main.pulaUserow.wczytajPuleZPliku();
        //Main.pulaDystrybutorow.wczytajPuleZPliku();

        systemSymulacji.stworzWatekCzasu();
        systemSymulacji.odpalWatkiDystrybutorowIUserowPrzyStarcie();
        launch(args);
    }
}
