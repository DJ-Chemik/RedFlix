package sample.InterfejsUzytkownika;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import sample.Main;
import sample.StricteBazaProdukcji.Film;
import sample.StricteBazaProdukcji.Live;
import sample.StricteBazaProdukcji.Produkcja;
import sample.StricteBazaProdukcji.Serial;

public class PanelInfoProdukcjaController extends Panel {

    @FXML
    private Label labelGatunek;
    @FXML
    private Label labelCzasNaObejrzenie;
    @FXML
    private Label labelIloscSezonow;
    @FXML
    private Label labelDataEmisji;
    @FXML
    private Label labelDataPromocjiPoczatek;
    @FXML
    private Label labelDataPromocjiKoniec;
    @FXML
    private Label labelUpustProcentowy;
    @FXML
    private Label labelListaAktorow;
    @FXML
    private Label labelListaZwiastunow;


    //LABELE w których wyświetlane są właściwe informacje o produkcji
    @FXML
    private Label infoTypProdukcji;
    @FXML
    private Label infoDystrybutor;
    @FXML
    private Label infoTytul;
    @FXML
    private Label infoCzasTrwania;
    @FXML
    private Label infoCena;
    @FXML
    private Label infoOcenaUzytkownikow;
    @FXML
    private Label infoGatunek;
    @FXML
    private Label infoCzasNaObejrzenie;
    @FXML
    private Label infoIloscSezonow;
    @FXML
    private Label infoDataEmisji;
    @FXML
    private Label infoDataPromocjiPoczatek;
    @FXML
    private Label infoDataPromocjiKoniec;
    @FXML
    private Label infoUpustProcentowy;

    @FXML
    private TextArea textOpis;
    @FXML
    private ImageView miniaturka;

    @FXML
    private ListView<String> listaAktorow;
    @FXML
    private ListView<String> listaKrajowProdukcji;
    @FXML
    private ListView<String> listaZwiastunow;



    private Produkcja produkcja = PanelGlownyController.getProdukcjaWybranaZPaneluGlownego();

    @FXML
    public void initialize() {
        uzupelnijPolaInformacyjne();
    }

    @FXML
    public void usunProdukcjeZSystemu(){
        Main.pulaProduktow.pula.usunZPuli(produkcja);
        Main.pulaProduktow.zapiszPuleDoPliku();
        openPanelGlowny();
    }

    private void uzupelnijPolaInformacyjne() {

        infoTytul.setText(produkcja.getNazwa());
        infoCzasTrwania.setText(Integer.toString(produkcja.getCzasTrwania()));
        infoCena.setText(Float.toString(produkcja.getCena()));
        infoOcenaUzytkownikow.setText(Float.toString(produkcja.getOcenaUzytkownikow()));
        textOpis.setText(produkcja.getOpis());
        miniaturka.setImage(new Image(produkcja.getZdjecie()));
        infoDystrybutor.setText(produkcja.getDystrybutorProdukcji().toString());
        ObservableList<String> krajeProdukcji = FXCollections.observableArrayList();
        krajeProdukcji.addAll(produkcja.getKrajeProdukcji());
        listaKrajowProdukcji.setItems(krajeProdukcji);

        if (produkcja instanceof Film) {
            infoTypProdukcji.setText("Film");
            infoGatunek.setText(((Film) produkcja).getGatunek());
            infoCzasNaObejrzenie.setText(Integer.toString(((Film) produkcja).getCzasMozliwoscOgladania()));
            infoUpustProcentowy.setText(Integer.toString(((Film) produkcja).getPromocja().getUpustProcentowy()));
            ustawDatyPromocjiFilmu();
            ObservableList<String> aktorzy = FXCollections.observableArrayList();
            for (int i=0; i<((Film) produkcja).getListaAktorow().size();i++) {

                aktorzy.add(((Film) produkcja).getListaAktorow().get(i).toString());
            }
            listaAktorow.setItems(aktorzy);
            ObservableList<String> zwiastuny = FXCollections.observableArrayList();
            zwiastuny.addAll(((Film) produkcja).getListaLinkowDoZwiastunow());
            listaZwiastunow.setItems(zwiastuny);

        } else if (produkcja instanceof Live) {
            infoTypProdukcji.setText("Wydarzenie na Żywo");
            infoUpustProcentowy.setText(Integer.toString(((Live) produkcja).getPromocja().getUpustProcentowy()));
            String data = Integer.toString(((Live) produkcja).getDataWyswietlenia().getDzien());
            data += ".";
            data += Integer.toString(((Live) produkcja).getDataWyswietlenia().getMiesiac());
            data += ".";
            data += Integer.toString(((Live) produkcja).getDataWyswietlenia().getRok());
            infoDataEmisji.setText(data);
            ustawDatyPromocjiLive();

        } else if (produkcja instanceof Serial) {
            infoTypProdukcji.setText("Serial");
            infoGatunek.setText(((Serial) produkcja).getGatunek());
            infoIloscSezonow.setText(Integer.toString(((Serial) produkcja).getIloscSezonow()));
            ObservableList<String> aktorzy = FXCollections.observableArrayList();

            for (int i=0; i<((Serial) produkcja).getListaAktorow().size();i++) {

                aktorzy.add(((Serial) produkcja).getListaAktorow().get(i).toString());
            }
            listaAktorow.setItems(aktorzy);
            //aktorzy.addAll(((Serial) produkcja).getListaAktorow().toString());

        }
        deaktywujPolaNiewlasciweDlaDanegoTypuProdukcji();
    }

