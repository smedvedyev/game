import java.awt.Point;

import javax.swing.JLabel;

public abstract class Troop {
    int player;
    Point location;
    JLabel image;
    int walkRange;
    int shootRange;
    /**
     * Default troop constructor
     * @param x x coordinate
     * @param y y coordinate
     * @param player which player the troop belongs to
     * @param image the image of the troop
     */
    Troop (int x, int y, int player, JLabel image) {
        this.player = player;
        location = new Point(x, y);
        this.image = image;
    }

    /**
     * Check for the ability to move to the chosen point
     * @param d coords of the point
     * @return true if the point is within the range of the troop
     */
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
    /**
     * Check for the shooting range 
     * @param differenceX difference of x between troop location and enemy location
     * @param differenceY difference of y between troop location and enemy location
     * @return true if the enemy is within the shooting range
     */
    public boolean shoot(int differenceX, int differenceY){
        return differenceX <=shootRange && differenceY <= shootRange && (differenceX != 0 || differenceY!=0);
    }
    
    
    
}   
