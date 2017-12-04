package ua.nure.lab2;

import java.util.ArrayList;
import java.util.List;

public class PotholerNode {

    private int x;
    private int y;
    private boolean pit;
    private boolean vampus;
    private boolean isVampusAlive;
    private boolean gold;
    private List<PotholerAgent> hunters;

    public PotholerNode(int x, int y) {
        this.x = x;
        this.y = y;
        this.pit = false;
        this.vampus = false;
        this.isVampusAlive = false;
        this.gold = false;
        this.hunters = new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public boolean isPit() {
        return pit;
    }

    public void setPit(boolean pit) {
        this.pit = pit;
    }

    public boolean isVampus() {
        return vampus;
    }

    public void setVampus(boolean vampus) {
        this.vampus = vampus;
    }

    public boolean isVampusAlive() {
        return isVampusAlive;
    }

    public void setVampusAlive(boolean vampusAlive) {
        isVampusAlive = vampusAlive;
    }

    public boolean isGold() {
        return gold;
    }

    public void setGold(boolean gold) {
        this.gold = gold;
    }

    public List<PotholerAgent> getHunters() {
        return hunters;
    }

    public void setHunters(List<PotholerAgent> hunters) {
        this.hunters = hunters;
    }
}
