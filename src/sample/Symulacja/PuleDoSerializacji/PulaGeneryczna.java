package sample.Symulacja.PuleDoSerializacji;

import sample.KlasyBledow.TypuRuntimeException.PrzekroczonyZakresPuli;
import sample.StricteBazaProdukcji.Produkcja;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class PulaGeneryczna<T> implements Serializable {

    private List<T> pula = new ArrayList<T>(); //niech będzie package-private wtedy tylko inne pule mogą ją widzieć

    public PulaGeneryczna() {
    }

    public void dodajDoPuli(T byt){
        pula.add(byt);
    }

    public T getElement(int indeks){

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

    public void usunZPuli (T byt){
        pula.remove(byt);
    }

    public int indexOf (T byt){

        return pula.indexOf(byt);
    }

    public void usunZPuli (int indeks){
        pula.remove(indeks);
    }

    public void usunZPuli (Collection<Produkcja> listaDoUsuniecia){
        pula.removeAll(listaDoUsuniecia);
    }

    public int getSize(){
        return pula.size();
    }

    public void usunCalaPule(){
        pula.removeAll(pula);
    }

    public List<T> getPula() {
        return pula;
    }
}
