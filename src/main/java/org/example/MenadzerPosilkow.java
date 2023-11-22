package org.example;

import java.util.Collections;
import java.util.List;
import java.util.Scanner;

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
        List<Produkt> produktyZWybranegoDania = zapytaniaDoBazy.wyswietlWszystkieProduktyZDanegoDania(wybraneDanie);
        dodanieGramturyWybranymPosilkom(produktyZWybranegoDania);
        List<Integer> wybraneProdukty = zapytaniaDoBazy.wyswietlWszystkieDodaneIdDoGram(produktyZWybranegoDania.size());
        Collections.reverse(wybraneProdukty);
         zapytaniaDoBazy.dodajListeGramProduktowDoDania(wybraneProdukty, wybraneDanie);
    }
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
}


