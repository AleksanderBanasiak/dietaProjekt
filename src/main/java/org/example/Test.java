package org.example;

import java.io.FileNotFoundException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {

        //zapisywanie nazw plikow do tablicy
//        File fileNazwy = new File("/C:/Users/olekb/IdeaProjects/dietaProjekt/src/Posilki/");
//        String[] nazwyPlikow  = fileNazwy.list();
//        for (String s : nazwyPlikow) {
//            System.out.println(s);
//        }

        MenadzerPosilku menadzerPosilku = new MenadzerPosilku();
        List<Posilek> posileks = menadzerPosilku.dodajPosilkiZPlikuDoListy();
        System.out.println(posileks.size());

        for (int i = 0; i < posileks.size(); i++) {
            menadzerPosilku.wypiszPosilek(posileks.get(i));
        }


//        String cos = "wasdas";
//        while (scanner.hasNext()){
//            licznik++;
//            String linia = scanner.nextLine();
//            System.out.println(licznik +" "+ linia);
//
//            if(licznik %5 ==1){
//                if(linia.equals(cos)){
//                    System.out.println("nigggger");
//                }
//                System.out.println(licznik+"!!!");
//            }


        }

      //  while (scanner.hasNext()) {
          //  System.out.println(licznik);
//            if (licznik % 5 == 0) {
//                String linia = scanner.nextLine();
//               // System.out.println(licznik+"!");
//
//            }

     //   }





    }
