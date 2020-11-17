package Model;

public class MatchUp {

    private Player pitcher;
    private Player batter;

    private double avg;
    private double obp;
    private double slg;
    private double ops;

    //constructor
    public MatchUp(Player pitcher, Player batter) {
        this.pitcher = pitcher;
        this.batter = batter;
    }

    //Set stats between a matchup
    public void setStat(double avg, double obp, double slg, double ops) {
        this.avg = avg;
        this.obp = obp;
        this.slg = slg;
        this.ops = ops;
    }

    public double getAvg() {
        return avg;
    }

    public double getObp() {
        return obp;
    }

    public double getSlg() {
        return slg;
    }

    public double getOps() {
        return ops;
    }

    public Player getPitcher() {
        return pitcher;
    }

    public Player getBatter() {
        return batter;
    }

    public void printMatch() {
        System.out.println(pitcher.getName() + batter.getName());
    }
}
