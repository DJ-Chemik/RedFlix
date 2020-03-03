package sample.DaneTekstoweWListach.DlaProdukcji;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

 public class BazaKrajow {

    public ObservableList<String> getListaKrajow() {
        return bazaKrajow;
    }

    private ObservableList<String> bazaKrajow = FXCollections.observableArrayList(

            "USA",
            "Kanada",
            "Meksyk",
            "Panama",

            "Polska",
            "Niemcy",
            "Francja",
            "Dania",
            "Portugalia",
            "Hiszpania",
            "Holandia",
            "Belgia",
            "Rosja",
            "Włochy",
            "Chorwacja",

            "Chiny",
            "Japonia",
            "Kazachstan",
            "Indie",

            "Australia"

    );
}