    private void ustawDatyPromocjiFilmu() {
        String data = Integer.toString(((Film) produkcja).getPromocja().getDataRozpoczecia().getDzien());
        data += ".";
        data += Integer.toString(((Film) produkcja).getPromocja().getDataRozpoczecia().getMiesiac());
        data += ".";
        data += Integer.toString(((Film) produkcja).getPromocja().getDataRozpoczecia().getRok());
        infoDataPromocjiPoczatek.setText(data);

        data = null;
        data = Integer.toString(((Film) produkcja).getPromocja().getDataZakonczenia().getDzien());
        data += ".";
        data += Integer.toString(((Film) produkcja).getPromocja().getDataZakonczenia().getMiesiac());
        data += ".";
        data += Integer.toString(((Film) produkcja).getPromocja().getDataZakonczenia().getRok());
        infoDataPromocjiKoniec.setText(data);


    }

    private void ustawDatyPromocjiLive() {
        String data = Integer.toString(((Live) produkcja).getPromocja().getDataRozpoczecia().getDzien());
        data += ".";
        data += Integer.toString(((Live) produkcja).getPromocja().getDataRozpoczecia().getMiesiac());
        data += ".";
        data += Integer.toString(((Live) produkcja).getPromocja().getDataRozpoczecia().getRok());
        infoDataPromocjiPoczatek.setText(data);

        data = null;
        data = Integer.toString(((Live) produkcja).getPromocja().getDataZakonczenia().getDzien());
        data += ".";
        data += Integer.toString(((Live) produkcja).getPromocja().getDataZakonczenia().getMiesiac());
        data += ".";
        data += Integer.toString(((Live) produkcja).getPromocja().getDataZakonczenia().getRok());
        infoDataPromocjiKoniec.setText(data);


    }

    private void deaktywujPolaNiewlasciweDlaDanegoTypuProdukcji() {

        if (produkcja instanceof Film) {
            labelIloscSezonow.setVisible(false);
            infoIloscSezonow.setVisible(false);
            labelDataEmisji.setVisible(false);
            infoDataEmisji.setVisible(false);

        } else if (produkcja instanceof Live) {
            labelIloscSezonow.setVisible(false);
            infoIloscSezonow.setVisible(false);
            labelCzasNaObejrzenie.setVisible(false);
            infoCzasNaObejrzenie.setVisible(false);
            labelGatunek.setVisible(false);
            infoGatunek.setVisible(false);

            labelListaZwiastunow.setVisible(false);
            listaZwiastunow.setVisible(false);
            labelListaAktorow.setVisible(false);
            listaAktorow.setVisible(false);


        } else if (produkcja instanceof Serial) {
            labelDataEmisji.setVisible(false);
            infoDataEmisji.setVisible(false);
            labelDataPromocjiPoczatek.setVisible(false);
            infoDataPromocjiPoczatek.setVisible(false);
            labelDataPromocjiKoniec.setVisible(false);
            infoDataPromocjiKoniec.setVisible(false);
            labelUpustProcentowy.setVisible(false);
            infoUpustProcentowy.setVisible(false);

            labelListaZwiastunow.setVisible(false);
            listaZwiastunow.setVisible(false);




        }

    }
}
