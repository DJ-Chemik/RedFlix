package sample.InterfejsUzytkownika;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.KlasyBledow.TypuException.NieprawidlowaLiczbaException;
import sample.Main;
import sample.StricteBazaProdukcji.Film;
import sample.StricteBazaProdukcji.Live;
import sample.StricteBazaProdukcji.Produkcja;
import sample.StricteBazaProdukcji.Serial;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class PanelGlownyController extends Panel {

    @FXML
    private ComboBox comboBoxWyborTypuProdukcji;
    @FXML
    private CheckBox checkAkcja;
    @FXML
    private CheckBox checkKomedia;
    @FXML
    private CheckBox checkSensacyjny;
    @FXML
    private CheckBox checkDramat;
    @FXML
    private CheckBox checkDokumentalny;
    @FXML
    private CheckBox checkDlaDzieci;
    @FXML
    private Button szukajButton;
    @FXML
    private TextField szukajTextField;
    @FXML
    private Button panelAdministratoraButton;
    @FXML
    private TextField textNumerStrony;
    @FXML
    private Label textMaxStron;


    @FXML
    private ImageView image1;
    @FXML
    private ImageView image2;
    @FXML
    private ImageView image3;
    @FXML
    private ImageView image4;
    @FXML
    private ImageView image5;
    @FXML
    private ImageView image6;
    @FXML
    private ImageView image7;
    @FXML
    private ImageView image8;
    @FXML
    private ImageView image9;
    @FXML
    private ImageView image10;
    @FXML
    private ImageView image11;
    @FXML
    private ImageView image12;

    @FXML
    private Label labelTytul1;
    @FXML
    private Label labelTytul2;
    @FXML
    private Label labelTytul3;
    @FXML
    private Label labelTytul4;
    @FXML
    private Label labelTytul5;
    @FXML
    private Label labelTytul6;
    @FXML
    private Label labelTytul7;
    @FXML
    private Label labelTytul8;
    @FXML
    private Label labelTytul9;
    @FXML
    private Label labelTytul10;
    @FXML
    private Label labelTytul11;
    @FXML
    private Label labelTytul12;



    private ArrayList<Produkcja> lista12Tytulow = new ArrayList<>();

    //Czy te dwie listy w związku z ich implementacją są dobrze użyte? Czy może lepiej jakby były 2 LinkedList, albo same Array?
    private List<Produkcja> kopiaPuliProduktowDokładna = new ArrayList<>(Main.pulaProduktow.pula.getPula());
    private List<Produkcja> kopiaPuliProduktowOperacyjna = new LinkedList<>(Main.pulaProduktow.pula.getPula());

    private int wyswietlanaStrona = 1;

    private int iloscProdukcjiPoKwerendzie = Main.pulaProduktow.pula.getSize(); //zmienia się przy zawężaniu wyników

    private static Produkcja produkcjaWybranaZPaneluGlownego;


    @FXML
    public void openPanelAdministratora(){
        openPanel("PlikiFXML/PanelAdministratora.fxml");
    }


    public void openPanelInformacyjny(){
        openPanel("PlikiFXML/PanelInfoProdukcja.fxml");
    };


    @FXML
    public void initialize() {

        this.comboBoxWyborTypuProdukcji.getItems().setAll(DaneDlaKontrolerow.typyProdukcji);
        refreshScreen();

    }

    private void refreshScreen(){

        textNumerStrony.setText(Integer.toString(wyswietlanaStrona));
        if (iloscProdukcjiPoKwerendzie%12!=0 || iloscProdukcjiPoKwerendzie==0){

            textMaxStron.setText(Integer.toString((iloscProdukcjiPoKwerendzie/12)+1));
        }else{
            textMaxStron.setText(Integer.toString((iloscProdukcjiPoKwerendzie/12)));
        }
        zaladujListe12Tytulow();
    }

    private void zaladujListe12Tytulow(){
        wylaczNiepotrzebneMiniaturki();
        Label[] tablicaLabeli = {null, labelTytul1, labelTytul2, labelTytul3,labelTytul4,
                labelTytul5,labelTytul6,labelTytul7,labelTytul8,
                labelTytul9,labelTytul10,labelTytul11,labelTytul12};

        ImageView[] tablicaMiniaturek = {null,image1,image2,image3,image4,image5,image6,
                image7,image8,image9,image10,image11,image12};

        lista12Tytulow.clear();

        int iloscProdukcji=iloscProdukcjiPoKwerendzie;
        int ileZostalo=iloscProdukcji%12;
        if (wyswietlanaStrona==Integer.parseInt(textMaxStron.getText())){ //jeśli jesteśmy na ostatniej stronie

            for (int i=0;i<ileZostalo;i++){ //ładujemy niepełną ilość produkcji (bo już zostało mniej niż 12)
                //lista12Tytulow.add(Main.pulaProduktow.pula.getElement(((wyswietlanaStrona-1)*12)+i)); --------------------------------------//STARE BAZUJĄCE NA ORYGINALNEJ PULI
                lista12Tytulow.add(kopiaPuliProduktowOperacyjna.get(((wyswietlanaStrona-1)*12)+i));
                tablicaLabeli[i+1].setText(lista12Tytulow.get(i).getNazwa());
                tablicaMiniaturek[i+1].setImage(new Image(lista12Tytulow.get(i).getZdjecie()));
            }

            if (ileZostalo==0 && iloscProdukcji!=0){
                for (int i=0;i<12;i++){
                    //lista12Tytulow.add(Main.pulaProduktow.pula.getElement(((wyswietlanaStrona-1)*12)+i)); ----------------------------------//STARE j.w.
                    lista12Tytulow.add(kopiaPuliProduktowOperacyjna.get(((wyswietlanaStrona-1)*12)+i));
                    tablicaLabeli[i+1].setText(lista12Tytulow.get(i).getNazwa());
                    tablicaMiniaturek[i+1].setImage(new Image(lista12Tytulow.get(i).getZdjecie()));
                }
            }


        }else{

            for (int i=0;i<12;i++){
                //lista12Tytulow.add(Main.pulaProduktow.pula.getElement(((wyswietlanaStrona-1)*12)+i));----------------------------STARE JAK WYZĘJ
                lista12Tytulow.add(kopiaPuliProduktowOperacyjna.get(((wyswietlanaStrona-1)*12)+i));
                tablicaLabeli[i+1].setText(lista12Tytulow.get(i).getNazwa());
                tablicaMiniaturek[i+1].setImage(new Image(lista12Tytulow.get(i).getZdjecie()));
            }
        }

    }

    /**
     * Metoda ta sprawdza czy jestesmy na ostatniej stronie (tej gdzie może wystąpić
     * niepełna ilość produkcji).
     * Jeśli tak to ukrywa niepotrzebne elementy
     */
    private void wylaczNiepotrzebneMiniaturki(){

        /*DLACZEGO TO NIE DZIAŁA POZA CIALEM TEJ METODY???
        * gdy zadeklarowałem te tablice jako private pola, to program nie działał.
        * Jeśli przy sprawdzaniu natknął Pan na to pytanie to proszę o wyjaśnienie mi tej kwestii, jeśli to nie problem
        * (przydałaby mi  się ta wiedza na przyszłosć)*/
        Label[] tablicaLabeli = {null, labelTytul1, labelTytul2, labelTytul3,labelTytul4,
                labelTytul5,labelTytul6,labelTytul7,labelTytul8,
                labelTytul9,labelTytul10,labelTytul11,labelTytul12};

        ImageView[] tablicaMiniaturek = {null,image1,image2,image3,image4,image5,image6,
                image7,image8,image9,image10,image11,image12};

        int iloscProdukcji=iloscProdukcjiPoKwerendzie;
        int ileZostalo=iloscProdukcji%12;

        if (wyswietlanaStrona==Integer.parseInt(textMaxStron.getText()) && ileZostalo!=0 || iloscProdukcji==0){

            for (int i=ileZostalo+1;i<=12;i++){
                tablicaLabeli[i].setVisible(false);
                tablicaMiniaturek[i].setVisible(false);
            }

        }else{
            for (int i=1;i<=12;i++) {
                tablicaLabeli[i].setVisible(true);
                tablicaMiniaturek[i].setVisible(true);
            }
        }

    }

    @FXML
    private void wybranieTypuProdukcjiZComboBoxa() {

        deaktywacjaNiepotrzebnychCheckBoxow();
        checkSensacyjny.setSelected(true);
        checkKomedia.setSelected(true);
        checkAkcja.setSelected(true);
        checkDramat.setSelected(true);
        checkDokumentalny.setSelected(true);
        checkDlaDzieci.setSelected(true);

        zawezWynikiTyp();

    }

    private void zawezWynikiTyp() {
        wyswietlanaStrona=1;
        kopiaPuliProduktowOperacyjna.clear();
        kopiaPuliProduktowOperacyjna.addAll(kopiaPuliProduktowDokładna);
        iloscProdukcjiPoKwerendzie = kopiaPuliProduktowDokładna.size();


        for (int i=0;i<kopiaPuliProduktowOperacyjna.size();i++){
            if (this.comboBoxWyborTypuProdukcji.getValue() == "Wydarzenie na Żywo"){

                if (!(kopiaPuliProduktowOperacyjna.get(i) instanceof Live)) {
                    kopiaPuliProduktowOperacyjna.remove(kopiaPuliProduktowOperacyjna.get(i));
                    iloscProdukcjiPoKwerendzie--;
                    i--;

                }

            }else if (this.comboBoxWyborTypuProdukcji.getValue() == "Serial"){

                if (!(kopiaPuliProduktowOperacyjna.get(i) instanceof Serial)) {
                    kopiaPuliProduktowOperacyjna.remove(kopiaPuliProduktowOperacyjna.get(i));
                    iloscProdukcjiPoKwerendzie--;
                    i--;

                }

            }else if (this.comboBoxWyborTypuProdukcji.getValue() == "Film"){

                if (!(kopiaPuliProduktowOperacyjna.get(i) instanceof Film)) {

                    kopiaPuliProduktowOperacyjna.remove(kopiaPuliProduktowOperacyjna.get(i));
                    iloscProdukcjiPoKwerendzie--;
                    i--; //Po usuwaniu zmniejsza nam sie rozmiar kopii, więc trzeba skompensować postepowanie pętli for

                }

            }
            refreshScreen();
        }
    }

    @FXML
    private void zawezWynikiGatunek(){


        zawezWynikiTyp();

        if (!checkAkcja.isSelected()){
            porownajCzyGatunekSieZgadza("Akcja");
        }
        if (!checkKomedia.isSelected()){
            porownajCzyGatunekSieZgadza("Komedia");
        }
        if (!checkSensacyjny.isSelected()){
            porownajCzyGatunekSieZgadza("Sensacyjny");
        }
        if (!checkDramat.isSelected()){
            porownajCzyGatunekSieZgadza("Dramat");
        }
        if (!checkDokumentalny.isSelected()){
            porownajCzyGatunekSieZgadza("Dokumentalny");
        }
        if (!checkDlaDzieci.isSelected()){
            porownajCzyGatunekSieZgadza("Dla Dzieci");
        }

        refreshScreen();
    }

    private void porownajCzyGatunekSieZgadza(String gatunek) {

        if(iloscProdukcjiPoKwerendzie!=0){

            for (int i=0;i<kopiaPuliProduktowOperacyjna.size();i++){
                if (kopiaPuliProduktowOperacyjna.get(i) instanceof Film) {
                    if(((Film) kopiaPuliProduktowOperacyjna.get(i)).getGatunek().equals(gatunek)){
                        kopiaPuliProduktowOperacyjna.remove(kopiaPuliProduktowOperacyjna.get(i));
                        iloscProdukcjiPoKwerendzie--;
                        if (i!=0)
                            i--; //Po usuwaniu zmniejsza nam sie rozmiar kopii, więc trzeba skompensować postepowanie pętli for
                    }
                }
                if (kopiaPuliProduktowOperacyjna.get(i) instanceof Serial) {
                    if(((Serial) kopiaPuliProduktowOperacyjna.get(i)).getGatunek().compareTo(gatunek)==0){
                        kopiaPuliProduktowOperacyjna.remove(kopiaPuliProduktowOperacyjna.get(i));
                        iloscProdukcjiPoKwerendzie--;
                        if (i!=0)
                            i--;
                    }
                }

            }
        }
    }


    @FXML
    private void szukajProdukcji(){

    }

    @FXML
    private void poprzedniaStrona(){
        if (wyswietlanaStrona!=1){
            wyswietlanaStrona--;
        }
        refreshScreen();
    }

    @FXML
    private void onEnter(){
        try {
            int x=Integer.parseInt(textNumerStrony.getText());
            if (x>=1 && x<=(iloscProdukcjiPoKwerendzie/12)+1){
                wyswietlanaStrona=x;
            }else{
                throw new NieprawidlowaLiczbaException();
            }
        }catch (NieprawidlowaLiczbaException e){
            System.out.println("Nieprawidłowa liczba w polu tekstowym do wyboru strony");
        }
        refreshScreen();

    }

    @FXML
    private void wyczyscTextNumerStrony(){
        textNumerStrony.clear();
    }

    @FXML
    private void przywrocTextNumerStrony(){
        textNumerStrony.setText(Integer.toString(wyswietlanaStrona));
    }

    @FXML
    private void nastepnaStrona(){
        if (wyswietlanaStrona!=Integer.parseInt(textMaxStron.getText())){

            wyswietlanaStrona++;
        }
        refreshScreen();
    }

    private void deaktywacjaNiepotrzebnychCheckBoxow() {
        if (this.comboBoxWyborTypuProdukcji.getValue() == "Wydarzenie na Żywo") {
            this.checkAkcja.setVisible(false);
            this.checkKomedia.setVisible(false);
            this.checkSensacyjny.setVisible(false);
            this.checkDokumentalny.setVisible(false);
            this.checkDramat.setVisible(false);
            this.checkDlaDzieci.setVisible(false);
        } else {
            this.checkAkcja.setVisible(true);
            this.checkKomedia.setVisible(true);
            this.checkSensacyjny.setVisible(true);
            this.checkDokumentalny.setVisible(true);
            this.checkDramat.setVisible(true);
            this.checkDlaDzieci.setVisible(true);
        }
    }

    public static Produkcja getProdukcjaWybranaZPaneluGlownego() {
        return produkcjaWybranaZPaneluGlownego;
    }



    @FXML
    private void openPanelInformacyjny1(){
        produkcjaWybranaZPaneluGlownego = lista12Tytulow.get(0);
        openPanelInformacyjny();

    }

    @FXML
    private void openPanelInformacyjny2(){
        produkcjaWybranaZPaneluGlownego = lista12Tytulow.get(1);
        openPanelInformacyjny();
    }

    @FXML
    private void openPanelInformacyjny3(){
        produkcjaWybranaZPaneluGlownego = lista12Tytulow.get(2);
        openPanelInformacyjny();
    }

    @FXML
    private void openPanelInformacyjny4(){
        produkcjaWybranaZPaneluGlownego = lista12Tytulow.get(3);
        openPanelInformacyjny();
    }

    @FXML
    private void openPanelInformacyjny5(){
        produkcjaWybranaZPaneluGlownego = lista12Tytulow.get(4);
        openPanelInformacyjny();
    }

    @FXML
    private void openPanelInformacyjny6(){
        produkcjaWybranaZPaneluGlownego = lista12Tytulow.get(5);
        openPanelInformacyjny();
    }

    @FXML
    private void openPanelInformacyjny7(){
        produkcjaWybranaZPaneluGlownego = lista12Tytulow.get(6);
        openPanelInformacyjny();
    }

    @FXML
    private void openPanelInformacyjny8(){
        produkcjaWybranaZPaneluGlownego = lista12Tytulow.get(7);
        openPanelInformacyjny();
    }

    @FXML
    private void openPanelInformacyjny9(){
        produkcjaWybranaZPaneluGlownego = lista12Tytulow.get(8);
        openPanelInformacyjny();
    }

    @FXML
    private void openPanelInformacyjny10(){
        produkcjaWybranaZPaneluGlownego = lista12Tytulow.get(9);
        openPanelInformacyjny();
    }

    @FXML
    private void openPanelInformacyjny11(){
        produkcjaWybranaZPaneluGlownego = lista12Tytulow.get(10);
        openPanelInformacyjny();
    }

    @FXML
    private void openPanelInformacyjny12(){
        produkcjaWybranaZPaneluGlownego = lista12Tytulow.get(11);
        openPanelInformacyjny();
    }



}
