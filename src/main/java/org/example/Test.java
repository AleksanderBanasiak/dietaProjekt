package org.example;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {
        MenadzerProduktow menadzerProduktow = new MenadzerProduktow();
        runing runing = new runing();
        Dania danie1 = runing.wyborDaniaDoPosilku(TypPosilku.KOLACJA);
        MenadzerDania menadzerDania = new MenadzerDania();
       // menadzerDania.wypiszDanieWPosilku(danie1); // ta metoda jest bez sensu
        List<Produkt> produkts = danie1.getSkladDania();
        Scanner scanner = new Scanner(System.in);
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



    }

}
        class runing{
        public Dania wyborDaniaDoPosilku(TypPosilku typPosilku) throws FileNotFoundException {
            Scanner scanner = new Scanner(System.in);
            MenadzerDania menadzerDania = new MenadzerDania();
            List<Dania> wybraneDania = menadzerDania.stworzListeZDaniamiDanegoTypu(typPosilku);
            menadzerDania.wyswietlDaniaZListy(wybraneDania);
            System.out.print("Jakie danie chcesz dodać do "+ odmianaTypuPosilku(typPosilku) + ": ");
            int wyborDania = Integer.parseInt(scanner.nextLine());
            wyborDania--;

            return wybraneDania.get(wyborDania);


        }

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







