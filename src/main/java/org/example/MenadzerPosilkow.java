package org.example;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MenadzerPosilkow {
    MenadzerDania menadzerDania = new MenadzerDania();
    MenadzerProduktow menadzerProduktow = new MenadzerProduktow();
    Scanner scanner = new Scanner(System.in);


    // wyswetlic dania dostepne dla wybranego posilku
    //po wybraniu dania ma sie wyswietlic pierwszy produkt
    // po zapisnie gramatury ma sie wyswietlic nastepny produkt
    //po zapisaniu gramatury wszyustkich produktow
    // ma sie wyswietlic komunikat z makroskladnikami

    // nastepuje zapis dania do pliu o nazwie daty


    //dodac zabezpiecznie podczas wyboru posilku zeby wybierac z zakresu
    //dodac zabezpieczenie zeby wyskoczyl jakis komunikat gdy nie ma dań dal danego poislku

    public List<Produkt> stworzenieListyProduktowZObliczonymMarko(TypPosilku typPosilku) throws FileNotFoundException {
        Dania danieDoPosilku = wyborDaniaDoPosilku(typPosilku);

        List<Produkt> produkts = danieDoPosilku.getSkladDania();
        List<Produkt> produktyZObliczonymMarko = new ArrayList<>();

        for (int i = 0; i < produkts.size(); i++) {
            System.out.println(menadzerProduktow.wypiszProdukt(produkts.get(i)));
            System.out.print("Podaj gramature: ");
            int ileGram = Integer.parseInt(scanner.nextLine());
            Produkt produktZObliczonymMakro = menadzerDania.obliczMakro(ileGram, produkts.get(i));
            produktyZObliczonymMarko.add(produktZObliczonymMakro);
        }
        System.out.println(menadzerProduktow.wypiszProdukt(produktyZObliczonymMarko.get(0)));
        System.out.println(menadzerProduktow.wypiszProdukt(produktyZObliczonymMarko.get(1)));
        System.out.println(menadzerProduktow.wypiszProdukt(produktyZObliczonymMarko.get(2)));
        //wypisanie obliczonych produktow dla testu
        return produktyZObliczonymMarko;
    }


    public Dania wyborDaniaDoPosilku(TypPosilku typPosilku) throws FileNotFoundException {
        List<Dania> wybraneDania = menadzerDania.stworzListeZDaniamiDanegoTypu(typPosilku);
        menadzerDania.wyswietlDaniaZListy(wybraneDania);
        System.out.println("Jakie danie chcesz dodać do "+ odmianaTypuPosilku(typPosilku));
        int wyborDania = Integer.parseInt(scanner.nextLine());
        wyborDania--;
        return wybraneDania.get(wyborDania);

    }

   // public



    public String odmianaTypuPosilku(TypPosilku typPosilku){
        String wynik="";
        switch (typPosilku){
            case SNIADANIE -> wynik = "śniadania";
            case DRUGIE_SNIADANIE -> wynik = "drugiego śniadania";
            case OBIAD -> wynik = "obiadu";
            case KOLACJA -> wynik = "kolacji";
        }
        return wynik;
    }


}
