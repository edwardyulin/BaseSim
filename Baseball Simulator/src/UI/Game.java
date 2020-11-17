package UI;

import Model.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Random;

public class Game {

    private Integer homeScore;
    private Integer awayScore;
    private Integer outs;
    private Integer inning;

    private Team home;
    private Team away;

    private Team offenseTeam;
    private Team defenseTeam;

    private Pitcher currentPitcher;
    private Batter currentBatter;

    private Integer homePitcherSta;
    private Integer awayPitcherSta;

    private Batter firstBase;
    private Batter secondBase;
    private Batter thirdBase;
    private Batter homePlate;

    private Integer homeBatOrder;
    private Integer awayBatOrder;

    private ArrayList<Integer> scoreDiffPerInning;
    private ArrayList<Integer> homeScorePerInning;
    private ArrayList<Integer> awayScorePerInning;

    private ArrayList<String> homeHitType;
    private ArrayList<String> awayHitType;

    private LinkedHashMap<Batter, ArrayList<Integer>> homeBatterStats;
    private LinkedHashMap<Batter, ArrayList<Integer>> awayBatterStats;
    private LinkedHashMap<Pitcher, ArrayList<Integer>> homePitcherStats;
    private LinkedHashMap<Pitcher, ArrayList<Integer>> awayPitcherStats;

    private Boolean isWalkOff;
    private Boolean isThereBottomNinth;

    private Integer homeHitNum;
    private Integer awayHitNum;
    private Integer homeErrorNum;
    private Integer awayErrorNum;

    private Boolean isGameFinished;

    private Pitcher homeStarter;
    private Pitcher awayStarter;




    public Game(Team home, Team away) {
        this.home = home;
        this.away = away;
        outs = 0;
        homeScore = 0;
        awayScore = 0;
        inning = 1;
        offenseTeam = away;
        defenseTeam = home;
        firstBase = null;
        secondBase = null;
        thirdBase = null;
        homePlate = null;
        homeBatOrder = 0;
        awayBatOrder = 0;
        isWalkOff = false;
        isThereBottomNinth = true;
        homeHitNum = 0;
        awayHitNum = 0;
        homeErrorNum = 0;
        awayErrorNum = 0;
        isGameFinished = false;
        homeStarter = home.getPitchers().get(0);
        awayStarter = away.getPitchers().get(0);
        currentPitcher = defenseTeam.getPitchers().get(0);
        currentBatter = offenseTeam.getBatters().get(awayBatOrder);
        homePitcherSta = currentPitcher.getSta();
        awayPitcherSta = away.getPitchers().get(0).getSta();
        scoreDiffPerInning = new ArrayList<>();
        homeScorePerInning = new ArrayList<>();
        awayScorePerInning = new ArrayList<>();

        homePitcherStats = new LinkedHashMap<>();
        awayPitcherStats = new LinkedHashMap<>();
        homePitcherStats.put(home.getPitchers().get(0), makeNewPitcherStatArray());
        awayPitcherStats.put(away.getPitchers().get(0), makeNewPitcherStatArray());

        homeHitType = new ArrayList<>();
        awayHitType = new ArrayList<>();
        homeBatterStats = new LinkedHashMap<>();
        awayBatterStats = new LinkedHashMap<>();
        for (int i= 0 ; i < 9 ; i++) {
            homeBatterStats.put(home.getBatters().get(i), makeNewBatterStatArray());
            awayBatterStats.put(away.getBatters().get(i), makeNewBatterStatArray());
            homeHitType.add("");
            awayHitType.add("");
        }


        System.out.println("Game Start!");
        System.out.println("----------------------Top " + inning + "---------------------------");
        faceOff(currentPitcher, currentBatter);
    }


    public void faceOff(Pitcher p, Batter b) {
        System.out.println("Pitcher: " + p.getName() + ", Batter: " + b.getName());
        Random walkProb = new Random();
        int walkProbInt = walkProb.nextInt(5 * (b.getDis() + p.getLoc()));
        Random hitProb = new Random();
        int hitProbInt = 3 * (p.getLoc() + p.getVel());
        Random typeOfHit = new Random();
        int typeOfHitInt = typeOfHit.nextInt(10000);
        Random kProb = new Random();
        int kProbInt = (1 * b.getCon()) + (40 * b.getDis());

        if ((2 * p.getLoc()) + (2 * p.getVel()) > kProb.nextInt(kProbInt)) { //strike out condition
            recordPitcher("K", defenseTeam);
            recordBatter("K", offenseTeam);
            homePitcherSta += 5;
            strikeOut(b);
        } else if (b.getDis() >= walkProbInt) {  //walk condition
            recordPitcher("BB", defenseTeam);
            recordBatter("BB", offenseTeam);
            homePitcherSta -= 5;
            walk(b);
        } else if (b.getDis() + b.getCon() >= hitProb.nextInt(hitProbInt)) {  //hit condition
            addTeamHitTotal();
            recordPitcher("hit", defenseTeam);
            if ((0.5 * b.getCon()) + (20 * b.getPwr()) > typeOfHitInt) {  //hr condition
                recordBatter("HR", offenseTeam);
                homePitcherSta -= 10;
                homerun(b);
            } else if (1900 > typeOfHitInt) {  //triple condition
                recordBatter("3B", offenseTeam);
                awayPitcherSta -= 10;
                triple(b);
            } else if (20 * (b.getPwr() + b.getCon()) > typeOfHitInt) {  //double condition
                recordBatter("2B", offenseTeam);
                homePitcherSta -= 5;
                doubleHit(b);
            } else {  //single condition
                recordBatter("1B", offenseTeam);
                homePitcherSta -= 3;
                single(b);
            }
        } else { //batter out (ground out or fly out)
            homePitcherSta += 0;
            addOut();
        }
//        for (MatchUp mu : allStat) {
//            if (p == mu.getPitcher() && b == mu.getBatter()) {
//                avg = mu.getAvg();
//                obp = mu.getObp();
//                slg = mu.getSlg();
//                ops = mu.getOps();
//            }
//        }
    }

