package sample;

public class PlayerObjectives {
    private String cords;
    private int numberOfAttacks;

    public PlayerObjectives(){}

    public PlayerObjectives(String cords, int numberOfAttacks){
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
        return "" + cords + ":" + numberOfAttacks + "\n";
    }
}
