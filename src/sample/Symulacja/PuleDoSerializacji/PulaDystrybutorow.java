package sample.Symulacja.PuleDoSerializacji;

import sample.KlasyBledow.TypuException.NieOdnalezionoPlikuDoSerializacjiException;
import sample.Symulacja.Dystrybutor;

import java.io.*;

public class PulaDystrybutorow extends Pula implements Serializable {

    public PulaGeneryczna<Dystrybutor> pula = new PulaGeneryczna<>();

    private  transient String standardowaNazwaPliku = new String("pulaDystrybutorow.txt");

    public void wczytajPuleZPliku() {
        wczytajPuleZPliku(this.standardowaNazwaPliku);
    }

    public  void zapiszPuleDoPliku(){

        ObjectOutputStream out = null;

        try {

            out = new ObjectOutputStream(
                    new BufferedOutputStream(new FileOutputStream(standardowaNazwaPliku)));

            out.writeObject(pula);
            out.close();

        }catch (IOException e){
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
            if (!file.exists()){
                throw new NieOdnalezionoPlikuDoSerializacjiException();
            }

            in = new ObjectInputStream(
                    new BufferedInputStream(
                            new FileInputStream(nazwaPlikuDoSerializacji)));
            this.pula = (PulaGeneryczna<Dystrybutor>) in.readObject();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Brak pliku\n");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            System.out.println("Nie odnaleziono klasy - nieznany typ\n");
        }finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }


    /*private List<Dystrybutor> pula = new ArrayList<>();

    public PulaDystrybutorow() {
        this.pula = new ArrayList<>();
    }

    public void dodajDoPuli(Dystrybutor dystrybutor ){
        pula.add(dystrybutor);
    }

    public Dystrybutor getElement(int indeks){

        try {
            if (indeks<pula.size()) {
                return pula.get(indeks);
            }
            else{
                throw new PrzekroczonyZakresPuli();
            }
        }catch (PrzekroczonyZakresPuli e){
            System.out.println("Przekroczono zakres puli(Zwracam NULL)\n");
            return null;
        }
    }

    public void usunZPuli(Dystrybutor dystrybutor ){
        pula.remove(dystrybutor);
    }

    public void usunZPuli(int numerNaLiscie){
        this.pula.remove(numerNaLiscie);
    }

    public int getSize() {
        return pula.size();
    }

    public void usunCalaPule(){
        pula.removeAll(pula);

    }*/
}
