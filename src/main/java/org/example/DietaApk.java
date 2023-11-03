package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class DietaApk {

    public void run(){
        wyswietlDate();
        int wybor;
        MenadzerProduktow menadzerProduktow = new MenadzerProduktow();
        Scanner scanner = new Scanner(System.in);
        while (true){
            menu();
            wybor = Integer.parseInt(scanner.nextLine());
            switch (wybor){
                case 1 -> menadzerProduktow.tworzenieProduktu(scanner);
                case 2 -> menadzerProduktow.wyswietlWszystkieProdukty();
                case 3 -> System.out.println("3");
                case 4 -> System.out.println("4");
                case 5 -> System.out.println("5");
                case 6 -> System.out.println("6");
                case 7 ->
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
        System.out.println("[7] - WYJŚCIE Z PROGRAMU");
        System.out.println("-".repeat(25));
    }



}
