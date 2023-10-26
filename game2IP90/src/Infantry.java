import java.awt.Point;
import javax.swing.JLabel;

public class Infantry extends Troop {

    Infantry(int x, int y, int player, JLabel image) {
        super(x, y, player, image);
        walkRange = 1;
        shootRange = 2;
    }

    @Override
    public void shoot(Point d) {
    }
    @Override
    public String toString(){
        return "Infantry "+player;
    }
}
