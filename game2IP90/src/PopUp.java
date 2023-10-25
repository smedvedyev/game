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

    MainFrame mainFrame;

    PopUp(Field field, int x, int y, TileListener tl) {
        this.field = field;
        frame = field.mf.mainFrame;
        this.tl = tl;
        JPanel popUpPanel = new JPanel();
        popUpPanel.setBackground(Color.LIGHT_GRAY);

        JLabel popUpLabel = new JLabel();
        popUpPanel.add(popUpLabel);

        walk = new JButton("WALK");
        shoot = new JButton("SHOOT");
        popUpPanel.add(walk);
        popUpPanel.add(shoot);

        walk.addActionListener(this);
        shoot.addActionListener(this);

        PopupFactory pf = new PopupFactory();
        int popx = 100 + (50 * x);
        int popy = 200 + (50 * y);
        pop = pf.getPopup(frame, popUpPanel, popx, popy);

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
            System.out.println("Shoot the jizz and out he is");
            tl.changeState("shoot");
            pop.hide();
        } else {
            pop.hide();
        }
    }
}
