package org.example;

public class korzytanieZBazy {

    public static void main(String[] args) {


        bazaDanych bazaDanych = new bazaDanych();


        if(!bazaDanych.open()){

            System.out.println("Nie można otworzyć bazy");
            return;
        }

        bazaDanych.close();

    }

}
