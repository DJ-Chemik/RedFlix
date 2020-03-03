package sample.StricteBazaProdukcji;

import sample.TypyDanychPomocnicznych.Data;
import sample.Symulacja.Promocja;

import java.io.Serializable;

public class Live extends Produkcja implements Serializable {
    private Data dataWyswietlenia;
    private Promocja promocja;

    public boolean kupDostepDoTransmisji(){

        return false;
    }


    public Live() {


    }

    public void losujWartosciWszystkichPol(){


    }

    public Data getDataWyswietlenia() {
        return dataWyswietlenia;
    }

    public void setDataWyswietlenia(Data dataWyswietlenia) {
        this.dataWyswietlenia = dataWyswietlenia;
    }

    public void setDataWyswietlenia(int dzien, int miesiac, int rok) {

        //DLACZEGO TO NIE DZIAŁA I WYSKAKUJE NULL POINTER EXCEPTION   !!!!

        /*this.dataWyswietlenia.setDzien(dzien);
        this.dataWyswietlenia.setMiesiac(miesiac);
        this.dataWyswietlenia.setRok(rok);*/

        this.dataWyswietlenia = new Data(dzien, miesiac, rok);
    }

    public Promocja getPromocja() {
        return promocja;
    }

    public void setPromocja(Promocja promocja) {
        this.promocja = promocja;
    }
}
