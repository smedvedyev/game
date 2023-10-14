import java.awt.event.*;

/**
 * class to see when something is clicked.
 */
public class ClickReporter implements MouseListener {
    boolean pressed = false;
       
    @Override
    public void mouseClicked(MouseEvent e) {
        pressed = true;
    }

    @Override
    public void mousePressed(MouseEvent e) {}
    @Override
    public void mouseReleased(MouseEvent e) {}
    @Override
    public void mouseEntered(MouseEvent e) {}
    @Override
    public void mouseExited(MouseEvent e) {}
    
}
