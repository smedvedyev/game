import java.awt.Point;
import javax.swing.JLabel;

public abstract class Object {

    Point location;
    JLabel image;

    Object (int x, int y, JLabel image) {
        location = new Point(x, y);
        this.image = image;
    }
}