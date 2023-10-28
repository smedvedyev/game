import java.awt.Point;
import javax.swing.JLabel;

public class Tank extends Troop {

    
    Tank(int x, int y,  int player, JLabel image){
        super(x, y, player, image);
    }


    @Override
    public String toString(){
        return "Tank "+player;
    }


}
