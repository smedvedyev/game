import java.awt.*;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

/**
 * Main menu frame.
 */
public class Menu extends JPanel{
    
    JButton playButton;
    JButton rulesButton;

    Menu() {
        setBorder(new EmptyBorder(10, 10, 10, 10));
        setLayout(new GridBagLayout());
        setBackground(new Color(89,41,65));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridwidth = GridBagConstraints.REMAINDER;
        gbc.anchor = GridBagConstraints.NORTH;
        Label gameName = new Label("Boom beach");
        gameName.setFont(new Font("Arial", Font.PLAIN, 100));
        gameName.setForeground(Color.white);
        add(gameName, gbc);

        gbc.anchor = GridBagConstraints.CENTER;
        gbc.fill = GridBagConstraints.HORIZONTAL;

        JPanel buttons = new JPanel(new GridBagLayout());
        buttons.setBackground(new Color(89,41,65));
        playButton = new JButton("PLAY");
        playButton.setPreferredSize(new Dimension(150, 50));
        playButton.setFocusPainted(false);
        playButton.setFont(new Font("Arial", Font.BOLD, 18));
        playButton.setBackground(Color.white);


        playButton.setActionCommand("CHANGE_TO_FIELD");

      


        rulesButton = new JButton("RULES");
        rulesButton.setPreferredSize(new Dimension(150, 50));
        rulesButton.setFocusPainted(false);
        rulesButton.setFont(new Font("Arial", Font.BOLD, 18));
        rulesButton.setBackground(Color.white);

        buttons.add(playButton, gbc);
        buttons.add(Box.createVerticalStrut(100));
        buttons.add(rulesButton, gbc);

        
        gbc.weighty = 5;
        add(buttons, gbc);
    }

    public void addListener(ActionHandler al){
        playButton.addActionListener(al);
        rulesButton.addActionListener(al);
    }

}