package sample.Symulacja;

import sample.TypyDanychPomocnicznych.Data;

import java.io.Serializable;
import java.util.Random;

public class Promocja implements Serializable {
    private Data dataRozpoczecia;
    private Data dataZakonczenia;
    private int upustProcentowy; //od 5% do 50%



    public Promocja() {

    }

    public Data getDataRozpoczecia() {
        return dataRozpoczecia;
    }

    public void setDataRozpoczecia(Data dataRozpoczecia) {
        this.dataRozpoczecia = dataRozpoczecia;
    }

    public Data getDataZakonczenia() {
        return dataZakonczenia;
    }

    public void setDataZakonczenia(Data dataZakonczenia) {
        this.dataZakonczenia = dataZakonczenia;
    }

    public int getUpustProcentowy() {
        return upustProcentowy;
    }

    public void setUpustProcentowy(int upustProcentowy) {
        this.upustProcentowy = upustProcentowy;
    }

    /**
     * Losuje wysokośc procentową promocji od 5% do 50%
     * @return wysokość procentowa promocji
     */
    public static int losujWysokoscPromocji(){
        Random random =new Random();
        return random.nextInt(46)+5;
    }
}
