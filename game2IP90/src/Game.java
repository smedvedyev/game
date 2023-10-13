

public class Game {
    final int WIDTH = 17;
    final int LENGTH = 9;
    Field gameField;
    GUI mainMenu;
    Troop[] allTroops;

    public void run() {
        gameField = new Field(LENGTH, WIDTH);
        mainMenu = new GUI(true);
    }

    public static void main(String[] args) throws Exception {
        new Game().run();
    }
}
