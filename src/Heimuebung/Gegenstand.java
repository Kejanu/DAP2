package Heimuebung;

public class Gegenstand {

    private String name;
    private int gewicht;
    private int wert;

    public Gegenstand(String name, int gewicht, int wert) {
        this.gewicht = gewicht;
        this.wert = wert;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getGewicht() {
        return gewicht;
    }

    public void setGewicht(int gewicht) {
        this.gewicht = gewicht;
    }

    public int getWert() {
        return wert;
    }

    public void setWert(int wert) {
        this.wert = wert;
    }
}
