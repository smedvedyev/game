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

    MainFrame mainFrame;

    PopUp(int x, int y, TileListener tl) {
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
        int popx = 394 + (50 * x);
        int popy = 182 + (50 * y);
        pop = pf.getPopup(null, popUpPanel, popx, popy);

        pop.show();
    }

    @Override
    public void actionPerformed(ActionEvent p) {
        if (p.getSource().equals(walk)) {
            System.out.println("He's a runner, he's a trackstar");
            tl.changeState("walk");
            pop.hide();
        } else {
            System.out.println("Shoot the jizz and out he is");
            tl.changeState("shoot");
            pop.hide();
        }
    }
}
