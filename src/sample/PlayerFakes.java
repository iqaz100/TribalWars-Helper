package sample;

import java.util.ArrayList;
import java.util.List;

public class PlayerFakes {
    private String name;
    private List<Fake> attacks;

    public PlayerFakes(String name) {
        this.name = name;
        attacks = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Fake> getAttacks() {
        return attacks;
    }

    public void setAttacks(List<Fake> attacks) {
        this.attacks = attacks;
    }
}
