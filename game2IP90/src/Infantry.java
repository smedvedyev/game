import java.awt.Point;
import javax.swing.JLabel;

public class Infantry extends Troop {

    Infantry(int x, int y, int player, JLabel image) {
        super(x, y, player, image);
        walkRange = 1;
        shootRange = 2;
    }

    @Override
    public Boolean move(Point d) {
        int diffx = Math.abs(d.x - location.x);
        int diffy = Math.abs(d.y - location.y);
        if (diffx <= walkRange && diffy <= walkRange) {
            location.x = (int) d.getX();
            location.y = (int) d.getY();
            return true;
        } else {
            System.out.println("Out of range!");
            return false;
        }
    }

    @Override
    public void shoot(Point d) {
    }

}
