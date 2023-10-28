import java.awt.Point;

import javax.swing.JLabel;

public abstract class Troop {
    int player;
    Point location;
    JLabel image;
    int walkRange;
    int shootRange;

    Troop (int x, int y, int player, JLabel image) {
        this.player = player;
        location = new Point(x, y);
        this.image = image;
    }

    public Boolean move(Point d){
        int diffx = Math.abs(d.x - location.x);
        int diffy = Math.abs(d.y - location.y);
        if (diffx <= walkRange && diffy <= walkRange) {
            location.x = (int) d.getX();
            location.y = (int) d.getY();
            return true;
        } else {
            return false;
        }

    }

    public boolean shoot(int differenceX, int differenceY){
        return differenceX <=shootRange && differenceY <= shootRange && (differenceX !=0 ^ differenceY!=0);
    }
    
    
    
}   
