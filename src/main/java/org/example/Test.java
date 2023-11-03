package org.example;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) throws FileNotFoundException {


        File file = new File("produkty.txt");
        int licznik = 0;
        Scanner scanner = new Scanner(file);

        String cos = "wasdas";
        while (scanner.hasNext()){
            licznik++;
            String linia = scanner.nextLine();
            System.out.println(licznik +" "+ linia);

            if(licznik %5 ==1){
                if(linia.equals(cos)){
                    System.out.println("nigggger");
                }
                System.out.println(licznik+"!!!");
            }


        }

      //  while (scanner.hasNext()) {
          //  System.out.println(licznik);
//            if (licznik % 5 == 0) {
//                String linia = scanner.nextLine();
//               // System.out.println(licznik+"!");
//
//            }
            licznik++;


     //   }





    }
}
