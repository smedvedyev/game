import java.awt.Point;
import javax.swing.JLabel;

public class Tank extends Troop {

    Boolean res;
    
    Tank(int x, int y,  int player, JLabel image){
        super(x, y, player, image);
    }

    @Override
    public Boolean move(Point d) {
        int diffx = Math.abs(d.x - location.x);
        int diffy = Math.abs(d.y - location.y);
        if (diffx <= walkRange && diffy <= walkRange) {
            location.x = (int) d.getX();
            location.y = (int) d.getY();
            return res = true;
        } else {
            System.out.println("Out of range!");
            return res = false;
        }
    }

    @Override
    public void shoot(Point d) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Shoot'");
    }

}
