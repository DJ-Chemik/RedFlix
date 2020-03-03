package sample.Symulacja;

public class SystemFinansowy
{
    private volatile float stanSystemuFinansowego;

    private volatile float miesieczneZobowiazaniaWzgledemDystrybutorow;

    public SystemFinansowy() {
        this.stanSystemuFinansowego = 0;
    }

    public float getStanSystemuFinansowego() {
        return stanSystemuFinansowego;
    }

    public void zmodyfikujStanSystemuFinansowegoOX(float kwotaModyfikacji){
        this.stanSystemuFinansowego+=kwotaModyfikacji;
    }

    public float getMiesieczneZobowiazaniaWzgledemDystrybutorow() {
        return miesieczneZobowiazaniaWzgledemDystrybutorow;
    }

    public void zwiekszZobowiazaniaMiesieczneOX(float kwotaWzrostu) {
        this.miesieczneZobowiazaniaWzgledemDystrybutorow += kwotaWzrostu;
    }
}