    private ArrayList<Integer> makeNewPitcherStatArray() {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < 5;i++) {
            temp.add(0);
        }
        return temp;
    }


    private ArrayList<Integer> makeNewBatterStatArray() {
        ArrayList<Integer> temp = new ArrayList<>();
        for (int i = 0; i < 8 ; i ++) {
            temp.add(0);
        }
        return temp;
    }


    private void recordPitcher(String outcome, Team team) {
        ArrayList<Integer> stat;
        if (team == home) {
            stat = homePitcherStats.get(currentPitcher);
        } else {
            stat = awayPitcherStats.get(currentPitcher);
        }
        if (outcome.equals("hit")) {
            stat.set(3, stat.get(3) + 1);
        } else if (outcome.equals("K")) {
            stat.set(1, stat.get(1) + 1);
            stat.set(0, stat.get(0) + 1);
        } else if (outcome.equals("BB")) {
            stat.set(2, stat.get(2) + 1);
        } else if (outcome.equals("sac")) {
            stat.set(0, stat.get(0) + 1);
        } else if (outcome.equals("DP")) {
            stat.set(0, stat.get(0) + 2);
        } else {
            stat.set(0, stat.get(0) + 1);
        }

    }

    private void recordBatter(String bo, Team team) {
        final boolean hitCondition = bo.equals("HR") || bo.equals("3B") || bo.equals("2B") || bo.equals("1B");
        ArrayList<Integer> stat;
        if (team == home) {
            stat = homeBatterStats.get(currentBatter);
        } else {
            stat = awayBatterStats.get(currentBatter);
        }
        if (hitCondition) {
            if (bo.equals("HR")) {
                stat.set(5, stat.get(5) + 1);
            } else if (bo.equals("3B")) {
                stat.set(4, stat.get(4) + 1);
            } else if (bo.equals("2B")) {
                stat.set(3, stat.get(3) + 1);
            }
            stat.set(0, stat.get(0) + 1);
            stat.set(1, stat.get(1) + 1);
            stat.set(2, stat.get(2) + 1);
            if (team == home) {
                homeHitType.set(homeBatOrder, homeHitType.get(homeBatOrder) + " " + bo);
            } else {
                awayHitType.set(awayBatOrder, awayHitType.get(awayBatOrder) + " " + bo);
            }
        } else if (bo.equals("K")) {
            stat.set(0, stat.get(0) + 1);
            stat.set(1, stat.get(1) + 1);
            stat.set(6, stat.get(6) + 1);
        } else if (bo.equals("Out")) {
            stat.set(0, stat.get(0) + 1);
            stat.set(1, stat.get(1) + 1);
        } else {
            if (bo.equals("BB")) {
                stat.set(7, stat.get(7) + 1);
            }
            stat.set(0, stat.get(0) + 1);
            if (team == home) {
                homeHitType.set(homeBatOrder, homeHitType.get(homeBatOrder) + " " + bo);
            } else {
                awayHitType.set(awayBatOrder, awayHitType.get(awayBatOrder) + " " + bo);
            }
        }
    }

    private void addTeamHitTotal() {
        if (offenseTeam == away) {
            awayHitNum++;
        } else {
            homeHitNum++;
        }
    }

    private void strikeOut(Batter b) {
        System.out.println(b.getName() + " is struck out");
        // switch offense when 3 outs
        if (outs == 2) {
            outs = 0;
            switchOffense();
            // else record an out and proceed to next batter
        } else {
            outs ++;
            System.out.println("Out(s): " + outs);
            System.out.println("");
            nextBatter();
        }
    }

    private void walk(Batter b) {
        System.out.println(b.getName() + " is walked");
        if (firstBase == null && secondBase == null && thirdBase == null) { // no runner on base
            firstBase = b;
        } else if (firstBase != null && secondBase == null && thirdBase == null) { //runner on first
            secondBase = firstBase;
            firstBase = b;
        } else if (firstBase == null && secondBase != null && thirdBase == null) { //runner on second
            firstBase = b;
        } else if (firstBase == null && secondBase == null && thirdBase != null) { //runner on third
            firstBase = b;
        } else if (firstBase != null && secondBase != null && thirdBase == null) { //runners on first and second
            thirdBase = secondBase;
            secondBase = firstBase;
            firstBase = b;
        } else if (firstBase != null && secondBase == null && thirdBase != null) { //runners on first and third
            secondBase = firstBase;
            firstBase = b;
        } else if (firstBase == null && secondBase != null && thirdBase != null) { //runners on second and third
            firstBase = b;
        } else if (firstBase != null && secondBase != null && thirdBase != null) { //runners on all bases
            thirdBase = secondBase;
            secondBase = firstBase;
            firstBase = b;
            addScore(1);
        }
        nextBatter();

    }

    public Batter getFielder(int side) {
        Batter fielder = null;
        for (Batter flder : defenseTeam.getBatters()) {
            if (side == 0 && flder.getPos().equals("LF")) {
                fielder = flder;
            } else if (side == 1 && flder.getPos().equals("CF")) {
                fielder = flder;
            } else if (side == 2 && flder.getPos().equals("RF")) {
                fielder = flder;
            }
        }
        assert  fielder != null;
        return fielder;
    }

    public void single(Batter b) {
        Random hitSide = new Random();
        int hitSideInt = hitSide.nextInt(3);
        Batter fielder = getFielder(hitSideInt);
        if (hitSideInt == 0) {
            System.out.println(b.getName() + " hits a single to the left field");
        } else if (hitSideInt == 1) {
            System.out.println(b.getName() + " hits a single to the centre field");
        } else {
            System.out.println(b.getName() + " hits a single to the right field");
        }
        if (firstBase == null && secondBase == null && thirdBase == null) { // no runner on base
            firstBase = b;
        } else if (firstBase != null && secondBase == null && thirdBase == null) { //runner on first
            if (hitSideInt == 0 && firstBase.getSpd() < fielder.getFld()) { //if ball is hit to LF and LFer has strong arm
                secondBase = firstBase; //runner stays at second
            } else {
                thirdBase = firstBase; //else runner goes to third
            }
            firstBase = b;
        } else if (firstBase == null && secondBase != null && thirdBase == null) { //runner on second
            if (secondBase.getSpd() < fielder.getFld()) {
                thirdBase = secondBase; //runner stays at third if fielder has strong arm
            } else {
                addScore(1);
            }
            secondBase = null;
            firstBase = b;
        } else if (firstBase == null && secondBase == null && thirdBase != null) { //runner on third
            thirdBase = null;
            firstBase = b;
            addScore(1);
        } else if (firstBase != null && secondBase != null && thirdBase == null) { //runners on first and second
            if (hitSideInt == 0 && fielder.getFld() > secondBase.getSpd()) { //hits towards LF and LFer has strong arm
                thirdBase = secondBase;
                secondBase = firstBase;
            } else if (hitSideInt == 0) { //hits towards LF and LFer has weak arm
                secondBase = firstBase;
                addScore(1);
            } else if (hitSideInt == 1 && fielder.getFld() > secondBase.getSpd() + 5) { //hits towards CF and CFer has strong arm
                thirdBase = secondBase;
                secondBase = firstBase;
            } else if (hitSideInt == 1) { //hits towards where CFer has weak arm
                thirdBase = firstBase;
                secondBase = null;
                addScore(1);
            } else if (hitSideInt == 2 && fielder.getFld() > secondBase.getSpd() + 10) { //hits towards RF and RFer has strong arm
                thirdBase = secondBase;
                secondBase = firstBase;
            } else { //hits towards RF and RFer has weak arm
                thirdBase = firstBase;
                secondBase = null;
                addScore(1);
            }
            firstBase = b;
        } else if (firstBase != null && secondBase == null && thirdBase != null) { //runners on first and third
            if (hitSideInt == 0) { //hits towards LF
                secondBase = firstBase;
            } else if (hitSideInt == 1 && fielder.getFld() > firstBase.getSpd()) { //hits towards CF and CFer has strong arm
                thirdBase = null;
                secondBase = firstBase;
            } else if (hitSideInt == 1) { //hits towards where CFer has weak arm
                thirdBase = firstBase;
            } else { //hits towards RF
                thirdBase = firstBase;
            }
            firstBase = b;
            addScore(1);
        } else if (firstBase == null && secondBase != null && thirdBase != null) { //runners on second and third
            if (hitSideInt == 0 && fielder.getFld() > secondBase.getSpd() + 7) { //hits towards LF and LFer has strong arm
                thirdBase = secondBase;
                addScore(1);
            } else if (hitSideInt == 0) { //hits towards LF and LFer has weak arm
                thirdBase = null;
                addScore(2);
            } else if (hitSideInt == 1 && fielder.getFld() > secondBase.getSpd() + 7) { //hits towards CF and CFer has strong arm
                thirdBase = firstBase;
                addScore(1);
            } else if (hitSideInt == 1) { //hits towards where CFer has weak arm
                thirdBase = null;
                addScore(2);
            } else if (hitSideInt == 2 && fielder.getFld() > secondBase.getSpd() + 10) { //hits towards RF and RFer has strong arm
                thirdBase = firstBase;
                addScore(1);
            } else { //hits towards RF and RFer has weak arm
                thirdBase = null;
                addScore(2);
            }
            secondBase = null;
            firstBase = b;
        } else if (firstBase != null && secondBase != null && thirdBase != null) { //runners on all bases
            if (fielder.getFld() > secondBase.getSpd() + 10) { //outfielder has strong arm
                thirdBase = secondBase;
                secondBase = firstBase;
                addScore(1);
            } else { //outfielderhas weak arm
                thirdBase = firstBase;
                secondBase = null;
                addScore(2);
            }
        }
        nextBatter();
    }

    public void doubleHit(Batter b) {
        Random hitSide = new Random();
        int hitSideInt = hitSide.nextInt(3);
        Batter fielder = getFielder(hitSideInt);
        if (hitSideInt == 0) {
            System.out.println(b.getName() + " hits a double to the left field");
        } else if (hitSideInt == 1) {
            System.out.println(b.getName() + " hits a double to the centre field");
        } else {
            System.out.println(b.getName() + " hits a double to the right field");
        }
        if (firstBase == null && secondBase == null && thirdBase == null) { // no runner on base
            secondBase = b;
        } else if (firstBase != null && secondBase == null && thirdBase == null) { //runner on first
            if (fielder.getFld() > firstBase.getSpd()) {
                thirdBase = firstBase;
            } else {
                addScore(1);
            }
            firstBase = null;
            secondBase = b;
        } else if (firstBase == null && secondBase != null && thirdBase == null) { //runner on second
            secondBase = b;
            addScore(1);
        } else if (firstBase == null && secondBase == null && thirdBase != null) { //runner on third
            thirdBase = null;
            secondBase = b;
            addScore(1);
        } else if (firstBase != null && secondBase != null && thirdBase == null) { //runners on first and second
            if (fielder.getFld() > secondBase.getSpd()) { //outfielder has strong arm
                thirdBase = firstBase;
                addScore(1);
            } else { //outfielder has weak arm
                addScore(2);
            }
            firstBase = null;
            secondBase = b;
        } else if (firstBase != null && secondBase == null && thirdBase != null) { //runners on first and third
            if (hitSideInt == 0) { //hits towards LF
                secondBase = firstBase;
                thirdBase = null;
                addScore(1);
            } else if (hitSideInt == 1 && fielder.getFld() > firstBase.getSpd()) { //hits towards CF and CFer has strong arm
                thirdBase = firstBase;
                addScore(1);
            } else if (hitSideInt == 1) { //hits towards where CFer has weak arm
                thirdBase = null;
                addScore(2);
            } else if (hitSideInt == 2 && fielder.getFld() > firstBase.getSpd() + 7) { //hits towards RF and RFer has strong arm
                thirdBase = firstBase;
                addScore(1);
            } else { //hits towards RF and RFer has weak arm
                thirdBase = null;
                addScore(2);
            }
            secondBase = b;
            firstBase = null;
            addScore(1);
        } else if (firstBase == null && secondBase != null && thirdBase != null) { //runners on second and third
            thirdBase = null;
            secondBase = b;
            addScore(2);
        } else if (firstBase != null && secondBase != null && thirdBase != null) { //runners on all bases
            if (fielder.getFld() > firstBase.getSpd() + 10) {
                thirdBase = firstBase;
                addScore(2);
            } else {
                thirdBase = null;
                addScore(3);
            }
            secondBase = b;
            firstBase = null;
        }
        nextBatter();
    }

    public void triple(Batter b) {
        System.out.println(b.getName() + " hits a triple");
        if (firstBase == null && secondBase == null && thirdBase == null) { // no runner on base
        } else if ((firstBase == null && secondBase == null) || (firstBase == null && thirdBase == null) ||
                (secondBase == null && thirdBase == null)) { //one runner on base
            addScore(1);
        } else if (firstBase == null || secondBase == null || thirdBase == null) { //two runners on base
            addScore(2);
        } else { //runners on all bases
            addScore(3);
        }
        clearBases();
        thirdBase = b;
        nextBatter();
    }



    public void homerun(Batter b) {
        System.out.println(b.getName() + " hits a homerun!");
        if (firstBase == null && secondBase == null && thirdBase == null) { // no runner on base
            addScore(1);
        } else if ((firstBase == null && secondBase == null) || (firstBase == null && thirdBase == null) ||
                (secondBase == null && thirdBase == null)) { //one runner on base
            addScore(2);
        } else if (firstBase == null || secondBase == null || thirdBase == null) { //two runners on base
            addScore(3);
        } else { //runners on all bases
            addScore(4);
        }
        clearBases();
        nextBatter();
    }

    private void nextBatter() {
        pitchingSubstitution();
        //call up the next batter for the away team
         if (offenseTeam == away) {
            if (offenseTeam.getBatters().size() == awayBatOrder + 1) {
                awayBatOrder = 0;
            } else {
                awayBatOrder++;
            }
            currentBatter = offenseTeam.getBatters().get(awayBatOrder);
        //call up the next batter for the home team
        } else if (offenseTeam == home) {
            if (offenseTeam.getBatters().size() == homeBatOrder + 1) {
                homeBatOrder = 0;
            } else {
                homeBatOrder ++;
            }
            currentBatter = offenseTeam.getBatters().get(homeBatOrder);
        }
         System.out.println("");
         faceOff(currentPitcher, currentBatter);

    }

    private void pitchingSubstitution() {

        //call up closer
        if (inning >= 9 && currentPitcher.getPos() != "CP") {
            if (home == defenseTeam && (awayScore + 3) >= homeScore && homeScore > awayScore) {
                currentPitcher = home.getCloser();
                homePitcherSta = currentPitcher.getSta();
                homePitcherStats.put(currentPitcher, makeNewPitcherStatArray());
            } else if (away == defenseTeam && (homeScore + 3) >= awayScore && awayScore > homeScore) {
                currentPitcher = away.getCloser();
                awayPitcherSta = currentPitcher.getSta();
                awayPitcherStats.put(currentPitcher, makeNewPitcherStatArray());
            }
        }

        //if the current pitcher is SP
        if (currentPitcher.getPos().equals("SP")) {
            if (home == defenseTeam) {
                ArrayList<Integer> SPstat = homePitcherStats.get(currentPitcher);
                if ((homePitcherSta < 50) || (inning >= 7 && Math.abs(awayScore - homeScore) <= 2)
                        || SPstat.get(4) > 5) {
                    home.getPitchers().remove(0);
                    currentPitcher = home.getPitchers().get(0);
                    homePitcherSta = currentPitcher.getSta();
                    homePitcherStats.put(currentPitcher, makeNewPitcherStatArray());
                }
            } else if (away == defenseTeam) {
                ArrayList<Integer> SPstat = awayPitcherStats.get(currentPitcher);
                if ((awayPitcherSta < 50) || (inning >= 7 && Math.abs(homeScore - awayScore) <= 2)
                        || SPstat.get(4) > 5) {
                    away.getPitchers().remove(0);
                    currentPitcher = away.getPitchers().get(0);
                    awayPitcherSta = currentPitcher.getSta();
                    awayPitcherStats.put(currentPitcher, makeNewPitcherStatArray());
                }
            }

        //if the current pitcher is RP
        } else if (currentPitcher.getPos().equals("RP")) {
//            if (homeScore > awayScore && home.getPitchers().contains(winCandidate)) {
//                winCandidate = currentPitcher;
//            } else if (awayScore > homeScore && away.getPitchers().contains(winCandidate)) {
//                winCandidate = currentPitcher;
//            }
            if (home == defenseTeam) {
                ArrayList<Integer> RPstat = homePitcherStats.get(currentPitcher);
                if (homePitcherSta < 40 || RPstat.get(0) > 2 || RPstat.get(2) > 2 || RPstat.get(4) > 2) {
                    home.getPitchers().remove(0);
                    currentPitcher = home.getPitchers().get(0);
                    homePitcherSta = currentPitcher.getSta();
                    homePitcherStats.put(currentPitcher, makeNewPitcherStatArray());
                }
            } else if (away == defenseTeam) {
                ArrayList<Integer> RPstat = awayPitcherStats.get(currentPitcher);
                if (awayPitcherSta < 40 || RPstat.get(0) > 2 || RPstat.get(2) > 2 || RPstat.get(4) > 2) {
                    away.getPitchers().remove(0);
                    currentPitcher = away.getPitchers().get(0);
                    awayPitcherSta = currentPitcher.getSta();
                    awayPitcherStats.put(currentPitcher, makeNewPitcherStatArray());
                }
            }
        }
    }


    private void addScore(int x) {
        System.out.println(currentBatter.getName() + " recorded " + x + " RBI");
        if (offenseTeam == home) {
            awayPitcherStats.get(currentPitcher).set(4, awayPitcherStats.get(currentPitcher).get(4) + x);
            homeScore = homeScore + x;
            System.out.println("Score " + awayScore + " : " + homeScore);
            if (inning >= 9 && homeScore > awayScore) { // walk-off
                isWalkOff = true;
                int prevSum = 0;
                for (int i = 0; i < inning - 1; i++) {
                    prevSum += homeScorePerInning.get(i);
                }
                homeScorePerInning.add(homeScore - prevSum);

                System.out.println(currentBatter.getName() + " walked off the game!");
                gameFinished();
            }
        } else {
            homePitcherStats.get(currentPitcher).set(4, homePitcherStats.get(currentPitcher).get(4) + x);
            awayScore = awayScore + x;
            System.out.println("Score " + awayScore + " : " + homeScore);
        }
    }


    public void addOut() {
        Random flyOrGround = new Random();
        int flyOrGroundInt = flyOrGround.nextInt(10);
        if (checkError(flyOrGroundInt)) {
            processError(flyOrGroundInt);
        } else {
            recordBatter("Out", offenseTeam);
            if (flyOrGroundInt == 0) {
                System.out.println(currentBatter.getName() + " grounds to first");
            } else if (flyOrGroundInt == 1) {
                System.out.println(currentBatter.getName() + " grounds to second");
            } else if (flyOrGroundInt == 2) {
                System.out.println(currentBatter.getName() + " grounds to short stop");
            } else if (flyOrGroundInt == 3) {
                System.out.println(currentBatter.getName() + " grounds to third");
            } else if (flyOrGroundInt == 4) {
                System.out.println(currentBatter.getName() + " grounds to the pitcher");
            } else if (flyOrGroundInt == 5) {
                System.out.println(currentBatter.getName() + " flies to left outfield");
            } else if (flyOrGroundInt == 6) {
                System.out.println(currentBatter.getName() + " flies to centre outfield");
            } else if (flyOrGroundInt == 7) {
                System.out.println(currentBatter.getName() + " flies to right outfield");
            } else if (flyOrGroundInt == 8) {
                System.out.println(currentBatter.getName() + " flies to the infielders");
            } else if (flyOrGroundInt == 9) {
                System.out.println(currentBatter.getName() + " flies to the catcher");
            }
        }
        outs++;
        checkInningOver(flyOrGroundInt);
    }

    private void checkInningOver(int flyOrGroundInt) {
            // switch offense when 3 outs
        if (outs >= 3) {
            outs = 0;
            recordPitcher("out", defenseTeam);
            switchOffense();
            // else record an out and proceed to next batter
        } else {
            if (!isGameFinished) {
                if (thirdBase != null && flyOrGroundInt >= 5 && flyOrGroundInt < 8) { //sac fly condition
                    recordPitcher("sac", defenseTeam);
                    sacFly(flyOrGroundInt);
                    runnerAdvance2ndTo3rd(flyOrGroundInt);
                    System.out.println("Out(s): " + outs);
                    System.out.println("");
                    nextBatter();
                } else if (firstBase != null && flyOrGroundInt <= 4) { //double play condition
                    recordPitcher("DP", defenseTeam);
                    doublePlay(flyOrGroundInt);
                } else {
                    recordPitcher("out", defenseTeam);
                    System.out.println(currentBatter.getName() + " is out");
                    System.out.println("Out(s): " + outs);
                    System.out.println("");
                    nextBatter();
                }
            }
        }
    }



    private Boolean checkError(int i) {
        Batter fielder = null;
        if (i != 4 && i != 8) {  //Assumption: ground to pitcher and fly to infielders will never have errors
            for (Batter fld : defenseTeam.getBatters()) {
                if (i == 0 && fld.getPos().equals("1B")) {
                    fielder = fld;
                }
                if (i == 1 && fld.getPos().equals("2B")) {
                    fielder = fld;
                }
                if (i == 2 && fld.getPos().equals("SS")) {
                    fielder = fld;
                }
                if (i == 3 && fld.getPos().equals("3B")) {
                    fielder = fld;
                }
                if (i == 5 && fld.getPos().equals("LF")) {
                    fielder = fld;
                }
                if (i == 6 && fld.getPos().equals("CF")) {
                    fielder = fld;
                }
                if (i == 7 && fld.getPos().equals("RF")) {
                    fielder = fld;
                }
                if (i == 9 && fld.getPos().equals("C")) {
                    fielder = fld;
                }
            }
        } else {
            return false;
        }
        Random errorRate = new Random();
        int errorRateInt = errorRate.nextInt(1000);
        if (105 - fielder.getFld() >= errorRateInt) {
            if (home == defenseTeam) {
                homeErrorNum = homeErrorNum + 1;
            } else {
                awayErrorNum = awayErrorNum + 1;
            }
            return true;
        } else {
            return false;
        }
    }



    private void processError(int i) {
        System.out.println("The fielder has made an error");
        recordBatter("ER", offenseTeam);
        if (firstBase == null && secondBase == null && thirdBase == null) { // no runner on base
            firstBase = currentBatter;
        } else if (firstBase != null && secondBase == null && thirdBase == null) { //runner on first
            secondBase = firstBase;
            firstBase = currentBatter;
        } else if (firstBase == null && secondBase != null && thirdBase == null) { //runner on second
            firstBase = currentBatter;
        } else if (firstBase == null && secondBase == null && thirdBase != null) { //runner on third
            firstBase = currentBatter;
        } else if (firstBase != null && secondBase != null && thirdBase == null) { //runners on first and second
            thirdBase = secondBase;
            secondBase = firstBase;
            firstBase = currentBatter;
        } else if (firstBase != null && secondBase == null && thirdBase != null) { //runners on first and third
            secondBase = firstBase;
            firstBase = currentBatter;
        } else if (firstBase == null && secondBase != null && thirdBase != null) { //runners on second and third
            firstBase = currentBatter;
        } else if (firstBase != null && secondBase != null && thirdBase != null) { //runners on all bases
            thirdBase = secondBase;
            secondBase = firstBase;
            firstBase = currentBatter;
            addScore(1);
        }
        nextBatter();
    }

    private void doublePlay(int i) {
        System.out.println(currentBatter.getName() + " hits into a double play");
        if (thirdBase != null && secondBase != null) { //ground ball while bases are loaded
            thirdBase = secondBase;
            secondBase = firstBase;
        } else if (thirdBase != null) { //ground ball w/ runners on 1st and 3rd
            secondBase = firstBase;
            thirdBase = null;
        } else if (i == 3 && secondBase != null) { //ground ball to third baseman w/ runners on 1st and 2nd (can make this into triple play)
            secondBase = firstBase;
            thirdBase = null;
        } else if (secondBase != null) { //ground ball w/ runners on 1st and 2nd
            thirdBase = secondBase;
            secondBase = null;
        } else { //ground ball w/ runner on 1st
            clearBases();
        }
        firstBase = null;
        outs++;
        if (outs == 3) {
            outs = 0;
            switchOffense();
        } else {
            System.out.println("Out(s): " + outs);
            nextBatter();
        }
    }

    private void sacFly(int i) {
        if (i == 5) {
            System.out.println(currentBatter.getName() + " hits a sac fly to left field");
        } else if (i == 6) {
            System.out.println(currentBatter.getName() + " hits a sac fly to centre field");
        } else {
            System.out.println(currentBatter.getName() + " hits a sac fly to right field");
        }
        thirdBase = null;
        addScore(1);
    }

    private void runnerAdvance2ndTo3rd(int i) {
        if (secondBase != null) {
            if (i == 7) {
                thirdBase = secondBase;
                secondBase = null;
            }
        }
    }


    private void switchOffense() {
        System.out.println("Out(s): 3");
        //due up next for the away team
        if (offenseTeam == away) {
            if (offenseTeam.getBatters().size() == awayBatOrder + 1) {
                awayBatOrder = 0;
            } else {
                awayBatOrder++;
            }
            currentBatter = offenseTeam.getBatters().get(awayBatOrder);
            //due up  next for the home team
        } else if (offenseTeam == home) {
            if (offenseTeam.getBatters().size() == homeBatOrder + 1) {
                homeBatOrder = 0;
            } else {
                homeBatOrder ++;
            }
            currentBatter = offenseTeam.getBatters().get(homeBatOrder);
        }
        // change from top half (away bat) to bottom half (home bat)
        if (offenseTeam == away && defenseTeam == home) {
            int prevSum = 0;
            for (int i = 0; i < inning - 1; i++) {
                prevSum += awayScorePerInning.get(i);
            }
            if (inning == 1) {
                awayScorePerInning.add(awayScore);
            } else {
                awayScorePerInning.add(awayScore - prevSum);
            }
            if (inning == 9 && homeScore > awayScore) {  //ends in top 9 when home team is already winning
                isThereBottomNinth = false;
                gameFinished();
            } else if (!isGameFinished) {
                offenseTeam = home;
                defenseTeam = away;
                currentBatter = home.getBatters().get(homeBatOrder);
                currentPitcher = away.getPitchers().get(0);
                System.out.println("3 outs, switch side");
                System.out.println("----------------------Bot " + inning + "---------------------------");
                clearBases();
                pitchingSubstitution();
                faceOff(currentPitcher, currentBatter);
            }
        // change from bottom half (home bat) to top half of next inning (away bat)
        } else if (offenseTeam == home && defenseTeam == away){
            inning ++;

            int prevSum = 0;
            for (int i = 0; i < inning - 2; i++) {
                prevSum += homeScorePerInning.get(i);
            }
            if (inning == 1) {
                homeScorePerInning.add(homeScore);
            } else {
                homeScorePerInning.add(homeScore - prevSum);
            }

            scoreDiffPerInning.add(homeScore - awayScore);
            if (inning >= 10 && homeScore != awayScore) {      //game ends when when inning reaches x
                gameFinished();
            } else if (!isGameFinished) {
                offenseTeam = away;
                defenseTeam = home;
                currentBatter = away.getBatters().get(awayBatOrder);
                currentPitcher = home.getPitchers().get(0);
                System.out.println("3 outs, switch side");
                System.out.println("----------------------Top " + inning + "---------------------------");
                clearBases();
                pitchingSubstitution();
                faceOff(currentPitcher, currentBatter);
            }
        }
    }

    private void clearBases() {
        firstBase = null;
        secondBase = null;
        thirdBase = null;
    }

