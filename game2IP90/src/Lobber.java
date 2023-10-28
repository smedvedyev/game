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
    public boolean shoot(int differenceX, int differenceY) {
        return ((differenceX == 3 && differenceY == 0)||(differenceY == 0 && differenceY == 3)||(differenceY == 3 && differenceY == 3));
    }

    @Override
    public String toString(){
        return "Lobber "+player;
    }

}
