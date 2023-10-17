import java.awt.*;

import javax.swing.*;

public class MainFrame {
    public Game gm;
    JFrame mainFrame;
    JPanel panelForTiles;
    public Menu menu = new Menu();
    JPanel activePanel = menu;

    // Rules rulesPanel = new Rules();

    Field field = new Field(this);
    

    MainFrame(Game gm){
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
        // rulesPanel.addListener(gm.cl);
        field.addListener(gm.cl);
        mainFrame.add(menu);
        // changePanel();
        mainFrame.revalidate();
        mainFrame.pack();
        mainFrame.setVisible(true);

    }

    public void changePanel(int n) {
        mainFrame.remove(activePanel);
        if (n == 1) {
            mainFrame.add(field);
            activePanel = field;
        // } else if (n == 2) {
        //     mainFrame.add(rulesPanel);
        //     activePanel = rulesPanel;
        } else if (n == 3) {
            mainFrame.add(menu);
            activePanel = menu;
        } else {
            mainFrame.add(menu);
            activePanel = menu;
        }
        mainFrame.revalidate();
        mainFrame.repaint();
        mainFrame.pack();
    }


}
