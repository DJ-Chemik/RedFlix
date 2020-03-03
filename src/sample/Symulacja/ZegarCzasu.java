package sample.Symulacja;

import sample.Main;

public class ZegarCzasu implements Runnable {

    private int obecnyDzien = 0;
    private int odIluDniSystemPrzynosiStraty = 0;

    ///////////////////////////////////////////////////////////////////////
    //TUTAJ MOGĄ BYC STAŁE DO REGULACJI SZYBKOSCIA DZIALANIA SYSTEMU
    //////////////////////////////////////////////////////////////////////
    //private static int jedenDzien = 5*1000; //5 sekund


    @Override
    public void run() {
        int j = 0;
        while (odIluDniSystemPrzynosiStraty < 90) { //system przynosi straty nie więcej niż 3 miesiące (dla uproszeczenia miesiąc trwa tu zawsze 30dni)
            SystemSymulacji.automatycznieDodajUserow();
            SystemSymulacji.poczekajXDni(1);
            if (odIluDniSystemPrzynosiStraty < 92) {
                obecnyDzien += 1;
                System.out.print(">>>>>>>>>>>>>>>>>>>>>>> DZIEŃ: ");
                System.out.print(obecnyDzien);
                if (odIluDniSystemPrzynosiStraty == 0) {
                    System.out.println();
                } else {
                    System.out.print(" ---STRATY OD:");
                    System.out.print(odIluDniSystemPrzynosiStraty);
                    System.out.println(" DNI");
                }
                j++;

                Object dillionHarper = new Object();
                synchronized (dillionHarper) {
                    if (j == 30) {
                        float miesieczneOplaty = Main.systemSymulacji.getSystemFinansowy().getMiesieczneZobowiazaniaWzgledemDystrybutorow();
                        Main.systemSymulacji.getSystemFinansowy().zmodyfikujStanSystemuFinansowegoOX((-1) * miesieczneOplaty);
                        j /= 30;
                    }
                    if (Main.systemSymulacji.getSystemFinansowy().getStanSystemuFinansowego() < 0) {
                        odIluDniSystemPrzynosiStraty++;
                    } else {
                        odIluDniSystemPrzynosiStraty = 0;
                    }

                }


            }

        }

    }


}
