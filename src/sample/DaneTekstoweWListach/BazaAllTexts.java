package sample.DaneTekstoweWListach;

import sample.DaneTekstoweWListach.DlaAktorow.BazaImion;
import sample.DaneTekstoweWListach.DlaAktorow.BazaNazwisk;
import sample.DaneTekstoweWListach.DlaDystrybutorow.BazaNazw;
import sample.DaneTekstoweWListach.DlaProdukcji.*;
import sample.DaneTekstoweWListach.DlaUserow.BazaLoginow;
import sample.DaneTekstoweWListach.DlaUserow.BazaMaili;

/**
 * Klasa ta przechowuje Wartości różnych Stringów, aby było z czego losowwać pola w Panelach.
 * Poza tym przechowuje Stringi odpowiadające obiektom, aby przy każdym załadowaniu Panelu nie wykonywać toString();
 */
public class BazaAllTexts {

    //DLA PRODUKCJI:
    static public BazaTytulow tytuly = new BazaTytulow();
    static public BazaZdjec zdjecia = new BazaZdjec();
    static public BazaOpisow opisy = new BazaOpisow();
    static public BazaKrajow kraje = new BazaKrajow();
    static public BazaLinkowDoZwiastunow linkiDoZwiastunow = new BazaLinkowDoZwiastunow();

    //DLA AKTORÓW:
    static public BazaImion imiona = new BazaImion();
    static public BazaNazwisk nazwiska = new BazaNazwisk();


    //DLA DYSTRYBUTORÓW
    static public BazaNazw nazwyDystrybutorow = new BazaNazw();


    //DLA USERÓW
    static public BazaLoginow loginy = new BazaLoginow();
    static public BazaMaili maile = new BazaMaili();





}
