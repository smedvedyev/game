import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

/**
 * The Action pop up generator.
 */
public class PopUp extends JFrame implements ActionListener{

    Popup pop;
    JButton walk;
    JButton shoot;
    TileListener tl;

    Field field;
    JFrame frame;

    Dimension size = Toolkit.getDefaultToolkit().getScreenSize();
    int width = (int) size.getWidth();
    int height = (int) size.getHeight();

    MainFrame mainFrame;

    PopUp(Field field, int x, int y, TileListener tl) {
        // this.field = field;
        // frame = field.mf.mainFrame;
        this.tl = tl;
        JPanel popUpPanel = new JPanel();
        popUpPanel.setBackground(Color.LIGHT_GRAY);

        // JLabel popUpLabel = new JLabel("Choose your action");
        // popUpPanel.add(popUpLabel);

        walk = new JButton("WALK");
        shoot = new JButton("SHOOT");
        popUpPanel.add(walk);
        popUpPanel.add(shoot);

        walk.addActionListener(this);
        shoot.addActionListener(this);

        PopupFactory pf = new PopupFactory();
        int popx = (width / 4) + (50 * x);
        int popy = (height / 5) + (50 * y);
        pop = pf.getPopup(null, popUpPanel, popx, popy);

        pop.show();
    }

    public void hidePop() {
        pop.hide();
    }

    @Override
    public void actionPerformed(ActionEvent p) {
        if (p.getSource().equals(walk)) {
            System.out.println("He's a runner, he's a trackstar");
            tl.changeState("walk");
            pop.hide();
        } else if (p.getSource().equals(shoot)) {
            tl.changeState("shoot");
            pop.hide();
        } else {
            pop.hide();
        }
    }
}
