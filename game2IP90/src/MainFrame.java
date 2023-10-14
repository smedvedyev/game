import java.awt.*;

import javax.swing.*;

public class MainFrame {
    public Game gm;
    JFrame mainFrame;
    JPanel panelForTiles;
    public Menu menu = new Menu();
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
        menu.playButton.addActionListener(gm.cl);
        mainFrame.add(menu);
        // changePanel();
        mainFrame.revalidate();
        mainFrame.pack();
        mainFrame.setVisible(true);

    }

    public void changePanel() {
        mainFrame.remove(activePanel);
        if(activePanel instanceof Menu){
            mainFrame.add(field);
            activePanel = field;
        }else{
            mainFrame.add(menu);
            activePanel = menu;

        }
        mainFrame.revalidate();
        mainFrame.pack();
    }


}
