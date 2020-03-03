package sample.StricteBazaProdukcji;

import sample.TypyDanychPomocnicznych.Aktor;
import sample.Symulacja.Promocja;

import java.io.Serializable;
import java.util.ArrayList;

public class Film extends Produkcja implements Serializable {
    private String gatunek;
    private ArrayList<Aktor> listaAktorow;
    private ArrayList<String> listaLinkowDoZwiastunow;
    private int czasMozliwoscOgladania;
    private Promocja promocja;

    public void kupFilm(){

    }

    public boolean sprawdzCzyFilmObjetyJestAbonamentem(){

        return false;
    }


    public Film() {
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

    public ArrayList<String> getListaLinkowDoZwiastunow() {
        return listaLinkowDoZwiastunow;
    }

    public void setListaLinkowDoZwiastunow(ArrayList<String> listaLinkowDoZwiastunow) {
        this.listaLinkowDoZwiastunow = listaLinkowDoZwiastunow;
    }

    public int getCzasMozliwoscOgladania() {
        return czasMozliwoscOgladania;
    }

    public void setCzasMozliwoscOgladania(int czasMozliwoscOgladania) {
        this.czasMozliwoscOgladania = czasMozliwoscOgladania;
    }

    public Promocja getPromocja() {
        return promocja;
    }

    public void setPromocja(Promocja promocja) {
        this.promocja = promocja;
    }
}
