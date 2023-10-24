import java.awt.Point;
import javax.swing.JLabel;

public class Capitan extends Infantry {
    public Capitan(int x, int y, int player, JLabel image){
        super(x, y, player, image);
        walkRange = 2;
        shootRange = 3;
    }

     @Override
    public void move(Point d) {
        location.x = (int) d.getX();
        location.y = (int) d.getY();
    }

    @Override
    public void shoot(Point d) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Shoot'");
    }
}
