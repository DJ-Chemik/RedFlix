package sample.InterfejsUzytkownika;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import sample.TypyDanychPomocnicznych.Aktor;

import java.util.ArrayList;

public class DaneDlaKontrolerow {

    final static ObservableList<String> typyProdukcji = FXCollections.observableArrayList(
            "Serial", "Film", "Wydarzenie na Żywo"
    );

    final static ObservableList<String> gatunkiProdukcji = FXCollections.observableArrayList(
            "Akcja", "Komedia", "Sensacyjny", "Dramat", "Dokumentalny", "Dla Dzieci"
    );

    final static ObservableList<String> typyAbonamentow = FXCollections.observableArrayList(
            "--brak--", "Basic", "Family", "Premium"
    );

    public static ObservableList<String> getTypyProdukcji() {
        return typyProdukcji;
    }

    public static ObservableList<String> getGatunkiProdukcji() {
        return gatunkiProdukcji;
    }

    public static ObservableList<String> getTypyAbonamentow() {
        return typyAbonamentow;
    }

    //INFORMACJE DLA PANELU DODAWANIA PRODUKCJI
    static void kasujDaneZTymczasowychList() {
        krajeProdukcji.clear();
        linkiDoZwiastunow.clear();
    }

    static String tytul;
    static String adresDoZdjecia;
    static String opis;
    static int czasTrwania; //CZY TE INTY I FLOATY TO NIE LEPIEJ JAKBY TU BYŁY STRINGAMI?
    static float cena;
    static float ocenaUzytkownikow;
    static ArrayList<String> krajeProdukcji = new ArrayList<>();

    static int indeksTypuProdukcji; //0-Seral, 1-Film, 2-Live
    static int indeksDystrybutoraPrzyDodawaniuProdukcji;
    static ArrayList<String> linkiDoZwiastunow = new ArrayList<>();
    static int czasNaObejrzenie;
    static ArrayList<Aktor> listaAktorow = new ArrayList<>();
    static int indeksGatunku; //0-5
    static int iloscSezonow;

    static String[] dzien = new String[3];
    static String[] miesiac = new String[3];
    static String[] rok = new String[3];
    static int wysokoscProcentowaPromocji;


    //INFORMACJE DLA PANELU DODAWANIA LUDZI
    static int indeksWybranegoDystrybutora;
    static int indeksWybranegoUsera;

}
