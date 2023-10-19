import java.awt.Dimension;
import java.awt.Point;

import javax.swing.JPanel;

public class Tile extends JPanel{
    Troop troop = null;
    public Point d;

    Tile(int x, int y){
        d = new Point(x,y);
        setPreferredSize(new Dimension(100,100));
    }
    
    public void addTroop(Troop troop){
        this.troop = troop;
    }
}