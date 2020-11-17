package Model;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class Team {

    private ArrayList<Pitcher> pitchers;
    private ArrayList<Batter> batters;


    public Team() {
        pitchers = new ArrayList<>();
        batters = new ArrayList<>();
    }

    public ArrayList<Pitcher> getPitchers() {
        return pitchers;
    }

    public ArrayList<Batter> getBatters() {
        return batters;
    }

    public void getRoster() {
        System.out.println("Team:");
        for (Pitcher p : pitchers) {
            System.out.println("     Pitcher: " + p.getName());
        }
        System.out.println("");
        for (Batter b : batters) {
            System.out.println("     Batter: " + b.getName());
        }
    }

    public void addBatter(Batter batter) {
        batters.add(batter);
    }

    public void removeBatter(Batter batter) {
        if (batters.contains(batter)) {
            batters.remove(batter);
        } else {
            System.out.println(batter.getName() + "is not in this roster");
        }
    }

    public void addPitcher(Pitcher pitcher) {
        if (pitcher.getPos().equals("SP") || pitcher.getPos().equals("RP")
                || pitcher.getPos().equals("CP")) {
            pitchers.add(pitcher);
        } else {
            System.out.println(pitcher.getName() + "is not a pitcher");
        }
    }

    public void removePitcher(Pitcher pitcher) {
        if (pitchers.contains(pitcher)) {
            pitchers.add(pitcher);
        } else {
            System.out.println(pitcher.getName() + "is not in this roster");
        }
    }

    public Pitcher getCloser() {
        Pitcher cp = null;
        for (Pitcher c : pitchers) {
            if (c.getPos() == "CP") {
                cp = c;
            }
        }
        return cp;
    }
}
