package sample.StricteBazaProdukcji;

import sample.TypyDanychPomocnicznych.Aktor;

import java.io.Serializable;
import java.util.ArrayList;

public class Serial extends Produkcja implements Serializable {
    private String gatunek;
    private ArrayList<Aktor> listaAktorow;
    private ArrayList<SezonSerialu> listaSezonow;
    private int iloscSezonow;


    public void kupOdcinek() {

    }

    public boolean sprawdzCzyObjetyAbonamentem() {
        return false;
    }



    public Serial() {

    }

    public void losujWartosciWszystkichPol(){


    }

    public String getGatunek() {
        return gatunek;
    }

    public void setGatunek(String gatunek) {
        this.gatunek = gatunek;
    }

    public ArrayList<Aktor> getListaAktorow() {
        return listaAktorow;
    }

    public void setListaAktorow(ArrayList<Aktor> listaAktorow) {
        this.listaAktorow = listaAktorow;
    }

    public void dodajAktora(Aktor aktor) {
        this.listaAktorow.add(aktor);
    }

    public SezonSerialu[] getListaSezonow() {

        SezonSerialu[] tablicaSezonow = new SezonSerialu[this.iloscSezonow];
        for (int i=0;i<this.iloscSezonow;i++)
        {
            tablicaSezonow[i]= this.listaSezonow.get(i);
        }
        return tablicaSezonow;
    }

    public void dodajSezon(SezonSerialu sezon) {
        this.listaSezonow.add(sezon);
    }

    public int getIloscSezonow() {
        return iloscSezonow;
    }

    public void setIloscSezonow(int iloscSezonow) {
        this.iloscSezonow = iloscSezonow;
    }



}
