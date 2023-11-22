
package org.example;

import java.util.*;

import static java.util.Collections.reverse;
import static java.util.List.*;

public class MenadzerPosilkow {
    MenadzerDania menadzerDania = new MenadzerDania();
    MenadzerProduktow menadzerProduktow = new MenadzerProduktow();
    ZapytaniaDoBazy zapytaniaDoBazy = new ZapytaniaDoBazy();
    Scanner scanner = new Scanner(System.in);

    public void pelenProgram(TypPosilku typPosilku){
        if(!zapytaniaDoBazy.open()){
            System.out.println("Nie można otworzyć bazy danych");
            return;
        }
        int wybraneDanie = wyborDaniaDoPosilku(typPosilku);


      //  String danieZSkladem = menadzerDania.wyswietlKonkretneDanie(typPosilku, wybraneDanie);
        // tu powinna byc metoda ktora zwraca id wybranego dania

        List<Produkt> produktyZWybranegoDania = zapytaniaDoBazy.wyswietlWszystkieProduktyZDanegoDania(wybraneDanie);
        dodanieGramturyWybranymPosilkom(produktyZWybranegoDania);


        List<Integer> wybraneProdukty = zapytaniaDoBazy.wyswietlWszystkieDodaneIdDoGram(produktyZWybranegoDania.size());
        Collections.reverse(wybraneProdukty);

         zapytaniaDoBazy.dodajListeGramProduktowDoDania(wybraneProdukty, wybraneDanie);





    }
//    public int wJakimMiejscu() {
//        menuDodatkowegoDania();
//        int wyborMiejscaPosilku = Integer.parseInt(scanner.nextLine());
//        while (wyborMiejscaPosilku > 3 || wyborMiejscaPosilku <= 0) {
//            System.out.println("Podałeś nieprawidłową wartość!");
//            System.out.print("Podaj liczbę jeszcze raz: ");
//            wyborMiejscaPosilku = Integer.parseInt(scanner.nextLine());
//        }
//        return wyborMiejscaPosilku;
//    }

    public void dodanieGramturyWybranymPosilkom(List<Produkt> produkts) {

        for (int i = 0; i < produkts.size(); i++) {
            System.out.println(menadzerProduktow.wypiszProdukt(produkts.get(i)));
            System.out.print("Podaj gramature: ");
            int ileGram = Integer.parseInt(scanner.nextLine());

            while (ileGram <= 0){
                System.out.println("Podałeś niepoprawną gramaturę");
                System.out.print("Podaj gramature jescze raz: ");
                ileGram = Integer.parseInt(scanner.nextLine());
            }


            zapytaniaDoBazy.insertIntoGramaturaPosilku(ileGram, produkts.get(i));
        }

    }
    public int wyborDaniaDoPosilku(TypPosilku typPosilku) {
            List<Integer> ids = zapytaniaDoBazy.wyswietlIdDaniaDanegoTypu(typPosilku);
            List<String> wyswietlDania = menadzerDania.wyswietlDanie(typPosilku);
            for (String s : wyswietlDania) {
                System.out.println(s);
            }

            System.out.print("Jakie danie chcesz dodać do " + odmianaTypuPosilku(typPosilku) + ": ");
             int idWybranegoDania = Integer.parseInt(scanner.nextLine());
            while (!ids.contains(idWybranegoDania)) {
                System.out.println("Podałeś liczbe spoza zakresu");
                System.out.print("Wybierz jescze raz:");
                idWybranegoDania = Integer.parseInt(scanner.nextLine());
            }
            return idWybranegoDania;

    }
    public String odmianaTypuPosilku(TypPosilku typPosilku){
        String wynik="";
        switch (typPosilku){
            case SNIADANIE -> wynik = "śniadania";
            case DRUGIE_SNIADANIE -> wynik = "drugiego śniadania";
            case OBIAD -> wynik = "obiadu";
            case KOLACJA -> wynik = "kolacji";
            case DODATKOWE_DANIE -> wynik = "dodatkowego posiłku";
        }
        return wynik;
    }
//    public String odmianaTypuPosilku2(TypPosilku typPosilku){
//        String wynik="";
//        switch (typPosilku){
//            case SNIADANIE -> wynik = "Śniadanie:";
//            case DRUGIE_SNIADANIE -> wynik = "Drugie śniadanie:";
//            case OBIAD -> wynik = "Obiad:";
//            case KOLACJA -> wynik = "Kolacja:";
//            case DODATKOWE_DANIE -> wynik = "Dodatkowe danie";
//        }
//        return wynik;
//    }
//    public void menuDodatkowegoDania(){
//        System.out.println("Śniadanie");
//        System.out.println("Drugie śniadanie");
//        System.out.println("--------------   <-[1]");
//        System.out.println("Obiad");
//        System.out.println("--------------   <-[2]");
//        System.out.println("Kolacja");
//        System.out.println("--------------   <-[3]");
//        System.out.print("W którym miejscu zjadłeś dodatkwy posiłek?: ");
//    }
}


