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
        MenadzerDania danie = new MenadzerDania();
        MenadzerPosilkow menadzerPosilkow = new MenadzerPosilkow();
        Scanner scanner = new Scanner(System.in);
        menadzerProduktow.dodajProduktyZPlikuDoListy();
        while (true){
            menu();
            wybor = Integer.parseInt(scanner.nextLine());
            switch (wybor){
                case 1 -> menadzerPosilkow.pelenProgram(TypPosilku.SNIADANIE);
                case 2 -> menadzerPosilkow.pelenProgram(TypPosilku.DRUGIE_SNIADANIE);
                case 3 -> menadzerPosilkow.pelenProgram(TypPosilku.OBIAD);
                case 4 -> menadzerPosilkow.pelenProgram(TypPosilku.KOLACJA);
                case 5 -> menadzerPosilkow.pelenProgram(TypPosilku.DODATKOWE_DANIE);
                case 6 -> danie.tworzenieDania(menadzerProduktow, scanner);
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
        System.out.println("-".repeat(24));
        DateTimeFormatter wyswietlDate = DateTimeFormatter.ofPattern("   dd/MM/yy HH:mm:ss");
        LocalDateTime teraz = LocalDateTime.now();
        System.out.println(wyswietlDate.format(teraz));
    }
    public void menu(){
        System.out.println("-".repeat(24));
        System.out.println("[1] - ŚNIADANIE");
        System.out.println("[2] - DRUGIE ŚNIADANIE");
        System.out.println("[3] - OBIAD");
        System.out.println("[4] - KOLACJA");
        System.out.println("[5] - DODATKOWY POSIŁEK");
        System.out.println("[6] - DODAJ DANIE");
        System.out.println("[7] - DODAJ PRODUKT");
        System.out.println("[8] - WYJŚCIE Z PROGRAMU");
        System.out.println("-".repeat(24));
    }
}
