package sample.TypyDanychPomocnicznych;

import java.io.Serializable;

public class Aktor implements Serializable {
    private String imie;
    private String nazwisko;
    private Data dataUrodzenia;

    public Aktor(String imie, String nazwisko, Data data) {
        this.imie = imie;
        this.nazwisko = nazwisko;
        this.dataUrodzenia = data;
    }

    public String getImie() {
        return imie;
    }

    public String getNazwisko() {
        return nazwisko;
    }

    public Data getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(Data dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }


    //To można jeszcze zoptymalizować
    @Override
    public String toString(){
        String string = new String();
        string+=imie;
        string+="  ";
        string+=nazwisko;
        string+="  ";
        string+=Integer.toString(dataUrodzenia.getRok());
        string+="-";
        string+=Integer.toString(dataUrodzenia.getMiesiac());
        string+="-";
        string+=Integer.toString(dataUrodzenia.getDzien());

        return string;
    }
}
