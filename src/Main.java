import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Game game = new Game();
        game.startGame();
        int x = game.getStartingPlayer();
        game.play(x,1);


    }
}
