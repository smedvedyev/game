/**
 * Class to run our game and all the inputs.
 */
public class Game {
    final int WIDTH = 17;
    final int LENGTH = 9;
    ActionHandler cl = new ActionHandler(this);

    public MainFrame gameField = new MainFrame(this);


    Troop[] allTroops;
   
    public static void main(String[] args) throws Exception {
        new Game();
    }

    

}
