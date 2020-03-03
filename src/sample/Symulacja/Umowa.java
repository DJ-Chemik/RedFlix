package sample.Symulacja;

import java.io.Serializable;

public class Umowa implements Serializable {
    private int nrKontaBankowego;
    private float kwotaStalaZaOdtworzenie;
    private float kwotaRyczaltuMiesiecznego;

    public Umowa() {
    }

    public Umowa(int nrKontaBankowego, float kwotaStalaZaOdtworzenie, float kwotaRyczaltuMiesiecznego) {
        this.nrKontaBankowego = nrKontaBankowego;
        this.kwotaStalaZaOdtworzenie = kwotaStalaZaOdtworzenie;
        this.kwotaRyczaltuMiesiecznego = kwotaRyczaltuMiesiecznego;
    }

    public int getNrKontaBankowego() {
        return nrKontaBankowego;
    }

    public void setNrKontaBankowego(int nrKontaBankowego) {
        this.nrKontaBankowego = nrKontaBankowego;
    }

    public float getKwotaStalaZaOdtworzenie() {
        return kwotaStalaZaOdtworzenie;
    }

    public void setKwotaStalaZaOdtworzenie(float kwotaStalaZaOdtworzenie) {
        this.kwotaStalaZaOdtworzenie = kwotaStalaZaOdtworzenie;
    }

    public float getKwotaRyczaltuMiesiecznego() {
        return kwotaRyczaltuMiesiecznego;
    }

    public void setKwotaRyczaltuMiesiecznego(float kwotaRyczaltuMiesiecznego) {
        this.kwotaRyczaltuMiesiecznego = kwotaRyczaltuMiesiecznego;
    }
}
