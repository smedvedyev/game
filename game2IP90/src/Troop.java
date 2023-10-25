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

    public abstract Boolean move(Point d);

    public abstract void shoot(Point d);
    
    
    
}   
