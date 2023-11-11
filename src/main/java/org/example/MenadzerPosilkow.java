package org.example;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenadzerPosilkow {
    MenadzerDania menadzerDania = new MenadzerDania();
    MenadzerProduktow menadzerProduktow = new MenadzerProduktow();
    MenadzerPlikow menadzerPlikow = new MenadzerPlikow();
    Scanner scanner = new Scanner(System.in);

    public void pelenProgram(TypPosilku typPosilku) throws IOException {
        int miejsceDodatkowegoPosilku = 0;
        if(typPosilku == TypPosilku.DODATKOWE_DANIE){
             miejsceDodatkowegoPosilku = wJakimMiejscu();
        }
        Dania wybraneDanie = wyborDaniaDoPosilku(typPosilku);
        List<Produkt> produktyZMakro = stworzenieListyProduktowZObliczonymMarko(wybraneDanie);
        for (Produkt produkt : produktyZMakro) {
            System.out.println(menadzerProduktow.wypiszProdukt(produkt));
        }
        Dania danieZObliczonymMakro = new Dania(wybraneDanie.typPosilku,wybraneDanie.getNazwaDania(), produktyZMakro);
        menadzerPlikow.dodajDoPlikZDanymDniem(danieZObliczonymMakro, miejsceDodatkowegoPosilku);
    }
    public int wJakimMiejscu() {
        menuDodatkowegoDania();
        int wyborMiejscaPosilku = Integer.parseInt(scanner.nextLine());
        while (wyborMiejscaPosilku > 3 || wyborMiejscaPosilku <= 0) {
            System.out.println("Podałeś nieprawidłową wartość!");
            System.out.print("Podaj liczbę jeszcze raz: ");
            wyborMiejscaPosilku = Integer.parseInt(scanner.nextLine());
        }
        return wyborMiejscaPosilku;
    }

    public List<Produkt> stworzenieListyProduktowZObliczonymMarko(Dania danieDoPosilku) {

            List<Produkt> produkts = danieDoPosilku.getSkladDania();
            List<Produkt> produktyZObliczonymMarko = new ArrayList<>();

            for (int i = 0; i < produkts.size(); i++) {
                System.out.println(menadzerProduktow.wypiszProdukt(produkts.get(i)));
                System.out.print("Podaj gramature: ");
                int ileGram = Integer.parseInt(scanner.nextLine());

                Produkt produktZObliczonymMakro = menadzerDania.obliczMakro(ileGram, produkts.get(i));
                produktyZObliczonymMarko.add(produktZObliczonymMakro);
            }
            return produktyZObliczonymMarko;
    }
    public Dania wyborDaniaDoPosilku(TypPosilku typPosilku) throws FileNotFoundException {
        List<Dania> wybraneDania = menadzerDania.stworzListeZDaniamiDanegoTypu(typPosilku);
        if(wybraneDania.size() == 0){
            System.out.println("Niestety nie ma dań dla tego posiłku");
            throw new IndexOutOfBoundsException();
        }else {
            menadzerDania.wyswietlDaniaZListy(wybraneDania);
            System.out.print("Jakie danie chcesz dodać do " + odmianaTypuPosilku(typPosilku) + ": ");
            int wyborDania = Integer.parseInt(scanner.nextLine());
            while (wyborDania > wybraneDania.size() || wyborDania <= 0) {
                System.out.println("Podałeś liczbe spoza zakresu");
                System.out.print("Wybierz jescze raz:");
                wyborDania = Integer.parseInt(scanner.nextLine());
            }
            wyborDania--;
            return wybraneDania.get(wyborDania);
        }
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
    public String odmianaTypuPosilku2(TypPosilku typPosilku){
        String wynik="";
        switch (typPosilku){
            case SNIADANIE -> wynik = "Śniadanie:";
            case DRUGIE_SNIADANIE -> wynik = "Drugie śniadanie:";
            case OBIAD -> wynik = "Obiad:";
            case KOLACJA -> wynik = "Kolacja:";
            case DODATKOWE_DANIE -> wynik = "Dodatkowe danie";
        }
        return wynik;
    }
    public void menuDodatkowegoDania(){
        System.out.println("Śniadanie");
        System.out.println("Drugie śniadanie");
        System.out.println("--------------   <-[1]");
        System.out.println("Obiad");
        System.out.println("--------------   <-[2]");
        System.out.println("Kolacja");
        System.out.println("--------------   <-[3]");
        System.out.print("W którym miejscu zjadłeś dodatkwy posiłek?: ");
    }
}
