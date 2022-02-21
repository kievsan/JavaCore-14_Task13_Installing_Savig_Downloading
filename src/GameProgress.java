//package knhel7.jd14.javacore14.task131;

import java.io.IOException;
import java.io.Serializable;

public class GameProgress implements Serializable {
    private static final long serialVersionUID = 1L;

    private int health;
    private int weapons;
    private int lvl;
    private double distance;

    public GameProgress(int health, int weapons, int lvl, double distance) throws IOException {
        this.health = health;
        this.weapons = weapons;
        this.lvl = lvl;
        this.distance = distance;
        Logger logger = Logger.INSTANCE;
        logger.log("An object of the 'Game Progress' class has been created.");
    }

    @Override
    public String toString() {
        return "GameProgress{" +
                "health=" + health +
                ", weapons=" + weapons +
                ", lvl=" + lvl +
                ", distance=" + distance +
                '}';
    }
}
