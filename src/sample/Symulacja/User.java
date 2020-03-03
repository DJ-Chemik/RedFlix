package sample.Symulacja;

import sample.Main;
import sample.Symulacja.Abonamenty.Abonament;
import sample.StricteBazaProdukcji.Produkcja;
import sample.TypyDanychPomocnicznych.Data;

import java.io.Serializable;
import java.util.Random;

public class User implements Serializable, Runnable {
    private int kod;
    private String login;
    private Data dataUrodzenia;
    private String email;
    private int nrKarty1;
    private int nrKarty2;
    private int nrKarty3;
    private int nrKarty4;
    private Abonament posiadanyAbonament;

    private boolean czyDalejWspolpracowac = true;



    public void obejrzyjProdukcje(Produkcja produkcja, Random generator){

        int indeksProdukcji=generator.nextInt(Main.pulaProduktow.pula.getSize());
        float cenaProdukcji=Main.pulaProduktow.pula.getElement(indeksProdukcji).getCena();
        float kwotaDlaDystrybutora=Main.pulaProduktow.pula.getElement(indeksProdukcji).getDystrybutorProdukcji().getUmowaZNami().getKwotaStalaZaOdtworzenie();

        Object carmenElektra = new Object();
        synchronized (carmenElektra){
            Main.systemSymulacji.getSystemFinansowy().zmodyfikujStanSystemuFinansowegoOX(cenaProdukcji-kwotaDlaDystrybutora);
        }


    }

    public void kupAbonament(Abonament nowyAbonament){

    }

    @Override
    public void run(){

        Random generator = new Random();
        int ileDniPoczekac;

        while (czyDalejWspolpracowac && Main.systemSymulacji.getZegarCzasu().isAlive()) {
            ileDniPoczekac = generator.nextInt(3)+1;
            SystemSymulacji.poczekajXDni(ileDniPoczekac);
            int indeksProdukcji = generator.nextInt(Main.pulaProduktow.pula.getSize());
            this.obejrzyjProdukcje(Main.pulaProduktow.pula.getElement(indeksProdukcji), generator);

        }

    }


    public User(int kod, String login, Data dataUrodzenia, String email, int nrKarty1, int nrKarty2, int nrKarty3, int nrKarty4, Abonament posiadanyAbonament) {
        this.kod = kod;
        this.login = login;
        this.dataUrodzenia = dataUrodzenia;
        this.email = email;
        this.nrKarty1 = nrKarty1;
        this.nrKarty2 = nrKarty2;
        this.nrKarty3 = nrKarty3;
        this.nrKarty4 = nrKarty4;
        this.posiadanyAbonament = posiadanyAbonament;
    }

    @Override
    public String toString(){
        String wynik=login;
        wynik += " <";
        wynik += Integer.toString(kod);
        wynik += "> ";
        return wynik;
    }

    public int getKod() {
        return kod;
    }

    public void setKod(int kod) {
        this.kod = kod;
    }

    public int getNrKarty1() {
        return nrKarty1;
    }

    public void setNrKarty1(int nrKarty1) {
        this.nrKarty1 = nrKarty1;
    }

    public int getNrKarty2() {
        return nrKarty2;
    }

    public void setNrKarty2(int nrKarty2) {
        this.nrKarty2 = nrKarty2;
    }

    public int getNrKarty3() {
        return nrKarty3;
    }

    public void setNrKarty3(int nrKarty3) {
        this.nrKarty3 = nrKarty3;
    }

    public int getNrKarty4() {
        return nrKarty4;
    }

    public void setNrKarty4(int nrKarty4) {
        this.nrKarty4 = nrKarty4;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public Data getDataUrodzenia() {
        return dataUrodzenia;
    }

    public void setDataUrodzenia(Data dataUrodzenia) {
        this.dataUrodzenia = dataUrodzenia;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Abonament getPosiadanyAbonament() {
        return posiadanyAbonament;
    }

    public void setPosiadanyAbonament(Abonament posiadanyAbonament) {
        this.posiadanyAbonament = posiadanyAbonament;
    }

    public void setCzyDalejWspolpracowac(boolean czyDalejWspolpracowac) {
        this.czyDalejWspolpracowac = czyDalejWspolpracowac;
    }
}
