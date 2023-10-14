import java.awt.event.*;

/**
 * Class to run our game and all the inputs.
 */
public class Game {
    final int WIDTH = 17;
    final int LENGTH = 9;
    Field gameField;
    Menu mainMenu;
    Troop[] allTroops;
    
    ClickReporter listener;    

    public void run() {
        listener = new ClickReporter();
        mainMenu = new Menu();

        if (listener.pressed) {
            gameField = new Field(LENGTH, WIDTH);
        }
        listener.pressed = false;


        // gameField = new Field(LENGTH, WIDTH);
    }

    public static void main(String[] args) throws Exception {
        new Game().run();
    }
}
