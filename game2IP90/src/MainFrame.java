import java.awt.*;

import javax.swing.*;

public class MainFrame {
    Game gm;
    JFrame mainFrame;
    JPanel panelForTiles;
    Menu menu = new Menu();
    JPanel activePanel = menu;



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
            field.generateField();
            mainFrame.add(field);
            activePanel = field;
        } else if (n == 2) {
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