//    private Pitcher winningPitcher() {
//        if (homeScore > awayScore) {
//            if (homePitcherStats.get(0).get(0) >= 15) {
//                return homeStarter;
//            }
//        } else {
//            if (awayPitcherStats.get(0).get(0) >= 15) {
//                return  awayStarter;
//            }
//        }
//    }

    private void printScoreBoard() {
        //Scoreboard
        if (awayScorePerInning.size() <= 9) {
            System.out.print(" ");
        }

        System.out.print("    R  H  E");
        System.out.println("");

        System.out.print("Away: ");
        for (int i = 0; i < awayScorePerInning.size(); i++) {
            if (i > 9) {
                System.out.print(" ");
            }
            System.out.print(" " + awayScorePerInning.get(i));
        }
        addSpace(awayScore, 4);
        addSpace(awayHitNum, 1);
        addSpace(awayErrorNum, 1);

        System.out.println("");

        System.out.print("Home: ");
        for (int i = 0; i < homeScorePerInning.size(); i++) {
            if (i > 9) {
                System.out.print(" ");
            }
            System.out.print(" " + homeScorePerInning.get(i));
        }
        if (isWalkOff) {
            System.out.print("X");
            addSpace(homeScore, 3);
        }
        if (!isThereBottomNinth) {
            System.out.print(" X");
            addSpace(homeScore, 4);
        }
        if (isThereBottomNinth && !isWalkOff) {
            addSpace(homeScore, 4);
        }
        addSpace(homeHitNum, 1);
        addSpace(homeErrorNum, 1);
    }

    private void addSpace(int i, int size) {
        if (i < 10) {
            System.out.print(" ");
        }
        for (int j = 0; j < size; j++) {
            System.out.print(" ");
        }
        System.out.print(i);
    }

    private void printBattingBoard() {
        System.out.println("");
        System.out.println("");
        System.out.println("Away Batting:");
        int away = 0;
        for (Batter b : awayBatterStats.keySet()) {
            System.out.print("  " + (away + 1) + ": " + b.getName() + ": "
                    + awayBatterStats.get(b).get(2) + "/" + awayBatterStats.get(b).get(1) + " ");

            System.out.println("  " + awayHitType.get(away));
            away++;
        }

        System.out.println("Home Batting:");
        int home = 0;
        for (Batter b : homeBatterStats.keySet()) {
            System.out.print("  " + (home + 1) + ": " + b.getName() + ": "
                    + homeBatterStats.get(b).get(2) + "/" + homeBatterStats.get(b).get(1) + " ");
            System.out.println("  " + homeHitType.get(home));
            home++;
        }
    }

    private void printPitchingBoard() {
        System.out.println("");
        System.out.println("                            [IP, K, BB, H, R]");
        System.out.println("Home Team:");
        for (Pitcher p : homePitcherStats.keySet()) {
            System.out.print(p.getName());
            System.out.println("                " + homePitcherStats.get(p));
        }
        System.out.println("");
        System.out.println("Away Team:");
        for (Pitcher p : awayPitcherStats.keySet()) {
            System.out.print(p.getName());
            System.out.println("                " + awayPitcherStats.get(p));
        }

    }

    private void gameFinished() {
//        winningPitchers();
//        if (winCandidate.getPos() == "SP") {
//            System.out.println("W: " + winCandidate.getName());
//        }


//        System.out.println("Home pitching: ");
//        for (Pitcher p: homePitchOrder) {
//            System.out.println("    " + p.getName());
//        }
//        System.out.println("");
//        System.out.println("Away pitching: ");
//        for (Pitcher p: awayPitchOrder) {
//            System.out.println("    " + p.getName());
//        }


        if (!isGameFinished) {
            System.out.println("------------------------------------------------------");
            System.out.println("Game Finished");
            System.out.println("Away: " + awayScore + ", Home: " + homeScore);
            System.out.print("      ");
            for (int i = 1; i <= awayScorePerInning.size(); i++) {
                System.out.print(" " + i);
            }

            printScoreBoard();
            printBattingBoard();
            printPitchingBoard();
            isGameFinished = true;
        }
    }

    public ArrayList<ArrayList<Integer>> getHomeStats() {
        ArrayList<ArrayList<Integer>> bs = new ArrayList<>();
        for (Batter b: homeBatterStats.keySet()) {
            bs.add(homeBatterStats.get(b));
        }
        return bs;
    }

    public ArrayList<ArrayList<Integer>> getAwayStats() {
        ArrayList<ArrayList<Integer>> bs = new ArrayList<>();
        for (Batter b: awayBatterStats.keySet()) {
            bs.add(awayBatterStats.get(b));
        }
        return bs;
    }

    public Team getWinner() {
        if (awayScore > homeScore) {
            return away;
        } else {
            return home;
        }
    }

}
