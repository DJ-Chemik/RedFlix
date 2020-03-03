package sample.Symulacja;

import sample.DaneTekstoweWListach.BazaAllTexts;
import sample.InterfejsUzytkownika.DaneDlaKontrolerow;
import sample.InterfejsUzytkownika.PanelDodawaniaProdukcjiController;
import sample.Main;
import sample.StricteBazaProdukcji.Film;
import sample.StricteBazaProdukcji.Live;
import sample.StricteBazaProdukcji.Produkcja;
import sample.StricteBazaProdukcji.Serial;
import sample.TypyDanychPomocnicznych.Aktor;
import sample.TypyDanychPomocnicznych.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Dystrybutor implements Serializable, Runnable {
    private String nazwaDystrybutora;
    private Umowa umowaZNami;
    boolean czyDalejWspolpracowac = true;


    public Dystrybutor(String nazwaDystrybutora, Umowa umowaZNami) {
        this.nazwaDystrybutora = nazwaDystrybutora;
        this.umowaZNami = umowaZNami;

    }

    public Dystrybutor(String nazwaDystrybutora, int nrKonta, float kwotaJednorazowa, float ryczaltMiesieczny ) {
        this.nazwaDystrybutora = nazwaDystrybutora;
        this.umowaZNami = new Umowa(nrKonta, kwotaJednorazowa, ryczaltMiesieczny);
    }

    public void setCzyDalejWspolpracowac(boolean czyDalejWspolpracowac) {
        this.czyDalejWspolpracowac = czyDalejWspolpracowac;
    }

    @Override
    public void run(){
        Random generator = new Random();
        int x;
        int ileDniPoczekac;

        while (czyDalejWspolpracowac && Main.systemSymulacji.getZegarCzasu().isAlive()) {
            x=generator.nextInt(4); //--------------------------------------------------TUTAJ SZANSA NEGOCJACJI UMOWY: 1/x     (x-1)/x ---szansa na wypsuzczenie nowej produkcji
            if (x==0) {
                ileDniPoczekac = generator.nextInt(1)+4;   //---------------------------TUTAJ MAMY INFO ile dni musi "przespac" dystrybutor zanim wykona dana czynnosc
                SystemSymulacji.poczekajXDni(ileDniPoczekac);
                if (czyDalejWspolpracowac && Main.systemSymulacji.getZegarCzasu().isAlive()) {
                    negocjujUmowe(generator);
                    Object davidBeckham = new Object();
                    synchronized (davidBeckham){
                        Main.pulaDystrybutorow.zapiszPuleDoPliku();
                    }

                }

            }else{
                ileDniPoczekac = generator.nextInt(1)+1;    //---------------------------TUTAJ MAMY INFO ile dni musi "przespac" dystrybutor zanim wykona dana czynnosc
                SystemSymulacji.poczekajXDni(ileDniPoczekac);
                if (czyDalejWspolpracowac && Main.systemSymulacji.getZegarCzasu().isAlive()) {
                    wypuscNowaProdukcje();
                    System.out.print("Wypuszczenie nowej produkcji przez: ");
                    System.out.println(this.toString());
                }
            }

        }


    }

    public void wypuscNowaProdukcje(){
        PanelDodawaniaProdukcjiController panelDodawaniaProdukcji = new PanelDodawaniaProdukcjiController();
        Random generator = new Random();
        Produkcja produkcja = null;
        int typ = generator.nextInt(3);

        if (typ==0){ //FILM
            produkcja = new Film();
            ((Film) produkcja).setGatunek(DaneDlaKontrolerow.getGatunkiProdukcji().get(generator.nextInt(6)));

            ArrayList<Aktor> listaAktorow = new ArrayList<>();
            if (Main.pulaAktorow.pula.getSize()>3) {
                for (int i = 0; i < 3; i++) {

                    listaAktorow.add(Main.pulaAktorow.pula.getElement(generator.nextInt(Main.pulaAktorow.pula.getSize())));
                }
            }
            ((Film) produkcja).setListaAktorow(listaAktorow);

            ArrayList<String> listaZwiastunow = new ArrayList<>();
            for (int i=0;i<3;i++){

                listaZwiastunow.add(BazaAllTexts.linkiDoZwiastunow.getListaLinkow().get(
                        generator.nextInt(BazaAllTexts.linkiDoZwiastunow.getListaLinkow().size())));
            }
            ((Film) produkcja).setListaLinkowDoZwiastunow(listaZwiastunow);
            ((Film) produkcja).setPromocja(losujDatyPromocji(generator));


        }else if (typ==1){ //SERIAL
            produkcja = new Serial();
            ((Serial) produkcja).setGatunek(DaneDlaKontrolerow.getGatunkiProdukcji().get(generator.nextInt(6)));

            ArrayList<Aktor> listaAktorow = new ArrayList<>();
            if (Main.pulaAktorow.pula.getSize()>3) {
                for (int i = 0; i < 3; i++) {
                    listaAktorow.add(Main.pulaAktorow.pula.getElement(generator.nextInt(Main.pulaAktorow.pula.getSize())));
                }
            }
            ((Serial) produkcja).setListaAktorow(listaAktorow);
            ((Serial) produkcja).setIloscSezonow(generator.nextInt(20)+1);

        }else { //Live
            produkcja = new Live();

            int miesiac = Data.losujMiesiac();

            ((Live) produkcja).setDataWyswietlenia(Data.losujDzien(miesiac),miesiac,Data.losujRok(1970,2020));

            ((Live) produkcja).setPromocja(losujDatyPromocji(generator));

        }

        produkcja.setNazwa(BazaAllTexts.tytuly.getListaTytulow().get(generator.nextInt(BazaAllTexts.tytuly.getListaTytulow().size())));
        produkcja.setZdjecie(BazaAllTexts.zdjecia.getListaZdjec().get(generator.nextInt(BazaAllTexts.zdjecia.getListaZdjec().size())));
        produkcja.setOpis(BazaAllTexts.opisy.getListaOpisow().get(generator.nextInt(BazaAllTexts.opisy.getListaOpisow().size())));
        ArrayList<String> listaKrajowProdukcji = new ArrayList<>();
        for (int i=0;i<3;i++){

            listaKrajowProdukcji.add(BazaAllTexts.kraje.getListaKrajow().get(generator.nextInt(BazaAllTexts.kraje.getListaKrajow().size())));
        }
        produkcja.setKrajeProdukcji(listaKrajowProdukcji);


        produkcja.setDystrybutorProdukcji(this);
        produkcja.setCzasTrwania(generator.nextInt(299)+1);
        produkcja.setCena(generator.nextInt(60)+60);
        produkcja.setOcenaUzytkownikow((generator.nextFloat()*10 % 9) + 1);



        Object jackSparrow = new Object();
        synchronized (jackSparrow) {
            Main.pulaProduktow.pula.dodajDoPuli(produkcja);
            Main.pulaProduktow.zapiszPuleDoPliku();
        }



    }

    private Promocja losujDatyPromocji(Random generator) {
        Promocja promocja = new Promocja();
        Data data1 = new Data(1,1,1);
        Data data2 = new Data(1,1,1);
        int rok2 = Data.losujRok(2018,2020);
        int rok3 = Data.losujRok(2018,2020);
        int miesiac2 = Data.losujMiesiac();
        int miesiac3 = Data.losujMiesiac();
        if (rok3>rok2){
            data1.setRok(rok2);
            data2.setRok(rok3);
            data1.setMiesiac(miesiac2);
            data2.setMiesiac(miesiac3);
        }else{
            data1.setRok(rok3);
            data2.setRok(rok2);
            if (rok2==rok3){
                if (miesiac3>miesiac2) {
                    data1.setMiesiac(miesiac2);
                    data2.setMiesiac(miesiac3);
                }else{
                    data1.setMiesiac(miesiac3);
                    data2.setMiesiac(miesiac2);
                }
            }else{
                data1.setMiesiac(miesiac2);
                data2.setMiesiac(miesiac3);
            }
        }
        int dzien2;
        int dzien3;
        do {
            dzien2 = Data.losujDzien(4);
            dzien3 = Data.losujDzien(4);
        }while(dzien2==dzien3);
        if (dzien2<dzien3) {
            data1.setDzien(dzien2);
            data2.setDzien(dzien3);
        }else {
            data1.setDzien(dzien3);
            data2.setDzien(dzien2);
        }


        promocja.setDataRozpoczecia(data1);
        promocja.setDataZakonczenia(data2);
        promocja.setUpustProcentowy(generator.nextInt(46)+5);
        return promocja;
    }

    public void negocjujUmowe(Random generator){

        int szansaNaWynegocjowanieLepszychWarunkow = generator.nextInt(100);
        float obecnyRyczaltMiesieczny = umowaZNami.getKwotaRyczaltuMiesiecznego();
        float obecnaKwotaZaODtworzenie = umowaZNami.getKwotaStalaZaOdtworzenie();

        if (szansaNaWynegocjowanieLepszychWarunkow>80){  //------------------------------------------------------TUTAJ INFORMACJA JAKIE SA SZANSE NA NEGOCJACJE UMOWY
            float procentZysku = 100-szansaNaWynegocjowanieLepszychWarunkow; //----------------------------------TUTAJ DEFINIUJEMY ile dystrybutor zyska procent w stosunku do tego ile juz ma
            if (obecnyRyczaltMiesieczny!=0){
                umowaZNami.setKwotaRyczaltuMiesiecznego(obecnyRyczaltMiesieczny*(1+(procentZysku/100)));
                System.out.print(">>>+++Dystrybutor ");
                System.out.print(this.toString());
                System.out.print(" wynegocjował nową kwotę miesięczną: ");
                System.out.println(this.umowaZNami.getKwotaRyczaltuMiesiecznego());
            }

            if (obecnaKwotaZaODtworzenie!=0){

                umowaZNami.setKwotaStalaZaOdtworzenie(obecnaKwotaZaODtworzenie*(1+(procentZysku/100)));
                System.out.print(">>>+++Dystrybutor ");
                System.out.print(this.toString());
                System.out.print(" wynegocjował nową kwotę za odtworzenie: ");
                System.out.println(this.umowaZNami.getKwotaStalaZaOdtworzenie());

            }
        }else{
            System.out.print("---Dystrybutor ");
            System.out.print(this.toString());
            System.out.println(" nie wynegocjował nowej umowy.");

        }
    }

    public Umowa getUmowaZNami() {
        return umowaZNami;
    }

    @Override
    public String toString(){
        String wynik=nazwaDystrybutora;
        wynik += " [";
        wynik += Float.toString(umowaZNami.getKwotaStalaZaOdtworzenie());
        wynik += ", ";
        wynik += Float.toString(umowaZNami.getKwotaRyczaltuMiesiecznego());
        wynik += "] ";
        return wynik;
    }
}
