package ua.nure.lab2;


import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class CavernEnvironment {

    private List<List<PotholerNode>> potholers = null;
    private List<PotholerListener> listenersCave;

    private List<NavigatorAgent> hunters;
    private List<NavigatorListener> listenersHunter;

    public CavernEnvironment() {
        potholers = new ArrayList<>();
        listenersCave = new ArrayList<>();

        hunters = new ArrayList<>();
        listenersHunter = new ArrayList<>();

        initCave();
    }

    private void initCave() {
        createPotholerNode();
        setData();
        setPit();
    }

    private void setPit() {
        int x;
        int y;
        for (x = 0; x < potholers.size(); x++) {
            for (y = 0; y < potholers.get(x).size(); y++) {
                setPit(x, y);
            }
        }
    }

    private void setPit(int x, int y) {
        if ((x != 0 || y != 0) && potholers.get(x).get(y).getHunters().isEmpty()) {
            if (Math.random() <= 0.2) {
                potholers.get(x).get(y).setPit(true);
            }
        }
    }

    private void setData() {
        Random r = new Random();
        int x, y;
        boolean placed = false;
        while (!placed) {
            x = r.nextInt(potholers.size());
            y = r.nextInt(potholers.get(1).size());
            if (x != 0 || y != 0) {
                potholers.get(x).get(y).setVampus(true);
                placed = true;
            }
        }

        placed = false;
        while (!placed) {
            x = r.nextInt(potholers.size());
            y = r.nextInt(potholers.get(1).size());
            if ((x != 0 || y != 0) && potholers.get(x).get(y).getHunters().isEmpty()) {
                potholers.get(x).get(y).setGold(true);
                placed = true;
            }
        }
    }

    private void createPotholerNode() {
        for (int i = 0; i < potholers.size(); i++) {
            for (int j = 0; j < potholers.get(i).size(); j++) {
                potholers.get(i).add(new PotholerNode(i, j));
            }
        }
    }

}
