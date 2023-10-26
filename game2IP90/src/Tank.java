import java.awt.Point;
import javax.swing.JLabel;

public class Tank extends Troop {

    
    Tank(int x, int y,  int player, JLabel image){
        super(x, y, player, image);
    }


    @Override
    public void shoot(Point d) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'Shoot'");
    }
    @Override
    public String toString(){
        return "Tank "+player;
    }


}
