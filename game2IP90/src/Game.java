

public class Game {
    final int WIDTH = 17;
    final int LENGTH = 9;
    Field gameField;
    public void run(){
        gameField = new Field(LENGTH, WIDTH);
    }

    public static void main(String[] args) throws Exception {
        new Game().run();
    }
}
