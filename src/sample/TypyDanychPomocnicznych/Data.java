package sample.TypyDanychPomocnicznych;

import java.io.Serializable;
import java.util.Random;

public class Data implements Serializable {
    private int dzien;
    private int miesiac;
    private int rok;

    public Data() {

    }

    public Data(int dzien, int miesiac, int rok) {
        this.dzien = dzien;
        this.miesiac = miesiac;
        this.rok = rok;
    }

    public int getDzien() {
        return dzien;
    }

    public void setDzien(int dzien) {
        this.dzien = dzien;
    }

    public int getMiesiac() {
        return miesiac;
    }

    public void setMiesiac(int miesiac) {
        this.miesiac = miesiac;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public static int losujDzien(int miesiac){
        Random generator = new Random();
        int dzien;
        if (miesiac==1 || miesiac==3 || miesiac==5 || miesiac==7 || miesiac==8 || miesiac==10 || miesiac==12){
            dzien=generator.nextInt(31)+1;
        }else if (miesiac==2){
            dzien=generator.nextInt(28)+1;
        }else { //miesiąc krótki
            dzien=generator.nextInt(30)+1;
        }

        return dzien;

    }

    public static int losujMiesiac(){
        Random generator = new Random();
        int miesiac = generator.nextInt(12)+1;
        return miesiac;

    }

    public static int losujRok(int odRoku, int doRoku){
        Random generator = new Random();


        int rok = generator.nextInt(doRoku-odRoku+1) + odRoku; //losuje rok w zakresie 1970-2020

        return rok;
    }
}
