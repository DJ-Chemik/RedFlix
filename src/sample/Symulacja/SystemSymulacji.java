package sample.Symulacja;

import sample.DaneTekstoweWListach.BazaAllTexts;
import sample.Main;
import sample.Symulacja.Abonamenty.*;
import sample.TypyDanychPomocnicznych.Data;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SystemSymulacji implements Serializable {

    private List<Thread> watkiDystrybutorow = new ArrayList<>();
    private List<Thread> watkiUserow = new ArrayList<>();
    private volatile SystemFinansowy systemFinansowy = new SystemFinansowy();
    private Thread zegarCzasu;
    private static int jedenDzienStatic = 5 * 1000; //jeden dzień to pięć sekund



    public void stworzWatekCzasu() {
        zegarCzasu = new Thread(new ZegarCzasu());
        zegarCzasu.setDaemon(true);
        zegarCzasu.start();
    }

    public synchronized void stworzWatekDystrybutora(Dystrybutor dystrybutor) {

        watkiDystrybutorow.add(new Thread(dystrybutor));
        this.systemFinansowy.zwiekszZobowiazaniaMiesieczneOX(dystrybutor.getUmowaZNami().getKwotaRyczaltuMiesiecznego());
        watkiDystrybutorow.get(watkiDystrybutorow.size() - 1).setDaemon(true);
        watkiDystrybutorow.get(watkiDystrybutorow.size() - 1).start();

    }

    public synchronized void usunWatekDystrybutora(Dystrybutor dystrybutorDoUsuniecia) {

        Main.pulaDystrybutorow.pula.getElement(Main.pulaDystrybutorow.pula.indexOf(dystrybutorDoUsuniecia)).setCzyDalejWspolpracowac(false);
        Main.systemSymulacji.systemFinansowy.zwiekszZobowiazaniaMiesieczneOX((-1) * dystrybutorDoUsuniecia.getUmowaZNami().getKwotaRyczaltuMiesiecznego());
        watkiDystrybutorow.remove(dystrybutorDoUsuniecia);
    }

    public void stworzWatekUsera(User user) {

        watkiDystrybutorow.add(new Thread(user));
        watkiDystrybutorow.get(watkiDystrybutorow.size() - 1).setDaemon(true);
        watkiDystrybutorow.get(watkiDystrybutorow.size() - 1).start();

    }

    public void usunWatekUsera(User userDoUsuniecia) {
        Main.pulaUserow.pula.getElement(Main.pulaUserow.pula.indexOf(userDoUsuniecia)).setCzyDalejWspolpracowac(false);
        watkiUserow.remove(userDoUsuniecia);
    }


    public void odpalWatkiDystrybutorowIUserowPrzyStarcie() {
        for (int i = 0; i < Main.pulaDystrybutorow.pula.getSize(); i++) {
            stworzWatekDystrybutora(Main.pulaDystrybutorow.pula.getElement(i));
        }

        for (int i = 0; i < Main.pulaUserow.pula.getSize(); i++) {
            stworzWatekUsera(Main.pulaUserow.pula.getElement(i));
        }
    }

    public static void automatycznieDodajUserow() {
        Random generator = new Random();
        boolean warunek1 = Main.pulaProduktow.pula.getSize() % 3 == 0;
        boolean warunek2 = false;

        if (Main.pulaUserow.pula.getSize()!=0){
            warunek2 = Main.pulaProduktow.pula.getSize() / Main.pulaUserow.pula.getSize() >= 3;
        }

        if (warunek2) {

            int kod = generator.nextInt(1_000_000_000);
            String login = BazaAllTexts.loginy.getBazaLoginow().get(generator.nextInt(BazaAllTexts.loginy.getBazaLoginow().size()));
            int nrKarty[] = new int[4];
            for (int i = 0; i < 4; i++) {
                nrKarty[i] = generator.nextInt(8999) + 1000;
            }
            int nrKarty1 = nrKarty[0];
            int nrKarty2 = nrKarty[1];
            int nrKarty3 = nrKarty[2];
            int nrKarty4 = nrKarty[3];
            String email = BazaAllTexts.maile.getBazaMaili().get(generator.nextInt(BazaAllTexts.loginy.getBazaLoginow().size()));
            int miesiac = Data.losujMiesiac();
            int dzien = Data.losujDzien(miesiac);
            int rok = Data.losujRok(1940, 2020);
            Data data = new Data(dzien, miesiac, rok);
            Abonament abonament = null;
            switch (generator.nextInt(4)) {
                case 0:
                    abonament = new AbonamentNieistniejacy();
                    break;
                case 1:
                    abonament = new AbonamentBasic();
                    break;
                case 2:
                    abonament = new AbonamentFamily();
                    break;
                case 3:
                    abonament = new AbonamentPremium();
                    break;
            }
            User user = new User(kod, login, data, email, nrKarty1, nrKarty2, nrKarty3, nrKarty4, abonament);
            Main.systemSymulacji.stworzWatekUsera(user);
            Main.pulaUserow.pula.dodajDoPuli(user);
            Main.pulaUserow.zapiszPuleDoPliku();
            System.out.println("Dodano Użytkownika");

        }
    }

    public static void poczekajXDni(int xDni) { //xD
        try {
            Thread.sleep(jedenDzienStatic * xDni);
        } catch (InterruptedException e) {
            e.printStackTrace();
            System.out.println("----------Błąd w Systemie Symulacji w czekaniu jeden dzień-------");
        }
    }

    public Thread getZegarCzasu() {
        return zegarCzasu;
    }

    public SystemFinansowy getSystemFinansowy() {
        return systemFinansowy;
    }

    public void setSystemFinansowy(SystemFinansowy systemFinansowy) {
        this.systemFinansowy = systemFinansowy;
    }

    public List<Thread> getWatkiDystrybutorow() {
        return watkiDystrybutorow;
    }
}
