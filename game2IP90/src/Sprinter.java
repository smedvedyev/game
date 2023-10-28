import java.awt.Point;
import javax.swing.JLabel;

public class Sprinter extends Troop{

    Boolean res;

    Sprinter(int x, int y, int player, JLabel image) {
        super(x, y, player, image);
        walkRange = 5;
        shootRange = 1;
    }


    @Override
    public String toString(){
        return "Sprinter "+player;
    }

}
