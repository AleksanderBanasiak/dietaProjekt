package org.example;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DietaApk {

    public void run() throws IOException {
        wyswietlDate();
        int wybor;
        MenadzerProduktow menadzerProduktow = new MenadzerProduktow();
        MenadzerPosilku posilek = new MenadzerPosilku();
        Scanner scanner = new Scanner(System.in);
        menadzerProduktow.dodajProduktyZPlikuDoListy();
        while (true){
            menu();
            wybor = Integer.parseInt(scanner.nextLine());
            switch (wybor){
                case 1 -> menadzerProduktow.dodajProduktyZPlikuDoListy();
                case 2 -> menadzerProduktow.wyswietlWszystkieProdukty();
                case 3 -> System.out.println("3");
                case 4 -> System.out.println("4");
                case 5 -> System.out.println("5");
                case 6 -> posilek.tworzeniePosilku(menadzerProduktow, scanner);
                case 7 -> menadzerProduktow.tworzenieProduktu(scanner);
                case 8 ->
                {
                    System.out.println("Koniec programu");
                    scanner.close();
                    System.exit(0);
                }
                default -> System.out.println("Podaj poprawną wartość!");
            }
        }
    }

    public void wyswietlDate(){
        DateTimeFormatter wyswietlDate = DateTimeFormatter.ofPattern("dd/MM/yy HH:mm:ss");
        LocalDateTime teraz = LocalDateTime.now();
        System.out.println(wyswietlDate.format(teraz));
    }

    public void menu(){
        System.out.println("-".repeat(25));
        System.out.println("[1] - ŚNIADANIE");
        System.out.println("[2] - DRUGIE ŚNIADANIE");
        System.out.println("[3] - OBIAD");
        System.out.println("[4] - KOLACJA");
        System.out.println("[5] - DODATKOWE DANIE");
        System.out.println("[6] - DODAJ POSIŁEK");
        System.out.println("[7] - DODAJ PRODUKT");
        System.out.println("[8] - WYJŚCIE Z PROGRAMU");
        System.out.println("-".repeat(25));
    }



}
