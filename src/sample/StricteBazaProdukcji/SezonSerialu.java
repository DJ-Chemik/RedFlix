package sample.StricteBazaProdukcji;

import java.io.Serializable;

public class SezonSerialu implements Serializable {
    private OdcinekSerialu[] lsitaOdcinkow;
    private int iloscOdcinkow;


    public SezonSerialu() {
    }

    public OdcinekSerialu[] getLsitaOdcinkow() {
        return lsitaOdcinkow;
    }

    public void setLsitaOdcinkow(OdcinekSerialu[] lsitaOdcinkow) {
        this.lsitaOdcinkow = lsitaOdcinkow;
    }

    public int getIloscOdcinkow() {
        return iloscOdcinkow;
    }

    public void setIloscOdcinkow(int iloscOdcinkow) {
        this.iloscOdcinkow = iloscOdcinkow;
    }
}
