package Model;

public class Batter {
    private String name;
    private String pos;
    private Integer con;
    private Integer pwr;
    private Integer dis;
    private Integer spd;
    private Integer fld;

    public Batter(String name, String pos, Integer con, Integer pwr, Integer dis, Integer spd, Integer fld) {
        this.name = name;
        this.pos = pos;
        this.con = con;
        this.pwr = pwr;
        this.dis = dis;
        this.spd = spd;
        this.fld = fld;
    }

    public String getName() {
        return name;
    }
    public String getPos() {
        return pos;
    }

    public Integer getCon() {
        return con;
    }

    public Integer getPwr() {
        return pwr;
    }

    public Integer getDis() {
        return dis;
    }

    public Integer getSpd() {
        return spd;
    }

    public Integer getFld() {
        return fld;
    }
}
