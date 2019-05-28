import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Game {

    ArrayList<String> closingSuffixes = new ArrayList<String>(
            Arrays.asList("nt", "rt", "ct", "ee", "rb", "ns", "nc",
                    "rd", "lt", "ej", "ps", "ng", "nz", "lm", "rn", "nd"));
    Scanner scanner = new Scanner(System.in);
    private int round;
    private int startingPlayer;
    private ArrayList<Player> players = new ArrayList<Player>();

    public Game() {
        this.round = 1;
        this.startingPlayer = 0;
    }

    public ArrayList<Player> getPlayers() {
        ArrayList<Player> copy = players;
        return copy;
    }

    public void setPlayers(ArrayList<Player> players) {
        this.players = players;
    }

    public int getRound() {
        return round;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public int getStartingPlayer() {
        return startingPlayer;
    }

    public void setStartingPlayer(int startingPlayer) {
        this.startingPlayer = startingPlayer;
    }

    public void getRandom(int n) {
        //Random rand= new Random();
        int r = new Random().nextInt(n);
        this.setStartingPlayer(r);
    }

    public void startGame() {

        System.out.println("INTRODUCETI NUMARUL DE JUCATORI: ");
        int nrPlayers = scanner.nextInt();
        String nume = "";


        for (int i = 0; i < nrPlayers; i++) {
            System.out.print("INTRDUCETI NUMELE JUCATORULUI " + (i + 1) + ": ");
            nume = scanner.next();
            scanner.nextLine();
            players.add(new Player(i + 1, nume));
        }

        for (int i = 0; i < nrPlayers; i++) {
            System.out.println(players.get(i).toString());
        }

        this.getRandom(nrPlayers);
        System.out.println("Incepe jucatorul cu numele: " + players.get(getStartingPlayer()).getName());
        System.out.println("===== INCEPE JOCUL  =====");
    }

    public String getSuffix(String a) {
        return a.substring(a.length() - 2);
    }

    public void play(int n,int rnd) {
        this.round =rnd;
        //System.out.println("runda: "+this.round);
        int start = n;
        String word = "";
        int id;
        if (this.round == 1) {
            int ok=1;
            // fara inchideri
            int r = new Random().nextInt(26) + 97;
            char initialLetter = (char) r;
            System.out.println("Se va incepe cu litera: " + initialLetter);

            while (ok == 1) {
                System.out.println("Jucatorul " + players.get(start).getName());
                word = scanner.next();

//                for(char c : word.toCharArray()) {
//                    if ()
//                }

                if (word.charAt(0) != initialLetter || word.length() <= 2 ||closingSuffixes.contains(word.substring(word.length() - 2))) {
                    System.out.println("Alege alt cuvant: ");
                } else {
                    ok = 0;

                }
            }
            id = start ;
            id = (id+1) % players.size();
            //System.out.println("ID: "+id +"start"+start);
            boolean b = true;
            ok = 1;
            String ll = "";
            while (ok == 1 && id != start) {
                if (b) {
                    ll = getSuffix(word);
                }

                System.out.println("Jucatorul " + players.get(id).getName());
                word = scanner.next();
                if (word.charAt(0) != ll.charAt(0) || word.charAt(1) != ll.charAt(1) || closingSuffixes.contains(word.substring(word.length() - 2))) {
                    System.out.println("Alege alt cuvant: ");
                    b = false;
                } else {
                    b = true;

                    if (id != start) {
                        id++;
                        id = id % players.size();
                    } else {
                        ok = 0;
                    }
                }
            }
            this.round++;
        }

        if (round > 1) {
            int ok=1;
            id = start;
           // System.out.println(id);
            boolean b = true;
            String sufix = "";


            while (ok == 1) {
                if (b) {
                    sufix = getSuffix(word);
                }
                System.out.println("Urmatorul cuvant trebuie sa inceapa cu: " + sufix);
                System.out.println("Jucatorul " + players.get(id).getName());
                word = scanner.next();
                if (word.charAt(0) != sufix.charAt(0) || word.charAt(1) != sufix.charAt(1)) {
                    System.out.println("Alege alt cuvant: ");
                    b = false;
                } else {

                    if (closingSuffixes.contains(getSuffix(word))) {
                        ok=0;
                        Player p = players.get((id + 1) % players.size());
                        p.setLives(p.getLives() - 1);
                        System.out.println("=== RUNDA INCHEIATA ===");
                        for (int i = 0; i < players.size(); i++) {
                            System.out.println(players.get(i).toString());
                        }

                        if (p.getLives() == 0) {
                            if (players.size() > 1) {
                                if ((id + 1) % players.size() != players.size()) {
                                    for (int i = (id + 1) % players.size(); i <= players.size(); i++) {
                                        players.get(id-1).setID(i - 1);
                                    }
                                    players.remove(p);
                                    this.setStartingPlayer(start + 1);
                                }
                                ok = 0;
                            }

                        }
                    } else {
                        //System.out.println("print3");
                        b = true;
                        id++;
                        id = id % players.size();
                    }
                }
            }

        }


        if (players.size() == 1) {
            System.out.println("CASTIGATORUL ESTE " + players.get(0).getName().toUpperCase() + "!!");
        }
        else {
            this.play( (start+1)%players.size(),1);
        }

    }


}
