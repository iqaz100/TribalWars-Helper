package sample;

import java.util.ArrayList;
import java.util.List;

public class Player implements Comparable<Player>{
    private String name;
    private int fullOffCount;
    private int threefourOffCount;
    private int halfOffCount;
    private int quarterOffCount;
    private int countedOffs;
    public List<PlayerObjectives> objectives = new ArrayList<>();

    public Player(){}

    public Player(String name, int fullOffCount, int threefourOffCount, int halfOffCount, int quarterOffCount, int countedOffs){
        this.name = name;
        this.fullOffCount = fullOffCount;
        this.threefourOffCount = threefourOffCount;
        this.halfOffCount = halfOffCount;
        this.quarterOffCount = quarterOffCount;
        this.countedOffs = countedOffs;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getFullOffCount() {
        return fullOffCount;
    }

    public void setFullOffCount(int fullOffCount) {
        this.fullOffCount = fullOffCount;
    }

    public int getThreefourOffCount() {
        return threefourOffCount;
    }

    public void setThreefourOffCount(int threefourOffCount) {
        this.threefourOffCount = threefourOffCount;
    }

    public int getHalfOffCount() {
        return halfOffCount;
    }

    public void setHalfOffCount(int halfOffCount) {
        this.halfOffCount = halfOffCount;
    }

    public int getQuarterOffCount() {
        return quarterOffCount;
    }

    public void setQuarterOffCount(int quarterOffCount) {
        this.quarterOffCount = quarterOffCount;
    }

    public String toString(){
        return "Nick: " + name + " fullOffy: " + fullOffCount;
    }

    public int getCountedOffs() {
        return countedOffs;
    }

    public void setCountedOffs(int countedOffs) {
        this.countedOffs = countedOffs;
    }

    @Override
    public int compareTo(Player p){
        return this.countedOffs - p.getCountedOffs();
    }
}
