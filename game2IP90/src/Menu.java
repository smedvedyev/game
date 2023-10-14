import java.awt.*;
import javax.swing.*;

/**
 * Main menu frame.
 */
public class Menu {
    JFrame menuFrame;
    JPanel menuPanel;
    JButton play; //play button
    JButton rules;  //button to see how to play
    JLabel title;

    /**
     * generates the menu screen with two clickable buttons.
     */
    public void generateMenu() {
        menuFrame = new JFrame("menu");
        menuPanel = new JPanel();
        play = new JButton("PLAY");
        rules = new JButton("RULES");
        title = new JLabel("BoomBeach!");

        menuFrame.setLayout(null);
        menuFrame.setSize(400, 400);
        menuFrame.setLocationRelativeTo(null);
        menuFrame.setResizable(false);

        Color f = new Color(50, 40, 80);
        menuPanel.setSize(400, 400);
        menuPanel.setBackground(f);
        menuPanel.setLayout(null);

        play.setBounds(100, 100, 200, 50);
        rules.setBounds(100, 200, 200, 50);
        title.setBounds(90, 5, 300, 50);

        Color g = new Color(0, 180, 80);
        title.setForeground(g);
        title.setFont(new Font("Arial", Font.BOLD, 35));

        menuPanel.add(play);
        menuPanel.add(rules);
        menuPanel.add(title);

        menuFrame.add(menuPanel);
        menuFrame.setVisible(true);
        menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    Menu() {
        generateMenu();
    }
}