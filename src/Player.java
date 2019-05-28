public class Player {

    private int ID;
    private String name;
    private int lives;

    public Player(int ID, String name) {
        this.ID = ID;
        this.name = name;
        this.lives = 5;
    }

    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getLives() {
        return lives;
    }

    public void setLives(int lives) {
        this.lives = lives;
    }

    @Override
    public String toString() {
        return "Nume jucator: " + this.name + ", ID jucator: "  + this.ID + ", numar de vieti: " + this.lives;
    }
}


