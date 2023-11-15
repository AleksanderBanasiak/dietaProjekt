package org.example;

import java.sql.SQLException;
import java.util.List;
import java.util.Scanner;

public class KorzytanieZBazy {

    public static void main(String[] args) throws SQLException {
        MenadzerProduktow menadzerProduktow = new MenadzerProduktow();
        BazaDanych bazaDanych = new BazaDanych();
        ZapytaniaDoBazy zapytaniaDoBazy = new ZapytaniaDoBazy();

        if(!zapytaniaDoBazy.open()){
            System.out.println("Nie można otworzyć bazy danych");
            return;
        }

        //zapytanie 1 wyswietla wszyskie produkty (dziala)
//        List<Produkt> produkty = zapytaniaDoBazy.wyswietlWyszyskieProdukty();
//        if(produkty ==null){
//            System.out.println("Nie ma produktow do wyswietlenia");
//            return;
//        }
//        for (Produkt produkt : produkty){
//            System.out.println(menadzerProduktow.wypiszProdukt(produkt));
//        }

       //  zapytanie 2 dodaje produkt do bazy plus zapytanie o nastepne id
        //  zapytaniaDoBazy.insertIntoProdukt( "test2", 2.2, 12.2, 9.9, 6.6, 5.5);


//        Scanner scanner = new Scanner(System.in);
//        System.out.print("Podaj nazwe: ");
//        String cos = scanner.nextLine();
//        System.out.print("Podaj kcal: ");
//        double cos2 = scanner.nextDouble();
//        List<Produkt> produkty = zapytaniaDoBazy.podwojneZapytanieSelect(cos, cos2);
//        if(produkty ==null){
//            System.out.println("Nie ma produktow do wyswietlenia");
//            return;
//        }
//        for (Produkt produkt : produkty){
//            System.out.println(menadzerProduktow.wypiszProdukt(produkt));
//        }

//         int w =zapytaniaDoBazy.pobierzOstatnieID();
//        System.out.println(w);

        Scanner scanner = new Scanner(System.in);
        int id = scanner.nextInt();

        List<Produkt> produkty = zapytaniaDoBazy.wyswietlWszystkieProduktyZDanegoDania(id);
        if(produkty ==null){
            System.out.println("Nie ma produktow do wyswietlenia");
            return;
        }
        for (Produkt produkt : produkty){
            System.out.println(menadzerProduktow.wypiszProdukt(produkt));
        }





        zapytaniaDoBazy.close();

    }

}
