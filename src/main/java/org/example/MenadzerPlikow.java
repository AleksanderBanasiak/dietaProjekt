package org.example;

import java.io.*;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;
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

    public File zapiszMakroPosilku(Dania dania){


        MenadzerDania menadzerDania = new MenadzerDania();
        DateTimeFormatter wyswietlDate = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate teraz = LocalDate.now();
        String dayOfWeek = DayOfWeek.from(teraz).toString().toLowerCase();
        //File file = new File("/C:/Users/olekb/IdeaProjects/dietaProjekt/src/MojaDieta/" +wyswietlDate.format(teraz));
        File file = new File("/C:/IntelliJNauka/dietaProjekt/src/MakroZDiety/" +wyswietlDate.format(teraz)+" "+dayOfWeek);
        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (file.canWrite()) {
            try {
                FileWriter fileWriter = new FileWriter(file, true);
                PrintWriter printWriter = new PrintWriter(fileWriter, true);
                Produkt pelneMakro = menadzerDania.obliczMarkoZCalegoDania(dania.getSkladDania());
                printWriter.println(pelneMakro.getKcal());
                printWriter.println(pelneMakro.getBialko());
                printWriter.println(pelneMakro.getWeglowodany());
                printWriter.println(pelneMakro.getBlonnik());
                printWriter.println(pelneMakro.getTluszcze());
                printWriter.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        return file;
    }


    public Produkt odczytajZPlikuMakroDlaCalegoDnia(Dania dania) throws FileNotFoundException {


        File file = zapiszMakroPosilku(dania);

        Scanner scanner = new Scanner(file);

        double caleKcal =0;
        double caleBialko=0;
        double caleWegle=0;
        double caleBlonnik=0;
        double caleTluszcze=0;

        while (scanner.hasNext()) {
            caleKcal += Double.parseDouble(scanner.nextLine());
            caleBialko += Double.parseDouble(scanner.nextLine());
            caleWegle += Double.parseDouble(scanner.nextLine());
            caleBlonnik += Double.parseDouble(scanner.nextLine());
            caleTluszcze += Double.parseDouble(scanner.nextLine());

        }

        return new Produkt("Makroskładniki z dnia: ",caleKcal,caleBialko,caleWegle,caleBlonnik,caleTluszcze);


    }






    public void dodajDoPlikZDanymDniem(Dania dania, int miejsceDodatkowegoPosilku) throws IOException {

        Produkt obliczneMarkoDlaDnia = odczytajZPlikuMakroDlaCalegoDnia(dania);


        String  ileSkipnac =  ileSkipnacDlaDodatkowegoPosilku(miejsceDodatkowegoPosilku);
        MenadzerPosilkow menadzerPosilkow = new MenadzerPosilkow();
        String posilek = menadzerPosilkow.odmianaTypuPosilku2(dania.getTypPosilku());

        MenadzerDania menadzerDania = new MenadzerDania();
        DateTimeFormatter wyswietlDate = DateTimeFormatter.ofPattern("dd-MM-yy");
        LocalDate teraz = LocalDate.now();
        String dayOfWeek = DayOfWeek.from(teraz).toString().toLowerCase();
        //File file = new File("/C:/Users/olekb/IdeaProjects/dietaProjekt/src/MojaDieta/" +wyswietlDate.format(teraz));
        File file = new File("/C:/IntelliJNauka/dietaProjekt/src/MojaDieta/" +wyswietlDate.format(teraz)+" "+dayOfWeek);

        if(!file.exists()) {
            try {
                file.createNewFile();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
        if (file.canWrite()) {
            try {
                FileWriter fileWriter = new FileWriter(file, true);
                PrintWriter printWriter = new PrintWriter(fileWriter, true);

                printWriter.println(obliczneMarkoDlaDnia.getNazwa() + obliczneMarkoDlaDnia.getKcal() + " kcal, " + obliczneMarkoDlaDnia.getBialko() + " białka, "+
                        obliczneMarkoDlaDnia.getWeglowodany()+ " węgli, " + obliczneMarkoDlaDnia.getBlonnik() + " błonnika, "
                        + obliczneMarkoDlaDnia.getTluszcze() + " tłuszczy");
                printWriter.println("-".repeat(40));
                //tutaj ponny sie wyswietlac makrosy dla calego dnia

               // printWriter.println("-".repeat(40));

                //caly ten zapis powinnien inaczej wygladac
                // ma yc tutaj zapisywane tylko dane po kolei

                Produkt pelneMakro = menadzerDania.obliczMarkoZCalegoDania(dania.getSkladDania());


                    printWriter.print(posilek + " "+ileSkipnac +"" + dania.getNazwaDania() + "(");
                    for (int i = 0; i < dania.getSkladDania().size(); i++) {
                            printWriter.print(dania.getSkladDania().get(i).getNazwa());
                        if(i != dania.getSkladDania().size() -1){
                            printWriter.print(",");
                        }
                    }
                    printWriter.println(")");
                    printWriter.println(pelneMakro.getNazwa() + pelneMakro.getKcal() + " kcal, " + pelneMakro.getBialko() + " białka, "+ pelneMakro.getWeglowodany()+ " węgli, "
                    + pelneMakro.getBlonnik() + " błonnika, "+ pelneMakro.getTluszcze() + " tłuszczy");
                    printWriter.println("-".repeat(40));



                // tutaj sie to nadpisuje nie wiem czy to powinno tak dzialac
//                printWriter.println(nazwa.toUpperCase());
//                printWriter.println(typ);
//                for (Produkt produkt : produkts) {
//                    printWriter.println(produkt.getNazwa());
//                    printWriter.println(produkt.getKcal());
//                    printWriter.println(produkt.getBialko());
//                    printWriter.println(produkt.getWeglowodany());
//                    printWriter.println(produkt.getBlonnik());
//                    printWriter.println(produkt.getTluszcze());
                //}
                printWriter.close();
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

    }




    public String ileSkipnacDlaDodatkowegoPosilku(int miejsceDodatkowegoPosilku){
        String ileSkipnac = "";
        switch (miejsceDodatkowegoPosilku){
            case 1 -> ileSkipnac = "po drugim sniadaniu: ";
            case 2 -> ileSkipnac = "po obiedzie: ";
            case 3 -> ileSkipnac = "po kolacji: ";
            default -> ileSkipnac = "";
        }
        return ileSkipnac;
    }



    public void stworzPlikZDaniem(Dania dania){
        //File file = new File("/C:/Users/olekb/IdeaProjects/dietaProjekt/src/Posilki/"+nazwa);
        File file = new File("/C:/IntelliJNauka/dietaProjekt/src/Posilki/"+dania.getNazwaDania());
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
                    printWriter.println(dania.getNazwaDania());
                    printWriter.println(dania.typPosilku);
                    for (Produkt produkt : dania.getSkladDania()) {
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


    public boolean sprawdzCzyIstniejeTakiPlik(String nazwa){

        //File fileNazwy = new File("/C:/Users/olekb/IdeaProjects/dietaProjekt/src/Posilki/");
        File fileNazwy = new File("/C:/IntelliJNauka/dietaProjekt/src/Posilki/");
        String[] nazwyPlikow  = fileNazwy.list();
        boolean flaga = true;
        for (String s : nazwyPlikow) {
            if (Objects.equals(s, nazwa)) {
                flaga = false;
            }
        }
        return flaga;
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

