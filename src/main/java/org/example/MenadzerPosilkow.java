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



    //dodac zabezpieczenie zeby wyskoczyl jakis komunikat gdy nie ma dań dal danego poislku


    public void pelenProgram(TypPosilku typPosilku) throws FileNotFoundException {

        List<Produkt> produktyZMakro = stworzenieListyProduktowZObliczonymMarko(typPosilku, wyborDaniaDoPosilku(typPosilku));
        for (int i = 0; i < produktyZMakro.size(); i++) {
            System.out.println(menadzerProduktow.wypiszProdukt(produktyZMakro.get(i)));
        }



    }

    public List<Produkt> stworzenieListyProduktowZObliczonymMarko(TypPosilku typPosilku, Dania danieDoPosilku) throws FileNotFoundException {

            //Dania danieDoPosilku = wyborDaniaDoPosilku(typPosilku);
            List<Produkt> produkts = danieDoPosilku.getSkladDania();
            List<Produkt> produktyZObliczonymMarko = new ArrayList<>();

            for (int i = 0; i < produkts.size(); i++) {
                System.out.println(menadzerProduktow.wypiszProdukt(produkts.get(i)));
                System.out.print("Podaj gramature: ");
                int ileGram = Integer.parseInt(scanner.nextLine());
                Produkt produktZObliczonymMakro = menadzerDania.obliczMakro(ileGram, produkts.get(i));
                produktyZObliczonymMarko.add(produktZObliczonymMakro);
            }
//            System.out.println(menadzerProduktow.wypiszProdukt(produktyZObliczonymMarko.get(0)));
//            System.out.println(menadzerProduktow.wypiszProdukt(produktyZObliczonymMarko.get(1)));
//            System.out.println(menadzerProduktow.wypiszProdukt(produktyZObliczonymMarko.get(2)));
            return produktyZObliczonymMarko;

        //wypisanie obliczonych produktow dla testu




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
            case DODATKOWE_DANIE -> wynik = "dodatku";
        }
        return wynik;
    }


}
