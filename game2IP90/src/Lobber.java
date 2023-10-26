import java.awt.Point;
import javax.swing.JLabel;

public class Lobber extends Troop {

    Boolean res;
    
    Lobber(int x, int y,  int player, JLabel image){
        super(x, y ,  player, image);
        walkRange = 1;
        shootRange = 3;
    }


    @Override
    public void shoot(Point d) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Shoot'");
    }
    @Override
    public String toString(){
        return "Lobber "+player;
    }

}
