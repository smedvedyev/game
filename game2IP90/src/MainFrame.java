import java.awt.*;

import javax.swing.*;

public class MainFrame {
    Game gm;
    JFrame mainFrame;
    JPanel panelForTiles;
    Menu menu = new Menu();
    JPanel activePanel = menu;

    EndScreen end;
    Field field = new Field(this);

    MainFrame(Game gm) {
        this.gm = gm;
        mainFrame = new JFrame("Game");
        mainFrame.setSize(900, 650);
        mainFrame.setMinimumSize(new Dimension(900, 650));
        mainFrame.setMaximumSize(new Dimension(900, 650));
        mainFrame.getContentPane().setLayout(new BoxLayout(mainFrame.getContentPane(), BoxLayout.Y_AXIS));
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setResizable(false);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        menu.addListener(gm.cl);
        field.generateField();
        field.generateObjects();
        field.generateTroops();
        field.addListener(gm.cl);
        mainFrame.add(menu);
        mainFrame.revalidate();
        mainFrame.pack();
        mainFrame.setVisible(true);

    }

    /**
     * Change between main panels
     * 
     * @param n index of the panel
     */
    public void changePanel(int n) {
        mainFrame.remove(activePanel);
        if (n == 1) {
            mainFrame.add(field);
            activePanel = field;
        } else if (n == 2) {
            mainFrame.add(menu);
            activePanel = menu;
        } else if (n == 3) {
            end = new EndScreen(this, n);
            mainFrame.add(end);
            activePanel = end;
        } else if (n == 4) {
            end = new EndScreen(this, n);
            mainFrame.add(end);
            activePanel = end;
        } else {
            mainFrame.add(menu);
            activePanel = menu;
        }
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.pack();
    }

}
