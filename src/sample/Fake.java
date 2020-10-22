package sample;

public class Fake {

    private String attackedVillage;
    private String attackingPlayer;
    private String attackingVillage;

    public Fake(String attackedVillage, String attackingPlayer, String attackingVillage) {
        this.attackedVillage = attackedVillage;
        this.attackingPlayer = attackingPlayer;
        this.attackingVillage = attackingVillage;
    }

    public String getAttackedVillage() {
        return attackedVillage;
    }

    public void setAttackedVillage(String attackedVillage) {
        this.attackedVillage = attackedVillage;
    }

    public String getAttackingPlayer() {
        return attackingPlayer;
    }

    public void setAttackingPlayer(String attackingPlayer) {
        this.attackingPlayer = attackingPlayer;
    }

    public String getAttackingVillage() {
        return attackingVillage;
    }

    public void setAttackingVillage(String attackingVillage) {
        this.attackingVillage = attackingVillage;
    }
}
