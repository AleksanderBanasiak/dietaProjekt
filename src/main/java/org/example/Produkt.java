package org.example;

public class Produkt{
    private int id;
    private String nazwa;
    private double kcal;
    private double bialko;
    private double weglowodany;
    private double blonnik;
    private double tluszcze;
    private static int nextId=1;

    public Produkt(String nazwa, double kcal, double bialko, double weglowodany, double blonnik, double tluszcze){
        this.id = nextId;
        nextId++;
        this.nazwa = nazwa;
        this.kcal = kcal;
        this.bialko = bialko;
        this.weglowodany = weglowodany;
        this.blonnik = blonnik;
        this.tluszcze = tluszcze;
    }

    public int getId() {
        return id;
    }

    public String getNazwa() {
        return nazwa;
    }

    public double getKcal() {
        return kcal;
    }

    public double getBialko() {
        return bialko;
    }

    public double getWeglowodany() {
        return weglowodany;
    }

    public double getBlonnik() {
        return blonnik;
    }

    public double getTluszcze() {
        return tluszcze;
    }
}





