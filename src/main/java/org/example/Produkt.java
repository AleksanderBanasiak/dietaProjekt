package org.example;

public class Produkt{
    private final int id;
    private final String nazwa;
    private final double kcal;
    private final double bialko;
    private final double weglowodany;
    private final double blonnik;
    private final double tluszcze;

    public Produkt(int id, String nazwa, double kcal, double bialko, double weglowodany, double blonnik, double tluszcze){
        this.id = id;
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





