package UI;

import Model.*;

import java.util.ArrayList;


public class Main {
    public static void main(String arg[]) {
        Team home = new Team();
        Team away = new Team();

        //home team pitching staff
        //starting pitchers
//        Pitcher kersh = new Pitcher("Clayton Kershaw", "SP", 97, 87, 99);
//        home.addPitcher(kersh);
        Pitcher john = new Pitcher("Randy Johnson", "SP", 92, 99, 99);
        home.addPitcher(john);
//        Pitcher jv = new Pitcher("Justin Verlander", "SP", 96, 93, 99);
//        home.addPitcher(jv);
        //relief pitchers
        Pitcher kimb = new Pitcher("Craig Kimbrel", "RP", 91, 95, 50);
        home.addPitcher(kimb);
        Pitcher nate = new Pitcher("Joe Nathan", "RP", 93, 89, 50);
        home.addPitcher(nate);
        Pitcher hader = new Pitcher("Josh Hader", "RP", 85, 98, 50);
        home.addPitcher(hader);
        Pitcher wag = new Pitcher("Billy Wagner", "RP", 93, 99, 50);
        home.addPitcher(wag);
        Pitcher krod = new Pitcher("Francisco Rodriguez", "RP", 92, 90, 50);
        home.addPitcher(krod);
        Pitcher mo = new Pitcher("Mariano Rivera", "CP", 99, 95, 50);
        home.addPitcher(mo);

        //away team pitching staff
        //starting pitchers
//        Pitcher scherz = new Pitcher("Max Scherzer", "SP", 96, 90, 99);
//        away.addPitcher(scherz);
//        Pitcher doc = new Pitcher("Roy Halladay", "SP", 99, 80, 99);
//        away.addPitcher(doc);
        Pitcher pedro = new Pitcher("Pedro Martinez", "SP", 99, 95, 99);
        away.addPitcher(pedro);
        //relief pitchers
        Pitcher gagne = new Pitcher("Eric Gagne", "RP", 93, 97, 50);
        away.addPitcher(gagne);
        Pitcher chap = new Pitcher("Aroldis Chapman", "RP", 89, 99, 50);
        away.addPitcher(chap);
        Pitcher brit = new Pitcher("Zach Britton", "RP", 87, 92, 50);
        away.addPitcher(brit);
        Pitcher rob = new Pitcher("David Robertson", "RP", 85, 83, 50);
        away.addPitcher(rob);
        Pitcher vel = new Pitcher("Jose Valverde", "RP", 92, 91, 50);
        away.addPitcher(vel);
        Pitcher hoffy = new Pitcher("Trevor Hoffman", "CP", 99, 95, 50);
        away.addPitcher(hoffy);

        //away team batting order
        //batting 1st
        Batter mauer = new Batter("Joe Mauer", "C", 98, 73, 92, 85, 90);
        away.addBatter(mauer);
        //batting 2nd
        Batter vladdy = new Batter("Vladimir Guerrero", "RF", 98, 92, 89, 84, 87);
        away.addBatter(vladdy);
        //batting 3rd
        Batter miggy = new Batter("Miguel Cabrera", "1B", 98, 90, 82, 74, 70);
        away.addBatter(miggy);
        //batting 4th
        Batter manny = new Batter("Manny Ramirez", "LF", 97, 96, 87, 75, 75);
        away.addBatter(manny);
        //batting 5th
        Batter chippy = new Batter("Chipper Jones", "DH", 97, 91, 92, 74, 73);
        away.addBatter(chippy);
        //batting 6th
        Batter cap = new Batter("David Wright", "3B", 95, 82, 83, 77, 83);
        away.addBatter(cap);
        //batting 7th
        Batter beltran = new Batter("Carlos Beltran", "CF", 85, 88, 88, 87, 87);
        away.addBatter(beltran);
        //batting 8th
        Batter utley = new Batter("Chase Utley", "2B", 89, 86, 85, 79, 75);
        away.addBatter(utley);
        //batting 9th
        Batter rollins = new Batter("Jimmy Rollins", "SS", 80, 77, 81, 95, 84);
        away.addBatter(rollins);


        //home team batting order
        //batting 1st
        Batter suzuki = new Batter("Ichiro Suzuki", "RF", 99, 60, 89, 99, 98);
        home.addBatter(suzuki);
        //batting 2nd
        Batter jeter = new Batter("Derek Jeter", "SS", 96, 77, 80, 88, 95);
        home.addBatter(jeter);
        //batting 3rd
        Batter pujols = new Batter("Albert Pujols", "1B", 99, 96, 98, 67, 75);
        home.addBatter(pujols);
        //batting 4th
        Batter bonds = new Batter("Barry Bonds", "LF", 96, 99, 99, 69, 93);
        home.addBatter(bonds);
        //batting 5th
        Batter ortiz = new Batter("David Ortiz", "DH", 92, 91, 88, 70, 60);
        home.addBatter(ortiz);
        //batting 6th
        Batter arod = new Batter("Alex Rodriguez", "3B", 95, 96, 78, 86, 90);
        home.addBatter(arod);
        //batting 7th
        Batter trout = new Batter("Mike Trout", "CF", 95, 85, 91, 88, 89);
        home.addBatter(trout);
        //batting 8th
        Batter cano = new Batter("Robinson Cano", "2B", 95, 85, 79, 87, 80);
        home.addBatter(cano);
        //batting 9th
        Batter yaddy = new Batter("Yadier Molina", "C", 92, 72, 82, 71, 98);
        home.addBatter(yaddy);







        new Game(home, away);






//
//        ArrayList<ArrayList<ArrayList<Integer>>> hs = new ArrayList<>();
//        ArrayList<ArrayList<ArrayList<Integer>>> as = new ArrayList<>();
//
//        int numHomeWin = 0;
//        int numAwayWin = 0;
//
//        for (int i = 0 ; i < 162 ; i++) {
//            Game batterStats = new Game(home, away);
//            hs.add(batterStats.getHomeStats());
//            as.add(batterStats.getAwayStats());
//            if (batterStats.getWinner() == away) {
//                numAwayWin++;
//            } else {
//                numHomeWin++;
//            }
//        }
//        System.out.println("");
//        for (int j = 0 ; j < 9 ; j++) {
//            int pa = 0;
//            int ab = 0;
//            int hit = 0;
//            int doubleHit = 0;
//            int triple = 0;
//            int hr = 0;
//            int k = 0;
//            int bb = 0;
//            for (int i = 0; i < 162; i++) {
//                pa += hs.get(i).get(j).get(0);
//                ab += hs.get(i).get(j).get(1);
//                hit += hs.get(i).get(j).get(2);
//                doubleHit += hs.get(i).get(j).get(3);
//                triple += hs.get(i).get(j).get(4);
//                hr += hs.get(i).get(j).get(5);
//                k += hs.get(i).get(j).get(6);
//                bb += hs.get(i).get(j).get(7);
//            }
//            System.out.println(home.getBatters().get(j).getName() + ": PA:"
//                    + pa + " AB:" + ab + " Hit:" + hit + " 2B:" + doubleHit + " 3B:" + triple + " HR:" + hr
//                    + " K:" + k + " BB:" + bb + " AVG:" + hit + "/" + ab);
//        }
//        System.out.println("");
//        for (int j = 0 ; j < 9 ; j++) {
//            int pa = 0;
//            int ab = 0;
//            int hit = 0;
//            int doubleHit = 0;
//            int triple = 0;
//            int hr = 0;
//            int k = 0;
//            int bb = 0;
//            for (int i = 0; i < 162; i++) {
//                pa += as.get(i).get(j).get(0);
//                ab += as.get(i).get(j).get(1);
//                hit += as.get(i).get(j).get(2);
//                doubleHit += as.get(i).get(j).get(3);
//                triple += as.get(i).get(j).get(4);
//                hr += as.get(i).get(j).get(5);
//                k += as.get(i).get(j).get(6);
//                bb += as.get(i).get(j).get(7);
//            }
//            System.out.println(away.getBatters().get(j).getName() + ": PA:"
//                    + pa + " AB:" + ab + " Hit:" + hit + " 2B:" + doubleHit + " 3B:" + triple + " HR:" + hr
//                    + " K:" + k + " BB:" + bb + " AVG:" + hit + "/" + ab);
//        }
//
//        System.out.println("Home:" + numHomeWin + ", Away: " + numAwayWin);














































//        //pitchers
//        Player cole = new Player("Gerrit Cole", "P");
//        Player degrom = new Player("Jacob deGrom", "P");
//        Player jv = new Player("Justin Verlander", "P");
//        Player scherz = new Player("Max Scherzer", "P");
//        Player stras = new Player("Stephen Strasburg", "P");
//        Player flaherty = new Player("Jack Flaherty", "P");
//        Player buehler = new Player("Walker Buehler", "P");
//        Player clevy = new Player("Mike Clevinger", "P");
//        Player corbin = new Player("Patrick Corbin", "P");
//        Player morton = new Player("Charlie Morton", "P");
//
//        //batters
//        Player trout = new Player("Mike Trout", "B");
//        Player yelich = new Player("Christian Yelich", "B");
//        Player belli = new Player("Cody Bellinger", "B");
//        Player betts = new Player("Mookie Betts", "B");
//        Player breg = new Player("Alex Bregman", "B");
//        Player rendon = new Player("Anthony Rendon", "B");
//        Player nado = new Player("Nolan Arenado", "B");
//        Player lindor = new Player("Francisco Lindor", "B");
//        Player soto = new Player("Juan Soto", "B");
//        Player acuna = new Player("Ronald Acuna Jr.", "B");
//
//
//
//
//
//        //home team pitchers
//        home.addPitcher(cole);
//        home.addPitcher(buehler);
//        home.addPitcher(scherz);
//        home.addPitcher(clevy);
//        home.addPitcher(morton);
//
//        //home team batters
//        home.addBatter(yelich);
//        home.addBatter(belli);
//        home.addBatter(breg);
//        home.addBatter(lindor);
//        home.addBatter(soto);
//
//        //away team pitchers;
//        away.addPitcher(degrom);
//        away.addPitcher(jv);
//        away.addPitcher(stras);
//        away.addPitcher(flaherty);
//        away.addPitcher(corbin);
//
//        //away team batters;
//        away.addBatter(trout);
//        away.addBatter(betts);
//        away.addBatter(rendon);
//        away.addBatter(nado);
//        away.addBatter(acuna);
//
//        ArrayList<MatchUp> allStat = new ArrayList<>();
//        MatchUp mu1 = new MatchUp(cole, trout);
//        mu1.setStat(0.278, 0.381, 0.444, 0.825);
//        allStat.add(mu1);
//        MatchUp mu2 = new MatchUp(cole, yelich);
//        mu2.setStat(0.450, 0.522, 1.000, 1.522);
//        allStat.add(mu2);
//        MatchUp mu3 = new MatchUp(cole, belli);
//        mu3.setStat(0.333,0.333,0.667,1.000);
//        allStat.add(mu3);
//        MatchUp mu4 = new MatchUp(cole, betts);
//        mu4.setStat(0.545,0.583,0.818,1.402);
//        allStat.add(mu4);
//        MatchUp mu5 = new MatchUp(cole, breg);
//        mu5.setStat(0.333,0.333,0.667,1.000);
//        allStat.add(mu5);
//        MatchUp mu6 = new MatchUp(cole, rendon);
//        mu6.setStat(0.385,0.467,0.385,0.851);
//        allStat.add(mu6);
//        MatchUp mu7 = new MatchUp(cole, nado);
//        mu7.setStat(0.474,0.474,1.000,1.474);
//        allStat.add(mu7);
//        MatchUp mu8 = new MatchUp(cole, lindor);
//        mu8.setStat(0.100,0.182,0.100,0.282);
//        allStat.add(mu8);
//        MatchUp mu9 = new MatchUp(cole, soto);
//        mu9.setStat(0,0,0,0);
//        allStat.add(mu9);
//        MatchUp mu10 = new MatchUp(cole, acuna);
//        mu10.setStat(0,0,0,0);
//        allStat.add(mu10);
//
//        MatchUp mu11 = new MatchUp(degrom, trout);
//        mu11.setStat(0.500,0.667,0.500,1.167);
//        allStat.add(mu11);
//        MatchUp mu12 = new MatchUp(degrom, yelich);
//        mu12.setStat(0.441,0.486,0.500,0.986);
//        allStat.add(mu12);
//        MatchUp mu13 = new MatchUp(degrom, belli);
//        mu13.setStat(0.214,0.214,0.571,0.786);
//        allStat.add(mu13);
//        MatchUp mu14 = new MatchUp(degrom, betts);
//        mu14.setStat(0.200,0.200,0.200,0.400);
//        allStat.add(mu14);
//        MatchUp mu15 = new MatchUp(degrom, breg);
//        mu15.setStat(0,0,0,0);
//        allStat.add(mu15);
//        MatchUp mu16 = new MatchUp(degrom, rendon);
//        mu16.setStat(0.171,0.310,0.200,0.510);
//        allStat.add(mu16);
//        MatchUp mu17 = new MatchUp(degrom, nado);
//        mu17.setStat(0.100,0.100,0.150,0.250);
//        allStat.add(mu17);
//        MatchUp mu18 = new MatchUp(degrom, lindor);
//        mu18.setStat(0,0,0,0);
//        allStat.add(mu18);
//        MatchUp mu19 = new MatchUp(degrom, soto);
//        mu19.setStat(0,0,0,0);
//        allStat.add(mu19);
//        MatchUp mu20 = new MatchUp(degrom, acuna);
//        mu20.setStat(0.250,0.318,0.300,0.618);
//        allStat.add(mu20);
//
//        MatchUp mu21 = new MatchUp(jv, trout);
//        mu21.setStat(0.125,0.271,0.275,0.546);
//        allStat.add(mu21);
//        MatchUp mu22 = new MatchUp(jv, yelich);
//        mu22.setStat(0.000,0.000,0.000,0.000);
//        allStat.add(mu22);
//        MatchUp mu23 = new MatchUp(jv, belli);
//        mu23.setStat(0.333,0.333,0.333,0.667);
//        allStat.add(mu23);
//        MatchUp mu24 = new MatchUp(jv, betts);
//        mu24.setStat(0.000,0.111,0.000,0.111);
//        allStat.add(mu24);
//        MatchUp mu25 = new MatchUp(jv, breg);
//        mu25.setStat(0.000,0.000,0.000,0.000);
//        allStat.add(mu25);
//        MatchUp mu26 = new MatchUp(jv, rendon);
//        mu26.setStat(0.000,0.000,0.000,0.000);
//        allStat.add(mu26);
//        MatchUp mu27 = new MatchUp(jv, nado);
//        mu27.setStat(0.300,0.300,0.700,1.000);
//        allStat.add(mu27);
//        MatchUp mu28 = new MatchUp(jv, lindor);
//        mu28.setStat(0.313,0.389,0.500,0.889);
//        allStat.add(mu28);
//        MatchUp mu29 = new MatchUp(jv, soto);
//        mu29.setStat(0,0,0,0);
//        allStat.add(mu29);
//        MatchUp mu30 = new MatchUp(jv, acuna);
//        mu30.setStat(0,0,0,0);
//        allStat.add(mu30);
//
//        MatchUp mu31 = new MatchUp(scherz, trout);
//        mu31.setStat(0.188,0.188,0.438,0.625);
//        allStat.add(mu31);
//        MatchUp mu32 = new MatchUp(scherz, yelich);
//        mu32.setStat(0.345,0.387,0.759,1.146);
//        allStat.add(mu32);
//        MatchUp mu33 = new MatchUp(scherz, belli);
//        mu33.setStat(0.091,0.167,0.091,0.258);
//        allStat.add(mu33);
//        MatchUp mu34 = new MatchUp(scherz, betts);
//        mu34.setStat(0.000,0.333,0.000,0.333);
//        allStat.add(mu34);
//        MatchUp mu35 = new MatchUp(scherz, breg);
//        mu35.setStat(0,0,0,0);
//        allStat.add(mu35);
//        MatchUp mu36 = new MatchUp(scherz, rendon);
//        mu36.setStat(0,0,0,0);
//        allStat.add(mu36);
//        MatchUp mu37 = new MatchUp(scherz, nado);
//        mu37.setStat(0.143,0.250,0.143,0.393);
//        allStat.add(mu37);
//        MatchUp mu38 = new MatchUp(scherz, lindor);
//        mu38.setStat(0.333,0.333,0.333,0.667);
//        allStat.add(mu38);
//        MatchUp mu39 = new MatchUp(scherz, soto);
//        mu39.setStat(0,0,0,0);
//        allStat.add(mu39);
//        MatchUp mu40 = new MatchUp(scherz, acuna);
//        mu40.setStat(0.357,0.400,0.571,0.971);
//        allStat.add(mu40);
//
//        MatchUp mu41 = new MatchUp(stras, trout);
//        mu41.setStat(0,0,0,0);
//        allStat.add(mu41);
//        MatchUp mu42 = new MatchUp(stras, yelich);
//        mu42.setStat(0.238,0.319,0.333,0.652);
//        allStat.add(mu42);
//        MatchUp mu43 = new MatchUp(stras, belli);
//        mu43.setStat(0.133,0.235,0.200,0.435);
//        allStat.add(mu43);
//        MatchUp mu44 = new MatchUp(stras, betts);
//        mu44.setStat(0.333,0.333,0.667,1.000);
//        allStat.add(mu44);
//        MatchUp mu45 = new MatchUp(stras, breg);
//        mu45.setStat(0.667,0.667,1.000,1.667);
//        allStat.add(mu45);
//        MatchUp mu46 = new MatchUp(stras, rendon);
//        mu46.setStat(0,0,0,0);
//        allStat.add(mu46);
//        MatchUp mu47 = new MatchUp(stras, nado);
//        mu47.setStat(0.278,0.350,0.611,0.961);
//        allStat.add(mu47);
//        MatchUp mu48 = new MatchUp(stras, lindor);
//        mu48.setStat(0.000,0.000,0.000,0.000);
//        allStat.add(mu48);
//        MatchUp mu49 = new MatchUp(stras, soto);
//        mu49.setStat(0,0,0,0);
//        allStat.add(mu49);
//        MatchUp mu50 = new MatchUp(stras, acuna);
//        mu50.setStat(0.429,0.500,0.714,1.214);
//        allStat.add(mu50);
//
//        MatchUp mu51 = new MatchUp(flaherty, trout);
//        mu51.setStat(0,0,0,0);
//        allStat.add(mu51);
//        MatchUp mu52 = new MatchUp(flaherty, yelich);
//        mu52.setStat(0.238,0.304,0.286,0.590);
//        allStat.add(mu52);
//        MatchUp mu53 = new MatchUp(flaherty, belli);
//        mu53.setStat(0.111,0.111,0.111,0.222);
//        allStat.add(mu53);
//        MatchUp mu54 = new MatchUp(flaherty, betts);
//        mu54.setStat(0,0,0,0);
//        allStat.add(mu54);
//        MatchUp mu55 = new MatchUp(flaherty, breg);
//        mu55.setStat(0.000,0.333,0.000,0.333);
//        allStat.add(mu55);
//        MatchUp mu56 = new MatchUp(flaherty, rendon);
//        mu56.setStat(0.500,0.500,1.000,1.500);
//        allStat.add(mu56);
//        MatchUp mu57 = new MatchUp(flaherty, nado);
//        mu57.setStat(0.500,0.600,0.500,1.100);
//        allStat.add(mu57);
//        MatchUp mu58 = new MatchUp(flaherty, lindor);
//        mu58.setStat(0.000,0.333,0.000,0.333);
//        allStat.add(mu58);
//        MatchUp mu59 = new MatchUp(flaherty, soto);
//        mu59.setStat(0.000,0.667,0.000,0.667);
//        allStat.add(mu59);
//        MatchUp mu60 = new MatchUp(flaherty, acuna);
//        mu60.setStat(0.143,0.333,0.143,0.476);
//        allStat.add(mu60);
//
//        MatchUp mu61 = new MatchUp(buehler, trout);
//        mu61.setStat(0.333,0.333,0.333,0.667);
//        allStat.add(mu61);
//        MatchUp mu62 = new MatchUp(buehler, yelich);
//        mu62.setStat(0.667,0.667,0.667,1.333);
//        allStat.add(mu62);
//        MatchUp mu63 = new MatchUp(buehler, belli);
//        mu63.setStat(0,0,0,0);
//        allStat.add(mu63);
//        MatchUp mu64 = new MatchUp(buehler, betts);
//        mu64.setStat(0,0,0,0);
//        allStat.add(mu64);
//        MatchUp mu65 = new MatchUp(buehler, breg);
//        mu65.setStat(0.500,0.500,1.000,1.500);
//        allStat.add(mu65);
//        MatchUp mu66 = new MatchUp(buehler, rendon);
//        mu66.setStat(0.500,0.571,0.833,1.405);
//        allStat.add(mu66);
//        MatchUp mu67 = new MatchUp(buehler, nado);
//        mu67.setStat(0.192,0.222,0.423,0.645);
//        allStat.add(mu67);
//        MatchUp mu68 = new MatchUp(buehler, lindor);
//        mu68.setStat(0,0,0,0);
//        allStat.add(mu68);
//        MatchUp mu69 = new MatchUp(buehler, soto);
//        mu69.setStat(0.000,0.167,0.000,0.167);
//        allStat.add(mu69);
//        MatchUp mu70 = new MatchUp(buehler, acuna);
//        mu70.setStat(0.000,0.000,0.000,0.000);
//        allStat.add(mu70);
//
//        MatchUp mu71 = new MatchUp(clevy, trout);
//        mu71.setStat(0.273,0.467,0.727,1.194);
//        allStat.add(mu71);
//        MatchUp mu72 = new MatchUp(clevy, yelich);
//        mu72.setStat(0,0,0,0);
//        allStat.add(mu72);
//        MatchUp mu73 = new MatchUp(clevy, belli);
//        mu73.setStat(0,0,0,0);
//        allStat.add(mu73);
//        MatchUp mu74 = new MatchUp(clevy, betts);
//        mu74.setStat(0.571,0.667,0.857,1.524);
//        allStat.add(mu74);
//        MatchUp mu75 = new MatchUp(clevy, breg);
//        mu75.setStat(0.286,0.444,0.714,1.159);
//        allStat.add(mu75);
//        MatchUp mu76 = new MatchUp(clevy, rendon);
//        mu76.setStat(0,0,0,0);
//        allStat.add(mu76);
//        MatchUp mu77 = new MatchUp(clevy, nado);
//        mu77.setStat(0.000,0.500,0.000,0.500);
//        allStat.add(mu77);
//        MatchUp mu78 = new MatchUp(clevy, lindor);
//        mu78.setStat(0,0,0,0);
//        allStat.add(mu78);
//        MatchUp mu79 = new MatchUp(clevy, soto);
//        mu79.setStat(0.500,0.500,1.000,1.500);
//        allStat.add(mu79);
//        MatchUp mu80 = new MatchUp(clevy, acuna);
//        mu80.setStat(0,0,0,0);
//        allStat.add(mu80);
//
//        MatchUp mu81 = new MatchUp(corbin, trout);
//        mu81.setStat(0,0,0,0);
//        allStat.add(mu81);
//        MatchUp mu82 = new MatchUp(corbin, yelich);
//        mu82.setStat(0.200,0.200,0.533,0.733);
//        allStat.add(mu82);
//        MatchUp mu83 = new MatchUp(corbin, belli);
//        mu83.setStat(0.133,0.278,0.200,0.478);
//        allStat.add(mu83);
//        MatchUp mu84 = new MatchUp(corbin, betts);
//        mu84.setStat(0.000,0.000,0.000,0.000);
//        allStat.add(mu84);
//        MatchUp mu85 = new MatchUp(corbin, breg);
//        mu85.setStat(0.000,0.000,0.000,0.000);
//        allStat.add(mu85);
//        MatchUp mu86 = new MatchUp(corbin, rendon);
//        mu86.setStat(0.386,0.386,0.429,0.714);
//        allStat.add(mu86);
//        MatchUp mu87 = new MatchUp(corbin, nado);
//        mu87.setStat(0.277,0.370,0.532,0.902);
//        allStat.add(mu87);
//        MatchUp mu88 = new MatchUp(corbin, lindor);
//        mu88.setStat(0.200,0.200,0.200,0.400);
//        allStat.add(mu88);
//        MatchUp mu89 = new MatchUp(corbin, soto);
//        mu89.setStat(0,0,0,0);
//        allStat.add(mu89);
//        MatchUp mu90 = new MatchUp(corbin, acuna);
//        mu90.setStat(0.200,0.200,0.267,0.467);
//        allStat.add(mu90);
//
//        MatchUp mu91 = new MatchUp(morton, trout);
//        mu91.setStat(0.200,0.333,0.400,0.733);
//        allStat.add(mu91);
//        MatchUp mu92 = new MatchUp(morton, yelich);
//        mu92.setStat(0.400,0.400,0.400,0.800);
//        allStat.add(mu92);
//        MatchUp mu93 = new MatchUp(morton, belli);
//        mu93.setStat(0,0,0,0);
//        allStat.add(mu93);
//        MatchUp mu94 = new MatchUp(morton, betts);
//        mu94.setStat(0.300,0.391,0.400,0.791);
//        allStat.add(mu94);
//        MatchUp mu95 = new MatchUp(morton, breg);
//        mu95.setStat(0.250,0.250,0.250,0.500);
//        allStat.add(mu95);
//        MatchUp mu96 = new MatchUp(morton, rendon);
//        mu96.setStat(0.250,0.308,0.333,0.641);
//        allStat.add(mu96);
//        MatchUp mu97 = new MatchUp(morton, nado);
//        mu97.setStat(0.152,0.214,0.308,0.522);
//        allStat.add(mu97);
//        MatchUp mu98 = new MatchUp(morton, lindor);
//        mu98.setStat(0.125,0.125,0.250,0.375);
//        allStat.add(mu98);
//        MatchUp mu99 = new MatchUp(morton, soto);
//        mu99.setStat(0,0,0,0);
//        allStat.add(mu99);
//        MatchUp mu100 = new MatchUp(morton, acuna);
//        mu100.setStat(0,0,0,0);
//        allStat.add(mu100);

//        ArrayList<MatchUp> homePawayB = new ArrayList<>();
//        ArrayList<MatchUp> awayPhomeB = new ArrayList<>();
//        for (Player p: home.getPitchers()) {
//            for (Player b: away.getBatters()) {
//                homePawayB.add(new MatchUp(p, b));
//            }
//        }
//        for (Player p: away.getPitchers()) {
//            for (Player b: home.getBatters()) {
//                awayPhomeB.add(new MatchUp(p, b));
//            }
//        }



    }
}
