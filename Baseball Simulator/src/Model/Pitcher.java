package Model;

public class Pitcher {
    private String name;
    private String pos;
    private Integer loc;
    private Integer vel;
    private Integer sta;

    public Pitcher(String name, String pos, Integer loc, Integer vel, Integer sta) {
        this.name = name;
        this.pos = pos;
        this.loc = loc;
        this.vel = vel;
        this.sta = sta;
    }

    public String getName() {
        return name;
    }

    public String getPos() {
        return pos;
    }

    public Integer getLoc() {
        return loc;
    }

    public Integer getVel() {
        return vel;
    }

    public Integer getSta() {
        return sta;
    }
}
