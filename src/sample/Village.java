package sample;

public class Village {
    private String cords;
    private int numberOfAttacks;

    public Village(){}

    public Village(String cords, int numberOfAttacks){
        this.cords = cords;
        this.numberOfAttacks = numberOfAttacks;
    }

    public String getCords() {
        return cords;
    }

    public void setCords(String cords) {
        this.cords = cords;
    }

    public int getNumberOfAttacks() {
        return numberOfAttacks;
    }

    public void setNumberOfAttacks(int numberOfAttacks) {
        this.numberOfAttacks = numberOfAttacks;
    }

    public String toString(){
        return "Kordy: " + cords + "Liczba atakow: " + numberOfAttacks;
    }
}
