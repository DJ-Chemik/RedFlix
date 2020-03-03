package sample.Symulacja.Abonamenty;

import java.io.Serializable;

public class Abonament implements Serializable {
    private float cena;
    private int liczbaUrzadzen;
    private int[] maxRozdzielczoscProduktow = new int[2];


    public Abonament() {

    }

    public float getCena() {
        return cena;
    }

    public void setCena(float cena) {
        this.cena = cena;
    }

    public int getLiczbaUrzadzen() {
        return liczbaUrzadzen;
    }

    public void setLiczbaUrzadzen(int liczbaUrzadzen) {
        this.liczbaUrzadzen = liczbaUrzadzen;
    }

    public int[] getMaxRozdzielczoscProduktow() {
        return maxRozdzielczoscProduktow;
    }

    public void setMaxRozdzielczoscProduktow(int[] maxRozdzielczoscProduktow) {
        this.maxRozdzielczoscProduktow = maxRozdzielczoscProduktow;
    }
}
