package sample.StricteBazaProdukcji;

import sample.TypyDanychPomocnicznych.Data;

import java.io.Serializable;

public class OdcinekSerialu implements Serializable {
    private Data dataPremiery;
    private int dlugosc;

    public OdcinekSerialu()
    {

    }

    public Data getDataPremiery() {
        return dataPremiery;
    }

    public void setDataPremiery(Data dataPremiery) {
        this.dataPremiery = dataPremiery;
    }

    public int getDlugosc() {
        return dlugosc;
    }

    public void setDlugosc(int dlugosc) {
        this.dlugosc = dlugosc;
    }
}
