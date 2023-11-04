package org.example;

import java.io.*;
import java.util.List;
import java.util.Scanner;

public class MenadzerPlikow {

    public void zapiszDoPliku(Produkt produkt, File file) {
        if (file.canWrite()) {
            try {
                FileWriter fileWriter = new FileWriter(file, true);
                PrintWriter printWriter = new PrintWriter(fileWriter);
                printWriter.println(produkt.getNazwa());
                printWriter.println(produkt.getKcal());
                printWriter.println(produkt.getBialko());
                printWriter.println(produkt.getWeglowodany());
                printWriter.println(produkt.getBlonnik());
                printWriter.println(produkt.getTluszcze());
                printWriter.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public void swtorzPlikZPosilkiem(TypPosilku typ,String nazwa,List<Produkt> produkts){
      //  String indexPosilku= zwrocPierwszyZnakTypu(typ);


        File file = new File("/C:/Users/olekb/IdeaProjects/dietaProjekt/src/Posilki/"+nazwa);

        if(!file.exists()) {
            try{
                file.createNewFile();
            }catch (Exception e){
                System.out.println(e.getMessage());
            }
        }
            if (file.canWrite()) {
                try {
                    FileWriter fileWriter = new FileWriter(file, true);
                    PrintWriter printWriter = new PrintWriter(fileWriter);
                    printWriter.println(nazwa.toUpperCase());
                    printWriter.println(typ);
                    for (Produkt produkt : produkts) {
                        printWriter.println(produkt.getNazwa());
                        printWriter.println(produkt.getKcal());
                        printWriter.println(produkt.getBialko());
                        printWriter.println(produkt.getWeglowodany());
                        printWriter.println(produkt.getBlonnik());
                        printWriter.println(produkt.getTluszcze());
                    }
                    printWriter.close();
                } catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            }




    }
    public String zwrocPierwszyZnakTypu(TypPosilku typ){
        String wynik ="";
        switch (typ){
            case SNIADANIE -> wynik ="1";
            case DRUGIE_SNIADANIE -> wynik ="2";
            case OBIAD -> wynik ="3";
            case KOLACJA -> wynik ="4";
        }

        return wynik;
    }




    public boolean sprawdzCzyJestWPilku(String nazwa, File file) throws FileNotFoundException {

        int licznik = 0;
        Scanner scanner = new Scanner(file);
        while (scanner.hasNext()) {
            licznik++;
            String linia = scanner.nextLine();
            if (licznik % 6 == 1 && nazwa.equalsIgnoreCase(linia)) {
                return false;
            }
        }
        return true;
    }
}

