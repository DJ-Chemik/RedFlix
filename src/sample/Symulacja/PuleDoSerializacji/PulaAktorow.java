package sample.Symulacja.PuleDoSerializacji;

import sample.KlasyBledow.TypuException.NieOdnalezionoPlikuDoSerializacjiException;
import sample.KlasyBledow.TypuRuntimeException.PrzekroczonyZakresPuli;
import sample.Symulacja.Dystrybutor;
import sample.TypyDanychPomocnicznych.Aktor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class PulaAktorow extends Pula implements Serializable {


    public PulaGeneryczna<Aktor> pula = new PulaGeneryczna<>();

    private transient String standardowaNazwaPliku = new String("pulaAktorow.txt");

    public void wczytajPuleZPliku() {
        wczytajPuleZPliku(this.standardowaNazwaPliku);
    }

    public void zapiszPuleDoPliku() {

        ObjectOutputStream out = null;

        try {

            out = new ObjectOutputStream(
                    new BufferedOutputStream(new FileOutputStream(standardowaNazwaPliku)));

            out.writeObject(pula);
            out.close();

        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Błąd otwierania pliku\n");
        }/*finally {
            try {
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
                System.out.println("Błąd zamykania pliku\n");
            }
        }*/
    }

    public void wczytajPuleZPliku(String nazwaPlikuDoSerializacji) {

        ObjectInputStream in = null;

        try {
            File file = new File(nazwaPlikuDoSerializacji);
            if (!file.exists()) {
                throw new NieOdnalezionoPlikuDoSerializacjiException();
            }

            in = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(nazwaPlikuDoSerializacji)));
            this.pula = (PulaGeneryczna<Aktor>) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Brak pliku\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Nie odnaleziono klasy - nieznany typ\n");
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
