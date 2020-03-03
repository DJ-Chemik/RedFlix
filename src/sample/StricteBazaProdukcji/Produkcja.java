package sample.StricteBazaProdukcji;

import sample.Symulacja.Dystrybutor;

import java.io.Serializable;
import java.util.ArrayList;

public abstract class Produkcja implements Serializable {
    private String nazwa;
    private String zdjecie;
    private String opis;
    private int czasTrwania;
    private Dystrybutor dystrybutorProdukcji;
    private ArrayList<String> krajeProdukcji;
    private float ocenaUzytkownikow;
    private float cena;

    public boolean wyszukajProdukcjeNaPodstawieFrazy(String fraza)
    {

        return false;
    }

    public Produkcja()
    {

    }

    public void losujWartosciWszystkichPol(){


    }


    public String getNazwa() {
        return nazwa;
    }

    public void setNazwa(String nazwa) {
        this.nazwa = nazwa;
    }

    public String getZdjecie() {
        return zdjecie;
    }

    public void setZdjecie(String zdjecie) {
        this.zdjecie = zdjecie;
    }

    public String getOpis() {
        return opis;
    }

    public void setOpis(String opis) {
        this.opis = opis;
    }

    public int getCzasTrwania() {
        return czasTrwania;
    }

    public void setCzasTrwania(int czasTrwania) {
        this.czasTrwania = czasTrwania;
    }

    public Dystrybutor getDystrybutorProdukcji() {
        return dystrybutorProdukcji;
    }

    public void setDystrybutorProdukcji(Dystrybutor dystrybutorProdukcji) {
        this.dystrybutorProdukcji = dystrybutorProdukcji;
    }

    public ArrayList<String> getKrajeProdukcji() {
        return krajeProdukcji;
    }

    public void setKrajeProdukcji(ArrayList<String> krajeProdukcji) {
        this.krajeProdukcji = krajeProdukcji;
    }

    public void addKrajProdukcji(String kraj){
        this.krajeProdukcji.add(kraj);
    }

    public float getOcenaUzytkownikow() {
        return ocenaUzytkownikow;
    }

    public void setOcenaUzytkownikow(float ocenaUzytkownikow) {
        this.ocenaUzytkownikow = ocenaUzytkownikow;
    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }



}
